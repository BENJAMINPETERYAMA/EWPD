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

<TITLE>ComponentAssociation</TITLE>
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
<!-- WAS 7.0 Changes - Hidden id hiddenInit value loaded using binding instead of value -->

<f:view>
	<BODY onload="return refresh();";  onkeypress="return submitOnEnterKey('productForm:saveButton');"; >
	<h:inputHidden id="Hidden"
		value="#{productComponentAssociationBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">

 		<h:inputHidden id="hidden1"
			binding="#{productComponentAssociationBackingBean.hiddenInit}"></h:inputHidden>

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
								<td width="200" id="tab1">
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
								<td width="200" id="tab2">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="  Component Association" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab3">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productAdminAssociationBackingBean.loadComponent}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="adminId">
											<h:outputText value="Admin Options" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab4">
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
								<td width="200" id="tab5">
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
									value="Component*" /></td>
								<td width="25%" align="center">
								<div id="componentDataDiv" align="left"
									class="selectDataDisplayDiv"></div>
								</td>
								<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;<h:commandButton
									image="../../images/select.gif" alt="Select"
									onclick="loadComponentPopUp();return false;"
								
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
								<fieldset
									style="margin-left:6px;margin-right:4px;padding-bottom:1px;padding-top:6px;width:100%;height:100%;">
								<DIV id="noBenefitComp" style="text-align:left;">
									<h:outputText value="No Benefit Components Associated." 
                                      styleClass="dataTableColumnHeader" /></DIV>
								<div id="dataTableDiv"
									style="height:292px;width:100%;">
								<table border="0" cellpadding="0" cellspacing="0">
									
									<tr>
										<td>
										<table bgcolor="#cccccc" width="100%" cellpadding="3"
											cellspacing="1" id="headert">
									
										<TR>
											<TD colspan="2" bgcolor="#cccccc" align="left"><strong>Associated Benefit Components</strong></TD>
										</TR>
											<tr>
												<td width="15%" class="dataTableOddRow" align="left"><h:commandButton
													type="submit" value="Update" styleClass="wpdButton" onmousedown="javascript:savePageAction(this.id);"
													id="updateButton"
													rendered="#{productComponentAssociationBackingBean.emptyList}"
													action="#{productComponentAssociationBackingBean.updateBenefitComponents}"></h:commandButton>
												</td>
												<td width="57%" class="dataTableColumnHeader"><h:outputText
													value="Benefit Component" styleClass="outputText" />
												<td width="28%" class="dataTableColumnHeader"><h:outputText
													value="Delete" styleClass="outputText" /></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<td>
										<div style="height:231px;overflow:auto;"><h:panelGrid
											id="panelTable" columns="3" width="665"
											styleClass="outputText" cellpadding="1" cellspacing="1"
											bgcolor="#cccccc"
											binding="#{productComponentAssociationBackingBean.panel}"
											rowClasses="dataTableEvenRow,dataTableOddRow">
										</h:panelGrid></div>
										</td>
									</tr>
									<tr>
									<td valign="top">
										<h:commandButton id="deleteButton" 
										styleClass="wpdButton" value="Delete" 
										onclick="unsavedDataFinder(this.id);return false;"
										></h:commandButton>
									</td>
								  </tr>
								</table>
								</div>
								</fieldset>
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
									value="#{productComponentAssociationBackingBean.checkin}"
									>
								</h:selectBooleanCheckbox></td>
								<td align="left"><h:outputText id="checkin" value="Check In" /></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<TD>:</TD>
										<td><h:outputText id="state"
											value="#{productComponentAssociationBackingBean.stateOfObject}" /></td>
										<h:inputHidden id="stateOfObject"
											value="#{productComponentAssociationBackingBean.stateOfObject}"></h:inputHidden>

									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<TD>:</TD>
										<td><h:outputText id="status"
											value="#{productComponentAssociationBackingBean.statusOfObject}" />
										</td>
										<h:inputHidden id="statusOfObject"
											value="#{productComponentAssociationBackingBean.statusOfObject}"></h:inputHidden>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<TD>:</TD>
										<td><h:outputText id="version"
											value="#{productComponentAssociationBackingBean.versionOfObject}" />
										</td>
										<h:inputHidden id="versionOfObject"
											value="#{productComponentAssociationBackingBean.versionOfObject}"></h:inputHidden>
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
									action="#{productComponentAssociationBackingBean.done}">
								</h:commandButton></td>
								<td width="90%"></td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenProductType"
					value="#{productComponentAssociationBackingBean.mandateType}"></h:inputHidden>
				<h:inputHidden id="rowId"
					value="#{productComponentAssociationBackingBean.rowId}"></h:inputHidden>
				<h:inputHidden id="componentData"
					value="#{productComponentAssociationBackingBean.benefitComponentString}"></h:inputHidden>
				<h:inputHidden id="hasValErrors"
					value="#{productComponentAssociationBackingBean.hasValidationErrors}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{productComponentAssociationBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="panelData"  />
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productComponentAssociationBackingBean.deleteBenefitComponent}"><f:verbatim /></h:commandLink>
		<%-- 	<h:commandLink id="refresh"
					style="display:none; visibility: hidden;"
					action="#{productComponentAssociationBackingBean.getHiddenInit}"><f:verbatim /></h:commandLink>--%>	
				<h:commandLink id="savelink"
					style="display:none; visibility: hidden;"
					action="#{productComponentAssociationBackingBean.saveBenefitComponents}"><f:verbatim /></h:commandLink>	
				<h:inputHidden id="hiddenProductKey"
					value="#{productComponentAssociationBackingBean.productEntityId}"></h:inputHidden>



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
	name="currentPrintPage" value="productComponentAssociation" /></form>
