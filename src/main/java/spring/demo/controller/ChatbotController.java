package spring.demo.controller;

import spring.demo.model.ChatRequest;
import spring.demo.model.ChatResponse;
import spring.demo.service.ToolChatbotService;
import spring.demo.service.ChatbotService;
import spring.demo.service.RagChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;
    private final RagChatbotService ragChatbotService;
    private final ToolChatbotService toolChatbotService;

    public ChatbotController(
            ChatbotService chatbotService,
            RagChatbotService ragChatbotService,
            ToolChatbotService toolChatbotService
    ) {
        this.chatbotService = chatbotService;
        this.ragChatbotService = ragChatbotService;
        this.toolChatbotService = toolChatbotService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest chatRequest) {
        return chatbotService.chat(chatRequest);
    }

    @PostMapping("/rag-chat")
    public ChatResponse ragChat(@RequestBody ChatRequest chatRequest) {
        return ragChatbotService.ragChat(chatRequest);
    }

    @PostMapping("/country")
    public ChatResponse getCountry(@RequestBody ChatRequest chatRequest) {
        return toolChatbotService.chatFunction(chatRequest);
    }
}
