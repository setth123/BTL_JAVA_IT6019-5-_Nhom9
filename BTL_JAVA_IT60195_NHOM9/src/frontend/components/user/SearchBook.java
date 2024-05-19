package frontend.components.user;

import backend.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Java\\BTL_Java_N9\\BTL_JAVA_IT6019-5-_Nhom9\\BTL_JAVA_IT60195_NHOM9\\src\\frontend\\DemoDB\\Book.txt"))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String title = parts[2].trim();
                    String publisher = parts[3].trim();
                    String releaseDate = parts[4].trim();
                    String category = parts[5].trim();
                    int quantity = Integer.parseInt(parts[6].trim());
                    double price = Double.parseDouble(parts[7].trim());
                    if (name.toLowerCase().contains(keyword.toLowerCase()) || code.toLowerCase().contains(keyword.toLowerCase())) {
                        books.add(new Book(code, name, title, publisher, LocalDate.parse(releaseDate), category, quantity, price));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        private JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;
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
                // Show book details when button is clicked
                int id = (int) table.getValueAt(row, 0);
                String code = (String) table.getValueAt(row, 1);
                String name = (String) table.getValueAt(row, 2);
                String category = (String) table.getValueAt(row, 3);
                Book book = getBookDetails(id, code, name, category);
                showBookDetails(book, table);
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

        private Book getBookDetails(int id, String code, String name, String category) {
            List<Book> books = searchBooks("");
            for (Book book : books) {
                if (books.indexOf(book) == id) {
                    return book;
                }
            }
            return null;
        }

        private void showBookDetails(Book book, JTable searchTable) {
            JFrame detailFrame = new JFrame("Chi tiết sách");
            detailFrame.setSize(400, 300);
            detailFrame.setLayout(new GridLayout(10, 1));

            JLabel lblCode = new JLabel("Mã sách: " + book.getMaSach());
            JLabel lblName = new JLabel("Tên sách: " + book.getTenSach());
            JLabel lblTitle = new JLabel("Tiêu đề: " + book.getTieuDe());
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
            detailFrame.add(lblTitle);
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
            detailedFrame.setLayout(new GridLayout(4, 1));

            JLabel lblCode = new JLabel("Mã sách: " + book.getMaSach());
            JLabel lblName = new JLabel("Tên sách: " + book.getTenSach());
            JLabel lblCategory = new JLabel("Thể loại: " + book.getTheLoai());

            JButton btnBack = new JButton("Quay lại");

            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    detailedFrame.dispose();
                }
            });

            detailedFrame.add(lblCode);
            detailedFrame.add(lblName);
            detailedFrame.add(lblCategory);
            detailedFrame.add(btnBack);

            detailedFrame.setLocationRelativeTo(null);
            detailedFrame.setVisible(true);
        }
    }
}
