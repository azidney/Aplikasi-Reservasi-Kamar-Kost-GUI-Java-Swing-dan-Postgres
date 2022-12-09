package com.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Model {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dbkos";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";

    private static Connection connect;

    private static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
