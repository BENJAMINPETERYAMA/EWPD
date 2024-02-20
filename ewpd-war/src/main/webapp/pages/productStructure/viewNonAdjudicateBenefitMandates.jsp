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

<TITLE>viewNonAdjudicateBenefitMandates</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="benefitAdmnViewForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="200" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<TD class="tabNormal"><h:commandLink
											action="#{productStructureBenefitDefenitionBackingBean.viewBenefitDefenition}"
											id="linkToGeneralInfo">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productStructureBenefitDefenitionBackingBean.displayStandardBenefit}"
											id="linkToStandardDef">
											<h:outputText
												value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<%--<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal">
											<h:outputText value="Adj Benefit Mandates" />
										</td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td> --%>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive"><h:commandLink>
											<h:outputText value="Mandate Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<TR id="stateRow">
								<TD width="24%" height="30">&nbsp;<h:outputText id="stateCd"
									value="Jurisdiction " /></TD>
								<TD width="42%" height="30"><!-- <DIV id="StateDiv" class="selectDivReadOnly"></DIV> -->
								<h:outputText id="state"
									value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:outputText>
								<h:inputHidden id="txtState"
									value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:inputHidden></TD>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</TR>
							<tr>
								<td width="24%" height="30">&nbsp;<h:outputText
									id="MandateTypeStar" value="Mandate Category " /></td>
								<td width="42%" height="30"><h:outputText
									value="#{productStructureBenefitMandatesBackingBean.mandateCategory}"></h:outputText>
									<h:inputHidden id="hiddenMndtCategory" 
										value="#{productStructureBenefitMandatesBackingBean.mandateCategory}"></h:inputHidden>
								</td>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</tr>
							<TR>
								<TD width="24%" height="30">&nbsp;<h:outputText
									id="CitationNumberStar" value="Citation Number " /></TD>
								<TD width="42%" height="30"><!--<DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV>  -->
								<h:outputText id="CitationNumber"
									value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:outputText>
								<h:inputHidden id="CitationNumberHidden"
									value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:inputHidden>
								</TD>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</TR>

							<TR>
								<TD width="24%" height="30">&nbsp;<h:outputText
									id="FundingArrangementStar" value="Funding Arrangement " /></TD>
								<TD width="42%" height="30">
								<DIV id="FundingArrangementDiv" class="selectDivReadOnly"
									style="border:0"></DIV>
								<h:inputHidden id="FundingArrangementHidden"
									value="#{productStructureBenefitMandatesBackingBean.fundingArrangement}"></h:inputHidden>
								</TD>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</TR>

							<TR>
								<TD width="24%" height="30">&nbsp;<h:outputText
									id="EffectivenessStar" value="Effectiveness " /></TD>
								<td width="42%" height="30"><h:outputText
									value="#{productStructureBenefitMandatesBackingBean.effectiveness}"></h:outputText>
									<h:inputHidden id="hiddenEffectiveness" 
										value="#{productStructureBenefitMandatesBackingBean.effectiveness}"></h:inputHidden>
								</td>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</TR>


							<TR>
								<TD width="24%" valign="top" height="30">&nbsp;<h:outputText
									id="descriptionStar" value="Text " /></TD>
								<TD width="42%" valign="top" height="30"><h:outputText
									id="txtDescription" value="#{productStructureBenefitMandatesBackingBean.text}"></h:outputText>
									<h:inputHidden id="hiddenDesc" value="#{productStructureBenefitMandatesBackingBean.text}"></h:inputHidden>
								</TD>
								<td width="75%" height="30"><f:verbatim></f:verbatim></td>
							</TR>


							<TR>

							</TR>
							<TR>
								<TD width="221">&nbsp;</TD>
							</TR>
						</table>
						</fieldset>
						<br />
						</td>
					</tr>
				</table>
				<!--	End of Page data	-->

	<!-- Space for hidden fields -->
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
	<h:inputHidden id="statHidden"
		value="#{productStructureBenefitMandatesBackingBean.stateCode}" />
	<h:commandLink id="hiddenLnk1"
		style="display:none; visibility: hidden;">
		<f:verbatim />
	</h:commandLink>
	<!-- End of hidden fields  -->

	</h:form>
	</td>
	</tr>
	<tr>
		<td><%@ include file="../navigation/bottom_view.jsp"%></td>
	</tr>
	</table>
	</BODY>
</f:view>

<script>
	
	formatTildaToCommaForStateCode('benefitAdmnViewForm:state');
	// copyHiddenToDiv_ewpd('benefitAdmnViewForm:txtState','StateDiv',2,2);
	copyHiddenToDiv_ewpd('benefitAdmnViewForm:FundingArrangementHidden','FundingArrangementDiv',2,2); 
	// copyHiddenToDiv_ewpd('benefitAdmnViewForm:CitationNumberHidden','CitationNumberDiv',2,2); 
	formatTildaToCommaForStateCode('benefitAdmnViewForm:CitationNumber');

	// displayState();
	function displayState(){
	var state = document.getElementById('benefitAdmnViewForm:statHidden').value;
	if(state=="" || state==null)
		stateRow.style.display='none';

	else
		stateRow.style.display='';

	}
	function formatTildaToCommaForStateCode(objName)
	{
	    
		var formattedString = "";
		var obj = document.getElementById(objName);
	
		var val = obj.innerHTML;
	
		if(val == null || val == '')
			return;
		
		var values = val.split('~');
		if(values != null)
		{
	
			for(i=1, n = values.length; i < n; i+=2)
			{
				formattedString += values[i] ;
				if(i < n-2)
				formattedString += ", " ;
			}
			obj.innerHTML = formattedString;
		}
		return ;
	}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="nonAdjudicate" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
