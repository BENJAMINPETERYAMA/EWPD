<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function submitTestSuite()
	 {
		if(document.getElementById("TSCreatForm:create") != null)
			return submitOnEnterKey('TSCreatForm:create');
		else
			return submitOnEnterKey('TSCreatForm:update');
	 }
</script>
<TITLE>Create Test Suite</TITLE>
</HEAD>
<f:view>
<body  onkeydown="return submitTestSuite();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
	</table>
	<h:form id="TSCreatForm">
	<table align="center" border="0" cellspacing="0" cellpadding="0" width="80%">
		<tr>
			<td colspan="2" valign="top" class="ContentArea">
			<table width="400" border="0" cellpadding="0" cellspacing="0" id="TabTable">
				<tr>
					<td width="200">
						<table width="200" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="2" align="left"><img src="../../images/activeTabLeft.gif" alt="Tab Left Active" width="3" height="21" /></td>
								<td width="186" class="tabActive"><a href="#"> Test Suite Information </a></td>
								<td width="2" align="right"><img src="../../images/activeTabRight.gif" alt="Tab Right Active" width="2" height="21" /></td>
							</tr>
						</table>
					</td>
					<td width="200">
						<table width="200" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
								<td width="186" class="tabNormal">
									<h:commandLink action="#{testSuiteBackingBean.goToSelectTestCase}"> 
										<h:outputText value="Test Case Information"/>
									</h:commandLink>
								</td>
								<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="2" height="21" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<fieldset>
			<div><w:message></w:message></div>
			<table border="0" cellspacing="0" cellpadding="3">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td valign="top" width="177">
						<h:outputText styleClass="#{ (testSuiteBackingBean.testSuiteNameEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Test Suite*" />
					</td>
					<td colspan="2" valign="top">
						<h:inputText id ="testSuiteName" styleClass="formInputField" value="#{testSuiteBackingBean.testSuiteName}"   onkeyup="return ismaxlength(this,70);"/>
					</td>
				</tr>
				<tr>
					<td valign="top" width="18%">
						<h:outputText styleClass="#{ (testSuiteBackingBean.testSuiteDescEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Description" />
					</td>
					<td colspan="3" valign="top">
						<h:inputTextarea id="testSuiteDesc" styleClass="formTxtAreaField_GeneralDesc" value="#{testSuiteBackingBean.testSuiteDesc}" cols="37" rows="7"
										   onblur="return validateTestSuiteDesc();" />
						
					</td>
				</tr>
				<tr>
					<td valign="top" width="188">
						<h:outputText styleClass="#{ (testSuiteBackingBean.contractIdEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Contract ID*" />
					</td>
					<td valign="left" width="183">
						<h:inputText  id="contractId" styleClass="formInputField" value="#{testSuiteBackingBean.contractId}"   onkeyup="return ismaxlength(this,9);"/>
					</td>
				</tr>
				<tr>
					<td valign="top" width="177">
						<h:outputText styleClass="#{ (testSuiteBackingBean.startDateEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Start Date*" />
					</td>
					<td colspan="2" valign="top">
						<h:inputText styleClass="formInputField" size="12" id="startDate_txt" maxlength="10" value="#{testSuiteBackingBean.startDate}"> </h:inputText>
						<a href="#" onclick="cal1.select('TSCreatForm:startDate_txt','startDate_cal','MM/dd/yyyy'); return false;" id="startDate_cal" name="startDate_cal"
									title="cal1.select(document.forms[0].TSCreatForm:startDate_txt,'startDate_cal','MM/dd/yyyy'); return false;">
							<h:commandButton image="../../images/cal.gif" style="cursor: hand" alt="Cal - mm/dd/yyyy" />
						</a>
					</td>
				</tr>
				<tr>
					<td valign="top" width="177">
						<h:outputText styleClass="#{ (testSuiteBackingBean.endDateEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="End Date*" />
					</td>
					<td nowrap colspan="2" valign="top">
						<h:inputText styleClass="formInputField" size="12" id="endDate_txt" maxlength="10" value="#{testSuiteBackingBean.endDate}"> </h:inputText>
						<a href="#" onclick="cal1.select('TSCreatForm:endDate_txt', 'endDate_cal', 'MM/dd/yyyy'); return false;" id="endDate_cal" name="endDate_cal"
									title="cal1.select(document.forms[0].TSCreatForm:endDate_txt,'endDate_cal','MM/dd/yyyy'); return false;">
							<h:commandButton image="../../images/cal.gif" style="cursor: hand" alt="Cal - mm/dd/yyyy" />
						</a>
					</td>
				</tr>
				<tr>
					<td height="37" width="185">
						<h:commandButton value="Create" id="create" styleClass="wpdButton"
														rendered="#{testSuiteBackingBean.isCopy}" 
														onclick="document.getElementById('TSCreatForm:createTestSuite').click(); return false;" ></h:commandButton>
						<h:commandLink style="display:none; visibility: hidden;" id="createTestSuite" action="#{testSuiteBackingBean.createTestSuite}">
							<h:outputText/>
						</h:commandLink>
						<h:commandButton value="Save" id="update" styleClass="wpdButton"
														rendered="#{!testSuiteBackingBean.isCopy}" 
														onclick="document.getElementById('TSCreatForm:updateTestSuite').click(); return false;" ></h:commandButton>
						<h:commandLink style="display:none; visibility: hidden;" id="updateTestSuite" action="#{testSuiteBackingBean.updateTestSuite}">
							<h:outputText/>
						</h:commandLink>
					</td>
				</tr>			
			</table>
			</td>
		</tr>
	</table>
				<!-- hidden variables -->
				<h:inputHidden id="testSuiteId" value="#{testSuiteBackingBean.testSuiteId}"></h:inputHidden>
				<h:inputHidden id="isCopy" value="#{testSuiteBackingBean.isCopy}"></h:inputHidden>
	</h:form>
	<div id="selectDiv" style="width:400px; padding:5px;"></div>
	<table width="100%" border="0" cellpadding="5" cellspacing="0">
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
</body>
</f:view>
<script language="JavaScript">
	if(document.getElementById("TSCreatForm:create") == null)
		document.getElementById("TSCreatForm:testSuiteName").readOnly=true;

	var validationMessage = "Description has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by WPD application. Please correct and try again.";

	function validateTestSuiteDesc(){
		if(validateChars('TSCreatForm:testSuiteDesc',validationMessage))
			return true;
		else{
			document.getElementById('TSCreatForm:testSuiteDesc').focus();
			return false;
		}
	}
</script>
</html>

