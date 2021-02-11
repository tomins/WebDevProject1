import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*Author: Thomas Cummins
 * Description: This page is the servlet for the register.html page, it takes in the login details of the user 
 * and checks if the passwords are the same of eachother, if they are then it adds the user to the database. 
 * Then the user is routed to the calculator page
 * 
 */
public class register extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
	
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String[] passwords = request.getParameterValues("password");
		if (passwords[0].equals(passwords[1])) {
			response.sendRedirect("calculator.html");
			try {
				addUser(name,username,passwords[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		else if(!passwords[0].equals(passwords[1])) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body> Your passwords do not match</body></html>");
		}
	}
	
	public void addUser(String name, String username, String password) throws Exception {
				//connection to correct DB
				Connection connection;
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/interestCalculator","root","root");
				
				//prepared statements for users and classes
				PreparedStatement createUser = connection.prepareStatement(
						"INSERT into user " + "(name, username, password)" + "VALUES (?,?,?)");
				
				
				//calling the actual parepared statements
				createUser.setString(1, name);
				createUser.setString(2, username);
				createUser.setString(3, password);
				int rowUpdated = createUser.executeUpdate();
				createUser.close();		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
}
