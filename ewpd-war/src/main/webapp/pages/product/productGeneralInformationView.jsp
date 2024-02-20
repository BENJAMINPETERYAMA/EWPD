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
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<base target=_self>
<TITLE>General Information View - Product</TITLE>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<h:inputHidden id="hidden2"
			value="#{productGeneralInformationBackingBean.hiddenInit}"></h:inputHidden>
		<h:inputHidden id="temp"
			value="#{productGeneralInformationBackingBean.initView}"></h:inputHidden>
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view_prod.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productForm">
					<w:message></w:message>

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="productTree.jsp"></jsp:include></DIV>

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
							<TABLE width="141" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="100%">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="General Information" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												action="#{productComponentAssociationBackingBean.loadComponent}">
												<h:outputText value="Component Association" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%" id="adminopttab">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{productAdminAssociationBackingBean.loadComponent}">
												<h:outputText value="Admin Option" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%" id="notestab">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD align="left" width="3"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal" onclick="loadNotes();return false;"
												width="97%"><h:outputText style="cursor:hand;"
												id="generalInfoTable" value="Notes" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{productDenialAndExclusionBackingBean.displayDenialAndExclusionTab}">
												<h:outputText value="Rules" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
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
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:370">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="5" cellpadding="3">
											<TR>
												<TD width="222"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productGeneralInformationBackingBean.lineOfBusinessDiv}"></h:inputHidden>
												<TD width="120">
												<div id="lineOfBusinessDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="24"></TD>
											</TR>
											<TR>
												<TD width="222"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productGeneralInformationBackingBean.businessEntityDiv}"></h:inputHidden>
												<TD width="120">
												<div id="businessEntityDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="24"></TD>
											</TR>
											<TR>
												<TD width="222"><h:outputText id="businessGroup"
													value="Business Group" styleClass="" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productGeneralInformationBackingBean.businessGroupDiv}"></h:inputHidden>
												<TD width="120">
												<div id="businessGroupDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="24"></TD>
											</TR>
<!-- CARS START -->							
											<TR>
												<TD width="222"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productGeneralInformationBackingBean.marketBusinessUnitDiv}"></h:inputHidden>
												<TD width="120">
												<div id="marketBusinessUnitDiv" class="selectDivReadOnly"></div>

												</TD>
												<TD width="24"></TD>
											</TR>
