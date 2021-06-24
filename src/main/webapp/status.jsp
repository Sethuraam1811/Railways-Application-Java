<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "railwaysApplication.Booking" %>
<%@ page import = "railwaysApplication.Passenger" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Booking Status</title>
	<link rel="icon" href="https://myrailinfo.in/IMG/200.png">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		<div class="navBar">
	            <div class="navBarLogo">
	                <img width="80px" src="https://m-www.localcircles.com/a/img/press/railwayslogo.png" alt="railways image">
	            </div>
	            <div class="navBarText">
	                <h1> BOOKING STATUS</h1>
	            </div>
	            <div>
	                <form action="logout" method="POST">
	                    <button type="submit" class="logoutBtn">LOGOUT</button>
	                </form>
	            </div>
	   	</div>
	</header>
	<main>
		<div id="bookingDetails">
			<h2>Ticket Status</h2>
			<table class=confirmTrain>
				<% 
					int i=1;
					List<Booking> list  = (List) request.getAttribute("statusbooking");
					for(Booking b : list) {
						out.println("<tr id=\"tableTrain\"><td> Train Number </td><td> " + b.getTrainNo() + " </td></tr>");
						out.println("<tr><td> Train Name </td><td> " + b.getTrainName() + " </td></tr>");
						out.println("<tr><td> Class Choice </td><td> " + b.getClassChoice() + "</td></tr>");
						out.println("<tr><td> PNR Number </td><td> " + b.getPnrNumber() + "</td></tr>");
						out.println("<tr><td> Status </td><td> " + b.getTicketStatus() + "</td></tr>");
						i++;
					}
					
				%>
				</table>
				<p>${message}</p>
				<table id=confirmPassenger>
				<% 
					int j=1;
					List<Passenger> lst = (List) request.getAttribute("statuspassenger");
					for(Passenger p : lst) {
						out.println("<tr id=\"tablePassenger\" ><td> Passenger " + j + " </td><td> " + p.getPassengerName()  + " </td></tr>");
						out.println("<tr><td> Age </td><td> " + p.getPassengerAge() + " </td></tr>");
						out.println("<tr><td> Gender </td><td> " + p.getPassengerGender() + " </td></tr>");
						j++;
					}
					
				%>
		</table>
		<br><br>
		<div id="demo"></div>
		</div>
	</main>
	<footer>
	

	</footer>
	<script type="text/javascript" src="home.js"></script>
</body>
</html>