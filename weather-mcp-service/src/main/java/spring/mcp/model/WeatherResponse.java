package spring.mcp.model;

import com.fasterxml.jackson.annotation.*;

@JsonClassDescription("Weather API response")
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
        @JsonPropertyDescription("Current temperature in Celsius.") long temp,
        @JsonPropertyDescription("What the temperature feels like in Celsius.") long feels_like,
        @JsonPropertyDescription("Current humidity percentage.") long humidity,
        @JsonPropertyDescription("Minimum temperature in Celsius.") long min_temp,
        @JsonPropertyDescription("Maximum temperature in Celsius.") long max_temp,
        @JsonPropertyDescription("Wind speed in meters per second.") double wind_speed,
        @JsonPropertyDescription("Wind direction in degrees.") long wind_degrees,
        @JsonPropertyDescription("Sunrise time in Unix timestamp.") long sunrise,
        @JsonPropertyDescription("Sunset time in Unix timestamp.") long sunset
) {
}
