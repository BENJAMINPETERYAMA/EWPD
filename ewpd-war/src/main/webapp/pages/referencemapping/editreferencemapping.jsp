<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Edit Reference mapping</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('editForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<table width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<tr>
				<td><h:form styleClass="form" id="editForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:500px"><!-- Space for Tree  Data	--></DIV>
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
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="25%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
											<td width="100%" class="tabActive"><h:outputText
												value=" General Information" /></td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" width="3" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="100%"></td>
								</tr>
							</table>
							<!-- End of Tab table --> <script type="text/javascript">
								
						</script>

							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<DIV id="ind"><!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%" >
								<TBODY>
								
							<h:inputHidden id="prev1"
											value="#{referenceMappingBackingBean.prevReferenceCriteria}"></h:inputHidden>			
							<h:inputHidden id="prev2"
											value="#{referenceMappingBackingBean.prevTypeCriteria}"></h:inputHidden>	
							<h:inputHidden id="prev3"
											value="#{referenceMappingBackingBean.prevTermCriteria}"></h:inputHidden>			
							<h:inputHidden id="prev4"
											value="#{referenceMappingBackingBean.prevQualifierCriteria}"></h:inputHidden>	
							<h:inputHidden id="prev5"
											value="#{referenceMappingBackingBean.prevpvaCriteria}"></h:inputHidden>	
							<h:inputHidden id="prev6"
											value="#{referenceMappingBackingBean.prevDataTypeCriteria}"></h:inputHidden>	
						



									<tr>
										<td width="25%">&nbsp;<h:outputText id="RuleStarRef"
											value="Reference Id*" /></td>
										<td width="25%">

										<DIV id="refDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="refhid"
											value="#{referenceMappingBackingBean.referenceCriteria}"></h:inputHidden>
										</TD>
										<TD width="5%" >&nbsp;</TD>
										<TD width="45%" >&nbsp;</TD>
									</tr>
									<TR>

										<TD width="25%">&nbsp;<h:outputText id="headerRuleSecText"
											value="Secondary Code" /></TD>
										<TD width="25%">
										<div id="snDiv" style="overflow-x:hidden"
											class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="5%" >&nbsp;</TD>
										<TD width="45%" >&nbsp;</TD>
									</TR>

									<TR>

										<TD width="25%">&nbsp;<h:outputText id="headerRuleDescText"
											value="Description" /></TD>
										<TD width="25%">
										<div id="DesDiv" style="overflow-x:hidden"
											class="selectDataDisplayDiv"></div>
										</TD>
										
										<TD width="5%" >&nbsp;</TD>
										<TD width="45%" >&nbsp;</TD>
	
									</TR>

									<TR>
									<TD width="27%">&nbsp;<h:outputText styleClass="#{referenceMappingBackingBean.requiredType? 'mandatoryError': 'mandatoryNormal'}" id="BenefitTypeStar" value="Type*" /></TD>
									<TD width="29%">
									<TABLE cellspacing="0" cellpadding="0" border="0">
										<TR>
											
											<TD>
											
											<h:inputHidden id="benefitType_copyhidden" value="#{referenceMappingBackingBean.typeCriteria}"></h:inputHidden>
																				
<h:selectOneMenu id="CorpPlanCd_list1" styleClass="formInputField" tabindex="6" value="#{referenceMappingBackingBean.typeCriteria}">
												<f:selectItems id="benefitTypeList" value="#{referenceMappingBackingBean.mappingTypes}" />
												<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
													 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
													 validation error. Please dont modify the code  --%>

											</h:selectOneMenu></TD>

												<TD width="5%" >&nbsp;</TD>
										<TD width="45%" >&nbsp;</TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							

								
							<TR>
									<TD width="25%"><h:outputText styleClass="#{referenceMappingBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}" id="TermStar" value="Term" /></TD>

									<TD width="25%">

									<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
									 <h:inputHidden id="txtTerm" value="#{referenceMappingBackingBean.termCriteria}"></h:inputHidden>
									</TD>
									<TD width="10%">&nbsp;&nbsp;<h:commandButton alt="Select" id="Term" image="../../images/select.gif" style="cursor: hand" onclick="loadSearchPopupWithName('../popups/searchTermMultiSelect.jsp','searchTerm','Term',
                                                         'Term Search Popup','TermDiv','editForm:txtTerm'); return false;">
                                               </h:commandButton>     
                                     </TD>									  
									<td align="left" width="42%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateTermCheckBox"
												value="#{referenceMappingBackingBean.aggregateTerm}" tabindex="2" readonly ></h:selectBooleanCheckbox>Combine
									</td>
							</TR>


							<TR>
									<TD width="25%"><h:outputText styleClass="#{referenceMappingBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}" id="QualifierStar" value="Qualifier" /></TD>
									<TD width="25%">
									<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtQualifier" value="#{referenceMappingBackingBean.qualifierCriteria}"></h:inputHidden>
									</TD>
									<TD width="9%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="QualifierButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadSearchPopupWithName('../popups/searchQualifierMultiselectForQstn.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','editForm:txtQualifier'); return false;"
											tabindex="13"></h:commandButton></TD>
										

								<td align="left" width="45%"><h:selectBooleanCheckbox
												styleClass="selectBooleanCheckbox" id="aggregateQualifierCheckBox"
												value="#{referenceMappingBackingBean.aggregateQualifier}" tabindex="4" ></h:selectBooleanCheckbox>Combine
										</td>							
							</TR>
									<TR>
									<TD width="25%"><h:outputText  styleClass="#{referenceMappingBackingBean.requiredProviderArrangement ? 'mandatoryError': 'mandatoryNormal'}" id="pva" value="Provider Arrangement" /></TD>
									<TD width="25%">
									<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="providerArrangement" value="#{referenceMappingBackingBean.pvaCriteria}"></h:inputHidden>
									</TD>
									<TD width="5%">&nbsp;&nbsp;<h:commandButton alt="Select" id="ProviderArrangementButton" image="../../images/select.gif" style="cursor: hand" onclick="pvaClick('../popups/providerArrangementPopupSingleSelect.jsp','ProviderArrangementDiv','editForm:providerArrangement',2,1); return false;" tabindex="14"></h:commandButton></TD>
								<TD width="45%" >&nbsp;</TD>	
