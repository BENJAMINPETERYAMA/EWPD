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

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 5%;
}.shortDescriptionColumn {
}
.longDescriptionColumn {	
}
.longDescriptionColumn2 {
	width: 100%;
	colspan: 2;
}
</style>
</HEAD>
<f:view>
	<BODY>
	<h:form id="popupForm">
		<h:outputText value="$#~"></h:outputText>
		<h:dataTable id="componentDataTable"
			value="#{popupBackingBean.searchList}" var="eachRow" cellpadding="2"
			cellspacing="1" bgcolor="#cccccc"
			rendered="#{popupBackingBean.searchList != null}" border="0"
			rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn" width="100%">

			<h:column>
				<wpd:singleRowSelect groupName="coverage" id="coverageId"
					rendered="true"></wpd:singleRowSelect>
				
			</h:column>
				<h:column>
				<h:inputHidden value="#{eachRow.adminMethodNo}"></h:inputHidden>
				<h:inputHidden value="#{eachRow.description}"></h:inputHidden>
				<h:inputHidden value="#{eachRow.adminMethodSysId}"></h:inputHidden>
				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
				<h:outputText value="#{eachRow.adminMethodNo}"></h:outputText>
				<f:verbatim>&nbsp;&nbsp;</f:verbatim>
			</h:column>
			<h:column>
				<f:verbatim>&nbsp;</f:verbatim>
				<h:outputText value="#{eachRow.description}"></h:outputText>
			</h:column>
		</h:dataTable>
<h:inputHidden id="recordsGreaterThanMaxSize" value="#{popupBackingBean.recordsGreaterThanMaxSize}"></h:inputHidden> 
		<h:outputText value="$$~"></h:outputText>
		<h:outputText value="*#~"></h:outputText>
		<h:dataTable id="errorDataTable" value="#{requestScope.messages}"
			var="item" cellpadding="7" cellspacing="1" bgcolor="#cccccc"
			rendered="#{requestScope.messages != null}" border="0"
			rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="longDescriptionColumn2">
			<h:column>
				
				<w:messageForPopup></w:messageForPopup>
			</h:column>
		</h:dataTable>
		<h:outputText value="**~"></h:outputText>
	</BODY>
	</h:form>
</f:view>