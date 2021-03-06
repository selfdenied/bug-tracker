<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="projects_list" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:choose>
	
	<c:when test="${not empty listOfProjects}">
		<h2 class="blackC">
			<span id="red">
			<rb:text message="projects" locale="${locale}" />
			</span><rb:text message="available_message_3" locale="${locale}" />
		</h2>
		<h3 class="blueC">
			<rb:text message="edit_record_message" locale="${locale}" />
		</h3>
		<div>
		<table>
			<tr align="center">
				<td><b>ID</b></td>
				<td><b><rb:text message="name_2" locale="${locale}" /></b></td>
				<td><b><rb:text message="description" locale="${locale}" /></b></td>
				<td><b><rb:text message="manager" locale="${locale}" /></b></td>
				<td><b><rb:text message="build" locale="${locale}" /></b></td>
			</tr>
			<c:forEach var='project' items='${listOfProjects}'>
			<tr align="center">
				<td>
				<a href="${base}?action=editProject&amp;projectID=${project.id}">
				<c:out value="${project.id}"></c:out>
				</a>
				</td>
				<td><c:out value="${project.projectName}"></c:out></td>
				<td><c:out value="${project.projectDescription}"></c:out></td>
				<td>
				<c:out value="${project.manager.firstName}"></c:out> 
				<c:out value="${project.manager.lastName}"></c:out>
				</td>
				<td>
					<form action="controller" method="post">
					<input type="HIDDEN" name="action" value="addBuild">
					<input type="HIDDEN" name="projectID" value="${project.id}">
					<input type="submit" value = "<rb:text message='add_new_message' locale='${locale}' />">		
					</form>
				</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</c:when>
	
	<c:otherwise>
		<h2 id="red">
			<rb:text message="projects_list_empty" locale="${locale}" />
		</h2>
	</c:otherwise>
	
	</c:choose>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>