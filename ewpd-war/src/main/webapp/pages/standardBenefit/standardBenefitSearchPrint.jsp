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

<TITLE>Search Benefit Print</TITLE>

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
			<h:inputHidden id="dummyId" value="#{StandardBenefitSearchBackingBean.benefitSearchPrint}"></h:inputHidden>
			
		</tr>
		<tr>
			<td><h:form id="StandarsBenefitsSearchPrintForm">
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:10px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:96.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{StandardBenefitSearchBackingBean.printBreadCrumbText}">
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
							<td valign="top" class="ContentArea">
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
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
												<TD align="left" ><h:outputText value="Name"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Version"></h:outputText>
												</TD>
												<TD align="left" ><h:outputText
													value="Description"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Status"></h:outputText>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" 
										rendered="#{StandardBenefitSearchBackingBean.locateResultList != null}"
										value="#{StandardBenefitSearchBackingBean.locateResultList}">
										<h:column>
											<h:outputText id="Minorheading"
												value="#{singleValue.benefitIdentifier}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="Verisonnumber"
												value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="Updateddate"
												value="#{singleValue.description}"></h:outputText>
											
										</h:column>
										<h:column>
											
											<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
										</h:column>
										
									</h:dataTable></DIV>
									</TD>
								</TR>
								
							</TABLE></DIV></DIV>
							</td>
		</tr>
		
	</table></h:form></td></tr></table>
	</BODY>

</f:view>
<script language="javascript">
		initialize();
	function initialize(){
		var tableobject = document.getElementById('StandarsBenefitsSearchPrintForm:searchResultTable');
		if(tableobject!=null){
			setColumnWidth('StandarsBenefitsSearchPrintForm:searchResultTable','25%:10%:35%:30%');
			setColumnWidth('resultHeaderTable','25%:10%:35%:30%');
		}
		
	}
	
window.print();
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="standardBenefitSearch" /></form>
</HTML>
