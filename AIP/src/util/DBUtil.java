package util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
public class DBUtil {
	
	public static Connection getDBConnect(ServletConfig config){
	Connection con=null;
	ServletContext application=config.getServletContext();
	/*String driver=config.getInitParameter("driver");
	String url=config.getInitParameter("url");
	String uname=config.getInitParameter("uname");
	String pwd=config.getInitParameter("pwd");*/
	String driver=application.getInitParameter("driver");
	String url=application.getInitParameter("url");
	String uname=application.getInitParameter("uname");
	String pwd=application.getInitParameter("pwd");
	try{
		Class.forName(driver);
		/*Enumeration<java.sql.Driver> e = DriverManager.getDrivers();
		//Printing the list
		while(e.hasMoreElements()) {
		   System.out.println(e.nextElement().getClass());
		}*/
		con=DriverManager.getConnection(url,uname,pwd);//mysql database
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return con;
}
	
	
	
}
