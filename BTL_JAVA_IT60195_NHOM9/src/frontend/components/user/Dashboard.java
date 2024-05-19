package frontend.components.user;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        SwingUtilities.invokeLater(() -> {

            // Create the buttons
            JButton btnSearchBook = new JButton("Tìm kiếm sách");
            JButton btnBorrowBook = new JButton("Đặt mượn sách");
            JButton btnRenewBook = new JButton("Gia hạn sách đã mượn");
            JButton btnViewHistory = new JButton("Xem lịch sử mượn sách/vi phạm");
            JButton btnViewPersonalInfo = new JButton("Xem thông tin cá nhân");
            JButton btnLogout = new JButton("Đăng xuất");

            // Set button actions
            btnSearchBook.addActionListener(e -> handleSearchBook());
            btnBorrowBook.addActionListener(e -> handleBorrowBook());
            btnRenewBook.addActionListener(e -> handleRenewBook());
            btnViewHistory.addActionListener(e -> handleViewHistory());
            btnViewPersonalInfo.addActionListener(e -> handleViewPersonalInfo());
            btnLogout.addActionListener(e -> handleLogout());

            // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.add(btnSearchBook);
            buttonPanel.add(btnBorrowBook);
            buttonPanel.add(btnRenewBook);
            buttonPanel.add(btnViewHistory);
            buttonPanel.add(btnViewPersonalInfo);
            buttonPanel.add(btnLogout);

            // Create the main frame and add the panel to it
            this.setTitle("Library Menu");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(400, 300);
            this.setLayout(new BorderLayout());
            this.add(buttonPanel, BorderLayout.WEST);
            this.setVisible(true);
        });
    }
    // Placeholder methods for button actions
    private void handleSearchBook () { SearchBook.showSearchBookLayout(this) ;}

    private void handleBorrowBook () {
        System.out.println("Đặt mượn sách được chọn");
    }

    private void handleRenewBook () {
        System.out.println("Gia hạn sách đã mượn được chọn");
    }

    private void handleViewHistory () {
        System.out.println("Xem lịch sử mượn sách/vi phạm được chọn");
    }

    private void handleViewPersonalInfo () {
        System.out.println("Xem thông tin cá nhân được chọn");
    }

    private void handleLogout () {
        System.out.println("Đăng xuất được chọn");
    }
}



