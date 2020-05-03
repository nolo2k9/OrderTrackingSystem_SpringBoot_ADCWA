<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form:form modelAttribute="nProd">
		<table>
			<tr>
				<td>Product Description:</td>
				<td><form:input path="pDesc"></form:input></td>
				<td><form:errors path="pDesc" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Quantity in Stock:</td>
				<td><form:input path="qtyInStock"></form:input></td>
				<td><form:errors path="qtyInStock" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>

		</table>
		
				<a href="/index.html">Home</a>
				<a href="/showOrders.html">List Orders</a>
				<a href="/showProducts.html">List Products</a>
				
			
	</form:form>

</body>
</html>