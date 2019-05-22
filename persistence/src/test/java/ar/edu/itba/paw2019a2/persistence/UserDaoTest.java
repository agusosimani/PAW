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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:schema.sql"})
public class UserDaoTest {

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDao userDao;

    private JdbcTemplate jdbcTemplate;

    private static final String USERNAME1 = "mJackson";
    private static final String PASSWORD1 = "jacksonFive";
    private static final String EMAIL1 = "michaelJ@gmail.com";
    private static final String NAME1 = "Michael";
    private static final String SURNAME1 = "Joseph Jackson";
    private static final String GENDER1 = "male";
    private static final String STATUS1 = "REGULAR";

    private static final String USERNAME2 = "jLennon";
    private static final String PASSWORD2 = "yokoOno";
    private static final String EMAIL2 = "LennonPeace@yahoo.com";
    private static final String NAME2 = "John";
    private static final String SURNAME2 = "Lennon";
    private static final String GENDER2 = "male";
    private static final String STATUS2 = "REGULAR";

    private static final String USERNAME3 = "aFranklin";
    private static final String PASSWORD3 = "memphis1942";
    private static final String EMAIL3 = "theArethaFranklin@gmail.com";
    private static final String NAME3 = "Aretha Louise";
    private static final String SURNAME3 = "Franklin";
    private static final String GENDER3 = "female";
    private static final String STATUS3 = "REGULAR";

    private static final String USERNAME4 = "aWineHouse";
    private static final String PASSWORD4 = "toRehabNoNoNo";
    private static final String EMAIL4 = "winehouseAmy@gmail.com";
    private static final String NAME4 = "Amy Jade";
    private static final String SURNAME4 = "Winehouse";
    private static final String GENDER4 = "female";
    private static final String STATUS4 = "REGULAR";

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testSignUpUserBasicInfo() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME1,PASSWORD1,EMAIL1)
                        .build());

        assertNotNull(user);
        assertEquals(USERNAME1, user.getUsername());
        assertEquals(PASSWORD1, user.getPassword());
        assertEquals(EMAIL1, user.getEmail());
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testSignUpUserCompleteInformation() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME2,PASSWORD2,EMAIL2)
                        .status(STATUS2).gender(GENDER2).surname(SURNAME2).name(NAME2)
                        .build());

        assertNotNull(user);
        assertEquals(USERNAME2, user.getUsername());
        assertEquals(PASSWORD2, user.getPassword());
        assertEquals(EMAIL2, user.getEmail());

        assertEquals(NAME2, user.getName());
        assertEquals(SURNAME2, user.getSurname());
        assertEquals(STATUS2, user.getStatus());
        assertEquals(GENDER2, user.getGender());


        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }

    @Test
    public void testUpdate() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME1,PASSWORD1,EMAIL1)
                        .status(STATUS1).gender(GENDER1).surname(SURNAME1).name(NAME1)
                        .build());


        Map<String, Object> map = new HashMap<>();
        map.put("password", PASSWORD2);
        map.put("mail", EMAIL2);
        map.put("user_satus", STATUS2);
        map.put("gender", GENDER2);
        map.put("surname", SURNAME2);
        map.put("name", NAME2);
        //username cannot be changed

        userDao.update(user, map);
        Optional<User> updatedUser = userDao.getById(user.getId());

        assertTrue(updatedUser.isPresent());
        assertEquals(updatedUser.get().getUsername(), USERNAME1);
        assertEquals(updatedUser.get().getPassword(), PASSWORD2);
        assertEquals(updatedUser.get().getEmail(), EMAIL2);
        assertEquals(updatedUser.get().getStatus(), STATUS2);
        assertEquals(updatedUser.get().getSurname(), SURNAME2);
        assertEquals(updatedUser.get().getName(), NAME2);
    }

    @Test
    public void getByUsernameSuccess() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME4,PASSWORD4,EMAIL4)
                        .status(STATUS4).gender(GENDER4).surname(SURNAME4).name(NAME4)
                        .build());

        Optional<User> foundUser = userDao.getByUsername(USERNAME4);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    public void getByUsernameFail() {
        User user1 = userDao
                .signUpUser(new User.Builder(USERNAME1,PASSWORD1,EMAIL1)
                        .status(STATUS1).gender(GENDER1).surname(SURNAME1).name(NAME1)
                        .build());


        User user2 = userDao
                .signUpUser(new User.Builder(USERNAME2,PASSWORD2,EMAIL2)
                        .status(STATUS2).gender(GENDER2).surname(SURNAME2).name(NAME2)
                        .build());

        Optional<User> foundUser1 = userDao.getByUsername(USERNAME1);
        Optional<User> foundUser2 = userDao.getByUsername(USERNAME2);
        Optional<User> foundUser3 = userDao.getByUsername(USERNAME3);

        assertFalse(foundUser3.isPresent());
        assertTrue(foundUser1.isPresent());
        assertTrue(foundUser2.isPresent());
        assertNotEquals(foundUser1.get(), foundUser2.get());
    }

    @Test
    public void getByEmailSuccess() {
        User user = userDao
                .signUpUser(new User.Builder(USERNAME4,PASSWORD4,EMAIL4)
                        .status(STATUS4).gender(GENDER4).surname(SURNAME4).name(NAME4)
                        .build());

        Optional<User> foundUser = userDao.getByEmail(EMAIL4);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    public void getByEmailFail() {
        User user1 = userDao
                .signUpUser(new User.Builder(USERNAME1,PASSWORD1,EMAIL1)
                        .status(STATUS1).gender(GENDER1).surname(SURNAME1).name(NAME1)
                        .build());


        User user2 = userDao
                .signUpUser(new User.Builder(USERNAME2,PASSWORD2,EMAIL2)
                        .status(STATUS2).gender(GENDER2).surname(SURNAME2).name(NAME2)
                        .build());

        Optional<User> foundUser1 = userDao.getByEmail(EMAIL1);
        Optional<User> foundUser2 = userDao.getByEmail(EMAIL2);
        Optional<User> foundUser3 = userDao.getByEmail(EMAIL3);

        assertFalse(foundUser3.isPresent());
        assertTrue(foundUser1.isPresent());
        assertTrue(foundUser2.isPresent());
        assertNotEquals(foundUser1.get(), foundUser2.get());
    }
}
