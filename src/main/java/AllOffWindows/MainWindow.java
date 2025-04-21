package AllOffWindows;

import Product.Ear;
import Product.Laptop;
import Product.PC;
import Product.Phone;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private String[][] categories = {
            {"Смартфоны", "/Images/phone.jpg"},
            {"Наушники", "/Images/ear.jpg"},
            {"Ноутбуки", "/Images/laptop.jpg"},
            {"Компьютеры", "/Images/pc.jpg"},
            {"Категория 5", "/Images/pc.jpg"},
            {"Категория 6", "/Images/ear.jpg"},
            {"Категория 7", "/Images/phone.jpg"},
            {"Категория 8", "/Images/laptop.jpg"},
            {"Категория 9", "/Images/pc.jpg"}
    };

    public MainWindow() {
        super("Магазин Гаджетов");
        setSize(1440, 980);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;

        // карточки
        int col = 0;
        int row = 0;
        int columns = 3;

        for (String[] category : categories) {
            String name = category[0];
            String image = category[1];

            JPanel card = createCategoryPanel(name, image);
            gbc.gridx = col;
            gbc.gridy = row;

            productPanel.add(card, (GridBagConstraints) gbc.clone());

            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }

        // оборачиваем карточки в панель с прокруткой
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // плавный скролл
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // кнопка корзины снизу
        JButton cartButton = new JButton("Корзина");
        cartButton.setFont(new Font("Arial", Font.BOLD, 14));
        cartButton.setBackground(new Color(72, 118, 255));
        cartButton.setForeground(Color.WHITE);
        cartButton.setPreferredSize(new Dimension(160, 30));
        cartButton.setFocusPainted(false);
        cartButton.addActionListener(e -> new Cart());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(cartButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }


    private JPanel createCategoryPanel(String categoryName, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 220));
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        ImageIcon categoryIcon = new ImageIcon(getClass().getResource(imagePath));
        Image img = categoryIcon.getImage().getScaledInstance(160, 140, Image.SCALE_SMOOTH);
        categoryIcon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(categoryIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);

        JButton button = new JButton(categoryName);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(72, 118, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(160, 30));
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            dispose();
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
                    JOptionPane.showMessageDialog(this, "Неизвестная категория.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(button);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}

// Используется GridBagLayout с 3 колонками.
//
//Каждая строка автоматически центрируется благодаря отступам (Insets).
//
//Используется JScrollPane, чтобы появлялся скролл при добавлении большего количества карточек.
