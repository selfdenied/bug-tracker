<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="member_page" locale="${locale}" /></title>
</head>
<body>

	<jsp:include page="/jsp/common/fragment/adminMenu.jsp"></jsp:include>
	
	<jsp:include page="/jsp/member/fragment/greeting.jsp"></jsp:include>

	<jsp:include page="/jsp/member/fragment/accessMode.jsp"></jsp:include>
	
	<br>
	<br>
		
	<jsp:include page="/jsp/common/fragment/smallMenu.jsp"></jsp:include>
		
	<br>
	<br>
		
	<c:choose>
		<c:when test="${empty sessionScope.assignedIssuesList}">
			<h2 style="color:red; text-align:center">
			<rb:text message="assigned_issues_empty" locale="${locale}" />
			</h2>
		</c:when>
		<c:otherwise>
			<jsp:include page="/jsp/issue/fragment/assignedIssueTable.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
		
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>