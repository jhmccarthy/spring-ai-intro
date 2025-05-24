package spring.demo.model;

import org.springframework.lang.Nullable;

import java.util.UUID;

public record ChatRequest(
        @Nullable UUID chatId,
        String question
) {
}
