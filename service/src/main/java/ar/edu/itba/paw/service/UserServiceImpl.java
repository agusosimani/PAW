package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Either;
import ar.edu.itba.paw.model.Enum.Status;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;

    @Autowired
    RecipeDao recipeDao;

    @Override
    public Either<User, Warnings> getByIdComplete(int id) {
        Optional<User> possibleUser = userDao.getById(id);
        if (possibleUser.isPresent()) {

            List<RecipeIngredient> list = ingredientsDao.getByUserId(id);
            if (!list.isEmpty()) {
                User user = possibleUser.get();
                user.setIngredients(list);

                List<Recipe> recipeList = recipeDao.getByUserId(id);
                if (!recipeList.isEmpty()) {
                    user.setRecipes(recipeList);
                }

                return Either.value(user);
            }

        }
        return Either.alternative(Warnings.valueOf("NoSuchUser"));
    }

    @Override
    public Either<User, Warnings> getById(int id) {
        Optional<User> maybeUser = userDao.getById(id);
        return maybeUser.<Either<User, Warnings>>map(Either::value).orElseGet(
                () -> Either.alternative(Warnings.valueOf("NoSuchUser")));
    }

    @Override
    public Either<User, Warnings> findByUsername(String username) {
        Optional<User> maybeUser = userDao.getByUsername(username);
        return maybeUser.<Either<User, Warnings>>map(Either::value).orElseGet(
                () -> Either.alternative(Warnings.valueOf("NoSuchUser")));
    }

    @Override
    public Either<User, Warnings> findByEmail(String email) {
        Optional<User> maybeUser = userDao.getByEmail(email);
        return maybeUser.<Either<User, Warnings>>map(Either::value).orElseGet(
                () -> Either.alternative(Warnings.valueOf("NoSuchUser")));
    }

    @Transactional
    @Override
    public Either<User, Warnings> signUpUser(User user) {
        Optional<User> maybeExistsUsername = userDao.getByUsername(user.getUsername());
        if (maybeExistsUsername.isPresent())
            return Either.alternative(Warnings.valueOf("UserAlreadyExists"));

        return Either.value(userDao.signUpUser(user));
    }

    @Transactional
    @Override
    public void update(User user) {
        Optional<User> maybeOldUser = userDao.getById(user.getId());
        if (maybeOldUser.isPresent()) {
            User oldUser = maybeOldUser.get();
            Map<String, Object> map = new HashMap<>();

            if (!oldUser.getSurname().equals(user.getSurname())) {
                map.put("surname", user.getSurname());
            }
            if (!oldUser.getName().equals(user.getName())) {
                map.put("name", user.getName());
            }
            if (!oldUser.getPassword().equals(user.getPassword())) {
                map.put("password", user.getPassword());
            }
            if (!oldUser.getEmail().equals(user.getEmail())) {
                map.put("email", user.getEmail());
            }
            if (!(oldUser.getGender().equals(user.getGender()))) {
                map.put("gender", user.getGender());
            }

            userDao.update(user, map);
        }
    }

    @Transactional
    @Override
    public void deleteAccount(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", Status.DELETED.toString());
        userDao.update(user, map);
    }


}
