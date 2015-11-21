import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Logger;

import javax.swing.*;


public class Checkout extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bookid;
	private JTextField textField_branchid;
	private JTextField textField_cardno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout frame = new Checkout();
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
	public Checkout() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCheckOut.setBounds(275, 11, 72, 50);
		contentPane.add(lblCheckOut);
		
		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBranchId.setBounds(29, 126, 62, 37);
		contentPane.add(lblBranchId);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBookId.setBounds(29, 56, 62, 37);
		contentPane.add(lblBookId);
		
		textField_bookid = new JTextField();
		textField_bookid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_bookid.setBounds(119, 64, 187, 20);
		contentPane.add(textField_bookid);
		textField_bookid.setColumns(10);
		
		textField_branchid = new JTextField();
		textField_branchid.setBounds(119, 134, 187, 20);
		contentPane.add(textField_branchid);
		textField_branchid.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCardNumber.setBounds(29, 201, 80, 37);
		contentPane.add(lblCardNumber);
		
		textField_cardno = new JTextField();
		textField_cardno.setBounds(119, 209, 187, 20);
		contentPane.add(textField_cardno);
		textField_cardno.setColumns(10);
		
		JButton btnCheckOut = new JButton("check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        if(textField_bookid.getText().trim().length()==0 || textField_branchid.getText().trim().length()==0 || textField_cardno.getText().trim().length()==0)
			        {
			            JOptionPane.showMessageDialog(null,"Enter all fields");
			        }
			        else if(textField_bookid.getText().trim().length()!=0 && textField_branchid.getText().trim().length()!=0 && textField_cardno.getText().trim().length()!=0)
			            {
			        	//System.out.println("abcd");
			        	String query="select Card_no from book_loans, fines where book_loans.Loan_id=fines.Loan_id and paid=false;";
			        	PreparedStatement pst1 = connection.prepareStatement(query);			        	
			        	ResultSet rs5=pst1.executeQuery();	
			        	//System.out.println("abc");
			        	int lm=0;
			        	while(rs5.next()){
			        	int d=rs5.getInt(1);			        	
			        	System.out.println(d);			        	
			        	int c=Integer.parseInt(textField_cardno.getText());
			        	if(c==d){
			        		JOptionPane.showMessageDialog(null,"Pay fines to checkout books");
			        		lm=1;
			        		break;
			        	}
			        	}
			        	if(lm==0){
			            String SQL="SELECT count(*) from book_loans where Card_no LIKE '"+textField_cardno.getText()+"';";
			            PreparedStatement pst = connection.prepareStatement(SQL);			            
			            int x;
			            ResultSet rs=pst.executeQuery();
			             while(rs.next())
			            {x=rs.getInt("count(*)");
			            
			            String SQL3="select (bc.no_of_copies-count(bl.book_id)) as avail from book_copies bc, book_loans bl,book b where bc.book_id=bl.book_id and bc.branch_id=bl.branch_id and b.book_id = bc.book_id and b.book_id = bl.book_id and bl.book_id = '"+textField_bookid.getText()+"' and bl.branch_id = '"+textField_branchid.getText()+"' and bl.Date_in is null;";
			            PreparedStatement pst3 =  connection.prepareStatement(SQL3);
			            ResultSet rs2=null;
			            int y;
			            rs2=pst3.executeQuery();
			            while(rs2.next())
			            {
			            y=rs2.getInt("avail");
			            //System.out.println(y);
			            if(x!=3 && y!=0)
			            {
			                String SQL2="INSERT INTO book_loans(Book_id,Branch_id,Card_no,Date_out,Due_date) VALUES('"+textField_bookid.getText()+"','"+textField_branchid.getText()+"','"+textField_cardno.getText()+"',curdate(),date_add(Date_out,INTERVAL 14 DAY));";
			                PreparedStatement stmt2 = connection.prepareStatement(SQL2);
			                stmt2.executeUpdate();
			                JOptionPane.showMessageDialog(null,"Updated");
			            }
			            else if(x==3)
			            {
			                JOptionPane.showMessageDialog(null,"ACCESS DENIED \n Maximum of of 3 books per user");
			                
			            }
			            else if(y==0)
			            {
			                JOptionPane.showMessageDialog(null,"No books available");
			            }
			            }
			            } 
			           }
			            }
			            
			       }catch (Exception ex) {
			    	   //System.out.println(ex);
			                 JOptionPane.showMessageDialog(null,"Invalid card number");
			                }


	
			}
		});
		btnCheckOut.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCheckOut.setBounds(401, 133, 89, 23);
		contentPane.add(btnCheckOut);
		
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
	}
}
