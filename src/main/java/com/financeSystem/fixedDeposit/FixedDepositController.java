package com.financeSystem.fixedDeposit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.account.AccountDao;
import com.financeSystem.loanAccount.LoanAccountDao;

/**
 * Servlet implementation class FixedDepositController
 */
@WebServlet("/fixedDeposit")
public class FixedDepositController extends HttpServlet {

	private FixedDepositDao fixedDepositDao;
    public void init() {
    	fixedDepositDao=new FixedDepositDao();
    }
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FixedDepositController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String account_num = (String) session.getAttribute("account_no");
		try {
			if(fixedDepositDao.validate(account_num)) {
				FixedDepositAccount fixedDepositAccount=fixedDepositDao.getAccountByNumber(account_num);
				request.setAttribute("fixedDepositAccount", fixedDepositAccount);
				System.out.println(fixedDepositAccount.getAccount_no());
				RequestDispatcher rd= request.getRequestDispatcher("fixeddeposit.jsp");
				rd.forward(request, response);
				//response.sendRedirect("fixeddeposit.jsp");
			}else {
				session.setAttribute("accountErorr", "Fixed Deposit account not created");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
