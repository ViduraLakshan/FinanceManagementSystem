package com.financeSystem.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.financeSystem.registration.model.User;

public class AccountDao {
	private final float interestRate=8.5f;
	
	public int createAccount(Account account) throws ClassNotFoundException {
        String INSERT_ACCOUNT_SQL = "INSERT INTO accounts" +
            "  (account_no, account_type, balance , username, interestRate, interest_earned) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            preparedStatement.setString(1, account.getAccount_no());
            preparedStatement.setString(2, account.getAccount_type());
            preparedStatement.setFloat(3, account.getBalance());
            preparedStatement.setString(4, account.getUserName());
            preparedStatement.setFloat(5, interestRate);
            preparedStatement.setFloat(6, 0.0f);
            //User userFromDb= new User();
            
          

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
	public boolean validate(String username) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from accounts where username = ? ")) {
			preparedStatement.setString(1, username);
	

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return status;
	}
	public Account getUserByName(String name) throws ClassNotFoundException {
		Account accountFromDb= new Account();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from accounts where username = ?")) {
			preparedStatement.setString(1, name);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	accountFromDb.setAccount_no(rs.getString("account_no"));
					accountFromDb.setBalance(rs.getFloat("balance"));
					accountFromDb.setAccount_type(rs.getString("account_type"));
					accountFromDb.setUserName(rs.getString("username"));
					accountFromDb.setInterestRate(rs.getFloat("interestRate"));
					accountFromDb.setInterestEarned(rs.getFloat("interest_earned"));
			 }

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return accountFromDb;
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
