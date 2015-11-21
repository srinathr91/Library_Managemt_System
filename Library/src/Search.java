import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;


public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bookid;
	private JTextField textField_title;
	private JTextField textField_Authorname;
	private JTable table_search_result;
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JButton btnMenu;

	/**
	 * Create the frame.
	 */
	public Search() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_BookId = new JLabel("Book ID");
		lbl_BookId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_BookId.setBounds(224, 13, 61, 20);
		contentPane.add(lbl_BookId);
		
		textField_bookid = new JTextField();
		textField_bookid.setFont(new Font("Times New Roman", Font.BOLD, 11));
		textField_bookid.setBounds(295, 12, 111, 20);
		contentPane.add(textField_bookid);
		textField_bookid.setColumns(10);
		
		JLabel lbl_Title = new JLabel("Title");
		lbl_Title.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_Title.setBounds(478, 65, 77, 20);
		contentPane.add(lbl_Title);
		
		textField_title = new JTextField();
		textField_title.setFont(new Font("Times New Roman", Font.BOLD, 11));
		textField_title.setBounds(355, 86, 335, 20);
		contentPane.add(textField_title);
		textField_title.setColumns(10);
		
		JLabel lbl_Authorname = new JLabel("Author's Name");
		lbl_Authorname.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_Authorname.setBounds(141, 65, 119, 20);
		contentPane.add(lbl_Authorname);
		
		textField_Authorname = new JTextField();
		textField_Authorname.setFont(new Font("Times New Roman", Font.BOLD, 11));
		textField_Authorname.setBounds(10, 86, 335, 20);
		contentPane.add(textField_Authorname);
		textField_Authorname.setColumns(10);
		
		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{					
					//String query="select * from borrower;";//use library2;
					// String query= "SELECT DISTINCT LIBRARY_BRANCH.Branch_id,a.book_id,a.title,book_authors.author_name,BOOK_COPIES.No_of_copies as total , ( BOOK_COPIES.No_of_copies- COUNT(BOOK_LOANS.Book_id)) AS Avail FROM (((Book as a INNER JOIN BOOK_COPIES ON a.book_id = BOOK_COPIES.Book_id INNER JOIN BOOK_AUTHORS ON a.Book_id = book_authors.Book_id) INNER JOIN LIBRARY_BRANCH  ON BOOK_COPIES.Branch_id = LIBRARY_BRANCH.Branch_id) LEFT OUTER JOIN BOOK_LOANS  ON  BOOK_LOANS.book_id=a.Book_id AND BOOK_LOANS.Branch_id = BOOK_COPIES.Branch_id AND book_loans.date_in is NULL) Where a.book_id in (select book_id from book_authors where author_name like '%"+textField_Authorname.getText()+"%' and  book_authors.fname like '%"+textField_fname.getText()+"%' and (book_authors.minit like '%"+textField_mname.getText()+"%' or book_authors.minit is NULL)and book_authors.lname like '%"+textField_Lname.getText()+"%')  GROUP BY book_copies.Branch_id,book_loans.book_id,a.title Having (a.book_id like '%"+textField_bookid.getText()+"%' and a.title like '%"+textField_title.getText()+"%' )order by LIBRARY_BRANCH.Branch_id;";					  
					String query="SELECT DISTINCT LIBRARY_BRANCH.Branch_id,a.book_id,a.title,book_authors.author_name,BOOK_COPIES.No_of_copies as total , ( BOOK_COPIES.No_of_copies- COUNT(BOOK_LOANS.Book_id)) AS Avail FROM (((Book as a INNER JOIN BOOK_COPIES ON a.book_id = BOOK_COPIES.Book_id INNER JOIN BOOK_AUTHORS ON a.Book_id = book_authors.Book_id) INNER JOIN LIBRARY_BRANCH  ON BOOK_COPIES.Branch_id = LIBRARY_BRANCH.Branch_id) LEFT OUTER JOIN BOOK_LOANS  ON  BOOK_LOANS.book_id=a.Book_id AND BOOK_LOANS.Branch_id = BOOK_COPIES.Branch_id AND book_loans.date_in is NULL) Where a.book_id in (select book_id from book_authors where author_name like '%"+textField_Authorname.getText()+"%' )  GROUP BY book_copies.Branch_id,book_loans.book_id,a.title Having (a.book_id like '%"+textField_bookid.getText()+"%' and a.title like '%"+textField_title.getText()+"%' )order by LIBRARY_BRANCH.Branch_id;";					  
					
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs1=pst.executeQuery();
					table_search_result.setModel(DbUtils.resultSetToTableModel(rs1));
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btn_search.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_search.setBounds(10, 52, 89, 23);
		contentPane.add(btn_search);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 680, 298);
		contentPane.add(scrollPane);
		
		table_search_result = new JTable();
		scrollPane.setViewportView(table_search_result);
		table_search_result.setFont(new Font("Times New Roman", Font.BOLD, 11));
		
		scrollBar = new JScrollBar();
		scrollBar.setBounds(673, 276, 17, 48);
		contentPane.add(scrollBar);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menubar().setVisible(true);
			}
		});
		btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnMenu.setBounds(10, 10, 89, 23);
		contentPane.add(btnMenu);
	}

}
