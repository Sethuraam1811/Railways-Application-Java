<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Railways Login</title>
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
                <h1> WELCOME TO THE RAILWAYS APPLICATION </h1>
            </div>
        </div>
    </header>
        
    <main id="main">
        <div class="loginMain">
            <div></div>
            <div class="form-1">
                <form action="<%= request.getContextPath() %>/login" method="POST">
                    <p class="formHeading">Enter Login Details</p>
                    <div id="wrongLogin"> ${message} </div>
                    <p><input autocomplete="off" size="35" type="email" name="email" class="inputBox" placeholder="Enter Email ID" required></p>
                    <p><input size="35" type="password" name="password" class="inputBox" placeholder="Enter Password" required></p>
                    <button type="submit" id="loginBtn">LOGIN</button>
                    <br/>
                    <br/>
                    <span class="redirect"> New User? To Register <a href="register.jsp"><button type="button" id="reDirect">Click here</button></a></span>
                </form>
            </div>
            <div></div>
        </div>
    </main>
    
    <footer>
        <div>
           
        </div>
    </footer>

</body>
</html>