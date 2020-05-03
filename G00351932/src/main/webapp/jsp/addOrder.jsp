<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<style type="text/css">
.error {
	color: red;
	font-weight: bold
}
</style>
</head>
<body>
	<form:form modelAttribute="orderList">
		<table>
			<tr>
				<td>Customer:</td>

				<td><form:select path="cust" items="${customers}" /></td>

			</tr>
			<tr>
				<td>Product:</td>
				<td><form:select path="prod" items="${products}" /></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><form:input path="qty" /></td>
				<td><form:errors path="qty" cssClass="error"></form:errors></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>


		</table>

		<a href="/index.html">Home</a>
		<a href="/showCustomers.html">List Customers</a>
		<a href="/showProducts.html">List Products</a>

	</form:form>

</body>
</html>