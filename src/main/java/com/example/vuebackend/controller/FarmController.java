package com.example.vuebackend.controller;

import com.example.vuebackend.pojo.Farm;
import com.example.vuebackend.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping("/findAll")
    @ResponseBody
    public Page<Farm> findAll(FindAllFarmQuery query) {
        return farmService.findAll(query);
    }

    @PostMapping("/edit")
    @ResponseBody
    public int edit(@RequestBody Farm farm) {
        farmService.edit(farm);
        return 1;
    }

}
