/**
 * 
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import entity.SweetsEntity;
import hibernate.HibernateUtils;

/**
 * @author grkar
 *
 */
//@WebServlet("/swets")
public class SweetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Session session;

	public SweetServlet() {
	}

	public void init() {
		session = HibernateUtils.buildSessionFactory().openSession();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<form action='' method='POST'>");
		out.println("<label>Enter Product (Sweet) ID: " + "<input type='text' name='sweet-id'></input></label>");
		out.println("<input type='submit'>Get Details</input>");
		out.println("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sweetId = request.getParameter("robot-id");
		PrintWriter out = response.getWriter();

		SweetsEntity sweet = session.get(SweetsEntity.class, Long.parseLong(sweetId));
		if (sweet != null) {
			out.println("Found sweet: "+ sweet.getName());
		} else {
			out.println("No sweet found for id: " + sweetId);
		}
	}

}