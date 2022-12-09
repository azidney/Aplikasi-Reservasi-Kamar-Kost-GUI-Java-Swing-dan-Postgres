package com.views;

import com.templates.cDashboardFrame;
import com.partials.*;
import com.program.Model;

import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

public class dashboardAdminView extends cDashboardFrame {

  private boolean statusLogin = false;
  private Integer idSelected = null;

  // sidebar menu
  private cSidebarMenu menuBeranda = new cSidebarMenu("Beranda", 70);
  private cSidebarMenu menuDataUser = new cSidebarMenu("Data User", 70 + 50);
  private cSidebarMenu menuDataPaket = new cSidebarMenu("Data Paket", 70 + 50 + 50);
  private cSidebarMenu menuLogout = new cSidebarMenu("Logout", 70 + 50 + 50 + 50);

  // beranda components
  private cLabelInfo labelJmlDataMitraBeranda = new cLabelInfo("Jumlah Data User", 25, 20);
  private cBigFont valueJumlahDataUser = new cBigFont("0", 25, 60);
  private cLabelInfo labelJmlDataUserBeranda = new cLabelInfo("Jumlah Data Kamar Aktif", 25, 150);
  private cBigFont valueJmlDataUserBeranda = new cBigFont("0", 25, 190);
  private cLabelInfo labelJmlTransaksiPulsaBeranda = new cLabelInfo("Jumlah Data Kamar Tidak Aktif", 495, 20);
  private cBigFont valueJmlTransaksiPulsaBeranda = new cBigFont("0", 495, 60);
  private cLabelInfo labelJmlCalonMitraBeranda = new cLabelInfo("Jumlah Data Transaksi", 495, 150);
  private cBigFont valueJmlCalonMitraBeranda = new cBigFont("0", 495, 190);

  // Data User components
  private cLabelInfo labelDataUser = new cLabelInfo("Data User Aktif", 25, 20);
  private cFormLabel labelCariDataUser = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataUser = new cTextField(83, 70, 350, false);
  private cTable tblDataDataUser;
  private cScrollPane spDataDataUser;
  private cBlueButton btnHapusDataUser = new cBlueButton("Hapus", 25, 446, 110);

  // Data Paket components
  private cLabelInfo labelDataPaket = new cLabelInfo("Data Kamar", 25, 20);
  private cFormLabel labelCariDataPaket = new cFormLabel("Cari", 25, 75, 55, false);
  private cTextField txtCariDataPaket = new cTextField(83, 70, 317, false);
  private cBlueButton btnTambahDataPaket = new cBlueButton("Tambah Kamar", 418, 70, 162);
  private cRadioButton rdSemuaDataPaket = new cRadioButton("Semua", "all", 25, 115, 97);
  private cRadioButton rdAktifDataPaket = new cRadioButton("Aktif", "active", 132, 115, 72);
  private cRadioButton rdTidakAktifDataPaket = new cRadioButton("Tidak Aktif", "nonactive", 214, 115, 112);
  private ButtonGroup groupActionDataPaket = new ButtonGroup();
  private DefaultTableModel dmDataPaket;
  private cTable tblDataDataPaket;
  private cScrollPane spDataDataPaket;
  private cBlueButton btnUbahDataPaket = new cBlueButton("Ubah", 25, 410, 92);

  // TambahDataPaket components
  private cLabelInfo labelTambahDataPaket = new cLabelInfo("Isi form data kamar dengan lengkap", 25, 20);
  private cFormLabel labelNamaPaketTambahDataPaket = new cFormLabel("Nama kamar", 25, 65, 550, false);
  private cTextField txtNamaPaketTambahDataPaket = new cTextField(25, 90, 550, false);
  private cErrorLabel errorNamaPaketTambahDataPaket = new cErrorLabel("nama kamar tidak boleh kosong!", 25, 125, 550,
      false);
  private cFormLabel labelKuotaPaketTambahDataPaket = new cFormLabel("Kuota kamar", 25, 150, 550, false);
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

      menuDataPaket.setForeground(cColor.GRAY);
      menuDataPaket.setBackground(cColor.WHITE);
      menuDataPaket.setSidebarNonAktif();

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

