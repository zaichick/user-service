package com.zaichick.user;

import com.zaichick.user.dao.UserDao;

public class Main {

    public static void main(String[] args) {
        System.out.println("it is alive !!!");

        UserDao userDao = new UserDao();
        userDao.findUserByEmailAddress("rusty@gmail.com");
    }
}
