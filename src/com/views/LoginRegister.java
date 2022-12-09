package com.views;

import com.desain.*;
import com.program.Controller;
import com.template.TemplateLoginRegister;

public class LoginRegister extends TemplateLoginRegister {

  // components of Login Customer
  private cFormLabel labelNoHpLoginCustomer = new cFormLabel("Nomor Hp", 0, 84, 450, true);
  private cTextField txtNoHpLoginCustomer = new cTextField(65, 114, 320, true);
  private cErrorLabel errorNoHpLoginCustomer = new cErrorLabel("no. hp kosong!!", 0, 149, 450, true);
  private cFormLabel labelPasswordLoginCustomer = new cFormLabel("Password", 0, 183, 450, true);
  private cPasswordField txtPasswordLoginCustomer = new cPasswordField(65, 213, 320, true);
  private cErrorLabel errorPasswordLoginCustomer = new cErrorLabel("password kosong!!", 0, 248, 450, true);
  private cBlueButton btnLoginLoginCustomer = new cBlueButton("Login", 65, 282, 320);
  private cLinkStart toDaftarCustomerLoginCustomer = new cLinkStart("belum punya akun customer?", 362);
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
  private cLinkStart toLoginCustomerLoginAdmin = new cLinkStart("Login Sebagai Customer", 382);

  public LoginRegister() {
    super();
    // implement for link frame loginCustomer
    toDaftarCustomerLoginCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
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

  public void initsLoginCustomer() {
    this.setTitle("Login Customer");
    cardStart.removeAll();
    titleStart.setText("Login Customer");
    cardStart.add(titleStart);

    btnLoginLoginCustomer.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        if (txtNoHpLoginCustomer.getText().equalsIgnoreCase("")
            || String.valueOf(txtPasswordLoginCustomer.getPassword()).equalsIgnoreCase("")) {
          Controller.showLoginCustomer();
          if (txtNoHpLoginCustomer.getText().equalsIgnoreCase("")) {
            cardStart.add(errorNoHpLoginCustomer);
          }
          if (String.valueOf(txtPasswordLoginCustomer.getPassword()).equalsIgnoreCase("")) {
            cardStart.add(errorPasswordLoginCustomer);
          }
        }
      }
    });

    cardStart.add(labelNoHpLoginCustomer);
    cardStart.add(txtNoHpLoginCustomer);
    cardStart.add(labelPasswordLoginCustomer);
    cardStart.add(txtPasswordLoginCustomer);
    cardStart.add(btnLoginLoginCustomer);
    cardStart.add(toDaftarCustomerLoginCustomer);
    cardStart.add(toLoginAdminLoginCustomer);
  }

  public void initsDaftarCustomer() {
    this.setTitle("Daftar Customer");
    cardStart.removeAll();
    titleStart.setText("Daftar Customer");
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
        if (txtUsernameLoginAdmin.getText().equalsIgnoreCase("")
            || String.valueOf(txtPasswordLoginAdmin.getPassword()).equalsIgnoreCase("")) {
          Controller.showLoginAdmin();
          if (txtUsernameLoginAdmin.getText().equalsIgnoreCase("")) {
            cardStart.add(errorUsernameLoginAdmin);
          }
          if (String.valueOf(txtPasswordLoginAdmin.getPassword()).equalsIgnoreCase("")) {
            cardStart.add(errorPasswordLoginAdmin);
          }
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
