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

<TITLE>Non Adjudication Benefit Mandates Print</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">

		<tr>
			<td><h:form styleClass="form" id="nonAdjBenefitMandatePrintForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">


						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<br>
						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<tr>
										<TD><h:outputText value="Associated Non Adj Benefit Mandates"></h:outputText>
										</TD>
									</tr>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="25%"><h:outputText
												value="Optional Identifier"></h:outputText></td>
											<td align="left" width="25%"><h:outputText
												value="Effective Date"></h:outputText></td>
											<td align="left" width="25%"><h:outputText
												value="Expiry Date"></h:outputText></td>
											<td align="left" width="25%"><h:outputText value="Mandate"></h:outputText>
											</td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>
							<tr>
								<TD><!-- Search Result Data Table -->
								<div id="searchResultdataTableDiv"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									rendered="#{benefitMandatesBackingBean.associatedbenefitMandatesList != null}"
									value="#{benefitMandatesBackingBean.associatedbenefitMandatesList}"
									border="0"
									width="100%">
									<h:column>
										<h:outputText id="identifier"
											value="#{singleValue.optionalIdentifier}"></h:outputText>
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
									<h:column>
										<h:outputText id="mandate" value="#{singleValue.mandate}"></h:outputText>
									</h:column>

								</h:dataTable></div>
								</TD>
							</tr>
						</table>
						<!--	End of Page data	--></fieldset>
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

	</table>

	</BODY>
</f:view>

<script language="javascript">
	initialize();

	function initialize(){
		if(document.getElementById('nonAdjBenefitMandatePrintForm:searchResultTable') != null) {
			setColumnWidth('nonAdjBenefitMandatePrintForm:searchResultTable', '25%:25%:25%:25%');
			setColumnWidth('resultHeaderTable', '25%:25%:25%:25%');
		}else {
			document.getElementById("resultHeaderDiv").style.visibility = 'hidden';
		}
	}
	
</script>
<script>
window.print();

</script>
</HTML>
