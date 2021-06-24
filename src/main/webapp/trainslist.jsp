<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "railwaysApplication.TrainsList" %>
<%@ page import = "java.util.List" %>    
    
<!DOCTYPE html>
<html>
<head>
	<%
		if(session.getAttribute("email")==null)
		{
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		
	%>
	<meta charset="ISO-8859-1">
	<title>Train List</title>
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
			    <h1> TRAINS LIST </h1>
			</div>
			<div>
			    <form action="logout" method="POST">
			        <button type="submit" class="logoutBtn">LOGOUT</button>
			    </form>
			</div>
	   	</div>
	</header>
	<main>
		<div id="trainDetails">
			<h1> AVAILABILITY </h1>
			<table id="trainsTable">
				<tr>
					<th>JOURNEY DATE</th>
					<th>TRAIN NUMBER</th>
					<th>TRAIN NAME</th>
					<th>START</th>
					<th>END</th>
					<th>DEPARTURE</th>
					<th>ARRIVAL</th>
					<th>SECOND AC</th>
					<th>FARE</th>
					<th>THIRD AC</th>
					<th>FARE</th>
					<th>SLEEPER</th>
					<th>FARE</th>
				</tr>
			<%
			List<TrainsList> list  = (List) request.getAttribute("trainslist");
			for(TrainsList tl : list) {
				out.println("<tr>");
				out.println("<td>"+tl.getJourneyDate()+"</td><td>" + tl.getTrainNo() + " </td><td> " + tl.getTrainName()  + " </td><td> " +
						tl.getStartDestination() + " </td><td> " + tl.getEndDestination() + " </td><td>  " + tl.getDepartureTime()
						+ " </td><td> " + tl.getArrivalTime() +"</td><td>" + tl.getSecondAc() + " Seats </td><td>" + tl.getSecondAcFare()
						+ "</td><td>" + tl.getThirdAc() + " Seats </td><td>" + tl.getThirdAcFare() + "</td><td>" + tl.getSleeper() +
						" Seats </td><td>" + tl.getSleeperFare());
				out.println("</tr>");
						}
			%>
			</table>
			<br>
			<br>
		</div>
	</main>
</body>
</html>