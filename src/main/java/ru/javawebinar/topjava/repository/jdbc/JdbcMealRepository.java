package ru.javawebinar.topjava.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcMealRepository.class);

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    // null if updated meal does not belong to userId
    // TODO: 24.02.2021 implemet
    public Meal save(Meal meal, int userId) {
//        String query = String.format("SELECT * FROM meals " +
//                "WHERE user_id = %d " +
//                "AND id = %d " +
//                "ORDER BY dateTime DESC", userId, id);
//        log.debug(query);
//        List<Meal> result = jdbcTemplate.query(query, ROW_MAPPER);
//        return result.isEmpty()? null : result.get(0);
        return null;
    }

    @Override
    // false if meal does not belong to userId
    // TODO: 24.02.2021 implemet
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    // null if meal does not belong to userId
    public Meal get(int id, int userId) {
        List<Meal> result = jdbcTemplate.query("SELECT * FROM meals " +
                "WHERE user_id = ? AND id = ? " +
                "ORDER BY dateTime DESC", ROW_MAPPER, userId, id);
        return result.isEmpty()? null : result.get(0);
    }

    @Override
    // ORDERED dateTime desc
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id = ? ORDER BY dateTime DESC", ROW_MAPPER, userId);
    }

    @Override
    // ORDERED dateTime desc
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals " +
                "WHERE user_id = ? AND datetime >= ? AND datetime < ? " +
                "ORDER BY dateTime DESC", ROW_MAPPER, userId, startDateTime, endDateTime);
    }
}
