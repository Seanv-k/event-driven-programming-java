import java.awt.*;
import javax.swing.*;

public class SmartHomeController extends JFrame {

    private JLabel lightStatus;
    private JLabel fanStatus;
    private JLabel doorStatus;
    private JLabel temperatureStatus;

    private boolean lightOn = false;
    private boolean fanOn = false;
    private boolean doorLocked = true;

    public SmartHomeController() {

        setTitle("Smart Home Controller");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel title = new JLabel("Smart Home Control Panel");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
        add(new JLabel(""));

        // Light control
        JButton lightButton = new JButton("Toggle Light");
        lightStatus = new JLabel("Light: OFF");

        lightButton.addActionListener(e -> {
            lightOn = !lightOn;
            lightStatus.setText(lightOn ? "Light: ON" : "Light: OFF");
        });

        add(lightButton);
        add(lightStatus);

        // Fan control
        JButton fanButton = new JButton("Toggle Fan");
        fanStatus = new JLabel("Fan: OFF");

        fanButton.addActionListener(e -> {
            fanOn = !fanOn;
            fanStatus.setText(fanOn ? "Fan: ON" : "Fan: OFF");
        });

        add(fanButton);
        add(fanStatus);

        // Door control
        JButton doorButton = new JButton("Toggle Door Lock");
        doorStatus = new JLabel("Door: LOCKED");

        doorButton.addActionListener(e -> {
            doorLocked = !doorLocked;
            doorStatus.setText(
                doorLocked ? "Door: LOCKED" : "Door: UNLOCKED"
            );
        });

        add(doorButton);
        add(doorStatus);

        // Temperature control
        JButton temperatureButton = new JButton("Increase Temperature");
        temperatureStatus = new JLabel("Temperature: 20°C");

        final int[] temperature = {20};

        temperatureButton.addActionListener(e -> {
            temperature[0]++;
            temperatureStatus.setText(
                "Temperature: " + temperature[0] + "°C"
            );
        });

        add(temperatureButton);
        add(temperatureStatus);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SmartHomeController().setVisible(true);
        });
    }
}