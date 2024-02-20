<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Custom Message Text View</TITLE>
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
<BASE target="_self"/>
</HEAD>
<f:view>
<BODY>
    <h:form styleClass="form" id="customMessageTextView">
    <h:inputHidden id="viewCustomMessageText" value= "#{customMessageSearchBackingBean.retrieveCustomMessageText}"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>	                		

						<TD colspan="2" valign="top" class="ContentArea" height="480">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>


						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD width="23%">&nbsp;<h:outputText id="headerRuleId"
										value="Header Rule Id " /></TD>
									<TD align="left" width="49%">
										<h:outputText
										value="#{customMessageSearchBackingBean.headerRuleId}" /> 
										<h:inputHidden id="headerRuleIdHidden"
										value="#{customMessageSearchBackingBean.headerRuleId}"></h:inputHidden>
										<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();return false;" />	
									</TD>
									<TD width="28%" align="left"></TD>
								</TR>

								<TR>
									<TD width="23%">&nbsp;<h:outputText id="headerRuleDesc"
										value="Header Rule Description " /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.headerRuleDesc}" /> <h:inputHidden
										value="#{customMessageSearchBackingBean.headerRuleDesc}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>

								<TR>
									<TD width="23%">&nbsp;<h:outputText id="spsParameterId"
										value="SPS Parameter ID" /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.spsId}" /> <h:inputHidden
										value="#{customMessageSearchBackingBean.spsId}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>

								<TR>
									<TD valign="top" width="23%">&nbsp;<h:outputText
										id="spsDescription" value="SPS Description " /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.spsDescription}" /> <h:inputHidden
										value="#{customMessageSearchBackingBean.spsDescription}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>
								<TR>
									<TD valign="top" width="23%">&nbsp;<h:outputText id="msgText"
										value="Message Text " /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.messageText}" styleClass="formTxtAreaReadOnly"/> <h:inputHidden
										value="#{customMessageSearchBackingBean.messageText}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>
								<TR>
									<TD valign="top" width="23%">&nbsp;<h:outputText
										id="msgIndicator" value="Message Indicator" /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.messageIndicator}" />
									<h:inputHidden
										value="#{customMessageSearchBackingBean.messageIndicator}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>
								<TR>
									<TD valign="top" width="23%">&nbsp;<h:outputText
										id="msgNoteTypeCode" value="Note Type Code" /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.noteTypeCode}" />
									</TD>
									<TD width="28%"></TD>
								</TR>
								<TR>
									<TD width="23%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.createdUser}" /> <h:inputHidden
										value="#{customMessageSearchBackingBean.createdUser}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>

								<TR>
									<TD width="23%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden value="#{requestScope.timezone}"></h:inputHidden>
									<h:inputHidden
										value="#{customMessageSearchBackingBean.createdDate}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>


								<TR>
									<TD width="23%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.lastUpdatedUser}" /> <h:inputHidden
										value="#{customMessageSearchBackingBean.lastUpdatedUser}"></h:inputHidden>
									</TD>
									<TD width="28%"></TD>
								</TR>


								<TR>
									<TD width="23%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="49%"><h:outputText
										value="#{customMessageSearchBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden value="#{requestScope.timezone}"></h:inputHidden>
									<h:inputHidden
										value="#{customMessageSearchBackingBean.lastUpdatedDate}"></h:inputHidden>

									</TD>
									<TD width="28%"></TD>
								</TR>





							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
					</table>
					
<!-- Space for hidden fields -->

<!-- End of hidden fields  -->

				</h:form>
			</TD>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>
	</table>
</BODY>
</f:view>
<!-- space for script -->
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="customMessageTextViewPrint" />
</form>
<script>
function viewAction(){
	var ruleIdStr = document.getElementById('customMessageTextView:headerRuleIdHidden').value;
	if(ruleIdStr.length <=1){
			alert('Benefit Rule ID need to be selected.');
		}
	else{		
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleIdStr)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}
</script>
</HTML>
