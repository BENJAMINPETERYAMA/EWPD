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

<STYLE type="text/css">
.gridColumn75{
	width: 75%;
	text-align:left;
}
.gridColumnRight25{
	width: 25%;
	text-align:right;
}
.gridColumn25{
	width: 25%;
	text-align:left;
}
.gridColumn23{
	width: 23%;
	text-align:left;
}
.gridColumn22{
	width: 22%;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	text-align:left;
}
.gridColumn19{
	width: 19%;
	text-align:left;
}
.gridColumn16{
	width: 16%;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	text-align:left;
}
.gridColumn12{
	width: 12%;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	text-align:left;
}
.gridColumn9{
	width: 9%;
	text-align:left;
}
.gridColumn8{
	width: 8%;
	text-align:left;
}
.gridColumn7{
	width: 7%;
	text-align:left;
}
.gridColumn5{
	width: 5%;
	text-align:left;
}
.gridColumn3{
	width: 3%;
	text-align:left;
}
.gridColumn0{
	width: 0%;	
}
.gridColumn11{
	width: 11%;	
}
</STYLE>

<TITLE>Contract Benefit Detailed Info</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY onload="return hideNotesColumn(false);">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr><td>&nbsp; </td></tr>
		<TR>
					<TD>  <FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
		<tr>
			<td><h:form styleClass="form" id="contractBenefitGeneralInfoPrint">
				<br />
				<br />

				<fieldset
					style="margin-left:12px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:98%">
				<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">General Information</div>
				<h:inputHidden id="loadForPrint"
					value="#{contractBenefitGeneralInfoBackingBean.valueForPrint}"></h:inputHidden>
				<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
					<TBODY>
						<TR>
						<TR>
							<TD colspan="5">
							<FIELDSET
								style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%">
							<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
							<TABLE border="0" width="100%" cellspacing="0" cellpadding="3">
								<tr>
									<TD width="32%"><h:outputText value="Line of Business" /></TD>
									<TD width="58%" align="left"><h:outputText id="lobHidden"
										value="#{contractBenefitGeneralInfoBackingBean.lob}">
                                    </h:outputText></TD>
								</TR>
								<TR>
									<TD width="32%"><h:outputText value="Business Entity" /></TD>
									<TD width="58%" align="left"><h:outputText id="entityHidden"
										value="#{contractBenefitGeneralInfoBackingBean.businessEntity}">
									</h:outputText></TD>
                                   
								</TR>
								<TR>
									<TD width="32%"><h:outputText value="Business Group" /></TD>
									<TD align="left" width="58%"><h:outputText id="groupHidden"
										value="#{contractBenefitGeneralInfoBackingBean.businessGroup}">
									</h:outputText></TD>
                                   
								</TR>
								<TR>
									<TD width="32%"><h:outputText value="Market Business Unit" /></TD>
									<TD align="left" width="58%"><h:outputText id="marketBusinessUnitHidden"
										value="#{contractBenefitGeneralInfoBackingBean.marketBusinessUnit}">
									</h:outputText></TD>
                                   
								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR>
							<TD width="25%"><h:outputText id="MinorHeadingStar"
								value=" Name" /></TD>
							<TD width="50%"  align="left"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.minorHeading}">
							</h:outputText> </TD>
							
						</TR>
					
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="ruleType"></span><h:outputText value=" Benefit Meaning" /></td>
							<td width="56%"  align="left"><h:outputText id="benefitMeaning"
								value="#{contractBenefitGeneralInfoBackingBean.benefitMeaning}" />
							</td>
						

						</tr>
					
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="creationDateId"></span><h:outputText
								value=" Benefit Type" /></td>
							<td width="56%"  align="left"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}" />
									<h:inputHidden id="txtBenefitId"
										value="#{contractBenefitGeneralInfoBackingBean.benefitType}"></h:inputHidden>
							

						</tr>
						<tr>
							<td valign="top" width="27%"><span class="mandatoryNormal"
								id="benefitCategoryId"></span><h:outputText
								value=" Benefit Category" /></td>
							<td width="56%"  align="left"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}" />
									<h:inputHidden id="txtBenefitCategoryId"
										value="#{contractBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden>
							

						</tr>	
							<TR>
							<TD width="27%" valign="top"><h:outputText id="descriptionStar"
								value=" Description" /></TD>
							<TD width="56%"  align="left"><h:outputText id="txtDescription"
								styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
								value="#{contractBenefitGeneralInfoBackingBean.description}"></h:outputText></TD>
							
						</TR>

						<TR>
							<TD width="27%" valign="top"><h:outputText value="Tier Definition " /></TD>
							<TD width="56%"  align="left"><h:outputText id="txtTier"
								value="#{contractBenefitGeneralInfoBackingBean.tierDefinitionForprint}"></h:outputText></TD>							
						</TR>

							<tr>
							<td valign="top" width="27%"><span class="mandatoryNormal"
								id="ruleType"></span><h:outputText value=" Benefit Rule ID" /></td>
							<td width="56%"  align="left"><h:outputText id="txtRuleType"
								value="#{contractBenefitGeneralInfoBackingBean.ruleType}"></h:outputText>
							<h:inputHidden id="ruleTypeHidden"
								value="#{contractBenefitGeneralInfoBackingBean.ruleIDFetched}"></h:inputHidden></td>

						</tr>
						<TR>
							<TD colspan="5">
							<FIELDSET style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:90%"><LEGEND><FONT color="black">Benefit Level Scope</FONT></LEGEND>
							<TABLE cellpadding="2" cellspacing="0">
								<TR>
									<TD width="21%"><h:outputText id="termEditStar" value="Term" /></TD>
									<TD width="45%"  align="left"><h:outputText id="txtTerm"
										value="#{contractBenefitGeneralInfoBackingBean.term}" /></TD>
								
								</TR>
								<TR>
									<TD width="21%"><h:outputText id="QualifierEditStar"
										value="Qualifier" /></TD>
									<TD width="45%"  align="left"><h:outputText id="txtQualifier"
										value="#{contractBenefitGeneralInfoBackingBean.qualifier}" />
									</TD>
									
								</TR>
								<TR>
									<TD width="21%"><h:outputText id="ProviderArrangementEditStar"
										value="Provider Arrangement" /></TD>
									<TD width="45%"  align="left"><h:outputText id="txtProviderArrangement"
										value="#{contractBenefitGeneralInfoBackingBean.providerArrangement}" />
									</TD>
							
								</TR>
								<TR>
									<TD width="21%"><h:outputText id="DataTypeEditStar"
										value="Data Type" /></TD>
									<TD width="45%"  align="left"><h:outputText id="txtDataType"
										value="#{contractBenefitGeneralInfoBackingBean.dataType}" />
									</TD>
								
								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<tr>
								<td valign="top" width="25%"><span class="mandatoryNormal"
									id="versionId"><h:outputText
										value="Version" /></td>
									<td width="56%"><h:outputText
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}" />
									<h:inputHidden id="versionHidden"
										value="#{contractBenefitGeneralInfoBackingBean.benefitVersion}"></h:inputHidden></td>
								</tr>
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="creationDateId"></span><h:outputText
								value="Created By" /></td>
							<td width="56%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.createdUser}" />
							<h:inputHidden id="txtCreatedUser"
								value="#{contractBenefitGeneralInfoBackingBean.createdUser}"></h:inputHidden></td>
					

						</tr>
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="createdBy"></span><h:outputText value="Created Date" /></td>
							<td width="56%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText><h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="txtCreatedDate"
								value="#{contractBenefitGeneralInfoBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden> <h:inputHidden id="time"
								value="#{requestScope.timezone}">
							</h:inputHidden></td>
						
						</tr>
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="updationDate"></span><h:outputText
								value="Last Updated By" /></td>
							<td width="56%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}" />
							<h:inputHidden id="txtUpdatedUser"
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedUser}"></h:inputHidden></td>
							
						</tr>
						<tr>
							<td valign="top" width="25%"><span class="mandatoryNormal"
								id="updateBy"></span><h:outputText
								value="Last Updated Date" /></td>
							<td width="56%"><h:outputText
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							<h:inputHidden id="txtUpdatedDate"
								value="#{contractBenefitGeneralInfoBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:inputHidden></td>
							
						</tr>

					</TBODY>
				</TABLE>

				<!--	Start of Table for actual Data	--> <!--	End of Page data	--></fieldset></TD>
		</TR>
    <!-- Space for hidden fields -->
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
	<h:commandLink id="hiddenLnk1"
		style="display:none; visibility: hidden;">
		<f:verbatim />
	</h:commandLink>
	<!-- End of hidden fields  -->
	</table>
	</h:form>

	<!--  Standard Benefit Line prints starts here -->
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td><h:form styleClass="form" id="benefitLevelFormPrint">

					<h:inputHidden id="getValue"
							value="#{contractCoverageBackingBean.benefitLevelForPrint}"></h:inputHidden>
					<h:inputHidden value="#{contractCoverageBackingBean.submitFlag}" id="submitFlag"/>

					</tr>
					<TR>
						<td>
						<div id="benefitDefitionsDiv">
						<table width="100%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="1">
							<tr>
								<td valign="top" class="ContentArea">
                                
								<fieldset
									style="margin-left:2px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">								
								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0" bordercolor="red">
									<TR>
										<TD colspan="1" class="ContentArea"><div id="panel1HeaderTier" class="tabTitleBar"><SPAN id="stateCodeStar"><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></SPAN></div></TD>
									</TR>
									<TR>
										<TD colspan="1" class="ContentArea"><DIV id="noBenefitDefinitions"
									style="font-family:Verdana, Arial, Helvetica, sans-serif;
						font-size:11px;font-weight:bold;text-align:center;color:#000000;
						background-color:#FFFFFF;">No
								Benefit Definitions available.</DIV></TD>
									</TR>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<table class="smallfont" id="resultsTable" width="100%"
											cellpadding="1" cellspacing="1" border="0">
											<tr>
												<td bordercolor="#cccccc">
												<div id="resultHeaderDiv" style=background-color:#FFFFFF;>												
												</div>
												
												 <div id="searchResultdataTableDiv1" style="overflow: auto;"><h:dataTable
													cellspacing="2" id="bComponentDataTable" 
													rendered="#{contractCoverageBackingBean.printBenftDefnList != null}"
													value="#{contractCoverageBackingBean.printBenftDefnList}"
													var="singleValue" cellpadding="3" width="100%" border="0">
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Description"/>
                          							</f:facet>
														<h:outputText id="level" value="#{singleValue.levelDesc}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Term"/>
                          							</f:facet>
														<h:outputText id="term"
															value="#{singleValue.termDesc}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Qualifier"/>
                          							</f:facet>
														<h:outputText id="qualifier"
															value="#{singleValue.qualifierDesc}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="PVA"/>
                          							</f:facet>
														<h:outputText id="pvaVal"
															value="#{singleValue.providerArrangementId}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Data Type"/>
                          							</f:facet>
														<h:outputText id="dataTypeLGND"
															value="#{singleValue.dataTypeDesc}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="Benefit Value"/>
                          							</f:facet>
														<h:outputText id="benfitVal"
															value="#{singleValue.benefitValue}" />
													</h:column>
													<h:column>
 													<f:facet name="header">
                              							 <h:outputText value="References"/>
                          							</f:facet>
														<h:outputText id="reference"
															value="#{singleValue.referenceDesc}" />
													</h:column>
												</h:dataTable></div> 
												</td>
											</tr>

											<tr>
												<TD><p>&nbsp;</p><p>&nbsp;</p></TD>
											</tr>
											<tr><td><div id="panel2HeaderTier" class="tabTitleBar"
												style="position:relative;width:100% ">Associated Tiered Benefit Levels</div></td>
											</tr>
											<tr>
												<td bordercolor="#cccccc">
												<DIV id="resultHeaderDivTier"
														style="position:relative;background-color:#FFFFFF;"><h:panelGrid
														id="resultHeaderTable1"
														binding="#{contractCoverageBackingBean.tierHeaderPanelForPrint}"
														rowClasses="dataTableOddRow">
												</h:panelGrid></DIV>
												<div id="searchResultdataTableDiv2" style="overflow: auto;">
													<h:panelGrid id="benresultHeaderTable"
														binding="#{contractCoverageBackingBean.panelForTierPrint}"
														rowClasses="dataTableOddRow">
													</h:panelGrid></div>
												</TD>
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
					<h:commandLink id="showTierLevelSection" style="hidden" action="#{contractCoverageBackingBean.showTieredLevelSection}"> </h:commandLink>
				<!-- Space for hidden fields -->

			</h:form></td>

		</tr>
	</table>

	<!--  standard benefit line print ends here  -->


	<!--    Notes Attachment Print starts here  -->
