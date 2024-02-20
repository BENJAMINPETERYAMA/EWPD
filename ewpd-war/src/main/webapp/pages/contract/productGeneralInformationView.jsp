<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

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

<TITLE>WellPoint Product Database: Product General Info</TITLE>
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
<base target=_self>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">


			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="contractTree.jsp"></jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="200" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value=" General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									
										<TD width="100%">
											<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD class="tabNormal"> 
														<h:commandLink action="#{contractComponentGeneralInfoBackingBean.loadBenefitComponentListTab}"> <h:outputText value="Benefit Component" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>
							<!-- 	<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											>
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
-->
									<TD width="100%"></TD>
									<TD width="200"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:360">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="233"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{contractProductGeneralInfoBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="172">
												<div id="lineOfBusinessDiv" class="selectDivReadOnly"></div>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="233"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{contractProductGeneralInfoBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="172">
												<div id="businessEntityDiv" class="selectDivReadOnly"></div>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="233"><h:outputText id="businessGroup"
													value="Business Group" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{contractProductGeneralInfoBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="172">
												<div id="businessGroupDiv" class="selectDivReadOnly"></div>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="233"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="mandatoryNormal" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{contractProductGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="172">
												<div id="marketBusinessUnitDiv" class="selectDivReadOnly"></div>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Name"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="productName_txt"
											value="#{contractProductGeneralInfoBackingBean.productName}" />
										<h:inputHidden id="productName_Hidden"
											value="#{contractProductGeneralInfoBackingBean.productName}" />
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>


									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText id="productFamily"
											value="Product Family" styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="productFamily_txt"
											value="#{contractProductGeneralInfoBackingBean.productFamilyDiv}" />
										<h:inputHidden id="productFamily_Hidden"
											value="#{contractProductGeneralInfoBackingBean.productFamilyDiv}" />
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Product Type" /></TD>
										<TD width="184"><h:outputText id="ProdType"
											value="#{contractProductGeneralInfoBackingBean.productType}" />
										<h:inputHidden id="productType_Hidden"
											value="#{contractProductGeneralInfoBackingBean.productType}"/>
										</TD>

										<TD width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText id="productStructure"
											value="Product Structure" styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="productStruct_txt"
											value="#{contractProductGeneralInfoBackingBean.productStructDiv}" />
										<h:inputHidden id="productStruct_Hiddent"
											value="#{contractProductGeneralInfoBackingBean.productStructDiv}" />
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="152"><h:outputText id="productStructureVersionId"
											value="Product Structure Version" styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="productStructVer_txt"
											value="#{contractProductGeneralInfoBackingBean.productStructureVersion}" />
										<h:inputHidden
											value="#{contractProductGeneralInfoBackingBean.productStructureVersion}"></h:inputHidden>

										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122" valign="top"><h:outputText value="Description"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="txtDescription"
											value="#{contractProductGeneralInfoBackingBean.productDescription}" styleClass="formTxtAreaReadOnly"></h:outputText> 
											<h:inputHidden id="descriptionHidden"
											value="#{contractProductGeneralInfoBackingBean.productDescription}"></h:inputHidden>
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
                                    <TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Effective Date"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="effectiveDate_txt"
											value="#{contractProductGeneralInfoBackingBean.effectiveDate}" />
										<h:inputHidden id="effectiveDate_Hidden"
											value="#{contractProductGeneralInfoBackingBean.effectiveDate}" />
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Expiry Date"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="expiryDate_txt"
											value="#{contractProductGeneralInfoBackingBean.expiryDate}" />
										<h:inputHidden id="expiryDate_Hidden"
											value="#{contractProductGeneralInfoBackingBean.expiryDate}" />
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Version"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:outputText id="productVersionId"
											value="#{contractProductGeneralInfoBackingBean.productVersion}" />
										<h:inputHidden
											value="#{contractProductGeneralInfoBackingBean.productVersion}"></h:inputHidden>
										</TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{contractProductGeneralInfoBackingBean.createdBy}" />
										<h:inputHidden id="createdByHidden"
											value="#{contractProductGeneralInfoBackingBean.createdBy}"></h:inputHidden>
										</TD>

										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{contractProductGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{contractProductGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>

										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="184"><h:outputText id="updatedBy_optxt"
											value="#{contractProductGeneralInfoBackingBean.updatedBy}" />
										<h:inputHidden id="updatedByHidden"
											value="#{contractProductGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{contractProductGeneralInfoBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDateHidden"
											value="#{contractProductGeneralInfoBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>

							</FIELDSET>
							<BR>
							<BR>

							<!--	End of Page data	--></TD>
						</TR>
					</TABLE>


				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractProductGeneralInformation" /></form>
<script>
 
 copyHiddenToDiv_ewpd('productForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:marketBusinessUnitHidden','marketBusinessUnitDiv',2,2); 
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
