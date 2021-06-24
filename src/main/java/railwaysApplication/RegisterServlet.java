package railwaysApplication;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	private RegisterDao registerDao = new RegisterDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String emailId = request.getParameter("email");
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			
			Register register = new Register();
			register.setUsername(username);
			register.setEmailId(emailId);
			register.setPassword(password);
			
			if(username.length()>=3 && emailId.contains("@gmail.com") && password.length()>=6) {
				registerDao.registerUser(register);
				request.setAttribute("message", "Registration Successful, Login to Continue"); 
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else {
				request.setAttribute("message", "Enter Valid Details to Register"); 
				request.getRequestDispatcher("register.jsp").forward(request,response);
			}
				
		}
		catch(Exception e) {
			request.getRequestDispatcher("register.jsp").forward(request,response);
			System.out.println(e);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}
