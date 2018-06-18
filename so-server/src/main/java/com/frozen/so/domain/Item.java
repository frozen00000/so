package com.frozen.so.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private List<String> tags;
    private Owner owner;
    private Boolean isAnswered;
    private Integer answerCount;
    private Integer score;
    private Integer creationDate;
    private String link;
    private String title;

}