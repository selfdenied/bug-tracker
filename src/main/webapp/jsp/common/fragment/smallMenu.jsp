<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<div class="smallmenu">
		<a href="controller?action=backHome">
		<rb:text message="main_page" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=updatePersonalData">
		<rb:text message="edit_pers_data" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=updatePass">
		<rb:text message="update_pass" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=logout">
		<rb:text message="logout" locale="${locale}" />
		</a>
	</div>
