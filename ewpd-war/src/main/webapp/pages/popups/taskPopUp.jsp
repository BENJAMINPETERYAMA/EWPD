<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 8%;
	
}.shortDescriptionColumn {
	width: 92%;
	
}
</style>
<TITLE>Task Popup</TITLE>
<%
String browser = request.getHeader("user-agent");
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
<script language="JavaScript" src="../../js/wpd.js"></script>
<%
}
else {
%>
<script language="JavaScript" src="../../js/browserCompatible.js"></script>
<script language="JavaScript" src="../../js/showModalDialog.js"></script>
<%
}
%>
</HEAD>
<f:view>
	<BODY>
	<h:form id="taskLookUpForm">
		<div id="noNotesDiv">
		<h:outputText value="No Tasks Available."
			rendered="#{taskBackingBean.taskLookUpList == null}"
			styleClass="dataTableColumnHeader"/></div>
		<div id="taskLookUpDiv">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('taskLookUpForm:taskLookUpDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" id="headerTable"
						bgcolor="#cccccc">
						<TR>
							<TD align="left" width="6%">
								
							</TD>
							<TD align="center" width="94%">
								<strong><h:outputText value="Task"></h:outputText></strong>
							</TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv>
					<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						cellspacing="1" width="100%" id="taskLookUpDataTable"
						rendered="#{taskBackingBean.taskLookUpList != null}"
						value="#{taskBackingBean.taskLookUpList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
						
							<f:verbatim>
								<wpd:singleRowSelect groupName="task" id="minor"
									rendered="true"></wpd:singleRowSelect>

							</f:verbatim>
							
						</h:column>
						<h:column>
						
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden value="#{eachRow.taskName}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.taskId}"></h:inputHidden>
							<h:outputText value="#{eachRow.taskName}"></h:outputText>
						</h:column>
					</h:dataTable>
					</DIV>
					</td>
				</tr>
			</TBODY>
		</table>
		</div>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
	initialize();
	function initialize(){
		if(document.getElementById('taskLookUpForm:taskLookUpDataTable') != null) {
			document.getElementById("noNotesDiv").style.visibility = 'hidden';
			document.getElementById("noNotesDiv").style.height = "0px";;
			setColumnWidth('taskLookUpForm:taskLookUpDataTable', '26:450');
			setColumnWidth('headerTable', '40:450');
		}else {
			document.getElementById("taskLookUpDiv").style.visibility = 'hidden';
		}
	}
	window.opener = window.dialogArguments.parentWindow;
	var hiddenObj = window.opener.document.getElementById(window.dialogArguments.hiddenId);
	if(hiddenObj == null || hiddenObj == undefined) {
		alert("Hidden field not available");
	}
	if(hiddenObj.value) {
	
	matchCheckboxItems_ewpd('taskLookUpForm:taskLookUpDataTable', window.dialogArguments.selectedValues, 2, 1, 1);
	}
</script>
</HTML>
