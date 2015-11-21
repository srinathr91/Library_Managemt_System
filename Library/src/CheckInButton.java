import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

public class CheckInButton extends JFrame {

	private JPanel contentPane;
	private JTextField textField_loanid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckInButton frame = new CheckInButton();
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
	public CheckInButton() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnter = new JLabel("Enter Loan id");
		lblEnter.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEnter.setBounds(109, 11, 91, 35);
		contentPane.add(lblEnter);
		
		textField_loanid = new JTextField();
		textField_loanid.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_loanid.setBounds(109, 41, 86, 20);
		contentPane.add(textField_loanid);
		textField_loanid.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="UPDATE book_loans SET Date_in = (curdate()) WHERE Loan_id=?;";                     
					PreparedStatement pst = connection.prepareStatement(query); 
					pst.setInt(1, Integer.parseInt(textField_loanid.getText()));//setString(1, textField_loanid.getText());
	                pst.execute();
	                 JOptionPane.showMessageDialog(null, "Date_in updated");
	                 dispose();
				}
				catch(Exception ex){
					//ex.printStackTrace();
					//JOptionPane.showConfirmDialog(null, "Please enter a valid Loan_id");
					JOptionPane.showMessageDialog(null,"Please enter a valid Loan_id");
				}
			}
		});
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnConfirm.setBounds(30, 76, 89, 23);
		contentPane.add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Checkin().setVisible(true);
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBack.setBounds(174, 76, 89, 20);
		contentPane.add(btnBack);
	}

}
