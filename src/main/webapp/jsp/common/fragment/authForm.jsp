<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
	<h3 style="color:red">
	<rb:text message="auth_message" />
	</h3>	
	<div>
		<span><rb:text message="enter_pass_message" /></span> 
	</div>
	<br>
		<form method="post">
			<input type="HIDDEN" name="action" value="welcome">
			<rb:text message="e-mail" /> 
			<br>
			<input type="email" name="login" required="required" size="30">
			<br>
			<rb:text message="password" /> 
			<br>
			<input type="password" name="password" required="required" size="30">
			<br> <br>
			<input type="submit" value = "<rb:text message="enter" />">		
		</form>