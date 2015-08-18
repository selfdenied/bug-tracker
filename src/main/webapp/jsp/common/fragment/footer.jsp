<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>

<p>
	<rb:text message="host_message" locale="${locale}" /> <b>${header["host"]}</b>
	<br> 
	<rb:text message="page_encoding" locale="${locale}" /> <b>${pageContext.request.characterEncoding}</b>
	<br>
	<rb:text message="email_message" locale="${locale}" /> <b>${initParam.adminEmail}</b>
</p>