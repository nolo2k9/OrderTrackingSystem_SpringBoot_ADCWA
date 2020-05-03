<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<link href="/css/style.css" rel="stylesheet"></link>
<head>
<meta charset="ISO-8859-1">
<title>List of Customers</title>
</head>
<body>
	<h1>List of Customers</h1>

	<c:forEach items="${custs}" var="cust">
		<b>${cust.cId} </b>
		<b>${cust.cName}</b>
		<br>
		<br>
       <b> ${cust.cName}'s Orders</b>
		<table>

			<tr>
				<th>Order ID</th>
				<th>Quantity</th>
				<th>Product ID</th>
				<th>Description</th>
			</tr>
			<tr>
				<c:forEach items="${cust.orders}" var="order">
					<tr>
						<td>${order.oId}</td>
						<td>${order.qty}</td>
						<td>${order.prod.pId}</td>
						<td>${order.prod.pDesc}</td>

					</tr>
				</c:forEach>
			</tr>

		</table>
	</c:forEach>


	<a href="/index.html">Home</a>
	<a href="/addCustomer.html">Add Customer</a>
	<a href="/showProducts.html">ListProducts</a>
	<a href="/showOrders.html">List Orders</a>
	<a href="/logout.html">Logout</a>



</body>
</html>