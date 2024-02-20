<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- $Id: $ --%>
<%-- <%@taglib uri="http://www.ibm.com/siteedit/sitelib" prefix="siteedit"> --%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet"
	type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/wpd.css" title="Style">

<TITLE>Wellpoint Product Database - Error</TITLE>
</HEAD>
<BODY>

<style>
	.msgDiv{
		color:rgb(23,98,165);
		font-family:Verdana, Arial, Helvetica, sans-serif;
		font-size:12px;
		background-color:rgb(243,243,243);
		border:1px solid;
		border-top-color:rgb(27,112,188);
		border-right-color:rgb(16,67,114);
		border-bottom-color:rgb(16,67,114);
		border-left-color:rgb(27,112,188);
		padding:3px 3px 3px 3px;
		margin:4px 4px 4px 4px;
	}
</style>
<!-- 
	DO NOT DELETE this comment. 

	IE has a 'feature' "Show friendly HTTP error message" that causes application
	specific error pages to NOT display if the content size is less than 512 bytes.  
	This comment was added to increase the size above this limit.  

	This 'feature' can be disabled by going to Tools -> Internet Options -> Advanced 
	and unchecking "Show friendly HTTP error messages" under Browsing.
 -->
<f:subview id="error">
	<jsp:include page="navigation/top_view.jsp"></jsp:include>
	<p></p>
	<p style="font-size:11px;font-family: Verdana, Arial, Helvetica, sans-serif;">
	We are unable to process your request at this time.  Please contact IT support with the information below.
	Sorry for the inconvenience caused.
	</p>
	<div class="msgDiv">
			<h:outputText value="#{ErrorBackingBean.message}" escape="false"></h:outputText>
	</div>
</f:subview>
</BODY>
</HTML>
<% 
request.getSession().removeAttribute("SESSION_LOCKED"); 
request.getSession().removeAttribute("RETURN_CACHED"); 
request.getSession().removeAttribute("RESPONSE_DATA");
%>