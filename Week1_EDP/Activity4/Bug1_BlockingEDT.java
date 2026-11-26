import java.awt.*;
import javax.swing.*;

public class Bug1_BlockingEDT extends JFrame {

    private JButton button;
    private JLabel label;

    public Bug1_BlockingEDT() {
        setTitle("Bug 1 - Fixed EDT");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        button = new JButton("Start Task");
        label = new JLabel("Ready");

        button.addActionListener(e -> {
            button.setEnabled(false);
            label.setText("Working...");

            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                SwingUtilities.invokeLater(() -> {
                    label.setText("Task Complete!");
                    button.setEnabled(true);
                });
            }).start();
        });

        add(button);
        add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT().setVisible(true);
        });
    }
}