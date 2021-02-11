import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*Author: Thomas Cummins
 * Description: This page is the destination after the calculation.html page user inpus the form details, this page takes in those 
 * details and creates 2 tables for the results, the first table is for each years interest total, then the second table is for the total after the term.
 */
public class mainPage extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		String principal = request.getParameter("principal");
		String rate = request.getParameter("interestRate");
		String years = request.getParameter("term");
		
		double p = 0.0;
		double r = 0.0;
		double y = 0.0;
		if (principal != null)
			 p = Double.parseDouble(principal);
		if (rate != null)
		 r = Double.parseDouble(rate);
		if (years != null)
		 y = Double.parseDouble(years);
		
		
		
		double total = 0;
		String returnVar ="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head> "
				+ "<link rel=\"stylesheet\" href=\"styles.css\">"
				+ "<title> Members only interest calculator </title> </head> \r\n" + 
				"<body>\r\n" + 
				"<h1 Welcome to D4's members only interest calculator! </h1>\r\n" + 
				"<table>\r\n" + 
				"  <tr>\r\n" + 
				"    <th>Each year Interest</th>\r\n"+
				"  </tr>\r\n";
		for(double i = 1; i<=y;i++) {
			 
			total =p * Math.pow(((.01*r)/1)+1, i) - p;
			returnVar = returnVar +  
					"  <tr>\r\n" + 
					"    <td>Year "+i +". $" + Math.round(total)+"</td>\r\n" + 
					"  </tr>\r\n";
		}
		returnVar = returnVar +"</table>\r\n" + 
		"<table>\r\n" + 
		"  <tr>\r\n" + 
		"    <th>Final Total</th>\r\n" + 
		"  </tr>\r\n" + 
		"  <tr>\r\n" + 
		"    <td> total :$"+(Math.round(p+total))+"</td>\r\n" + 
		"  </tr>\r\n" + 
		"</table>\r\n" +
		"</body>\r\n" +
		"<a href=\"calculator.html\">Reset</a>"+
		"</html>";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(returnVar);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
	
}
