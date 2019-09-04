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
	function back(){
		history.back();
	}
</script>
<body>
	<form action="/juastore/productMod.pro" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th colspan="6">${product.product_name }상품 수정</th>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" id="product_name" name="product_name" value="${product.product_name }" required></td>
				<th>카테고리</th>
				<td>
					<select id="type" name="type">
						<option value="op" ${type eq 'op' ? 'selected':'' }>op</option>
						<option value="to" ${type eq 'to' ? 'selected':'' }>to</option>
						<option value="bo" ${type eq 'bo' ? 'selected':'' }>bo</option>
						<option value="ot" ${type eq 'ot' ? 'selected':'' }>ot</option>
						<option value="ac" ${type eq 'ac' ? 'selected':'' }>ac</option>
					</select>
				</td>
				<th>상품번호</th>
				<td><input type="text" id="product_num" name="product_num" size="5" value="${num }" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" id="product_price" name="product_price" size="7" value="${product.product_price }" required>원</td>
				<th>사이즈</th>
				<td><input type="text" id="size" name="size" size="5" value="${size }" required></td>
				<th>색상</th>
				<td><input type="text" id="color" name="color" size="5" value="${color }" required></td>
			</tr>
			<tr>
				<th>사진변경</th>
				<td colspan="5"><input type="file" name="product_image" id="product_image"></td>
			</tr>
			<tr>
				<th>기타사항</th>
				<td colspan="5"><textarea id="etc" name="etc" rows="10" cols="60">${product.etc }</textarea>
			</tr>
		</table>
		<input type="submit" value="수정">
		<input type="button" onclick="back()" value="돌아가기">
	</form>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>