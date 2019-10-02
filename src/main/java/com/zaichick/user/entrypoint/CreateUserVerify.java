package com.zaichick.user.entrypoint;
import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;
import java.util.List;

public class CreateUserVerify {
    private UserDao userDao = new UserDao();


    public void verifyByEmail(String email){
        List<User> users = userDao.findUserByEmailAddress(email);
        if (users != null && !users.isEmpty()) {
            throw new RuntimeException();
        }
    }
}
