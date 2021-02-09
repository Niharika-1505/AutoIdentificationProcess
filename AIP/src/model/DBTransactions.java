package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import javax.servlet.ServletConfig;

import util.DBUtil;

import jbean.AIPJB;

public class DBTransactions {
	public static int insertAdm_Register(AIPJB ajb,ServletConfig config){

			int rs=0;
			try {
				Connection con=DBUtil.getDBConnect(config);
		String query="insert into adm_register(a_id,a_name,a_pwd,a_dob,a_email,a_contact,a_address,a_city,a_country) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setString(1,ajb.getA_id());
		ps.setString(2,ajb.getA_name());
		ps.setString(3,ajb.getA_pwd());
		ps.setString(4,ajb.getA_dob());
		ps.setString(5,ajb.getA_email());
		ps.setLong(6,ajb.getA_contact());
		ps.setString(7,ajb.getA_address());
		ps.setString(8,ajb.getA_city());
		ps.setString(9,ajb.getA_country());
		
		rs=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
		}
	public static int insertUsr_Register(AIPJB ajb,ServletConfig config){

		int rs=0;
		try {
			Connection con=DBUtil.getDBConnect(config);
	String query="insert into usr_register(u_id,u_name,u_pwd,u_dob,u_email,u_contact,u_address,u_city,u_country) values(?,?,?,?,?,?,?,?,?)";
	PreparedStatement ps=con.prepareStatement(query);
	
	ps.setString(1,ajb.getA_id());
	ps.setString(2,ajb.getA_name());
	ps.setString(3,ajb.getA_pwd());
	ps.setString(4,ajb.getA_dob());
	ps.setString(5,ajb.getA_email());
	ps.setLong(6,ajb.getA_contact());
	ps.setString(7,ajb.getA_address());
	ps.setString(8,ajb.getA_city());
	ps.setString(9,ajb.getA_country());
	
	rs=ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return rs;
	
	}
	public static ResultSet selectCountry(ServletConfig config){
		ResultSet rs = null;
		try{  
			 
			Connection con=DBUtil.getDBConnect(config);
			Statement stmt=con.createStatement();  
			String query="Select * from country"; 
			rs=stmt.executeQuery(query);  
	  
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;	
	}
	public static ResultSet selectCity(AIPJB ajb,ServletConfig config){
	ResultSet rs = null;
	try{  
		 
		Connection con=DBUtil.getDBConnect(config);
		String query="select b.city_id,b.city_name from country as a left outer join city as b on a.c_id=b.c_id where a.c_name=?"; 
		PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_country());
			rs=ps.executeQuery();   
  
		}catch(SQLException e)
		{ System.out.println(e);
			e.printStackTrace();
		}  
	
	return rs;	
}
	public static ResultSet adm_Login(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from adm_register where a_id=? and a_pwd=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_id());
			ps.setString(2, ajb.getA_pwd());
			rs=ps.executeQuery();   
	  
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static ResultSet adm_Recovery(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from adm_register where a_email=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_email());
			rs=ps.executeQuery();   
	  
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static int updateAdm_Password(AIPJB ajb, ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update adm_register set a_pwd=? where a_id=?";
		PreparedStatement ps=con.prepareStatement(query);  
			
		ps.setString(1,ajb.getA_pwd());
		ps.setString(2,ajb.getA_id());
		
		rs=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}	
	public static ResultSet usr_Login(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from usr_register where u_id=? and u_pwd=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_id());
			ps.setString(2, ajb.getA_pwd());
			rs=ps.executeQuery();   
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static ResultSet usr_Recovery(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from usr_register where u_email=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_email());
			rs=ps.executeQuery();   
	  
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}	
	public static int updateUsr_Password(AIPJB ajb, ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update usr_register set u_pwd=? where u_id=?";
		PreparedStatement ps=con.prepareStatement(query);  
			
		ps.setString(1,ajb.getA_pwd());
		ps.setString(2,ajb.getA_id());
		
		rs=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}	
	public static ResultSet selectUsers(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from usr_register where u_id=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_id());
			rs=ps.executeQuery();   
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static ResultSet selectAdmin(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from adm_register where a_id=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_id());
			rs=ps.executeQuery();   
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static int updateUsr_Register(AIPJB ajb, ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update usr_register set u_pwd=?,u_email=?,u_contact=?,u_address=? where u_id=?";
		PreparedStatement ps=con.prepareStatement(query);  
			
		ps.setString(1,ajb.getA_pwd());
		ps.setString(2,ajb.getA_email());
		ps.setLong(3,ajb.getA_contact());
		ps.setString(4,ajb.getA_address());
		ps.setString(5,ajb.getA_id());
		
		rs=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static int updateAdm_Register(AIPJB ajb, ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update adm_register set a_pwd=?,a_email=?,a_contact=?,a_address=? where a_id=?";
		PreparedStatement ps=con.prepareStatement(query);  
			
		ps.setString(1,ajb.getA_pwd());
		ps.setString(2,ajb.getA_email());
		ps.setLong(3,ajb.getA_contact());
		ps.setString(4,ajb.getA_address());
		ps.setString(5,ajb.getA_id());
		
		rs=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static ResultSet selectDefault_Details(AIPJB ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="Select default_status,borrower_mail,borrower_name,borrower_rating,accrual_status,bank_no,account_no,days_past_due,comments from default_details where borrower_mail=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getA_email());
			rs=ps.executeQuery();   
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static ResultSet selectDefault_Details(ServletConfig config){
		ResultSet rs = null;
		try{  
			 
			Connection con=DBUtil.getDBConnect(config);
			Statement stmt=con.createStatement();  
			String query="Select default_status,borrower_name,borrower_rating,accrual_status,bank_no,account_no,days_past_due,comments from default_details"; 
			rs=stmt.executeQuery(query);  
	  
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;	
	}
	public static int updateDefault_Details(AIPJB ajb, ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update temp set default_status=?,comments=? where borrower_mail=?";
		PreparedStatement ps=con.prepareStatement(query);  
			
		ps.setString(1,ajb.getDefault_status());
		ps.setString(2,ajb.getComments());
		ps.setString(3,ajb.getA_email());
		
		rs=ps.executeUpdate();
		System.out.println(rs);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static int insertTemp(AIPJB ajb,ServletConfig config){
		int rs = 0;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="INSERT INTO temp SELECT * FROM default_details where default_status=?"; 
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, ajb.getDefault_status());
			rs=ps.executeUpdate();
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static int truncateTemp(ServletConfig config){
		int rs = 0;
		try{  
			Connection con=DBUtil.getDBConnect(config);
			String query="truncate table temp"; 
			PreparedStatement ps=con.prepareStatement(query);
			rs=ps.executeUpdate();
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static ResultSet selectTemp(String ajb,ServletConfig config){
		ResultSet rs = null;
		try{  
			 
			Connection con=DBUtil.getDBConnect(config);
			String query="Select * from temp where default_status=?"; 
			PreparedStatement ps=con.prepareStatement(query);
		//	ps.setString(1, ajb.getDefault_status());
			ps.setString(1, ajb);
			rs=ps.executeQuery();   
			}catch(SQLException e)
			{ System.out.println(e);
				e.printStackTrace();
			}  
		
		return rs;
	}
	public static int update_Auto_Default_Details(ServletConfig config) {
		int rs=0;
		try {
		Connection con=DBUtil.getDBConnect(config);
		String query="update default_details join temp using(borrower_mail) set default_details.default_status=temp.default_status,default_details.comments=temp.comments";
		PreparedStatement ps=con.prepareStatement(query);  
		rs=ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}


	/*public static ResultSet selectCity(AIPJB ajb,ServletConfig config){
	ResultSet rs = null;
	try{  
		Connection con=DBUtil.getDBConnect(config);
		String query="select * from city where c_id=?"; 
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, ajb.getA_country());
		rs=ps.executeQuery();   
  
		}catch(SQLException e)
		{ System.out.println(e);
			e.printStackTrace();
		}  
	
	return rs;
	
}*/

	
}
