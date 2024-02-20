<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

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
	<BODY onkeypress="return submitOnEnterKey('taskEditForm:save');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="taskEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{taskBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<%-- <table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<!--  td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="100%" class="tabNormal"><h:commandLink
											action="#{taskBackingBean.loadSubTaskConfiguration}">
											<h:outputText id="dataDomainTable" value="Sub-Task Configuration" />
										</h:commandLink></td>

										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td-->
								<td width="72%"></td>
							</tr>
						</table> --%>
						<!-- End of Tab table -->
<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="25%">
		          							<table width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value=" General Information"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="3" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
						
						
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="80%">													
							<TBODY>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="RoleNameStar"
										value="Name*" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.taskName}" /> <h:inputHidden
										value="#{taskBackingBean.taskName}" /></TD>
									<TD width="40%"></TD>
								</TR>
								<script type="text/javascript">
										function RSCustomInterface(tbElementName){
											this.tbName = tbElementName;
											this.getText = getText;
											this.setText = setText;
									
										function getText(){
											if(!document.getElementById(this.tbName)) {
												alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
												return '';
											} else return document.getElementById(this.tbName).value;
										}
										function setText(text){
											if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
										}
									}
									</script>
								<TR>
									<TD width="25%" valign="top">&nbsp;<h:outputText
										id="descriptionStar" value="Description "
										styleClass="#{taskBackingBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD align="left" width="30%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{taskBackingBean.description}" tabindex="7"></h:inputTextarea></TD>
									<TD width="40%"></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="taskEditForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Task Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
								</RapidSpellWeb:rapidSpellWebLauncher>
								<TR>
									<TD width="25%">&nbsp;<h:outputText id="taskDatatypeStar"
										value="Type*"
										styleClass="#{taskBackingBean.requiredTaskType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.taskType}" /> <h:inputHidden
										id="taskTypeHidden" value="#{taskBackingBean.taskType}"></h:inputHidden>
									<%--  TD align="left" width="30%"><h:selectOneMenu id="dataTypeName"  styleClass="formInputField" 
											onchange="getTaskType()" tabindex="6" value="#{taskBackingBean.taskType}">
											<f:selectItems id="dataTypeList"  value="#{taskBackingBean.taskTypeList}"/>
    										</h:selectOneMenu>
                                         </TD--%>
								<TR id="sub1" style="display:none;">
									<TD width="156">&nbsp;<h:outputText id="moduleStar" value="Module*"
										styleClass="#{taskBackingBean.requiredModule ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="moduleHidden"
										value="#{taskBackingBean.selectedModule}"></h:inputHidden>
									<TD width="183">
									<div id="moduleDiv" class="selectDataDisplayDiv"></div>

									</TD>
									<TD width="24"><h:commandButton alt="Module" id="moduleButton"
										image="../../images/select.gif"
										onclick="ewpdModalWindow_ewpd('../popups/modulePopUp.jsp?number=' + number,'moduleDiv','taskEditForm:moduleHidden',2,1);
																clearRefaDataFieldOnBDChange('taskEditForm', 'moduleOldHidden', 'moduleHidden');return false;"
										tabindex="3">
									</h:commandButton></TD>
								</TR>

								<TR id="sub2" style="display:none;">
									<TD width="156">&nbsp;<h:outputText id="taskStar"
										value="Parent Task*"
										styleClass="#{taskBackingBean.requiredTask ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<h:inputHidden id="taskHidden"
										value="#{taskBackingBean.selectedTask}"></h:inputHidden>
									<TD width="183">
									<div id="taskDiv" class="selectDataDisplayDiv"></div>

									</TD>
									<TD width="24"><h:commandButton alt="Task" id="taskButton"
										image="../../images/select.gif"
										onclick="copySelectedModule();setRefDataUseFlag('taskEditForm', 'taskHidden', 'taskDiv');return false;" tabindex="3">
									</h:commandButton></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden" value="#{taskBackingBean.createdUser}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdDateHidden"
										value="#{taskBackingBean.createdTimestamp}" ><f:convertDateTime
										pattern="MM/dd/yyyy HH:mm:ss" /></h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{taskBackingBean.lastUpdatedUser}" /></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{taskBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdatedDateHidden"
										value="#{taskBackingBean.lastUpdatedTimestamp}" ><f:convertDateTime
										pattern="MM/dd/yyyy HH:mm:ss" /></h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>
								<TR>
									<TD width="25%">
										<h:commandButton value="Save" id="save" onmousedown="javascript:savePageAction(this.id);"
														styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="8">
												</h:commandButton>
												<h:commandLink id="saveTask"
														style="hidden" action="#{taskBackingBean.updateTask}">
												</h:commandLink>
									</TD>
								</TR>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="idHidden" value="#{taskBackingBean.taskId}"></h:inputHidden>
				<h:inputHidden id="moduleOldHidden" value="#{taskBackingBean.oldParentModuleValue}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form></TD>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>

<!-- space for script -->
<script>
	//function to set the selected module value to a variable

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('taskEditForm:saveTask').click();	
}
	function copySelectedModule(){
		var moduleValue = '';
		if(document.getElementById('taskEditForm:moduleHidden').value != '' ||
				document.getElementById('taskEditForm:moduleHidden').value != null){
			moduleValue = document.getElementById('taskEditForm:moduleHidden').value;
			ewpdModalWindow_ewpd('../popups/taskPopUp.jsp?number='+number + '&selectedModule=' + moduleValue,'taskDiv','taskEditForm:taskHidden',2,1);

		}
		return false;
	}
	getTaskType();
	function getTaskType(){
		
		var obj;
		obj = document.getElementById("taskEditForm:taskTypeHidden");
		
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
	copyHiddenToDiv_ewpd('taskEditForm:moduleHidden','moduleDiv',2,1); 
	copyHiddenToDiv_ewpd('taskEditForm:taskHidden','taskDiv',2,1); 
	var number = Math.random();
appendToRefDataVariablesSelectedRefDataName('taskHidden', 'taskDiv'); 
</script>


<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="subTaskGenInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
