<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    session.setAttribute("account","");
    session.setAttribute("username","");
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Login Form</title> 
    <link rel="stylesheet" href="css/registration.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
  </head>
<body>
    <div class="container">
      <div class="wrapper">
        <div class="title"><span>Login Form</span></div>
        <form method="post" action="login" id="login-form">
          <div class="row">
            <i class="fas fa-user"></i>
            <input type="text" name="username" id="username" placeholder="User Name" required>
          </div>
          <div class="row">
            <i class="fas fa-lock"></i>
            <input type="password"  name="password" id="password" placeholder="Password" required>
          </div>
          <%
          		String errorMassage="";
				if(session.getAttribute("credentialerror")==null)
				{
					errorMassage="";
				}else{
					errorMassage=session.getAttribute("credentialerror").toString();
				}
			%>
          <div class="pass"><p><%=errorMassage %></p></div>
          <div class="row button">
            <input type="submit" value="Login">
          </div>
          <div class="signup-link">Not a member? <a href="registration.jsp">Signup now</a></div>
        </form>
      </div>
    </div>
  </body>
</html>