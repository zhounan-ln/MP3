<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频</title>
<script type="text/javascript" src="js/header.js"></script>
<link href = "css/index.css" rel = "stylesheet" type = "text/css" >
<link href = "css/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>	
<!-- top -->
	<%@ include file ="top.jsp" %>
	
	<div id = "wrap_show">
		<div id ="top_content">
			<div id="content">
				<p id="whereami"></p>
				<h1>智慧云</h1>
				<p>
					<video src="video/dy1.mp4" controls ="controls" poster ="poster" width="15%"></video>
				</p>
			</div>
		</div>
	</div>

</body>
</html>