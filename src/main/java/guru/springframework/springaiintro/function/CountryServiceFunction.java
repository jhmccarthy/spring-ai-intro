package guru.springframework.springaiintro.function;

import guru.springframework.springaiintro.model.CountryRequest;
import guru.springframework.springaiintro.model.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
public class CountryServiceFunction implements Function<CountryRequest, CountryResponse> {
    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/country";

    private final String apiNinjaKey;

    public CountryServiceFunction(String apiNinjaKey) {
        this.apiNinjaKey = apiNinjaKey;
    }

    @Override
    public CountryResponse apply(CountryRequest countryRequest) {
        var restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjaKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                })
                .build();

        var response = restClient.get()
                .uri(uriBuilder -> {
                    log.info("Building URI for country request: {}", countryRequest);
                    return uriBuilder
                            .queryParam("name", countryRequest.name())
                            .build();
                })
                .retrieve().body(CountryResponse[].class);

        if (response == null) {
            log.error("Country response is null");
            return null;
        }

        log.info("Country response: {}", response[0]);

        return response[0];
    }
}
