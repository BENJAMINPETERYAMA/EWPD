<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitDefinitionCreate.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Module Configuration</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden
			value="#{moduleBackingBean.loadModuleConfigurationFromTree}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="moduleConfigurationForm">
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../module/moduleTree.jsp" /></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{moduleBackingBean.validationMessages}"></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="99%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td>
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="left" width="2"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td class="tabNormal" width="186"><h:commandLink
											action="#{moduleBackingBean.loadEditPage}" id="genId"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value=" General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<td>
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<TD class="tabActive" width="186"><h:commandLink
											action="#{moduleBackingBean.loadModuleConfiguration}">
											<h:outputText id="dataDomainTable"
												value="Module Configuration" />
										</h:commandLink></TD>

										<TD align="right" width="2"><IMG
											src="../../images/tabNormalRight.gif" width="3" height="21"></TD>
									</TR>
								</table>
								</td>
								<td width="63%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left: 6px; margin-right: -6px; padding-bottom: 1px; padding-top: 6px; width: 100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="80%">
							<TBODY>
								<TR>
									<TD colspan=3><BR>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="30%"><h:outputText id="TasksMod"
												value="Tasks" /></TD>

											<TD width="33%">
											<DIV id="TaskDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtTask" value="#{moduleBackingBean.task}"></h:inputHidden>
											<TD align="left" width="36%"><h:commandButton
												alt="TaskName" id="TaskButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="ewpdModalWindow_ewpd('../popups/moduleTaskPopup.jsp'+getUrl()+'?number=' + number,'TaskDiv','moduleConfigurationForm:txtTask',2,2); return false;"
												tabindex="3"></h:commandButton></TD>
										</TR>


									</TABLE>
									</TD>
								</TR>
								<TR>
									<TD width="30%"><h:commandButton value="Save"
										id="associated"
										onclick="unsavedDataFinder(this.id);return false;"
										onmousedown="javascript:savePageAction(this.id);"
										styleClass="wpdButton" tabindex="8">
									</h:commandButton></TD>

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
							style="position: relative; cursor: hand;">Module
						Configuration</DIV>
						<DIV id="panel2Content" class="tabContentBox"
							style="position: relative; font-size: 10px;"><BR>
						<table cellpadding="0" cellspacing="0" width="100%" border="0">
							<tr>
								<td>
								<div id="resultHeaderDiv" style="width: 97%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="99%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD width="50%"><h:outputText value="Tasks"></h:outputText></TD>
											<TD width="50%"><h:outputText value="Delete"></h:outputText></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<td><!-- Search Result Data Table -->
								<DIV id="searchResultdataTableDiv"
									style="height: 252px; overflow: auto; width: 97%;"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{moduleBackingBean.searchResultList != null}"
									value="#{moduleBackingBean.searchResultList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
									width="99%">

									<h:column>
										<h:outputText id="taskName" value="#{singleValue.taskName}"></h:outputText>
										<h:inputHidden id="taskID" value="#{singleValue.taskId}"></h:inputHidden>
										<f:verbatim> &nbsp; &nbsp;</f:verbatim>
										<f:verbatim>&nbsp;&nbsp;</f:verbatim>

									</h:column>
									<h:column>
										<h:selectBooleanCheckbox id="deleteChkBox"
											onclick="enableDisableDelete('moduleConfigurationForm:searchResultTable',1,0,'moduleConfigurationForm:deleteButton');">
										</h:selectBooleanCheckbox>
									</h:column>

								</h:dataTable></DIV>
								</td>

								<h:commandLink id="deleteAssociation"
									style="display:none; visibility: hidden;"
									action="#{moduleBackingBean.deleteModuleTaskAssociation}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandLink>
								<h:commandLink id="saveLink"
									style="display:none; visibility: hidden;"
									action="#{moduleBackingBean.saveModuleAssociation}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandLink>

							</TR>
							<TR>
								<TD><h:commandButton id="deleteButton"
									styleClass="wpdButton" value="Delete"
									onclick="unsavedDataFinder(this.id);return false;"></h:commandButton>
								</TD>
							</TR>
						</TABLE>
						</DIV>
						</DIV>

						<!-- Space for hidden fields --> <h:inputHidden id="moduleID"
							value="#{moduleBackingBean.moduleId}"></h:inputHidden> <h:inputHidden
							id="selectedTaskId" value="#{moduleBackingBean.selectedTaskId}"></h:inputHidden>
						<h:inputHidden id="selectedTaskIds"
							value="#{moduleBackingBean.selectedTaskIds}"></h:inputHidden> <h:inputHidden
							id="hiddenTxt" value="#{moduleBackingBean.taskTxt}"></h:inputHidden>
						<h:inputHidden id="panelData"  /> <h:inputHidden
							id="inputTextData" /> <h:inputHidden id="duplicateData"
							value="#{moduleBackingBean.duplicateData}" /> <!-- End of hidden fields  -->

						</fieldset>
						</TD>
					</tr>
				</table>
				<TR>
					<TD><%@include file="../navigation/bottom.jsp"%>
					</TD>
				</TR></td>
		</tr>
	</table>
	</h:form>
	</BODY>