<!-- CARS END -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>


									<TR valign="top">
										<TD width="10" height="20"></TD>
										<TD width="122"><h:outputText value="Name" styleClass="" /></TD>
										<TD width="253"><h:outputText id="productName_txt"
											value="#{productGeneralInformationBackingBean.productName}" />
										<h:inputHidden id="productNameHidden"
											value="#{productGeneralInformationBackingBean.productName}"></h:inputHidden>
										</TD>
										<TD width="21"><f:verbatim></f:verbatim></TD>
									</TR>

									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText id="productFamily"
											value="Product Family" styleClass="" /></TD>
										<TD width="253"><h:outputText id="productFamilyTxt"
											value="#{productGeneralInformationBackingBean.productFamily}" />
											<h:inputHidden id="productFamHidden"
												value="#{productGeneralInformationBackingBean.productFamily}"></h:inputHidden>
										</TD>
										<TD width="21"><f:verbatim></f:verbatim></TD>
									</TR>

									<!--changes for product type start here-->
									<TR>
										<TD width="6" height="20"></TD>
										<TD width="112"><h:outputText id="productTyp"
											value="Product Type " /></TD>
										<TD height="9" width="253"><h:inputHidden
											id="productTypeHidden"
											value="#{productGeneralInformationBackingBean.productType}"></h:inputHidden>
										<h:inputTextarea styleClass="selectDivReadOnly"
											id="productType"
											value="#{productGeneralInformationBackingBean.productType}"
											readonly="true" style="border:0"></h:inputTextarea></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="6" height="20"></TD>
										<TD height="9" width="112"><h:outputText id="mandType1"
											value="Mandate Type" /></TD>
										<TD height="9" width="253"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType1"
											value="#{productGeneralInformationBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandateKey"
											value="#{productGeneralInformationBackingBean.mandateType}"></h:inputHidden>
										</TD>
									</TR>
									<TR id="sub2" style="display:none;">
										<TD width="6" height="20"></TD>
										<TD height="9" width="112"><h:outputText id="state1"
											value="State" /></TD>
										<h:inputHidden id="hid_state1"
											value="#{productGeneralInformationBackingBean.stateCode}">
										</h:inputHidden>
										<TD height="9" width="253"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde1"
											value="#{productGeneralInformationBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <SCRIPT>
														copyHiddenToInputText('productForm:hid_state1','productForm:stateCde1',1); 
													</SCRIPT></TD>
									</TR>
									<!--changes for product type ends  here-->

									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Description"
											styleClass="" /></TD>
										<TD width="253"><h:outputText id="productDescription_txt"
											value="#{productGeneralInformationBackingBean.productDescription}" styleClass="formTxtAreaReadOnly"></h:outputText>
											<h:inputHidden id="productDescription_txtHidden"
												value="#{productGeneralInformationBackingBean.productDescription}">
											</h:inputHidden>
										</TD>
										<TD width="21"><f:verbatim></f:verbatim></TD>
									</TR>
                                    <TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Effective Date"
											styleClass="" /></TD>
										<TD width="253"><h:outputText id="effectiveDate_txt"
											value="#{productGeneralInformationBackingBean.effectiveDate}" />
										<h:inputHidden id="effectiveDate_txtHidden"
											value="#{productGeneralInformationBackingBean.effectiveDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="21"></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Expiry Date"
											styleClass="" /></TD>
										<TD width="253"><h:outputText styleClass=""
											id="expiryDate_txt"
											value="#{productGeneralInformationBackingBean.expiryDate}" />
										<h:inputHidden id="expiryDate_txtHidden"
											value="#{productGeneralInformationBackingBean.expiryDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="21"></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText id="productStructure"
											value="Product Structure" styleClass="" /></TD>
										<h:inputHidden id="productStructHidden"
											value="#{productGeneralInformationBackingBean.productStructDiv}"></h:inputHidden>
										<TD width="253">
										<div id="productStructDiv" class=""></div>

										</TD>
										<TD width="21"></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="150"><h:outputText id="productStructureVersion"
											value="Product Structure Version" styleClass="" /></TD>
										<TD width="253"><h:outputText id="productStructureVersionId"
											value="#{productGeneralInformationBackingBean.productStructureVersion}" />
										<TD width="21"></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="253"><h:outputText id="createdBy_optxt"
											value="#{productGeneralInformationBackingBean.createdBy}" />
										<h:inputHidden id="createdBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.createdBy}">
										</h:inputHidden></TD>
										<TD width="21"><f:verbatim /></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="253"><h:outputText id="creationDate_optxt"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>
										<TD width="21"><f:verbatim /></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="253"><h:outputText id="updatedBy_optxt"
											value="#{productGeneralInformationBackingBean.updatedBy}" />
										<h:inputHidden id="updatedBy_optxtHidden"
											value="#{productGeneralInformationBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="21"><f:verbatim /></TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="253"><h:outputText id="updationDate_optxt"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDate_optxtHidden"
											value="#{productGeneralInformationBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="21"><f:verbatim /></TD>
									</TR>
									
									<TR valign="top" height="20">
										<TD width="10"></TD>
										<TD width="122"></TD>
										<TD width="253"><f:verbatim /></TD>
										<TD width="21"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>

							</FIELDSET>
							<BR>
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
												<TD>:</TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.state}" /> <h:inputHidden
													id="stateHidden"
													value="#{productGeneralInformationBackingBean.state}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Status" /></td>
												<TD>:</TD>
												<td><h:outputText
													value="#{productGeneralInformationBackingBean.status}" />
												<h:inputHidden id="statusHidden"
													value="#{productGeneralInformationBackingBean.status}"></h:inputHidden>
												</td>
											</tr>
											<tr>
												<td><h:outputText value="Version" /></td>
												<TD>:</TD>
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
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<tr>
										<td width="1%"><f:verbatim /></td>
										<td width="6%"></td>
										<td width="93%"><f:verbatim /></td>
									</tr>
								</TBODY>
							</TABLE>

							<!--	End of Page data	--></TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="hiddenProductKey"
						value="#{productGeneralInformationBackingBean.productKey}"></h:inputHidden>
					<h:inputHidden id="hiddenProductKeyValue"
						value="#{productNoteAssociationBackingBean.productKey}"></h:inputHidden>
					<h:commandLink id="notesLink"
						action="#{productNoteAssociationBackingBean.loadNotesForView}">
					</h:commandLink>
					<!-- End of hidden fields  -->

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
	name="currentPrintPage" value="productGeneralInformation" /></form>

<script>  

var i;
var obj;
i = document.getElementById("productForm:productType").innerHTML;
// i= obj.value;
if(i== "MANDATE")
{
	i=document.getElementById("productForm:mandateType1").innerHTML
	if(i == 'ST'){
	document.getElementById("productForm:mandateType1").value="State";
	sub2.style.display='';
	}else if( i == 'ET'){
	document.getElementById("productForm:mandateType1").value="ET";
	sub2.style.display='';
	}else if( i == 'FED'){
	document.getElementById("productForm:mandateType1").value="Federal";
	sub2.style.display='';
	}
	sub1.style.display='';
	
}else{
	sub1.style.display='none';
	sub2.style.display='none';
}

// Script for Admin Option/Notes tab hide for mandate
var j;
var obj1;
obj1 = document.getElementById("productForm:productType");
j= obj1.value;
if(j== "MANDATE")
{
	adminopttab.style.display='none';
	notestab.style.display='none';	
}else{
	adminopttab.style.display='';
	notestab.style.display='';
}



 copyHiddenToDiv_ewpd('productForm:lineOfBusinessHidden','lineOfBusinessDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessEntityHidden','businessEntityDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:businessGroupHidden','businessGroupDiv',2,2); 
 copyHiddenToDiv_ewpd('productForm:marketBusinessUnitHidden','marketBusinessUnitDiv',2,2); 
 //copyHiddenToDiv_ewpd('productForm:productFamHidden','productFamilyDiv',2,2);
 copyHiddenToDiv_ewpd('productForm:productStructHidden','productStructDiv',2,1);  
 function loadNotes(){
	copyToHidden('productForm:hiddenProductKey','productForm:hiddenProductKeyValue');
	submitLink('productForm:notesLink');
}


</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productGeneralInformation" />
</form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
