package com.financeSystem.loanAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.financeSystem.account.Account;

public class LoanAccountDao {
	private final float interestRate=5.5f;
	public int createAccount(String account_num, float deposit) throws ClassNotFoundException {
        String INSERT_ACCOUNT_SQL = "INSERT INTO loan_accounts" +
            "  (account_no, created_date, loan_balance, interestRate, interest_balance) VALUES " +
            " (?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            preparedStatement.setString(1, account_num);
            preparedStatement.setString(2, java.time.LocalDate.now().toString());
            preparedStatement.setFloat(3, deposit);
            preparedStatement.setFloat(4, interestRate);
            preparedStatement.setFloat(5, 0.0f);
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
	public boolean validate(String account_no) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from loan_accounts where account_no = ? ")) {
			preparedStatement.setString(1, account_no);
	

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return status;
	}
	public LoanAccount getAccountByNumber(String account_no) throws ClassNotFoundException {
		LoanAccount accountFromDb= new LoanAccount();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from loan_accounts where account_no = ?")) {
			preparedStatement.setString(1, account_no);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	accountFromDb.setAccount_no(rs.getString("account_no"));
					accountFromDb.setCreated_date(rs.getString("created_date"));
					accountFromDb.setInterestRate(rs.getFloat("interestRate"));
					accountFromDb.setLoan_balance(rs.getFloat("loan_balance"));
					accountFromDb.setInterest_balance(rs.getFloat("interest_balance"));
			 }

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return accountFromDb;
	}
	public int rePayment(String account_no, float amount) throws ClassNotFoundException {
		float loan_balance=0.0f;
		int result = 0;
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from loan_accounts where account_no = ?")) {
			preparedStatement.setString(1, account_no);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	
				 loan_balance=rs.getFloat("loan_balance");
			 }
			 float newLoanBalance=loan_balance-amount;
			 
			 PreparedStatement preparedStatement2 = connection
						.prepareStatement("UPDATE loan_accounts SET loan_balance = ? WHERE account_no = ?;");
			preparedStatement2.setFloat(1, newLoanBalance);
			preparedStatement2.setString(2, account_no);

			System.out.println(preparedStatement2);
			result = preparedStatement2.executeUpdate();

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
