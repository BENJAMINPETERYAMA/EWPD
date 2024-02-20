<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>WellPoint Product Database: Pricing Information</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
<BODY >
<hx:scriptCollector id="scriptCollector1">
	<h:inputHidden id="hidden1" ></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
<h:inputHidden id="hiddenPricingLoad" value="#{contractPricingInfoBackingBean.hiddenLoadPricing}"></h:inputHidden>
			<TD>
				<h:form styleClass="form" id="pricingInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel">
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						
									<jsp:include page="contractTree.jsp"></jsp:include>	
						 		</DIV>
							</TD>

					<TD colspan="1" valign="top" class="ContentArea">
					<TABLE>
						<TR>
							<TD>
							</TD>
						</TR>
					</TABLE>
<!-- Table containing Tabs -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="TabTable">
						<tr>
							<td width="16%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab Left Active"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{contractBasicInfoBackingBean.getBasicInfo}"
										id="linkToProfileInfo">
										<h:outputText id="labelBasicInfo"
											value="General Information"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Active"
										width="2" height="21" /></td>
								</tr>
							</table>
							</td>
							<!-- >td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}"
										id="linkToAdjudicationInfo">
										<h:outputText id="labelSpecificInfo"
											value="Specific Information"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td-->
							<td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive">Pricing Information</td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="10%"  id="tabForStandard1">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{ContractNotesBackingBean.load}"
										id="contractNotesID">
										<h:outputText id="labelNotes" value="Notes"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment">
										<h:outputText id="LabelComments" value="Comments"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%"  id="tabForStandard">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}" id="linkToAdminOption">
										<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractRuleBackingBean.displayRules}" id="linkToRules">
										<h:outputText id="rules" value="Rules"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
<!-- End of Tab table -->
<!--	Start of Table for actual Data	-->		
					
					<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
<BR/>
					<div id="panel2">
						<div id="resultInfo" class="dataTableColumnHeader">
						<br/><CENTER>No Pricing Information is Available.</CENTER>
						</div>

					<div id="resultHeaderDiv">
						<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width:100%;height: 15">
							&nbsp;Associated Pricing Records
						</div>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0" id="resultHeaderTable" border="0" width="100%">
							<TBODY>

								<TR class="dataTableColumnHeader">
									<td align="left" >
												&nbsp;<h:outputText styleClass="formOutputColumnField" value="Coverage" ></h:outputText> 
									</td>
									<td  align="left" >
	              								&nbsp;<h:outputText styleClass="formOutputColumnField" value="Pricing" ></h:outputText>
									</td >
									<td align="left" >
	              								&nbsp;<h:outputText styleClass="formOutputColumnField" value="Network"></h:outputText>
									</td>
								</TR>
							</TBODY>
						</TABLE>
					</div>
<h:inputHidden id="renderHidden" value= "#{contractPricingInfoBackingBean.renderFlag}"/> 
					<div id="panel2Content" style="height:106px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
					<table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
					<tr>
					<td align="left">
							
							<h:dataTable headerClass="tableHeader" id="resultsTable" border="0"
								value="#{contractPricingInfoBackingBean.pricingInformationList}"
								rendered="#{contractPricingInfoBackingBean.renderFlag}" var="eachRow" 
								 cellpadding="0" cellspacing="1" bgcolor="#cccccc" width="650px" style="width:100%"
								rowClasses="dataTableEvenRow,dataTableOddRow">
								
								<h:column>
									<h:inputHidden id="contractID" value="#{eachRow.contractDateSegmentSysId}"></h:inputHidden>	
									<h:outputText id="coverageInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.coverage}"></h:outputText>
									<h:inputHidden id="coverageInfoID" value="#{eachRow.coverage}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="pricingInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.pricing}"></h:outputText>
									<h:inputHidden id="pricingInfoID" value="#{eachRow.pricing}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="networkInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.network}"></h:outputText>
									<h:inputHidden id="networkInfoID" value="#{eachRow.network}"></h:inputHidden>
								</h:column>
							</h:dataTable>
						</td>
					</tr>
					</table>

					</div>
					</div>
<BR/>
					</fieldset>
						
				<br/>
					<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr><td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
											<td  align="left" width="127">
												<table Width = 100%>
													<tr>
														<td><h:outputText value="State"/></td>
														<td>:<h:outputText value="#{contractPricingInfoBackingBean.state}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Status" /></td>
														<td>:<h:outputText value="#{contractPricingInfoBackingBean.status}" /></td>
													</tr>
													<tr>
														<td><h:outputText value="Version" /></td>
														<td>:<h:outputText value="#{contractPricingInfoBackingBean.version}" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- Transfer Log -->
										
									</table>
								</FIELDSET>	
<br>
</TABLE>

<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

		</h:form>
		</td>			
		</tr>
		<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
 	</TABLE>
</hx:scriptCollector></body>
</f:view>

<script language="JavaScript">
	hideResultDiv();
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');

		var tableObj = document.getElementById('pricingInfoForm:resultsTable');
		if (tableObj != null && tableObj.rows.length>0) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
	}

		if(document.getElementById('pricingInfoForm:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('pricingInfoForm:resultsTable').rows.length <= 5) {
				document.getElementById('pricingInfoForm:resultsTable').width = relTblWidth+"px";
				setColumnWidth('pricingInfoForm:resultsTable','33%:33%:34%');
				setColumnWidth('resultHeaderTable','33%:33%:34%');
			}else{
				document.getElementById('resultHeaderTable').width = (relTblWidth-19)+"px";
				setColumnWidth('pricingInfoForm:resultsTable','33%:33%:34%');
				setColumnWidth('resultHeaderTable','33%:33%:34%');
			}
		}
i = document.getElementById("pricingInfoForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
</script>
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractPricingInfo" />
</form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>

