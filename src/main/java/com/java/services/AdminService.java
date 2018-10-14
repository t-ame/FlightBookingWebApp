package com.java.services;

import com.java.components.User;
import com.java.exception.GeneralException;
import com.java.repositories.AdminRepository;
import com.java.repositories.CutomerRepository;

public class AdminService {
	
	private final AdminRepository repository;
	
	public AdminService() {
		super();
		repository = AdminRepository.getAdminRepository();
	}

	public User getUser(String userName)  throws GeneralException{

		return repository.getUser(userName);
	}

	public void addUser(User user)  throws GeneralException{
		repository.addUser(user);
	}

	public void updateUser(User user) throws GeneralException {
		repository.updateUser(user);
	}
	
	public String getErrorMsg() {
		return repository.getErrorMsg();
	}
}
