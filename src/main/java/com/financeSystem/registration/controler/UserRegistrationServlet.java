package com.financeSystem.registration.controler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.account.Account;
import com.financeSystem.registration.dao.UserDao;
import com.financeSystem.registration.model.User;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
    	userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
 

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUserName(username);
        user.setPassword(password);
        user.setemail(email);
        user.setAccountCreated(false);
        try {
			if (userDao.validate(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("userError","User Name allrady axist");
			} else {
				userDao.registerUser(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

//        try {
//        	
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        response.sendRedirect("registration.jsp");
    }
}
