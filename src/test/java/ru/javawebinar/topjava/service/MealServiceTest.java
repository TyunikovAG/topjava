package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static org.assertj.core.api.Assertions.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    private final static int WRONG_USER_ID = 10;
    private final static int WRONG_MEAL_ID = 20;
    private final static Logger log = LoggerFactory.getLogger(MealServiceTest.class);

    @Autowired
    private MealService service;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void get() {
        Meal meal = service.get(ADMIN_LANCH, ADMIN_ID);
        Assert.assertEquals(ADMIN_MEAL, meal);
    }

    @Test
    public void get_with_wrong_user_id() {
        Assert.assertThrows("Not found entity with id=" + ADMIN_MEAL,
                NotFoundException.class,
                () -> service.get(ADMIN_LANCH, WRONG_USER_ID));
    }

    @Test
    public void get_with_wrong_meal_id() {
        Assert.assertThrows("Not found entity with id=" + WRONG_MEAL_ID,
                NotFoundException.class,
                () -> service.get(WRONG_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(ADMIN_LANCH, ADMIN_ID);
    }

    @Test
    public void delete_with_wrong_user_id() {
        Assert.assertThrows("Not found entity with id=" + WRONG_USER_ID,
                NotFoundException.class,
                () -> service.delete(ADMIN_LANCH, WRONG_USER_ID));
    }

    @Test
    public void delete_with_wrong_meal_id() {
        Assert.assertThrows("Not found entity with id=" + WRONG_MEAL_ID,
                NotFoundException.class,
                () -> service.delete(WRONG_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void create_meal() {
        Meal copy = copyMeal(NEW_MEAL);
        Meal actual = service.create(copy, ADMIN_ID);
        assertThat(NEW_MEAL).isEqualToIgnoringGivenFields(actual, "id");
        assertNotNull(actual.getId());
    }

    @Test
    public void create_meal_with_wrong_user_id() {
        Meal copy = copyMeal(NEW_MEAL);
        assertThrows(Exception.class, () ->  service.create(copy, WRONG_USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        Assert.fail();
    }

    @Test
    public void getAll() {
        Assert.fail();
    }

    @Test
    public void update() {
        Assert.fail();
    }

}