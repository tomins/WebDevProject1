import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Author: Thomas Cummins
 * Description: This page is the servlet for the index.html page, it takes in the radio button form submission data
 * and reroutes the user to the login.html or register.html based on their choice
 */
public class IntrestCalculator extends HttpServlet{

	
			public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException{
				
				String firstChoice = request.getParameter("firstChoice");//gets the users choice for the radio box on the home page
				
				
				
				if(firstChoice.equalsIgnoreCase("login")) {
					response.sendRedirect("login.html");
					
					 
				}
				
					
				
				else {
					response.sendRedirect("register.html");
					
				}
				
				
				
					
			}
			
			public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
				}
			
			
			
}
