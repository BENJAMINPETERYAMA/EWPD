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
<TITLE>Edit Product Structure Benefit Definition</TITLE>
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

	<SCRIPT>

</SCRIPT>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitDefinitionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel">


						<DIV class="treeDiv" style="height:610"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD><w:message></w:message></TD>
								</TR>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<TD class="tabNormal"><h:commandLink
											onmousedown="return false;"
											action="#{productStructureBenefitDefenitionBackingBean.viewBenefitDefenition}"
											id="linkToGeneralInfo"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive"><h:outputText
											value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td id = "notesTab" width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												onmousedown="return false;"
												action="#{productStructureBenefitDefenitionBackingBean.loadStandardBenefitNotes}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="linkToNotes">
												<h:outputText
													value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								<%-- <td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:outputText
											value="Adj Benefit Mandates" /></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>--%>
								<td id="mandTab" width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productStructureBenefitMandatesBackingBean.retrieveMandates}"
											id="linkToNonAdjMan">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:530">
						<table width="100%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="0">
							<tr>
								<td valign="top" class="ContentArea">
								
								<div id="defnDiv"><h:outputText
									value="No Benefit Definitions Available."
									styleClass="dataTableColumnHeader" /></div>
								<DIV id="benefitDefinitionDiv" style="height:280px;">

								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0" >
									<TR bgcolor="#cccccc">
										<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar"><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></SPAN></TD>
									</TR>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<TABLE width="100%" cellpadding="0" align="left" border="0"
											id="tabheader" class="smallfont">
											<TR>
												<TD align="left" valign="top">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;background-color:#FFFFFF;z-index:1;width:100%;overflow-y:scroll;"><h:panelGrid
													id="resultHeaderTable"
													binding="#{productStructureBenefitDefenitionBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;height:365;overflow-y:scroll;">
												<h:panelGrid id="panelTable"  
													binding="#{productStructureBenefitDefenitionBackingBean.panel}">
												</h:panelGrid></DIV>
											</TR>


											<TR>
												<TD>&nbsp;</TD>
											</TR>

											<TR id="saveButton">
												<TD><SPAN style="margin-left:6px;margin-right:6px;"> <h:commandButton
													value="Save" styleClass="wpdButton" 
													onclick="return validate();" onmousedown="javascript:savePageAction(this.id);"
													action="#{productStructureBenefitDefenitionBackingBean.save}">
												</h:commandButton> </SPAN></TD>
											</TR>
				<h:commandLink id="displayHiddenLevels" action="#{productStructureBenefitDefenitionBackingBean.loadHiddenLevels}" style="hidden">
					<f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>
										</TABLE>
										</TD>


									</TR>
								</TABLE>
								</DIV>
								<!--	Start of Table for actual Data	-->

								
								</td>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="panelData" value="#{productStructureBenefitDefenitionBackingBean.panelData}" />
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:inputHidden id="levelsToDeleteHidden"
					value="#{productStructureBenefitDefenitionBackingBean.levelsToDelete}" />
				<h:inputHidden id="storedState"
					value="#{productStructureBenefitDefenitionBackingBean.storedStates}" />
				<h:inputHidden id="benTypeHidden"
					value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
				<h:inputHidden id="showHiddenInput"
					value="#{productStructureBenefitDefenitionBackingBean.showHidden}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
			<h:inputHidden id="duplicateData" value="#{productStructureBenefitDefenitionBackingBean.duplicateData}"/>				
					<!-- End of hidden fields  -->
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="javascript">
	IGNORED_FIELD1='benefitDefinitionForm:duplicateData';
	IGNORED_FIELD2='benefitDefinitionForm:panelData';
 //  window.onload = setBooleanFalse;

var benType = document.getElementById('benefitDefinitionForm:benTypeHidden').value;

function setBooleanFalse(){
	var test = document.getElementById('benefitDefinitionForm:showHiddenInput').value;
}

