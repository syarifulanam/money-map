package com.syarifulanam.moneymap.controller;

import com.syarifulanam.moneymap.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/foods/save")
    public String saveFood(@RequestParam("name") String name) {
        foodService.saveFood(name);
        return "OK";
    }

}
