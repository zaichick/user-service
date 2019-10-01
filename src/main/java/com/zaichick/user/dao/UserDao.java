package com.zaichick.user.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.zaichick.user.domain.User;

import java.util.List;

public class UserDao {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.EU_WEST_2)
            .build();

    private DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

    public void save(User user) {

        dynamoDBMapper.save(user);
    }

    public User getById(String id) {
        return dynamoDBMapper.load(User.class, id);
    }

    public List<User> findUserByEmailAddress(String email) {

        User user = User.Builder.create()
                .withEmail(email)
                .build();

        return dynamoDBMapper.query(User.class, new DynamoDBQueryExpression<User>()
                .withIndexName("email-index")
                .withConsistentRead(false)
                .withHashKeyValues(user));
    }
}
