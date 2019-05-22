package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.UserDao;
import ar.edu.itba.paw2019a2.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class UserDaoSignUpImplTest {


    @Autowired
    private DataSource ds;

    @Autowired
    private UserDao userDao;

    private JdbcTemplate jdbcTemplate;

    private static final String USERNAME = "mJackson";
    private static final String PASSWORD = "jacksonFive";
    private static final String EMAIL = "michaelJ@gmail.com";
    private static final String NAME = "Michael";
    private static final String SURNAME = "Joseph Jackson";
    private static final String GENDER = "male";
    private static final String STATUS = "REGULAR";


    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @After
    public void clearUp() {
        //TODO
    }

    @Test
    public void testSignUpUserBasicInfo() {

        User user = userDao
                .signUpUser(new User.Builder(USERNAME,PASSWORD,EMAIL)
                        .build());

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testSignUpUserCompleteInformation() {

        User user = userDao
                .signUpUser(new User.Builder(USERNAME,PASSWORD,EMAIL)
                        .status(STATUS).gender(GENDER).surname(SURNAME).name(NAME)
                        .build());

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());

        assertEquals(NAME, user.getName());
        assertEquals(SURNAME, user.getSurname());
        assertEquals(STATUS, user.getStatus());
        assertEquals(GENDER, user.getGender());


        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testSignUpUserFAIL() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME,PASSWORD,EMAIL)
                        .status(STATUS).gender(GENDER).surname(SURNAME).name(NAME)
                        .build());

        assertNotNull(user);
        assertNotEquals(USERNAME, "");
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());

        assertEquals(NAME, user.getName());
        assertEquals(SURNAME, user.getSurname());
        assertNotEquals(0, user.getStatus());
        assertEquals(GENDER, user.getGender());


        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}
