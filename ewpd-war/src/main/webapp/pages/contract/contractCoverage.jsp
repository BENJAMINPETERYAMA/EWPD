<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
.gridColumnRight25{
	width: 25%;
	text-align:right;
}
.gridColumn25{
	width: 25%;
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
.gridColumn22{
	width: 22%;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	text-align:left;
}
.gridColumn19{
	width: 19%;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	text-align:left;
}
.gridColumn12{
	width: 12%;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	text-align:left;
}
.gridColumn8{
	width: 8%;
	text-align:left;
}
.gridColumn7{
	width: 7%;
	text-align:left;
}
.gridColumn5{
	width: 5%;
	text-align:left;
}
.gridColumn3{
	width: 3%;
	text-align:left;
}
</style>
<TITLE>contractCoverage.jsp</TITLE>
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
<script>
var mandatoryFlag = false;
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
//validates the tiercriteria value fields on the click of save
function validate() {
if(mandatoryFlag) {
	return false;
}else return true;
}

//changes the colour of the row when clicked
function changeColour(levelId,lineSize,rowNum){
	var rowCount=rowNum-1;
	var table = document.getElementById('contractCoverageForm:panelTable'); 
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
			var bnftValueId = "contractCoverageForm:lineBnftValue" + (count-1) + "_" + levelId;
			document.getElementById(bnftValueId).readOnly = changeDisabled;
		}
		
}

</script>
<script language="JavaScript">
       var cal3 = new CalendarPopup();
       cal3.showYearNavigation();
</script>

</HEAD>
<f:view>
	<BODY>
	<h:inputHidden id="Hidden"
		value="#{contractCoverageBackingBean.dummyVar}"></h:inputHidden>
	<h:inputHidden id="hiddenLineId"
		value="#{contractCoverageBackingBean.lineSysId}"></h:inputHidden>

	<table width="100%" cellpadding="0" cellspacing="0"
		border=0>

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="contractCoverageForm">
				<h:inputHidden id="dummy"
					value="#{contractCoverageBackingBean.informationMsg}" />
				<table width="100%" cellspacing="0" cellpadding="0"
					bordercolor="" border="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv">  <jsp:include
							page="contractTree.jsp"></jsp:include></DIV>

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

