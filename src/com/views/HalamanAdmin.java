package com.views;

import com.desain.*;
import com.program.Koneksi;
import com.template.TemplateHalamanAdmin;

import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

class Filter {
  static String filter = "semua";
  static String keyword = "";

  static DefaultTableModel search() {
    DefaultTableModel tm = new DefaultTableModel();

    if (filter.equalsIgnoreCase("semua")) {
      // memanggil dan menggantikan tm dengan return dari getSearchAllPaket
      tm = Koneksi.getSearchAllKamar(keyword);
    } else if (filter.equalsIgnoreCase("Aktif")) {
      tm = Koneksi.getSearchAllKamarAktif(keyword);
    } else if (filter.equalsIgnoreCase("Tidak Aktif")) {
      tm = Koneksi.getSearchAllKamarTidakAktif(keyword);
    }

    return tm;
  }

}

public class HalamanAdmin extends TemplateHalamanAdmin {

  private boolean statusLogin = false;
  private Integer idSelected = null;

  // sidebar menu
  private cSidebarMenu menuBeranda = new cSidebarMenu("Beranda", 70);
  private cSidebarMenu menuDataUser = new cSidebarMenu("Data User", 70 + 50);
  private cSidebarMenu menuDataKamar = new cSidebarMenu("Data Kamar", 70 + 50 + 50);
  private cSidebarMenu menuDataReservasi = new cSidebarMenu("Data Reservasi", 70 + 50 + 50 + 50);
  private cSidebarMenu menuLogout = new cSidebarMenu("Logout", 70 + 50 + 50 + 50 + 50);

  // beranda components
  private cLabelInfo labelJmlDataMitraBeranda = new cLabelInfo("Jumlah Data User", 25, 20);
  private cBigFont valueJumlahDataUser = new cBigFont("0", 25, 60);
  private cLabelInfo labelJmlDataUserBeranda = new cLabelInfo("Jumlah Data Kamar Aktif", 25, 150);
  private cBigFont valueJmlKamarAktif = new cBigFont("0", 25, 190);
  private cLabelInfo labelJmlTransaksiPulsaBeranda = new cLabelInfo("Jumlah Data Kamar Tidak Aktif", 495, 20);
  private cBigFont valueJmlKamarTidakAktif = new cBigFont("0", 495, 60);
  private cLabelInfo labelJmlDataReservasi = new cLabelInfo("Jumlah Data Transaksi", 495, 150);
  private cBigFont valueJmlDataReservasi = new cBigFont("0", 495, 190);

  // Data User components
  private cLabelInfo labelDataUser = new cLabelInfo("Berikut adalah data user", 25, 20);
  private cFormLabel labelCariDataUser = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataUser = new cTextField(83, 70, 350, false);
  private cTable tblDataDataUser;
  private cScrollPane spDataDataUser;
  private cBlueButton btnHapusDataUser = new cBlueButton("Hapus", 25, 446, 110);

  // Data Reservasi components
  private cLabelInfo labelDataReservasi = new cLabelInfo("Berikut adalah data reservasi", 25, 20);
  private cFormLabel labelCariDataReservasi = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataReservasi = new cTextField(83, 70, 350, false);
  private cTable tblDataReservasi;
  private cScrollPane spDataDataReservasi;
  private cBlueButton btnHapusDataReservasi = new cBlueButton("Hapus", 25, 446, 110);

  // Data Kamar components
  private cLabelInfo labelDataKamar = new cLabelInfo("Berikut adalah data kamar", 25, 20);
  private cFormLabel labelCariDatakamar = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataKamar = new cTextField(83, 70, 317, false);
  private cBlueButton btnTambahDataKamar = new cBlueButton("Tambah Kamar", 418, 70, 162);
  private cRadioButton rdSemuaDataKamar = new cRadioButton("Semua", "all", 25, 122, 105);
  private cRadioButton rdAktifDataKamar = new cRadioButton("Aktif", "active", 130, 122, 85);
  private cRadioButton rdTidakAktifDataKamar = new cRadioButton("Tidak Aktif", "nonactive", 225, 122, 112);
  private cTable tblDataDataKamar;
  private cScrollPane spDataDataKamar;
  private cBlueButton btnUbahDataKamar = new cBlueButton("Ubah", 25, 410, 92);

