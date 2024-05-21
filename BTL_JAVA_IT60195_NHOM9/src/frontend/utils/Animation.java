package frontend.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Animation {
	public static void onHoover(JPanel p,Color o,Color n) {
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				p.setBackground(n);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				p.setBackground(o);
			}
		});
	}
	public static void placeHolder(JTextField t,String text) {
		t.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				t.setText("");
				t.setForeground(new Color(0,0,0));
			}
			public void focusLost(FocusEvent e) {
				if(t.getText().equals("")) {
				t.setText(text);
				t.setForeground(new Color(192,192,192));
				}
			}
		});
	}
	public static void f_placeHolder(JTextField t,JComboBox c) {
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected=(String) c.getSelectedItem();
				t.setText("Nhập tên "+selected.toLowerCase()+" cần tìm");
				t.setForeground(new Color(192,192,192));
			}
		});
		t.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				t.setText("");
				t.setForeground(new Color(0,0,0));
			}
		});
	}
}
