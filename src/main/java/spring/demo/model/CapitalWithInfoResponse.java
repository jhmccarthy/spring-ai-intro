package spring.demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.UUID;

public record CapitalWithInfoResponse(
        UUID chatId,
        @JsonPropertyDescription("The name of the city") String answer,
        @JsonPropertyDescription("The population of the city") String population,
        @JsonPropertyDescription("The region the city is located in") String region,
        @JsonPropertyDescription("The primary language spoken") String language,
        @JsonPropertyDescription("The currency used") String currency
) {
}
