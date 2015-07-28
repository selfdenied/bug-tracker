<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="add_member" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<span style="color:red"><rb:text message="members_2" locale="${locale}" /></span>
		<rb:text message="add_new_record" locale="${locale}" />
	</div>
	
	<br>
	
	<div class="form">
		<span>
		<rb:text message="enter_member_data" locale="${locale}" />
		</span>
		<br>
		<span style="color:red; font-size:17px">
		<rb:text message="pass_message_2" locale="${locale}" />
		<br>
		<rb:text message="max_symbols_message" locale="${locale}" />
		</span>
		<br> 
		<span style="color:blue; font-size:16px">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	
	<br>
	
	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="addMember">
			<rb:text message="name" locale="${locale}" />
			<br>  
			<input type="text" name="firstName" maxlength="50" 
			pattern="[A-Za-zА-Яа-яЁё '\\-]{1,50}" required="required">
			<br>
			<br>
			<rb:text message="last_name" locale="${locale}" />
			<br> 
			<input type="text" name="lastName" maxlength="50" 
			pattern="[A-Za-zА-Яа-яЁё '\\-]{1,50}" required="required">
			<br>
			<br>
			<rb:text message="e-mail" locale="${locale}" /> 
			<br>
			<input type="email" name="login" maxlength="50" required="required">
			<br>
			<br>
			<rb:text message="role" locale="${locale}" />
			<br>
			<select name="admin" required="required">
				<option></option>
				<option value="true"><rb:text message="admin_2" locale="${locale}" /></option>
				<option value="false"><rb:text message="user_2" locale="${locale}" /></option>
			</select>
			<br>
			<br>
			<rb:text message="password" locale="${locale}" />
			<br>
			<input type="password" name="pass" maxlength="20" 
			pattern="[A-Za-z0-9@\\._\\-]{5,20}" required="required">
			<br>
			<br>
			<rb:text message="confirm_password" locale="${locale}" />
			<br>
			<input type="password" name="passConf" maxlength="20" 
			pattern="[A-Za-z0-9@\\._\\-]{5,20}" required="required">
			<br>
			<br>
			<input type="submit" value="<rb:text message='add_button' locale='${locale}' />">		
		</form>
	</div>
	</c:if>
	
	<br>
	
	<c:if test="${newMemberAdded}">
		<div class="form4">
			<rb:text message="data_added" locale="${locale}" />
		</div>
	</c:if>
	
	<c:if test="${dataError}">
		<div class="form3">
			<rb:text message="member_data_error" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${memberAddError}">
		<div class="form3">
			<rb:text message="error_message_2" locale="${locale}" />
		</div>
	</c:if>
			
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>
	
</body>
</html>