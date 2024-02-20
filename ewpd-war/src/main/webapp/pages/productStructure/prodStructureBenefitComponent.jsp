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

<TITLE>Product Structure Benefit Component</TITLE>
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
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</tr>
		<h:inputHidden id="loadCompHidden"
			value="#{productStructureBenefitComponentBackingBean.loadComponent}" />
		<tr>
			<td><h:form styleClass="form" id="prodStructureForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="290" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

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
						<table width="200" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="100%">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											onmousedown="return false;"
											action="#{productStructureGeneralInfoBackingBean.displayProductStructureGeneralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="thisId">
											<h:outputText value="General Information" />
										</h:commandLink> <%--h:commandLink > <h:outputText value="General Information"/> </h:commandLink--%>
										</td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Benefit Component " /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left: 6px; margin-right: -6px; padding-bottom: 1px; padding-top: 6px; width: 100%">

						<!--	Start of Table for actual Data	-->

						<table width="100%" border="0" cellspacing="0" cellpadding="0">

							<tr>

								<td colspan="2" valign="top" class="ContentArea">

								<fieldset><br>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">

									<tr>
										<td valign="top" width="137"><h:outputText
											value="Components*" /></td>
										<td align="left" width="200">
										<div id="SelectBenefitHeirarchiesDiv"
											class="selectDataDisplayDiv"></div>
										</td>
										<td align="left" valign="top" width="633"><h:commandButton
											image="../../images/select.gif" alt="Select"
											style="cursor:hand;"
											onclick="loadBenefitComponentPopUp();return false;"
											tabindex="1"></h:commandButton></td>
									</tr>
									<tr>
										<td valign="top" width="137"></td>
										<td align="left" width="200"></td>
										<td align="left" valign="top" width="633"></td>
									</tr>
									<tr>
										<td valign="top" width="137"></td>
										<td align="left" width="200"></td>
										<td align="left" valign="top" width="633"></td>
									</tr>
									<tr>
										<td valign="top" width="136"><SPAN
											style="margin-left: 6px; margin-right: 6px;"> <h:commandButton
											value=" Save " styleClass="wpdButton" id="saveButton"
											onclick="unsavedDataFinder(this.id);return false;"
											tabindex="2">
										</h:commandButton> </SPAN></td>
										<td align="left" width="200"></td>
										<td align="left" valign="top" width="633"></td>
									</tr>
									<tr>
										<td width="103">&nbsp;</td>
										<td width="176"></td>
										<td width="590"></td>
									</tr>
								</table>

								<BR>
								<div id="panel2Header"
									style="background-color: #cccccc; z-index: 1; overflow: auto; height: 20px; width: 100%">
								<B> <h:outputText
									value="#{productStructureBenefitComponentBackingBean.title}"></h:outputText>
								</B></div>

								<div id="emptymsg">&nbsp;<h:outputText
									value="No Benefit Components Attached."
									styleClass="dataTableColumnHeader" /></div>
								<table class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0">

									<tr>
										<td>
										<div id="resultHeaderDiv"
											style="background-color: #FFFFFF; z-index: 1; overflow: auto;"><h:panelGrid
											id="displayHeaderTable"
											binding="#{productStructureBenefitComponentBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										</td>
									</tr>
									<tr>
										<td bordercolor="#cccccc">
										<div id="searchResultdataTableDiv"
											style="height: 155px; overflow: auto;"><h:panelGrid
											id="panelTable"
											binding="#{productStructureBenefitComponentBackingBean.panel}"
											rowClasses="dataTableEvenRow,dataTableOddRow">

										</h:panelGrid></div>

										</td>
									</tr>
									<tr>
										<TD></TD>
									</tr>
									<tr>
										<td valign="top"><h:commandButton id="deleteButton"
											tabindex="5" styleClass="wpdButton" value="Delete"
											onclick="unsavedDataFinder(this.id);return false;"></h:commandButton>
										</td>
									</tr>
									<tr>
										<TD></TD>
									</tr>

								</table>

								</fieldset>
								<br />
								<FIELDSET
									style="margin-left: 0px; margin-right: -6px; margin-top: 6px; padding-bottom: 1px; padding-top: 20px; width: 100%">
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td width="15%"><h:selectBooleanCheckbox id="checkIn"
											rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}"
											value="#{productStructureGeneralInfoBackingBean.checkIn}"
											tabindex="6">
										</h:selectBooleanCheckbox><h:outputText value="Check In"
											rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}" /></td>

										<td align="right">
										<table>
											<tr>
												<td>State</td>
												<td>:<h:outputText id="state"
													value="#{productStructureBenefitComponentBackingBean.state}">
												</h:outputText>&nbsp;</td>
												<h:inputHidden id="stateHid"
													value="#{productStructureBenefitComponentBackingBean.state}">
												</h:inputHidden>
											</tr>
											<tr>
												<td>Status</td>
												<td>:&nbsp;<h:outputText id="status"
													value="#{productStructureBenefitComponentBackingBean.status}"></h:outputText>
												</td>
												<h:inputHidden id="statusHid"
													value="#{productStructureBenefitComponentBackingBean.status}"></h:inputHidden>
											</tr>
											<tr>
												<td>Version</td>
												<td>:<h:outputText id="version"
													value="#{productStructureBenefitComponentBackingBean.version}">
												</h:outputText>&nbsp;</td>
											</tr>
											<h:inputHidden id="versionHid"
												value="#{productStructureBenefitComponentBackingBean.version}">
											</h:inputHidden>
										</table>

										</td>
									</tr>
								</table>
								</fieldset>
								<br />
								<TABLE>
									<tr>
										<TD colspan="3"><SPAN
											style="margin-left: 6px; margin-right: 6px;"> <h:commandButton
											value=" Done " id="doneButton" styleClass="wpdButton"
											action="#{productStructureBenefitComponentBackingBean.checkIn}"
											rendered="#{productStructureGeneralInfoBackingBean.checkout!= true}"
											tabindex="7">
										</h:commandButton> </SPAN></TD>
									</tr>
								</TABLE>
								<h:commandLink id="saveToCache"
									style="display:none; visibility: hidden;"
									action="#{productStructureBenefitComponentBackingBean.saveBenefitComponentList}">
									<f:verbatim />
								</h:commandLink> <h:commandLink id="deleteLink"
									action="#{productStructureBenefitComponentBackingBean.deleteBenefitComponent}">
									<f:verbatim></f:verbatim>
								</h:commandLink> <!--	End of Page data	--></TD>
							</TR>
						</table>



						<!-- Space for hidden fields --> <h:inputHidden
							id="hiddenBenefitComponentId"
							value="#{productStructureBenefitComponentBackingBean.hiddenBenefitComponentId}"></h:inputHidden>
						<h:inputHidden id="panelData"  /> <h:inputHidden
							id="txtComponent"
							value="#{productStructureBenefitComponentBackingBean.selectedBenefitComponents}" />
						<h:inputHidden id="hasValErrors"
							value="#{productStructureBenefitComponentBackingBean.hasValidationErrors}"></h:inputHidden>
						<h:inputHidden id="benefitComponentId"
							value="#{productStructureBenefitComponentBackingBean.deleteBenefitComponentId}" />
						<h:inputHidden id="duplicateData"
							value="#{productStructureBenefitComponentBackingBean.duplicateData}" />
						<!-- End of hidden fields  --> 
						</td>
					</tr>
				</table>

				<tr>
					<td><%@ include file="../navigation/bottom.jsp"%></td>
				</tr>
	</table>
	</BODY>
	</h:form>
