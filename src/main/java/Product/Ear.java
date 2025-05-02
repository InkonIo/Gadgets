package Product;

import AllOffWindows.MainWindow;
import AllOffWindows.ProductItem;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Ear extends JFrame {

    public static ArrayList<ProductItem> cart = new ArrayList<>();  // теперь список типа Products

    public Ear() {
        super("Магазин наушников");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(760, 1024);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Добро пожаловать в магазин наушников", SwingConstants.CENTER);
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

        ArrayList<ProductItem> items = new ArrayList<>();

        items.add(new ProductItemImpl(
                "Apple AirPods 4",
                "67 993 ₸",
                "/Images/ForEar/Ear1.jpg",
                """
                Общие параметры
                Тип: гарнитура
                Вид: вкладыши
                Назначение: для любых задач
                Подключение: беспроводное
                Тип акустического оформления: открытые
                Частота воспроизведения: 20 - 20000 Гц
                Импеданс: 16.0 Ом
                Чувствительность: 96.0 дБ
                Вес: 4.3 г
                Тип крепления: без крепления
                Система активного шумоподавления: Нет
                Особенности конструкции: водостойкий корпус
                Разъем для зарядки: USB Type-C
            
                Дополнительная информация
                Цвет: белый
                Комплектация: наушники, зарядный кейс, инструкция
                Совместимость: с iPhone (iOS 18+), iPad (iPad OS 18+)
                Время работы: до 4.5 ч
                Hi-Res Audio: Нет
                Радиус действия: 10.0 м
                Версия Bluetooth: 5.3
                Профиль: hands free / headset
                Микрофон: Да (фиксированный)
                """
        ));

        items.add(new ProductItemImpl(
                "JBL Tune 510BT черный",
                "12 998 ₸",
                "/Images/ForEar/Ear2.jpg",
                """
                Общие параметры
                Тип: гарнитура
                Вид: накладные
                Назначение: для любых задач
                Подключение: беспроводное
                Тип акустического оформления: закрытые
                Частота воспроизведения: 20 - 20000 Гц
                Импеданс: 32.0 Ом
                Чувствительность: 103.5 дБ
                Вес: 160.0 г
                Тип крепления: оголовье
                Система активного шумоподавления: Нет
                Особенности конструкции: складная конструкция
                Разъем для зарядки: USB Type-C
            
                Дополнительная информация
                Цвет: черный
                Комплектация: наушники
                Совместимость: с iPhone, Android
                Емкость аккумулятора: 450 мАч
                Время работы: до 40 часов
            
                Беспроводное подключение
                Радиус действия: 10.0 м
                Версия Bluetooth: 5.0
                NFC: Нет
            
                Микрофон: Да (фиксированный)
                """
        ));

        items.add(new ProductItemImpl(
                "AirPods 3 Max",
                "99 990 ₸",
                "/Images/ForEar/Ear3.jpg",
                """
                Общие параметры
                Тип: гарнитура
                Вид: накладные
                Назначение: музыка, звонки, фитнес
                Подключение: беспроводное (Bluetooth)
                Тип акустического оформления: полуоткрытые
                Частота воспроизведения: 20 - 24000 Гц
                Импеданс: 24 Ом
                Чувствительность: 102 дБ
                Вес: 220 г
                Тип крепления: оголовье
                Система активного шумоподавления: Да
                Особенности конструкции: сенсорное управление, алюминиевый корпус
                Разъем для зарядки: USB-C
            
                Дополнительная информация
                Цвет: графитовый
                Комплектация: наушники, кабель USB-C, кейс
                Совместимость: все устройства с Bluetooth 5.2+
                Время работы: до 20 ч с ANC, до 28 ч без ANC
            
                Микрофон: Да, с шумоподавлением
                Поддержка голосовых ассистентов: Siri, Google Assistant
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
