package spring.toolschat.model;

import java.util.UUID;

public record ChatResponse(
        UUID chatId,
        String answer
) {
}