</f:view>

<script>
IGNORED_FIELD1='prodStructureForm:duplicateData';
//addaed for enabling and disabling delete button
enableDisableDelete('prodStructureForm:panelTable', 2, 0, 'prodStructureForm:deleteButton');
copyHiddenToDiv_ewpd('prodStructureForm:txtComponent','SelectBenefitHeirarchiesDiv',2,2); 
initialize();
//allElementForStructure("init");
function initialize(){
	var tableObject = document.getElementById('prodStructureForm:panelTable');
	if (tableObject!=null ){
	if(tableObject.rows.length != 0) {
		document.getElementById('emptymsg').style.visibility = 'hidden';
		setColumnWidth('prodStructureForm:displayHeaderTable','21%:51%:23%');		
		setColumnWidth('prodStructureForm:panelTable','21%:51%:23%');
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('panel2Header').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
		document.getElementById('prodStructureForm:deleteButton').style.visibility = 'hidden';
	}
	}
	
}

var currentDate = new Date();
var currentTime = currentDate.getTime();
	//setColumnWidth('headerTable','21%:51%:23%');		
	//setColumnWidth('prodStructureForm:bComponentDataTable','21%:51%:23%');	

changeToReadOnly();
function changeToReadOnly(){
	var tag = document.getElementsByTagName('input');

	var tagName;
	var requiredTag;
	var tagValue;
	var count = 0;
	for (i=0; i<tag.length; i++){
		tagName = tag[i].name;
		requiredTag = tagName.match('prodStructureForm:hiddenName');
		if(requiredTag !=null && requiredTag !='undefined') {
			tagValue = 	document.getElementById(tagName).value;
			if(requiredTag == 'prodStructureForm:hiddenName' && 
				(tagValue == 'GENERAL BENEFITS' || tagValue == 'GENERAL PROVISIONS' || tagValue == 'SUPPLEMENTAL BENEFITS')){
				document.getElementById('prodStructureForm:sequence' + tagName.substring('prodStructureForm:hiddenName'.length)).readOnly = true;
				count ++;
				if(count == 3){
					return; // To stop the looping, once 'GENERAL BENEFITS' and 'GENERAL PROVISIONS' are changed to read only
				}
		 	}
	  	}
	}
}

