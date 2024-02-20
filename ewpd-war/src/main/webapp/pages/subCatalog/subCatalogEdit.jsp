<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>

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

<TITLE>Edit SubCatalog</TITLE>
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
	<BODY
		onkeypress="return submitOnEnterKey('subCatalogEditForm:create');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><h:inputHidden id="dummy"
				value="#{subCatalogBackingBean.subCatalogName}"></h:inputHidden> <jsp:include
				page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="subCatalogEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{subCatalogBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="37%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="left" width="2"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td class="tabActive" width="50%"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>

										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<TD class="tabNormal" width="50%"><h:commandLink
											action="#{subCatalogBackingBean.loadItem}" id="subId"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText id="dataDomainTable" value="Item" />
										</h:commandLink></TD>

										<TD align="right" width="2"><IMG
											src="../../images/tabNormalRight.gif" width="3" height="21"></TD>
									</TR>
								</table>
								</td>
								<td width="63%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="80%">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style="width:100%"><LEGEND><FONT color="black">Business
									Domain</FONT></LEGEND>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredLob ? 'mandatoryError': 'mandatoryNormal'}"
												id="LobStar" value="Line of Business*" /></TD>
											<TD width="30%">
											<DIV id="lobDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtLob"
												value="#{subCatalogBackingBean.lob}"></h:inputHidden></TD>
											<TD width="40%"></TD>
										</TR>
										<TR>
											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredBusinessEntity ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessEntityStar" value="Business Entity*" /></TD>
											<TD width="30%">
											<DIV id="BusinessEntityDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{subCatalogBackingBean.businessEntity}"></h:inputHidden>
											</TD>
											<TD width="40%"></TD>
										</TR>
										<TR>

											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredBusinessGroup ? 'mandatoryError': 'mandatoryNormal'}"
												id="BusinessGroupStar" value="Business Group*" /></TD>
											<TD width="30%">
											<DIV id="BusinessGroupDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{subCatalogBackingBean.businessGroup}"></h:inputHidden>
											</TD>
											<TD width="40%"></TD>
										</TR>
<!-- CARS START -->						
										<TR>

											<TD width="30%"><h:outputText
												styleClass="#{subCatalogBackingBean.requiredMarketBusinessUnit ? 'mandatoryError': 'mandatoryNormal'}"
												id="marketBusinessUnitStar" value="Market Business Unit*" /></TD>
											<TD width="30%">
											<DIV id="marketBusinessUnitDiv" class="selectDataDisplayDiv"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{subCatalogBackingBean.marketBusinessUnit}"></h:inputHidden>
											</TD>
											<TD width="40%"></TD>
										</TR>
<!-- CARS END -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{subCatalogBackingBean.requiredSubCatalogName ? 'mandatoryError': 'mandatoryNormal'}"
										id="CatalogNameStar" value="Name*" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{subCatalogBackingBean.subCatalogName}" /> <h:inputHidden
										value="#{subCatalogBackingBean.subCatalogName}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{subCatalogBackingBean.requiredParentCatalogId ? 'mandatoryError': 'mandatoryNormal'}"
										id="ParentCatalogStar" value="Parent Catalog*" /></TD>

									<TD width="30%">
									<DIV id="ParentCatalogDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtParentCatalog"
										value="#{subCatalogBackingBean.parentCatalogId}"></h:inputHidden>
									<TD align="left" width="30%"><h:commandButton
										alt="parentCatalogName" id="ParentCatalogButton"
										image="../../images/select.gif" style="cursor: hand"
										onclick="ewpdModalWindow_ewpd('../popups/selectCatalogPopupForItemCreate.jsp','ParentCatalogDiv','subCatalogEditForm:txtParentCatalog',4,2); return false;"
										tabindex="3"></h:commandButton></TD>
								</TR>
								<script type="text/javascript">
										function RSCustomInterface(tbElementName){
											this.tbName = tbElementName;
											this.getText = getText;
											this.setText = setText;
									
										function getText(){
											if(!document.getElementById(this.tbName)) {
												alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
												return '';
											} else return document.getElementById(this.tbName).value;
										}
										function setText(text){
											if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
										}
									}
								</script>
								<TR>
									<TD width="30%" valign="top">&nbsp;<h:outputText
										id="descriptionStar" value="Description " /></TD>
									<TD align="left" width="30%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{subCatalogBackingBean.description}" tabindex="7"></h:inputTextarea></TD>
									<TD width="40%"></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="subCatalogEditForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Sub Catalog Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{subCatalogBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{subCatalogBackingBean.createdUser}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="30%"><h:outputText styleClass=""
										value="#{subCatalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createDateHidden"
										value="#{subCatalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="timezone"
										value="#{requestScope.timezone}"></h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{subCatalogBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{subCatalogBackingBean.lastUpdatedUser}" /></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="30%"><h:outputText styleClass=""
										value="#{subCatalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updateDateHidden"
										value="#{subCatalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="30%">
										<h:commandButton value="Save" id="create" onmousedown="javascript:savePageAction(this.id);"
												styleClass="wpdButton" onclick="return validateDesc() && runSpellCheck();" tabindex="7">
											</h:commandButton>
											<h:commandLink id="save"
												style="hidden" action="#{subCatalogBackingBean.update}">
											</h:commandLink>

									</TD>

								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				<h:inputHidden id="subCatalogId"
					value="#{subCatalogBackingBean.subCatalogId}"></h:inputHidden>
				<h:inputHidden id="subCatalogParentId"
					value="#{subCatalogBackingBean.subCatalogParentId}"></h:inputHidden>

				<!-- End of hidden fields  -->

			</h:form></TD>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="subCatalogGenInfo" /></form>
<!-- space for script -->
<script>
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}

	var validationMessage = "Description allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateDesc() {
		if (validateChars('subCatalogEditForm:txtDescription',
				validationMessage))
			return true;
		else {
			document.getElementById('subCatalogEditForm:txtDescription')
					.focus();
			return false;
		}
	}
	function spellFin(cancelled) {

		document.getElementById('subCatalogEditForm:save').click();
	}
	copyHiddenToDiv_ewpd('subCatalogEditForm:txtLob', 'lobDiv', 2, 2);
	copyHiddenToDiv_ewpd('subCatalogEditForm:txtBusinessEntity',
			'BusinessEntityDiv', 2, 2);
	copyHiddenToDiv_ewpd('subCatalogEditForm:txtBusinessGroup',
			'BusinessGroupDiv', 2, 2);
	copyHiddenToDiv_ewpd('subCatalogEditForm:txtMarketBusinessUnit',
			'marketBusinessUnitDiv', 2, 2);
	copyHiddenToDiv_ewpd('subCatalogEditForm:txtParentCatalog',
			'ParentCatalogDiv', 3, 2);
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>












