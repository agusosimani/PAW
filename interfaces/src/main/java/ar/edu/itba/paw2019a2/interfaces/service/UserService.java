package ar.edu.itba.paw2019a2.interfaces.service;

import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;
import ar.edu.itba.paw2019a2.model.UserTokenState;
import ar.edu.itba.paw2019a2.model.VerificationToken;

public interface UserService {
    Either<User, Warnings> getById(final int id);

    Either<User, Warnings> findByUsername(final String username);

    Either<User, Warnings> findByEmail(final String username);

    Either<User, Warnings> signUpUser(User user);

    void update(User user);

    boolean deleteAccount(int userId);

    Either<VerificationToken, Warnings> createVerificationToken(User user);

    Either<VerificationToken,Warnings> getVerificationToken(String VerificationToken);

    Warnings setUserEnabledStatus(final int userId, final boolean status);

    Either<User, Warnings> update(final int userId, User.Builder userBuilder);

    Either<UserTokenState, Warnings> getUserTokenState(VerificationToken verificationToken);

    Either<VerificationToken, Warnings> createNewVerificationToken(String existingTokenValue);

    double getRelativeRatingFromUser(int userId);
}

