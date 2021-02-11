package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDaoImpl;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final MealDao repoMeals = InMemoryMealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("into MealServlet");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "create":
                    createMeal(request, response);
                    break;
                case "delete":
                    deleteMeal(request);
                    break;
                case "edit":
                    prepareToEdit(request, response);
                    break;
            }
        }
        showAllMeals(request, response);
//        log.debug("redirect to meals");
//        response.sendRedirect("meals.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("income in doPost");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    addMeal(request);
                    break;
                case "edit":
                    edit(request);
                    break;
            }
        }
        showAllMeals(request, response);
    }

    private void showAllMeals(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("create meal list");
        LocalTime minTime = LocalTime.MIN;
        LocalTime maxTime = LocalTime.MAX;
        List<MealTo> mealTos = MealsUtil.filteredByStreams(repoMeals.getAllMeal(), minTime, maxTime, MealsUtil.CALORIES_LIMIT);

        log.debug("attach List to response");
        request.setAttribute("mealTos", mealTos);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    private void createMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("input into createMeal()");
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).replace(" ", "T");
        request.setAttribute("time", dateTime);
        request.getRequestDispatcher("/create-meal.jsp").forward(request, response);
    }

    private void addMeal(HttpServletRequest request) {
        log.debug("input into addMeal()");
        repoMeals.addMeal(mealFromRequest(request));
    }

    private void prepareToEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("into prepareToEdit()");
        request.setAttribute("meal", repoMeals.getMealByID(idFromRequest(request)));
        request.getRequestDispatcher("/edit-meal.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request) {
        log.debug("into edit()");
        repoMeals.updateMeal(idFromRequest(request), mealFromRequest(request));
    }

    private void deleteMeal(HttpServletRequest request) {
        log.debug("into deleteMeal()");
        repoMeals.deleteMeal(idFromRequest(request));
    }

    private Meal mealFromRequest(HttpServletRequest request) {
        try {
            LocalDateTime datetime = LocalDateTime.parse(request.getParameter("datetime"));
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));
            return new Meal(datetime, description, calories);
        } catch (NumberFormatException formatException) {
            log.debug(formatException.toString());
            throw new IllegalArgumentException();
        }
    }

    private int idFromRequest(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException formatException) {
            log.debug("illegal format of id");
            throw new IllegalArgumentException();
        }
    }

}
