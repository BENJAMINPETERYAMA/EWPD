<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Edit Module</TITLE>
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
<BODY ><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<h:inputHidden id="dummy" value="#{roleBackingBean.configureBreadCrumbText}" />
				<h:inputHidden id="associatedTask" value="#{roleBackingBean.associatedTaskList}" />
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="roleAssociatedTaskForm">
					<TABLE width="99%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
								<jsp:include page="../role/roleConfigTree.jsp" />				

						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<TR>
											<TD>
											 <w:message value="#{roleBackingBean.validationMessages}"></w:message>
											</TD>
										</TR>		
									</TBODY>
								</TABLE>


								
<!-- Table containing Tabs -->
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<TR>
					          			<TD width="25%">
									<TABLE width="215" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD class="tabActive" width="210"><h:outputText
												id="dataDomainTable" value="Authorized Tasks " /></TD>
											<TD align="right" width="0"></TD>
											<TD align="right" width="2"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</td>
									
									</TR></table>
<!-- End of Tab table -->					
									<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">				
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="80%">
									<TBODY>
										<TR>
											<TD colspan="3">
												<BR>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="30%"><h:outputText id="TasksMod" value="Tasks" /></TD>
											
											<TD width="33%">
											<DIV id="TaskDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtTask" value="#{roleBackingBean.task}"></h:inputHidden>
											</TD><TD align="left" width="36%">
												<h:commandButton alt="TaskName" id="TaskButton" image="../../images/select.gif" 
													style="cursor: hand" onclick="ewpdModalWindow_ewpd('../popups/roleAssociatedTaskPopup.jsp'+getUrl()+'?number=' + number,'TaskDiv','roleAssociatedTaskForm:txtTask',3,2); return false;" tabindex="3">
												</h:commandButton>
												</TD>
									   </TR>       
										
									
									</TABLE>
									</TD></TR>
  										<TR>
											<TD width="30%">
												<h:commandButton value="Save" onclick="unsavedDataFinder(this.id);return false;" onmousedown="javascript:savePageAction(this.id);" id="associated" styleClass="wpdButton" tabindex="8" action="#{roleBackingBean.saveTaskAssociation}"> </h:commandButton>
											</TD>
											
										</TR>
									</TBODY>
									
<!--	End of Page data	-->
														
					</TABLE>
					<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
								<TR>
									<TD></TD>
									<TD align="right"></TD>
								</TR>
							</TABLE>
							
						<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar" style="position:relative; cursor:hand;">Module Configuration</DIV>
							<DIV id="panel2Content" class="tabContentBox" style="position:relative;font-size:10px;"><BR>
							<TABLE cellpadding="0" cellspacing="0" border="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv" style="width:97%;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="99%">
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
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv" style="height:252px; overflow:auto; width:97%;">
									<h:dataTable headerClass="dataTableHeader" id="searchResultTable" var="singleValue" 
									cellpadding="3" cellspacing="1" bgcolor="#cccccc" rendered="#{roleBackingBean.associatedTaskList != null}" 
									value="#{roleBackingBean.associatedTaskList}" rowClasses="dataTableEvenRow,dataTableOddRow" border="0" width="99%">
										
										<h:column>
											<h:inputHidden id="taskID" value="#{singleValue.taskId}"></h:inputHidden>
											<h:inputHidden id="rolModAssnId" value="#{singleValue.moduleId}"></h:inputHidden>
											<h:inputHidden id="taskName" value="#{singleValue.taskName}"></h:inputHidden>
											<h:outputText id="taskNameTxt" value="#{singleValue.taskName}"></h:outputText>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
										</h:column>
										<h:column>
											<h:selectBooleanCheckbox id="deleteChkBox" onclick="enableDisableDelete('roleAssociatedTaskForm:searchResultTable',1,0,'roleAssociatedTaskForm:deleteButton');" > </h:selectBooleanCheckbox>
										</h:column>
										
									</h:dataTable></DIV>
									</TD>
						
						<h:commandLink id="deleteAssociation" style="display:none; visibility: hidden;" action="#{roleBackingBean.deleteTaskAssociation}"> 
                                            <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>	
						<h:commandLink id="saveLink" style="display:none; visibility: hidden;" action="#{roleBackingBean.saveTaskAssociation}"> 
                                         <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>		
								</TR>
								<TR>
								<TD width="684"><h:commandButton id="deleteButton" styleClass="wpdButton" value="Delete"  onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> 
									</TD>
								</TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->
