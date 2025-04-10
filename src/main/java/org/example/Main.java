package org.example;

import GUI.LoginFormGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFormGUI loginForm = new LoginFormGUI();
                loginForm.setVisible(true);
            }
        });
    }
}
