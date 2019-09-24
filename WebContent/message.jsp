<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>留言板</title>
<link href = "css/index.css" rel = "stylesheet" type = "text/css" >
<link href = "css/message.css" rel = "stylesheet" type = "text/css">
<script type="text/javascript" src = "js/header.js"></script>
</head>
<body>
	<%@include file = "top.jsp" %>
	<div id = "message">
		<form action="message.do" method = "post">
			<div>
				<textarea rows="8" cols="168" name = "comment" id = "message_ly">
				</textarea>
				<br>昵称<br>
				<%
				if(user!=null){
				%>
				<input type="text" name= "username"  value = "<%=user.getUsername()%>">
				<%}else{ %>
				<input type="text" name ="username" value = "游客">
				<%} %>
				<input type = "submit" name = "button" id = "message_ly_sub" value = "留言">
			</div>
		</form>
		<div id = "message_date">
			<h1>最近留言</h1>
		</div>
		<div id = "message_content">
			<%
				List<String> list =(List<String>)session.getAttribute("messages");
				if(list!=null){
					for(int i =list.size()-1; i>=0 ; i--){
						String string = list.get(i);
			%>
			<p><%=string %></p>
			<%
					}
				}
			%>
		</div>
	</div>

</body>
</html>