import java.sql.*;

import javax.swing.*;
public class DBconnect {
	public static Connection conn=null;
	public static Connection DBconnector()
	{
		try{
			//Class.forName("org.mysql.JDBC");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "srinathr");
			Statement stmt = conn.createStatement();
			stmt.execute("use library2;");
			//System.out.println("Connected to Library Database");
			//JOptionPane.showMessageDialog(null, "Connected to Library Database");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
