package com.views;

import com.desain.*;
import com.program.Koneksi;
import com.template.TemplateHalamanUser;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

public class HalamanUser extends TemplateHalamanUser {

  private Integer id_user = null;
  private Integer idSelected = null;

  // sidebar menu
  private cSidebarMenu menuBeranda = new cSidebarMenu("Beranda", 70);
  private cSidebarMenu menuReservasi = new cSidebarMenu("Reservasi", 70 + 50);
  private cSidebarMenu menuHistory = new cSidebarMenu("History", 70 + 50 + 50);
  private cSidebarMenu menuAkun = new cSidebarMenu("Akun", 70 + 50 + 50 + 50);
  private cSidebarMenu menuLogout = new cSidebarMenu("Logout", 70 + 50 + 50 + 50 + 50);

  // components of beranda
  private cLabelUser labelSisaPulsaBeranda = new cLabelUser("Total Kamar yang Anda Reservasi", 25, 20);
  private cBigFontUser valueSisaPulsaBeranda = new cBigFontUser("2", 25, 60);

  // beli paket components
  private cLabelUser labelPilihanKamar = new cLabelUser("Silahkan Pilihan Kamar", 25, 20);
  private DefaultTableModel dmPaket;
  private cTable dataKamar;
  private cScrollPane spDataKamarAktif;
  private cBlueButton btnReservasi = new cBlueButton("Sewa Kamar", 25, 290, 155);

  // history beli Paket components
  private cLabelUser labelHistoryPaket = new cLabelUser("Data Transaksi Anda", 25, 20);
  private DefaultTableModel dmHistoryPaket;
  private cTable tblDataHistoryPaket;
  private cScrollPane spDataHistoryPaket;

  // akun user components
  private cLabelUser labelAkun = new cLabelUser("Data Akun Saya", 25, 20);
  private cFormLabel labelNama = new cFormLabel("Nama", 25, 65, 360, false);
  private cTextField txtNama = new cTextField(25, 90, 360, false);

  private cFormLabel labelNoHp = new cFormLabel("No Hp", 25, 130, 360, false);
  private cTextField txtNoHp = new cTextField(25, 155, 360, false);

  private cFormLabel labelEmail = new cFormLabel("Email", 25, 190, 360, false);
  private cTextField txtEmail = new cTextField(25, 215, 360, false);

  private cFormLabel labelPassword = new cFormLabel("Password", 25, 255, 360, false);
  private cPasswordField txtPassword = new cPasswordField(25, 280, 360, false);

  private cFormLabel labelAlamat = new cFormLabel("Alamat", 25, 320, 360, false);
  private cTextField txtAlamat = new cTextField(25, 345, 360, false);

  private cBlueButton btnUbahAkun = new cBlueButton("Ubah Data Akun", 25, 400, 155);

  // method resetSidebar
  private void resetSidebar() {
    try {
      setVisible(false);

      menuBeranda.setForeground(cColor.GRAY);
      menuBeranda.setBackground(cColor.WHITE);
      menuBeranda.setSidebarNonAktif();

      menuReservasi.setSidebarNonAktif();
      menuReservasi.setForeground(cColor.GRAY);
      menuReservasi.setBackground(cColor.WHITE);

      menuHistory.setSidebarNonAktif();
      menuHistory.setForeground(cColor.GRAY);
      menuHistory.setBackground(cColor.WHITE);

      menuAkun.setSidebarNonAktif();
      menuAkun.setForeground(cColor.GRAY);
      menuAkun.setBackground(cColor.WHITE);

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

  public HalamanUser(Integer id) {
    super("Dashboard User");
    this.id_user = id;
    roleText.setText("Selamat Datang, " + Koneksi.getDetailUser(id_user)[3].toString());
    menuBeranda.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsBeranda();
      }
    });

