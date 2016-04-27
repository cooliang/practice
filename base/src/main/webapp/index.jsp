<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>test</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/upload" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" name="submit" value="submit">
	</form>
	<form action="${pageContext.request.contextPath}/loadYunpay" method="post">
		<input type="text" name="startDate" value="2016-04-18">
		<input type="text" name="endDate" value="2016-04-25">
		<input type="submit" name="loadYunpay" value="loadYunpay">
	</form>
	<form action="${pageContext.request.contextPath}/check" method="post">
		<input type="text" name="startDate" value="2016-04-18">
		<input type="text" name="endDate" value="2016-04-25">
		<input type="submit" name="check" value="check">
	</form>
</body>
</html>
