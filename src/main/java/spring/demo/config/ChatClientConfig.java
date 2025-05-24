package spring.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Configure the {@code ChatClient} that is used by the chatbot.
 */
@Configuration
public class ChatClientConfig {
    private final ChatMemory chatMemory;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatMemory the contract for storing and managing the memory of chat conversations
     */
    public ChatClientConfig(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
    }

    /**
     * Create a simple {@code ChatClient} that maintains the structure of the conversation history.
     *
     * @param chatModel the applicable chat model
     * @return the chat client
     */
    @Bean(name = "chatClient")
    public ChatClient chatClient(
            ChatModel chatModel
    ) {
        // @formatter:off
        var client = ChatClient
                .builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        // @formatter:on

        return client.build();
    }

    /**
     * Create a {@code ChatClient} uses a system prompt to define a personality for the chat client. The chat client
     * also maintains the structure of the conversation history.
     *
     * @param chatModel the applicable chat model
     * @return the chat client
     */
    @Bean(name = "chatClientWithPersonality", defaultCandidate = false)
    public ChatClient chatClientWithPersonality(
            ChatModel chatModel,
            @Value("classpath:prompts/chatbot-system-prompt.st") Resource systemPrompt
    ) {
        // @formatter:off
        var client = ChatClient
                .builder(chatModel)
                .defaultSystem(systemPrompt)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        // @formatter:on

        return client.build();
    }
}
