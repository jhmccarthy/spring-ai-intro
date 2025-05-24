package spring.demo.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Country API request")
public record CountryRequest(
        @JsonProperty(required = true, value = "name")
        @JsonPropertyDescription("Plain English name, 2-letter ISO-3166 alpha-2, or 3-letter ISO-3166 alpha-3 code of country.")
        String name
) {
}
