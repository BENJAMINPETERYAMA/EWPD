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
<TITLE>Print Admin Methods</TITLE>
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


				<h:form styleClass="form" id="adminMethodForm">
						<table width="100%">
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
						</table>
							<h:inputHidden id="loadPageforPrint" value="#{adminMethodBackingBean.loadProductStructPageForPrint}"></h:inputHidden>
							
							<TD colspan="2" valign="top" class="ContentArea">
			<br>
						<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><h:outputText style="font-weight:bold"
												value="Processing Methods"></h:outputText></TD>
										</TR>
									</TABLE>	
						<div id="noAdminProcess" style="font-size: 11"><BR>
						<h:outputText value="No Administration Process Available." /></div>

							<!--<h:outputText value="#{benefitAdminProcessBackingBean.srsName}"></h:outputText> -->
						<DIV id="resultHeaderDiv">
									
									<TABLE cellspacing="1" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												
												<TD align="left" width="35%"><h:outputText
													value="Admin Method "></h:outputText></TD>
                                                <TD align="left" width="25%"><h:outputText value=""></h:outputText>
												</TD>
												<TD align="left" width="40%"><h:outputText
													value="Reference "></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
								
								
									<h:dataTable styleClass="outputText"
										headerClass="dataTableHeader" id="adminProcess"
										var="adminmethods" cellpadding="3" width="100%"
										cellspacing="1" rendered="true"
										value="#{adminMethodBackingBean.adminMethodsList}"
										 border="0">

										<h:column>
											<h:outputText id="srsName" value="#{adminmethods.spsName}">
											</h:outputText>
											<h:inputHidden id="srsId" value="#{adminmethods.spsId}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="methodName" value="#{adminmethods.adminMethodDesc}">
											</h:outputText>
										</h:column> 	
										<h:column>
								<h:outputText id="reference" value="#{adminmethods.reference}">
								</h:outputText>
							</h:column> </h:dataTable>
							</DIV>
					
					</fieldset>
		</h:form>
		
	</BODY>
</f:view>
<script language="javascript">
		if(document.getElementById('adminMethodForm:adminProcess')!= null){		
		setColumnWidth('adminMethodForm:adminProcess', '25%:35%:40%');
	}
var divObj = document.getElementById('noAdminProcess');
var divHeaderObj = document.getElementById('resultHeaderDiv');
var tableObj = document.getElementById('adminMethodForm:adminProcess:0:srsId');
if (tableObj != null) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';

		}
window.print();

</script>
</html>


