package spring.mcp.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import spring.mcp.model.WeatherResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
@Slf4j
public class WeatherTool {
    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";
    private final RestClient restClient;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param apiNinjasKey the key for accessing the API Ninjas services
     */
    public WeatherTool(@Value("${ai-app.api-ninjas-key}") String apiNinjasKey) {
        restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                })
                .build();
    }

    public static void main(String[] args) {
        var client = new WeatherTool(System.getenv("API_NINJAS_KEY"));
        client.getWeatherForecastByLocation(41.879895599412045, -87.62928376877025);
    }

    /**
     * Get the weather forecast for a location using their latitude and longitude.
     *
     * @param latitude  the latitude of the desired location
     * @param longitude the longitude of the desired location
     * @return the weather forecast
     */
    @Tool(description = "Get the weather forecast for a specific latitude/longitude")
    public String getWeatherForecastByLocation(double latitude, double longitude) {
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .build()
                )
                .retrieve().body(WeatherResponse.class);

        if (response == null) {
            log.error("Weather API response is null");
            return null;
        }

        log.info("Weather API response: {}", response);

        var forecast = String.format("""
                        Temperature: %s
                        Feels like temperature: %s
                        Sunrise: %s
                        Sunset: %s
                        """,
                response.temp(), response.feels_like(), convertUnixTimestamp(response.sunrise()), convertUnixTimestamp(response.sunset()));
        log.info("Weather forecast: {}", forecast);

        return forecast;
    }

    /**
     * Convert the UNIX timestamp into a date/time.
     *
     * @param timestamp the UNIX timestamp
     * @return the locale date and time
     */
    private LocalDateTime convertUnixTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());
    }
}
