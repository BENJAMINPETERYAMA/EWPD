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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 20%;
}
.shortDescriptionColumn {
	width: 80%
}
</style>	
<TITLE>Benefit Component Hierarchies Print</TITLE>
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
	<h:form styleClass="form" id="BenefitComponentHierarchiesPrintForm">
		<h:inputHidden id="init"
			value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchiesForPrint}"></h:inputHidden>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD>
						<FIELDSET
							style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
					 	<h:outputText id="breadcrumb"
							value="#{BenefitComponentHierarchiesBackingBean.printBreadCrumbText}">
						</h:outputText></FIELDSET>
						</TD>
					</TR>
					<TR>
						<TD>&nbsp;</TD>
					</TR>

					<TR>

						<TD colspan="2" valign="top" class="ContentArea">

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:0px;width:100%">

						<!--	Start of Table for actual Data	--> <BR />
						<div id="emptymsg"><h:outputText value="No benefits Available."
							styleClass="dataTableColumnHeader" /></div>

						<TABLE width="100%" cellpadding="0" cellspacing="0">


							<tr>
								<td>

								<div id="resultHeaderDiv" style="width:100%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<tr>
										<TD><b><h:outputText value="Associated Benefits"></h:outputText></b>
										</TD>
									</tr>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="20%"><b><h:outputText
												value="Sequence"></h:outputText></b></td>
											<td align="left" width="80%"><b><h:outputText
												value="Benefit"></h:outputText></b></td>

										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>

							<TR valign="top">
								<TD width="100%">
								<h:dataTable headerClass="dataTableHeader"
									id="benefitHierarchiesTable" var="singleValue" cellpadding="3"
									cellspacing="1"
									value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchies}"
									border="0" width="100%" columnClasses="selectOrOptionColumn,shortDescriptionColumn">
									<h:column>
										<h:inputHidden id="benefitIdHidden"
											value="#{singleValue.sequenceNumber}"></h:inputHidden>
										<h:outputText id="benefitId"
											value="#{singleValue.sequenceNumber}"></h:outputText>
									
									</h:column>
									<h:column>
										<h:outputText id="benefitName"
											value="#{singleValue.benefitName}"></h:outputText>
										
									</h:column>
								</h:dataTable></TD>
							</TR>

						</TABLE>
						<!--	End of Page data	-->
						</fieldset>
						
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="4%"></td>
								<td align="left" width="71%"></td>
								<td align="left" width="25%">
								<table Width=100%>
									<tr>
										<td width="2%"><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentHierarchiesBackingBean.state}" /></td>
										
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentHierarchiesBackingBean.status}" /></td>
										
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentHierarchiesBackingBean.version}" /></td>
										
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>

						</TD>
					</TR>

				</table>


				</h:form>
	</BODY>
</f:view>

<script>
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('BenefitComponentHierarchiesPrintForm:benefitHierarchiesTable');		
		if(hiddenIdObj.rows.length == 0){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
	 		setColumnWidth('BenefitComponentHierarchiesPrintForm:benefitHierarchiesTable','20%:80%');
		}
	}	

</script>

<script>
window.print();

</script>

</HTML>

