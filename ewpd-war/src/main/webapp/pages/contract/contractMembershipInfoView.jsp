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

<TITLE>WellPoint Product Database: Membership Information</TITLE>
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
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<h:inputHidden id="maxresultMember" value="#{contractBasicInfoBackingBean.valueToMembership}"></h:inputHidden>
				<TD><h:form styleClass="form" id="MembershipInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="273"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
							</DIV>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>

								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<TR>

										<TD width="16%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width= "100%" class="tabActive"> <h:outputText value=" General Information" /> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD>
					          			<!-- TD width="18%">
		          							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width="100%" class="tabActive"> <h:outputText value="Specific Information" /> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD-->
										<TD width="18%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"> <h:outputText value="Pricing Information" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>

										<TD width="10%"  id="tabForStandard1">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<td class="tabNormal"><h:commandLink
														action="#{ContractNotesBackingBean.load}"
														id="contractNotesID">
														<h:outputText id="labelNotes" value="Notes"></h:outputText>
													</h:commandLink></td>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>

										<TD width="14%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment"><h:outputText value="Comments" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
												</TR>
											</TABLE>
										</TD>
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
									</TR>
								</TABLE>	
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
							<table style="margin-top: 6px;">
								<tr>
									<td align="left"><h:commandLink
										action="#{contractBasicInfoBackingBean.getBasicInfo}">
										<h:outputText value="Basic Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}">
										<h:outputText value="Specific Information"></h:outputText>
									</h:commandLink></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left" class="sectionheading"><h:outputText
										value="Membership Information"></h:outputText></td>
									<td>&nbsp;|&nbsp;</td>
									<td align="left"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractAdaptedInfo}">
										<h:outputText value="Adapted Information"></h:outputText>
									</h:commandLink></td>
								</tr>
							</table>

							<BR />
							<!--	Start of Table for actual Data	-->
						

									<div id="panel2">
									<div id="resultInfo" class="dataTableColumnHeader"><br />
									<CENTER>No Membership Information is Available.</CENTER></div>

									<div id="resultHeaderDiv">

									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
										id="resultHeaderTable" border="0">
										<TBODY>

											<TR class="dataTableColumnHeader">
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Case Number & Name"></h:outputText></td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Case Effective- Cancel Date"></h:outputText></td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField" value="Case HQ State"></h:outputText>
												</td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Group Number & Name"></h:outputText></td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Group Effective- Cancel Date"></h:outputText></td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Funding Arrangement"></h:outputText></td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField" value="MBU Code & Value"></h:outputText>
												</td>
												<td align="left"><h:outputText
													styleClass="formOutputColumnField"
													value="Re-rate Code & Value"></h:outputText></td>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									<%--
<h:inputHidden id="loadPricingInfoList" value= "#{contractPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>
									<div id="panel2Content"
										style="height:181px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td align="left"><h:dataTable headerClass="tableHeader"
												id="resultsTable" border="0"
												value="#{contractBasicInfoBackingBean.membershipList}"
												rendered="#{contractBasicInfoBackingBean.membershipList != null}"
												var="eachRow" cellpadding="0" cellspacing="1"
												bgcolor="#cccccc"
												rowClasses="dataTableEvenRow,dataTableOddRow">

												<h:column>
													<h:outputText id="caseNumber"
														styleClass="formOutputColumnField"
														value="#{eachRow.caseNumber}"></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" : "></h:outputText>
													<h:outputText id="caseName"
														styleClass="formOutputColumnField"
														value="#{eachRow.caseName}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="CaseEffective"
														styleClass="formOutputColumnField"
														value="#{eachRow.caseEffectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" - "></h:outputText>
													<h:outputText id="CaseExpiry"
														styleClass="formOutputColumnField"
														value="#{eachRow.caseExpiryDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="caseHqState"
														styleClass="formOutputColumnField"
														value="#{eachRow.caseHqState}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="GroupNumber"
														styleClass="formOutputColumnField"
														value="#{eachRow.groupNumber}"></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" : "></h:outputText>
													<h:outputText id="GroupName"
														styleClass="formOutputColumnField"
														value="#{eachRow.groupName}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="GroupEffective"
														styleClass="formOutputColumnField"
														value="#{eachRow.groupEffectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" - "></h:outputText>
													<h:outputText id="GroupExpiry"
														styleClass="formOutputColumnField"
														value="#{eachRow.groupExpiryDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="fundingCode"
														styleClass="formOutputColumnField"
														value="#{eachRow.fundingArrangementCode}"></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" : "></h:outputText>
													<h:outputText id="fundingDesc"
														styleClass="formOutputColumnField"
														value="#{eachRow.fundingArrangementValue}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="mbuCode"
														styleClass="formOutputColumnField"
														value="#{eachRow.mbuCode}"></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" : "></h:outputText>
													<h:outputText id="mbuDesc"
														styleClass="formOutputColumnField"
														value="#{eachRow.mbuValue}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="rateCode"
														styleClass="formOutputColumnField"
														value="#{eachRow.rerateCode}"></h:outputText>
													<h:outputText styleClass="formOutputColumnField"
														value=" : "></h:outputText>
													<h:outputText id="rateDesc"
														styleClass="formOutputColumnField"
														value="#{eachRow.rerateValue}"></h:outputText>
												</h:column>
											</h:dataTable></td>
										</tr>
									</table>

									</div>
									</div>

							<br>
							</FIELDSET>
							<br>
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<td>:<h:outputText
												value="#{contractBasicInfoBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td>:<h:outputText
												value="#{contractBasicInfoBackingBean.status}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td>:<h:outputText
												value="#{contractBasicInfoBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
								

							</table>
							</FIELDSET>
							<br>
							<br>
						
							<br>
							<br>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

							<!--	End of Page data	--> <!-- Space for hidden fields --> <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>


				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script>
hideResultDiv();
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');

		var tableObj = document.getElementById('MembershipInfoForm:resultsTable');
		if (tableObj.rows.length > 0) {
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

	if(document.getElementById('MembershipInfoForm:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('MembershipInfoForm:resultsTable').offsetHeight <= 181) {
				document.getElementById('MembershipInfoForm:resultsTable').width = relTblWidth+"px";				
				setColumnWidth('MembershipInfoForm:resultsTable','12%:12%:12%:12%:12%:12%:12%:12%');
				setColumnWidth('resultHeaderTable','12%:12%:12%:12%:12%:12%:12%:12%');
			}else{
				document.getElementById('MembershipInfoForm:resultsTable').width = relTblWidth-17+"px";
				document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
				setColumnWidth('MembershipInfoForm:resultsTable','12%:12%:12%:12%:12%:12%:12%:12%');
				setColumnWidth('resultHeaderTable','12%:12%:12%:12%:12%:12%:12%:12%');
				
			}
			
		}

i = document.getElementById("MembershipInfoForm:hiddenProductType").value;
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
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractMembershipInfo" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>


