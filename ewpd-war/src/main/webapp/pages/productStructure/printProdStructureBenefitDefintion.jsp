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
<script>window.print();</script>
<TITLE>Print Benefit</TITLE>
<BASE target="_self" />
</HEAD>

<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>&nbsp;</TD>

		</TR>
		<TR>
			<TD>
			<FIELDSET
				style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
			<h:outputText id="breadcrumb"
				value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
			</h:outputText></FIELDSET>
			</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitDefinitionForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<h:inputHidden id="getValue"
							value="#{productStructureBenefitDefenitionBackingBean.printBenefitDefinition}"></h:inputHidden>
						<TD colspan="2" valign="top" class="ContentArea"><h:outputText
							value="No Standard Definitions Available."
							rendered="#{productStructureBenefitDefenitionBackingBean.benefitDefinitonsList == null}"
							styleClass="dataTableColumnHeader" />
						<div id="benefitDefitionsDiv">
						<table width="80%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="0">
							<tr>
								<td valign="top" class="ContentArea">
								<fieldset
									style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0">
									<TR>
										<TD colspan="1"><!--  <SPAN id="stateCodeStar"><STRONG><h:outputText
											value="121212121Associated Benefit Levels" /></STRONG></SPAN>-->

										<div id="panel2Header" class="tabTitleBar"
											style="position:relative;width:100% "><STRONG>Associated
										Benefit Levels</STRONG></div>

										</TD>
									</TR>

									<TR align="left">

										<TD class="ContentArea" align="left" valign="top" width="100%">

										<table class="smallfont" id="resultsTable" width="100%"
											cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td>
												
												<div id="resultHeaderDiv">
												<TABLE id="headerTable" width="100%" border="0"
													cellpadding="1" cellspacing="1" bgcolor="#cccccc">
													<tr class="dataTableOddRow" >
														<td width="19%"><strong><h:outputText value="Description" /></strong></td>
														<td width="19%"><strong><h:outputText value="Term" /></strong></td>
														<td width="20%"><strong><h:outputText value="Frequency - Qualifier" /></strong></td>
														<td width="8%"><strong><h:outputText value="PVA" /></strong></td>
														<td width="8%"><strong><h:outputText value="Format" /></strong></td>
														<td width="8%"><strong><h:outputText value="Benefit Value" /></strong></td>
														<td width="18%"><strong><h:outputText value="Reference" /></strong></td>
													</tr>
												</TABLE>
												</div>
												</td>
											</tr>
											<tr>
												<td >
												<div id="searchResultdataTableDiv" style=" width:100%;"><h:dataTable
													cellpadding="5"  cellspacing="1" 
													id="bComponentDataTable"  rowClasses="dataTableOddRow" bgcolor="#cccccc"
													rendered="#{productStructureBenefitDefenitionBackingBean.printBenftDefnList != null}"
													value="#{productStructureBenefitDefenitionBackingBean.printBenftDefnList}"
													var="singleValue"  width="100%" >
													<h:column>
														<h:outputText id="level" value="#{singleValue.levelDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="Term" value="#{singleValue.termDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="Frequency" value="#{singleValue.benefitFrequency}" />													
														<h:outputText id="Qualifier" value="#{singleValue.qualifierDesc}" />
													</h:column>
													<h:column>
														<h:outputText id="pvaVal"
															value="#{singleValue.providerArrangementId}" />
													</h:column>
													<h:column>
														<h:outputText id="dataTypeLGND"
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
						<!--	End of Page data	--></TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:inputHidden id="levelsToDeleteHidden"
					value="#{productStructureBenefitDefenitionBackingBean.levelsToDelete}" />
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

<script language="javascript">
			if(document.getElementById('benefitDefinitionForm:bComponentDataTable') != null){
				setColumnWidth('benefitDefinitionForm:bComponentDataTable','19%:19%:20%:8%:8%:8%:18%');	
				//setColumnWidth('benefitDefinitionForm:panelTable','19%:19%:20%:8%:8%:8%:18%');
			}else{
				document.getElementById('benefitDefitionsDiv').style.visibility = 'hidden';
			}

</script>

</HTML>
