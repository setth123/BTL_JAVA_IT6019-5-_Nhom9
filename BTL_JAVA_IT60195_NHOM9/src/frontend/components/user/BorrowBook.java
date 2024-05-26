package frontend.components.user;

import backend.models.Book;
import backend.utils.ReadData;
import backend.utils.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
        loadBorrowedBooks(SessionManager.getCurrentUser().getTenDangNhap());
        loadWaitingBooks();
    }

    public void loadBorrowedBooks(String userName) {
        List<BorrowedBook> borrowedBooks = getBorrowedBooks(userName);
        updateBorrowedTable(borrowedBooks);
    }

    public static List<BorrowedBook> getBorrowedBooks(String userName) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        try (BufferedReader brSlip = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\borrow-slip.txt")))) {
            String line;
            while ((line = brSlip.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 6 && parts[3].trim().equals(userName) && "Active".equals(parts[4].trim())) {
                    String maPhieuMuon = parts[0].trim();
                    String ngayMuon = parts[1].trim();
                    String ngayTra = parts[2].trim();
                    String tenSach = parts[5].trim();

                    // Assuming you have a method to get the Book object by its name
                    Book book = getBookByTitle(tenSach);
                    if (book != null) {
                        borrowedBooks.add(new BorrowedBook(maPhieuMuon, ngayMuon, ngayTra, book, true));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return borrowedBooks;
    }

    private void updateBorrowedTable(List<BorrowedBook> borrowedBooks) {
        borrowedTableModel.setRowCount(0);
        for (int i = 0; i < borrowedBooks.size(); i++) {
            BorrowedBook borrowedBook = borrowedBooks.get(i);
            borrowedTableModel.addRow(new Object[]{i + 1, borrowedBook.getBookTitle(), borrowedBook.getNgayMuon(), borrowedBook.getNgayTra()});
        }
    }

    private void loadWaitingBooks() {
        List<WaitingBook> waitingBooks = getWaitingBooks();
        updateWaitingTable(waitingBooks);
    }

    private List<WaitingBook> getWaitingBooks() {
        List<WaitingBook> waitingBooks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\borrow-slip.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    line = line.substring(1, line.length() - 1);
                    String[] parts = line.split("\\|");
                    if (parts.length >= 6 && "Inactive".equals(parts[4].trim())) {
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

    private static Book getBookByTitle(String bookTitle) {
        try (BufferedReader br = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\Book.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8 && parts[1].trim().equals(bookTitle)) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String releaseDate = parts[3].trim();
                    String category = parts[4].trim();
                    int quantity = Integer.parseInt(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());
                    return new Book(code, name, author, releaseDate, category, quantity, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static class BorrowedBook {
        private String maPhieuMuon;
        private String ngayMuon;
        private String ngayTra;
        private Book book;
        private boolean trangThai;

        public BorrowedBook(String maPhieuMuon, String ngayMuon, String ngayTra, Book book, boolean trangThai) {
            this.maPhieuMuon = maPhieuMuon;
            this.ngayMuon = ngayMuon;
            this.ngayTra = ngayTra;
            this.book = book;
            this.trangThai = trangThai;
        }

        public String getMaPhieuMuon() {
            return maPhieuMuon;
        }

        public String getNgayMuon() {
            return ngayMuon;
        }

        public Book getBook() {
            return book;
        }

        public boolean isTrangThai() {
            return trangThai;
        }

        public String getBookTitle() {
            return book.getTenSach();
        }

        public String getNgayTra() {
            return ngayTra;
        }

        public void setNgayTra(String ngayTra) {
            this.ngayTra = ngayTra;
        }
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
