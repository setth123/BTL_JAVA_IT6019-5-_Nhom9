//package frontend.components.librarian;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import backend.controllers.LibrarianController;
//import backend.models.Librarian;
//
//import java.awt.Font;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JTextField;
//import javax.swing.JPasswordField;
//import java.awt.Color;
//
//public class AdminLogin extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JTextField textField;
//	private JPasswordField passwordField;
//
//	public AdminLogin(JFrame parent) {
//		initialize(parent);
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize(JFrame parent) {
//		getContentPane().setForeground(Color.GRAY);
//		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 20));
//		setBounds(100, 100, 381, 348);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(null);
//		setResizable(false);
//
//		JLabel lblNewLabel = new JLabel("Đăng nhập (Thủ thư)");
//		lblNewLabel.setForeground(Color.GRAY);
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
//		lblNewLabel.setBounds(61, 33, 301, 68);
//		getContentPane().add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNewLabel_1.setBounds(48, 112, 70, 14);
//		getContentPane().add(lblNewLabel_1);
//
//		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
//		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNewLabel_1_1.setBounds(48, 173, 70, 14);
//		getContentPane().add(lblNewLabel_1_1);
//
//
//		JButton btnNewButton = new JButton("Đăng nhập");
//		btnNewButton.setBackground(new Color(0, 255, 255));
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String password=new String(passwordField.getPassword());
//				if(textField.getText().equals("")||password.equals("")) {
//					JOptionPane.showMessageDialog(AdminLogin.this,"Vui lòng nhập đầy đủ thông tin");
//					return;
//				}
//				Librarian l=LibrarianController.login(textField.getText(),password);
//				if(l!=null) {
//					LibrarianHP hp=new LibrarianHP(AdminLogin.this,l);
//					hp.setVisible(true);
//					setVisible(false);
//					dispose();
//				}
//				else  JOptionPane.showMessageDialog(AdminLogin.this,"Tài khoản hoặc mật khẩu không chính xác");
//			}
//		});
//		btnNewButton.setBounds(116, 246, 115, 23);
//		getContentPane().add(btnNewButton);
//
//		textField = new JTextField();
//		textField.setBounds(140, 112, 153, 20);
//		getContentPane().add(textField);
//		textField.setColumns(10);
//
//		passwordField = new JPasswordField();
//		passwordField.setBounds(140, 172, 153, 20);
//		getContentPane().add(passwordField);
//
//		JButton btnNewButton_1 = new JButton("Quay lại");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				parent.setVisible(true);
//				setVisible(false);
//			    dispose();
//
//			}
//		});
//		btnNewButton_1.setBounds(10, 11, 89, 23);
//		getContentPane().add(btnNewButton_1);
//
//	}
//}
