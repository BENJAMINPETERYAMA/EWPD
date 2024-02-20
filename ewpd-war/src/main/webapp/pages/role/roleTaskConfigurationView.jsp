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
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
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
<BODY >
	<table width="100%" cellpadding="0" cellspacing="0">
		 <h:inputHidden id="dummy" value="#{roleBackingBean.viewBreadCrumbText}" />
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="roleTaskConfigurationViewForm">
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								<DIV class="treeDiv"><jsp:include
							page="../role/roleConfigTree.jsp" /></DIV>	
<!-- Space for Tree  Data	-->					


							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
											 <w:message value="#{roleBackingBean.validationMessages}"></w:message>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Authorized Tasks" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="100%"></TD>
								</TR>
							</TABLE>
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">
									
<!--	Start of Table for actual Data	-->		
				
							<h:outputText value="No Tasks Available."
							rendered="#{roleBackingBean.associatedTaskList == null}"
							styleClass="dataTableColumnHeader"/>
						<DIV id="panel2">
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="99%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD width="35%"><h:outputText
													value="Tasks"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td width="672"><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{roleBackingBean.associatedTaskList != null}"
										value="#{roleBackingBean.associatedTaskList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="99%">
										
										<h:column>
											<h:outputText id="taskName" value="#{singleValue.taskName}"></h:outputText>
											<h:inputHidden id="taskID" value="#{singleValue.taskId}"></h:inputHidden>
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
											<f:verbatim>&nbsp;&nbsp;</f:verbatim>
											
										</h:column>
									</h:dataTable></DIV>
									</td>
						
								</TR>
								<TR>
								<TD>
									</TD>
								</TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->

<!-- End of hidden fields  -->
					
				
				
			</fieldset></TD>
		</tr>
		</table>
		
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>

	</h:form>
</td></tr></table></BODY>
</f:view>
<!-- space for script -->

<script>
		
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	function initialize(){
		if(document.getElementById('roleTaskConfigurationViewForm:searchResultTable') != null) {

			setColumnWidth('resultHeaderTable','35%:35%:30%');		
			setColumnWidth('roleTaskConfigurationViewForm:searchResultTable','35%:35%:30%');		
		}else{
			document.getElementById('panel2').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="roleTaskConfigInfo" /></form>
	<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>












