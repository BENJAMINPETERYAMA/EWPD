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
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<script language="JavaScript" src="../../js/PopupWindow.js"></script>
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
<TITLE>Create Test Suite</TITLE>
</HEAD>
<f:view>
<body>
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
								<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
								<td width="186" class="tabNormal">
									<h:commandLink action="#{testSuiteBackingBean.goToEditTestSuite}"> 
										<h:outputText value="Test Suite Information"/>
									</h:commandLink>
								</td>
								<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="2" height="21" /></td>
							</tr>
						</table>
					</td>
					<td width="200">
						<table width="200" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="2" align="left"><img src="../../images/activeTabLeft.gif" alt="Tab Left Active" width="3" height="21" /></td>
								<td width="186" class="tabActive"> <h:outputText value="Test Case Information"/> </td>
								<td width="2" align="right"><img src="../../images/activeTabRight.gif" alt="Tab Right Active" width="2" height="21" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<fieldset>
				<div><w:message></w:message></div>
				<table cellpadding="0" cellspacing="0" width="90%" style="margin:27px;">
					<tr>
						<td valign="top" width="137">
							<h:outputText styleClass="#{ (testSuiteBackingBean.selectedTestCaseValEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Test Case"></h:outputText>
						</td>
						<td align="left" width="200">
							<b>
							<h:inputHidden id="testCaseSelectIds" value="#{testSuiteBackingBean.selectedTestCaseVal}"></h:inputHidden>
							<div class="formInputField" id="testCaseSelectDIV"></div>
						</td>
						<td align="left" valign="top" width="633">
							<img src="../../images/select.gif" alt="Select" width="15" height="15" style="cursor:hand;" 
									onclick="ewpdModalWindow_ewpd('../webtesttool/claimTestCasePopup.jsp'+getUrl()+'?date=' + new Date(),'testCaseSelectDIV','TSCreatForm:testCaseSelectIds',2,2);" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:left;">
							<h:commandButton value="Add Test Case" id="addTestCaseBtt" styleClass="wpdButton"
											onclick="validateAddTestCase(); return false;" >
							</h:commandButton>
							<h:commandLink style="display:none; visibility: hidden;" id="addTestCase" action="#{testSuiteBackingBean.addTestCase}">
								<h:outputText/>
							</h:commandLink>
						</td>
					</tr>
				</table>
				<div style="margin:0px 13px 0px 17px;">Associated Test Cases : </div>
				<table id="businessEntityTable" style="margin:3px 13px 17px 17px" width="50%" cellpadding="0" cellspacing="0"  bgcolor="#cccccc">
					<tr>
						<td>
							<table width="100%" cellpadding="0" cellspacing="1">
								<tr>
									<td width="13%">
										<h:selectBooleanCheckbox onclick="checkAll_ewpd(this, 'TSCreatForm:searchResultTable'); ">
										</h:selectBooleanCheckbox>
									</td>
									<td width="69%" align="left"><strong>Test Case</strong></td>
									<td width="17%"><strong><div id="popHeading"></div></strong></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
						    <h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
								styleClass="outputText" headerClass="dataTableHeader"
								id="searchResultTable" var="testCaseRef" cellpadding="1"
								cellspacing="1" bgcolor="#cccccc"
								rendered="#{testSuiteBackingBean.selectedTestCaseList != null}"
								value="#{testSuiteBackingBean.selectedTestCaseList}"
								width="100%">
								<h:column>
									<f:attribute name="width" value="13%"/>
									<h:selectBooleanCheckbox></h:selectBooleanCheckbox>
								</h:column>
								<h:column>
									<f:attribute name="width" value="69%"/>
									<h:inputHidden id="hiddenTestCaseId" value="#{testCaseRef.testCaseId}"></h:inputHidden>
									<h:outputText value="#{testCaseRef.testCaseName}"></h:outputText>
								</h:column>
								<h:column>
									<f:attribute name="width" value="17%"/>
									<h:commandButton alt="Delete" id="basicDelete" value="Delete" image="../../images/delete.gif" onclick="getValuesForDelete();return false;">
									</h:commandButton>
								</h:column>
							</h:dataTable>
						</td>
					</tr>
				</table>
				<input type="button" name="action" style="margin:7px 13px" value="&nbsp; Test &nbsp;" class="wpdbutton" onclick="popupTestResult(); return false;"></input>
			</fieldset>
				<!-- hidden variables -->
				<h:inputHidden id="testSuiteId" value="#{testSuiteBackingBean.testSuiteId}"></h:inputHidden>
				<h:inputHidden id="testCaseId" value="#{testSuiteBackingBean.testCaseIdRef}"></h:inputHidden>
				<h:inputHidden id="testSuiteName" value="#{testSuiteBackingBean.testSuiteName}"></h:inputHidden>
				<h:inputHidden id="testSuiteDesc" value="#{testSuiteBackingBean.testSuiteDesc}"></h:inputHidden>
				<h:inputHidden id="contractId" value="#{testSuiteBackingBean.contractId}"></h:inputHidden>
				<h:inputHidden id="startDate" value="#{testSuiteBackingBean.startDate}"></h:inputHidden>
				<h:inputHidden id="endDate" value="#{testSuiteBackingBean.endDate}"></h:inputHidden>
				<h:inputHidden id="isCopy" value="#{testSuiteBackingBean.isCopy}"></h:inputHidden>
				<h:commandLink id="testCaseDelete"
							   style="display:none; visibility: hidden;"
							   action="#{testSuiteBackingBean.testCaseRefDelete}">
					<h:outputText/>
				</h:commandLink>
		</td>
	</tr>
