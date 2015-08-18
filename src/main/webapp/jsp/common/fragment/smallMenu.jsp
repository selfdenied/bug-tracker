<%@ taglib prefix="rb" uri="customtags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<div id="cssmenu" style="background: #262626">
	<ul>
   		<li class="active">
			<a href="controller?action=backHome">
				<rb:text message="main_page" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=updatePersonalData">
				<rb:text message="edit_pers_data" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=updatePass">
				<rb:text message="update_pass" locale="${locale}" />
			</a>
   		</li>
   		<li class="last">
			<a href="controller?action=logout">
				<rb:text message="logout" locale="${locale}" />
			</a>
   		</li>
	</ul>
	</div>
