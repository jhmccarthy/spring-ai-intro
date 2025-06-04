package spring.rag.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import spring.rag.model.ChatRequest;
import spring.rag.model.ChatResponse;
import spring.rag.service.ChatbotService;

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
    void chat() {
        when(chatbotService.ragChat(any(ChatRequest.class))).thenReturn(new ChatResponse(RANDOM_UUID, RESPONSE));

        var result = controller.ragChat(new ChatRequest(null, QUESTION));
        verify(chatbotService).ragChat(any(ChatRequest.class));

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

        assertEquals(RESPONSE, result.getBody().answer());
    }
}
