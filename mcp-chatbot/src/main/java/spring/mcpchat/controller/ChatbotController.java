package spring.mcpchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.mcpchat.model.ChatRequest;
import spring.mcpchat.model.ChatResponse;
import spring.mcpchat.service.ChatbotService;

@RestController
public class ChatbotController {
    private final ChatbotService chatbotService;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param chatbotService the basic chat service
     */
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {
        var response = chatbotService.chat(chatRequest);

        return ResponseEntity.ok(response);
    }
}
