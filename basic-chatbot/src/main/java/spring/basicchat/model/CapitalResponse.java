package spring.basicchat.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.UUID;

public record CapitalResponse(
        UUID chatId,
        @JsonPropertyDescription("The name of the city") String answer
) {
}