if(document.getElementById('prodStructureForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodValidation.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('prodStructureForm:hasValErrors').value = 'false';
}
function deleteBnftcmpntAttachedToprdctStrctr(){
		var message = "Are you sure you want to delete the selected BenefitComponent attached to the ProductStructure?";
		var confirmValue = confirm(message);
		return confirmValue;
}
checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('prodStructureForm:panelTable');
	if (tableObject!=null ){
	if(tableObject.rows.length != 0)
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('prodStructureForm:panelTable');
		document.getElementById('prodStructureForm:panelData').value = onLoadPanelData;
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
			for(var j=0;j<columns-1;j++){
				if(null != tableObject.rows[i].cells[j].children[0]){
					dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
				}
			}
		}
		return dataOnScreen;
	}else{
		return dataOnScreen;
	}
}
function unsavedDataFinder(objectId){
	var buttonId = objectId;
	var panelData = getPanelData('prodStructureForm:panelTable');
	if(document.getElementById('prodStructureForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			if(buttonId == 'prodStructureForm:saveButton'){
				submitLink('prodStructureForm:saveToCache');	
			}
			else if(buttonId == 'prodStructureForm:deleteButton'){
				if(deleteBnftcmpntAttachedToprdctStrctr()){
					submitLink('prodStructureForm:deleteLink');	
				}
			}
		}
	}
	else{
		if(buttonId == 'prodStructureForm:saveButton'){
				submitLink('prodStructureForm:saveToCache');	
		}
		else if(buttonId == 'prodStructureForm:deleteButton'){
			if(deleteBnftcmpntAttachedToprdctStrctr()){
					submitLink('prodStructureForm:deleteLink');	
			}
		}
	}
}

function loadBenefitComponentPopUp(){
	var entityId = document.getElementById('prodStructureForm:hiddenBenefitComponentId').value;
	var queryName = 'selectBenefitComponent';
	var titleName = 'Benefit Component Popup';
	ewpdModalWindow_ewpd( '../popups/searchPopup.jsp?entityId='+entityId+'&headerName=Benefit Component&queryName='+queryName+'&titleName='+titleName+'&temp='+Math.random(), 'SelectBenefitHeirarchiesDiv','prodStructureForm:txtComponent',2,2); 
	return true;
}



</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComp" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('prodStructureForm:duplicateData').value == '')
		document.getElementById('prodStructureForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('prodStructureForm:duplicateData').value;
</script>
</HTML>
