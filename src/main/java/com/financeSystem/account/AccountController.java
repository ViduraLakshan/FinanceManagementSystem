package com.financeSystem.account;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financeSystem.fixedDeposit.FixedDepositDao;
import com.financeSystem.loanAccount.LoanAccountDao;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/createaccount")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AccountDao accountDao;
	private FixedDepositDao fixedDepositDao;
	private LoanAccountDao loanAccountDao;

    public void init() {
    	accountDao = new AccountDao();
    	fixedDepositDao=new FixedDepositDao();
    	loanAccountDao=new LoanAccountDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("accountCreation.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountType = request.getParameter("accounttype");
		String depositString = request.getParameter("deposit");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		System.out.println(userName);
		float deposit=0.0f;
		if (depositString != null && !depositString.isEmpty()) {
            try {
                
            	deposit = Integer.parseInt(depositString);
               
                response.getWriter().println("Number from request: " + deposit);
            } catch (NumberFormatException e) {    
                response.getWriter().println("Invalid number format");
            }
        } else {
            response.getWriter().println("Number parameter not found in the request");
        }
		String account_num;
		account_num = (String) session.getAttribute("account_no");
		Account account=new Account();
		
		account.setAccount_type(accountType);
		account.setBalance(0.0f);
		account.setUserName(userName);
		
		
		try {
			if(accountDao.validate(userName)) {
				if(accountType!= null&&accountType.equals("fixeddeposits")) {
					if(fixedDepositDao.validate(account_num)) {
						session.setAttribute("accountEror", "account allredy have");
						
					}else {
						fixedDepositDao.createAccount(account_num, deposit);
						session.setAttribute("succes", "account created");
					}
					
				}else if(accountType!= null&&accountType.equals("loans")){
					if(loanAccountDao.validate(account_num)) {
						session.setAttribute("accountEror", "account allredy have");
					}else {
						loanAccountDao.createAccount(account_num, deposit);
						session.setAttribute("succes", "account created");
					}
				}
				else if(accountType!= null&&accountType.equals("savingsaccounts")){
					session.setAttribute("accountEror", "account allredy have");
				}
				response.sendRedirect("accountCreation.jsp");
			}else {
				int min = 10000000;
		        int max = 99999999;
		        int randomNum = new Random().nextInt((max - min) + 1) + min;
		        account_num= String.valueOf(randomNum);
		        account.setAccount_no(account_num);
				
				if(accountType!= null&&accountType.equals("fixeddeposits")) {
					accountDao.createAccount(account);
					fixedDepositDao.createAccount(account_num, deposit);
				}else if(accountType!= null&&accountType.equals("loans")){
					accountDao.createAccount(account);
					loanAccountDao.createAccount(account_num, deposit);
				}
				else if(accountType!= null&&accountType.equals("savingsaccounts")){
					account.setBalance(deposit);
					accountDao.createAccount(account);
				}
				response.sendRedirect("transaction");
			}
			
			
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
	}

}
