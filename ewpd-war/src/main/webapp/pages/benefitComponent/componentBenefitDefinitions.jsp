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
<script language="JavaScript" src="../../js/prototype.js"></script>
<TITLE>Benefit Definitions</TITLE>
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

</script>
</HEAD>
<f:view>
	<BODY >
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="componentBenefitDefinition">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					bordercolor="red">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>



						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0" 
							id="TabTable">
							<tr>

								<td width="25%" >
								<table width="100%" border="0" cellspacing="0" cellpadding="0" >
									<tr >
										<td width="2" align="left" ><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink id="benefitComponentLink"
											action="#{benefitComponentCreateBackingBean.loadBenefitComponentforView}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="hiddenTabValue"
									value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>
								<td width="25%" id="stdDefTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="#{benefitComponentCreateBackingBean.componentTypeTab}" />
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%" id="sbMandateInfoTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{benefitMandateBackingBean.loadBenefitMandateForView}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%" id="sbOverrideNotesTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink id="benefitComponentNotesLink"
											action="#{BenefitComponentNotesBackingBean.loadStandardBenefitOverrideNotes}"
												onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="25%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:0px;width:100%;height:550"><br>
						<div  id="messageTextForNoBenefitLevelsDiv" align="center"><br>
						<br>
						<STRONG>&nbsp;<h:outputText  value="No Benefit Level available." /></STRONG>
						<CENTER>
						</div>
						<div id="associatedBenefitLevelsDiv">
						<TABLE class="smallfont" id="resultsTable" width="100%"
							cellpadding="3" cellspacing="1" border="0" bordercolor="red">
							<TR bgcolor="#cccccc">
								<TD bgcolor="#CCCCCC" height="23px"><b><h:outputText
									value="Associated Benefit Levels" /></b></TD>
							</TR>
							<TR>
								<TD><DIV id="resultHeaderDiv" align="left"
										style="position:relative;background-color:#FFFFFF;z-index:1;width:100%;overflow-y:scroll=no;"><h:panelGrid id="resultHeaderTable"
									binding="#{ComponentBenefitDefinitionsBackingBean.headerPanel}"
									rowClasses="dataTableOddRow"></h:panelGrid> </DIV>
									<DIV id="panelContent1" style="position:relative;height:373;overflow-y:auto;width:100%">
									<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;overflow:auto;width:100%"><h:panelGrid
									id="panelTable"
									binding="#{ComponentBenefitDefinitionsBackingBean.panel}"></h:panelGrid></DIV></DIV>
									<div  id="messageTextForNoVisibleBenefitLevelsDiv" align="center">
									<STRONG>&nbsp;<h:outputText  value="No Visible Benefit Lines." /></STRONG>
									<CENTER>
									</div>
								</TD>
							</TR>
							<TR>
								<TD id="saveButton"><SPAN
									style="margin-left:6px;margin-right:6px;"> <h:commandButton
									value="Save" styleClass="wpdButton" 
									onmousedown="javascript:savePageAction(this.id);"
									onclick="getShowHiddenSave();return false;">
								</h:commandButton> </SPAN></TD>
							</TR>
						</TABLE>
						</div>

						</fieldset>
						<div id="dummyDiv"></div>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="panelData" ></h:inputHidden>
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="selectedRowHidden"
					value="#{ComponentBenefitDefinitionsBackingBean.selectedRow}" />
				<h:commandLink id="hideCommandLink"
					action="#{ComponentBenefitDefinitionsBackingBean.hideSelectedLevel}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="levelsToDeleteHidden"
					value="#{ComponentBenefitDefinitionsBackingBean.levelsToDelete}">
					<f:verbatim />
				</h:inputHidden>

				<h:commandLink id="showHiddenLink"
					action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinition}"
					style="hidden">
					<f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink>
				<h:commandLink id="showHiddenSaveLink"
					action="#{ComponentBenefitDefinitionsBackingBean.save}"
					style="hidden">
					<f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink>
				<h:inputHidden id="showHiddenInput"
					value="#{ComponentBenefitDefinitionsBackingBean.showHidden}"></h:inputHidden>

				<h:inputHidden id="displayHeaderPanel"
					value="#{ComponentBenefitDefinitionsBackingBean.headerDisplay}" />
				<h:inputHidden id="duplicateData"
								value="#{ComponentBenefitDefinitionsBackingBean.duplicateData}"></h:inputHidden>
			<h:commandLink id="refresh" style="display:none; visibility: hidden;" 
					action="#{ComponentBenefitDefinitionsBackingBean.reloadPage}"> <f:verbatim /></h:commandLink>


				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
