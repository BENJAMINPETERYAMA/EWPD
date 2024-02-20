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
<script>window.print();</script>
<TITLE>Print Date Segment Transfer Log</TITLE>
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>

			<td><br/>
					<FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">

                    	<h:outputText id="breadcrumb" value="#{contractBasicInfoBackingBean.breadCrumpTextForTransferLogPrint}"></h:outputText>

                     </FIELDSET>
			</td>
		</tr>
		
		<tr>
			<td><h:form id="ContractTransferLogViewForm">
		<h:inputHidden id="statusPrint"
											value="#{contractBasicInfoBackingBean.transferLogPageStatus}">	</h:inputHidden>
		<h:inputHidden id="viewLog"
					value="#{contractBasicInfoBackingBean.transferLog}"></h:inputHidden>
				<Div id = "emptymsg"><BR/>
					&nbsp;&nbsp;&nbsp;&nbsp;<h:outputText value="No Transfer Log available."	styleClass="dataTableColumnHeader" />
				</Div>
<Div id = "TransferLogContent"> 
				<FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
				
				<TABLE align="center" width="100%" border="0" cellspacing="2"
					cellpadding="0">
					<TBODY>

						<tr>
							<td>
							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">

								<tr>
									<td>
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70%"><B>Transfer Log For Date Segment&nbsp;&nbsp;
												<h:outputText id="effectiveDate_txt" value="#{contractBasicInfoBackingBean.startDate}" />
												&nbsp;&nbsp;-&nbsp;&nbsp;<h:outputText id="expiryDate_txt" value="#{contractBasicInfoBackingBean.endDate}" /></B>

											</td>											
										</tr>
									</table>
									</td>


								</tr>
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE  cellpadding="3"
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
										style="width:100%;position:relative;z-index:1;font-size:10px;">
									<!-- Search Result Data Table --> <h:dataTable										
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" 
										rendered="#{contractBasicInfoBackingBean.contractTransferResultList != null}"
										value="#{contractBasicInfoBackingBean.contractTransferResultList}">

										<h:column>

											<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="userAction"
												value="#{singleValue.status}"></h:outputText>
										</h:column>
										<h:column>

											<h:outputText id="user" value="#{singleValue.user}"></h:outputText>

										</h:column>
										<h:column>

											<h:outputText id="timeStamp" value="#{singleValue.timeStamp}"
											 >	<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" /></h:outputText>
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
			
</FIELDSET>
		</Div>		
			</h:form></td>
		</tr>

		
	</table>
	</BODY>
</f:view>
<script language="JavaScript" type="text/javascript">
initialize();
function initialize(){
	var tableObject = document.getElementById('ContractTransferLogViewForm:searchResultTable');
	if(tableObject.rows.length != 0) {
		document.getElementById('emptymsg').style.visibility = 'hidden';		
	}else {
		document.getElementById('emptymsg').style.visibility = 'visible';
		document.getElementById('TransferLogContent').style.visibility = 'hidden';		
	}
}
setColumnWidth('ContractTransferLogViewForm:searchResultTable', '20%:30%:20%:30%');
setColumnWidth('resultHeaderTable', '20%:30%:20%:30%');
</script>
</HTML>
