package railwaysApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/passenger")
public class PassengerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PassengerDao passengerDao = new PassengerDao();
	BookingDao bookingDao = new BookingDao();
	private String fare;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession(false);
			String emailId = (String)session.getAttribute("email");
			
			String journeyDate = (String)session.getAttribute("journey");
			
			int trainNo = Integer.parseInt(request.getParameter("trainno"));
			
			String classChoice = request.getParameter("compartment");
			
			int seatCount = Integer.parseInt(request.getParameter("seatcount"));
			
			int pnrNumber = (int)(Math.random()*9000)+1000;
			
			String ticketStatus = "confirmed";
			
			Booking booking = new Booking();
			
			booking.setTrainNo(trainNo);
			booking.setClassChoice(classChoice);
			booking.setSeatCount(seatCount);
			booking.setEmailId(emailId);
			booking.setPnrNumber(pnrNumber);
			booking.setTicketStatus(ticketStatus);
			booking.setJourneyDate(journeyDate);
			bookingDao.setBookingDetails(booking);
			
			
			/*int seatCount = (Integer)session.getAttribute("seats");
			int pnrNumber = (Integer)session.getAttribute("pnrnumber");
			String classChoice = (String)session.getAttribute("compartments");
			int trainNo = (Integer)session.getAttribute("train");
			*/
			Passenger passenger = new Passenger();
			
			int foodCount = 0;
			
			for(int i=1;i<=seatCount;i++) {
				String passengerName = request.getParameter("passengername"+i);
				int passengerAge = Integer.parseInt(request.getParameter("passengerage"+i));
				String passengerGender = request.getParameter("passengergender"+i);
				String foodChoice = request.getParameter("foodchoice"+i);
				if(foodChoice.equals("Yes"))
					foodCount++;
				
				String aadharNumber = request.getParameter("aadharnumber"+i);

				passenger.setPassengerName(passengerName);
				passenger.setPassengerAge(passengerAge);
				passenger.setPassengerGender(passengerGender);
				passenger.setFoodChoice(foodChoice);
				passenger.setAadharNumber(aadharNumber);
				passenger.setPnrNumber(pnrNumber);
				passengerDao.updatePassenger(passenger);
			}
	
			if(classChoice.equals("second_ac"))
				fare = "secondac_fare";
			else if(classChoice.equals("third_ac"))
				fare = "thirdac_fare";
			else if(classChoice.equals("sleeper"))
				fare = "sleeper_fare";
			
			
			List <Booking> bk = new ArrayList <Booking>();
			List <Passenger> pas = new ArrayList <Passenger>();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement updateSeats = connection.prepareStatement("SELECT "+ booking.getClassChoice() +" FROM availability WHERE train_no = "+ booking.getTrainNo()+
					" and journey_date = '"+journeyDate+"'");
			ResultSet res = updateSeats.executeQuery();
			res.next();
			int totalSeats = res.getInt(1);
			
			PreparedStatement updateStatement = connection.prepareStatement("UPDATE availability SET "+ booking.getClassChoice() +" = ? WHERE train_no = "+ booking.getTrainNo()+
				" and journey_date = '"+journeyDate+"'");
			updateStatement.setInt(1, (totalSeats - booking.getSeatCount()));
			updateStatement.executeUpdate();
			
			PreparedStatement selectDetails = connection.prepareStatement("select tickets.train_no, train.train_name, class_choice, pnr_number,ticket_status from railways.tickets inner join railways.train on tickets.train_no = train.train_no where pnr_number = "+pnrNumber);
			ResultSet rs = selectDetails.executeQuery();
			
			PreparedStatement selectPassenger = connection.prepareStatement("select passenger_name, passenger_age, passenger_gender from passenger where pnr_number = "+pnrNumber);
			ResultSet r = selectPassenger.executeQuery();
			
			PreparedStatement fareCalculate = connection.prepareStatement("select "+ fare +" ,food_fare from train where train_no = "+ trainNo);
			ResultSet rx = fareCalculate.executeQuery();
			rx.next();
			int totalFare = (rx.getInt(1) * seatCount) + (rx.getInt(2) * foodCount);
			
			while(rs.next()) {
				Booking b = new Booking();
				b.setTrainNo(rs.getInt(1));
				b.setTrainName(rs.getString(2));
				b.setClassChoice(rs.getString(3));
				b.setPnrNumber(rs.getInt(4));
				b.setTicketStatus(rs.getString(5));
				b.setFinalfare(totalFare);
				bk.add(b);
			}
			
			while(r.next()) {
				Passenger p = new Passenger();
				p.setPassengerName(r.getString(1));
				p.setPassengerAge(r.getInt(2));
				p.setPassengerGender(r.getString(3));
				pas.add(p);
			}
			
			request.setAttribute("list1", bk);
			request.setAttribute("list2", pas);
			RequestDispatcher rd = request.getRequestDispatcher("confirmbooking.jsp");
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
