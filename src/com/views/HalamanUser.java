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
  private cLabelUser labelJumlahReservasi = new cLabelUser("Total Kamar yang Anda Reservasi", 25, 20);
  private cBigFontUser valueJumlahReservasi = new cBigFontUser("0", 25, 60);

  // sewa kost components
  private cLabelUser labelPilihanKamar = new cLabelUser("Silahkan Pilihan Kost", 25, 20);
  private DefaultTableModel dmPaket;
  private cTable dataKamar;
  private cScrollPane spDataKamarAktif;
  private cBlueButton btnReservasi = new cBlueButton("Sewa Kost", 25, 290, 155);

  // history reservasi
  private cLabelUser labelHistoryPaket = new cLabelUser("Data Reservasi Anda", 25, 20);
  private cTable dataHistory;
  private cScrollPane spDataHistory;
  private cBlueButton btnHapusHistory = new cBlueButton("Check Out", 25, 406, 110);

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
        initsBeranda(Koneksi.getDetailUser(id_user)[0].toString());
      }
    });

    menuReservasi.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsReservasi(Koneksi.getDetailUser(id_user)[0].toString());
      }
    });

    menuHistory.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsHistoryReservasi(Koneksi.getDetailUser(id_user)[0].toString());
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
          HalamanUser.this.setVisible(false);
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

    initsBeranda(Koneksi.getDetailUser(id_user)[0].toString());
  }

  private void initsBeranda(String id) {
    idSelected = null;

    this.id_user = Integer.valueOf(id);
    resetSidebar();
    menuBeranda.setBackground(cColor.GREEN);
    menuBeranda.setForeground(cColor.WHITE);
    refreshContent();
    menuBeranda.setSidebarAktif();

    valueJumlahReservasi.setText(String.valueOf(Koneksi.getCountAllReservasiUser(id_user)));
    content.add(labelJumlahReservasi);
    content.add(valueJumlahReservasi);
    setVisible(true);
  }

  private void initsReservasi(String id) {
    idSelected = null;

    this.id_user = Integer.valueOf(id);
    resetSidebar();
    menuReservasi.setBackground(cColor.GREEN);
    menuReservasi.setForeground(cColor.WHITE);
    refreshContent();
    menuReservasi.setSidebarAktif();
    dataKamar = new cTable(Koneksi.getAllKamarAktif());
    dataKamar.getColumnModel().getColumn(0).setMinWidth(0);
    dataKamar.getColumnModel().getColumn(0).setMaxWidth(0);
    dataKamar.getColumnModel().getColumn(0).setWidth(0);

    dataKamar.getColumnModel().getColumn(1).setMinWidth(55);
    dataKamar.getColumnModel().getColumn(1).setMaxWidth(55);
    dataKamar.getColumnModel().getColumn(1).setWidth(55);
    dataKamar.getColumnModel().getColumn(3).setMinWidth(60);
    dataKamar.getColumnModel().getColumn(3).setMaxWidth(60);
    dataKamar.getColumnModel().getColumn(3).setWidth(60);

    dataKamar.getColumnModel().getColumn(5).setMinWidth(60);
    dataKamar.getColumnModel().getColumn(5).setMaxWidth(60);
    dataKamar.getColumnModel().getColumn(5).setWidth(60);

    dataKamar.getColumnModel().getColumn(4).setMinWidth(0);
    dataKamar.getColumnModel().getColumn(4).setMaxWidth(0);
    dataKamar.getColumnModel().getColumn(4).setWidth(0);

    spDataKamarAktif = new cScrollPane(dataKamar, 25, 70, 350, 190);
    btnReservasi.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        int selectedIndex = dataKamar.getSelectedRow();

        if (selectedIndex != -1) {
          // kalo ada yang dipilih
          int id_kamar = Integer.valueOf(dataKamar.getValueAt(selectedIndex, 0).toString());

          String waktu = dataKamar.getValueAt(selectedIndex, 5).toString();

          if (Koneksi.tambahReservasi(id_user, id_kamar, waktu)) {
            JOptionPane.showMessageDialog(HalamanUser.this, "Anda berhasil Reservasi!", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
            initsReservasi(Koneksi.getDetailUser(id_user)[0].toString());
          } else {
            JOptionPane.showMessageDialog(HalamanUser.this, "Anda Gagal Reservasi!", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          // kalo gak ada yang diseleksi
          JOptionPane.showMessageDialog(HalamanUser.this, "Pilih data terlebih dahulu!", "Peringatan",
              JOptionPane.WARNING_MESSAGE);
        }

      }
    });
    content.add(labelPilihanKamar);
    content.add(spDataKamarAktif);
    content.add(btnReservasi);
    setVisible(true);
  }

  private void initsHistoryReservasi(String id) {
    idSelected = null;
    this.id_user = Integer.valueOf(id);
    resetSidebar();
    menuHistory.setBackground(cColor.GREEN);
    menuHistory.setForeground(cColor.WHITE);
    refreshContent();
    menuHistory.setSidebarAktif();

    dataHistory = new cTable(Koneksi.getReservasiUser(id_user));
    dataHistory.getColumnModel().getColumn(0).setMinWidth(0);
    dataHistory.getColumnModel().getColumn(0).setMaxWidth(0);
    dataHistory.getColumnModel().getColumn(0).setWidth(0);

    spDataHistory = new cScrollPane(dataHistory, 25, 65, 350, 310);

    btnHapusHistory.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        int selectedIndex = dataHistory.getSelectedRow();

        if (selectedIndex != -1) {
          // kalo ada yang dipilih
          int id_reservasi = Integer.valueOf(dataHistory.getValueAt(selectedIndex, 0).toString());

          if (Koneksi.hapusReservasi(id_reservasi)) {
            JOptionPane.showMessageDialog(HalamanUser.this, "Data History Reservasi berhasil di Check Out!", "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
            initsHistoryReservasi(Koneksi.getDetailUser(id_user)[0].toString());
          } else {
            JOptionPane.showMessageDialog(HalamanUser.this, "Data History Reservasi gagal di Check Out!", "Gagal",
                JOptionPane.ERROR_MESSAGE);
          }
        } else {
          // kalo gak ada yang diseleksi
          JOptionPane.showMessageDialog(HalamanUser.this, "Pilih data terlebih dahulu!", "Peringatan",
              JOptionPane.WARNING_MESSAGE);
        }

      }
    });
    content.add(labelHistoryPaket);
    content.add(spDataHistory);
    content.add(btnHapusHistory);
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

            initsBeranda(Koneksi.getDetailUser(id_user)[0].toString());
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
