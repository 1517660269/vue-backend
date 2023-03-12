package com.example.vuebackend.controller;

import com.example.vuebackend.pojo.Farm;
import com.example.vuebackend.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void edit(@RequestBody Farm farm) {
        farmService.edit(farm);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam("ids") List<String> ids) {
        farmService.delete(ids);
    }

}