</table>
</h:form>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
	<tr>
		<td><%@ include file="../navigation/bottom.jsp"%></td>
	</tr>
</table>
</body>
</f:view>
<script type="text/javascript">
		// function to delete the Test Case
		function getValuesForDelete() {
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('TSCreatForm:searchResultTable','hiddenTestCaseId','TSCreatForm:testCaseId');
				submitLink('TSCreatForm:testCaseDelete');					
			}else{
				return false;
			}
		}
		// function to delete the Test Case
		function validateAddTestCase() {
			var testCId = document.getElementById('TSCreatForm:testCaseSelectIds').value;
			var testSId = document.getElementById('TSCreatForm:testSuiteId').value;
			if(testSId==""){
				alert("Please create 'Test Suite' before you are adding 'Test Cases' to 'Test Suite'.");
				return false;
			}else{
				document.getElementById('TSCreatForm:addTestCase').click();
				return true;
			}
		}
		function popupTestResult(){
			var testCaseIds = getCheckedTestCases('TSCreatForm:searchResultTable');
			var testSuiteId = document.getElementById("TSCreatForm:testSuiteId").value;
			var testSuiteName = document.getElementById("TSCreatForm:testSuiteName").value;
			var url = "../webtesttool/claimTestSuiteResult.jsp"+getUrl()+"?";
			url += "testSuiteId=" + testSuiteId + "&";
			url += "testCaseIds=" + testCaseIds+ "&";
			url += "date=" + new Date();
			window.showModalDialog(url, window, "dialogHeight:600px;dialogWidth:1000px;resizable=yes;status=no;scroll=yes;"); 
			return false;
		}
		function getCheckedTestCases(id)
		{
			var tableObject;	
			if (document.getElementById(id))
			{
				tableObject = document.getElementById(id);
				var selectedCodes = '';
				var cnt = 0;
				for (var i=0;i<tableObject.rows.length;i++)
				{
					var checkboxObject = tableObject.rows[i].cells[0].children[0];
					if (checkboxObject && checkboxObject.checked)
					{	
						if(cnt > 0)
						{
							selectedCodes += '~';
						}
						selectedCodes += tableObject.rows[i].cells[1].children[0].value;
						cnt++;
					}
				}	
			}
			return selectedCodes;		
		}
</script>
</html>