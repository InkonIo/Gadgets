package AllOffWindows;

import Product.Ear;
import Product.Laptop;
import Product.PC;
import Product.Phone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    // Массив для категорий, содержащий название и путь к изображению
    private String[][] categories = {
            {"Смартфоны", "/Images/phone.jpg"},
            {"Наушники", "/Images/ear.jpg"},
            {"Ноутбуки", "/Images/laptop.jpg"},
            {"Компьютеры", "/Images/pc.jpg"}
    };

    public MainWindow() {
        super("Магазин Гаджетов");
        setLayout(new FlowLayout()); // Для удобного размещения блоков категорий
        setSize(1440, 980);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255)); // Белый фон для окна

        // Создаем и добавляем блоки категорий
        for (String[] category : categories) {
            JPanel categoryPanel = createCategoryPanel(category[0], category[1]);
            add(categoryPanel);
        }

        setVisible(true);
    }

    // Метод для создания панели категории с изображением, названием и кнопкой
    private JPanel createCategoryPanel(String categoryName, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 240)); // Задаем размер блока
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245)); // Фон блока

        // Загрузка изображения из папки resources/Images
        ImageIcon categoryIcon = new ImageIcon(getClass().getResource(imagePath));

        // Масштабируем изображение, чтобы оно поместилось в панель
        Image img = categoryIcon.getImage();
        Image scaledImg = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH); // Масштабируем изображение
        categoryIcon = new ImageIcon(scaledImg); // Присваиваем новое изображение

        // Создаем изображение и добавляем его в панель
        JLabel imageLabel = new JLabel(categoryIcon);
        panel.add(imageLabel, BorderLayout.CENTER);

        // Кнопка с названием категории
        JButton button = new JButton(categoryName); // Название категории на кнопке
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Сделаем текст кнопки жирным
        button.setBackground(new Color(72, 118, 255)); // Синий цвет кнопки
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 40)); // Задаем размер кнопки
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Закрытие окна MainWindow
                dispose();

                // Перенаправление на нужное окно
                switch (categoryName) {
                    case "Смартфоны":
                        new Phone();
                        break;
                    case "Наушники":
                        new Ear();
                        break;
                    case "Ноутбуки":
                        new Laptop();
                        break;
                    case "Компьютеры":
                        new PC();
                        break;
                    default:
                        JOptionPane.showMessageDialog(MainWindow.this, "Неизвестная категория.");
                }
            }
        });

        // Добавляем кнопку в панель
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
