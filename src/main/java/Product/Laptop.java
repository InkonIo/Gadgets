package Product;

import AllOffWindows.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Laptop extends JFrame {
    public Laptop() {
        super("Смартфоны");

        // Основное окно для смартфонов
        setLayout(new FlowLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Здесь могут быть компоненты для отображения товаров
        JLabel label = new JLabel("Товары для смартфонов");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        add(label);

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> {
            dispose(); // Закрывает текущее окно
            new MainWindow(); // Возвращает на главное окно
        });
        add(backButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Phone());
    }
}
