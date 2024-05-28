package frontend.components.librarian;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import backend.controllers.LibrarianController;
import backend.models.BorrowSlip;
import backend.utils.FetchBE;
import frontend.utils.ImageProcess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorrowApprove extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable pm;

    public static void fetchBorrowSlipAdmin(DefaultTableModel m) {
        m.setRowCount(0);
        List<BorrowSlip> result = FetchBE.findBSbyStatus("Pending");
        for (BorrowSlip bs : result) {
            Object[] row = {bs.getMaPhieuMuon(), bs.getNgayMuon(),bs.getNgayTra(), bs.getMaTaiKhoan(), bs.getMaSach(), "Pending"};
            m.addRow(row);
        }
    }

    public BorrowApprove(JFrame parent) {
        Initialize(parent);
    }

    private void Initialize(JFrame parent) {
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setBounds(0, 0, r.width, r.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);
        setTitle("Phê duyệt mượn sách");

        JLabel Title = new JLabel("DANH SÁCH PHIẾU MƯỢN ĐANG CHỜ");
        Title.setForeground(new Color(128, 128, 128));
        Title.setFont(new Font("Tahoma", Font.BOLD, 25));
        Title.setBounds(473, 65, 490, 31);
        getContentPane().add(Title);

        JButton ql = new JButton("Quay lại");
        ql.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		parent.setVisible(true);
        		setVisible(false);
        		dispose();
        	}
        });
        ql.setBounds(42, 37, 89, 23);
        getContentPane().add(ql);

        String[] column = {"Mã phiếu mượn", "Ngày mượn","Ngày trả","Mã tài khoản", "Mã sách mượn", "Phê duyệt"};

        DefaultTableModel model = new DefaultTableModel(column, 0);
        fetchBorrowSlipAdmin(model);
        pm = new JTable(model);
        pm.setRowHeight(40);
        pm.getColumnModel().getColumn(0).setPreferredWidth(27); // Column1 width
        pm.getColumnModel().getColumn(1).setPreferredWidth(30);
        pm.getColumnModel().getColumn(2).setPreferredWidth(27); // Column1 width
        pm.getColumnModel().getColumn(3).setPreferredWidth(30);
        pm.getColumnModel().getColumn(4).setPreferredWidth(30);
        pm.getColumn("Phê duyệt").setCellRenderer(new ButtonRenderer());
        pm.getColumn("Phê duyệt").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane sp = new JScrollPane(pm);
        sp.setBounds(10, 132, 1319, 521);
        getContentPane().add(sp);
        
        JButton ref = new JButton("");
        ref.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		fetchBorrowSlipAdmin(model);
        	}
        });
        ImageIcon reload=ImageProcess.scaled("Assets/reload.png",23,23);
        ref.setIcon(reload);
        ref.setBounds(1238, 37, 23, 23);
        
        getContentPane().add(ref);
    }

    // Renderer cho cột chứa các nút bấm
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JButton approveButton;
        private final JButton rejectButton;

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            approveButton = new JButton("Chấp thuận");
            rejectButton = new JButton("Không chấp thuận");
            add(approveButton);
            add(rejectButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int currentRow;
		private final JPanel panel;
        private final JButton approveButton;
        private final JButton rejectButton;

        public ButtonEditor(JCheckBox checkBox) {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            approveButton = new JButton("Chấp thuận");            
            rejectButton = new JButton("Không chấp thuận");
            
            approveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int opt=JOptionPane.showConfirmDialog(BorrowApprove.this,"Xác nhận đồng ý");
                    if(opt==JOptionPane.YES_OPTION) {
                    	if(LibrarianController.approveBorrowSlip(pm.getValueAt(currentRow, 0).toString(),"Approved")) {
                    		JOptionPane.showMessageDialog(BorrowApprove.this,"Phê duyệt thành công vui lòng tải lại trang");
                    	}                    	
                    }
                    
                }
            });

            rejectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int opt=JOptionPane.showConfirmDialog(BorrowApprove.this,"Xác nhận không đồng ý");
                    if(opt==JOptionPane.YES_OPTION) {
                    	if(LibrarianController.approveBorrowSlip(pm.getValueAt(currentRow, 0).toString(),"Dissaprove")) {
                    		JOptionPane.showMessageDialog(BorrowApprove.this,"Phê duyệt thành công vui lòng tải lại trang");
                    	}                    	
                    }

                }
            });

            panel.add(approveButton);
            panel.add(rejectButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}
