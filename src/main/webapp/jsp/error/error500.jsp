<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="error">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="error_page_500" locale="${locale}" /></title>
</head>
<body>

    <div class="error">
   
    <img src="images/error500.jpg" alt="<rb:text message='internal_error' locale='${locale}' />" 
    height="319" width="320">
    </div>
    
    <div class="error3">
    <p>
    	<b><rb:text message="request_uri" locale="${locale}" /></b> 
    	<c:choose>
    	<c:when test="${not empty pageContext.exception}">
    		${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}
    	</c:when>
    	<c:otherwise>
    		${base}
    	</c:otherwise>
    	</c:choose>
    </p>
    <p>
    	<b><rb:text message="request_status" locale="${locale}" /></b> 
    	<rb:text message="failed" locale="${locale}" />
    </p>
    <p>
    	<b><rb:text message="error_code" locale="${locale}" /></b> 
    	<c:choose>
    	<c:when test="${not empty pageContext.exception}">
    		${pageContext.errorData.statusCode}
    	</c:when>
    	<c:otherwise>
    		<rb:text message="500" locale="${locale}" />
    	</c:otherwise>
    	</c:choose>
    </p>
    <p>
    	<b><rb:text message="exception" locale="${locale}" /></b>
    	<c:choose>
    	<c:when test="${not empty pageContext.exception}">
    		${pageContext.exception}
    	</c:when>
    	<c:otherwise>
    		${requestScope.exception}
    	</c:otherwise>
    	</c:choose>
    </p>
    <p>
    	<b><rb:text message="exception_message" locale="${locale}" /></b>
    	<c:choose>
    	<c:when test="${not empty pageContext.exception}">
    		${pageContext.exception.message}
    	</c:when>
    	<c:otherwise>
    		${requestScope.exception.message}
    	</c:otherwise>
    	</c:choose> 
    </p>
	<c:if test="${not empty requestScope.exception}">
	<p>
		<b><rb:text message="exception_cause" locale="${locale}" /></b> 
		${requestScope.exception.cause}
	</p>
	</c:if>
    
    <button onclick="history.back()">
    	<rb:text message="back_to_previous" locale="${locale}" />
    </button>
    </div>

</body>
</html>