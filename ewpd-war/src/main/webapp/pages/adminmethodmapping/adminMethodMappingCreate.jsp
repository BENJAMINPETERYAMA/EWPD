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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.rowIndexColumn {
	
}
.selectOrOptionColumn {
	width: 5%;
}
</style>
	
<TITLE>Create Admin Method Mapping</TITLE>
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
<script language="JavaScript"
	src="../../js/adminMethod/adminmethodMapping.js"></script>

</HEAD>
<f:view>

	<BODY
		onkeypress="return submitOnEnterKey('customMessageCreateForm:createButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="createRefForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>
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
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="3" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<FIELDSET>
							<TABLE border="0" cellspacing="0" cellpadding="5"
								class="outputText" width="100%">
								<TBODY>

									<TR>
										<TD width="27%">&nbsp;<h:outputText
											styleClass="#{adminMethodMappingBackingBean.processMethodReq? 'mandatoryError': 'mandatoryNormal'}"
											id="BenefitTypeStar" value="Processing Method*" /></TD>

										<TD width="29%">
										<TABLE cellspacing="0" cellpadding="0" border="0">
											<TR>

												<h:inputHidden id="txtProcessingMethod1"
													value="#{adminMethodMappingBackingBean.processMethod}"></h:inputHidden>

												<TD height="15%"><h:selectOneMenu id="dropProcessingMethod"
													styleClass="formInputField" style="width:225px"
													value="#{adminMethodMappingBackingBean.processMethod}"
													tabindex="10" onchange="functionToClearDiv();">
													<f:selectItems id="processMethodList"
														value="#{adminMethodMappingBackingBean.processingMethodList}" />


												</h:selectOneMenu></TD>
											</TR>
										</TABLE>
										</TD>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText
											styleClass="#{adminMethodMappingBackingBean.adminMethodNoReq? 'mandatoryError': 'mandatoryNormal'}"
											id="config" value="Admin Method Number*" /></TD>
										<TD width="28%">
										<DIV id="amNoDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="amNoid"
											value="#{adminMethodMappingBackingBean.adminMethodNo}"></h:inputHidden>
										</TD>
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="RuleButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadAdminMethodNumberSearchPopup('retrieveAdminMethodNumber','Reference',
                                                         'Admin Method Popup ','amNoDiv','createRefForm:amNoid');
														 return false;"></h:commandButton>
										</TD>
										<TD width="42%"></TD>

									</TR>
									<TR>
										<TD width="24%">&nbsp;<h:outputText id="headerRuleDescText"
											value="Admin Method Description	" /></TD>
										<TD width="28%">
										<DIV id="DesDiv" title="" onmouseover="return setToolTip();"
											class="selectDataDisplayDiv"></DIV>
										</TD>
										<TD width="6%">&nbsp;</TD>
										<TD width="42%"></TD>
									</TR>



									<TR>
										<TD width="24%">&nbsp;<h:outputText id="TermStar" value="Term" /></TD>

										<TD width="28%">

										<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtTerm"
											value="#{adminMethodMappingBackingBean.term}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="Term" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchTermMultiSelect.jsp','searchTerm','Term',
                                                         'Term Popup','TermDiv','createRefForm:txtTerm'); return false;">
										</h:commandButton>&nbsp;&nbsp;&nbsp;&nbsp;<h:selectBooleanCheckbox
											styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
											value="#{adminMethodMappingBackingBean.aggregateTerm}"
											tabindex="2" readonly></h:selectBooleanCheckbox>Combine</td>

									</TR>
									<TR>
										<TD width="24%">&nbsp;<h:outputText id="QualifierStar"
											value="Qualifier" /></TD>
										<TD width="28%">
										<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtQualifier"
											value="#{adminMethodMappingBackingBean.qualifier}"></h:inputHidden>
										</TD>
										<TD width="9%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchMultipleQualifierPopup.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','createRefForm:txtQualifier'); return false;"
											tabindex="13"></h:commandButton></TD>

									</TR>
									<TR>
										<TD width="144">&nbsp;<h:outputText id="pva" value="Provider Arrangement" /></TD>
										<TD width="177">
										<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="providerArrangement"
											value="#{adminMethodMappingBackingBean.pva}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp; <h:commandButton alt="Select"
											id="ProviderArrangementButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="pvaClick('../standardbenefitpopups/providerArrangementPopUp.jsp','ProviderArrangementDiv','createRefForm:providerArrangement',2,2); return false;"
											tabindex="14"></h:commandButton></TD>
									</TR>


									<TR>
										<TD width="24%">&nbsp;<h:outputText id="DataTypeStar"
											value="Data Type" /></TD>
										<TD width="28%">
										<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtDataType"
											value="#{adminMethodMappingBackingBean.datatype}"></h:inputHidden>
										</TD>
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="DataTypeButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopup.jsp'+getUrl(),'DataTypeDiv','createRefForm:txtDataType',2,2); return false;"
											tabindex="15"></h:commandButton></TD>
										<TD width="42%"></TD>
									</TR>
									<TR>
										<TD width="24%" valign="top">&nbsp;<h:outputText
											id="descriptionStar" value="Comments"
											styleClass="#{adminMethodMappingBackingBean.commentsReq? 'mandatoryError': 'mandatoryNormal'}" /></TD>

										<TD width="28%"><h:inputTextarea
											styleClass="formTxtAreaField_GeneralDesc" id="comments"
											value="#{adminMethodMappingBackingBean.comments}"
											tabindex="9"></h:inputTextarea></TD>
										<td width="48%"><f:verbatim></f:verbatim></td>
									</TR>
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
									<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="createRefForm:comments"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Comments"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										finishedListener="spellFin" allowAnyCase="True"
										allowMixedCase="True" windowWidth="570" windowHeight="300"
										modal="False" showButton="False" windowX="-1"
										warnDuplicates="False" textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
									<TR>
										<TD colspan=2>
										<fieldset id='fieldsetID'><legend><font color="black">Question
										And Answer</font></legend>
										<TABLE border=0 cellspacing="0" cellpadding="0"
											class="outputText" width="100%">
											<TR>
												<TD width="94%">
												<fieldset
													style="margin:0px auto;text-align:left;padding:10px;border:1px;">

												<DIV id="QuestionDiv1"
													style="margin:0px;text-align:left;padding:10px;border:1px;">
												Add Question And Answer</DIV>
												</fieldset>
												</TD>

												<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
													styleClass="wpdbutton" id="QuestionButton"
													value="Add Questions" style="cursor: hand"
													onclick="getQuestions();return false;"></h:commandButton>
												</TD>
											</tr>

											<tr>
												<TD>
												<DIV id="QuestionDiv"></DIV>
												</TD>
											</tr>
											<tr>
												<h:dataTable id="questiondt"
													value="#{adminMethodMappingBackingBean.questionAnswerDisplayList}"
													rendered="#{adminMethodMappingBackingBean.questionAnswerDisplayList != null}"
													var="question" cellpadding="3" width="75%" cellspacing="3"
													border="0" style="border:solid #7f9db9 1px;"
													columnClasses="rowIndexColumn,selectOrOptionColumn">

													<h:column>
														<h:outputText id="rowIndex" value="#{question}" />
													</h:column>
													<h:column>
														<f:verbatim>
															<h:selectBooleanCheckbox id="componentChkBox">
															</h:selectBooleanCheckbox>
														</f:verbatim>
														
													</h:column>
												</h:dataTable>
												<h:commandButton alt="Select" styleClass="wpdbutton"
													id="QuestionDelete" value="Delete Questions"
													style="cursor: hand"
													rendered="#{adminMethodMappingBackingBean.deleteButtonFlag}"
													onclick="deleteQuestions();return false;"></h:commandButton>

											</tr>

											<TABLE border="0">
												<TR>
													<TD width="23%">&nbsp;<h:commandButton value="Create"
														id="createButton" styleClass="wpdButton" tabindex="15"
														onclick="return runSpellCheck();"></h:commandButton>
													<TD width="23%">&nbsp;</TD>

												</TR>
											</TABLE>







										</TABLE>
										<!--	End of Page data	--></FIELDSET>

										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<h:inputHidden id="termCombine"
								value="#{adminMethodMappingBackingBean.aggregateTerm}">
							</h:inputHidden> <h:inputHidden id="qaString"
								value="#{adminMethodMappingBackingBean.questionAnswerString}"></h:inputHidden>
							<h:inputHidden id="quesDelString"
								value="#{adminMethodMappingBackingBean.questionDeleteString}"></h:inputHidden>
							<h:inputHidden id="quesAnsDelString"
								value="#{adminMethodMappingBackingBean.deleteQuestionAnswerString}"></h:inputHidden>
							<h:commandLink id="pageLink"
								style="display:none; visibility: hidden;"
								action="#{adminMethodMappingBackingBean.pageReload}">
							</h:commandLink> <h:commandLink id="deleteLink"
								style="display:none; visibility: hidden;"
								action="#{adminMethodMappingBackingBean.deleteQuestions}">
							</h:commandLink> <h:commandLink id="createProduct" style="hidden"
								action="#{adminMethodMappingBackingBean.createAdminMethodMapping}">
							</h:commandLink>
							</TD>
						</TR>
						<TR>
							<TD><%@include file="../navigation/bottom.jsp"%></TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script>
	
	copyHiddenToDiv_ewpd('createRefForm:amNoid','amNoDiv',3,1);
	copyHiddenToDiv_ewpd('createRefForm:txtDataType','DataTypeDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:providerArrangement','ProviderArrangementDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:txtTerm','TermDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:amNoid','DesDiv',3,2);

if(document.getElementById('createRefForm:aggregateTermCheckBox') != null){
    document.getElementById('createRefForm:aggregateTermCheckBox').disabled =true; 
   }
   
 function loadAdminMethodNumberSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
    
    var spsId=document.getElementById('createRefForm:dropProcessingMethod').value;
 
	ewpdModalWindow_ewpd( '../popups/adminMethonNumberPopUp.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&spsId='+spsId+'&temp='+Math.random(), outComp,outComp,4,2);
}
function setToolTip(){
  document.getElementById('DesDiv').title=document.getElementById('DesDiv').innerText;
}   

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}

function spellFin(cancelled){
	
		document.getElementById('createRefForm:createProduct').click();
}
</script>