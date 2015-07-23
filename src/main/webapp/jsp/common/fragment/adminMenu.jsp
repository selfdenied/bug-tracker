<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>

	<div class="menu">
	<span style="color:red">
		<strong><rb:text message="admin_menu" locale="${locale}" /></strong>
	</span>
	<br>
	<br>
		<a href="controller?action=listFeatures&amp;feature=status">
		<rb:text message="admin_statuses" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=listFeatures&amp;feature=resolution">
		<rb:text message="admin_resolutions" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=addFeature&amp;feature=resolution">
		<rb:text message="admin_add_resolution" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=listFeatures&amp;feature=type">
		<rb:text message="admin_types" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=addFeature&amp;feature=type">
		<rb:text message="admin_add_type" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=listFeatures&amp;feature=priority">
		<rb:text message="admin_priorities" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=addFeature&amp;feature=priority">
		<rb:text message="admin_add_priority" locale="${locale}" />
		</a>
		<br>
		<br>
		<a href="controller?action=listProjects">
		<rb:text message="admin_projects" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=addProject">
		<rb:text message="admin_add_project" locale="${locale}" />
		</a>
		<br>
		<br>
		<a href="controller?action=listMembers">
		<rb:text message="admin_members" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=addMember">
		<rb:text message="admin_add_member" locale="${locale}" />
		</a>
		<br>
		<br>
		<a href="controller?action=listIssues">
		<rb:text message="admin_issues" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=submitIssue">
		<rb:text message="admin_add_issue" locale="${locale}" />
		</a>
		<br>
		<a href="controller?action=deleteIssue">
		<rb:text message="admin_delete_issues" locale="${locale}"/>
		</a>
	</div>