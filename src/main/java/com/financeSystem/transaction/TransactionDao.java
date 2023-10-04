package com.financeSystem.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionDao {

	public int doTransaction(String account_no, String transactionType, float amount, float balance) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//Account accountFromDb= new Account();
		float sub=0.0f;
		if(transactionType != null && transactionType.equals("deposit")) {
			sub=amount+balance;
			System.out.println(transactionType);
		}else{
			System.out.println(sub);
			sub=balance-amount;
		}
		System.out.println(transactionType);
		Class.forName("com.mysql.jdbc.Driver");
		int result = 0;

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE accounts SET balance = ? WHERE account_no = ?;")) {
			preparedStatement.setFloat(1, sub);
			preparedStatement.setString(2, account_no);

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return result;
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
