<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Create Task</TITLE>
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
<BODY onkeypress="return submitOnEnterKey('taskCreateForm:create');">
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<h:inputHidden id="dummy" value="#{taskBackingBean.taskName}"></h:inputHidden>
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="taskCreateForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv" style="height:380px">	
<!-- Space for Tree  Data	-->					

						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message value="#{taskBackingBean.validationMessages}"></w:message> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

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
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="80%">
									<TBODY>
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
											<TD width="168"><h:outputText  value="Name*" 
											styleClass="#{taskBackingBean.requiredTaskName ? 'mandatoryError': 'mandatoryNormal'}"/>
											</TD>
											<TD align="left" width="32%"><h:inputText styleClass="formInputField" id="taskNameTxt" maxlength="30" tabindex="1" value="#{taskBackingBean.taskName}"/> </TD>
											<TD width="37%"></TD>
										</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="taskCreateForm:taskNameTxt"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Task Name"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
                                      
										<TR>
												<TD width="168" valign="top"><h:outputText  value="Description" styleClass="#{taskBackingBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}"/>
												</TD>
												<TD align="left" width="32%"><h:inputTextarea
												styleClass="formTxtAreaField_GeneralDesc" id="txtDescription" 
												value="#{taskBackingBean.description}"
												tabindex="2"></h:inputTextarea></TD>
											<TD width="37%"></TD>
										</TR>
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher2"
										textComponentName="taskCreateForm:txtDescription"
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
											<TD width="25%"><h:outputText  
												id="taskDatatypeStar" value="Type*"
												styleClass="#{taskBackingBean.requiredTaskType ? 'mandatoryError': 'mandatoryNormal'}"/> </TD>
											<TD align="left" width="30%"><h:selectOneMenu id="dataTypeName"  styleClass="formInputField" 
											onchange="getTaskType()" tabindex="3" value="#{taskBackingBean.taskType}">
											<f:selectItems id="dataTypeList"  value="#{taskBackingBean.taskTypeList}"/>
    										</h:selectOneMenu>
                                         </TD>
										<TR id="sub1" style="display:none;">
											<TD width="156"><h:outputText id="moduleStar"
												value="Module*"
												styleClass="#{taskBackingBean.requiredModule ? 'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<h:inputHidden id="moduleHidden"
												value="#{taskBackingBean.selectedModule}"></h:inputHidden>
											<TD width="183">
											<div id="moduleDiv" class="selectDataDisplayDiv"></div>
											
											</TD>
											<TD width="24"><h:commandButton alt="Module"
												id="moduleButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/modulePopUp.jsp'+getUrl()+'?number='+number,'moduleDiv','taskCreateForm:moduleHidden',2,1);
																clearRefaDataFieldOnBDChange('taskCreateForm', 'moduleOldHidden', 'moduleHidden');return false;" 
												tabindex="4">
											</h:commandButton></TD>
										</TR>
											
										<TR id="sub2" style="display:none;">
											<TD width="156"><h:outputText id="taskStar"
												value="Parent Task*"
												styleClass="#{taskBackingBean.requiredTask ? 'mandatoryError': 'mandatoryNormal'}" />
											</TD>
											<h:inputHidden id="taskHidden"
												value="#{taskBackingBean.selectedTask}"></h:inputHidden>
											<TD width="183">
											<div id="taskDiv" class="selectDataDisplayDiv"></div>
											
											</TD>
											<TD width="24"><h:commandButton alt="Task"
												id="taskButton" image="../../images/select.gif"
												onclick="copySelectedModule();setRefDataUseFlag('taskCreateForm', 'taskHidden', 'taskDiv');return false;"
												tabindex="5">
											</h:commandButton></TD>
										</TR>
										<TR>
											<TD width="25%">
												<h:commandButton value="Create" id="create"
														styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="6">
												</h:commandButton>
												<h:commandLink id="createTask"
														style="hidden" action="#{taskBackingBean.create}">
												</h:commandLink>
											</TD>
											
										</TR>
									</TBODY>
									</TABLE>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->

<h:inputHidden id="idHidden" value="#{taskBackingBean.taskId}"></h:inputHidden>
<h:inputHidden id="moduleID" value="#{taskBackingBean.selectedModuleId}"></h:inputHidden>
<h:inputHidden id="moduleOldHidden" value="#{taskBackingBean.oldParentModuleValue}"></h:inputHidden>
<!-- End of hidden fields  -->

				</h:form>
			</TD>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	</table>
</BODY>
</f:view>
<!-- space for script -->
<script>

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1","rapidSpellWebLauncher2"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('taskCreateForm:createTask').click();	
}
	document.getElementById('taskCreateForm:taskNameTxt').focus(); // for on load default selection

	//function to set the selected module value to a variable
	function copySelectedModule(){
		var moduleValue = '';
		if(document.getElementById('taskCreateForm:moduleHidden').value != '' ||
				document.getElementById('taskCreateForm:moduleHidden').value != null){
			moduleValue = document.getElementById('taskCreateForm:moduleHidden').value;
			ewpdModalWindow_ewpd('../popups/taskPopUp.jsp'+getUrl()+'?number='+number + '&selectedModule=' + moduleValue,'taskDiv','taskCreateForm:taskHidden',2,1);

		}
		return false;
	}

	getTaskType();
	function getTaskType(){
		var i;
		var obj;
		obj = document.getElementById("taskCreateForm:dataTypeName");
		i= obj.selectedIndex;
		if(i!=1 && i!=0)
		{
		sub1.style.display='';		
		sub2.style.display='';	
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		}
	}
	copyHiddenToDiv_ewpd('taskCreateForm:moduleHidden','moduleDiv',2,1); 
	copyHiddenToDiv_ewpd('taskCreateForm:taskHidden','taskDiv',2,1); 
	var number = Math.random();

appendToRefDataVariablesSelectedRefDataName('taskHidden', 'taskDiv'); 


</script>
</HTML>





