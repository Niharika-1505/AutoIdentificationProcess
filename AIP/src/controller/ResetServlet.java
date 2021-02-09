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

public class ResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletConfig config=getServletConfig();
		response.setContentType("text/html");		
		int rs=0;
		HttpSession session =request.getSession();
		String u_id=request.getParameter("userid");
		AIPJB ajb=new AIPJB();
		ajb.setA_id(u_id);
		String opassword=request.getParameter("password");
		String npassword=request.getParameter("password1");
		ajb.setA_pwd(npassword);
		String cpassword=request.getParameter("password2");
		String mpassword=(String)session.getAttribute("password");
		if(u_id.equals("")||opassword.equals("")||npassword.equals("")||cpassword.equals(""))
		{
			String message="All fields are mandatory";
			request.setAttribute("msg",message);
			getServletContext().getRequestDispatcher("/JSP_Pages/forgotPassword.jsp").include(request,response);
		}
		else 
		{
			try
			{
				if(opassword.equals(mpassword))
				{
					if(npassword.equals(cpassword))
					{
						String id=ajb.getA_id().substring(0,2);
						if(id.equals("BU")){
						rs=DBTransactions.updateUsr_Password(ajb,config);
						}
						else{
							rs=DBTransactions.updateAdm_Password(ajb,config);
						}
				if(rs>=1)
				{
					String message="Password changed successfully";
					request.setAttribute("msg", message);
					getServletContext().getRequestDispatcher("/JSP_Pages/login.jsp").forward(request, response);
				}
				else
				{
					String message="Please try again";
					request.setAttribute("msg",message);
					getServletContext().getRequestDispatcher("/JSP_Pages/resetPassword.jsp").include(request,response);
				}
					}
				else
				{
					String message="enter same password";
					request.setAttribute("msg",message);
					getServletContext().getRequestDispatcher("/JSP_Pages/resetPassword.jsp").include(request,response);
				}
					}
				else
				{
					String message="Please enter the password that is sent to your mail";
					request.setAttribute("msg",message);
					getServletContext().getRequestDispatcher("/JSP_Pages/resetPassword.jsp").include(request,response);
				}
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			
		}

	
	}


