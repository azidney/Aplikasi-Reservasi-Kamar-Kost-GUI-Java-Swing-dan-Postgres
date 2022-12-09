package com.template;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.desain.cColor;
import com.desain.cFonts;

import javax.swing.JLabel;

public abstract class TemplateHalamanUser extends JFrame {

    public JPanel sidebar = new JPanel();
    public JPanel header = new JPanel();
    public JPanel main = new JPanel();
    public JPanel content = new JPanel();

    private JLabel appText = new JLabel("Kosanku");
    public JLabel roleText = new JLabel("Selamat Datang, Role!");
    public JLabel menuTitle = new JLabel("Menu Title");
    public JLabel copyrightText = new JLabel("Copyright 2022. Muhamad Yazid Imani.");

    public TemplateHalamanUser() {
        super();
        setSize(550, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(null);

        sidebar.setBackground(cColor.WHITE);
        sidebar.setBounds(0, 0, 130, 620);
        sidebar.setLayout(null);

        header.setBackground(cColor.GREEN);
        header.setBounds(130, 0, 420, 70);
        header.setLayout(null);

        main.setBackground(cColor.WHITE_GRAY);
        main.setBounds(130, 70, 420, 520);
        main.setLayout(null);

        content.setBackground(cColor.WHITE);
        content.setBounds(10, 10, 400, 520);
        content.setLayout(null);

        appText.setFont(cFonts.JUDUL_FONT);
        appText.setBounds(0, 0, 125, 70);
        appText.setHorizontalAlignment(JLabel.CENTER);
        appText.setVerticalAlignment(JLabel.CENTER);
        appText.setForeground(cColor.GREEN);

        roleText.setFont(cFonts.USER_FONT);
        roleText.setBounds(30, 0, 300, 70);
        roleText.setVerticalAlignment(JLabel.CENTER);
        roleText.setForeground(cColor.WHITE);

        copyrightText.setFont(cFonts.COPYUSER_FONT);
        copyrightText.setBounds(225, 590, 550, 30);
        copyrightText.setVerticalAlignment(JLabel.CENTER);
        copyrightText.setForeground(cColor.GRAY);

        header.add(roleText);
        sidebar.add(appText);
        main.add(content);
        main.add(menuTitle);
        add(copyrightText);
        add(sidebar);
        add(header);
        add(main);
    }

    public TemplateHalamanUser(String title) {
        this();
        setTitle(title);
    }

}
