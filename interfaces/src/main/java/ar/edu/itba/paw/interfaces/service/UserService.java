package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.User;

public interface UserService {
    User getById(final int id);
}
