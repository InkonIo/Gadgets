package GUI;

import database.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFormGUI extends Form {
    public RegisterFormGUI() {
        super("Регистрация");
        addGuiComponents();
    }

    private void addGuiComponents() {
        setLayout(null);

        // Заголовок формы
        JLabel registerLabel = new JLabel("Регистрация");
        registerLabel.setBounds(0, 20, 370, 80);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        // Метка для имени пользователя
        JLabel usernameLabel = new JLabel("Имя пользователя:");
        usernameLabel.setBounds(20, 110, 300, 20);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameLabel);

        // Поле ввода имени пользователя
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20, 140, 330, 45);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameField);

        // Метка для пароля
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(20, 220, 300, 20);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordLabel);

        // Поле ввода пароля
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 250, 330, 45);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordField);

        // Кнопка регистрации
        JButton registerButton = new JButton("Зарегистрироваться");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setForeground(CommonConstants.BACKGROUND_COLOR);
        registerButton.setBounds(70, 350, 230, 45);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Проверка на пустые поля
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Поля не должны быть пустыми!");
                return;
            }

            // Регистрация пользователя
            if (MyJDBC.register(username, password)) {
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Регистрация прошла успешно!");
                RegisterFormGUI.this.dispose();
                new LoginFormGUI().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(RegisterFormGUI.this, "Пользователь уже существует.");
            }
        });
        add(registerButton);

        // Ссылка для перехода на форму входа
        JLabel loginLabel = new JLabel("Уже есть аккаунт? Войти");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(90, 410, 190, 25);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();
                new LoginFormGUI().setVisible(true);
            }
        });
        add(loginLabel);
    }
}
