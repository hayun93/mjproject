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
}

th, td {
	border: 1px solid black;
}

.total {
	margin: auto;
	width: 80%;
}
</style>
</head>
<body>
	<div class="total">
		<h1>주문한 내역</h1>
		<table>
			<thead>
				<tr>
					<td>상품명</td>
					<td>가격</td>
					<td>수량</td>
					<td>총 금액</td>
					<td></td>
				</tr>
			</thead>
			<c:forEach var="list" items="${itemList }">
				<tr>
					<td><a href="#">${list.product_name }</a></td>
					<td>${list.product_price }</td>
					<td>${list.amount }</td>
					<td>${list.pay }</td>
					<c:choose>
						<c:when test="${order.order_state eq '배송완료' }">
							<td><a href="#">후기</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="#">문의</a></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<h2>배송지 정보</h2>
		<table>
			<tr>
				<th>받는 분</th>
				<td>${order.receive_name }</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${order.phone }</td>
			</tr>
			<tr>
				<th rowspan="2">주소</th>
				<td>${order.zip_code }</td>
			</tr>
			<tr>
				<td>${order.address }</td>
			</tr>
		</table>
		<br>
		<c:if test="${order.order_state eq '주문완료' || order.order_state eq '결제완료'}">
			<a href="/juastore/cancelMyOrder.ord?order_num=${order.order_num }">주문 취소</a>
		</c:if>
		<br> 
		<a href="/juastore/myOrderList.ord">주문목록으로</a>
	</div>
	<jsp:include page="/side.jsp" />
	<jsp:include page="/bottom.jsp" />
</body>
</html>