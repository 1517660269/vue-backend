package com.example.vuebackend.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAllFarmQuery {

    private int page = 0;

    private int pageSize = 10;

    private String name;

    private String startTime;

    private String endTime;
}
