<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                <h1>PASSENGER MENU</h1>
            </div>
            <div>
                <form action="logout" method="POST">
                    <button type="submit" class="logoutBtn">LOGOUT</button>
                </form>
            </div>
        </div>
	</header>
	<main>
	<%
		int pnr = (Integer)request.getAttribute("pnr");
		int seatCount = (Integer)request.getAttribute("seats");	
		
	%>
	<form action="<%= request.getContextPath() %>/passenger" method="POST">
	<%
		for(int i=1;i<=seatCount;i++) {
		String idName = "passengername"+i;
		String idAge = "passengerage"+i;
		String idGender = "passengergender"+i;
		String idFood = "foodchoice"+i;
		String idAadhar = "aadharnumber"+i;
			
			
			
	%>
	<div id="passengerTitle"><p> Passenger <%= i %></p></div>
	<div id="passengerDetails">
		
       <div><input size="40" type="text" name="<%= idName %>" class="inputBox" placeholder ="Enter Passenger Name"></div>
      <div><input size="40" type="text" name="<%= idAge %>" class="inputBox" placeholder ="Enter Passenger Age"></div>
       
       <div class="radioTitle">
       <select name="<%= idGender %>" class="inputBox">
	     	<option value="none" selected disabled hidden>
	      				Choose Given Gender
			</option>	
		    <option value="Male">Male</option>
		    <option value="Female">Female</option>
		    <option value="Other">Other</option>
		</select>
		</div>
       
       <div class="radioTitle">
       <select name="<%= idFood %>" class="inputBox">
       		<option value="none" selected disabled hidden>
       				Add Food For Rs.100?
			</option>	
		    <option value="Yes">Yes</option>
		    <option value="No">No</option>
		</select>
		</div>
		<div><input size="40" type="text" name="<%= idAadhar %>" class="inputBox" placeholder ="Enter Aadhar Number"></div>
	</div>
		 
	<%
		}
	%>
	<br>
	<button id="bookButton" type="submit">BOOK TICKET</button>
	</form>
	</main>	
</body>
</html>