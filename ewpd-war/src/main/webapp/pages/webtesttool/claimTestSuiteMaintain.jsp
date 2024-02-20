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
	<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.testSuiteNameColumn {
      width: 20%;
}

.testCaseRefColumn {
      width: 50%;
}
.commandButtonColumn {
      width: 29%;
}
</style>

<TITLE>Test Suite Search</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>

<f:view>
<body onkeydown="return submitOnEnterKey('TSSearchForm:locateButton');">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
	<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
  </tr>
</table>
<h:form id="TSSearchForm">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td width="1%" valign="top"></td>

      <td colspan="2" valign="top" class="ContentArea"><div id="bodyDiv">
         <table width="400" border="0" cellpadding="0" cellspacing="0" id="TabTable" style="position:relative; top:25px; left:5px; z-index:120;">
	      </table>
			<div style="margin-bottom: 17px;"><w:message></w:message></div>
          <div id="accordionTest" style="margin:5px;">
            <div id="searchPanel">
              <div id="searchPanelHeader" class="tabTitleBar" style="position:relative; cursor:hand;" ><b>Locate Criteria</b></div>
              <div id="searchPanelContent" class="tabContentBox" style="position:relative;"> <br />
                    <table border="0" cellpadding="3" cellspacing="0">
						<tr>
							<td valign="top">
								<h:outputText styleClass="#{ (testSuiteBackingBean.testSuiteNameEntry) ? 'mandatoryError' : 'mandatoryNormal'}" value="Test Suite*" />
							</td>
							<td colspan="2" valign="top">
								<h:inputText  id="testSuitName" styleClass="formInputField" value="#{testSuiteBackingBean.testSuiteName}" />
							</td>
						</tr>
					</table>
					<br />
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
                      <tr>
                        <td>
							<h:commandButton styleClass="wpdbutton" id="locateButton" value="Locate" style="cursor: hand" action="#{testSuiteBackingBean.locateTestSuite}" tabindex="9"></h:commandButton>
						</td>
                      </tr>
                    </table>
              </div>
            </div>
            <div id="panel2">
              <div id="panel2Header" class="tabTitleBar" style="position:relative; cursor:hand;"> Locate Results </div>
              <div id="panel2Content" class="tabContentBox" style="position:relative;font-size:10px;"> <br/>
                  <table cellpadding="0" cellspacing="0" width="100%" border="0">
                    <tr>
                      <td>
						<div>
						  	<table bgcolor="#cccccc" class="smallfont" id="resultsTable" width="100%" cellpadding="3" cellspacing="1" border="0">
	                          <tr bgcolor="#ffffff">
								<td width="20%"><span id="stateCodeStar"><strong>Test Suite Name</strong></span></td>		
								<td width="50%"><span id="stateCodeStar"><strong> Test Case Name</strong></span></td>
								<td width="29%">&nbsp;</td>
	                          </tr>
	                    	</table>
						  </div>
						  <div id="searchResultdataTableDiv" style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> 
						    <h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="testSuite" cellpadding="3"
										cellspacing="1" bgcolor="#cccccc"
										rendered="#{testSuiteBackingBean.testSuiteList != null}"
										value="#{testSuiteBackingBean.testSuiteList}"
										width="100%" columnClasses="testSuiteNameColumn,testCaseRefColumn,commandButtonColumn">
								<h:column>
									
									<h:inputHidden id="hiddenTestSuiteId" value="#{testSuite.testSuiteId}"></h:inputHidden>
									<h:inputHidden id="hiddenTestSuiteName" value="#{testSuite.testSuiteName}"></h:inputHidden>
									<h:outputText value="#{testSuite.testSuiteName}"></h:outputText>
								</h:column>
								<h:column>
									
									<h:outputText value="#{testSuite.testCaseRefStr}"></h:outputText>
								</h:column>
								<h:column>
									
									<h:outputFormat value=" "></h:outputFormat>
									<h:commandButton alt="Delete" id="basicDelete" value="Delete" image="../../images/delete.gif" onclick="getValuesForDelete();return false;">
									</h:commandButton>
									<h:outputFormat value=" "></h:outputFormat>
									<h:commandButton alt="Edit" id="basicEdit" value="Edit" image="../../images/edit.gif" onclick="getValuesForEdit();return false;">
									</h:commandButton>
									<h:outputFormat value=" "></h:outputFormat>
									<h:commandButton alt="Run Test Suite" id="basicExecute" value="Test Suite" image="../../images/testRun.gif" onclick="popupTestResult(); return false;">
									</h:commandButton>
									<h:outputFormat value=" "></h:outputFormat>
									<h:commandButton alt="Copy" id="basicCopy" value="Copy" image="../../images/copy.gif" onclick="getValuesForCopy();return false;">
									</h:commandButton>
								</h:column>
							</h:dataTable>
						</div>
					  </td>
                    </tr>
                  </table>
              </div>
            </div>
          </div>
    </div>	</td>
  </tr>
</table>
		<!--  hidden fields -->
		<h:inputHidden id="testSuiteIdRef" value="#{testSuiteBackingBean.testSuiteIdRef}" />
		<h:inputHidden id="testSuiteNameRef" value="#{testSuiteBackingBean.testSuiteNameRef}" />
		<h:commandLink id="testSuiteDelete"
			style="display:none; visibility: hidden;"
			action="#{testSuiteBackingBean.deleteTestSuite}">
			<h:outputText/>
		</h:commandLink>
		<h:commandLink id="testSuiteEdit"
			style="display:none; visibility: hidden;"
			action="#{testSuiteBackingBean.editTestSuite}">
			<h:outputText/>
		</h:commandLink>
		<h:commandLink id="testSuiteCopy"
			style="display:none; visibility: hidden;"
			action="#{testSuiteBackingBean.copyTestSuite}">
			<h:outputText/>
		</h:commandLink>
</h:form>
<%@ include file="../navigation/bottom.jsp"%>
</body>
</f:view>
<script type="text/javascript">
		document.getElementById("TSSearchForm:testSuitName").focus(); // for on load default selection
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'329',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});	
		
		if(document.getElementById('TSSearchForm:searchResultTable') != null) {
			document.getElementById('panel2Header').click();
		}


		function deleteTestCase(){
			window.confirm("Are you sure you want to delete?");
		}
		// function to delete the Test Suite
		function getValuesForDelete() {
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteId','TSSearchForm:testSuiteIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteName','TSSearchForm:testSuiteNameRef');
				submitLink('TSSearchForm:testSuiteDelete');					
			}else{
				return false;
			}
		}
		function getValuesForEdit(){
			var message = "Are you sure you want to Edit?"
			if(window.confirm(message)){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteId','TSSearchForm:testSuiteIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteName','TSSearchForm:testSuiteNameRef');
				submitLink('TSSearchForm:testSuiteEdit');					
			}else{
				return false;
			}
		}
		function getValuesForCopy(){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteId','TSSearchForm:testSuiteIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteName','TSSearchForm:testSuiteNameRef');
				submitLink('TSSearchForm:testSuiteCopy');							
		}
		function popupTestResult(){
			getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestSuiteId','TSSearchForm:testSuiteIdRef');
			var testSuiteId = document.getElementById("TSSearchForm:testSuiteIdRef").value;
			var url = "../webtesttool/claimTestSuiteResult.jsp"+getUrl()+"?";
			url += "testSuiteId=" + testSuiteId + "&";
			url += "date=" + new Date();
			window.showModalDialog(url, window, "dialogHeight:600px;dialogWidth:1000px;resizable=yes;status=no;scroll=yes;"); 
			return false;
		}		
</script>
</html>
