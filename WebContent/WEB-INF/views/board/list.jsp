<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/header.jspf"%>
<%
if(session.getAttribute("user")==null){
	response.sendRedirect("/");
}
if(request.getAttribute("msg")!=null){
%>
<script>
	alert('<%=request.getAttribute("msg")%>');
</script>
<%
}
Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table style="margin-left:auto; margin-right:auto; text-align:center">
	<tr>
		<th>번호</th>
		<th width="800">제목</th>
		<th width="300">작성자</th>
		<th>작성일자</th>
	</tr>
	<tr>
	</tr>
	<%
	if(request.getAttribute("btList") == null)
	{
		out.println("<tr><td colspan=\"4\">게시물이 없습니다.</td></tr>");
	}
	else
	{
		List<Map<String,String>> btList = (List<Map<String,String>>)request.getAttribute("btList");
		for(Map<String,String> board : btList)
		{
			%>
			<tr>
				<td><%=board.get("btNum")%></td>
				<td><%=board.get("btTitle")%></td>
				<td><%=board.get("utNum")%></td>
				<td><%=board.get("credat")%></td>
			</tr>
			<%	
		}
	}
%>
<tr>
	<td colspan="4" align="right"><a href="/views/board/insert"><button>글쓰기</button></a><td>   
</tr>
</table>
</body>
</html>