package spring.toolschat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;
import spring.toolschat.model.CountryRequest;
import spring.toolschat.model.CountryResponse;

import java.util.function.Function;

@Slf4j
public class CountryServiceFunction implements Function<CountryRequest, CountryResponse> {
    public static final String COUNTRY_URL = "https://api.api-ninjas.com/v1/country";
    private final RestClient restClient;

    /**
     * Default constructor used for Spring dependency injection.
     *
     * @param apiNinjasKey the key for accessing the API Ninjas services
     */
    public CountryServiceFunction(String apiNinjasKey) {
        restClient = RestClient.builder()
                .baseUrl(COUNTRY_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                })
                .build();
    }

    @Override
    public CountryResponse apply(CountryRequest countryRequest) {
        var response = restClient.get()
                .uri(uriBuilder -> {
                    log.info("Building URI for country request: {}", countryRequest);
                    return uriBuilder
                            .queryParam("name", countryRequest.name())
                            .build();
                })
                .retrieve().body(CountryResponse[].class);

        if (response == null) {
            log.error("Country API response is null");
            return null;
        }

        return response[0];
    }
}
