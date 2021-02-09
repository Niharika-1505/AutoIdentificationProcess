<%@page import="java.sql.*,model.*,jbean.*"%>
<%ResultSet rs =null;
ResultSetMetaData meta=null;
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
type="text/javascript" language="javascript">    
javascript:window.history.forward(1);
</script>
  <script type="text/javascript" src="AIP/JS/test.js"></script>
<%  
response.setHeader("Pragma","no-cache");  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Cache-Control","cache");  
response.setDateHeader("Expires",0);  
%>
</head>
<%
if(session.getAttribute("ID")!=null)
{
 %>
<body>
<form action="/AIP/Search_Default_Details" method="post">
<input type="text" name="search"><input type="submit" value="Search">
${searchTxt}
</form>
<h3 align="right"><a href="/AIP/JSP_Pages/usr_update.jsp">Update Profile</a></h3>
<h3 align="right"><a href="/AIP/JSP_Pages/logout.jsp">Logout</a></h3>
Hello ${userid}
 <%
  rs=DBTransactions.selectDefault_Details(config);
meta=rs.getMetaData();
if(rs.next()){%>
  <table border="1">
  <thead><tr>
  <th><%=meta.getColumnName(1)%></th>
  <th><%=meta.getColumnName(2)%></th>
  <th><%=meta.getColumnName(3)%></th>
  <th><%=meta.getColumnName(4)%></th>
  <th><%=meta.getColumnName(5)%></th>
  <th><%=meta.getColumnName(6)%></th>
  <th><%=meta.getColumnName(7)%></th>
  <th><%=meta.getColumnName(8)%></th>
 </tr>
  </thead>
 <%
 rs.previous();
  while(rs.next()){%>
	  <tr>
	  <td><%=rs.getString(1)%></td>
	    <td><%=rs.getString(2)%></td>
	     <td><%=rs.getInt(3)%></td>
	     <td><%=rs.getInt(4)%></td>
	     <td><%=rs.getLong(5)%></td>
	     <td><%=rs.getLong(6)%></td>
	     <td><%=rs.getInt(7)%></td>
	  	<td><TextArea><%=rs.getString(8)%></TextArea></td>
	  </tr>
<%}%>
</table>
${updation}
${truncate}
<%
 }
else
	out.print("No data Available Currently");

 }
 else
 {
 String message="Please Login";
 request.setAttribute("msg", message);
 getServletContext().getRequestDispatcher("/login.jsp").include(request,response);
 }
  %>
</body>
</html>