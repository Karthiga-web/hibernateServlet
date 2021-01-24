/**
 * 
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.log.Log;

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
		out.println("<label>Enter Sweet Name: <input type='text' name='sweet-name'></input></label><br></br>");
		out.println("<label>Enter Sweet Price: <input type='text' name='sweet-price'></input></label><br></br>");
		out.println("<input type='submit'></input><br></br>");
		out.println("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("sweet-name");
		String price = request.getParameter("sweet-price");
		SweetsEntity sweet = new SweetsEntity();
//		sweet.setId((long) 3);
		sweet.setName(name);
		try{
			float floatValue= Float.parseFloat(price);
			sweet.setPrice(floatValue);
			}
			catch(NumberFormatException e){
			System.out.println ("NumberFormatException occurred.");
			System.out.println(price + " is not a valid number.");
			}
		Transaction transaction = session.beginTransaction();
		session.save(sweet);
	
		transaction.commit();
		session.close();
		response.setContentType("text/html");
		out.println("Sweet was created in table");
	}

}