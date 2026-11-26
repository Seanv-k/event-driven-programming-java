import javax.swing.*;

public class FirstWindow {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            createAndShowWindow();
        });
    }

    private static void createAndShowWindow() {

        // 1. Create the JFrame
        JFrame frame = new JFrame("Sean's First Swing App");

        // 2. Close the program when X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Set window size
        frame.setSize(500, 400);

        // 4. Center the window
        frame.setLocationRelativeTo(null);

        // 5. Display the window
        frame.setVisible(true);

        // 6. Check which thread is running
        System.out.println("Window created on thread: "
                + Thread.currentThread().getName());
    }
}
