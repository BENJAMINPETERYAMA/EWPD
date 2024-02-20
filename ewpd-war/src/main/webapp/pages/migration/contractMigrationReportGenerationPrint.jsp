<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/migration/ContractMigrationReportGenerationPrint.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>WellPoint Product Database: Migration Report Generation</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
<script language="JavaScript" src="../../js/date.js" ></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
<BODY>
<hx:scriptCollector id="scriptCollector1">
	<h:form styleClass="form" id="generateReportForm">

	<h:inputHidden id="hidden1" value="#{migrationReportBackingBean.unmappedVariableList}"></h:inputHidden>
	<TABLE width="110%" cellpadding="0" cellspacing="0">
		<%--
		<TR>
			<TD>
				<jsp:include page="../navigation/top_migration.jsp"></jsp:include>
			</TD>
		</TR>
		--%>
			<TR>
				<TD>
					&nbsp;<w:message/>
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:10px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:85%">
						<h:outputText id="breadcrumb" 
							value="#{migrationReportBackingBean.printBreadCrumbText}">
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
				<TD id="contractMigrationReport">
					<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:85%">

					
			
<!-- Table containing Tabs -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
						<tr>
											<td width="100%"><b>Step 9 : Report Generation </b> </td>
										</tr>
										<tr>
											 <td>This page will list all coded variables in the selected legacy contract that is not yet mapped to any benefit line in the ET Auto Bagging Product.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

					</table>
<!-- End of Tab table -->
<!--	Start of Table for actual Data	-->		
<!--
				<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
-->
					<div id="panel2">
						<div id="resultInfo" class="dataTableColumnHeader">
						<br/>No Unmapped Variable found.
					</div>
					<br>
					<div id="resultHeaderDiv">
						<div id="resultHeaderTableInfo" style="width:100%">
							<b>Unmapped Variables </b>
						</div>
						<br>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0" id="resultHeaderTable" border="0" width="100%">
							<TBODY>

								<TR class="dataTableColumnHeader">
									<td align="center" >
												<h:outputText styleClass="formOutputColumnField" value="Major Heading" ></h:outputText> 
									</td>
									<td  align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Minor Heading" ></h:outputText>
									</td >
									<td align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Variable"></h:outputText>
									</td>
									<td align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Description"></h:outputText>
									</td>
									<td align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="PVA"></h:outputText>
									</td>
									<td align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Format"></h:outputText>
									</td>
									<td align="center" width="209">
	              								<h:outputText styleClass="formOutputColumnField" value="Value"></h:outputText>
									</td>
								</TR>
							</TBODY>
						</TABLE>
							
					<div id="panel2Content" style="position:relative;z-index:1;font-size:10px;overflow:auto;width:100%;">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
					<td align="left">
							
							<h:dataTable headerClass="tableHeader" id="resultsTable" border="0"
								value="#{migrationReportBackingBean.unmappedVariableList}"
								rendered="#{migrationReportBackingBean.renderFlag}" var="eachRow" 
								 cellpadding="0" cellspacing="1">
								
								<h:column>
									<h:outputText id="majorHeading"  styleClass="formOutputColumnField" value="#{eachRow.majorHeading}"></h:outputText>
									<h:inputHidden id="majorHeadingID" value="#{eachRow.majorHeading}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="minorHeading" styleClass="formOutputColumnField" value="#{eachRow.minorHeading}"></h:outputText>
									<h:inputHidden id="minorHeadingID" value="#{eachRow.minorHeading}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="variable" styleClass="formOutputColumnField" value="#{eachRow.contractVariable}"></h:outputText>
									<h:inputHidden id="variableID" value="#{eachRow.contractVariable}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="description" styleClass="formOutputColumnField" value="#{eachRow.contractVariableText}"></h:outputText>
									<h:inputHidden id="descriptionHidden" value="#{eachRow.contractVariableText}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="pva" styleClass="formOutputColumnField" value="#{eachRow.providerArrangement}"></h:outputText>
									<h:inputHidden id="pvaHidden" value="#{eachRow.providerArrangement}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="format" styleClass="formOutputColumnField" value="#{eachRow.contractVariableFormat}"></h:outputText>
									<h:inputHidden id="formatHidden" value="#{eachRow.contractVariableFormat}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="value" styleClass="formOutputColumnField" value="#{eachRow.contractVariableValue}"></h:outputText>
									<h:inputHidden id="valueHidden" value="#{eachRow.contractVariableValue}"></h:inputHidden>
								</h:column>
							</h:dataTable>
						</td>
					</tr>
					</table>

					</div>
					</div>
					</div>
					<br>
					<br>
					<div id="conflictDiv">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center">
						<tr>
							<td>
								<div id="resultHeaderTableInfo2" style="width:100%">
									<b>Conflicting Mappings</b>
								</div>
							</td>
						</tr>
						<tr>
							<td>

							</td>
						</tr>
						<tr>
						<td align="center">
								<table id="headTable2" cellpadding="0" cellspacing="1" bgcolor="#cccccc" border="0">
									<tr class="tableHeader">
										<td>
											<h:outputText styleClass="formOutputColumnField" value="Benefit Component" ></h:outputText> 
										</td>
										<td>
											<h:outputText styleClass="formOutputColumnField" value="Standard Benefit" />
										</td>
										<td>
											<h:outputText styleClass="formOutputColumnField" value="Line Description"></h:outputText>
										</td>
										<td>
											<h:outputText styleClass="formOutputColumnField" value="PVA"/>
										</td>
										<td>
											<h:outputText styleClass="formOutputColumnField" value="Variable"/>
										</td>
										<td>
											<h:outputText styleClass="formOutputColumnField" value="Master Mapping"/>
										</td>
									</tr>
									<tr>
								</table>
								<h:dataTable headerClass="tableHeader" id="resultsTable2" border="0" width="100%"
									value="#{migrationReportBackingBean.conflictingLines}"
									rendered="#{migrationReportBackingBean.conflictingLines != null}" var="eachRow" 
									 cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
									rowClasses="dataTableEvenRow,dataTableOddRow">
									
									<h:column>
										<h:outputText id="benCompName"  styleClass="formOutputColumnField" value="#{eachRow.benCompName}"></h:outputText>
									</h:column>
									<h:column>

										<h:outputText id="benefitName" styleClass="formOutputColumnField" value="#{eachRow.benefitName}"></h:outputText>
									</h:column>
									<h:column>

										<h:outputText id="lineDesc" styleClass="formOutputColumnField" value="#{eachRow.lineDesc}"></h:outputText>
									</h:column>
									<h:column>

										<h:outputText id="lineProvArr" styleClass="formOutputColumnField" value="#{eachRow.lineProvArr}"></h:outputText>
									</h:column>
									<h:column>

										<h:outputText id="variableId" styleClass="formOutputColumnField" value="#{eachRow.variableId}"></h:outputText>
									</h:column>
									<h:column>

										<h:outputText id="masterMappingVariable" styleClass="formOutputColumnField" value="#{eachRow.masterMappingVariable}"></h:outputText>
									</h:column>
								</h:dataTable>
							</td>
						</tr>
						</table>
					</div>
					<br>
				<br/>

			</FIELDSET>
			</TD>
		</TR>
 	</TABLE>
		</h:form>

