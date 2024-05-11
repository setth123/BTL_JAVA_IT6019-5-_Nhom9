package frontend.components.user;

import backend.controllers.UserController;
import backend.models.Account;

import javax.swing.*;

public class UserLogin extends JFrame {

    public UserLogin(JFrame parent) {
        // Set window title
        setTitle("Độc giả đăng nhập");

        // Create and configure components
        JLabel headerLabel = new JLabel("Hãy đăng nhập để tiếp tục");
        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Đăng nhập");
        JButton backButton = new JButton("Quay lại");

        // Configure layout using GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
                .addComponent(loginButton)
                .addComponent(backButton));


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameLabel)
                        .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField))
                .addComponent(loginButton)
                .addComponent(backButton));

        // Add action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            // Assuming UserController.login returns a boolean indicating successful login
            Account loggedIn = UserController.login(username, password);
            if (loggedIn != null) {
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
            // Show the main window
            parent.setVisible(true);
            // Close the login window
            dispose();
        });

        // Set window size, close operation, and visibility
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}
