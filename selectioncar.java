import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class selectioncar extends JFrame implements ActionListener {
    // Declaration Variables
    JButton checkoutButton;
    JCheckBox gpsCheckBox, babySeatCheckBox, insuranceCheckBox;
    JRadioButton onlineBankingRadio, creditCardRadio;
    ButtonGroup paymentMethodGroup;
    JComboBox<String> carModelComboBox; // Combo box for car selection
    String[] carModels = {"Economy", "SUV", "Luxury", "Compact", "Convertible", "Sports"}; // Available car models
    JLabel basePriceLabel; // Label to display base price
    JDateChooser pickupDatePicker, returnDatePicker; // Using JCalendar's date picker
    JTextField nameField, ageField; // Text fields for name and age
    double totalPriceBase = 0.00, totalPriceExtras = 0.00, overallTotal = 0.00;
    boolean[] carAvailability = {true, true, true, true, true, true}; // Indicates whether a car is available

    public selectioncar() {
        // Create Title
        setTitle("Car Booking System");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(18, 1)); // Increased to accommodate name and age fields
        add(panel);

        // Create Label 0 (Name)
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(nameLabel);

        // Create name text field
        nameField = new JTextField();
        panel.add(nameField);

        // Create Label 1 (Age)
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(ageLabel);

        // Create age text field
        ageField = new JTextField();
        panel.add(ageField);

        // Create Label 2 (Select Car Model)
        JLabel label1 = new JLabel("Select Car Model:");
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label1);

        // Create car model combo box with custom renderer
        carModelComboBox = new JComboBox<>(carModels);
        carModelComboBox.setRenderer(new ImageTextRenderer());
        carModelComboBox.setSelectedIndex(0); // Set default selection to Economy
        panel.add(carModelComboBox);

        // Add base price label
        basePriceLabel = new JLabel("Base Price: RM0.00"); // Default base price label
        basePriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(basePriceLabel);

        // Create Label 3 (Pickup Date)
        JLabel label2 = new JLabel("Pickup Date:");
        label2.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label2);

        // Create pickup date picker
        pickupDatePicker = new JDateChooser();
        panel.add(pickupDatePicker);

        // Create Label 4 (Return Date)
        JLabel label3 = new JLabel("Return Date:");
        label3.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label3);

        // Create return date picker
        returnDatePicker = new JDateChooser();
        panel.add(returnDatePicker);

        // Create Label 5 (Select Additional Items)
        JLabel label4 = new JLabel("Select Additional Items : ");
        label4.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label4);

        // Create extra options checkboxes
        gpsCheckBox = new JCheckBox("GPS (RM 20)");
        babySeatCheckBox = new JCheckBox("Baby Seat (RM 15)");
        insuranceCheckBox = new JCheckBox("Insurance (RM 30)");
        panel.add(gpsCheckBox);
        panel.add(babySeatCheckBox);
        panel.add(insuranceCheckBox);

        // Create payment method radio buttons
        JPanel paymentMethodPanel = new JPanel();
        paymentMethodPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel paymentMethodLabel = new JLabel("Select Payment Method: ");
        onlineBankingRadio = new JRadioButton("Online Banking");
        creditCardRadio = new JRadioButton("Credit Card");
        paymentMethodGroup = new ButtonGroup();
        paymentMethodGroup.add(onlineBankingRadio);
        paymentMethodGroup.add(creditCardRadio);
        paymentMethodPanel.add(paymentMethodLabel);
        paymentMethodPanel.add(onlineBankingRadio);
        paymentMethodPanel.add(creditCardRadio);
        panel.add(paymentMethodPanel);

        // Create checkout button
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);
        panel.add(checkoutButton);

        setVisible(true);

        // Register Listeners
        gpsCheckBox.addActionListener(this);
        babySeatCheckBox.addActionListener(this);
        insuranceCheckBox.addActionListener(this);
        carModelComboBox.addActionListener(this); // Add action listener to car model combo box
    }

    // Method to calculate the base price based on car model
    private double calculateBasePrice(String carModel) {
        double basePrice = 0.00;
        // Add logic to determine base price based on car model
        // Example:
        if (carModel.equals("Economy")) {
            basePrice = 50.00; // Assuming base price for Economy car
        } else if (carModel.equals("SUV")) {
            basePrice = 80.00; // Assuming base price for SUV car
        } else if (carModel.equals("Luxury")) {
            basePrice = 150.00; // Assuming base price for Luxury car
        } else if (carModel.equals("Compact")) {
            basePrice = 60.00; // Assuming base price for Compact car
        } else if (carModel.equals("Convertible")) {
            basePrice = 200.00; // Assuming base price for Convertible car
        } else if (carModel.equals("Sports")) {
            basePrice = 180.00; // Assuming base price for Sports car
        }
        return basePrice;
    }

    // Method to calculate the total price including base price and extras
    private void calculateOverallTotal() {
        overallTotal = totalPriceBase + totalPriceExtras;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gpsCheckBox || e.getSource() == babySeatCheckBox || e.getSource() == insuranceCheckBox ||
                e.getSource() == carModelComboBox) { // Update base price label when car model is selected
            // Get selected car model from combo box
            String selectedCarModel = (String) carModelComboBox.getSelectedItem();
            totalPriceBase = calculateBasePrice(selectedCarModel);
            basePriceLabel.setText("Base Price: RM" + String.format("%.2f", totalPriceBase)); // Update base price label
            calculateOverallTotal(); // Recalculate overall total
        }
        if (e.getSource() == gpsCheckBox) {
            if (gpsCheckBox.isSelected()) {
                totalPriceExtras += 20.00;
            } else {
                totalPriceExtras -= 20.00;
            }
        }
        if (e.getSource() == babySeatCheckBox) {
            if (babySeatCheckBox.isSelected()) {
                totalPriceExtras += 15.00;
            } else {
                totalPriceExtras -= 15.00;
            }
        }
        if (e.getSource() == insuranceCheckBox) {
            if (insuranceCheckBox.isSelected()) {
                totalPriceExtras += 30.00;
            } else {
                totalPriceExtras -= 30.00;
            }
        }
        if (e.getSource() == checkoutButton) {
            // Perform checkout action
            confirmPaymentAndCreateReceipt();
        }
    }

    // Method to confirm payment via bank before generating receipt
    private void confirmPaymentAndCreateReceipt() {
        int confirmOption = JOptionPane.showConfirmDialog(this, "Please confirm payment via bank before generating receipt.",
                "Confirm Payment", JOptionPane.YES_NO_OPTION);
        if (confirmOption == JOptionPane.YES_OPTION) {
            // Payment confirmed, proceed to generate receipt
            createReceipt();
        }
    }

    // Method to create receipt and close payment frame
    private void createReceipt() {
        // Check if a car model is selected
        if (carModelComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a car model.");
            return; // Exit the method
        }

        // Check if name and age are filled
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        if (name.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in your name and age.");
            return; // Exit the method
        }

        // Check if age is valid (above 18)
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.");
            return; // Exit the method
        }

        if (age < 18) {
            JOptionPane.showMessageDialog(this, "You must be 18 or older to book a car.");
            return; // Exit the method
        }

        // Get selected car model from combo box
        String carModel = (String) carModelComboBox.getSelectedItem();
        totalPriceBase = calculateBasePrice(carModel); // Update base price based on selected car model
        calculateOverallTotal();

        // Get selected pickup date
        Date pickupDate = pickupDatePicker.getDate();

        // Get selected return date
        Date returnDate = returnDatePicker.getDate();

        if (pickupDate == null || returnDate == null) {
            JOptionPane.showMessageDialog(this, "Please select pickup and return dates.");
        } else {
            // Check car availability
            int carIndex = carModelComboBox.getSelectedIndex();
            if (!carAvailability[carIndex]) {
                JOptionPane.showMessageDialog(this, "The selected car model is not available.");
                return;
            }

            // Convert pickup and return dates to desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Display receipt and close payment frame
            new ReceiptPayment(carModel, dateFormat.format(pickupDate), dateFormat.format(returnDate),
                    overallTotal, name, ageText);
            dispose();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        new PaymentFrame();
    }

    // Custom renderer to display text and images in the combo box
    private class ImageTextRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Set text
            label.setText(value.toString());

            // Set icon based on the value (car model)
            if (value.equals("SUV")) {
                // Load the image using ImageIcon
                ImageIcon suvIcon = new ImageIcon("C:/Users/ariff/Downloads/suv.jpg"); // Adjust the path to your SUV image file
                // Set the icon
                label.setIcon(suvIcon);
            } else {
                // For other car models, set no icon
                label.setIcon(null);
            }

            // Return the customized label
            return label;
        }
    }
}
