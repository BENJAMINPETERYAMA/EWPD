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

<TITLE>Benefit Administration View</TITLE>
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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitAdminForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><!-- Space for Tree  Data	-->
						<jsp:include page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --> <w:message
										value="#{benefitAdministrationBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{benefitLevelBackingBean.loadBenefitLevelFromBenefitAdministrationViewTab}">
											<h:outputText value="Benefit Level" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value=" Benefit Administration" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;
							width:100%;height:500">

						<div id="emptymsg"><h:outputText
									value="No Benefit Administrations Available."
									styleClass="dataTableColumnHeader" /></div>

						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<tr>
								<TD><b> <h:outputText value="Associated Benefit Administrations"></h:outputText>
								</b></TD>
							</tr>
						</TABLE>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR class="dataTableColumnHeader" style="height:20">
									<td align="left" width="25%"><h:outputText value="Description"></h:outputText>
									</td>
									<td align="left" width="25%"><h:outputText
										value="Effective Date"></h:outputText></td>
									<td align="left" width="25%"><h:outputText value="Expiry Date"></h:outputText>
									</td>

								</TR>
							</TBODY>
						</TABLE>
						</div>
						<div id="panel2Content"
							style="height:100%;width:100%;position:relative;z-index:1;font-size:10px; overflow:auto;">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td align="left"><h:dataTable styleClass="outputText"
									headerClass="dataTableHeader" id="resultsTable"
									var="singleValue" cellpadding="3" width="100%" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{benefitAdministrationBean.benefitAdministrationListForViewandPrint!= null}"
									value="#{benefitAdministrationBean.benefitAdministrationListForViewandPrint}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
									<h:column>
										<h:outputText id="description"
											value="#{singleValue.description}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="effectiveDate"
											value="#{singleValue.effectiveDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="expiryDate"
											value="#{singleValue.expiryDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>

								</h:dataTable></td>
							</tr>
						</table>
						</div>
						</fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script language="JavaScript">
		if(document.getElementById('benefitAdminForm:resultsTable') != null && document.getElementById('benefitAdminForm:resultsTable').rows.length > 0) {
			var relTblWidth = document.getElementById('benefitAdminForm:resultsTable').offsetWidth;
		if(document.getElementById('benefitAdminForm:resultsTable').offsetHeight <= 242) {
				document.getElementById('benefitAdminForm:resultsTable').width = relTblWidth+"px";
				document.getElementById('resultHeaderTable').style.width = relTblWidth + "px";
				setColumnWidth('resultHeaderTable','30%:30%:30%:10%');	
				setColumnWidth('benefitAdminForm:resultsTable','30%:30%:30%:10%');
			}else{
				document.getElementById('resultHeaderTable').width = relTblWidth+"px";
				document.getElementById('benefitAdminForm:resultsTable').width = relTblWidth;
			setColumnWidth('resultHeaderTable','30%:30%:30%:10%');	
			setColumnWidth('benefitAdminForm:resultsTable','30%:30%:30%:10%');
			}	
			document.getElementById('emptymsg').style.visibility = 'hidden';	
		}
		else {
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
