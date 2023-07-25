<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Postavljanje Oglasa</title>
</head>
<body>
    <form action="/OglasiWEB/PostavljanjeOglasaServlet" method="get">
    Unesite tekst o Vasem oglasu: <input type="text" name="tekst"> <br>
    Unesite Vas id: <input type="text" name="idKorisnik">
    <input type="submit" value="Unesi">
    </form>
  
</body>
</html>