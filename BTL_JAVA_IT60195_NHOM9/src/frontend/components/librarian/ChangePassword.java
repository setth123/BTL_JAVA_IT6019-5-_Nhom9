package frontend.components.librarian;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import backend.controllers.LibrarianController;
import backend.models.Librarian;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;



	/**
	 * Create the application.
	 */
	public ChangePassword(Librarian l,JFrame parent) {
		initialize(l,parent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Librarian l,JFrame parent) {
		setBounds(100, 100, 400, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setBounds(136, 69, 162, 48);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nhập mật khẩu cũ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(63, 132, 119, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nhập mật khẩu mới");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(63, 229, 119, 14);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Xác nhận mật khẩu");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(63, 323, 119, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(63, 177, 230, 20);
		getContentPane().add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(63, 267, 230, 20);
		getContentPane().add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(63, 352, 230, 20);
		getContentPane().add(passwordField_2);

		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String op=new String(passwordField.getPassword());
				String np=new String(passwordField_1.getPassword());
				String cnp=new String(passwordField_2.getPassword());
				if(op.equals("")||np.equals("")||cnp.equals("")) {
					JOptionPane.showMessageDialog(ChangePassword.this,"Vui lòng nhập đầy đủ thông tin");
					return;
				}
				if(!op.equals(l.getPassword())) {
					JOptionPane.showMessageDialog(ChangePassword.this, "Mật khẩu không chính xác");
					return;
				}
				if(!np.equals(cnp)) {
					JOptionPane.showMessageDialog(ChangePassword.this, "Mật khẩu không trùng khớp");
					return;
				}
				if(LibrarianController.changePassword(l, cnp)) {
					JOptionPane.showMessageDialog(ChangePassword.this,"Đổi mật khẩu thành công vui lòng nhấn quay lại để tiếp tục");
				}

			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setBounds(147, 404, 113, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Quay lại");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnNewButton_1.setBounds(21, 27, 89, 23);
		getContentPane().add(btnNewButton_1);
	}
}
