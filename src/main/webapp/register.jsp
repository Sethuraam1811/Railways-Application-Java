<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="https://myrailinfo.in/IMG/200.png">
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta charset="ISO-8859-1">
    <title>User Registration</title>
</head>
<body>
	<header>
        <div class="navBar">
            <div class="navBarLogo">
                <img width="80px" src="https://m-www.localcircles.com/a/img/press/railwayslogo.png" alt="railways image">
            </div>
            <div class="navBarText">
                <h1>NEW USER REGISTRATION</h1>
            </div>
        </div>
    </header>
    
    <main id="main">
        <div class="loginMain">
            <div></div>
            <div class="form-1">
                <form action="<%= request.getContextPath() %>/register" method="POST">
                    <p class="formHeading">Create Your Account</p>
                    <div id="wrongRegister"> ${message} </div>
                    <p><input size="35" type="text" name="username" class="inputBox" placeholder ="Enter Username" required></p>
                    <p><input size="35" type="email" name="email" class="inputBox" placeholder ="Enter Email ID" required></p>
                    <p><input size="35" type="password" name="password" class="inputBox" placeholder ="Enter Password" required></p>
                    <button id="registerBtn">REGISTER</button>
           			<br/>
           			<br/>
                    <span class="redirect"> Existing User? To Login <a href="login.jsp"><button type="button" id="reDirect">Click here</button></a></span>
          		</form>
            </div>
            <div></div>
        </div>
    </main>
    
    <footer>

        
    </footer>
</body>
</html>