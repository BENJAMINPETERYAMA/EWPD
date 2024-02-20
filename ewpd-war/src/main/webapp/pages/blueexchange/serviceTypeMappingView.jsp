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

<TITLE>View Service Type Mapping</TITLE>
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

	<h:inputHidden id="temp"
		value="#{serviceTypeMappingBackingBean.initView}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		javax.servlet.http.HttpSession httpSession = (javax.servlet.http.HttpSession) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		httpReq.setAttribute("breadCrumbText",
				"Administration >> Blue Exchange >> Service Type Mapping ("
						+ httpSession.getAttribute("ruleID") + ") >> View");
	%> <jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</td>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="servTypMappingForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD>
									<TABLE>
										<!-- Header Rule -->
										<TR>
											<TD width="235"><h:outputText value="Header Rule" /></TD>
											<TD width="221"><h:inputHidden id="headerRuleHidden"
												value="#{serviceTypeMappingBackingBean.ruleId}" /> <h:outputText
												value="#{serviceTypeMappingBackingBean.ruleId}" />&nbsp;&nbsp;
											<h:commandButton alt="View" id="viewButton" image="../../images/view.gif" 
											onclick="viewAction();return false;" tabindex="11" />
											</TD>
			
										</TR>
										<!-- Applicable To Blue Exchange -->
										<TR>
											<TD width="235"><h:outputText
												value="Applicable To Blue Exchange" /></TD>
											<TD width="221"><h:inputHidden id="srvClassHighHidden"
												value="#{serviceTypeMappingBackingBean.validForBX}" /> <h:outputText
												value="#{serviceTypeMappingBackingBean.validForBX}" /></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>								
								<!-- EB03/Service Type Code  -->
								<TR>
									<TD colspan="3" width="100%">
									<div id="adminDiv"><h:outputText
										rendered="#{serviceTypeMappingBackingBean.eb03IdentifierList == null}"
										value="No EB03 Codes Available."
										styleClass="dataTableColumnHeader" /></div>
									<DIV id="searchResultdataTableDiv" style="width:100%;">
									<TABLE width="100%" cellspacing="0" cellpadding="0" id="headerTableId" >
										<TR>
											<TD colspan="3">
											<DIV id="resultHeaderDiv" style="width:100%;">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable1" border="0" width="100%">
												<TR align="center">
													<TD align="left" ><b><h:outputText
														value="EB03/Service Type Code"></h:outputText></b></TD>
												</TR>
											</TABLE>
											</DIV></TD></TR>
											<TR><TD><DIV>
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="left" width="35%"><h:outputText value="Code"></h:outputText>
														</TD>
														<TD align="left" width="35%"><h:outputText
															value="Description"></h:outputText></TD>
														<TD align="left" width="30%"><h:outputText
															value="Send Dynamic Information"></h:outputText></TD>
													</TR>
												</TBODY>
											</TABLE></DIV>
											</TD></TR>	
										<TR>
											<TD colspan="3">
											<div id="paneltable"
												style="width:100%;height:90px;overflow-y:auto;"><!-- Search Result Data Table -->
											<h:dataTable styleClass="outputText"
												headerClass="dataTableHeader" id="searchResultTable"
												var="singleValue" cellpadding="3" width="100%"
												cellspacing="1" bgcolor="#cccccc"
												value="#{serviceTypeMappingBackingBean.eb03IdentifierList}"
												rendered="#{serviceTypeMappingBackingBean.eb03IdentifierList != null}"
												rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

												<h:column>
													<h:inputHidden id="serviceTypeCode"
														value="#{singleValue.serviceTypeCode}"></h:inputHidden>
													<h:outputText id="Code"
														value="#{singleValue.serviceTypeCode}">
													</h:outputText>
												</h:column>
												<h:column>
													<h:inputHidden id="serviceTypeCodeDesc"
														value="#{singleValue.serviceTypeSecCode}"></h:inputHidden>
													<h:outputText id="Description"
														value="#{singleValue.serviceTypeSecCode}">
													</h:outputText>
												</h:column>
												<h:column>
													<h:inputHidden id="sendynamicinfo"
														value="#{singleValue.sendDynamicInfo}"></h:inputHidden>
													<h:outputText id="senddynamic"
														value="#{singleValue.sendDynamicInfo}">
													</h:outputText>
												</h:column>
											</h:dataTable></DIV>
											</TD>
										</TR>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<TABLE>
										<!-- Created User -->
										<TR>
											<TD width="235"><h:outputText value="Created By" /></TD>
											<TD width="221"><h:outputText
												value="#{serviceTypeMappingBackingBean.createdUser}" /> <h:inputHidden
												value="#{serviceTypeMappingBackingBean.createdUser}" /></TD>
										</TR>
										<!-- Created Date -->
										<TR>
											<TD width="235"><h:outputText value="Created Date" /></TD>
											<TD width="221"><h:outputText
												value="#{serviceTypeMappingBackingBean.createdTimestamp}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}" /> <h:inputHidden
												value="#{serviceTypeMappingBackingBean.createdTimestamp}" />
											</TD>
										</TR>
										<!-- Last updated user -->
										<TR>
											<TD width="235"><h:outputText value="Last Updated By" /></TD>
											<TD width="221"><h:outputText
												value="#{serviceTypeMappingBackingBean.lastUpdatedUser}" />
											<h:inputHidden
												value="#{serviceTypeMappingBackingBean.lastUpdatedUser}" />
											</TD>
										</TR>
										<!-- Last update time -->
										<TR>
											<TD width="235"><h:outputText value="Last Updated Date" /></TD>
											<TD width="221"><h:outputText
												value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
											</h:outputText> <h:outputText
												value="#{requestScope.timezone}" /> <h:inputHidden
												value="#{serviceTypeMappingBackingBean.lastUpdatedTimestamp}" />
											</TD>
										</TR>

										<TR>
											<TD width="235"></TD>
											<TD width="221">&nbsp;</TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>
			</h:form></td>
		</tr>
		<tr>
			<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
		</tr>

	</table>
	</BODY>
</f:view>

<script>
initialize();
		function initialize(){
			if(document.getElementById('servTypMappingForm:searchResultTable').rows.length != 0 && document.getElementById('servTypMappingForm:searchResultTable') != null) {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
				document.getElementById('paneltable').style.visibility = 'visible';
				setColumnWidth('servTypMappingForm:searchResultTable','35%:35%:30%');
				syncTables('resultHeaderTable','servTypMappingForm:searchResultTable');
				var relTblWidth = document.getElementById('servTypMappingForm:searchResultTable').offsetWidth;
				document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
				document.getElementById('resultHeaderTable1').width = relTblWidth + 'px';
			}else {
				document.getElementById('searchResultdataTableDiv').style.display = 'none';
				document.getElementById('paneltable').style.display = 'none';
			    document.getElementById('paneltable').style.height = "0px";
			}
		}

function viewAction(){	
<!--
String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
}
// end hiding contents -->

	var ruleIdObject = document.getElementById('servTypMappingForm:headerRuleHidden').value;
	var splitRuleId = ruleIdObject.split('-');
	var ruleId = splitRuleId[0];
	ruleId = ruleId.trim();
	if(ruleId.length <=1){
			alert('Benefit Rule ID need to be selected.');
		}
	else{
		window.showModalDialog('../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random(),'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	}
}	

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="serviceTypeMapping" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>
