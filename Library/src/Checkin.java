import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Checkin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bookid;
	private JTextField textField_cardno;
	private JTextField textField_fname;
	private JTextField textField_lname;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkin frame = new Checkin();
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
	public Checkin() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Check In Form");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(234, 5, 94, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblBookId.setBounds(19, 28, 48, 20);
		contentPane.add(lblBookId);
		
		textField_bookid = new JTextField();
		textField_bookid.setFont(new Font("Times New Roman", Font.BOLD, 11));
		textField_bookid.setBounds(77, 29, 126, 20);
		contentPane.add(textField_bookid);
		textField_bookid.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCardNumber.setBounds(267, 30, 73, 17);
		contentPane.add(lblCardNumber);
		
		textField_cardno = new JTextField();
		textField_cardno.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_cardno.setBounds(350, 28, 110, 20);
		contentPane.add(textField_cardno);
		textField_cardno.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblFirstName.setBounds(10, 59, 66, 20);
		contentPane.add(lblFirstName);
		
		textField_fname = new JTextField();
		textField_fname.setBounds(77, 60, 165, 20);
		contentPane.add(textField_fname);
		textField_fname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblLastName.setBounds(277, 58, 63, 21);
		contentPane.add(lblLastName);
		
		textField_lname = new JTextField();
		textField_lname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_lname.setBounds(350, 59, 173, 20);
		contentPane.add(textField_lname);
		textField_lname.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		//final Long d=Date.UTC(0000, 00, 00,00,00,00);
		final String dd="0000-00-00";		
		//System.out.println(d);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_lname.getText().equals("")&& textField_cardno.getText().equals("")&& textField_fname.getText().equals("")&&textField_lname.getText().equals("")){
					JOptionPane.showConfirmDialog(null, "Give atleast one search field value");
				}
				else{
					try{
						String query= "SELECT  book_loans.Loan_id,book_loans.Book_id ,book_loans.Card_no ,borrower.fname,borrower.Lname ,book_loans.Date_out,book_loans.Due_date,book_loans.Date_in from book_loans , book , borrower where book_loans.Book_id like '%"+textField_bookid.getText()+"%' and book_loans.Card_no like '%"+textField_cardno.getText()+"%' and  (borrower.Fname like '%"+textField_fname.getText()+"%'  or borrower.Lname like '%"+textField_lname.getText()+"%')  and book.Book_id= book_loans.Book_id and book_loans.Card_no = borrower.Card_no and date_in is NULL;";
						//String query= "SELECT  book_loans.Loan_id,book_loans.Book_id ,book_loans.Card_no ,borrower.fname,borrower.Lname ,book_loans.Date_out,book_loans.Due_date,book_loans.Date_in from book_loans , book , borrower where book_loans.Book_id like '%"+textField_bookid.getText()+"%' and book_loans.Card_no like '%"+textField_cardno.getText()+"%' and  (borrower.Fname like '%"+textField_fname.getText()+"%'  or borrower.Lname like '%"+textField_lname.getText()+"%')  and book.Book_id= book_loans.Book_id and book_loans.Card_no = borrower.Card_no and (date_in is NULL or date_in="+"0000-00-00"+");";		                  
						PreparedStatement pst = connection.prepareStatement(query); 
		                 ResultSet rs=pst.executeQuery();
		                 table1.setModel(DbUtils.resultSetToTableModel(rs));
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSearch.setBounds(534, 27, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckInButton().setVisible(true);
			}
		});
		btnCheckIn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCheckIn.setBounds(534, 58, 89, 23);
		contentPane.add(btnCheckIn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 684, 312);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menubar().setVisible(true);
			}
		});
		btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnMenu.setBounds(10, 1, 89, 23);
		contentPane.add(btnMenu);
	}
}
