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

	<c:if test="${sessionScope.member.admin}">
		<jsp:include page="/jsp/common/fragment/adminMenu.jsp"></jsp:include>
	</c:if>
	
	<jsp:include page="/jsp/member/fragment/greeting.jsp"></jsp:include>

	<jsp:include page="/jsp/member/fragment/accessMode.jsp"></jsp:include>
	
	<br>
	<br>
		
	<jsp:include page="/jsp/common/fragment/smallMenu.jsp"></jsp:include>
		
	<br>
	<br>
	
	<h2 class="maintable">
		<span style="color:red">
		<rb:text message="members" locale="${locale}" />
		</span><rb:text message="registered_message" locale="${locale}" />
	</h2>
	<h3 style="color:black; text-align:center">
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
						<span style="color:#FFA500">
						<b><rb:text message="admin_2" locale="${locale}" /></b>
						</span>
					</c:when>
					<c:otherwise>
						<b><rb:text message="user_2" locale="${locale}" /></b>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
			<a href="${base}?action=changeUserPassword&amp;userID=${member.id}">
			<rb:text message="change" locale="${locale}" />
			</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>


</body>
</html>