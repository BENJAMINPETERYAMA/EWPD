<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitDefinitionCreate.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<BASE target="_self" />
<TITLE>Associated SubTask View</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
</HEAD>
<f:view>
<BODY >
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="associatedSubTaskViewForm">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->	<jsp:include page="../module/moduleTree.jsp" />				
									
						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
											 <w:message value="#{moduleBackingBean.validationMessages}"></w:message>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
							<TR>
								<TD width="200">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText value=" Associated SubTasks" /></TD>
										<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:75%">
									
<!--	Start of Table for actual Data	-->		
				   									
					<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
							</TABLE>
							<h:outputText value="No SubTasks Available."
							rendered="#{moduleBackingBean.tasksSubTaskList == null}"
							styleClass="dataTableColumnHeader"/>
						<DIV id="panel2">
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
												<TD width="50%"><h:outputText
													value="Tasks"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{moduleBackingBean.tasksSubTaskList != null}"
										value="#{moduleBackingBean.tasksSubTaskList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										
										<h:column>
											<h:outputText id="taskName" value="#{singleValue.subTaskName}"></h:outputText>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
										</h:column>
									</h:dataTable></DIV>
									</td>
				<%--<h:inputHidden id="selectedTaskId" value="#{moduleBackingBean.selectedTaskId}"></h:inputHidden>
					</h:inputHidden>--%>
						
								</TR>
								<TR>
								<TD>
									</TD>
								</TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->
<h:inputHidden id="moduleID" value="#{moduleBackingBean.moduleId}"></h:inputHidden>
<h:commandLink id="removeSession" action="#{moduleBackingBean.clearSession}" style="hidden">
<f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>
<!-- End of hidden fields  -->
					
				
				
			</fieldset></TD>
		</tr>
		</table>
		<!-- WAS 6.0 migration changes - Javascript error Invalid arguement thrown, when html tags are not placed properly -->
		</h:form>	
</td></tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>
</table></BODY>
</f:view>
<!-- space for script -->

<script>
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('associatedSubTaskViewForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','50%:50%');		
			setColumnWidth('associatedSubTaskViewForm:searchResultTable','50%:50%');		
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="associatedSubTask" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>












