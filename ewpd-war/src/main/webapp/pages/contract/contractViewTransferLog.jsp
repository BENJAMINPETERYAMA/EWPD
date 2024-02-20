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

<TITLE>View Date Segment Transfer Log</TITLE>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="viewLog"
				value="#{contractBasicInfoBackingBean.transferLog}"></h:inputHidden>
			<td><jsp:include page="../navigation/top_view.jsp"></jsp:include>
		</tr>
		<tr>

			<td>
			<TABLE width="40%">
				<TR>
					<TD>
					<TABLE>
						<TBODY>
							<tr>
								<TD><w:message></w:message></TD>
							</tr>
						</TBODY>
					</TABLE>
					</TD>

				</TR>
			</TABLE>
			</td>


		</tr>
		<tr>
			<td><h:form id="ContractTransferLogViewForm">

				<Div id="emptymsg">&nbsp;<h:outputText
					value="No Transfer Log available."
					styleClass="dataTableColumnHeader" /></Div>
				<Div id="TransferLogContent">
				<TABLE align="center" width="90%" border="0" cellspacing="2"
					cellpadding="0">
					<TBODY>

						<tr>
							<td>
							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">

								<tr>
									<td>
									<TABLE id="HeaderDiv" width="100%" border="0" cellspacing="0"
										cellpadding="0">
										<tr class="dataTableColumnHeaderInfo">
											<td width="70%"><B>Transfer Log For Date Segment&nbsp;&nbsp;</B>
											<h:outputText id="effectiveDate_txt"
												value="#{contractBasicInfoBackingBean.startDate}" />
											&nbsp;&nbsp;-&nbsp;&nbsp;<h:outputText id="expiryDate_txt"
												value="#{contractBasicInfoBackingBean.endDate}" /></td>
											<td id="viewMoreButton" align="right"><a id="viewAllCursor"
												href="#" onclick="viewFullTransferLogList();return false;"><h:outputText
												id="viewAll" value="View More" /></a></td>
											<td>&nbsp;|&nbsp;</td>
											<td id="viewMoreButton1" align="right" width="15%"><a
												id="viewLatestCursor" href="#"
												onclick="viewLatestTransferLogList();return false;"><h:outputText
												id="viewLatest" value="View Latest 20 Logs" /></a></td>
										</tr>
									</table>
									</td>


								</tr>
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="20%"><h:outputText
													value="Contract Version"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText
													value="User Action"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="User"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText value="Date"></h:outputText></TD>

											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:404px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{contractBasicInfoBackingBean.contractTransferResultList != null}"
										value="#{contractBasicInfoBackingBean.contractTransferResultList}">

										<h:column>

											<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="userAction" value="#{singleValue.status}"></h:outputText>
										</h:column>
										<h:column>

											<h:outputText id="user" value="#{singleValue.user}"></h:outputText>

										</h:column>
										<h:column>

											<h:outputText id="timeStamp" value="#{singleValue.timeStamp}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText>
											<h:outputText id="space" value=" "></h:outputText>
											<h:outputText id="timeZone" value="#{requestScope.timezone}">
											</h:outputText>
										</h:column>

									</h:dataTable>
									</TD>
								</TR>

							</TABLE>



							</TD>

						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
				</TABLE>
				</Div>

				<h:commandLink id="viewFullTransferLog" style="hidden"
					action="#{contractBasicInfoBackingBean.viewMoreInList}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="viewLatestTransferLog" style="hidden"
					action="#{contractBasicInfoBackingBean.viewLatestRecordFromList}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="logPageStatus"
					value="#{contractBasicInfoBackingBean.logPageStatus}"></h:inputHidden>
				<h:inputHidden id="isViewTwenty"
					value="#{contractBasicInfoBackingBean.isViewTwenty}"></h:inputHidden>

			</h:form></td>
		</tr>

		<!-- Print Was here Previously -->
	</table>
	</BODY>

</f:view>

<script language="JavaScript" type="text/javascript">

function viewFullTransferLogList(){
	if (document.getElementById('ContractTransferLogViewForm:viewAll').className == 'variableLink'){	
		document.getElementById('ContractTransferLogViewForm:logPageStatus').value= 'all';
		submitLink('ContractTransferLogViewForm:viewFullTransferLog');
	}else{	
		return false;
	}
}
function viewLatestTransferLogList(){
	if (document.getElementById('ContractTransferLogViewForm:viewLatest').className == 'variableLink'){
		document.getElementById('ContractTransferLogViewForm:logPageStatus').value= '';
		submitLink('ContractTransferLogViewForm:viewLatestTransferLog');
	}else{
		return false;
	}	
}
function initialize(){
	var tableObject = document.getElementById('ContractTransferLogViewForm:searchResultTable');
	if(tableObject.rows.length != 0) {
		document.getElementById('emptymsg').style.visibility = 'hidden';
		if(document.getElementById('ContractTransferLogViewForm:searchResultTable').offsetHeight > 404){
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			document.getElementById('ContractTransferLogViewForm:searchResultTable').width = relTblWidth-17+"px";
			document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
			document.getElementById('HeaderDiv').width = relTblWidth-17+"px";
		}
				
	}else {
		document.getElementById('emptymsg').style.visibility = 'visible';
		document.getElementById('TransferLogContent').style.visibility = 'hidden';		
	}
}
initialize();
if(document.getElementById('ContractTransferLogViewForm:isViewTwenty').value != 'true'){
	if(document.getElementById('ContractTransferLogViewForm:logPageStatus').value == 'all'){
		document.getElementById('ContractTransferLogViewForm:viewLatest').className = 'variableLink';
		document.getElementById('viewAllCursor').style.cursor= 'default';
	}else if(document.getElementById('ContractTransferLogViewForm:logPageStatus').value != 'all'){
		document.getElementById('ContractTransferLogViewForm:viewAll').className = 'variableLink';
		document.getElementById('viewLatestCursor').style.cursor= 'default';
	}
}else{
	document.getElementById('viewAllCursor').style.cursor= 'default';
	document.getElementById('viewLatestCursor').style.cursor= 'default';
	document.getElementById('ContractTransferLogViewForm:viewAll').disabled ='true';
	document.getElementById('ContractTransferLogViewForm:viewLatest').disabled ='true';
	
}




setColumnWidth('ContractTransferLogViewForm:searchResultTable', '20%:30%:20%:30%');
setColumnWidth('resultHeaderTable', '20%:30%:20%:30%');
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractTransferLogPrint" />
</HTML>
