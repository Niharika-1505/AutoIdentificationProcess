package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.DBTransactions;

import jbean.AIPJB;

public class Usr_UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		int rs=0;
		String url="";
		
		AIPJB ajb=new AIPJB();
		String u_id=(String) session.getAttribute("ID");
		ajb.setA_id(u_id);
		ajb.setA_pwd(request.getParameter("u_pwd"));
		ajb.setA_email(request.getParameter("u_email"));
		ajb.setA_contact(Long.parseLong(request.getParameter("u_contact")));
		ajb.setA_address(request.getParameter("u_address"));
		String id=ajb.getA_id().substring(0,2);
		if(id.equals("BU")){
			 rs=DBTransactions.updateUsr_Register(ajb,config);
			url="/AIP/JSP_Pages/usr_home.jsp";
			}
			else{
				 rs=DBTransactions.updateAdm_Register(ajb,config);
				url="/AIP/JSP_Pages/adm_home.jsp";
			}
		
		if(rs>=1)
		{
			out.println("<body bgcolor='#3b5998'><font face='Lucida Handwriting' size='10' color='#ff6666'><b>Your profile has been updated successfully!!</br>");
			out.println("Click here to back to <a href='"+url+"'><b> Home<b></a>");
		}
		else 
		{
			getServletContext().getRequestDispatcher("/JSP_Pages/usr_update.jsp").include(request, response);
			out.println("Updation failed please Try again");
		}
	}

}
