package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.UserService;
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
    public Optional<User> getByIdComplete(int id) {
        Optional<User> possibleUser = userDao.getById(id);
        if(possibleUser.isPresent()) {

            Optional<List<RecipeIngredient>> probablyList = ingredientsDao.getByUserId(id);
            if(probablyList.isPresent()){
                List<RecipeIngredient> list = probablyList.get();
                User user = possibleUser.get();
                user.setIngredients(list);

                Optional<List<Recipe>> probablyRecipeList = recipeDao.getByUserId(id);
                if(probablyRecipeList.isPresent()){
                    List<Recipe> recipeList = probablyRecipeList.get();
                    user.setRecipes(recipeList);
                }

                //TODO brazuca: encontrar como resolver esto. tirar excepcion y cachearla del otro lado?
                return Optional.of(user);
            }

        }
        return possibleUser;
    }

    @Override
    public Optional<User> getById(int id) {
        return userDao.getById(id);

    }

        @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    @Override
    public User signUpUser(User user) {
        return userDao.signUpUser(user);
    }

    @Override
    public void update(User user,String change, Object value) throws RuntimeException{
        Map<String,Object> map = new HashMap<>();
        map.put(change,value);
        this.update(user,map);
    }

    //TODO meter excepcion
    @Transactional
    @Override
    public void update(User user, Map<String, Object> map) throws RuntimeException{
        map.forEach((k,v)->{
            if(!k.equals("name") && !k.equals("surname") && !k.equals("password") &&
                    !k.equals("gender") && !k.equals("status")  && !k.equals("email")) {
                throw new RuntimeException();
            }
        });
        userDao.update(user,map);
    }

    @Override
    public void deleteAccount(User user) {
        this.update(user,"status",0);
    }


}
