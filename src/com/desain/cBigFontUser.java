package com.desain;

import javax.swing.JLabel;

public class cBigFontUser extends JLabel {

    public cBigFontUser(String text, int x, int y) {
        super();
        setText(text);
        setBounds(x, y, 450, 70);
        setVerticalAlignment(JLabel.CENTER);
        setFont(cFonts.BIG_FONT_USER);
        setForeground(cColor.BLUE);
    }

}
