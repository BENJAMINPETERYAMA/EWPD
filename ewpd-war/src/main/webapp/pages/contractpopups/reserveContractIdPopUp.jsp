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

<TITLE>Contract Search</TITLE>
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
</script>

<f:view>
	<BODY><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>

			<TD>
				<% javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
					httpReq.setAttribute("breadCrumbText","Administration >> Contract Id >> Reserve >> Availability ");
				%>	
				<jsp:include page="../navigation/top_reserve.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="formName">
				<h:inputHidden id="init" value="#{reservedContractBackingBean.init}" />	
				<TABLE border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>

						<TD valign="top" class="ContentArea" width="100%">
							<TABLE>
								<TR>
									<TD width="704">
										<TABLE>
											<TBODY>
												<TR>
													<TD>
														<w:message value="#{reservedContractSearchBackingBean.validationMessages}"></w:message> 
													</TD>
												</TR>		
											</TBODY>
										</TABLE>
									</TD>

								</TR>
							</TABLE>

							<DIV>
							

							<DIV id="accordionTest">

							<DIV id="panel2" style="position:relative;font-size:10px;width:100%;">
								
								

								<DIV id="panel2Content" class="tabContentBox" style="position:relative;font-size:10px;width:100%"><BR>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0">
										<TR>
											<TD width="742"><B><STRONG><h:outputText value="Contract Ids Availability"></h:outputText></STRONG></B></TD>
										</TR>
									</TABLE>
							
									<TABLE cellpadding="0" cellspacing="0" border="0">
										<TR>
											<TD width="792">
											<DIV id="resultHeaderDiv" style="width:95%;">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="750">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left"><h:outputText value="Contract Id"></h:outputText></TD>
															<TD align="left"><h:outputText value="Availability"></h:outputText></TD>
															<TD align="left"><h:outputText value="Effective Date"></h:outputText></TD>
															<TD align="left"><h:outputText value="Expiry Date"></h:outputText></TD>
															<TD align="left"><h:outputText value="System Domain"></h:outputText></TD>
															
													</TR>
													</TBODY>
												</TABLE>
											</DIV>
										</TD>
									</TR>
									<TR>
									<TD width="792">
										<DIV id="searchResultdataTableDiv" style="height:300px;width:95%;overflow-y:auto;">
										<!-- Search Result Data Table --> 
										<h:dataTable styleClass="outputText"
											headerClass="dataTableHeader" id="searchResultTable"
											var="singleValue" cellpadding="3" cellspacing="1"
											bgcolor="#cccccc"  
											rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
											rendered="#{reservedContractBackingBean.newSystemList!= null}"
											value="#{reservedContractBackingBean.newSystemList}"
											var="eachRow" width="750">
											<h:column>
												<h:outputText id="cntrctId" value="#{eachRow.contractId}"
													styleClass="#{(eachRow.systemPoolStatus != 'Available') ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="Availability"
													value="#{eachRow.systemPoolStatus}"
													styleClass="#{(eachRow.systemPoolStatus != 'Available') ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="effectiveDate"
													value="#{eachRow.effectiveDate}"
													styleClass="#{(eachRow.systemPoolStatus != 'Available') ? 'mandatoryError': 'mandatoryNormal'}">

												</h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="expiryDate" value="#{eachRow.expiryDate}"
													styleClass="#{(eachRow.systemPoolStatus != 'Available') ? 'mandatoryError': 'mandatoryNormal'}">

												</h:outputText>
											</h:column>
											<h:column>
												<h:outputText id="systemDomain"
													value="#{eachRow.systemDomain}"
													styleClass="#{(eachRow.systemPoolStatus != 'Available') ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText>
											</h:column>

										</h:dataTable>

										</DIV>
									</TD>
									</TR>
										
								</TABLE>
								<DIV id="noContractsDiv" style="height:50px;">
									<h:outputText value="No Contracts Available." styleClass="dataTableColumnHeader" />
								</DIV>
							</DIV>
							
						</DIV>
					</DIV>
				</DIV>
			</TD>
			</TR>
		</TBODY></TABLE>
		<TABLE>
			<TR>
				<TD>
						<h:commandButton alt="Confirm" id="confirm" value="Confirm" styleClass="wpdButton" rendered="#{reservedContractBackingBean.renderConfirmButton}" onclick="closeWindowOnConfirm();return false;">
						</h:commandButton>
				</TD>
		
				<TD>
						<h:commandButton alt="Cancel" id="cancel" styleClass="wpdButton" onclick="closeWindow();return false;" value="Cancel">
													</h:commandButton>
					
				</TD>
			</TR>
			
		</TABLE>
		</h:form>
		</TD>
		</TR>
		
	</TABLE>
	</hx:scriptCollector></BODY>

</f:view>
<script language="javascript">
	initialize();

	function closeWindowOnConfirm(){
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
	function initialize(){
		if(document.getElementById('formName:searchResultTable')!=null){
		var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		var widthVar=document.getElementById('formName:searchResultTable').width;
				widthVar = relTblWidth+"px";
				//syncTables('resultHeaderTable','formName:searchResultTable');
				setColumnWidth('formName:searchResultTable','20%:20%:20%:20%:20%');
				setColumnWidth('resultHeaderTable','20%:20%:20%:20%:20%');
		}
	}
		

	
		
	if(document.getElementById('formName:searchResultTable')!=null){
		if(document.getElementById('formName:searchResultTable').rows.length > 0){
			document.getElementById("noContractsDiv").style.visibility = 'hidden';
			document.getElementById("noContractsDiv").style.height = "0px";
			document.getElementById("searchResultdataTableDiv").style.visibility = 'visible';
			document.getElementById("resultHeaderDiv").style.visibility = 'visible';
			setColumnWidth('formName:searchResultTable','20%:20%:20%:20%:20%');
		}else {

			document.getElementById("searchResultdataTableDiv").style.visibility = 'hidden';
			document.getElementById("searchResultdataTableDiv").style.height = "0px";
			document.getElementById("resultHeaderDiv").style.visibility = 'hidden';
			document.getElementById("noContractsDiv").style.visibility = 'visible';
		}
}

	function syncTables(){
		var widthVar=document.getElementById('formName:searchResultTable').width;
		var relTblWidth = document.getElementById('formName:searchResultTable').offsetWidth;
		widthVar = relTblWidth + 'px';
	}	
		
		if(document.getElementById('formName:searchResultTable') != null){
			document.getElementById('formName:searchResultTable').onresize = syncTables;
			syncTables();
		}
	

</script>
</HTML>