</hx:scriptCollector></body>
</f:view>

<script language="JavaScript">
	hideResultDiv();

	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');
		var divNoResult = document.getElementById('panel2');
		var tableObj = document.getElementById('generateReportForm:resultsTable');
		if (tableObj != null) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
				
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
	}
		if(document.getElementById('generateReportForm:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "100%";
			document.getElementById('generateReportForm:resultsTable').width = "100%";
/*
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('generateReportForm:resultsTable').offsetHeight >= 106) {

				document.getElementById('generateReportForm:resultsTable').width =(relTblWidth-18)+"px";
				syncTables('resultHeaderTable','generateReportForm:resultsTable');	
				setColumnWidth('generateReportForm:resultsTable','19%:21.5%:16%:22%:7%:9.5%:7%');
				setColumnWidth('resultHeaderTable','19%:21.5%:16%:22%:7%:9.5%:7%');
			}else{
				document.getElementById('generateReportForm:resultsTable').width = (relTblWidth)+"px";
*/
				setColumnWidth('generateReportForm:resultsTable','20%:20%:17.5%:22%:7%:9.5%:7%');
				setColumnWidth('resultHeaderTable','20%:20%:17.5%:22%:7%:9.5%:7%');
//			}
		}
		if(document.getElementById('generateReportForm:resultsTable2') != null) {

			document.getElementById('headTable2').width = "100%";
			setColumnWidth('headTable2','20%:20%:20%:8%:17%:15%');

			document.getElementById('generateReportForm:resultsTable2').width = "100%";
			setColumnWidth('generateReportForm:resultsTable2','20%:20%:20%:8%:17%:15%');
		} else {
			if(getObj('conflictDiv') != null) 
				getObj('conflictDiv').style.display = 'none';
		}

</script>
<script>	
window.print();		
</script>
</HTML>

