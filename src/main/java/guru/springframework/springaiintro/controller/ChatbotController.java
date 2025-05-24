package guru.springframework.springaiintro.controller;

import guru.springframework.springaiintro.model.ChatRequest;
import guru.springframework.springaiintro.model.ChatResponse;
import guru.springframework.springaiintro.service.ChatbotFunctionService;
import guru.springframework.springaiintro.service.ChatbotService;
import guru.springframework.springaiintro.service.RagChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;
    private final RagChatbotService ragChatbotService;
    private final ChatbotFunctionService chatbotFunctionService;

    public ChatbotController(
            ChatbotService chatbotService,
            RagChatbotService ragChatbotService,
            ChatbotFunctionService chatbotFunctionService
    ) {
        this.chatbotService = chatbotService;
        this.ragChatbotService = ragChatbotService;
        this.chatbotFunctionService = chatbotFunctionService;
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
        return chatbotFunctionService.chat(chatRequest);
    }
}
