package AllOffWindows;

import Product.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends JFrame {

    private JPanel cartPanel;
    private JLabel totalLabel;
    private Map<ProductItem, Integer> itemQuantities = new HashMap<>();

    public Cart() {
        super("Корзина");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Modern font style
        totalLabel.setForeground(new Color(50, 50, 50)); // Dark gray text
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton clearButton = new JButton("Очистить корзину");
        clearButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        clearButton.setBackground(new Color(255, 99, 71)); // Tomato red color
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> {
            itemQuantities.clear();
            PC.cart.clear();
            Phone.cart.clear();
            Ear.cart.clear();
            Laptop.cart.clear();
            Tablet.cart.clear();
            Watch.cart.clear();
            updateCart();
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(clearButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Собрать все товары
        collectItems(PC.cart);
        collectItems(Phone.cart);
        collectItems(Ear.cart);
        collectItems(Laptop.cart);
        collectItems(Tablet.cart);
        collectItems(Watch.cart);

        updateCart();
        setVisible(true);
    }

    private void collectItems(List<ProductItem> cart) {
        for (ProductItem item : cart) {
            itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);
        }
    }

    private void updateCart() {
        cartPanel.removeAll();
        int totalPrice = 0;

        for (Map.Entry<ProductItem, Integer> entry : itemQuantities.entrySet()) {
            ProductItem item = entry.getKey();
            int quantity = entry.getValue();

            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Light border around items
            itemPanel.setBackground(Color.WHITE);

            // Изображение товара
            ImageIcon icon = new ImageIcon(getClass().getResource(item.getImageUrl()));
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            itemPanel.add(imageLabel, BorderLayout.WEST);

            // Информация о товаре
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            itemPanel.add(infoPanel, BorderLayout.CENTER);

            // Панель управления количеством
            JPanel controlPanel = new JPanel();
            controlPanel.setBackground(Color.WHITE);
            JLabel quantityLabel = new JLabel(String.valueOf(quantity));

            JButton plusButton = new JButton("+");
            JButton minusButton = new JButton("-");
            plusButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            minusButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            plusButton.setBackground(new Color(34, 139, 34)); // Green
            minusButton.setBackground(new Color(255, 69, 0)); // Red
            plusButton.setForeground(Color.WHITE);
            minusButton.setForeground(Color.WHITE);

            plusButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            minusButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            plusButton.setFocusPainted(false);
            minusButton.setFocusPainted(false);

            plusButton.addActionListener(e -> {
                itemQuantities.put(item, quantity + 1);
                updateCart();
            });

            minusButton.addActionListener(e -> {
                if (quantity > 1) {
                    itemQuantities.put(item, quantity - 1);
                } else {
                    itemQuantities.remove(item);
                }
                updateCart();
            });

            controlPanel.add(minusButton);
            controlPanel.add(quantityLabel);
            controlPanel.add(plusButton);

            itemPanel.add(controlPanel, BorderLayout.EAST);

            // Название и цена товара
            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            nameLabel.setForeground(new Color(50, 50, 50));

            JLabel priceLabel = new JLabel(item.getPrice());
            priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            priceLabel.setForeground(new Color(255, 69, 0)); // Red color for price

            itemPanel.add(nameLabel, BorderLayout.WEST);
            itemPanel.add(priceLabel, BorderLayout.SOUTH);

            cartPanel.add(itemPanel);
            cartPanel.add(Box.createVerticalStrut(10)); // Space between items

            try {
                int price = Integer.parseInt(item.getPrice().replaceAll("[^0-9]", ""));
                totalPrice += price * quantity;
            } catch (NumberFormatException ignored) {}
        }

        totalLabel.setText("Общая сумма: " + totalPrice + " ₸");
        cartPanel.revalidate();
        cartPanel.repaint();
    }
}
