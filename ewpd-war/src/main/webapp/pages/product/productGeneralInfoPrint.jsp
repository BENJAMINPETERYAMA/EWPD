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
<script>window.print();</script>
</HEAD>
<TITLE>Print Product General Information</TITLE>
<f:view>
	<BODY>
	<h:form styleClass="form" id="productGenInfoDetailedForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<!--	Start of Table for actual Data	-->
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD>&nbsp;<h:inputHidden id="temp"
					value="#{productGeneralInformationBackingBean.initViewForPrint}"></h:inputHidden>
				</TD>
			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
				<h:outputText id="breadcrumb"
					value="#{productGeneralInformationBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<!-- General information block starts -->
			<TR id="productGeneralInformation">
				<TD>
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="95%">
					<TBODY>
						<TR>

							<td colspan="1" valign="top" class="ContentArea">

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">General Information</div>
							<!--Start of Table for actual Data--> <!-- <table>
								 <tr>
									<td><h:outputText id="productName_txtLable"
										value="#{productGeneralInformationBackingBean.productName}" /></td>
								</tr>
							</table>-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
                                        <br>
										<TABLE border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="10"></TD>
												<TD width="155"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="outputText" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="178"><h:outputText id="lineOfBusinessDiv"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
                                            <TR><TD width="10"><br></TD></TR>
											<TR>
												<TD width="10"></TD>
												<TD width="155"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="outputText" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="178"><h:outputText id="businessEntityDiv"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
                                            <TR><TD width="10"><br></TD></TR>
											<TR>
												<TD width="10"></TD>
												<TD width="155"><h:outputText id="businessGroup"
													value="Business Group" styleClass="outputText" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="178"><h:outputText id="businessGroupDiv"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR> 
<!-- CARS START -->
                                            <TR><TD width="10"><br></TD></TR>
											<TR>
												<TD width="10"></TD>
												<TD width="155"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="outputText" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="178"><h:outputText id="marketBusinessUnitDiv"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>                   
