<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"		title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" 	title="Style">

<TITLE>WellPoint Product Database: Denial and Exclusion</TITLE>
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
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('denialAndExclusionForm:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
	<!--  WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
	<h:inputHidden id="pageInit" 	binding="#{migrationContractRulesBackingBean.loadProductRules}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_migration.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="denialAndExclusionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> 
							<jsp:include page ="../migration/migrationWizardTree.jsp">  </jsp:include>	
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{migrationContractRulesBackingBean.validationMessages}"></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
<%--
							<tr>
								<td width="25%" id="tab1">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Step 6" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="75%" >
								</td>
							</tr>
--%>		

										<tr>
											<td width="100%"><b>Step6 : Rules</b> </td>
										</tr>
										<tr>
											 <td>To attach rule(s) to contract.<br>The migration wizard will check if the exclusions on the selected contract are available as rules on the selected ETAB product selected.
And user can exclude a service class range or limit class or specialty code or service codes on a contract.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

						</table>
						<!-- End of Tab table -->
<!--
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
-->
						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="2" class="smallfont" border="0">
							<TBODY>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<TD width="150" align="left">&nbsp;<h:outputText 
										id="ruleType_tx"	value="Rule Type *"
										styleClass="#{migrationContractRulesBackingBean.requiredRuleType ? 'mandatoryError': 'mandatoryNormal'}" />
									</TD>
									<TD width="180" align="left">
									<h:selectOneMenu id="ruleType_txt" styleClass="formInputField" 
										value="#{migrationContractRulesBackingBean.ruleType}" 
										onchange="resetRuleID();"	>
										<f:selectItems id="contractTypeList" value="#{migrationContractRulesBackingBean.ruleTypeListForCombo}" />
									</h:selectOneMenu></TD>	
								</TR>		
								<tr>
									<td width="150" align="left">&nbsp;<h:outputText
										styleClass="#{migrationContractRulesBackingBean.requiredRuleID ? 'mandatoryError': 'mandatoryNormal'}"
										id="RuleStar" value="Rule ID *" /></td>
									<td width="180" align="left">
											<DIV id="RuleDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtRule" value="#{migrationContractRulesBackingBean.ruleID}"/></td>
									<td align="left"><h:commandButton alt="Select" id="RuleButton"
												image="../../images/select.gif" style="cursor: hand" 
												onclick="getRuleID(); return false;"></h:commandButton></TD>
								</tr>

								<TR>
									<td height="2"></td>
								</TR>
								<TR>
									<td width="150" align="left">&nbsp;<h:commandButton
										styleClass="wpdButton" type="submit" value="Add"
										id="saveButton"
										action="#{migrationContractRulesBackingBean.addAndStoreRule}"
										></h:commandButton></td>
									<td width="150" align="left">&nbsp;</td>
									<td align="left" style="cursor:hand">&nbsp;</td>
								</TR>
								<TR>
									<td>&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>

									<TABLE width="100%" cellspacing="0" cellpadding="0">
										<TR>
											<TD>
<!-- 
											<DIV id="resultHeaderDiv">
 -->	
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD><b> <h:outputText value="Associated Rule Id"/></b></TD>
												</TR>
											</TABLE>

		<table style="margin-top: 6px;">
					<tr>
						<td align="left" >
							<h:commandLink action="#{migrationContractRulesBackingBean.loadExclusionRule}" id="linkToExclusions" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{migrationContractRulesBackingBean.exclusionRuleSelected ? 'sectionheading': ''}">
							<h:outputText styleClass="#{migrationContractRulesBackingBean.exclusionRuleSelected ? 'sectionheading': ''}" value="Exclusion Rule"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{migrationContractRulesBackingBean.loadUMRule}" id="linkToUMRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{migrationContractRulesBackingBean.umRuleSelected ? 'sectionheading': ''}">
							<h:outputText styleClass="#{migrationContractRulesBackingBean.umRuleSelected ? 'sectionheading': ''}" value="UM Rule"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{migrationContractRulesBackingBean.loadDenialRule}" id="linkToDenialRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{migrationContractRulesBackingBean.denialRuleSelected ? 'sectionheading': ''}">
							<h:outputText styleClass="#{migrationContractRulesBackingBean.denialRuleSelected ? 'sectionheading': ''}" value="Denial Rule"/></h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{migrationContractRulesBackingBean.loadPNRRule}" id="linkToPNRRule" onmousedown="javascript:navigatePageAction(this.id);"
							styleClass="#{migrationContractRulesBackingBean.pnrRuleSelected ? 'sectionheading': ''}">
							<h:outputText styleClass="#{migrationContractRulesBackingBean.pnrRuleSelected ? 'sectionheading': ''}" value="PNR Rule"/></h:commandLink>  
						</td>
					</tr>
		</table>									
<BR/>
						<div id="resultInfo">
						<div id="resultInfoExclusion" class="dataTableColumnHeader">
						<CENTER>No associated Exclusion Rule.	</CENTER></div>
						<div id="resultInfoDenial" 	class="dataTableColumnHeader">
						<CENTER>No associated Denial Rule.	</CENTER></div>
						<div id="resultInfoUM" 		class="dataTableColumnHeader">
						<CENTER>No associated UM Rule.	</CENTER></div>
						<div id="resultInfoPNR" 	class="dataTableColumnHeader">
						<CENTER>No associated PNR Rule.	</CENTER></div>
						</div>
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="center"><h:outputText value="EWPD Rule Id"/>														</TD>
														<TD align="center"><h:outputText value="Rule Id"/>														</TD>
														<TD align="center"><h:outputText value="Description"/>														</TD>
														<TD align="center"><h:outputText value="PVA"/>														</TD>
														<TD align="center"><h:outputText value="Group Indicator"/>														</TD>
														<TD align="center"><h:outputText value="Value"/>														</TD>
														<TD>&nbsp;</TD>
														</TR>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>
											<td>
												<div id="resultsTableDiv"	style="height:106px; overflow: auto;">
												<h:panelGrid
												id="panelTable"
												binding="#{migrationContractRulesBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow"/>
												</div>
											</td>
										</tr>
									</TABLE>



</BR><div>

&nbsp;<h:commandButton	styleClass="wpdButton" type="submit" value="Back" id="backButton"
						onclick="clickBack();return false;" />
	<h:commandLink id="ruleLink"
						style="display:none; visibility: hidden;"
							action="#{migrationContractRulesBackingBean.back}"><f:verbatim /></h:commandLink>
&nbsp;<h:commandButton	styleClass="wpdButton" type="submit" value="Next" id="nextButton"
						onclick="gotoNext();return false;" />
<h:commandLink id="nextLink"
						style="display:none; visibility: hidden;"
							action="#{migrationContractRulesBackingBean.next}"><f:verbatim /></h:commandLink>

&nbsp;<h:commandButton	styleClass="wpdButton" type="submit" value="Cancel" id="cancelButton"
						onclick="deleteContract();return false;" />
&nbsp;<h:commandButton	styleClass="wpdButton" type="submit" value="Save" id="updateButton" onmousedown="javascript:savePageAction(this.id);"
						action="#{migrationContractRulesBackingBean.updateRulesComments}" />
</div>

</BR>
&nbsp;<h:commandButton	styleClass="wpdButton" type="submit" value="Done" id="doneButton" onclick="gotoDone();return false;"
						/>
<h:commandLink id="doneLink"
						style="display:none; visibility: hidden;"
							action="#{migrationContractRulesBackingBean.done}" ><f:verbatim /></h:commandLink>

						<!--	End of Page data	-->
<!--</fieldset>-->

						
						</TD>
			<h:commandLink id="cancelMigrationLink"
				style="display:none; visibility: hidden;"
				action="#{migrationGeneralInfoBackingBean.cancelMigration}">
				<f:verbatim />
			</h:commandLink>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				<h:inputHidden id="renderFlagExclusion"	value="#{migrationContractRulesBackingBean.exclusionRuleSelected}"/>
				<h:inputHidden id="renderFlagDenial"	value="#{migrationContractRulesBackingBean.denialRuleSelected}"/>
				<h:inputHidden id="renderFlagUM"		value="#{migrationContractRulesBackingBean.umRuleSelected}"/>
				<h:inputHidden id="renderFlagPNR"		value="#{migrationContractRulesBackingBean.pnrRuleSelected}"/>
				<h:inputHidden id="renderFlag"			value="#{migrationContractRulesBackingBean.renderFlag}"/>
				<h:inputHidden id="hiddenProductType"	value="#{migrationContractRulesBackingBean.productType}"/>
				<h:inputHidden id="rowId"				value="#{migrationContractRulesBackingBean.productRuleSysID}"/>
				<h:inputHidden id="ewpdGenID" 			value="#{migrationContractRulesBackingBean.ewpdGenRuleID}" />
				<h:inputHidden id="duplicateData" value="#{migrationContractRulesBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{migrationContractRulesBackingBean.deleteRule}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productAdminAssociation" /></form>
<script>
IGNORED_FIELD1= 'denialAndExclusionForm:duplicateData' ;
function gotoNext(){
	if('' != document.getElementById('denialAndExclusionForm:txtRule').value){
		var retValue = confirm("Are you sure you want to navigate away from this page? \nAll unsaved changes will be lost.  Click OK to continue, or Cancel to stay on the current page.");
			if (retValue){
				document.getElementById('denialAndExclusionForm:nextLink').click();
			}
	}else{
		document.getElementById('denialAndExclusionForm:nextLink').click();
	}
}

function gotoDone(){
	if('' != document.getElementById('denialAndExclusionForm:txtRule').value){
		var retValue = confirm("Are you sure you want to navigate away from this page? \nAll unsaved changes will be lost.  Click OK to continue, or Cancel to stay on the current page.");
			if (retValue){
				document.getElementById('denialAndExclusionForm:doneLink').click();
			}
	}else{
		document.getElementById('denialAndExclusionForm:doneLink').click();
	}
}

function clickBack(){
	navigatePageActionSubmit('denialAndExclusionForm:ruleLink');
}

function deleteContract(){
		var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
		if(window.confirm(message)){
			submitLink('denialAndExclusionForm:cancelMigrationLink');
		}else{
				return false;
		}
}

//	copyHiddenToDiv_ewpd('denialAndExclusionForm:txtProviderArrangement','ProviderArrangementDiv',2,1); 
	copyHiddenToDiv_ewpd('denialAndExclusionForm:txtRule','RuleDiv',2,2);

	function getRuleID(){


	if(document.getElementById('denialAndExclusionForm:ruleType_txt').value ==''){
		alert("Please select Rule Type before selecting Rule ID");
		return false;
	}

		var url = '../popups/migrationRuleIDPopup.jsp';
		url = url 	+getUrl()+ '?ruleType='+ escape(document.getElementById('denialAndExclusionForm:ruleType_txt').value)
					+ '&temp='+Math.random();	
		ewpdModalWindow_ewpd(url,'RuleDiv','denialAndExclusionForm:txtRule',2,2);
	}
	function resetRuleID()
	{
		document.getElementById('denialAndExclusionForm:txtRule').value ='';
		copyHiddenToDiv_ewpd('denialAndExclusionForm:txtRule','RuleDiv',2,2);
	}

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		if(document.getElementById('denialAndExclusionForm:renderFlag').value=='true'){
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('denialAndExclusionForm:panelTable').offsetHeight <= 106) {
				document.getElementById('denialAndExclusionForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('denialAndExclusionForm:panelTable','14%:11%:45%:6%:10%:8%:4%');
				setColumnWidth('resultHeaderTable','14%:11%:45%:6%:10%:8%:4%');
			}else{
				document.getElementById('denialAndExclusionForm:panelTable').height = 106;
				document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
				document.getElementById('denialAndExclusionForm:panelTable').width = (relTblWidth-17)+"px";
				setColumnWidth('denialAndExclusionForm:panelTable','14%:12%:39%:12%:10%:7%:4%');
				setColumnWidth('resultHeaderTable','14%:12%:39%:12%:10%:7%:4%');
			}
				document.getElementById('resultInfo').style.visibility = 'hidden';				
				document.getElementById('resultInfo').style.height = "0px";				
				document.getElementById('resultInfo').style.position = 'absolute';					
		}else{
			document.getElementById('denialAndExclusionForm:updateButton').style.visibility = 'hidden';	
			document.getElementById('resultsTableDiv').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.height = "0px";
			document.getElementById(divId).style.visibility = 'hidden';
			if(document.getElementById('denialAndExclusionForm:renderFlagExclusion').value=='false')
			{	
				document.getElementById('resultInfoExclusion').style.visibility = 'hidden';		
				document.getElementById('resultInfoExclusion').style.height = "0px";			
				document.getElementById('resultInfoExclusion').position = 'absolute';		
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagDenial').value=='false')
			{	
				document.getElementById('resultInfoDenial').style.visibility = 'hidden';		
				document.getElementById('resultInfoDenial').style.height = "0px";			
				document.getElementById('resultInfoDenial').position = 'absolute';		
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagUM').value=='false')
			{	
				document.getElementById('resultInfoUM').style.visibility = 'hidden';		
				document.getElementById('resultInfoUM').style.height = "0px";			
				document.getElementById('resultInfoUM').position = 'absolute';		
			}			
			if(document.getElementById('denialAndExclusionForm:renderFlagPNR').value=='false')
			{	
				document.getElementById('resultInfoPNR').style.visibility = 'hidden';		
				document.getElementById('resultInfoPNR').style.height = "0px";			
				document.getElementById('resultInfoPNR').position = 'absolute';		
			}			
		}
	}	

function confirmDeletion(key1, key2){				
		var message = "Are you sure you want to delete?"		
		if(window.confirm(message)){
		document.getElementById('denialAndExclusionForm:rowId').value = document.getElementById('denialAndExclusionForm:'+key1).value;
		document.getElementById('denialAndExclusionForm:ewpdGenID').value = document.getElementById('denialAndExclusionForm:'+key2).value;
		document.getElementById('denialAndExclusionForm:deleteLink').click();
		}
		else{			
				return false;
		}
	}

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
 	if(document.getElementById('denialAndExclusionForm:duplicateData').value == '')
        document.getElementById('denialAndExclusionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
        document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('denialAndExclusionForm:duplicateData').value; 

</script>
</HTML>




