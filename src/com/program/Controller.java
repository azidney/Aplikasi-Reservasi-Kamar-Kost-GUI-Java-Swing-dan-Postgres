package com.program;

import com.views.*;

public class Controller {

  private static startView frameLoginAndRegister = new startView();

  public static void showLoginCustomer() {
    frameLoginAndRegister.setVisible(false);
    frameLoginAndRegister.initsLoginCustomer();
    frameLoginAndRegister.setVisible(true);
  }

  public static void showDaftarCustomer() {
    frameLoginAndRegister.setVisible(false);
    frameLoginAndRegister.initsDaftarCustomer();
    frameLoginAndRegister.setVisible(true);
  }

  public static void showLoginAdmin() {
    frameLoginAndRegister.setVisible(false);
    frameLoginAndRegister.initsLoginAdmin();
    frameLoginAndRegister.setVisible(true);
  }

  public static void showDashboardUser(Integer id) {
    dashboardUserView dashboardCustomer = new dashboardUserView(id);
    dashboardCustomer.setVisible(true);
  }

  public static void showDashboardAdmin(boolean statusLogin) {
    dashboardAdminView dashboardAdmin = new dashboardAdminView(statusLogin);
    dashboardAdmin.setVisible(true);
  }

}
