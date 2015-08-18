<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<script src="<c:url value='js/issue.js' />"></script>
<title><rb:text message="close_issue" locale="${locale}" /></title>
</head>
<body>

	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	
	<div class="form5">
		<span id="red">
			<rb:text message="issues_2" locale="${locale}" />
		</span>
		<rb:text message="edit_existing_record" locale="${locale}" />
	</div>
	
	<br>
	
	<div class="form">
		<span id="black17">
			<rb:text message="enter_issue_data" locale="${locale}" />
		</span>
		<br>
		<span id="green16">
			<rb:text message="issue_message_2" locale="${locale}" />
		</span>
	</div>
	
	<br>
	
	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="closeIssue">
			<input type="HIDDEN" name="issueID" value="${issueID}">
			<rb:text message="id_2" locale="${locale}" />
			<input type="text" value="${issueID}" size="5" disabled="disabled">
			<br>
			<br>
			<rb:text message="created_date" locale="${locale}" />
			<input type="text" value="${createdDate}" size="15" disabled="disabled">
			<rb:text message="created_by" locale="${locale}" />
			<input type="text" value="${createdBy.firstName} ${createdBy.lastName}" 
			size="30" disabled="disabled">
			<br>
			<br>
			<rb:text message="modified_date" locale="${locale}" />
			<input type="text" value="${modifiedDate}" size="15" disabled="disabled">
			<rb:text message="modified_by" locale="${locale}" />
			<c:choose>
			<c:when test="${not empty modifiedBy}">
				<input type="text" value="${modifiedBy.firstName} ${modifiedBy.lastName}" 
				size="30" disabled="disabled">
			</c:when>
			<c:otherwise>
				<input type="text" disabled="disabled">
			</c:otherwise>
			</c:choose>
			<br>
			<br>
			<rb:text message="summary" locale="${locale}" />
			<br>
			<span id="tinyRed"><rb:text message="max_100" locale="${locale}" /></span>
			<br>    
			<input type="text" name="summary" size="50" maxlength="100" value="${summary}"
			pattern="[A-Za-zА-Яа-яЁё0-9 \\.,!?:;\\-]{1,100}" required="required">
			<br>
			<br>
			<rb:text message="description" locale="${locale}" />
			<br>
			<span id="tinyRed"><rb:text message="max_1000" locale="${locale}" /></span>
			<br>  
			<textarea name="desc" maxlength="1000" required="required">${desc}</textarea>
			<br>
			<br>
			<rb:text message="status" locale="${locale}" />
			<select name="status" id="status" required="required" onchange="handleStatusRes()">
				<option></option>
				<c:forEach var="status" items="${statuses}">
					<option value="${status.id}"><c:out value="${status.featureName}"/></option>
				</c:forEach>
			</select>
			&nbsp;
			<rb:text message="resolution" locale="${locale}" />
			<select name="resolution" id="resolution" disabled="disabled">
				<option></option>
				<c:forEach var="resolution" items="${resolutions}">
					<option value="${resolution.id}"><c:out value="${resolution.featureName}"/></option>
				</c:forEach>
			</select>
			&nbsp;
			<rb:text message="type" locale="${locale}" />
			<select name="type" required="required">
				<option></option>
				<c:forEach var="type" items="${types}">
					<option value="${type.id}"><c:out value="${type.featureName}"/></option>
				</c:forEach>
			</select>
			&nbsp;
			<rb:text message="priority" locale="${locale}" />
			<select name="priority" required="required">
				<option></option>
				<c:forEach var="priority" items="${priorities}">
					<option value="${priority.id}"><c:out value="${priority.featureName}"/></option>
				</c:forEach>
			</select>
			<br>
			<br>
			<rb:text message="project" locale="${locale}" />
			<select name="project" id="project" required="required" onchange="hideBuild()">
				<option></option>
				<c:forEach var="project" items="${projects}">
					<option value="${project.id}" onclick="handleProject(this.value)">
					<c:out value="${project.projectName}"/>
					</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<rb:text message="build" locale="${locale}" />
			<table id="build">
			<c:forEach var="listElement" items="${listOfBuilds}">
			<tr id="${listElement[0].project.id}" class="collapse">
				<td id="build">
				<select name="build" id="select${listElement[0].project.id}" disabled="disabled">
					<option></option>
					<c:forEach var="build" items="${listElement}">
						<option value="${build.id}"><c:out value="${build.buildName}"/></option>
					</c:forEach>
				</select>
				</td>
			</tr>
			</c:forEach>
			</table> 
			<br>
			<rb:text message="assignee" locale="${locale}" />
			<select name="assignee" disabled="disabled">
				<option><c:out value="${assignee.firstName} ${assignee.lastName}"/></option>
			</select>
			<br>
			<br>
			<input type="submit" value="<rb:text message='update_button' locale='${locale}' />">		
		</form>
	</div>
	
	</c:if>
	
	<br>
	
	<c:if test="${issueUpdated}">
		<div class="form4">
			<rb:text message="data_updated" locale="${locale}" />
		</div>
	</c:if>
	
	<c:if test="${issueUpdateError}">
		<div class="form3">
			<rb:text message="error_message_1" locale="${locale}" />
		</div>
	</c:if>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>