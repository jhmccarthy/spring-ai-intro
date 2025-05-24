package spring.demo.service;

import spring.demo.model.CapitalResponse;
import spring.demo.model.CapitalWithInfoResponse;
import spring.demo.model.ChatRequest;
import spring.demo.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final Resource getCapitalPrompt;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatClient the chat client
     * @param getCapitalPrompt the prompt string for the {@code getCapital} request
     */
    public ChatbotService(
            @Qualifier("chatClientWithPersonality") ChatClient chatClient,
            @Value("classpath:prompts/get-capital-prompt.st") Resource getCapitalPrompt
    ) {
        this.chatClient = chatClient;
        this.getCapitalPrompt = getCapitalPrompt;
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
    public CapitalResponse getCapital(ChatRequest chatRequest) {
        var converter = new BeanOutputConverter<>(CapitalResponse.class);
        var format = converter.getFormat();

        var promptTemplate = new PromptTemplate(getCapitalPrompt);
        var prompt = promptTemplate.create(
                Map.of(
                        "stateOrCountry", chatRequest.question(),
                        "format", format
                )
        );

        var answer = chatClient
                .prompt(prompt)
                .user(chatRequest.question())
                .call()
                .content();
        log.info("Response: {}", answer);

        if (answer == null) {
            throw new RuntimeException("No response received");
        }

        return converter.convert(answer);
    }

    /**
     * Get the capital of for a state or country from the LLM with additional information.
     *
     * @param chatRequest the state or country request
     * @return the answer
     */
    public CapitalWithInfoResponse getCapitalWithInfo(ChatRequest chatRequest) {
        var converter = new BeanOutputConverter<>(CapitalWithInfoResponse.class);
        var format = converter.getFormat();

        var promptTemplate = new PromptTemplate(getCapitalPrompt);
        var prompt = promptTemplate.create(
                Map.of(
                        "stateOrCountry", chatRequest.question(),
                        "format", format
                )
        );

        var answer = chatClient
                .prompt(prompt)
                .user(chatRequest.question())
                .call()
                .content();
        log.info("Response: {}", answer);

        if (answer == null) {
            throw new RuntimeException("No response received");
        }

        return converter.convert(answer);
    }
}
