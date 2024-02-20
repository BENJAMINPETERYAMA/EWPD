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
<base target=_self>
<TITLE>Admin Options</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionForm">
<h:inputHidden value="#{adminOptionBackingBean.valuesFromSessionForBenefit}"></h:inputHidden>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><jsp:include
								page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>

							</TD>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
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
												value="Administration Option" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												onmousedown="return false;"
												action="#{adminMethodBackingBean.loadForBenefitView}">
												<h:outputText value="Administration Process" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>

									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:510">

							<!--	Start of Table for actual Data	-->
							<DIV id="administrationPeriodDiv" >
							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD width="20%">&nbsp;<h:outputText
											styleClass="mandatoryNormal" id="lblBenefitAdmin"
											value="Administration Period" /></TD>
										<TD width="50%"><h:outputText
											styleClass="mandatoryNormal" id="benefitAdmin"
											value="#{adminOptionsBackingBean.benefitAdminName}" />
									</TD>
								</TR>
							</TABLE>
							</DIV>
							<br>
							<br>
								<div id="adminDiv" align="center" ><h:outputText rendered="#{adminOptionBackingBean.adminOptionList == null}"
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader" /></div>
							<DIV id="searchResultdataTableDiv" >
							<TABLE width="100%" cellspacing="0" cellpadding="0">
									
								<TR>
									<TD>	
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><b><h:outputText value="Associated Admin Options"></h:outputText></b>
											</TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="15%"><h:outputText value="Sequence "></h:outputText>
												</TD>
												<TD align="left" width="35%"><h:outputText value="Name "></h:outputText>
												</TD>
												<TD align="left" width="20%"><h:outputText
													value="Admin Level "></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText
													value="Benefit Level "></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<div id="paneltable" style="height:240px;position:relative;z-index:1;font-size:10px;overflow:auto;" >
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc" 
										value="#{adminOptionBackingBean.adminOptionList}"
										rendered="#{adminOptionBackingBean.adminOptionList != null}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
										<h:column>
											<h:outputText id="adminLevelSeqNo"
												value="#{singleValue.sequenceNumber}">
											</h:outputText>
										</h:column>

										<h:column>
											<h:inputHidden id="adminOptionIdHidden"
												value="#{singleValue.adminLevelOptionAssnSystemId}"></h:inputHidden>
											<h:outputText id="adminOptionName"
												value="#{singleValue.adminOptionDesc}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminLevelName"
												value="#{singleValue.adminLevelDesc}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:inputHidden id="benefitLevelIdOnPageLoad"
												value="#{singleValue.benefitLevelSysIdFromMaster}" />
											<h:outputText id="benefitLevelName"
												value="#{singleValue.benefitLevelDesc}">
											</h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->				
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
initialize();
		function initialize(){
			if(document.getElementById('adminOptionForm:searchResultTable') != null) {
				var relTblWidth = document.getElementById('adminOptionForm:searchResultTable').offsetWidth;
				if(document.getElementById('adminOptionForm:searchResultTable').rows.length < 11){	
				   document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
					document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
					setColumnWidth('adminOptionForm:searchResultTable', '15%:35%:20%:30%');
					setColumnWidth('resultHeaderTable','15%:35%:20%:30%');
				}else{
					document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
					setColumnWidth('adminOptionForm:searchResultTable', '15%:35%:20%:30%');
					setColumnWidth('resultHeaderTable','15%:35%:20%:30%');
					syncTables('adminOptionForm:searchResultTable','resultHeaderTable');
				}
						//	setColumnWidth('adminOptionForm:searchResultTable','15%:35%:20%:30%');
						//	setColumnWidth('resultHeaderTable','15%:35%:20%:30%');
			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			}
		}	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptions" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</html>