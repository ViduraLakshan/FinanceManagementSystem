package com.financeSystem.listener;

import com.financeSystem.fixedDeposit.FixedDepositDao;

public class InterestCalculateScheduled implements Runnable {
	private FixedDepositDao fixedDepositDao;
    public void init() {
    	fixedDepositDao=new FixedDepositDao();
    }
	@Override
	public void run() {
		System.out.println("run");
		try {
			fixedDepositDao.dointerestCalculate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
