<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
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

<TITLE>Migration Wizard-Step 1</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('migrationForm:nextButton');">
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden id="dummy"></h:inputHidden> <jsp:include
					page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="migrationForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{legacyContractBackingBean.validationMessages}"></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<%--
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Migration Wizard" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
--%>

								<tr>
									<td width="100%"><b> Step 1 : Contract </b></td>
								</tr>
								<tr>
									<td>Please select the Contract Id to be migrated from the
									source system to ET-Auto Bagging system. <br>
									Click Next to initiate migration on the selected Date Segments
									option.</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

							</TABLE>
							<!-- End of Tab table --> <!--							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
-->
							<TABLE border="0" cellspacing="0" cellpadding="3">
								<TR>
									<TD width="168">&nbsp;<h:outputText
										styleClass="#{legacyContractBackingBean.requiredContractId? 'mandatoryError': 'mandatoryNormal'}"
										value="Contract ID*" /></TD>
									<!--  <TD valign="top" width="154"><SPAN class="mandatoryNormal"
										id="contractId"> Contract ID*</SPAN></TD>-->
									<TD valign="top" width="285"><h:inputText
										styleClass="formInputField" id="ContractId" size="10"
										value="#{legacyContractBackingBean.contractId}" maxlength="4">
										<f:validateLength maximum="4"></f:validateLength>
									</h:inputText></TD>
								</TR>

								<TR>
									<TD width="168">&nbsp;<h:outputText
										styleClass="#{legacyContractBackingBean.requiredSourceSystem? 'mandatoryError': 'mandatoryNormal'}"
										value="System*" /></TD>
									<!--  <TD valign="top" width="154"><SPAN class="mandatoryNormal"
										id="system">System*</SPAN></TD>-->
									<TD align="left" width="285"><h:selectOneMenu
										value="#{legacyContractBackingBean.sourceSystem}" id="System">

										<f:selectItems
											value="#{legacyContractBackingBean.legacySystem}" />
									</h:selectOneMenu></TD>
								</TR>
								<tr height="8"><td></td></tr>
								<tr>
									<TD width="100" valign="top">&nbsp;<h:outputText
										styleClass="#{legacyContractBackingBean.requiredOption? 'mandatoryError': 'mandatoryNormal'}"
										value="Option*" /></TD>
									
									<td align="left"  width="200">
										<t:selectOneRadio id="buttons" layout="spread"
											value="#{legacyContractBackingBean.option}">
											
											<f:selectItem
												itemLabel="#{legacyContractBackingBean.MIGRATION}"
												itemValue="3" />
										
											<f:selectItem
												itemLabel="#{legacyContractBackingBean.RENEW_EXISTING_DATE_SEGMENT}"
												itemValue="2" />
										</t:selectOneRadio>
										<h:panelGrid columns="1">
											<t:radio for="buttons" index="0"/>
											<t:radio for="buttons" index="1" />
										</h:panelGrid>
									</td>
								</tr>
								<TR>
								<TR>
									<TD width="189">&nbsp;<SPAN
										style="margin-left:0px;margin-right:0px;width:15px;"> <h:commandButton
										value="Next" styleClass="wpdButton" id="nextButton"
										action="#{legacyContractBackingBean.goToStep2}"></h:commandButton></SPAN>
									</TD>
								</TR>
							</TABLE>
							<!--</FIELDSET>--> <BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->

					<h:inputHidden id="userConfirmToAddNewDS"
						value="#{legacyContractBackingBean.userConfirmToAddNewDateSegment}" />
					<h:inputHidden id="afterMigrationDSExist"
						value="#{legacyContractBackingBean.afterMigrationDateSegmentExist}" />
					<h:inputHidden id="migCompleted"
						value="#{legacyContractBackingBean.migCompleted}" />
					<h:inputHidden id="selectedContract"
						value="#{legacyContractBackingBean.selectedContract}" />
					<h:inputHidden id="selectedContractKey"
						value="#{legacyContractBackingBean.selectedContractKeyFromSearch}" />
					<h:inputHidden id="selectedContractType"
						value="#{legacyContractBackingBean.selectedContractTypeFromSearch}" />
					<h:inputHidden id="selectedContractDateSegSysId"
						value="#{legacyContractBackingBean.selectedDateSegKeyFromSearch}" />
					<h:inputHidden id="selectedVerion"
						value="#{legacyContractBackingBean.selectedVerionFromSearch}" />
					<h:inputHidden id="selectedStatus"
						value="#{legacyContractBackingBean.selectedStatusFromSearch}" />

					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>

	</BODY>
</f:view>
<script language="javascript">
/*
var afterMigrationDateSegmentExistFlag = document.getElementById('migrationForm:afterMigrationDSExist').value;
if(afterMigrationDateSegmentExistFlag =='true'){
		var message = 'The contract is already migrated. Do you want to migrate rest of the datesegments.';
		var message1 = 'Contract is already migrated, Click Ok to migrate all rest of the Date Segments? and Cancel to abort';
//		if(window.confirm(message)){
			document.getElementById('migrationForm:userConfirmToAddNewDS').value = 'true';	
			document.getElementById('migrationForm:nextButton').click();
//		}
document.getElementById('migrationForm:afterMigrationDSExist').value = 'false';	
}
*/
var migCompletFlag = document.getElementById('migrationForm:migCompleted').value;
if(migCompletFlag=='true'){
showPopupForContract('../contract/contractBasicInfoView.jsp');
}
//Open a popup window to show contract.
function showPopupForContract(page){
	var url=page+getUrl()+"?contractID="+document.getElementById('migrationForm:selectedContract').value
				+'&contractSysId='+document.getElementById('migrationForm:selectedContractKey').value
				+'&contractDateSegmentSysId='+document.getElementById('migrationForm:selectedContractDateSegSysId').value
				+'&type='+document.getElementById('migrationForm:selectedContractType').value
				+'&status='	+document.getElementById('migrationForm:selectedStatus').value
				+'&version='+document.getElementById('migrationForm:selectedVerion').value
				+'&migCompletFlag='+document.getElementById('migrationForm:migCompleted').value
				+'&temp='+Math.random()			;	

	var returnvalue=window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
document.getElementById('migrationForm:migCompleted').value = 'false';
}


</script>


</HTML>