<!--						 Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable" >
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{contractBenefitGeneralInfoBackingBean.displayStandardBenefitGeneralInfo}"
											onmousedown="javascript:navigatePageAction(this.id);"
											id="thisId">
											<h:outputText value="General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="#{contractCoverageBackingBean.benefitTypeTab}" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>


								<td width="200" id="tab2">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractBenefitNotesBackingBean.loadNotes}"
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

								<td width="200" id="tab3">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{contractBenefitMndateInfoBacingBean.retrieveMandates}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200"></td>
								<td width="200"></td>
								<td width="200%"></td>
							</tr>
						</table>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table width="100%" border="0" cellspacing="0"
							cellpadding="0">

							<tr>

								<td valign="top" class="ContentArea">
								<div style="height:327;overflow-y:auto;">
								<DIV id="noBenefitDefinitions"
									style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No
								Benefit Definitions available.</DIV>
								<DIV id="benefitDefinitionTable">
								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="0" cellspacing="0" border="0" >


									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<TABLE width="100%" cellpadding="0" align="left" border="0"
											id="tabheader" >
											<TR bgcolor="#cccccc">
												<TD colspan="1" bgcolor="#CCCCCC">
												<DIV id="panelMainHeader"
													style="background-color:#cccccc;width:100%;height: 20 "><B>&nbsp;<h:outputText
													value="Associated Benefit Levels"></h:outputText></B></div>
												</TD>
											</TR>

											<TR>
												<TD align="left" valign="top" width="100%">
												<DIV style="position:relative;top:-2px;left:0px"
													align="left">
												<DIV id="resultHeaderDiv" align="left"
													style="position:relative;background-color:#FFFFFF;z-index:1;">
												<h:panelGrid id="resultHeaderTable"
													binding="#{contractCoverageBackingBean.headerPanel}"
													rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>

												<DIV id="panelContent"
													style="position:relative;background-color:#FFFFFF;"><h:panelGrid
													id="panelTable"
													binding="#{contractCoverageBackingBean.panel}">
												</h:panelGrid></DIV>
												<br>
												</DIV>
												</TD>

											</TR>
											<TR>
												<TD>&nbsp;</TD>
											</TR>
											<TR align="left">
												<TD align="left" valign="top" width="100%">
												<TABLE cellpadding="0" align="left" border="0"
													id="tabheaderTier" class="smallfont" width="100%"
													>
													<TR bgcolor="#cccccc">
														<TD colspan="1" bgcolor="#CCCCCC"><SPAN id="stateCodeStar"
															style="background-color:#cccccc;z-index:0;position:relative;top:0px;left:2px;height: 20">
														<STRONG><h:outputText
															value="Associated Tiered Benefit Levels" /></STRONG></SPAN></TD>
													</TR>
													<TR>
														<TD valign="top">
														<DIV id="resultHeaderDiv1"
															style="position:relative;background-color:#FFFFFF;"><h:panelGrid
															id="resultHeaderTable1"
															binding="#{contractCoverageBackingBean.tierHeaderPanel}"
															rowClasses="dataTableOddRow">
														</h:panelGrid></DIV>

														<DIV id="panelContent1"
															style="position:relative;background-color:#FFFFFF; solid #cccccc;">
														<h:panelGrid id="panelTable1"
															binding="#{contractCoverageBackingBean.tierPanel}">
														</h:panelGrid></DIV>
														</TD>
													</TR>
												</TABLE>
												</TD>
											</TR>
										</TABLE>
										</TD>
									</TR>
									</DIV>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD><SPAN style="margin-left:6px;margin-right:6px;padding:20px;">
								<h:commandButton value="Save" id="saveBtn"
									styleClass="wpdButton"
									onmousedown="manadatoryValidations();javascript:savePageAction(this.id);"
									onclick="return validate();"
									rendered="#{contractCoverageBackingBean.mandate}"
									action="#{contractCoverageBackingBean.save}">
								</h:commandButton> </SPAN></TD>
							</TR>
						</TABLE>
						</fieldset>
						</td>
					</tr>
				</table></TD>
		</TR>
	
	</table>
	<h:commandLink id="refresh" style="display:none; visibility: hidden;"
		action="#{contractCoverageBackingBean.reloadPage}">
		<f:verbatim />
	</h:commandLink>
	<h:commandLink id="deleteBenefitTier"
		action="#{contractCoverageBackingBean.deleteBenefitTier}"
		style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>
	<h:commandLink id="doRefreshLink"
		action="#{contractCoverageBackingBean.refresh}" style="display:none; visibility: hidden;">
		<f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink>
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
	<h:inputHidden id="hiddenProductType"
		value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
	<h:inputHidden id="hiddenMandate"
		value="#{contractCoverageBackingBean.mandate}"></h:inputHidden>
	<h:inputHidden id="tierIdForDelete"
		value="#{contractCoverageBackingBean.tierIdToDelete}"></h:inputHidden>

	<h:inputHidden id="duplicateData"
		value="#{contractCoverageBackingBean.duplicateData}"></h:inputHidden>
<div id="dummyDiv" ></div>


	</h:form></td>
		</tr>
	
			<%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>

	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractCoveragePrint" /></form>

<script>
IGNORED_FIELD1='contractCoverageForm:duplicateData'; 
//setColumnWidth('benefitDefinitionForm:resultHeaderTable','15%:15%:15%:15%:15%:15%:10%');
//setColumnWidth('benefitDefinitionForm:panelTable','15%:15%:15%:15%:15%:15%:10%');
setColumnWidth('contractCoverageForm:resultHeaderTable','17%:11%:18%:6%:7%:9%:17%:6%:9%');
setColumnWidth('contractCoverageForm:panelTable','17%:11%:18%:6%:7%:9%:17%:6%:9%');

