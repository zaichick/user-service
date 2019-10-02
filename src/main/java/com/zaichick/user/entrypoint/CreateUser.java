package com.zaichick.user.entrypoint;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CreateUser {

    private final UserDao userDao;
    private final ObjectMapper objectMapper;
    private final CreateUserVerify createUserVerify;
    public CreateUser() {
        userDao = new UserDao();
        objectMapper = new ObjectMapper();
        createUserVerify = new CreateUserVerify();
    }

    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest) throws IOException {
        User user = objectMapper.readValue(awsProxyRequest.getBody(), User.class);

        String email = user.getEmail();

        try{
            createUserVerify.verifyByEmail(email);
        } catch (Exception e){
            AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
            awsProxyResponse.setStatusCode(400);
            awsProxyResponse.setBody("Duplicate email");

            return awsProxyResponse;
        }

//        List<User> users = userDao.findUserByEmailAddress(email);
//
//        if (users != null && !users.isEmpty()) {
//
//            AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
//            awsProxyResponse.setStatusCode(400);
//            awsProxyResponse.setBody("Duplicate email");
//
//            return awsProxyResponse;
//        }

        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        userDao.save(user);

        String bodyAsJsonString = objectMapper.writeValueAsString(user);
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        awsProxyResponse.setStatusCode(201);
        awsProxyResponse.setBody(bodyAsJsonString);

        return awsProxyResponse;
    }
}
