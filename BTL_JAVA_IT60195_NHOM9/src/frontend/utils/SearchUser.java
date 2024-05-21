package frontend.utils;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import backend.models.Account;
import backend.utils.SearchBE;
//import frontend.components.librarian.EditBook;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Color;

public class SearchUser extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton_1;
	private JLabel lblTmKim;


	/**
	 * Create the application.
	 */
	
	
	public SearchUser(JFrame parent,String keyword) {
		initialize(parent,keyword);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public static void fetchUser(String keyword,DefaultTableModel m) {
		m.setRowCount(0);
		System.out.println(keyword);
		List<Account> result=SearchBE.findA(keyword);
		for (Account a : result) {
			Object[] row= {a.getMaTaiKhoan(),a.getTenNguoiDung(),a.getDiaChi(),a.getSoDienThoai(),a.getTenDangNhap(),a.getMatKhau()};
			m.addRow(row);
		}
	}
	public static void fetchUser(DefaultTableModel m) {
		m.setRowCount(0);
		List<Account> result=SearchBE.findA("");
		int end = Math.min(10, result.size());
		for (int i = result.size()-1; i >=result.size()-end; i--) {
		    Account a = result.get(i);
		    Object[] row = {a.getMaTaiKhoan(), a.getTenNguoiDung(), a.getSoDienThoai(), a.getTenDangNhap()};
		    m.addRow(row);
		}
	}
	private void initialize(JFrame parent,String keyword) {
		setTitle("Tìm kiếm");
		Rectangle r=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0,0,r.width,r.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		textField=new JTextField();
		textField.setText(keyword);
		textField.setBounds(951, 21, 276, 35);
		textField.setColumns(10);
		//Animation.placeHolder(textField, "Nhập tên người dùng cần tìm");
		getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setBounds(1249, 20, 89, 30);
		getContentPane().add(btnNewButton);
		
		String[] u= {"Mã tài khoản ","Tên người dùng","Địa chỉ","Số điện thoại","Tên đăng nhập","Mật khẩu"};
		DefaultTableModel model=new DefaultTableModel(u,0);
		fetchUser(keyword,model);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchUser(textField.getText(),model);
			}
		});
				
		table = new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		
		sp.setBounds(39, 117, 1257, 541);
		getContentPane().add(sp);
		
		btnNewButton_1 = new JButton("Quay lại");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				parent.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(42, 20, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		
		lblTmKim = new JLabel("TÌM KIẾM NGƯỜI DÙNG");
		lblTmKim.setForeground(Color.GRAY);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTmKim.setBounds(300, 33, 373, 35);
		getContentPane().add(lblTmKim);
	}

}
