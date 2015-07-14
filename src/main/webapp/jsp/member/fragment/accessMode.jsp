<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<h2>
		<rb:text message="access_mode" locale="${locale}" /> 
		<c:choose>
			<c:when test="${sessionScope.member.admin}">
				<span style="color:red"><rb:text message="admin" locale="${locale}" /></span>
			</c:when>
			<c:otherwise>
				<span style="color:red"><rb:text message="user" locale="${locale}" /></span>
			</c:otherwise>
		</c:choose>
	</h2>
