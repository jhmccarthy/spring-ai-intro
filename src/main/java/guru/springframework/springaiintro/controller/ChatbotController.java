package guru.springframework.springaiintro.controller;

import guru.springframework.springaiintro.model.*;
import guru.springframework.springaiintro.service.ChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/api/ask")
    public ChatResponse askQuestion(@RequestBody ChatRequest chatRequest) {
        return chatbotService.chat(chatRequest);
    }

    @PostMapping("/api/capital")
    public ChatResponse getCapital(@RequestBody ChatRequest chatRequest) {
        return chatbotService.getCapital(chatRequest);
    }

    @PostMapping("/api/capitalWithInfo")
    public ChatResponse getCapitalWithInfo(@RequestBody ChatRequest chatRequest) {
        return chatbotService.getCapital(chatRequest);
    }
}
