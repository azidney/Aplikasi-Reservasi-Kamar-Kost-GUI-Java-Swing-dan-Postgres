package com.program;

import com.views.LoginRegister;

public class App {

  public static void main(String[] args) {

    // Controller.showDashboardAdmin(true);
    // Controller.showDashboardUser(1);
    // Controller.showLoginAdmin();
    // Model.connection(); // kalo di publi

    LoginRegister auth = new LoginRegister();
    auth.initsLoginUser();
    auth.setVisible(true);

  }

}
