<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%		
		if(session.getAttribute("username")==null)
		{
			response.sendRedirect("login.jsp");
		}
	%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<link href="css/registration.css" rel="stylesheet" />
<meta charset="ISO-8859-1">
<title>Transaction page</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <h1 class="navbar-brand">Finance Management System</h1>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
  </div>
</nav>
	
		<div class="container">
     	<div class="wrapper">
		    <div class="title"><span>Your Account Balance</span></div>
          	<div class="p-4">
            <label>Account Number</label><label class="offset-md-1"> :</label><label> <%=session.getAttribute("account_no") %></label><br/>
            <label>Balance </label><label class="offset-md-3"> :</label><label> <%=session.getAttribute("balance") %></label><br/>
            </div>
            <div class="m-2"></div>
            <a href="transaction"><button class="btn btn-secondary w-100 mt-2">Back</button></a>
            </div>
        </div>
 
</body>
</html>