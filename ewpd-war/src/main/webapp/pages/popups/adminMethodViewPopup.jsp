<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">

<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 50%;
	
}.shortDescriptionColumn {
	width: 50%;
}

</style>
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
<BASE target="_self" />
<f:view>
<TITLE>Admin Method Configuration Popup</TITLE>
</HEAD>
	<BODY onload="return false;">
	<h:form id="adminMethodViewPopupForm">

	<table width="100%">
			<TR>
				<td>
				<table width = '100%'>
					<tr>
						<td align="right">
							<a href="#">
								<img src="../../images/print.gif" alt="Print" width="19" height="14" border="0"
									onclick="printSelection();return false;" />
							 </a>
						</td>
					</tr>
				</table>	
				</td>	
			</tr>

<tr>
<td>
	<h:inputHidden value="#{adminMethodPopupBackingBean.loadAdminMethodViewPopup}"></h:inputHidden>
		<h:outputText value="No Admin Configurations Available." 
					rendered="#{adminMethodPopupBackingBean.adminMethodConfig == null}" 
					styleClass="dataTableColumnHeader"/>
		<DIV id="adminMethodViewDiv">

		<table border="0" cellpadding="5" cellspacing="0" width="100%">
			<tr>
				<td align="left">&nbsp;</td>
			</tr>

		</table>

		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR height="100%">
					<TD>
					<table width="100%" id="headerTable" border="0" cellspacing="2" cellpadding="3"
						bgcolor="#cccccc">
						<TR>

							<TD width="50%" align="center"><strong><h:outputText
								value="Reference"></h:outputText></strong><br></TD>
							<TD width="50%" align="center"><strong><h:outputText
								value="Value"></h:outputText></strong>
						</TR>
					</table>
					</TD>
				</TR>
				<tr >
					<td>
					<DIV id="popupDataTableDiv" class=popupDataTableDiv><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn" cellspacing="1"
						width="100%" id="adminMethodViewTable" 
							rendered="#{adminMethodPopupBackingBean.adminMethodConfig != null}"
						value="#{adminMethodPopupBackingBean.adminMethodConfig}"
						var="eachRow" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
                            <h:outputText escape="false" value="#{eachRow.referenceId}"></h:outputText>
						</h:column>
						<h:column>
							
                            <h:outputText escape="false" value="#{eachRow.answerDesc}"></h:outputText>
						</h:column>				
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</DIV>
	</td>
	</tr>
</table>
					<h:inputHidden id="entityId"
					value="#{adminMethodPopupBackingBean.entityId}"></h:inputHidden> 
					<h:inputHidden id="adminId"
					value="#{adminMethodPopupBackingBean.adminId}"></h:inputHidden> 
					<h:inputHidden id="entityType"
					value="#{adminMethodPopupBackingBean.entityType}"></h:inputHidden> 
					<h:inputHidden id="adminMethodSysId"
					value="#{adminMethodPopupBackingBean.adminMethodSysId}"></h:inputHidden> 


	</h:form>


	</BODY>

</f:view>
<script language="JavaScript">

	initialize();
	window.opener = window.dialogArguments.parentWindow;
	function initialize(){
		if(document.getElementById('adminMethodViewPopupForm:adminMethodViewTable') != null) {
			setColumnWidth('adminMethodViewPopupForm:adminMethodViewTable', '50%:50%');
			setColumnWidth('headerTable', '50%:50%');
		}else {
			document.getElementById("adminMethodViewDiv").style.visibility = 'hidden';
		}
	}



</script>
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="adminMethodPopup" />
</form>
</HTML>
