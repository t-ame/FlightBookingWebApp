package com.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.java.components.Account;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.utils.MyDataSource;
import com.java.utils.RepositorySupport;

public class AdminRepository {

	private static AdminRepository rep=null;
	private static BasicDataSource ds;
	private static String errorMsg = "";

	static {
		try {
			ds = MyDataSource.getDataSource();
		} catch (GeneralException e) {
			errorMsg = "Loading Data Source failed: " + e.getMessage();
		}
	}
	
	private AdminRepository() {
		super();
	}
	
	public static AdminRepository getAdminRepository() {
		if(rep == null) {
			synchronized(AdminRepository.class) {
				if(rep == null)
					rep = new AdminRepository();
			}
		}
		return rep;
	}

	public User getUser(String userName) throws GeneralException {

		User user = null;
		
		String fetchUserSQL = "select * from Admins where user_name = ?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchUserSt = conn.prepareStatement(fetchUserSQL);) {

			fetchUserSt.setString(1, userName.toUpperCase());
			ResultSet set = fetchUserSt.executeQuery();
			
			List<User> ulist = RepositorySupport.mapToUser(set, true);
				if(ulist.size() > 0)
					user = ulist.get(0);
			
			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve user: " + e.getMessage());
		} catch(PatternSyntaxException e) {
			throw new GeneralException("Invalid address, contains special characters : " + e.getMessage());
		}

		return user;
	}

	public void addUser(User user) throws GeneralException {

		if(!user.isAdmin()) {
			throw new GeneralException("Cannot add Customer to Admin database.");
		}
		Account account = user.getAccount();
		
		String insertUserSQL = "insert into Admins (first_name, last_name, user_name, passwords, address, date_of_birth, gender)\n" + 
				"values (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ds.getConnection();
				PreparedStatement insertUserSt = conn.prepareStatement(insertUserSQL);) {
			insertUserSt.setString(1, account.getFirstName().toUpperCase());
			insertUserSt.setString(2, account.getLastName().toUpperCase());
			insertUserSt.setString(3, account.getUserName().toUpperCase());
			insertUserSt.setString(4, account.getPassword().toUpperCase());
			insertUserSt.setString(5, account.getConcatAddress().toUpperCase());
			insertUserSt.setDate(6, java.sql.Date.valueOf(account.getDateOfBirth()));
			if(account.getGender() == Account.Gender.FEMALE) {
				insertUserSt.setString(7, "female".toUpperCase());
			} else {
				insertUserSt.setString(7, "male".toUpperCase());
			}
			
			insertUserSt.executeUpdate();  
			conn.commit();
			
		} catch (SQLException e) {
			throw new GeneralException("Unable to Add user to db : " + e.getMessage());
		}
	}

	public void updateUser(User user) throws GeneralException {
		
		if(!user.isAdmin()) {
			throw new GeneralException("Cannot Update Customer in Admin database.");
		}
		Account account = user.getAccount();
		
		String updateUserSQL = "update Admins set first_name=?, last_name=?, user_name=?, passwords=?"
				+ 								"address=?, date_of_birth=?, gender=? where user_name=?";

		try (Connection conn = ds.getConnection();
				PreparedStatement updateUserSt = conn.prepareStatement(updateUserSQL);) {

			updateUserSt.setString(1, account.getFirstName().toUpperCase());
			updateUserSt.setString(2, account.getLastName().toUpperCase());
			updateUserSt.setString(3, account.getConcatAddress().toUpperCase());
			updateUserSt.setString(4, account.getPassword().toUpperCase());
			updateUserSt.setDate(5, java.sql.Date.valueOf(account.getDateOfBirth()));
			if(account.getGender() == Account.Gender.FEMALE) {
				updateUserSt.setString(6, "female".toUpperCase());
			} else {
				updateUserSt.setString(6, "male".toUpperCase());
			}
			updateUserSt.setString(7, account.getUserName().toUpperCase());
			
			updateUserSt.executeUpdate();  
			conn.commit();
			
		} catch (SQLException e) {
			throw new GeneralException("Unable to Update user db : " + e.getMessage());
		}
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
