package guru.springframework.springaiintro.model;

import java.util.UUID;

public record ChatResponse(UUID chatId, String answer) {
}
