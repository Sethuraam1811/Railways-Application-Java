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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/cancellation")
public class CancellationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int pnrNumber = Integer.parseInt(request.getParameter("cancelpnr"));
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");

			List <Booking> bkn = new ArrayList <Booking>();
			List <Passenger> psn = new ArrayList <Passenger>();
		
			PreparedStatement selectDetails = connection.prepareStatement("select tickets.train_no, train.train_name, class_choice, pnr_number,ticket_status from railways.tickets inner join railways.train on tickets.train_no = train.train_no where pnr_number = "+pnrNumber);
			ResultSet rs = selectDetails.executeQuery();
			
			PreparedStatement selectPassenger = connection.prepareStatement("select passenger_name, passenger_age, passenger_gender from passenger where pnr_number = "+pnrNumber);
			ResultSet r = selectPassenger.executeQuery();
			
			
			int count = 0;
			while(rs.next()) {
				count++;
				Booking b = new Booking();
				b.setTrainNo(rs.getInt(1));
				b.setTrainName(rs.getString(2));
				b.setClassChoice(rs.getString(3));
				b.setPnrNumber(rs.getInt(4));
				b.setTicketStatus(rs.getString(5));
				bkn.add(b);
			}
			
			while(r.next()) {
				Passenger p = new Passenger();
				p.setPassengerName(r.getString(1));
				p.setPassengerAge(r.getInt(2));
				p.setPassengerGender(r.getString(3));
				psn.add(p);
			}
			
			if(count==0) {
				request.setAttribute("message","PNR DOES NOT EXIST");
			}
			else {
				PreparedStatement getData = connection.prepareStatement("SELECT class_choice,ticket_count,train_no,journey_date FROM tickets WHERE pnr_number = "+ pnrNumber);
				ResultSet rx = getData.executeQuery();
				rx.next();
				String classChoice = rx.getString(1);
				int seatCount = rx.getInt(2);
				int trainNo = rx.getInt(3);
				String journeyDate = rx.getString(4);
				
				
				PreparedStatement updateSeats = connection.prepareStatement("SELECT "+ classChoice +" FROM availability WHERE train_no = "+ trainNo +
						" and journey_date = '"+journeyDate+"'");
				ResultSet res = updateSeats.executeQuery();
				res.next();
				int totalSeats = res.getInt(1);
				
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE availability SET "+ classChoice +" = ? WHERE train_no = "+ trainNo +
					" and journey_date = '"+journeyDate+"'");
				updateStatement.setInt(1, (totalSeats + seatCount));
				updateStatement.executeUpdate();
				
				PreparedStatement cancelTicket = connection.prepareStatement("DELETE FROM tickets WHERE pnr_number = "+ pnrNumber);
				cancelTicket.execute();
				
				PreparedStatement deletePassengers = connection.prepareStatement("DELETE FROM passenger WHERE pnr_number = "+pnrNumber);
				deletePassengers.execute();
			}
			request.setAttribute("cancelbooking", bkn);
			request.setAttribute("cancelpassenger", psn);
			RequestDispatcher rd = request.getRequestDispatcher("cancellation.jsp");
			rd.forward(request, response);
			
			
		}
		catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}
