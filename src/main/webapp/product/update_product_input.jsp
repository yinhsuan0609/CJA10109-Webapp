<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - update_product_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料修改 - update_product_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="product.do" name="form1">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="productName"
					value="<%=(productVO == null) ? "商品1" : productVO.getProductName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="productPrice"
					value="<%=(productVO == null) ? "10000" : productVO.getProductPrice()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><input type="TEXT" name="productDescription"
					value="<%=(productVO == null) ? "描述" : productVO.getProductDescription()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>庫存量:</td>
				<td><input type="TEXT" name="productQuantity"
					value="<%=(productVO == null) ? "100" : productVO.getProductQuantity()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>商品狀態:<font color="red"><b>*</b></font></td>
				<td><input type="radio" name="productStatus" value="1"
					<%=(productVO != null && productVO.getProductStatus() == 1) ? "checked" : ""%>>
					上架 <input type="radio" name="productStatus" value="0"
					<%=(productVO != null && productVO.getProductStatus() == 0) ? "checked" : ""%>>
					未上架</td>
			</tr>


			<jsp:useBean id="productCategorySvc" scope="page"
				class="com.productcategory.model.ProductCategoryService" />
			<tr>
				<td>商品種類:<font color=red><b>*</b></font></td>
				<td><select size="1" name="productCategoryId">
						<c:forEach var="productCategoryVO"
							items="${productCategorySvc.all}">
							<option value="${productCategoryVO.productCategoryId}"
								${(productVO.productCategoryId==productCategoryVO.productCategoryId)? 'selected':'' }>${productCategoryVO.productCategoryName}
							</option>

						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="productId" value="<%=productVO.getProductId()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</html>