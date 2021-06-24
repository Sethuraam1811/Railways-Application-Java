<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "railwaysApplication.Trains" %>
<%@ page import = "java.util.List" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Railways Booking</title>
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
                <h1> BOOKING MENU </h1>
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
					<th>TRAIN NUMBER</th>
					<th>TRAIN NAME</th>
					<th>START</th>
					<th>END</th>
					<th>DEPARTURE</th>
					<th>ARRIVAL</th>
					<th style="background-color:#008489">SECOND AC</th>
					<th style="background-color:#008489">FARE</th>
					<th style="background-color:#329542">THIRD AC</th>
					<th style="background-color:#329542">FARE</th>
					<th style="background-color:#3A6324">SLEEPER</th>
					<th style="background-color:#3A6324">FARE</th>
				</tr>
			<%
			List<Trains> list  = (List) request.getAttribute("train");
			for(Trains train : list) {
				out.println("<tr>");
				out.println("<td>" + train.getTrainNoRet() + " </td><td> " + train.getTrainNameRet()  + " </td><td> " +
						train.getStartDestination() + " </td><td> " + train.getEndDestination() + " </td><td>  " + train.getDepartureTimeRet()
						+ " </td><td> " + train.getArrivalTimeRet() +"</td><td>" + train.getSecondAcRet() + " Seats </td><td>" + train.getSecondAcFareRet()
						+ "</td><td>" + train.getThirdAcRet() + " Seats </td><td>" + train.getThirdAcFareRet() + "</td><td>" + train.getSleeperRet() +
						" Seats </td><td>" + train.getSleeperFareRet());
				out.println("</tr>");
						}
			%>
			</table>
			<br>
			<br>
		</div>
		<form action="<%= request.getContextPath() %>/passenger" method="POST">
		<div>
				<div id="userInput">
			       <div class="trainTags"> Enter Train Number <input size="15" type="text" name="trainno" class="inputBox"></div>
			       <div>
				       <span class="trainTags">Choose Booking Class</span>
				      	<select name="compartment" class="inputBox" >
					      	<option value="none" selected disabled hidden>
		          				Select an Option
		      				</option>	
						    <option value="second_ac">Second AC</option>
						    <option value="third_ac">Third AC</option>
						    <option value="sleeper">Sleeper Class</option>
		  				</select>
	  				</div>
			       <div class="trainTags">
			       		<span class="trainTags">Select Number of Seats</span>
			       		<select name="seatcount" class="inputBox" id="seatCount">
			       			<option value="none" selected disabled hidden>
		          				Select an Option
		      				</option>	
						    <option value="1">1</option>
						    <option value="2">2</option>
						    <option value="3">3</option>
						    <option value="4">4</option>
						    <option value="5">5</option>
						    <option value="6">6</option>
			       		</select>	
			       </div>
			       <div>
			       		<button type="button" id="trainSubmit">ADD PASSENGERS</button>
			       </div>
			     </div>
		 </div>
		 <div id="passengerDetails">
		 	
		 </div>
		 <button id="bookButton" type="submit">BOOK TICKET</button>
		 </form>
	 </main>
	 <script type="text/javascript" src="bookingscript.js"></script>
</body>
</html>