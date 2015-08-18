<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="members_list" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<h2 class="blackC">
		<span id="red">
		<rb:text message="members" locale="${locale}" />
		</span><rb:text message="registered_message" locale="${locale}" />
	</h2>
	<h3 class="blueC">
		<rb:text message="edit_record_message" locale="${locale}" />
	</h3>
	<div>
	<table>
		<tr align="center">
			<td><b>ID</b></td>
			<td><b><rb:text message="name" locale="${locale}" /> </b></td>
			<td><b><rb:text message="last_name" locale="${locale}" /> </b></td>
			<td><b><rb:text message="e-mail_2" locale="${locale}" /></b></td>
			<td><b><rb:text message="access_mode" locale="${locale}" /></b></td>
			<td><b><rb:text message="password_2" locale="${locale}" /></b></td>
		</tr>
		<c:forEach var='member' items='${listOfMembers}'>
		<tr align="center">
			<td>
			<a href="${base}?action=editMember&amp;userID=${member.id}">
			<c:out value="${member.id}"></c:out>
			</a>
			</td>
			<td><c:out value="${member.firstName}"></c:out></td>
			<td><c:out value="${member.lastName}"></c:out></td>
			<td><c:out value="${member.login}"></c:out></td>
			<td>
				<c:choose>
					<c:when test="${member.admin}">
						<span id="orange">
						<rb:text message="admin_2" locale="${locale}" />
						</span>
					</c:when>
					<c:otherwise>
						<rb:text message="user_2" locale="${locale}" />
					</c:otherwise>
				</c:choose>
			</td>
			<td>	
				<form action="controller" method="post">
				<input type="HIDDEN" name="action" value="changeUserPassword">
				<input type="HIDDEN" name="userID" value="${member.id}">
				<input type="submit" value = "<rb:text message='change' locale='${locale}' />">		
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>


</body>
</html>