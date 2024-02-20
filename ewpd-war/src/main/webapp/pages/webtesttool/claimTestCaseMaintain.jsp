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
.testCaseNameColumn {
      width: 20%;
}

.commandButtonColumn {
      width: 29%;
}
</style>

<TITLE>Test Case Search</TITLE>
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
		  <div><w:message></w:message></div>
          <div id="accordionTest" style="margin:5px;">
            <div id="searchPanel">
              <div id="searchPanelHeader" class="tabTitleBar" style="position:relative; cursor:hand;" ><b>Locate Criteria</b></div>
              <div id="searchPanelContent" class="tabContentBox" style="position:relative;"> <br />
					
                    <table border="0" cellpadding="3" cellspacing="0">
						<tr><td colspan="3"></td></tr>
						<tr>
							<td valign="top"><span class="mandatoryNormal" id="descriptionStar"> Test Case </span></td>
							<td colspan="2" valign="top">
								<h:inputText  id="testCaseName" styleClass="formInputField" value="#{testCaseBackingBean.testCaseName}"/>
							</td>
						</tr>
						<tr>
							<td valign="top"><span class="mandatoryNormal" id="descriptionStar"> Benefit Component</span></td>
							<td colspan="2" valign="top">
								<h:inputText  id="benefitComponent" styleClass="formInputField" value="#{testCaseBackingBean.benefitComponent}"/>
							</td>
						</tr>
						<tr>
							<td valign="top"><span class="mandatoryNormal" id="descriptionStar"> Benefit  </span></td>
							<td colspan="2" valign="top">
								<h:inputText  id="benefit" styleClass="formInputField" value="#{testCaseBackingBean.benefit}"/>
							</td>
						</tr>
					</table>
					<br />
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
                      <tr>
                        <td>
							<h:commandButton styleClass="wpdbutton" id="locateButton" value="Locate" style="cursor: hand" action="#{testCaseBackingBean.locateTestCase}" tabindex="9"></h:commandButton>
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
						  	<table  bgcolor="#cccccc" class="smallfont" id="resultsTable" width="100%" cellpadding="3" cellspacing="1" border="0">
	                          <tr bgcolor="#ffffff">
								<td width="20%"><span id="stateCodeStar"><strong>Test Case Name</strong></span></td>		
								<td width="29%">&nbsp;</td>
	                          </tr>
	                    	</table>
						  </div>
						  <div id="searchResultdataTableDiv" style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> 
						    <h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="testCase" cellpadding="3"
										cellspacing="1" bgcolor="#cccccc"
										rendered="#{testCaseBackingBean.testCaseList != null}"
										value="#{testCaseBackingBean.testCaseList}"
										width="100%" columnClasses="testCaseNameColumn,commandButtonColumn">
								<h:column>
									
									<h:inputHidden id="hiddenTestCaseId" value="#{testCase.testCaseId}"></h:inputHidden>
									<h:inputHidden id="hiddenTestCaseName" value="#{testCase.testCaseName}"></h:inputHidden>
									<h:outputText value="#{testCase.testCaseName}"></h:outputText>
								</h:column>
								<h:column>
									
									<h:outputFormat value="  "></h:outputFormat>
									<h:commandButton alt="Delete" id="basicDelete" value="Delete" image="../../images/delete.gif" onclick="getValuesForDelete();return false;">
									</h:commandButton>
									<h:outputFormat value="  "></h:outputFormat>
									<h:commandButton alt="Edit" id="basicEdit" value="Edit" image="../../images/edit.gif" onclick="getValuesForEdit();return false;">
									</h:commandButton>
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
		<h:inputHidden id="testCaseIdRef" value="#{testCaseBackingBean.testCaseIdRef}" />
		<h:inputHidden id="testCaseNameRef" value="#{testCaseBackingBean.testCaseNameRef}" />
		<h:commandLink id="testCaseDelete"
			style="display:none; visibility: hidden;"
			action="#{testCaseBackingBean.deleteTestCase}">
			<h:outputText/>
		</h:commandLink>
		<h:commandLink id="testCaseEdit"
			style="display:none; visibility: hidden;"
			action="#{testCaseBackingBean.editTestCase}">
			<h:outputText/>
		</h:commandLink>
		<h:commandLink id="testCaseCopy"
			style="display:none; visibility: hidden;"
			action="#{testCaseBackingBean.copyTestCase}">
			<h:outputText/>
		</h:commandLink>
</h:form>
<%@ include file="../navigation/bottom.jsp"%>
</body>
</f:view>
<script type="text/javascript">

		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'329',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});	
		if( document.getElementById('TSSearchForm:searchResultTable') != null) {
			document.getElementById('panel2Header').click();
		}



		//function to delete the Test Case
		function getValuesForDelete() {
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseId','TSSearchForm:testCaseIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseName','TSSearchForm:testCaseNameRef');
				submitLink('TSSearchForm:testCaseDelete');					
			}else{
				return false;
			}
		}
		
		function getValuesForEdit(){
			var message = "Are you sure you want to Edit?"
			if(window.confirm(message)){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseId','TSSearchForm:testCaseIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseName','TSSearchForm:testCaseNameRef');
				submitLink('TSSearchForm:testCaseEdit');					
			}else{
				return false;
			}
		}
		function getValuesForCopy(){
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseId','TSSearchForm:testCaseIdRef');
				getFromDataTableToHidden('TSSearchForm:searchResultTable','hiddenTestCaseName','TSSearchForm:testCaseNameRef');
				submitLink('TSSearchForm:testCaseCopy');							
		}	
</script>
</html>
