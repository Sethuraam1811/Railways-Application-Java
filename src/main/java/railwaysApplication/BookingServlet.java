package railwaysApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;


@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BookingDao bookingDao = new BookingDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String emailId = (String)session.getAttribute("email");
	
			int trainNo = Integer.parseInt(request.getParameter("trainno"));
			session.setAttribute("train", trainNo);
			
			String classChoice = request.getParameter("compartment");
			session.setAttribute("compartments", classChoice);
			
			int seatCount = Integer.parseInt(request.getParameter("seatcount"));
			
			Booking booking = new Booking();
			
			int pnrNumber = (int)(Math.random()*9000)+1000;
			
			booking.setTrainNo(trainNo);
			booking.setClassChoice(classChoice);
			booking.setSeatCount(seatCount);
			booking.setEmailId(emailId);
			booking.setPnrNumber(pnrNumber);
		
			bookingDao.setBookingDetails(booking);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement updateSeats = connection.prepareStatement("SELECT "+ booking.getClassChoice() +" FROM train WHERE train_no = "+ booking.getTrainNo());
			ResultSet rs = updateSeats.executeQuery();
			rs.next();
			int totalSeats = rs.getInt(1);
			
			PreparedStatement updateStatement = connection.prepareStatement("UPDATE train SET "+ booking.getClassChoice() +" = ? WHERE train_no = "+ booking.getTrainNo());
			updateStatement.setInt(1, (totalSeats - booking.getSeatCount()));
			updateStatement.executeUpdate();
			
			session.setAttribute("pnrnumber", pnrNumber);
			session.setAttribute("seats", seatCount);
			request.setAttribute("seats", seatCount);
			request.setAttribute("pnr", pnrNumber);
			
			RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);
		}
		catch(Exception e) {
			System.out.println(e);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}