<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import = "railwaysApplication.BookingMenuServlet" %>
<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 	
	
	if(session.getAttribute("username")==null)
	{
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="https://myrailinfo.in/IMG/200.png">
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta charset="ISO-8859-1">
    <title>Railways Home</title>
</head>
<body>
    <header>
    	<%
    	
			String username = (String)session.getAttribute("username");
		 %>
        <div class="navBar">
            <div class="navBarLogo">
                <img width="80px" src="https://m-www.localcircles.com/a/img/press/railwayslogo.png" alt="railways image">
            </div>
            <div class="navBarText">
                <h1>BOOKING MENU</h1>
            </div>
            <div>
                <form action="logout" method="get">
                    <button type="submit" class="logoutBtn">LOGOUT</button>
                </form>
            </div>
        </div>
    </header>

    <main>
        <div class="homeTab">
        	<div id="homeMenu">
        		<div id="helloUser">Hello, <%= username%>!</div>
	            <div>
     	          	<button type="button" id="bookBtn">Book Ticket</button>
	            </div>
	            <div id="bookingAlert"> ${message} </div> 
	            <div id="trainsPosition">
					<form action="<%= request.getContextPath() %>/trains" method="POST" name="bookingForm" autocomplete="off">
						<p><input class="inputBox" type="date" id="journeyDate" placeholder="Journey Date" name="journeydate" required /></p>
						<p><input required size="30" class="inputBox" type="text" id="trainStart" list="browsers" placeholder="Start Destination" name="startdestination" /></p>
						 <datalist id="browsers">
						    
						 </datalist>
					
						<p><input required size="30" class="inputBox" type="text" id="trainEnd" list="browser" placeholder="End Destination" name="enddestination" /></p>
						 <datalist id="browser">
						    
						 </datalist>
						
						<button type="submit" id="trainSubmit"> CHECK TRAINS </button>
					</form>
				</div>
	            <br>
	            <br>
	            <div>
                    <button type="submit" id="cancelBtn">Cancel Ticket</button>
	            </div>
	            <div id="cancelPosition">
	            	<form action="<%= request.getContextPath() %>/cancellation" method="POST">
						<p><input required size="30" class="inputBox" type="text" name="cancelpnr" placeholder="Enter Your PNR" /></p>
						<button type="submit" id="cancelSubmit"> GET BOOKING </button>
					</form>
	            </div>
	            
	            <br>
	            <br>
	            <div>
                    <button type="submit" id="statusBtn">Ticket Status</button>
	            </div>
	            <div id="statusPosition">
					<form method="POST" id="statusForm" name="statusForm">
						<p><input required name="pnrstatus" size="30" class="inputBox" type="text" id="statusPnr" placeholder="Enter Your PNR" /></p>
						<button type="button" id="statusSubmit"> GET STATUS </button>
					</form>	            
	            </div>
	            <div id="demo">
	            	 <table id="statusTable" class="confirmTrain">
	            	 
	            	</table>	
	            </div>
	            <div id="trainsListDiv">
		            <form action="<%= request.getContextPath() %>/trainslist" method="POST">
		            	<button type="submit" id="trainsBtn"> Check Trains List </button>
		            </form>
	            </div>
	         </div>
	  		 <div class="homeImage">
            	<img class="trainImg" src="https://cms.qz.com/wp-content/uploads/2018/09/indian-railways-history.jpg?quality=75&strip=all&w=1600&h=900&crop=1">
                <img class="trainImg" src="https://www.orissapost.com/wp-content/uploads/2019/01/whatshot-recruitment-2018-1.jpg">
                <img class="trainImg" src="https://upload.wikimedia.org/wikipedia/commons/c/c8/WAP-4_Class_locomotive_of_Indian_Railways.jpg">
            </div>        
        </div>
       
    </main>

    <footer>

    </footer>
    <script type="text/javascript" src="home.js"></script>
    <script type="text/javascript" src="homeimages.js"></script>
</body>
</html>

<!-- action="<%= request.getContextPath() %>/status" -->