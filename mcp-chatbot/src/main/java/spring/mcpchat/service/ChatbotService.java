package spring.mcpchat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;
import spring.mcpchat.model.ChatRequest;
import spring.mcpchat.model.ChatResponse;

import java.util.Optional;
import java.util.UUID;

/**
 * This service demonstrates how to use an MCP (Model Context Protocol) client to extend the functionality of the chat
 * client.
 * We're using the Brave Search MCP server to perform web searches when the chatbot needs to retrieve current
 * information.
 */
@Service
public class ChatbotService {
    private final ChatClient chatClient;
    private final SyncMcpToolCallbackProvider toolCallbackProvider;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatClient           the chat client
     * @param toolCallbackProvider the tool provider for the MCP servers
     */
    public ChatbotService(
            ChatClient chatClient,
            SyncMcpToolCallbackProvider toolCallbackProvider
    ) {
        this.chatClient = chatClient;
        this.toolCallbackProvider = toolCallbackProvider;
    }

    public ChatResponse chat(ChatRequest chatRequest) {
        // @formatter:off
        var chatId = Optional
                .ofNullable(chatRequest.chatId())
                .orElse(UUID.randomUUID());
        // @formatter:on

        // @formatter:off
        var answer = chatClient
                .prompt()
                .user(chatRequest.question())
                .advisors(advisorSpec ->
                        advisorSpec
                                .param("chat_memory_conversation_id", chatId))
                .toolCallbacks(toolCallbackProvider)
                .call()
                .content();
        // @formatter:on

        return new ChatResponse(chatId, answer);
    }
}
