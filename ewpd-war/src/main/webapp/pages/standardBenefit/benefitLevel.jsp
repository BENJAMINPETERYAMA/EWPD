<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<TITLE>Benefit Level</TITLE>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnPressingEnterKey('benefitLevelForm:createButton', 'benefitLevelForm:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form id="benefitLevelForm">
				<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>



						</TD>

						<td colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message
										value="#{benefitLevelBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Benefit Level" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								
								<td width="100%"></td>
							</tr>
						</table>						
						<script type="text/javascript">
							function RSCustomInterface(tbElementName){
								this.tbName = tbElementName;
								this.getText = getText;
								this.setText = setText;
						
							function getText(){
								if(!document.getElementById(this.tbName)) {
									alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
									return '';
								} else return document.getElementById(this.tbName).value;
							}
							function setText(text){
								if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
							}
						}
						</script>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<table border="0" cellpadding="5" cellspacing="0" width="100%">

							<tr>
								<td valign="top" width="250"><h:outputText
									value="Benefit Period" styleClass="outputText" /></td>
								<td align="left" width="200"><h:outputText
									value="#{benefitLevelBackingBean.benefitDefinition}"
									styleClass="outputText" /></td>

							</tr>
							<tr>
								<td valign="top" width="250" nowrap="nowrap"><h:outputText
									value="Benefit Term*"
									styleClass="#{benefitLevelBackingBean.requiredBenefitTerm ? 'mandatoryError': 'mandatoryNormal'}" /></td>
								<td align="left" width="200">
								<div id="benefitTermDiv" class="selectDataDisplayDiv"></div>
								<h:inputHidden id="benefitTermDivHidden"
									value="#{benefitLevelBackingBean.benefitTerm}" /></td>
								<!-- Chnage -->
								<td align="left" valign="top" width="20"><!-- End --> <h:commandButton
									alt="Select" id="termButton" image="../../images/select.gif"
									style="cursor: hand" onclick="termInfo();return false;"
									tabindex="1"></h:commandButton></td>
								<!-- Change -->
								<td align="left" width="9500"><h:selectBooleanCheckbox
									styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
									value="#{benefitLevelBackingBean.aggregateTerm}" tabindex="2"></h:selectBooleanCheckbox>Combine
								</td>
								<!-- End -->
							</tr>
							<tr>
								<td valign="top" width="250" nowrap="nowrap"><h:outputText
									value="Benefit Term Qualifier"
									styleClass="#{benefitLevelBackingBean.requiredBenefitQualifier ? 'mandatoryError': 'mandatoryNormal'}" />
								</td>
								<td align="left" width="200">
								<div id="benefitTermQualifierDiv" class="selectDataDisplayDiv"></div>
								<h:inputHidden id="benefitTermQualifierDivHidden"
									value="#{benefitLevelBackingBean.benefitTermQualifier}" /></td>
								<td align="left" valign="top" width="633"><h:commandButton
									alt="Select" id="qualifierButton"
									image="../../images/select.gif" style="cursor: hand"
									onclick="qualifierInfo();return false;" tabindex="3">
								</h:commandButton></td>
							<!-- Change: Aggregate Qualifier -->
							<td align="left" width="9500"><h:selectBooleanCheckbox
									styleClass="selectBooleanCheckbox" id="aggregateQualifierCheckBox"
									value="#{benefitLevelBackingBean.aggregateQualifier}" tabindex="4" ></h:selectBooleanCheckbox>Combine
							</td>
							<!-- End -->
							</tr>
							<tr>
								<td valign="top" width="250" nowrap="nowrap"><h:outputText
									value="Provider Arrangement*"
									styleClass="#{benefitLevelBackingBean.requiredPVA ? 'mandatoryError': 'mandatoryNormal'}" />
								</td>
								<td align="left" width="200">
								<div id="providerArrangementDiv" class="selectDataDisplayDiv"></div>
								<h:inputHidden id="providerArrangementDivHidden"
									value="#{benefitLevelBackingBean.providerArrangement}" /></td>
								<td align="left" valign="top" width="633"><h:commandButton
									alt="Select" id="pvaButton" image="../../images/select.gif"
									style="cursor: hand"
									onclick="providerArrangementInfo();return false;" tabindex="5"></h:commandButton>
								</td>
							</tr>

							<table border="0" cellpadding="5" cellspacing="0" width="100%">

								<tr>
									<td align="left" width="125"><h:commandButton id="createButton"
										onclick="unsavedDataFinder(this.id);return false;"
										alt="Create" value="Add" styleClass="wpdbutton" tabindex="6" />
									</td>

								</tr>
								
							</table>
							<br />
							<div id="displayPanel" ><h:panelGrid
								id="displayTable" 
								binding="#{benefitLevelBackingBean.displayPanel}">
							</h:panelGrid> </div>
							<div id="headerPanel" style="width:100%;"><h:panelGrid
								id="headerTable"
								binding="#{benefitLevelBackingBean.headerPanel}">
							</h:panelGrid> </div>
							<div id="associatedBenefitspanel" style="height:400px;overflow:auto;">
							<h:panelGroup id="associatedpanel"
								style="width:100%;"
								styleClass="dataTableColumnHeader">
							<h:dataTable headerClass="tableHeader" id="panelTable"  
									value="#{benefitLevelBackingBean.benefitLevelViewList}"
									 var="eachRow" width="100%"
									 cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
									rowClasses="dataTableOddRow,dataTableEvenRow">
									
							<h:column>
								<h:inputText maxlength="7" style="width : 1cm;"  rendered="#{!eachRow.benefitLine}"
									styleClass="formInputField" value="#{eachRow.benefitLevelSeq}"/>
							</h:column>
							<h:column>
									<h:inputText id="description"  styleClass="formInputField"  rendered="#{!eachRow.benefitLine}" 
										value="#{eachRow.benefitLevelDesc}" />
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" rendered="#{!eachRow.benefitLine}"
									value="#{eachRow.benefitTermsAsString}"/>
							</h:column>
							<h:column>
								<h:inputText style="width : 20px;"  styleClass="formInputField" maxlength="3" rendered="#{!eachRow.benefitLine && !eachRow.frequencyZero}"
										 value="#{eachRow.freequencyAsString}" onkeypress="return isNum(event);"/>
								<h:outputText  value="-" rendered="#{!eachRow.benefitLine&& !eachRow.frequencyZero}"/>
								<h:outputText  styleClass="formOutputColumnField" rendered="#{!eachRow.benefitLine}"
									value="#{eachRow.benefitQualifiersAsString}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.pvaCode}" rendered="#{eachRow.benefitLine}"/>	
							</h:column>
							<h:column>
								<h:selectOneMenu id="levelFormat"  styleClass="formOutputColumnField" 
										value="#{eachRow.dataTypeAsString}" rendered="#{!eachRow.benefitLine}" onchange="javascript:setFormatToLines(this.id);">
									<f:selectItems value="#{benefitLevelBackingBean.dateTypeList }"/>
								</h:selectOneMenu>
								<h:selectOneMenu id="lineFormat"  styleClass="formOutputColumnField" value="#{eachRow.benefitLineDataTypeIdAsString}" rendered="#{eachRow.benefitLine}">
									<f:selectItems value="#{benefitLevelBackingBean.dateTypeList }"/>
								</h:selectOneMenu>
							</h:column>
							<h:column>
								<h:inputText id="benefitVal"  style="width : 2cm;" styleClass="formInputField" 
										value="#{eachRow.benefitLineBenefitValue}" rendered="#{!eachRow.benefitLine}" onblur="setBenefitValToLines(this.id);" />	
								<h:inputText id="benefitLineVal" style="width : 2cm;" styleClass="formInputField" 
										value="#{eachRow.benefitLineBenefitValue}" rendered="#{eachRow.benefitLine}"/>	

							</h:column>
							<h:column>
								<h:inputText  styleClass="formInputField"  rendered="#{eachRow.benefitLine}"
										style="width : 4cm;" value="#{eachRow.benefitLineReference}" id="reference"/>
								<h:inputHidden value="#{eachRow.benefitLineReferenceCode}"></h:inputHidden>
								<h:inputHidden id="hiddenReferenceCode" value="#{eachRow.hiddenReference}" />
									<h:commandButton alt="select"
											id="addReference"
											image="../../images/select.gif"
											rendered="#{eachRow.benefitLine}" onclick="#{eachRow.referencePopUpUrl}" />
								
								<h:commandButton alt="notes"
											id="attachNote"
											image="#{eachRow.noteExist?'../../images/notes_exist.gif':'../../images/page.gif'}"
											onclick="#{eachRow.notePopUpURl}"
											rendered="#{eachRow.benefitLine}" />
							</h:column>
							<h:column>
								<h:selectBooleanCheckbox id="levelDelete" value="#{eachRow.selectForDelete}" rendered="#{!eachRow.benefitLine}" onclick="javascript:setDeleteStatusToLines(this.id);"></h:selectBooleanCheckbox>
								<h:selectBooleanCheckbox id="lineDelete" value="#{eachRow.selectForDelete}" rendered="#{eachRow.benefitLine}" onclick="javascript:isAnyCheckBoxChecked();"></h:selectBooleanCheckbox>
							</h:column>
						</h:dataTable>
						</h:panelGroup>
 							<h:inputHidden id="stanBenftId"
								value="#{benefitLevelBackingBean.standardBenefitIdForRefData}"></h:inputHidden>
							</div>
							
							<div id="saveButtonDiv">
							<br>
							<br>
							<h:commandButton id="saveButton" styleClass="wpdbutton"
								value="Save" alt="Save"   onclick="savePage(this.id);return false; "></h:commandButton>
							<h:commandLink id="saveLink" action="#{benefitLevelBackingBean.saveBenefitLevels}">
								<f:verbatim> &nbsp;</f:verbatim>

							</h:commandLink>
							<h:commandButton id="deleteButton" styleClass="wpdbutton"
								value="Delete" alt="Delete"
								onclick="unsavedDataFinder(this.id);return false;">
							</h:commandButton> 
								<%--action="#{benefitLevelBackingBean.deleteLevelsAndLines}" --%>
						</div>
							<h:commandLink id="getHidden"
								action="#{benefitLevelBackingBean.loadBenefitLevelFromBenefitAdministrationTab}"
								style="hidden">
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink>
							<h:commandLink id="deleteLevelsAndLines" 
								action="#{benefitLevelBackingBean.deleteLevelsAndLines}"
								style="hidden">
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink>
							<h:commandLink id="addBenefitLevel"
								action="#{benefitLevelBackingBean.createBenefitLevel}" 	
								style="hidden">
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink>
							<h:inputHidden id="termHidden"
									value="#{benefitLevelBackingBean.selectedTerm}" />
							<h:inputHidden id="hiddenDt"
									 />
							<h:inputHidden id="numOfLevels"
									value="#{benefitLevelBackingBean.numOfLevels}" />
							<h:inputHidden id="duplicateData"
					value="#{benefitLevelBackingBean.duplicateData}"></h:inputHidden> 
							<h:inputHidden id="panelDataNoDel" value="#{benefitLevelBackingBean.panelDataNoDel}"></h:inputHidden>	
							<h:inputHidden id="panelDataOnlyDel" value="#{benefitLevelBackingBean.panelDataOnlyDel}"></h:inputHidden>
							<h:inputHidden id="panelData" value="#{benefitLevelBackingBean.panelData}" />
							<h:inputHidden id="addButtonInvoked"
									value="#{benefitLevelBackingBean.addButtonInvoked}" /> 				
			<h:commandLink id="refresh" style="display:none; visibility: hidden;" 
					action="#{benefitLevelBackingBean.reloadPage}"> <f:verbatim /></h:commandLink>

						</table>
						
<%int numberOfLevels = 0;
if(null != request.getSession().getAttribute("numOfLevels") && !"".equals(request.getSession().getAttribute("numOfLevels")))
	numberOfLevels = ((Integer)(request.getSession().getAttribute("numOfLevels"))).intValue();
if(numberOfLevels > 0){
for(int j = 0;j < numberOfLevels;j++){ 
String val = "rapidSpellWebLauncher" +j ;
String descField = "benefitLevelForm:panelTable:"+j+":description";
if(j != (numberOfLevels-1)){%>
				<RapidSpellWeb:rapidSpellWebLauncher
					id="<%=val%>"
					textComponentName="<%=descField%>"
					rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Level Description"
					modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
					showNoErrorsMessage="False" showFinishedMessage="False"
					includeUserDictionaryInSuggestions="True"
					allowAnyCase="True" allowMixedCase="True"
					windowWidth="570" windowHeight="300"
					modal="False" showButton="False"
					windowX="-1" warnDuplicates="False"
					textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
<%}
else{%>
<RapidSpellWeb:rapidSpellWebLauncher
					id="<%=val%>"
					textComponentName="<%=descField%>"
					rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Benefit Level Description"
					modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
					showNoErrorsMessage="False" showFinishedMessage="False"
					includeUserDictionaryInSuggestions="True"
					allowAnyCase="True" allowMixedCase="True"
					finishedListener="spellFin" 
					windowWidth="570" windowHeight="300"
					modal="False" showButton="False"
					windowX="-1" warnDuplicates="False"
					textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
<%}}}%>
						</h:form>
						</TD>
					</TR>
					<tr>
						<td><%@ include file="../navigation/bottom.jsp"%></td>
					</tr>
				</TABLE>
	</BODY>
</f:view>
<script language="JavaScript">

function setBenefitValToLines(ibjID) {
	var splitIds = ibjID.split(":");
	var itr = parseInt(splitIds[2]) + 1;
	var idToSearch = splitIds[0]+":"+splitIds[1]+":";
	while(true) {
		var idForSerch = idToSearch+itr+":benefitLineVal";
		var obj = document.getElementById(idForSerch);
		if(obj == null){
			break;
		} else {
			itr = parseInt(itr) + 1;
			obj.value = document.getElementById(ibjID).value;
		}
	}
}

function setDeleteStatusToLines(ibjID) {
	var splitIds = ibjID.split(":");
	var itr = parseInt(splitIds[2]) + 1;
	var idToSearch = splitIds[0]+":"+splitIds[1]+":";
	while(true) {
		var idForSerch = idToSearch+itr+":lineDelete";
		var obj = document.getElementById(idForSerch);
		if(obj == null){
			break;
		} else {
			itr = parseInt(itr) + 1;
			if(document.getElementById(ibjID).checked){
				document.getElementById('benefitLevelForm:deleteButton').disabled = false;
				obj.checked = true ;
			} else {
				obj.checked = false ;
				document.getElementById('benefitLevelForm:deleteButton').disabled = true;
			}
		}
	}
}

function setFormatToLines(ibjID) {
	var splitIds = ibjID.split(":");
	var itr = parseInt(splitIds[2]) + 1;
	var idToSearch = splitIds[0]+":"+splitIds[1]+":";
	while(true) {
		var idForSerch = idToSearch+itr+":lineFormat";
		var obj = document.getElementById(idForSerch);
		if(obj == null){
			break;
		} else {
			itr = parseInt(itr) + 1;
			obj.value = document.getElementById(ibjID).value;
		}
	}
}

function runSpellCheck(){
	var numberOfLevelsObject = document.getElementById('benefitLevelForm:numOfLevels');
	var rswlCntrlsField = "";
	if(null != numberOfLevelsObject && numberOfLevelsObject.value != 0){
		if(numberOfLevelsObject.value > 1){
				rswlCntrlsField = "rapidSpellWebLauncher0";
				for(var i = 1; i < numberOfLevelsObject.value; i++){
					 	rswlCntrlsField = rswlCntrlsField +",rapidSpellWebLauncher"+i;
				}
		}
		else
			rswlCntrlsField = "rapidSpellWebLauncher0";
		}
		var rswlCntrls = rswlCntrlsField.split(",");
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
	return false;
}
function spellFin(cancelled){


		document.getElementById('benefitLevelForm:saveLink').click();	

}

function setDataType(id){
		var hiddenFieldName = id;
		var hiddenFieldVal = document.getElementById(hiddenFieldName).options[document.getElementById(hiddenFieldName).selectedIndex].value;
		document.getElementById('benefitLevelForm:hiddenDt').value =hiddenFieldVal;
}

var selectedTermObject = document.getElementById('benefitLevelForm:termHidden');
if(selectedTermObject.value != 'ALL THE TERMS'){
	var tableObject = document.getElementById('benefitLevelForm:panelTable');
	if(null != tableObject){
		var numberOfRows = tableObject.rows.length;
		for(var i = 0; i < numberOfRows; i++){
			var sequenceObject = document.getElementById('benefitLevelForm:SeqNo'+i);
			var hiddenSequenceObject = document.getElementById('benefitLevelForm:hiddenSequence'+i);
			if(null != sequenceObject && null != hiddenSequenceObject) {
				sequenceObject.disabled = true;
				sequenceObject.value = hiddenSequenceObject.value;
			}	
		}
	}
}
IGNORED_FIELD1='benefitLevelForm:selectTerm';
IGNORED_FIELD2='benefitLevelForm:termHidden';
IGNORED_FIELD3='benefitLevelForm:duplicateData';
IGNORED_FIELD4='benefitLevelForm:panelDataOnlyDel';
IGNORED_FIELD5='benefitLevelForm:panelData';
IGNORED_FIELD6='benefitLevelForm:panelDataNoDel';

	if(document.getElementById('benefitLevelForm:selectTerm')!= null){
		document.getElementById('benefitLevelForm:termHidden').value = 
								document.getElementById('benefitLevelForm:selectTerm').value;
	}
	var tableObject = document.getElementById('benefitLevelForm:panelTable');
	if(tableObject.rows.length > 1){
	 	var divObj = document.getElementById('associatedBenefitspanel');
	}
	else{
		var divObj = document.getElementById('associatedBenefitspanel');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		var divObj1 = document.getElementById('displayPanel');
		divObj1.style.visibility = "hidden";
		// divObj1.style.height = "0px";
		var divObj2 = document.getElementById('headerPanel');
		divObj2.style.visibility = "hidden";
		// divObj2.style.height = "0px";
	}

	copyHiddenToDiv_ewpd('benefitLevelForm:benefitTermDivHidden','benefitTermDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitLevelForm:benefitTermQualifierDivHidden','benefitTermQualifierDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitLevelForm:providerArrangementDivHidden','providerArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitLevelForm:providerArrangementDivHidden','providerArrangementDiv',2,2); 

	if(document.getElementById('benefitLevelForm:panelTable').rows.length > 1) {
			var relTblWidth = document.getElementById('benefitLevelForm:panelTable').offsetWidth;
			document.getElementById('benefitLevelForm:panelTable').width = relTblWidth+"px";
			document.getElementById('benefitLevelForm:headerTable').width = relTblWidth+"px";
			setColumnWidth('benefitLevelForm:panelTable','7%:16%:10%:16%:5%:12%:8%:20%:6%');	
			setColumnWidth('benefitLevelForm:headerTable','7%:16%:10%:16%:5%:12%:8%:20%:6%');
			document.getElementById('benefitLevelForm:deleteButton').disabled = true;
		}else{
			document.getElementById('saveButtonDiv').style.visibility = "hidden";
			syncTables('benefitLevelForm:headerTable','benefitLevelForm:panelTable');
			setColumnWidth('benefitLevelForm:panelTable','7%:16%:10%:16%:5%:12%:8%:20%:6%');	
			setColumnWidth('benefitLevelForm:headerTable','7%:16%:10%:16%:5%:12%:8%:20%:6%');		
}

function deleteBenefitLevel(){
	var message = "The admin option attached to benefit level,if any, will also get deleted. Do you want to continue?";
	var confirmValue = confirm(message);
	return confirmValue;
}

function deleteBenefitLine(){
	var message = "Do you want to delete the selected benefit line?";
	var confirmValue = confirm(message);
	return confirmValue;
}
//Modified Script For Production Fix
function selectValuesForCorrespondingBenefitLine(levelComponent){
	var levelComponentId = levelComponent.id;
	var idArray = levelComponentId.split("A");		
	var levelComponentValue = levelComponent.value;
	var levelPageId = idArray[1];
	var componentName = idArray[0];
	var numberOfLinesInALevel = idArray[2];
	var startingIndex = idArray[3];
	var maxIndex = parseInt(numberOfLinesInALevel) + parseInt(startingIndex);
	for(i = parseInt(startingIndex); i <= parseInt(maxIndex); i++){
		var dataTypeComponents = document.getElementById(componentName+"A"+levelPageId+"A"+numberOfLinesInALevel+"A"+i);
		if(dataTypeComponents != null && document.getElementById('benefitLevelForm:SeqNo'+startingIndex) != null){
			dataTypeComponents.value = levelComponentValue;
		}
	}
}

function retrieveByTerm(){
	document.getElementById('benefitLevelForm:termHidden').value= 
											document.getElementById('benefitLevelForm:selectTerm').value;
	return navigatePageActionSubmit('benefitLevelForm:getHidden');
	//document.getElementById('benefitLevelForm:getHidden').click();
}

// **Change**

function notesAttachmentPopup(url, benefitTermCode, benefitQualifierCode, benefitLineId,imageId){
	var retValue = window.showModalDialog(url +getUrl()+ "?" + "&temp=" + Math.random() + "&bnftLineId=" + benefitLineId + "&bnftQualifierCode=" + benefitQualifierCode + "&bnftTermCode=" + benefitTermCode, self, "dialogHeight:800px;dialogWidth:1100px;resizable=false;status=no;");	
	var imageObj = document.getElementById(imageId);
	if(retValue == "notes_exists"){
		imageObj.src = "../../images/notes_exist.gif";
	}else if(retValue == "no_notes"){
		imageObj.src = "../../images/page.gif";
	}
	//submitLink('benefitLevelForm:refresh');
	//document.getElementById('benefitLevelForm:hiddenNotesStatus' + imageId).value = retValue;
	return false;
}

// Function to submit page while hitting 'Enter Key'  
function submitOnPressingEnterKey(addButton, saveButton){
	var addButton = document.getElementById(addButton);
	var saveButton = document.getElementById(saveButton);

	// Do not submit if 'Enter Key' pressed in Text area.
	var srcElement = window.event.srcElement;
	if( srcElement != null && srcElement != undefined && 
		srcElement.type != undefined && srcElement.type =='textarea') {
		return true;
	}

	if(window.event.keyCode==13) {
		var tableObject = document.getElementById('benefitLevelForm:panelTable');
		if(tableObject.rows.length > 1){
		 	var divObj = document.getElementById('associatedBenefitspanel');
			saveButton.click();
		}else{
			addButton.click();
		}
		return false;
	}
	return true;
}
function termInfo(){
	var stanBenftId = document.getElementById('benefitLevelForm:stanBenftId').value;
	ewpdModalWindow_ewpd('../standardbenefitpopups/benefitLevelTermPopUp.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'Term'+'&entityId='+ stanBenftId+ '&entityType=' + 'stdbenefit'+'&temp=' + Math.random(),'benefitTermDiv','benefitLevelForm:benefitTermDivHidden',2,2); 
}

function qualifierInfo(){
	var stanBenftId = document.getElementById('benefitLevelForm:stanBenftId').value;
	ewpdModalWindow_ewpd('../standardbenefitpopups/benefitLevelQualifierPopUp.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'Qualifier'+'&entityId='+ stanBenftId+ '&entityType=' + 'stdbenefit'+'&temp=' + Math.random(),'benefitTermQualifierDiv','benefitLevelForm:benefitTermQualifierDivHidden',2,2); 
}

function providerArrangementInfo(){
	var stanBenftId = document.getElementById('benefitLevelForm:stanBenftId').value;
	ewpdModalWindow_ewpd('../standardbenefitpopups/benefitLevelProviderArrangementPopUp.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'ProviderArrangement'+'&entityId='+ stanBenftId+ '&entityType=' + 'stdbenefit'+'&temp=' + Math.random(),'providerArrangementDiv','benefitLevelForm:providerArrangementDivHidden',2,2); 
}

	function setTitle(){
		var e = window.event;
		var button_id = e.srcElement.id;
		if(document.getElementById(button_id).title != document.getElementById(button_id).value)
			document.getElementById(button_id).title = document.getElementById(button_id).value;
	}
function syncTables(){
			var relTblWidth = document.getElementById('benefitLevelForm:panelTable').offsetWidth;
			document.getElementById('associatedBenefitspanel').width = relTblWidth ;
			var relTblWidth1 = document.getElementById('benefitLevelForm:headerTable').offsetWidth;
			document.getElementById('headerPanel').width = relTblWidth1 ;
		}
/*if(document.getElementById('benefitLevelForm:panelTable')!= null){
		alert("inside");
		document.getElementById('benefitLevelForm:panelTable').onresize = syncTables;
		var size = document.getElementById('benefitLevelForm:panelTable').rows.length;
		var relTblWidth = document.getElementById('associatedBenefitspanel').offsetWidth;
		var relTblWidth1 = document.getElementById('headerPanel').offsetWidth;
		if(size<=14){
				document.getElementById('benefitLevelForm:panelTable').width = relTblWidth+"px";
				document.getElementById('benefitLevelForm:headerTable').width = relTblWidth1+"px";
		}else{
			syncTables();

		}
	}*/
// **End**

// To select the corresponding the lines
function selectTheCorrespondingBenefitLines(levelNumber, noOfLines){
	var levelId = "benefitLevelForm:levelCheckBox" + levelNumber;
	var lineId;
	for( i = 1; i <= parseInt(noOfLines); i++ ){
		lineId = "benefitLevelForm:lineCheckBox" + levelNumber + "A" + i;
		document.getElementById(lineId).checked = document.getElementById(levelId).checked;
	}
	// Validate Delete Button
	var isChecked = validateDelete();
	document.getElementById('benefitLevelForm:deleteButton').disabled = !isChecked;
}

// To select the corresponding level
function selectTheCorrespondingBenefitLevel(levelNumber, lineNumber, noOfLines){
	var levelId = "benefitLevelForm:levelCheckBox" + levelNumber;
	var lineId;
	var count = 0;
	for( i = 1; i <= parseInt(noOfLines); i++ ){
		lineId = "benefitLevelForm:lineCheckBox" + levelNumber + "A" + i;
		if(document.getElementById(lineId).checked){
			count++;
		}
	}
	if(parseInt(count) == parseInt(noOfLines)){
		document.getElementById(levelId).checked = true;
	}else{
		document.getElementById(levelId).checked = false;
	}
	// validate delete button
	var isChecked = validateDelete();
	document.getElementById('benefitLevelForm:deleteButton').disabled = !isChecked;
}

// Enable or Disable the delete button.
function validateDelete(){
	var tags = document.getElementsByTagName('input');
	var isSelected = false;
	for (var j = 0; j < tags.length; j++) 
	{
		tagType = tags[j].type;			
		if(tagType == 'checkbox')
		{
			var tagName = tags[j].name;
			if(tagName != 'benefitLevelForm:aggregateTermCheckBox' 
				&& tagName != 'benefitLevelForm:aggregateQualifierCheckBox'){
				if(tags[j].checked == true)
				{
					isSelected = true;
					break;
				}
			}
		}
	}
	return isSelected;
}

// function to delete the levels and lines.
function deleteLevelsAndLines(){
	var tags = document.getElementsByTagName('input');
	var isSelected = false;
	for (var j = 0; j < tags.length; j++) 
		{
			tagType = tags[j].type;			
			if(tagType == 'checkbox')
			{
				var tagName = tags[j].name;
				if(tagName != 'benefitLevelForm:aggregateTermCheckBox' 
					&& tagName != 'benefitLevelForm:aggregateQualifierCheckBox'){
					if(tags[j].checked == true)
					{
						isSelected = true;
						break;
					}
				}
			}
		}
	if(isSelected){
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
			document.getElementById('benefitLevelForm:deleteLevelsAndLines').click();
		}
		else{
			return false;
		}
	}
	else
	{
		alert("Please select atleast one line to delete.");
		return false;
	}
}

function isAnyCheckBoxChecked(){
	var tags = document.getElementsByTagName('input');
	var isSelected = false;
	for (var j = 0; j < tags.length; j++) 
		{
			tagType = tags[j].type;			
			if(tagType == 'checkbox')
			{
				var tagName = tags[j].name;
				if(tagName != 'benefitLevelForm:aggregateTermCheckBox' 
					&& tagName != 'benefitLevelForm:aggregateQualifierCheckBox'){
					if(tags[j].checked == true)
					{
						isSelected = true;
						break;
					} else {
						isSelected = false;
					}
				}
			}
		}
		document.getElementById('benefitLevelForm:deleteButton').disabled = !isSelected;
}


	
// Method to make the reference input field as ReadOnly, when page loads
//var len =  document.getElementById("benefitLevelForm:panelTable").rows.length;
//for (var j=0;j<=len;j++) {			
			//if(null != document.getElementById("benefitLevelForm:Reference"+j))
			//document.getElementById("benefitLevelForm:Reference"+j).readOnly = true;
		//}


setTimeout (checkForPanel,500);
function checkForPanel(){
	var tableObject = document.getElementById('benefitLevelForm:panelTable');
	if(tableObject.rows.length >0){
		var isChecked = validateDelete();
		document.getElementById('benefitLevelForm:deleteButton').disabled = !isChecked;
		var onLoadPanelData = getPanelData('benefitLevelForm:panelTable');
		document.getElementById('benefitLevelForm:panelData').value = onLoadPanelData;

		var onLoadPanelData = getPanelDataNoDel('benefitLevelForm:panelTable');
		document.getElementById('benefitLevelForm:panelDataNoDel').value = onLoadPanelData;

		var onLoadPanelData = getPanelDataOnlyDel('benefitLevelForm:panelTable');
		document.getElementById('benefitLevelForm:panelDataOnlyDel').value = onLoadPanelData;		
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
      }else
		return dataOnScreen;
}
function getPanelDataNoDel(list){
	var tagNames = list.split(',');
	var dataOnScreen = "";
	var tableObject = document.getElementById(tagNames[0]);
	var rows = tableObject.rows.length;
	if(rows >0){
            var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
                  for(var j=0;j<columns-1;j++){
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
function getPanelDataOnlyDel(list){
	var tagNames = list.split(',');
	var dataOnScreen = "";
	var tableObject = document.getElementById(tagNames[0]);
	var rows = tableObject.rows.length;
	if(rows >0){
            var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
                  for(var j=columns-1;j<columns;j++){
                        if(null != tableObject.rows[i].cells[j].children[0]){
								if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
									dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
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
 	if(buttonId == 'benefitLevelForm:deleteButton'){
		var txtData = getPanelDataNoDel('benefitLevelForm:panelTable');
		if(txtData != document.getElementById('benefitLevelForm:panelDataNoDel').value ){
			var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
			if (retValue){
				deleteLevelsAndLines();
			}
		}else{
			deleteLevelsAndLines();
		}
	}else if(buttonId == 'benefitLevelForm:saveButton'){
		var txtData = getPanelDataOnlyDel('benefitLevelForm:panelTable');
		if(txtData != document.getElementById('benefitLevelForm:panelDataOnlyDel').value ){
			var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
			if (retValue){
				//runSpellCheck();
				submitLink('benefitLevelForm:saveLink');
			}
		}else{
			 //runSpellCheck();
			submitLink('benefitLevelForm:saveLink');
		}
	}else if(buttonId == 'benefitLevelForm:createButton'){

		document.getElementById('benefitLevelForm:addButtonInvoked').value = 'Y';
		var tableObj = document.getElementById('benefitLevelForm:panelTable');
		if(tableObj.rows.length>0){
				var panelData = getPanelData('benefitLevelForm:panelTable');
				if(panelData != document.getElementById('benefitLevelForm:panelData').value ){
					var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
					if (retValue){
						submitLink('benefitLevelForm:addBenefitLevel');
					}
				}else{
					submitLink('benefitLevelForm:addBenefitLevel');
				}
		}else{
			submitLink('benefitLevelForm:addBenefitLevel');
		}
	}
}

function savePage(objectId){	
	if(document.getElementById('benefitLevelForm:addButtonInvoked').value == 'Y'){
		unsavedDataFinder(objectId);
	}else{
		savePageAction(objectId);
	}
	document.getElementById('benefitLevelForm:addButtonInvoked').value = 'N'; 	
} 

function savePageAction(objectId){	
	var obj = document.getElementById(objectId);
	if((!isEwpdbDataModifed() && document.getElementById('benefitLevelForm:addButtonInvoked').value == 'N') 
		|| (!isEwpdbDataModifed() && document.getElementById('benefitLevelForm:addButtonInvoked').value == ''))
	{		
		alert('No modifications to be updated.');
	}
	else{	
		document.getElementById('benefitLevelForm:saveLink').click();		
		//runSpellCheck();
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

function descriptionChange(levelComponent){
	var frequencyId = levelComponent.id;
	var levelComponentValue = levelComponent.value;
	var idArrayForm = frequencyId.split(":");
	var idArrayLevel = frequencyId.split("Frequency");
	var levelOutputId = idArrayForm[0]+':'+'outputDescription'+idArrayLevel[1];
	var levelInputId = idArrayForm[0]+':'+'Description'+idArrayLevel[1];
	if(levelComponentValue == '0'){
		document.getElementById(levelOutputId).style.display = '';
		document.getElementById(levelInputId).style.display = 'none';		
	}else{		
		document.getElementById(levelOutputId).style.display = 'none';
		document.getElementById(levelInputId).style.display = '';			
	}
}
//START CARS
//Description : To make the description non editable if the frequency values is zero.
//var tableObject = document.getElementById('benefitLevelForm:panelTable');
//if(null != tableObject){
	//var numberOfRows = tableObject.rows.length;
	//for(var i = 0; i < numberOfRows; i++){
		//var frequencyObject = document.getElementById('benefitLevelForm:Frequency'+i);
		//if(null != frequencyObject){
			//var frequencyValue = document.getElementById('benefitLevelForm:Frequency'+i).value;
			//if(null != frequencyValue && 0 == frequencyValue) {
				//document.getElementById('benefitLevelForm:Description'+i).style.display = 'none';
				//document.getElementById('benefitLevelForm:outputDescription'+i).style.display = '';
			//}else{
				//document.getElementById('benefitLevelForm:outputDescription'+i).style.display = 'none';
				//document.getElementById('benefitLevelForm:Description'+i).style.display = '';
			//}
		//}	
	//}
//}
//END CARS



</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitLevel" /></form>
<form name="ewpdDataChangedForm">
	<input type="hidden" name="ewpdOnloadData" value="" />
</form>
<script>
setTimeout ( storeDataForUnsavedDataFinder, 500 );

	function storeDataForUnsavedDataFinder(){
		document.ewpdDataChangedForm.ewpdOnloadData.value = getModifiedDataOnLoad();
		document.getElementById('benefitLevelForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	}

</script>
</HTML>
