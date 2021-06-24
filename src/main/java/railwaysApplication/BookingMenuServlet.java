package railwaysApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/bookingmenu")
public class BookingMenuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
		
			HttpSession session = request.getSession(false);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select start_destination,end_destination from train");
			ResultSet rs = preparedStatement.executeQuery();
			 	
			List <BookingMenu> list = new ArrayList <BookingMenu>();
			
			while(rs.next()) {
				BookingMenu train = new BookingMenu();
				train.setStartDestination(rs.getString("start_destination"));
				train.setEndDestination(rs.getString("end_destination"));
				list.add(train);
			}
			
			Gson gson = new Gson();
			String statusJson = gson.toJson(list);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(statusJson);
		
		}
		catch(Exception e) {
			out.write("ERROR");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}