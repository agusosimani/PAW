package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenDao {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> save(VerificationToken.Builder token);
}
