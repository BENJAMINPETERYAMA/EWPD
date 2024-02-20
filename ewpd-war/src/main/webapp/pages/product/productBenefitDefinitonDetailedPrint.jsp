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
<STYLE type="text/css">
.gridColumn0{
	width: 0%;	
}
.gridColumn8{
	width: 8%;	
}
.gridColumn10{
	width: 10%;	
}
.gridColumn11{
	width: 11%;	
}
.gridColumn12{
	width: 12%;	
}
.gridColumn22{
	width: 22%;	
}
.gridColumn26{
	width: 26%;	
}
.gridColumn27{
	width: 27%;	
}
.gridColumn30{
	width: 30%;	
}
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
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn20{
	width: 20%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn15{
	width: 15%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn3{
	width: 3%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn10{
	width: 10%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn9{
	width: 9%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn6{
	width: 6%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn7{
	width: 7%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn8{
	width: 8%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 </STYLE>
 
 <!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->
 <style type="text/css">		
.selectnoteIdColumn {
	width: 10%;
}
.shortDescriptionColumn {
	width: 90%;
}
</style>
<TITLE>Detailed Print For Benefit</TITLE>
</HEAD>

<f:view>
	<BODY onload="return hideNotesColumn(false);">
	<h:form styleClass="form" id="productBenDefDetailedForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<!--	Start of Table for actual Data	-->
		<table width="95%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
						<h:outputText id="breadcrumb" 
							value="#{productBenefitGeneralInfoBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<!-- General information block starts -->
			<TR id="productGeneralInformation">
				<TD>
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="95%">
					<TBODY>
						<TR>
							<td colspan="1" valign="top" class="ContentArea">
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">General Information</div>
							<!--Start of Table for actual Data	--> <h:inputHidden
								id="loadViewForPrint"
								value="#{productBenefitGeneralInfoBackingBean.loadViewForPrint}"></h:inputHidden>
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="outputText" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productBenefitGeneralInfoBackingBean.lineOfBusiness}"></h:inputHidden>
												<TD width="184">&nbsp;<h:outputText id="lineOfBusinessDiv"
													value="#{productBenefitGeneralInfoBackingBean.lineOfBusiness}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
											<TR><TD><br></TD></TR>											
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="outputText" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productBenefitGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
												<TD width="184">&nbsp;<h:outputText id="businessEntityDiv"
													value="#{productBenefitGeneralInfoBackingBean.businessEntity}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
											<TR><TD><br></TD></TR>	
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="businessGroup"
													value="Business Group" styleClass="outputText" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productBenefitGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
												<TD width="184">&nbsp;<h:outputText id="businessGroupDiv"
													value="#{productBenefitGeneralInfoBackingBean.businessGroup}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
<!-- CARS START -->							
											<TR><TD><br></TD></TR>	
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="outputText" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productBenefitGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="184">&nbsp;<h:outputText id="marketBusinessUnitDiv"
													value="#{productBenefitGeneralInfoBackingBean.marketBusinessUnit}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
<!-- CARS END -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Name" /></TD>
										<TD width="184"><h:outputText id="Name_txt"
											value="#{productBenefitGeneralInfoBackingBean.name}"
											styleClass="outputText" /> <h:inputHidden
											id="benefitNameHidden"
											value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Benefit Meaning" /></TD>
										<TD width="184"><h:outputText id="benefitMeaning_txt"
											value="#{productBenefitGeneralInfoBackingBean.name}"
											styleClass="outputText" /> <h:inputHidden
											id="benefitMeaningHidden"
											value="#{productBenefitGeneralInfoBackingBean.name}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Benefit Type" /></TD>
										<TD width="184"><h:outputText id="benType"
											value="#{productBenefitGeneralInfoBackingBean.benefitType}"
											styleClass="outputText" /> <h:inputHidden id="benTypeHidden"
											value="#{productBenefitGeneralInfoBackingBean.benefitType}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Benefit Category" /></TD>
										<TD width="184"><h:outputText id="benCategory"
											value="#{productBenefitGeneralInfoBackingBean.benefitCategory}"
											styleClass="outputText" /> <h:inputHidden id="benCategoryHidden"
											value="#{productBenefitGeneralInfoBackingBean.benefitCategory}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Mandate Type" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productBenefitGeneralInfoBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandateType1Hidden"
											value="#{productBenefitGeneralInfoBackingBean.mandateType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Benefit Rule ID" /></TD>
										<TD width="184"><h:outputText id="ruleId"
											value="#{productBenefitGeneralInfoBackingBean.ruleId}"
											styleClass="outputText" /> 
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>

									
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Description" /></TD>
										<TD width="184"><h:outputText id="Description_txt"
											value="#{productBenefitGeneralInfoBackingBean.description}"
											styleClass="outputText"></h:outputText> <h:inputHidden
											id="benefitDescHidden"
											value="#{productBenefitGeneralInfoBackingBean.description}"></h:inputHidden>
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>

									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Tier Definition" /></TD>
										<TD width="184"><h:outputText id="txtTier"
											value="#{productBenefitGeneralInfoBackingBean.tier}"
											styleClass="outputText"></h:outputText> 
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
									
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Benefit Level Scope</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="0">
											<TR>
												<!-- <TD width="5"></TD> -->
												<TD width="140"><h:outputText id="TermStar" value="Term"
													styleClass="outputText" /></TD>
												<h:inputHidden id="txtTerm"
													value="#{productBenefitGeneralInfoBackingBean.term}"></h:inputHidden>
												<TD width="184"><h:outputText id="txtTermDiv"
													value="#{productBenefitGeneralInfoBackingBean.term}"
													styleClass="outputText" /></TD>
												<!-- <TD width="24"></TD> -->
											</TR>
											<TR><TD><br></TD></TR>
											<TR>
												<!-- <TD width="5"></TD> -->
												<TD width="140"><h:outputText id="QualifierStar"
													value="Qualifier" styleClass="outputText" /></TD>
												<h:inputHidden id="txtQualifier"
													value="#{productBenefitGeneralInfoBackingBean.qualifier}"></h:inputHidden>
												<TD width="184"><h:outputText id="txtQualifierDiv"
													value="#{productBenefitGeneralInfoBackingBean.qualifier}"
													styleClass="outputText" /></TD>
												<!-- <TD width="24"></TD> -->
											</TR>
											<TR><TD><br></TD></TR>
											<TR>
												<!-- <TD width="5"></TD> -->
												<TD width="140"><h:outputText id="ProviderArrangementStar"
													value="Provider Arrangement" styleClass="outputText" /></TD>
												<h:inputHidden id="txtProviderArrangement"
													value="#{productBenefitGeneralInfoBackingBean.providerArrangement}"></h:inputHidden>
												<TD width="184"><h:outputText id="txtProviderArrangementDiv"
													value="#{productBenefitGeneralInfoBackingBean.providerArrangement}"
													styleClass="outputText" /></TD>
												<!-- <TD width="24"></TD> -->
											</TR>
											<TR><TD><br></TD></TR>
											<TR>
												<!-- <TD width="5"></TD> -->
												<TD width="140"><h:outputText id="DataTypeStar"
													value="Data Type" styleClass="outputText" /></TD>
												<TD width="184"><h:outputText id="txtDataTypeDiv"
													value="#{productBenefitGeneralInfoBackingBean.dataType}"
													styleClass="outputText" /></TD>
												<!-- <TD width="24"></TD> -->
											</TR>
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Version" /></TD>
										<TD width="184"><h:outputText id="versionId"
											value="#{productBenefitGeneralInfoBackingBean.bnftVersion}"
											styleClass="outputText" /> <h:inputHidden
											id="versionIdHidden"
											value="#{productBenefitGeneralInfoBackingBean.bnftVersion}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{productBenefitGeneralInfoBackingBean.createdBy}"
											styleClass="outputText" /> <h:inputHidden
											id="createdBy_optxtHidden"
											value="#{productBenefitGeneralInfoBackingBean.createdBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{productBenefitGeneralInfoBackingBean.creationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDate_optxtHidden"
											value="#{productBenefitGeneralInfoBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <h:inputHidden id="time"
											value="#{requestScope.timezone}">
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated By" /></TD>
										<TD width="184"><h:outputText id="updatedBy_optxt"
											value="#{productBenefitGeneralInfoBackingBean.updatedBy}" />
										<h:inputHidden id="updatedBy_optxtHidden"
											value="#{productBenefitGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{productBenefitGeneralInfoBackingBean.updationDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDate_optxtHidden"
											value="#{productBenefitGeneralInfoBackingBean.updationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"></TD>
										<TD width="184"><f:verbatim /></TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- General information block ends -->
			<!-- New Std Defn block starts-->
			<TR id="productBenefitDefinition">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">Associated Benefit Levels</div>
					<DIV id="noBenefitLineDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;
								font-size:11px;color:#000000; 
								background-color:#FFFFFF;">No Benefit Lines Available</DIV>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<DIV id="resultHeaderDiv" align="left"
									style="position:relative;background-color:#FFFFFF;z-index:1;overflow:auto;">
								<h:panelGrid id="benresultHeaderTable"
									binding="#{productBenefitDetailBackingBean.headerPanelForPrint}"
									rowClasses="dataTableOddRow">
								</h:panelGrid></DIV>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<DIV id="panelContent"
									style="position:relative;background-color:#FFFFFF;height:165">
								<h:panelGrid id="benpanelTable"
									binding="#{productBenefitDetailBackingBean.panelForPrint}">
								</h:panelGrid></DIV>
								</td>
							</tr>
							<tr>
								<TD><p>&nbsp;</p><p>&nbsp;</p></TD>
							</tr>
							<tr><td><div id="panel2HeaderTier" class="tabTitleBar"
								style="position:relative;width:100% ">Associated Tiered Benefit Levels</div></td></tr>
							<tr>
								<td bordercolor="#cccccc">
								<DIV id="resultHeaderDiv1"
										style="position:relative;background-color:#FFFFFF;"><h:panelGrid
										id="resultHeaderTable1"
										binding="#{productBenefitDetailBackingBean.tierHeaderPanelForPrint}"
										rowClasses="dataTableOddRow">
									</h:panelGrid></DIV>
								<DIV id="panelContent1"
									style="position:relative;background-color:#FFFFFF;height:165">
								<h:panelGrid id="benpanelTierTable"
									binding="#{productBenefitDetailBackingBean.panelForTierPrint}">
								</h:panelGrid></DIV>
								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>
						</table>
						</fieldset>
						<br />
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- New Std Defn block ends-->
			<!-- Notes block starts-->
			<TR id="productNotes">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="notediv" class="tabTitleBar"
							style="position:relative;width:100% ">Associated Notes</div>
						<DIV id="noNotesDiv"><br>No Notes Available</DIV>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="notesHeaderDiv">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="10%">&nbsp;</td>

										<td width="90%"><strong><h:outputText value="Notes"
											styleClass="outputText" /></strong></td>
									</tr>
								</TABLE>
								</div>
								<div id="InformationDiv"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDiv" style="height:165px; "><h:dataTable
									cellspacing="1" id="noteDataTable"
									rendered="#{productBenefitNoteBackingBean.noteList != null}"
									value="#{productBenefitNoteBackingBean.noteList}"
									var="singleValue" cellpadding="3" width="100%"  columnClasses="selectnoteIdColumn,shortDescriptionColumn">
									<h:column>
									</h:column>
									<h:column>
										<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
										<h:outputText id="notName" value="#{singleValue.noteName}"
											styleClass="outputText"></h:outputText>
										<h:inputHidden id="notNameHidden"
											value="#{singleValue.noteName}"></h:inputHidden>
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
						</fieldset>
						<br />
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
			<!-- Notes block ends-->
			<!-- Mandate Info block starts-->
			<TR id="productMandateInformation">
				<TD>
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="95%">
					<TBODY>
						<TR>
							<td colspan="1" valign="top" class="ContentArea">
							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">Mandate Information</div>
							<h:inputHidden id="loadmandateView"
								value="#{productBenefitMandateBackingBean.loadViewForPrint}"></h:inputHidden>
							<!--Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="State" /></TD>
										<TD width="184"><h:outputText id="StateCodeStar"
											value="#{productBenefitMandateBackingBean.stateCode}"
											styleClass="outputText" /> <h:inputHidden id="txtState"
											value="#{productBenefitMandateBackingBean.stateCode}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Mandate Category" /></TD>
										<TD width="184"><h:outputText id="MandateTypeStar"
											value="#{productBenefitMandateBackingBean.mandateCategory}"
											styleClass="outputText" /> <h:inputHidden
											id="MandateTypeStar_txtHidden"
											value="#{productBenefitMandateBackingBean.mandateCategory}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Citation Number" /></TD>
										<TD width="184"><h:outputText id="CitationNumberStar"
											value="#{productBenefitMandateBackingBean.citationNumber}"
											styleClass="outputText" /> <h:inputHidden
											id="CitationNumberHidden"
											value="#{productBenefitMandateBackingBean.citationNumber}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Funding Arrangement" /></TD>
										<TD width="184"><h:outputText id="FundingArrangementStar"
											value="#{productBenefitMandateBackingBean.fundingArrangement}"
											styleClass="outputText" /> <h:inputHidden
											id="FundingArrangementHidden"
											value="#{productBenefitMandateBackingBean.fundingArrangement}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Effectiveness" /></TD>
										<TD width="184"><h:outputText id="EffectivenessStar"
											value="#{productBenefitMandateBackingBean.effectiveness}"
											styleClass="outputText" /> <h:inputHidden
											id="EffectivenessStarHidden"
											value="#{productBenefitMandateBackingBean.effectiveness}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Text" /></TD>
										<TD width="184"><h:outputText id="descriptionStar"
											value="#{productBenefitMandateBackingBean.text}"
											styleClass="outputText" /> <h:inputHidden
											id="txtDescription"
											value="#{productBenefitMandateBackingBean.text}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
								</TBODY>
							</TABLE>
							</FIELDSET>
							<BR>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</TD>
			</TR>
			<!-- Mandate Info block ends-->
		</TABLE>

		<h:inputHidden id="printproductGeneralInformation"
			value="#{productBenefitGeneralInfoBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductBenefitDefinition"
			value="#{productBenefitDetailBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductNotes"
			value="#{productBenefitNoteBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductMandateInformation"
			value="#{productBenefitMandateBackingBean.printValue}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script>

	setColumnWidth('productBenDefDetailedForm:benresultHeaderTable','22%:12%:12%:11%:10%:8%:25%');
	setColumnWidth('productBenDefDetailedForm:benpanelTable','22%:12%:12%:11%:10%:8%:25%');
	syncTables('productBenDefDetailedForm:benresultHeaderTable','productBenDefDetailedForm:benpanelTable');
	
	hideIfNoValue('searchResultdataTableDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('productBenDefDetailedForm:noteDataTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noNotesDiv').style.visibility = 'visible';
			document.getElementById('notesHeaderDiv').style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noNotesDiv').style.visibility = 'hidden';
			document.getElementById('notesHeaderDiv').style.visibility = 'visible';
		}
	}
	var printForGenInfo = document.getElementById("productBenDefDetailedForm:printproductGeneralInformation").value;
	var printForBenDef = document.getElementById("productBenDefDetailedForm:printproductBenefitDefinition").value;
	var printForNotes = document.getElementById("productBenDefDetailedForm:printproductNotes").value;
	var printForManInfo = document.getElementById("productBenDefDetailedForm:printproductMandateInformation").value;
	if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('productGeneralInformation');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	} 
	if( null == printForBenDef || "" == printForBenDef ){
		var compAssDivObj = document.getElementById('productBenefitDefinition');
		compAssDivObj.style.visibility = "hidden";
		compAssDivObj.style.height = "0px";
		compAssDivObj.innerText = null;
	} 
	if( null == printForNotes || "" == printForNotes ){
		var proNoteDivObj = document.getElementById('productNotes');
		proNoteDivObj.style.visibility = "hidden";
		proNoteDivObj.style.height = "0px";
		proNoteDivObj.innerText = null;
	} 
	if( null == printForManInfo || "" == printForManInfo ){
		var proAdminDivObj = document.getElementById('productMandateInformation');
		proAdminDivObj.style.visibility = "hidden";
		proAdminDivObj.style.height = "0px";
		proAdminDivObj.innerText = null;
	}
	if("" != printForManInfo){
		formatTildaToCommaforCombo('productBenDefDetailedForm:StateCodeStar');
		formatTildaToComma('productBenDefDetailedForm:CitationNumberStar');
		formatTildaToCommaforCombo('productBenDefDetailedForm:FundingArrangementStar');
	}
	if( "" != printForGenInfo){
		formatTildaToComma('productBenDefDetailedForm:txtTermDiv');
		formatTildaToComma('productBenDefDetailedForm:txtQualifierDiv');
		formatTildaToComma('productBenDefDetailedForm:txtProviderArrangementDiv');
		formatTildaToCommaforCombo('productBenDefDetailedForm:txtDataTypeDiv');
		formatTildaToComma('productBenDefDetailedForm:lineOfBusinessDiv');
		formatTildaToComma('productBenDefDetailedForm:businessEntityDiv');
		formatTildaToComma('productBenDefDetailedForm:businessGroupDiv');
		formatTildaToComma('productBenDefDetailedForm:marketBusinessUnitDiv');

		//for tier definitions
		formatTilda("productBenDefDetailedForm:txtTier"); 

		copyHiddenToInputText('productBenDefDetailedForm:benTypeHidden','productBenDefDetailedForm:benType',1);
		copyHiddenToInputText1('productBenDefDetailedForm:ruleIdHidden','productBenDefDetailedForm:ruleId',1);
		copyHiddenToInputText('productBenDefDetailedForm:mandateType1Hidden','productBenDefDetailedForm:mandateType',1);
		
		var i;
		var obj;
		obj = document.getElementById("productBenDefDetailedForm:benType");
		i= obj.value;
		if(i=="MANDATE")
		{
			sub1.style.display='';
			}
			else 
			{
			sub1.style.display='none';
		}

		getMandateType();
		function getMandateType()
		{
			var obj;
			obj = document.getElementById("productBenDefDetailedForm:mandateType");
			if(obj.value==2 || obj.value == 3)
			{
				if(obj.value==2)
					document.getElementById("productBenDefDetailedForm:mandateType").value = "State";
				else
					document.getElementById("productBenDefDetailedForm:mandateType").value = "ET";
			}
			else 
			{
				document.getElementById("productBenDefDetailedForm:mandateType").value = "Federal";
			}
		}

		// Script for Admin Option/Notes tab hide for mandate
		var j;
		var obj1;
		obj1 = document.getElementById("productBenDefDetailedForm:benType");
		j= obj1.value;
		if(j== "MANDATE")
		{
			productNotes.style.display='none';
			productMandateInformation.style.display='';	
		}else{
			productNotes.style.display='';
			productMandateInformation.style.display='none';
		}
	}
hideBenefitLine();
function hideBenefitLine(){
if(null!=document.getElementById("productBenDefDetailedForm:benpanelTable")){
	var line = document.getElementById('productBenDefDetailedForm:benpanelTable').rows.length ;
	if(line ==0){
	document.getElementById('noBenefitLineDiv').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
	}
	else{
	document.getElementById('noBenefitLineDiv').style.visibility = 'hidden';
	document.getElementById('resultHeaderDiv').style.visibility = 'visible';
	}
}
}
//input will be Tier4~4~Tier2~2~Tier3~3
//output will be Tier4,Tier2,Tier3
function formatTilda(objName)
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

//the function is to hide the notes column 
//since it is the print page
 function hideNotesColumn(do_show) {
    var stl;
    if (do_show) stl = 'block'
    else         stl = 'none';
 	var tables = document.getElementsByTagName('table');
	for(i =0; i<tables.length; i++) {
		var tableid = tables[i].id
		if(tableid.search(/^productBenDefDetailedForm:notesColumnId/)!=-1) {			
			var tierTable  = document.getElementById(tableid);			
	    	var rows = tierTable.getElementsByTagName('tr');		
	    	for (var row=0; row<rows.length;row++) {	     		
 				var noteCell = rows[row].getElementsByTagName('td')[7];
				noteCell.style.display=stl;
	    	}
		}
    }
  }

hideIfNoTierData();
function hideIfNoTierData(){
if(null!=document.getElementById("productBenDefDetailedForm:benpanelTierTable")){
	var noOfRows = document.getElementById('productBenDefDetailedForm:benpanelTierTable').rows.length ;
	if(noOfRows ==0){
	document.getElementById('panel2HeaderTier').style.visibility = 'hidden';
	document.getElementById('resultHeaderDiv1').style.visibility = 'hidden';
	document.getElementById('panelContent1').style.visibility = 'hidden';
	}
	else{
	document.getElementById('panel2HeaderTier').style.visibility = 'visible';
	document.getElementById('resultHeaderDiv1').style.visibility = 'visible';
	document.getElementById('panelContent1').style.visibility = 'visible';
	}
}
}

</script>
</HTML>

