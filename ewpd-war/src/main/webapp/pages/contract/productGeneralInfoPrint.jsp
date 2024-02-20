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
			<tr><td>&nbsp; </td></tr>
			<TR>
					<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>

			<TR>
				<TD><h:form styleClass="form" id="productForm">
					<h:inputHidden id="viewPageLoad"
						value="#{contractProductGeneralInfoBackingBean.printGeneralInfo}"></h:inputHidden>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>



							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>


							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<tr>
										<TD width="10">&nbsp;</TD>
										<TD width="122">&nbsp;</TD>
										<td colspan="4"><h:outputText
											value="Product General Information"
											styleClass="dataTableColumnHeader" /></td>
										<TD width="40">&nbsp;</TD>
									</tr>
									<TR>
										<TD colspan="4"><FONT color="black">Business Domain</FONT>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="135"><h:outputText id="lineOfBusiness"
													value="Line Of Business" /></TD>

												<TD width="205"><h:outputText id="lineOfBusinessHidden"
													value="#{contractProductGeneralInfoBackingBean.lineOfBusinessDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="135"><h:outputText id="businessEntity"
													value="Business Entity" /></TD>

												<TD width="205"><h:outputText id="businessEntityHidden"
													value="#{contractProductGeneralInfoBackingBean.businessEntityDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="135"><h:outputText id="businessGroup"
													value="Business Group" /></TD>

												<TD width="205"><h:outputText id="businessGroupHidden"
													value="#{contractProductGeneralInfoBackingBean.businessGroupDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
										</TABLE>
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
										<TD width="122" valign="top"><h:outputText value="Description"
											styleClass="mandatoryNormal" /></TD>
										<TD width="184"><h:inputTextarea id="txtDescription"
											value="#{contractProductGeneralInfoBackingBean.productDescription}"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"></h:inputTextarea> <h:inputHidden
											id="descriptionHidden"
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

		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productGeneralInformation" /></form>
<script>
 	formatTildaToComma('productForm:businessGroupHidden');
 	formatTildaToComma('productForm:businessEntityHidden');
 	formatTildaToComma('productForm:lineOfBusinessHidden');
  

 window.print();
</script>
</HTML>
