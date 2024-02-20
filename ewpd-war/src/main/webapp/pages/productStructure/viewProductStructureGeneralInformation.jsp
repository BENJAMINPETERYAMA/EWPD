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

<TITLE>View Product Structure</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="getValue"
					value="#{productStructureGeneralInfoBackingBean.generalInfo}"></h:inputHidden>
				<TD><jsp:include page="../navigation/top_view_prod.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="productStructureGeneralInfoForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">

							<DIV class="treeDiv" style="height:580"><jsp:include
								page="../productStructure/productStructureTree.jsp" /></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE width="100%">
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>
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
										<TBODY>
											<TR>
												<TD width="3" align="left"><IMG
													src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
													width="3" height="21"></TD>
												<TD class="tabNormal"><h:commandLink
													action="#{productStructureBenefitComponentBackingBean.loadBenefitComponent}"
													id="linkToBenefitComponent">
													<h:outputText id="labelBC" value="Benefit Component"></h:outputText>
												</h:commandLink></TD>
												<TD width="2" align="right"><IMG
													src="../../images/tabNormalRight.gif"
													alt="Tab Right Normal" width="4" height="21"></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:450">

							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText" width="100%">
								<TBODY>
									<TR>
										<TD colspan="5">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:65%">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
											<TR>
												<TD width="130"><h:outputText value="Line of Business" /></TD>
												<TD width="194"><h:inputHidden id="lineofBusinessHidden"
													value="#{productStructureGeneralInfoBackingBean.lob}"></h:inputHidden>
												<DIV id="divGroupSizeForLob" class="selectDivReadOnly"></DIV>
												<SCRIPT> // parseForDiv(document.getElementById('divGroupSizeForLob'), document.getElementById('productStructureGeneralInfoForm:lineofBusinessHidden')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Entity" /></TD>
												<TD width="194"><h:inputHidden id="businessEntityHidden"
													value="#{productStructureGeneralInfoBackingBean.entity}"></h:inputHidden>
												<DIV id="divGroupSizeForEntity" class="selectDivReadOnly"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('divGroupSizeForEntity'), document.getElementById('productStructureGeneralInfoForm:businessEntityHidden')); </SCRIPT>
												</TD>
											</TR>
											<TR>
												<TD width="130"><h:outputText value="Business Group" /></TD>
												<TD width="194"><h:inputHidden id="businessGroupHidden"
													value="#{productStructureGeneralInfoBackingBean.group}"></h:inputHidden>
												<DIV id="divGroupSizeForGroup" class="selectDivReadOnly"></DIV>
												<SCRIPT>// parseForDiv(document.getElementById('divGroupSizeForGroup'), document.getElementById('productStructureGeneralInfoForm:businessGroupHidden')); </SCRIPT>
												</TD>
											</TR>
											<!-- CARS START -->
											<TR>
												<TD width="130"><h:outputText value="Market Business Unit" /></TD>
												<TD width="194"><h:inputHidden id="marketBusinessUnitHidden"
													value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<DIV id="divGroupSizeFormarketBusinessUnit" class="selectDivReadOnly"></DIV>
												</TD>
											</TR>	
											<!-- CARS END -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Name" /></TD>
										<TD width="239"><h:outputText id="structureName"
											value="#{productStructureGeneralInfoBackingBean.name}" /> <h:inputHidden
											id="nameHidden"
											value="#{productStructureGeneralInfoBackingBean.name}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:nameHidden','productStructureGeneralInfoForm:structureName',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Product Family" /></TD>	
										<TD width="239"><h:outputText id="ProductFamilyForView"
											value="#{productStructureGeneralInfoBackingBean.productFamilyForView}" />
										<h:inputHidden id="productFamilyForViewHidden"
											value="#{productStructureGeneralInfoBackingBean.productFamilyForView}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:productFamilyForViewHidden','productStructureGeneralInfoForm:productFamilyForView',1); </SCRIPT>
										</TD>							
									</TR>		
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Structure Type" /></TD>
										<TD width="239"><h:outputText id="strType"
											value="#{productStructureGeneralInfoBackingBean.structureType}" />
										<h:inputHidden id="strTypeHidden"
											value="#{productStructureGeneralInfoBackingBean.structureType}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:strTypeHidden','productStructureGeneralInfoForm:strType',1); </SCRIPT>
										</TD>
									</TR>
									<tr id="sub1" style="display:none;">
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Mandate Type" /></TD>
										<TD width="239"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productStructureGeneralInfoBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandTypeHidden"
											value="#{productStructureGeneralInfoBackingBean.mandateType}">
										</h:inputHidden><SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:mandTypeHidden','productStructureGeneralInfoForm:mandateType',1);</SCRIPT>
										</TD>
									</TR>
									<tr id="sub2" style="display:none;">
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Jurisdiction" /></TD>
										<TD width="239"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde"
											value="#{productStructureGeneralInfoBackingBean.stateCode}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="stateCdeHidden"
											value="#{productStructureGeneralInfoBackingBean.stateCode}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:stateCdeHidden','productStructureGeneralInfoForm:stateCde',2); </SCRIPT>
										</TD>
									</TR>
									<tr id="sub3" style="display:none;">
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Jurisdiction" /></TD>
										<TD width="239"><h:outputText id="stateFed" value="ALL" /></TD>
									</TR>
									

									<TR>
										<TD width="3"></TD>
										<TD width="25%" valign="top"><h:outputText value="Description" /></TD>
										<TD width="239"><h:outputText id="structureDesc"
											value="#{productStructureGeneralInfoBackingBean.description}" styleClass="formTxtAreaReadOnly"></h:outputText>
										<h:inputHidden id="descHidden"
											value="#{productStructureGeneralInfoBackingBean.description}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:descHidden','productStructureGeneralInfoForm:structureDesc',1); </SCRIPT>
										</TD>
									</TR>
                                    <TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Effective Date " /></TD>
										<TD width="239"><h:outputText id="effectiveDate"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}" />
										<h:inputHidden id="effDateHidden"
											value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:effDateHidden','productStructureGeneralInfoForm:effectiveDate',1); </SCRIPT>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Expiry Date " /></TD>
										<TD width="239"><h:outputText id="expiryDate"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}" />
										<h:inputHidden id="expiryDateHidden"
											value="#{productStructureGeneralInfoBackingBean.expiryDate}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:expiryDateHidden','productStructureGeneralInfoForm:expiryDate',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Created By" /></TD>
										<TD width="239"><h:outputText id="createdUser"
											value="#{productStructureGeneralInfoBackingBean.createdUser}" />
										<h:inputHidden id="createdUserHidden"
											value="#{productStructureGeneralInfoBackingBean.createdUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:createdUserHidden','productStructureGeneralInfoForm:createdUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Created Date" /></TD>
										<TD width="239"><h:outputText id="creationDate"
											value="#{productStructureGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productStructureGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:creationDateHidden','productStructureGeneralInfoForm:creationDate',1); </SCRIPT>
										<h:inputHidden id="time" value="#{requestScope.timezone}">
										</h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Last Updated By" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedUser"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
										<h:inputHidden id="updatedUserHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:updatedUserHidden','productStructureGeneralInfoForm:lastUpdatedUser',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="3"></TD>
										<TD width="25%"><h:outputText value="Last Updated Date" /></TD>
										<TD width="239"><h:outputText id="lastUpdatedDate"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updatedDateHidden"
											value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:updatedDateHidden','productStructureGeneralInfoForm:lastUpdatedDate',1); </SCRIPT>
										</TD>
									</TR>

									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
								</TBODY>

							</TABLE>
							</FIELDSET>
							<br>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:1px;width:100%">
							<TABLE border="0" cellspacing="0" cellpadding="3" width="99%">
								<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td>State</td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText id="state"
												value="#{productStructureGeneralInfoBackingBean.state}">
											</h:outputText> <h:inputHidden id="stateHidden"
												value="#{productStructureGeneralInfoBackingBean.state}">
											</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:stateHidden','productStructureGeneralInfoForm:state',1); </SCRIPT>
											</td>
										</tr>
										<tr>
											<td>Status</td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText id="status"
												value="#{productStructureGeneralInfoBackingBean.status}"></h:outputText>
											<h:inputHidden id="statusHidden"
												value="#{productStructureGeneralInfoBackingBean.status}">
											</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:statusHidden','productStructureGeneralInfoForm:status',1); </SCRIPT>

											</td>
										</tr>
										<tr>
											<td>Version</td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText id="version"
												value="#{productStructureGeneralInfoBackingBean.version}">
											</h:outputText> <h:inputHidden id="versionHidden"
												value="#{productStructureGeneralInfoBackingBean.version}">
											</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureGeneralInfoForm:versionHidden','productStructureGeneralInfoForm:version',1); </SCRIPT>
											</td>
										</tr>
									</table></td>
								</tr>
							</TABLE>
							</FIELDSET>
							
							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			
			<TR>
				<TD><%@ include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="generalInfo" /></form>
