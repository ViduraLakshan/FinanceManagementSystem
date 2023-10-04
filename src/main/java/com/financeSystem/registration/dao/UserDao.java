package com.financeSystem.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.financeSystem.registration.model.User;

public class UserDao {

    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO users" +
            "  (username, email, password , uuid, is_acc_created) VALUES " +
            " (?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getemail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUuid());
            preparedStatement.setBoolean(5, user.isAccountCreated());
          
          

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
    
    public boolean validate(User user) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return status;
	}
    
    public User getUserByName(String name, String password) throws ClassNotFoundException {
		User userFromDb= new User();
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/finance_system?useSSL=false", "root", "3636");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ?")) {
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			userFromDb.setUuid(rs.getString("uuid"));
			userFromDb.setUserName(rs.getString("username"));
			userFromDb.setAccountCreated(rs.getBoolean("is_acc_created"));
			System.out.println(userFromDb);

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return userFromDb;
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