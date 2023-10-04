package com.financeSystem.transaction;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.account.Account;
import com.financeSystem.account.AccountDao;
import com.financeSystem.loanAccount.LoanAccount;

/**
 * Servlet implementation class TransactionController
 */
@WebServlet("/transaction")
public class TransactionController extends HttpServlet {
	private TransactionDao transactionDao;
	private AccountDao accountDao;

	public void init() {
		transactionDao =new TransactionDao();
		accountDao =new AccountDao();
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionController() {
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
				request.setAttribute("account", account);
				//System.out.println(loanAccount.getAccount_no());
				RequestDispatcher rd= request.getRequestDispatcher("transaction.jsp");
				rd.forward(request, response);
				//response.sendRedirect("loanRepayment.jsp");
			}else {
				session.setAttribute("accountErorr", "Loan Account account not created");
				response.sendRedirect("accountCreation.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transactionType = request.getParameter("accounttype");
		String amountString = request.getParameter("amount");
		HttpSession session = request.getSession();
		String account_no = (String) session.getAttribute("account_no");
		//String balancefromSession = (String) session.getAttribute("balance");
		float balance=(float) session.getAttribute("balance");
	
              
		float amount=0.0f;
		if (amountString != null && !amountString.isEmpty()) {
            try {
            	amount = Integer.parseInt(amountString);
               
                response.getWriter().println("Number from request: " + amount);
            } catch (NumberFormatException e) {    
                response.getWriter().println("Invalid number format");
            }
        } else {
            response.getWriter().println("Number parameter not found in the request");
        }
		
		
		try {
			if (transactionDao.doTransaction(account_no,transactionType,amount,balance)>0) {
				response.sendRedirect("transaction");
			}
			else {
				
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //response.sendRedirect("transaction");
	}

}
