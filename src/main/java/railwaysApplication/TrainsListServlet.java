package railwaysApplication;
import railwaysApplication.CancellationServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/trainslist")
public class TrainsListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement selectDetails = connection.prepareStatement("select * from train inner join availability on train.train_no = availability.train_no");
			ResultSet rs = selectDetails.executeQuery();
			
			List <TrainsList> tl = new ArrayList <TrainsList>();
			
			while(rs.next()) {
				TrainsList t = new TrainsList();
				t.setJourneyDate(rs.getString("journey_date"));
				t.setTrainNo(rs.getInt("train_no"));
				t.setTrainName(rs.getString("train_name"));
				t.setStartDestination(rs.getString("start_destination"));
				t.setEndDestination(rs.getString("end_destination"));
				t.setDepartureTime(rs.getString("departure_time"));
				t.setArrivalTime(rs.getString("arrival_time"));
				t.setSecondAc(rs.getInt("second_ac"));
				t.setSecondAcFare(rs.getInt("secondac_fare"));
				t.setThirdAc(rs.getInt("third_ac"));
				t.setThirdAcFare(rs.getInt("thirdac_fare"));
				t.setSleeper(rs.getInt("sleeper"));
				t.setSleeperFare(rs.getInt("sleeper_fare"));
				tl.add(t);
			}
			
			request.setAttribute("trainslist", tl);
			RequestDispatcher rd = request.getRequestDispatcher("trainslist.jsp");
			rd.forward(request, response);
		}
		catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("railwayshome.jsp");
			rd.forward(request, response);
			System.out.println(e);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}
