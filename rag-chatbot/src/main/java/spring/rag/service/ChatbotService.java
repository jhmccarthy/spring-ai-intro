package spring.rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.rag.model.ChatRequest;
import spring.rag.model.ChatResponse;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatbotService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final Resource ragPrompt;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatClient  the chat client
     * @param vectorStore the vector store that will hold the documents
     * @param ragPrompt   the prompt string for the chat client
     */
    public ChatbotService(
            ChatClient chatClient,
            SimpleVectorStore vectorStore,
            @Value("classpath:prompts/rag-meta-prompt.st") Resource ragPrompt
    ) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.ragPrompt = ragPrompt;
    }

    public ChatResponse ragChat(ChatRequest chatRequest) {
        // @formatter:off
        var chatId = Optional
                .ofNullable(chatRequest.chatId())
                .orElse(UUID.randomUUID());
        // @formatter:on

        var documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(chatRequest.question())
                        .topK(4)
                        .build()
        );

        var contentList = Objects.requireNonNull(documents).stream()
                .map(Document::getText)
                .toList();

        var promptTemplate = new PromptTemplate(ragPrompt);
        var prompt = promptTemplate.create(
                Map.of(
                        "input", chatRequest.question(),
                        "documents", String.join("\n", contentList)
                )
        );

        // @formatter:off
        var answer = chatClient
                .prompt(prompt)
                .user(chatRequest.question())
                .advisors(advisorSpec ->
                        advisorSpec
                                .param("chat_memory_conversation_id", chatId))
                .call()
                .content();
        // @formatter:on

        return new ChatResponse(chatId, answer);
    }
}
