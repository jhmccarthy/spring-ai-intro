package guru.springframework.springaiintro.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatClientConfig {
    private final ChatMemory chatMemory;

    public ChatClientConfig(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
    }

    @Bean
    public ChatClient chatClient(
            ChatModel chatModel,
            @Value("classpath:prompts/chatbot-system-prompt.st") Resource systemPrompt
    ) {
        // @formatter:off
        var client = ChatClient
                .builder(chatModel)
                .defaultSystem(systemPrompt)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory));
        // @formatter:on

        return client.build();
    }
}
