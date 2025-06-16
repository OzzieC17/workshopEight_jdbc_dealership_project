package com.ps.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseManager {
    private DataSource dataSource;

    public DataBaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
