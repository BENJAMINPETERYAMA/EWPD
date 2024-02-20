<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Create Reference Mapping</TITLE>
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
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
								

									<TR>
										<TD width="24%">&nbsp;<h:outputText
											styleClass="#{referenceMappingBackingBean.referenceReq? 'mandatoryError': 'mandatoryNormal'}"
											id="RuleStar" value="Reference Id*" /></TD>
										<TD width="28%">
										<DIV id="refDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="refhid"
											value="#{referenceMappingBackingBean.referenceCriteria}"></h:inputHidden>
										</TD>
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="RuleButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="10"
											onclick="loadSPSSearchPopup('searchSPSDetail','Reference',
                                                         'SPS Popup','refDiv','createRefForm:refhid');
                                                          return false;"></h:commandButton>
										</TD>
										<TD width="42%"></TD>

									</TR>
									<TR>
										<TD width="24%">&nbsp;<h:outputText id="headerRuleSecText"
											value="Secondary Code" /></TD>
										<TD width="28%">
										<DIV id="snDiv" style="overflow-x:hidden"
											class="selectDataDisplayDiv"></DIV>
										</TD>
										<TD width="6%">&nbsp;</TD>
										<TD width="42%"></TD>
									</TR>
									<TR>
										<TD width="24%">&nbsp;<h:outputText id="headerRuleDescText"
											value="Description	" /></TD>
										<TD width="28%">
										<DIV id="DesDiv" style="overflow-x:hidden"
											class="selectDataDisplayDiv"></DIV>
										</TD>
										<TD width="6%">&nbsp;</TD>
										<TD width="42%"></TD>
									</TR>



									<TR>
										<TD width="27%">&nbsp;<h:outputText
											styleClass="#{referenceMappingBackingBean.requiredType? 'mandatoryError': 'mandatoryNormal'}"
											id="BenefitTypeStar" value="Type*" /></TD>
										<TD width="29%">
										<TABLE cellspacing="0" cellpadding="0" border="0">
											<TR>
												<TD><h:inputHidden id="benefitType_copyhidden"
													value="#{referenceMappingBackingBean.typeCriteria}"></h:inputHidden>
												<h:selectOneMenu id="CorpPlanCd_list1"
													styleClass="formInputField" tabindex="6"
													value="#{referenceMappingBackingBean.typeCriteria}">
													<f:selectItems id="benefitTypeList"
														value="#{referenceMappingBackingBean.mappingTypes}" />
													<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
													 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
													 validation error. Please dont modify the code  --%>

												</h:selectOneMenu></TD>
												<TD width="63%" align="right"></TD>
											</TR>
										</TABLE>
										</TD>
									</TR>


									<TR>
										<TD width="24%"><h:outputText
											styleClass="#{referenceMappingBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
											id="TermStar" value="Term" /></TD>

										<TD width="28%">

										<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtTerm"
											value="#{referenceMappingBackingBean.termCriteria}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="Term" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchTermMultiSelect.jsp','searchTerm','Term',
                                                         'Term Search Popup','TermDiv','createRefForm:txtTerm'); return false;">
										</h:commandButton></TD>
										<td align="left" width="42%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
												value="#{referenceMappingBackingBean.aggregateTerm}" tabindex="2" readonly ></h:selectBooleanCheckbox>Combine
										</td>
									</TR>
									<TR>
										<TD width="24%"><h:outputText
											styleClass="#{referenceMappingBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
											id="QualifierStar" value="Qualifier" /></TD>
										<TD width="28%">
										<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtQualifier"
											value="#{referenceMappingBackingBean.qualifierCriteria}"></h:inputHidden>
										</TD>
										<TD width="9%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchQualifierMultiselectForQstn.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','createRefForm:txtQualifier'); return false;"
											tabindex="13"></h:commandButton></TD>
										<td align="left" width="45%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateQualifierCheckBox"
												value="#{referenceMappingBackingBean.aggregateQualifier}" tabindex="4" ></h:selectBooleanCheckbox>Combine
										</td>
									</TR>


									<TR>
										<TD width="144"><h:outputText
											styleClass="#{referenceMappingBackingBean.requiredProviderArrangement ? 'mandatoryError': 'mandatoryNormal'}"
											id="pva" value="Provider Arrangement" /></TD>
										<TD width="177">
										<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="providerArrangement"
											value="#{referenceMappingBackingBean.pvaCriteria}"></h:inputHidden>
										</TD>
										<TD width="375">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="ProviderArrangementButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="pvaClick('../popups/providerArrangementPopupSingleSelect.jsp','ProviderArrangementDiv','createRefForm:providerArrangement',2,1); return false;"
											tabindex="14"></h:commandButton></TD>
									</TR>



									<TR>
										<TD width="24%"><h:outputText
											styleClass="#{referenceMappingBackingBean.requiredDataType ? 'mandatoryError': 'mandatoryNormal'}"
											id="DataTypeStar" value="Data Type" /></TD>
										<TD width="28%">
										<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtDataType"
											value="#{referenceMappingBackingBean.dataTypeCriteria}"></h:inputHidden>
										</TD>
										<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="DataTypeButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopupSingleSelect.jsp'+getUrl(),'DataTypeDiv','createRefForm:txtDataType',2,2); return false;"
											tabindex="15"></h:commandButton></TD>
										<TD width="42%"></TD>
									</TR>


								</TBODY>
							</TABLE>
							<BR>
							<TABLE>
								<TR>
									<TD width="23%">&nbsp;<h:commandButton value="Create"
										id="createButton" styleClass="wpdButton"
										action="#{referenceMappingBackingBean.createReferenceMapping}"
										tabindex="15">
									</h:commandButton></TD>
								</TR>
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="qualifierCombine"
					value="#{referenceMappingBackingBean.aggregateQualifier}">
				</h:inputHidden>
					<h:inputHidden id="termCombine"
					value="#{referenceMappingBackingBean.aggregateTerm}">
				</h:inputHidden>
					
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>

	copyHiddenToDiv_ewpd('createRefForm:refhid','refDiv',3,1);
	copyHiddenToDiv_ewpd('createRefForm:refhid','snDiv',3,2);
	copyHiddenToDiv_ewpd('createRefForm:refhid','DesDiv',3,3);

	copyHiddenToDiv_ewpd('createRefForm:txtDataType','DataTypeDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:providerArrangement','ProviderArrangementDiv',2,1);
	copyHiddenToDiv_ewpd('createRefForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('createRefForm:txtTerm','TermDiv',2,2);




function loadIndicativeSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchIndicativeSPSpopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}

function loadSPSSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/SearchReferencePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), outComp,outComp,3,2);
}



function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchSPSSingleSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,1);
}

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchPopupSingle.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function showIndNr(val){

	if((null!=val) && !('' == val)){
		var indr = val.split('~');
		
	
		document.getElementById('refDiv').innerHTML=indr[0];
		document.getElementById('snDiv').innerHTML=indr[1];
		document.getElementById('DesDiv').innerHTML=indr[2];
		
	
		
	}else{
		document.getElementById('snDiv').innerHTML='';
		document.getElementById('refDiv').innerHTML='';
		document.getElementById('DesDiv').innerHTML='';
	}
}




function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp){

      ewpdModalWindow_ewpd( popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
      
      	if(queryName=='searchTerm'){
		var term=document.getElementById('createRefForm:txtTerm').value;
		var terms= new Array(6) 
 	 	terms=term.split("~");
	  	if(terms.length>2){
			document.getElementById('createRefForm:aggregateTermCheckBox').checked = true;
			document.getElementById('createRefForm:termCombine').value="true";
				}
		else{
				document.getElementById('createRefForm:aggregateTermCheckBox').checked = false;
				document.getElementById('createRefForm:termCombine').value="false";
		}	
	}
	else {
		var qualifier=document.getElementById('createRefForm:txtQualifier').value;
		var qualifiers= new Array(6) 
 	 	qualifiers=qualifier.split("~");
	  	if(qualifiers.length>2){
			document.getElementById('createRefForm:aggregateQualifierCheckBox').checked = true;
			document.getElementById('createRefForm:qualifierCombine').value="true";
			}
		else{
			document.getElementById('createRefForm:aggregateQualifierCheckBox').checked = false;
			document.getElementById('createRefForm:qualifierCombine').value="false";
		}	
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

   if(document.getElementById('createRefForm:aggregateQualifierCheckBox') != null){
    document.getElementById('createRefForm:aggregateQualifierCheckBox').disabled =true; 
   }
   
   if(document.getElementById('createRefForm:aggregateTermCheckBox') != null){
    document.getElementById('createRefForm:aggregateTermCheckBox').disabled =true; 
   }


</script>
</HTML>
