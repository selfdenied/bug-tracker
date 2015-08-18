<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="add_build" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<span id="red"><rb:text message="builds" locale="${locale}" /></span>
		<rb:text message="add_new_record" locale="${locale}" />
		<span id="black">
		(<rb:text message="project_id" locale="${locale}" /> <c:out value="${projectID}"></c:out>)
		</span>	
	</div>
	
	<br>
	
	<div class="blackC">
		<span id="black17"><rb:text message="enter_build_name" locale="${locale}" /></span>
		<br>
		<span id="blue16">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	<br>

	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="addBuild">
			<input type="HIDDEN" name="projectID" value="${projectID}">
			<rb:text message="feature_name" locale="${locale}" />
			<br>
			<input type="text" name="buildName" maxlength="50"
			pattern="[A-Za-zА-Яа-яЁё0-9 \\.\\-]{1,50}" required="required">
			<br>
			<span id="tinyRed"><rb:text message="max_50" locale="${locale}" /></span>
			<br>
			<br>
			<input type="submit" value="<rb:text message='add_button' locale='${locale}' />">		
		</form>
	</div>	
	</c:if>
	
	<br>
	
	<c:if test="${newBuildAdded}">
		<div class="form4">
			<rb:text message="data_added" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${buildNameExists}">
		<div class="form3">
			<rb:text message="build_name_exists" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${buildAddError}">
		<div class="form3">
			<rb:text message="error_message_2" locale="${locale}" />
		</div>
	</c:if>
		
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>