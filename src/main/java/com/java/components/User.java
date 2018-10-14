package com.java.components;

public class User {

	private int id;
	private boolean isAdmin;
	Account account;

	public User(boolean isAdmin, Account account) {
		super();
		this.isAdmin = isAdmin;
		this.account = account;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", isAdmin=" + isAdmin + ", account=" + account + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
