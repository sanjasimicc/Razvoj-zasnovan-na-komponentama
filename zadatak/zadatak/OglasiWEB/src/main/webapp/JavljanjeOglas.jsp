<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Javljanje na oglas</title>
</head>
<body>
   <form action="/OglasiWEB/JavljanjeServlet" method="get">
   Unesite poruku vezanu za javljanje na zeljeni oglas i id oglasa na koji se odnosi: <br>
    <input type="text" name="tekst">
   <input type="text" name="idOglas">
   <input type="text" name="idKorisnik">
   <input type="submit" value="Posalji">
   </form>

</body>
</html>