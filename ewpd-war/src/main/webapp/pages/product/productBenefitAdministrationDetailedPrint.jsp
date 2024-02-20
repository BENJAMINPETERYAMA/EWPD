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
<TITLE>Print Benefit Administration</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="benefitAdministrationDetailedPrintForm">
		
		<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:5px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
						<h:outputText id="breadcrumb" 
							value="#{productAdminOptionBackingBean.printBreadCrumbText}">
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
				<TD>
				
				<STRONG> <br> <h:outputText value=" Product : " /><h:outputText
								id="txtLabel"
								value=" #{adminOptionBackingBean.breadCrumbName}"></h:outputText><br> </STRONG>
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
				<div id="adminDiv" align="center" ><h:outputText rendered="#{adminOptionBackingBean.adminOptionListForPrint == null}"
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader" /></div>
				<DIV id="searchResultdataTableDiv" >
					<!--	Start of Table for actual Data	--> <BR />
					
					<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" >
						<tr>
							<td>

							<div id="HeaderDiv" style="width:100%;">
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<tr>
									<TD><h:outputText value="Associated Admin Options"
										style="font-weight: bold"></h:outputText></TD>
								</tr>
							</TABLE>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>
									<TR class="dataTableColumnHeader">
										<td align="left" width="35%"><b><h:outputText value="Name"></h:outputText></b>
										</td>
										<td align="left" width="35%"><b><h:outputText
											value="Admin Level "></h:outputText></b></td>
											<td align="left" width="30%"><b><h:outputText
											value="Benefit Level "></h:outputText></b></td>

									</TR>
								</TBODY>
							</TABLE> 
							</div>
							</td>
						</tr>
					<h:inputHidden value="#{adminOptionBackingBean.loadProductAdminOptions}"/>
						<TR valign="top">
							<TD width="100%">
							<div id="searchResultdataTableDiv"><h:dataTable
								headerClass="dataTableHeader" id="searchResultTable"
								var="singleValue" cellpadding="3" cellspacing="1"
								
								rendered="#{adminOptionBackingBean.adminOptionListForPrint != null }"
								value="#{adminOptionBackingBean.adminOptionListForPrint}"
								border="0"
								width="100%">
								<h:column>
									<h:inputHidden id="adminOptionIdHidden"
												value="#{singleValue.benefitAdminSystemId}"></h:inputHidden>
											<h:outputText id="adminOptionName"
												value="#{singleValue.adminOptionDesc}">
									</h:outputText>

								
							</h:column>
										<h:column>
											<h:outputText id="adminLevelName"
												value="#{singleValue.adminLevelDesc}">
											</h:outputText>

								
							</h:column>
										<h:column>
											<h:inputHidden id="benefitLevelIdOnPageLoad"
												value="#{singleValue.benefitLevelSysIdFromMaster}" />
											<h:outputText id="benefitLevelName"
												value="#{singleValue.benefitLevelDesc}">
											</h:outputText>
								
							</h:column>
							</h:dataTable></div>

							</TD>
						</TR>

					</TABLE>
				</div>
			</fieldset>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<!--  Admin Process -->
			<TR>
				<TD>
				
				<h:inputHidden id="pageLoad" value="#{adminMethodBackingBean.valuesFromSessionForProd}"></h:inputHidden>	
				
				<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<div id="messageDiv" align="center"><STRONG><h:outputText rendered="#{adminMethodBackingBean.adminMethodsList == null}"
										value="No Admin Methods Available."
										styleClass="dataTableColumnHeader" /></STRONG></div>
									<DIV id="searchResultdataTableDivForMethod" >
					<!--	Start of Table for actual Data	--> <BR />
					
					<TABLE width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>

							<div id="HeaderDiv" style="width:100%;">
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<tr>
									<TD><h:outputText value="Processing Methods"
										style="font-weight: bold"></h:outputText></TD>
								</tr>
							</TABLE>
							 <TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>
									<TR class="dataTableColumnHeader">
										<td align="left" width="35%"><b><h:outputText value=" "></h:outputText></b>
										</td>
										<td align="left" width="35%"><b><h:outputText
											value="Admin Method "></h:outputText></b></td>
											<td align="left" width="30%"><b><h:outputText
											value="Reference "></h:outputText></b></td>

									</TR>
								</TBODY>
							</TABLE> 
							</div>
							</td>
						</tr>

						<TR valign="top">
							<TD width="100%">
							<div id="dataTableForMethod"><h:dataTable
								headerClass="dataTableHeader" id="searchResultTableForMethod"
								var="singleValue" cellpadding="3" cellspacing="1"
								
								rendered="#{adminMethodBackingBean.adminMethodsList != null }"
								value="#{adminMethodBackingBean.adminMethodsList}"
								border="0"
								width="100%">
								<h:column>
									<h:outputText id="spsname"
												value="#{singleValue.spsName}">
											</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="adminMethod"
											value="#{singleValue.adminMethodDesc}">
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="referenceId"
											value="#{singleValue.reference}">
										</h:outputText>
									</h:column>
							</h:dataTable></div>

							</TD>
						</TR>

					</TABLE>
				</div>
			<!-- End ofAdmin Process -->
			<!--	End of Page data	-->


		</TABLE>

	</h:form>
	</BODY>
</f:view>
<script language="JavaScript">

initialize();
		function initialize(){
				if(document.getElementById('benefitAdministrationDetailedPrintForm:searchResultTable') != null) {
					if(document.getElementById('benefitAdministrationDetailedPrintForm:searchResultTable').rows.length != 0){
				setColumnWidth('benefitAdministrationDetailedPrintForm:searchResultTable','35%:35%:30%');
				document.getElementById('adminDiv').style.visibility = 'hidden';
				}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				}
			}
			if(document.getElementById('benefitAdministrationDetailedPrintForm:searchResultTableForMethod')!= null){		
				setColumnWidth('benefitAdministrationDetailedPrintForm:searchResultTableForMethod', '35%:35%:30%');
			}else{
			document.getElementById('searchResultdataTableDivForMethod').style.visibility = 'hidden';
			}
		}	


</script>

<script>
	window.print();	

	
</script>

</HTML>




