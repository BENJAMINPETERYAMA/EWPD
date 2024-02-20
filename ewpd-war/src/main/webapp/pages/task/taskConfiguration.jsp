<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Edit Task</TITLE>
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
<BODY >
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="taskConfigurationForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->					

						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
											<!-- <w:message value="#{taskBackingBean.validationMessages}"></w:message> -->
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal"><h:commandLink action="#{taskBackingBean.loadEditPage}">
														<h:outputText value=" General Information"/></h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabActive"><h:commandLink
											action="#{taskBackingBean.loadSubTaskConfiguration}">
											<h:outputText id="dataDomainTable" value="Sub-Task Configuration" />
										</h:commandLink></td>

										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="50%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="80%">
									<TBODY>
										<TR>
											<TD colspan=3>
												<BR>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="19%"><h:outputText id="taskLabel"
													value="Tasks" styleClass="mandatoryNormal" /></TD>
											<TD width="24%"><h:inputText id="taskTxtArea"
													tabindex="1" value="#{taskBackingBean.subTaskTxt}"
													styleClass="selectDataDisplayDivForTask"></h:inputText> </TD> 
											<TD width="57%"><h:commandButton id="taskButton"
													alt="Select" image="../../images/autoComplete.gif"
													style="cursor: hand" onclick="taskSelected();return false;"
													tabindex="2" /><h:inputHidden id="taskTxtHidden"
													value="#{taskBackingBean.subTask}"></h:inputHidden></TD>
										</TR>
										
									</TABLE>
									</TD></TR>
                                                                                                                   
                                                                          

										

										<TR>
											<TD width="30%">
												<h:commandButton value="Save" id="associated" styleClass="wpdButton" tabindex="8" onmousedown="javascript:savePageAction(this.id);" action="#{taskBackingBean.createSubTaskAssociation}"> </h:commandButton>
											</TD>
											
										</TR>
									</TBODY>
									
<!--	End of Page data	-->
														
					</table>
					<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
								<TR>
									<TD></TD>
									<TD align="right"></TD>
								</TR>
							</TABLE>
							
						<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Sub-Task Configuration</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD width="35%"><h:outputText
													value="Tasks"></h:outputText></TD>
												<TD width="65%">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{taskBackingBean.associatedSubTask != null}"
										value="#{taskBackingBean.associatedSubTask}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										
										<h:column>
											<h:outputText id="subTaskId" value="#{singleValue.entityName}"></h:outputText>
											<h:inputHidden id="entityID" value="#{singleValue.entityId}"></h:inputHidden>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
										</h:column>
										<h:column>
											<h:commandButton alt="Delete" id="basicDelete" image="../../images/delete.gif" value="Delete" onclick="confirmDeletion(); return false;"/>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
										</h:column>
										
									</h:dataTable></DIV>
									</td>
								</TR>
								<TR>
								<TD>
									</TD>
								</TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->
<h:inputHidden id="taskSelectedID" value="#{taskBackingBean.entityId}"></h:inputHidden>
<h:inputHidden id="selectedTaskName" value="#{taskBackingBean.taskType}"></h:inputHidden>
<!-- End of hidden fields  -->

				</h:form>
			</fieldset></TD>
		</tr>
		</table>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	
</td></tr></table></BODY>
</f:view>
<!-- space for script -->
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	function taskSelected(){
		var retvalue = ewpdModalWindow_ewpd('../popups/subTaskPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'roleModuleValue='+document.getElementById('taskConfigurationForm:taskTxtArea').value, 'taskConfigurationForm:taskTxtArea','taskConfigurationForm:taskTxtHidden',2,2);	
	}
	//copyHiddenToDiv_ewpd('taskConfigurationForm:Prm','itemDiv',2,2 );
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('taskConfigurationForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','35%:35%:30%');		
			setColumnWidth('taskConfigurationForm:searchResultTable','35%:35%:30%');		
		}else{
			document.getElementById('panel2Header').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}
	function confirmDeletion(){				
			var message = "Are you sure you want to delete the associated task?"	
			if(window.confirm(message)){			
				getFromDataTableToHidden('taskConfigurationForm:searchResultTable','entityID','taskConfigurationForm:entityId');
			 	submitLink('taskConfigurationForm:deleteTaskAssociation');
			} 	
			else{			
				return false;
			}
      }
</script>
</HTML>












