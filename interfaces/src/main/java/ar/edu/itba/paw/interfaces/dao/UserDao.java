package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.User;

public interface UserDao {
    User getById(final int id);
}
