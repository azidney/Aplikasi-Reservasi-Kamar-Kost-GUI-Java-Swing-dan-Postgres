package com.views;

import javax.swing.JOptionPane;

import com.desain.*;
import com.program.Controller;
import com.program.Koneksi;
import com.template.TemplateLoginRegister;

public class LoginRegister extends TemplateLoginRegister {

  // components of Login User
  private cFormLabel labelLoginEmailUser = new cFormLabel("Email", 0, 84, 450, true);
  private cTextField txtLoginEmailUser = new cTextField(65, 114, 320, true);
  private cErrorLabel erorEmailLoginUser = new cErrorLabel("Email harus di isi!!", 0, 149, 450, true);
  private cFormLabel labelPasswordLoginUser = new cFormLabel("Password", 0, 183, 450, true);
  private cPasswordField txtPasswordLoginUser = new cPasswordField(65, 213, 320, true);
  private cErrorLabel erorPaswordLoginUser = new cErrorLabel("password harus di isi!", 0, 248, 450, true);
  private cBlueButton btnLoginLoginUser = new cBlueButton("Login", 65, 282, 320);
  private cLinkStart toDaftarUserDaftarUser = new cLinkStart("Belum Punya Akun?", 362);
  private cLinkStart toLoginAdminLoginUser = new cLinkStart("Login Sebagai Admin", 382);

  // components of Register User
  private cFormLabel labelNamaDaftarUser = new cFormLabel("Nama", 0, 84, 450, true);
  private cTextField txtNamadaftarUser = new cTextField(65, 114, 320, true);
  private cErrorLabel erorNamaDaftarUser = new cErrorLabel("nama kosong!!", 0, 145, 450, true);
  private cFormLabel labelNoHpDatarUser = new cFormLabel("Nomor Hp", 0, 155, 450, true);
  private cTextField txtNoHpDaftarUser = new cTextField(65, 185, 320, true);
  private cErrorLabel erorNoHpDaftarUser = new cErrorLabel("no. hp kosong!!", 0, 220, 450, true);
  private cFormLabel labelPasworDaftarUser = new cFormLabel("Password", 0, 225, 450, true);
  private cPasswordField txtPasswordDaftarUser = new cPasswordField(65, 255, 320, true);
  private cErrorLabel erorPassworddaftarUser = new cErrorLabel("password kosong!!", 0, 285, 450, true);
  private cFormLabel labelDaftarAlamatUser = new cFormLabel("Alamat", 0, 295, 450, true);
  private cTextField txtAlamatDaftarUser = new cTextField(65, 325, 320, true);
  private cErrorLabel erorAlamatDaftarUser = new cErrorLabel("Alamat kosong!!", 0, 355, 450, true);
  private cFormLabel labelEmailDaftarUser = new cFormLabel("Email", 0, 367, 450, true);
  private cTextField txtEmailDaftarUser = new cTextField(65, 397, 320, true);
  private cErrorLabel erorEmailDaftarUser = new cErrorLabel("Email kosong!!", 0, 432, 450, true);

  private cBlueButton btnDaftarDaftarUser = new cBlueButton("Daftar", 65, 451, 320);
  private cLinkStart toLoginUserLoginUser = new cLinkStart("Sudah Punya Akun?", 491);

  // components of Login Admin
  private cFormLabel labelUsernameLoginAdmin = new cFormLabel("Username", 0, 84, 450, true);
  private cTextField txtUsernameLoginAdmin = new cTextField(65, 114, 320, true);
  private cErrorLabel errorUsernameLoginAdmin = new cErrorLabel("username kosong!!", 0, 149, 450, true);
  private cFormLabel labelPasswordLoginAdmin = new cFormLabel("Password", 0, 183, 450, true);
  private cPasswordField txtPasswordLoginAdmin = new cPasswordField(65, 213, 320, true);
  private cErrorLabel errorPasswordLoginAdmin = new cErrorLabel("password kosong!!", 0, 248, 450, true);
  private cBlueButton btnLoginLoginAdmin = new cBlueButton("Login", 65, 282, 320);
  private cLinkStart toLoginUserLoginAdmin = new cLinkStart("Login Sebagai User", 382);

