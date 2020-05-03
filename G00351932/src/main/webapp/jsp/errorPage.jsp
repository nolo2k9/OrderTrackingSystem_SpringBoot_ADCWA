<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${exception.header}</h1>
	<h2>${exception.error}</h2>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Description</th>
			<th>Quantity</th>
		</tr>

		<tr>
		<tr>
			<td>${exception.productId}</td>
			<td>${exception.customerId}</td>
			<td>${exception.qty}</td>
		</tr>


	</table>
	<a href="/index.html">Home</a>
	<a href="/newOrder.html">Add Products</a>
	<a href="/showOrders.html">List Orders</a>

</body>
</html>