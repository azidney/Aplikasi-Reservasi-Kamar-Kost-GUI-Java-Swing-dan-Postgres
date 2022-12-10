package com.views;

import javax.swing.JOptionPane;

import com.desain.*;
import com.program.Controller;
import com.program.Koneksi;
import com.template.TemplateLoginRegister;

public class LoginRegister extends TemplateLoginRegister {

  // components of Login Customer
  private cFormLabel labelLoginEmailUser = new cFormLabel("Email", 0, 84, 450, true);
  private cTextField txtLoginEmailUser = new cTextField(65, 114, 320, true);
  private cErrorLabel erorEmailLoginUser = new cErrorLabel("Email harus di isi!!", 0, 149, 450, true);
  private cFormLabel labelPasswordLoginUser = new cFormLabel("Password", 0, 183, 450, true);
  private cPasswordField txtPasswordLoginUser = new cPasswordField(65, 213, 320, true);
  private cErrorLabel erorPaswordLoginUser = new cErrorLabel("password harus di isi!", 0, 248, 450, true);
  private cBlueButton btnLoginLoginUser = new cBlueButton("Login", 65, 282, 320);
  private cLinkStart toDaftarUserDaftarUser = new cLinkStart("belum punya akun user?", 362);
  private cLinkStart toLoginAdminLoginCustomer = new cLinkStart("Login Sebagai Admin", 382);

  // components of Register Customer
  private cFormLabel labelNamaDaftarCustomer = new cFormLabel("Nama", 0, 84, 450, true);
  private cTextField txtNamaDaftarCustomer = new cTextField(65, 114, 320, true);
  private cErrorLabel errorNamaDaftarCustomer = new cErrorLabel("nama kosong!!", 0, 149, 450, true);
  private cFormLabel labelNoHpDaftarCustomer = new cFormLabel("Nomor Hp", 0, 183, 450, true);
  private cTextField txtNoHpDaftarCustomer = new cTextField(65, 213, 320, true);
  private cErrorLabel errorNoHpDaftarCustomer = new cErrorLabel("no. hp kosong!!", 0, 248, 450, true);
  private cFormLabel labelPasswordDaftarCustomer = new cFormLabel("Password", 0, 282, 450, true);
  private cPasswordField txtPasswordDaftarCustomer = new cPasswordField(65, 312, 320, true);
  private cErrorLabel errorPasswordDaftarCustomer = new cErrorLabel("password kosong!!", 0, 347, 450, true);
  private cBlueButton btnDaftarDaftarCustomer = new cBlueButton("Daftar", 65, 381, 320);
  private cLinkStart toLoginCustomerDaftarCustomer = new cLinkStart("sudah punya akun customer?", 461);
  private cLinkStart toLoginAdminDaftarCustomer = new cLinkStart("Login Sebagai Admin", 481);

  // components of Login Admin
  private cFormLabel labelUsernameLoginAdmin = new cFormLabel("Username", 0, 84, 450, true);
  private cTextField txtUsernameLoginAdmin = new cTextField(65, 114, 320, true);
  private cErrorLabel errorUsernameLoginAdmin = new cErrorLabel("username kosong!!", 0, 149, 450, true);
  private cFormLabel labelPasswordLoginAdmin = new cFormLabel("Password", 0, 183, 450, true);
  private cPasswordField txtPasswordLoginAdmin = new cPasswordField(65, 213, 320, true);
  private cErrorLabel errorPasswordLoginAdmin = new cErrorLabel("password kosong!!", 0, 248, 450, true);
  private cBlueButton btnLoginLoginAdmin = new cBlueButton("Login", 65, 282, 320);
  private cLinkStart toLoginCustomerLoginAdmin = new cLinkStart("Login Sebagai User", 382);

  public LoginRegister() {
    super();
    // implement for link frame loginCustomer
    toDaftarUserDaftarUser.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Controller.showDaftarCustomer();
      }
    });
    toLoginAdminLoginCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Controller.showLoginAdmin();
      }
    });

    // implement for link frame daftarCustomer

    toLoginCustomerDaftarCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Controller.showLoginCustomer();
      }
    });
    toLoginAdminDaftarCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Controller.showLoginAdmin();
      }
    });

    toLoginCustomerLoginAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent me) {
        Controller.showLoginCustomer();
      }
    });

  }

  public void initsLoginUser() {
    this.setTitle("Login User");
    cardStart.removeAll();
    titleStart.setText("Login User");
    cardStart.add(titleStart);

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
    cardStart.add(toLoginAdminLoginCustomer);
  }

  public void initsDaftarCustomer() {
    this.setTitle("Daftar User");
    cardStart.removeAll();
    titleStart.setText("Daftar User");
    cardStart.add(titleStart);

    btnDaftarDaftarCustomer.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        if (txtNamaDaftarCustomer.getText().equalsIgnoreCase("") || txtNoHpDaftarCustomer.getText().equalsIgnoreCase("")
            || String.valueOf(txtPasswordDaftarCustomer.getPassword()).equalsIgnoreCase("")) {

          Controller.showDaftarCustomer();
          if (txtNamaDaftarCustomer.getText().equalsIgnoreCase("")) {
            cardStart.add(errorNamaDaftarCustomer);
          }
          if (txtNoHpDaftarCustomer.getText().equalsIgnoreCase("")) {
            cardStart.add(errorNoHpDaftarCustomer);
          }
          if (String.valueOf(txtPasswordDaftarCustomer.getPassword()).equalsIgnoreCase("")) {
            cardStart.add(errorPasswordDaftarCustomer);
          }
        }
      }
    });

    cardStart.add(labelNamaDaftarCustomer);
    cardStart.add(txtNamaDaftarCustomer);
    cardStart.add(labelNoHpDaftarCustomer);
    cardStart.add(txtNoHpDaftarCustomer);
    cardStart.add(labelPasswordDaftarCustomer);
    cardStart.add(txtPasswordDaftarCustomer);
    cardStart.add(btnDaftarDaftarCustomer);
    cardStart.add(toLoginCustomerDaftarCustomer);
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
    cardStart.add(toLoginCustomerLoginAdmin);

  }

}
