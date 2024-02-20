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
<base target=_self>
<TITLE>Product Denial and Exclusion View</TITLE>
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
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
	<h:inputHidden id="pageInit"value="#{productDenialAndExclusionBackingBean.loadProductRules}"></h:inputHidden>
	<h:inputHidden id="productSysId"value="#{productDenialAndExclusionBackingBean.productSysId}"></h:inputHidden>
	<h:inputHidden id="productName"value="#{productDenialAndExclusionBackingBean.productName}"></h:inputHidden>
	<h:inputHidden id="pnrRuleSelected" value="#{productDenialAndExclusionBackingBean.pnrRuleSelected}"></h:inputHidden>
	<h:inputHidden id="exclusionRuleSelected" value="#{productDenialAndExclusionBackingBean.exclusionRuleSelected}"></h:inputHidden>
	<h:inputHidden id="denialRuleSelected" value="#{productDenialAndExclusionBackingBean.denialRuleSelected}"></h:inputHidden>
	<h:inputHidden id="umRuleSelected" value="#{productDenialAndExclusionBackingBean.umRuleSelected}"></h:inputHidden>
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="denialAndExclusionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{productDenialAndExclusionBackingBean.validationMessages}"></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="141" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<TD class="tabNormal"><h:commandLink
											action="#{productGeneralInformationBackingBean.loadGeneralInfo}">
											<h:outputText id="labelGI" value="General Information"></h:outputText>
										</h:commandLink></TD>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<TD width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productComponentAssociationBackingBean.loadComponent}">
											<h:outputText value="Component Association" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</TD>
								<td width="200" id="adminopttab">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{productAdminAssociationBackingBean.loadComponent}">
											<h:outputText value="Admin Option" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<TD width="100%" id="notestab">
								<TABLE width="141" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"
											onclick="loadNotes();return false;"><h:outputText
											style="cursor:hand;" id="generalInfoTable" value="Notes" />
										</TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>

								<td width="200">
								<table width="141" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Rules" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>

								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><b> <h:outputText value="Associated Rule Id" /></b></TD>
									</TR>
								</TABLE>

								<table style="margin-top: 6px;width: 100%;">
									<tr>
										<td align="left"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadExclusionRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="exclusionId"
											styleClass="#{productDenialAndExclusionBackingBean.exclusionRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="Exclusion Rule" styleClass="#{productDenialAndExclusionBackingBean.exclusionRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadUMRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="umId"
											styleClass="#{productDenialAndExclusionBackingBean.umRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="UM Rule" styleClass="#{productDenialAndExclusionBackingBean.umRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadDenialRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="denialId"
											styleClass="#{productDenialAndExclusionBackingBean.denialRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="Denial Rule" styleClass="#{productDenialAndExclusionBackingBean.denialRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td>&nbsp;|&nbsp;</td>
										<td align="left"><h:commandLink
											action="#{productDenialAndExclusionBackingBean.loadPNRRule}"
                                            onmousedown="javascript:navigatePageAction(this.id);" id="pnrId"
											styleClass="#{productDenialAndExclusionBackingBean.pnrRuleSelected ? 'sectionheading': ''}">
											<h:outputText value="PNR Rule" styleClass="#{productDenialAndExclusionBackingBean.pnrRuleSelected ? 'sectionheading': ''}"/>
										</h:commandLink></td>
										<td align="right" width="60%">
                                         <a href="#" onclick="loadSearchPopupWithName('../popups/extractRulespopup.jsp','extractrule','EXTRACT ALL RULES',
                                         'Extract Rules Popup','ExtractRuleDiv','ExtractRuleDiv','product');return false;">
										&nbsp;<h:outputText value="Extract Rules" styleClass="variableLink" /></a>
                                         </td>
									</tr>
								</table>
								<BR />
								<div id="InformationDiv"></div>
								<div id="resultInfo">
								<div id="resultInfoExclusion" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoDenial" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoUM" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								<div id="resultInfoPNR" class="dataTableColumnHeader">
								<CENTER></CENTER>
								</div>
								</div>
								<DIV id="noResultDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000; 
						background-color:#FFFFFF;"><br><br>No Rules Available</DIV>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="center"><h:outputText value="EWPD Rule Id"/>														</TD>
											<TD align="center"><h:outputText value="Rule Id"/>														</TD>
											<TD align="center"><h:outputText value="Rule Type"/>														</TD>
											<TD align="center"><h:outputText value="Description"/>														</TD>
											<TD align="center"><h:outputText value="PVA"/>														</TD>
											<TD align="center"><h:outputText value="Group Indicator"/>														</TD>
											<TD align="center"><h:outputText value="Value"/>
											<TD align="center"></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>

							<tr>
								<td>
								<DIV id="searchResultdataTableDiv"
									style="height:216px;position:relative;z-index:1;font-size:10px;overflow:auto;"> 
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="resultsTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{productDenialAndExclusionBackingBean.ruleList != null}"
									value="#{productDenialAndExclusionBackingBean.ruleList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
									<h:column>
										<h:inputHidden id="ruleKey1"	value="#{singleValue.productRuleSysID}" />
										<h:inputHidden id ="RuleIdHidden" value="#{singleValue.ruleID}"></h:inputHidden>  
										<h:outputText id="genRuleID" value="#{singleValue.genRuleID}" />
									</h:column>
									<h:column>
										<h:outputText id="ruleID"	value="#{singleValue.ruleID}" />
									</h:column>
									<h:column>
										<h:outputText id="ruleType"	value="#{singleValue.ruleType}" />
									</h:column>
									<h:column>
										<h:outputText id="ruleDescription1"	value="#{singleValue.ruleDescription}" />
									</h:column>
									<h:column>
										<h:outputText id="pva"	value="#{singleValue.providerArrangement}" />
									</h:column>
									<h:column>
										<h:outputText id="groupIndicator" value="#{singleValue.flag}" />
									</h:column>
									<h:column>
										<h:outputText id="ruleComment" value="#{singleValue.ruleComment}" />
									</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="View" id="viewButton"
															image="../../images/view.gif"
															onclick="viewAction();return false;">
										</h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
							
								</h:column>
								</h:dataTable></DIV>
								</td>
							</tr>
						</TABLE>
						<BR><BR>



						<!--	End of Page data	--></fieldset>
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
												value="#{productDenialAndExclusionBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productDenialAndExclusionBackingBean.status}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
												<TD>:</TD>
												<td><h:outputText
												value="#{productDenialAndExclusionBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</FIELDSET>

						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenProductKey"
					value="#{productGeneralInformationBackingBean.productKey}"></h:inputHidden>
				<h:inputHidden id="hiddenProductKeyValue"
					value="#{productNoteAssociationBackingBean.productKey}"></h:inputHidden>
				<h:commandLink id="notesLink"
					action="#{productNoteAssociationBackingBean.loadNotesForView}">
				</h:commandLink>
				<%-- 
				<h:inputHidden id="rowId"
					value="#{productDenialAndExclusionBackingBean.rowId}"></h:inputHidden>
