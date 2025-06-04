package spring.toolschat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.toolschat.model.ChatRequest;
import spring.toolschat.model.ChatResponse;
import spring.toolschat.service.ChatbotService;

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

    @PostMapping("/chat/function")
    public ResponseEntity<ChatResponse> chatFunction(@RequestBody ChatRequest chatRequest) {
        var response = chatbotService.chatFunction(chatRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/chat/tool")
    public ResponseEntity<ChatResponse> chatTool(@RequestBody ChatRequest chatRequest) {
        var response = chatbotService.chatTool(chatRequest);

        return ResponseEntity.ok(response);
    }
}
