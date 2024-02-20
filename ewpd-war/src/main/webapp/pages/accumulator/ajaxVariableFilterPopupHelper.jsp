<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
</HEAD>
<f:view>
	<BODY>
	<h:form id="variableSelectForm">
		<h:outputText value="$#~"></h:outputText>
		<div id="searchItemValueListDiv"><h:selectOneMenu
			id="searchItemValueList" value="#{StructureVariableBean.category}"
			style="visibility:visible;" onkeypress="getSearchText(this);">
			<f:selectItems value="#{lgContractRefDataBean.categoryForCombo}" />
		</h:selectOneMenu></div>
		<h:outputText value="$$~"></h:outputText>

		
		<div id="popupDataTableDiv">
		<h:outputText value="**$"></h:outputText>
		<h:dataTable cellspacing="1" rowClasses="dataTableEvenRow,dataTableOddRow" 
			width="100%" id="varDataTable"
			value="#{StructureVariableBean.variableSearchFilteredResults}" var="varObj"
			cellpadding="0" style="border:1px solid #cccccc;">
			<h:column >
				<wpd:singleRowSelect groupName="varSelectGN" id="varSelect" rendered="true"></wpd:singleRowSelect>
			</h:column>
			
			<h:column>
				<h:outputText value="#{varObj.variableId}"></h:outputText>
				<h:inputHidden
					value="#{varObj.variableId}"></h:inputHidden>
			</h:column>
			<h:column>
				<h:outputText value="#{varObj.description}"></h:outputText>
			</h:column>
		</h:dataTable>
		<h:outputText value="@@$"></h:outputText>
		</div>
		<DIV id="messageDispDiv">
		<h:outputText value="$##"></h:outputText>
			<TABLE width="100%"><TR><TD align="left"><span id="messag"><w:message/></span></TD></TR></TABLE>
		<h:outputText value="~$~"></h:outputText>
	</div>

	</h:form>
	</BODY>
</f:view>
</HTML>