package Product;

import AllOffWindows.MainWindow;
import AllOffWindows.ProductItem;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Watch extends JFrame {

    public static ArrayList<ProductItem> cart = new ArrayList<>();  // теперь список типа Products

    public Watch() {
        super("Магазин смарт-часов");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(760, 1024);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Добро пожаловать в магазин смарт-часов", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // Основная панель с карточками товаров
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Список товаров
        ArrayList<ProductItem> items = new ArrayList<>();
        items.add(new ProductItemImpl(
                "Huawei Watch GT 5 Pro 46 мм",
                "114 284 ₸",
                "/Images/ForWatch/Watch2.jfif",
                """
                Стильные и мощные смарт-часы от Huawei с корпусом 46 мм.
                Цвет: черный. Поддерживают мониторинг пульса, сна и уровня стресса.
                Автономность до 14 дней, водонепроницаемость, GPS и продвинутые спортивные функции.
                Идеальны для активного образа жизни.
                """
        ));

        items.add(new ProductItemImpl(
                "Apple Watch Series 10 S/M 42 мм",
                "191 332 ₸",
                "/Images/ForWatch/Watch1.jfif",
                """
                Смарт-часы от Apple с изысканным дизайном: золотистый корпус и белый ремешок.
                Диагональ 42 мм, множество фитнес-функций, экстренные уведомления и поддержка приложений.
                Интеграция с iPhone, отслеживание активности, сна и сердечного ритма.
                """
        ));


        // Добавление карточек в сетку
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;

        int col = 0;
        int row = 0;
        int columns = 2;

        for (ProductItem item : items) {
            JPanel productCard = createProductCard(item);

            gbc.gridx = col;
            gbc.gridy = row;

            productPanel.add(productCard, gbc);

            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }

        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> {
            dispose();
            new MainWindow();
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createProductCard(ProductItem item) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(300, 300));

        JButton infoButton = new JButton("Показать описание");
        infoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, item.getDescription(), "Описание " + item.getName(), JOptionPane.INFORMATION_MESSAGE);
        });

        JLabel imageLabel;
        try {
            URL imageUrl = getClass().getResource(item.getImageUrl());
            if (imageUrl == null) {
                System.out.println("Изображение не найдено: " + item.getImageUrl());
                imageLabel = new JLabel("Изображение не найдено", SwingConstants.CENTER);
            } else {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image scaled = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaled));
            }
        } catch (Exception e) {
            System.out.println("Ошибка загрузки изображения: " + item.getImageUrl());
            imageLabel = new JLabel("Изображение не найдено", SwingConstants.CENTER);
        }

        card.add(imageLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel(item.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel priceLabel = new JLabel(item.getPrice(), SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton buyButton = new JButton("Добавить в корзину");
        buyButton.setPreferredSize(new Dimension(150, 40));
        buyButton.addActionListener(e -> {
            cart.add(item);
            JOptionPane.showMessageDialog(this, item.getName() + " добавлен в корзину!");
        });

        infoPanel.setLayout(new GridLayout(4, 1)); // Было 3, стало 4
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(buyButton);
        infoPanel.add(infoButton);

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    static class ProductItemImpl implements ProductItem {
        private String name;
        private String price;
        private String imageUrl;
        private String description; // <-- добавлено

        public ProductItemImpl(String name, String price, String imageUrl, String description) {
            this.name = name;
            this.price = price;
            this.imageUrl = imageUrl;
            this.description = description;
        }

        @Override
        public String getName() { return name; }

        @Override
        public String getPrice() { return price; }

        @Override
        public String getImageUrl() { return imageUrl; }

        public String getDescription() { return description; } // <-- геттер
    }

}