if(benType == "Standard Definition"){
	var hiddenCheck = document.getElementById('benefitDefinitionForm:showHiddenCheckBox')
	if(null!=hiddenCheck)
 		document.getElementById('benefitDefinitionForm:showHiddenInput').value = document.getElementById('benefitDefinitionForm:showHiddenCheckBox').checked;

}

function checkAllLevels(){
var select = document.getElementById('benefitDefinitionForm:showHiddenCheckBox');
	if(select.checked){
   	 	document.getElementById('benefitDefinitionForm:showHiddenInput').value = true;
		submitLink('benefitDefinitionForm:displayHiddenLevels');
	}
	else{
		document.getElementById('benefitDefinitionForm:showHiddenInput').value = false;
		submitLink('benefitDefinitionForm:displayHiddenLevels');
	}
	return true;
}

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


		function initialize(){
				if(document.getElementById('benefitDefinitionForm:panelTable') != null) {
				document.getElementById('defnDiv').style.visibility = 'hidden';
				var relTblWidth = document.getElementById('benefitDefinitionForm:panelTable').offsetWidth;
			if(document.getElementById('benefitDefinitionForm:panelTable').offsetHeight <= 100) {
					//document.getElementById('benefitDefinitionForm:panelTable').width = relTblWidth+"px";
					//document.getElementById('benefitDefinitionForm:resultHeaderTable').width = relTblWidth+"px";
					setColumnWidth('benefitDefinitionForm:resultHeaderTable','21%:10%:14%:5%:7%:10%:14%:5%:8%');	
					setColumnWidth('benefitDefinitionForm:panelTable','21%:10%:14%:5%:7%:10%:14%:5%:8%');
					syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
				}else{
					syncTables('benefitDefinitionForm:resultHeaderTable','benefitDefinitionForm:panelTable');
					setColumnWidth('benefitDefinitionForm:resultHeaderTable','17%:12%:17%:6%:7%:11%:17%:5%:8%');	
					setColumnWidth('benefitDefinitionForm:panelTable','17%:12%:17%:6%:7%:11%:17%:5%:8%');
				}
			}else {
				// document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('panelContent').style.height = '1px';
				document.getElementById('panelContent').style.visibility = 'hidden';
			//	document.getElementById('benefitDefinitionDiv').style.visibility = 'hidden';
			}
		}

//validates the benefit value fields on the click of save
function validate() {
// **change**
/*	var objList = document.getElementsByTagName('input');
	var txtBox;
	for(var i=0; i<objList.length; i++) {
		txtBox = objList[i];
		
		//checks for benefit values with data type $
		if(txtBox.type == 'text' && txtBox.title == 'BenefitValue$') {
			if(isNaN(+txtBox.value)){
				alert("Benefit Value should be a Number.");
				document.getElementById(txtBox.id).focus();
				return false;
			}
			else{
				if(txtBox.value < 0){
					alert("Benefit Value with data type $ should be greater than or equal to 0.");
					document.getElementById(txtBox.id).focus();
					return false;
				}
				if(trim(txtBox.value) == '') {
					txtBox.value = 0;
				}
			}
		}

		//checks for benefit value with data type %
		if(txtBox.type == 'text' && txtBox.title == 'BenefitValue%') {
			if(isNaN(+txtBox.value)){
				alert("Benefit Value should be a Number.");
				document.getElementById(txtBox.id).focus();
				return false;
			}
			else{
				if(txtBox.value < 0 ||txtBox.value > 100 ){
					alert("Benefit Value with data type % should be between 0 and 100.");
					document.getElementById(txtBox.id).focus();
					return false;
				}
				if(trim(txtBox.value) == '') {
					txtBox.value = 0;
				}
			}	
		}
	}*/
// **End**
	return true;
}
		
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

	/*for(var i=1;i==lineSize;i++){
		rows[rowCount+i].style.backgroundColor = changeColor;
		var bnftValueId = "benefitDefinitionForm:lineBnftValue" + (i-1) + levelId;
		document.getElementById(bnftValueId).disabled = changeDisabled;
	}*/
  
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
//alert(document.getElementById('benefitDefinitionForm:levelsToDeleteHidden').value);
}
displayMandateTab();
	function displayMandateTab(){
	
	if(benType=="Mandate Definition"){
		mandTab.style.display='';
		notesTab.style.display='none';
	}
	else{
		mandTab.style.display='none';
		notesTab.style.display='';
	}
}

