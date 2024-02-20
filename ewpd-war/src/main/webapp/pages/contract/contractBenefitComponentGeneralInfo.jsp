<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractBenefitComponentGeneralInfo.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Contract Benefit Component General Info</TITLE>
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
        <tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="contractBenefitComponentForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="contractTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
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
											value="General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractComponentGeneralInfoBackingBean.loadBenefits}">
											<h:outputText value="Benefits" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="tabForStandard">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractBenefitComponentNotesBackingBean.loadNotes}">
											<h:outputText value="Notes" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">


						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
								<TR>
									<TD colspan="5">
									<FIELDSET
										style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
									<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE border="0" cellspacing="0" cellpadding="3">
										<tr>


											<TD width="130"><h:outputText value="Line of Business" /></TD>
											<TD width="194">

											<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="lobHidden"
												value="#{contractComponentGeneralInfoBackingBean.lineOfBusiness}">
											</h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Entity" /></TD>
											<TD width="194">

											<DIV id="entityDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="entityHidden"
												value="#{contractComponentGeneralInfoBackingBean.businessEntity}">
											</h:inputHidden></TD>
										</TR>
										<TR>
											<TD width="130"><h:outputText value="Business Group" /></TD>
											<TD width="194">

											<DIV id="groupDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="groupHidden"
												value="#{contractComponentGeneralInfoBackingBean.businessGroup}">
											</h:inputHidden></TD>

										</TR>
<!-- ----------------------------------------------------------------------------------------------------------------- -->
										<TR>
											<TD width="130"><h:outputText id="MarketBusinessUnit" 
												value="Market Business Unit" styleClass="mandatoryNormal"/></TD>
											<TD width="194">

											<DIV id="marketBusinessUnit" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="marketBusinessUnitHidden"
												value="#{contractComponentGeneralInfoBackingBean.marketBusinessUnit}">
											</h:inputHidden></TD>

										</TR>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Name" /></TD>
									<TD width="239"><h:outputText id="name"
										value="#{contractComponentGeneralInfoBackingBean.name}" /> <h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.name}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Component Type" /></TD>
									<TD width="239"><h:outputText
										value="#{contractComponentGeneralInfoBackingBean.componentType}"
										id="compType" /> <h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.componentType}"></h:inputHidden>
									</TD>
								</TR>
							


								<TR>
									<TD width="3"></TD>
									<TD width="135" valign="top"><h:outputText value="Description" /></TD>
									<TD width="239"><h:inputTextarea
										styleClass="formTxtAreaReadOnly" id="txtDescription"
										value="#{contractComponentGeneralInfoBackingBean.description}"
										tabindex="5" readonly="true"></h:inputTextarea> <h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.description}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Benefit Rule ID" /></TD>
									<TD width="239"><TABLE><TR>
										<TD>	<h:outputText value="#{contractComponentGeneralInfoBackingBean.ruleIdForView}"
												id="ruleId" /> 
										</TD>
										<TD>		
												<h:inputHidden id="txtRuleType"
												value="#{contractComponentGeneralInfoBackingBean.ruleIdForView}"></h:inputHidden>
												<h:inputHidden id="txtStrRuleType" value="#{contractComponentGeneralInfoBackingBean.ruleType}"></h:inputHidden>
												<SCRIPT>copyHiddenToInputText1('contractBenefitComponentForm:txtRuleType','contractBenefitComponentForm:ruleId',1); </SCRIPT>	
												<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												rendered = "#{contractComponentGeneralInfoBackingBean.ruleIdForView != null}"
												onclick="viewAction();return false;" />
										</TD></TR></TABLE>
									</TD>
								</TR>
                                <TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Effective Date" /></TD>
									<TD width="239"><h:outputText
										value="#{contractComponentGeneralInfoBackingBean.effectiveDate}"
										id="effDate" /> <h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.effectiveDate}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Expiry Date" /></TD>
									<TD width="239"><h:outputText id="expiryDate"
										value="#{contractComponentGeneralInfoBackingBean.expiryDate}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.expiryDate}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="versionId"
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created By" /></TD>
									<TD width="239"><h:outputText id="createdUser"
										value="#{contractComponentGeneralInfoBackingBean.createdBy}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.createdBy}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Created Date" /></TD>
									<TD width="239"><h:outputText id="creationDate"
										value="#{contractComponentGeneralInfoBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated By" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedUser"
										value="#{contractComponentGeneralInfoBackingBean.updatedBy}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Last Updated Date" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedDate"
										value="#{contractComponentGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
								</TR>
							</TBODY>
						</TABLE>







						<!--	Start of Table for actual Data	--> <!--	End of Page data	-->
						</fieldset>
						</TD>
					</TR>
				</table>


				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>

	// Space for page realated scripts
	copyHiddenToDiv_ewpd('contractBenefitComponentForm:lobHidden','lobDiv',2,2);
	copyHiddenToDiv_ewpd('contractBenefitComponentForm:entityHidden','entityDiv',2,2);
	copyHiddenToDiv_ewpd('contractBenefitComponentForm:groupHidden','groupDiv',2,2);
	copyHiddenToDiv('contractBenefitComponentForm:txtRuleType','RuleTypeDiv')
	copyHiddenToDiv_ewpd('contractBenefitComponentForm:marketBusinessUnitHidden','marketBusinessUnit',2,2);
	
i = document.getElementById("contractBenefitComponentForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	}else{
	tabForStandard.style.display='';

	}
//CARS START
//DESCRIPTION : This method calls rule view popup.
function viewAction(){
	var ruleIdStr = document.getElementById('contractBenefitComponentForm:txtRuleType').value;
	var ruleType = document.getElementById('contractBenefitComponentForm:txtStrRuleType').value;

	var ruleArray = ruleIdStr.split('-');
	var ruleId = ruleArray[0];
	
	if(ruleType=="BLZWPDAB")
	{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp='+ Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
	else if(ruleType!="BLZWPDAB") {
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&ruleType='+ruleType+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
//CARS END
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComponentGeneralInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
