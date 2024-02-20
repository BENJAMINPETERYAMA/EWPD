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

<TITLE>Print Benefit Administration</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">

		<tr>
			<td><h:form styleClass="form" id="benefitAdminForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{standardBenefitBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;
                        </TD>
                  </TR>
					<TR>



						<TD colspan="2" valign="top" class="ContentArea">




						<br />
						<div id="noResult">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<tr>
								<TD><b> <h:outputText value="Associated Benefit Administrations"></h:outputText>
								</b></TD>
							</tr>
						</TABLE>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR class="dataTableColumnHeader">
									<td align="left"><h:outputText value="No Associated Benefit Administrations."></h:outputText>
									</td>

								</TR>
							</TBODY>
						</TABLE>
						</div>

						<div id="resultHeaderDiv">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;
							width:100%">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<tr>
								<TD><b> <h:outputText value="Associated Benefit Administrations"></h:outputText>
								</b></TD>
							</tr>
						</TABLE>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR class="dataTableColumnHeader">
									<td align="left" width="25%"><h:outputText value="Description"></h:outputText>
									</td>
									<td align="left" width="25%"><h:outputText
										value="Effective Date"></h:outputText></td>
									<td align="left" width="25%"><h:outputText value="Expiry Date"></h:outputText>
									</td>

								</TR>
							</TBODY>
						</TABLE>

						<div id="panel2Content"
							style="height:100%;width:100%;position:relative;z-index:1;font-size:10px; overflow:auto;">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td align="left"><h:dataTable styleClass="outputText"
									headerClass="dataTableHeader" id="resultsTable"
									var="singleValue" cellpadding="3" width="100%" cellspacing="1"
									rendered="#{benefitAdministrationBean.benefitAdministrationListForViewandPrint!= null}"
									value="#{benefitAdministrationBean.benefitAdministrationListForViewandPrint}"
									border="0">
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
						</div>

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
<script language="JavaScript">
		if(document.getElementById('benefitAdminForm:resultsTable') != null) {
			document.getElementById('resultHeaderDiv').style.visibility = 'visible';
			document.getElementById('noResult').style.visibility = 'hidden';
			document.getElementById('noResult').innerHTML = '';
			setColumnWidth('benefitAdminForm:resultsTable','30%:30%:30%:10%');
			setColumnWidth('resultHeaderTable','30%:30%:30%:10%');		
		}else{
			document.getElementById('noResult').style.visibility = 'visible';
			document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
			document.getElementById('resultHeaderDiv').innerHTML = '';
		}
</script>
<script>
		if(document.getElementById('benefitAdminForm:resultsTable')!= null){
			document.getElementById('benefitAdminForm:resultsTable').onresize = syncTables;
			syncTables();
			setColumnWidth('benefitAdminForm:resultsTable', '25%:25%:25%:25%')
		}
		
</script>
<script>window.print();</script>
</HTML>
