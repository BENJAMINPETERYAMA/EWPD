
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Print Task Detail</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="20">
		<tr>
			<td><h:form styleClass="form" id="taskPrintForm">
				<h:inputHidden id="viewTaskId" value="#{taskBackingBean.viewTaskId}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
						<TR>

							<TD colspan=3>Administration >> Task (<h:outputText
								id="headerTaskName" value="#{taskBackingBean.taskName}"></h:outputText>)
							>> Print
							<div id="taskGenInfo">
							<FIELDSET style="width:70%">


							<TABLE width="60%">
								<TR>
									<TD width="45%"><h:outputText value="Name" /></TD>
									<TD width="50%"><h:outputText id="txtTaskName"
										value="#{taskBackingBean.taskName}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Type" /></TD>
									<TD width="50%"><h:outputText id="txtTaskType"
										value="#{taskBackingBean.taskType}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%" valign="top"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="50%"><h:outputText id="txtDescription"
										value="#{taskBackingBean.description}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></TD>
									<TD width="50%"><h:outputText id="createdUserView"
										value="#{taskBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="50%"><h:outputText id="createdDateView"
										value="#{taskBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="50%"><h:outputText id="updatedUserView"
										value="#{taskBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="50%"><h:outputText id="updatedTimeView"
										value="#{taskBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
							</TABLE>

							</FIELDSET>

							<FIELDSET style="width:70%"><h:outputText
								value="No Items Available."
								rendered="#{taskBackingBean.printSearchResultList == null}" />
							<TABLE border="0" cellspacing="1" cellpadding="3"
								class="outputText">
								<TBODY>


									<TR valign="top">
										<TD width="100%">
										<DIV id="panel2">
										<DIV id="panel2Header" class="tabTitleBar"
											style="position:relative; cursor:hand;">Sub-Task
										Configuration</DIV>
										<DIV id="panel2Content" class="tabContentBox"
											style="position:relative;font-size:10px;"><BR>
										<table cellpadding="0" cellspacing="0" width="100%" border="0">
											<h:dataTable headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												cellspacing="1"
												rendered="#{taskBackingBean.subTaskList != null}"
												value="#{taskBackingBean.subTaskList}" border="0"
												width="100%">
												<h:column>
													<h:outputText id="itName"
														value="#{singleValue.primaryCode}"></h:outputText>
													<h:inputHidden id="primaryCd"
														value="#{singleValue.primaryCode}" />
													<h:inputHidden id="catalogId"
														value="#{singleValue.subCatalogId}" />
													<h:inputHidden id="CtlgSysId"
														value="#{singleValue.subCatalogSysId}"></h:inputHidden>
												</h:column>
											</h:dataTable>

										</table>
										</div>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</td>
						</tr>
						<tr>
							<td></h:form></td>
						</tr>
				</table>
	</BODY>
</f:view>
<script>
initialize();
		function initialize(){
			if(document.getElementById('taskPrintForm:searchResultTable') == null) 
				
				document.getElementById("panel2").style.visibility = 'hidden';
			}
	
var printForGenInfo = document.getElementById("taskPrintForm:viewSubCatalogId").value;
if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('associatedItemPrint');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
</script>
<script>window.print();</script>
</HTML>
