<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {

}.shortDescriptionColumn {
	text-align:center;
	vertical-align: middle;
}

</style>

<TITLE>Create Question</TITLE>
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
</HEAD>
<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><h:inputHidden id="dummy"
				value=" #{saveQuestionBackingBean.breadCrumpCreate}"></h:inputHidden>
			<jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="createQuestionForm">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{saveQuestionBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
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
						</script> <!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Question" /></td>
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
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="700">
							<TBODY>
								<TR>
									<TD width="20%" height="37"><h:outputText id="question"
										value="Question*"
										styleClass="#{saveQuestionBackingBean.requiredQuestion ? 'mandatoryError' : 'mandatoryNormal'}" />
									</TD>
									<TD height="30%" width="177"><h:inputText
										styleClass="formInputField" id="questonText"
										value="#{saveQuestionBackingBean.question}" maxlength="250"
										tabindex="1" /></TD>
									<TD width="10%"></TD>
									<TD width="40%"></TD>
								</TR>
								<tr>
									<TD width="20%"><h:outputText id="funcdomain"
										value="Functional Domain*"
										styleClass="#{saveQuestionBackingBean.domainInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</td>
									<h:inputHidden id="functionalDomainCD"
										value="#{saveQuestionBackingBean.functionalDomain}"></h:inputHidden>
									<TD width="30%">
									<DIV id="FunctionalDomainDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="Domain" image="../../images/select.gif"
										style="cursor: hand"
										onclick="domainClick('../popups/functionalDomainPopup.jsp','FunctionalDomainDiv','createQuestionForm:functionalDomainCD',3,1); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%"></TD>
								</tr>
								<TR>
									<TD width="20%"><h:outputText
										styleClass="#{saveQuestionBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
										id="TermStar" value="Term" /></TD>
									<TD width="30%">
									<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtTerm"
										value="#{saveQuestionBackingBean.term}"></h:inputHidden></TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="TermButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="loadSearchPopup('../popups/searchTermMultiSelect.jsp','searchTerm','Term',
                                                         'Term Search Popup','TermDiv','createQuestionForm:txtTerm'); return false;">
									</h:commandButton></TD>
									<td align="left" width="40%"><h:selectBooleanCheckbox
										styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
										value="#{saveQuestionBackingBean.aggregateTerm}" tabindex="2"
										readonly></h:selectBooleanCheckbox>Combine</td>
								</TR>
								<TR>
									<TD width="20%"><h:outputText
										styleClass="#{saveQuestionBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
										id="QualifierStar" value="Qualifier" />&nbsp;&nbsp;</TD>
									<TD width="30%">
									<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtQualifier"
										value="#{saveQuestionBackingBean.qualifier}"></h:inputHidden>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="QualifierButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="loadSearchPopup('../popups/searchQualifierMultiselectForQstn.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','createQuestionForm:txtQualifier'); return false;"
										tabindex="13"></h:commandButton></TD>
									<td align="left" width="40%"><h:selectBooleanCheckbox
										styleClass="selectBooleanCheckbox"
										id="aggregateQualifierCheckBox"
										value="#{saveQuestionBackingBean.aggregateQualifier}"
										tabindex="4"></h:selectBooleanCheckbox>Combine</td>
								</TR>

								<!-- Added PVA -->
								<tr>
									<TD width="20%"><h:outputText styleClass="#{saveQuestionBackingBean.requiredPVA ? 'mandatoryError': 'mandatoryNormal'}" id="pva"
										value="Provider Arrangement" /></td>
									<TD width="30%">
									<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="ProviderArrangementButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="pvaClick('../popups/providerArrangementPopupSingleSelect.jsp','ProviderArrangementDiv','createQuestionForm:providerArrangement',2,1); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%"></TD>
								</tr>
								<TR>
									<TD width="20%" height="25"><h:outputText id="dataType"
										value="DataType*"
										styleClass="#{saveQuestionBackingBean.requiredDataType ? 'mandatoryError' : 'mandatoryNormal'}" />
									</TD>
									<TD width="30%" width="150"><h:selectOneMenu id="dataTypeName"
										value="#{saveQuestionBackingBean.dataTypeId}"
										onchange="confirmBeforeClear()" tabindex="2">
										<f:selectItems id="dataTypeList"
											value="#{ReferenceDataBackingBeanCommon.dataTypeListForCombo}" />
									</h:selectOneMenu></TD>
									<TD width="10%"></TD>
									<TD width="40%"></TD>
								</TR>
								<tr>
									<TD width="20%"><h:outputText id="spsParam" value="Reference"
										styleClass="#{saveQuestionBackingBean.referenceInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</td>
									<h:inputHidden id="spsParamCD"
										value="#{saveQuestionBackingBean.spsRefernceId}"></h:inputHidden>
									<TD width="30%">
									<DIV id="SPSRefDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="ref" image="../../images/select.gif" style="cursor: hand"
										onclick="spsRefClick('SPSRefDiv','createQuestionForm:spsParamCD'); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%"></TD>
								</tr>

								<TR id="benefitLineDataTypeTR" style="display:none;">
									<TD width="20%"><h:outputText id="benefitLineDataTypeTxt"
										value="Benefit Line Data Type*" 
										styleClass="#{saveQuestionBackingBean.requiredBenefitLineDataType ? 'mandatoryError': 'mandatoryNormal'}" />
										</td>
									<TD width="30%">
									<DIV id="benefitLineDataTypeDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="BenefitLineDataTypeButton" image="../../images/select.gif"
										style="cursor: hand"
										
										onclick="loadSearchPopup('../standardbenefitpopups/benefitDataTypePopup.jsp','searchBenefitLineDataType','BenefitLineDataType',
                                        'Benefit Line Data Type Search Popup','benefitLineDataTypeDiv','createQuestionForm:benefitLineDataType'); return false;"										
										tabindex="15"></h:commandButton></TD>
									<TD width="40%"></TD>
								</tr>

								<h:commandLink id="clearLink"
									action="#{saveQuestionBackingBean.clearAnswersList}">
									<f:verbatim />
								</h:commandLink>
								<TR>
									<TD colspan="3" height="39">
									<TABLE id="selectTable" border="0" cellspacing="0"
										cellpadding="0" class="outputText">
										<TR>
											<TD width="134" height="37"><h:outputText id="answers"
												value="Possible Answers*"
												styleClass="#{saveQuestionBackingBean.requiredAnswers ? 'mandatoryError' : 'mandatoryNormal'}" /></TD>
											<TD width="190" height="37"><h:inputText
												styleClass="formInputField" id="answerText"
												value="#{saveQuestionBackingBean.possibleAnswer}"
												maxlength="250" tabindex="3" /></TD>
											<TD width="40" height="37"><h:commandButton id="addButton"
												value="Add" styleClass="wpdButton"
												onclick="addAnsToHidden();copyDataType();return true;"
												action="#{saveQuestionBackingBean.addAnswer}" tabindex="4">
											</h:commandButton></TD>

										</TR>


									</TABLE>
									</TD>
								</TR>

								<TR>
									<TD width="138">&nbsp;</TD>
								</TR>

							</TBODY>
						</TABLE>
						<DIV id="associatedAnswersDiv">
						<TABLE id="associatedAnswersTable" width="50%" cellpadding="0"
							cellspacing="0" bgcolor="#cccccc">
							<TR>
								<TD colspan="2" align="left"><strong>Associated Answers</strong></TD>
							</TR>
							<TR>
								<TD colspan="2">
								<TABLE id="questionHeaderTable" cellspacing="1"
									bgcolor="#cccccc" cellpadding="3" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left"><h:outputText value="Answer"></h:outputText></td>
											<td align="right"><h:outputText value="Delete"></h:outputText></td>
										</TR>
									</TBODY>
								</TABLE>
								</TD>
							</TR>

							<TR>
								<TD colspan="2" width="50%"><h:dataTable styleClass="outputText"
									headerClass="dataTableHeader" id="answerTable" var="eachRow"
									cellpadding="3" width="100%" cellspacing="1"
									value="#{saveQuestionBackingBean.answerList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
									rendered="#{saveQuestionBackingBean.answerList != null}">
									<h:column>
										<h:inputHidden id="answerDesc"
											value="#{eachRow.possibleAnswerDesc}"></h:inputHidden>
										<h:outputText id="answer"
											value="#{eachRow.possibleAnswerDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:selectBooleanCheckbox id="deleteChkBox"
											onclick="enableDisableDelete('createQuestionForm:answerTable',1,0,'createQuestionForm:deleteButton');">
										</h:selectBooleanCheckbox>
									</h:column>
								</h:dataTable></TD>
							</TR>

						</TABLE>
						<br>

						</DIV>
						<TABLE cellspacing="0" cellpadding="0">
							<TR style="height:2px">
								<TD>&nbsp;</TD>
							</TR>
							<RapidSpellWeb:rapidSpellWebLauncher id="rapidSpellWebLauncher1"
								textComponentName="createQuestionForm:questonText"
								rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Question"
								modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
								showNoErrorsMessage="False" showFinishedMessage="False"
								includeUserDictionaryInSuggestions="True" allowAnyCase="True"
								allowMixedCase="True" finishedListener="spellFin"
								windowWidth="570" windowHeight="300" modal="False"
								showButton="False" windowX="-1" warnDuplicates="False"
								textComponentInterface="Custom">
							</RapidSpellWeb:rapidSpellWebLauncher>
							<TR>
								<TD>&nbsp; <h:commandButton value="Create" id="createQuestion"
									styleClass="wpdButton" onclick="return runSpellCheck();"
									tabindex="5">
								</h:commandButton> <h:commandLink id="create" style="hidden"
									action="#{saveQuestionBackingBean.create}">
								</h:commandLink> <h:commandLink id="answerAdd" style="hidden"
									action="#{saveQuestionBackingBean.addAnswer}">
								</h:commandLink></TD>
								<TD colspan="50%" align="right">&nbsp;<h:commandButton
									id="deleteButton" styleClass="wpdButton" value="Delete"
									onclick="unsavedDataFinder(this.id);return false;"></h:commandButton>
								</TD>
							</TR>
							<TR style="height:2px">
								<TD height="2">&nbsp;</TD>
							</TR>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenAnswer"
					value="#{saveQuestionBackingBean.answer}"></h:inputHidden>
				<h:inputHidden id="hiddenAnswerForDelete"
					value="#{saveQuestionBackingBean.deletedAnswer}"></h:inputHidden>
				<h:inputHidden id="hiddenQuestionNumber"
					value="#{saveQuestionBackingBean.questionNumber}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdateFlag"
					value="#{saveQuestionBackingBean.updateFlag}"></h:inputHidden>

				<h:commandLink id="deleteLink"
					action="#{saveQuestionBackingBean.deleteAnswer}">
					<f:verbatim />
				</h:commandLink>

				<h:inputHidden id="questionVersion"
					value="#{saveQuestionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="questionStatus"
					value="#{saveQuestionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="dataTypeHidden"
					value="#{saveQuestionBackingBean.dataTypeId}"></h:inputHidden>
				<h:inputHidden id="providerArrangement"
					value="#{saveQuestionBackingBean.providerArrangement}"></h:inputHidden>
				<!-- End of hidden fields  -->
				<h:inputHidden id="answerToDelete"
					value="#{saveQuestionBackingBean.deleteAnswerIds}">
				</h:inputHidden>
				<h:inputHidden id="qualifierCombine"
					value="#{saveQuestionBackingBean.aggregateQualifier}">
				</h:inputHidden>
				<h:inputHidden id="termCombine"
					value="#{saveQuestionBackingBean.aggregateTerm}">
				</h:inputHidden>
				<h:inputHidden id="benefitLineDataType"
					value="#{saveQuestionBackingBean.benefitLineDataType}">
				</h:inputHidden>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
