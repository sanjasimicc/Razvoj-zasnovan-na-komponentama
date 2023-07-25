<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled oglasa</title>
</head>
<body>
    <form action="/OglasiWEB/PregledServlet" method="get">
    Unesite kljucnu rec za pretragu svih oglasa: <input type="text" name="pretrazi"> <br> 
    <br>
    <input type="submit" value="Pretrazi">
    <br> 
    <c:if test="${!empty oglasi}">
    <br>
    
    <table border="1">
       <tr> <th> Tekst oglasa: </th> <th> Broj pregleda: </th> </tr>
       <c:forEach items="${oglasi}" var="o">
         <tr>
            <td> ${o.text}</td>
            <td> ${o.brojPregleda} </td>
            
         </tr>
       </c:forEach>
    
    </c:if>
    </form>

</body>
</html>