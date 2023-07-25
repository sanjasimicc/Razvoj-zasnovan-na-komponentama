<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Oglasi</title>
</head>
<body>
   <h3>Dobrodosli na stranicu malih oglasa! </h3>
   <br>
   <br>
   
   <form action="/OglasiWEB/LoginServlet" method="post">
   Username: <input type="text" name="username">
   Password: <input type="text" name="password">
   Nickname: <input type="text" name="nickname">
   <input type="submit" value="Prijavi se">
   </form>

</body>
</html>