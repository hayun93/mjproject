<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body, div, ul, li {
	margin: 0;
	padding: 0;
}

body {
	font-size: 12px;
	font-family: "맑은 고딕", arial;
}

.gnb ul {
	list-style: none;
}

.gnb a {
	color: #000;
	text-decoration: none;
}

.gnb>ul {
	text-align: center;
	height: 36px;
	background: #DCB87C;
	padding: 0;
}

.gnb>ul>li {
	display: inline-block;
	width: 102px;
	height: 36px;
	margin-top: 0;
	position: relative;
}

.gnb>ul>li>a {
	display: block;
	width: 100%;
	height: 100%;
	font: bold 13px/30px "맑은고딕", arial;
	text-align: center;
	color: #fff;
	background: #DCB87C;
}

.gnb ul li a:hover {
	color: #eee;
	background: #444;
}

.gnb ul ul {
	display: none;
}

.gnb ul li:hover ul {
	display: block;
}

.gnb li li {
	width: 100px;
	height: 36px;
	background-color: #47a9f4;
	text-align: center;
	float: left;
}

.gnb li li a {
	display: block;
	width: 100%;
	height: 100%;
	font: bold 12px/25px "맑은고딕", arial;
	argin-top: 5px;
}

.gnb li li a:hover {
	color: white;
	background: none;
}
</style>
</head>
<body>
	<div class="gnb">
		<ul>
			<li><a href="/juastore/memberList.mem">회원관리</a></li>
			<li><a href="/juastore/productList.pro">상품관리</a></li>
		</ul>
	</div>

</body>
</html>