package org.trotsky.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.trotsky.springmvc.dao.UserDao;
import org.trotsky.springmvc.entity.User;

import java.sql.SQLException;

@Controller
public class UsersController {

    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        try {
            model.addAttribute("users", userDao.getAllUsers());
            model.addAttribute("new_user", new User());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDao.show(id));
        return "/show";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userDao.update(id, user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) throws SQLException {
        userDao.saveUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id) throws SQLException {
        userDao.removeUserById(id);
        return "redirect:/users";
    }


}