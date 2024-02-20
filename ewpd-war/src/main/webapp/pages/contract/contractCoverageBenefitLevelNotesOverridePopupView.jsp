<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractCoverageBenefitLevelNotesOverridePopupView.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">

<TITLE>Notes Look Up Popup</TITLE>
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

	<h:form id="notesOverrideForm">
		<h:outputText value="No Notes Available." 
					rendered="#{contractCoverageNotesOverrideBackingBean.viewNotes == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="notesDiv">
											<w:message></w:message>

								
			<table width="98%" align="right" cellpadding="0" cellspacing="0"
				border="0">
				<TBODY>
					<TR>
						
						<TD>
						<table id="headerTable" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc" width="100%">
							<tr class="dataTableColumnHeader"
								style="background-color:#cccccc;">
								
								<TD align="center"><strong> <h:outputText
									value="Notes">
								</h:outputText> </strong></td>
							</tr>
						</table>
						</TD>
					</TR>
					<tr>
						<td>
						<DIV id="popupDataTableDiv" style="height: 337px; overflow:auto;">
						<h:dataTable cellspacing="1" id="notesDataTable" 
							rendered="#{contractCoverageNotesOverrideBackingBean.viewNotes != null}"
							value="#{contractCoverageNotesOverrideBackingBean.viewNotes}"
							var="singleValue" cellpadding="0" bgcolor="#cccccc" width="100%"
							rowClasses="dataTableEvenRow,dataTableOddRow">
							
							<h:column>
								<h:inputHidden value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden  value="#{singleValue.noteId}"></h:inputHidden>
                                 	<f:verbatim>&nbsp;</f:verbatim>
								<h:outputText value="#{singleValue.noteName}"></h:outputText>
								
							</h:column>
						</h:dataTable></DIV>
						</td>
					</tr>
				</TBODY>
			</table>
	</DIV>
	 
	</h:form>
	</BODY>
</f:view>

<script language="javascript">

 initialize();
	
	function initialize(){
	
		if(document.getElementById('notesOverrideForm:notesDataTable') != null) {
		
		}else {
			document.getElementById("notesDiv").style.visibility = 'hidden';
		}
	}



</script>
</HTML>

