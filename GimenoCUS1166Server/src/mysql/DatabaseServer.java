package mysql;

import java.sql.*;
import java.util.*;

public class DatabaseServer
{

	private static String db_path = "jdbc:mysql://127.0.0.1:3306/cus1166final";
	private static String db_username = "root";
	private static String db_password = "";
		
	
	/**
	 * Finds user account, checks credentials, and checks user type
	 * @param name
	 * @param pass
	 * 
	 * @return -1 (invalid credentials/not found)
	 * 0 (business account)
	 * 1 (consumer account) 
	 */
	
	public static ArrayList<String> getEmails()
	{
		Connection dbConnect = null;
		
		
		String returnUsers = "SELECT accountEmail FROM account WHERE accountType = 1";
		ArrayList<String> emails = new ArrayList<>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(returnUsers);
			while(rs.next())
			{
				String email = rs.getString("accountEmail");
				emails.add(email);
				System.out.println(email);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return emails;
	}

	
}
