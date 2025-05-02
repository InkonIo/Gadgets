package AllOffWindows;

import javax.swing.*;
import java.awt.*;

public class Shipping extends JFrame {

    private final Runnable onSuccess;

    public Shipping(Runnable onSuccess) {
        super("Оформление доставки");
        this.onSuccess = onSuccess;

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField cityField = new JTextField();
        JTextField streetField = new JTextField();
        JTextField entranceField = new JTextField();
        JTextField apartmentField = new JTextField();

        panel.add(new JLabel("Город:"));
        panel.add(cityField);
        panel.add(new JLabel("Улица, дом:"));
        panel.add(streetField);
        panel.add(new JLabel("Подъезд:"));
        panel.add(entranceField);
        panel.add(new JLabel("Квартира:"));
        panel.add(apartmentField);

        JButton deliverButton = new JButton("Доставить");
        deliverButton.setBackground(new Color(34, 139, 34));
        deliverButton.setForeground(Color.WHITE);
        deliverButton.setFocusPainted(false);
        deliverButton.addActionListener(e -> {
            if (cityField.getText().isEmpty() || streetField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Успешно куплено!", "Успех", JOptionPane.INFORMATION_MESSAGE);

            if (onSuccess != null) {
                onSuccess.run(); // Очистка корзины
            }

            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deliverButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
