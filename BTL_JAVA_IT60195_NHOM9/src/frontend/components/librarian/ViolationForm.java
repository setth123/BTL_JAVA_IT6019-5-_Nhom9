package frontend.components.librarian;



import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ViolationForm extends JFrame {

	private static final long serialVersionUID = 1L;



	/**
	 * Create the frame.
	 */
	public ViolationForm() {
		setBounds(300,300,800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Tạo phiếu vi phạm");
		getContentPane().setLayout(null);
		
		JLabel Title = new JLabel("DANH SÁCH PHIẾU MƯỢN");
		Title.setForeground(Color.GRAY);
		Title.setFont(new Font("Tahoma", Font.BOLD, 25));
		Title.setBounds(94, 92, 354, 31);
		getContentPane().add(Title);
	}

}
