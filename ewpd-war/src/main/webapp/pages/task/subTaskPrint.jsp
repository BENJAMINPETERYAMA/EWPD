
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

<TITLE>Print Sub-Task</TITLE>
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
			<td><h:form styleClass="form" id="subTaskPrintForm">
				<h:inputHidden id="taskId" value="#{taskBackingBean.viewTaskId}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
						<TR>

							<TD colspan=3>Administration >> <h:outputText
								id="headerSubTaskType" value="#{taskBackingBean.selectedTaskType}"></h:outputText> (<h:outputText
								id="headerSubTaskName" value="#{taskBackingBean.taskName}"></h:outputText>)
							>> Print
							<div id="SubTaskGenInfo">
							<FIELDSET style="width:70%">


							<TABLE width="60%">

								<TR>
									<TD width="45%"><h:outputText value="Name" /></TD>
									<TD width="50%"><h:outputText id="txtTaskName"
										value="#{taskBackingBean.taskName}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%" valign="top"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="50%"><h:outputText id="txtDescription"
										value="#{taskBackingBean.description}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Type" /></TD>
									<TD width="50%"><h:outputText id="txtTaskType"
										value="#{taskBackingBean.taskType}"></h:outputText>
										<h:inputHidden
										id="taskTypeHidden" value="#{taskBackingBean.taskType}"></h:inputHidden>
									</TD>
								</TR>
								<TR  id="sub1" style="display:none;">
									<TD width="45%"><h:outputText value="ModuleName" /></TD>
									<TD width="50%"><h:outputText id="txtModuleName"
										value="#{taskBackingBean.moduleName}"></h:outputText></TD>
								</TR>
								<TR  id="sub2" style="display:none;">
									<TD width="45%"><h:outputText value="Parent" /></TD>
									<TD width="50%"><h:outputText id="SubTaskParent"
										value="#{taskBackingBean.subTaskParent}"></h:outputText></TD>
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

							</div>


							</TD>
						</TR>



					</TBODY>
				</TABLE></td>
		</tr>
		<tr>
			<td></h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>


<script>

	var printForGenInfo = document.getElementById("subTaskPrintForm:taskId").value;
	if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('subTaskGenInfo');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
	getTaskType();
	function getTaskType(){
		
		var obj;
		obj = document.getElementById("subTaskPrintForm:taskTypeHidden");
		
		if(obj.value == 'Child' )
		{
		sub1.style.display='';		
		sub2.style.display='';	
		}
		else if(obj.value == 'Parent' )
		{
		sub1.style.display='none';
		sub2.style.display='none';
		}
	}
</script>
<script>window.print();</script>
</HTML>
