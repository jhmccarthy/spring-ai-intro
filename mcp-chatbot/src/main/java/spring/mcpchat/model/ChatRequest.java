package spring.mcpchat.model;

import org.jspecify.annotations.Nullable;

import java.util.UUID;

public record ChatRequest(
        @Nullable UUID chatId,
        String question
) {
}
