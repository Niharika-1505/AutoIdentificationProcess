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

public class Update_Auto_defaults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		int rs=0;
		String url="";
		
		AIPJB ajb=new AIPJB();
		String borrower_mail=(String)session.getAttribute("borrower_mail");
		ajb.setA_email(borrower_mail);
		ajb.setDefault_status(request.getParameter("default_Status"));
		ajb.setComments(request.getParameter("comments"));
			 rs=DBTransactions.updateDefault_Details(ajb,config);
			url="/AIP/JSP_Pages/auto_defaults.jsp";

		if(rs>=1)
		{
			out.println("<body bgcolor='#3b5998'><font face='Lucida Handwriting' size='10' color='#ff6666'><b>Your profile has been updated successfully!!</br>");
			out.println("Click here to back to <a href='"+url+"'><b> Home<b></a>");
		}
		else 
		{
			getServletContext().getRequestDispatcher("/JSP_Pages/auto_Defaults_Edit.jsp").include(request, response);
			out.println("Updation failed please Try again");
		}
	}

}
