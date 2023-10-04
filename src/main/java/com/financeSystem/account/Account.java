package com.financeSystem.account;

import java.io.Serializable;

public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String account_no;
    private String userName;
    private String account_type;
    private float balance;
    private float interestRate;
	private float interestEarned;
	
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public float getInterestEarned() {
		return interestEarned;
	}
	public void setInterestEarned(float interestEarned) {
		this.interestEarned = interestEarned;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
    
    
}

    