<div id="noteHeader">
	<table width="99%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:form id="benefitNotePrint">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<h:inputHidden id="loadNote"
							value="#{contractBenefitNotesBackingBean.loadNotesForPrint}"></h:inputHidden>
					</TR>
				</table>
				<br />
				<br />
				<fieldset
					style="margin-left:12px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">
				<!--	Start of Table for actual Data	-->
				<TABLE width="100%" cellspacing="0" cellpadding="0">

					<TR>
						<TD>
						<DIV id="noContractNote"
							style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No
						Notes Attached.</DIV>
						<DIV id="resultHeaderDiv1">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="headerTable" border="0" width="100%">
							<TR>
								<TD><strong><h:outputText value="Associated Notes"></h:outputText></strong></TD>
							</TR>
						</TABLE>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR class="dataTableColumnHeader">
									<TD align="left"><h:outputText value=" Note ID "></h:outputText></TD>
									<td><h:outputText value=" Note Name "></h:outputText></td>
								</TR>
							</TBODY>
						</TABLE>
						</DIV>
						</TD>
					</TR>
					<TR>
						<td colspan="4">

						<DIV id="searchResultdataTableDiv"
							style="height:50px;position:relative;z-index:1;font-size:10px;overflow:auto;">
						<!-- Search Result Data Table --> <h:dataTable
							styleClass="outputText" headerClass="dataTableHeader"
							id="searchResultTable" var="singleValue" cellpadding="3"
							width="100%" cellspacing="1" bgcolor="#cccccc"
							rendered="#{contractBenefitNotesBackingBean.noteList != null}"
							value="#{contractBenefitNotesBackingBean.noteList}"
							rowClasses="dataTableOddRow" border="0">

							<h:column>
								<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
								<h:inputHidden id="version" value="#{singleValue.version}"></h:inputHidden>
								<h:inputHidden id="noteNameHidden"
									value="#{singleValue.noteName}"></h:inputHidden>
								<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}">
								</h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
									
							</h:column>	
						</h:dataTable></DIV>
						</TD>
					</TR>
				</TABLE>
				</fieldset>

				<h:inputHidden id="viewNoteId"
					value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
			</h:form></td>
		</tr>
	</table>
