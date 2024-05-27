package frontend.components.user;

import backend.controllers.UserController;
import backend.models.Account;
import backend.utils.SessionManager;

import javax.swing.*;
import java.awt.*;

public class UserLogin extends JFrame {
    public UserLogin(JFrame parent) {

        // Set window title
        setTitle("Độc giả đăng nhập");
        setResizable(false);

        // Create and configure components
        JLabel headerLabel = new JLabel("Đăng nhập");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerLabel.setForeground(Color.gray);

        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Đăng nhập");
        JButton backButton = new JButton("Quay lại");

        // Create a panel for the back button and align it to the left
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);

        // Create a panel for the main login form
        JPanel loginPanel = new JPanel();
        GroupLayout layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(headerLabel)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameLabel)
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameField)
                                .addComponent(passwordField)))
                .addComponent(loginButton));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameLabel)
                        .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField))
                .addComponent(loginButton));

        // Set the layout for the main content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(backButtonPanel, BorderLayout.NORTH);
        getContentPane().add(loginPanel, BorderLayout.CENTER);

        // Add action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            Account loggedIn = UserController.login(username, password);
            if (loggedIn != null) {
                SessionManager.login(loggedIn);

                // Open the main dashboard window
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);

                // Close the login window
                dispose();
            } else {
                // Display error message or handle unsuccessful login
                JOptionPane.showMessageDialog(this, "Đăng nhập không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for back button
        backButton.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });

        // Set window size, close operation, and visibility
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
