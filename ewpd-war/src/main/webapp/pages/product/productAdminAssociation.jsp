
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>AdminAssociation</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('formName:saveButton');">
	<h:inputHidden id="Hidden"
		value="#{productAdminAssociationBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1"
			value="#{productAdminAssociationBackingBean.hiddenInit}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="141" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productGeneralInformationBackingBean.loadGeneralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="genId">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentAssociationBackingBean.loadComponent}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="compId">
											<h:outputText value="Component Association" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Admin Options" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productNoteAssociationBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="noteId">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.displayDenialAndExclusionTab}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="ruleId">
											<h:outputText value="Rules" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td width="16%" align="left">&nbsp;&nbsp;<h:outputText
										value="Admin Options*" /></td>
									<td width="24%" align="center">
									<div id="adminDataDiv" align="left"
										class="selectDataDisplayDiv"></div>
									</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;<h:commandButton
										image="../../images/select.gif" alt="Select"
										onclick="loadAdminOptionPopUp();return false;"
										></h:commandButton></td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td height="2"></td>
								</TR>
								<TR>
									<td width="16%" align="left">&nbsp;&nbsp;<h:commandButton
										styleClass="wpdButton" type="submit" value="Save"
										id="saveButton"
										onclick="unsavedDataFinder(this.id);return false;"	
										></h:commandButton></td>
									<td width="24%" align="center">&nbsp;</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;</td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td colspan="4">

									<DIV id="noAdminDiv"
										style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:left;color:#000000;
						background-color:#FFFFFF;">No Admin Options Associated.</DIV>
									<div id="dataTableDiv" style="height:292px;width:100%;">
									<fieldset
										style="margin-left:6px;margin-right:4px;padding-bottom:1px;padding-top:6px;width:100%">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
											<table bgcolor="#cccccc" width="100%" cellpadding="3"
												cellspacing="1" id="headert">
												<TR>
													<TD colspan="2" bgcolor="#cccccc" align="left"><strong>Associated
													Admin Options</strong></TD>
												</TR>
												<tr>
													<td width="15%" class="dataTableOddRow" align="left"><h:commandButton
														type="submit" value="Update" styleClass="wpdButton"
														id="updateButton"
														rendered="#{productAdminAssociationBackingBean.adminList != null}"
														onmousedown="javascript:savePageAction(this.id);"
														action="#{productAdminAssociationBackingBean.updateAdminOptions}"></h:commandButton>
													</td>
													<td width="57%" class="dataTableColumnHeader"><h:outputText
														value="Admin Options" styleClass="outputText" /></td>
													<td width="28%" class="dataTableColumnHeader"><h:outputText
														value="Delete" styleClass="outputText" /></td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<div style="height:245px;overflow:auto;"><h:panelGrid
												id="panelTable" columns="3" width="665"
												styleClass="outputText" cellpadding="1" cellspacing="1"
												bgcolor="#cccccc"
												binding="#{productAdminAssociationBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid></div>
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<td>
												<h:commandButton id="deleteButton" styleClass="wpdbutton"
													value="Delete" alt="Delete"
													onclick="unsavedDataFinder(this.id);return false;"	>
												</h:commandButton> 
											</td>
										</tr>
									</table>
									</fieldset>
									</div>

									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</TBODY>
						</TABLE>

						<!--	End of Page data	--></fieldset>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="4%">&nbsp;&nbsp;<h:selectBooleanCheckbox
										id="checkall"
										value="#{productAdminAssociationBackingBean.checkin}"
										>
									</h:selectBooleanCheckbox></td>
									<td align="left"><h:outputText id ="checkId" value="Check In" /></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<TD>:</TD>
											<td><h:outputText id="stateId"
												value="#{productAdminAssociationBackingBean.state}" /></td>
											<h:inputHidden id="stateHidden"
												value="#{productAdminAssociationBackingBean.state}"></h:inputHidden>

										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<TD>:</TD>
											<td><h:outputText id = "statusId"
												value="#{productAdminAssociationBackingBean.status}" /> <h:inputHidden
												id="statusHidden"
												value="#{productAdminAssociationBackingBean.status}"></h:inputHidden>
											</td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<TD>:</TD>
											<td><h:outputText id="versiontype"
												value="#{productAdminAssociationBackingBean.version}" /> <h:inputHidden
												id="versionHidden"
												value="#{productAdminAssociationBackingBean.version}"></h:inputHidden>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</FIELDSET>
						<BR>
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<tr>
									<td width="4%">&nbsp;
									<h:commandButton value="Done"
										styleClass="wpdButton" id="doneButton" 
										action="#{productAdminAssociationBackingBean.done}">
									</h:commandButton></td>
									<td width="90%"></td>
								</tr>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="txtData" ></h:inputHidden>
				<h:inputHidden id="panelData" value="#{productAdminAssociationBackingBean.panelData}" />
				<h:inputHidden id="rowId"
					value="#{productAdminAssociationBackingBean.rowId}"></h:inputHidden>
				<h:inputHidden id="adminData"
					value="#{productAdminAssociationBackingBean.adminString}"></h:inputHidden>
					<h:inputHidden id="hasValErrors"
						value="#{productAdminAssociationBackingBean.hasValidationErrors}"></h:inputHidden>
				
				<h:inputHidden id="duplicateData"
					value="#{productAdminAssociationBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productAdminAssociationBackingBean.deleteAdminOption}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="saveLink"
					style="display:none; visibility: hidden;"
					action="#{productAdminAssociationBackingBean.saveProductAdmin}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="hiddenProductKey"
					value="#{productAdminAssociationBackingBean.productEntityId}"></h:inputHidden>


				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productAdminOption" /></form>
