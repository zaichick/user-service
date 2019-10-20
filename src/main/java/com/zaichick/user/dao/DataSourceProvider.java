package com.zaichick.user.dao;

import com.zaichick.user.config.DbCredentials;
import com.zaichick.user.config.ParameterStore;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

//    private static final String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public DataSource getDataSource() {

        ParameterStore parameterStore = new ParameterStore();
        DbCredentials dbCredentials = parameterStore.getDbCredentials();

//        DatabaseCredentials databaseCredentials = this.getDatabaseCredentials();
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbCredentials.getUrl());
        basicDataSource.setDriverClassName(dbCredentials.getDriverClassName());
        basicDataSource.setUsername(dbCredentials.getUsername());
        basicDataSource.setPassword(dbCredentials.getPassword());
//        this.getCommonCredentials(databaseCredentials, basicDataSource);
        return basicDataSource;
    }
}
