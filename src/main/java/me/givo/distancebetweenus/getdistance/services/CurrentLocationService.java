package me.givo.distancebetweenus.getdistance.services;

import me.givo.distancebetweenus.getdistance.models.ApiCurrentLocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrentLocationService {

    private final WebClient.Builder webClient;

    // @Value("${DISTANCE_KEY}")
    private final String key = "cf7a1150-4976-11ec-a918-7300defbdb2f";

    public CurrentLocationService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    public ApiCurrentLocationResponse getCurrentLocation(String requestIP) {

        String url = "https://api.freegeoip.app/json/";
        return webClient.baseUrl(url).build().get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestIP)
                        .queryParam("apikey", key)
                        .build())
                .retrieve()
                .bodyToMono(ApiCurrentLocationResponse.class)
                .block();
    }
}
