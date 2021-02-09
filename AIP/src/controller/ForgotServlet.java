package controller;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aip.MyFunctions;

import model.DBTransactions;

import jbean.AIPJB;


public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");		
		ResultSet rs=null;
		int rs1=0;
		boolean status=false;
		String msg="";
		
		AIPJB ajb=new AIPJB();
		String email=request.getParameter("emailid");
		ajb.setA_email(email);
		ajb.setA_id(request.getParameter("userid"));
		String to=email;
		System.out.printf("%tc",new Date());
		long start=System.currentTimeMillis();
		try
		{
			Thread.sleep(400*60);
			}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(new Date());
		long end=System.currentTimeMillis();
		long diff=end-start;
		if(diff>=1440000)
		{
			getServletContext().getRequestDispatcher("/JSP_Pages/siteupdate.jsp").forward(request, response);
		}
		try {
			String id=ajb.getA_id().substring(0,2);
			if(id.equals("BU")){
			rs=DBTransactions.usr_Recovery(ajb,config);
			}
			else{
				rs=DBTransactions.adm_Recovery(ajb,config);
			}
		if(rs.next())
		{
			//System.out.print("Forgot Servlet Status: ");
			String generatepassword=MyFunctions.passwordGen();
		status=MyFunctions.sendMail(to,generatepassword);
		//System.out.println(status);
		if(status){
			ajb.setA_pwd(generatepassword);
			if(id.equals("BU")){
				rs1=DBTransactions.updateUsr_Password(ajb,config);
				}
				else{
					rs1=DBTransactions.updateAdm_Password(ajb,config);
				}
			if(rs1>=1)
			{
				HttpSession session =request.getSession();
				session.setAttribute("loginid", ajb.getA_id());
				session.setAttribute("password", generatepassword);
				getServletContext().getRequestDispatcher("/JSP_Pages/resetPassword.jsp").forward(request, response);
			}
			msg="Reset Link is Sent to your mail";
			request.setAttribute("msg",msg);
			getServletContext().getRequestDispatcher("/JSP_Pages/forgotPassword.jsp").include(request,response);
		}
		}
		else{
			msg="Please Check Your Credentials";
			request.setAttribute("msg",msg);
			getServletContext().getRequestDispatcher("/JSP_Pages/forgotPassword.jsp").include(request,response);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}





