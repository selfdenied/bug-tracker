<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
	<h3 style="color:red">
	<rb:text message="auth_message" locale="${locale}" />
	</h3>	
	<div>
		<span><rb:text message="enter_pass_message" locale="${locale}" /></span> 
	</div>
	<br>
		<form action="controller" method="post">
			<input type="HIDDEN" name="action" value="auth">
			<input type="HIDDEN" name="lang" value="${locale}">
			<rb:text message="login" locale="${locale}" /> 
			<br>
			<input type="email" name="login" size="30" maxlength="50" required="required">
			<br>
			<rb:text message="password" locale="${locale}" /> 
			<br>
			<input type="password" name="password" maxlength="20" size="30" 
			pattern="[A-Za-z0-9@\\._\\-]{5,20}" required="required">
			<br> <br>
			<input type="submit" value = "<rb:text message='enter' locale='${locale}' />">		
		</form>