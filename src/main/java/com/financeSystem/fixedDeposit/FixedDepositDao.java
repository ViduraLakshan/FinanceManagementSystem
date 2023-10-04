package com.financeSystem.fixedDeposit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.financeSystem.loanAccount.LoanAccount;

public class FixedDepositDao {
	private final float interestRate=8.5f;
	
	public int createAccount(String account_num, float deposit) throws ClassNotFoundException {
        String INSERT_ACCOUNT_SQL = "INSERT INTO fixed_accounts" +
            "  (account_no, created_date, maturity_date, fixed_balance, interestRate, interest_earned) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            preparedStatement.setString(1, account_num);
            preparedStatement.setString(2, java.time.LocalDate.now().toString());
            preparedStatement.setString(3, java.time.LocalDate.now().plusYears(2).toString());
            preparedStatement.setFloat(4, deposit);
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
	public boolean validate(String account_no) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from fixed_accounts where account_no = ? ")) {
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
	public FixedDepositAccount getAccountByNumber(String account_no) throws ClassNotFoundException {
		FixedDepositAccount accountFromDb= new FixedDepositAccount();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from fixed_accounts where account_no = ?")) {
			preparedStatement.setString(1, account_no);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	accountFromDb.setAccount_no(rs.getString("account_no"));
					accountFromDb.setCreated_date(rs.getString("created_date"));
					accountFromDb.setInterestRate(rs.getFloat("interestRate"));
					accountFromDb.setMaturity_date(rs.getString("maturity_date"));
					accountFromDb.setFixed_balance(rs.getFloat("fixed_balance"));
					accountFromDb.setInterestEarned(rs.getFloat("interest_earned"));
			 }

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return accountFromDb;
	}
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
