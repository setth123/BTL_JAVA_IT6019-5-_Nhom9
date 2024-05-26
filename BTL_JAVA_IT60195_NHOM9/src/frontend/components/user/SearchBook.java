package frontend.components.user;

import backend.models.Account;
import backend.models.Book;
import backend.models.BorrowSlip;
import backend.utils.ReadData;
import backend.utils.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchBook {
    public static void showSearchBookLayout(JFrame parentFrame) {

        // Tạo một JFrame cho layout tìm kiếm sách
        final JFrame searchFrame = new JFrame("Tìm kiếm sách");
        searchFrame.setSize(800, 600);
        searchFrame.setResizable(false);
        searchFrame.setLayout(new BorderLayout());

        // Tạo các thành phần UI cho layout tìm kiếm sách
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 16)); // Set larger font for label
        JTextField txtSearch = new JTextField(30);
        JButton btnSearch = new JButton("Tìm kiếm");
        JButton btnBack = new JButton("Quay lại");

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnBack);

        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Bảng để hiển thị kết quả tìm kiếm
        String[] columnNames = {"STT", "Mã sách", "Tên sách", "Thể loại", "Thao tác"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        List<Book> allBooks = searchBooks("");
        if (allBooks.isEmpty()) {
            tableModel.setRowCount(0); // Clear existing rows
            tableModel.addRow(new Object[]{"", "", "No books found", "", ""});
        } else {
            updateTable(allBooks, tableModel);
        }
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only the button column is editable
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 4) {
                    return new ButtonRenderer();
                }
                return super.getCellRenderer(row, column);
            }
        };

        // Add button to "Thao tác" column
        table.getColumn("Thao tác").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        // Adjust table column widths
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Mã sách
        table.getColumnModel().getColumn(2).setPreferredWidth(300); // Tên sách
        table.getColumnModel().getColumn(3).setPreferredWidth(200); // Thể loại
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Thao tác

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Set preferred size for the scroll pane

        // Thêm các thành phần UI vào JFrame
        searchFrame.add(topPanel, BorderLayout.NORTH);
        searchFrame.add(scrollPane, BorderLayout.CENTER);

        // Xử lý sự kiện khi nhấn vào nút "Tìm kiếm"
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = txtSearch.getText().trim();
                if (keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(searchFrame, "Vui lòng nhập từ khóa tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<Book> books = searchBooks(keyword);
                    if (books.isEmpty()) {
                        tableModel.setRowCount(0); // Clear existing rows
                        tableModel.addRow(new Object[]{"", "", "No books found", "", ""});
                    } else {
                        updateTable(books, tableModel);
                    }
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào nút "Quay lại"
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFrame.dispose();
                parentFrame.setVisible(true);
            }
        });

        // Đảm bảo JFrame hiển thị ở trung tâm màn hình
        searchFrame.setLocationRelativeTo(null);
        parentFrame.setVisible(false);

        // Hiển thị JFrame tìm kiếm sách
        searchFrame.setVisible(true);
    }

    private static List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ReadData.f_path("src\\backend\\DemoDB\\Book.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String releaseDate = parts[3].trim();
                    String category = parts[4].trim();
                    int quantity = Integer.parseInt(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());

                    if (name.toLowerCase().contains(keyword.toLowerCase()) || code.toLowerCase().contains(keyword.toLowerCase())) {
                        books.add(new Book(code, name, author, LocalDate.parse(releaseDate), category, quantity, price));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return books;
    }

    private static void updateTable(List<Book> books, DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Book book : books) {
            tableModel.addRow(new Object[]{books.indexOf(book) + 1, book.getMaSach(), book.getTenSach(), book.getTheLoai(), "Xem"});
        }
    }

    // Button renderer class
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Xem" : value.toString());
            return this;
        }
    }

    // Button editor class
    static class ButtonEditor extends DefaultCellEditor {
        private final JButton button;
        private String label;
        private boolean isPushed;
        private final JTable table;
        private int row;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Xem" : value.toString();
            button.setText(label);
            this.row = row;
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Lấy thông tin sách từ bảng
                String code = (String) table.getValueAt(row, 1);

                Book book = findBookByCode(code);
                if (book != null) {
                    showBookDetails(book);
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        private Book findBookByCode(String code) {
            List<Book> books = searchBooks(""); // Load lại toàn bộ danh sách sách
            for (Book book : books) {
                if (book.getMaSach().equals(code)) {
                    return book;
                }
            }
            return null;
        }

        private void showBookDetails(Book book) {
            JFrame detailFrame = new JFrame("Chi tiết sách");
            detailFrame.setSize(400, 300);
            detailFrame.setLayout(new GridLayout(10, 1));

            JLabel lblCode = new JLabel("Mã sách: " + book.getMaSach());
            JLabel lblName = new JLabel("Tên sách: " + book.getTenSach());
            JLabel lblPublisher = new JLabel("NXB: " + book.getNXB());
            JLabel lblReleaseDate = new JLabel("Ngày phát hành: " + book.getNph());
            JLabel lblCategory = new JLabel("Thể loại: " + book.getTheLoai());
            JLabel lblQuantity = new JLabel("Số lượng: " + book.getSl());
            JLabel lblPrice = new JLabel("Giá: " + book.getGia());

            JButton btnBorrowBook = new JButton("Đặt mượn sách");
            JButton btnBack = new JButton("Quay lại");

            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    detailFrame.dispose();
                }
            });

            btnBorrowBook.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showDetailedBookInfo(book);
                }
            });

            detailFrame.add(lblCode);
            detailFrame.add(lblName);
            detailFrame.add(lblPublisher);
            detailFrame.add(lblReleaseDate);
            detailFrame.add(lblCategory);
            detailFrame.add(lblQuantity);
            detailFrame.add(lblPrice);
            detailFrame.add(btnBorrowBook);
            detailFrame.add(btnBack);

            detailFrame.setLocationRelativeTo(null);
            detailFrame.setVisible(true);
        }

        private void showDetailedBookInfo(Book book) {
            JFrame detailedFrame = new JFrame("Thông tin chi tiết sách");
            detailedFrame.setSize(400, 300);
            detailedFrame.setLayout(new GridLayout(5, 1));

            JLabel lblCode = new JLabel("Mã sách: " + book.getMaSach());
            JLabel lblName = new JLabel("Tên sách: " + book.getTenSach());
            JLabel lblCategory = new JLabel("Thể loại: " + book.getTheLoai());
            JButton btnBack = new JButton("Quay lại");
            JButton btnConfirm = new JButton("Xác nhận");

            btnConfirm.setEnabled(book.getSl() > 0);

            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    detailedFrame.dispose();
                }
            });

            btnConfirm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    book.reduceQuantity(1);

                    // Write the borrow slip and get the generated ID
                    writeBorrowSlip(book);

                    // Update the Book.txt file to reflect the new quantity
                    updateBookFile(book);

                    // Display a confirmation dialog
                    JOptionPane.showMessageDialog(detailedFrame, "Chờ xác nhận từ thủ thư", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    detailedFrame.dispose();
                }
            });

            detailedFrame.add(lblCode);
            detailedFrame.add(lblName);
            detailedFrame.add(lblCategory);
            detailedFrame.add(btnConfirm);
            detailedFrame.add(btnBack);

            detailedFrame.setLocationRelativeTo(null);
            detailedFrame.setVisible(true);
        }



        private void updateBookFile(Book updatedBook) {
            String filePath = ReadData.f_path("src\\backend\\DemoDB\\Book.txt");
            List<String> fileContent = new ArrayList<>();

            // Đọc toàn bộ nội dung của tệp vào danh sách
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
                return;
            }

            // Tìm và cập nhật thông tin sách
            for (int i = 0; i < fileContent.size(); i++) {
                String tempLine = fileContent.get(i);
                String line = tempLine.substring(1, tempLine.length() - 1);
                if (line.contains(updatedBook.getMaSach())) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 8) {
                        int quantity = Integer.parseInt(parts[5].trim());
                        parts[5] = " " + (quantity - 1) + " "; // Cập nhật số lượng
                        StringBuilder updatedLine = new StringBuilder("|");
                        for (String part : parts) {
                            updatedLine.append(part).append("|");
                        }
                        fileContent.set(i, updatedLine.toString());
                    }
                }
            }

            // Ghi lại nội dung đã cập nhật vào tệp
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : fileContent) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }

        public static void writeBorrowSlip(Book book) {
            String maPhieuMuon = generateBorrowSlipId();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ReadData.f_path("src\\backend\\DemoDB\\borrow-slip.txt"), true))) {
                LocalDate ngayMuon = LocalDate.now();

                // Use SessionManager to get the current user
                Account currentUser = SessionManager.getCurrentUser();
                String maTaiKhoan = currentUser != null ? currentUser.getMaTaiKhoan() : "N/A";

                BorrowSlip borrowSlip = new BorrowSlip(maPhieuMuon, ngayMuon, maTaiKhoan, book, false); // Assume newly created slip is inactive
                // Write the borrow slip to file
                writer.write(borrowSlip.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }


        private void writeBorrowSlipDetail(Book book, String maPhieuMuon) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("borrow-slip-detail.txt", true))) {
                String ngayTraDuKien = calculateDueDate(); // Implement this method to calculate the due date
                String ngayTraThucTe = "N/A"; // This will be empty initially as the book hasn't been returned yet
                int soLuong = 1;

                String borrowSlipDetail = String.format("| %-10s | %-30s | %-20s | %-10s | %-12s |",
                        book.getMaSach(), maPhieuMuon, ngayTraDuKien, ngayTraThucTe, soLuong);
                writer.write(borrowSlipDetail);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }


        // Sample method to generate a borrow slip ID
        private static String generateBorrowSlipId() {
            // Implement ID generation logic
            return "BS" + System.currentTimeMillis();
        }

        // Sample method to calculate the due date
        private String calculateDueDate() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 14); // Assuming a 14-day borrow period
            return new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        }
    }

}

