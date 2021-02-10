package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDaoImpl;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class EditMealServlet extends HttpServlet {
    private static final Logger log = getLogger(EditMealServlet.class);
    private static final MealDao repoMeals = InMemoryMealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("into edit servlet");
        System.out.println("Try to edit meal #" + request.getParameter("id"));

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            LocalDateTime datetime = LocalDateTime.parse(request.getParameter("datetime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            Meal meal = new Meal(datetime, description, calories);
            repoMeals.updateMeal(id, meal);
        } catch (NumberFormatException formatException) {
            log.debug(formatException.toString());
        }

//        request.getRequestDispatcher("/edit-meal.jsp").forward(request, response);
        response.sendRedirect("/topjava/meals");
    }
}
