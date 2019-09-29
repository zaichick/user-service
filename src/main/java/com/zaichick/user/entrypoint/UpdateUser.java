package com.zaichick.user.entrypoint;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;

import java.io.IOException;

public class UpdateUser {
    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    public UpdateUser() {
        userDao = new UserDao();
        objectMapper = new ObjectMapper();
    }

    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest) throws IOException {
        User user = objectMapper.readValue(awsProxyRequest.getBody(), User.class);
        String id = user.getId();
        User oldUser = userDao.getById(id);
        if (oldUser == null) {
            throw new RuntimeException("user not found");
        }
        user.setCreateDt(oldUser.getCreateDt());
        userDao.save(user);
        String bodyAsJsonString = objectMapper.writeValueAsString(user);
        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        awsProxyResponse.setBody(bodyAsJsonString);
        awsProxyResponse.setStatusCode(200);
        return awsProxyResponse;
    }
}
