package spring.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.model.ChatRequest;
import spring.demo.model.ChatResponse;
import spring.demo.service.ChatbotService;
import spring.demo.service.McpChatbotService;
import spring.demo.service.RagChatbotService;
import spring.demo.service.ToolChatbotService;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;
    private final RagChatbotService ragChatbotService;
    private final ToolChatbotService toolChatbotService;
    private final McpChatbotService mcpChatbotService;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatbotService     the basic chat service
     * @param ragChatbotService  the service that provide RAG support for the chatbot
     * @param toolChatbotService the service that provides tooling support for the chatbot
     * @param mcpChatbotService  the service that provides MCP client support for the chatbot
     */
    public ChatbotController(
            ChatbotService chatbotService,
            RagChatbotService ragChatbotService,
            ToolChatbotService toolChatbotService,
            McpChatbotService mcpChatbotService
    ) {
        this.chatbotService = chatbotService;
        this.ragChatbotService = ragChatbotService;
        this.toolChatbotService = toolChatbotService;
        this.mcpChatbotService = mcpChatbotService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {
        var response = chatbotService.chat(chatRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/rag-chat")
    public ResponseEntity<ChatResponse> ragChat(@RequestBody ChatRequest chatRequest) {
        var response = ragChatbotService.ragChat(chatRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/mcp-chat")
    public ResponseEntity<ChatResponse> mcpChat(@RequestBody ChatRequest chatRequest) {
        var response = mcpChatbotService.chatMcp(chatRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/tool")
    public ResponseEntity<ChatResponse> toolChat(@RequestBody ChatRequest chatRequest) {
        var response = toolChatbotService.chatFunction(chatRequest);

        return ResponseEntity.ok(response);
    }
}
