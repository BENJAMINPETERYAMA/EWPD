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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">
.selectmoduleIDColumn {
	width: 35%;
	
}
</style>
<BASE target="_self" />
<TITLE>Role Configuration View</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<h:inputHidden id="dummy"
			value="#{roleBackingBean.viewBreadCrumbText}" />
		<TR>
			<TD><jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="roleConfigurationViewForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include
							page="../role/roleConfigTree.jsp" /></DIV>
						<!-- Space for Tree  Data	--></TD>


						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- <w:message value="#{roleBackingBean.validationMessages}"></w:message> -->
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
													src="../../images/tabNormalLeft.gif" width="2"
													alt="Tab Left Active" height="21" /></td>
												<td width="186" class="tabNormal"><h:commandLink
													action="#{roleBackingBean.loadGeneralInformationView}">
													<h:outputText value=" General Information" />
												</h:commandLink></td>
												<td width="2" align="right"><img
													src="../../images/tabNormalRight.gif" width="2"
													alt="Tab Right Active" height="21" /></td>
											</tr>
										</table>
										</td>
										<td width="200">
										<table width="200" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td align="left" width="2"><img
													src="../../images/activetabLeft.gif" width="2" height="21" /></td>
												<td class="tabActive" width="186"><h:outputText
													value="Authorized Modules" /></td>
												<td width="2" align="right"><img
													src="../../images/activeTabRight.gif" width="2" height="21" /></td>
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

						<!--	Start of Table for actual Data	--> <!--	End of Page data	-->
						<h:outputText value="No Modules Available."
							rendered="#{roleBackingBean.searchResultList == null}"
							styleClass="dataTableColumnHeader" />
						<DIV id="panel2">
						<DIV id="panel2Content" class="tabContentBox"
							style="position:relative;font-size:10px;"><BR>
						<BR />
						<TABLE width="99%" cellpadding="0" cellspacing="0">
							<tr>
								<td>

								<div id="resultHeaderDiv" style="width:100%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="99%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="35%"><b><h:outputText value="Module"></h:outputText></b>
											</td>

										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>

							<TR valign="top">
								<TD width="100%">
								<div id="searchResultdataTableDiv"
									style="height:252px; overflow:auto; width:99%;"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{roleBackingBean.searchResultList != null}"
									value="#{roleBackingBean.searchResultList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectmoduleIDColumn" border="0"
									width="100%">
									<h:column>
										<h:outputText id="moduleName"
											value="#{singleValue.moduleName}"></h:outputText>
										<h:inputHidden id="moduleID" value="#{singleValue.moduleId}"></h:inputHidden>
									
									</h:column>
								</h:dataTable></div>

								</TD>
							</TR>

						</TABLE>
						</fieldset>
						<!-- Space for hidden fields --> <!-- End of hidden fields  --> 
				</table>
				</h:form>
						</TD>
					</tr>
				<TR>
					<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
				</TR>
				</table>
	</BODY>
</f:view>
<!-- space for script -->

<script>
	
	//copyHiddenToDiv_ewpd('roleConfigurationForm:Prm','itemDiv',2,2 );
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('roleConfigurationViewForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','35%:35%:30%');		
			setColumnWidth('roleConfigurationViewForm:searchResultTable','35%:35%:30%');		
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="roleDetailPrint" /></form>
	<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>












