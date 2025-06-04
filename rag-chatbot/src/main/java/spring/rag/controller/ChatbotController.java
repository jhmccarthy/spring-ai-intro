package spring.rag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.rag.model.ChatRequest;
import spring.rag.model.ChatResponse;
import spring.rag.service.ChatbotService;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatbotService the RAG chat service
     */
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> ragChat(@RequestBody ChatRequest chatRequest) {
        var response = chatbotService.ragChat(chatRequest);

        return ResponseEntity.ok(response);
    }
}
