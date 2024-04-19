import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Receipt extends JFrame implements ActionListener {
    public Receipt(String carModel, String pickupTime, String pickupDate, double totalPrice) {
        // Create receipt frame
        setTitle("Car Booking Receipt");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Create Label
        JLabel label = new JLabel("Car Booking Receipt");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Create receipt text area
        JTextArea receiptTextArea = new JTextArea("Car Model: " + carModel + "\n" +
                "Pickup Time: " + pickupTime + "\n" + "Pickup Date: " + pickupDate + "\n" +
                "Total Price: RM" + totalPrice);
        receiptTextArea.setEditable(false);
        receiptTextArea.setBackground(Color.WHITE);
        receiptTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(receiptTextArea, BorderLayout.CENTER);

        // Create close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        panel.add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String[] args) {
        // Example usage:
        new Receipt("Economy", "10:00 AM", "2024-04-20", 80.00);
    }
}
