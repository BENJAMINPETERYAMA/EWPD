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
<META http-equiv="Content-Style-Type" content="text/css">
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
<TITLE>ProductRuleValidation</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
</HEAD>
<f:view>
	<BODY>
<h:form styleClass="form" id="formName">
		<h:inputHidden id="timeId"
					value="#{productGeneralInformationBackingBean.loadRuleValidationPopUp}"></h:inputHidden>
<table width = '100%'>
	<tr>
		<td align="right">
			<a href="#">
				<img src="../../images/print.gif" alt="Print" width="19" height="14" border="0"
					onclick="printSelection();return false;" />
			 </a>
		</td>
	</tr>
</table>
					   <TABLE>
							<TBODY>
								<tr>
									<TD>&nbsp;</TD>
								</tr>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>

								<TR>
									<TD>
										<div class='errorDiv' >
											<li id=errorItem>Rule Validation.</li>
										</div>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
		<table id="headerTable" width="98%"  cellpadding="1" cellspacing="1" border="0" align="center">
			<tr>
				<td width="15%">
					<h:outputText value="Product Name "/>
				</td>
				<td>
					<h:outputText value=":"/>
				</td>
				<td >
					<h:outputText value="#{productGeneralInformationBackingBean.productName}"/>
				</td>
				<td width="25%">
						<SPAN id="timeID">time</SPAN>
				</td>
			</tr>
			<tr>
				<td width="15%">
					<h:outputText value="Version"/>
				</td>
				<td>
					<h:outputText value=":"/>
				</td>	
				<td>
					<h:outputText value="#{productGeneralInformationBackingBean.version}"/>
				</td>		
				<td>
				</td>
			</tr>			
		</table>


		
								
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><strong><h:outputText value="Rule-Id in Deleted Status"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left"><h:outputText value="eWPD Rule Id"></h:outputText></TD>
											<TD align="left"><h:outputText value="Rule Id"></h:outputText></TD>
											<TD align="left"><h:outputText value="Description"></h:outputText></TD>
											<TD align="left"><h:outputText value="PVA"></h:outputText></TD>
											<TD align="left"><h:outputText value="Rule Type"></h:outputText></TD>
									</TR>
									</TBODY>
								</TABLE>
								</DIV>
								
						
								
								<DIV id="searchResultdataTableDiv"
									style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{productGeneralInformationBackingBean.deletedRulesList != null}"
									value="#{productGeneralInformationBackingBean.deletedRulesList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

									<h:column>
										<h:outputText id="ewpdRuleId" value="#{singleValue.genRuleID}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="ruleId" value="#{singleValue.ruleID}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="ruleDesc" value="#{singleValue.ruleDescription}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="pva" value="#{singleValue.providerArrangement}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="ruleType" value="#{singleValue.ruleType}"></h:outputText>
									</h:column>								
								</h:dataTable>
						</DIV>
								
				<DIV id="resultHeaderUnCodedDiv">
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="unCodedHeaderTable" border="0" width="100%">
									<TR>
										<TD><strong><h:outputText value="Value need to be coded"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultUnCodedHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left"><h:outputText value="eWPD Rule Id"></h:outputText></TD>
											<TD align="left"><h:outputText value="Rule Id"></h:outputText></TD>
											<TD align="left"><h:outputText value="Description"></h:outputText></TD>
											<TD align="left"><h:outputText value="PVA"></h:outputText></TD>
											<TD align="left"><h:outputText value="Rule Type"></h:outputText></TD>
									</TR>
									</TBODY>
								</TABLE>
								
								</TD>
							</TR>
							<TR>
								<td colspan="4">
								<DIV id="searchResultdataTableUnCodedDiv"
									style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultUnCodedTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{productGeneralInformationBackingBean.unCodedRulesList != null}"
									value="#{productGeneralInformationBackingBean.unCodedRulesList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

									<h:column>
										<h:outputText id="unCodedEwpdRuleId" value="#{singleValue.genRuleID}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="unCodedRuleId" value="#{singleValue.ruleID}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="unCodedRuleDesc" value="#{singleValue.ruleDescription}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="unCodedPva" value="#{singleValue.providerArrangement}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="unCodedRuleType" value="#{singleValue.ruleType}"></h:outputText>
									</h:column>								
								</h:dataTable></DIV> 
								</TD>
							</TR>
						</TABLE>
</DIV>
<!-- Space for hidden fields -->

</h:form>
	</BODY>

</f:view>

<script>
if(null != document.getElementById('formName:searchResultTable'))
	setColumnWidth('formName:searchResultTable','20%:20%:40%:8%:12%');
setColumnWidth('resultHeaderTable','20%:20%:40%:8%:12%');
if(null != document.getElementById('formName:searchResultUnCodedTable'))
	setColumnWidth('formName:searchResultUnCodedTable','20%:20%:40%:8%:12%');
setColumnWidth('resultUnCodedHeaderTable','20%:20%:40%:8%:12%');
	var tableObject = document.getElementById('formName:searchResultTable');
	if(null != tableObject ){
		var tablesize = tableObject.rows.length;
		if(tablesize == 0)
		{
			document.getElementById('resultHeaderDiv').style.display = 'none';
			document.getElementById('searchResultdataTableDiv').style.display = 'none';
		}
	}
	else{
		document.getElementById('resultHeaderDiv').style.display = 'none';
		document.getElementById('searchResultdataTableDiv').style.display = 'none';
	}

	var tableObjectUnCoded = document.getElementById('formName:searchResultUnCodedTable');
	if(null != tableObjectUnCoded ){
		var tablesizeUnCoded = tableObjectUnCoded.rows.length;
		if(tablesizeUnCoded == 0)
		{
			document.getElementById('resultHeaderUnCodedDiv').style.display = 'none';
			document.getElementById('searchResultdataTableUnCodedDiv').style.display = 'none';
		}
	}
	else{
		document.getElementById('resultHeaderUnCodedDiv').style.display = 'none';
		document.getElementById('searchResultdataTableUnCodedDiv').style.display = 'none';
	}
	var Stamp = new Date();
	var year = Stamp.getYear();
	var month = (Stamp.getMonth() + 1);
	if(month < 10){
		month = "0"+ month;
	}
	var day = Stamp.getDate();
	if(day < 10){
		day = "0"+day;
	}
	var Hours;
	var Mins;
	var Time;
	Hours = Stamp.getHours();
	if(Hours <10){
		Hours = "0"+ Hours;
	}
	if (Hours >= 12) {
		Time = " P.M.";
	}
	else {
		Time = " A.M.";
	}

	Mins = Stamp.getMinutes();
	if (Mins < 10) {
		Mins = "0" + Mins;
	}	
	seconds =Stamp.getSeconds()
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	var totaltime=""+month+'/'+day+'/'+year+"        "+Hours+':'+Mins+':'+seconds;
	document.getElementById('timeID').innerHTML=totaltime;

	function printPage(){
		window.print();
	}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productRuleValidation" /></form>
</HTML>