</div><br />
<DIV  id="mandateHeader"  >
<TABLE width="95%">
<tr>
  <td>
		<h:form styleClass="form" id="nonAdjBenCompForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD colspan="2" valign="top" class="ContentArea">
<!-- End of Tab table -->
<!--								<h:inputHidden id="Hidden" value="#{contractBenefitMndateInfoBacingBean.loadForPrint}"></h:inputHidden>-->
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">Mandate Information</div>
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">

<tr>
										<td width="32%">&nbsp;<h:outputText										
										id="StateCodeStar" value="Jurisdiction " /></td>
									<td width="68%">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
										<tr>
											<td width="44%">
											<h:outputText id="txtState"
												value="#{contractBenefitMndateInfoBacingBean.stateCode}"></h:outputText>
											</TD>											
										</tr>
									</table>
									</td>
									</tr>
									<tr>
										<td width="24%">&nbsp;<h:outputText											
											id="MandateTypeStar" value="Mandate Category " /></td>
										<td width="42%"><h:outputText value="#{contractBenefitMndateInfoBacingBean.mandateCategory}"></h:outputText></td>										
										
									</tr>
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="CitationNumberStar" value="Citation Number " /></TD>
										<td><h:outputText id="CitationNumberHidden"
												value="#{contractBenefitMndateInfoBacingBean.citationNumber}"></h:outputText>										
										</TD>
									</TR>
										
									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="FundingArrangementStar" value="Funding Arrangement " /></TD>
										<td><h:outputText id="FundingArrangementHidden" value="#{contractBenefitMndateInfoBacingBean.fundingArrangement}"></h:outputText>
										</td>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText  id="EffectivenessStar" value="Effectiveness " /></TD>
										<td width="42%"><h:outputText value="#{contractBenefitMndateInfoBacingBean.effectiveness}"></h:outputText>
										
										</td>
									</TR>


									<TR>
									<TD width="24%" valign="top">&nbsp;<h:outputText										
										id="descriptionStar1" value="Text " /></TD>
									<TD width="42%" valign="top"><h:outputText
										 id="txtDescription1"
										value="#{contractBenefitMndateInfoBacingBean.text}" ></h:outputText></TD>
									<td width="63%"><f:verbatim></f:verbatim></td>
									</TR>

																					
									<TR>							
										
									</TR>
									<TR>
										<TD width="221">&nbsp;</TD>
									</TR>
            					</TABLE>
								
								</FIELDSET>		
								<BR>
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
<!-- End of hidden fields  -->

				</h:form>
	</td>
