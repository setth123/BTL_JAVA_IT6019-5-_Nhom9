package frontend;

import frontend.components.user.Register;
import frontend.components.user.UserLogin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainF {

	private JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainF window = new MainF();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hệ thống quản lý sách ");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(127, 11, 325, 97);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đăng nhập để tiếp tục");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(195, 94, 176, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Đăng ký");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.showRegisterLayout(frame); // Gọi phương thức showRegisterLayout từ class DangKi
				frame.dispose();
			}
		});


		btnNewButton.setBounds(192, 146, 166, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Đăng nhập");
		btnNewButton_1.addActionListener((e) -> {
			// Create the second window (UserLogin)
			UserLogin userLogin = new UserLogin(frame);
			// Set the second window visible
			userLogin.setVisible(true);
			// Hide the main window
			frame.setVisible(false);
		});
		btnNewButton_1.setBounds(192, 198, 166, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Đăng nhập (Thủ thư)");
		btnNewButton_2.setBounds(195, 243, 163, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AdminLogin admin=new AdminLogin(frame);
//				admin.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton_2);
	}
}
