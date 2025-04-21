package AllOffWindows;

import Product.Ear;
import Product.Laptop;
import Product.PC;
import Product.Phone;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Cart extends JFrame {

    public Cart() {
        super("Корзина");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(Color.WHITE);

        int totalPrice = 0;

        totalPrice += addItemsFromCart(cartPanel, PC.cart);
        totalPrice += addItemsFromCart(cartPanel, Phone.cart);
        totalPrice += addItemsFromCart(cartPanel, Ear.cart);
        totalPrice += addItemsFromCart(cartPanel, Laptop.cart);

        JLabel totalLabel = new JLabel("Общая сумма: " + totalPrice + " ₸");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(totalLabel);

        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private int addItemsFromCart(JPanel cartPanel, List<ProductItem> cart) {
        int subtotal = 0;

        for (ProductItem item : cart) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel nameLabel = new JLabel(item.getName());
            JLabel priceLabel = new JLabel(item.getPrice());
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            itemPanel.add(nameLabel, BorderLayout.WEST);
            itemPanel.add(priceLabel, BorderLayout.EAST);
            cartPanel.add(itemPanel);

            try {
                subtotal += Integer.parseInt(item.getPrice().replace(" ₸", "").replace("₸", "").replace(" ", ""));
            } catch (NumberFormatException ignored) {}
        }

        return subtotal;
    }
}
