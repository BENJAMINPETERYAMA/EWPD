<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Administration Option Search Print</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>

<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>



<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{searchAdminOptionBackingBean.adminOptionSearchPrint}">
			</h:inputHidden>
			
		</tr>
		<tr>
			<td><h:form id="AdminOptionSearchPrintForm">
				<TABLE border="0" cellspacing="0" cellpadding="0" width="100%">
					<TBODY>
						<tr><td>&nbsp; </td></tr>
				<TR>
					<TD>  <FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
									<h:outputText id="breadcrumb" value="#{searchAdminOptionBackingBean.breadCrumpText}">
									</h:outputText>
						 </FIELDSET>
					</TD>
				</TR>
				<tr><td>&nbsp; </td></tr>
				<TR>
							<td valign="top" class="ContentArea">
							

							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="25%"><h:outputText
													value="Administration Name"></h:outputText></TD>
												<TD align="left"><h:outputText value="Term"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Qualifier"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="adminOption" cellpadding="3"
										width="100%" cellspacing="1" 
										rendered="#{searchAdminOptionBackingBean.adminOptionSearchResultList != null}"
										value="#{searchAdminOptionBackingBean.adminOptionSearchResultList}">
										<h:column>
											
											<h:outputText id="adminOptionName"
												value="#{adminOption.adminName}"></h:outputText>
											
										</h:column>
										<h:column>

											<h:outputText id="Term" value="#{adminOption.termDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="Qualifier"
												value="#{adminOption.qualifierDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminVersion"
												value="#{adminOption.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminStatus" value="#{adminOption.status}"></h:outputText>
										</h:column>
										
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							
							</TD>
						</TR>
				</TABLE></h:form></td>
		</tr>

	</table>
	</BODY>

</f:view>
<script language="javascript">
	if(document.getElementById('AdminOptionSearchPrintForm:searchResultTable') != null) {
			setColumnWidth('AdminOptionSearchPrintForm:searchResultTable','25%:20%:20%:10%:25%');
			setColumnWidth('resultHeaderTable','25%:20%:20%:10%:25%');	
			
		}
	window.print();	
</script>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="adminOptionSearchResultPrint" >
	</form>
</HTML>
