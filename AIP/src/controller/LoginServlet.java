package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aip.MyFunctions;

import model.DBTransactions;

import jbean.AIPJB;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");		
		HttpSession session=request.getSession();
		ResultSet rs=null;
		String url="",msg="";
		
		AIPJB ajb=new AIPJB();
		String u_id=request.getParameter("u_id");
		String u_pwd=request.getParameter("u_pwd");
		String code=request.getParameter("captcha");
		String captcha=(String)session.getAttribute("captcha");
		boolean status=MyFunctions.checkCaptcha(captcha,code);
		if(u_id.equals("")||u_pwd.equals(""))
		{
		String message="Please enter both UserID and Password";
		request.setAttribute("msg",message);
		getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").include(request, response);
		}
		else if(status)
		{
		try {
		ajb.setA_id(u_id);
		ajb.setA_pwd(u_pwd);
		String id=ajb.getA_id().substring(0,2);
		if(id.equals("BU")){
		rs=DBTransactions.usr_Login(ajb,config);
		url="/JSP_Pages/usr_home.jsp";
		}
		else{
			rs=DBTransactions.adm_Login(ajb,config);
			url="/JSP_Pages/adm_home.jsp";
		}
			if(rs.next())  
			{
				session.setAttribute("ID", ajb.getA_id());
				session.setAttribute("Email",rs.getString(5));
				String message=rs.getString(2);
				request.setAttribute("userid",message);
				getServletContext().getRequestDispatcher(url).include(request, response);
			//	out.println("<body bgcolor='#3b5998'><font face='Lucida Handwriting' size='10' color='#ff6666'><b>Hello "+rs.getString(2)+"</b></font></body>");
			}
			else
			{
				msg="Please enter correct password or user name";
				request.setAttribute("msg",msg);
				getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		else{
			msg="Enter same captcha";
			request.setAttribute("msg",msg);
			getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").include(request,response);
		}
	}
}
