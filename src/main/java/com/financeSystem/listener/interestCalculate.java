package com.financeSystem.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.financeSystem.fixedDeposit.FixedDepositAccount;

public class interestCalculate {
	
	public void dointerestCalculate() throws ClassNotFoundException {
		List<FixedDepositAccount> fixedDepositAccountList=new ArrayList<>();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from fixed_accounts ")) {

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	
				 FixedDepositAccount rowData = new FixedDepositAccount();
				 rowData.setAccount_no(rs.getString("account_no"));
				 rowData.setInterestRate(rs.getFloat("interestRate"));
				 rowData.setFixed_balance(rs.getFloat("fixed_balance"));
				
				 fixedDepositAccountList.add(rowData);
			 }
			 for (FixedDepositAccount rowData : fixedDepositAccountList) {
				 float interest_earned=(rowData.getFixed_balance()*rowData.getInterestRate())/100;
			 
				 PreparedStatement preparedStatement2 = connection
						.prepareStatement("UPDATE fixed_accounts SET interest_earned = ? WHERE account_no = ?;");
				 preparedStatement2.setFloat(1, interest_earned);
				 preparedStatement2.setString(2, rowData.getAccount_no());
				 System.out.println(preparedStatement2);
				 preparedStatement2.executeUpdate();
			 }
			

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
			
	}
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
