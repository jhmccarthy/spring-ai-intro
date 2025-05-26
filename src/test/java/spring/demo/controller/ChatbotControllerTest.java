package spring.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import spring.demo.model.ChatRequest;
import spring.demo.model.ChatResponse;
import spring.demo.service.ChatbotService;
import spring.demo.service.McpChatbotService;
import spring.demo.service.RagChatbotService;
import spring.demo.service.ToolChatbotService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatbotControllerTest {
    private static final String RESPONSE = "response";
    private static final UUID RANDOM_UUID = UUID.randomUUID();
    // @formatter:off
    private static final String QUESTION = """
        {
            "question": "What is Spring AI?"
         }
    """;
    // @formatter:on

    @Mock
    private ChatbotService chatbotService;
    @Mock
    private RagChatbotService ragChatbotService;
    @Mock
    private ToolChatbotService toolChatbotService;
    @Mock
    private McpChatbotService mcpChatbotService;

    @InjectMocks
    private ChatbotController controller;

    @Test
    void chat() {
        when(chatbotService.chat(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.chat(new ChatRequest(null, QUESTION));
        verify(chatbotService).chat(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }

    @Test
    void ragChat() {
        when(ragChatbotService.ragChat(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.ragChat(new ChatRequest(null, QUESTION));
        verify(ragChatbotService).ragChat(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }

    @Test
    void mcpChat() {
        when(mcpChatbotService.chatMcp(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.mcpChat(new ChatRequest(null, QUESTION));
        verify(mcpChatbotService).chatMcp(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }

    @Test
    void getCountry() {
        when(toolChatbotService.chatFunction(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.toolChat(new ChatRequest(null, QUESTION));
        verify(toolChatbotService).chatFunction(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }
}
