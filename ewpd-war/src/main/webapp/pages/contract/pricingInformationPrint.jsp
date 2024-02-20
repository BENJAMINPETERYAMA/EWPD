<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/isg/PricingInformation.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>WellPoint Product Database: Pricing Information</TITLE>
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

</HEAD>
<f:view>
<BODY >
<hx:scriptCollector id="scriptCollector1">
	<h:inputHidden id="hidden1" ></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
				<h:form styleClass="form" id="pricingInfoFormPrint">
					
					
					<tr>
						<h:inputHidden id="selectedContractDSSysID" value="#{contractPricingInfoBackingBean.loadForPrint}"></h:inputHidden>
					</tr>
					<br/><br/>

					
					
					<div id="panel2">
							<div id="resultInfo" class="dataTableColumnHeader">
								<br/>No Pricing Information is Available.
							</div>
						
							<div id="resultHeaderDiv">
								<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width:90%">
									Associated Pricing Records
								</div>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" >
									<TBODY>
		
										<TR class="dataTableColumnHeader">
											<td align="left" >
														<h:outputText styleClass="formOutputColumnField" value="Coverage" ></h:outputText> 
											</td>
											<td  align="left" >
			              								<h:outputText styleClass="formOutputColumnField" value="Pricing" ></h:outputText>
											</td >
											<td align="left" >
			              								<h:outputText styleClass="formOutputColumnField" value="Network"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
							</div>
						<div id="panel2Content" style="height:106px;width:90%;position:relative;z-index:1;font-size:10px;overflow:auto;">
						<table cellpadding="0" cellspacing="0" border="0">
						<tr>
						<td align="left">
								
								<h:dataTable headerClass="tableHeader" id="resultsTable" border="0"
									value="#{contractPricingInfoBackingBean.pricingInformationList}"
									rendered="#{contractPricingInfoBackingBean.renderFlag}" var="eachRow" 
									 cellpadding="3" cellspacing="1" 
									rowClasses="dataTableOddRow">
									
									<h:column>
										<h:outputText id="coverageInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.coverageDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="pricingInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.pricingDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="networkInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.networkDesc}"></h:outputText>
									</h:column>
								</h:dataTable>
							</td>
						</tr>
						</table>
	
						</div>
						</div>
						
		</h:form>
		
</TABLE>
</hx:scriptCollector></body>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractPricingInformation" /></form>
<script language="JavaScript">
	hideResultDiv();
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');

		var tableObj = document.getElementById('pricingInfoFormPrint:resultsTable');
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

		if(document.getElementById('pricingInfoFormPrint:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "90%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('pricingInfoFormPrint:resultsTable').offsetHeight <= 106) {
				document.getElementById('pricingInfoFormPrint:resultsTable').width = relTblWidth+"px";
				setColumnWidth('pricingInfoFormPrint:resultsTable','32%:32%:32%:4%');
				setColumnWidth('resultHeaderTable','32%:32%:32%:4%');
			}else{
				document.getElementById('pricingInfoFormPrint:resultsTable').width = (relTblWidth-17)+"px";
				setColumnWidth('pricingInfoFormPrint:resultsTable','32%:32%:32%:4%');
				setColumnWidth('resultHeaderTable','31.3%:31.3%:31.3%:6.1%');
			}
		}
</script>
</HTML>

