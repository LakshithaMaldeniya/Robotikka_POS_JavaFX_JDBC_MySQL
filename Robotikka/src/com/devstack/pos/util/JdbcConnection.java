package com.devstack.pos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private JdbcConnection(){}

   private static JdbcConnection jdbcConnection=null;

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null!=connection){
            return connection;
        }else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka",
                        "root","1234");
                 return connection;
        }

    }
}
