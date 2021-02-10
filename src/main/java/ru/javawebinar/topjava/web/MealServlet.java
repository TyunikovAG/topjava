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
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final MealDao repoMeals = InMemoryMealDaoImpl.getInstance();
//    private static final List<Meal> meals = ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("into MealServlet");
        log.debug("create meal list");
        LocalTime minTime =  LocalTime.MIN;
        LocalTime maxTime =  LocalTime.MAX;
        List<MealTo> mealTos = MealsUtil.filteredByStreams(repoMeals.getAllMeal(), minTime, maxTime, MealsUtil.CALORIES_LIMIT);

        log.debug("attach List to response");
        request.setAttribute("mealTos", mealTos);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        log.debug("redirect to meals");
//        response.sendRedirect("meals.jsp");
    }
}
