import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Register extends JFrame implements ActionListener {
    private String registeredUsername;
    private String registeredPassword;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;
    private JTextArea logTextArea;

    public Register() {
        super("Grab");
        registeredUsername = "";
        registeredPassword = "";

        // Create username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        // Create password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        // Create register button
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        // Create login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        // Create log text area
        logTextArea = new JTextArea(10, 30);
        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);

        // Set layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        // Add components to frame
        add(panel, BorderLayout.CENTER);
        add(logScrollPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (register(username, password)) {
                logTextArea.append("Registration successful! Welcome, " + username + "!\n");
                registeredUsername = username;
                registeredPassword = password; // Store the registered password
                saveUserInfo(username, password); // Save user info to a text file
                passwordField.setText(""); // Clear the password field after successful registration
            } else {
                logTextArea.append("Registration failed. User already exists.\n");
            }
        } else if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password)) {
                logTextArea.append("Login successful!\n");
                // Open the Grab app window after successful login
                openGrabApp();
            } else {
                logTextArea.append("Login failed. Invalid username or password.\n");
            }
        }
    }

    private boolean register(String username, String password) {
        // Simulate registration logic (e.g., check against database)
        if (username.isEmpty() || password.isEmpty()) {
            logTextArea.append("Please enter both username and password.\n");
            return false;
        }
        // Check if user already exists
        if (username.equals(registeredUsername)) {
            logTextArea.append("User already exists. Please login.\n");
            return false;
        }
        // Simulate user registration
        logTextArea.append("Registering new user...\n");
        registeredUsername = username; // Store the new username
        registeredPassword = password; // Store the new password
        // Assume registration successful
        return true;
    }

    private boolean authenticate(String username, String password) {
        // Simulate authentication logic (e.g., check against database)
        if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
            return true;
        } else {
            logTextArea.append("Invalid username or password.\n");
            return false;
        }
    }

    private void openGrabApp() {
        // Open the Grab app window
        new GrabApp();
        // Close the registration window
        dispose();
    }

    private void saveUserInfo(String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("userinfo.txt", true))) {
            writer.println(username + "," + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(Register::new);
    }
}

class GrabApp extends JFrame {
    public GrabApp() {
        super("Grab App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

	public void saveReceipt(String carModel, String pickupDate, String returnDate, double totalPrice, String name,
			String age) {
		// TODO Auto-generated method stub
		
	}
}
