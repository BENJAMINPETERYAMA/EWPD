<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/ComponentBenefitAdministration.java" --%><%-- /jsf:pagecode --%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 40%;
}

.shortDescriptionColumn {
	width: 15%;
}

.longDescriptionColumn {
	width: 30%;
}
</style>

<TITLE>Component Benefit Administration Print</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<h:inputHidden
		value="#{BenefitComponentAdministrationBackingBean.loadQuestionForView}" />
	<h:form styleClass="form" id="bcBenefitAdministrationPrintForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>

			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left: 15px; margin-right: -10px; padding-bottom: 1px; padding-top: 1px; width: 98%">
				<h:outputText id="breadcrumb"
					value="#{benefitComponentBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">

						<div id="emptymsg">
						<fieldset
							style="margin-left: 6px; margin-right: -4px; padding-bottom: 1px; padding-top: 6px; width: 100%">
						<h:outputText value="No questions Available."
							styleClass="dataTableColumnHeader" /></fieldset>
						</div>
						<div id="resultHeaderDiv" style="width: 100%;">
						<fieldset
							style="margin-left: 6px; margin-right: -4px; padding-bottom: 1px; padding-top: 6px; width: 99%">

						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>

								<div id="resultHeaderDiv1" style="width: 100%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<tr>
										<TD><b><h:outputText value="Selected Question"></h:outputText></b></TD>
									</tr>
								</TABLE>
								<TABLE cellspacing="1" cellpadding="3" id="resultHeaderTable"
									border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="40%"><b><h:outputText
												value="Question"></h:outputText></b></td>
											<td align="left" width="30%"><b><h:outputText
												value="Answer"></h:outputText></b></td>
											<td align="left" width="30%"><b><h:outputText
												value="Reference"></h:outputText></b></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>
							</tr>

							<TR>
								<TD width="100%"><h:dataTable headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									cellspacing="1"
									value="#{BenefitComponentAdministrationBackingBean.viewQuestionnaireList}"
									border="0" width="100%"
									columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">
									<h:column>
										<h:outputText id="questionDesc"
											value="#{singleValue.questionName}"></h:outputText>

									</h:column>
									<h:column>
										<h:outputText id="answerDesc"
											value="#{singleValue.selectedAnswerDesc}"></h:outputText>

									</h:column>
									<h:column>
										<h:outputText id="referenceDesc"
											value="#{singleValue.referenceDesc}"></h:outputText>

									</h:column>
								</h:dataTable></TD>
							</TR>

						</TABLE>
						</fieldset>
						</div>
						<!--	End of Page data	--></TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --> </h:form></td>
			</tr>

		</table>
	</BODY>
</f:view>
<script>
	// Space for page realated scripts
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId) {
		hiddenIdObj = document
				.getElementById('bcBenefitAdministrationPrintForm:searchResultTable');
		if (hiddenIdObj.rows.length == 0) {
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		} else {
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
			setColumnWidth(
					'bcBenefitAdministrationPrintForm:searchResultTable',
					'40%:30%:30%');
		}
	}
</script>
<script>
	window.print();
</script>
</HTML>
