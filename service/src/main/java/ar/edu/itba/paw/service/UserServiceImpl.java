package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//8===D
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;

    @Override
    public Optional<User> getById(int id) {
        Optional<User> possibleUser = userDao.getById(id);
        if(possibleUser.isPresent()) {

            Optional<List<RecipeIngredient>> probablyList = ingredientsDao.getByUserId(id);
            if(probablyList.isPresent()){
                List<RecipeIngredient> list = probablyList.get();
                User user = possibleUser.get();
                user.setIngredients(list);

                //TODO brazuca: encontrar como resolver esto. tirar excepcion y cachearla del otro lado?
                return null;
            }
        }
        return possibleUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User signUpUser(User user) {
        return userDao.signUpUser(user);
    }
}
