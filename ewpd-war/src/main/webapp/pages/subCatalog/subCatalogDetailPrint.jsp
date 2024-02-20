<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Print Sub-Catalog Detail</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="20">
		<tr>
			<td><h:form styleClass="form" id="subCatalogPrintForm">
				<h:inputHidden id="viewSubCatalogId"
					value="#{subCatalogBackingBean.viewSubCatalogId}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
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
						<TR>

							<TD colspan=3>
							<div id="subCatalogGenInfo">
							<FIELDSET style="width:70%">


							<TABLE width="60%">
								<TR>
									<TD width="45%"><h:outputText value="Line Of Business" /></TD>
									<TD width="50%"><h:outputText id="txtLob"
										value="#{subCatalogBackingBean.lob}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Business Entity" /></TD>
									<TD width="50%"><h:outputText id="txtBusinessEntity"
										value="#{subCatalogBackingBean.businessEntity}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Business Group" /></TD>
									<TD width="50%"><h:outputText id="txtBusinessGroup"
										value="#{subCatalogBackingBean.businessGroup}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Market Business Unit" /></TD>
									<TD width="50%"><h:outputText id="txtMarketBusinessUnit"
										value="#{subCatalogBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Name" /></TD>
									<TD width="50%"><h:outputText id="txtSubCatalogName"
										value="#{subCatalogBackingBean.subCatalogName}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><h:outputText value="Parent Catalog" /></TD>
									<TD width="50%"><h:outputText id="txtParentCatalogName"
										value="#{subCatalogBackingBean.parentCatalog}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%" valign="top"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="50%"><h:outputText id="txtDescription"
										value="#{subCatalogBackingBean.description}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></TD>
									<TD width="50%"><h:outputText id="createdUserView"
										value="#{subCatalogBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="50%"><h:outputText id="createdDateView"
										value="#{subCatalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="50%"><h:outputText id="updatedUserView"
										value="#{subCatalogBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="50%"><h:outputText id="updatedTimeView"
										value="#{subCatalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
							</TABLE>

							</FIELDSET>

							<FIELDSET style="width:70%"><h:outputText
								value="No Items Available."
								rendered="#{subCatalogBackingBean.printSearchResultList == null}" />
							<TABLE border="0" cellspacing="1" cellpadding="3"
								class="outputText">
								<TBODY>


									<TR valign="top">
										<TD width="100%">
										<DIV id="panel2">
										<DIV id="panel2Header" class="tabTitleBar"
											style="position:relative; cursor:hand;">SubCatalog Associated
										Items</DIV>
										<DIV id="panel2Content" class="tabContentBox"
											style="position:relative;font-size:10px;"><BR>
										<table cellpadding="0" cellspacing="0" width="100%" border="0">
											<h:dataTable headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												cellspacing="1"
												rendered="#{subCatalogBackingBean.printSearchResultList != null}"
												value="#{subCatalogBackingBean.printSearchResultList}"
												border="0" width="100%">
												<h:column>
													<h:outputText id="itName"
														value="#{singleValue.description}"></h:outputText>

												</h:column>
											</h:dataTable>

										</table>
										</div>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</td>
						</tr>
						<tr>
							<td></h:form></td>
						</tr>
				</table>
	</BODY>
</f:view>
<script>
initialize();
		function initialize(){
			if(document.getElementById('subCatalogPrintForm:searchResultTable') == null) 
				
				document.getElementById("panel2").style.visibility = 'hidden';
			}
	
formatTildaToComma("subCatalogPrintForm:txtLob");
formatTildaToComma("subCatalogPrintForm:txtBusinessEntity");
formatTildaToComma("subCatalogPrintForm:txtBusinessGroup");
formatTildaToComma("subCatalogPrintForm:txtMarketBusinessUnit");
var printForGenInfo = document.getElementById("subCatalogPrintForm:viewSubCatalogId").value;
if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('associatedItemPrint');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
</script>
<script>window.print();</script>
</HTML>
