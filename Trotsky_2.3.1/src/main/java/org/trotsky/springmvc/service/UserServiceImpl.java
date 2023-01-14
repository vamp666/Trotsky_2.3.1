package org.trotsky.springmvc.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trotsky.springmvc.dao.UserDao;
import org.trotsky.springmvc.dao.UserDaoImpl;
import org.trotsky.springmvc.entity.User;

import java.sql.SQLException;
import java.util.List;
@Component
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    public UserServiceImpl() {
    }

    @Override
    public void saveUser(User user) throws SQLException {
        userDao.saveUser(user);
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }
    @Override
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    public void update(long id, User user) {

    }
}
