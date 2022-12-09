package com.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class Model {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dbkos";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";

    private static Connection connect;

    private static Statement statement;

    private static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // mitra static function
    public static DefaultTableModel getAllUser() {
        connection();

        String[] dataHeader = { "id_user", "Nama User", "Email User", "Alamat User", "No Handphone" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "SELECT * FROM tbl_user";

            // eksekusi query-nya
            ResultSet resultData = statement.executeQuery(query);

            // looping pengisian DefaultTableModel
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_user"), resultData.getString("nama"),
                        resultData.getString("email"), resultData.getString("alamat"), resultData.getString("no_hp") };
                tm.addRow(rowData);
            }

            // close statement dan connection
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    public static int getCountAllUser() {
        connection();
        int count = 0;

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "SELECT COUNT(*) FROM tbl_user";

            // eksekusi query-nya
            ResultSet resultData = statement.executeQuery(query);

            // looping pengisian DefaultTableModel
            resultData.next();
            count = resultData.getInt(1);

            // close statement dan connection
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
