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

    @PostMapping("/api/request")
    public ChatResponse request(@RequestBody ChatRequest chatRequest) {
        return chatbotService.chat(chatRequest);
    }
}
