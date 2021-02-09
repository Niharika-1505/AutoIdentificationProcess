package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jbean.AIPJB;
import model.DBTransactions;
import aip.MyFunctions;

public class Usr_RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		AIPJB ajb=new AIPJB();
		
		ajb.setA_name(request.getParameter("u_name"));
		String usrID=ajb.getA_name();
		String u_id=MyFunctions.usrID(usrID);
		session.setAttribute("u_id", u_id);
		ajb.setA_id(u_id);
		ajb.setA_pwd(request.getParameter("u_pwd"));
		ajb.setA_dob(MyFunctions.formatDate(request.getParameter("u_dob")));
		ajb.setA_email(request.getParameter("u_email"));
		ajb.setA_contact(Long.parseLong(request.getParameter("u_contact")));
		ajb.setA_address(request.getParameter("u_address"));
		ajb.setA_country(request.getParameter("a_country"));
		ajb.setA_city(request.getParameter("a_city"));
		
		int rs=DBTransactions.insertUsr_Register(ajb,config);
			
			if(rs>=1)
			{
				out.println("<body bgcolor='#3b5998'><font face='Lucida Handwriting' size='10' color='#ff6666'><b>Hello  Your USER ID is "+session.getAttribute("u_id")+"</br>");
				getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").include(request, response);
			}
			else 
			{
				getServletContext().getRequestDispatcher("/JSP_Pages/UserRegistration.jsp").include(request, response);
				out.println("Email already Registered!!!");
			}
	  
	}

}