<h:inputHidden id="roleID" value="#{roleBackingBean.roleId}"></h:inputHidden>
<h:inputHidden id="selectedTaskId" value="#{roleBackingBean.selectedTaskId}"></h:inputHidden>
<h:inputHidden id="hiddenTxt" value="#{roleBackingBean.taskTxt}"></h:inputHidden>
<h:inputHidden id="panelData"   />
<h:inputHidden id="inputTextData"  />
<h:inputHidden id="duplicateData" value="#{moduleBackingBean.duplicateData}"/>
<h:inputHidden id="selectedTaskIds" value="#{roleBackingBean.selectedTaskIds}"></h:inputHidden>
<h:inputHidden id="roleIdHidden" value="#{roleBackingBean.roleIdHidden}"></h:inputHidden>
<!-- End of hidden fields  -->

				
			</TD>
		</TR>
		</TABLE>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	
</h:form></TD></TR></TABLE></hx:scriptCollector></BODY>
</f:view>
<!-- space for script -->

<script>
IGNORED_FIELD1='roleAssociatedTaskForm:duplicateData';
IGNORED_FIELD2='roleAssociatedTaskForm:panelData';	
IGNORED_FIELD3='roleAssociatedTaskForm:inputTextData';	

	var number = Math.random();
	copyHiddenToDiv_ewpd('roleAssociatedTaskForm:txtTask','TaskDiv',3,2 );

	enableDisableDelete('roleAssociatedTaskForm:searchResultTable',1,0,'roleAssociatedTaskForm:deleteButton');
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('roleAssociatedTaskForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','50%:50%');		
			setColumnWidth('roleAssociatedTaskForm:searchResultTable','50%:50%');
			var  tableObj = getObj('roleAssociatedTaskForm:searchResultTable');
				if(tableObj.rows.length>9){
					syncTables('resultHeaderTable','roleAssociatedTaskForm:searchResultTable');
				}	
				else{
					
					var relTblWidth = document.getElementById('roleAssociatedTaskForm:searchResultTable').offsetWidth;
					document.getElementById('resultHeaderTable').width = relTblWidth+ 'px';
					document.getElementById('roleAssociatedTaskForm:searchResultTable').width = relTblWidth+ 'px';
					setColumnWidth('resultHeaderTable','50%:50%');		
					setColumnWidth('roleAssociatedTaskForm:searchResultTable','50%:50%');
					}			
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}

	function syncTables(){
			var relTblWidth = document.getElementById('roleAssociatedTaskForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth-17 + 'px';
			setColumnWidth('resultHeaderTable','49%:51%');		
			setColumnWidth('roleAssociatedTaskForm:searchResultTable','49%:51%');
		}

	function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'roleAssociatedTaskForm:associated'){
		var tableObject = document.getElementById('roleAssociatedTaskForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('roleAssociatedTaskForm:searchResultTable');
			if(document.getElementById('roleAssociatedTaskForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('roleAssociatedTaskForm:saveLink');	
				}
			}else{
				submitLink('roleAssociatedTaskForm:saveLink');
			}
		}else{
			submitLink('roleAssociatedTaskForm:saveLink');
		}
	}else if (buttonId == 'roleAssociatedTaskForm:deleteButton'){
			
				if(confirmDelete()){
							submitLink('roleAssociatedTaskForm:deleteAssociation');	
				}
			
		}
			
	}	

	function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Task?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('roleAssociatedTaskForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
		delTaskId = getObj('roleAssociatedTaskForm:selectedTaskIds');
		roleId= getObj('roleAssociatedTaskForm:roleIdHidden');
		roleId.value=tableObj.rows[0].cells[0].children[1].value;
		delTaskId.value = '';
		var taskCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			
			if(cur_row.cells[1].children[0].checked) {
				if(taskCount != 0) {
					delTaskId.value += '~';
				 }
				delTaskId.value += cur_row.cells[0].children[0].value;
				taskCount ++;
			}
		}
		if(taskCount > 0)
			return true;
		return false;
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

//checkForOnLoadData();
function checkForOnLoadData(){

	var tableObject = document.getElementById('roleAssociatedTaskForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('roleAssociatedTaskForm:searchResultTable');
			document.getElementById('roleAssociatedTaskForm:panelData').value = onLoadPanelData;
		}
	}
	if(null != document.getElementById('roleAssociatedTaskForm:txtTask')){
		var inputTexts = checkForTextData();
		document.getElementById('roleAssociatedTaskForm:inputTextData').value = inputTexts;
	}

}

function checkForTextData(){
	var inputTexts = document.getElementById('roleAssociatedTaskForm:txtTask').value; 
	return inputTexts;

}	

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="roleTaskConfigInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('roleAssociatedTaskForm:duplicateData').value == ''){
		document.getElementById('roleAssociatedTaskForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForOnLoadData();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('roleAssociatedTaskForm:duplicateData').value;
	}
</script>
</HTML>
