package com.partials;

import javax.swing.JLabel;

public class cLabelUser extends JLabel {
    public cLabelUser(String text, int x, int y) {
        super();
        setText(text);
        setBounds(x, y, 400, 40);
        setVerticalAlignment(JLabel.CENTER);
        setFont(cFonts.USER_INFO_FONT);
        setForeground(cColor.BLACK);
    }
}
