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

	<c:forEach items="${ordrs}" var="ords">

		<c:forEach items="${ords.orders}" var="order">

			<b>${order.oId} </b>

			<table>
				<tr>
					<th>Quantity</th>
					<th>Order Date</th>
					<th>Customer ID</th>
					<th>Customer Name</th>
					<th>Product ID</th>
					<th>Description</th>
				</tr>

				<tr>
					<td>${order.qty}</td>
					<td>${order.orderDate}</td>

					<td>${ords.cId}</td>
					<td>${ords.cName}</td>

					<td>${order.prod.pId}</td>
					<td>${order.prod.pDesc}</td>

				</tr>

			</table>
		</c:forEach>



	</c:forEach>

	<a href="/index.html">Home</a>
	<a href="/newOrder.html">Add Order</a>
	<a href="/showProducts.html">List Products</a>
	<a href="/showCustomers.html">List Customers</a>
	<a href="/logout1.html">Logout</a>


</body>
</html>