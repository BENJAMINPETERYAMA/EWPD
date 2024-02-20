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
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Benefit Mandate View</TITLE>
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
<BASE target="_self" />
</head>

<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="benefitMandateViewForm">
				<h:inputHidden id="viewBenefitMandate"
					value="#{benefitMandateBackingBean.viewBenefitMandate}" />
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="273"><DIV class="treeDiv"><jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>
						</TD>
						<TD colspan="1" valign="top" class="ContentArea" width="963">
						<TABLE>
							<TBODY>
								<TR>
									<TD><!-- Insert WPD Message Tag --></TD>
								</TR>
							</TBODY>
						</TABLE>




						<!-- Table containing Tabs -->



						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">


							<TR>
								<TD width="34%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{standardBenefitBackingBean.loadStandardBenefitView}">
											<h:outputText value="General Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{benefitDefinitionBackingBean.loadBenefitDefinitionView}">
											<h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<!-- 
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal">
												<h:commandLink action="#{MandateDefinitionBackingBean.loadMandateDefinition}">
												<h:outputText value="Adj Benefit Mandates" /></h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
 -->
								<TD width="33%" id="manInfo">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD class="tabActive" align="center" width="99%"><h:outputText
											value="Mandate Information" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<td width="33%" id="noteTab">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{standardBenefitNotesBackingBean.loadBenefitNotesView}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2%" align="right"><img
											src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">

							<tr>
								<td>
								<div id="stateCodeDiv">
								<TABLE width="211%">
									<TBODY>
										<tr>
											<td width="48%">&nbsp;<h:outputText id="StateCodeStar"
												value="Jurisdiction " /></td>
											<td width="52%">
											<table cellspacing="0" cellpadding="0" border="0"
												width="100%">
												<tr>
													<td width="88%">
													<DIV id="StateDiv" class="selectDivReadOnly"></DIV>
													<h:inputHidden id="txtState"
														value="#{benefitMandateBackingBean.stateCode}"></h:inputHidden>
													</TD>

												</tr>
											</table>
											</td>
										</tr>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>





							<tr>
								<td width="32%">&nbsp;<h:outputText id="MandateCategoryStar"
									value="Mandate Category " /></td>
								<td width="68%">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><h:inputTextarea styleClass="selectDivReadOnly"
											tabindex="5" readonly="true" style="border:0"
											value="#{benefitMandateBackingBean.mandateCategory}"></h:inputTextarea>
										</td>
										<td width="63%" align="right"></td>
									</tr>
								</table>
								</td>
							</tr>
							<TR>
								<TD width="32%">&nbsp;<h:outputText id="CitationNumberStar"
									value="Citation Number" /></TD>
								<TD width="68%">
								<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
									<TR>
										<TD width="44%">
										<DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV>
										<h:inputHidden id="txtCitationNumber"
											value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
										</TD>

									</TR>
								</TABLE>
								</TD>
							</TR>


							<TR>
								<TD width="32%">&nbsp;<h:outputText id="FundingArrangementStar"
									value="Funding Arrangement " /></TD>
								<TD width="68%">
								<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
									<TR>
										<TD width="44%">
										<DIV id="FundingArrangementDiv" class="selectDivReadOnly" style="border:0"></DIV>
										<h:inputHidden id="txtFundingArrangement"
											value="#{benefitMandateBackingBean.fundingArrangement}"></h:inputHidden>
										</TD>

									</TR>
								</TABLE>
								</TD>
							</TR>

							<TR>
								<TD width="32%">&nbsp;<h:outputText id="EffectivenessStar"
									value="Effectiveness " /></TD>
								<td width="68%">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td><h:inputTextarea styleClass="selectDivReadOnly"
											tabindex="5" readonly="true" style="border:0"
											value="#{benefitMandateBackingBean.effectiveness}"></h:inputTextarea>
										<td width="63%" align="right"></td>
									</tr>
								</table>
								</td>
							</TR>


							<TR>
								<TD valign="top" width="32%">&nbsp;<h:outputText
									id="descriptionStar" value="Text " /></TD>
								<TD valign="top" width="68%"><h:outputText id="txtDescription"
									value="#{benefitMandateBackingBean.text}"></h:outputText></TD>
								<td width="63%"><f:verbatim></f:verbatim></td>
							</TR>



							<TR>
								<TD width="206">&nbsp;</TD>
							</TR>
						</TABLE>

						</FIELDSET>
						<br />

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td align="right">
								<table>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitMandateBackingBean.state}" /></td>
										
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitMandateBackingBean.status}" /></td>
										
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitMandateBackingBean.version}" /></td>
										
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>


						

						<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
							id="tabHidden"
							value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
						<!-- End of hidden fields  --></TD>
					</TR>
				</table>
			</h:form></td>
		</tr>
		<tr>
			<td colspan="2" width="100%"><%@ include
				file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
	// Space for page realated scripts
	copyHiddenToDiv_ewpd('benefitMandateViewForm:txtFundingArrangement','FundingArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('benefitMandateViewForm:txtState','StateDiv',3,2); 
	parseForDiv(document.getElementById('CitationNumberDiv'), document.getElementById('benefitMandateViewForm:txtCitationNumber')); 

	hideIfNoValue('stateCodeDiv','txtState');

function hideIfNoValue(divId, txtName){
		hiddenIdObj = document.getElementById('benefitMandateViewForm:'+txtName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj.value == 'null'){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}	
	
	hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitMandateViewForm:tabHidden").value ;
	if(tab=="Standard Definition"){
		manInfo.style.display='none';
		noteTab.style.display='';
	}
	else{
		manInfo.style.display='';
		noteTab.style.display='none';
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitMandate" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
