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

<TITLE>RoleConfiguration Print</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
<BODY>
	<h:form styleClass="form" id="roleConfiguraionPrintForm">
	<h:outputText value="No Modules Available." 
					rendered="#{roleBackingBean.searchResultList == null}" 
					styleClass="dataTableColumnHeader"/>
	<div id ="printFormDiv">
	<table width="70%" cellpadding="0" cellspacing="20" border=0>
		
						<TR>
							
	                		<TD colspan="2" valign="top" class="ContentArea">&nbsp; Administration >> Role (<h:outputText
								id="headerRoleName" value="#{roleBackingBean.roleName}"></h:outputText>)>> Print
	
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; width=100% cursor:hand;">Modules</DIV>
<!--	Start of Table for actual Data	-->		
									
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="position:relative; cursor:hand;width=100%"><h:dataTable
										headerClass="dataTableHeader" id="associatedModulesTable" 
										var="singleValue" cellpadding="0" cellspacing="0"
										rendered="#{roleBackingBean.searchResultList != null}"
										value="#{roleBackingBean.searchResultList}"
										border="0"
										width="100%">
										<h:column>
											<h:outputText id="itName" value="#{singleValue.moduleName}"></h:outputText>
											</h:column>
									
									</h:dataTable></DIV>
									</td>
								</TR>
								<TR>
								<TD>
									 
									</TD>
								</TR>
							</TABLE>

									
<!--	End of Page data	-->		
								</fieldset>		
							</TD>
						</TR>
					</table>
					</div>
				</h:form>
</BODY>
</f:view>

<script>
	initialize();
		function initialize(){
			if(document.getElementById('roleConfiguraionPrintForm:associatedModulesTable') == null) 
				
				document.getElementById("printFormDiv").style.visibility = 'hidden';
			}
	

</script>

<script>
window.print();

</script>

</HTML>