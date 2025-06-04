package spring.toolschat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.toolschat.model.ChatRequest;
import spring.toolschat.model.ChatResponse;
import spring.toolschat.model.CountryRequest;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatbotService {
    private final ChatClient chatClient;
    private final String apiNinjasKey;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatClient   the chat client
     * @param apiNinjasKey the API key
     */
    public ChatbotService(
            ChatClient chatClient,
            @Value("${ai-app.api-ninjas-key}") String apiNinjasKey
    ) {
        this.chatClient = chatClient;
        this.apiNinjasKey = apiNinjasKey;
    }

    /**
     * Use a function as a tool to respond to a request from a user.
     *
     * @param chatRequest the user's request
     * @return the chatbot's response
     */
    public ChatResponse chatFunction(ChatRequest chatRequest) {
        var countryFunction = FunctionToolCallback
                .builder("CountryInformation", new CountryServiceFunction(apiNinjasKey))
                .description("Get current information about a country.")
                .inputType(CountryRequest.class)
                .build();

        // @formatter:off
        var chatId = Optional
                .ofNullable(chatRequest.chatId())
                .orElse(UUID.randomUUID());
        // @formatter:on

        var prompt = new PromptTemplate(chatRequest.question()).createMessage();

        // @formatter:off
        var answer = chatClient
                .prompt(prompt.toString())
                .user(chatRequest.question())
                .advisors(advisorSpec ->
                        advisorSpec
                                .param("chat_memory_conversation_id", chatId))
                .toolCallbacks(countryFunction)
                .call()
                .content();
        // @formatter:on

        return new ChatResponse(chatId, answer);
    }

    /**
     * Use a method as a tool to respond to a request from a user.
     *
     * @param chatRequest the user's request
     * @return the chatbot's response
     */
    public ChatResponse chatTool(ChatRequest chatRequest) {
        // @formatter:off
        var chatId = Optional
                .ofNullable(chatRequest.chatId())
                .orElse(UUID.randomUUID());
        // @formatter:on

        var prompt = new PromptTemplate(chatRequest.question()).createMessage();

        // @formatter:off
        var answer = chatClient
                .prompt(prompt.toString())
                .user(chatRequest.question())
                .advisors(advisorSpec ->
                        advisorSpec
                                .param("chat_memory_conversation_id", chatId))
                .tools(new DateTimeTools())
                .call()
                .content();
        // @formatter:on

        return new ChatResponse(chatId, answer);
    }
}
