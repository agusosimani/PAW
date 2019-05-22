package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.*;
import ar.edu.itba.paw2019a2.interfaces.service.AuthenticationService;
import ar.edu.itba.paw2019a2.interfaces.service.UserService;
import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.Status;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static ar.edu.itba.paw2019a2.model.UserTokenState.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    RatingsDao ratingsDao;

    @Autowired
    private VerificationTokenDao verificationTokenDao;

    @Autowired
    private AuthenticationService authenticationService;

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
    public boolean deleteAccount(int user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_status", Status.DELETED.toString());
        Either<User,Warnings> u = getById(user);
        if(!u.isValuePresent())
            return false;
        userDao.update(u.getValue(), map);
        return true;
    }

    @Override
    public Either<VerificationToken, Warnings> createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken.Builder myToken = new VerificationToken.Builder(token, user.getId());
        Optional<VerificationToken> verToken = verificationTokenDao.save(myToken);
        return verToken.isPresent()? Either.value(verToken.get()) : Either.alternative(Warnings.NoSuchToken);
    }

    @Override
    public Either<VerificationToken, Warnings> createNewVerificationToken(String existingTokenValue) {
        Optional<VerificationToken> verToken = verificationTokenDao.findByToken(existingTokenValue);
        if (!verToken.isPresent()) {
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }
        Either<VerificationToken, Warnings> existingToken = Either.value(verToken.get());
        if(!existingToken.isValuePresent()) {
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }
        Optional<User> u = userDao.getById(existingToken.getValue().getUserID());
        if (!u.isPresent())
            return Either.alternative(Warnings.valueOf("NoSuchUser"));
        Either<User, Warnings> user = Either.value(u.get());
        return user.isValuePresent()? createVerificationToken(user.getValue()) : Either.alternative(Warnings.valueOf("NoSuchUser"));
    }

    @Override
    public Either<VerificationToken, Warnings> getVerificationToken(String tokenString) {
        Optional<VerificationToken> verToken = verificationTokenDao.findByToken(tokenString);
        return verToken.isPresent()? Either.value(verToken.get()) : Either.alternative(Warnings.NoSuchToken);
    }

    @Override
    public Either<UserTokenState, Warnings> getUserTokenState(VerificationToken verificationToken) {
        Optional<VerificationToken> verToken = verificationTokenDao.findByToken(verificationToken.getToken());
        if (!verToken.isPresent()) {
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }
        Either<VerificationToken, Warnings> validToken = Either.value(verToken.get());

        Optional<User> u = userDao.getById(verificationToken.getUserID());
        if (!u.isPresent()) {
            return Either.alternative(Warnings.valueOf("ServerError"));
        }
        Either<User, Warnings> user = Either.value(u.get());
        if(!user.isValuePresent()) {
            return Either.alternative(Warnings.valueOf("ServerError"));
        }

        Calendar cal = Calendar.getInstance();
        boolean tokenExpired = verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0;
        boolean userEnabled = user.getValue().isEnabled();

        if(tokenExpired){
            if (userEnabled){
                return Either.value(USER_ENABLED_EXPIRED_TOKEN);
            }
            return Either.value(USER_DISABLED_EXPIRED_TOKEN);
        }

        if(userEnabled) {
            return Either.value(USER_ENABLED_VALID_TOKEN);
        }
        return Either.value(USER_DISABLED_VALID_TOKEN);
    }

    @Override
    public Warnings setUserEnabledStatus(int userId, boolean status) {
        return userDao.setUserStatus(userId, status);
    }

    @Override
    public Either<User, Warnings> update(final int userId, User.Builder userBuilder) {
        if(!isLoggedUserAuthorizedToUpdateUser(userId)){
            return Either.alternative(Warnings.valueOf("AuthorizationDenied"));
        }
        Optional<User> u = userDao.getById(userId);
        if (!u.isPresent())
            return Either.alternative(Warnings.NoSuchUser);
        return Either.value(u.get());
    }

    private boolean isLoggedUserAuthorizedToUpdateUser(long userId) {
        Optional<User> u = authenticationService.getLoggedUser();
        if (!u.isPresent())
            return false;
        return authenticationService.getLoggedUser().isPresent() && u.get().getId() == userId;
    }

    @Override
    public double getRelativeRatingFromUser(int userId){

        double retVal = 0;
        double totalRatings = 0;

        List<Recipe> list = recipeDao.getAllRecipesByUserId(userId);

        for (Recipe recipe : list) {
            int nOfRatings = ratingsDao.getAmountRatings(recipe.getId());
            retVal += recipe.getRating()*nOfRatings;
            totalRatings += nOfRatings;
        }

        if(totalRatings == 0)
            return 0;


        return retVal/totalRatings;

    }
}