var dt = new Date();
var stTime  = dt.getTime();

IGNORED_FIELD1='componentBenefitDefinition:duplicateData';
IGNORED_FIELD2='componentBenefitDefinition:panelData';
var panelTableObject = document.getElementById('componentBenefitDefinition:panelTable');
	if(panelTableObject.rows.length > 0){
		var panelDivObj = document.getElementById('messageTextForNoVisibleBenefitLevelsDiv');
		panelDivObj.style.visibility = "hidden";
		panelDivObj.style.height = "0px";
	}else{
		var msgDivObj = document.getElementById('panelContent');
		msgDivObj.style.visibility = "hidden";
		msgDivObj.style.height = "0px";
		
	}
	
	setColumnWidth('componentBenefitDefinition:resultHeaderTable','20%:11%:15%:5%:7%:10%:14%:4%:8%');
	setColumnWidth('componentBenefitDefinition:panelTable','20%:11%:15%:5%:7%:10%:14%:4%:8%');

	var object = document.getElementById('componentBenefitDefinition:displayHeaderPanel').value;
	if(object == 'display'){
		var msgDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
		msgDivObj.style.visibility = "hidden";
		msgDivObj.style.height = "0px";
			document.getElementById('resultsTable').width = "100%";
				var relTblWidth = document.getElementById('resultsTable').offsetWidth;
				if(document.getElementById('componentBenefitDefinition:panelTable').rows.length < 10){
					//document.getElementById('panelContent').width = relTblWidth+"px";
					setColumnWidth('componentBenefitDefinition:resultHeaderTable','20%:11%:15%:5%:7%:10%:14%:4%:8%');	
					setColumnWidth('componentBenefitDefinition:panelTable','20%:11%:15%:5%:7%:10%:14%:4%:8%');
					syncTables('componentBenefitDefinition:resultHeaderTable','componentBenefitDefinition:panelTable');
				}else{
					//document.getElementById('componentBenefitDefinition:resultHeaderTable').width = relTblWidth-25+"px";
					setColumnWidth('componentBenefitDefinition:resultHeaderTable','17%:11%:18%:6%:8%:11%:17%:3%:9%');	
					setColumnWidth('componentBenefitDefinition:panelTable','17%:11%:18%:6%:8%:11%:17%:3%:9%');
					syncTables('componentBenefitDefinition:resultHeaderTable','componentBenefitDefinition:panelTable');
				}
	}else{
		var divObj = document.getElementById('associatedBenefitLevelsDiv');
		divObj.style.visibility = "hidden";
		divObj.style.height = "0px";
	}


	

	//changes the colour of the row when clicked
	function changeColour(levelId,lineSize,rowNum){
		var rowCount = rowNum - 1;
		var table = document.getElementById('componentBenefitDefinition:panelTable'); 
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
			var bnftValueId = "componentBenefitDefinition:lineBnftValue" + (count-1) + "_" + levelId;
			document.getElementById(bnftValueId).readOnly = changeDisabled;
		}
	  
		//gets the id to which the levels to be deleted is to be added
		var levelsToDelete = document.getElementById('componentBenefitDefinition:levelsToDeleteHidden').value;
		if(checkBackGroundColor == ""){
				
			if( levelsToDelete == null || levelsToDelete == '' ){
				document.getElementById('componentBenefitDefinition:levelsToDeleteHidden').value = levelId;
			}else{
				var appendLevelId = levelsToDelete + '~' + levelId;
				document.getElementById('componentBenefitDefinition:levelsToDeleteHidden').value = appendLevelId;
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
				document.getElementById('componentBenefitDefinition:levelsToDeleteHidden').value = updatedLevelIds;
			}
		}

	}

	//calls the maintain colour function
	maintainColour();
	
	//this function maintains the colour of the rows when tree is expanded or compressed
	function maintainColour(){
				var levelsToDelete = document.getElementById('componentBenefitDefinition:levelsToDeleteHidden').value;
				var table = document.getElementById('componentBenefitDefinition:panelTable'); 
					var rows = table.getElementsByTagName("tr");
				if(levelsToDelete != ''){
				
				var levelIdArray = levelsToDelete.split('~');
							var arraySize = levelIdArray.length;
							for( var i = 0; i < arraySize; i++ ){
						
								var hiddenLineSize = "componentBenefitDefinition:hiddenLineSize" + levelIdArray[i];
								var hiddenRowNum = "componentBenefitDefinition:hiddenRowNum" + levelIdArray[i];					
								var hiddenRowSize = document.getElementById(hiddenRowNum).value;
								var size=document.getElementById(hiddenLineSize).value;	 
								var rowCount=hiddenRowSize-1;
								rows[rowCount].style.backgroundColor = "#cccccc";
							for(var j = 0;j<size*1;j++){
									var count=j+1;
									rows[rowCount+count].style.backgroundColor = "#cccccc";
									var bnftValueIds = "componentBenefitDefinition:lineBnftValue" + (count-1) + "_" + levelIdArray[i];
									document.getElementById(bnftValueIds).readOnly = true;
								}
							}
				}
	}


	// For Override BenefitLine Notes at benefitComponent - Reusing the popUp for Contract -- changed from getUrl to getUrlAssigned
	function getUrlAssigned(linesysid,j,i){
		var retValue = ewpdModalWindow_ewpd('../contract/contractCoverageBenefitLevelNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCOMP&lookUpAction=4&secondaryEntityId='+linesysid+'&temp='+Math.random()+'&jValue='+j+'&iValue='+i,'dummyDiv','componentBenefitDefinition:hidden1',2,1);	
		var imageObj = document.getElementById('componentBenefitDefinition:notesButton'+j+'_'+i);
		var divObj = document.getElementById('dummyDiv');
		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	
		document.getElementById('componentBenefitDefinition:hiddenNotesStatus' + j + '_' + i).value = divObj.innerHTML;
		divObj.innerHTML = '';
		//submitLink('componentBenefitDefinition:refresh');	
		return false;
	}

hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("componentBenefitDefinition:hiddenTabValue").value ;
	if(tab=="Standard Definition"){
		saveButton.style.display = '' ;
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{		
		saveButton.style.display = 'none' ;
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}

function getShowHiddenActionMethod(){
	var select = document.getElementById('componentBenefitDefinition:showHiddenCheckBox');

	
	if(select.checked){

    document.getElementById('componentBenefitDefinition:showHiddenInput').value = true;}
	else
	document.getElementById('componentBenefitDefinition:showHiddenInput').value = false;
	
	//alert(document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value);
	submitLink('componentBenefitDefinition:showHiddenLink');


}

function getShowHiddenSave(){
	var select = document.getElementById('componentBenefitDefinition:showHiddenCheckBox');
	if(select.checked){
   	document.getElementById('componentBenefitDefinition:showHiddenInput').value = true;}
	else{
	document.getElementById('componentBenefitDefinition:showHiddenInput').value = false;
	}
	submitLink('componentBenefitDefinition:showHiddenSaveLink');

}

//Modified Script For Production Fix
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
//Modified Script For Production Fix
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
	var lineCheckBoxComponentId = lineCheckBoxComponent.id;
	var idArray = lineCheckBoxComponentId.split("A");		
	var checkBoxValue = lineCheckBoxComponent.checked;
	var levelId = idArray[1]; 
	var componentName = idArray[0];
	var numberOfLinesInALevel = idArray[2]; // total no. of ben lines in a level
	var startingIndex = idArray[3]; //which line
	var maxIndex = parseInt(numberOfLinesInALevel) + (parseInt(startingIndex)+1);
	var rowNumber = (document.getElementById('componentBenefitDefinition:hiddenRowNum'+levelId)).value;
	var table = document.getElementById('componentBenefitDefinition:panelTable'); 
	var rows = table.getElementsByTagName("tr");
	var idOfcheckBoxForLevel = (((rows[(rowNumber-1)]).getElementsByTagName("input"))[0]).id;
	var checkBoxForLevel = document.getElementById(idOfcheckBoxForLevel);
	var flag = checkForAllBenefitLinesChecked(checkBoxForLevel);
	if(flag)
		checkBoxForLevel.checked = true;
}

	//getHiddenNotesStatus();
	// function to get the hidden element of the notes
	function getHiddenNotesStatus(){
		var tags = document.getElementsByTagName('input');
		for (var j = 0; j < tags.length; j++) 
		{
			tagType = tags[j].type;
			if(tagType == 'hidden')
			{
				var tagName = tags[j].name;
				var exactMatch = tagName.match('componentBenefitDefinition:hiddenNotesStatus');
				if(exactMatch == 'componentBenefitDefinition:hiddenNotesStatus'){
					var id = tagName.substring(44);
					var notesStatus = document.getElementById(tagName).value;
					var imageObj = document.getElementById('componentBenefitDefinition:notesButton' + id);
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
	var tableObject = document.getElementById('componentBenefitDefinition:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('componentBenefitDefinition:panelTable');
		document.getElementById('componentBenefitDefinition:panelData').value = onLoadPanelData;
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
	
	var panelData = getPanelData('componentBenefitDefinition:panelTable');
	if(document.getElementById('componentBenefitDefinition:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			getShowHiddenActionMethod();
		}
		else{
			var select =  document.getElementById('componentBenefitDefinition:showHiddenCheckBox');
			if(select.checked){
				document.getElementById('componentBenefitDefinition:showHiddenCheckBox').checked=false;
			}else{
				document.getElementById('componentBenefitDefinition:showHiddenCheckBox').checked=true;
			}
		}
	}
	else{
		getShowHiddenActionMethod();
	}
}
function descriptionChange(levelComponent){
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("lineFreqValue");
	var levelId = idArrayForm[0]+':'+'hiddenLevelId'+idArrayLevel[1];
	var levelIdVal = document.getElementById(levelId).value;
	var descriptionId = idArrayForm[0]+':'+'levelDesc'+idArrayLevel[1];
	var description = trim(document.getElementById(descriptionId).innerText);
	var termId = idArrayForm[0]+':'+'Term'+idArrayLevel[1];
	var qualifierId = idArrayForm[0]+':'+'Qualifier'+idArrayLevel[1];
	var descriptionInputText = idArrayForm[0]+':'+'levelDescInputText'+levelIdVal;
	var term = document.getElementById(termId).innerText;
	var termArray = term.split(",");
	if((typeof(termArray[0]) != 'undefined') && (typeof(termArray[1]) != 'undefined')){
		term = trim(termArray[0])+' '+trim(termArray[1]);
	}else{
		term = document.getElementById(termId).innerText;
	}
	
	var qualifier = document.getElementById(qualifierId).innerText;
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
			if(document.getElementById(descriptionInputText).style.display != 'block'){
				document.getElementById(descriptionInputText).value = descHiddenvalue;
			}
			document.getElementById(descriptionInputText).style.display = 'block';
			document.getElementById(descriptionId).style.display = 'none';				
		}else{
			document.getElementById(descriptionInputText).style.display = 'none';
			document.getElementById(descriptionId).style.display = 'block';
			description = alignDescriptionLength(descLowerLevelvalue,18);
			document.getElementById(descriptionId).innerText = description;
			document.getElementById(descriptionInputText).value = descLowerLevelvalue;
		}
	}else{
		document.getElementById(descriptionInputText).style.display = 'none';
		document.getElementById(descriptionId).style.display = 'block';
		description = alignDescriptionLength(descHiddenvalue,18);
		document.getElementById(descriptionId).innerText = description;		
	}
}
//START CARS
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('componentBenefitDefinition:panelTable');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
		//var frequencyObject = document.getElementById('componentBenefitDefinition:lineFreqValue'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('componentBenefitDefinition:lineFreqValue'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//var levelId = document.getElementById('componentBenefitDefinition:hiddenLevelId'+i).value;
				//document.getElementById('componentBenefitDefinition:levelDescInputText'+levelId).style.display = 'none';
				//document.getElementById('componentBenefitDefinition:levelDesc'+levelId).style.display = '';
			//}
		//}	
	//}
//}
//END CARS
var dtend = new Date();
var endTime  = dtend.getTime();
//alert("Time1:"+(endTime-stTime)/1000)

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitDefinitions" /></form>
<!--  Unsaved Data Finder form -->
<form name="ewpdDataChangedForm">
	<input type="hidden" name="ewpdOnloadData" value="" />
</form>
<script>
	var stDt1 = new Date();
	var stTime1  = stDt1.getTime();
	
	// Async call to reduce page load time.
	setTimeout ( storeDataForUnsavedDataFinder, 500 );
	
	function storeDataForUnsavedDataFinder(){
		document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
		document.getElementById('componentBenefitDefinition:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}

	//if(document.getElementById('componentBenefitDefinition:duplicateData').value == ''){
		//document.getElementById('componentBenefitDefinition:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		//checkForPanel();
	//}
	//else
		//document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('componentBenefitDefinition:duplicateData').value;

	var endDt1 = new Date();
	var endTime1  = endDt1.getTime();
	
	//alert("Time2:"+(endTime1-stTime1)/1000);

</script>
</HTML>

