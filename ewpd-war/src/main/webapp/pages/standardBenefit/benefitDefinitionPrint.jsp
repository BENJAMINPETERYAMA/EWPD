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

<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Print Benefit Definition</TITLE>

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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>


<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</head>
<f:view>
	<BODY>
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
			<TD><h:form styleClass="form" id="BenefitDefinitionPrintForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">

					<TR>
						<TD colspan="1" valign="top" class="ContentArea" width="963"><!-- Table containing Tabs -->
<DIV id="noBEndefDiv"><TABLE width="100%" id="noBendefTable" cellpadding="0" cellspacing="1" bgcolor="#cccccc" border="0">

							<tbody>
								<tr>
									<td>
									<STRONG><h:outputText
										value="Associated Benefit Definitions"></h:outputText></STRONG>
									</td>
								</tr>
<tr class="dataTableColumnHeader"><td><strong><h:outputText value="No Associated Benefit Definitions."></h:outputText>
</strong></td>
</tr>
</tbody>
</TABLE>
</DIV>
<DIV id="bendefDiv">
						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <br>

						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

							<tbody>
								<tr>
									<td>
									<DIV id="panel2Header" class="tabTitleBar"
										style="position:relative;width:100% "><STRONG><h:outputText
										value="Associated Benefit Definitions"></h:outputText></STRONG></DIV>
									</td>
								</tr>

								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<td align="left" width="40%"><h:outputText
													value="Description"></h:outputText></td>
												<td align="left" width="20%"><h:outputText
													value="Effective Date"></h:outputText></td>
												<td align="left" width="20%"><h:outputText
													value="Expiry Date"></h:outputText></td>
												<td align="left" width="20%"><h:outputText
													value="Tier Definition"></h:outputText></td>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>
								<TR>
									<TD>

									<DIV id="searchResultdataTableDiv"
										style="overflow: auto;"><!-- Search Result Data Table -->
									<h:dataTable
										rendered="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList != null}"
										styleClass="outputText"
										headerClass="dataTableHeader" id="benefitDefinitionsTable"
										var="singleValue" cellpadding="3" width="100%" cellspacing="1"
										value="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList}">

										<h:column>
											<h:outputText id="desc" value="#{singleValue.description}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effDate"
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
											<h:outputText id="tier" value="#{singleValue.tierDefinitions}"> 												
											</h:outputText>
										</h:column>

									</h:dataTable></DIV>
<BR>
<br>

									</TD>
								</TR>
							</tbody>

						</TABLE>

						</FIELDSET>
						<FIELDSET style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{benefitDefinitionBackingBean.state}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText value="#{benefitDefinitionBackingBean.status}" /></td>

									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td><h:outputText value=":" /></td>
										<td><h:outputText
											value="#{benefitDefinitionBackingBean.version}" /></td>

									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>	
</div>
						</TD>
					</TR>
				</TABLE>






				<!--	End of Page data	-->


				<!-- Space for hidden fields -->

				<!-- End of hidden fields  -->

			</h:form></TD>
		</TR>
	</TABLE>
	</BODY>
</f:view>
<script language="javascript">
if(null == document.getElementById('BenefitDefinitionPrintForm:benefitDefinitionsTable')){
			document.getElementById('bendefDiv').style.visibility = 'hidden';
			document.getElementById('bendefDiv').innerHTML = '';
			document.getElementById('noBEndefDiv').style.visibility = 'visible';	
			document.getElementById('noBendefTable').style.height = '50px';
	}else{
			document.getElementById('bendefDiv').style.visibility = 'visible';
			document.getElementById('noBEndefDiv').style.visibility = 'hidden';
			document.getElementById('noBEndefDiv').innerHTML = '';	
			setColumnWidth('BenefitDefinitionPrintForm:benefitDefinitionsTable','40%:20%:20%:20%');
	}
		
			
</script>

</HTML>
<script>window.print();</script>