</tr>

</TABLE>
<script>
 setColumnWidth('benefitLevelFormPrint:bComponentDataTable','18%:16%:14%:7%:9%:9%:25%');
 formatTildaToComma('nonAdjBenCompForm:FundingArrangementHidden');
 formatTildaToComma('nonAdjBenCompForm:CitationNumberHidden');
 formatTildaToComma('nonAdjBenCompForm:txtState');
</SCRIPT>
</DIV>
<!--   Notes Attachment Print ends here -->

</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitGenInfoPrint" /></form>
<script>		
formatTildaToComma('contractBenefitGeneralInfoPrint:lobHidden');
formatTildaToComma('contractBenefitGeneralInfoPrint:entityHidden');
formatTildaToComma('contractBenefitGeneralInfoPrint:groupHidden');
formatTildaToComma('contractBenefitGeneralInfoPrint:marketBusinessUnitHidden');
formatTildaToComma('contractBenefitGeneralInfoPrint:txtTerm');
formatTildaToComma('contractBenefitGeneralInfoPrint:txtQualifier');
formatTildaToComma('contractBenefitGeneralInfoPrint:txtProviderArrangement');
		  formatTildaToCommaFotDatatype('contractBenefitGeneralInfoPrint:txtDataType');
formatTildaToComma1('contractBenefitGeneralInfoPrint:txtRuleType');
formatTildaToCommaFotDatatype('contractBenefitGeneralInfoPrint:txtTier');
	
