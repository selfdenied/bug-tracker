<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>

<h2 class="redC">
<rb:text message="assigned_to_message" locale="${locale}" />
</h2>
<div>

<c:choose>
<c:when test="${sessionScope.member.admin}">
	<table>
</c:when>
<c:otherwise>
	<table id="default">
</c:otherwise>
</c:choose>
		<tr align="center">
			<td><b>ID</b></td>
			<td><b><rb:text message="priority" locale="${locale}" /></b></td>
			<td><b><rb:text message="assignee" locale="${locale}" /></b></td>
			<td><b><rb:text message="type" locale="${locale}" /></b></td>
			<td><b><rb:text message="status" locale="${locale}" /></b></td>
			<td><b><rb:text message="summary" locale="${locale}" /></b></td>
		</tr>
		<c:forEach var='issue' items='${sessionScope.assignedIssuesList}'>
		<tr align="center">
			<td><c:out value="${issue.id}"></c:out></td>
			<c:choose> 
				<c:when test='${issue.priority.id == 1}'>
					<td id="critical"><c:out value="${issue.priority.featureName}"></c:out></td>
				</c:when>
				<c:when test='${issue.priority.id == 2}'>
					<td id="major"><c:out value="${issue.priority.featureName}"></c:out></td>
				</c:when>
				<c:when test='${issue.priority.id == 3}'>
					<td id="important"><c:out value="${issue.priority.featureName}"></c:out></td>
				</c:when>
				<c:otherwise>
					<td id="minor"><c:out value="${issue.priority.featureName}"></c:out></td>
				</c:otherwise>
			</c:choose>
			<td><c:out value="${issue.assignee.firstName}"></c:out> <c:out value="${issue.assignee.lastName}"></c:out></td>
			<td><c:out value="${issue.type.featureName}"></c:out></td>
			<td><c:out value="${issue.status.featureName}"></c:out></td>
			<td><c:out value="${issue.summary}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
</div>