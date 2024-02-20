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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {

}.shortDescriptionColumn {
	text-align:center;
	vertical-align: middle;
}

</style>


<TITLE>Edit Question</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('editQuestionForm:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="editQuestionForm">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>
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

						<!-- Table containing Tabs -->
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
							class="outputText">
							<TBODY>
								<TR>
									<TD height="37" width="20%"><h:outputText id="question"
										value="Question*"
										styleClass="#{saveQuestionBackingBean.requiredQuestion ? 'mandatoryError' : 'mandatoryNormal'}" />
									</TD>
									<TD height="37" width="20%"><h:outputText id="questonText"
										value="#{saveQuestionBackingBean.question}" /> <h:inputHidden
										id="hiddenquestonText"
										value="#{saveQuestionBackingBean.question}" /></TD>
									<td height="37" width="10%">&nbsp;</td>
									<TD width="40%">
									</TD>
									
								</TR>
								<tr>
									<TD width="20%"><h:outputText id="funcdomain"
										value="Functional Domain*"
										styleClass="#{saveQuestionBackingBean.domainInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</td>
									<h:inputHidden id="functionalDomainCD"
										value="#{saveQuestionBackingBean.functionalDomain}">
									</h:inputHidden>
									<TD width="30%">
									<DIV id="FunctionalDomainDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="Domain" image="../../images/select.gif"
										style="cursor: hand"
										onclick="domainClick('../popups/functionalDomainPopup.jsp','FunctionalDomainDiv','editQuestionForm:functionalDomainCD',3,1); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%">
									</TD>
								</tr>
								<TR>
											<TD width="20%"><h:outputText
												styleClass="#{saveQuestionBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
												id="TermStar" value="Term" /></TD>
											<TD width="30%">
											<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtTerm"
												value="#{saveQuestionBackingBean.term}"></h:inputHidden>
											</TD>
											<TD width="10%">&nbsp;&nbsp;<h:commandButton
												alt="Select" id="TermButton" image="../../images/select.gif"
												style="cursor: hand"
												onclick="loadSearchPopup('../popups/searchTermMultiSelect.jsp','searchTerm','Term','Term Search Popup','TermDiv','editQuestionForm:txtTerm'); return false;">
                                               </h:commandButton>          
											</TD>
											<td align="left" width="40%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
												value="#{saveQuestionBackingBean.aggregateTerm}" tabindex="2"></h:selectBooleanCheckbox>Combine
											</td>	
								</TR>
								<TR>
											<TD width="20%"><h:outputText
												styleClass="#{saveQuestionBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
												id="QualifierStar" value="Qualifier" /></TD>
											<TD width="30%">
											<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtQualifier"
												value="#{saveQuestionBackingBean.qualifier}"></h:inputHidden>
											</TD>
											<TD width="10%">&nbsp;&nbsp;<h:commandButton
												alt="Select" id="QualifierButton"
												image="../../images/select.gif" style="cursor: hand"
												onclick="loadSearchPopup('../popups/searchQualifierMultiselectForQstn.jsp','searchQualifier','Qualifier','Qualifier Search Popup','QualifierDiv','editQuestionForm:txtQualifier'); return false;"
												tabindex="13"></h:commandButton>
											</TD>
											<td align="left" width="40%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateQualifierCheckBox"
												value="#{saveQuestionBackingBean.aggregateQualifier}" tabindex="4" ></h:selectBooleanCheckbox>Combine
											</td>
								</TR>
								
								
								<TR>
									<TD height="37" width="20%"><h:outputText styleClass="#{saveQuestionBackingBean.requiredPVA ? 'mandatoryError': 'mandatoryNormal'}" 
										id="providerArrangementtxt" value="Provider Arrangement" /></TD>
									<TD height="37" width="30%">
									<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD height="37" width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="ProviderArrangementButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="pvaClick('../popups/providerArrangementPopupSingleSelect.jsp','ProviderArrangementDiv','editQuestionForm:providerArrangement',2,1); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%">
									</TD>
								</TR>

								<TR>
									<TD height="37" width="20%"><h:outputText id="dataType"
										value="DataType*"
										styleClass="#{saveQuestionBackingBean.requiredDataType ? 'mandatoryError' : 'mandatoryNormal'}" />
									</TD>
									<TD height="37" width="30%"><h:selectOneMenu id="dataTypeName"
										value="#{saveQuestionBackingBean.dataTypeId}"
										onchange="confirmBeforeClear();setChangeValue();" tabindex="1">
										<f:selectItems id="dataTypeList"
											value="#{ReferenceDataBackingBeanCommon.dataTypeListForCombo}" />
									</h:selectOneMenu></TD>
									<td height="37" width="10%">&nbsp;</td>
									<TD width="40%">
									</TD>
								</TR>
								<tr>
									<TD width="20%"><h:outputText id="spsParam"
										value="Reference"
										styleClass="#{saveQuestionBackingBean.referenceInvalid ? 'mandatoryError': 'mandatoryNormal'}" />
									</td>
									<h:inputHidden id="spsParamCD"
										value="#{saveQuestionBackingBean.spsRefernceId}"></h:inputHidden>
									<TD width="30%">
									<DIV id="SPSRefDiv" class="selectDataDisplayDiv"></DIV>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="ref" image="../../images/select.gif"
										style="cursor: hand"
										onclick="spsRefClick('SPSRefDiv','editQuestionForm:spsParamCD'); return false;"
										tabindex="14"></h:commandButton></TD>
									<TD width="40%">
									</TD>
								</tr>

								<tr id="benefitLineDataTypeTR" style="display:none;">
									<TD width="22%"><h:outputText id="benefitLineDataTypeTxt" 
										value="Benefit Line Data Type*" 
										styleClass="#{saveQuestionBackingBean.requiredBenefitLineDataType ? 'mandatoryError': 'mandatoryNormal'}"/></td>
									<TD width="28%">
									<DIV id="benefitLineDataTypeDiv"  class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtBenefitLineDataType"  
												value="#{saveQuestionBackingBean.benefitLineDataType}"></h:inputHidden>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="BenefitLineDataTypeButton" image="../../images/select.gif" 
										style="cursor: hand"
										onclick="loadSearchPopup('../standardbenefitpopups/benefitDataTypePopup.jsp','searchBenefitLineDataType',
										'txtBenefitLineDataType','Benefit Line DataType Search Popup','benefitLineDataTypeDiv','editQuestionForm:txtBenefitLineDataType'); return false;">
										</h:commandButton></TD>
									<TD width="40%"></TD>
									
								</tr>
								<h:commandLink id="clearLink"
									action="#{saveQuestionBackingBean.clearAnswersList}">
									<f:verbatim />
								</h:commandLink>

							</TBODY>
						</TABLE>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
						<legend><font color="black">Answers</font></legend> <br></br>
						<TABLE id="selectTable" border="0" cellspacing="0" cellpadding="0"
							class="outputText">
							<TR>
								<TD width="170"><h:outputText id="answers"
									value="Possible Answer*"
									styleClass="#{saveQuestionBackingBean.requiredAnswers ? 'mandatoryError' : 'mandatoryNormal'}" /></TD>
								<TD width="194"><h:inputText styleClass="formInputField"
									id="answerText"
									value="#{saveQuestionBackingBean.possibleAnswer}"
									maxlength="250" tabindex="2" /></TD>
								<TD width="40"><h:commandButton id="addButton" value="Add"
									styleClass="wpdButton"
									onclick="addAnsToHidden();copyDataType();return true;"
									action="#{saveQuestionBackingBean.addAnswer}" tabindex="3"></h:commandButton></TD>
							</TR>

						</TABLE>
						<br></br>
						<DIV id="associatedAnswersDiv">
						<TABLE id="associatedAnswersTable" width="100%" cellpadding="0"
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
										<h:inputHidden id="answerDescHidden"
											value="#{eachRow.possibleAnswerDesc}"></h:inputHidden>
										<h:outputText id="answer"
											value="#{eachRow.possibleAnswerDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:selectBooleanCheckbox id="deleteChkBox"
											onclick="enableDisableDelete('editQuestionForm:answerTable',1,0,'editQuestionForm:deleteButton');">
										</h:selectBooleanCheckbox>
									</h:column>
								</h:dataTable></TD>
							</TR>

						</TABLE>
						<br>
						<TABLE id="associatedAnswersTable1" width="100%" cellpadding="0"
							cellspacing="0">
							<TR>
								<TD colspan="100%" align="left">&nbsp;<h:commandButton
									id="deleteButton" styleClass="wpdButton" value="Delete"
									onclick="unsavedDataFinder(this.id);return false;"></h:commandButton>
								</TD>
							</TR>
						</TABLE>
						</DIV>
						</fieldset>
						<br></br>
						<TABLE>
							<TBODY>
								<TR>
									<TD>
									<Table>
										<TR>
											<TD><h:outputText id="outTxtCreatedUserId" value="Created By" /></TD>
											<TD><h:outputText id="createdUserId"
												value="#{saveQuestionBackingBean.createdUserId}" /></TD>
										</TR>
										<TR>
											<TD><h:outputText id="outTxtCreatedDate" value="Created Date" />
											</TD>
											<TD><h:outputText id="createdDate"
												value="#{saveQuestionBackingBean.createdDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}"></h:outputText></TD>
										</TR>
										<TR>
											<TD><h:outputText id="outTxtUpdatedUserId"
												value="Last Updated By" /></TD>
											<TD><h:outputText id="updatedUserId"
												value="#{saveQuestionBackingBean.updatedUserId}" /></TD>
										</TR>
										<TR>
											<TD><h:outputText id="outTxtUpdatedDate"
												value="Last Updated Date" /></TD>
											<TD><h:outputText id="updatedDate"
												value="#{saveQuestionBackingBean.updatedDate}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}"></h:outputText></TD>
										</TR>

										<TR>
											<TD width="138">&nbsp;</TD>
										</TR>
									</Table>
									</TD>
								</TR>
								<TR>
									<TD>
									<TABLE cellspacing="0" cellpadding="0">

										<TR>
											<TD>&nbsp;<h:commandButton id= "saveButton" value="Save"
												styleClass="wpdButton" onclick="copyDataType();"
												onmousedown="javascript:savePageActionForDataTableQuestion(this.id, 'editQuestionForm');"
												action="#{saveQuestionBackingBean.create}" tabindex="5">
											</h:commandButton></TD>
										</TR>
										<TR style="height:2px">
											<TD height="2">&nbsp;</TD>
										</TR>
									</TABLE>
									</TD>
								</TR>


							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>

						<br>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"><h:selectBooleanCheckbox
									value="#{saveQuestionBackingBean.checkInFlag}" id="checkall"
									tabindex="6" /></td>
								<td align="left"><h:outputText id="outTxtCheckIn"
									value="Check In" /></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td width="50%"><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{saveQuestionBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtStatus" value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{saveQuestionBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtVersion" value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{saveQuestionBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
							</tr>

						</table>

						</fieldset>
						<br />
						<TABLE>
							<TR>
								<TD>
								<Table>
									<TR>
										<TD>&nbsp;&nbsp;<h:commandButton value="Done"
											styleClass="wpdButton" tabindex="7"
											action="#{saveQuestionBackingBean.checkIn}">
										</h:commandButton></TD>
									</TR>
								</Table>
								</TD>
							</TR>
						</TABLE>


						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenAnswer"
					value="#{saveQuestionBackingBean.answer}"></h:inputHidden>
				<h:inputHidden id="hiddenValueChanged"
					value="#{saveQuestionBackingBean.valueChanged}"></h:inputHidden>
				<h:inputHidden id="hiddenAnswerForDelete"
					value="#{saveQuestionBackingBean.deletedAnswer}"></h:inputHidden>
				<h:inputHidden id="hiddenQuestionNumber"
					value="#{saveQuestionBackingBean.questionNumber}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdateFlag"
					value="#{saveQuestionBackingBean.updateFlag}"></h:inputHidden>
				<h:commandLink id="deleteAnswer"
					action="#{saveQuestionBackingBean.deleteAnswer}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="answerAdd"
					action="#{saveQuestionBackingBean.addAnswer}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="questionVersion"
					value="#{saveQuestionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="questionStatus"
					value="#{saveQuestionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="questionState"
					value="#{saveQuestionBackingBean.state}"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedUserId"
					value="#{saveQuestionBackingBean.createdUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedDate"
					value="#{saveQuestionBackingBean.createdDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="hiddenUpdatedUserId"
					value="#{saveQuestionBackingBean.updatedUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdatedDate"
					value="#{saveQuestionBackingBean.updatedDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="time" value="#{requestScope.timezone}">
				</h:inputHidden>

				<h:inputHidden id="hiddenDataType"
					value="#{saveQuestionBackingBean.dataTypeId}"></h:inputHidden>
				<h:inputHidden id="hiddenCheckOutFlag"
					value="#{saveQuestionBackingBean.checkOutFlag}"></h:inputHidden>
				<h:inputHidden id="answerToDelete"
					value="#{saveQuestionBackingBean.deleteAnswerIds}">
				</h:inputHidden>
				<h:inputHidden id="providerArrangement"
					value="#{saveQuestionBackingBean.providerArrangement}"></h:inputHidden>
					<h:inputHidden id="qualifierCombine"
					value="#{saveQuestionBackingBean.aggregateQualifier}">
				</h:inputHidden>
				<h:inputHidden id="termCombine"
					value="#{saveQuestionBackingBean.aggregateTerm}">
				</h:inputHidden>	
				<h:inputHidden id="adjudAccumSelected"
					value="#{saveQuestionBackingBean.adjudAccumSelected}">
				</h:inputHidden>	
				
				<!-- End of hidden fields  -->

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
	copyHiddenToDiv_ewpd('editQuestionForm:providerArrangement','ProviderArrangementDiv',2,1); 
	copyHiddenToDiv_ewpd('editQuestionForm:functionalDomainCD','FunctionalDomainDiv',3,1);
	copyHiddenToDiv_ewpd('editQuestionForm:spsParamCD','SPSRefDiv',2,2);
	copyHiddenToDiv_ewpd('editQuestionForm:txtTerm','TermDiv',2,2);
	copyHiddenToDiv_ewpd('editQuestionForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('editQuestionForm:txtBenefitLineDataType','benefitLineDataTypeDiv',2,1);
	;
	if(document.getElementById('editQuestionForm:answerTable') != null) {	
		tigra_tables('editQuestionForm:answerTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		setColumnWidth('questionHeaderTable','90%:10%');
		setColumnWidth('editQuestionForm:answerTable','90%:10%');
	}
	else{
		var divObj = document.getElementById('associatedAnswersDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}
	
	if(document.getElementById('editQuestionForm:adjudAccumSelected').value == 'true') {
	   		benefitLineDataTypeTR.style.display='';
	}
	
	
	function enableDisableBnftLineDtType(){
  
    var	textValue = document.getElementById('editQuestionForm:functionalDomainCD').value;
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

	//function to add answer to hidden field
	function addAnsToHidden(){
		document.getElementById("editQuestionForm:hiddenAnswer").value =document.getElementById("editQuestionForm:hiddenAnswer").value + '~' + document.getElementById("editQuestionForm:answerText").value ;
	}
	enableDisableDelete('editQuestionForm:answerTable',1,0,'editQuestionForm:deleteButton');
	
	function setChangeValue(){
		document.getElementById('editQuestionForm:hiddenValueChanged').value = 1;
	}
	
	function loadSearchPopup(popupName,queryName,headerName,titleName,displayDiv,outComp){
	if(queryName=='searchBenefitLineDataType'){
		ewpdModalWindow_ewpd( popupName+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,1);
	}else{
		ewpdModalWindow_ewpd( popupName+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
	}
	if(queryName=='searchTerm'){
		var term=document.getElementById('editQuestionForm:txtTerm').value;
		var terms= new Array(6) 
 	 	terms=term.split("~");
	  	if(terms.length>2){
			document.getElementById('editQuestionForm:aggregateTermCheckBox').checked = true;
			document.getElementById('editQuestionForm:termCombine').value="true";
		}
		else{
				document.getElementById('editQuestionForm:aggregateTermCheckBox').checked = false;
				document.getElementById('editQuestionForm:termCombine').value="false";
		}	
	}
	else {
		var qualifier=document.getElementById('editQuestionForm:txtQualifier').value;
		var qualifiers= new Array(6) 
 	 	qualifiers=qualifier.split("~");
	  	if(qualifiers.length>2){
			document.getElementById('editQuestionForm:aggregateQualifierCheckBox').checked = true;
			document.getElementById('editQuestionForm:qualifierCombine').value="true";
		}
		else{
			document.getElementById('editQuestionForm:aggregateQualifierCheckBox').checked = false;
			document.getElementById('editQuestionForm:qualifierCombine').value="false";
		}
	}
	
	
	
}

	//fn to deisplay confirm box before clearing
	function confirmBeforeClear(){
		if(document.getElementById('editQuestionForm:answerTable') != null){
				var confirmForClear = confirm("This will result in clearing the answers list.");
				selectObj = document.getElementById('editQuestionForm:dataTypeName');
				oldObj = document.getElementById('editQuestionForm:hiddenDataType');
				if(confirmForClear){
					oldObj.value = selectObj.options[selectObj.selectedIndex].value;
					document.getElementById('editQuestionForm:clearLink').click();
				}
				else{
					selectObj.value  = oldObj.value;
					return false;
				}
		}
	}
	
	//fn to copy datatype to hidden field
	function copyDataType(){
		document.getElementById('editQuestionForm:hiddenDataType').value = document.getElementById('editQuestionForm:dataTypeName').value;
	}
	function unsavedDataFinder(objectId){
		var buttonId = objectId;
		if(buttonId == 'editQuestionForm:addButton'){
		var tableObject = document.getElementById('editQuestionForm:answerTable');
		if(null != tableObject){
			var panelData = getPanelData('editQuestionForm:answerTable');
			if(document.getElementById('editQuestionForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('editQuestionForm:answerAdd');	
				}
			}else{
				submitLink('editQuestionForm:answerAdd');
			}
		}else{
			submitLink('editQuestionForm:answerAdd');
		}
		}else if (buttonId == 'editQuestionForm:deleteButton'){
			if(confirmDelete()){
				
				submitLink('editQuestionForm:deleteAnswer');	
				}
    }	
  }	

   function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Answer?"		
		if(!window.confirm(message)){
			return false;
		}
		tableObj = getObj('editQuestionForm:answerTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
       
		delAnswerId = getObj('editQuestionForm:answerToDelete');
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
	url = url + '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
	}
	function domainClick(url,targetDiv,targetHidden,attrCount,attrPosn){
		var lob = 'ALL~ALL';
	    var be = 'ALL~ALL';
	    var bg = 'ALL~ALL';
		var parentCatalog = 'Functional Domain';
		url = url + '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
		var a =ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
		 enableDisableBnftLineDtType();
		return a;
	
    }
    
    
   function spsRefClick(targetDiv,targetHidden){
    
    var termVal=getTerm();
    var qualVal=getQualifier();
    var pvaVal=getPVA();
    var dataTypeVal=getDataType();

    return ewpdModalWindow_ewpd( '../popups/SearchSPSForLinesAndQuestions.jsp?&' +
								"queryName=searchSPSForMapping&headerName=Reference&titleName=" +
								"SPS Parameter Popup&titleName=SPS Parameter Popup&spsType=QUESTION&term="+termVal+
								"&qualifier=" + qualVal + "&pva=" +pvaVal+"&dataType="+dataTypeVal+
								'&temp='+Math.random(),
								targetDiv,targetHidden,2,2);
   }
   
   function getTerm(){
  		
  	var term =	document.getElementById('editQuestionForm:txtTerm').value;
  	var terms= new Array(5) 
  	terms=term.split("~");
  	if(terms.length>2)
  		term=terms[0]+","+terms[2];
  	else	
  		term=terms[0];
  	return term;
   
   }
   function getQualifier(){
   
   var qualifier =	document.getElementById('editQuestionForm:txtQualifier').value;
  	var qualifiers= new Array(5) 
  	qualifiers=qualifier.split("~");
  	if(qualifiers.length>2)
  		qualifier=qualifiers[0]+","+qualifiers[2];
  	else
  		qualifier=qualifiers[0];
  	return qualifier;
   }
   function getPVA(){
      var pva =	document.getElementById('editQuestionForm:providerArrangement').value;
   		return pva;
   }
   function getDataType(){
   
   var dataType =	document.getElementById('editQuestionForm:dataTypeName').value;
   		if(dataType == -1) dataType = 0;
   		return dataType;   
   }
   if(document.getElementById('editQuestionForm:aggregateQualifierCheckBox') != null){
    document.getElementById('editQuestionForm:aggregateQualifierCheckBox').disabled =true; 
   }
   
   if(document.getElementById('editQuestionForm:aggregateTermCheckBox') != null){
    document.getElementById('editQuestionForm:aggregateTermCheckBox').disabled =true; 
   } 
    

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="QuestionPrint" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>

function savePageActionForDataTableQuestion(id, editQuestionForm){
 //IGNORED_FIELD1 = "editQuestionForm:answerText";
savePageActionForDataTable(id, editQuestionForm);
}
</script>
</HTML>
