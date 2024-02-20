<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractBasicSearch.java" --%><%-- /jsf:pagecode --%>
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


<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>


		</tr>
		<tr>
			<td><h:form id="ContractBasicSearchPrintForm">
			<h:inputHidden value="#{contractSearchBackingBean.printPageLoad}" id="printPageLoad"></h:inputHidden>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<tr><td>&nbsp; </td></tr>
						<TR>
							<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:97.5%">
		
		                                    <h:outputText id="breadcrumb" 
		
		                                          value="#{contractSearchBackingBean.locateBreadCrumb}">
		
		                                    </h:outputText>
		
		                              </FIELDSET>
		
		
							</TD>
						</TR>
						<tr><td>&nbsp; </td></tr>
						<TR>

							<td valign="top" class="ContentArea">


							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<tr>
									<td width="200"></td>
									<td width="200"></td>
									<td width="100%"></td>
								</tr>
							</TABLE>
							<!-- End of Tab table -->
							<DIV id="accordionTest" style="margin:5px;">


							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Search Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="15%"><h:outputText
													value="ContractId"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText
													value="No of Date Segments"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText
													value="Effective Date"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText
													value="Expiry Date"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText
													value="Contract Type"></h:outputText></TD>
												<TD align="left" width="15%"><h:outputText value="Contract Status"></h:outputText></TD>
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
										rendered="#{contractSearchBackingBean.locateResultList != null}"
										value="#{contractSearchBackingBean.locateResultList}">
										<h:column>
											<h:outputText id="ContractId"
												value="#{singleValue.contractId}"></h:outputText>
										</h:column>
										<h:column>

											<h:outputText id="DateSegment"
												value="#{singleValue.dateSegmentCount}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="StartDate" value="#{singleValue.startDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="EndDate" value="#{singleValue.endDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="contractKey"
												value="#{singleValue.contractKey}"></h:inputHidden>
											<h:inputHidden id="contractIdHid"
												value="#{singleValue.contractId}">
											</h:inputHidden>
											<h:inputHidden id="versionHid" value="#{singleValue.version}">
											</h:inputHidden>
											<h:inputHidden id="statusHid" value="#{singleValue.status}">
											</h:inputHidden>
											<h:inputHidden id="contractDateSegmentSysHId"
												value="#{singleValue.dateSegmentId}" />
											<h:inputHidden id="contractTypeHId"
												value="#{singleValue.contractType}" />
											<h:inputHidden id="dateSegmentCountHId"
												value="#{singleValue.dateSegmentCount}" />
											<h:inputHidden id="productSysHId"
												value="#{singleValue.productSysId}" />
										</h:column>
										<h:column>
											<h:outputText id="Version" value="#{singleValue.version}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="ContractType"
												value="#{singleValue.contractType}"></h:outputText>

										</h:column>

										<h:column>
											<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>

										</h:column>
										
									</h:dataTable></DIV>
									</TD>
								</TR>

							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
				</TABLE></td>
		</tr>
		<tr>
  
			</h:form>
			
		</tr>
	</table>
	</BODY>

</f:view>
<script language="javascript">



window.print();
setColumnWidth('resultHeaderTable','10%:13%:13%:13%:8%:13%:25%');
setColumnWidth('ContractBasicSearchPrintForm:searchResultTable','10%:13%:13%:13%:8%:13%:25%')
// for delete contract



	

</script>
</HTML>
