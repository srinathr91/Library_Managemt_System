import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class Fines extends JFrame {

	private JPanel contentPane;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fines frame = new Fines();
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
	public Fines() {
		connection=DBconnect.DBconnector();;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFinesTable = new JLabel("Fines Table");
		lblFinesTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblFinesTable.setBounds(285, 11, 89, 14);
		contentPane.add(lblFinesTable);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menubar().setVisible(true);
				
			}
		});
		btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnMenu.setBounds(0, 0, 89, 23);
		contentPane.add(btnMenu);
		
		JButton btnShowFines = new JButton("Show Fines");
		btnShowFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="INSERT INTO FINES(Loan_id,Fine_amt,paid)(select BL.Loan_id , case when (BL.Date_in is null) THEN datediff(curdate(),BL.Due_date)*0.25 ELSE datediff(BL.Date_in,BL.Due_date)*0.25 END,False From BOOK_LOANS BL LEFT JOIN FINES BF ON BF.Loan_id = BL.Loan_id where BF.Loan_id is null AND (BL.Due_date < BL.Date_in OR (BL.Date_in is null and BL.Due_date < curdate() )));";
					String query1="UPDATE FINES F JOIN BOOK_LOANS BL ON F.Loan_id = BL.Loan_id SET F.fine_amt = datediff(curdate(),BL.Due_date)*0.25 where BL.Date_in is null and F.paid = FALSE and fine_amt <> datediff(curdate(),Due_date)*0.25;";
				    PreparedStatement pst=connection.prepareStatement(query);
				    PreparedStatement pst1=connection.prepareStatement(query1);
					pst.executeUpdate();
					pst1.executeUpdate();
					String query2="select BL.card_no, fine_amt,paid from BOOK_LOANS BL JOIN FINES F ON F.Loan_id = BL.Loan_id where BL.Date_in is not null and F.PAID = FALSE group by BL.card_no;";		
					PreparedStatement pst2=connection.prepareStatement(query2);
					ResultSet rs=pst2.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs));
					//System.out.println("Success");
					
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnShowFines.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnShowFines.setBounds(0, 34, 104, 23);
		contentPane.add(btnShowFines);
		
		JButton btnSettleFines = new JButton("Settle Fines");
		btnSettleFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CardnoDialogBox().setVisible(true);
				
			}
		});
		btnSettleFines.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSettleFines.setBounds(253, 34, 104, 23);
		contentPane.add(btnSettleFines);
		
		JButton btnRefrshTable = new JButton("Refresh Table");
		btnRefrshTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//String query2="select BL.card_no, sum(F.fine_amt) from BOOK_LOANS BL JOIN FINES F ON F.Loan_id = BL.Loan_id where BL.Date_in is not null and F.PAID = FALSE group by BL.card_no;";		
					String query2="select * from fines;";
					PreparedStatement pst2=connection.prepareStatement(query2);
					ResultSet rs=pst2.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs));
					//System.out.println("Success");
					
				}
				catch(Exception ex){
					ex.printStackTrace();
					
				}
				
			}
		});
		btnRefrshTable.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnRefrshTable.setBounds(462, 34, 115, 23);
		contentPane.add(btnRefrshTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 679, 338);
		contentPane.add(scrollPane);
		
		table2 = new JTable();
		scrollPane.setViewportView(table2);
	}
}
