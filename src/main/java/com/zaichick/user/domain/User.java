package com.zaichick.user.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Calendar;

@DynamoDBTable(tableName = "user-record-table")
public class User {

    @DynamoDBHashKey
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Calendar updatedDt;
    private Calendar createDt;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Calendar updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Calendar getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Calendar createDt) {
        this.createDt = createDt;
    }
}
