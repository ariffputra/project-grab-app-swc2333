import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GrabApp extends JFrame implements ActionListener {
    JButton startButton;

    public GrabApp() {
        // Create a title for GRAB
        setTitle("Grab");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create image icon logo
        ImageIcon logo = new ImageIcon("image/grab_logo.png");

        // Create a Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        // Create label
        JLabel label = new JLabel("Welcome to Grab Booking System", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);

        // Create Start Button
        startButton = new JButton("Book a Car");
        startButton.addActionListener(this);
        panel.add(startButton, BorderLayout.SOUTH);

        // Add hover effect to the button
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(UIManager.getColor("control"));
            }
        });

        setVisible(true);
    }

    // method overriding
    public void actionPerformed(ActionEvent e) {
        // Play a click sound
        playClickSound();
        // Open PaymentFrame when the button is clicked
        new PaymentFrame().setVisible(true);
        dispose(); // Close the current frame
    }

    // Method to play a click sound
    private void playClickSound() {
        try {
            // Load sound file
            java.applet.AudioClip click = java.applet.Applet.newAudioClip(getClass().getResource("click.wav"));
            // Play the sound
            click.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GrabApp::new); // Corrected class name to GrabApp
    }
}
