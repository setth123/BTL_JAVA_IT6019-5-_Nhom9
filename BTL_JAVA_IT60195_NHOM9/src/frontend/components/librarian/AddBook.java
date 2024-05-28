//package frontend.components.librarian;
//
//
//import javax.swing.JFrame;
//
//import backend.controllers.LibrarianController;
//import frontend.utils.Animation;
//
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import java.awt.Font;
//import java.awt.Color;
//import javax.swing.JTextField;
//import javax.swing.SpinnerNumberModel;
//
//import javax.swing.JSpinner;
//import javax.swing.JComboBox;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.ChangeEvent;
//
//public class AddBook extends JFrame {
//	private static final long serialVersionUID = 1L;
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	private JTextField textField_3;
//
//
//
//	/**
//	 * Create the frame.
//	 */
//	public AddBook(JFrame parent) {
//		Initialize(parent);
//	}
//
//	private static int daysInMonth(int year, int month) {
//		int day;
//		if(year % 4 == 0){
//		    if(year % 100 == 0){
//		        if(year % 400 == 0){
//		           day=29;
//		        } else {
//		            day=29;
//		        }
//		    } else {
//		        day=28;
//		    }
//		} else {
//				day=29;
//		}
//		return day;
//    }
//
//
//	private void Initialize(JFrame parent) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 800, 500);
//		getContentPane().setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("Thêm sách\r\n");
//		lblNewLabel.setBounds(20, 56, 258, 53);
//		lblNewLabel.setForeground(new Color(128, 128, 128));
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
//		getContentPane().add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("Mã sách");
//		lblNewLabel_1.setBounds(44, 139, 80, 14);
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		getContentPane().add(lblNewLabel_1);
//
//		JLabel lblNewLabel_1_1 = new JLabel("Tên sách");
//		lblNewLabel_1_1.setBounds(44, 199, 80, 14);
//		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		getContentPane().add(lblNewLabel_1_1);
//
//		JLabel lblNewLabel_1_2 = new JLabel("Nhà xuất bản");
//		lblNewLabel_1_2.setBounds(44, 272, 111, 14);
//		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
//		getContentPane().add(lblNewLabel_1_2);
//
//		JLabel lblNewLabel_1_3 = new JLabel("Ngày phát hành");
//		lblNewLabel_1_3.setBounds(44, 336, 111, 14);
//		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
//		getContentPane().add(lblNewLabel_1_3);
//
//		textField = new JTextField();
//		textField.setBounds(165, 138, 160, 20);
//		getContentPane().add(textField);
//		textField.setColumns(10);
//
//		textField_1 = new JTextField();
//		textField_1.setBounds(165, 198, 160, 20);
//		textField_1.setColumns(10);
//		getContentPane().add(textField_1);
//
//		textField_2 = new JTextField();
//		textField_2.setBounds(165, 271, 160, 20);
//		textField_2.setColumns(10);
//		getContentPane().add(textField_2);
//
//		JLabel lblNewLabel_2_1_1 = new JLabel("Năm");
//		lblNewLabel_2_1_1.setBounds(328, 338, 46, 14);
//		getContentPane().add(lblNewLabel_2_1_1);
//
//		SpinnerNumberModel model11=new SpinnerNumberModel(2024,1900,2024,1);
//		JSpinner spinner_1_1 = new JSpinner(model11);
//				spinner_1_1.setBounds(357, 335, 51, 20);
//		getContentPane().add(spinner_1_1);
//
//		JLabel lblNewLabel_2_1 = new JLabel("Tháng");
//		lblNewLabel_2_1.setBounds(250, 338, 46, 14);
//		getContentPane().add(lblNewLabel_2_1);
//
//		SpinnerNumberModel model12=new SpinnerNumberModel(5,1,12,1);
//		JSpinner spinner_1 = new JSpinner(model12);
//		spinner_1.setBounds(286, 335, 39, 20);
//		getContentPane().add(spinner_1);
//
//		JLabel lblNewLabel_2 = new JLabel("Ngày");
//		lblNewLabel_2.setBounds(175, 338, 46, 14);
//		getContentPane().add(lblNewLabel_2);
//
//		int year=(int) spinner_1_1.getValue();
//		int month=(int)spinner_1.getValue();
//
//
//		SpinnerNumberModel model13=new SpinnerNumberModel(19,1,daysInMonth(year,month),1);
//		JSpinner spinner = new JSpinner(model13);
//		spinner.setBounds(208, 335, 39, 20);
//		getContentPane().add(spinner);
//
//		spinner_1.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				int s_year = (int) spinner_1_1.getValue();
//				int s_month=(int)spinner_1.getValue();
//				int maxday=daysInMonth(s_year,s_month);
//				SpinnerNumberModel n_model=new SpinnerNumberModel(19,1,maxday,1);
//				spinner.setModel(n_model);
//			}
//		});
//
//
//		JLabel lblNewLabel_1_1_1 = new JLabel("Thể loại");
//		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		lblNewLabel_1_1_1.setBounds(441, 139, 80, 19);
//		getContentPane().add(lblNewLabel_1_1_1);
//
//		JLabel lblNewLabel_1_1_1_1 = new JLabel("Giá");
//		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		lblNewLabel_1_1_1_1.setBounds(441, 274, 80, 14);
//		getContentPane().add(lblNewLabel_1_1_1_1);
//
//		JLabel lblNewLabel_1_1_1_2 = new JLabel("Số lượng");
//		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
//		lblNewLabel_1_1_1_2.setBounds(441, 201, 80, 17);
//		getContentPane().add(lblNewLabel_1_1_1_2);
//
//		JComboBox<String> comboBox = new JComboBox<String>();
//		comboBox.addItem("Giáo trình");
//		comboBox.addItem("Văn học");
//		comboBox.addItem("Văn hoá - Xã hội");
//		comboBox.addItem("Tâm lý - Tình cảm");
//		comboBox.addItem("Tôn giáo");
//		comboBox.addItem("Khoa học - Công nghệ");
//		comboBox.addItem("Sách tự lực");
//		comboBox.setBounds(521, 137, 136, 22);
//		getContentPane().add(comboBox);
//
//		SpinnerNumberModel model2=new SpinnerNumberModel(1,1,100,1);
//		JSpinner spinner_2 = new JSpinner(model2);
//		spinner_2.setBounds(521, 198, 66, 20);
//		getContentPane().add(spinner_2);
//
//		textField_3 = new JTextField();
//		textField_3.setBounds(521, 271, 136, 20);
//		Animation.placeHolder(textField_3, "Đơn vị: VND");
//		getContentPane().add(textField_3);
//		textField_3.setColumns(10);
//
//		JButton btnNewButton = new JButton("Thêm sách");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")) {
//					JOptionPane.showMessageDialog(AddBook.this,"Vui lòng nhập đầy đủ thông tin");
//				}
//				else {
//					LibrarianController.addBook(textField.getText(),textField_1.getText(),textField_2.getText(),year,month,(Integer)spinner.getValue(),(String)comboBox.getSelectedItem(),(Integer)spinner_2.getValue(),Double.parseDouble(textField_3.getText()));
//					JOptionPane.showMessageDialog(AddBook.this, "Thêm sách thành công");
//				}
//			}
//		});
//		btnNewButton.setBounds(486, 386, 89, 23);
//		getContentPane().add(btnNewButton);
//
//		JButton btnHu = new JButton("Huỷ");
//		btnHu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				textField.setText("");
//				textField_1.setText("");
//				textField_2.setText("");
//				textField_3.setText("");
//				comboBox.setSelectedItem("Giáo trình");
//				spinner.setValue(1);
//				spinner_1.setValue(5);
//				spinner_1_1.setValue(2024);
//				spinner_2.setValue(1);
//
//			}
//		});
//		btnHu.setBounds(607, 386, 89, 23);
//		getContentPane().add(btnHu);
//
//		JButton btnNewButton_1 = new JButton("Quay lại");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
//				dispose();
//				parent.setVisible(true);
//			}
//		});
//		btnNewButton_1.setBounds(35, 11, 89, 23);
//		getContentPane().add(btnNewButton_1);
//		}
//}

