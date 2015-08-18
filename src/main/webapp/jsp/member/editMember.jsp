<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="edit_member" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<span id="red"><rb:text message="members_2" locale="${locale}" /></span>
		<rb:text message="edit_existing_record" locale="${locale}" />
		<br>
		<span id="black">
		(<rb:text message="user_id" locale="${locale}" /> <c:out value="${userID}"></c:out>)
		</span>
	</div>
	
	<br>
	
	<div class="blackC">
		<span id="black17">
		<rb:text message="enter_member_data" locale="${locale}" />
		</span>
		<br>
		<span id="red17">
		<rb:text message="max_symbols_message" locale="${locale}" />
		</span>
		<br> 
		<span id="blue16">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	
	<br>
	
	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="editMember">
			<input type="HIDDEN" name="userID" value="${userID}">
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
			<c:choose>
			<c:when test="${userID == 1}">
				<select name="admin" required="required">
					<option></option>
					<option value="true"><rb:text message="admin_2" locale="${locale}" /></option>
				</select>
			</c:when>
			<c:otherwise>
				<select name="admin" required="required">
					<option></option>
					<option value="true"><rb:text message="admin_2" locale="${locale}" /></option>
					<option value="false"><rb:text message="user_2" locale="${locale}" /></option>
				</select>
			</c:otherwise>
			</c:choose>
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
	
	<c:if test="${loginExists}">
		<div class="form3">
			<rb:text message="login_exists" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${dataUpdateError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
			
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>
	
</body>
</html>