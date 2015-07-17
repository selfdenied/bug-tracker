<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<title><rb:text message="features_list" locale="${locale}" /></title>
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

	<c:choose>
	
	<c:when test="${not empty listOfFeatures}">
		<h2 class="maintable">
			<c:choose>
			
			<c:when test="${feature == 'status'}">
			<span style="color:red"><rb:text message="admin_statuses" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'resolution'}">
			<span style="color:red"><rb:text message="admin_resolutions" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'type'}">
			<span style="color:red"><rb:text message="admin_types" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'priority'}">
			<span style="color:red"><rb:text message="admin_priorities" locale="${locale}" /></span>
			</c:when>
			
			</c:choose>
			<rb:text message="available_message" locale="${locale}" />
		</h2>
		<h3 style="color:black; text-align:center">
			<rb:text message="edit_record_message" locale="${locale}" />
		</h3>
		<div>
		<table>
			<tr align="center">
				<td><b>ID</b></td>
				<td><b><rb:text message="feature_name" locale="${locale}" /></b></td>
			</tr>
			<c:forEach var="feat" items="${listOfFeatures}">
			<tr align="center">
				<td>
				<a href="${base}?action=editFeature&amp;featureID=${feat.id}&amp;feature=${feature}">
				<c:out value="${feat.id}"></c:out>
				</a>
				</td>
				<td><c:out value="${feat.featureName}"></c:out></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</c:when>
	
	<c:otherwise>
		<h2 style="color:blue">
			<rb:text message="features_list_empty" locale="${locale}" />
		</h2>
	</c:otherwise>
	
	</c:choose>

	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>