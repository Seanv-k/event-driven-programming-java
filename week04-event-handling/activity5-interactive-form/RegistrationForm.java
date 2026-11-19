import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private ButtonGroup genderGroup;

    private JCheckBox sportsCheck;
    private JCheckBox musicCheck;
    private JCheckBox readingCheck;

    private JComboBox<String> countryBox;
    private JTextArea commentsArea;

    private JLabel statusLabel;

    public RegistrationForm() {

        setTitle("Registration Form");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(titleLabel);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Full Name:"));
        nameField = new JTextField(30);
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(30);
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(30);
        panel.add(passwordField);

        panel.add(new JLabel("Gender:"));

        JPanel genderPanel = new JPanel();

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        panel.add(genderPanel);

        panel.add(new JLabel("Interests:"));

        JPanel interestsPanel = new JPanel();

        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");

        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);

        panel.add(interestsPanel);

        panel.add(new JLabel("Country:"));

        String[] countries = {
            "Kenya", "Tanzania", "Uganda", "Rwanda"
        };

        countryBox = new JComboBox<>(countries);
        panel.add(countryBox);

        panel.add(new JLabel("Comments:"));

        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);

        panel.add(new JScrollPane(commentsArea));

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        submitButton.setBackground(Color.GREEN);
        exitButton.setBackground(Color.RED);

        panel.add(submitButton);
        panel.add(clearButton);
        panel.add(exitButton);

        statusLabel = new JLabel("Status: Ready");
        panel.add(statusLabel);

        submitButton.addActionListener(e -> submitForm());
        clearButton.addActionListener(e -> clearForm());
        exitButton.addActionListener(e -> exitApplication());

        add(panel);

        setVisible(true);
    }

    private void submitForm() {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty()) {
            statusLabel.setText("Error: Enter your name");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Enter a valid email");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (password.length() < 6) {
            statusLabel.setText("Error: Password needs 6 characters");
            statusLabel.setForeground(Color.RED);
            return;
        }

        String gender = "Not specified";

        if (maleRadio.isSelected()) {
            gender = "Male";
        } else if (femaleRadio.isSelected()) {
            gender = "Female";
        } else if (otherRadio.isSelected()) {
            gender = "Other";
        }

        StringBuilder interests = new StringBuilder();

        if (sportsCheck.isSelected()) {
            interests.append("Sports ");
        }

        if (musicCheck.isSelected()) {
            interests.append("Music ");
        }

        if (readingCheck.isSelected()) {
            interests.append("Reading ");
        }

        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        String message = "Registration Successful!\n\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Gender: " + gender + "\n"
                + "Interests: " + interests + "\n"
                + "Country: " + country + "\n"
                + "Comments: " + comments;

        JOptionPane.showMessageDialog(
                this,
                message,
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    private void clearForm() {

        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");

        genderGroup.clearSelection();

        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);

        countryBox.setSelectedIndex(0);
        commentsArea.setText("");

        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    private void exitApplication() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
}