  // tambah Data Kamar components
  private cLabelInfo labelTambahKamar = new cLabelInfo("Isi form data kamar dengan lengkap", 25, 20);
  private cFormLabel nomorTambahKamar = new cFormLabel("Nomor kamar", 25, 65, 550, false);
  private cTextField txtTambahNomorKamar = new cTextField(25, 90, 550, false);
  private cErrorLabel erorNomorTambahKamar = new cErrorLabel("Nomor kamar tidak boleh kosong!", 25, 125, 550,
      false);
  private cFormLabel labelFormTambahKamar = new cFormLabel("Tipe kamar", 25, 150, 550, false);
  private cTextField txtTambahNamaTipe = new cTextField(25, 175, 550, false);
  private cErrorLabel errorKuotaPaketTambahDataPaket = new cErrorLabel("tipekamar tidak boleh kosong!", 25, 210, 550,
      false);
  private cFormLabel labelHargaPaketTambahDataPaket = new cFormLabel("Harga kamar", 25, 235, 550, false);
  private cTextField txtTambahNamaHarga = new cTextField(25, 260, 550, false);
  private cErrorLabel errorHargaPaketTambahDataPaket = new cErrorLabel("harga kamar tidak boleh kosong!", 25, 295, 550,
      false);
  private cCheckbox chAktifTambahDataPaket = new cCheckbox("Aktifkan", "Aktif", 25, 316, 100);
  private cBlueButton btnTambahKamar = new cBlueButton("Tambah", 25, 348, 110);
  private cRedButton btnBatalTambahDataPaket = new cRedButton("Batal", 155, 348, 110);

  // Ubah Data Kamar components
  private cLabelInfo labelUbahDataKamar = new cLabelInfo("Isi form data kamar dengan lengkap", 25, 20);
  private cFormLabel labelNomorUbahDataKamar = new cFormLabel("Nama kamar", 25, 65, 550, false);
  private cTextField txtUbahNomorKamar = new cTextField(25, 90, 550, false);
  private cErrorLabel erorUbahNomorKamar = new cErrorLabel("nama kamar tidak boleh kosong!", 25, 125, 550,
      false);
  private cFormLabel labelTipeUbahKamar = new cFormLabel("Kuota kamar", 25, 150, 550, false);
  private cTextField txtUbahTipeKamar = new cTextField(25, 175, 550, false);
  private cErrorLabel erorUbahTipeKamar = new cErrorLabel("kuota kamar tidak boleh kosong!", 25, 210, 550,
      false);
  private cFormLabel labelHargaUbahTipeKamar = new cFormLabel("Harga kamar", 25, 235, 550, false);
  private cTextField txtUbahHargaKamar = new cTextField(25, 260, 550, false);
  private cErrorLabel erorHargaUbahKamar = new cErrorLabel("harga kamar tidak boleh kosong!", 25, 295, 550,
      false);
  private cCheckbox chAktifUbahKamar = new cCheckbox("Aktifkan", "Aktif", 25, 316, 100);
  private cBlueButton btnUbahDataKamarUbahData = new cBlueButton("Ubah", 25, 348, 110);
  private cBlueButton btnBatalUbahDataKamar = new cRedButton("Batal", 155, 348, 110);

