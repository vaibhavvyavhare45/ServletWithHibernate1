package com.prowings;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8896686093612161030L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		out.println("Hello, world!");
		//System.out.println((out.getClass().getName()));
		out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get the values from the request using 'getParameter'
        String name = request.getParameter("name");
        String phNum = request.getParameter("phone");
        String rollNo = request.getParameter("roll");
        
        Student student = new Student();
        student.setName(name);
        student.setPhoneNumber(Long.parseLong(phNum));
        student.setRoll(Integer.parseInt(rollNo));
        
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        
        Session session = factory.openSession();
        
        Transaction txn = session.beginTransaction();
        
        session.save(student);
        
        txn.commit();
        session.close();
        factory.close();
        
        
            // set the content type of response to 'text/html'
        response.setContentType("text/html");
          
        // Get the PrintWriter object to write 
        // the response to the text-output stream
        PrintWriter out = response.getWriter();
          
        // Print the data
        out.print("<html><body>");
        out.print("<h3>Details Entered</h3><br/>");
          
        out.print("Full Name: "+ name + "<br/>");
        out.print("Phone Number: "+ phNum +"<br/>");
        out.print("Roll Number: "+ rollNo +"<br/>");
        
        out.print("<h1>Student record added to DB!!!</h1><br/>");
        out.print("</body></html>");
        
          
    }

}
