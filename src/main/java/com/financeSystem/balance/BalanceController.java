package com.financeSystem.balance;

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

/**
 * Servlet implementation class BalanceController
 */
@WebServlet("/balance")
public class BalanceController extends HttpServlet {
	private AccountDao accountDao;

	public void init() {
		accountDao =new AccountDao();
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		try {
			if(accountDao.validate(userName)) {
				Account account=accountDao.getUserByName(userName);
				session.setAttribute("account_no",account.getAccount_no());
				session.setAttribute("balance",account.getBalance());
				response.sendRedirect("balance.jsp");
				
			}else {
				session.setAttribute("accountErorr", "Loan Account account not created");
				response.sendRedirect("accountCreation.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("balance.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.sendRedirect("balance.jsp");
	}

}