  // method resetSidebar
  private void resetSidebar() {
    try {
      setVisible(false);

      menuBeranda.setForeground(cColor.GRAY);
      menuBeranda.setBackground(cColor.WHITE);
      menuBeranda.setSidebarNonAktif();

      menuDataUser.setForeground(cColor.GRAY);
      menuDataUser.setBackground(cColor.WHITE);
      menuDataUser.setSidebarNonAktif();

      menuDataKamar.setForeground(cColor.GRAY);
      menuDataKamar.setBackground(cColor.WHITE);
      menuDataKamar.setSidebarNonAktif();

      menuDataReservasi.setForeground(cColor.GRAY);
      menuDataReservasi.setBackground(cColor.WHITE);
      menuDataReservasi.setSidebarNonAktif();

      menuLogout.setSidebarNonAktif();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  // method refresh content
  private void refreshContent() {
    try {
      content.removeAll();
    } catch (Exception e) {
    }
  }

  public HalamanAdmin(boolean statusLogin) {
    super("Dashboard Admin");
    this.statusLogin = statusLogin;
    this.idSelected = null;
    roleText.setText("Admin");
    menuBeranda.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsBeranda();
      }
    });
    menuDataUser.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsDataUser();
      }
    });

    menuDataReservasi.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsDataReservasi();
      }
    });
    menuDataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsDataKamar();
      }
    });

    menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsLogout();
      }
    });

    // add component default
    sidebar.add(menuBeranda);
    sidebar.add(menuDataUser);
    sidebar.add(menuDataKamar);

    sidebar.add(menuDataReservasi);
    sidebar.add(menuLogout);

    initsBeranda();
  }

  private void initsBeranda() {
    idSelected = null;
    resetSidebar();
    menuBeranda.setBackground(cColor.GREEN);
    menuBeranda.setForeground(cColor.WHITE);
    refreshContent();
    menuBeranda.setSidebarAktif();
    menuTitle.setText("Beranda");

    valueJumlahDataUser.setText(String.valueOf(Koneksi.getCountAllUser()));
    valueJmlKamarAktif.setText(String.valueOf(Koneksi.getCountAllKamarAktif()));
    valueJmlKamarTidakAktif.setText(String.valueOf(Koneksi.getCountAllKamarTidakAktif()));
    valueJmlDataReservasi.setText(String.valueOf(Koneksi.getCountAllReservasi()));

    content.add(labelJmlDataMitraBeranda);
    content.add(valueJumlahDataUser);
    content.add(labelJmlDataUserBeranda);
    content.add(valueJmlKamarAktif);
    content.add(labelJmlTransaksiPulsaBeranda);
    content.add(valueJmlKamarTidakAktif);
    content.add(labelJmlDataReservasi);
    content.add(valueJmlDataReservasi);
    setVisible(true);
  }

  private void initsDataUser() {
    idSelected = null;
    resetSidebar();
    menuDataUser.setBackground(cColor.GREEN);
    menuDataUser.setForeground(cColor.WHITE);
    refreshContent();
    menuDataUser.setSidebarAktif();
    menuTitle.setText("Data User");
    tblDataDataUser = new cTable(Koneksi.getAllUser());

    tblDataDataUser.getColumnModel().getColumn(0).setMinWidth(100);
    tblDataDataUser.getColumnModel().getColumn(0).setMaxWidth(100);
    tblDataDataUser.getColumnModel().getColumn(0).setWidth(100);

    spDataDataUser = new cScrollPane(tblDataDataUser, 25, 120, 725, 310); // width = 925

    // cari user
    txtCariDataUser.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        String keyword = txtCariDataUser.getText();
        tblDataDataUser.setModel(Koneksi.getSearchUser(keyword));
        tblDataDataUser.getColumnModel().getColumn(0).setMinWidth(100);
        tblDataDataUser.getColumnModel().getColumn(0).setMaxWidth(100);
        tblDataDataUser.getColumnModel().getColumn(0).setWidth(100);
      }
    });

    btnHapusDataUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        int selectedIndex = tblDataDataUser.getSelectedRow();

        if (selectedIndex != -1) {
          // kalo ada yang dipilih
          int id_user = Integer.valueOf(tblDataDataUser.getValueAt(selectedIndex, 0).toString());

          if (Koneksi.hapusUser(id_user)) {
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Data User berhasil dihapus!", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
            initsDataUser();
          } else {
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Data User gagal dihapus!", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          // kalo gak ada yang diseleksi
          JOptionPane.showMessageDialog(HalamanAdmin.this, "Pilih data terlebih dahulu!", "Peringatan",
              JOptionPane.WARNING_MESSAGE);
        }

      }
    });
    content.add(labelDataUser);
    content.add(labelCariDataUser);
    content.add(txtCariDataUser);
    content.add(spDataDataUser);
    content.add(btnHapusDataUser);
    setVisible(true);
  }

  private void initsDataKamar() {
    idSelected = null;
    resetSidebar();
    menuDataKamar.setBackground(cColor.GREEN);
    menuDataKamar.setForeground(cColor.WHITE);
    refreshContent();
    menuDataKamar.setSidebarAktif();
    menuTitle.setText("Data Kamar");

    tblDataDataKamar = new cTable(Koneksi.getAllKamar());
    tblDataDataKamar.getColumnModel().getColumn(0).setMinWidth(0);
    tblDataDataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
    tblDataDataKamar.getColumnModel().getColumn(0).setWidth(0);

    spDataDataKamar = new cScrollPane(tblDataDataKamar, 25, 155, 925, 250);

    // cari kamar
    txtCariDataKamar.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        Filter.keyword = txtCariDataKamar.getText();
        tblDataDataKamar.setModel(Filter.search());
        tblDataDataKamar.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setWidth(0);
      }
    });

    // bikin group kamar aktif / tidak aktif
    ButtonGroup groupButtonRadio = new ButtonGroup();
    groupButtonRadio.add(rdSemuaDataKamar);
    groupButtonRadio.add(rdAktifDataKamar);
    groupButtonRadio.add(rdTidakAktifDataKamar);
    rdSemuaDataKamar.setSelected(true);

    rdSemuaDataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Filter.filter = "semua";

        tblDataDataKamar.setModel(Koneksi.getAllKamar());
        tblDataDataKamar.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setWidth(0);

      }
    });

    rdAktifDataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override

      public void mouseClicked(java.awt.event.MouseEvent me) {
        Filter.filter = "Aktif";

        tblDataDataKamar.setModel(Koneksi.getAllKamarAktif());
        tblDataDataKamar.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setWidth(0);

        tblDataDataKamar.getColumnModel().getColumn(5).setMinWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(5).setWidth(0);

      }
    });

    rdTidakAktifDataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override

      public void mouseClicked(java.awt.event.MouseEvent me) {
        Filter.filter = "Tidak Aktif";
        tblDataDataKamar.setModel(Koneksi.getAllKamarTidakAktif());
        tblDataDataKamar.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataDataKamar.getColumnModel().getColumn(0).setWidth(0);

      }
    });

    btnTambahDataKamar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsTambahKamar();
      }
    });

    btnUbahDataKamar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        int selectedIndex = tblDataDataKamar.getSelectedRow();
        // validasi kalo gak ada yang diseleksi
        if (selectedIndex == -1) {
          // ngapain kalo gak ada yang diseleksi
          JOptionPane.showMessageDialog(HalamanAdmin.this, "Pilih data kamar yang akan diubah terlebih dahulu!",
              "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
          int id_kamar = Integer.valueOf(tblDataDataKamar.getValueAt(selectedIndex, 0).toString());
          initsUbahKamar(id_kamar);
        }
      }
    });

    content.add(labelDataKamar);
    content.add(labelCariDatakamar);
    content.add(txtCariDataKamar);
    content.add(btnTambahDataKamar);
    content.add(rdSemuaDataKamar);
    content.add(rdAktifDataKamar);
    content.add(rdTidakAktifDataKamar);
    content.add(spDataDataKamar);
    content.add(btnUbahDataKamar);
    setVisible(true);
  }

  private void initsTambahKamar() {
    // setVisible(false);
    idSelected = null;
    resetSidebar();
    menuDataKamar.setBackground(cColor.GREEN);
    menuDataKamar.setForeground(cColor.WHITE);
    refreshContent();
    menuDataKamar.setSidebarAktif();
    menuTitle.setText("Tambah Data Kamar");

    // set jadi null
    txtTambahNomorKamar.setText(null);
    txtTambahNamaTipe.setText(null);
    txtTambahNamaHarga.setText(null);
    chAktifTambahDataPaket.setSelected(false);

    btnBatalTambahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataKamar();
      }
    });

    btnTambahKamar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {

        // pengeckan kalau fieldnya kosong
        if (txtTambahNomorKamar.getText().trim().isEmpty()
            || txtTambahNamaTipe.getText().trim().isEmpty()
            || txtTambahNamaHarga.getText().trim().isEmpty()) {

          HalamanAdmin.this.setVisible(false);

          // spesifik ke txt nama paket
          if (txtTambahNomorKamar.getText().trim().isEmpty()) {
            content.add(erorNomorTambahKamar);
          } else {
            content.remove(erorNomorTambahKamar);
          }

          // spesifik ke tipe
          if (txtTambahNamaTipe.getText().trim().isEmpty())
            content.add(errorKuotaPaketTambahDataPaket);
          else
            content.remove(errorKuotaPaketTambahDataPaket);

          // spesifik ke Harga
          if (txtTambahNamaHarga.getText().trim().isEmpty())
            content.add(errorHargaPaketTambahDataPaket);
          else
            content.remove(errorHargaPaketTambahDataPaket);

          HalamanAdmin.this.setVisible(true);

        } else {
          // lakukan insert data

          int nomor = Integer.valueOf(txtTambahNomorKamar.getText());
          String tipe = txtTambahNamaTipe.getText();
          int harga = Integer.valueOf(txtTambahNamaHarga.getText());
          String statusAktif = chAktifTambahDataPaket.isSelected() ? chAktifTambahDataPaket.getActionCommand()
              : "Tidak Aktif";
          // panggil method tambahDataPaket
          if (Koneksi.tambahdataKamar(nomor, tipe, harga, statusAktif)) {
            // kalau berhasil
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Berhasil tambah data kamar.", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);

            txtTambahNomorKamar.setText(null);
            txtTambahNamaTipe.setText(null);
            txtTambahNamaHarga.setText(null);
            initsDataKamar();
          } else {
            // kalau tidak berhasil
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Gagal tambah data kamar.", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }

        }

      }
    });

    content.add(labelTambahKamar);
    content.add(nomorTambahKamar);
    content.add(txtTambahNomorKamar);
    content.add(labelFormTambahKamar);
    content.add(txtTambahNamaTipe);
    content.add(labelHargaPaketTambahDataPaket);
    content.add(txtTambahNamaHarga);
    content.add(chAktifTambahDataPaket);
    content.add(btnTambahKamar);
    content.add(btnBatalTambahDataPaket);
    setVisible(true);
  }

  private void initsUbahKamar(int id_kamar) {
    // setVisible(false);
    idSelected = null;
    resetSidebar();
    menuDataKamar.setBackground(cColor.GREEN);
    menuDataKamar.setForeground(cColor.WHITE);
    refreshContent();
    menuDataKamar.setSidebarAktif();
    menuTitle.setText("Ubah Data Kamar");

    // ngambil data kamar sesuai id
    Object[] detailKamar = Koneksi.getDetailKamar(id_kamar);

    txtUbahNomorKamar.setText(detailKamar[1].toString());
    txtUbahTipeKamar.setText(detailKamar[2].toString());
    txtUbahHargaKamar.setText(detailKamar[3].toString());
    if (detailKamar[4].toString().equalsIgnoreCase("Aktif")) {
      chAktifUbahKamar.setSelected(true);
    } else {
      chAktifUbahKamar.setSelected(false);
    }

    btnBatalUbahDataKamar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataKamar();
      }
    });
    btnUbahDataKamarUbahData.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {

        // pengeckan kalau fieldnya kosong
        if (txtUbahNomorKamar.getText().trim().isEmpty()
            || txtUbahTipeKamar.getText().trim().isEmpty()
            || txtUbahHargaKamar.getText().trim().isEmpty()) {

          HalamanAdmin.this.setVisible(false);

          if (txtUbahNomorKamar.getText().trim().isEmpty()) {
            content.add(erorUbahNomorKamar);
          } else {
            content.remove(erorUbahNomorKamar);
          }

          if (txtUbahTipeKamar.getText().trim().isEmpty())
            content.add(erorUbahTipeKamar);
          else
            content.remove(erorUbahTipeKamar);

          if (txtUbahHargaKamar.getText().trim().isEmpty())
            content.add(erorHargaUbahKamar);
          else
            content.remove(erorHargaUbahKamar);

          HalamanAdmin.this.setVisible(true);

        } else {
          // lakukan insert data
          int nomor = Integer.valueOf(txtUbahNomorKamar.getText());
          String tipe = txtUbahTipeKamar.getText();
          int harga = Integer.valueOf(txtUbahHargaKamar.getText());
          String statusAktif = chAktifUbahKamar.isSelected() ? chAktifUbahKamar.getActionCommand() : "Tidak Aktif";

          // panggil method ubahDataPaket
          if (Koneksi.ubahDataKamar(id_kamar, nomor, tipe, harga, statusAktif)) {
            // kalau berhasil
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Berhasil ubah data kamar.", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
            txtUbahNomorKamar.setText(null);
            txtUbahTipeKamar.setText(null);
            txtUbahHargaKamar.setText(null);
            initsDataKamar();
          } else {
            // kalau tidak berhasil
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Gagal ubah data kamar.", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }

        }

      }
    });
    content.add(labelUbahDataKamar);
    content.add(labelNomorUbahDataKamar);
    content.add(txtUbahNomorKamar);

    content.add(labelTipeUbahKamar);
    content.add(txtUbahTipeKamar);

    content.add(labelHargaUbahTipeKamar);
    content.add(txtUbahHargaKamar);

    content.add(chAktifUbahKamar);
    content.add(btnUbahDataKamarUbahData);
    content.add(btnBatalUbahDataKamar);
    setVisible(true);
  }

  private void initsDataReservasi() {
    idSelected = null;
    resetSidebar();
    menuDataReservasi.setBackground(cColor.GREEN);
    menuDataReservasi.setForeground(cColor.WHITE);
    refreshContent();
    menuDataReservasi.setSidebarAktif();
    menuTitle.setText("Data Reservasi");
    tblDataReservasi = new cTable(Koneksi.getAllReservasi());

    tblDataReservasi.getColumnModel().getColumn(0).setMinWidth(0);
    tblDataReservasi.getColumnModel().getColumn(0).setMaxWidth(0);
    tblDataReservasi.getColumnModel().getColumn(0).setWidth(0);

    spDataDataUser = new cScrollPane(tblDataReservasi, 25, 120, 725, 310); // width = 925

    // cari user
    txtCariDataReservasi.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        String keyword = txtCariDataReservasi.getText();
        tblDataReservasi.setModel(Koneksi.getSearchReservasi(keyword));
        tblDataReservasi.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataReservasi.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataReservasi.getColumnModel().getColumn(0).setWidth(0);
      }
    });
    btnHapusDataReservasi.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        int selectedIndex = tblDataReservasi.getSelectedRow();

        if (selectedIndex != -1) {
          // kalo ada yang dipilih
          int id_reservasi = Integer.valueOf(tblDataReservasi.getValueAt(selectedIndex, 0).toString());

          if (Koneksi.hapusReservasi(id_reservasi)) {
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Data Reservasi berhasil dihapus!", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
            initsDataReservasi();
          } else {
            JOptionPane.showMessageDialog(HalamanAdmin.this, "Data Reservasi gagal dihapus!", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          // kalo gak ada yang diseleksi
          JOptionPane.showMessageDialog(HalamanAdmin.this, "Pilih data terlebih dahulu!", "Peringatan",
              JOptionPane.WARNING_MESSAGE);
        }

      }
    });
    content.add(labelDataReservasi);
    content.add(labelCariDataReservasi);
    content.add(txtCariDataReservasi);
    content.add(spDataDataUser);
    content.add(btnHapusDataReservasi);
    setVisible(true);
  }

  private void initsLogout() {
    Object[] options = { "YA", "BATAL" };
    int confirm = JOptionPane.showOptionDialog(null, "Yakin ingin logout?", "Logout",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
        null, options, options[0]);
    if (confirm == 0) {
      this.statusLogin = false;
      this.idSelected = null;
      HalamanAdmin.this.setVisible(false);
      com.program.Controller.showLoginAdmin();
    }
  }

}