var width = screen.width;
var height = screen.height;
//hides the header panel when no data is present
var tableObject = document.getElementById('contractCoverageForm:panelTable');
var tableObjectTier = document.getElementById('contractCoverageForm:panelTable1');
if(tableObjectTier.rows.length == 0){ 
document.getElementById('tabheaderTier').style.display = 'none';
}
if(tableObject.rows.length > 0){ 
var panelTabOffset = document.getElementById('contractCoverageForm:panelTable').offsetHeight;
		
		if(panelTabOffset > 230) {

			if((width == 1280) || (height == 1024)){
		
				document.getElementById('contractCoverageForm:resultHeaderTable').width = "100%";
				document.getElementById('contractCoverageForm:panelTable').width = "100%";
				document.getElementById('panelMainHeader').style.width = "97.7%";
			}else if((width == 1152)){	
				document.getElementById('contractCoverageForm:resultHeaderTable').width = "100%";
				document.getElementById('contractCoverageForm:panelTable').width = "100%";
				document.getElementById('panelMainHeader').style.width = "97.3%";
			}else if((width == 1024) || (height == 768) || (width == 800)){
			
				document.getElementById('contractCoverageForm:resultHeaderTable').width = "100%";
				document.getElementById('contractCoverageForm:panelTable').width = "100%";
				document.getElementById('panelMainHeader').style.width = "97%";
			}
		}else{
			document.getElementById('panelMainHeader').style.width = "99.5%";
		}
		var divBnftDefn = document.getElementById('noBenefitDefinitions');
		divBnftDefn.style.visibility = "hidden";
		divBnftDefn.style.height = "2px";
	}else{
		var divObj = document.getElementById('benefitDefinitionTable');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		document.getElementById('noBenefitDefinitions').style.visibility = "visible";
}

function getUrlForTier(benefitLineId,j,i,tierSysId,linenum){
	var isModified = document.ewpdDataChangedForm.ewpdOnloadData.value != getModifiedDataOnUnLoad();
	var retValue = ewpdModalWindow_ewpd('../contract/benefitLineNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCONTRACT&lookUpAction=101&secondaryEntityId='+ benefitLineId+'&tierSysId='+tierSysId+'&temp=' + Math.random() ,'dummyDiv', 'contractCoverageForm:hidden1',2,1);
	var imageObj = document.getElementById('contractCoverageForm:notesButton'+j+'_'+i+linenum+tierSysId);
	var divObj = document.getElementById('dummyDiv');
	if(divObj.innerHTML == "notes_exists<BR>"){
		imageObj.src = "../../images/notes_exist.gif";
	}
	else if(divObj.innerHTML == "no_notes<BR>"){
		imageObj.src = "../../images/page.gif";
	}	
	document.getElementById('contractCoverageForm:hiddenNotesStatus' + j + '_' + i+linenum+tierSysId).value = divObj.innerHTML;
	divObj.innerHTML = '';
	//submitLink('contractCoverageForm:refresh');
    if(!isModified)loadDataOnNotesAttach();
	return false;
}

