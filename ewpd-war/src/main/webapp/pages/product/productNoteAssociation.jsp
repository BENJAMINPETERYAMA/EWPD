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

<TITLE>Note Attachment</TITLE>
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
	<h:inputHidden id="Hidden" ></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="hidden1" ></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="200" valign="top" class="leftPanel">

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
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText value="Notes" /></td>
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
										value="Note*"
										styleClass="#{productNoteAssociationBackingBean.noteValdn?'mandatoryNormal': 'mandatoryError'}" />
									</td>
									<td width="24%" align="center"><h:inputText
										id="noteSearchCriteria" 
									styleClass="formInputField"></h:inputText></td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;<h:commandButton
										image="../../images/autoComplete.gif" alt="Select"
										onclick="noteSelect(); return false;" ></h:commandButton></td>
									<td width="52%" align="left">
								</TR>
								<TR id="divRow" style="display:none;">
									<td width="16%" align="left"></TD>
									<td width="24%" align="center">
									<DIV id="selectedNotesDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
								</TR>
								<TR>
									<td height="2"></td>
								</TR>
								<tr>
									<td height="4"></td>
								</tr>
								<TR>
									<td width="16%" align="left">&nbsp;&nbsp;<h:commandButton
										styleClass="wpdButton" type="submit" value="Attach Notes"
										id="saveButton"
										action="#{productNoteAssociationBackingBean.saveProductNotes}"
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
										style="margin-left:6px;margin-right:4px;padding-bottom:1px;padding-top:6px;width:100%">
									<DIV id="noBenefitComp"
										style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:left;color:#000000; height:292px;
						background-color:#FFFFFF;">No Notes Attached.</DIV>
									<div id="dataTableDiv"
										style="height:100%; width:100%;">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
											<table bgcolor="#cccccc" width="100%" cellpadding="3"
												cellspacing="1" id="headert">
										<TR>
											<TD colspan="2" bgcolor="#cccccc" align="left"><strong>Associated Notes</strong></TD>
										</TR>
												<tr>
													<td width="70%" class="dataTableColumnHeader"><h:outputText
														value="Name" styleClass="outputText" /></td>
													<td width="15%" class="dataTableColumnHeader"><h:outputText
														value="View" styleClass="outputText" /></td>
													<td width="15%" class="dataTableColumnHeader"><h:outputText
														value="Delete" styleClass="outputText" /></td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td>
											<div style="height:245px;overflow:auto;"><h:panelGrid
												id="panelTable" width="665"
												styleClass="outputText" cellpadding="2" cellspacing="1"
												bgcolor="#cccccc"
												binding="#{productNoteAssociationBackingBean.panel}"
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
													onclick = "deleteConfirm(); return false;">
												</h:commandButton> 
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
										value="#{productNoteAssociationBackingBean.checkin}"
										>
									</h:selectBooleanCheckbox></td>
									<td align="left"><h:outputText id = "check_Id" value="Check In" /></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<TD>:</TD>
											<td><h:outputText id = "stateId"
												value="#{productNoteAssociationBackingBean.state}" /></td>
											<h:inputHidden id="stateHidden"
												value="#{productNoteAssociationBackingBean.state}"></h:inputHidden>

										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<TD>:</TD>
											<td><h:outputText id="statusId"
												value="#{productNoteAssociationBackingBean.status}" /> <h:inputHidden
												id="statusHidden"
												value="#{productNoteAssociationBackingBean.status}"></h:inputHidden>
											</td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<TD>:</TD>
											<td><h:outputText id="versiontype"
												value="#{productNoteAssociationBackingBean.version}" /> <h:inputHidden
												id="versionHidden"
												value="#{productNoteAssociationBackingBean.version}"></h:inputHidden>
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
									<td width="6%">&nbsp;&nbsp;<h:commandButton value="Done"
										styleClass="wpdButton" id="doneButton" 
										action="#{productNoteAssociationBackingBean.done}">
									</h:commandButton></td>
									<td width="90%"></td>
								</tr>
							</TBODY>
						</TABLE>
						</TD>
					</TR>

				</table>
				<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!-- Space for hidden fields -->
				<h:inputHidden id="rowId"
					value="#{productNoteAssociationBackingBean.rowId}"></h:inputHidden>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<h:inputHidden id="componentData"
					value="#{productNoteAssociationBackingBean.noteString}"></h:inputHidden>
				<h:inputHidden id="hasValErrors"
					value="#{productNoteAssociationBackingBean.hasValidationErrors}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{productNoteAssociationBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productNoteAssociationBackingBean.deleteNotes}">
					<f:verbatim />
				</h:commandLink>
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
	name="currentPrintPage" value="productNotes" /></form>
<script>
IGNORED_FIELD1='formName:duplicateData';
var entityType = 'ATTACHPRODUCT';
var lookUpAction = 2;
 var noteIdForView; 
 var noteNameForView; 

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
			var message = 'Are you sure you want to detach the selected notes?';
				if (confirm(message) ){
					submitLink('formName:deleteLink');
					return true;
				} else
				return false;
			}
setColumnWidth('formName:panelTable','70%:15%:15%');
// copyHiddenToDiv_ewpd('formName:componentData','componentDataDiv',2,2); 
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
	function noteSelect(){
		var noteName= document.getElementById('formName:noteSearchCriteria').value;
		ewpdModalWindow_ewpd('../popups/notesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=2&temp='+Math.random()+'&noteSearchCriteria='+noteName,'selectedNotesDiv','formName:componentData',3,1);
		showDiv();
	}
	function callNoteSelect(){
	if(window.event.keyCode == 13){
		noteSelect();
		return false;
	}else{
		return true;
	}
	}
hideTable();
showDiv();
copyHiddenToDiv_ewpd("formName:componentData", "selectedNotesDiv", 3, 1);
function showDiv(){
var selNote = document.getElementById("formName:componentData").value ;
	if(null==selNote || ""==selNote)
		divRow.style.display='none';
	else
		divRow.style.display='';
}

if(document.getElementById('formName:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidation.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('formName:hasValErrors').value = 'false';
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
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == '')
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
</script>
</HTML>