  public dashboardAdminView(boolean statusLogin) {
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
    menuDataPaket.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsDataPaket();
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
    sidebar.add(menuDataPaket);
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

    valueJumlahDataUser.setText(String.valueOf(Model.getCountAllUser()));

    content.add(labelJmlDataMitraBeranda);
    content.add(valueJumlahDataUser);
    content.add(labelJmlDataUserBeranda);
    content.add(valueJmlDataUserBeranda);
    content.add(labelJmlTransaksiPulsaBeranda);
    content.add(valueJmlTransaksiPulsaBeranda);
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
    tblDataDataUser = new cTable(Model.getAllUser());

    tblDataDataUser.getColumnModel().getColumn(0).setMinWidth(100);
    tblDataDataUser.getColumnModel().getColumn(0).setMaxWidth(100);
    tblDataDataUser.getColumnModel().getColumn(0).setWidth(100);

    spDataDataUser = new cScrollPane(tblDataDataUser, 25, 120, 725, 310); // width = 925
    content.add(labelDataUser);
    content.add(labelCariDataUser);
    content.add(txtCariDataUser);
    content.add(spDataDataUser);
    content.add(btnHapusDataUser);
    setVisible(true);
  }

  private void initsDataPaket() {
    idSelected = null;
    resetSidebar();
    menuDataPaket.setBackground(cColor.GREEN);
    menuDataPaket.setForeground(cColor.WHITE);
    refreshContent();
    menuDataPaket.setSidebarAktif();
    menuTitle.setText("Data Paket");
    String[] dataUserHeader = { "Header 1", "Header 2", "Header 3" };
    String[][] dataUser = {
        { "Row1 Col1", "Row1 Col2", "Row1 Col3" },
        { "Row2 Col1", "Row2 Col2", "Row2 Col3" },
        { "Row3 Col1", "Row3 Col2", "Row3 Col3" },
        { "Row4 Col1", "Row4 Col2", "Row4 Col3" },
        { "Row5 Col1", "Row5 Col2", "Row5 Col3" }
    };
    dmDataPaket = new DefaultTableModel(dataUser, dataUserHeader);
    tblDataDataPaket = new cTable(dmDataPaket);
    spDataDataPaket = new cScrollPane(tblDataDataPaket, 25, 145, 925, 250);
    rdSemuaDataPaket.setSelected(true);
    groupActionDataPaket.add(rdSemuaDataPaket);
    groupActionDataPaket.add(rdAktifDataPaket);
    groupActionDataPaket.add(rdTidakAktifDataPaket);

    btnTambahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsTambahPaket();
      }
    });

    btnUbahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsUbahPaket();
      }
    });

    content.add(labelDataPaket);
    content.add(labelCariDataPaket);
    content.add(txtCariDataPaket);
    content.add(btnTambahDataPaket);
    content.add(rdSemuaDataPaket);
    content.add(rdAktifDataPaket);
    content.add(rdTidakAktifDataPaket);
    content.add(spDataDataPaket);
    content.add(btnUbahDataPaket);
    setVisible(true);
  }

  private void initsTambahPaket() {
    // setVisible(false);
    idSelected = null;
    resetSidebar();
    menuDataPaket.setBackground(cColor.GREEN);
    menuDataPaket.setForeground(cColor.WHITE);
    refreshContent();
    menuDataPaket.setSidebarAktif();
    menuTitle.setText("Tambah Data Kamar");
    btnBatalTambahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataPaket();
      }
    });
    content.add(labelTambahDataPaket);
    content.add(labelNamaPaketTambahDataPaket);
    content.add(txtNamaPaketTambahDataPaket);
    content.add(errorNamaPaketTambahDataPaket);
    content.add(labelKuotaPaketTambahDataPaket);
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
    menuDataPaket.setBackground(cColor.GREEN);
    menuDataPaket.setForeground(cColor.WHITE);
    refreshContent();
    menuDataPaket.setSidebarAktif();
    menuTitle.setText("Ubah Data Kamar");
    btnBatalUbahDataPaket.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        initsDataPaket();
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
