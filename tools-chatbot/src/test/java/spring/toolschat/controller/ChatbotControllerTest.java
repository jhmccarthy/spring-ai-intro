package spring.toolschat.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import spring.toolschat.model.ChatRequest;
import spring.toolschat.model.ChatResponse;
import spring.toolschat.service.ChatbotService;

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

    @InjectMocks
    private ChatbotController controller;

    @Test
    void chatFunction() {
        when(chatbotService.chatFunction(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.chatFunction(new ChatRequest(null, QUESTION));
        verify(chatbotService).chatFunction(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }

    @Test
    void chatTool() {
        when(chatbotService.chatTool(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.chatTool(new ChatRequest(null, QUESTION));
        verify(chatbotService).chatTool(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }
}
