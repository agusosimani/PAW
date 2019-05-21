package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenDao {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> save(VerificationToken.Builder token);
}