displaySaveButton();
	function displaySaveButton(){
	var benType = document.getElementById('benefitDefinitionForm:benTypeHidden').value;
	var hiddenCheck = document.getElementById('benefitDefinitionForm:showHiddenCheckBox')
	if(benType=="Mandate Definition" || null == hiddenCheck ){
		saveButton.style.display='none';
	}
	else
		saveButton.style.display='';
}

	function getUrlAssigned(linesysid,benefitComponentId){
	ewpdModalWindow_ewpd('../productStructure/productStrucureBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCOMP&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random(),'dummyDiv','benefitDefinitionForm:hidden1',2,1);	
	return false;
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

initialize();

function descriptionChange(levelComponent){	
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("levelFreqValue");
	var descriptionId = idArrayForm[0]+':'+'levelDesc'+idArrayLevel[1];
	var description = trim(document.getElementById(descriptionId).innerText);
	var termId = idArrayForm[0]+':'+'Term'+idArrayLevel[1];
	var qualifierId = idArrayForm[0]+':'+'hiddenQualifier'+idArrayLevel[1];
	var descriptionInputText = idArrayForm[0]+':'+'levelDescInputText'+idArrayLevel[1];
	var term = document.getElementById(termId).innerText;
	var qualifier = document.getElementById(qualifierId).value;
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
		qualifier = document.getElementById(qualifierId).value;
	}
	var frequencyHiddenId = idArrayForm[0]+':'+'hiddenLevelFreqValue'+idArrayLevel[1];
	var freqvalue = document.getElementById(frequencyHiddenId).value;
	var frequencyLowerLevelHid = idArrayForm[0]+':'+'hiddenLowerLevelFreqValue'+idArrayLevel[1];
	var freqLowerLevelvalue = document.getElementById(frequencyLowerLevelHid).value;
	var descLowerLevelHid = idArrayForm[0]+':'+'hidLowerLevelValDesc'+idArrayLevel[1];
	var descLowerLevelvalue = document.getElementById(descLowerLevelHid).value;
	var hidOutputValDesc = idArrayForm[0]+':'+'levelHidDesc'+idArrayLevel[1];
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
			description = alignDescriptionLength(descLowerLevelvalue,17);
			document.getElementById(descriptionId).innerText = description;
			document.getElementById(descriptionInputText).value = descLowerLevelvalue;
		}			
	}else{
		document.getElementById(descriptionInputText).style.display = 'none';
		document.getElementById(descriptionId).style.display = '';
		description = alignDescriptionLength(descHiddenvalue,17);
		document.getElementById(descriptionId).innerText = description;		
	}
}
//START CARS
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('benefitDefinitionForm:panelTable');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
	//	var frequencyObject = document.getElementById('benefitDefinitionForm:levelFreqValue'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('benefitDefinitionForm:levelFreqValue'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//document.getElementById('benefitDefinitionForm:levelDescInputText'+i).style.display = 'none';
				//document.getElementById('benefitDefinitionForm:levelDesc'+i).style.display = '';
			//}
		//}	
	//}
//}
//END CARS
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitDefinition" /></form>
<!--  Unsaved Data Finder form -->
<form name="ewpdDataChangedForm">
	<input type="hidden" name="ewpdOnloadData" value="" />
</form>

<script>
	//if(document.getElementById('benefitDefinitionForm:duplicateData').value == ''){
		//document.getElementById('benefitDefinitionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		//checkForPanel();
	//}
	//else
		//document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitDefinitionForm:duplicateData').value;
	setTimeout ( storeDataForUnsavedDataFinder, 500 );

	function storeDataForUnsavedDataFinder(){
		document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
		document.getElementById('benefitDefinitionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
</script>
</HTML>
