<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<base target=_self>

<TITLE>ProductBenefitMandateInformationView</TITLE>
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
<BODY>
<h:inputHidden id="Hidden"
		value="#{productBenefitMandateBackingBean.dummyVar}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</td>
		</tr>
		
		
		<tr>
			<td>
				<h:form styleClass="form" id="nonAdjBenCompForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->	<jsp:include page="productTree.jsp"></jsp:include>						

						 		</DIV>

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message></w:message>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td width="186" class="tabNormal"><h:commandLink action ="#{productBenefitGeneralInfoBackingBean.getProductBenefitGenaralInfo}"> <h:outputText value="General Information"/> </h:commandLink></td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink action = "#{productBenefitDetailBackingBean.getBenefitDefinitionsPage}"> <h:outputText value="Mandate Definition"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
		          						</td>
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/activeTabLeft.gif"  width="3" height="21" /></td>
													<td class="tabActive"> 
														<h:commandLink > <h:outputText value="Mandate Information"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/activeTabRight.gif"  width="4" height="21" /></td>
												</tr>
											</table>
										</td>
								<td width="200">
								
								</td>
								<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
									<tr>
											<td width="24%">&nbsp;<h:outputText											
												id="txtStateCode" value="State" /></td>
											<td width="42%"><DIV id="StateDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtState"
												value="#{productBenefitMandateBackingBean.stateCode}"></h:inputHidden>
											</td>										
											
									</tr>
									<tr>
										<td width="24%">&nbsp;<h:outputText											
											id="MandateTypeStar" value="Mandate Category " /></td>
										<td width="42%"><h:outputText value="#{productBenefitMandateBackingBean.mandateCategory}"></h:outputText></td>										
										
									</tr>
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="CitationNumberStar" value="Citation Number " /></TD>
										<TD width="42%"><DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV>
										<h:inputHidden id="CitationNumberHidden"
												value="#{productBenefitMandateBackingBean.citationNumber}"></h:inputHidden>										
										</TD>
									</TR>
										
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="FundingArrangementStar" value="Funding Arrangement " /></TD>
										<TD width="42%"><DIV id="FundingArrangementDiv" class="selectDivReadOnly"></DIV>
										<h:inputHidden id="FundingArrangementHidden" value="#{productBenefitMandateBackingBean.fundingArrangement}"></h:inputHidden>
										
										</TD>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="EffectivenessStar" value="Effectiveness " /></TD>
										<td width="42%"><h:outputText value="#{productBenefitMandateBackingBean.effectiveness}"></h:outputText>
										
										</td>
									</TR>

									<TR>
									<TD width="24%" valign="top">&nbsp;<h:outputText										
										id="descriptionStar" value="Text " /></TD>
									<TD width="42%" valign="top">
									<DIV id="TxtDescDiv" class="selectDivReadOnly"></DIV>
									<h:inputHidden id="txtDescription"
										value="#{productBenefitMandateBackingBean.text}" ></h:inputHidden></TD>
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
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" ></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
		</tr>
	</table>
</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitMandateInfoPrint" /></form>
<script>
	copyHiddenToDiv_ewpd('nonAdjBenCompForm:FundingArrangementHidden','FundingArrangementDiv',2,2); 
	copyHiddenToDiv_ewpd('nonAdjBenCompForm:CitationNumberHidden','CitationNumberDiv',2,2);  
	copyHiddenToDiv_ewpd('nonAdjBenCompForm:txtDescription','TxtDescDiv',2,1); 
	copyHiddenToDiv_ewpd('nonAdjBenCompForm:txtState','StateDiv',2,2); 
	//formatTildaToComma('nonAdjBenCompForm:txtStateCode');
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
