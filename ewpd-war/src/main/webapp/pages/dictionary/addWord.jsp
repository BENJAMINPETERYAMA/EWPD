<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitDefinitionCreate.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Add Word</TITLE>
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
	<BODY onkeypress="return submitOnEnterKey('addWordForm:addWord');">
	<table width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><h:inputHidden id="dummy"
				value="#{dictionaryManageBackingBean.wordToBeAdded}"></h:inputHidden>
			<jsp:include page="../navigation/top.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="addWordForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv" style="height:380px"><!-- Space for Tree  Data	-->

						</DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{dictionaryManageBackingBean.validationMessages}"></w:message>
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
											value=" Add Word" /></td>
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
										styleClass="#{dictionaryManageBackingBean.requiredWord ? 'mandatoryError': 'mandatoryNormal'}"
										id="WordStar" value="Word to be added to the Dictionary *" />
									</TD>
									<TD align="left" width="30%"><h:inputText
										styleClass="formInputField" id="wordTxt" maxlength="30"
										tabindex="4"
										value="#{dictionaryManageBackingBean.wordToBeAdded}" /></TD>
									<TD width="40%"></TD>
								</TR>
								<TR>
									<TD width="25%">&nbsp;</TD>
								</TR>
								<TR>
									<TD width="25%">
										<h:commandButton value="Add" id="addWord"
											styleClass="wpdButton" onclick="return add();" tabindex="2">
										</h:commandButton>
										<h:commandLink id="addWordLink"
											style="hidden" action="#{dictionaryManageBackingBean.addWordToDictionary}">
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
document.getElementById('addWordForm:wordTxt').focus(); // for on load default selection
function add(){
	var flag = confirm("Are you sure to continue adding the word?");	
	if(flag){
		document.getElementById('addWordForm:addWordLink').click();
	}
	return false;
}
</script>
</HTML>





