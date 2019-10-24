<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	Welcome Aliens.. 
	<a href="/logout-success">logout</a>
	
	
	<form action = "/calculate" method = "GET">
	Hello     :    <input type="text" name="username" value = "${username}" />
	User Type :    <input type="text" name="usertype" value = "${usertype}" />
	
	<table>
  <c:forEach var="product" items="${products}">
    <tr>
    <td><input type="checkbox" name="selectedProducts" value="${product.productName}"/></td>
      <td><c:out value="${product.productName}" /></td>
      <td><c:out value="${product.productPrice}" /></td>
    </tr>
  </c:forEach>
</table>

<input type = "submit" value = "Calculate Bill" />
      </form>
      
      Calculated Discount :    <input type="text" name="discountedPrice" value = "${discountedPrice}" />

</body>
</html>