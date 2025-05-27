<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - listOneProduct.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品編號</th>
			<th>商品類別</th>
			<th>商品名稱</th>
			<th>價格</th>
			<th>描述</th>
			<th>庫存量</th>
			<th>狀態</th>
		</tr>
		<tr>
			<td><%=productVO.getProductId()%></td>

			<jsp:useBean id="productCategorySvc" scope="page"
				class="com.productcategory.model.ProductCategoryService" />

			<td>${productVO.productCategoryId}- <c:forEach
					var="productCategoryVO" items="${productCategorySvc.all}">
					<c:if
						test="${productCategoryVO.productCategoryId == productVO.productCategoryId}">
      ${productCategoryVO.productCategoryName}
    </c:if>
				</c:forEach>
			</td>

			<td><%=productVO.getProductName()%></td>
			<td><%=productVO.getProductPrice()%></td>
			<td><%=productVO.getProductDescription()%></td>
			<td><%=productVO.getProductQuantity()%></td>
			<td><%=productVO.getProductStatus()%></td>
		</tr>
	</table>

</body>
</html>