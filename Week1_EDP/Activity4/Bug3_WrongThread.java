import java.awt.*;
import javax.swing.*;

public class Bug3_WrongThread extends JFrame {

    private JLabel label;

    public Bug3_WrongThread() {
        setTitle("Bug 3 - Fixed Thread");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Waiting...");
        add(label);

        Thread worker = new Thread(() -> {
            SwingUtilities.invokeLater(() -> {
                label.setText("Updated safely on the EDT!");
            });
        });

        worker.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug3_WrongThread().setVisible(true);
        });
    }
}