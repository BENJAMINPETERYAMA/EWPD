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
<TITLE>Print Benefit Level</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:form styleClass="form" id="benefitLevelForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
<TR>
                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:20px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:96%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{standardBenefitBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
 					<tr>
						<h:inputHidden id="getValue"
							value="#{benefitLevelBackingBean.printBenefitLevelHiddenId}"></h:inputHidden>


						<td style="outputText" width="40%" align="left"><br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <h:outputText
							value="Benefit Period" styleClass="outputText" />
						&nbsp;&nbsp;&nbsp;&nbsp;: <h:outputText id="benefitDefinition"
							value="#{benefitLevelBackingBean.benefitDefinition}"
							styleClass="outputText" /></td>
					</tr>
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">
<DIV id="NoBenLevel">
					<TABLE id="headerTable" bgcolor="#cccccc" width="100%" border="0"
													cellpadding="1" cellspacing="1">
									<TR>
										<TD bgcolor="#cccccc" colspan="3" ><STRONG><h:outputText
											value="Associated Benefit Levels" rendered="#{benefitLevelBackingBean.printBenftDefnList == null}"/></STRONG></TD>
									</TR>
													<tr class="dataTableOddRow">
														<td><h:outputText
							value="No Benefit Levels Available."
							rendered="#{benefitLevelBackingBean.printBenftDefnList == null}"
							styleClass="dataTableColumnHeader" /></td>
													</tr>
												</TABLE>
</DIV>
						<div id="benefitDefitionsDiv">
						<table width="100%" border="0" bordercolor="green" cellspacing="1"
							cellpadding="1">
							<tr>
								<td valign="top" class="ContentArea">
								<fieldset style="width: 100%">

								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0" bordercolor="red">
									<td>
									<table width="100%" height="50%" cellspacing="1" cellpadding="3" border="0">
									<TR>
										<TD bgcolor="#cccccc" height="50%"><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></TD>
									</TR></table>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<table class="smallfont" id="resultsTable" width="100%"
											cellpadding="1" cellspacing="1" border="0">
											<tr>
												<td>
												<div id="resultHeaderDiv">
												<TABLE id="headerTable" width="100%" border="0"
													cellpadding="1" cellspacing="1">
													<tr class="dataTableOddRow">
														<td><strong><h:outputText value="Sequence" /></strong></td>
														<td><strong><h:outputText value="Description" /></strong></td>
														<td><strong><h:outputText value="Term" /></strong></td>
														<td><strong><h:outputText value="Frequency - Qualifier" /></strong></td>
														<td><strong><h:outputText value="PVA" /></strong></td>
														<td><strong><h:outputText value="Format" /></strong></td>
														<td><strong><h:outputText value="Benefit Value" /></strong></td>
														<td><strong><h:outputText value="Reference" /></strong></td>
													</tr>
												</TABLE>
												</div>
												</td>
											</tr>
											<tr>
												<td bordercolor="#cccccc">
												<div id="searchResultdataTableDiv"
													style="width:100%;position:relative;z-index:1;font-size:10px;">
												<!-- Search Result Data Table --> <h:dataTable
													cellspacing="1" id="bComponentDataTable"
													rendered="#{benefitLevelBackingBean.printBenftDefnList != null}"
													value="#{benefitLevelBackingBean.printBenftDefnList}"
													var="singleValue" cellpadding="3" width="100%">
													<h:column>
														<h:outputText id="seq" value="#{singleValue.sequenceNo}" />
													</h:column>
													<h:column>
														<h:outputText id="level" value="#{singleValue.levelDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="type" value="#{singleValue.termDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="frequency"
															value="#{singleValue.benefitFrequency}" />
														<h:outputText id="space"
															value=" " />
														<h:outputText id="qualifier"
															value="#{singleValue.qualifierDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="pvaVal"
															value="#{singleValue.providerArrangementId}" />
													</h:column>
													<h:column>
														<h:outputText id="dataTypeVal"
															value="#{singleValue.dataTypeDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="benfitVal"
															value="#{singleValue.benefitValue}" />
													</h:column>
													<h:column>
														<h:outputText id="reference"
															value="#{singleValue.referenceDesc}" />
													</h:column>
												</h:dataTable></div>

												</td>
											</tr>
											<tr>
												<TD></TD>
											</tr>
											<tr>
												<TD></TD>
											</tr>

										</table>
										</TD>


									</TR>
								</TABLE>
								<!--	Start of Table for actual Data	--></fieldset>
								</td>
							</tr>
						</table>
						</div>
						<%--/DIV> --%> <!--	End of Page data	--></TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />

					<!-- End of hidden fields  -->
				</h:commandLink>
			</h:form></td>

		</tr>
	</table>
	</BODY>
</f:view>
<script language="JavaScript" type="text/javascript">

	var tableObject = document.getElementById('benefitLevelForm:bComponentDataTable');

	if(tableObject!= null ){
		if(tableObject.rows.length > 0){
		 	var divObj = document.getElementById('benefitLevelForm:searchResultdataTableDiv');	
			document.getElementById('benefitDefitionsDiv').style.visibility = 'visible';
			document.getElementById('NoBenLevel').style.visibility = 'hidden';
			document.getElementById('NoBenLevel').innerHTML = '';	
			document.getElementById('headerTable').style.height = '50px';
			setColumnWidth('benefitLevelForm:bComponentDataTable','9%:21%:11%:20%:7%:8%:8%:16%');	
			setColumnWidth('headerTable','9%:21%:11%:20%:7%:8%:8%:16%');
	}
}else{
			document.getElementById('benefitDefitionsDiv').style.visibility = 'hidden';
			document.getElementById('benefitDefitionsDiv').innerHTML = '';	
			document.getElementById('NoBenLevel').style.visibility = 'visible';
			document.getElementById('headerTable').style.height = '50px';

}
	
</script>


<script>window.print();</script>
</HTML>