--%>
				<h:inputHidden id="rowId"
					value="#{productDenialAndExclusionBackingBean.productRuleSysID}"></h:inputHidden>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productDenialAndExclusionBackingBean.deleteRule}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="hiddenProductType"
					value="#{productDenialAndExclusionBackingBean.productType}"></h:inputHidden>
				<h:inputHidden id="exclusionRuleHidden"
					value="#{productDenialAndExclusionBackingBean.exclusionRuleSelected}"></h:inputHidden>
				<h:inputHidden id="denialRuleHidden"
					value="#{productDenialAndExclusionBackingBean.denialRuleSelected}"></h:inputHidden>
				<h:inputHidden id="umRuleHidden"
					value="#{productDenialAndExclusionBackingBean.umRuleSelected}"></h:inputHidden>
				<h:inputHidden id="pnrRuleHidden"
					value="#{productDenialAndExclusionBackingBean.pnrRuleSelected}"></h:inputHidden>
				<h:inputHidden id="hiddenViewRuleListRender"
					value="#{productDenialAndExclusionBackingBean.viewRuleListRender}"></h:inputHidden>
				<h:inputHidden id ="RuleId"></h:inputHidden>
		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	<DIV id="ExtractRuleDiv"></DIV>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productDenialExclusion" /></form>
