package spring.demo.service;

import spring.demo.model.ChatRequest;
import spring.demo.model.ChatResponse;
import spring.demo.model.CountryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * This service demonstrates how to use a function or method as a tool when a model needs to interact with a set of
 * tools to augment its capabilities.
 */
@Service
@Slf4j
public class ToolChatbotService {
    private final ChatClient chatClient;

    @Value("${ai-app.api-ninjas-key}")
    private final String apiNinjasKey = System.getenv("API_NINJAS_KEY");

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatClient the chat client
     */
    public ToolChatbotService(
            @Qualifier("chatClient") ChatClient chatClient
    ) {
        this.chatClient = chatClient;
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
