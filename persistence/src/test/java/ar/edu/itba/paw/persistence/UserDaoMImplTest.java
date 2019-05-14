package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.model.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.SQLException;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class UserDaoMImplTest {


    private static final String USERNAME1 = "mJackson";
    private static final String PASSWORD1 = "jacksonFive";
    private static final String EMAIL1 = "michaelJ@gmail.com";
    private static final String NAME1 = "Michael";
    private static final String SURNAME1 = "Joseph Jackson";
    private static final Boolean GENDER1 = false;
    private static final Integer STATUS1 = 1;

    private static final String USERNAME2 = "jLennon";
    private static final String PASSWORD2 = "yokoOno";
    private static final String EMAIL2 = "LennonPeace@yahoo.com";
    private static final String NAME2 = "John";
    private static final String SURNAME2 = "Lennon";
    private static final Boolean GENDER2 = false;
    private static final Integer STATUS2 = 1;

    private static final String USERNAME3 = "aFranklin";
    private static final String PASSWORD3 = "memphis1942";
    private static final String EMAIL3 = "theArethaFranklin@gmail.com";
    private static final String NAME3 = "Aretha Louise";
    private static final String SURNAME3 = "Franklin";
    private static final Boolean GENDER3 = true;
    private static final Integer STATUS3 = 1;

    private static final String USERNAME4 = "aWineHouse";
    private static final String PASSWORD4 = "toRehabNoNoNo";
    private static final String EMAIL4 = "winehouseAmy@gmail.com";
    private static final String NAME4 = "Amy Jade";
    private static final String SURNAME4 = "Winehouse";
    private static final Boolean GENDER4 = true;
    private static final Integer STATUS4 = 1;

    @Mock
    private UserDao userDao;


    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @BeforeClass
    public void setUpClass() throws Exception {



    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);

        User u1 = userDao.signUpUser(new User.Builder(USERNAME1, PASSWORD1, EMAIL1)
                .status(STATUS1).gender(GENDER1).surname(SURNAME1).name(NAME1)
                .build());

        User u2 = userDao.signUpUser(new User.Builder(USERNAME2, PASSWORD2, EMAIL2)
                .status(STATUS2).gender(GENDER2).surname(SURNAME2).name(NAME2)
                .build());

        User u3 = userDao.signUpUser(new User.Builder(USERNAME3, PASSWORD3, EMAIL3)
                .status(STATUS3).gender(GENDER3).surname(SURNAME3).name(NAME3)
                .build());

        User u4 = userDao.signUpUser(new User.Builder(USERNAME4, PASSWORD4, EMAIL4)
                .status(STATUS4).gender(GENDER4).surname(SURNAME4).name(NAME4)
                .build());
    }

    @Test
    public void getUserByEmailTest() {
        User u;



    }


}