<script><!--
hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		var rowCount = 0;
		if(null !=document.getElementById('denialAndExclusionForm:resultsTable')){
			rowCount = document.getElementById('denialAndExclusionForm:resultsTable').rows.length;
		}
		if(rowCount == 0){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById("noResultDiv").style.visibility = 'visible';

			if(document.getElementById('denialAndExclusionForm:exclusionRuleHidden').value == 'true')
				document.getElementById("noResultDiv").innerHTML = "No associated Exclusion Rule.";

			else if(document.getElementById('denialAndExclusionForm:denialRuleHidden').value == 'true')
				document.getElementById("noResultDiv").innerHTML = "No associated Denial Rule.";

			else if(document.getElementById('denialAndExclusionForm:umRuleHidden').value == 'true')
				document.getElementById("noResultDiv").innerHTML = "No associated UM Rule.";

			else 
				document.getElementById("noResultDiv").innerHTML = "No associated PNR Rule.";
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById("noResultDiv").style.visibility = 'hidden';
		}
	}	
	 if(null!= document.getElementById('denialAndExclusionForm:resultsTable')){
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			setColumnWidth('denialAndExclusionForm:resultsTable','14%:11%:10%:34%:8%:10%:6%');
			if(document.getElementById('denialAndExclusionForm:resultsTable').offsetHeight <= 216) {
				document.getElementById('denialAndExclusionForm:resultsTable').width = relTblWidth+"px";
				setColumnWidth('denialAndExclusionForm:resultsTable','14%:11%:10%:34%:8%:10%:6%');
				setColumnWidth('resultHeaderTable','14%:11%:10%:34%:8%:10%:6%');
			}else{
				document.getElementById('denialAndExclusionForm:resultsTable').width = (relTblWidth-18)+"px";
				document.getElementById('resultHeaderTable').width = (relTblWidth-18)+"px";
				setColumnWidth('denialAndExclusionForm:resultsTable','14%:11%:10%:34%:8%:10%:6%');
				setColumnWidth('resultHeaderTable','14%:11%:10%:34%:8%:10%:6%');
			}
		}


function loadNotes(){
	copyToHidden('denialAndExclusionForm:hiddenProductKey','denialAndExclusionForm:hiddenProductKeyValue');
	submitLink('denialAndExclusionForm:notesLink');
}

// Script for Admin Option/Notes tab hide for mandate
var j;
j = document.getElementById("denialAndExclusionForm:hiddenProductType").value;
if(j== "MANDATE")
{
	adminopttab.style.display='none';
	notestab.style.display='none';	
}else{
	adminopttab.style.display='';
	notestab.style.display='';
}

function viewAction(){
	getFromDataTableToHidden('denialAndExclusionForm:resultsTable','RuleIdHidden','denialAndExclusionForm:RuleId');
	var ruleId = document.getElementById('denialAndExclusionForm:RuleId').value;
	var pnrRuleSelected = document.getElementById('pnrRuleSelected').value;
	var exclusionRuleSelected = document.getElementById('exclusionRuleSelected').value;
	var denialRuleSelected = document.getElementById('denialRuleSelected').value;
	var umRuleSelected = document.getElementById('umRuleSelected').value;
	var checkMode = 'view';
	var url = '../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random()
				+'&pnrRuleSelected='+pnrRuleSelected+'&exclusionRuleSelected='+exclusionRuleSelected
				+'&denialRuleSelected='+denialRuleSelected+'&umRuleSelected='+umRuleSelected+'&checkMode='+checkMode;
				
	window.showModalDialog(url,'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}
// ICD 10 Changes Starts Here
function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp,pageFrom){		
	var productId = document.getElementById('productSysId').value;
	var productName = document.getElementById('productName').value;
	var url = popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random()+'&pageFrom='+pageFrom+'&entityId='+productId+'&entityName='+productName;;
    ewpdModalWindow_ewpd(url,displayDiv,outComp,2,2);
}
--></script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>




