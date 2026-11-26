import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Bug4_MemoryLeak extends JFrame {

    private JPanel panel;
    private ArrayList<JButton> createdButtons = new ArrayList<>();

    public Bug4_MemoryLeak() {
        setTitle("Bug 4 - Fixed Memory Leak");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel();
        JButton addButton = new JButton("Add Button");
        JButton removeButton = new JButton("Remove Buttons");

        addButton.addActionListener(e -> {
            JButton newButton = new JButton("Button " + (createdButtons.size() + 1));
            createdButtons.add(newButton);
            panel.add(newButton);
            panel.revalidate();
            panel.repaint();
        });

        removeButton.addActionListener(e -> {
            panel.removeAll();
            createdButtons.clear();
            panel.revalidate();
            panel.repaint();
        });

        add(addButton, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(removeButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug4_MemoryLeak().setVisible(true);
        });
    }
}
