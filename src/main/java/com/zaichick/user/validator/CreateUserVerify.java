package com.zaichick.user.validator;

import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;

import java.util.List;

public class CreateUserVerify {
    private UserDao userDao;

    public CreateUserVerify(UserDao userDao) {
        this.userDao = userDao;
    }

    public void verifyByEmail(String email) {
        List<User> users = userDao.findUserByEmailAddress(email);
        if (users != null && !users.isEmpty()) {
            throw new RuntimeException();
        }
    }
}
