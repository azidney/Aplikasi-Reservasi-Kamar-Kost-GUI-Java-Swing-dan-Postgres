package com.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class Koneksi {
    private static final String hostname = "localhost";
    private static final String port = "5432";
    private static final String dbname = "dbkos";
    private static final String username = "postgres";
    private static final String password = "1";
    private static final String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbname + "?user=" + username
            + "&password=" + password;

    private static Connection connect;

    private static Statement statement;

    private static void connection() {
        try {
            connect = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // function ngambil semua data kamar dan user
    public static DefaultTableModel getAllUser() {
        connection();

        String[] dataHeader = { "id", "Nama User", "Email User", "Alamat User", "No Handphone" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "SELECT * FROM tbl_user ORDER BY id_user ASC";

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

    public static DefaultTableModel getAllKamar() {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar ORDER BY id_kamar ASC";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // function ngambil semua data kamar yang aktif
    public static DefaultTableModel getAllKamarAktif() {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE status = 'Aktif' ORDER BY id_kamar ASC";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // function ngambil semua data kamar yang tidak aktif
    public static DefaultTableModel getAllKamarTidakAktif() {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE status = 'Tidak Aktif' ORDER BY id_kamar ASC";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // function hitung semua user dan kamar
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

    public static int getCountAllKamarAktif() {
        connection();
        int count = 0;

        try {
            statement = connect.createStatement();
            String query = "SELECT COUNT(*) FROM tbl_kamar WHERE status = 'Aktif'";
            ResultSet resultData = statement.executeQuery(query);
            resultData.next();
            count = resultData.getInt(1);
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int getCountAllKamarTidakAktif() {
        connection();
        int count = 0;

        try {
            statement = connect.createStatement();
            String query = "SELECT COUNT(*) FROM tbl_kamar WHERE status = 'Tidak Aktif'";
            ResultSet resultData = statement.executeQuery(query);
            resultData.next();
            count = resultData.getInt(1);
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // buat nyari data user dan kamar
    public static DefaultTableModel getSearchUser(String keyword) {
        connection();

        String[] dataHeader = { "id_user", "Nama User", "Email User", "Alamat User", "No Handphone" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_user WHERE nama LIKE '%" + keyword + "%' OR email LIKE '%" + keyword
                    + "%'";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_user"), resultData.getString("nama"),
                        resultData.getString("email"), resultData.getString("alamat"), resultData.getString("no_hp") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    public static DefaultTableModel getSearchAllKamar(String keyword) {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE tipe LIKE '%" + keyword + "%' OR nomor LIKE '%" + keyword
                    + "%'";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    public static DefaultTableModel getSearchAllKamarAktif(String keyword) {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE tipe LIKE '%" + keyword + "%' OR nomor LIKE '%" + keyword
                    + "%' AND status = 'Aktif'";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    public static DefaultTableModel getSearchAllKamarTidakAktif(String keyword) {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE tipe LIKE '%" + keyword + "%' OR nomor LIKE '%" + keyword
                    + "%' AND status = 'Tidak Aktif'";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // Method for insert data kamar
    public static boolean tambahdataKamar(int nomor, String tipe, int harga,
            String statusAktif) {
        boolean data = false;

        connection();

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "INSERT into tbl_kamar VALUES ( DEFAULT , " + nomor + ",  '" + tipe + "', "
                    + harga + ", '" + statusAktif + "')";

            if (statement.executeUpdate(query) > 0) {
                data = true;
            }

            // close statement dan connection
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // get detail kamar
    public static Object[] getDetailKamar(int id_kamar) {
        connection();

        Object[] rowData = new Object[5];
        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE id_kamar = " + id_kamar;
            ResultSet resultData = statement.executeQuery(query);

            resultData.next();
            rowData[0] = resultData.getInt("id_kamar");
            rowData[1] = resultData.getString("nomor");
            rowData[2] = resultData.getString("tipe");
            rowData[3] = resultData.getInt("harga");
            rowData[4] = resultData.getString("status");
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowData;

    }

    // ubah data kamar
    public static boolean ubahDataKamar(int id_kamar, int nomor, String tipe, int harga,
            String statusAktif) {
        boolean data = false;

        connection();

        try {

            statement = connect.createStatement();

            String query = "UPDATE tbl_kamar SET nomor = " + nomor + ", tipe = '" + tipe
                    + "', harga = " + harga + ", status = '" + statusAktif
                    + "' WHERE id_kamar = " + id_kamar;

            if (statement.executeUpdate(query) > 0) {
                data = true;
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // hapus user
    public static boolean hapusUser(int id_user) {
        boolean data = false;
        connection();
        try {
            statement = connect.createStatement();
            String query = "DELETE FROM tbl_user WHERE id_user = " + id_user;

            if (statement.executeUpdate(query) > 0) {
                data = true;
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

}
