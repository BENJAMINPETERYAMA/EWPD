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
<TITLE>Reserve Contract Id</TITLE>
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
		<h:inputHidden id="retrieveContractDetails"
			value="#{releaseReservedContractsBackingBean.displayContractDetails}" />
		<table width='100%'>
			<tr>
				<td><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpReq.setAttribute("breadCrumbText",
				"Administration >> Contract Id >> Release >> Availability");
	%> <jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include>
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


			</TBODY>
		</TABLE>
		
		<DIV id="resultHeaderDiv">
		<TABLE>
			<TR>
				<TD><h:outputText
					rendered="#{releaseReservedContractsBackingBean.listTodisplay != null}" 
					value="Note: Contract Ids that are in-use(if any) cannot be released"></h:outputText></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">

			<TR>
				<TD><strong><h:outputText value="Contract Ids Availability"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Contract Id(s)"></h:outputText></TD>
					<TD align="left"><h:outputText value="Line of Business"></h:outputText></TD>
					<TD align="left"><h:outputText value="Business Entity"></h:outputText></TD>
					<TD align="left"><h:outputText value="Business Group"></h:outputText></TD>
					<TD align="left"><h:outputText value="Effective Date"></h:outputText></TD>
					<TD align="left"><h:outputText value="Expiry Date"></h:outputText></TD>
					<TD align="left"><h:outputText value="Status"></h:outputText></TD>
					<TD align="left"><h:outputText value="Privilege"></h:outputText></TD>
					<TD align="left"><h:outputText value="User"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>
		<DIV id="searchResultdataTableDiv"
			style="height:200px;z-index:1;font-size:10px;overflow:auto;"><!-- Search Result Data Table -->
		<h:dataTable styleClass="outputText" headerClass="dataTableHeader"
			id="searchResultTable" var="singleValue" cellpadding="3" width="100%"
			cellspacing="1" bgcolor="#cccccc"
			rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
			rendered="#{releaseReservedContractsBackingBean.listTodisplay!= null}"
			value="#{releaseReservedContractsBackingBean.listTodisplay}"
			var="eachRow">
			<h:column>
				<h:outputText id="cntrctId" value="#{eachRow.contractId}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="lobId" value="#{eachRow.lineOfBusiness}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="beId" value="#{eachRow.businessEntity}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="bgId" value="#{eachRow.businessGroup}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="effectiveDateId" value="#{eachRow.effectiveDate}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="expiryDateId" value="#{eachRow.expiryDate}"></h:outputText>
			</h:column>

			<h:column>
				<h:outputText id="Availability" value="#{eachRow.reservePoolStatus}"></h:outputText>
			</h:column>

			<h:column>
				<h:outputText id="Privilege" value="#{eachRow.privilege}"></h:outputText>
			</h:column>

			<h:column>
				<h:outputText id="User" value="#{eachRow.createdUser}"></h:outputText>
			</h:column>

		</h:dataTable></DIV>
		<TABLE>
			<tr>
				<td>
				<div id="confirmButtonDiv"
					style="position:relative;overflow:auto;width:100%"><h:commandButton
					alt="Confirm" id="confirm" value="Confirm" styleClass="wpdButton"
					onclick="confirm();return false;">
				</h:commandButton></div>
				<td>
				<TD>
				<div id="cancelButtonDiv"
					style="position:relative;overflow:auto;width:100%"><h:commandButton
					alt="Cancel" id="cancel" styleClass="wpdButton"
					onclick="closeWindow();return false;" value="Cancel">
				</h:commandButton></div>

				</TD>
			</tr>

		</TABLE>

		<!-- Space for hidden fields -->

	</h:form>

	</BODY>

</f:view>

<script>

	function closeWindow(){
	
		window.returnValue = "cancel";
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
		}
		function confirm(){
	
		window.returnValue = "confirm";
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
		}
	
	 var tableObject=document.getElementById('formName:searchResultTable');
		if(tableObject==null){
			document.getElementById("resultHeaderTable").style.visibility = 'hidden';
			document.getElementById("confirmButtonDiv").style.visibility = 'hidden';
			document.getElementById("cancelButtonDiv").style.visibility = 'hidden';
		}
		if(null!=tableObject){
			if(tableObject.rows.length > 0){
			//	document.getElementById("noReservedContractsDiv").style.visibility = 'hidden';
			//	document.getElementById("noReservedContractsDiv").style.height = "0px";
			//	document.getElementById("searchResultdataTableDiv").style.visibility = 'visible';
			//	document.getElementById("resultHeaderDiv").style.visibility = 'visible';
				//setColumnWidth('formName:searchResultTable','15%:15%:15%:15%:10%:10%:10%');
				//setColumnWidth('resultHeaderTable','15%:15%:15%:15%:10%:10%:10%');
			}else {
					document.getElementById("confirmButtonDiv").style.visibility = 'hidden';
					document.getElementById("cancelButtonDiv").style.visibility = 'hidden';
			//		document.getElementById("searchResultdataTableDiv").style.visibility = 'hidden';
			//		document.getElementById("searchResultdataTableDiv").style.height = "0px";
			//		document.getElementById("resultHeaderDiv").style.visibility = 'hidden';
			//		document.getElementById("noReservedContractsDiv").style.visibility = 'visible';
			}	
		}
		if(null!=tableObject){
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			var resultwidth = document.getElementById('formName:searchResultTable').width;
			//var headerWidth = document.getElementById('resultHeaderTable').width;
			if(tableObject.rows.length > 9){
				resultwidth = relTblWidth-17+"px";
				document.getElementById('resultHeaderTable').width = resultwidth;
				document.getElementById('headerTable').width = resultwidth;
				setColumnWidth('formName:searchResultTable','12%:10%:10%:10%:13%:12%:7%:8%:8%');
				setColumnWidth('resultHeaderTable','12%:10%:10%:10%:13%:12%:7%:8%:8%');
			}else{
				setColumnWidth('formName:searchResultTable','12%:10%:10%:10%:13%:12%:7%:8%:8%');
				setColumnWidth('resultHeaderTable','12%:10%:10%:10%:13%:12%:7%:8%:8%');
			}
	}

	

	
</script>
</HTML>
