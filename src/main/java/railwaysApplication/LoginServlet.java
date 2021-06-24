package railwaysApplication;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("email");
		String password = request.getParameter("password");
		
		Login login = new Login();
		login.setEmailId(emailId);
		login.setPassword(password);
		
		try {
			if((emailId.equals("admin@gmail.com")) && (password.equals("qwerty"))) {
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            	rd.forward(request, response);
			}
			else if(loginDao.validateUser(login)) {
				 HttpSession session = request.getSession(true);
	             session.setAttribute("email",emailId);
	             session.setAttribute("username", login.getUsername());
	             response.sendRedirect("railwayshome.jsp");
//	             RequestDispatcher rd = request.getRequestDispatcher("railwayshome.jsp");
//	             rd.forward(request, response);
			}
			else {
				request.setAttribute("message", "Invalid Username or Password"); 
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
	
}
