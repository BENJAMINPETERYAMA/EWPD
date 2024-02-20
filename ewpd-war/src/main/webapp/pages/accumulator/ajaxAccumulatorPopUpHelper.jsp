<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
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
	<h:form id="accumulatorPopupForm">
		<h:outputText value="$#~"></h:outputText>
		<!--  WAS 6.0 Migration Changes - Code changes for  onkeypress to onchange -->
		<div id="searchItemValueListDiv"><h:selectOneMenu
			id="searchItemValueList" value="#{lgContractRefDataBean.searchText}"
			style="visibility:visible;" onchange="getSearchText(event,this);">
			<f:selectItems value="#{lgContractRefDataBean.searchItemValueList}" />
		</h:selectOneMenu></div>
		<h:outputText value="$$~"></h:outputText>

		<h:outputText value="**$"></h:outputText>
		<div id="accumulatorDataTableDiv" >
	
							<h:dataTable cellspacing="1" width="100%" id="accumulatorDataTable" 
							value="#{lgContractRefDataBean.accumumlatorValues}" var="accumObj"
							cellpadding="0" style="border:1px solid #cccccc;">
							
							<h:column>
							<f:facet name="header">
           						<h:selectBooleanCheckbox onclick="checkAllid(this,'accumulatorPopupForm:accumulatorDataTable','accumulatorCodeChkBox'); "></h:selectBooleanCheckbox>
        					</f:facet>
								<f:verbatim> <h:selectBooleanCheckbox id="accumulatorCodeChkBox"> </h:selectBooleanCheckbox></f:verbatim>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="Name"/>
        					</f:facet>
								<h:outputText value="#{accumObj.svcCde}"></h:outputText>
								<h:inputHidden value="#{accumObj.svcCde}@@#{accumObj.moniesFlg}@@#{accumObj.daysFlg}@@#{accumObj.occursFlg}@@#{accumObj.inClaimsFlg}"></h:inputHidden>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="Description"/>
        					</f:facet>
								<h:outputText value="#{accumObj.name}"></h:outputText>
							</h:column>
							<h:column>
							<f:facet name="header">
           						<h:outputText  value="PVA"/>
        					</f:facet>
								<h:outputText value="#{accumObj.pva}"></h:outputText>
							</h:column>
							<h:column>
							
        					<f:facet name="header">
           						<h:outputText  value="Cost Share Type"/>
        					</f:facet>
								<h:outputText value="#{accumObj.cstTyp}"></h:outputText>
							</h:column>
						</h:dataTable>
						
						</div>
						<h:outputText value="@@$"></h:outputText>
		<DIV id="messageDispDiv">
		<h:outputText value="$##"></h:outputText>
			<TABLE width="100%"><TR><TD align="left"><span id="messag"><w:message/></span></TD></TR></TABLE>
		<h:outputText value="~$~"></h:outputText>
	</div>

	</h:form>
	</BODY>
</f:view>
</HTML>