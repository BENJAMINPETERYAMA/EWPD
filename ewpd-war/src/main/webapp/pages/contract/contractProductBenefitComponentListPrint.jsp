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
<script>window.print();</script>
<TITLE>WellPoint Product Database: Contract Product components</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('contractComponentListForm:saveButton');">
	<hx:scriptCollector id="scriptCollector1">
	<h:form styleClass="form" id="contractComponentListForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
					<h:inputHidden id="viewPageLoad"
						value="#{contractComponentGeneralInfoBackingBean.printGeneralInfo}"></h:inputHidden>
					<h:inputHidden id="viewPageLoadProduct"
						value="#{contractProductGeneralInfoBackingBean.printGeneralInfo}"></h:inputHidden>
			<tr><td>&nbsp; </td></tr>
			<TR>
					<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractComponentGeneralInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>

			<TR>
			
				<TD>
					<DIV id="productGeneralInformation">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD colspan="2" valign="top" class="ContentArea">
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="98%">
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
										<TD colspan="4"><FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:360"><LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="163"><h:outputText id="lineOfBusiness"
													value="Line Of Business" /></TD>

												<TD width="189"><h:outputText id="lineOfBusinessHidden"
													value="#{contractProductGeneralInfoBackingBean.lineOfBusinessDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="163"><h:outputText id="businessEntity"
													value="Business Entity" /></TD>

												<TD width="189"><h:outputText id="businessEntityHidden"
													value="#{contractProductGeneralInfoBackingBean.businessEntityDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="163"><h:outputText id="businessGroup"
													value="Business Group" /></TD>

												<TD width="189"><h:outputText id="businessGroupHidden"
													value="#{contractProductGeneralInfoBackingBean.businessGroupDiv}"></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
											<TR>
												<TD width="163"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" /></TD>

												<TD width="189"><h:outputText id="marketBusinessUnitHidden"
													value="#{contractProductGeneralInfoBackingBean.marketBusinessUnit}"></h:outputText>
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
											value="#{contractProductGeneralInfoBackingBean.productDescription}" 
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;">
											</h:outputText> <h:inputHidden
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
							</table>
							</FIELDSET>						</TD>
					</TR>
				</TABLE>
			</DIV>
		</TD>
	</TR>

									<tr>
									<td>
										<DIV id="panelBenefitComponentList">
					<TABLE border="0" cellspacing="0" cellpadding="0"
						class="outputText" width="100%">
						<TBODY>
							<TR>
								<TD>
								<FIELDSET
								style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%"><BR />
								
								<div id="panel2Member">
								<div id="resultInfoMember" class="dataTableColumnHeader"><br />
								<CENTER>No Benefit Components is Available.</CENTER>
								</div>

								<div id="resultHeaderDivMember">

								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
									id="resultHeaderTableMember" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left"><h:outputText
												styleClass="formOutputColumnField"
												value="Benefit Components"></h:outputText></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								<%--
<h:inputHidden id="loadPricingInfoList" value= "#{contractPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>
								<div id="panel2ContentMember"
									style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<table cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td align="left"><h:dataTable headerClass="tableHeaderMember"
											id="resultsTableMember" border="0"
											value="#{contractComponentGeneralInfoBackingBean.benefitComponentList}"
											rendered="#{contractComponentGeneralInfoBackingBean.benefitComponentList != null}"
											var="eachRow" cellpadding="0" cellspacing="1">

											<h:column>
												<h:outputText id="caseNumber"
													styleClass="formOutputColumnField"
													value="#{eachRow.benefitComponentDesc}"></h:outputText>
											</h:column>
											
										</h:dataTable></td>
									</tr>
								</table>

								</div>
								</div>

								<br>
								</FIELDSET>

								</TD>
							</TR>
						</TBODY>
					</TABLE>
					</DIV>
									</td>
								</tr>
								
							</TABLE>

						
							<BR>
							<BR>

							<!--	End of Page data	-->
					

			<h:inputHidden id="printProductGeneralInformation"
				value="#{contractProductGeneralInfoBackingBean.printValue}"></h:inputHidden>
			<h:inputHidden id="printContractBenefitComponents"
				value="#{contractComponentGeneralInfoBackingBean.printValue}"></h:inputHidden>
				</h:form>
		
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractProductBenefitComponentList" /></form>
<script>
	var printContractBenefitComponents = document.getElementById("contractComponentListForm:printContractBenefitComponents").value;
	var printProductGeneralInformation = document.getElementById("contractComponentListForm:printProductGeneralInformation").value;

	if( null == printProductGeneralInformation || "" == printProductGeneralInformation ){
		var genInfoDivObj = document.getElementById('productGeneralInformation');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	} 
	else{
	   	formatTildaToComma('contractComponentListForm:businessGroupHidden');
	 	formatTildaToComma('contractComponentListForm:businessEntityHidden');
	 	formatTildaToComma('contractComponentListForm:lineOfBusinessHidden');
	 	formatTildaToComma('contractComponentListForm:marketBusinessUnitHidden');
	}

	if( null == printContractBenefitComponents || "" == printContractBenefitComponents ){
		var genInfoDivObj = document.getElementById('panelBenefitComponentList');
		//alert("genInfoDivObj - "+genInfoDivObj);
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	} 
	else{
		var genInfoDivObj = document.getElementById('panelBenefitComponentList');
		genInfoDivObj.style.visibility = "visible";

		var divObj = document.getElementById('resultInfoMember');
		var divHeaderObj = document.getElementById('panel2ContentMember');
		var divResultHeaderObj = document.getElementById('resultHeaderDivMember');

		var tableObj = document.getElementById('contractComponentListForm:resultsTableMember');
		if (tableObj.rows.length > 0) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}

	}
</script>
</HTML>
