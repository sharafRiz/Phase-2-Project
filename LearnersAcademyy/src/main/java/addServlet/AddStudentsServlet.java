package addServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Classes;
import entity.Students;
import util.HibernateUtil;



@WebServlet("/AddStudentsServlet")
public class AddStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("s_name");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Students student = new Students(name);
		
		try {
			session.save(student);
			tx.commit();
			out.print("<h3 style = 'color:green'> Data Added Succefully</h3>");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			out.print("<h3 style = 'color:red'> Error</h3>");
		}
		session.close();
		out.print("<a href = \"admin.html\">go back to the home page </a><br>");
		out.print("<a href = \"addStudents.html\">add more Students</a><br>");
		out.print("<a href = \"viewStudents.jsp\">List of Students</a><br>");
		
	}

}