function formatTildaToCommaFotDatatype(objName)
{
	var formattedString = "";
	var obj = document.getElementById(objName);
	var val = obj.innerHTML;
	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{
		for(i=0, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}

	var tableObject = document.getElementById('benefitLevelFormPrint:bComponentDataTable');

	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('noBenefitDefinitions');
		divObj.style.visibility = "hidden";
	}
	else{
		var divObj = document.getElementById('noBenefitDefinitions');
		divObj.style.visibility = "visible";
		divObj.style.height = "2px";
		document.getElementById('resultsTable').style.visibility = "hidden";		
	}

	if(tableObject.rows.length > 0){
		if(document.getElementById('benefitLevelFormPrint:bComponentDataTable') != null) {		
			setColumnWidth('benefitLevelFormPrint:bComponentDataTable','15%:12%:16%:8%:10%:10%:29%');	
			setColumnWidth('headerTable','15%:12%:16%:8%:10%:10%:29%');	
		}
	}	
	hideIfNoValue('resultHeaderDiv1');

	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitNotePrint:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractNote').style.visibility = 'hidden';
			setColumnWidth('benefitNotePrint:searchResultTable', '20%:80%');
			setColumnWidth('resultHeaderTable', '20%:80%');
		}
	}
i = document.getElementById("contractBenefitGeneralInfoPrint:hiddenProductType").value;
	if(i=='MANDATE')
	{
		mandateHeader.style.display ='';
		noteHeader.style.display ='none';
	}else{
		mandateHeader.style.display='none';
	}
