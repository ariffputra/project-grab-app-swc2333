import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReceiptPayment extends JFrame implements ActionListener {
    // Payment information variables
    private String carModel;
    private String pickupDate;
    private String returnDate; // New variable for return date
    private double totalPrice;
    private String name;
    private String age;

    public ReceiptPayment(String carModel, String pickupDate, String returnDate, double totalPrice, String name, String age) {
        // Store payment information
        this.carModel = carModel;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate; // Initialize return date
        this.totalPrice = totalPrice;
        this.name = name;
        this.age = age;

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
        JTextArea receiptTextArea = new JTextArea();
        receiptTextArea.setEditable(false);
        receiptTextArea.setBackground(Color.WHITE);
        receiptTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(new JScrollPane(receiptTextArea), BorderLayout.CENTER); // Add scroll pane for text area

        // Generate receipt information
        generateReceiptInfo(receiptTextArea);

        // Add image panel for car model
        JPanel imagePanel = new JPanel();
        ImageIcon carImage = null;

        try {
            if (carModel.equals("Economy")) {
                carImage = new ImageIcon(getClass().getResource("/image/ECONOMY.jpeg")); // Load the image for Economy car
            } else if (carModel.equals("SUV")) {
                carImage = new ImageIcon(getClass().getResource("/image/SUV.jpeg")); // Load the image for SUV car
            } else if (carModel.equals("Luxury")) {
                carImage = new ImageIcon(getClass().getResource("/image/luxury_car_image.jpg")); // Load the image for Luxury car
            } else if (carModel.equals("Compact")) {
                carImage = new ImageIcon(getClass().getResource("/image/images.jpeg")); // Load the image for Compact car
            } else if (carModel.equals("Convertible")) {
                carImage = new ImageIcon(getClass().getResource("/image/convertible_car_image.jpg")); // Load the image for Convertible car
            } else if (carModel.equals("Sports")) {
                carImage = new ImageIcon(getClass().getResource("/image/sports_car_image.jpg")); // Load the image for Sports car
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Print stack trace if an error occurs
        }

        JLabel carImageLabel = new JLabel(carImage);
        imagePanel.add(carImageLabel);
        panel.add(imagePanel, BorderLayout.WEST);

        // Create rating panel
        JPanel ratingPanel = new JPanel();
        JLabel ratingLabel = new JLabel("Rate this app: ");
        JComboBox<String> ratingComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        JButton submitRatingButton = new JButton("Submit");
        submitRatingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rating = (String) ratingComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "Thank you for rating the app with " + rating + " stars!");
            }
        });
        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingComboBox);
        ratingPanel.add(submitRatingButton);
        panel.add(ratingPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to generate receipt information
    private void generateReceiptInfo(JTextArea receiptTextArea) {
        StringBuilder receiptInfo = new StringBuilder();
        receiptInfo.append("Name: ").append(name).append("\n");
        receiptInfo.append("Age: ").append(age).append("\n");
        receiptInfo.append("Car Model: ").append(carModel).append("\n");
        receiptInfo.append("Pickup Date: ").append(pickupDate).append("\n");
        receiptInfo.append("Return Date: ").append(returnDate).append("\n"); // Add return date
        receiptInfo.append("Total Price: RM").append(totalPrice).append("\n");
        receiptTextArea.setText(receiptInfo.toString());
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
