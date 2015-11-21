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

import net.proteanit.sql.DbUtils;

public class BorrManage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_fname;
	private JTextField textField_lname;
	private JTextField textField_pno;
	private JTextField textField_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrManage frame = new BorrManage();
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
	public BorrManage() {
		connection=DBconnect.DBconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Borrower Management");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(230, 11, 151, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 90, 64, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(296, 86, 50, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 160, 200, 50);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone Number");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(296, 175, 85, 20);
		contentPane.add(lblNewLabel_4);
		
		textField_fname = new JTextField();
		textField_fname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_fname.setBounds(74, 94, 200, 20);
		contentPane.add(textField_fname);
		textField_fname.setColumns(10);
		
		textField_lname = new JTextField();
		textField_lname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_lname.setBounds(74, 175, 200, 20);
		contentPane.add(textField_lname);
		textField_lname.setColumns(10);
		
		textField_pno = new JTextField();
		textField_pno.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_pno.setBounds(382, 175, 157, 20);
		contentPane.add(textField_pno);
		textField_pno.setColumns(10);
		
		textField_address = new JTextField();
		textField_address.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		textField_address.setBounds(356, 90, 245, 24);
		contentPane.add(textField_address);
		textField_address.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_fname.getText().equals("")||textField_lname.getText().equals("")||textField_address.getText().equals("")|| textField_pno.getText().equals(""))
			    {			        
			        JOptionPane.showMessageDialog(null,"Please enter values in all the fields");	
			    }
			else{
				try{     
			           
			           String query1 = "INSERT INTO Borrower (Fname,Lname,Address,Phone) VALUES(?,?,?,?);";
			           PreparedStatement pst1 = connection.prepareStatement(query1);
			           pst1.setString(1,textField_fname.getText());
			           pst1.setString(2,textField_lname.getText());
			           pst1.setString(3,textField_address.getText());
			           pst1.setString(4,textField_pno.getText());
			           pst1.execute();
			           JOptionPane.showMessageDialog(null,"Data Saved");
			           pst1.close();
			          // pst.close();
			          // rs.close();
			             }catch (SQLException ex) {
			            	 //ex.printStackTrace();
			            	 JOptionPane.showMessageDialog(null,"Data entered already exists");
			             } 
				
			                     
			           }	
			}
		});
		btnEnter.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnEnter.setBounds(121, 227, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Menubar().setVisible(true);
			}
		});
		btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnMenu.setBounds(10, 0, 89, 23);
		contentPane.add(btnMenu);
	}

}
