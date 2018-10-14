package com.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;

import com.java.components.Account;
import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.utils.MyDataSource;
import com.java.utils.MyFlyway;
import com.java.utils.RepositorySupport;

public class CutomerRepository {

	private static CutomerRepository rep=null;
	private static BasicDataSource ds;
	private static String errorMsg = "";
	
	static {
		try {
			ds = MyDataSource.getDataSource();
		} catch (GeneralException e) {
			errorMsg = "Loading Data Source failed: "+e.getMessage();
		}
	}
	
	private CutomerRepository() {
		super();
	}
	
	public static CutomerRepository getCutomerRepository() {
		if(rep == null) {
			synchronized(CutomerRepository.class) {
				if(rep == null)
					rep = new CutomerRepository();
			}
		}
		return rep;
	}

	public User getUser(String userName)  throws GeneralException{

		User user = null;
		String fetchUserSQL = "select * from Customers where user_name= ?";

		try (Connection conn = ds.getConnection();
				PreparedStatement fetchUserSt = conn.prepareStatement(fetchUserSQL);) {

			fetchUserSt.setString(1, userName.toUpperCase());
			ResultSet set = fetchUserSt.executeQuery();

//			if(set.getFetchSize() > 0) {
			List<User> ulist = RepositorySupport.mapToUser(set, true);
			if(ulist.size() > 0)
				user = ulist.get(0);
//			} 
			
			set.close();
		} catch (SQLException e) {
			throw new GeneralException("Unable to retrieve user: " + e.getMessage());
		} catch(PatternSyntaxException e) {
			throw new GeneralException("Invalid address, contains special characters : " + e.getMessage());
		}
		
		return user;
	}

	public void addUser(User user)  throws GeneralException{

		if(user.isAdmin()) {
			throw new GeneralException("Cannot add admin to Customer database.");
		}
		Account account = user.getAccount();
		
		String insertUserSQL = "insert into Customers (first_name, last_name, user_name, passwords, address, date_of_birth, gender)\n" + 
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
		if(user.isAdmin()) {
			throw new GeneralException("Cannot update admin in Customer database.");
		}
		Account account = user.getAccount();
		
		String updateUserSQL = "update Customers set first_name=?, last_name=?, user_name=?, passwords=?"
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
			throw new GeneralException("Unable to Update user in db : " + e.getMessage());
		}
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
}
