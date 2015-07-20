<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="search_issues" locale="${locale}" /></title>
</head>
<body>

	<c:if test="${!sessionScope.member.admin}">
		<h2 class="usermenu">
			<a href="controller?action=submitIssue">
				<rb:text message="submit_issue" locale="${locale}" />
			</a>
		</h2>
	</c:if>
	
	<rb:header role="${sessionScope.member.admin}" />

	<c:choose>
	
	<c:when test="${not empty listOfIssues}">
		<h2 class="maintable">
			<span style="color:red">
			<rb:text message="issues" locale="${locale}" />
			</span><rb:text message="available_message_3" locale="${locale}" />
		</h2>
		<h3 style="color:black; text-align:center">
			<rb:text message="edit_record_message" locale="${locale}" />
		</h3>
		
		<div>
		<table>
			<tr align="center">
				<td><b>ID</b></td>
				<td><b><rb:text message="created_date" locale="${locale}"/></b></td>
				<td><b><rb:text message="created_by" locale="${locale}"/></b></td>
				<td><b><rb:text message="modified_date" locale="${locale}"/></b></td>
				<td><b><rb:text message="modified_by" locale="${locale}"/></b></td>
				<td><b><rb:text message="summary" locale="${locale}"/></b></td>
				<td><b><rb:text message="status" locale="${locale}"/></b></td>
				<td><b><rb:text message="resolution" locale="${locale}"/></b></td>
				<td><b><rb:text message="type" locale="${locale}"/></b></td>
				<td><b><rb:text message="priority" locale="${locale}"/></b></td>
				<td><b><rb:text message="project" locale="${locale}"/></b></td>
				<td><b><rb:text message="build" locale="${locale}"/></b></td>
				<td><b><rb:text message="assignee" locale="${locale}"/></b></td>
			</tr>
			<c:forEach var='issue' items='${listOfIssues}'>
			<tr align="center">
				<td>
					<c:choose>
					<c:when test="${issue.status.id == 1 || issue.status.id == 2 || 
						issue.status.id == 3 || issue.status.id == 6}">
						<a href="${base}?action=editIssue&amp;issueID=${issue.id}">
							<c:out value="${issue.id}"></c:out>
						</a>
					</c:when>
					<c:otherwise>
						<c:out value="${issue.id}"></c:out>
					</c:otherwise>
					</c:choose>
				</td>
				<td><c:out value="${issue.createdDate}"></c:out></td>
				<td><c:out value="${issue.createdBy.firstName}"></c:out> 
				<c:out value="${issue.createdBy.lastName}"></c:out></td>
				<td><c:out value="${issue.modifiedDate}"></c:out></td>
					<c:choose>
					<c:when test="${empty issue.modifiedBy}">
						<td></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="${issue.modifiedBy.firstName}"></c:out> 
						<c:out value="${issue.modifiedBy.lastName}"></c:out></td>
					</c:otherwise>
					</c:choose>
				<td><c:out value="${issue.summary}"></c:out></td>
				<td><c:out value="${issue.status.featureName}"></c:out></td>
					<c:choose>
					<c:when test="${empty issue.resolution}">
						<td></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="${issue.resolution.featureName}"></c:out></td>
					</c:otherwise>
					</c:choose>
				<td><c:out value="${issue.type.featureName}"></c:out></td>
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
				<td><c:out value="${issue.project.projectName}"></c:out></td>
				<td><c:out value="${issue.build.buildName}"></c:out></td>
					<c:choose>
					<c:when test="${empty issue.assignee}">
						<td></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="${issue.assignee.firstName}"></c:out> 
						<c:out value="${issue.assignee.lastName}"></c:out></td>
					</c:otherwise>
					</c:choose>
					<c:if test="${issue.status.id == 3 || issue.status.id == 4}">
					<td>
						<a href="${base}?action=closeIssue&amp;issueID=${issue.id}">
							<rb:text message="close" locale="${locale}"/>
						</a>
					</td>
					</c:if>
					<c:if test="${issue.status.id == 5}">
					<td>
						<a href="${base}?action=reopenIssue&amp;issueID=${issue.id}">
							<rb:text message="reopen" locale="${locale}"/>
						</a>
					</td>
					</c:if>				
			</tr>
			</c:forEach>
		</table>
		</div>
			
	</c:when>

	<c:otherwise>
		<h2 style="color:red; text-align:center">
			<rb:text message="issues_list_empty" locale="${locale}" />
		</h2>	
	</c:otherwise>
	
	</c:choose>
	
	<br>
	
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>