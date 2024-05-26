package frontend.components.user;

import backend.models.Account;
import backend.models.Book;
import backend.models.BorrowSlip;
import backend.utils.ReadData;
import backend.utils.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowBook extends JFrame {

    private JTable borrowedBooksTable;
    private JTable waitingBooksTable;
    private DefaultTableModel borrowedTableModel;
    private DefaultTableModel waitingTableModel;
    private JFrame parentFrame;

    public BorrowBook(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setTitle("Xem lịch sử mượn sách");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the frame fullscreen
        setLayout(new BorderLayout());

        // Create the table models and tables
        String[] borrowedColumnNames = {"STT", "Tên sách", "Ngày mượn", "Ngày trả dự kiến"};
        borrowedTableModel = new DefaultTableModel(borrowedColumnNames, 0);
        borrowedBooksTable = new JTable(borrowedTableModel);
        JScrollPane borrowedScrollPane = new JScrollPane(borrowedBooksTable);

        String[] waitingColumnNames = {"STT", "Tên sách", "Ngày mượn"};
        waitingTableModel = new DefaultTableModel(waitingColumnNames, 0);
        waitingBooksTable = new JTable(waitingTableModel);
        JScrollPane waitingScrollPane = new JScrollPane(waitingBooksTable);

        // Tạo tiêu đề cho mỗi bảng
        JLabel borrowedHeader = new JLabel("Sách đã mượn");
        borrowedHeader.setFont(new Font("Arial", Font.PLAIN, 24));
        borrowedHeader.setSize(100, 50);
        borrowedHeader.setForeground(Color.GRAY);
        borrowedHeader.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel waitingHeader = new JLabel("Sách chờ phê duyệt");
        waitingHeader.setFont(new Font("Arial", Font.PLAIN, 24));
        waitingHeader.setSize(100, 50);
        waitingHeader.setForeground(Color.GRAY);
        waitingHeader.setHorizontalAlignment(SwingConstants.CENTER);

        // Căn tiêu đề cho mỗi bảng
        JPanel borrowedPanel = new JPanel(new BorderLayout());
        borrowedPanel.add(borrowedHeader, BorderLayout.NORTH);
        borrowedPanel.add(borrowedScrollPane, BorderLayout.CENTER);

        JPanel waitingPanel = new JPanel(new BorderLayout());
        waitingPanel.add(waitingHeader, BorderLayout.NORTH);
        waitingPanel.add(waitingScrollPane, BorderLayout.CENTER);

        // Tạo một ngăn chia để giữ hai bảng cạnh nhau
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, borrowedPanel, waitingPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        add(splitPane, BorderLayout.CENTER);


        // Tạo nút "Quay lại "
        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parentFrame.setVisible(true);
            }
        });

        //Thêm nút "Quay lại" ở cuối cửa sổ
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);

        // Căn giữa cửa sổ và hiển thị
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Tải dữ liệu cho cả hai bảng
        loadBorrowedBooks();
        loadWaitingBooks();
    }

    public void loadBorrowedBooks() {
        String userId = SessionManager.getCurrentUser().getMaTaiKhoan();
        java.util.List<BorrowSlip> borrowedBooks = getBorrowedBooks(userId);
        updateBorrowedTable(borrowedBooks);
    }

    public static java.util.List<BorrowSlip> getBorrowedBooks(String userId) {
        List<backend.models.BorrowSlip> borrowSlips = new ArrayList<>();
        try (BufferedReader brSlip = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\borrow-slip.txt")))) {
            String line;
            while ((line = brSlip.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 6 && parts[3].trim().equals(userId) && "Active".equals(parts[4].trim())) {
                    String maPhieuMuon = parts[0].trim();
                    String ngayMuon = parts[1].trim();
                    String maNguoiDung = parts[3].trim();
                    String maSach = parts[5].trim();

                    // Assuming you have a method to get the Book object by its name
                    Book book = getBookByCode(maSach);
                    if (book != null) {
                        borrowSlips.add(new BorrowSlip(maPhieuMuon, LocalDate.parse(ngayMuon), maNguoiDung, book, true));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return borrowSlips;
    }

    private void updateBorrowedTable(List<BorrowSlip> borrowedBooks) {
        borrowedTableModel.setRowCount(0);
        for (int i = 0; i < borrowedBooks.size(); i++) {
            BorrowSlip borrowSlip = borrowedBooks.get(i);
            borrowedTableModel.addRow(new Object[]{i + 1, borrowSlip.getSachMuon().getTenSach(), borrowSlip.getNgayMuon(), borrowSlip.getNgayTra()});
        }
    }

    private void loadWaitingBooks() {
        List<WaitingBook> waitingBooks = getWaitingBooks();
        updateWaitingTable(waitingBooks);
    }

    private List<WaitingBook> getWaitingBooks() {
        Account a = SessionManager.getCurrentUser();
        List<WaitingBook> waitingBooks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\borrow-slip.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    line = line.substring(1, line.length() - 1);
                    String[] parts = line.split("\\|");
                    if (parts.length >= 6 && "Inactive".equals(parts[4].trim()) && a.getMaTaiKhoan().equals(parts[3].trim())) {
//                    String maSach = parts[0].trim();
                        String ngayMuon = parts[1].trim();
                        String ngayTra = parts[2].trim();
                        String tenSach = parts[5].trim();
                        waitingBooks.add(new WaitingBook(tenSach, ngayMuon, ngayTra));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return waitingBooks;
    }

    private void updateWaitingTable(List<WaitingBook> waitingBooks) {
        waitingTableModel.setRowCount(0);
        for (int i = 0; i < waitingBooks.size(); i++) {
            WaitingBook waitingBook = waitingBooks.get(i);
            waitingTableModel.addRow(new Object[]{i + 1, waitingBook.getBookTitle(), waitingBook.getBorrowDate()});
        }
    }

    private static Book getBookByCode(String bookCode) {
        try (BufferedReader br = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\Book.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8 && parts[0].trim().equals(bookCode)) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String releaseDate = parts[3].trim();
                    String category = parts[4].trim();
                    int quantity = Integer.parseInt(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());
                    return new Book(code, name, author, LocalDate.parse(releaseDate), category, quantity, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private class WaitingBook {
        private String bookTitle;
        private String borrowDate;
        private String expectedReturnDate;

        public WaitingBook(String bookTitle, String borrowDate, String expectedReturnDate) {
            this.bookTitle = bookTitle;
            this.borrowDate = borrowDate;
            this.expectedReturnDate = expectedReturnDate;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public String getExpectedReturnDate() {
            return expectedReturnDate;
        }
    }
}
