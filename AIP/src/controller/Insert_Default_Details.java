package controller;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.DBTransactions;

public class Insert_Default_Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");
		int rs=0;
		//int rs=0,rs2=0;
		//String updation="",truncate="";
		String updation="";
		
		
		rs=DBTransactions.update_Auto_Default_Details(config);
			
		if(rs>=1)
		{
			updation="updation successful";
			request.setAttribute("updation",updation);
			//rs2=DBTransactions.truncateTemp(config);
			DBTransactions.truncateTemp(config);
			/*if(rs2==0){
				truncate="truncation successful";
				request.setAttribute("truncate",truncate);
			}
			else{
				truncate="truncation Failed";
				request.setAttribute("truncate",truncate);
			}*/
			getServletContext().getRequestDispatcher("/JSP_Pages/adm_home.jsp").include(request, response);
		}
		else 
		{
			updation="updation Failed";
			request.setAttribute("updation",updation);
			getServletContext().getRequestDispatcher("/JSP_Pages/auto_defaults.jsp").include(request, response);
		}
	}

}
