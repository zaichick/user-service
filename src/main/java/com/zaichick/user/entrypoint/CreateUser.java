package com.zaichick.user.entrypoint;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;

import java.io.IOException;

public class CreateUser {

    private final UserDao userDao;

    public CreateUser() {
        userDao = new UserDao();
    }

    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest) throws IOException {

        User user = new ObjectMapper().readValue(awsProxyRequest.getBody(), User.class);
        userDao.save(user);

        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        awsProxyResponse.setStatusCode(200);
        return awsProxyResponse;
    }
}
