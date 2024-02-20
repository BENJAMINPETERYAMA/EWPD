<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/admin/ViewLog.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<TITLE>Log Viewer</TITLE>
<script language="javascript">
	function confirmDeleteRequest(id){ 
		var text = "Are you sure you wish to delete log entry " + id +"? \nClick OK to delete, Cancel to go back";
		if(!confirm(text)){
			return false;
		}
	}
</script>
</HEAD>
<f:view>
	<BODY>
	<jsp:include page="../navigation/top.jsp"></jsp:include>
	<br/>
    <span class="pagetitle">View Exception Log</span>
	<w:message/>
	<h:form id="logViewerForm" style="padding:2px 2px 2px 2px;height:420">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		Id:&nbsp;<h:inputText id="logId" value="#{logViewer.logId}" maxlength="8" styleClass="formInputField" style="width:50px;"></h:inputText>
		<h:commandButton value="Go" styleClass="wpdbutton" action="#{logViewer.showLogId}"></h:commandButton> &nbsp;|&nbsp; 
		<h:commandLink action="#{logViewer.show50}">
			<h:outputText value="Last 50"></h:outputText>
		</h:commandLink> &nbsp;|&nbsp; <h:commandLink
			action="#{logViewer.show100}">
			<h:outputText value="Last 100"></h:outputText>
		</h:commandLink> &nbsp;|&nbsp; <h:commandLink
			action="#{logViewer.show200}">
			<h:outputText value="Last 200"></h:outputText>
		</h:commandLink> &nbsp;|&nbsp; <h:commandLink
			action="#{logViewer.showAll}">
			<h:outputText value="All"></h:outputText>
		</h:commandLink> &nbsp;|&nbsp; 
		
		<h:dataTable id="logSummary" value="#{logViewer.logEntriesModel}" var="summary"
			rendered="#{logViewer.summaryRendered}"
			headerClass="dataTableColumnHeader" bgcolor="#CCCCCC"
			rowClasses="dataTableOddRow,dataTableEvenRow"
			cellpadding="1" cellspacing="1"
			width="99%">
			<h:column>
				<f:facet name="header">
					<h:outputText value="Log Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{summary.id}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Server Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{summary.serverName}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Message"></h:outputText>
				</f:facet>
				<h:outputText value="#{summary.message}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Logging Entity"></h:outputText>
				</f:facet>
				<h:outputText value="#{summary.loggingEntity}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Log Time"></h:outputText>
				</f:facet>
				<h:outputText value="#{summary.updateTimestamp}">
					<f:convertDateTime pattern="MM.dd.yyyy HH:mm:ss z" timeZone="#{logViewer.timeZone}"/>
				</h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value=" "></h:outputText>
				</f:facet>
				<h:commandButton action="#{logViewer.showDetails}" 
				   immediate="true" style="text-decoration:none" title="View Log Details"
				   image="../../images/view.gif">
				</h:commandButton>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:panelGroup>
						<h:commandButton value="Delete" styleClass="wpdbutton" action="#{logViewer.delete}"></h:commandButton>
					</h:panelGroup>
				</f:facet>
				<f:verbatim><input type="checkbox" name="logIds" value="</f:verbatim>
					<h:outputText value='#{summary.id}'/>
				<f:verbatim>"/></f:verbatim>
			</h:column>
		</h:dataTable>
		<h:panelGrid columns="2" rendered="#{logViewer.detailsRendered}"
			headerClass="dataTableColumnHeader" bgcolor="#CCCCCC"
			rowClasses="dataTableOddRow"
			cellpadding="1" cellspacing="1"
			width="99%">
				<h:outputText value="Id" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.logEntry.id}"></h:outputText>
				<h:outputText value="Server Name" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.logEntry.serverName}"></h:outputText>
				<h:outputText value="Message" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.logEntry.message}"></h:outputText>
				<h:outputText value="Parameters" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.formattedParameters}" escape="false"></h:outputText>
				<h:outputText value="Stack Trace" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.formattedStackTrace}" escape="false"></h:outputText>
				<h:outputText value="Logging Entity" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.logEntry.loggingEntity}"></h:outputText>
				<h:outputText value="Log Time" styleClass="dataTableColumnHeader"></h:outputText>
				<h:outputText value="#{logViewer.logEntry.updateTimestamp}">
					<f:convertDateTime pattern="MM.dd.yyyy HH:mm:ss z"/>
				</h:outputText>
		</h:panelGrid>
		<TR>
			<%@ include file ="../navigation/bottom_view.jsp" %>
		</TR>
</TABLE>
	</h:form>
	
	</BODY>
</f:view>
</HTML>

