<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h2 class="redC">
<rb:text message="issues_list" locale="${locale}" />
</h2>
<div>
<table id="default">
		<tr align="center">
			<td><b>ID</b></td>
			<td><b><rb:text message="priority" locale="${locale}" /></b></td>
			<td><b><rb:text message="assignee" locale="${locale}" /></b></td>
			<td><b><rb:text message="type" locale="${locale}" /></b></td>
			<td><b><rb:text message="status" locale="${locale}" /></b></td>
			<td><b><rb:text message="summary" locale="${locale}" /></b></td>
		</tr>
		<c:forEach var='issue' items='${issuesList}'>
		<tr align="center">
			<td>
			<a href="${base}?action=issueInfo&amp;issueID=${issue.id}&amp;lang=${locale}">
			<c:out value="${issue.id}"></c:out>
			</a>
			</td>
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
			<c:choose>
				<c:when test="${empty issue.assignee}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td><c:out value="${issue.assignee.firstName}"></c:out> 
					<c:out value="${issue.assignee.lastName}"></c:out></td>
				</c:otherwise>
			</c:choose>
			<td><c:out value="${issue.type.featureName}"></c:out></td>
			<td><c:out value="${issue.status.featureName}"></c:out></td>
			<td><c:out value="${issue.summary}"></c:out></td>
		</tr>
		</c:forEach>
</table>
</div>