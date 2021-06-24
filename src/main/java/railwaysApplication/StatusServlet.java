package railwaysApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
			//int pnrNumber = Integer.parseInt(request.getParameter("pnrstatus"));
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");

			List <Booking> bk = new ArrayList <Booking>();
			List <Passenger> pas = new ArrayList <Passenger>();
		
			PreparedStatement selectDetails = connection.prepareStatement("select tickets.train_no, train.train_name, class_choice, pnr_number,ticket_status,ticket_count from railways.tickets inner join railways.train on tickets.train_no = train.train_no");
			ResultSet rs = selectDetails.executeQuery();
			
//			PreparedStatement selectPassenger = connection.prepareStatement("select passenger_name, passenger_age, passenger_gender,pnr_number from passenger"); //where pnr_number = "+pnrNumber);
//			ResultSet r = selectPassenger.executeQuery();
			
			while(rs.next()) {
				Booking b = new Booking();
				b.setTrainNo(rs.getInt(1));
				b.setTrainName(rs.getString(2));
				b.setClassChoice(rs.getString(3));
				b.setPnrNumber(rs.getInt(4));
				b.setTicketStatus(rs.getString(5));
				b.setSeatCount(rs.getInt(6));
				bk.add(b);
				
			} 
			
			
//			while(r.next()) {
//				Passenger p = new Passenger();
//				p.setPassengerName(r.getString(1));
//				p.setPassengerAge(r.getInt(2));
//				p.setPassengerGender(r.getString(3));
//				p.setPnrNumber(r.getInt(4));
//				pas.add(p);
//			}
			
			/*if(count==0) {
				out.println("PNR DOES NOT EXIST");
//				request.setAttribute("message","PNR DOES NOT EXIST");
			}*/
			
			Gson gson = new Gson();
			String detailsJson = gson.toJson(bk);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(detailsJson);
			
			
			/*request.setAttribute("statusbooking", bk);
			request.setAttribute("statuspassenger", pas);
			RequestDispatcher rd = request.getRequestDispatcher("status.jsp");
			rd.forward(request, response);*/
			
		}
		catch(Exception e) {
			/*RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			System.out.println(e);*/
			out.println("ENTER A VALID PNR");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}