</TR>



								<TR>
									<TD width="25%"><h:outputText styleClass="#{referenceMappingBackingBean.requiredDataType ? 'mandatoryError': 'mandatoryNormal'}" id="DataTypeStar" value="Data Type" /></TD>
									<TD width="25%">
									<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtDataType" value="#{referenceMappingBackingBean.dataTypeCriteria}"></h:inputHidden>
									</TD>
									<TD width="5%">&nbsp;&nbsp;<h:commandButton alt="Select" id="DataTypeButton" image="../../images/select.gif" style="cursor: hand" onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopupSingleSelect.jsp','DataTypeDiv','editForm:txtDataType',2,2); return false;" tabindex="15"></h:commandButton></TD>
									<TD width="45%"></TD>
								</TR>



									<TR>
										<TD width="25%"><h:outputText id="createdBy"
											value="Created By" /></TD>
										<TD width="25%">										
										<DIV id="mapcu" >
														<h:outputText
											value="#{referenceMappingBackingBean.createdUser}">
											
										</h:outputText> 
									</DIV>
										</TD>
										<td width="5%"><f:verbatim></f:verbatim></td>
										<TD width="45%"></TD>
									</TR>

									<TR>
										<TD width="25%"><h:outputText id="createdDateText"
											value="Created Date" /></TD>
										<TD width="25%">										
										<DIV id="mapcdo" >
									 <h:inputHidden id="createdDate" value="#{referenceMappingBackingBean.createdDate}"></h:inputHidden>
													<h:outputText
											value="#{referenceMappingBackingBean.createdDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> 
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										</DIV>
										</TD>
										<td width="5%"><f:verbatim></f:verbatim></td>
										<TD width="45%"></TD>
									</TR>




							
									<TR>
										<TD width="25%"><h:outputText id="updatedBy"
											value="Last Updated By" /></TD>
										<TD width="25%">										
										<DIV id="mapclu" >
									 <h:inputHidden id="updatedByid" value="#{referenceMappingBackingBean.lastUpdatedUser}"></h:inputHidden>
													<h:outputText
											value="#{referenceMappingBackingBean.lastUpdatedUser}">
											
										</h:outputText> 
												</DIV>
										</TD>
										<td width="5%"><f:verbatim></f:verbatim></td>
										<TD width="45%"></TD>
									</TR>

										


										<TR>
										<TD width="25%"><h:outputText id="updatedDateText"
											value="Last Updated Date" /></TD>
										<TD width="25%">										
										<DIV id="maplte" >
									 <h:inputHidden id="changeDate" value="#{referenceMappingBackingBean.changeDate}"></h:inputHidden>
													<h:outputText
											value="#{referenceMappingBackingBean.changeDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> 
										<h:outputText value="#{requestScope.timezone}"></h:outputText>

										</TD>
										<td width="5%"><f:verbatim></f:verbatim></td>
										<TD width="45%"></TD>
									</TR>
										</DIV>





								</TBODY>
							</TABLE>
							</DIV>
							<table>
								<TR>
									<TD width="23%">&nbsp;<h:commandButton value="Save" id="saveButton" styleClass="wpdButton"
 action="#{referenceMappingBackingBean.editReferenceMapping}"
 tabindex="15" onmousedown="javascript:savePageAction(this.id);"> 



									</h:commandButton></TD>
								</TR>
							</table>
							<!--	End of Page data	--></fieldset>
							</TD>
						</TR>

					</table>
				<h:inputHidden id="qualifierCombine"
					value="#{referenceMappingBackingBean.aggregateQualifier}">
				</h:inputHidden>
					<h:inputHidden id="termCombine"
					value="#{referenceMappingBackingBean.aggregateTerm}">
				</h:inputHidden>
				</h:form></td>
			</tr>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</table>

	
	</hx:scriptCollector>
	</BODY>
