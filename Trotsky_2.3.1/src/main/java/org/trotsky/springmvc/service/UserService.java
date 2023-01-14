package org.trotsky.springmvc.service;

import org.trotsky.springmvc.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void saveUser(User user) throws SQLException;

    void removeUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    User show(long id);

    void update(long id, User user);

}