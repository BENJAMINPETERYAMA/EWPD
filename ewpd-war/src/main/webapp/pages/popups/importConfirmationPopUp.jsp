<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
		<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
		<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
		<%@ taglib uri="/wpd.tld" prefix="w"%>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<META name="GENERATOR" content="IBM WebSphere Studio">
		<META http-equiv="Content-Style-Type" content="text/css">
		<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
		<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
		<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
		<BASE target="_self" />
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
		<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<f:view>

	<TITLE><h:outputText />Confirmation</TITLE>
	<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
</HEAD>
<BODY>
	<h:form id="confirmationpopupform">
		<div id="confirmInfo">
			<TABLE>
				<TBODY>
					<TR>
						<TD><w:message>
							</w:message></TD>
					</TR>
				</TBODY>
			</TABLE>
			<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="94%" align="center" colspan = "6"><strong><h:outputText
								value="Confirm Import"></h:outputText></strong></TD>
						</TR>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv" class=indctvDtlDataTableDiv >
						<h:dataTable headerClass="dataTableHeader"
							rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="column15pc,column35px,column15pc,column35px" cellspacing="1"
							width="100%" id="dtlTable"	rendered="#{indicativeLayoutMappingBackingBean.displayDataTable}"	
							value="#{indicativeLayoutMappingBackingBean.indicativeDetailVOList}"
							var="indicativeDetailVO" cellpadding="0" bgcolor="#E6E6E6">
							<h:column >
								<f:facet name="header">
									<h:outputText  value="Indicative Code"/>
								</f:facet>    
									<h:outputText value="#{indicativeDetailVO.indicativeCode}"></h:outputText>
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText  value="Indicative Desc"/>
								</f:facet>  
								<h:outputText value="#{indicativeDetailVO.indicativeCodeDescText}"></h:outputText>
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText  value="Length"/>
								</f:facet>
								<h:outputText value="#{indicativeDetailVO.fieldLength}"></h:outputText>
							</h:column>
							<h:column>	
								<f:facet name="header">
									<h:outputText  value="Action"/>
								</f:facet>
								<h:outputText value="#{indicativeDetailVO.action}"></h:outputText>
							</h:column>	
												
						</h:dataTable>
					</DIV>
					</td>
				</tr>
				
				<TR bgcolor="#ffffff" >
						<td align="left" colspan ="4">&nbsp;
						<h:commandButton id="selectButton" value="Confirm"
							styleClass="wpdbutton" disabled="#{indicativeLayoutMappingBackingBean.confirmDisable}" tabindex ="1"
							onclick="submitConfirmation('confirmationpopupform:lnkConfirm');return false;"></h:commandButton>
						</td>
				</TR>
											
			</TBODY>
		</table>
		</div>
		<h:commandLink id="lnkConfirm" style="display:none; visibility: hidden;"
			action="#{indicativeLayoutMappingBackingBean.confirmIndicativeMapping}">
			<f:verbatim />
		</h:commandLink>
	</h:form>
	<div class="transparent" id="transparentDiv"></div>
	<div class="picDiv" id="loadingImageDiv">
		<table width="100%" height="100%">
			<tr>
				<td align="center"><img src="../../images/wait.gif"></td>
			</tr>
		</table>
	</div>
</BODY>
</f:view>
<script>
	enablePage();
	function submitConfirmation(commandLinkId) {
		document.getElementById(commandLinkId).click();
		disablePage();
	}
</script>
</HTML>

