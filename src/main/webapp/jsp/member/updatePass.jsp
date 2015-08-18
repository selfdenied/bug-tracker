<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="update_pass_page" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
		<div class="form">
			<span><rb:text message="pass_message" locale="${locale}" /></span>
			<br>
			<span id="blue16">
			<rb:text message="pass_message_2" locale="${locale}" />
			</span> 
		</div>
		
		<br>
		
		<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="updatePass">
			<rb:text message="new_password" locale="${locale}" /> 
			<br>
			<input type="password" name="newPassword" maxlength="20" 
			pattern="[A-Za-z0-9@\\._\\-]{5,20}" required ="required">
			<br>
			<br>
			<rb:text message="confirm_password" locale="${locale}" />
			<br>
			<input type="password" name="newPasswordConfirm" maxlength="20" 
			pattern="[A-Za-z0-9@\\._\\-]{5,20}" required ="required">
			<br>
			<br>
			<input type="submit" value="<rb:text message='update_button' locale='${locale}' />">		
		</form>
		</div>
	</c:if>
		
	<br>
		
	<c:if test="${passChanged}">
		<div class="form4">
			<rb:text message="pass_updated" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${passNotEqual}">
		<div class="form3">
			<rb:text message="pass_not_confirmed" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${passChangeError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
		
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>