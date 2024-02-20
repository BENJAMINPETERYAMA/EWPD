<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>userLogin.jsp</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<h:form styleClass="form" id="userForm">
			<h:outputLabel styleClass="outputLabel" id="label1" for="userId">

				<h:outputText id="text2" styleClass="outputText" value="User Id"></h:outputText>
			</h:outputLabel>
			<h:inputText styleClass="inputText" id="userId"></h:inputText>
			<hx:commandExButton type="submit" value="Submit"
				styleClass="commandExButton" id="login" onclick="loginBackingBean.gotoLaunchPage()"></hx:commandExButton>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>
</HTML>
