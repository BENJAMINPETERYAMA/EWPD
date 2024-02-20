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
				
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionsForm">
					<h:inputHidden value="#{adminOptionBackingBean.loadProductStructureAdminOptions}"/>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
 				<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
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
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Starts Tab table -->
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
                            <TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><B><h:outputText value="Admin Options"></h:outputText></B>
											</TD>
										</TR>
                                     
									</TABLE>                           
							<!--	Start of Table for actual Data	-->
								<br><div id="adminDiv" align="left" ><h:outputText rendered="#{adminOptionBackingBean.adminOptionListForPrint == null}"
										value="No Administration Option Available."	/></div>
							<DIV id="searchResultdataTableDiv" >
                             
								<TABLE width="100%" cellspacing="0" cellpadding="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="35%"><h:outputText value="Name "></h:outputText>
												</TD>
												<TD align="left" width="35%"><h:outputText
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
									<div id="paneltable" >
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" 
										value="#{adminOptionBackingBean.adminOptionListForPrint}"
										rendered="#{adminOptionBackingBean.adminOptionListForPrint != null}"
										border="0">

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
				<!-- <TD><%@include file="../navigation/bottom.jsp"%></TD>-->
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
initialize();
		function initialize(){
			if(document.getElementById('adminOptionsForm:searchResultTable').rows.length != 0) {
				setColumnWidth('adminOptionsForm:searchResultTable','35%:35%:30%');
			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			}
		}

	window.print();
</script>
</HTML>