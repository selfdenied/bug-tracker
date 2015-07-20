<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css'/>
<script src="<c:url value='js/issue.js' />"></script>
<style>
table, th, td {
	width:100%;
    border: none;
    border-collapse: collapse;
    background-color: white;
}
</style>
<title><rb:text message="submit_issue" locale="${locale}" /></title>
</head>
<body>

	<c:if test="${!sessionScope.member.admin}">
		<h2 class="usermenu">
			<a href="controller?action=listIssues">
				<rb:text message="to_issues_list" locale="${locale}" />
			</a>
		</h2>
	</c:if>
	
	<rb:header role="${sessionScope.member.admin}" />
	
	<c:if test="${formNotFilled}">
	
	<div class="form5">
		<span style="color:red">
			<rb:text message="issues_2" locale="${locale}" />
		</span>
		<rb:text message="add_new_record" locale="${locale}" />
	</div>
	
	<br>
	
	<div class="form">
		<span>
			<rb:text message="enter_issue_data" locale="${locale}" />
		</span>
		<br>
		<span style="color:green; font-size:16px">
			<rb:text message="submit_issue_message" locale="${locale}" />
		</span>
		<br>
		<span style="color:blue; font-size:16px">
			<rb:text message="empty_fields_message" locale="${locale}" />
		</span> 
	</div>
	
	<br>
	
	<div class="form">
		<form method="post">
			<input type="HIDDEN" name="action" value="submitIssue">
			<rb:text message="summary" locale="${locale}" />
			<br>  
			<input type="text" name="summary" required="required" 
			size="30" pattern="[A-Za-zА-Яа-яЁё0-9| .,!?]+">
			<br>
			<br>
			<rb:text message="description" locale="${locale}" />
			<br> 
			<textarea name="desc" rows="3" cols="30" 
			required="required" pattern="[A-Za-zА-Яа-яЁё0-9| .,!?]+"></textarea>
			<br>
			<br>
			<rb:text message="status" locale="${locale}" />
			<select name="status" id="status" required="required" onchange="handleStatus()">
				<option></option>
				<c:forEach var="status" items="${statuses}">
					<option value="${status.id}"><c:out value="${status.featureName}"/></option>
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
			<table>
			<c:forEach var="listElement" items="${listOfBuilds}">
			<tr id="${listElement[0].project.id}" style="visibility:collapse">
				<td>
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
			<select name="assignee" id="assignee" disabled="disabled">
				<option></option>
				<c:forEach var="assignee" items="${members}">
					<option value="${assignee.id}">
						<c:out value="${assignee.firstName} ${assignee.lastName}"/>
					</option>
				</c:forEach>
			</select>
			<br>
			<br>
			<input type="submit" value="<rb:text message='add_button' locale='${locale}' />">		
		</form>
	</div>
	
	</c:if>
	
	<c:if test="${newIssueAdded}">
		<div class="form4">
			<rb:text message="data_added" locale="${locale}" />
		</div>
	</c:if>
	
	<c:if test="${issueAddError}">
		<div class="form3">
			<rb:text message="error_message_2" locale="${locale}" />
		</div>
	</c:if>
	
	<br>
		
	<jsp:include page="/jsp/common/fragment/footer.jsp"></jsp:include>

</body>
</html>