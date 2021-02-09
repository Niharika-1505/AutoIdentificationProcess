<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null,rs1=null;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<form action="/AIP/Adm_RegistrationServlet" method="post"> 
<table>
<tr><td>
UserName :</td><td><input type="text" name="a_name" ></td></tr>
<tr><td>Password :</td><td><input type="password" name="a_pwd"  ></td></tr>
<tr><td>Date of Birth :</td><td><input type="text" name="a_dob"></td></tr>
<tr><td>Email Address :</td><td><input type="email" name="a_email"></td></tr>
<tr><td>Contact Number :</td><td><input type="text" name="a_contact"></td></tr>
<tr><td>Address :</td><td><textarea name="a_address" cols="16" rows="3"></textarea></td></tr>
<tr><td>Country Code :</td><td>
<%
rs=DBTransactions.selectCountry(config);
//rs2=DBTransactions.select_Join_Country_City(config);
%>
<select name="a_country" onchange="changeFunc();">
<option  value="0">Select</option>
	    	   <% while(rs.next())
	    	    {
	            %>
	    	            <option value="<%= rs.getString(2)%>"><%= rs.getString("c_name")%></option>
	    	            
	    	        <% } %>
</select></td>
<tr><td>City Code :</td><td>
<script type="text/javascript">

  function changeFunc() {
	   var selectBox = document.getElementById("a_country");
	   var c_id = selectBox.options[selectBox.selectedIndex].value;
   <% 
  AIPJB ajb=new AIPJB();
	String c_name=request.getParameter("a_country");
  	//String c_id=(String)("IN").toString();
   ajb.setA_country(c_name);
   rs1=DBTransactions.selectCity(ajb,config);%>

   }

  </script>
<select name="a_city">
<option  value="0">Select</option>
	    	   <% while(rs1.next())
	    	    {
	            %>
	    	            <option value="<%= rs1.getString(2)%>"><%= rs1.getString("city_name")%></option>
	    	            
	    	        <% } %>
</select></td></tr>
<tr><td><input type="submit" value="submit"></td><td><input type="reset" value="REFRESH"></td></tr>
</table>
</form>
</body>
</html>
