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
  private cSidebarMenu menuLogout = new cSidebarMenu("Logout", 70 + 50 + 50 + 50);

  // beranda components
  private cLabelInfo labelJmlDataMitraBeranda = new cLabelInfo("Jumlah Data User", 25, 20);
  private cBigFont valueJumlahDataUser = new cBigFont("0", 25, 60);
  private cLabelInfo labelJmlDataUserBeranda = new cLabelInfo("Jumlah Data Kamar Aktif", 25, 150);
  private cBigFont valueJmlKamarAktif = new cBigFont("0", 25, 190);
  private cLabelInfo labelJmlTransaksiPulsaBeranda = new cLabelInfo("Jumlah Data Kamar Tidak Aktif", 495, 20);
  private cBigFont valueJmlKamarTidakAktif = new cBigFont("0", 495, 60);
  private cLabelInfo labelJmlCalonMitraBeranda = new cLabelInfo("Jumlah Data Transaksi", 495, 150);
  private cBigFont valueJmlCalonMitraBeranda = new cBigFont("0", 495, 190);

  // Data User components
  private cLabelInfo labelDataUser = new cLabelInfo("Berikut adalah data user", 25, 20);
  private cFormLabel labelCariDataUser = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataUser = new cTextField(83, 70, 350, false);
  private cTable tblDataDataUser;
  private cScrollPane spDataDataUser;
  private cBlueButton btnHapusDataUser = new cBlueButton("Hapus", 25, 446, 110);

  // Data Kamar components
  private cLabelInfo labelDataKamar = new cLabelInfo("Berikut adalah data kamar", 25, 20);
  private cFormLabel labelCariDatakamar = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataKamar = new cTextField(83, 70, 317, false);
  private cBlueButton btnTambahDataKamar = new cBlueButton("Tambah Kamar", 418, 70, 162);
  private cRadioButton rdSemuaDataKamar = new cRadioButton("Semua", "all", 25, 115, 97);
  private cRadioButton rdAktifDataKamar = new cRadioButton("Aktif", "active", 132, 115, 72);
  private cRadioButton rdTidakAktifDataKamar = new cRadioButton("Tidak Aktif", "nonactive", 214, 115, 112);
  private cTable tblDataDataKamar;
  private cScrollPane spDataDataKamar;
  private cBlueButton btnUbahDataKamar = new cBlueButton("Ubah", 25, 410, 92);

  // TambahDataPaket components
  private cLabelInfo labelTambahKamar = new cLabelInfo("Isi form data kamar dengan lengkap", 25, 20);
  private cFormLabel namaTambahKamar = new cFormLabel("Nama kamar", 25, 65, 550, false);
  private cTextField txtTambahKamar = new cTextField(25, 90, 550, false);
  private cErrorLabel erorTambahKamar = new cErrorLabel("nama kamar tidak boleh kosong!", 25, 125, 550,
      false);
  private cFormLabel labelFormTambahKamar = new cFormLabel("Kuota kamar", 25, 150, 550, false);
  private cTextField txtKuotaPaketTambahDataPaket = new cTextField(25, 175, 550, false);
  private cErrorLabel errorKuotaPaketTambahDataPaket = new cErrorLabel("kuota kamar tidak boleh kosong!", 25, 210, 550,
      false);
  private cFormLabel labelHargaPaketTambahDataPaket = new cFormLabel("Harga kamar", 25, 235, 550, false);
  private cTextField txtHargaPaketTambahDataPaket = new cTextField(25, 260, 550, false);
  private cErrorLabel errorHargaPaketTambahDataPaket = new cErrorLabel("harga kamar tidak boleh kosong!", 25, 295, 550,
      false);
  private cCheckbox chAktifTambahDataPaket = new cCheckbox("Aktifkan", "1", 25, 316, 100);
  private cBlueButton btnTambahPaketTambahDataPaket = new cBlueButton("Tambah", 25, 348, 110);
  private cRedButton btnBatalTambahDataPaket = new cRedButton("Batal", 155, 348, 110);

  // UbahDataPaket components
  private cLabelInfo labelUbahDataPaket = new cLabelInfo("Isi form data kamar dengan lengkap", 25, 20);
  private cFormLabel labelNamaPaketUbahDataPaket = new cFormLabel("Nama kamar", 25, 65, 550, false);
  private cTextField txtNamaPaketUbahDataPaket = new cTextField(25, 90, 550, false);
  private cErrorLabel errorNamaPaketUbahDataPaket = new cErrorLabel("nama kamar tidak boleh kosong!", 25, 125, 550,
      false);
  private cFormLabel labelKuotaPaketUbahDataPaket = new cFormLabel("Kuota kamar", 25, 150, 550, false);
  private cTextField txtKuotaPaketUbahDataPaket = new cTextField(25, 175, 550, false);
  private cErrorLabel errorKuotaPaketUbahDataPaket = new cErrorLabel("kuota kamar tidak boleh kosong!", 25, 210, 550,
      false);
  private cFormLabel labelHargaPaketUbahDataPaket = new cFormLabel("Harga kamar", 25, 235, 550, false);
  private cTextField txtHargaPaketUbahDataPaket = new cTextField(25, 260, 550, false);
  private cErrorLabel errorHargaPaketUbahDataPaket = new cErrorLabel("harga kamar tidak boleh kosong!", 25, 295, 550,
      false);
  private cCheckbox chAktifUbahDataPaket = new cCheckbox("Aktifkan", "1", 25, 316, 100);
  private cBlueButton btnUbahPaketUbahDataPaket = new cBlueButton("Ubah", 25, 348, 110);
  private cBlueButton btnBatalUbahDataPaket = new cRedButton("Batal", 155, 348, 110);

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

    content.add(labelJmlDataMitraBeranda);
    content.add(valueJumlahDataUser);
    content.add(labelJmlDataUserBeranda);
    content.add(valueJmlKamarAktif);
    content.add(labelJmlTransaksiPulsaBeranda);
    content.add(valueJmlKamarTidakAktif);
    content.add(labelJmlCalonMitraBeranda);
    content.add(valueJmlCalonMitraBeranda);
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
        tblDataDataUser.getColumnModel().getColumn(0).setMinWidth(0);
        tblDataDataUser.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDataDataUser.getColumnModel().getColumn(0).setWidth(0);
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

    spDataDataKamar = new cScrollPane(tblDataDataKamar, 25, 145, 925, 250);
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
        initsTambahPaket();
      }
    });

    btnUbahDataKamar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsUbahPaket();
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

  private void initsTambahPaket() {
    // setVisible(false);
    idSelected = null;
    resetSidebar();
    menuDataKamar.setBackground(cColor.GREEN);
    menuDataKamar.setForeground(cColor.WHITE);
    refreshContent();
    menuDataKamar.setSidebarAktif();
    menuTitle.setText("Tambah Data Kamar");
    btnBatalTambahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataKamar();
      }
    });
    content.add(labelTambahKamar);
    content.add(namaTambahKamar);
    content.add(txtTambahKamar);
    content.add(erorTambahKamar);
    content.add(labelFormTambahKamar);
    content.add(txtKuotaPaketTambahDataPaket);
    content.add(errorKuotaPaketTambahDataPaket);
    content.add(labelHargaPaketTambahDataPaket);
    content.add(txtHargaPaketTambahDataPaket);
    content.add(errorHargaPaketTambahDataPaket);
    content.add(chAktifTambahDataPaket);
    content.add(btnTambahPaketTambahDataPaket);
    content.add(btnBatalTambahDataPaket);
    setVisible(true);
  }

  private void initsUbahPaket() {
    // setVisible(false);
    idSelected = null;
    resetSidebar();
    menuDataKamar.setBackground(cColor.GREEN);
    menuDataKamar.setForeground(cColor.WHITE);
    refreshContent();
    menuDataKamar.setSidebarAktif();
    menuTitle.setText("Ubah Data Kamar");
    btnBatalUbahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataKamar();
      }
    });
    content.add(labelUbahDataPaket);
    content.add(labelNamaPaketUbahDataPaket);
    content.add(txtNamaPaketUbahDataPaket);
    content.add(errorNamaPaketUbahDataPaket);
    content.add(labelKuotaPaketUbahDataPaket);
    content.add(txtKuotaPaketUbahDataPaket);
    content.add(errorKuotaPaketUbahDataPaket);
    content.add(labelHargaPaketUbahDataPaket);
    content.add(txtHargaPaketUbahDataPaket);
    content.add(errorHargaPaketUbahDataPaket);
    content.add(chAktifUbahDataPaket);
    content.add(btnUbahPaketUbahDataPaket);
    content.add(btnBatalUbahDataPaket);
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
      com.program.Controller.showLoginAdmin();
    }
  }

}
