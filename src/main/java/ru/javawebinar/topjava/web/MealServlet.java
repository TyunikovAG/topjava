package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("create meal list");
        List<Meal> meals = MealsUtil.createMeals();
//        LocalTime startTime =  LocalTime.of(0, 0);
//        LocalTime endTime =  LocalTime.of(0, 0);

        LocalTime minTime =  LocalTime.MIN;
        LocalTime maxTime =  LocalTime.MAX;
        List<MealTo> mealTos = MealsUtil.filteredByStreams(meals, minTime, maxTime, MealsUtil.CALORIES_LIMIT);

        log.debug("attach List to response");
        request.setAttribute("mealTos", mealTos);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        log.debug("redirect to meals");
//        response.sendRedirect("meals.jsp");
    }
}
