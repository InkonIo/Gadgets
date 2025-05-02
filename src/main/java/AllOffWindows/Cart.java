package AllOffWindows;

import Product.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart extends JFrame {

    private JPanel cartPanel;
    private JLabel totalLabel;
    private Map<ProductItem, Integer> itemQuantities = new HashMap<>();

    public Cart() {
        super("Ваша корзина");
        setSize(500, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        totalLabel = new JLabel("Общая сумма: 0 ₸");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setForeground(new Color(33, 37, 41));
        bottomPanel.add(totalLabel, BorderLayout.WEST);

        // Buttons
        JButton clearButton = createButton("Очистить", new Color(255, 85, 85));
        clearButton.addActionListener(e -> clearCart());

        JButton buyButton = createButton("Купить", new Color(34, 139, 230));
        buyButton.addActionListener(e -> new Shipping(this::clearCart));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(clearButton);
        buttonPanel.add(buyButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Items
        collectItems(PC.cart);
        collectItems(Phone.cart);
        collectItems(Ear.cart);
        collectItems(Laptop.cart);
        collectItems(Tablet.cart);
        collectItems(Watch.cart);

        updateCart();
        setVisible(true);
    }

    public void clearCart() {
        itemQuantities.clear();
        PC.cart.clear();
        Phone.cart.clear();
        Ear.cart.clear();
        Laptop.cart.clear();
        Tablet.cart.clear();
        Watch.cart.clear();
        updateCart();
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
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(new CompoundBorder(
                    new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            // Image
            JLabel imageLabel = new JLabel();
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(item.getImageUrl()));
                Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            } catch (Exception e) {
                imageLabel.setText("Нет изображения");
            }
            itemPanel.add(imageLabel, BorderLayout.WEST);

            // Info
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JLabel priceLabel = new JLabel(item.getPrice());
            priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            priceLabel.setForeground(new Color(255, 69, 0));
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);
            itemPanel.add(infoPanel, BorderLayout.CENTER);

            // Controls
            JPanel controls = new JPanel();
            controls.setBackground(Color.WHITE);
            JLabel quantityLabel = new JLabel(String.valueOf(quantity));
            quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JButton minus = createMiniButton("-");
            JButton plus = createMiniButton("+");

            minus.addActionListener(e -> {
                if (quantity > 1) {
                    itemQuantities.put(item, quantity - 1);
                } else {
                    itemQuantities.remove(item);
                }
                updateCart();
            });

            plus.addActionListener(e -> {
                itemQuantities.put(item, quantity + 1);
                updateCart();
            });

            controls.add(minus);
            controls.add(quantityLabel);
            controls.add(plus);
            itemPanel.add(controls, BorderLayout.EAST);

            cartPanel.add(itemPanel);

            try {
                int price = Integer.parseInt(item.getPrice().replaceAll("[^0-9]", ""));
                totalPrice += price * quantity;
            } catch (NumberFormatException ignored) {}
        }

        totalLabel.setText("Общая сумма: " + totalPrice + " ₸");
        cartPanel.revalidate();
        cartPanel.repaint();
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        return button;
    }

    private JButton createMiniButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(40, 30));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);

        if (text.equals("+")) {
            button.setBackground(new Color(76, 175, 80)); // зелёный
        } else if (text.equals("-")) {
            button.setBackground(new Color(244, 67, 54)); // красный
        } else {
            button.setBackground(new Color(200, 200, 200));
            button.setForeground(Color.DARK_GRAY);
        }

        return button;
    }

}
