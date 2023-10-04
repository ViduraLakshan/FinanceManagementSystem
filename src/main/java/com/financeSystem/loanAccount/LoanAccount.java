package com.financeSystem.loanAccount;

import java.util.Date;

public class LoanAccount {
	private String account_no;
	private String created_date;
	private float interestRate;
	private float loan_balance;
	private float interest_balance;
	
	public float getInterest_balance() {
		return interest_balance;
	}
	public void setInterest_balance(float interest_balance) {
		this.interest_balance = interest_balance;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	public float getLoan_balance() {
		return loan_balance;
	}
	public void setLoan_balance(float loan_balance) {
		this.loan_balance = loan_balance;
	}
	
}
