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

<TITLE>Sub-Catalog View</TITLE>
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
	<h:form styleClass="form" id="subCatalogViewForm">
		<h:inputHidden id="viewSelectedSubCatalogId"
			value="#{subCatalogBackingBean.viewSubCatalogId}" />
		<table width="100%" cellpadding="0" cellspacing="0">

			<TR>
				<TD><jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
				</TD>
			</TR>
			<tr>
				<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="150" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>

						</TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{subCatalogBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="25%">
								<table width="112%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="200">
										<table width="200" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="2" align="left"><img
													src="../../images/activeTabLeft.gif" width="3"
													alt="Tab Left Active" height="21" /></td>
												<td width="186" class="tabActive"><h:outputText
													value=" General Information" /></td>
												<td width="2" align="right"><img
													src="../../images/activeTabRight.gif" width="2"
													alt="Tab Right Active" height="21" /></td>
											</tr>
										</table>
										</td>
										<td width="200">
										<table width="200" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="2" align="left"><img
													src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
												<td width="186" class="tabNormal"><h:commandLink
													action="#{subCatalogBackingBean.loadItem}">
													<h:outputText value="Item" />
												</h:commandLink></td>
												<td width="2" align="right"><img
													src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
											</tr>
										</table>
										</td>
										<td width="100%"></td>

									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width="100%">
							<TBODY>
								<TR>
									<TD colspan=3>
									<FIELDSET style=""><LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
									<TABLE>
										<tr>
											<TD width="194"></TD>
										</tr>
										<TR>
											<TD width="25%"><h:outputText value="Line of Business" /></TD>
											<TD width="36%">
											<DIV id="lobDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtLob"
												value="#{subCatalogBackingBean.lob}"></h:inputHidden></TD>
											<TD width="300"></TD>
										</TR>
										<TR>
											<TD width="25%"><h:outputText value="Business Entity" /></TD>
											<TD width="36%">
											<DIV id="BusinessEntityDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessEntity"
												value="#{subCatalogBackingBean.businessEntity}"></h:inputHidden>

											</TD>
											<TD width="300"></TD>
										</TR>
										<TR>
											<TD width="25%"><h:outputText value="Business Group" /></TD>
											<TD width="36%">
											<DIV id="BusinessGroupDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtBusinessGroup"
												value="#{subCatalogBackingBean.businessGroup}"></h:inputHidden>

											</TD>
											<TD width="300"></TD>
										</TR>
<!-- CARS START -->						
										<TR>
											<TD width="25%"><h:outputText value="Market Business Unit" /></TD>
											<TD width="36%">
											<DIV id="marketBusinessUnitDiv" class="selectDivReadOnly"></DIV>
											<h:inputHidden id="txtMarketBusinessUnit"
												value="#{subCatalogBackingBean.marketBusinessUnit}"></h:inputHidden>

											</TD>
											<TD width="300"></TD>
										</TR>
<!-- CARS END -->
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{subCatalogBackingBean.requiredSubCatalogName ? 'mandatoryError': 'mandatoryNormal'}"
										id="subCatalogStar" value="Name " /></TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.subCatalogName}" /></TD>
									<TD width="8%"></TD>
								</TR>
								<TR>
									<TD width="30%">&nbsp;<h:outputText
										styleClass="#{subCatalogBackingBean.requiredParentCatalogId ? 'mandatoryError': 'mandatoryNormal'}"
										id="parentCatalogStar" value="Parent Catalog " /></TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.parentCatalog}" /></TD>
									<TD width="55%"></TD>
								</TR>


								<TR>
									<TD width="30%" valign="top">&nbsp;<h:outputText
										id="descriptionStar" value="Description " /></TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.description}" /></TD>
									<TD width="55%"></TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Created By" /></TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.createdUser}" /></TD>
									<TD width="55%"></TD>
								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Created Date" /></TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
									<TD width="55%"></TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Last Updated By" />
									</TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.lastUpdatedUser}" /></TD>
									<TD width="55%"></TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText value="Last Updated Date" />
									</TD>
									<TD align="left" width="66%"><h:outputText
										value="#{subCatalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
									<TD width="55%"></TD>
								</TR>





							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <!-- End of hidden fields  --> <h:inputHidden
					id="subCatalogId" value="#{subCatalogBackingBean.subCatalogId}"></h:inputHidden>
				<h:inputHidden id="subCatalogParentId"
					value="#{subCatalogBackingBean.subCatalogParentId}"></h:inputHidden>
				<h:inputHidden id="subCatalogName"
					value="#{subCatalogBackingBean.subCatalogName}"></h:inputHidden> 
				</TD>
			</tr>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</table>
		</h:form>
	</BODY>
</f:view>
<!-- space for script -->
<script>
	copyHiddenToDiv_ewpd('subCatalogViewForm:txtLob','lobDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogViewForm:txtBusinessEntity','BusinessEntityDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogViewForm:txtBusinessGroup','BusinessGroupDiv',2,2); 
	copyHiddenToDiv_ewpd('subCatalogViewForm:txtMarketBusinessUnit','marketBusinessUnitDiv',2,2); 
    
	</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="subCatalogGenInfo" /></form>

</HTML>
