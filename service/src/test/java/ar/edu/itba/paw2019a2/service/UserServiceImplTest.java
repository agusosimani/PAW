package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.UserDao;
import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final int ID = 1;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwordpassword";
    private static final String NAME = "name";
    private static final String SURNAME = "surnname";
    private static final String EMAIL = "user@example.ord";

    private static final int ID2 = 2;
    private static final String USERNAME2 = "username2";
    private static final String NAME2 = "name2";
    private static final String SURNAME2 = "surnname2";


    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private UserDao mockUserDao;

    @Test
    public void testSignUpUser() {
        //1. Setup
        Mockito.when(mockUserDao.signUpUser(Mockito.eq(new User.Builder(USERNAME, PASSWORD, EMAIL).build()))).thenReturn(
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
    public void testSignUpUserAlreadyExists() {
        //1. Setup
        Mockito.when(mockUserDao.getByUsername(Mockito.eq(USERNAME)))
                .thenReturn(Optional.of(new User.Builder(USERNAME, PASSWORD, EMAIL).build()));

        //2.
        Either<User, Warnings> eitherUser = userService.signUpUser(new User.Builder(USERNAME, PASSWORD, EMAIL).build());

        //3. Asserts
        Assert.assertNotNull(eitherUser);
        Assert.assertFalse(eitherUser.isValuePresent());
    }

    @Test
    public void testUpdateUser() {
        //1. Setup
        User oldUser = new User.Builder(USERNAME, PASSWORD, EMAIL)
                .name(NAME).surname(SURNAME).build();
        oldUser.setId(ID);

        User newUser = new User.Builder(USERNAME, PASSWORD, EMAIL)
                .name(NAME2).surname(SURNAME2).build();
        newUser.setId(ID);

        Mockito.when(mockUserDao.getById(Mockito.eq(ID)))
                .thenReturn(Optional.of(oldUser));

        Mockito.doAnswer((i) -> {
            //3. Asserts
            Assert.assertEquals(2, ((Map<String,Object>)i.getArgument(1)).keySet().size());
            Assert.assertEquals(NAME2, ((Map<String,Object>)i.getArgument(1)).get("name"));
            Assert.assertEquals(SURNAME2, ((Map<String,Object>)i.getArgument(1)).get("surname"));
            return null;
        }).when(mockUserDao).update(any(),any());

        //2.
        userService.update(newUser);
    }

    @Test
    public void testFindByUsername() {
        //1. Setup
        User user = new User.Builder(USERNAME, PASSWORD, EMAIL)
                .name(NAME).surname(SURNAME).build();
        user.setId(ID);

        Mockito.when(mockUserDao.getByUsername(Mockito.eq(USERNAME)))
                .thenReturn(Optional.of(user));

        //2.
        Either<User, Warnings> eitherUser = userService.findByUsername(USERNAME);

        //3. Asserts
        Assert.assertTrue(eitherUser.isValuePresent());
        Assert.assertEquals(USERNAME, eitherUser.getValue().getUsername());
    }

    @Test
    public void testGetById() {
        //1. Setup
        User user = new User.Builder(USERNAME, PASSWORD, EMAIL)
                .name(NAME).surname(SURNAME).build();
        user.setId(ID);

        Mockito.when(mockUserDao.getById(Mockito.eq(ID)))
                .thenReturn(Optional.of(user));

        //2.
        Either<User, Warnings> eitherUser = userService.getById(ID);

        //3. Asserts
        Assert.assertTrue(eitherUser.isValuePresent());
        Assert.assertEquals(ID, eitherUser.getValue().getId());
    }

    public void testGetByIdComplete() {
        //TODO
    }
}