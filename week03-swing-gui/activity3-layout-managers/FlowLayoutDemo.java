import java.awt.*;
import javax.swing.*;

public class FlowLayoutDemo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("FlowLayout Demo");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(500, 300);

            frame.setLocationRelativeTo(null);

            // Arrange buttons from left to right
            frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            for (int i = 1; i <= 12; i++) {
                frame.add(new JButton("Button " + i));
            }

            frame.setVisible(true);

        });

    }
}