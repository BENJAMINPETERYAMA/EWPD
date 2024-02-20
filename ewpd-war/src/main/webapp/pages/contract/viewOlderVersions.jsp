<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<script language="JavaScript" src="../../js/prototype.js"></script>
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

<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
<TITLE>View Older Versions</TITLE>

</HEAD>

<f:view>
	<BODY>
	<h:form id="viewOlderVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
						<h:inputHidden id="breadCrumb"
							value="#{dateSegmentBackingBean.breadCrumbForOlderVersion}" />
			<jsp:include page="../navigation/top_view_simple.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%"><w:message></w:message>
				<div id="searchPanelHeader" class="tabTitleBar" style="width:100%"><b>Older Versions</b></div>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="930px">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="140px"><strong><h:outputText
										value="ContractId"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="Version"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Effective Date"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Expiry Date"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Contract Type"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="Contract Status"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="DateSegment Type"></h:outputText></strong></td>
									<td align="left" width="105px">&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>

						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:460; overflow:auto; width:950px;">
						<h:dataTable headerClass="dataTableHeader" 
							id="previousVersionsTable" var="singleValue" cellpadding="3"
							cellspacing="1" bgcolor="#cccccc" rendered="true"
							value="#{dateSegmentBackingBean.olderVersionResultList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="930px">
							<h:column>
								<h:outputText id="ContractId"  value="#{singleValue.contractId}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="Version" value="#{singleValue.version}"></h:outputText>
								<h:inputHidden id="versionHid" value="#{singleValue.version}" />
							</h:column>
							<h:column>
								<h:outputText id="StartDate" value="#{singleValue.startDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="EndDate" value="#{singleValue.endDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
								<h:inputHidden id="contractKey"
									value="#{singleValue.contractKey}" />
								<h:inputHidden id="contractIdHid"
									value="#{singleValue.contractId}" />
								<h:inputHidden id="contractDateSegmentSysHId"
									value="#{singleValue.dateSegmentId}" />
							</h:column>
							<h:column>
								<h:outputText id="ContractType"
									value="#{singleValue.contractType}"></h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
								<h:inputHidden id="statusHid" value="#{singleValue.status}" />
								<h:inputHidden id="contractProductSysId"
									value="#{singleValue.productSysId}"></h:inputHidden>
								<h:inputHidden id="dateSegmentType"
									value="#{singleValue.testIndicator}" />
							</h:column>
							<h:column>
								<h:outputText value="#{singleValue.description}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value=" " id="a1spaceSpan02" rendered="true"></h:outputText>
								<h:commandButton alt="View" id="viewButton"
									image="../../images/view.gif"
									rendered="#{singleValue.state.validForView}"
									onclick="getContractView();return false;"></h:commandButton>
							</h:column>
						</h:dataTable>
						</div>
						</TD>
					</tr>
				</table>
				</td>
				</tr>
			<TR>
				<TD><h:inputHidden id="sysIdHidden" value="#{requestScope.sysId}" />
				<h:inputHidden id="contIdHidden" value="#{requestScope.contId}" />
				<h:inputHidden id="versionHidden" value="#{requestScope.version}" />
				<h:inputHidden id="statusHidden" value="#{requestScope.status}" />
				</TD>
			</TR>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		
		<h:inputHidden id="selectedContractId"
			value="#{contractSearchBackingBean.selectedContractIDFromSearch}" />
		<h:inputHidden id="selectedContractKey"
			value="#{contractSearchBackingBean.selectedContractKeyFromSearch}" />
		<h:inputHidden id="selectedContractDateSegSysId"
			value="#{contractSearchBackingBean.selectedDateSegKeyFromSearch}" />
		<h:inputHidden id="selectedStatus"
			value="#{contractSearchBackingBean.selectedStatusFromSearch}" />
		<h:inputHidden id="selectedVersion"
			value="#{contractSearchBackingBean.selectedVerionFromSearch}" />

	</h:form>
	</BODY>
</f:view>
<script>
	

	if(null !=  document.getElementById('viewOlderVersionForm:previousVersionsTable') ) {
			setColumnWidth('viewOlderVersionForm:previousVersionsTable','9%:6%:10%:9%:10%:20%:14%:14%');
			setColumnWidth('resultHeaderTable','9%:6%:10%:9%:10%:20%:14%:14%');
			showResultsTab();	
			tableObj = getObj('viewOlderVersionForm:previousVersionsTable');
			if(tableObj.rows.length == 0)
				document.getElementById('messageDiv').style.display = 'block';
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}
		
	function getContractView(){
	getFromDataTableToHidden('viewOlderVersionForm:previousVersionsTable','contractKey','viewOlderVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewOlderVersionForm:previousVersionsTable','contractIdHid','viewOlderVersionForm:selectedContractId');
	getFromDataTableToHidden('viewOlderVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewOlderVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewOlderVersionForm:previousVersionsTable','versionHid','viewOlderVersionForm:selectedVersion');
	getFromDataTableToHidden('viewOlderVersionForm:previousVersionsTable','statusHid','viewOlderVersionForm:selectedStatus');
	var url = '../contract/contractBasicInfoView.jsp'+getUrl();
	  url = url +"?contractID="+escape(document.getElementById('viewOlderVersionForm:selectedContractId').value)
				+'&contractSysId='+document.getElementById('viewOlderVersionForm:selectedContractKey').value
				+'&contractDateSegmentSysId='+document.getElementById('viewOlderVersionForm:selectedContractDateSegSysId').value
				+'&status='	+document.getElementById('viewOlderVersionForm:selectedStatus').value
				+'&version='+document.getElementById('viewOlderVersionForm:selectedVersion').value
				+'&temp='+Math.random()
				+'&reloadTree=Y';

	var returnvalue=window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	}	
</script>
</HTML>
