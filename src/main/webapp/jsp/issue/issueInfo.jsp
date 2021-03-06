<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="issue_info" locale="${locale}" /></title>
</head>
<body>
	<h1 class="blackC">
		<rb:text message="issue_data" locale="${locale}" />
	</h1>
		
	<br>
	<br>
	
	<table id="default">
		<tr align="center">
			<td><b>ID</b></td>
			<td><b><rb:text message="created_date" locale="${locale}"/></b></td>
			<td><b><rb:text message="created_by" locale="${locale}"/></b></td>
			<td><b><rb:text message="modified_date" locale="${locale}"/></b></td>
			<td><b><rb:text message="modified_by" locale="${locale}"/></b></td>
			<td><b><rb:text message="summary" locale="${locale}"/></b></td>
			<td><b><rb:text message="description" locale="${locale}"/></b></td>
			<td><b><rb:text message="status" locale="${locale}"/></b></td>
			<td><b><rb:text message="resolution" locale="${locale}"/></b></td>
			<td><b><rb:text message="type" locale="${locale}"/></b></td>
			<td><b><rb:text message="priority" locale="${locale}"/></b></td>
			<td><b><rb:text message="project" locale="${locale}"/></b></td>
			<td><b><rb:text message="build" locale="${locale}"/></b></td>
			<td><b><rb:text message="assignee" locale="${locale}"/></b></td>
		</tr>
		<tr align="center">
			<td><c:out value="${issueToView.id}"></c:out></td>
			<td><c:out value="${issueToView.createdDate}"></c:out></td>
			<td><c:out value="${issueToView.createdBy.firstName}"></c:out> 
			<c:out value="${issueToView.createdBy.lastName}"></c:out></td>
			<td><c:out value="${issueToView.modifiedDate}"></c:out></td>
				<c:choose>
				<c:when test="${empty issueToView.modifiedBy}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td><c:out value="${issueToView.modifiedBy.firstName}"></c:out> 
					<c:out value="${issueToView.modifiedBy.lastName}"></c:out></td>
				</c:otherwise>
				</c:choose>
			<td><c:out value="${issueToView.summary}"></c:out></td>
			<td><c:out value="${issueToView.description}"></c:out></td>
			<td><c:out value="${issueToView.status.featureName}"></c:out></td>
				<c:choose>
				<c:when test="${empty issueToView.resolution}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td><c:out value="${issueToView.resolution.featureName}"></c:out></td>
				</c:otherwise>
				</c:choose>
			<td><c:out value="${issueToView.type.featureName}"></c:out></td>
				<c:choose> 
				<c:when test='${issueToView.priority.id == 1}'>
					<td id="critical"><c:out value="${issueToView.priority.featureName}"></c:out></td>
				</c:when>
				<c:when test='${issueToView.priority.id == 2}'>
					<td id="major"><c:out value="${issueToView.priority.featureName}"></c:out></td>
				</c:when>
				<c:when test='${issueToView.priority.id == 3}'>
					<td id="important"><c:out value="${issueToView.priority.featureName}"></c:out></td>
				</c:when>
				<c:otherwise>
					<td id="minor"><c:out value="${issueToView.priority.featureName}"></c:out></td>
				</c:otherwise>
				</c:choose>
			<td><c:out value="${issueToView.project.projectName}"></c:out></td>
			<td><c:out value="${issueToView.build.buildName}"></c:out></td>
				<c:choose>
				<c:when test="${empty issueToView.assignee}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td><c:out value="${issueToView.assignee.firstName}"></c:out> 
					<c:out value="${issueToView.assignee.lastName}"></c:out></td>
				</c:otherwise>
				</c:choose>
		</tr>
	</table>
	
	<br>
	<br>
	
	<h2 class="blackC">
		<form action="controller" method="post">
			<input type="HIDDEN" name="lang" value="${locale}">
			<input type="submit" value = "<rb:text message='back_message' locale='${locale}' />">		
		</form>
	</h2>
	
	<br>
	
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>
	
</body>
</html>