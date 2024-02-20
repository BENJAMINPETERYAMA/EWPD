<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitDefinitionCreate.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Edit Catalog</TITLE>
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
<script language="JavaScript" src="../../js/showModalDialog.js"></script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('catalogEditForm:save');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="catalogEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{catalogBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
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
									<TD width="25%">&nbsp;<h:outputText
										styleClass="#{catalogBackingBean.requiredCatalogName ? 'mandatoryError': 'mandatoryNormal'}"
										id="CatalogNameStar" value="Name*" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{catalogBackingBean.catalogName}" /> <h:inputHidden
										value="#{catalogBackingBean.catalogName}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText
										styleClass="#{catalogBackingBean.requiredCatalogDatatype ? 'mandatoryError': 'mandatoryNormal'}"
										id="CatalogDatatypeStar" value="Datatype*" /></TD>
									<TD align="left" width="30%"><h:selectOneMenu id="dataTypeName"
										value="#{catalogBackingBean.catalogDatatype}">
										<f:selectItems id="dataTypeList"
											value="#{catalogBackingBean.dataTypeListForCombo}" />
									</h:selectOneMenu></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText
										styleClass="#{catalogBackingBean.requiredCatalogSize ? 'mandatoryError': 'mandatoryNormal'}"
										id="CatalogSizeStar" value="Size*" /></TD>
									<TD align="left" width="30%"><h:inputText
										styleClass="formInputField" maxlength="2" tabindex="6"
										value="#{catalogBackingBean.catalogSize}" /></TD>
									<TD width="40%"></TD>
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
									<TD width="25%" valign="top">&nbsp;<h:outputText
										styleClass="#{catalogBackingBean.requiredDesription ? 'mandatoryError': 'mandatoryNormal'}"
										id="descriptionStar" value="Description " /></TD>
									<TD align="left" width="30%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{catalogBackingBean.description}" tabindex="7"></h:inputTextarea></TD>
									<TD width="40%"></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="catalogEditForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Catalog Description"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="False" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{catalogBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{catalogBackingBean.createdUser}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{catalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createDateHidden"
										value="#{catalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden><h:inputHidden id="timezone"
										value="#{requestScope.timezone}"></h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{catalogBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{catalogBackingBean.lastUpdatedUser}" /></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{catalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdatedDateHidden"
										value="#{catalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>



								<TR>
									<TD width="25%">
										<h:commandButton value="Save" id="save" onmousedown="javascript:savePageAction(this.id);"
												styleClass="wpdButton" onclick="return validateDesc() && runSpellCheck();" tabindex="8">
										</h:commandButton>
										<h:commandLink id="saveCatalog"
												style="hidden" action="#{catalogBackingBean.editCatalog}">
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
				<h:inputHidden id="hiddenSize"
					value="#{catalogBackingBean.catalogSizeOld}" />
				<h:inputHidden id="hiddenCatalogId"
					value="#{catalogBackingBean.catalogId}" />
				<h:inputHidden id="hiddenDatatype"
					value="#{catalogBackingBean.catalogDatatypeOld}" />
				<h:inputHidden id="hiddenDescription"
					value="#{catalogBackingBean.descriptionOld}" />
				<!-- End of hidden fields  -->

			</h:form></TD>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>
<script>

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('catalogEditForm:saveCatalog').click();	
}

 var validationMessage = "Description allows only ASCII characters and should not have linefeeds or non-ASCII characters. Please correct and try again.";
	function validateDesc(){
	if(validateChars('catalogEditForm:txtDescription',validationMessage))
			return true;
		else{
			document.getElementById('catalogEditForm:txtDescription').focus();
			return false;
		}
}
	</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="catalogGenInfo" /></form> <!-- space for script -->
<%@ include file="../navigation/unsavedDataFinder.html"%>

</HTML>





