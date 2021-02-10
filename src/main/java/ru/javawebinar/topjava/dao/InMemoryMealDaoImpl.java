package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealDaoImpl implements MealDao {

    private static InMemoryMealDaoImpl instance;
    private static ConcurrentHashMap<Integer, Meal> storage;
    private static AtomicInteger id = new AtomicInteger(0);

    private InMemoryMealDaoImpl() {
        storage = new ConcurrentHashMap<>(createMeals().stream()
                .map(meal -> meal.setIdandReturn(id.incrementAndGet()))
                .collect(Collectors.toMap(Meal::getId, meal -> meal)));
    }

    public static InMemoryMealDaoImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryMealDaoImpl();
        }

        return instance;
    }

    @Override
    public void addMeal(Meal meal) {
        storage.put(id.incrementAndGet(), meal);
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
    public void updateMeal(int id, Meal meal) {
        meal.setId(id);
        storage.put(id, meal);
    }

    @Override
    public List<Meal> getAllMeal() {
        return new ArrayList<Meal>(storage.values());
    }

    private static List<Meal> createMeals() {
        return Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
    }
}