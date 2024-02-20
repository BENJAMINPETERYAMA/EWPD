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

<TITLE>Configure Role</TITLE>
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
	<table width=99%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<h:inputHidden id="dummy" value="#{roleBackingBean.configureBreadCrumbText}" />
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="roleConfigurationForm">
					<table width="99%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
								<jsp:include
								page="../role/roleConfigTree.jsp" />
						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
											 	<w:message value="#{roleBackingBean.validationMessages}"></w:message> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="60%">
		          							<table width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td align="left" width="2"><img src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
													<td class="tabNormal" width="50%"> <h:commandLink action="#{roleBackingBean.loadEditPage}" id="genId" onmousedown="javascript:navigatePageAction(this.id);">
														<h:outputText value=" General Information"/></h:commandLink> </td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
		              							
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<TD class="tabActive" width="50%"><h:commandLink action="#{roleBackingBean.loadRoleConfiguration}">
																<h:outputText id="dataDomainTable" value="Authorized Modules" /> </h:commandLink></TD> 
										
				                					<TD align="right" width="2"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</table>
		          						</td>
										<td width="63%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="99%">
									<TBODY>
										<TR>
											<TD colspan=3>
												<BR>
									<TABLE width="102%" border="0">
										<TR>
											<TD width="18%"><h:outputText id="moduleLabel" value="Module"
												styleClass="mandatoryNormal" /></TD>
											<TD width="19%">
											<DIV id="moduleDiv" class="selectDataDisplayDiv"></DIV>
											</TD>
											<TD width="592"><h:commandButton alt="moduleName"
												id="moduleNameButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../popups/moduleRolePopUp.jsp'+getUrl()+'?'+ 'date='+currentTime,'moduleDiv','roleConfigurationForm:selectedModuleId',2,1);
															return false;"
												tabindex="1">
											</h:commandButton> <h:inputHidden id="selectedModuleId"
												value="#{roleBackingBean.selectedModuleId}"></h:inputHidden>
											</TD>
										</TR>
									</TABLE></TR>
                                                                                                                   
                                                                          

										

										<TR>
											<TD width="30%">
												<h:commandButton  value="Save" onclick="unsavedDataFinder(this.id);return false;" onmousedown="javascript:savePageAction(this.id);" id="associated" styleClass="wpdButton" tabindex="8" action="#{roleBackingBean.saveRoleModuleAssociation}"> </h:commandButton>
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
								style="position:relative; cursor:hand;">Authorized Modules</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv" style="width:97%;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="99%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD width="50%"><h:outputText
													value="Module"></h:outputText></TD>
												<TD width="50%"><h:outputText
													value="Delete"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:97%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{roleBackingBean.searchResultList != null}"
										value="#{roleBackingBean.searchResultList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="99%">
										<h:column>
											<h:outputText id="moduleNm" value="#{singleValue.moduleName}"></h:outputText>
											<h:inputHidden id="moduleID" value="#{singleValue.moduleId}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:selectBooleanCheckbox id="deleteChkBox" onclick="enableDisableDelete('roleConfigurationForm:searchResultTable',1,0,'roleConfigurationForm:deleteButton');" > </h:selectBooleanCheckbox>
										</h:column>
									</h:dataTable></DIV>
									</td>
								<h:commandLink id="deleteAssociation" style="display:none; visibility: hidden;" action="#{roleBackingBean.deleteModuleAssociation}"> 
                                            <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>
								<h:commandLink id="saveLink" style="display:none; visibility: hidden;" action="#{roleBackingBean.saveRoleModuleAssociation}"> 
                                         <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>	
								</TR>
								<TR>
								<TD>
									<h:commandButton id="deleteButton" styleClass="wpdButton" value="Delete"  onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> 
								</TD>
						   </TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->
<h:inputHidden id="panelData"  />
<h:inputHidden id="inputTextData" />
<h:inputHidden id="duplicateData" value="#{roleBackingBean.duplicateData}"/>
<h:inputHidden id="selectedRoleModuleId" value="#{roleBackingBean.associatedModuleId}"></h:inputHidden>
<h:inputHidden id="selectedModuleIds" value="#{roleBackingBean.selectedModuleIds}"></h:inputHidden>
<!-- End of hidden fields  -->

				
			</fieldset></TD>
		</tr>
		</table>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	
