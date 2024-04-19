import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class booking implements ActionListener {
    private JFrame frame;
    private JComboBox<String> carBox;
    private JRadioButton[] radioTime;
    private JCheckBox chkInsurance;
    private JButton btnNext;
    private String time = "";
    private boolean insuranceSelected = false;

    public booking() {
        String[] cars = {"JustGrab", "GrabCar 7-seater", "GrabCar Plus"};

        radioTime = new JRadioButton[8];
        radioTime[0] = new JRadioButton("9:00am");
        radioTime[1] = new JRadioButton("10:30am");
        radioTime[2] = new JRadioButton("12:00pm");
        radioTime[3] = new JRadioButton("1:30pm");
        radioTime[4] = new JRadioButton("3:00pm");
        radioTime[5] = new JRadioButton("4:30pm");
        radioTime[6] = new JRadioButton("6:00pm");
        radioTime[7] = new JRadioButton("7:30pm");

        ButtonGroup groupTime = new ButtonGroup();
        for (int i = 0; i < 8; i++)
            groupTime.add(radioTime[i]);

        chkInsurance = new JCheckBox("Insurance");

        JPanel panelCar = new JPanel();
        panelCar.setLayout(new GridLayout(1, 0));
        panelCar.setBounds(0, 0, 500, 50);
        panelCar.setBorder(BorderFactory.createTitledBorder("Choose Car"));

        JPanel panelTime = new JPanel();
        panelTime.setBounds(0, 50, 500, 80);
        panelTime.setBorder(BorderFactory.createTitledBorder("Choose Time"));
        for (int i = 0; i < 8; i++)
            panelTime.add(radioTime[i]);

        JPanel panelInsurance = new JPanel();
        panelInsurance.setBounds(0, 130, 500, 50);
        panelInsurance.setBorder(BorderFactory.createTitledBorder("Additional Options"));
        panelInsurance.add(chkInsurance);

        btnNext = new JButton("NEXT");
        btnNext.setBounds(200, 200, 100, 40);

        frame = new JFrame();
        frame.setSize(520, 300);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(panelCar);
        carBox = new JComboBox<>(cars);
        panelCar.add(carBox);
        carBox.addActionListener(this);
        frame.getContentPane().add(panelTime);
        frame.getContentPane().add(panelInsurance);
        frame.getContentPane().add(btnNext);

        btnNext.addActionListener(this);
        for (int i = 0; i < 8; i++) {
            radioTime[i].addActionListener(this);
        }
        chkInsurance.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            String selectedCar = carBox.getSelectedItem().toString();
            JOptionPane.showMessageDialog(frame, "Car: " + selectedCar + "\nTime: " + time + "\nInsurance: " + insuranceSelected);
        } else if (e.getSource() == chkInsurance) {
            insuranceSelected = chkInsurance.isSelected();
        } else {
            for (int i = 0; i < 8; i++) {
                if (e.getSource() == radioTime[i]) {
                    time = radioTime[i].getText();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        new booking();
    }
}