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
<TITLE>Edit Module</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('moduleEditForm:save');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><h:inputHidden id="dummy"
				value="#{moduleBackingBean.breadCrumbForEdit}" /> <jsp:include
				page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="moduleEditForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include page="../module/moduleTree.jsp" />
						</DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message value="#{moduleBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td>
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" General Information" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td>
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{moduleBackingBean.loadModuleConfiguration}"
											id="modId"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText id="dataDomainTable"
												value="Module Configuration" />
										</h:commandLink></td>

										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
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
									<TD width="25%">&nbsp;<h:outputText id="RoleNameStar"
										value="Name*" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{moduleBackingBean.moduleName}" /> <h:inputHidden
										value="#{moduleBackingBean.moduleName}" /></TD>
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
										id="descriptionStar" value="Description " /></TD>
									<TD align="left" width="30%"><h:inputTextarea
										styleClass="formTxtAreaField_GeneralDesc" id="txtDescription"
										value="#{moduleBackingBean.description}" tabindex="7"></h:inputTextarea></TD>
									<TD width="40%"></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="moduleEditForm:txtDescription"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Module Description"
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
									<TD width="25%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{moduleBackingBean.createdUser}" /> <h:inputHidden
										id="createdUserHidden"
										value="#{moduleBackingBean.createdUser}" /></TD>
									<TD width="40%"></TD>
								</TR>

								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="30%"><h:outputText
										value="#{moduleBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="createdDateHidden"
										value="#{moduleBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <h:inputHidden id="time"
										value="#{requestScope.timezone}">
									</h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{moduleBackingBean.lastUpdatedUser}" /> <h:inputHidden
										id="lastUpdatedUserHidden"
										value="#{moduleBackingBean.lastUpdatedUser}" /></TD>
									<TD width="40%"></TD>
								</TR>


								<TR>
									<TD width="25%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="30%"><h:outputText
										value="#{moduleBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="lastUpdatedDateHidden"
										value="#{moduleBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden></TD>
									<TD width="40%"></TD>
								</TR>



								<TR>
									<TD width="25%">
										<h:commandButton value="Save" id="save" onmousedown="javascript:savePageAction(this.id);"
												styleClass="wpdButton" onclick="return runSpellCheck();" tabindex="8">
										</h:commandButton>
										<h:commandLink id="saveModule"
												style="hidden" action="#{moduleBackingBean.editModule}">
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
				<h:inputHidden id="moduleID" value="#{moduleBackingBean.moduleId}"></h:inputHidden>
				<!-- End of hidden fields  -->

			</h:form></TD>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</table>
	</BODY>
</f:view>
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
function spellFin(cancelled){

		document.getElementById('moduleEditForm:saveModule').click();	
}
	</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="moduleGenInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
