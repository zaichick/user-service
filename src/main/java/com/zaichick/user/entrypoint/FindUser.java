package com.zaichick.user.entrypoint;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaichick.user.dao.UserDao;
import com.zaichick.user.domain.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FindUser {

    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    public FindUser() {
        userDao = new UserDao();
        objectMapper = new ObjectMapper();
    }

    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest) throws IOException {
        Map<String, String> queryParamMap = awsProxyRequest.getQueryStringParameters();
        List<User> userByEmailAddress = userDao.findUserByEmailAddress(queryParamMap.get("email"));

        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        String responseBody = objectMapper.writeValueAsString(userByEmailAddress);
        awsProxyResponse.setBody(responseBody);
        awsProxyResponse.setStatusCode(200);

        return awsProxyResponse;
    }

    public AwsProxyResponse byNamesAndDOB(AwsProxyRequest awsProxyRequest) throws IOException {
        Map<String, String> queryParamMap = awsProxyRequest.getQueryStringParameters();
//        List<User> userByEmailFnameLnameDOB = userDao.findUserByFnameLnameDOB(queryParamMap.get("firstName", "lastName", "dateOfBirth"));
        List<User> userByEmailFnameLnameDOB = userDao.findUserByFnameLnameDOB(queryParamMap.get("firstName"), queryParamMap.get("lastName"), queryParamMap.get("dateOfBirth"));

        AwsProxyResponse awsProxyResponse = new AwsProxyResponse();
        String responseBody = objectMapper.writeValueAsString(userByEmailFnameLnameDOB);
        awsProxyResponse.setBody(responseBody);
        awsProxyResponse.setStatusCode(200);

        return awsProxyResponse;
    }


}
