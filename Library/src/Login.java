import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textField_usename;
	private JPasswordField textField_password;
	private JButton btnLogin;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=DBconnect.DBconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 644, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Label_username = new JLabel("Username");
		Label_username.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Label_username.setBounds(220, 47, 67, 50);
		frame.getContentPane().add(Label_username);
		
		JLabel Label_password = new JLabel("Password");
		Label_password.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Label_password.setBounds(220, 130, 67, 50);
		frame.getContentPane().add(Label_password);
		
		textField_usename = new JTextField();
		textField_usename.setBounds(320, 59, 200, 29);
		frame.getContentPane().add(textField_usename);
		textField_usename.setColumns(10);
		
		textField_password = new JPasswordField();
		textField_password.setEchoChar('*');
		textField_password.setBounds(320, 142, 200, 29);
		frame.getContentPane().add(textField_password);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_usename.getText().equals("srinath")&& textField_password.getText().equals("srinathr")){
					JOptionPane.showMessageDialog(null,"Login Successful");	
					frame.dispose();
					//Search se=new Search();
					//se.setVisible(true);
					//new Search().setVisible(true);
					//new BorrManage().setVisible(true);
					//new Checkout().setVisible(true);
					new Menubar().setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "Login Failed. Please enter the correct username and password");
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLogin.setBounds(340, 206, 145, 50);
		frame.getContentPane().add(btnLogin);
	}
}
