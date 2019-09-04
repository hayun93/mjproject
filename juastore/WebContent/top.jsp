<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
	font-size: 12px;
	font-family: "맑은 고딕", arial;
	}
	#header{
	width:100%;
	height:250px;
	text-align:center;
	}
	#menu{
	text-align:center;
	margin:0px;
	padding-bottom:10px;
	}
	#menu a{
	text-decoration:none;
	color:#D9B577;
	}
	#nev{
	height:36px;
	background-color:#D9B577;
	text-align:center;
	}
	#nev ul, li{
	list-style:none;
	display:inline-block;
	padding-right:30px;
	}
	#nev a{
	text-decoration:none;
	color:white;
	}
</style>
</head>
<body>
<section id="header">
	<a href="main.jsp"><img src="images/logo.jpg" height="250" alt="메인으로" ></a>
</section>
<section id="menu">
<%String id=(String)session.getAttribute("id");

	if(id ==null){%>
	<a href="loginForm.mem">로그인</a>&nbsp;&nbsp;
	<a href="joinForm.mem">회원가입</a>&nbsp;&nbsp;
<%} else {%>
	<a href="logout.mem">로그아웃</a>&nbsp;&nbsp;
	<a href="myPage.jsp">마이페이지</a>&nbsp;&nbsp;
<%} %>
	<a href="#">주문조회</a>&nbsp;&nbsp;
	<a href="#">리뷰</a>&nbsp;&nbsp;
	<a href="#">QnA</a>&nbsp;&nbsp;
	<a href="#">고객센터</a>
</section>

<%if(id!=null && id.equals("admin")) {%>
<jsp:include page="/top_menu.jsp"/>
<%}else {%>
<section id="nev">
	<ul>
		<li><a href="#">원피스</a></li>
		<li><a href="#">상의</a></li>
		<li><a href="#">하의</a></li>
		<li><a href="#">아우터</a></li>
		<li><a href="#">패션 잡화</a></li>
	</ul>
</section>
<%} %>
</body>
</html>