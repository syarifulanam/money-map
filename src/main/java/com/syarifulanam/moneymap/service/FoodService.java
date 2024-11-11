package com.syarifulanam.moneymap.service;

public interface FoodService {

    String saveFood(String foodName);

    String updateFood(String foodName);

    String getAllFood();

    String getFoodById(long foodId);

    String deleteFood(long foodId);
}

