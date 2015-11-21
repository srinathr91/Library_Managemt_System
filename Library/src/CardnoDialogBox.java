import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

public class CardnoDialogBox extends JFrame {

	private JPanel contentPane;
	private JTextField textField_cardno1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardnoDialogBox frame = new CardnoDialogBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;

	/**
	 * Create the frame.
	 */
	public CardnoDialogBox() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_cardno1 = new JTextField();
		textField_cardno1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_cardno1.setBounds(115, 65, 86, 20);
		contentPane.add(textField_cardno1);
		textField_cardno1.setColumns(10);
		
		JLabel lblEnterCardNumber = new JLabel("Enter Card number");
		lblEnterCardNumber.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblEnterCardNumber.setBounds(107, 40, 120, 14);
		contentPane.add(lblEnterCardNumber);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a=textField_cardno1.getText();
				try {
					
					int cardno=Integer.parseInt(a);						
				String sql="UPDATE FINES F JOIN BOOK_LOANS BL ON F.Loan_id = BL.Loan_id SET F.paid = TRUE where BL.Date_in is not null and F.paid = FALSE and BL.card_no = '"+cardno+"';";		
				PreparedStatement sqlstmt=connection.prepareStatement(sql);
				int r=sqlstmt.executeUpdate(sql);

				if(r!=0)
				{
					JOptionPane.showMessageDialog(null, "Fines table updated");
					dispose();
				}
						
				//dispose();		
				JOptionPane.showConfirmDialog(null, "Card number not in the list of fines");	
				dispose();
						
					} catch (Exception e1) {
					 JOptionPane.showConfirmDialog(null, "Invalid card number");
					 dispose();
						e1.printStackTrace();
					}
			}
		});
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnOk.setBounds(115, 92, 89, 23);
		contentPane.add(btnOk);
	}
}
