package frontend.components.user;

import backend.models.Account;
import backend.utils.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangePassword extends JFrame {

    public ChangePassword(JFrame parent, Account currentUser) {
        // Set window title
        setTitle("Đổi mật khẩu");

        // Create and configure components
        JLabel headerLabel = new JLabel("Đổi mật khẩu");
        headerLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.gray);

        JLabel currentPasswordLabel = new JLabel("Mật khẩu hiện tại:");
        JPasswordField currentPasswordField = new JPasswordField(20);

        JLabel newPasswordLabel = new JLabel("Mật khẩu mới:");
        JPasswordField newPasswordField = new JPasswordField(20);

        JLabel lblPasswordHint = new JLabel("Mật khẩu phải có ít nhất 5 ký tự gồm 1 chữ in hoa, 1 chữ số");
        lblPasswordHint.setForeground(Color.GRAY);

        JLabel confirmPasswordLabel = new JLabel("Xác nhận mật khẩu:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton saveButton = new JButton("Lưu");
        JButton backButton = new JButton("Quay lại");

        // Password validation method
        boolean[] isValid = {false};
        newPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String newPassword = new String(newPasswordField.getPassword());
                isValid[0] = validatePassword(newPassword);
                if (isValid[0]) {
                    lblPasswordHint.setForeground(Color.GRAY);
                } else {
                    lblPasswordHint.setForeground(Color.RED);
                }
            }
        });

        // Set button actions
        saveButton.addActionListener(e -> {
            // Validate and save the new password
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không khớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValid[0]) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới không đáp ứng yêu cầu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate current password (Assuming validateCurrentPassword is a method that validates the password)
            if (!currentUser.validateCurrentPassword(currentPassword)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the password
            currentUser.setMatKhau(newPassword); // Assuming Account has a setPassword method
            SessionManager.login(currentUser); // Update the session

            // Show success message
            JOptionPane.showMessageDialog(this, "Mật khẩu đã được cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Return to edit personal info view
            new EditPersonalInfo(parent, currentUser);
            dispose();
        });

        backButton.addActionListener(e -> {
            // Return to edit personal info view
            new Dashboard();
            dispose();
        });

        // Configure layout using GroupLayout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(headerLabel)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(currentPasswordLabel)
                        .addComponent(currentPasswordField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(newPasswordLabel)
                        .addComponent(newPasswordField))
                .addComponent(lblPasswordHint)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmPasswordLabel)
                        .addComponent(confirmPasswordField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addComponent(backButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(currentPasswordLabel)
                        .addComponent(currentPasswordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(newPasswordLabel)
                        .addComponent(newPasswordField))
                .addComponent(lblPasswordHint)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(confirmPasswordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(backButton)));

        // Set window properties
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setVisible(true);
    }

    private boolean validatePassword(String password) {
        if (password.length() < 5) return false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        return hasUpperCase && hasDigit;
    }
}