<!-- CARS END -->                     
										</TABLE>
										<!-- </FIELDSET> -->
										</TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Name" /></TD>
										<TD width="184"><h:outputText id="productName_txt"
											value="#{productGeneralInformationBackingBean.productName}"
											styleClass="outputText" /> <h:inputHidden
											id="productNameHidden"
											value="#{productGeneralInformationBackingBean.productName}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>

									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText id="productFamily"
											value="Product Family" styleClass="outputText" /></TD>
										<TD width="184"><h:outputText id="productFamilyTxt"
											value="#{productGeneralInformationBackingBean.productFamily}"
											styleClass="outputText" /></TD>
										<TD width="24"></TD>
									</TR>
									<!--Adding product type starts-->
									<TR>
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Product Type" /></TD>
										<TD width="184"><h:outputText id="productTyp"
											value="#{productGeneralInformationBackingBean.productType}" /><h:inputHidden
											id="productTypeHidden"
											value="#{productGeneralInformationBackingBean.productType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Mandate Type" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productGeneralInformationBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandateType1Hidden"
											value="#{productGeneralInformationBackingBean.mandateType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub2" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="State" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde"
											value="#{productGeneralInformationBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="stateCdeHidden"
											value="#{productGeneralInformationBackingBean.stateCode}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productGenInfoDetailedForm:stateCdeHidden','productGenInfoDetailedForm:stateCde',1); </SCRIPT>
										</TD>
										<TD width="24"></TD>
									</TR>
									<!--Adding product type ends-->									
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Description" /></TD>
										<TD width="184"><h:outputText id="productDescription_txt"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
											value="#{productGeneralInformationBackingBean.productDescription}"></h:outputText> <h:inputHidden
											id="productDescription_txtHidden"
											value="#{productGeneralInformationBackingBean.productDescription}"></h:inputHidden>
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Effective Date" /></TD>
										<TD width="184"><h:outputText id="effectiveDate_txt"
											value="#{productGeneralInformationBackingBean.effectiveDate}"
											styleClass="outputText" /> <h:inputHidden
											id="effectiveDate_txtHidden"
											value="#{productGeneralInformationBackingBean.effectiveDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Expiry Date"
											styleClass="" /></TD>
										<TD width="184"><h:outputText id="expiryDate_txt"
											value="#{productGeneralInformationBackingBean.expiryDate}"
											styleClass="outputText" /> <h:inputHidden
											id="expiryDate_txtHidden"
											value="#{productGeneralInformationBackingBean.expiryDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>

										<TD width="122"><h:outputText id="productStructure"
											value="Product Structure" styleClass="outputText" /></TD>
										<TD width="184"><h:inputHidden id="productStructHidden"
											value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
										<h:outputText id="productStructDiv"
											value="#{productGeneralInformationBackingBean.productStructDiv}"
											styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"/></TD>
										<TD width="24"></TD>
									</TR>
									<TR valign="top" >
										<TD width="5"></TD>
										<TD width="150"><h:outputText id="productStructureVersion"
														value="Product Structure Version" styleClass="" /></TD>
										<TD width="253"><h:outputText id="productStructureVersionId"
														value="#{productGeneralInformationBackingBean.productStructureVersion}" />
										<TD width="21"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>

										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{productGeneralInformationBackingBean.createdBy}"
											styleClass="outputText" /> <h:inputHidden
											id="createdBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.createdBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{productGeneralInformationBackingBean.creationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="184"><h:outputText id="updatedBy_optxt"
											value="#{productGeneralInformationBackingBean.updatedBy}" />
										<h:inputHidden id="updatedBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{productGeneralInformationBackingBean.updationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>

									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"></TD>
										<TD width="184"><f:verbatim /></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<tr>
										<td width="4%"></td>
										<td align="left"></td>
										<td align="left" width="127">
										<table Width=100%>
											<tr>
												<td><h:outputText value="State" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.state}" /> <h:inputHidden
													id="stateHidden"
													value="#{productGeneralInformationBackingBean.state}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.status}" />
												<h:inputHidden id="statusHidden"
													value="#{productGeneralInformationBackingBean.status}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD><h:outputText value=":" /></TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.version}" />
												<h:inputHidden id="versionHidden"
													value="#{productGeneralInformationBackingBean.version}"></h:inputHidden>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- General information block ends -->
		</TABLE>

		<h:inputHidden id="printproductGeneralInformation"
			value="#{productGeneralInformationBackingBean.printValue}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script>
	var printForGenInfo = document.getElementById("productGenInfoDetailedForm:printproductGeneralInformation").value;
	if( "" != printForGenInfo){
		formatTildaToComma('productGenInfoDetailedForm:lineOfBusinessDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessEntityDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessGroupDiv');
		formatTildaToComma('productGenInfoDetailedForm:marketBusinessUnitDiv');
		//formatTildaToComma('productGenInfoDetailedForm:productFamilyDiv');
		//formatTildaToComma('productGenInfoDetailedForm:productStructDiv');

		copyHiddenToDiv_ewpd('productGenInfoDetailedForm:productStructHidden','productGenInfoDetailedForm:productStructDiv', 2, 1);

		copyHiddenToInputText('productGenInfoDetailedForm:productTypeHidden','productGenInfoDetailedForm:productTyp',1);
		copyHiddenToInputText('productGenInfoDetailedForm:mandateType1Hidden','productGenInfoDetailedForm:mandateType',1);

		var i;
		var obj;
		obj = document.getElementById("productGenInfoDetailedForm:productTypeHidden");
		i= obj.value;
		if(i== "MANDATE")
		{
			i=document.getElementById("productGenInfoDetailedForm:mandateType").innerHTML
			if(i == 'ST'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="State";
			sub2.style.display='';
			}else if( i == 'ET'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="ET";
			sub2.style.display='';
			}else if( i == 'FED'){
			document.getElementById("productGenInfoDetailedForm:mandateType").value="Federal";
			sub2.style.display='';
			}
			sub1.style.display='';
			
		}else{
			sub1.style.display='none';
			sub2.style.display='none';
		}

	
	}
</script>
</HTML>

