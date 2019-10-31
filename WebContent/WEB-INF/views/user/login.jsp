<%@page import="com.board.mini.ListTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
String msg = (String)request.getAttribute("msg");
if(msg != null)
{
	%>
	<script>
	alert('<%=msg%>');  
	</script>
	<%
}
%>
<title>Insert title here</title>
</head>
<body style="text-align: center"> 
	로그인 화면
	<form method="post" action="/user/login">
		<table 
			style="margin-left: auto; margin-right: auto; text-align: center">
			<tr>
				<th>ID</th>
				<td><input type="text" name="utId" id="utId"></td>
			</tr>
			<tr>
				<th>PWD</th>
				<td><input type="password" name="utPwd" id="utPwd"></td>
			</tr>
			<tr>
				<th colspan="2"><button>LOG IN</button></th>
			</tr>
		</table>
	</form>
</body>
</html>