package frontend.components.librarian;


import javax.swing.JFrame;

import backend.controllers.LibrarianController;
import backend.models.Category;
import backend.utils.FetchBE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AddBook extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField maSachInp;
	private JTextField tenSachInp;
	private JTextField nxbInp;
	private JTextField priceInp;

	

	/**
	 * Create the frame.
	 */
	public AddBook(JFrame parent) {
		Initialize(parent);
	}
	
	
	
	private static int daysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

	
	private void Initialize(JFrame parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		setTitle("Thêm sách");
		
		JLabel title = new JLabel("Thêm sách\r\n");
		title.setBounds(20, 56, 258, 53);
		title.setForeground(new Color(128, 128, 128));
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(title);
		
		
		//Các trường nhập liệu
		JLabel maSach = new JLabel("Mã sách");
		maSach.setBounds(44, 139, 80, 14);
		maSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(maSach);
		
		JLabel tenSach = new JLabel("Tên sách");
		tenSach.setBounds(44, 199, 80, 14);
		tenSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(tenSach);
		
		JLabel nxb = new JLabel("Nhà xuất bản");
		nxb.setBounds(44, 272, 111, 14);
		nxb.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(nxb);
		
		JLabel nph = new JLabel("Ngày phát hành");
		nph.setBounds(44, 336, 111, 14);
		nph.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(nph);
		
		maSachInp = new JTextField();
		maSachInp.setBounds(165, 138, 160, 20);
		getContentPane().add(maSachInp);
		maSachInp.setColumns(10);
		
		tenSachInp = new JTextField();
		tenSachInp.setBounds(165, 198, 160, 20);
		tenSachInp.setColumns(10);
		getContentPane().add(tenSachInp);
		
		nxbInp = new JTextField();
		nxbInp.setBounds(165, 271, 160, 20);
		nxbInp.setColumns(10);
		getContentPane().add(nxbInp);
				
		JLabel nam = new JLabel("Năm");
		nam.setBounds(328, 338, 46, 14);
		getContentPane().add(nam);
		
		SpinnerNumberModel model11=new SpinnerNumberModel(2024,1900,2024,1);
		JSpinner yearInp = new JSpinner(model11);
				yearInp.setBounds(357, 335, 51, 20);
		getContentPane().add(yearInp);
		
		JLabel thang = new JLabel("Tháng");
		thang.setBounds(250, 338, 46, 14);
		getContentPane().add(thang);
		
		SpinnerNumberModel model12=new SpinnerNumberModel(5,1,12,1);
		JSpinner thangInp = new JSpinner(model12);
		thangInp.setBounds(286, 335, 39, 20);
		getContentPane().add(thangInp);
		
		JLabel day = new JLabel("Ngày");
		day.setBounds(175, 338, 46, 14);
		getContentPane().add(day);
		
		int year=(int) yearInp.getValue();
		int month=(int)thangInp.getValue();
						
		
		SpinnerNumberModel model13=new SpinnerNumberModel(19,1,daysInMonth(year,month),1);
		JSpinner dayInp = new JSpinner(model13);
		dayInp.setBounds(208, 335, 39, 20);
		getContentPane().add(dayInp);
		
		thangInp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int s_year = (int) yearInp.getValue();
				int s_month=(int)thangInp.getValue();
				int maxday=daysInMonth(s_year,s_month);
				SpinnerNumberModel n_model=new SpinnerNumberModel(19,1,maxday,1);
				dayInp.setModel(n_model);
			}
		});

		
		JLabel cate = new JLabel("Thể loại");
		cate.setFont(new Font("Tahoma", Font.BOLD, 14));
		cate.setBounds(441, 139, 80, 19);
		getContentPane().add(cate);
		
		JLabel price = new JLabel("Giá");
		price.setFont(new Font("Tahoma", Font.BOLD, 14));
		price.setBounds(441, 272, 80, 14);
		getContentPane().add(price);
		
		JLabel sl = new JLabel("Số lượng");
		sl.setFont(new Font("Tahoma", Font.BOLD, 14));
		sl.setBounds(441, 201, 80, 17);
		getContentPane().add(sl);
		
		JComboBox<String> categoryInp = new JComboBox<String>();
		List<Category> cs=FetchBE.fetchCate();
		for(Category c: cs) {
			categoryInp.addItem(c.getTenDanhMuc());
		}
		categoryInp.setBounds(521,137,136,20);
		getContentPane().add(categoryInp);
		
		SpinnerNumberModel model2=new SpinnerNumberModel(1,1,100,1);
		JSpinner slInp = new JSpinner(model2);
		slInp.setBounds(521, 198, 66, 20);
		getContentPane().add(slInp);
		
		priceInp = new JTextField();
		priceInp.setBounds(521, 271, 136, 20);
		getContentPane().add(priceInp);
		priceInp.setColumns(10);
		
		
		//Thêm sách
		JButton submit = new JButton("Thêm sách");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(maSachInp.getText().equals("")||tenSachInp.getText().equals("")||nxbInp.getText().equals("")||priceInp.getText().equals("")) {
					JOptionPane.showMessageDialog(AddBook.this,"Vui lòng nhập đầy đủ thông tin");
					return;
				}
				if(!priceInp.getText().matches("-?\\\\d+(\\\\.\\\\d+)?")) {
					JOptionPane.showMessageDialog(AddBook.this,"Giá thành không hợp lý");
					return;
				}
				LibrarianController.addBook(maSachInp.getText(),tenSachInp.getText(),nxbInp.getText(),year,month,(Integer)dayInp.getValue(),(String)categoryInp.getSelectedItem(),(Integer)slInp.getValue(),Double.parseDouble(priceInp.getText()));
				JOptionPane.showMessageDialog(AddBook.this, "Thêm sách thành công");
			}
		});
		submit.setBounds(486, 386, 101, 23);
		getContentPane().add(submit);
		
		//Reset dữ liệu đã nhập
		JButton huy = new JButton("Huỷ");
		huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maSachInp.setText("");
				tenSachInp.setText("");
				nxbInp.setText("");
				priceInp.setText("");
				categoryInp.setSelectedItem("Giáo trình");
				dayInp.setValue(1);
				thangInp.setValue(5);
				yearInp.setValue(2024);
				slInp.setValue(1);
				
			}
		});
		huy.setBounds(607, 386, 89, 23);
		getContentPane().add(huy);
		
		//Quay lại trang trước đó
		JButton ql = new JButton("Quay lại");
		ql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				parent.setVisible(true);
			}
		});
		ql.setBounds(35, 11, 89, 23);
		getContentPane().add(ql);
		}
}
