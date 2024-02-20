<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Benefit Mandate Information</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('MandateInfoForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="MandateInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="273" valign="top" class="leftPanel"><jsp:include
								page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{benefitMandateBackingBean.validationMessages}"></w:message>

										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="25%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{benefitComponentCreateBackingBean.loadBenefitComponentforView}">
												<h:outputText value="General Information" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<h:inputHidden id="hiddenTabValue"
										value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>
									<td width="25%" id="stdDefTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
											<td width="186" class="tabNormal"><h:commandLink
												action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinition}">
												<h:outputText
													value="#{benefitComponentCreateBackingBean.componentTypeTab}" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
										</tr>
									</table>
									</td>

									<td width="25%" id="sbMandateInfoTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
											<td width="186" class="tabActive">
												<h:outputText value="Mandate Information" />
											</td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" width="2" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="25%" id="sbOverrideNotesTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{BenefitComponentNotesBackingBean.loadStandardBenefitOverrideNotes}">
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
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<tr>
									<td width="32%">&nbsp;<h:outputText id="StateCodeStar"
										value="Jurisdiction " /></td>
									<td width="68%">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
										<tr>
											<td width="44%">
											<DIV id="StateDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtState"
												value="#{benefitMandateBackingBean.stateCode}"></h:inputHidden>
											</TD>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td width="24%">&nbsp;<h:outputText id="MandateTypeStar"
										value="Mandate Category " /></td>
									<td width="42%"><h:outputText
										value="#{benefitMandateBackingBean.mandateCategory}"></h:outputText>
										<h:inputHidden value="#{benefitMandateBackingBean.mandateCategory}"></h:inputHidden>
									</td>
								</tr>
								<TR>
									<TD width="24%">&nbsp;<h:outputText id="CitationNumberStar"
										value="Citation Number " /></TD>
									<TD width="42%">
									<DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV>
									<h:inputHidden id="CitationNumberHidden"
										value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
									</TD>
								</TR>

								<TR>
									<TD width="24%">&nbsp;<h:outputText id="FundingArrangementStar"
										value="Funding Arrangement " /></TD>
									<TD width="42%">
									<DIV id="FundingArrangementDiv" class="selectDivReadOnly"></DIV>
									<h:inputHidden id="FundingArrangementHidden"
										value="#{benefitMandateBackingBean.fundingArrangement}"></h:inputHidden>

									</TD>
								</TR>

								<TR>
									<TD width="24%">&nbsp;<h:outputText id="EffectivenessStar"
										value="Effectiveness " /></TD>
									<td width="42%"><h:outputText
										value="#{benefitMandateBackingBean.effectiveness}"></h:outputText>
										<h:inputHidden value="#{benefitMandateBackingBean.effectiveness}"></h:inputHidden>
									</td>
								</TR>


								<TR>
									<TD width="24%" valign="top">&nbsp;<h:outputText
										id="descriptionStar" value="Text " /></TD>
									<TD width="42%" valign="top"><h:inputTextarea
										id="txtDescription" value="#{benefitMandateBackingBean.text}" readonly="true"></h:inputTextarea></TD>
									<td width="63%"><f:verbatim></f:verbatim></td>
								</TR>


								<TR>

								</TR>
								<TR>
									<TD width="221">&nbsp;</TD>
								</TR>
							</TABLE>

							</FIELDSET>
							<BR>


							<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
								id="targetHiddenToStoreMasterKey"
								value="#{benefitMandateBackingBean.benefitMandateMasterKey}"></h:inputHidden>
							<h:commandLink id="deleteBenefitMandate"
								style="display:none; visibility: hidden;"
								action="#{benefitMandateBackingBean.deleteBenefitMandate}">
								<f:verbatim />
							</h:commandLink> <h:inputHidden id="masterKeyUsedForUpdate"
								value="#{benefitMandateBackingBean.masterKeyUsedForUpdate}"></h:inputHidden>
							<!-- start: for editing the selected details --> <h:commandLink
								id="editCommandLink"
								action="#{benefitMandateBackingBean.editMandateDetails}">
								<f:verbatim />
							</h:commandLink> <h:inputHidden id="mandateIdHidden"
								value="#{benefitMandateBackingBean.masterKeyUsedForUpdate}">
							</h:inputHidden> <h:inputHidden id="tabHidden"
								value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
							<!-- end: for editing the selected details --> <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="JavaScript">	
	copyHiddenToDiv_ewpd('MandateInfoForm:FundingArrangementHidden','FundingArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('MandateInfoForm:CitationNumberHidden','CitationNumberDiv',2,2);  	
    copyHiddenToDiv_ewpd('MandateInfoForm:txtState','StateDiv',2,2); 
	
hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("MandateInfoForm:tabHidden").value ;
	if(tab=="Standard Definition"){		
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{				
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}



</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitMandateInformation" /></form>

</HTML>
