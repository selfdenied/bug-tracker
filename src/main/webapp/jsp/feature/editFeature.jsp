<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="edit_feature" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled and feature != 'status'}">
	<div class="form5">
		<c:choose>
			
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
		<rb:text message="edit_existing_record" locale="${locale}" />
		<span id="black">
		(<rb:text message="id" locale="${locale}" /> <c:out value="${featureID}"></c:out>)
		</span>
	</div>
	
	<br>
	
	<div class="blackC">
		<span id="black17"><rb:text message="enter_feature_name" locale="${locale}" /></span>
		<br>
		<span id="blue16">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	<br>

	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="addFeature">
			<input type="HIDDEN" name="feature" value="${feature}">
			<input type="HIDDEN" name="featureID" value="${featureID}">
			<rb:text message="feature_name" locale="${locale}" />
			<br>
			<span id="tinyRed"><rb:text message="max_50" locale="${locale}" /></span>
			<br>
			<input type="text" name="featureName" maxlength="50" 
			pattern="[A-Za-zА-Яа-яЁё0-9 \\.\\-]{1,50}" required="required" >
			<br>
			<br>
			<input type="submit" value="<rb:text message='update_button' locale='${locale}' />">		
		</form>
	</div>	
	</c:if>
	
	<br>
	
	<c:if test="${featureNameUpdated}">
		<div class="form4">
			<rb:text message="data_updated" locale="${locale}" />
		</div>
	</c:if>
	
	<c:if test="${featureNameExists}">
		<div class="form3">
			<rb:text message="feature_name_exists" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${featureNameChangeError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
	
	<br>
	
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>