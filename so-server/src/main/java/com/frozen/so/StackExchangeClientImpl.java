package com.frozen.so;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.frozen.so.domain.ItemPage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class StackExchangeClientImpl implements StackExchangeClient {

    private final WebClient webClient = WebClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();

    StackExchangeClientImpl() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    private ItemPage convert(String body) {
        return objectMapper.readValue(body, ItemPage.class);
    }

    @Override
    public Mono<ItemPage> search(String query, String page, String pageSize) {
        return webClient.get()
                .uri(builder -> builder.scheme("http")
                        .host("api.stackexchange.com").path("2.2/search")
                        .queryParam("intitle", query)
                        .queryParam("order", "desc")
                        .queryParam("sort", "activity")
                        .queryParam("site", "stackoverflow")
                        .queryParam("page", page)
                        .queryParam("pageSize", pageSize)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::convert);
    }

}