</td></tr></table> </h:form> </BODY>
</f:view>
<!-- space for script -->

<script>
IGNORED_FIELD1='roleConfigurationForm:duplicateData';	
IGNORED_FIELD2='roleConfigurationForm:panelData';	
IGNORED_FIELD3='roleConfigurationForm:inputTextData';	
	function moduleSelected(){
		var retvalue = ewpdModalWindow_ewpd('../popups/modulePopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'roleModuleValue='+document.getElementById('roleConfigurationForm:moduleTxtArea').value, 'roleConfigurationForm:moduleTxtArea','roleConfigurationForm:moduleTxtHidden',2,1);	
	}
		
	initialize();
	var number = Math.random();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('roleConfigurationForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','50%:50%');		
			setColumnWidth('roleConfigurationForm:searchResultTable','50%:50%');	
			var  tableObj = getObj('roleConfigurationForm:searchResultTable');
				if(tableObj.rows.length>9){
					syncTables('resultHeaderTable','roleConfigurationForm:searchResultTable');
				}	
				else{
					
					var relTblWidth = document.getElementById('roleConfigurationForm:searchResultTable').offsetWidth;
					document.getElementById('resultHeaderTable').width = relTblWidth+ 'px';
					document.getElementById('roleConfigurationForm:searchResultTable').width = relTblWidth+ 'px';
					setColumnWidth('resultHeaderTable','50%:50%');		
					setColumnWidth('roleConfigurationForm:searchResultTable','50%:50%');
					}	
		}else{
			document.getElementById('panel2Header').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}
copyHiddenToDiv_ewpd('roleConfigurationForm:selectedModuleId','moduleDiv',2,1 );

function syncTables(){
			var relTblWidth = document.getElementById('roleConfigurationForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth-17 + 'px';
			setColumnWidth('resultHeaderTable','49%:51%');		
			setColumnWidth('roleConfigurationForm:searchResultTable','49%:51%');
		}
enableDisableDelete('roleConfigurationForm:searchResultTable',1,0,'roleConfigurationForm:deleteButton');

function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'roleConfigurationForm:associated'){
		var tableObject = document.getElementById('roleConfigurationForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('roleConfigurationForm:searchResultTable');
			if(document.getElementById('roleConfigurationForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('roleConfigurationForm:saveLink');	
				}
			}else{
				submitLink('roleConfigurationForm:saveLink');
			}
		}else{
			submitLink('roleConfigurationForm:saveLink');
		}
	}else if (buttonId == 'roleConfigurationForm:deleteButton'){
				if(confirmDelete()){
							submitLink('roleConfigurationForm:deleteAssociation');	
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
		var message = "Are you sure you want to detach the selected Module?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('roleConfigurationForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
		delModuleId = getObj('roleConfigurationForm:selectedModuleIds');
		delModuleId.value = '';
		var moduleCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			
			if(cur_row.cells[1].children[0].checked) {
				if(moduleCount != 0) {
					delModuleId.value += '~';
				 }
				delModuleId.value += cur_row.cells[0].children[1].value;
				moduleCount ++;
			}
		}
		if(moduleCount > 0)
			return true;
		return false;
	}

	checkForOnLoadData();
   function checkForOnLoadData(){

	var tableObject = document.getElementById('roleConfigurationForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('roleConfigurationForm:searchResultTable');
			document.getElementById('roleConfigurationForm:panelData').value = onLoadPanelData;
		}
	}
	if(null != document.getElementById('roleConfigurationForm:selectedModuleId')){
		var inputTexts = checkForTextData();
		document.getElementById('roleConfigurationForm:inputTextData').value = inputTexts;
	}

   }

  function checkForTextData(){
	var inputTexts = document.getElementById('roleConfigurationForm:selectedModuleId').value; 
	return inputTexts;

	}


			

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="roleDetailPrint" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('roleConfigurationForm:duplicateData').value == ''){
		document.getElementById('roleConfigurationForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		//checkForOnLoadData();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('roleConfigurationForm:duplicateData').value;
	}
</script>
</HTML>












