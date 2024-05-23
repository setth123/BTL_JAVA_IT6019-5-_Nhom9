package frontend.components.librarian;


import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class BorrowApprove extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable pm;


	private static void fetchSlip() {
		
	}
	
	/**
	 * Create the frame.
	 */
	public BorrowApprove() {
		Rectangle r=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0,0,r.width,r.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("Phê duyệt mượn sách");
		
		JLabel Title = new JLabel("DANH SÁCH PHIẾU MƯỢN");
		Title.setForeground(new Color(128, 128, 128));
		Title.setFont(new Font("Tahoma", Font.BOLD, 25));
		Title.setBounds(473, 65, 354, 31);
		getContentPane().add(Title);
		
		JButton ql = new JButton("Quay lại");
		ql.setBounds(42, 37, 89, 23);
		getContentPane().add(ql);
		
		String[] column= {"Mã phiếu mượn","Ngày mượn","Mã tài khoản","Trạng thái"};
		
		DefaultTableModel model=new DefaultTableModel(column,0);
		pm = new JTable(model);
		
		JScrollPane sp = new JScrollPane(pm);
		sp.setBounds(27, 132, 1279, 521);
		getContentPane().add(sp);
	}
}
