<%@page import="entity.Classes"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="util.HibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="admin.html">Back to Main Menu</a><br>

<h1>Choose a Class for Report Page</h1>
<%
	SessionFactory sf  = HibernateUtil.getSessionFactory();
	Session hibernateSession = sf.openSession();
	List<Classes> classes = hibernateSession.createQuery("from Classes").list();
%>

<form action="classReport.jsp" method="post">
<table>
<tr>
<th>Class Name </th>

</tr>

<tr>
<td>
<select name="class">
<%
	for (Classes clas : classes){
	out.print("<option>" + clas.getC_name());
	out.print("</option>");
	}
%>
</select>
</td>
</tr>
</table>
	<input type="submit" value="Submit">
</form>
</body>
</html>