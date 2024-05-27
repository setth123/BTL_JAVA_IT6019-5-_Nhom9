package frontend.components.user;

import backend.models.Account;
import backend.utils.ReadData;
import backend.utils.SessionManager;
import backend.utils.WriteData;

import javax.swing.*;
import java.awt.*;

public class EditPersonalInfo extends JFrame {

    public EditPersonalInfo(JFrame parent, Account currentUser) {
        // Set window title
        setTitle("Chỉnh sửa thông tin cá nhân");

        // Create and configure components
        JLabel headerLabel = new JLabel("Chỉnh sửa thông tin cá nhân");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.gray);

        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        JLabel fullNameLabel = new JLabel("Tên đầy đủ:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneNumberLabel = new JLabel("Số điện thoại:");

        JTextField usernameField = new JTextField(currentUser.getTenDangNhap(), 20);
        JTextField fullNameField = new JTextField(currentUser.getTenNguoiDung(), 20);
        JTextField emailField = new JTextField(currentUser.getDiaChi(), 20);
        JTextField phoneField = new JTextField(currentUser.getSoDienThoai(), 20);

        JButton saveButton = new JButton("Lưu");
        JButton changePasswordButton = new JButton("Đổi mật khẩu");
        JButton backButton = new JButton("Quay lại");

        // Set button actions
        saveButton.addActionListener(e -> {
            // Save the updated information
            currentUser.setTenDangNhap(usernameField.getText());
            currentUser.setTenNguoiDung(fullNameField.getText());
            currentUser.setDiaChi(emailField.getText());
            currentUser.setSoDienThoai(phoneField.getText());
            // Save other updated information as needed
            // Update the session user details
            SessionManager.login(currentUser);
            java.util.List<Account> accounts = ReadData.readAccount("..\\DemoDB\\user-account.txt");
            for (int i = 0; i < accounts.size(); i++) {
                if (currentUser.getMaTaiKhoan().equals(accounts.get(i).getMaTaiKhoan())) {
                    accounts.set(i, currentUser);
                    break;
                }
            }

            WriteData.writeAccount(accounts, "..\\DemoDB\\user-account.txt");
            // Show success message
            JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Return to personal info view
            new Dashboard();
            dispose();
        });

        changePasswordButton.addActionListener(e -> {
            // Open ChangePassword window
            new ChangePassword(this, currentUser);
            dispose();
        });

        backButton.addActionListener(e -> {
            // Return to personal info view
            new Dashboard();
            dispose();
        });

        // Configure layout using GroupLayout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameLabel)
                                .addComponent(fullNameLabel)
                                .addComponent(emailLabel)
                                .addComponent(phoneNumberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(usernameField)
                                .addComponent(fullNameField)
                                .addComponent(emailField)
                                .addComponent(phoneField)))
                .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(headerLabel))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(changePasswordButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(headerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameLabel)
                        .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fullNameLabel)
                        .addComponent(fullNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneNumberLabel)
                        .addComponent(phoneField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(changePasswordButton)
                        .addComponent(backButton)));

        // Set window properties
        setSize(900, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setVisible(true);
    }
}
