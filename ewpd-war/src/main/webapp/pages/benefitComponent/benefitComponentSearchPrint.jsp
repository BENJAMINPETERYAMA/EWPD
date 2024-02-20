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
<TITLE>Benefit Component Search Print</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>

</HEAD>

<body>
<f:view>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{BenefitComponentSearchBackingBean.benefitComponentSearchPrint}">
			</h:inputHidden>
			
		</tr>
		<tr>
			<td><h:form id="benefitComponentSearchPrintForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{BenefitComponentSearchBackingBean.printBreadCrumbText}">
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
							<td>
									
									<div id="panel2">
									<div id="panel2Header" class="tabTitleBar"
										style="position:relative; cursor:hand;">Locate Results</div>
									<div id="panel2Content" class="tabContentBox"
										style="position:relative;font-size:10px;width:100%"><br>
									<table cellpadding="0" cellspacing="0" width="100%" border="0">
										<tr>
											<td>


											<div id="resultHeaderDiv" style="width:100%;">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<td align="left" ><h:outputText value="Name"></h:outputText>
														</td>
														<td align="left" ><h:outputText value="Version"></h:outputText>
														</td>
														<td align="left" ><h:outputText value="Effective Date"></h:outputText>
														</td>
														<td align="left" ><h:outputText value="Expiry Date"></h:outputText></td>
														<td align="left" ><h:outputText value="Status"></h:outputText>
														</td>
														
													</TR>
												</TBODY>
											</TABLE>
											</div>



											</td>
										</tr>
										<tr>
											<TD><!-- Search Result Data Table -->
											<div id="searchResultdataTableDiv"
												style="height:250px; width:100%;"><h:dataTable
												headerClass="dataTableHeader" id="searchResultTable"
												var="singleValue" cellpadding="3" cellspacing="1"
												rendered="#{BenefitComponentSearchBackingBean.searchResultList != null}"
												value="#{BenefitComponentSearchBackingBean.searchResultList}"
												
												width="100%">
												<h:column>
													<h:outputText id="bcname" value="#{singleValue.name}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
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
													<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
												</h:column>
											</h:dataTable></div>
											</TD>
										</tr>
									</table>
									</div>
									</div>
									</TD>
								</TR>
							</TABLE>
							</h:form></td>
						</tr>
						
				</table>
				</f:view>
<script language="JavaScript">
		if(document.getElementById('benefitComponentSearchPrintForm:searchResultTable') != null) {
			setColumnWidth('resultHeaderTable','25%:10%:15%:15%:35%');
			setColumnWidth('benefitComponentSearchPrintForm:searchResultTable','25%:10%:15%:15%:35%');
			//showResultsTab();
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
window.print();
</script>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="benefitComponentSearchResultPrint" >
	</form>
</BODY>
</HTML>

