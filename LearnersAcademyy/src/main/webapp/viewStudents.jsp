<%@page import="entity.Students"%>
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
<a href="addStudents.html">Add Student</a><br>
<a href="assignStudentToClass.jsp">Assign Student</a>

<h1>The Following Students are listed</h1>
<%
	SessionFactory sf  = HibernateUtil.getSessionFactory();
	Session hibernateSession = sf.openSession();
	List<Students> students = hibernateSession.createQuery("from Students").list();
%>
<table>
<tr>
<th>Name </th>
<th>Assigned Class </th>
</tr>
	<%		
		for(Students student : students){
			out.print("<tr>");	
			out.print("<td>" + student.getS_name() + "</td>");
			out.print("<td>" + getClassName(student) + "</td>");
			out.print("</tr>");	
		}
	%>
</table>

<%!
	public String getClassName(Students student){
	if (student.getClasses() == null){
		return "no class assigned";
	}
	else{
		return student.getClasses().getC_name();
	}
}
%>
</body>
</html>