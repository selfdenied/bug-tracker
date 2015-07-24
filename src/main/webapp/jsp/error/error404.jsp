<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/style.css' type='text/css' />
<title><rb:text message="error_page_404" locale="${locale}" /></title>
</head>
<body>

    <div class="error">
   
	<br>
    
    <img src="images/error404.jpg" alt="<rb:text message='page_not_found' locale='${locale}' />" 
    height="328" width="554">
    </div>
    
    <div class="error2">
    <p>
    	<b><rb:text message="request_uri" locale="${locale}" /></b> 
    	${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}
    </p>
    <p>
    	<b><rb:text message="request_status" locale="${locale}" /></b> 
    	<rb:text message="failed" locale="${locale}" />
    </p>
   	<p>
   		<b><rb:text message="servlet_name" locale="${locale}" /></b> 
   		${pageContext.errorData.servletName}
   	</p>
    <p>
    	<b><rb:text message="error_code" locale="${locale}" /></b> 
    	${pageContext.errorData.statusCode}
    </p>
    
    <br>
    
    <button onclick="history.back()">
    	<rb:text message="back_to_previous" locale="${locale}" />
    </button>
    </div>

</body>
</html>