package guru.springframework.springaiintro.service;

import guru.springframework.springaiintro.model.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChatbotFunctionServiceTest {
    @Autowired
    private ChatbotFunctionService service;

    @Test
    void chat() {
        var question = new ChatRequest(null, "What is current population of New Zealand?");

        var result = service.chat(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}