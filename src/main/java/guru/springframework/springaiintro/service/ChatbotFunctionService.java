package guru.springframework.springaiintro.service;

import guru.springframework.springaiintro.function.CountryServiceFunction;
import guru.springframework.springaiintro.model.ChatRequest;
import guru.springframework.springaiintro.model.ChatResponse;
import guru.springframework.springaiintro.model.CountryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ChatbotFunctionService {
    private final ChatClient chatClient;

    @Value("${ai-app.api-ninjas-key}")
    private final String apiNinjasKey = System.getenv("API_NINJAS_KEY");

    public ChatbotFunctionService(
            ChatClient chatClient
    ) {
        this.chatClient = chatClient;
    }

    public ChatResponse chat(ChatRequest chatRequest) {
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
}
