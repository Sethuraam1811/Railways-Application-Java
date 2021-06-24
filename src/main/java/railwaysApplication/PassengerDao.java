package railwaysApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PassengerDao {

	public void updatePassenger(Passenger passenger) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into passenger (passenger_name, passenger_age, passenger_gender, food_choice, aadhar_number,pnr_number) values (?,?,?,?,?,?)");
			preparedStatement.setString(1,passenger.getPassengerName());
			preparedStatement.setInt(2,passenger.getPassengerAge());
			preparedStatement.setString(3, passenger.getPassengerGender());
			preparedStatement.setString(4, passenger.getFoodChoice());
			preparedStatement.setString(5, passenger.getAadharNumber());
			preparedStatement.setInt(6, passenger.getPnrNumber());
			preparedStatement.executeUpdate();
			
			
			//NEED TO LINK PNR FROM TRAINS SERVLET USING SESSION
			
		}
		catch(Exception e) {
			
		}
	}
}
