package com.financeSystem.registration.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.account.Account;
import com.financeSystem.account.AccountDao;
import com.financeSystem.registration.dao.UserDao;
import com.financeSystem.registration.model.User;


/**
 * @email Ramesh Fadatare
 */

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private AccountDao accountDao;

	public void init() {
		userDao = new UserDao();
		accountDao =new AccountDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.invalidate();
		response.sendRedirect("login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		
//		try {
//			userDao.getUserByName(username,password);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
		
		
		try {
			
			if (userDao.validate(user)) {
				HttpSession session = request.getSession();
				
					Account account=accountDao.getUserByName(username);
					if (account.getUserName() != null) {
						session.setAttribute("username",username);
						response.sendRedirect("transaction");
					}else {
						session.setAttribute("username",username);
						response.sendRedirect("accountCreation.jsp");
					}
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("credentialerror", "user name or password wrong");
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
