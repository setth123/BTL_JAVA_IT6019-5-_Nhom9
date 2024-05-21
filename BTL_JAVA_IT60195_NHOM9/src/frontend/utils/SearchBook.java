package frontend.utils;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import backend.controllers.LibrarianController;
import backend.models.Book;
import backend.utils.SearchBE;
import frontend.components.librarian.EditBook;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Color;

public class SearchBook extends JFrame{
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
	
	
	public SearchBook(JFrame parent,String keyword) {
		initialize(parent,keyword);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public SearchBook(JFrame parent,int searchFor) {
		initialize(parent,searchFor);
	}

	public static void fetchBook(String keyword,DefaultTableModel m) {
		System.out.println(keyword);
		m.setRowCount(0);
		List<Book> result=SearchBE.findB(keyword);
		
		for (Book b : result) {
			Object[] row= {b.getMaSach(),b.getTenSach(),b.getNXB(),b.getNph(),b.getTheLoai(),b.getSl(),b.getGia()};
			m.addRow(row);
		}
	}
	public static void fetchBook(DefaultTableModel m) {
		m.setRowCount(0);
		List<Book> result = SearchBE.findB("");
		int end = Math.min(10, result.size());
		for (int i = result.size()-1; i >=result.size()-end; i--) {
		    Book b = result.get(i);
		    Object[] row = {b.getMaSach(), b.getTenSach(), b.getNXB(), b.getSl()};
		    m.addRow(row);
		}

	}


	/**
	 * Initialize the contents of the frame.
	 */
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
		getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setBounds(1249, 20, 89, 30);
		getContentPane().add(btnNewButton);

		String[] b= {"Mã sách","Tên sách","Nhà xuất bản","Ngày phát hành","Thể loại","Số lượng","Giá"};
		DefaultTableModel model=new DefaultTableModel(b,0);
		fetchBook(keyword,model);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					fetchBook(textField.getText(),model);
			}
		});

		
		table = new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		
		sp.setBounds(39, 117, 1257, 597);
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
		
		
		lblTmKim = new JLabel("TÌM KIẾM SÁCH");
		lblTmKim.setForeground(Color.GRAY);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTmKim.setBounds(468, 33, 269, 35);
		getContentPane().add(lblTmKim);
	}
	
	//searchFor=2(edit),searchFor=3(delete),searchFor=4(borrow)
	private void initialize(JFrame parent,int searchFor) {
		setTitle("Tìm kiếm");
		Rectangle r=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0,0,r.width,r.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		textField=new JTextField();
		textField.setBounds(951, 21, 276, 35);
		textField.setColumns(10);
		
		
		getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setBounds(1249, 20, 89, 30);
		getContentPane().add(btnNewButton);
		
		
		String[] b= {"Mã sách","Tên sách","Nhà xuất bản","Ngày phát hành","Thể loại","Số lượng","Giá"};

		DefaultTableModel model=new DefaultTableModel(b,0);
		fetchBook(textField.getText(),model);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchBook(textField.getText(),model);
				if(model.getRowCount()==0) {
					JOptionPane.showMessageDialog(SearchBook.this,"Không tìm thấy sách");
					}
			}
		});

		
		table = new JTable(model);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(39, 117, 1257, 533);
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
		btnNewButton_1.setBounds(42, 20, 112, 35);
		getContentPane().add(btnNewButton_1);
		
		
		lblTmKim = new JLabel();
		lblTmKim.setForeground(Color.GRAY);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTmKim.setBounds(206, 33, 695, 35);
		getContentPane().add(lblTmKim);
		switch(searchFor) {
		case 2:
			lblTmKim.setText("TÌM KIẾM SÁCH CẦN SỬA");
			//Animation.placeHolder(textField, "Nhập tên sách cần sửa");
			table.addMouseListener(new MouseAdapter() {
				@Override 
				public void mouseClicked(MouseEvent e) {
					int selected=table.getSelectedRow();
					String maSach=table.getValueAt(selected, 0).toString();
					String tenSach=table.getValueAt(selected, 1).toString();
					String nxb=table.getValueAt(selected, 2).toString();
					String nph=table.getValueAt(selected, 3).toString();
					String theLoai=table.getValueAt(selected, 4).toString();
					int sl =(Integer)table.getValueAt(selected, 5);
					double gia=(Double)table.getValueAt(selected, 6);
					Book b=new Book(maSach,tenSach,nxb,nph,theLoai,sl,gia);
					EditBook eb=new EditBook(SearchBook.this,b);
					eb.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			break;
		case 3:
			lblTmKim.setText("TÌM KIẾM SÁCH CẦN XOÁ");
			//Animation.placeHolder(textField, "Nhập tên sách cần xoá");
			table.addMouseListener(new MouseAdapter() {
				@Override 
				public void mouseClicked(MouseEvent e) {
					int option=JOptionPane.showConfirmDialog(SearchBook.this, "Bạn có chắc chắn muốn xoá");
					if(option==JOptionPane.YES_OPTION) {
						int selected=table.getSelectedRow();
						String maSach=table.getValueAt(selected, 0).toString();
						if(LibrarianController.delBook(maSach)) {
							JOptionPane.showMessageDialog(SearchBook.this, "Xoá thành công,vui lòng quay trở lại");
						}
					}
				}
			});
			break;
		}
	}

}
