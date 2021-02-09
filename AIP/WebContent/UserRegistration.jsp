<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null,rs1=null;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="/JS/formValidation.js"></script>
<script type="text/javascript">

   function changeFunc() {
	   var selectBox = document.getElementById("a_country");
	   var c_id = selectBox.options[selectBox.selectedIndex].value;
   <% 
   AIPJB ajb=new AIPJB();
//  String c_id=request.getParameter("a_country");
   String c_id=(String)("IN").toString();
   ajb.setA_country(c_id);
   rs1=DBTransactions.selectCity(ajb,config);
   %>

   }

  </script>
</head>
<body bgcolor="009999">
<form name="form" action="/AIP/Usr_RegistrationServlet" method="post" onsubmit="return validate(),age()">
<h1>Welcome to Online Banking</h1>
<h3>Enter details to get registered!!</h3>
<table>
<tr><td>User Name: </td><td><input type="text" name="u_name"  ></td></tr>
<tr><td>Password: </td><td><input type="password" name="u_pwd"  ></td></tr>
<tr><td>Confirm Password: </td><td><input type="password" name="u_pwd1" ></td></tr>
<tr><td>Date of Birth: </td><td><input type="text" name="u_dob"  placeholder="dd/mm/yyyy"></td></tr>
<tr><td>Email Address: </td><td><input type="text" name="u_email"  ></td></tr>
<tr><td>Contact Number: </td><td><input type="text" name="u_contact"  ></td></tr>
<tr><td>Address: </td><td><textarea name="u_address" cols="16" rows="3"></textarea></td></tr>
<tr><td>Country Code:</td><td>
<%
rs=DBTransactions.selectCountry(config);
%>
<select name="a_country" onchange="changeFunc();">
<option  value="0">Select</option>
	    	   <% while(rs.next())
	    	    {
	            %>
	    	            <option value="<%= rs.getString(1)%>"><%= rs.getString("c_name")%></option>
	    	            
	    	        <% } %>

</select></td>
<tr><td>City Code :</td><td>
<select name="a_city">
<option  value="0">Select</option>
	    	   <% while(rs1.next())
	    	    {
	            %>
	    	            <option value="<%= rs1.getString(2)%>"><%= rs1.getString("city_name")%></option>
	    	            
	    	        <% } %>
</select></td></tr>
<tr ><td><input type="submit" value="REGISTER"></td><td><input type="reset" value="REFRESH"></td></tr>

</table>
</form>


</body>
</html>