</f:view>


<script>

	copyHiddenToDiv_ewpd('editForm:refhid','refDiv',3,1);
	copyHiddenToDiv_ewpd('editForm:refhid','snDiv',3,2);
	copyHiddenToDiv_ewpd('editForm:refhid','DesDiv',3,3);

	copyHiddenToDiv_ewpd('editForm:txtDataType','DataTypeDiv',2,2);
	copyHiddenToDiv_ewpd('editForm:providerArrangement','ProviderArrangementDiv',2,1);
	copyHiddenToDiv_ewpd('editForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('editForm:txtTerm','TermDiv',2,2);	


function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp){

      ewpdModalWindow_ewpd( popupName+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
      
      	      	if(queryName=='searchTerm'){
		var term=document.getElementById('editForm:txtTerm').value;
		var terms= new Array(6) 
 	 	terms=term.split("~");
	  	if(terms.length>2){
			document.getElementById('editForm:aggregateTermCheckBox').checked = true;
			document.getElementById('editForm:termCombine').value="true";
				}
		else{
				document.getElementById('editForm:aggregateTermCheckBox').checked = false;
				document.getElementById('editForm:termCombine').value="false";
		}	
	}
	else {
		var qualifier=document.getElementById('editForm:txtQualifier').value;
		var qualifiers= new Array(6) 
 	 	qualifiers=qualifier.split("~");
	  	if(qualifiers.length>2){
			document.getElementById('editForm:aggregateQualifierCheckBox').checked = true;
			document.getElementById('editForm:qualifierCombine').value="true";
			}
		else{
			document.getElementById('editForm:aggregateQualifierCheckBox').checked = false;
			document.getElementById('editForm:qualifierCombine').value="false";
		}	
	}
      
}


function loadIndicativeSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchIndicativeSPSpopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}

function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchSPSSingleSelectPopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/benefitindicativepopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
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


function  divValue(){

document.getElementById('editForm:msgHid').value=document.getElementById('editForm:msg').value;

}

   if(document.getElementById('editForm:aggregateQualifierCheckBox') != null){
    document.getElementById('editForm:aggregateQualifierCheckBox').disabled =true; 
   }
   
   if(document.getElementById('editForm:aggregateTermCheckBox') != null){
    document.getElementById('editForm:aggregateTermCheckBox').disabled =true; 
   }



</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="printEditReferenceMappingPage" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
