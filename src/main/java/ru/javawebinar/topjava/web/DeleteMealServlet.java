package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.InMemoryMealDaoImpl;
import ru.javawebinar.topjava.dao.MealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static org.slf4j.LoggerFactory.getLogger;

public class DeleteMealServlet extends HttpServlet {
    private static final Logger log = getLogger(DeleteMealServlet.class);
    private static final MealDao repoMeals = InMemoryMealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("into DELETE Servlet");
        System.out.println("Try to delete meal #" + request.getParameter("id"));
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            repoMeals.deleteMeal(id);
        } catch (NumberFormatException formatException){

        }

//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        response.sendRedirect("/topjava/meals");
    }
}
