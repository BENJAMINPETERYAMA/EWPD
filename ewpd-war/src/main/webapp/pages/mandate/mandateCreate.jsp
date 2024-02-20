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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
</script>
<TITLE>Create Mandate</TITLE>
<script language="JavaScript" src="../../js/wpd.js"></script>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy" value="#{mandateBackingBean.breadCrumpCreate}"></h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="mandateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{mandateBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="168"><h:outputText value="Mandate Name *"
										styleClass="#{mandateBackingBean.requiredMandateName ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="183"><h:inputText styleClass="formInputField"
										id="txtMandateNm" tabindex="1" maxlength="250"
										value="#{mandateBackingBean.mandateName}" onkeypress="return noenter();"/></TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText value="Citation Number *"
										styleClass="#{mandateBackingBean.requiredCitationNumber? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="183"><h:inputText styleClass="formInputField"
										id="txtCitation" maxlength="15" tabindex="2"
										value="#{mandateBackingBean.citationNumber}"
										maxlength="50"/></TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText id="effectiveDateOutText"
										value="Effective Date *"
										styleClass="#{mandateBackingBean.requiredEffectiveDate ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="183"><h:inputText styleClass="formInputField"
										size="12" id="effectiveDate_txt" maxlength="10" tabindex="3"
										value="#{mandateBackingBean.effectiveDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD valign="top" width="20"><A href="#"
										onclick="cal1.select('mandateForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;"
										name="anchor1" id="anchor1"
										title="cal1.select('mandateForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="4" /> </A></TD>
								</TR>

								<TR>
									<TD width="168"><h:outputText id="expiryDateOutText"
										value="Expiry Date *"
										styleClass="#{mandateBackingBean.requiredExpiryDate ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="183"><h:inputText styleClass="formInputField"
										size="12" id="expiryDate_txt" maxlength="10" tabindex="5"
										value="#{mandateBackingBean.expiryDate}" /> <span
										class="dateFormat">(mm/dd/yyyy)</span></TD>
									<TD valign="top" width="20"><A href="#"
										onclick="cal1.select('mandateForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
										name="anchor2" id="anchor2"
										title="cal1.select('mandateForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;">
									<h:commandButton image="../../images/cal.gif"
										style="cursor: hand" alt="Cal" tabindex="6" /> </A></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="mandateType"
										value="Mandate Type *"
										styleClass="#{mandateBackingBean.requiredMandateType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD height="25" width="183"><h:selectOneMenu
										id="mandateTypeName" styleClass="formInputField" tabindex="7"
										value="#{mandateBackingBean.mandateType}" onchange="changeJurisdiction();">
										<f:selectItems id="mandateTypeList"
											value="#{ReferenceDataBackingBeanCommon.mandateTypeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
								<TR>
									<TD width="168"><h:outputText id="jurisdiction"
										value="Jurisdiction *"
										styleClass="#{mandateBackingBean.requiredJurisdiction? 'mandatoryError': 'mandatoryNormal'}" /></TD>
									<TD width="183">
									<h:inputText id="txtJurisdiction"
										styleClass="formInputFieldReadOnly"
										value="#{mandateBackingBean.jurisdiction}" readonly="true">
									</h:inputText> 
									<h:inputHidden id="hiddenJurisdiction"
										value="#{mandateBackingBean.jurisdiction}"></h:inputHidden></TD>
									<TD width="20"><h:commandButton alt="Select"
										id="jurisdictionButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="ewpdModalWindow_ewpd('../mandatepopups/selectOneJurisdictionPopup.jsp'+getUrl(), 
										'jurisdictionDiv','mandateForm:hiddenJurisdiction',2,1); return false;"
										tabindex="8"></h:commandButton></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText id="groupSize"
										value="Group Size *"
										styleClass="#{mandateBackingBean.requiredGroupSize ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD height="25" width="183"><h:selectOneMenu id="groupSizeName"
										styleClass="formInputField" tabindex="9"
										value="#{mandateBackingBean.groupSize}">
										<f:selectItems id="groupSizeList"
											value="#{ReferenceDataBackingBeanCommon.groupSizeListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText
										id="fundingArrangement" value="Funding Arrangement *"
										styleClass="#{mandateBackingBean.requiredFundingArrangement ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD height="25" width="183"><h:selectOneMenu id="fundingName"
										styleClass="formInputField" tabindex="10"
										value="#{mandateBackingBean.fundingArrangement}">
										<f:selectItems id="fundingList"
											value="#{ReferenceDataBackingBeanCommon.fundingArrangementListForCombo}" />
									</h:selectOneMenu></TD>
								</TR>
								<TR>
									<TD height="25" width="168"><h:outputText value="Description*"
										styleClass="#{mandateBackingBean.requiredDescription ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="183"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc"
										id="productDescription_txt" tabindex="11"
										value="#{mandateBackingBean.description}"></h:inputTextarea></TD>

								</TR>
								<TR>
									<TD width="168"><h:commandButton value="Create"
										styleClass="wpdButton" tabindex="12"
										action="#{mandateBackingBean.createMandate}" /></TD>
									<TD width="183">&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>


			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
copyHiddenToInputText('mandateForm:hiddenJurisdiction','mandateForm:txtJurisdiction',1);

function changeJurisdiction(){
	if (document.getElementById('mandateForm:mandateTypeName').value == 1) {
		document.getElementById('mandateForm:txtJurisdiction').value = 'ALL';
		document.getElementById('mandateForm:hiddenJurisdiction').value = 'ALL~99';
	}
	if(document.getElementById('mandateForm:mandateTypeName').value != 1){
		document.getElementById('mandateForm:txtJurisdiction').value = '';
		document.getElementById('mandateForm:hiddenJurisdiction').value = '';
	}
}
</script>
</HTML>
