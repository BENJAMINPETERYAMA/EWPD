<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Custom Message Text Print</TITLE>
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
	
		<h:inputHidden id="spsMappingViewHidden"
			value="#{customMessageSearchBackingBean.retrieveCustomMessageText}"></h:inputHidden>
		
		<TABLE cellpadding="0" cellspacing="0">	
			<tr><td>&nbsp;</td></tr>
			<tr>
						<td width="1000"><FIELDSET style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%"><h:outputText id="breadcrumb" 
		                              value="#{customMessageSearchBackingBean.customMessagePrintBreadCrumb}">
		                        </h:outputText> 
		                   </FIELDSET>
						</td>		
			</tr>
			<TR>
				<TD width="1000"><br/><h:form styleClass="form" id="spsMappingViewPrintForm">
							<FIELDSET
								style="margin-left:30px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%">
						
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR valign="top">
										<TD width="300"><h:outputText	id="headerRuleId" value="Header Rule Id "/> </TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.headerRuleId}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.headerRuleId}"></h:inputHidden>	
											
										</TD>
									
									</TR>

									<TR valign="top" height="20">
										<TD width="300"><h:outputText 
												id="headerRuleDesc" value="Header Rule Description "/></TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.headerRuleDesc}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.headerRuleDesc}"></h:inputHidden>	
										</TD>										
									</TR>

									<TR>
										<TD width="300"><h:outputText 
												id="spsParameterId" value="SPS Parameter ID"/></TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.spsId}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.spsId}"></h:inputHidden>		
										</TD>
									</TR>
									
									<TR valign="top" height="20">
										<TD width="300"><h:outputText id = "spsDescription" value="SPS Description "/></TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.spsDescription}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.spsDescription}"></h:inputHidden>	
										</TD>
									</TR>
                                    <TR valign="top" height="20">
										<TD width="300"><h:outputText id = "msgText" value="Message Text "/></TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.messageText}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.messageText}"></h:inputHidden>		
										</TD>
									</TR>
									<TR valign="top" height="20">
										<TD width="300"><h:outputText id = "msgIndicator" value="Message Indicator"/> </TD>
										<TD width="500">
											<h:outputText value="#{customMessageSearchBackingBean.messageIndicator}" />
											<h:inputHidden value ="#{customMessageSearchBackingBean.messageIndicator}"></h:inputHidden>		
										</TD>
									</TR>	
									<TR valign="top" height="20">
										<TD width="300"><h:outputText id="msgNoteTypeCode" value="Note Type Code" /></TD>
										<TD width="500"><h:outputText value="#{customMessageSearchBackingBean.noteTypeCode}" /></TD>
									</TR>
									<TR>
										<TD width="275"><h:outputText value="Created By" /></TD>
										<TD width="150">
											<h:outputText id="createdUser"
											value="#{customMessageSearchBackingBean.createdUser}" />													
										   <h:inputHidden id="createdUserHidden"
											value="#{customMessageSearchBackingBean.createdUser}"></h:inputHidden>	
										</TD>

									</TR>
									
									<TR valign="top" height="20">
										<TD width="300"><h:outputText value="Created Date" /></TD>
										<TD width="500">
											<h:outputText id="createdDate"
											value="#{customMessageSearchBackingBean.createdDate}" >	
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />										
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										   <h:inputHidden id="createdDateHidden"
											value="#{customMessageSearchBackingBean.createdDate}"></h:inputHidden>											
										</TD>

									</TR>
									<TR>
										<TD width="275"><h:outputText value="Last Updated By" /></TD>
										<TD width="150">
											<h:outputText id="lastChangedUser"
											value="#{customMessageSearchBackingBean.lastUpdatedUser}" />													
										   <h:inputHidden id="lastChangedUserHidden"
											value="#{customMessageSearchBackingBean.lastUpdatedUser}"></h:inputHidden>	
										</TD>

									</TR>
								<TR valign="top" height="20">
										<TD width="300"><h:outputText value="Last Updated Date" /></TD>
										<TD width="500">
											<h:outputText id="lastChangedDate"
											value="#{customMessageSearchBackingBean.lastUpdatedDate}" >
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />													
											</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText>
										   <h:inputHidden id="lastChangedDateHidden"
											value="#{customMessageSearchBackingBean.lastUpdatedDate}"></h:inputHidden>	
										</TD>

									</TR>
									
								</TBODY>
							</TABLE>
					</FIELDSET>
						</TD>
						</TR>				
					</TABLE>
				</h:form>				
	</BODY>
</f:view>
<script>
window.print();
</script>
</HTML>
