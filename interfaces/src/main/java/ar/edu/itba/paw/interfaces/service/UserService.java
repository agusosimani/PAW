package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Either;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.model.UserTokenState;
import ar.edu.itba.paw.model.VerificationToken;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Either<User, Warnings> getById(final int id);

    Either<User, Warnings> getByIdComplete(int id);

    Either<User, Warnings> findByUsername(final String username);

    Either<User, Warnings> findByEmail(final String username);

    Either<User, Warnings> signUpUser(User user);

    void update(User user);

    void deleteAccount(User user);

    Either<VerificationToken, Warnings> createVerificationToken(User user);

    Either<VerificationToken,Warnings> getVerificationToken(String VerificationToken);

    Warnings setUserEnabledStatus(final int userId, final boolean status);

    Either<VerificationToken, Warnings> getVerificationTokenWithRole(final int userId, final String VerificationToken);

    void resetPassword(final int id, final String password);

    void confirmMailVerification(final int userId, final int tokenId);

    Either<User, Warnings> update(final int userId, User.Builder userBuilder);

    Either<UserTokenState, Warnings> getUserTokenState(VerificationToken verificationToken);

    Either<VerificationToken, Warnings> createNewVerificationToken(String existingTokenValue);
}