function getUrlAssigned(benefitLineId,j,i){
	var isModified = document.ewpdDataChangedForm.ewpdOnloadData.value != getModifiedDataOnUnLoad();
	ewpdModalWindow_ewpd('../contract/benefitLineNotesOverridePopup.jsp'+getUrl()+'?parentEntityType=ATTACHCONTRACT&lookUpAction=4&secondaryEntityId='+ benefitLineId+'&temp=' + Math.random() ,'dummyDiv', 'contractCoverageForm:hidden1',2,1);
	var imageObj = document.getElementById('contractCoverageForm:noteImage'+j+'_'+i);
		var divObj = document.getElementById('dummyDiv');
		if(divObj.innerHTML == "notes_exists<BR>"){
			imageObj.src = "../../images/notes_exist.gif";
		}
		else if(divObj.innerHTML == "no_notes<BR>"){
			imageObj.src = "../../images/page.gif";
		}	
		document.getElementById('contractCoverageForm:hiddenNotesStatus' + j + '_' + i).value = divObj.innerHTML;
		divObj.innerHTML = '';
		//submitLink('contractCoverageForm:refresh');
		if(!isModified) loadDataOnNotesAttach();
		return false;
}

	i = document.getElementById("contractCoverageForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tab2.style.display='none';
	tab3.style.display='';
	}else{
	tab2.style.display='';
	tab3.style.display='none';
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
				var exactMatch = tagName.match('contractCoverageForm:hiddenNotesStatus');
				if(exactMatch == 'contractCoverageForm:hiddenNotesStatus'){
					var id = tagName.substring(38);
					var notesStatus = document.getElementById(tagName).value;
					var imageObj = document.getElementById('contractCoverageForm:noteImage' + id);
					if(notesStatus == "notes_exists<BR>"){
						imageObj.src = "../../images/notes_exist.gif";
					}else if(notesStatus == "no_notes<BR>"){
						imageObj.src = "../../images/page.gif";
					}
				}
			}
		}
	}

function deleteTier(tierId){
	var flag = confirm("Are you sure you want to delete?");
	if(flag){
		document.getElementById('contractCoverageForm:tierIdForDelete').value = tierId;
		submitLink('contractCoverageForm:deleteBenefitTier');
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
	var hidOutputValDesc = idArrayForm[0]+':'+'levelHidDescTier'+idArrayLevel[1];
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
			description = alignDescriptionLength(descLowerLevelvalue,16);
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

function descriptionChange(levelComponent){
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("lineFreqValue");
	var levelId = idArrayForm[0]+':'+'hiddenLevelId'+idArrayLevel[1];
	//var levelIdVal = document.getElementById(levelId).value;
	var descriptionId = idArrayForm[0]+':'+'levelDesc'+idArrayLevel[1];
	var description = trim(document.getElementById(descriptionId).innerText);
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
	//storeDataForUnsavedDataFinder();
	function storeDataForUnsavedDataFinder(){
		document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
		document.getElementById('contractCoverageForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	}
   	//if(document.getElementById('contractCoverageForm:duplicateData').value == ''){
    //}else{
	//	document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractCoverageForm:duplicateData').value;
	//	}
function loadDataOnNotesAttach(){
document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnUnLoad();
}
function DisableRightClick(event){
if (event.button==2){
	alert("Right Click is Disabled");
	return false;
}
}
//START CARS
//NON TIER PANEL 
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('contractCoverageForm:panelTable');
//if(null != tableObject){
//	var numberOfRows = tableObject.rows.length;
//	for(var i = 0; i < numberOfRows; i++){
//		var frequencyObject = document.getElementById('contractCoverageForm:lineFreqValue'+i);
//		if(null != frequencyObject){
//			var frequencyValue = document.getElementById('contractCoverageForm:lineFreqValue'+i).value;
//			if(null != frequencyValue && 0 == frequencyValue) {
//				document.getElementById('contractCoverageForm:levelDescInputText'+i).style.display = 'none';
//				document.getElementById('contractCoverageForm:levelDesc'+i).style.display = '';
//			}
//		}	
//	}
//}
//TIER PANEL
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('contractCoverageForm:panelTable1');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
		//var frequencyObject = document.getElementById('contractCoverageForm:levelFreqValueTier'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('contractCoverageForm:levelFreqValueTier'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//document.getElementById('contractCoverageForm:levelDescInputTextTier'+i).style.display = 'none';
				//document.getElementById('contractCoverageForm:tierLevelDescription'+i).style.display = '';
			//}
		//}	
	//}
//}
//END CARS
</script>

</HTML>
