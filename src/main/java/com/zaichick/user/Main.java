package com.zaichick.user;

import com.zaichick.user.dao.UserDao;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        System.out.println("it is alive !!!");

//        UserDao userDao = new UserDao();
//        userDao.findUserByEmailAddress("rusty@gmail.com");
        Calendar c = Calendar.getInstance();
        System.out.println("The Current Date is:" + c.getTime());

        String i  = String.valueOf(c);
        System.out.println("String value is "+i);

    }
}
