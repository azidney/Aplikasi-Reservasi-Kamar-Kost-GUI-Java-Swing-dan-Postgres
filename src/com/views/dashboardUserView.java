package com.views;

import com.templates.HalamanUser;
import com.partials.*;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dashboardUserView extends HalamanUser {

  private Integer idUser = null;
  private Integer idSelected = null;

  // sidebar menu
  private cSidebarMenu menuBeranda = new cSidebarMenu("Beranda", 70);
  private cSidebarMenu menuBeliPaket = new cSidebarMenu("Reservasi", 70 + 50);
  private cSidebarMenu menuHistoryBeliPaket = new cSidebarMenu("History", 70 + 50 + 50);
  private cSidebarMenu menuAkun = new cSidebarMenu("Akun", 70 + 50 + 50 + 50);
  private cSidebarMenu menuLogout = new cSidebarMenu("Logout", 70 + 50 + 50 + 50 + 50);

  // components of beranda
  private cLabelUser labelSisaPulsaBeranda = new cLabelUser("Total Kamar yang Anda Reservasi", 25, 20);
  private cBigFontUser valueSisaPulsaBeranda = new cBigFontUser("2", 25, 60);

  // beli paket components
  private cLabelUser labelPilihanBeliPaket = new cLabelUser("Silahkan Pilihan Kamar", 25, 20);
  private DefaultTableModel dmPaket;
  private cTable dataPaket;
  private cScrollPane spDataPaket;
  private cBlueButton btnBeliPaket = new cBlueButton("Sewa Kamar", 25, 290, 155);

  // history beli Paket components
  private cLabelUser labelHistoryPaket = new cLabelUser("Data Transaksi Anda", 25, 20);
  private DefaultTableModel dmHistoryPaket;
  private cTable tblDataHistoryPaket;
  private cScrollPane spDataHistoryPaket;

  // akun user components
  private cLabelUser labelAkun = new cLabelUser("Data Akun Saya", 25, 20);
  private cFormLabel labelNama = new cFormLabel("Nama", 25, 65, 360, false);
  private cTextField txtNama = new cTextField(25, 90, 360, false);
  private cErrorLabel errorNama = new cErrorLabel("nama tidak boleh kosong!", 25, 125, 360, false);
  private cFormLabel labelNoHp = new cFormLabel("No Hp", 25, 150, 360, false);
  private cFormLabel valueNoHp;
  private cFormLabel labelEmail = new cFormLabel("Email", 25, 202, 360, false);
  private cTextField txtEmail = new cTextField(25, 227, 360, false);
  private cErrorLabel errorEmail = new cErrorLabel("email tidak boleh kosong!", 25, 262, 360, false);
  private cBlueButton btnUbahAkun = new cBlueButton("Ubah Data Akun", 25, 292, 155);

  // method resetSidebar
  private void resetSidebar() {
    try {
      setVisible(false);

      menuBeranda.setForeground(cColor.GRAY);
      menuBeranda.setBackground(cColor.WHITE);
      menuBeranda.setSidebarNonAktif();

      menuBeliPaket.setSidebarNonAktif();
      menuBeliPaket.setForeground(cColor.GRAY);
      menuBeliPaket.setBackground(cColor.WHITE);

      menuHistoryBeliPaket.setSidebarNonAktif();
      menuHistoryBeliPaket.setForeground(cColor.GRAY);
      menuHistoryBeliPaket.setBackground(cColor.WHITE);

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

  public dashboardUserView(Integer id) {
    super("Dashboard User");
    idUser = id;
    roleText.setText("Selamat Datang, User!");
    menuBeranda.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsBeranda();
      }
    });

    menuBeliPaket.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsBeliPaket();
      }
    });

    menuHistoryBeliPaket.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsHistoryBeliPaket();
      }
    });
    menuAkun.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        initsAkun();
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
          idUser = null;
          idSelected = null;
          com.program.Controller.showLoginCustomer();
        }
      }
    });
    // add component default
    sidebar.add(menuBeranda);
    sidebar.add(menuBeliPaket);
    sidebar.add(menuHistoryBeliPaket);
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

  private void initsBeliPaket() {
    idSelected = null;
    resetSidebar();
    menuBeliPaket.setBackground(cColor.GREEN);
    menuBeliPaket.setForeground(cColor.WHITE);
    refreshContent();
    menuBeliPaket.setSidebarAktif();
    String[] dataUserHeader = { "Nama", "Kuota", "Harga" };
    String[][] dataUser = {
        { "Row1 Col1", "Row1 Col2", "Row1 Col3" },
        { "Row2 Col1", "Row2 Col2", "Row2 Col3" },
        { "Hemat Mantap", "11GB", "76.000" },
        { "Row4 Col1", "Row4 Col2", "Row4 Col3" },
        { "Row5 Col1", "Row5 Col2", "Row5 Col3" },
        { "Row6 Col1", "Row6 Col2", "Row6 Col3" }
    };
    dmPaket = new DefaultTableModel(dataUser, dataUserHeader);
    dataPaket = new cTable(dmPaket);
    spDataPaket = new cScrollPane(dataPaket, 25, 70, 350, 190);

    content.add(labelPilihanBeliPaket);
    content.add(spDataPaket);
    content.add(btnBeliPaket);
    setVisible(true);
  }

  private void initsHistoryBeliPaket() {
    idSelected = null;
    resetSidebar();
    menuHistoryBeliPaket.setBackground(cColor.GREEN);
    menuHistoryBeliPaket.setForeground(cColor.WHITE);
    refreshContent();
    menuHistoryBeliPaket.setSidebarAktif();
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

  private void initsAkun() {
    idSelected = null;
    resetSidebar();
    menuAkun.setBackground(cColor.GREEN);
    menuAkun.setForeground(cColor.WHITE);
    refreshContent();
    menuAkun.setSidebarAktif();
    valueNoHp = new cFormLabel("08123xxx", 25, 174, 360, false);
    valueNoHp.setFont(com.partials.cFonts.RADIO_BUTTON_FONT);
    valueNoHp.setForeground(com.partials.cColor.RED);
    content.add(labelAkun);
    content.add(labelNama);
    content.add(txtNama);
    content.add(errorNama);
    content.add(labelNoHp);
    content.add(valueNoHp);
    content.add(labelEmail);
    content.add(txtEmail);
    content.add(errorEmail);
    content.add(btnUbahAkun);
    setVisible(true);
  }

}
