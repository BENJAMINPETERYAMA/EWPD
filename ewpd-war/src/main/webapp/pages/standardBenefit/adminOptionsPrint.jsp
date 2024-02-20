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
				<TD><h:form styleClass="form" id="adminOptionForm">
				<h:inputHidden value="#{adminOptionBackingBean.valuesFromSessionForBenefit}"></h:inputHidden>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD colspan="2" valign="top" class="ContentArea">
							
							<!-- Table containing Tabs -->
							
							<!-- End of Tab table -->

							

							<!--	Start of Table for actual Data	-->
							<DIV id="administrationPeriodDiv" align="center">
							<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD width="20%"><h:outputText
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
							
						<div id="adminDiv" align="center" >	<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><STRONG><h:outputText value="Associated Admin Options"></h:outputText></STRONG>
											</TD>
										</TR>
							<tr class="dataTableColumnHeader">
											<TD><STRONG><h:outputText rendered="#{adminOptionBackingBean.adminOptionList == null}"
										value="No Benefit Administration Option Available."
										styleClass="dataTableColumnHeader"></h:outputText></STRONG>
											</TD>
							</tr>
									
						</TABLE></div>

							<DIV id="searchResultdataTableDiv"  align="center">
<FIELDSET
								style="margin-left:5px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
								<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><STRONG><h:outputText value="Associated Admin Options"></h:outputText></STRONG>
											</TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="25%"><h:outputText value="Sequence "></h:outputText>
														</TD>												
												<TD align="left" width="25%"><h:outputText value="Name "></h:outputText>
														</TD>
												
												<TD align="left" width="25%"><h:outputText
													value="Admin Level "></h:outputText></TD>
												<TD align="left" width="25%"><h:outputText
													value="Benefit Level "></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<div id="paneltable" >
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1"  cellpadding="3"
										value="#{adminOptionBackingBean.adminOptionList}"
										rendered="#{adminOptionBackingBean.adminOptionList != null}"
										border="0">
										<h:column>
											
											<h:outputText id="SequenceNo"
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
</FIELDSET>
							</DIV>
							<!--	End of Page data	-->
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->				
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
initialize();
		function initialize(){
			if(document.getElementById('adminOptionForm:searchResultTable') != null) {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
				document.getElementById('adminDiv').style.visibility = 'hidden';
				document.getElementById('adminDiv').innerHTML = '';
				setColumnWidth('adminOptionForm:searchResultTable','25%:25%:25%:25%');
			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('adminDiv').style.visibility = 'visible';
				document.getElementById('searchResultdataTableDiv').innerHTML = '';
			}
		}	
window.print();
</script>

</html>