<script language="javascript">
copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:lineofBusinessHidden','divGroupSizeForLob',2,2);
copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:businessEntityHidden','divGroupSizeForEntity',2,2);
copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:businessGroupHidden','divGroupSizeForGroup',2,2);
copyHiddenToDiv_ewpd('productStructureGeneralInfoForm:marketBusinessUnitHidden','divGroupSizeFormarketBusinessUnit',2,2);

getStructureType();
getMandateType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("productStructureGeneralInfoForm:strType");
		if(obj.value== "Mandate" || obj.value == "MANDATE" ||obj.value== "Mandate")
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}

function getMandateType()
	{
	var obj;
	obj = document.getElementById("productStructureGeneralInfoForm:mandateType");
	if( obj.value == "ET" || obj.value == "ST"){
				if(obj.value=="ST")
					document.getElementById("productStructureGeneralInfoForm:mandateType").value = "State";
				else
					document.getElementById("productStructureGeneralInfoForm:mandateType").value = "Extra-Territorial";

		sub2.style.display='';
		sub3.style.display='none';
	}else if(obj.value=="FED"){
		document.getElementById("productStructureGeneralInfoForm:mandateType").value = "Federal";
		sub3.style.display='';
		sub2.style.display='none';
	}else {
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
	}
	}
document.getElementById('divGroupSizeForLob').style.height= "17px";
document.getElementById('divGroupSizeForEntity').style.height= "17px";
document.getElementById('divGroupSizeForGroup').style.height= "17px";
document.getElementById('divGroupSizeFormarketBusinessUnit').style.height= "17px";
</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
