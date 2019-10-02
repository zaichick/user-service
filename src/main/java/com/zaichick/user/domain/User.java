package com.zaichick.user.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zaichick.user.enums.UserType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Calendar;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDBTable(tableName = "user-record-table")
public class User {

    @DynamoDBHashKey(attributeName = "PK")
    private String id;
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "email-index")
    private String email;
    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;
    private UserType userType;
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
    private Calendar updatedDt;
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    private Calendar createDt;

    public User() {
    }

    public User(
            String id,
            String email,
            String firstName,
            String lastName,
            Calendar dateOfBirth,
            UserType userType,
            Calendar updatedDt,
            Calendar createDt
    ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
        this.updatedDt = updatedDt;
        this.createDt = createDt;
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

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public static final class Builder {
        private String id;
        private String email;
        private String firstName;
        private String lastName;
        private Calendar updatedDt;
        private Calendar createDt;
        private Calendar dateOfBirth;
        private UserType userType;

        public static Builder create() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUpdatedDt(Calendar updatedDt) {
            this.updatedDt = updatedDt;
            return this;
        }

        public Builder withCreateDt(Calendar createDt) {
            this.createDt = createDt;
            return this;
        }

        public Builder withDateOfBirth(Calendar dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public User build() {
            return new User(id, email, firstName, lastName, dateOfBirth, userType, updatedDt, createDt);
        }
    }
}