hideBenefitLevel();
function hideBenefitLevel(){
if(null!=document.getElementById("benefitLevelFormPrint:bComponentDataTable")){
	var rows = document.getElementById('benefitLevelFormPrint:bComponentDataTable').rows.length ;
	if(rows ==0){
	document.getElementById('noBenefitDefinitions').style.visibility = 'visible';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'hidden';
	}
	else{
	document.getElementById('noBenefitDefinitions').style.visibility = 'hidden';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'visible';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'visible';
	}
}else{
	document.getElementById('noBenefitDefinitions').style.visibility = 'visible';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'hidden';
}
}



hideIfNoTierData();
function hideIfNoTierData(){
if(null!=document.getElementById("benefitLevelFormPrint:benresultHeaderTable")){
	var noOfRows = document.getElementById('benefitLevelFormPrint:benresultHeaderTable').rows.length ;
	if(noOfRows ==0){
	document.getElementById('panel2HeaderTier').style.visibility = 'hidden';
	document.getElementById('resultHeaderDivTier').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv2').style.visibility = 'hidden';

	}
	else{
	document.getElementById('panel2HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDivTier').style.visibility = 'visible';
	document.getElementById('searchResultdataTableDiv2').style.visibility = 'visible';
	}
}
}
//the function is to hide the notes column 
//since it is the print page
 function hideNotesColumn(do_show) {
    var stl;
    if (do_show) stl = 'block'
    else         stl = 'none';
 	var tables = document.getElementsByTagName('table');
	for(i =0; i<tables.length; i++) {
		var tableid = tables[i].id
		if(tableid.search(/^benefitLevelFormPrint:notesColumnId/)!=-1) {			
			var tierTable  = document.getElementById(tableid);			
	    	var rows = tierTable.getElementsByTagName('tr');		
	    	for (var row=0; row<rows.length;row++) {	     		
 				var noteCell = rows[row].getElementsByTagName('td')[7];
				noteCell.style.display=stl;
	    	}
		}
    }
  }

function pageReload(){
	var submitFlag = document.getElementById('benefitLevelFormPrint:submitFlag').value;
	if(submitFlag == 'false'){
		document.getElementById('benefitLevelFormPrint:showTierLevelSection').click();
	}else window.print();
}
pageReload();

hideBenefitLevel();
function hideBenefitLevel(){
if(null!=document.getElementById("benefitLevelFormPrint:bComponentDataTable")){
	var rows = document.getElementById('benefitLevelFormPrint:bComponentDataTable').rows.length ;
	if(rows ==0){
	document.getElementById('noBenefitDefinitions').style.visibility = 'visible';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'hidden';
	}
	else{
	document.getElementById('noBenefitDefinitions').style.visibility = 'hidden';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'visible';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'visible';
	}
}else{
	document.getElementById('noBenefitDefinitions').style.visibility = 'visible';
	document.getElementById('panel1HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv1').style.visibility = 'hidden';
}
}



hideIfNoTierData();
function hideIfNoTierData(){
if(null!=document.getElementById("benefitLevelFormPrint:benresultHeaderTable")){
	var noOfRows = document.getElementById('benefitLevelFormPrint:benresultHeaderTable').rows.length ;
	if(noOfRows ==0){
	document.getElementById('panel2HeaderTier').style.visibility = 'hidden';
	document.getElementById('resultHeaderDivTier').style.visibility = 'hidden';
	document.getElementById('searchResultdataTableDiv2').style.visibility = 'hidden';

	}
	else{
	document.getElementById('panel2HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDivTier').style.visibility = 'visible';
	document.getElementById('searchResultdataTableDiv2').style.visibility = 'visible';
	}
}
}
//the function is to hide the notes column 
//since it is the print page
 function hideNotesColumn(do_show) {
    var stl;
    if (do_show) stl = 'block'
    else         stl = 'none';
 	var tables = document.getElementsByTagName('table');
	for(i =0; i<tables.length; i++) {
		var tableid = tables[i].id
		if(tableid.search(/^benefitLevelFormPrint:notesColumnId/)!=-1) {			
			var tierTable  = document.getElementById(tableid);			
	    	var rows = tierTable.getElementsByTagName('tr');		
	    	for (var row=0; row<rows.length;row++) {	     		
 				var noteCell = rows[row].getElementsByTagName('td')[7];
				noteCell.style.display=stl;
	    	}
		}
    }
  }

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComponentGeneralInfo" /></form>
</HTML>
