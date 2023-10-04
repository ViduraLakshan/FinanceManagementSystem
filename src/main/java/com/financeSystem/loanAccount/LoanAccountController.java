package com.financeSystem.loanAccount;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.account.AccountDao;
import com.financeSystem.fixedDeposit.FixedDepositAccount;
import com.financeSystem.fixedDeposit.FixedDepositDao;

/**
 * Servlet implementation class LoanAccountController
 */
@WebServlet("/loanAccount")
public class LoanAccountController extends HttpServlet {

	private LoanAccountDao loanAccountDao;

    public void init() {
    	loanAccountDao=new LoanAccountDao();
    }
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String account_num = (String) session.getAttribute("account_no");
		try {
			if(loanAccountDao.validate(account_num)) {
				LoanAccount loanAccount=loanAccountDao.getAccountByNumber(account_num);
				request.setAttribute("loanAccount", loanAccount);
				//System.out.println(loanAccount.getAccount_no());
				RequestDispatcher rd= request.getRequestDispatcher("loanRepayment.jsp");
				rd.forward(request, response);
				//response.sendRedirect("loanRepayment.jsp");
			}else {
				session.setAttribute("accountErorr", "Loan Account account not created");
				response.sendRedirect("transaction");
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
		HttpSession session = request.getSession();
		String amountString= request.getParameter("amount");
		String account_no= (String) session.getAttribute("account_no");
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
			
			loanAccountDao.rePayment(account_no, amount);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("loanAccount");
	}

}
