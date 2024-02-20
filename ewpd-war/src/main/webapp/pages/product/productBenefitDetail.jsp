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
<META http-equiv="Content-Style-Type" content="text/css">
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
<STYLE type="text/css">
.gridColumn75{
	width: 75%;
	text-align:left;
}
.gridColumn40{
	width: 40%;
	text-align:right;
}
.gridColumnRight20{
	width: 20%;
	text-align:right;
}
.gridColumnRight25{
	width: 25%;
	text-align:right;
}
.gridColumn25{
	width: 25%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn3{
	width: 3%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn9{
	width: 9%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn6{
	width: 6%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn7{
	width: 7%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn8{
	width: 8%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}

 </STYLE>
<TITLE>Benefit Standard Definition</TITLE>

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
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
<script language="JavaScript">
//validates the benefit value fields on the click of save
//changes the colour of the row when clicked
function changeColour(levelId,lineSize,rowNum){
	var rowCount=rowNum-1;
	var table = document.getElementById('benefitDefinitionForm:panelTable'); 
	var rows = table.getElementsByTagName("tr");
	var changeDisabled = '';

	//gets the background colour of the clicked row
	var checkBackGroundColor = rows[rowCount].style.backgroundColor;

	var changeColor = '';
	//changes colour of the row if clicked alternatively
	if( checkBackGroundColor == '' ){
		changeColor = "#cccccc";
		changeDisabled = true;
	}
	else if( checkBackGroundColor == "#cccccc" ){
		changeColor = "";
		changeDisabled = false;
	}
	
	//changes the colour of the row as well as disables and enables the benefit value field
	rows[rowCount].style.backgroundColor = changeColor;
	
	for( var i = 0; i < lineSize; i++ ){
			
			var count = i+1;
			
			rows[rowCount+count].style.backgroundColor = changeColor;
			var bnftValueId = "benefitDefinitionForm:lineBnftValue" + (count-1) + "_" + levelId;
			document.getElementById(bnftValueId).readOnly = changeDisabled;
		}
 
	//gets the id to which the levels to be deleted is to be added
	var levelsToDelete = document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value;
	if(checkBackGroundColor == ""){
			
		if( levelsToDelete == null || levelsToDelete == '' ){
			document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value = levelId;
		}else{
			var appendLevelId = levelsToDelete + '~' + levelId;
			document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value = appendLevelId;
		}
	}
	else if( checkBackGroundColor == "#cccccc" ){
		if( levelsToDelete != null || levelsToDelete != '' ){
			var updatedLevelIds = '';
			var levelIdArray = levelsToDelete.split('~');
			var arraySize = levelIdArray.length;
			for( var i = 0; i < arraySize; i++ ){
				if( levelId != levelIdArray[i] ){
					if( updatedLevelIds == '' ){
						updatedLevelIds = levelIdArray[i];
					}
					else{
						updatedLevelIds = updatedLevelIds + '~' + levelIdArray[i];
					}
				}
			}
			document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value = updatedLevelIds;
		}
	}

}

</script>
<script language="JavaScript">
       var cal2 = new CalendarPopup();
       cal2.showYearNavigation();
</script>

</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnter('benefitDefinitionForm:saveButton');">
	<h:inputHidden id="Hidden"
		value="#{productBenefitDetailBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0" 
		>

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitDefinitionForm">
				<h:inputHidden id="dummy"
					value="#{productBenefitDetailBackingBean.informationMsg}" />
				<table width="100%"  cellspacing="0" cellpadding="0" >
					<TR>

						<TD width="201px" valign="top" class="leftPanel">


						<div class="treeDiv" style="width:210px"><!-- Space for Tree  Data	-->
						<jsp:include page="productTree.jsp"></jsp:include></div>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD id="informationMsg"><w:message></w:message></TD>
									<td>
									<div id='mandatoryDiv' style="display:none; " class='errorDiv' />
									<li id=errorItem>Please enter the Mandatory Field.</li>
									</div>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable" >
							<tr>
								<td width="200" id="tab1">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitGeneralInfoBackingBean.getProductBenefitGenaralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="genid">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Coverage" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tabmandate">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Mandate Definition" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab3">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productBenefitMandateBackingBean.loadMandateInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="mandateid">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tab4">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{productBenefitNoteBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="noteid">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="200%"></td>
							</tr>
						</table>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table width="100%" border="0" cellspacing="0" class="ContentArea"
							cellpadding="0" >
							<tr>
								<td valign="top" class="ContentArea">
								<div style="height:250;overflow-y:auto;">
								<DIV id="noBenefitDefinitions"
									style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No
								Benefit Definitions available.</DIV>
								<DIV id="benefitDefinitionTable"
									style="overflow-x:hidden;overflow-y:scroll">
								<TABLE class="smallfont" id="resultsTable" width="95%"
									cellpadding="1" cellspacing="1" border="0">
									<TR bgcolor="#cccccc">
										<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar"><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></SPAN></TD>
									</TR>
									<TR align="left">
										<TD  align="left" valign="top" width="80%">	
										<TABLE cellpadding="0" align="left" border="0"
											id="tabheader" class="smallfont" width="100%">
									<TR>

												<TD valign="top">
												<DIV id="resultHeaderDiv"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="resultHeaderTable"
													binding="#{productBenefitDetailBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="panelTable"
													binding="#{productBenefitDetailBackingBean.panel}">
												</h:panelGrid></DIV>
												</TD>
											</TR>
										</TABLE>
										</TD>
									</TR>

									<TR>
										<TD>&nbsp;</TD>
									</TR>
									<TR>
										<TD>&nbsp;</TD>
									</TR>
									<TR align="left">
										<TD align="left" valign="top" width="95%">
										<TABLE cellpadding="0" align="left" border="0"
											id="tabheaderTier" class="smallfont" width="100%">
											<TR bgcolor="#cccccc">
												<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar">
												<STRONG><h:outputText
													value="Associated Tiered Benefit Levels" /></STRONG></SPAN></TD>
											</TR>
											<TR>
												<TD valign="top">
												<DIV id="resultHeaderDiv1"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="resultHeaderTable1"
													binding="#{productBenefitDetailBackingBean.tierHeaderPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent1"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="panelTable1"
													binding="#{productBenefitDetailBackingBean.tierPanel}">
												</h:panelGrid></DIV>
												</TD>
											</TR>
										</TABLE>
										</TD>
									</TR>
								</TABLE>
								<DIV>
								</TD>
							</TR>
							<TR>
								<TD id="save"><SPAN style="margin-left:4px;margin-right:6px;"><h:commandButton
									id="saveButton" value="Save" styleClass="wpdButton"
									onmousedown="manadatoryValidations();javascript:savePageAction(this.id);"
									onclick="validate();return false;">
								</h:commandButton> </SPAN> <h:commandLink
									id="displayHiddenLevels"
									action="#{productBenefitDetailBackingBean.loadHiddenLevels}"
									style="display:none; visibility: hidden;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandLink></TD>
							</TR>					
					
				</table>
				</fieldset>
					
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<div id="dummyDiv"></div></TD>
		</TR>
	</table>
	<h:commandLink id="showHiddenLink"
		action="#{productBenefitDetailBackingBean.save}"
		style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>
	<h:commandLink id="doRefreshLink"
		action="#{productBenefitDetailBackingBean.refresh}"
		style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>
	<h:commandLink id="deleteBenefitTier"
		action="#{productBenefitDetailBackingBean.deleteBenefitTier}"
		style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>
	<h:commandLink id="deleteLevelFromBenefitTier"
		action="#{productBenefitDetailBackingBean.deleteLevelFromBenefitTier}"
		style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>

	<h:inputHidden id="tierSysId"
		value="#{productBenefitDetailBackingBean.tierSysId}"></h:inputHidden>
	<h:inputHidden id="panelData"
		value="#{productBenefitDetailBackingBean.panelData}" />
	<h:inputHidden id="levelsToDeleteHidden"
		value="#{productBenefitDetailBackingBean.levelsToDelete}" />
	<h:inputHidden id="hiddenProductType"
		value="#{productBenefitDetailBackingBean.productType}"></h:inputHidden>
	<h:inputHidden id="showHiddenInput"
		value="#{productBenefitDetailBackingBean.showHidden}"></h:inputHidden>
	<h:inputHidden id="tierIdForDelete"
		value="#{productBenefitDetailBackingBean.tierIdToDelete}"></h:inputHidden>
	<h:inputHidden id="deleteTierLevelId"
		value="#{productBenefitDetailBackingBean.deleteTierLevelId}"></h:inputHidden>
	<h:inputHidden id="duplicateData"
		value="#{productBenefitDetailBackingBean.duplicateData}"></h:inputHidden>
	<h:commandLink id="refresh" style="display:none; visibility: hidden;"
		action="#{productBenefitDetailBackingBean.reloadPage}">
		<f:verbatim />
	</h:commandLink>

	</h:form></td>
	<tr>
		<td>
		<%@ include file="../navigation/bottom.jsp"%>
		</td>
	</tr>
	</table>

	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitDefinitionPrint" /></form>
<script>
	
	var mandatoryFlag = false;
	IGNORED_FIELD1='benefitDefinitionForm:duplicateData';
	IGNORED_FIELD2='benefitDefinitionForm:panelData';
	
	//window.onload = setBooleanFalse;

var tableObjectTier = document.getElementById('benefitDefinitionForm:panelTable1');
if(tableObjectTier.rows.length == 0){ 
document.getElementById('tabheaderTier').style.display = 'none';
}

setColumnWidth('benefitDefinitionForm:resultHeaderTable','17%:12%:15%:6%:8%:8%:18%:4%:9%:3%');
setColumnWidth('benefitDefinitionForm:panelTable','17%:12%:15%:6%:8%:8%:18%:4%:9%:3%');
syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');

//calls the maintain colour function
maintainColour();

//this function maintains the colour of the rows when tree is expanded or compressed
function maintainColour(){
			var levelsToDelete = document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value;
			var table = document.getElementById('benefitDefinitionForm:panelTable'); 
				var rows = table.getElementsByTagName("tr");
			if(levelsToDelete != ''){

			var levelIdArray = levelsToDelete.split('~');
						var arraySize = levelIdArray.length;
						for( var i = 0; i < arraySize; i++ ){

							var hiddenLineSize = "benefitDefinitionForm:hiddenLineSize" + levelIdArray[i];
							var hiddenRowNum = "benefitDefinitionForm:hiddenRowNum" + levelIdArray[i];					
							var hiddenRowSize = document.getElementById(hiddenRowNum).value;
							var size=document.getElementById(hiddenLineSize).value;	 
							var rowCount=hiddenRowSize-1;
							rows[rowCount].style.backgroundColor = "#cccccc";
					
					/*	for(var j = 1;j==size;j++){
								rows[rowCount+j].style.backgroundColor = "#cccccc";
								var bnftValueIds = "benefitDefinitionForm:lineBnftValue" + (j-1) + levelIdArray[i];
								document.getElementById(bnftValueIds).readOnly = true;
							}*/
							
							for(var j = 0;j<size*1;j++){
									var count=j+1;
									rows[rowCount+count].style.backgroundColor = "#cccccc";
									var bnftValueIds = "benefitDefinitionForm:lineBnftValue" + (count-1) + "_" + levelIdArray[i];
									document.getElementById(bnftValueIds).readOnly = true;
								}
						}
			}
}



//hides the header panel when no data is present
var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
if(tableObject.rows.length > 0){
	var divBnftDefn = document.getElementById('noBenefitDefinitions');
	divBnftDefn.style.visibility = "hidden";
	divBnftDefn.style.height = "2px";
}else{
	var divObj = document.getElementById('benefitDefinitionTable');
	divObj.style.visibility = "hidden";
	divObj.style.height = "2px";
	document.getElementById('noBenefitDefinitions').style.visibility = "visible";
	document.getElementById('resultHeaderDiv').style.visibility = "visible";
}


/*function getUrl(linesysid){
	ewpdModalWindow_ewpd('../product/productBenefitLevelNotesOverridePopup.jsp?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);
	submitLink('benefitDefinitionForm:refresh');
	return false;
}*/
	
	function getUrlAssigned(linesysid, j, i,lineNum){
		var isModified = document.ewpdDataChangedForm.ewpdOnloadData.value != getModifiedDataOnUnLoad();		
		var retValue = ewpdModalWindow_ewpd('../product/productBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);
		var imageObj = document.getElementById('benefitDefinitionForm:notesButton'+j+'_'+i+lineNum);
		var divObj = document.getElementById('dummyDiv');
		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	
		document.getElementById('benefitDefinitionForm:hiddenNotesStatus' + j + '_' + i+lineNum).value = divObj.innerHTML;
		divObj.innerHTML = '';
		//submitLink('benefitDefinitionForm:refresh');
		if(!isModified) loadDataOnNotesAttach();
		return false;
	}

i = document.getElementById("benefitDefinitionForm:hiddenProductType").value;
var hiddenCheck = document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
if(i=='MANDATE')
{
tab4.style.display='none';
tab2.style.display='none';
tab3.style.display='';
tabmandate.style.display='';
save.style.display='none';
}else{
tab4.style.display='';
tab2.style.display='';
tab3.style.display='none';
tabmandate.style.display='none';
	if(null==hiddenCheck)
		save.style.display='none';
	else
		save.style.display='';
}
function submitOnEnter(submitButton){
	var button = document.getElementById(submitButton);

	// Do not submit if 'Enter Key' pressed in Text area.
	var srcElement = window.event.srcElement;
	if( srcElement != null && srcElement != undefined && 
		srcElement.type != undefined && srcElement.type =='textarea') {
		return true;
	}
	if(window.event.keyCode==13 && tableObject.rows.length > 0 && i!='MANDATE') {
		button.click();
		return false;
	}
	return true;
}
function setBooleanFalse(){
	document.getElementById('benefitDefinitionForm:showHiddenInput').value;
}

if(i=="STANDARD"){
	var hiddenCheck = document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
	if(null!=hiddenCheck)
	document.getElementById('benefitDefinitionForm:showHiddenInput').value = document.getElementById('benefitDefinitionForm:showHiddenCheckBox').checked
}

function checkAllLevels(){
	var select = document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
	if(select.checked){
   	 	document.getElementById('benefitDefinitionForm:showHiddenInput').value = true;
		submitLink('benefitDefinitionForm:displayHiddenLevels');}
	else{
		document.getElementById('benefitDefinitionForm:showHiddenInput').value = false;
		submitLink('benefitDefinitionForm:displayHiddenLevels');
	}
	return true;
}
/* function checkAllLines(levelId, noOfLines ){
	var headCheckBox = document.getElementById("resultHeaderDiv");
	if(!(headCheckBox.disabled == true)){
		for(var i = 0; i < noOfLines; i++ ){
			document.getElementById("benefitDefinitionForm:checkBox"+i+"_"+levelId).checked = true;
		}
	}
}*/
function checkTheCorrespondingBenefitLines(checkBoxComponent){
	var checkBoxComponentId = checkBoxComponent.id;
	var idArray = checkBoxComponentId.split("A");		
	var checkBoxValue = checkBoxComponent.checked;
	var levelPageId = idArray[1];
	var componentName = idArray[0];
	var numberOfLinesInALevel = idArray[2];
	var startingIndex = idArray[3];
	var maxIndex = parseInt(numberOfLinesInALevel) + (parseInt(startingIndex)+1);
	for(i = (parseInt(startingIndex)+1); i < parseInt(maxIndex); i++){
		var checkBoxComponentForLine = document.getElementById(componentName+"A"+levelPageId+"A"+numberOfLinesInALevel+"A"+i+"A"+"Line");
		if(checkBoxComponentForLine != null){
			checkBoxComponentForLine.checked = checkBoxValue;
		}
	}
}

function checkForAllBenefitLinesChecked(checkBoxComponent){
	var checkBoxComponentId = checkBoxComponent.id;
	var checkFlag = true;
	var idArray = checkBoxComponentId.split("A");		
	var checkBoxValue = checkBoxComponent.checked;
	var levelPageId = idArray[1];
	var componentName = idArray[0];
	var numberOfLinesInALevel = idArray[2];
	var startingIndex = idArray[3];
	var maxIndex = parseInt(numberOfLinesInALevel) + (parseInt(startingIndex)+1);
	for(i = (parseInt(startingIndex)+1); i < parseInt(maxIndex); i++){
		var checkBoxComponentForLine = document.getElementById(componentName+"A"+levelPageId+"A"+numberOfLinesInALevel+"A"+i+"A"+"Line");
		if(!checkBoxComponentForLine.checked){
			checkFlag = false;
		}
	}
	return checkFlag;
}
function checkTheCorrespondingBenefitLevel(lineCheckBoxComponent){
	var flag;
	var lineCheckBoxComponentId = lineCheckBoxComponent.id;
	var idArray = lineCheckBoxComponentId.split("A");		
	var checkBoxValue = lineCheckBoxComponent.checked;
	var levelId = idArray[1]; 
	var componentName = idArray[0];
	var numberOfLinesInALevel = idArray[2]; // total no. of ben lines in a level
	var startingIndex = idArray[3]; //which line
	var maxIndex = parseInt(numberOfLinesInALevel) + (parseInt(startingIndex)+1);
	var rowNumber = (document.getElementById('benefitDefinitionForm:hiddenRowNum'+levelId)).value;
	var table = document.getElementById('benefitDefinitionForm:panelTable'); 
	var rows = table.getElementsByTagName("tr");
	var length = ((rows[(rowNumber-1)]).getElementsByTagName("input")).length;
	for(var i=0;i<length;i++){
		if(((((rows[(rowNumber-1)]).getElementsByTagName("input"))[i]).id).indexOf('checkBox') != -1){
			var idOfcheckBoxForLevel = (((rows[(rowNumber-1)]).getElementsByTagName("input"))[i]).id;
			var checkBoxForLevel = document.getElementById(idOfcheckBoxForLevel);
			flag = checkForAllBenefitLinesChecked(checkBoxForLevel);
		}
	}
	
	if(flag)
		checkBoxForLevel.checked = true;
	else
		checkBoxForLevel.checked = false;
}

function manadatoryValidations(){
var criteriaObjList = document.getElementsByTagName('input');
	var txtBox;
	mandatoryFlag = false;
	for(var i=0; i<criteriaObjList.length; i++) {
		txtBox = criteriaObjList[i];
		if(txtBox.type == 'text' && txtBox.title == 'CriteriaValue') {
				var targetId = txtBox.id.split('label');
				if(document.getElementById(targetId[0])!=null)document.getElementById(targetId[0]).style.color='blue';
				if(trim(txtBox.value) == '') {
					if(document.getElementById(targetId[0])!=null) document.getElementById(targetId[0]).style.color='red';
					mandatoryFlag = true;
				}
			}
		}
	if(mandatoryFlag) {
		document.getElementById('mandatoryDiv').style.display = "block";
		document.getElementById('informationMsg').style.display = "none";
		return false;
	}
}
function validate(){
if(mandatoryFlag) return false;
var select = document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
if(select.checked){
document.getElementById('benefitDefinitionForm:showHiddenInput').value = true;
}else{
document.getElementById('benefitDefinitionForm:showHiddenInput').value = false;
}
//return true;
submitLink('benefitDefinitionForm:showHiddenLink');
}

	//getHiddenNotesStatus(); // commented as part of performance fix for stabilization release
	// function to get the hidden element of the notes
	function getHiddenNotesStatus(){
		var tags = document.getElementsByTagName('input');
		for (var j = 0; j < tags.length; j++) 
		{
			tagType = tags[j].type;
			if(tagType == 'hidden')
			{
				var tagName = tags[j].name;
				var exactMatch = tagName.match('benefitDefinitionForm:hiddenNotesStatus');
				if(exactMatch == 'benefitDefinitionForm:hiddenNotesStatus'){
					var id = tagName.substring(39);
					var notesStatus = document.getElementById(tagName).value;
					var imageObj = document.getElementById('benefitDefinitionForm:notesButton' + id);
					if(notesStatus == "notes_exists<BR>"){
						imageObj.src = "../../images/notes_exist.gif";
					}else if(notesStatus == "no_notes<BR>"){
						imageObj.src = "../../images/page.gif";
					}
				}
			}
		}
	}

//checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('benefitDefinitionForm:panelTable');
		document.getElementById('benefitDefinitionForm:panelData').value = onLoadPanelData;
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
function unsavedDataFinder(){

	var panelData = getPanelData('benefitDefinitionForm:panelTable');
	if(document.getElementById('benefitDefinitionForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			checkAllLevels();
		}
		else{
			var select =  document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
			if(select.checked){
				document.getElementById('benefitDefinitionForm:showHiddenCheckBox').checked=false;
			}else{
				document.getElementById('benefitDefinitionForm:showHiddenCheckBox').checked=true;
			}
		}
	}
	else{
		checkAllLevels();
	}
}

function deleteTier(tierId){
	var flag = confirm("Are you sure you want to delete?");
	if(flag){
		document.getElementById('benefitDefinitionForm:tierIdForDelete').value = tierId;
		submitLink('benefitDefinitionForm:deleteBenefitTier');
	}
}

function deleteFromTier(levelId){
	var flag = confirm("Are you sure you want to delete?");
	if(flag){
		document.getElementById('benefitDefinitionForm:deleteTierLevelId').value = levelId;
		submitLink('benefitDefinitionForm:deleteLevelFromBenefitTier');

	}
}

function getUrlForTier(linesysid, j, i,tierSysId, lineNum){
		var isModified = document.ewpdDataChangedForm.ewpdOnloadData.value != getModifiedDataOnUnLoad();		
		var retValue = ewpdModalWindow_ewpd('../product/productBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHPRODUCT&lookUpAction=101&secondaryEntityId='+linesysid+'&tierSysId='+tierSysId+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);
		var imageObj = document.getElementById('benefitDefinitionForm:notesButton'+j+'_'+i+lineNum+tierSysId);
		var divObj = document.getElementById('dummyDiv');
		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	
		document.getElementById('benefitDefinitionForm:hiddenNotesStatus' + j + '_' + i+lineNum+tierSysId).value = divObj.innerHTML;
		divObj.innerHTML = '';
		if(!isModified) loadDataOnNotesAttach();
		return false;
	}

function descriptionChange(levelComponent){
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("lineFreqValue");
	var levelId = idArrayForm[0]+':'+'hiddenLevelId'+idArrayLevel[1];
	var levelIdVal = document.getElementById(levelId).value;
	var descriptionId = idArrayForm[0]+':'+'levelDesc'+idArrayLevel[1];
	var description = document.getElementById(descriptionId).innerText;
	var termId = idArrayForm[0]+':'+'Term'+idArrayLevel[1];
	var qualifierId = idArrayForm[0]+':'+'Qualifier'+idArrayLevel[1];
	var descriptionInputText = idArrayForm[0]+':'+'levelDescInputText'+idArrayLevel[1];
	var term = document.getElementById(termId).innerText;
	var qualifier = document.getElementById(qualifierId).innerText;
	var termArray = term.split(",");
	if((typeof(termArray[0]) != 'undefined') && (typeof(termArray[1]) != 'undefined')){
		term = trim(termArray[0])+' '+trim(termArray[1]);
	}else{
		term = document.getElementById(termId).innerText;
	}
	var qualifierArray = qualifier.split(",");
	if((typeof(qualifierArray[0]) != 'undefined') && (typeof(qualifierArray[1]) != 'undefined')){
		qualifier = trim(qualifierArray[0])+' '+trim(qualifierArray[1]);
	}else{
		qualifier = document.getElementById(qualifierId).innerText;
	}
	var frequencyHiddenId = idArrayForm[0]+':'+'hiddenLevelFreqValue'+idArrayLevel[1];
	var freqvalue = document.getElementById(frequencyHiddenId).value;
	var frequencyLowerLevelHid = idArrayForm[0]+':'+'hiddenLowerLevelFreqValue'+idArrayLevel[1];
	var freqLowerLevelvalue = document.getElementById(frequencyLowerLevelHid).value;
	var descLowerLevelHid = idArrayForm[0]+':'+'hidLowerLevelValDesc'+idArrayLevel[1];
	var descLowerLevelvalue = document.getElementById(descLowerLevelHid).value;
	var hidOutputValDesc = idArrayForm[0]+':'+'hidOutputValDesc'+idArrayLevel[1];
	var descHiddenvalue = document.getElementById(hidOutputValDesc).value;
	var newDescription;
	if(freqvalue == 1){
		newDescription = term+' PER '+qualifier;
	}else{
		newDescription = term+' PER '+freqvalue+' '+qualifier;
	}
	var len = newDescription.length;
	if(len >= 32){
		newDescription = trim(newDescription.substring(0,32));
	}
	if(description != newDescription &&  levelComponentValue != 0 && isNumericCheck(levelComponentValue)){
		if(freqLowerLevelvalue != levelComponentValue){
			if(document.getElementById(descriptionInputText).style.display != ''){
				document.getElementById(descriptionInputText).value = descHiddenvalue;
			}
			document.getElementById(descriptionInputText).style.display = '';
			document.getElementById(descriptionId).style.display = 'none';				
		}else{
			document.getElementById(descriptionInputText).style.display = 'none';
			document.getElementById(descriptionId).style.display = '';
			description = alignDescriptionLength(descLowerLevelvalue,18);
			document.getElementById(descriptionId).innerText = description;
			document.getElementById(descriptionInputText).value = descLowerLevelvalue;
		}	
	}else{
		document.getElementById(descriptionInputText).style.display = 'none';
		document.getElementById(descriptionId).style.display = '';
		description = alignDescriptionLength(descHiddenvalue,18);
		document.getElementById(descriptionId).innerText = description;	
	}
}

function descriptionChangeForTier(levelComponent){
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("levelFreqValueTier");
	var descriptionId = idArrayForm[0]+':'+'tierLevelDescription'+idArrayLevel[1];
	var description = trim(document.getElementById(descriptionId).innerText);
	var termId = idArrayForm[0]+':'+'hiddenTermTier'+idArrayLevel[1];
	var qualifierId = idArrayForm[0]+':'+'hiddenQualifierTier'+idArrayLevel[1];
	var descriptionInputText = idArrayForm[0]+':'+'levelDescInputTextTier'+idArrayLevel[1];
	var term = document.getElementById(termId).value;
	var qualifier = document.getElementById(qualifierId).value;
	var termArray = term.split(",");
	if((typeof(termArray[0]) != 'undefined') && (typeof(termArray[1]) != 'undefined')){
		term = trim(termArray[0])+' '+trim(termArray[1]);
	}else{
		term = document.getElementById(termId).value;
	}
	var qualifierArray = qualifier.split(",");
	if((typeof(qualifierArray[0]) != 'undefined') && (typeof(qualifierArray[1]) != 'undefined')){
		qualifier = trim(qualifierArray[0])+' '+trim(qualifierArray[1]);
	}else{
		qualifier = document.getElementById(qualifierId).value;
	}
	var frequencyHiddenId = idArrayForm[0]+':'+'hiddenLevelFreqValueTier'+idArrayLevel[1];
	var freqvalue = document.getElementById(frequencyHiddenId).value;
	var frequencyLowerLevelHid = idArrayForm[0]+':'+'hiddenTierLowerLevelFreqValue'+idArrayLevel[1];
	var freqLowerLevelvalue = document.getElementById(frequencyLowerLevelHid).value;
	var descLowerLevelHid = idArrayForm[0]+':'+'hidTierLowerLevelValDesc'+idArrayLevel[1];
	var descLowerLevelvalue = document.getElementById(descLowerLevelHid).value;
	var hidOutputValDesc = idArrayForm[0]+':'+'hidTierOutputValDesc'+idArrayLevel[1];
	var descHiddenvalue = document.getElementById(hidOutputValDesc).value;
	var newDescription;
	if(freqvalue == 1){
		newDescription = term+' PER '+qualifier;
	}else{
		newDescription = term+' PER '+freqvalue+' '+qualifier;
	}
	var len = newDescription.length;
	if(len >= 32){
		newDescription = trim(newDescription.substring(0,32));
	}
	if(description != newDescription &&  levelComponentValue != 0 && isNumericCheck(levelComponentValue)){
		if(freqLowerLevelvalue != levelComponentValue){
			if(document.getElementById(descriptionInputText).style.display != ''){
				document.getElementById(descriptionInputText).value = descHiddenvalue;
			}
			document.getElementById(descriptionInputText).style.display = '';
			document.getElementById(descriptionId).style.display = 'none';				
		}else{
			document.getElementById(descriptionInputText).style.display = 'none';
			document.getElementById(descriptionId).style.display = '';
			description = alignDescriptionLength(descLowerLevelvalue,22);
			document.getElementById(descriptionId).innerText = description;
			document.getElementById(descriptionInputText).value = descLowerLevelvalue;
		}
	}else{
		document.getElementById(descriptionInputText).style.display = 'none';
		document.getElementById(descriptionId).style.display = '';
		description = alignDescriptionLength(descHiddenvalue,22);
		document.getElementById(descriptionId).innerText = description;		
	}
}
//START CARS
//NON TIER PANEL 
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
		//var frequencyObject = document.getElementById('benefitDefinitionForm:lineFreqValue'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('benefitDefinitionForm:lineFreqValue'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//document.getElementById('benefitDefinitionForm:levelDescInputText'+i).style.display = 'none';
				//document.getElementById('benefitDefinitionForm:levelDesc'+i).style.display = '';
			//}
		//}	
	//}
//}
//TIER PANEL
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('benefitDefinitionForm:panelTable1');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
		//var frequencyObject = document.getElementById('benefitDefinitionForm:levelFreqValueTier'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('benefitDefinitionForm:levelFreqValueTier'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//document.getElementById('benefitDefinitionForm:levelDescInputTextTier'+i).style.display = 'none';
				//document.getElementById('benefitDefinitionForm:tierLevelDescription'+i).style.display = '';
			//}
		//}	
	//}
//}
//END CARS
// **Function to allow only numbers**
function isNum(e){
var key;
key = e.which ? e.which : e.keyCode;
	if((key>=48 && key<=57)) {
		e.returnValue= true;
	}else{
		e.returnValue = false;
	}
}
// ** This function is restict the user to add new tier definition if any error message is present ** //
function validateErr(){
if(document.getElementById('informationMsg').innerHTML !='' && document.getElementById('informationMsg').innerHTML !=null){
	if(document.getElementById('informationMsg').childNodes[0].className == 'errorDiv'){
		alert('Please correct error(s) in the page before trying to add a new tier.');
		return false;
	}
}
return true;
}
</script>
<!--  Unsaved Data Finder form -->
<form name="ewpdDataChangedForm">
	<input type="hidden" name="ewpdOnloadData" value="" />
</form>

<script>
// Async call to reduce page load time.
	setTimeout ( storeDataForUnsavedDataFinder, 500 );

	function storeDataForUnsavedDataFinder(){
		document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
		document.getElementById('benefitDefinitionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
	//if(document.getElementById('benefitDefinitionForm:duplicateData').value == ''){
		//document.getElementById('benefitDefinitionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		//checkForPanel();commented as part of stabilizationRelease on 03-02-2011
	//}
	//else{
	//	document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitDefinitionForm:duplicateData').value;
	//}
function loadDataOnNotesAttach(){
document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnUnLoad();
}
function DisableRightClick(event){
if (event.button==2){
	alert("Right Click is Disabled");
	return false;
}
}
</script>
</HTML>
