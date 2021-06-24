package railwaysApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingDao {
	
	private static String fare;	
	
	public void setBookingDetails (Booking booking) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
		
			int pnrNumber = booking.getPnrNumber();
			String ticketStatus = "confirm";
			
			PreparedStatement insertTickets = connection.prepareStatement("insert into tickets (train_no,class_choice,ticket_count,pnr_number,ticket_status,email_id,journey_date) values (?,?,?,?,?,?,?)");
			insertTickets.setInt(1, booking.getTrainNo());
			insertTickets.setString(2, booking.getClassChoice());
			insertTickets.setInt(3, booking.getSeatCount());
			insertTickets.setInt(4, pnrNumber);
			insertTickets.setString(5, ticketStatus);
			insertTickets.setString(6, booking.getEmailId());
			insertTickets.setString(7,booking.getJourneyDate());
			insertTickets.executeUpdate();
			
//			String getClassChoice = booking.getClassChoice();
//			System.out.println(getClassChoice);
//			if(getClassChoice.contains("second_ac"))
//				fare = "secondac_fare";
//			else if((getClassChoice.contains("third_ac")))
//				fare = "thirdac_fare";
//			else if((getClassChoice.contains("sleeper")))
//				fare = "sleeper_fare";
//			
//			int foodCount = 0;
//			if(booking.getFoodChoice().contains("Yes")) {
//				foodCount++;
//			}
//			
//			PreparedStatement getStatement = connection.prepareStatement("SELECT "+ booking.getClassChoice() +" , food_fare FROM train WHERE train_no = "+ booking.getTrainNo());
//			ResultSet rs = getStatement.executeQuery();
//			rs.next();
//			int totalSeats = rs.getInt(1);
//			int foodFare = rs.getInt(2);
//			System.out.println(foodFare);
//
//			int totalFood = foodCount * foodFare;
//			System.out.println(totalFood);
//			
//			PreparedStatement updateStatement = connection.prepareStatement("UPDATE train SET "+ booking.getClassChoice() +" = ? WHERE train_no = "+ booking.getTrainNo());
//			updateStatement.setInt(1, (totalSeats - booking.getSeatCount()));
//			updateStatement.executeUpdate();
//			
//			PreparedStatement getFare = connection.prepareStatement("SELECT "+ fare +" FROM train WHERE train_no = "+ booking.getTrainNo());
//			ResultSet rx = getFare.executeQuery();
//			rx.next();
//			int fareCalculate = rx.getInt(1);
//			System.out.println(fareCalculate);
//			int totalFare = (booking.getSeatCount() * fareCalculate) + totalFood;
//			System.out.println(totalFare);
//			booking.setFinalfare(totalFare);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
