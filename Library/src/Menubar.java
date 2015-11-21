import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menubar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menubar frame = new Menubar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menubar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMenu.setBounds(116, 0, 40, 38);
		contentPane.add(lblMenu);
		
		JButton btnBookSearch = new JButton("Book Search");
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Search().setVisible(true);
			}
		});
		btnBookSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBookSearch.setBounds(10, 52, 108, 23);
		contentPane.add(btnBookSearch);
		
		JButton btnBorrowerManagement = new JButton("Borrower Management");
		btnBorrowerManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BorrManage().setVisible(true);
			}
		});
		btnBorrowerManagement.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBorrowerManagement.setBounds(146, 52, 166, 23);
		contentPane.add(btnBorrowerManagement);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Checkin().setVisible(true);
			}
		});
		btnCheckIn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCheckIn.setBounds(10, 119, 108, 23);
		contentPane.add(btnCheckIn);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Checkout().setVisible(true);
			}
		});
		btnCheckOut.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCheckOut.setBounds(10, 185, 108, 23);
		contentPane.add(btnCheckOut);
		
		JButton btnFines = new JButton("Fines");
		btnFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Fines().setVisible(true);
			
					
			}
		});
		btnFines.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnFines.setBounds(146, 119, 166, 23);
		contentPane.add(btnFines);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnExit.setBounds(146, 185, 166, 23);
		contentPane.add(btnExit);
	}

}