</f:view>
<!-- space for script -->

<script>
IGNORED_FIELD1='moduleConfigurationForm:duplicateData';
	var number = Math.random();
	copyHiddenToDiv_ewpd('moduleConfigurationForm:txtTask','TaskDiv',2,2 );
	function taskSelected(){
		var filterTxt=document.getElementById('moduleConfigurationForm:taskTxtArea');
		var retvalue = ewpdModalWindow_ewpd('../popups/moduleTaskPopup.jsp'+getUrl()+'?'+'&filterTxt='+ filterTxt + '&temp=' + Math.random()+'&&'+'roleModuleValue='+document.getElementById('moduleConfigurationForm:taskTxtArea').value, 'moduleConfigurationForm:taskTxtArea','moduleConfigurationForm:taskTxtHidden',2,2);	
	}
	//copyHiddenToDiv_ewpd('moduleConfigurationForm:Prm','itemDiv',2,2 );
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('moduleConfigurationForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','50%:50%');		
			setColumnWidth('moduleConfigurationForm:searchResultTable','50%:50%');	
			var  tableObj = getObj('moduleConfigurationForm:searchResultTable');
				if(tableObj.rows.length>9){
					syncTables('resultHeaderTable','moduleConfigurationForm:searchResultTable');
				}	
				else{
					
					var relTblWidth = document.getElementById('moduleConfigurationForm:searchResultTable').offsetWidth;
					document.getElementById('resultHeaderTable').width = relTblWidth+ 'px';
					document.getElementById('moduleConfigurationForm:searchResultTable').width = relTblWidth+ 'px';
					setColumnWidth('resultHeaderTable','50%:50%');		
					setColumnWidth('moduleConfigurationForm:searchResultTable','50%:50%');
					}	
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}
	function syncTables(){
			var relTblWidth = document.getElementById('moduleConfigurationForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth-17 + 'px';
			setColumnWidth('resultHeaderTable','49%:51%');		
			setColumnWidth('moduleConfigurationForm:searchResultTable','49%:51%');
		}
	enableDisableDelete('moduleConfigurationForm:searchResultTable',1,0,'moduleConfigurationForm:deleteButton');
	
	function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'moduleConfigurationForm:associated'){
		var tableObject = document.getElementById('moduleConfigurationForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('moduleConfigurationForm:searchResultTable');
			if(document.getElementById('moduleConfigurationForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('moduleConfigurationForm:saveLink');	
				}
			}else{
				submitLink('moduleConfigurationForm:saveLink');
			}
		}else{
			submitLink('moduleConfigurationForm:saveLink');
		}
	}else if (buttonId == 'moduleConfigurationForm:deleteButton'){
			
				if(confirmDelete()){
							submitLink('moduleConfigurationForm:deleteAssociation');	
				}
			
		}
			
	}			

	function getPanelData(list){
      var tagNames = list.split(',');
      var dataOnScreen = "";
      var tableObject = document.getElementById(tagNames[0]);
      var rows = tableObject.rows.length;
      if(rows >0){
            var columns = tableObject.rows[0].cells.length;
            for(var i=0;i<rows;i++){
                  for(var j=0;j<columns;j++){
                        if(null != tableObject.rows[i].cells[j].children[0]){
                              if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
                              }else{
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML);       
                              }
                        }
                  }
            }
			
            return dataOnScreen;
      }else{
            return dataOnScreen;
      }
	}



	function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Task?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('moduleConfigurationForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
		delTaskId = getObj('moduleConfigurationForm:selectedTaskIds');
		delTaskId.value = '';
		var taskCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			
			if(cur_row.cells[1].children[0].checked) {
				if(taskCount != 0) {
					delTaskId.value += '~';
				 }
				delTaskId.value += cur_row.cells[0].children[1].value;
				taskCount ++;
			}
		}
		if(taskCount > 0)
			return true;
		return false;
	}

	function selectTask(){
	if(window.event.keyCode == 13){
		taskSelected();
		return false;
	}else{
		return true;
	}
}

checkForOnLoadData();
function checkForOnLoadData(){

	var tableObject = document.getElementById('moduleConfigurationForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('moduleConfigurationForm:searchResultTable');
			document.getElementById('moduleConfigurationForm:panelData').value = onLoadPanelData;
		}
	}
	if(null != document.getElementById('moduleConfigurationForm:txtTask')){
		var inputTexts = checkForTextData();
		document.getElementById('moduleConfigurationForm:inputTextData').value = inputTexts;
	}

}

function checkForTextData(){
	var inputTexts = document.getElementById('moduleConfigurationForm:txtTask').value; 
	return inputTexts;

}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="moduleConfigInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('moduleConfigurationForm:duplicateData').value == ''){
		document.getElementById('moduleConfigurationForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		//checkForOnLoadData();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('moduleConfigurationForm:duplicateData').value;
	}
</script>
</HTML>









