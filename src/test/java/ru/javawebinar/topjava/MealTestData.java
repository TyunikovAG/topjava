package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int ADMIN_LANCH = 100_002;

    public static final Meal ADMIN_MEAL = new Meal(ADMIN_LANCH, LocalDateTime.of(2015, 06, 01, 14, 00, 00), "Админ ланч", 510);
    public static final Meal NEW_MEAL = new Meal(null, LocalDateTime.of(2000, 12, 31, 23, 59, 00), "Шампанское", 210);

    public static Meal copyMeal(Meal meal){
        Meal copy = new Meal();
        copy.setId(meal.getId());
        copy.setDateTime(meal.getDateTime());
        copy.setCalories(meal.getCalories());
        copy.setDescription(meal.getDescription());
        return copy;
    }

}