    menuReservasi.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsReservasi();
      }
    });

    menuHistory.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsHistoryBeliPaket();
      }
    });
    menuAkun.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsAkun(Koneksi.getDetailUser(id_user)[0].toString());
      }
    });
    menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Object[] options = { "YA", "BATAL" };
        int confirm = JOptionPane.showOptionDialog(null, "Yakin ingin logout?", "Logout",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);
        if (confirm == 0) {
          id_user = null;
          idSelected = null;
          com.program.Controller.showLoginUser();
        }
      }
    });
    // add component default
    sidebar.add(menuBeranda);
    sidebar.add(menuReservasi);
    sidebar.add(menuHistory);
    sidebar.add(menuAkun);
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
    content.add(labelSisaPulsaBeranda);
    content.add(valueSisaPulsaBeranda);
    setVisible(true);
  }

  private void initsReservasi() {
    idSelected = null;
    resetSidebar();
    menuReservasi.setBackground(cColor.GREEN);
    menuReservasi.setForeground(cColor.WHITE);
    refreshContent();
    menuReservasi.setSidebarAktif();
    dataKamar = new cTable(Koneksi.getAllKamarAktif());
    dataKamar.getColumnModel().getColumn(0).setMinWidth(0);
    dataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
    dataKamar.getColumnModel().getColumn(0).setWidth(0);

    dataKamar.getColumnModel().getColumn(1).setMinWidth(70);
    dataKamar.getColumnModel().getColumn(1).setMaxWidth(70);
    dataKamar.getColumnModel().getColumn(1).setWidth(70);
    dataKamar.getColumnModel().getColumn(3).setMinWidth(80);
    dataKamar.getColumnModel().getColumn(3).setMaxWidth(80);
    dataKamar.getColumnModel().getColumn(3).setWidth(80);

    dataKamar.getColumnModel().getColumn(4).setMinWidth(0);
    dataKamar.getColumnModel().getColumn(4).setMaxWidth(0);
    dataKamar.getColumnModel().getColumn(4).setWidth(0);

    spDataKamarAktif = new cScrollPane(dataKamar, 25, 70, 350, 190);

    content.add(labelPilihanKamar);
    content.add(spDataKamarAktif);
    content.add(btnReservasi);
    setVisible(true);
  }

  private void initsHistoryBeliPaket() {
    idSelected = null;
    resetSidebar();
    menuHistory.setBackground(cColor.GREEN);
    menuHistory.setForeground(cColor.WHITE);
    refreshContent();
    menuHistory.setSidebarAktif();
    String[] dataHistoryPaketHeader = { "Paket", "Kuota", "Harga", "Waktu", "Status" };
    String[][] dataHistoryPaket = {
        { "Row1 Col1", "Row1 Col2", "Row1 Col3", "Row1 Col4", "Row1 Col5" },
        { "Hemat Mantap", "11GB", "76.000", "2021-08-19 15:21:20", "selesai" },
        { "Row3 Col1", "Row3 Col2", "Row3 Col3", "Row3 Col4", "Row3 Col5" },
        { "Row4 Col1", "Row4 Col2", "Row4 Col3", "Row4 Col4", "Row4 Col5" },
        { "Row5 Col1", "Row5 Col2", "Row5 Col3", "Row5 Col4", "Row5 Col5" },
        { "Row6 Col1", "Row6 Col2", "Row6 Col3", "Row6 Col4", "Row6 Col5" },
        { "Row7 Col1", "Row7 Col2", "Row7 Col3", "Row7 Col4", "Row7 Col5" },
        { "Row8 Col1", "Row8 Col2", "Row8 Col3", "Row8 Col4", "Row8 Col5" },
        { "Row9 Col1", "Row9 Col2", "Row9 Col3", "Row9 Col4", "Row9 Col5" },
        { "Row10 Col1", "Row10 Col2", "Row10 Col3", "Row10 Col4", "Row10 Col5" }
    };
    dmHistoryPaket = new DefaultTableModel(dataHistoryPaket, dataHistoryPaketHeader);
    tblDataHistoryPaket = new cTable(dmHistoryPaket);
    spDataHistoryPaket = new cScrollPane(tblDataHistoryPaket, 25, 65, 350, 310);
    content.add(labelHistoryPaket);
    content.add(spDataHistoryPaket);
    setVisible(true);
  }

  private void initsAkun(String id) {
    idSelected = null;
    this.id_user = Integer.valueOf(id);
    resetSidebar();
    menuAkun.setBackground(cColor.GREEN);
    menuAkun.setForeground(cColor.WHITE);
    refreshContent();
    menuAkun.setSidebarAktif();

    // ngambil data user sesuai id
    Object[] detailUser = Koneksi.getDetailUser(id_user);

    txtNama.setText(detailUser[3].toString());
    txtNoHp.setText(detailUser[5].toString());
    txtEmail.setText(detailUser[1].toString());
    txtPassword.setText(detailUser[2].toString());
    txtAlamat.setText(detailUser[4].toString());

    String password = String.valueOf(txtPassword.getPassword());
    btnUbahAkun.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {

        // pengeckan kalau fieldnya kosong
        if (txtNama.getText().trim().isEmpty()
            || txtAlamat.getText().trim().isEmpty()
            || txtEmail.getText().trim().isEmpty()
            || password.trim().isEmpty()
            || txtNoHp.getText().trim().isEmpty()) {

          JOptionPane.showMessageDialog(HalamanUser.this, "Tolong isi data, jangan sampai kosong!", "Gagal Login",
              JOptionPane.ERROR_MESSAGE);

        } else {
          // lakukan insert data
          int no_hp = Integer.valueOf(txtNoHp.getText());
          String nama = txtNama.getText();
          String alamat = txtAlamat.getText();
          String password = String.valueOf(txtPassword.getPassword());
          String email = txtEmail.getText();
          // panggil method ubahDataPaket
          if (Koneksi.ubahDataUser(id_user, email, password, nama, alamat, no_hp)) {
            // kalau berhasil
            JOptionPane.showMessageDialog(HalamanUser.this, "Berhasil ubah data user.", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);

            initsBeranda();
          } else {
            // kalau tidak berhasil
            JOptionPane.showMessageDialog(HalamanUser.this, "Gagal ubah data user.", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }

        }

      }
    });
    content.add(labelAkun);
    content.add(labelNama);
    content.add(txtNama);

    content.add(labelNoHp);
    content.add(txtNoHp);

    content.add(labelEmail);
    content.add(txtEmail);
    content.add(labelPassword);
    content.add(txtPassword);
    content.add(labelAlamat);
    content.add(txtAlamat);

    content.add(btnUbahAkun);
    setVisible(true);
  }

}
