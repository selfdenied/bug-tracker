<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="home_page"/></title>
</head>
<body>
	
	<jsp:include page="fragment/header.jsp"></jsp:include>
	
	<h1 style="text-align: center; color: blue">
		<rb:text message="welcome_message" />
	</h1>

	<jsp:include page="fragment/authForm.jsp"></jsp:include>

	<br>

	<c:if test="${authFailed == true}">
		<div style="color: red">
			<rb:text message="auth_incorrect_message" />
		</div>
	</c:if>

	<c:choose>
		<c:when test="${empty issuesList}">
			<h3 style="color: blue">
				<rb:text message="issues_list_empty" />
			</h3>
		</c:when>
		<c:otherwise>
			<jsp:include page="fragment/startTable.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<jsp:include page="fragment/footer.jsp"></jsp:include>

</body>
</html>