enableDisableBnftLineDtType();
	document.getElementById('createQuestionForm:questonText').focus(); // for on load default selection
	copyHiddenToDiv_ewpd('createQuestionForm:functionalDomainCD','FunctionalDomainDiv',3,1);
	copyHiddenToDiv_ewpd('createQuestionForm:providerArrangement','ProviderArrangementDiv',2,1);
	copyHiddenToDiv_ewpd('createQuestionForm:spsParamCD','SPSRefDiv',2,2);
	copyHiddenToDiv_ewpd('createQuestionForm:txtTerm','TermDiv',2,2);
	copyHiddenToDiv_ewpd('createQuestionForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('createQuestionForm:benefitLineDataType','benefitLineDataTypeDiv',2,1);
	
	if(document.getElementById('createQuestionForm:answerTable') != null) {	
		tigra_tables('createQuestionForm:answerTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		setColumnWidth('questionHeaderTable','85%:15%');
		setColumnWidth('createQuestionForm:answerTable','85%:15%');
	}
	else{
		var divObj = document.getElementById('associatedAnswersDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}
	function addAnsToHidden(){
		document.getElementById("createQuestionForm:hiddenAnswer").value =document.getElementById("createQuestionForm:hiddenAnswer").value + '~' + document.getElementById("createQuestionForm:answerText").value ;
	}
	enableDisableDelete('createQuestionForm:answerTable',1,0,'createQuestionForm:deleteButton');
	function confirmBeforeClear(){
		if(document.getElementById('createQuestionForm:answerTable') != null){
				var confirmForClear = confirm("This will result in clearing the answers list.");
				selectObj = document.getElementById('createQuestionForm:dataTypeName');
				oldObj = document.getElementById('createQuestionForm:dataTypeHidden');
				if(confirmForClear){
					oldObj.value = selectObj.options[selectObj.selectedIndex].value;
					document.getElementById('createQuestionForm:clearLink').click();
				}
				else{
					document.getElementById('createQuestionForm:dataTypeName').value = document.getElementById('createQuestionForm:dataTypeHidden').value;
				}
		}
	}

function loadSearchPopup(popupName,queryName,headerName,titleName,displayDiv,outComp){

	if(queryName=='searchBenefitLineDataType'){
		ewpdModalWindow_ewpd( popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,1);
	}
	else{
		ewpdModalWindow_ewpd( popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
	}
	if(queryName=='searchTerm'){
		var term=document.getElementById('createQuestionForm:txtTerm').value;
		var terms= new Array(6) 
 	 	terms=term.split("~");
	  	if(terms.length>2){
			document.getElementById('createQuestionForm:aggregateTermCheckBox').checked = true;
			document.getElementById('createQuestionForm:termCombine').value="true";
				}
		else{
				document.getElementById('createQuestionForm:aggregateTermCheckBox').checked = false;
				document.getElementById('createQuestionForm:termCombine').value="false";
		}	
	}
	else {
		var qualifier=document.getElementById('createQuestionForm:txtQualifier').value;
		var qualifiers= new Array(6) 
 	 	qualifiers=qualifier.split("~");
	  	if(qualifiers.length>2){
			document.getElementById('createQuestionForm:aggregateQualifierCheckBox').checked = true;
			document.getElementById('createQuestionForm:qualifierCombine').value="true";
			}
		else{
			document.getElementById('createQuestionForm:aggregateQualifierCheckBox').checked = false;
			document.getElementById('createQuestionForm:qualifierCombine').value="false";
		}	
	}
	
}

	function copyDataType(){
		document.getElementById('createQuestionForm:dataTypeHidden').value = document.getElementById('createQuestionForm:dataTypeName').value;
	}
	function runSpellCheck(){
		document.getElementById('createQuestionForm:dataTypeHidden').value = document.getElementById('createQuestionForm:dataTypeName').value;
		var rswlCntrls = ["rapidSpellWebLauncher1"];
		var i=0;
		for(var i=0; i<rswlCntrls.length; i++){
			eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
		}
		return false;
	}
	function spellFin(cancelled){

			document.getElementById('createQuestionForm:create').click();	
	}
	function unsavedDataFinder(objectId){
		var buttonId = objectId;
		if(buttonId == 'createQuestionForm:addButton'){
		var tableObject = document.getElementById('createQuestionForm:answerTable');
		if(null != tableObject){
			var panelData = getPanelData('createQuestionForm:answerTable');
			if(document.getElementById('createQuestionForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('createQuestionForm:answerAdd');	
				}
			}else{
				submitLink('createQuestionForm:answerAdd');
			}
		}else{
			submitLink('createQuestionForm:answerAdd');
		}
		}else if (buttonId == 'createQuestionForm:deleteButton'){
			if(confirmDelete()){
				
				submitLink('createQuestionForm:deleteLink');	
				}
    }	
  }	
   function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Answer?"		
		if(!window.confirm(message)){
			return false;
		}
		tableObj = getObj('createQuestionForm:answerTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
       
		delAnswerId = getObj('createQuestionForm:answerToDelete');
		delAnswerId.value = '';
		var answerCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			if(cur_row.cells[1].children[0].checked) {
				if(answerCount != 0) 
					delAnswerId.value += '~';
				delAnswerId.value += cur_row.cells[0].children[0].value;
				answerCount ++;
			}
		}
		if(answerCount > 0)
			return true;
		return false;
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
    
	function pvaClick(url,targetDiv,targetHidden,attrCount,attrPosn){
	var lob = 'ALL~ALL';
	var be = 'ALL~ALL';
	var bg = 'ALL~ALL';
	var parentCatalog = 'provider arrangement';
	url = url +getUrl()+ '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
	}
	function domainClick(url,targetDiv,targetHidden,attrCount,attrPosn){
		var lob = 'ALL~ALL';
	    var be = 'ALL~ALL';
	    var bg = 'ALL~ALL';
		var parentCatalog = 'Functional Domain';
		url = url +getUrl()+ '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
		var a = ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		enableDisableBnftLineDtType();
		return a; 
    }
    
    function enableDisableBnftLineDtType(){
  
    var	textValue = document.getElementById('createQuestionForm:functionalDomainCD').value;
    var attrPos = 1;
    var attrCount = 3;
    var flag =0;
	if(textValue != null && textValue != undefined && textValue.length > 0){
		attrPos -= 1;			
		values = textValue.split("~");
		for(var i=0, n = values.length; i<n; i+=attrCount) {
		    if('ADJUD - ACCUM' == values[i+attrPos]){
		    flag = 1;
		    }
		}
    }
    if(flag == 0){
    		benefitLineDataTypeTR.style.display='none';
			benefitLineDataTypeTR.style.display='none';
    }  else{ 
			benefitLineDataTypeTR.style.display='';
			benefitLineDataTypeTR.style.display = '';
    }
      
    }
    function spsRefClick(targetDiv,targetHidden){
    
    var termVal=getTerm();
    var qualVal=getQualifier();
    var pvaVal=getPVA();
    var dataTypeVal=getDataType();

    return ewpdModalWindow_ewpd( '../popups/SearchSPSForLinesAndQuestions.jsp'+getUrl()+'?&' +
								"queryName=searchSPSForMapping&headerName=Reference&titleName=" +
								"SPS Parameter Popup&titleName=SPS Parameter Popup&spsType=QUESTION&term="+termVal+
								"&qualifier=" + qualVal + "&pva=" +pvaVal+"&dataType="+dataTypeVal+
								'&temp='+Math.random(),
								targetDiv,targetHidden,2,2);
   }
   
   function getTerm(){
  		
  	var term =	document.getElementById('createQuestionForm:txtTerm').value;
  	if(term != null){
  	var terms= new Array(5) 
  	terms=term.split("~");
  	if(terms.length>2)
  		term=terms[0]+","+terms[2];
  	else	
  		term=terms[0];
  	}else{
  	    term = '';
  	}
  	return term;
   
   }
   function getQualifier(){
   
   var qualifier =	document.getElementById('createQuestionForm:txtQualifier').value;
   if(qualifier != null){
  	var qualifiers= new Array(5) 
  	qualifiers=qualifier.split("~");
  	if(qualifiers.length>2)
  		qualifier=qualifiers[0]+","+qualifiers[2];
  	else
  		qualifier=qualifiers[0];
  		}else {
  		qualifier = '';
  		}
  	return qualifier;
   }
   function getPVA(){
      var pva =	document.getElementById('createQuestionForm:providerArrangement').value;
      if(pva == null){
      pva='';
      }
   		return pva;
   }
   function getDataType(){
   var dataType =	document.getElementById('createQuestionForm:dataTypeName').value;
   	if(dataType == -1) dataType = 0;
   		return dataType;   
   }
   
   if(document.getElementById('createQuestionForm:aggregateQualifierCheckBox') != null){
    document.getElementById('createQuestionForm:aggregateQualifierCheckBox').disabled =true; 
   }
   
   if(document.getElementById('createQuestionForm:aggregateTermCheckBox') != null){
    document.getElementById('createQuestionForm:aggregateTermCheckBox').disabled =true; 
   }

</script>
</HTML>
