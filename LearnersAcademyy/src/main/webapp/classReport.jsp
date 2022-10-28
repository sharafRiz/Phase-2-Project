<%@page import="entity.Students"%>
<%@page import="entity.Teachers"%>
<%@page import="entity.Subjects"%>
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
<a href="index.html">Back to Main Menu</a><br>
<%String clas = request.getParameter("class");%>
<h1>Class Report for <%=clas%></h1>

<%
	SessionFactory sf  = HibernateUtil.getSessionFactory();
	Session hibernateSession = sf.openSession();
	List<Classes> classes = hibernateSession.createQuery("from Classes cl where cl.c_name='" + clas + "'").list();
	Classes clasForReport = classes.get(0);
%>
<table>
<tr>
<th>Subject Name </th>
<th>Teacher Name </th>
</tr>
<%	
	for(Subjects subject: clasForReport.getSubjects()){
		out.print("<tr>");	
		out.print("<td>" + subject.getS_name() + "</td>");
		out.print("<td>" + getTeacherName(subject) + "</td>");
		out.print("</tr>");
	}
%>
</table>
<%!
	public String getTeacherName(Subjects subject){
		Teachers teacher = subject.getTeachers();
		String name;
	
		if(teacher != null){
			name=teacher.getT_name();
			
			return name;
		}else{
			return "No Teacher assigned";
		}
}
%>
<table>
<br>
<tr>
<th>Listed Students</th>
</tr>
<%
	for(Students student : clasForReport.getStudents()){
		out.print("<tr>");
		out.print("<td>" + student.getS_name() +"</td>");
		out.print("</tr>");
	}
%>
</table>
</body>
</html>