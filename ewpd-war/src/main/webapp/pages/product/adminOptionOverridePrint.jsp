
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
<base target=_self>
<TITLE>Admin Options</TITLE>
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
		<h:form styleClass="form" id="adminOptionForm">
			<TABLE width="100%">
				<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:4px;margin-right:-1px;padding-bottom:1px;padding-top:1px;width:100%">
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
		</TABLE>
			<h:inputHidden value="#{adminOptionBackingBean.loadProductAdminOptions}"/>
							<TD colspan="2" valign="top" class="ContentArea">
			<br>
					
							
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<div id="adminDiv" align="center" ><h:outputText rendered="#{adminOptionBackingBean.adminOptionListForPrint == null}"
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader" /></div>
							<DIV id="searchResultdataTableDiv" >
										 
								<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><h:outputText value="Associated Admin Options"></h:outputText>
											</TD>
										</TR>
									</TABLE>
							
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
										
											<TR class="dataTableColumnHeader">
											<div id="noAdminOptionDiv">
												<TD align="left" width="35%"><h:outputText value="Name "></h:outputText>
												</TD>
												<TD align="left" width="35%"><h:outputText
													value="Admin Level "></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText
													value="Benefit Level "></h:outputText></TD>
											<div>	
											</TR>
										
										</TBODY>
									</TABLE>

									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
										<div id="paneltable" >
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" 
										width="100%" cellspacing="1"  cellpadding="3"
										rendered="#{adminOptionBackingBean.adminOptionListForPrint != null}"
										value="#{adminOptionBackingBean.adminOptionListForPrint}"
										border="0">
										
											<h:column>
												<h:inputHidden id="adminOptionIdHidden"
													value="#{singleValue.adminLevelOptionAssnSystemId}"></h:inputHidden>
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
       									
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
						</DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						
					

					<!-- Space for hidden fields -->						
					<!-- End of hidden fields  -->

				</h:form>
			
				
	
	</BODY>
</f:view>
<script language="JavaScript">

initialize();
		function initialize(){
			if(document.getElementById('adminOptionForm:searchResultTable').rows.length != 0) {
//alert("12324");
				setColumnWidth('adminOptionForm:searchResultTable','35%:35%:30%');

			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			}
		}


window.print();
	
</script>

