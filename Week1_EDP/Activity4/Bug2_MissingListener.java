import java.awt.*;
import javax.swing.*;

public class Bug2_MissingListener extends JFrame {

    private JButton button;
    private JLabel label;

    public Bug2_MissingListener() {
        setTitle("Bug 2 - Fixed Listener");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        button = new JButton("Click Me");
        label = new JLabel("Button not clicked yet");

        button.addActionListener(e -> {
            label.setText("Button clicked successfully!");
        });

        add(button);
        add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug2_MissingListener().setVisible(true);
        });
    }
}