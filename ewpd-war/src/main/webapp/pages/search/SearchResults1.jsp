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

<TITLE>Benefit Component Create</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
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
		<TR>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>
	
						<TABLE>
							<tr>
								<TD><B><h:outputText id="resultSummary" value="Search Summary"/></B></TD>
						</tr>
						<tr></tr>
						<tr>
						<td><h:outputText id="numberOfResults" value="Number Of Results"/></td><TD></TD>
						<TD><h:outputText id"totalResults" value="160"/></TD>
						</tr>
<tr></tr>
						<tr>
						<TD><a href="SearchResultsC.html" ><h:outputText id="contract" value="Contract"/></a></TD><td></td>
						<TD><h:outputText  value="40"/></TD>
						</tr>	
		
<tr></tr>
				<tr>
						<TD><a href="SearchResultsProduct.html" ><h:outputText id="product" value="Product"/></a></TD><TD></TD>
						<TD><h:outputText  value="40"/></TD>
						</tr>	
<tr></tr>		
				<tr>
						<TD><a href="SearchResultsBC.html" ><h:outputText id="BenefitComponent" value="BenefitComponent"/></a></TD><TD></TD>
						<TD><h:outputText  value="40"/></TD>
						</tr>	
		<tr></tr>				
						<tr>
						<TD><a href="SearchResults1.html" ><h:outputText id="Benefit" value="Benefit"/></a></TD><TD></TD>
						<TD><h:outputText  value="40"/></TD>
						</tr>	
						
						<tr></tr><tr></tr>
						<TR>
						<TD width="149">&nbsp;<h:commandButton value=" Refine Criteria "
										styleClass="wpdButton">
									</h:commandButton></TD>

								</TR>


						</TABLE>
					
					</td>
				
				</tr>
				</table>				
								

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
	
	copyHiddenToDiv('benefitComponentCreateForm:lobTxtHidden','lobDiv');
	copyHiddenToDiv('benefitComponentCreateForm:businessEntityTxtHidden','businessEntityDiv');
	copyHiddenToDiv('benefitComponentCreateForm:businessGroupTxtHidden','businessGroupDiv');	

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponent" /></form>
</HTML>
