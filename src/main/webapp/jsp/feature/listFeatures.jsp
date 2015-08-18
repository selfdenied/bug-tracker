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

	<rb:header role="${sessionScope.member.admin}" />

	<c:choose>
	
	<c:when test="${not empty listOfFeatures}">
		<h2 class="blackC">
			<c:choose>
			
			<c:when test="${feature == 'status'}">
			<span id="red"><rb:text message="admin_statuses" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'resolution'}">
			<span id="red"><rb:text message="admin_resolutions" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'type'}">
			<span id="red"><rb:text message="admin_types" locale="${locale}" /></span>
			</c:when>
			
			<c:when test="${feature == 'priority'}">
			<span id="red"><rb:text message="admin_priorities" locale="${locale}" /></span>
			</c:when>
			
			</c:choose>
			<rb:text message="available_message" locale="${locale}" />
		</h2>
		
		<c:choose>
		<c:when test="${feature != 'status'}">
			<h3 class="blueC">
				<rb:text message="edit_record_message" locale="${locale}" />
			</h3>
		</c:when>
		<c:otherwise>
			<h3>
			<br>
			</h3>
		</c:otherwise>
		</c:choose>
		
		<div>
		<table>
			<tr align="center">
				<td><b>ID</b></td>
				<td><b><rb:text message="name_2" locale="${locale}" /></b></td>
			</tr>
			<c:forEach var="feat" items="${listOfFeatures}">
			<tr align="center">
				<td>
				<c:choose>
				<c:when test="${feature == 'status'}">
					<c:out value="${feat.id}"></c:out>
				</c:when>
				<c:otherwise>
				<a href="${base}?action=editFeature&amp;featureID=${feat.id}&amp;feature=${feature}">
					<c:out value="${feat.id}"></c:out>
				</a>
				</c:otherwise>
				</c:choose>
				</td>
				<td><c:out value="${feat.featureName}"></c:out></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</c:when>
	
	<c:otherwise>
		<h2 id="red">
			<rb:text message="features_list_empty" locale="${locale}" />
		</h2>
	</c:otherwise>
	
	</c:choose>

	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>