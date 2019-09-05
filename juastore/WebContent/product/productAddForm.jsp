<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td{
	border: 1px solid black;
}
</style>
</head>
<script>
	function backList(){
		history.back();
	}
</script>
<body>
	<form action="/juastore/productAdd.pro" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th colspan="6">상품추가</th>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" id="product_name" name="product_name" required></td>
				<th>카테고리</th>
				<td>
					<select id="type" name="type">
						<option value="op">op</option>
						<option value="to">to</option>
						<option value="bo">bo</option>
						<option value="ot">ot</option>
						<option value="ac">ac</option>
					</select>
				</td>
				<th>상품번호</th>
				<td><input type="text" id="product_num" name="product_num" size="5" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" id="product_price" name="product_price" size="7" required>원</td>
				<th>사이즈</th>
				<td><input type="text" id="size" name="size" size="5" required></td>
				<th>색상</th>
				<td><input type="text" id="color" name="color" size="5" required></td>
			</tr>
			<tr>
				<th>사진첨부</th>
				<td colspan="2"><input type="file" name="product_image" id="product_image" required></td>
				<th>최초재고</th>
				<td colspan="2"><input type="text" name="inventory" id="inventory" value="10" size="5" required></td>
			</tr>
			<tr>
				<th>기타사항</th>
				<td colspan="5"><textarea id="etc" name="etc" rows="10" cols="60"></textarea>
			</tr>
		</table>
		<input type="submit" value="등록">
		<input type="button" onclick="backList()" value="상품목록">
	</form>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>