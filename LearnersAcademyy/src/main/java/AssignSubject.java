

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Classes;
import entity.Subjects;
import util.HibernateUtil;

/**
 * Servlet implementation class AssignSubject
 */
@WebServlet("/AssignSubject")
public class AssignSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignSubject() {
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
		
		// Step 1: Get details , user has entered
		String clas = request.getParameter("class");
		String subject = request.getParameter("subject");
				
		// Step2: Create session
		SessionFactory sf  = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		// Step 3: Begin Transaction
		Transaction tx = session.beginTransaction();
		
		String hql_clas= "from Classes where c_name='" + clas + "'";
		List<Classes> classes = session.createQuery(hql_clas).list();
		
		String hql_subject = "update Subjects s set s.classes=:n where s.s_name=:sn";
		
		Query<Subjects> query = session.createQuery(hql_subject);
		query.setParameter("n", classes.get(0));
		query.setParameter("sn", subject);
				
		query.executeUpdate();

		
		// STep5: Commit transaction and close sessoin
		tx.commit();
		session.close();
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewClasses.jsp");
        dispatcher.forward(request, response); 
	}
	

}