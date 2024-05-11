package frontend.components.user;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Dashboard extends JFrame {
    public Dashboard() {
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame
            JFrame frame = new JFrame("Hello World");

            // Create a JLabel with "Hello World" text
            JLabel label = new JLabel("Hello World");

            // Add the label to the frame
            frame.getContentPane().add(label);

            // Set frame size and behavior
            frame.setSize(200, 100);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}

