package railwaysApplication;

import java.sql.*;

public class LoginDao {

	public boolean validateUser(Login login) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select email_id,passcode,username from user");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				if(login.getEmailId().equals(rs.getString(1)) && login.getPassword().equals(rs.getString(2))) {
					login.setUsername(rs.getString(3));
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}

	}
	
}
