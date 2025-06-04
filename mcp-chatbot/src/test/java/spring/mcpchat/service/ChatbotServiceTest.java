package spring.mcpchat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.mcpchat.model.ChatRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChatbotServiceTest {
    @Autowired
    private ChatbotService service;

    @Test
    void chat() {
        var question = new ChatRequest(null, "What's the current weather at latitude/longitude 41.879895599412045, -87.62928376877025?");

        var result = service.chat(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
