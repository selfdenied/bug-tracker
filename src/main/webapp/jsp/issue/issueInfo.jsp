<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="issue_info" /></title>
</head>
<body>
	<h1 class="maintable">
		<rb:text message="issue_data" />
	</h1>
		
	<br>
	
	<br>
	
	<table id="default">
		<tr align="center">
			<td><b>ID</b></td>
			<td><b><rb:text message="created_date" /></b></td>
			<td><b><rb:text message="created_by" /></b></td>
			<td><b><rb:text message="modified_date" /></b></td>
			<td><b><rb:text message="modified_by" /></b></td>
			<td><b><rb:text message="summary" /></b></td>
			<td><b><rb:text message="description" /></b></td>
			<td><b><rb:text message="status" /></b></td>
			<td><b><rb:text message="resolution" /></b></td>
			<td><b><rb:text message="type" /></b></td>
			<td><b><rb:text message="priority" /></b></td>
			<td><b><rb:text message="project" /></b></td>
			<td><b><rb:text message="build" /></b></td>
			<td><b><rb:text message="assignee" /></b></td>
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
	
	<h2 class="maintable">
		<a href="${base}"><rb:text message="back_message" /></a>
	</h2>
</body>
</html>