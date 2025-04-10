package GUI;

import javax.swing.*;

public class Form extends JFrame {
    public Form(String title) {
        super(title);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(CommonConstants.BACKGROUND_COLOR);
    }
}
