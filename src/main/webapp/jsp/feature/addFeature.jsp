<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="add_feature" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<c:choose>
			
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
		<rb:text message="add_new_record" locale="${locale}" />	
	</div>
	
	<br>
	
	<div class="form">
		<span><rb:text message="enter_feature_name" locale="${locale}" /></span>
		<br>
		<span style="color:blue; font-size:16px">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	<br>

	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="addFeature">
			<input type="HIDDEN" name="feature" value="${feature}">
			<rb:text message="feature_name" locale="${locale}" />
			<br>
			<input type="text" name="featureName" required="required" pattern="[A-Za-zА-Яа-яЁё| ,]+">
			<br>
			<br>
			<input type="submit" value="<rb:text message='add_button' locale='${locale}' />">		
		</form>
	</div>	
	</c:if>
	
	<br>
	
	<c:if test="${newFeatureAdded}">
	<div class="form4">
		<rb:text message="data_added" locale="${locale}" />
	</div>
	</c:if>
		
	<c:if test="${requestScope.featureNameExists}">
	<div class="form3">
		<rb:text message="feature_name_exists" locale="${locale}" />
	</div>
	</c:if>
		
	<c:if test="${requestScope.featureAddError}">
	<div class="form3">
		<rb:text message="error_message_2" locale="${locale}" />
	</div>
	</c:if>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>