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
table, tr, th, td{
	border: 1px solid black;
}
</style>
</head>
<body>
	<h1>상품 정보</h1>

	<table>
		<tr>
			<td rowspan="4"> <img alt="상품사진" src="/juastore/productImage/${product.image }"> </td>
			<th>상품코드</th>
			<td>${product.product_code }</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${product.product_name }</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${product.product_price }</td>
		</tr>
		<tr>
			<th>재고</th>
			<c:choose>
				<c:when test="${empty product.inventory_amount }">
					<td>0</td>
				</c:when>
				<c:otherwise>
					<td>${product.inventory_amount }</td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	<a href="/juastore/inventoryInOutForm.pro?product_code=${product.product_code }">입/출고</a>
	<a href="/juastore/productModForm.pro?product_code=${product.product_code }">상품정보 수정</a>
	<a href="">상품 삭제</a>
	<br>
	<br>

	<h1>기타사항</h1>
	<p>
		<c:choose>
			<c:when test="${empty product.etc }">
				기타사항이 없습니다.
			</c:when>
			<c:otherwise>
				${product.etc }
			</c:otherwise>
		</c:choose>
	</p>
	<br>

	<h1>입/출고내역</h1>
	<form action="/juastore/productDetail.pro">
		<input type="hidden" id="product_code" name="product_code" value="${product.product_code }">
		<select id="inventoryYear" name="inventoryYear">
			<option value="0">== 년 ==</option>
			<option value="2019">2019년</option>
			<option value="2018">2018년</option>
			<option value="2017">2017년</option>
		</select>
		<select id="inventoryMonth" name="inventoryMonth">
			<option value="0">== 월 ==</option>
			<c:forEach var="month" begin="1" end="12">
				<option value="${month }">${month }월</option>
			</c:forEach>
		</select>
		<input type="submit" value="검색">
	</form>
	<table>
		<thead>
			<tr>
				<td>번호</td>
				<td>날짜</td>
				<td>수량</td>
				<td>상태</td>
			</tr>
		</thead>
		<tr>
			<c:choose>
				<c:when test="${empty inventoryList }">
					<td colspan="4">내역이 없습니다.</td>
				</c:when>
				<c:otherwise>
					<c:forEach var="list" items="${inventoryList }">
						<tr>
							<td>${list.inventory_num }</td>
							<td>${list.inventory_date }</td>
							<td>${list.inventory_amount }</td>
							<td>${list.inventory_state }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="4">
							<c:choose>
								<c:when test="${pageInfo.page<=1 }">
										<&nbsp;
									</c:when>
								<c:otherwise>
									<a href="/juastore/myOrderList.ord?page=${pageInfo.page-1 }"><</a>&nbsp;
								</c:otherwise>
							</c:choose>
							<c:forEach var="a" begin="${pageInfo.startPage }"
								end="${pageInfo.endPage }">
								<c:choose>
									<c:when test="${a eq pageInfo.page }">
										${a }&nbsp;
									</c:when>
									<c:otherwise>
										<a href="/juastore/myOrderList.ord?page=${a }">${a }</a>&nbsp;
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${pageInfo.page >= pageInfo.maxPage }">
									>
								</c:when>
								<c:otherwise>
									<a href="/juastore/myOrderList.ord?page=${pageInfo.page+1 }">></a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	<a href="/juastore/productList.pro">목록으로 돌아가기</a>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>