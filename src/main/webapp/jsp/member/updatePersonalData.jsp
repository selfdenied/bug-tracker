<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="edit_pers_data" locale="${locale}" /></title>
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
		
	
	<c:if test="${formNotFilled}">
		<div class="form2">
			<span><rb:text message="enter_newdata_message" locale="${locale}" /></span>
			<br>
			<span style="color:blue; font-size:17px">
			<rb:text message="empty_fields_message" locale="${locale}" />
			</span> 
		</div>
		<br>
		
		<div class="form">
				<form method="post">
				<input type="HIDDEN" name="action" value="updatePersonalData">
				<rb:text message="first_name" locale="${locale}" /> 
				<br>
				<input type="text" name="firstName" required ="required" pattern="[A-Za-zА-Яа-яЁё]+">
				<br>
				<rb:text message="last_name" locale="${locale}" /> 
				<br>
				<input type="text" name="lastName" required ="required" pattern="[A-Za-zА-Яа-яЁё]+">
				<br>
				<rb:text message="e-mail" locale="${locale}" /> 
				<br>
				<input type="email" name="login" required="required">
				<br>
				<br>
				<input type="submit" value="<rb:text message='update_button' locale='${locale}' />">		
			</form>
		</div>
	</c:if>
		
	<br>
		
	<c:if test="${dataUpdated}">
		<div class="form4">
			<rb:text message="data_updated" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${suchLoginExists}">
		<div class="form3">
			<rb:text message="login_exists" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${dataChangeError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
		
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>