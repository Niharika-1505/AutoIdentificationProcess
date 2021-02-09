package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBTransactions;

import jbean.AIPJB;

public class Search_Default_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		int rs1=0;
		String searchTxt="";
		AIPJB ajb=new AIPJB();
		String status=request.getParameter("search");
		session.setAttribute("status",status);
		ajb.setDefault_status(status);
		rs1=DBTransactions.insertTemp(ajb,config);
		if(rs1>=1){
			getServletContext().getRequestDispatcher("/JSP_Pages/auto_defaults.jsp").include(request, response);
		}
		else{
			searchTxt="Search Failed";
			request.setAttribute("searchTxt",searchTxt);
			getServletContext().getRequestDispatcher("/JSP_Pages/adm_home.jsp").include(request, response);
		}
		
	}

}
