package railwaysApplication;

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

@WebServlet("/trains")
public class TrainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(false);
			session.getAttribute("username");
			
			String journeyDate = request.getParameter("journeydate");
			session.setAttribute("journey", journeyDate);
			
			String startDestination = request.getParameter("startdestination");
			String endDestination = request.getParameter("enddestination");
		
			
			Trains tran = new Trains();
			tran.setJourneyDate(journeyDate);
			tran.setStartDestination(startDestination);
			tran.setEndDestination(endDestination);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/railways","root","Mysql123!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from train inner join availability on train.train_no = availability.train_no where availability.journey_date = '"
					+journeyDate+"' and start_destination = '"+startDestination+"' and end_destination = '"+ endDestination +"' ");
			ResultSet rs = preparedStatement.executeQuery(); 
			 	
			List <Trains> list = new ArrayList <Trains>();
			
			while(rs.next()) {
				Trains train = new Trains();
				train.setTrainNoRet(rs.getInt("train_no"));
				train.setTrainNameRet(rs.getString("train_name"));
				train.setJourneyDate(rs.getString("journey_date"));
				train.setStartDestination(rs.getString("start_destination"));
				train.setEndDestination(rs.getString("end_destination"));
				train.setDepartureTimeRet(rs.getString("departure_time"));
				train.setArrivalTimeRet(rs.getString("arrival_time"));
				train.setSecondAcRet(rs.getInt("second_ac"));
				train.setSecondAcFareRet(rs.getInt("secondac_fare"));
				train.setThirdAcRet(rs.getInt("third_ac"));
				train.setThirdAcFareRet(rs.getInt("thirdac_fare"));
				train.setSleeperRet(rs.getInt("sleeper"));
				train.setSleeperFareRet(rs.getInt("sleeper_fare"));
				list.add(train);
			}
			
			if(list.isEmpty()) {
				request.setAttribute("train", list);
				request.setAttribute("message", "No trains available");
				RequestDispatcher rd = request.getRequestDispatcher("railwayshome.jsp");
		        rd.forward(request, response);
			}
			else {
				request.setAttribute("train", list);
				RequestDispatcher rd = request.getRequestDispatcher("trains.jsp");
		        rd.forward(request, response);
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
			RequestDispatcher rd = request.getRequestDispatcher("railwayshome.jsp");
	        rd.forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} 
}
