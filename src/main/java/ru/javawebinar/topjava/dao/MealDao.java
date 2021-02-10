package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealDao {
    void addMeal(Meal meal);
    void deleteMeal(Meal meal);
    void deleteMeal(int id);
    Meal getMealByID(int id);
    void updateMeal(int id, Meal meal);
    List<Meal> getAllMeal();

}
