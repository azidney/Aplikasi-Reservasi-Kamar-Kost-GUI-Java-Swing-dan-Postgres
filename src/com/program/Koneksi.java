package com.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class Koneksi {

    /* KONEKSI KE DATABASE POSTGRES */
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

    /* HALAMAN ADMIN */

    // 1. FUNCTION / METHOD BUAT NGAMBIL SEMUA DATA USER
    public static DefaultTableModel getAllUser() {
        connection();

        // bikin header tabel
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

    // 2. FUNCTION BUAT NGAMBIL SEMUA DATA KAMAR
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

    // 3. FUNCTION BUAT NGAMBIL SEMUA DATA KAMAR YANG AKTIF
    public static DefaultTableModel getAllKamarAktif() {
        connection();

        String[] dataHeader = { "id_kamar", "nomor", "tipe", "harga", "status", "Waktu" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_kamar WHERE status = 'Aktif' ORDER BY id_kamar ASC";
            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_kamar"), resultData.getString("nomor"),
                        resultData.getString("tipe"), resultData.getString("harga"), resultData.getString("status"),
                        "1 bulan" };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // 4. FUNCTION BUAT NGAMBIL SEMUA DATA KAMAR YANG TIDAK AKTIF
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

    // 5. FUNCTION HITUNG SEMUA DATA USER DAN KAMAR
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

    // 6. FUNCTION BUAT NYARI DATA USER DAN KAMAR
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

    // 7. FUNCTION / METHOD BUAT NAMBAH DATA KAMAR
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

    // 8. FUNCTION BUAT NGAMBIL DATA KAMAR SESUAI ID
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

    // 9. MENGUBAH DATA KAMAR
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

    // 10. MENGHAPUS USER
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

    // 11. NGAMBIL SEMUA DATA RESERVASI
    public static DefaultTableModel getAllReservasi() {
        connection();

        String[] dataHeader = { "id_reservasi", "Nama User", "Nomor Kamar", "Tipe Kamar", "Harga", "Waktu" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT c.id_reservasi, a.nama, b.nomor, b.tipe, b.harga, c.waktu from tbl_user as a, tbl_kamar as b, tbl_reservasi as c  where c.id_user = a.id_user and c.id_kamar = b.id_kamar";

            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_reservasi"), resultData.getString("nama"),
                        resultData.getString("nomor"), resultData.getString("tipe"), resultData.getString("harga"),
                        resultData.getString("waktu") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // 12. TOTAL RESERVASI
    public static int getCountAllReservasi() {
        connection();
        int count = 0;

        try {
            statement = connect.createStatement();
            String query = "SELECT COUNT(*) FROM tbl_reservasi";
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

    // 13. CARI RESERVASI
    public static DefaultTableModel getSearchReservasi(String keyword) {
        connection();

        String[] dataHeader = { "id_reservasi", "Nama User", "Nomor Kamar", "Tipe Kamar", "Harga", "Waktu" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT c.id_reservasi, a.nama, b.nomor, b.tipe, b.harga, c.waktu from tbl_user as a, tbl_kamar as b, tbl_reservasi as c  where c.id_user = a.id_user and c.id_kamar = b.id_kamar and a.nama LIKE '%"
                    + keyword
                    + "%'";

            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_reservasi"), resultData.getString("nama"),
                        resultData.getString("nomor"), resultData.getString("tipe"), resultData.getString("harga"),
                        resultData.getString("waktu") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    /* HALAMAN LOGIN DAN REGISTER */

    // 1. BUAT LOGIN USER
    public static boolean loginUser(String email, String password) {
        connection();
        boolean available = false;

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "SELECT COUNT(*) FROM tbl_user WHERE email = '" + email + "' AND password = '"
                    + password + "'";

            // eksekusi query-nya
            ResultSet resultData = statement.executeQuery(query);

            // looping pengisian DefaultTableModel
            resultData.next();
            if (resultData.getInt(1) == 1) {
                available = true;
            }

            // close statement dan connection
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return available;
    }

    // 2. NGAMBIL DATA USER SESUAI EMAIL
    public static Object[] getDetailEmailUser(String email) {
        connection();

        Object rowData[] = new Object[4];

        try {

            statement = connect.createStatement();
            String query = "SELECT * FROM tbl_user WHERE email = '" + email + "'";
            ResultSet resultData = statement.executeQuery(query);
            resultData.next();
            rowData[0] = resultData.getInt("id_user");
            rowData[1] = resultData.getString("email");
            rowData[2] = resultData.getString("password");
            rowData[3] = resultData.getString("nama");
            rowData[4] = resultData.getString("alamat");
            rowData[5] = resultData.getString("no_hp");

            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowData;

    }

    // 3. BUAT NGECEK APAKAH ADA USER DI DATABASE ATAU TIDAK SESUAI EMAIL
    public static boolean verifyEmailUser(String email) {
        connection();
        boolean available = false;

        try {

            statement = connect.createStatement();
            String query = "SELECT COUNT(*) FROM tbl_user WHERE email = '" + email + "'";
            ResultSet resultData = statement.executeQuery(query);
            resultData.next();
            if (resultData.getInt(1) == 0) {
                available = true;
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return available;
    }

    // 4. REGISTER USER
    public static boolean daftarUser(String email, String password, String nama, String alamat, String no_hp) {
        boolean data = false;

        connection();

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // query select
            String query = "INSERT INTO tbl_user VALUES (DEFAULT, '" + email + "', '" + password + "', '" + nama
                    + "', '" + alamat + "', '" + no_hp + "')";

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

    /* HALAMAN USER */

    // 1. NGAMBIL DATA USER SESUAI ID
    public static Object[] getDetailUser(int id_user) {
        connection();

        Object rowData[] = new Object[6];

        try {

            statement = connect.createStatement();

            String query = "SELECT * FROM tbl_user WHERE id_user = " + id_user;

            ResultSet resultData = statement.executeQuery(query);

            resultData.next();
            rowData[0] = resultData.getInt("id_user");
            rowData[1] = resultData.getString("email");
            rowData[2] = resultData.getString("password");
            rowData[3] = resultData.getString("nama");
            rowData[4] = resultData.getString("alamat");
            rowData[5] = resultData.getString("no_hp");
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowData;

    }

    // 2. MENGUBAH DATA USER
    public static boolean ubahDataUser(int id_user, String email, String password, String nama,
            String alamat, int no_hp) {
        boolean data = false;
        connection();
        try {

            statement = connect.createStatement();

            String query = "UPDATE tbl_user SET email = '" + email + "', password = '" + password
                    + "', nama = '" + nama + "', alamat = '" + alamat
                    + "' , no_hp = " + no_hp + " WHERE id_user = " + id_user;

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

    // 3. NGAMBIL DATA RESERVASI SESUAI ID USER
    public static DefaultTableModel getReservasiUser(int id_user) {
        connection();

        String[] dataHeader = { "id_reservasi", "Nama", "Nomor Kamar", "Tipe", "Harga", "Waktu" };
        DefaultTableModel tm = new DefaultTableModel(null, dataHeader);

        try {
            statement = connect.createStatement();
            String query = "SELECT c.id_reservasi, a.nama, b.nomor, b.tipe, b.harga, c.waktu from tbl_user as a, tbl_kamar as b, tbl_reservasi as c  where c.id_user = a.id_user and c.id_kamar = b.id_kamar and c.id_user = "
                    + id_user;

            ResultSet resultData = statement.executeQuery(query);
            while (resultData.next()) {
                Object[] rowData = { resultData.getInt("id_reservasi"), resultData.getString("nama"),
                        resultData.getString("nomor"), resultData.getString("tipe"), resultData.getString("harga"),
                        resultData.getString("waktu") };
                tm.addRow(rowData);
            }
            statement.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tm;

    }

    // 4. USER MELAKUKAN CHECK OUT JADINYA TERHAPUS
    public static boolean hapusReservasi(int id_reservasi) {
        boolean data = false;
        connection();
        try {
            statement = connect.createStatement();
            String query = "DELETE FROM tbl_reservasi WHERE id_reservasi = " + id_reservasi;

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

    // 5. JUMLAHIN SEMUA DATA RESERVASI SESUAI USER
    public static int getCountAllReservasiUser(int id_user) {
        connection();
        int count = 0;

        try {
            statement = connect.createStatement();
            String query = "SELECT COUNT(*) FROM tbl_reservasi WHERE id_user = " + id_user;
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

    // 5. FUNCTION MELAKUKAN RESERVASI
    public static boolean tambahReservasi(int id_user, int id_kamar, String waktu) {
        boolean data = false;

        connection();

        try {

            statement = connect.createStatement();
            String query = "INSERT into tbl_reservasi VALUES ( DEFAULT , " + id_user + ",  " + id_kamar + ", '"
                    + waktu + "')";

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