  public LoginRegister() {

    super();
    // implement for link frame loginUser
    toDaftarUserDaftarUser.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {

        LoginRegister.this.setVisible(false);
        Controller.showDaftarUser();
      }
    });
    toLoginAdminLoginUser.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {

        LoginRegister.this.setVisible(false);
        Controller.showLoginAdmin();
      }
    });

    // implement for link frame daftarUser
    toLoginUserLoginUser.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {

        LoginRegister.this.setVisible(false);
        Controller.showLoginUser();
      }
    });

    toLoginUserLoginAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {

        LoginRegister.this.setVisible(false);
        Controller.showLoginUser();
      }
    });

  }

  public void initsLoginUser() {
    this.setTitle("Login User");
    cardStart.removeAll();
    titleStart.setText("Login User");
    cardStart.add(titleStart);
    txtLoginEmailUser.setText(null);
    txtPasswordLoginUser.setText(null);

    btnLoginLoginUser.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        String email = txtLoginEmailUser.getText();
        String password = String.valueOf(txtPasswordLoginUser.getPassword());

        // kalau salah satu datanya kosong
        if (email.equalsIgnoreCase("") || email.isEmpty() || password.equalsIgnoreCase("") || password.isEmpty()) {

          LoginRegister.this.setVisible(false);

          // masuk validasi
          if (email.equalsIgnoreCase("") || email.isEmpty()) {
            cardStart.add(erorEmailLoginUser);
          } else {
            cardStart.remove(erorEmailLoginUser);
          }

          if (password.equalsIgnoreCase("") || password.isEmpty())
            cardStart.add(erorPaswordLoginUser);
          else
            cardStart.remove(erorPaswordLoginUser);

          LoginRegister.this.setVisible(true);
        } else {
          // lolos validasi
          if (Koneksi.loginUser(email, password)) {
            // kalo berhasil login
            Controller.showDashboardUser(Integer.valueOf(Koneksi.getDetailEmailUser(email)[0].toString()));
            LoginRegister.this.setVisible(false);
          } else {
            // kalo gagal login
            JOptionPane.showMessageDialog(LoginRegister.this, "Silahkan periksa email dan password!!", "Gagal Login",
                JOptionPane.ERROR_MESSAGE);
            txtLoginEmailUser.setText(null);
            txtPasswordLoginUser.setText(null);
          }

        }

      }
    });

    cardStart.add(labelLoginEmailUser);
    cardStart.add(txtLoginEmailUser);
    cardStart.add(labelPasswordLoginUser);
    cardStart.add(txtPasswordLoginUser);
    cardStart.add(btnLoginLoginUser);
    cardStart.add(toDaftarUserDaftarUser);
    cardStart.add(toLoginAdminLoginUser);
  }

  public void initsDaftarUser() {
    this.setTitle("Daftar User");
    cardStart.removeAll();
    titleStart.setText("Daftar User");
    cardStart.add(titleStart);

    btnDaftarDaftarUser.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {

        String nama = txtNamadaftarUser.getText();
        String no_hp = txtNoHpDaftarUser.getText();
        String alamat = txtAlamatDaftarUser.getText();
        String email = txtEmailDaftarUser.getText();
        String password = String.valueOf(txtPasswordDaftarUser.getPassword());
        if (alamat.equalsIgnoreCase("") || alamat.isEmpty()
            || nama.equalsIgnoreCase("") || nama.isEmpty()
            || email.equalsIgnoreCase("") || email.isEmpty()
            || password.equalsIgnoreCase("") || password.isEmpty()) {

          LoginRegister.this.setVisible(false);

          // kalau alamatnya yang kosong
          if (alamat.equalsIgnoreCase("") || alamat.isEmpty())
            cardStart.add(erorAlamatDaftarUser);
          else
            cardStart.remove(erorAlamatDaftarUser);

          // kalau namanya yang kosong
          if (nama.equalsIgnoreCase("") || nama.isEmpty())
            cardStart.add(erorNamaDaftarUser);
          else
            cardStart.remove(erorNamaDaftarUser);

          // kalau Emailnya yang kosong
          if (email.equalsIgnoreCase("") || email.isEmpty())
            cardStart.add(erorEmailDaftarUser);
          else
            cardStart.remove(erorEmailDaftarUser);

          // kalau Passwordnya yang kosong
          if (password.equalsIgnoreCase("") || password.isEmpty())
            cardStart.add(erorPassworddaftarUser);
          else
            cardStart.remove(erorPassworddaftarUser);

          LoginRegister.this.setVisible(true);

        } else {
          // lolos validasi
          // cek apakah email sudah terdaftar atau belum
          if (Koneksi.verifyEmailUser(email)) {
            // berarti email belum terdaftar
            if (Koneksi.daftarUser(email, password, nama, alamat, no_hp)) {
              // kalau berhasil daftar
              JOptionPane.showMessageDialog(LoginRegister.this, "Daftar Berhasil!!", "Berhasil Daftar",
                  JOptionPane.INFORMATION_MESSAGE);

              Controller.showLoginUser();

            } else {
              // kalau gagal daftar
              JOptionPane.showMessageDialog(LoginRegister.this, "Pendafaran gagal!!", "Gagal Daftar",
                  JOptionPane.ERROR_MESSAGE);
            }

          } else {
            // berarti email sudah terdaftar
            JOptionPane.showMessageDialog(LoginRegister.this, "Silahkan cek kembali data anda!!", "Gagal Daftar",
                JOptionPane.ERROR_MESSAGE);
          }

        }

      }
    });

    cardStart.add(labelNamaDaftarUser);
    cardStart.add(txtNamadaftarUser);
    cardStart.add(labelNoHpDatarUser);
    cardStart.add(txtNoHpDaftarUser);
    cardStart.add(labelPasworDaftarUser);
    cardStart.add(txtPasswordDaftarUser);

    cardStart.add(labelDaftarAlamatUser);
    cardStart.add(txtAlamatDaftarUser);

    cardStart.add(labelEmailDaftarUser);
    cardStart.add(txtEmailDaftarUser);

    cardStart.add(btnDaftarDaftarUser);
    cardStart.add(toLoginUserLoginUser);
  }

  public void initsLoginAdmin() {

    this.setTitle("Login Admin");
    cardStart.removeAll();
    titleStart.setText("Login Admin");
    cardStart.add(titleStart);

    btnLoginLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {

        String username = txtUsernameLoginAdmin.getText();
        String password = String.valueOf(txtPasswordLoginAdmin.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
          // loginnya Berhasil
          Controller.showDashboardAdmin(true);
          LoginRegister.this.setVisible(false);
        } else {
          // loginnya gagal
          JOptionPane.showMessageDialog(LoginRegister.this, "Silahkan periksa username dan password!", "Gagal Login",
              JOptionPane.ERROR_MESSAGE);
        }

      }
    });

    cardStart.add(labelUsernameLoginAdmin);
    cardStart.add(txtUsernameLoginAdmin);
    cardStart.add(labelPasswordLoginAdmin);
    cardStart.add(txtPasswordLoginAdmin);
    cardStart.add(btnLoginLoginAdmin);
    cardStart.add(toLoginUserLoginAdmin);

  }

}
