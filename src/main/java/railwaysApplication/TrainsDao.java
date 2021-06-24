package railwaysApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainsDao {
	
	public void getTrainDetails(Trains train) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from train where journey_date = '"+ train.getJourneyDate() +"' and start_destination = '"+train.getStartDestination() +"' and end_destination = '"+train.getEndDestination() +"' ");
			ResultSet rs = preparedStatement.executeQuery();
			
			 	
			while(rs.next()) {
				train.setTrainNoRet(rs.getInt("train_no"));
				train.setTrainNameRet(rs.getString("train_name"));
				train.setJourneyDate(rs.getString("journey_date"));
				train.setStartDestination(rs.getString("start_destination"));
				train.setEndDestination(rs.getString("end_destination"));
				train.setDepartureTimeRet(rs.getString("departure_time"));
				train.setArrivalTimeRet(rs.getString("arrival_time"));
				System.out.println(train.getTrainNoRet() + train.getTrainNameRet());
			}
			
			//System.out.println(train.getTrainNoRet() + train.getTrainNameRet());
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
}
