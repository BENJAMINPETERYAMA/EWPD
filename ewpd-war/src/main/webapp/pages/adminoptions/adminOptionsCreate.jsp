<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Create Admin Options</TITLE>
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
		onkeypress="return submitOnEnterKey('adminOptionForm:createButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><h:inputHidden id="dummy"
				value="#{createAdminOptionBackingBean.dummyVariable}"></h:inputHidden>
			<jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="adminOptionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	--></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{createAdminOptionBackingBean.validationMessages}"></w:message></TD>
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
											value=" Admin Options" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<BR />
						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
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
									<TD width="180"><h:outputText id="outTxtAdminName"
										value="Name*"
										styleClass="#{createAdminOptionBackingBean.requiredAdminName ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<TD width="180"><h:inputText styleClass="formInputField"
										id="txtAdminName" tabindex="1" maxlength="250"
										value="#{createAdminOptionBackingBean.adminName}" /></TD>
								</TR>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="adminOptionForm:txtAdminName"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Admin Option Name"
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
									<TD><h:outputText id="outTxtTerm" value="Term"
										styleClass="#{createAdminOptionBackingBean.requiredTerm ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<%-- <TD><h:inputText id="txtTerm"
										styleClass="formInputField"
										value="#{createAdminOptionBackingBean.term}" readonly="true">
									</h:inputText> <h:inputHidden id="hiddenTerm"
										value="#{createAdminOptionBackingBean.term}"></h:inputHidden>
									</TD>--%>

<h:inputHidden id="hiddenTerm" value="#{createAdminOptionBackingBean.term}"></h:inputHidden>
<TD width="180">
			<div id="txtTerm" class="selectDataDisplayDiv"></div>
			<SCRIPT> parseForDiv(document.getElementById('txtTerm'), document.getElementById('adminOptionForm:hiddenTerm')); </SCRIPT>		
</TD>
<TD width="60">
	<h:commandButton alt="Term" id="selectButtonTerm" image="../../images/select.gif" onclick="termInfo();return false;" tabindex = "2">
	</h:commandButton>
</TD> 

									<%--<TD width="25" valign="top"><h:commandButton alt="Select"
										id="selectButtonTerm" image="../../images/select.gif"
										style="cursor: hand"
										onclick="termInfo();return false;"
										tabindex="2"></h:commandButton></TD>--%> 
								</TR>
								<TR>
									<TD><h:outputText id="outTxtQualifier" value="Qualifier" /></TD>
<h:inputHidden
										id="hiddenQualifier"
										value="#{createAdminOptionBackingBean.qualifier}"></h:inputHidden>
									<TD width="180">	<div id="txtQualifier" class="selectDataDisplayDiv"></div>
			<SCRIPT> parseForDiv(document.getElementById('txtQualifier'), document.getElementById('adminOptionForm:hiddenQualifier')); </SCRIPT>		
<%--<h:inputText id="txtQualifier"
										styleClass="formInputField"
										value="#{createAdminOptionBackingBean.qualifier}"
										readonly="true"></h:inputText> --%>
</TD>
									<TD valign="top" width="60"><h:commandButton alt="Select"
										id="selectButtonQualifier" image="../../images/select.gif"
										style="cursor: hand"
										onclick="qualifierInfo();return false;"
										tabindex="3"></h:commandButton></TD>
								</TR>
								<TR>
									<TD>&nbsp;</TD>
								</TR>
								<TR>
									<TD colspan="2"><h:commandButton id="createButton" value="Create" styleClass="wpdButton"
											tabindex="16" onclick="return runSpellCheck();">
										</h:commandButton> &nbsp;&nbsp;
									<TD width="60">&nbsp;</TD>
									<h:commandLink id="createAdminOption"
											style="hidden" action="#{createAdminOptionBackingBean.saveAdminOption}">
									</h:commandLink>						
	
								</TR>
								<TR>
									<TD>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>

						</fieldset>
						<BR />
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

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

 
copyHiddenToDiv_ewpd('adminOptionForm:hiddenTerm','txtTerm',2,2);
copyHiddenToDiv_ewpd('adminOptionForm:hiddenQualifier','txtQualifier',2,2);

//copyHiddenToDiv_ewpd('adminOptionForm:hiddenTerm','BusinessEntityDiv' ,2,2);
//copyHiddenToDiv_ewpd('adminOptionForm:hiddenQualifier','lobDiv' ,2,2);

document.getElementById('adminOptionForm:txtAdminName').focus(); // for on load default selection

//copyHiddenToDiv_ewpd('adminOptionForm:hiddenTerm','adminOptionForm:txtTerm',2,2);
//copyHiddenToDiv_ewpd('adminOptionForm:hiddenQualifier','adminOptionForm:txtQualifier',2,2); 
copyHiddenToInputText('adminOptionForm:hiddenReference','adminOptionForm:txtReference',1); 
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		document.getElementById('adminOptionForm:createAdminOption').click();
}
function termInfo(){
	ewpdModalWindow_ewpd('../adminoptionspopups/selectOneTermPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'term','txtTerm','adminOptionForm:hiddenTerm',2,2); 
}
function qualifierInfo()
{
	ewpdModalWindow_ewpd('../adminoptionspopups/selectOneQualifierPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'qualifier'+'&titleName=Qualifier Popup'+'&temp='+Math.random(),'txtQualifier','adminOptionForm:hiddenQualifier',2,2); 
}


</script>
</HTML>

