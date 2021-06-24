package railwaysApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterDao {
	
	public void registerUser(Register register) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into user (username,email_id,passcode) values (?,?,?)");
			preparedStatement.setString(1, register.getUsername());
			preparedStatement.setString(2, register.getEmailId());
			preparedStatement.setString(3, register.getPassword());
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
