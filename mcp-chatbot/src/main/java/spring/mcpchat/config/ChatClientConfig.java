package spring.mcpchat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        // @formatter:off
        var client = ChatClient
                .builder(chatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
        // @formatter:on

        return client.build();
    }
}