<script>

IGNORED_FIELD1='formName:duplicateData';
IGNORED_FIELD2='formName:panelData';

copyHiddenToDiv_ewpd('formName:adminData','adminDataDiv',2,2); 

if(getObj('formName:panelTable') != null) {
	if (getObj('formName:panelTable').rows.length > 0)
		syncTables('headert','formName:panelTable');
}
function getRow(row){
	document.getElementById('formName:rowId').value = row;
	submitLink('formName:deleteLink');
	return true;
}

function deleteConfirm(){
	var message = 'Are you sure you want to delete?';
	if (confirm(message) ){
		submitLink('formName:deleteLink');
		return true;
	} else
	return false;
}
changeButton();
function changeButton(){
	var flag = validateCheckBox();
	document.getElementById("formName:deleteButton").disabled = !flag;
}
function validateCheckBox(){
	var isChecked = false;
	var tags = document.getElementsByTagName('input');
	for(i=0; i < tags.length; i++){
		tagType = tags[i].type;
		if(tagType == 'checkbox' && tags[i].checked == true){
			isChecked = true;
			break;
		}
	}
	return isChecked;
}

setColumnWidth('formName:panelTable','15%:57%:28%');

function hideTable(){
	var tableObject = document.getElementById('formName:panelTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noAdminDiv');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('dataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
	}
}
hideTable();
if(document.getElementById('formName:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidation.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('formName:hasValErrors').value = 'false';
}
//checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('formName:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('formName:panelTable');
		document.getElementById('formName:panelData').value = onLoadPanelData;
	}
	var txtData = document.getElementById('formName:adminData').value;
	document.getElementById('formName:txtData').value = txtData;
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
	}else
		return dataOnScreen;
}
function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'formName:deleteButton'){
		txtData = document.getElementById('formName:adminData').value;
		if(txtData != document.getElementById('formName:txtData').value){
			var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
			if (retValue){
				deleteConfirm();
			}
		}else{
			deleteConfirm();
		}
	}
	else if(buttonId == 'formName:saveButton'){
		var tableObject = document.getElementById('formName:panelTable');
		if(tableObject.rows.length >0){
			var panelData = getPanelData('formName:panelTable');
			if(document.getElementById('formName:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('formName:saveLink');	
				}
			}else{
			submitLink('formName:saveLink');	
			}
		}else{
			submitLink('formName:saveLink');	
		}
	}
}
function loadAdminOptionPopUp(){
var productkey = document.getElementById('formName:hiddenProductKey').value;
var titleName = 'Admin Options Popup';
ewpdModalWindow_ewpd('../popups/searchPopup.jsp'+getUrl()+'?entityId='+productkey+'&headerName=Admin Options&queryName=retrieveAllLatestAdminOptons&titleName=Admin Options Popup&temp='+Math.random(),'adminDataDiv','formName:adminData',2,2)
								;return false;
}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == ''){
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
	}
</script>
</HTML>




