package frontend.components.user;

import backend.models.Account;
import backend.utils.SessionManager;

import javax.swing.*;
import java.awt.*;

public class PersonalInfoView extends JFrame {

    public PersonalInfoView(JFrame parent) {
        // Set window title
        setTitle("Thông tin cá nhân");

        // Get current user details
        Account currentUser = SessionManager.getCurrentUser();

        // Create and configure components
        JLabel headerLabel = new JLabel("Thông tin cá nhân");
        headerLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.gray);

        JLabel nameLabel = new JLabel("Tên tài khoản: ");
        JLabel nameValue = new JLabel(currentUser.getTenDangNhap());
        JLabel addressLabel = new JLabel("Địa chỉ: ");
        JLabel addressValue = new JLabel(currentUser.getDiaChi());
        JLabel phoneNumberLabel = new JLabel("Số điện thoại: ");
        JLabel phoneNumberValue = new JLabel(currentUser.getSoDienThoai());
        JLabel fullNameLabel = new JLabel("Tên người dùng: ");
        JLabel fullNameValue = new JLabel(currentUser.getTenNguoiDung());

        // Add other personal information fields as needed

        JButton editButton = new JButton("Chỉnh sửa thông tin cá nhân");
        JButton changePasswordButton = new JButton("Đổi mật khẩu");
        JButton backButton = new JButton("Quay lại");

        // Set button actions
        editButton.addActionListener(e -> {
            // Open EditPersonalInfo window
            new EditPersonalInfo(this, currentUser);
            dispose();
        });

        changePasswordButton.addActionListener(e -> {
            // Open ChangePassword window
            new ChangePassword(this, currentUser);
            dispose();
        });

        backButton.addActionListener(e -> {
            // Return to dashboard
            parent.setVisible(true);
            dispose();
        });

        // Configure layout using GroupLayout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(backButton)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(fullNameLabel)
                                .addComponent(addressLabel)
                                .addComponent(phoneNumberLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameValue)
                                .addComponent(fullNameValue)
                                .addComponent(addressValue)
                                .addComponent(phoneNumberValue)))
                .addComponent(headerLabel, GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addGap(100, 100, 100) // Add space between buttons
                        .addComponent(changePasswordButton))
                .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addComponent(changePasswordButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(backButton)
                .addComponent(headerLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fullNameLabel)
                        .addComponent(fullNameValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressLabel)
                        .addComponent(addressValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneNumberLabel)
                        .addComponent(phoneNumberValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(editButton)
                        .addComponent(changePasswordButton)));

        // Set window properties
        setSize(450, 260);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setVisible(true);
    }
}
