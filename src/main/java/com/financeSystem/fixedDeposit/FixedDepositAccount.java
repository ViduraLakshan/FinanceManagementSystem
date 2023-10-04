package com.financeSystem.fixedDeposit;


public class FixedDepositAccount {
	private String account_no;
	private String created_date;
	private String maturity_date;
	private float fixed_balance;
	private float interestRate;
	private float interestEarned;
	
	public float getFixed_balance() {
		return fixed_balance;
	}
	public void setFixed_balance(float fixed_balance) {
		this.fixed_balance = fixed_balance;
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
	public String getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
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
	
	
}
