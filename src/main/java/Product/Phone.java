package Product;

import AllOffWindows.MainWindow;
import AllOffWindows.ProductItem;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Phone extends JFrame {

    public static ArrayList<ProductItem> cart = new ArrayList<>();  // теперь список типа Products

    public Phone() {
        super("Магазин смартфонов");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(760, 1024);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Добро пожаловать в магазин смартфонов", SwingConstants.CENTER);
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
                "iPhone 15 Pro",
                "230 000 ₸",
                "/Images/ForPhone/Ph1.jpg",
                """
                Общие:
                - 4G (LTE): Да
                - 5G: Да
                - NFC: Да
                - ОС: iOS 17
                - SIM: nano SIM + eSIM
                - Материал: металл и стекло
                - Цвет: черный
            
                Дисплей:
                - OLED, Super Retina XDR, 6.1", 2566x1179 (460 ppi)
                - Автоповорот, защита от царапин
            
                Память и процессор:
                - 6 ГБ RAM, 128 ГБ ROM
                - Apple A16 Bionic, 6-ядерный
            
                Камеры:
                - Основная: 48+12+12 МП, вспышка
                - Фронтальная: 12 МП
                - Видео: до 4K 60 к/с
            
                Аудио: WAV, AAC, WMA, MP3, FLAC
                """
        ));

        items.add(new ProductItemImpl(
                "iPhone 14 Pro",
                "200 000 ₸",
                "/Images/ForPhone/Ph2.jpg",
                """
                Общие:
                - 4G (LTE): Да
                - 5G: Да
                - NFC: Да
                - ОС: iOS 16
                - SIM: nano SIM + eSIM
                - Материал: стекло и нержавеющая сталь
                - Цвет: серебристый
            
                Дисплей:
                - OLED, Super Retina XDR, 6.1", 2532x1170 (460 ppi)
                - Автоповорот, защита от царапин
            
                Память и процессор:
                - 6 ГБ RAM, 128 ГБ ROM
                - Apple A15 Bionic, 6-ядерный
            
                Камеры:
                - Основная: 48+12+12 МП, вспышка
                - Фронтальная: 12 МП
                - Видео: до 4K 60 к/с
            
                Аудио: WAV, AAC, WMA, MP3, FLAC
                """
        ));

        items.add(new ProductItemImpl(
                "Samsung Galaxy S23 Ultra",
                "270 000 ₸",
                "/Images/ForPhone/Ph3.jpg",
                """
                Общие:
                - 4G (LTE): Да
                - 5G: Да
                - NFC: Да
                - ОС: Android 13
                - SIM: nano SIM + eSIM
                - Материал: стекло и алюминий
                - Цвет: черный
            
                Дисплей:
                - Dynamic AMOLED 2X, 6.8", 3088x1440 (500 ppi)
                - Автоповорот, защита от царапин
            
                Память и процессор:
                - 12 ГБ RAM, 256 ГБ ROM
                - Snapdragon 8 Gen 2, 8-ядерный
            
                Камеры:
                - Основная: 200+12+10+10 МП, вспышка
                - Фронтальная: 12 МП
                - Видео: до 8K 30 к/с
            
                Аудио: WAV, AAC, MP3, FLAC
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
