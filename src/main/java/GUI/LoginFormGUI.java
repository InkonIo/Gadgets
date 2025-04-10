package GUI;

import AllOffWindows.MainWindow;
import database.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFormGUI extends Form {

    public LoginFormGUI() {
        super("Вход");
        addGuiComponents();
    }

    private void addGuiComponents() {
        setLayout(null);

        // Заголовок
        JLabel loginLabel = new JLabel("Вход");
        loginLabel.setBounds(0, 50, 400, 50);
        loginLabel.setForeground(new Color(113, 129, 109));  // #71816d
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        // Поле для имени пользователя
        JLabel usernameLabel = new JLabel("Логин:");
        usernameLabel.setBounds(50, 120, 300, 20);
        usernameLabel.setForeground(new Color(113, 129, 109));  // #71816d
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 150, 300, 40);
        usernameField.setBackground(new Color(201, 183, 156)); // #c9b79c
        usernameField.setForeground(new Color(113, 129, 109));  // #71816d
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        usernameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Закругленные углы
        add(usernameField);

        // Поле для пароля
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(50, 210, 300, 20);
        passwordLabel.setForeground(new Color(113, 129, 109));  // #71816d
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 240, 300, 40);
        passwordField.setBackground(new Color(201, 183, 156)); // #c9b79c
        passwordField.setForeground(new Color(113, 129, 109));  // #71816d
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Закругленные углы
        add(passwordField);

        // Кнопка Войти
        JButton loginButton = new JButton("Войти");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(new Color(113, 129, 109));  // #71816d
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(50, 310, 300, 45);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Закругленные углы
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (MyJDBC.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Вход выполнен успешно!");

                    // Закрываем текущее окно и открываем главное окно магазина
                    dispose();
                    new MainWindow().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Ошибка входа...");
                }
            }
        });
        add(loginButton);

        // Регистрация
        JLabel registerLabel = new JLabel("Нет аккаунта? Зарегистрируйтесь");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(new Color(113, 129, 109));  // #71816d
        registerLabel.setBounds(50, 380, 300, 25);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new RegisterFormGUI().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
