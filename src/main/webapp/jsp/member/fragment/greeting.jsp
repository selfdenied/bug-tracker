<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<h1>
		<rb:text message="welcome_message_2" locale="${locale}" />
		<span style="color:blue"> 
		<c:out value='${sessionScope.member.firstName} ${sessionScope.member.lastName}'></c:out>
		</span>
	</h1>