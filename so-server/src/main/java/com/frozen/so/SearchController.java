package com.frozen.so;

import com.frozen.so.domain.ItemPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
class SearchController {

    private final StackExchangeClient stackexchangeClient;

    @GetMapping("/api/search")
    public Mono<ItemPage> search(@RequestParam String query, @RequestParam String page, @RequestParam String pageSize) {
        return stackexchangeClient.search(query, page, pageSize);
    }

}
