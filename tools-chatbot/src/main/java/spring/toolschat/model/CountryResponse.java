package spring.toolschat.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Country API response")
@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryResponse(
        @JsonPropertyDescription("Capital of the country") String capital,
        @JsonPropertyDescription("Current population of the country") Integer population,
        @JsonPropertyDescription("Name of the Country") String name,
        @JsonPropertyDescription("Region the country is in") String region,
        @JsonPropertyDescription("Number of tourists to the country") Integer tourists
) {
}
