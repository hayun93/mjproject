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
table {
	margin: auto;
	text-align: center;
}

td {
	border: 1px solid black;
}

.div {
	margin: auto;
	width: 80%;
}

.pageList {
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
	<form action="/juastore/productList.pro">
		<select id="category" name="category">
			<option value="">== 카테고리 ==</option>
			<option value="op">원피스</option>
			<option value="to">상의</option>
			<option value="bo">하의</option>
			<option value="ot">아우터</option>
			<option value="ac">패션잡화</option>
		</select> <input type="submit" value="검색">
	</form>
	<table>
		<thead>
			<tr>
				<td>상품코드</td>
				<td>상품명</td>
				<td>가격</td>
				<td>재고</td>
				<td>상세</td>
			</tr>
		</thead>

		<c:choose>
			<c:when test="${empty productList }">
				<tr>
					<td colspan="5">상품이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="list" items="${productList }">
					<tr>
						<td>${list.product_code }</td>
						<td>${list.product_name }</td>
						<td>${list.product_price }</td>
						<td>${list.inventory_amount }</td>
						<td><a href="/juastore/productDetail.pro?product_code=${list.product_code }">상세</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	
	<a href="/juastore/productAddForm.pro">상품등록</a>
	
	<section class="pageList">
		<c:choose>
			<c:when test="${pageInfo.page<=1 }">
					[이전]
				</c:when>
			<c:otherwise>
				<a href="/juastore/myOrderList.ord?page=${pageInfo.page-1 }">[이전]</a>
			</c:otherwise>
		</c:choose>
		&nbsp;
		<c:forEach var="a" begin="${pageInfo.startPage }"
			end="${pageInfo.endPage }">
			<c:choose>
				<c:when test="${a eq pageInfo.page }">
					${a }
				</c:when>
				<c:otherwise>
					<a href="/juastore/myOrderList.ord?page=${a }">${a }</a>
				</c:otherwise>
			</c:choose>
			&nbsp;
		</c:forEach>
		<c:choose>
			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="/juastore/myOrderList.ord?page=${pageInfo.page+1 }">[다음]</a>
			</c:otherwise>
		</c:choose>
	</section>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>