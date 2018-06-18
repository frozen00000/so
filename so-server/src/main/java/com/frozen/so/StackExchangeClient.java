package com.frozen.so;

import com.frozen.so.domain.ItemPage;
import reactor.core.publisher.Mono;

interface StackExchangeClient {

    Mono<ItemPage> search(String query, String page, String pageSize);

}