<script>
IGNORED_FIELD1='formName:duplicateData';
IGNORED_FIELD2='formName:panelData';
enableDisableDelete('formName:panelTable', 2, 0, 'formName:deleteButton');

function refresh(){
	submitLink('formName:refresh');
}

//alert("inside norm refresh");
//submitLink('formName:refresh');
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
	var message = 'Are you sure you want to delete the benefit component?';
		if (confirm(message) ){
			return true;
		} else
		return false;
	}

setColumnWidth('formName:panelTable','15%:57%:28%');
copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',2,2); 

function hideTable(){
	var tableObject = document.getElementById('formName:panelTable');
	if(tableObject.rows.length > 0){
		var divBnftDefn = document.getElementById('noBenefitComp');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('dataTableDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noBenefitComp').style.visibility = "visible";
	}
}

hideTable();
i = document.getElementById("formName:hiddenProductType").value;
if(i=='MANDATE')
{
tab4.style.display='none';
tab3.style.display='none';
}else{
tab3.style.display='';
tab4.style.display='';
}

changeToReadOnly();

function changeToReadOnly(){
	var tag = document.getElementsByTagName('input');	
	var tagName;
	var requiredTag;
	var tagValue;
	var count = 0;
	for (i=0; i<tag.length; i++){
		tagName = tag[i].name;
		requiredTag = tagName.match('formName:hiddenBnftComponentName');
		
		if(requiredTag !=null && requiredTag !='undefined') {		
		tagValue = 	document.getElementById(tagName).value;				
		
			if(requiredTag == 'formName:hiddenBnftComponentName' && 
					(tagValue == 'GENERAL BENEFITS' || tagValue == 'GENERAL PROVISIONS' || tagValue == 'SUPPLEMENTAL BENEFITS')){
				document.getElementById('formName:sequence' + tagName.substring('formName:hiddenBnftComponentName'.length)).readOnly = true;
				count ++;
				if(count == 3){
					return; // To stop the looping, once 'GENERAL BENEFITS' and 'GENERAL PROVISIONS' are changed to read only
				}
			}
		}			
	}
}


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
}
function getPanelData(list){
	var tagNames = list.split(',');
	var dataOnScreen = "";
	var tableObject = document.getElementById(tagNames[0]);
	var rows = tableObject.rows.length;
	if(rows >0){
		var columns = tableObject.rows[0].cells.length;
		for(var i=0;i<rows;i++){
			for(var j=0;j<columns-1;j++){
				if(null != tableObject.rows[i].cells[j].children[0]){
					dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
				}
			}
		}
		return dataOnScreen;
	}else
		return dataOnScreen;
}
function unsavedDataFinder(objectId){
	var buttonId = objectId;
	var panelData = getPanelData('formName:panelTable');
	if(document.getElementById('formName:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			if(buttonId == 'formName:saveButton'){
				submitLink('formName:savelink');	
			}
			else if(buttonId == 'formName:deleteButton'){
				if(deleteConfirm()){
					submitLink('formName:deleteLink');	
				}
			}
		}
	}
	else{
		if(buttonId == 'formName:saveButton'){
				submitLink('formName:savelink');	
		}
		else if(buttonId == 'formName:deleteButton'){
			if(deleteConfirm()){
					submitLink('formName:deleteLink');	
			}
		}
	}
}
function loadComponentPopUp(){
var productkey = document.getElementById('formName:hiddenProductKey').value;
var titleName = 'Benefit Component Popup';
ewpdModalWindow_ewpd('../popups/searchPopup.jsp'+getUrl()+'?entityId='+productkey+'&headerName=Benefit Component&queryName=getValidComponentsForProduct&titleName=Benefit Component Popup&temp='+Math.random(),'componentDataDiv','formName:componentData',2,2)
								;return false;
}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == ''){
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
</script>
</HTML>




