package frontend.components.librarian;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.controllers.LibrarianController;
import backend.models.Book;
import backend.models.Category;
import backend.utils.FetchBE;


public class EditBook extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public EditBook(JFrame parent,Book b) {
		initialize(parent,b);
	}
	
	private static int daysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame parent,Book b) {
		
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sửa thông tin sách\r\n"+" "+b.getMaSach());
		lblNewLabel.setBounds(20, 56, 401, 53);
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sách");
		lblNewLabel_1.setBounds(44, 139, 80, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên sách");
		lblNewLabel_1_1.setBounds(44, 199, 80, 14);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhà xuất bản");
		lblNewLabel_1_2.setBounds(44, 272, 111, 14);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày phát hành");
		lblNewLabel_1_3.setBounds(44, 336, 111, 14);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.setBounds(165, 138, 160, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(165, 198, 160, 20);
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(165, 271, 160, 20);
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		
		int day=b.getNph().getDayOfMonth();
		int month=b.getNph().getMonthValue();
		int year=b.getNph().getYear();
		JLabel lblNewLabel_2_1_1 = new JLabel("Năm");
		lblNewLabel_2_1_1.setBounds(328, 338, 46, 14);
		getContentPane().add(lblNewLabel_2_1_1);
		
		SpinnerNumberModel model11=new SpinnerNumberModel(year,1900,2024,1);
		JSpinner spinner_1_1 = new JSpinner(model11);
		spinner_1_1.setBounds(357, 335, 51, 20);
		getContentPane().add(spinner_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tháng");
		lblNewLabel_2_1.setBounds(250, 338, 46, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		SpinnerNumberModel model12=new SpinnerNumberModel(month,1,12,1);
		JSpinner spinner_1 = new JSpinner(model12);
		spinner_1.setBounds(286, 335, 39, 20);
		getContentPane().add(spinner_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày");
		lblNewLabel_2.setBounds(175, 338, 46, 14);
		getContentPane().add(lblNewLabel_2);
						
		
		SpinnerNumberModel model13=new SpinnerNumberModel(19,1,daysInMonth(year,month),1);
		JSpinner spinner = new JSpinner(model13);
		spinner.setBounds(208, 335, 39, 20);
		getContentPane().add(spinner);
		
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int s_year = (int) spinner_1_1.getValue();
				int s_month=(int)spinner_1.getValue();
				int maxday=daysInMonth(s_year,s_month);
				SpinnerNumberModel n_model=new SpinnerNumberModel(19,1,maxday,1);
				spinner.setModel(n_model);
			}
		});
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Thể loại");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(441, 139, 80, 19);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Giá");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(441, 274, 80, 14);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Số lượng");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(441, 201, 80, 17);
		getContentPane().add(lblNewLabel_1_1_1_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		List<Category> cs=FetchBE.fetchCate();
		for(Category c: cs) {
			comboBox.addItem(c.getTenDanhMuc());
		}
		comboBox.setBounds(521,137,136,20);
		getContentPane().add(comboBox);
		
		SpinnerNumberModel model2=new SpinnerNumberModel(1,1,100,1);
		JSpinner spinner_2 = new JSpinner(model2);
		spinner_2.setBounds(521, 198, 66, 20);
		getContentPane().add(spinner_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(521, 271, 136, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField.setText(b.getMaSach());
		String oldMaSach=b.getMaSach();
		textField_1.setText(b.getTenSach());
		textField_2.setText(b.getNXB());
		textField_3.setText(String.valueOf(b.getGia()));
		comboBox.setSelectedItem(b.getTheLoai());
		spinner.setValue(day);
		spinner_1.setValue(month);
		spinner_1_1.setValue(year);
		spinner_2.setValue(b.getSl());
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")) {
					JOptionPane.showMessageDialog(EditBook.this,"Vui lòng nhập đầy đủ thông tin");
				}
				else {
					if(LibrarianController.editBook(oldMaSach,textField.getText(),textField_1.getText(),textField_2.getText(),year,month,(Integer)spinner.getValue(),(String)comboBox.getSelectedItem(),(Integer)spinner_2.getValue(),Double.parseDouble(textField_3.getText()))) {
						JOptionPane.showMessageDialog(EditBook.this, "Sửa thông tin sách thành công");
					}
					else JOptionPane.showMessageDialog(EditBook.this, "Lỗi không xác định");
				}
			}
		});
		btnNewButton.setBounds(486, 386, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnHu = new JButton("Huỷ");
		btnHu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(b.getMaSach());
				textField_1.setText(b.getTenSach());
				textField_2.setText(b.getNXB());
				textField_3.setText(String.valueOf(b.getGia()));
				comboBox.setSelectedItem(b.getTheLoai());
				spinner.setValue(day);
				spinner_1.setValue(month);
				spinner_1_1.setValue(year);
				spinner_2.setValue(b.getSl());
				
			}
		});
		btnHu.setBounds(607, 386, 89, 23);
		getContentPane().add(btnHu);
		
		JButton btnNewButton_1 = new JButton("Quay lại");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				parent.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(35, 11, 89, 23);
		getContentPane().add(btnNewButton_1);
	}

}
