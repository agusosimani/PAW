package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.*;
import ar.edu.itba.paw2019a2.interfaces.service.AuthenticationService;
import ar.edu.itba.paw2019a2.interfaces.service.UserService;
import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.Status;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

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

    @Override
    public Either<VerificationToken, Warnings> createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken.Builder myToken = new VerificationToken.Builder(token, user.getId());
        return Either.value(verificationTokenDao.save(myToken).get());
    }

    @Override
    public Either<VerificationToken, Warnings> createNewVerificationToken(String existingTokenValue) {
        Either<VerificationToken, Warnings> existingToken = Either.value(verificationTokenDao.findByToken(existingTokenValue).get());
        if(!existingToken.isValuePresent()) {
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }
        Either<User, Warnings> user = Either.value(userDao.getById((int)existingToken.getValue().getUserID()).get());
        if(!user.isValuePresent()) {
            return Either.alternative(Warnings.valueOf("NoSuchUser"));
        }
        return createVerificationToken(user.getValue());
    }

    @Override
    public Either<VerificationToken, Warnings> getVerificationToken(String tokenString) {
        return Either.value(verificationTokenDao.findByToken(tokenString).get());
    }

    @Override
    public Either<UserTokenState, Warnings> getUserTokenState(VerificationToken verificationToken) {
        Either<VerificationToken, Warnings> validToken = Either.value(verificationTokenDao.findByToken(verificationToken.getToken()).get());
        if(!validToken.isValuePresent()){
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }

        Either<User, Warnings> user = Either.value(userDao.getById((int)verificationToken.getUserID()).get());
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
    public Either<VerificationToken, Warnings> getVerificationTokenWithRole(final int userId, final String VerificationToken) {
        Either<VerificationToken, Warnings> verificationToken = Either.value(verificationTokenDao.findByToken(VerificationToken).get());
        if(!verificationToken.isValuePresent() || Integer.compare(verificationToken.getValue().getUserID(), userId) != 0){
            return Either.alternative(Warnings.valueOf("NoSuchToken"));
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getValue().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return Either.alternative(Warnings.valueOf("ExpiredToken"));
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(
                this.getById((int)verificationToken.getValue().getUserID()), null, Arrays.asList( new SimpleGrantedAuthority("ROLE_CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return verificationToken;
    }

    @Override
    public Warnings setUserEnabledStatus(int userId, boolean status) {
        return userDao.setUserStatus(userId, status);
    }

    @Override
    public void resetPassword(int id, String password) {
        userDao.updatePassword(id, passwordEncoder.encode(password));
    }

    @Override
    public void confirmMailVerification(final int userId, final int tokenId) {
        setUserEnabledStatus(userId, true);
    }

    @Override
    public Either<User, Warnings> update(final int userId, User.Builder userBuilder) {
        if(!isLoggedUserAuthorizedToUpdateUser(userId)){
            return Either.alternative(Warnings.valueOf("AuthorizationDenied"));
        }

        return Either.value(userDao.getById(userId).get());
    }

    private boolean isLoggedUserAuthorizedToUpdateUser(long userId) {
        return authenticationService.getLoggedUser().isPresent() && authenticationService.getLoggedUser().get().getId() == userId;
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
