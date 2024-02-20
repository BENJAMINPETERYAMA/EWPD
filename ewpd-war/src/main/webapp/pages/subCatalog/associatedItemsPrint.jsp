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

<TITLE>AssociatedItems Print</TITLE>
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
	<h:form styleClass="form" id="AssociatedItemsPrintForm">
	<h:inputHidden id="viewSubCatalogId"
					value="#{subCatalogBackingBean.viewSubCatalogId}" />
	<table width="100%">
		<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:0px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
						<h:outputText id="breadcrumb" 
							value="#{subCatalogBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
	</table>
	<h:outputText value="No Items Available." 
					rendered="#{subCatalogBackingBean.printSearchResultList == null}" 
					styleClass="dataTableColumnHeader"/>
	<div id ="printItemFormDiv">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>				
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD colspan="2" valign="top" class="ContentArea">
												
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:60%">
									
<!--	Start of Table for actual Data	-->		
									<BR/>

							 
							<TABLE width="100%" cellpadding="0" cellspacing="0" >
									<tr>
										<td>
										
											
											<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">SubCatalog Associated Items</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="overflow:auto; width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="associatedItemsTable" 
										var="singleValue" cellpadding="0" cellspacing="0"
										rendered="#{subCatalogBackingBean.printSearchResultList != null}"
										value="#{subCatalogBackingBean.printSearchResultList}"
										border="0"
										width="100%">
										<h:column>
											<h:outputText id="itName" value="#{singleValue.description}"></h:outputText>
											</h:column>
									
									</h:dataTable></DIV>
									</td>
								</TR>
								<TR>
								<TD>
									 
									</TD>
								</TR>
							</TABLE>
</DIV>
									</TABLE>
<!--	End of Page data	-->		
								</fieldset>		
							</TD>
						</TR>
					</table>
					
				</td></tr></table></div>
				</h:form>
</BODY>
</f:view>

<script>
	initialize();
		function initialize(){
			if(document.getElementById('AssociatedItemsPrintForm:associatedItemsTable') == null) 
				
				document.getElementById("printItemFormDiv").style.visibility = 'hidden';
			}
	

</script>

<script>
window.print();

</script>

</HTML>

