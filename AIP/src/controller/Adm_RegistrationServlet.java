package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aip.MyFunctions;

import model.DBTransactions;

import jbean.AIPJB;

public class Adm_RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		//ServletContext application=getServletContext();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		
		AIPJB ajb=new AIPJB();
		String a_id=MyFunctions.admID();
		session.setAttribute("a_id", a_id);
		ajb.setA_id(a_id);
		ajb.setA_name(request.getParameter("a_name"));
		ajb.setA_pwd(request.getParameter("a_pwd"));
		ajb.setA_dob(MyFunctions.formatDate(request.getParameter("a_dob")));
		ajb.setA_email(request.getParameter("a_email"));
		ajb.setA_contact(Long.parseLong(request.getParameter("a_contact")));
		ajb.setA_address(request.getParameter("a_address"));
		ajb.setA_country(request.getParameter("a_country"));
		ajb.setA_city(request.getParameter("a_city"));
		
		
		int rs=DBTransactions.insertAdm_Register(ajb,config);
			
			if(rs>=1)
			{
				out.println("<body bgcolor='#3b5998'><font face='Lucida Handwriting' size='10' color='#ff6666'><b>Hello  Your USER ID is "+session.getAttribute("a_id")+"</b></font></body>");
				getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").include(request, response);
			}
			else 
			{
				
				getServletContext().getRequestDispatcher("/JSP_Pages/AdminRegistration.jsp").include(request, response);
				out.println("Email already Registered!!!");
			}
	  
	}

}
