package spring.toolschat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.toolschat.model.ChatRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChatbotServiceTest {
    @Autowired
    private ChatbotService service;

    @Test
    void populationOfCountry() {
        var question = new ChatRequest(null, "What is current population of New Zealand?");

        var result = service.chatFunction(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }

    @Test
    void whatDayIsTomorrow() {
        var question = new ChatRequest(null, "What day is tomorrow?");

        var result = service.chatTool(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }

    @Test
    void setAlarmForTomorrow() {
        var question = new ChatRequest(null, "Set an alarm 2 hours from now.");

        var result = service.chatTool(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
