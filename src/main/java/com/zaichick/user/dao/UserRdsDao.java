package com.zaichick.user.dao;

import com.zaichick.user.domain.UserRds;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserRdsDao {

    private static final String CREATE_USER  = "INSERT INTO Users (id, firstName, lastName, createdDt, dateOfBirth) " +
            "VALUES (:id, :firstName, :lastName, :createdDt, :dateOfBirth)";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private RowMapper<UserRds> userRowMapper;

    public UserRdsDao(
//            NamedParameterJdbcTemplate namedParameterJdbcTemplate,
//            RowMapper<UserRds> userRowMapper
    ) {

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSourceProvider.getDataSource());
//        this.userRowMapper = userRowMapper;
    }

    public void save(UserRds user) {

        final Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", user.getId());
        parameterMap.put("firstName", user.getFirstName());
        parameterMap.put("lastName", user.getLastName());
        parameterMap.put("createdDt", user.getCreateDt());
        parameterMap.put("dateOfBirth", user.getDateOfBirth());

        namedParameterJdbcTemplate.update(CREATE_USER, parameterMap);
    }

    // TODO: testing
    public static void main(String[] args) {
        UserRdsDao userRdsDao = new UserRdsDao();

//        Date dob = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1989);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.DAY_OF_MONTH, 29);

//        Date dob = cal.getTime();

        userRdsDao.save(UserRds.Builder.create()
                .withId("456")
                .withFirstName("ali")
                .withLastName("pipes")
                .withCreateDt(new Date())
                .withDateOfBirth(cal.getTime())
                .build());
    }


}
