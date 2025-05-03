package guru.springframework.springaiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.model.ChatRequest;
import guru.springframework.springaiintro.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ChatbotService {
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Value("classpath:prompts/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:prompts/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfo;

    public ChatbotService(ChatClient chatClient, ObjectMapper objectMapper) {
        this.chatClient = chatClient;
        this.objectMapper = objectMapper;
    }

    public ChatResponse chat(ChatRequest chatRequest) {
        return chat(chatRequest, new Prompt());
    }

    public ChatResponse chat(ChatRequest chatRequest, Prompt prompt) {
        // @formatter:off
        var chatId = Optional
                .ofNullable(chatRequest.chatId())
                .orElse(UUID.randomUUID());
        // @formatter:on

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

    /**
     * Get the capital of for a state or country from the LLM.
     *
     * @param chatRequest the state or country request
     * @return the answer
     */
    public ChatResponse getCapital(ChatRequest chatRequest) {
        var promptTemplate = new PromptTemplate(getCapitalPrompt);
        var prompt = promptTemplate.create(Map.of("stateOrCountry", chatRequest.question()));

        var response = chat(chatRequest, prompt);
        log.info("Response: {}", response.answer());

        String jsonString;

        try {
            var jsonNode = objectMapper.readTree(response.answer());
            jsonString = jsonNode.get("answer").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ChatResponse(response.chatId(), jsonString);
    }

    /**
     * Get the capital of for a state or country from the LLM with additional information.
     *
     * @param chatRequest the state or country request
     * @return the answer
     */
    public ChatResponse getCapitalWithInfo(ChatRequest chatRequest) {
        var promptTemplate = new PromptTemplate(getCapitalWithInfo);
        var prompt = promptTemplate.create(Map.of("stateOrCountry", chatRequest.question()));

        return chat(chatRequest, prompt);
    }
}
