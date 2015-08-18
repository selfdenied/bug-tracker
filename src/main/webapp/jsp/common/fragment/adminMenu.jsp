<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>

	<div id="cssmenu" class="fright">
	<ul>
   		<li class="active">
			<a href="controller?action=listFeatures&amp;feature=status">
				<rb:text message="admin_statuses" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=listFeatures&amp;feature=resolution">
				<rb:text message="admin_resolutions" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=listFeatures&amp;feature=type">
				<rb:text message="admin_types" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=listFeatures&amp;feature=priority">
				<rb:text message="admin_priorities" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=addFeature&amp;feature=resolution">
				<rb:text message="admin_add_resolution" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=addFeature&amp;feature=type">
				<rb:text message="admin_add_type" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=addFeature&amp;feature=priority">
				<rb:text message="admin_add_priority" locale="${locale}" />
			</a>
   		</li>
		<br>
   		<li>
			<a href="controller?action=listProjects">
				<rb:text message="admin_projects" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=addProject">
				<rb:text message="admin_add_project" locale="${locale}" />
			</a>
   		</li>
   		<br>
   		<li>
			<a href="controller?action=listMembers">
				<rb:text message="admin_members" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=addMember">
				<rb:text message="admin_add_member" locale="${locale}" />
			</a>
   		</li>
   		<br>
   		<li>
			<a href="controller?action=listIssues">
				<rb:text message="admin_issues" locale="${locale}" />
			</a>
   		</li>
   		<li>
			<a href="controller?action=submitIssue">
				<rb:text message="admin_add_issue" locale="${locale}" />
			</a>
   		</li>
   		<li class="last">
			<a href="controller?action=deleteIssue">
				<rb:text message="admin_delete_issues" locale="${locale}"/>
			</a>
   		</li>
	</ul>
	</div>