<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<TITLE>Product Search Print</TITLE>
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

</HEAD>

<f:view>
	<body>
	<h:inputHidden id="hidden1"
		value="#{productSearchBackingBean.productSearchPrint}"></h:inputHidden>
	<hx:scriptCollector id="scriptCollector1">

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			
			<TR>
				<TD><h:form id="searchPrintForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:18px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{productSearchBackingBean.printBreadCrumbText}">
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
							
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Name"></h:outputText></TD>
												<TD align="left"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left"><h:outputText value="Effective Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Expiry Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:280px;width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										rendered="#{productSearchBackingBean.searchResultList != null}"
										value="#{productSearchBackingBean.searchResultList}"
										border="0"
										width="100%">
										<h:column>
											<h:outputText id="prodName"
												value="#{singleValue.productName}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="productVersion"
												value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effectDate"
												value="#{singleValue.effectiveDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="expDate" value="#{singleValue.expiryDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											
										</h:column>
										<h:column>
											<h:outputText id="productStatus"
												value="#{singleValue.status}"></h:outputText>
										</h:column></h:dataTable>
										</DIV>
									</TD>
								</TR>
								
							</TABLE>
							</DIV>
							</DIV>
							
							</TD>
						</TR>
					</TABLE>
					
				</h:form></TD>
			</TR>
			
		</TABLE>
	</hx:scriptCollector>
	</body>
</f:view>
<script language="JavaScript">
		if(document.getElementById('searchPrintForm:searchResultTable') != null) {		
			setColumnWidth('resultHeaderTable','32%:7%:11%:10%:19%:21%');
			setColumnWidth('searchPrintForm:searchResultTable','32%:7%:11%:10%:19%:21%');	
			//showResultsTab();
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}
				  
</script>

<script>window.print();</script>
</HTML>
