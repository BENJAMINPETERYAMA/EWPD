<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"		title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" 	title="Style">

<TITLE>WellPoint Product Database: Admin Option</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('productForm:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="formName">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> 
							<jsp:include page="../contract/contractTree.jsp"></jsp:include>
						</DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{contractProductAdminOptionBackingBean.validationMessages}"></w:message></TD>
								</tr>
							</TBODY>
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
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal">
										<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"> 
										<h:outputText value="Pricing Information" /> </h:commandLink> </td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="10%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{ContractNotesBackingBean.load}"
										id="linkToExclusions">
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
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive"><h:outputText value="Admin Option" /></td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
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

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>
								<TR>
									<td colspan="4">
									<TABLE width="100%" cellspacing="0" cellpadding="0">
										<TR>
											<TD><BR />
	<DIV id="noContractAdminOptions"
														style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
														background-color:#FFFFFF;">No
														Admin Options Attached.</DIV>
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD><b> <h:outputText value="Associated Admin Options"></h:outputText>
													</b></TD>
												</TR>
											</TABLE>
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="left"><h:outputText value="Admin Name "></h:outputText>	</TD>
													</TR>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>
											<td>
										
											<DIV id="searchResultdataTableDiv"
												style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
											<!-- Search Result Data Table --> 
											<h:dataTable
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{contractProductAdminOptionBackingBean.adminList != null}"
												value="#{contractProductAdminOptionBackingBean.adminList}"
												rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
												<h:column>
													<h:inputHidden id="adminId" value="#{singleValue.adminKey}"></h:inputHidden>
													<h:outputText id="adminDesc" value="#{singleValue.adminDesc}"></h:outputText>
												</h:column>
											</h:dataTable></DIV>
											</td>
										</tr>
									</TABLE>


									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</TBODY>
						</TABLE>

						<!--	End of Page data	-->
						</fieldset>

								<BR>
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr>
											<!-- Transfer Log -->
											<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
											<td  align="left" width="127">
												<table Width = 100%>
												<tr>
													<td>State</td>
													<td>:<h:outputText
														value="#{contractProductAdminOptionBackingBean.state}"
														styleClass="outputText" /></td>
												</tr>
												<tr>
													<td>Status</td>
													<td>:<h:outputText
														value="#{contractProductAdminOptionBackingBean.status}"
														styleClass="outputText" /></td>
												</tr>
												<tr>
													<td>Version</td>
													<td>:<h:outputText
														value="#{contractProductAdminOptionBackingBean.version}"
														styleClass="outputText" /></td>
												</tr>
												</table>
											</td></tr>
											
										
									</table>
								</FIELDSET>	
			
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="adminData"	value="#{contractProductAdminOptionBackingBean.adminString}"></h:inputHidden>

				<h:commandLink id="deleteLink"	style="display:none; visibility: hidden;"
					action="#{contractProductAdminOptionBackingBean.deleteAdmin}">
					<f:verbatim />
				</h:commandLink>
				
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:adminId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractAdminOptions').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('formName:searchResultTable', '40%:60%');
	 		setColumnWidth('resultHeaderTable', '40%:60%');
			document.getElementById('noContractAdminOptions').style.visibility = 'hidden';
		}
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
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractAdminOption" />
</form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>




