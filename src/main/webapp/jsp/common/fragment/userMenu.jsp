<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>

	<div id="cssmenu" class="fright">
	<ul>
   		<li class="active">
   			<a href="controller?action=listIssues">
   				<span><rb:text message="search_issues" locale="${locale}" /></span>
   			</a>
   		</li>
   		<li class="last">
   			<a href="controller?action=submitIssue">
   				<span><rb:text message="submit_issue" locale="${locale}" /></span>
   			</a>
   		</li>
	</ul>
	</div>