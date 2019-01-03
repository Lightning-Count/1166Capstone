package mysql;

import java.sql.*;
import mysql.Database;
import itemList.Pkg;
import java.util.*;

public class Database 
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
	
	public static int login(String user, String pass)
	{
		int userId = -1;
		Connection dbConnect = null;
		String findUser = "SELECT * FROM account WHERE accountName = '" + user + "' AND accountPass = '" + pass + "'";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			java.sql.Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(findUser);
			rs.next();
			userId = rs.getInt("accountID");
			dbConnect.close();
			if(rs.wasNull())
			{
				return userId;
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	public static int checkUsertype(String user) {
		int usertype = -1;
		Connection dbConnect = null;
		String checkUsertype = "SELECT accountType FROM account WHERE "
				+ "accountName = '" + user + "'";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			java.sql.Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(checkUsertype);
			rs.next();
			usertype = rs.getInt("accountType");
			dbConnect.close();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usertype;
	}

	
	/**
	 * helper method for createUser,
	 * checks account table for existing account with name
	 * @param user
	 * @return true if found, false if not found
	 */
	private static boolean checkUser(String user)
	{
		boolean user_found = false;
		Connection dbConnect = null;
		String findUser = "SELECT accountName FROM account WHERE accountName = '" + user + "'";
		
		try 
		{
			System.out.println(findUser);
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(findUser);
			
			if(rs.next() == false)
			{
				System.out.println("User not found: " + user_found);
				dbConnect.close();			
				return user_found;
			}
			else
			{
				user_found = true;
				System.out.println("User found: " + user_found);
				dbConnect.close();			
			}
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_found;
	}

	
	/**
	 * takes username, pass, email, and creates a new user. returns -1 if failed or 1 if success.
	 * @param user
	 * @param pass
	 * @param email
	 * @return 1 if successful, -1 if unsuccessful
	 */
	public static boolean createUser(String user, String pass, String email, int accountType)
	{
		boolean userExists = checkUser(user);
		System.out.println("userExists = " + userExists);
		if(!userExists)
		{
			System.out.println("Attempting to create account.");
			Connection dbConnect = null;
			String createUser = "INSERT INTO account(accountName, accountPass, "
					+ "accountType, accountEmail) VALUES(?, ?, ?, ?)";
				
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
				PreparedStatement stmt = dbConnect.prepareStatement(createUser);
				stmt.setString(1, user);
				stmt.setString(2, pass);
				stmt.setInt(3, accountType);
				stmt.setString(4, email);
				stmt.executeUpdate();
				dbConnect.close();
			}
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	
		/**
		 * Various search methods for the user
		 */
	}
	
	/**
	 * The following methods all pertain to searching the database via package information.
	 * @author Brian Gimeno
	 */
	
	public static ArrayList<Pkg> getPackages(int userId, int usertype)
	{
		String getPackages;
		ArrayList<Pkg> Pkgs = new ArrayList<>();
		Connection dbConnect = null;
		if(usertype == 1)
		{
			getPackages = "SELECT package.trackingNumber, account.accountName, "
					+ "package.date_shipped, package.date_arrival, package.status "
					+ "FROM account, package WHERE package.package_business = account.accountID "
					+ "AND package.package_consumer = " + userId;
		}
		else
		{
			getPackages = "SELECT package.trackingNumber, account.accountName, "
					+ "package.date_shipped, package.date_arrival, package.status "
					+ "FROM account, package WHERE package.package_consumer = "
					+ "account.accountID AND package.package_business = " + userId;
		}
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			java.sql.Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(getPackages);
			while(rs.next())
			{
				Pkg item = new Pkg(
				rs.getString("trackingNumber"),
				rs.getString("accountName"),
				rs.getString("date_shipped"),
				rs.getString("date_arrival"),
				rs.getString("status"));
				
				Pkgs.add(item);
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Pkgs;
	}

	public static int getRowCount(int userID, int usertype) {
		
		int numRows = 0;
		String getRowCount;
		String countColumn = "\"count\"";
		Connection dbConnect = null;
		if(usertype == 1)
		{
			getRowCount = "SELECT COUNT(package_consumer) AS " + countColumn 
					+ "FROM package WHERE package_consumer = " + userID;
		}
		else
		{
			getRowCount = "SELECT COUNT(package_business) AS " + countColumn
					+ "FROM package WHERE package_business = " + userID;
		}
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			dbConnect = DriverManager.getConnection(db_path, db_username, db_password);
			java.sql.Statement stmt = dbConnect.createStatement();
			ResultSet rs = stmt.executeQuery(getRowCount);
			rs.next();
			numRows = rs.getInt("count");
			dbConnect.close();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Num rows: " + numRows);
		return numRows;
	}

	
}
