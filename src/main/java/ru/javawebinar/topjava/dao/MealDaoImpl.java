package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoImpl implements MealDao {

    private static ConcurrentHashMap<Integer, Meal> storage;

    public MealDaoImpl() {
        AtomicInteger i = new AtomicInteger(0);
        storage = new ConcurrentHashMap<>(MealsUtil.createMeals().stream()
                .collect(Collectors.toMap(n->i.incrementAndGet(), meal -> meal)));
    }

    @Override
    public void addMeal(Meal meal) {
        storage.put(storage.size(), meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        storage.remove(meal);
    }

    @Override
    public void deleteMeal(int id) {
        storage.remove(id);
    }

    @Override
    public Meal getMealByID(int id) {
        return storage.get(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        //TODO: how we can understand that meal must be rewrite?
    }

    @Override
    public List<Meal> getAllMeal() {
        return new ArrayList<Meal>(storage.values());
    }
}