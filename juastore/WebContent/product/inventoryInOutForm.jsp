<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function backDetail(){
		location.href="/juastore/productDetail.pro?product_code=${param.product_code}";
	}
</script>
<body>
	<form action="/juastore/inventoryInOut.pro">
	<input type="hidden" id="product_code" name="product_code" value="${param.product_code }" >
		<table>
			<thead>
				<tr>
					<td colspan="2">${param.product_code } &nbsp;입/출고</td>
				</tr>
			</thead>
			<tr>
				<th>입/출고 선택</th>
				<td>
					<select id="state" name="state">
						<option value="in">입고</option>
						<option value="out">출고</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>수량</th>
				<td><input type="text" name="amount" id="amount" size="3" value="10"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="button" value="돌아가기" onclick="backDetail()">
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>