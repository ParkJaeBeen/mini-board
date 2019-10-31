<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mini Board</title>
</head>
<body>
처음만드는 웹 게시판<br>
<%
if(session.getAttribute("user") != null)
{
	Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
	out.print(user.get("utName") + "님 반갑습니다.");
	out.print("<a href=\"/views/user/logout\">logout</a>");
	out.print("<a href=\"/views/board/list\">게시판</a>");
}
else
{
%>
<a href="/views/user/login">login</a><br>
<a href="/views/user/signup">회원가입</a>
<%
}
%>
</body>
</html>