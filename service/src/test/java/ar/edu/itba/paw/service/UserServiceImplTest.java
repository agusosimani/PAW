package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.model.Either;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final String PASSWORD = "passwordpassword";
    private static final String USERNAME = "username";
    private static final String EMAIL = "user@example.ord";

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private UserDao mockDao;

    @Test
    public void testCreate() {
        //1. Setup
        Mockito.when(mockDao.signUpUser(Mockito.eq(new User.Builder(USERNAME, PASSWORD, EMAIL).build()))).thenReturn(
                new User.Builder(USERNAME, PASSWORD, EMAIL).build()
        );

        //2.
        Either<User, Warnings> eitherUser = userService.signUpUser(new User.Builder(USERNAME, PASSWORD, EMAIL).build());

        //3. Asserts
        Assert.assertNotNull(eitherUser);
        Assert.assertTrue(eitherUser.isValuePresent());
        Assert.assertEquals(USERNAME, eitherUser.getValue().getUsername());
        Assert.assertEquals(PASSWORD, eitherUser.getValue().getPassword());
    }


    @Test
    public void testCreateAlreadyExists() {
        //1. Setup
        Mockito.when(mockDao.getByUsername(Mockito.eq(USERNAME)))
                .thenReturn(Optional.of(new User.Builder(USERNAME, PASSWORD, EMAIL).build()));

        //2.
        Either<User, Warnings> eitherUser = userService.signUpUser(new User.Builder(USERNAME, PASSWORD, EMAIL).build());

        //3. Asserts
        Assert.assertNotNull(eitherUser);
        Assert.assertFalse(eitherUser.isValuePresent());
    }
}