package com.template;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.desain.cColor;
import com.desain.cFonts;
import com.desain.cLinkKeluar;

import javax.swing.JLabel;

public abstract class TemplateLoginRegister extends JFrame {

  public JPanel bg = new JPanel();
  public JPanel cardStart = new JPanel();
  public cLinkKeluar linkKeluar = new cLinkKeluar(1168);
  public JLabel titleStart = new JLabel("Title Start");

  public TemplateLoginRegister() {
    super();
    setSize(1280, 720);
    setLocationRelativeTo(null);
    setLayout(null);
    setUndecorated(true);
    setDefaultCloseOperation(TemplateLoginRegister.EXIT_ON_CLOSE);

    bg.setBackground(cColor.GREEN);
    bg.setBounds(0, 0, 1280, 720);
    bg.setLayout(null);

    cardStart.setBackground(cColor.WHITE90);
    cardStart.setBounds(415, 90, 450, 540);
    cardStart.setLayout(null);

    titleStart.setFont(cFonts.TITLE_START_FONT);
    titleStart.setForeground(cColor.BLACK);
    titleStart.setBounds(0, 30, 450, 40);
    titleStart.setHorizontalAlignment(JLabel.CENTER);
    titleStart.setVerticalAlignment(JLabel.CENTER);

    cardStart.add(titleStart);
    bg.add(linkKeluar);
    bg.add(cardStart);
    add(bg);

  }

  public TemplateLoginRegister(String title) {
    this();
    setTitle(title);
  }

}
