package com.frozen.so.domain;

import lombok.Data;

import java.util.List;

@Data
public class ItemPage {

    private List<Item> items;
    private Boolean hasMore;
    private Integer quotaMax;
    private Integer quotaRemaining;

}