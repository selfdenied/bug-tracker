<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="add_build" locale="${locale}" /></title>
</head>
<body>

	<jsp:include page="/jsp/common/fragment/adminMenu.jsp"></jsp:include>
	
	<jsp:include page="/jsp/member/fragment/greeting.jsp"></jsp:include>
	
	<jsp:include page="/jsp/member/fragment/accessMode.jsp"></jsp:include>
	
	<br>
	<br>
	
	<jsp:include page="/jsp/common/fragment/smallMenu.jsp"></jsp:include>
		
	<br>
	<br>
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<span style="color:red"><rb:text message="builds" locale="${locale}" /></span>
		<rb:text message="add_new_record" locale="${locale}" />
		<span style="color:blue">
		(<rb:text message="project_id" locale="${locale}" /> <c:out value="${projectID}"></c:out>)
		</span>	
	</div>
	
	<br>
	
	<div class="form">
		<span><rb:text message="enter_build_name" locale="${locale}" /></span>
		<br>
		<span style="color:blue; font-size:16px">
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
			<input type="text" name="buildName" required="required" pattern="[A-Za-zА-Яа-яЁё0-9| .]+">
			<br>
			<br>
			<input type="submit" value="<rb:text message='add_button' locale='${locale}' />">		
		</form>
	</div>	
	</c:if>
	
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