<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>

<p>
	<rb:text message="host_message" /> <b>${header["host"]}</b>
	<br> 
	<rb:text message="page_encoding" /> <b>${pageContext.request.characterEncoding}</b>
	<br>
	<em><rb:text message="email_message" /></em> <b>${initParam.adminEmail}</b>
</p>