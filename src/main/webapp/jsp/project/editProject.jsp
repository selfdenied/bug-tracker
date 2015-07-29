<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="edit_project" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	<div class="form5">
		<span style="color:red"><rb:text message="admin_projects" locale="${locale}" /></span>
		<rb:text message="edit_existing_record" locale="${locale}" />
		<span style="color:blue">
		(<rb:text message="project_id" locale="${locale}" /> <c:out value="${projectID}"></c:out>)
		</span>
	</div>
	
	<br>
	
	<div class="form">
		<span><rb:text message="enter_project_data" locale="${locale}" /></span>
		<br>
		<span style="color:blue; font-size:16px">
		<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	
	<br>
	
	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="editProject">
			<input type="HIDDEN" name="projectID" value="${projectID}">
			<rb:text message="feature_name" locale="${locale}" />
			<br>  
			<input type="text" name="projectName" size="50" maxlength="50" 
			pattern="[A-Za-zА-Яа-яЁё0-9 \\.\\-]{1,50}" value="${name}" required="required">
			<br>
			<br>
			<rb:text message="project_description" locale="${locale}" />
			<br> 
			<textarea name="projectDescription" rows="3" cols="40" maxlength="1000" 
			required="required">${desc}</textarea>
			<br>
			<br>
			<rb:text message="available_builds" locale="${locale}" /> 
			<br>
				<c:forEach var="build" items="${buildsList}">
					<li>
					<span style="font-size:17px"><c:out value="${build.buildName}"></c:out></span>
					</li>
				</c:forEach>
			<br>
			<br>
			<rb:text message="manager" locale="${locale}" />: 
			<select name="projectManager" required="required">
				<option></option>
				<c:forEach var="manager" items="${managersList}">
					<option value="${manager.id}">
						<c:out value="${manager.firstName} ${manager.lastName}"/>
					</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<input type="submit" value="<rb:text message='update_button' locale='${locale}' />">		
		</form>
	</div>
	</c:if>
	
	<br>
	
	<c:if test="${projectDataUpdated}">
		<div class="form4">
			<rb:text message="data_updated" locale="${locale}" />
		</div>
	</c:if>
		
	<c:if test="${projectUpdateError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
			
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>