import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*Author: Thomas Cummins
 * Description: This page is the servlet for the login.html page, it takes in the login details of the user 
 * and checks if the passwords are the same of eachother, if they are then it checks the database
 * (findUser()). If this returns true then the user is sent to the calculator page, if not then it resends the user to the register page.
 * 
 * 
 */
public class login extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		String username = request.getParameter("username");
		
		String[] passwords = request.getParameterValues("password");
	
		

		if (passwords[0].equals(passwords[1])) {
			try {
				if(findUser(username,passwords[0]))
					response.sendRedirect("calculator.html?username="+username);
				else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<html><body> Your user doesn't exist</body></html>");
					response.sendRedirect("register.html");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!passwords[0].equals(passwords[1])) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body> Your passwords do not match</body></html>");
			response.sendRedirect("login.html");
		}
	}
	
	
	public boolean findUser(String username, String password)  throws Exception{
		
		//connection to correct DB
		Connection connection;
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/interestCalculator","root","root");
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from user");
		
		while(rs.next()) {
			String user = rs.getString(2);
			String pWord = rs.getString(3);
			if(username.equalsIgnoreCase(user)&& password.equals(pWord)) {
				return true;
			}
		}
		return false;
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
}
