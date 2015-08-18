<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="home_page" locale="${locale}" /></title>
</head>
<body>
	
	<jsp:include page="/jsp/common/fragment/header.jsp"></jsp:include>
	
	<h1 class="blackC">
		<rb:text message="welcome_message" locale="${locale}" />
	</h1>

	<jsp:include page="fragment/authForm.jsp"></jsp:include>

	<c:if test="${authFailed == true}">
		<div id="red">
			<rb:text message="auth_incorrect_message" locale="${locale}" />
		</div>
	</c:if>

	<c:choose>
		<c:when test="${empty issuesList}">
			<h2 class="redC">
				<rb:text message="issues_list_empty" locale="${locale}" />
			</h2>
		</c:when>
		<c:otherwise>
			<jsp:include page="/jsp/common/fragment/startTable.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>