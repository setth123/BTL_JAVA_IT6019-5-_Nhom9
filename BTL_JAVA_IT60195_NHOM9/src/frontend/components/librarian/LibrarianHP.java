package frontend.components.librarian;


import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.controllers.Statics;
import backend.models.Librarian;
import frontend.utils.Animation;
import frontend.utils.ImageProcess;
import frontend.utils.Search;
import backend.controllers.Statics;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class LibrarianHP extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JTextField textField;

	
	public LibrarianHP(JFrame parent,Librarian l) {
		initialize(parent,l);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize(JFrame parent,Librarian l) {
		setTitle("Phần mềm quản lý thư viện");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		Rectangle r=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0,0,r.width,r.height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBounds(0, 0, 1467, 130);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" THƯ VIỆN A");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(418, 35, 269, 35);
		ImageIcon library=ImageProcess.scaled("Assets/33874-200.png",75,75);
		lblNewLabel.setIcon(library);
		
		panel.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel();
		ImageIcon admin=ImageProcess.scaled("Assets/1144760.png", 61, 61);
		lblNewLabel_1.setBounds(37, 28, 61, 61);
		lblNewLabel_1.setIcon(admin);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Đăng xuất");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				parent.setVisible(true);
			}
		});
		btnNewButton.setBounds(1250, 47, 105, 23);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		
		textField.setBounds(886, 48, 193, 20);
		
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(771, 47, 105, 22);
		comboBox.addItem("Sách");
		comboBox.addItem("Người dùng");
		Animation.f_placeHolder(textField, comboBox);
		panel.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Tìm kiếm");
		btnNewButton_1.setBackground(new Color(0, 128, 255));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search s=new Search(LibrarianHP.this,textField.getText(),(String)comboBox.getSelectedItem());
				s.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(1089, 47, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.setBackground(new Color(0, 128, 255));
		
		btnNewButton_2.setBounds(1177, 47, 64, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Xin chào, "+l.getAccountName());
		lblNewLabel_7.setBounds(10, 100, 129, 19);
		panel.add(lblNewLabel_7);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(0, 128, 163, 621);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		JLabel lblNewLabel_2 = new JLabel("    Sách");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(192, 192, 192));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 37, 126, 35);
		panel_1.add(lblNewLabel_2);
		ImageIcon book=ImageProcess.scaled("Assets/151594.png", 27, 27);
		lblNewLabel_2.setIcon(book);
		ImageIcon add=ImageProcess.scaled("Assets/262038.png",20,20);
		ImageIcon delete=ImageProcess.scaled("Assets/delete-icon-1864x2048-bp2i0gor.png", 20, 20);
		ImageIcon modify=ImageProcess.scaled("Assets/modify-icon-512x512-clqmeplb.png", 20, 20);
		
		JLabel lblNewLabel_2_1 = new JLabel("    Người dùng");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(10, 241, 103, 27);
		ImageIcon user=ImageProcess.scaled("Assets/1077063.png", 27, 27);
		lblNewLabel_2_1.setIcon(user);
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddBook a=new AddBook(LibrarianHP.this);
				a.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		Animation.onHoover(panel_2, new Color(0,128,255), new Color(0,128,192));
		panel_2.setBackground(new Color(0, 128, 255));
		panel_2.setBounds(0, 83, 163, 40);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("  Thêm sách");
		lblNewLabel_3.setBounds(0, 0, 163, 40);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setIcon(add);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search s=new Search(LibrarianHP.this,"Sách",3);
				s.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		Animation.onHoover(panel_2_1, new Color(0,128,255), new Color(0,128,192));
		panel_2_1.setBackground(new Color(0, 128, 255));
		panel_2_1.setBounds(0, 123, 163, 40);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("  Xoá sách");
		lblNewLabel_3_1.setBounds(0, 0, 163, 40);
		panel_2_1.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBackground(new Color(0, 128, 255));
		lblNewLabel_3_1.setIcon(delete);
		
		JPanel panel_2_2 = new JPanel();
		Animation.onHoover(panel_2_2, new Color(0,128,255), new Color(0,128,192));
		panel_2_2.setBackground(new Color(0, 128, 255));
		panel_2_2.setBounds(0, 163, 163, 40);
		panel_1.add(panel_2_2);
		panel_2_2.setLayout(null);
		
		JLabel lblNewLabel_3_2 = new JLabel("  Sửa thông tin sách");
		panel_2_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search s=new Search(LibrarianHP.this,"Sách",2);
				s.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		lblNewLabel_3_2.setBounds(0, 0, 153, 40);
		panel_2_2.add(lblNewLabel_3_2);
		lblNewLabel_3_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_2.setIcon(modify);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setLayout(null);
		panel_2_3.setBackground(new Color(0, 128, 255));
		panel_2_3.setBounds(0, 279, 163, 40);
		panel_1.add(panel_2_3);
		Animation.onHoover(panel_2_3,new Color(0,128,255) , new Color(0,128,192));
		
		JLabel lblNewLabel_3_3 = new JLabel("Yêu cầu mượn sách");
		lblNewLabel_3_3.setForeground(Color.WHITE);
		lblNewLabel_3_3.setBounds(0, 0, 163, 40);
		ImageIcon borrow=ImageProcess.scaled("Assets/borrow.png", 20, 20);
		lblNewLabel_3_3.setIcon(borrow);
		panel_2_3.add(lblNewLabel_3_3);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setLayout(null);
		panel_2_4.setBackground(new Color(0, 128, 255));
		panel_2_4.setBounds(0, 320, 163, 40);
		panel_1.add(panel_2_4);
		Animation.onHoover(panel_2_4, new Color(0,128,255) , new Color(0,128,192));
		
		JLabel lblNewLabel_3_4 = new JLabel("Phiếu vi phạm");
		lblNewLabel_3_4.setForeground(Color.WHITE);
		lblNewLabel_3_4.setBounds(0, 0, 163, 40);
		ImageIcon vipham=ImageProcess.scaled("Assets/warning-icon-512x511-3vl9qoze.png", 20, 20);
		lblNewLabel_3_4.setIcon(vipham);
		panel_2_4.add(lblNewLabel_3_4);
		
		JPanel panel_2_4_1 = new JPanel();
		panel_2_4_1.setLayout(null);
		panel_2_4_1.setBackground(new Color(0, 128, 255));
		panel_2_4_1.setBounds(0, 360, 163, 40);
		panel_1.add(panel_2_4_1);
		Animation.onHoover(panel_2_4_1, new Color(0,128,255) , new Color(0,128,192));
		
		JLabel lblNewLabel_3_4_1 = new JLabel("Khoá tài khoản");
		lblNewLabel_3_4_1.setForeground(Color.WHITE);
		lblNewLabel_3_4_1.setBounds(0, 0, 163, 40);
		ImageIcon banned=ImageProcess.scaled("Assets/banned.png", 20, 20);
		lblNewLabel_3_4_1.setIcon(banned);
		panel_2_4_1.add(lblNewLabel_3_4_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Thủ thư");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2_1_1.setBounds(10, 424, 103, 27);
		ImageIcon libra=ImageProcess.scaled("Assets/librarian.png", 27, 27);
		lblNewLabel_2_1_1.setIcon(libra);
		panel_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_2_4_1_1 = new JPanel();
		panel_2_4_1_1.setLayout(null);
		panel_2_4_1_1.setBackground(new Color(0, 128, 255));
		panel_2_4_1_1.setBounds(0, 462, 163, 40);
		panel_1.add(panel_2_4_1_1);
		Animation.onHoover(panel_2_4_1_1, new Color(0,128,255) , new Color(0,128,192));
		
		JLabel lblNewLabel_3_4_1_1 = new JLabel("Đổi mật khẩu");
		panel_2_4_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePassword cp=new ChangePassword(l,LibrarianHP.this);
				cp.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		lblNewLabel_3_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_4_1_1.setBounds(0, 0, 163, 40);
		ImageIcon change1=ImageProcess.scaled("Assets/change-password-5.png", 20, 20);
		lblNewLabel_3_4_1_1.setIcon(change1);
		panel_2_4_1_1.add(lblNewLabel_3_4_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 128, 64));
		panel_3.setBounds(234, 206, 219, 121);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		
		System.out.println(Statics.books());
		JLabel lblNewLabel_6 = new JLabel(String.valueOf(Statics.books()));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_6.setBounds(26, 11, 151, 84);
		panel_3.add(lblNewLabel_6);
		
		String[] b= {"Mã sách","Tên sách","Nhà xuất bản","Số lượng"};
		DefaultTableModel model=new DefaultTableModel(b,0);
		Search.fetchBook(model);
		table = new JTable(model);
	    JScrollPane jsp = new JScrollPane(table);
	    jsp.setBounds(173, 424, 567, 229);
		getContentPane().add(jsp);
		
		JLabel lblNewLabel_4 = new JLabel("Sách mới nhất");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(173, 370, 172, 30);
		getContentPane().add(lblNewLabel_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 0, 0));
		panel_4.setBounds(234, 170, 219, 35);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Số đầu sách");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(10, 11, 125, 24);
		panel_4.add(lblNewLabel_5);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(new Color(0, 64, 128));
		panel_4_1.setBounds(525, 170, 219, 35);
		getContentPane().add(panel_4_1);
		panel_4_1.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("Số người dùng");
		lblNewLabel_5_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5_1.setBounds(10, 11, 155, 24);
		panel_4_1.add(lblNewLabel_5_1);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setBackground(new Color(128, 128, 255));
		panel_4_2.setBounds(525, 206, 219, 121);
		getContentPane().add(panel_4_2);
		panel_4_2.setLayout(null);
		
		JLabel lblNewLabel_6_1 = new JLabel("20");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_6_1.setBounds(10, 11, 151, 84);
		panel_4_2.add(lblNewLabel_6_1);
		
		JPanel panel_4_3 = new JPanel();
		panel_4_3.setBackground(new Color(0, 128, 0));
		panel_4_3.setBounds(823, 170, 219, 35);
		getContentPane().add(panel_4_3);
		panel_4_3.setLayout(null);
		
		JLabel lblNewLabel_5_2 = new JLabel("Số sách đang mượn");
		lblNewLabel_5_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5_2.setBounds(10, 11, 209, 24);
		panel_4_3.add(lblNewLabel_5_2);
		
		JPanel panel_4_4 = new JPanel();
		panel_4_4.setBackground(new Color(0, 255, 0));
		panel_4_4.setBounds(823, 206, 219, 121);
		getContentPane().add(panel_4_4);
		panel_4_4.setLayout(null);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("20");
		lblNewLabel_6_1_1.setForeground(Color.WHITE);
		lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_6_1_1.setBounds(10, 11, 151, 84);
		panel_4_4.add(lblNewLabel_6_1_1);
		
		JPanel panel_4_5 = new JPanel();
		panel_4_5.setBackground(new Color(64, 0, 0));
		panel_4_5.setBounds(1118, 170, 219, 35);
		getContentPane().add(panel_4_5);
		panel_4_5.setLayout(null);
		
		JLabel lblNewLabel_5_3 = new JLabel("Số sách quá hạn");
		lblNewLabel_5_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5_3.setBounds(10, 11, 199, 24);
		panel_4_5.add(lblNewLabel_5_3);
		
		JPanel panel_4_6 = new JPanel();
		panel_4_6.setBackground(new Color(128, 0, 0));
		panel_4_6.setBounds(1118, 206, 219, 121);
		getContentPane().add(panel_4_6);
		panel_4_6.setLayout(null);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("20");
		lblNewLabel_6_1_2.setForeground(Color.WHITE);
		lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_6_1_2.setBounds(10, 11, 151, 84);
		panel_4_6.add(lblNewLabel_6_1_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Người dùng mới");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(794, 370, 172, 30);
		getContentPane().add(lblNewLabel_4_1);
		
		table_1 = new JTable();
		table_1.setBounds(779, 424, 558, 229);
		getContentPane().add(table_1);
		
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_6.setText(String.valueOf(Statics.books()));
				Search.fetchBook(model);
				table.setModel(model);
			}
		});
	}
}
