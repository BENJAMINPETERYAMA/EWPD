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
<TITLE>Print Benefit</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="benefitDetailedPrintForm">
		<h:inputHidden id="generalInfoPrint"
			value="#{benefitComponentCreateBackingBean.loadBenefitComponentforPrint}" />
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:10px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:90%">
				<h:outputText id="breadcrumb"
					value="#{benefitComponentBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD id="benefitGeneralInformation">
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">


				<TABLE width="100%">
					<TR>
						<TD width="325">
						<div id="panel3Header" style="position:relative;width:100% "><STRONG>General
						Information</STRONG></div>
						<br />
						</TD>
					</TR>
					<TR>
						<TD width="42%"><h:outputText id="LobStar"
							value="Line of Business " /></TD>
						<TD width="58%"><h:outputText id="txtLob"
							value="#{benefitComponentCreateBackingBean.lineOfBusiness}"></h:outputText>
						</TD>

					</TR>
					<TR>
						<TD width="42%"><h:outputText id="BusinessEntityStar"
							value="Business Entity " /></TD>
						<TD width="58%"><h:outputText id="txtBusinessEntity"
							value="#{benefitComponentCreateBackingBean.businessEntity}"></h:outputText>
						</TD>

					</TR>
					<TR>
						<TD width="42%"><h:outputText id="BusinessGroupStar"
							value="Business Group " /></TD>
						<TD width="58%"><h:outputText id="txtBusinessGroup"
							value="#{benefitComponentCreateBackingBean.businessGroup}"></h:outputText>
						</TD>
					</TR>
<!-- ------------------------------------------------- -->
					<TR>
						<TD width="42%"><h:outputText
							value="Market Business Unit " /></TD>
						<TD width="58%"><h:outputText id="txtMarketBusinessUnit"
							value="#{benefitComponentCreateBackingBean.marketBusinessUnit}"></h:outputText>
						</TD>

					</TR>
<!-- ------------------------------------------------- -->
					<TR>
						<TD width="42%"><h:outputText value="Name" /></TD>
						<TD width="58%"><h:outputText
							value="#{benefitComponentCreateBackingBean.minorHeading}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD width="42%"><h:outputText value="Benefit Meaning" /></TD>
						<TD width="58%"><h:outputText
							value="#{benefitComponentCreateBackingBean.minorHeading}"></h:outputText>
						</TD>
					</TR>

					<TR>
						<TD width="325"><h:outputText value="Benefit Type" /></TD>
						<TD width="446"><h:outputText id="benType"
							value="#{benefitComponentCreateBackingBean.sbBenType}" /> <h:inputHidden
							id="benTypeHidden"
							value="#{benefitComponentCreateBackingBean.sbBenType}" /></TD>
					</TR>
					<TR id="sub1" ; style="display:none;">
						<TD width="325"><h:outputText value="Mandate Type" /></TD>
						<TD width="446"><h:outputText id="manType"
							value="#{benefitComponentCreateBackingBean.sbMandateType}" /></TD>
					</TR>
					<!-- <TR id="sub2" ; style="display:none;">
									<TD width="325"><h:outputText value="State" /></TD>
									<TD width="446"><h:outputText id="stateCde"
										value="#{benefitComponentCreateBackingBean.sbSelState}" /></TD>
								</TR> -->
					<TR>
						<TD width="325"><h:outputText value="Benefit Category" /></TD>
						<TD width="446"><h:outputText id="benefitCategoryId"
							value="#{benefitComponentCreateBackingBean.benefitCategory}" />
						<h:inputHidden id="benefitCategoryHidden"
							value="#{benefitComponentCreateBackingBean.benefitCategory}" /></TD>
					</TR>
					<TR>
						<TD valign="top" width="42%"><h:outputText id="descriptionStar"
							value="Description " /></TD>
						<TD width="58%"><h:outputText id="txtDescription"
							value="#{benefitComponentCreateBackingBean.stdBenefitDescription}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD width="325"><h:outputText value="Benefit Rule ID" /></TD>
						<TD width="446"><h:outputText id="ruleIdTxt"
							value="#{benefitComponentCreateBackingBean.sbRule}"></h:outputText>
						</TD>
					</TR>


					<TR>
						<TD width="325"><h:outputText value="Tier Definition" /></TD>
						<TD width="446">
						<h:outputText id="txtTier" 
								value="#{benefitComponentCreateBackingBean.tierBenefitComp}"></h:outputText>									
						</TD>
					</TR>


					<TR>
						<TD width="42%"><h:outputText id="termEditStar" value="Term " /></TD>
						<TD width="58%"><h:outputText id="txtTerm"
							value="#{benefitComponentCreateBackingBean.term}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD width="42%"><h:outputText id="QualifierEditStar"
							value="Qualifier" /></TD>
						<TD width="58%"><h:outputText id="txtQualifier"
							value="#{benefitComponentCreateBackingBean.qualifier}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD width="42%"><h:outputText id="ProviderArrangementEditStar"
							value="Provider Arrangement " /></TD>
						<TD width="58%"><h:outputText id="txtProviderArrangement"
							value="#{benefitComponentCreateBackingBean.providerArrangement}"></h:outputText>
						</TD>
					</TR>
					<TR>
						<TD width="42%"><h:outputText id="DataTypeEditStar"
							value="Data Type " /></TD>
						<TD width="58%"><h:outputText id="txtDataType"
							value="#{standardBenefitBackingBean.dataType}" /></TD>
					</TR>
					<TR>
						<TD width="42%"><span class="mandatoryNormal" id="creationDateId"></span><h:outputText
							value="Created By" /></TD>
						<TD width="58%"><h:outputText id="createdUserView"
							value="#{benefitComponentCreateBackingBean.createdUser}" /></TD>
					</TR>
					<TR>
						<TD width="42%"><span class="mandatoryNormal" id="version"></span><h:outputText
							value="Version" /></TD>
						<TD width="58%"><h:outputText id="VersionView"
							value="#{benefitComponentCreateBackingBean.benefitVersion}" /></TD>
					</TR>
					<TR>
						<TD width="42%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
							value="Created Date" /></TD>
						<TD width="58%"><h:outputText id="createdDateView"
							value="#{benefitComponentCreateBackingBean.createdTimestamp}">
							<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
						</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
					</TR>
					<TR>
						<TD width="42%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
							value="Last Updated By" /></TD>
						<TD width="58%"><h:outputText id="updatedUserView"
							value="#{benefitComponentCreateBackingBean.lastUpdatedUser}" />
						</TD>
					</TR>
					<TR>
						<TD width="42%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
							value="Last Updated Date" /></TD>
						<TD width="58%"><h:outputText id="updatedTimeView"
							value="#{benefitComponentCreateBackingBean.lastUpdatedTimestamp}">
							<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
						</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
					</TR>
				</TABLE>
				</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD id="benefitDefinitions"><br />
				<br />

				<BR />
				<br />
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
				<!--	Start of Table for actual Data	
				<div id="emptymsgForBenefitDefinition"><h:outputText
					value="No benefit definitions Available."
					styleClass="dataTableColumnHeader" /></div> -->
				<div id="panel">
				

				<TABLE width="95%" cellpadding="0" cellspacing="0">
					<tr>
				<TD>
					<DIV id="messageTextForNoBenefitLevelsDiv" align="center"><BR>
								<CENTER>
								<BR>
								<STRONG>&nbsp;<h:outputText value="No Benefit Level available." /></STRONG>
								</CENTER>
						</DIV>
						
							
							<DIV id="associatedBenefitspanelHeader" >
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR bgcolor="#cccccc">
												<TD bgcolor="#CCCCCC" height="23px" colspan="3"><B><h:outputText
													value="Associated Benefit Lines" /></B></TD>
											</TR>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Description" /></TD>
												<TD align="left"><h:outputText value="Term" /></TD>
												<TD align="left"><h:outputText value="Frequency - Qualifier" /></TD>
												<TD align="left"><h:outputText value="PVA" /></TD>
												<TD align="left"><h:outputText value="Format" /></TD>
												<TD align="left"><h:outputText value="Benefit Value" /></TD>
												<TD align="left"><h:outputText value="Reference" /></TD>
												
											</TR>
										</TBODY>
								</TABLE>
							
							<h:dataTable headerClass="tableHeader" id="panelTable" border="0" width="100%" value="#{ComponentBenefitDefinitionsBackingBean.benefitLevelsListForView}" 
								var="eachRow" cellpadding="0" cellspacing="1" >
								<h:column>
									<h:inputHidden id="benefitLineIdHidden" value="#{eachRow.benefitLineId}" /> 
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.description}" />
								</h:column>

								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.term}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField"
										value="#{eachRow.qualifier}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.pva}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.format}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.benefitValue}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.reference}" />
								</h:column>
								
							</h:dataTable>

							</DIV>
						
						<DIV id="dummyDiv"></DIV>
					
				</TD>

			</tr>
				</TABLE>
				<!--	End of Page data	-->
			<TR>
				<TD id="mandateInformation"><BR />
				<BR />
				
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
				<h:inputHidden id="printComponentBenefitMandate"
					value="#{benefitMandateBackingBean.printComponentBenefitMandate}" />
				<br />
				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="0" cellpadding="0" class="outputText"
					width="100%" id="mandateInfoTable">

					<tr>
						<td>
						<div id="stateCodeDiv">
						<TABLE width="211%">
							<TBODY>
								<tr>
									<td width="282">
									<DIV id="panel2Header" style="position:relative;width:100% "><STRONG>
									Mandate Information</STRONG></DIV>
									<br />
									</td>
								</tr>
								<tr>
									<td width="47%">&nbsp;<h:outputText id="StateCodeStar"
										value="Jurisdiction " /></td>
									<td width="53%">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">
										<tr>
											<td width="88%">
											<DIV id="StateDiv" style="visiblity:hidden"></DIV>
											<h:outputText id="StateForMandateInfo"
												value="#{benefitMandateBackingBean.stateCode}"></h:outputText>
											<h:inputHidden id="txtStateForMandateInfo"
												value="#{benefitMandateBackingBean.stateCode}"></h:inputHidden>

											</TD>

										</tr>
									</table>
									</td>
								</tr>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>

					<tr>
						<td width="32%">&nbsp;<h:outputText id="MandateCategoryStar"
							value="Mandate Category " /></td>
						<td width="68%">
						<table cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td><h:inputTextarea styleClass="selectDivReadOnly" tabindex="5"
									readonly="true" style="border:0"
									value="#{benefitMandateBackingBean.mandateCategory}"></h:inputTextarea>
								</td>
								<td width="63%" align="right"></td>
							</tr>
						</table>
						</td>
					</tr>
					<TR>
						<TD width="32%">&nbsp;<h:outputText id="CitationNumberStar"
							value="Citation Number" /></TD>
						<TD width="68%">
						<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
							<TR>
								<TD width="44%"><!-- <DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV> -->

								<h:outputText id="CitationNumber"
									value="#{benefitMandateBackingBean.citationNumber}"></h:outputText>
								<h:inputHidden id="txtCitationNumber"
									value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
								</TD>

							</TR>
						</TABLE>
						</TD>
					</TR>


					<TR>
						<TD width="32%">&nbsp;<h:outputText id="FundingArrangementStar"
							value="Funding Arrangement " /></TD>
						<TD width="68%">
						<TABLE cellspacing="0" cellpadding="0" border="0" width="100%">
							<TR>
								<TD width="44%"><h:outputText id="txtFundingArrangement"
									value="#{benefitMandateBackingBean.fundingArrangement}"></h:outputText>
								</TD>

							</TR>
						</TABLE>
						</TD>
					</TR>

					<TR>
						<TD width="32%">&nbsp;<h:outputText id="EffectivenessStar"
							value="Effectiveness " /></TD>
						<td width="68%">
						<table cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td><h:inputTextarea styleClass="selectDivReadOnly" tabindex="5"
									readonly="true" style="border:0"
									value="#{benefitMandateBackingBean.effectiveness}"></h:inputTextarea>
								<td width="63%" align="right"></td>
							</tr>
						</table>
						</td>
					</TR>


					<TR>
						<TD valign="top" width="32%">&nbsp;<h:outputText
							id="descriptionStar1" value="Text " /></TD>
						<TD valign="top" width="68%"><h:outputText id="txtDescription1"
							value="#{benefitMandateBackingBean.text}"></h:outputText></TD>
						<td width="63%"><f:verbatim></f:verbatim></td>
					</TR>



					<TR>
						<TD width="206">&nbsp;</TD>
					</TR>
				</TABLE>

				</FIELDSET>
				</TD>
			</TR>
			<!--	End of Page data	-->
			<TR>
				<TD id="benefitNotesTab"><BR />
				<BR />
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">

				<div id="emptymsgForNotes"><h:outputText value="No Notes Available." /></div>
				<div id="noteDiv"><!--	Start of Table for actual Data	--> <br />


				<TABLE width="100%" cellpadding="0" cellspacing="0" border="0"
					id="notetable">

					<tbody>


						<TR>
							<TD>
							<div id="resultHeaderDivForNotes">
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
								id="resultHeaderTableForNotes" border="0" width="100%">
								<TBODY>
									<tr>
										<td>
										<DIV id="panel2Header" style="position:relative;width:100% "><STRONG>
										Associated Notes</STRONG></DIV>
										</td>
									</tr>
									<TR class="dataTableColumnHeader">
										<td align="left" width="25%"><h:outputText value="Note Name"></h:outputText>
										</td>
									</TR>
								</TBODY>
							</TABLE>
							</div>
							</TD>
						</TR>
						<TR>
							<TD>
							<DIV id="searchResultdataTableDivForNotes"
								style="overflow: auto;"><!-- Search Result Data Table -->
							<h:dataTable
								rendered="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList!= null}"
								rowClasses="dataTableOddRow" bgcolor="#cccccc"
								styleClass="outputText" headerClass="dataTableHeader"
								id="NotesTable" var="singleValue" cellpadding="1" width="100%"
								cellspacing="1"
								value="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList}">

								<h:column>
									<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
								</h:column>

							</h:dataTable></DIV>
							</TD>
						</TR>
					</tbody>

				</TABLE>
				</FIELDSET>
				</TD>
			</TR>



			<h:inputHidden id="bencompHidden"
				value="#{ComponentBenefitDefinitionsBackingBean.benefitComponent}"></h:inputHidden>
			<!-- Space for hidden fields -->
			<h:inputHidden id="hidden1" value="value1"></h:inputHidden>

			<h:inputHidden id="displayHeaderPanel"
				value="#{ComponentBenefitDefinitionsBackingBean.headerDisplay}" />

			<h:commandLink id="hiddenLnk1"
				style="display:none; visibility: hidden;">
				<f:verbatim />
			</h:commandLink>
			</h:form>
	</BODY>
</f:view>
<script><!--

// General Information
formatTildaToComma("benefitDetailedPrintForm:txtLob");
formatTildaToComma("benefitDetailedPrintForm:txtBusinessEntity");
formatTildaToComma("benefitDetailedPrintForm:txtBusinessGroup");
formatTildaToComma("benefitDetailedPrintForm:txtMarketBusinessUnit");
formatTildaToComma("benefitDetailedPrintForm:txtTerm");
formatTildaToComma("benefitDetailedPrintForm:txtQualifier");
formatTildaToComma("benefitDetailedPrintForm:txtProviderArrangement");
formatTildaToComma1("benefitDetailedPrintForm:ruleIdTxt");
formatTildaToCommaForDatatype("benefitDetailedPrintForm:txtDataType"); 

//for tier definitions
formatTilda("benefitDetailedPrintForm:txtTier"); 

function formatTildaToCommaForDatatype(objName)
{

	var formattedString = "";
	var obj = document.getElementById(objName);	
	var val = obj.innerHTML;
	
	if(val == null || val == '')
		return;
	
	var values = val.split('~');
	if(values != null)
	{

		for(i=1, n = values.length; i < n; i+=2)
		{
			formattedString += values[i] ;
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}

getBenefitType();
getMandateType();

function getBenefitType()
	{
	var benType;	
	benType = document.getElementById("benefitDetailedPrintForm:benType");
	if(benType.innerHTML =="MANDATE")
	{
		sub1.style.display = '';
	}
	else
	{
		sub1.style.display = 'none';
		// sub2.style.display = 'none';
	}
} 

function getMandateType(){
	var manType;
	manType = document.getElementById("benefitDetailedPrintForm:manType");
	if(manType.innerHTML =="State" || manType.innerHTML=="ET"){
		// sub2.style.display = '';
	}
	else{
		// sub2.style.display = 'none';
	}
}



// BenefitDefinitions
	
//	hideIfNoValue1('resultHeaderDiv');
//	function hideIfNoValue1(divId){
	//	hiddenIdObj = document.getElementById('benefitDetailedPrintForm:benefitDefinitionsTable:0:levelIdHidden');		
	//	if(hiddenIdObj == null){
	//		document.getElementById(divId).style.visibility = 'hidden';
		//	document.getElementById('emptymsgForBenefitDefinition').style.visibility = 'visible';
	//	}else{
	//		document.getElementById(divId).style.visibility = 'visible';
		//	document.getElementById('emptymsgForBenefitDefinition').style.visibility = 'hidden';
	//		setColumnWidth('benefitDetailedPrintForm:benefitDefinitionsTable', '30%:15%:20%:15%:20%');
	// 		setColumnWidth('resultHeaderTable', '30%:15%:20%:15%:20%');
	//	}
	//}		

// Benefit Notes
	hideIfNoValue('resultHeaderDivForNotes');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitDetailedPrintForm:NotesTable');		
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsgForNotes').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsgForNotes').style.visibility = 'hidden';
			setColumnWidth('benefitDetailedPrintForm:NotesTable', '25%:25%:25%');
	 		setColumnWidth('resultHeaderTableForNotes', '25%:25%:25%');
		}
	}	
	
// Mandate Information
	// copyHiddenToDiv_ewpd('benefitDetailedPrintForm:txtStateForMandateInfo','StateDiv',2,2); 
	formatTildaToCommaForStateCode("benefitDetailedPrintForm:StateForMandateInfo");
	formatTildaToCommaForStateCode("benefitDetailedPrintForm:txtFundingArrangement");
	formatTildaToComma("benefitDetailedPrintForm:CitationNumber");
	
	// parseForDiv(document.getElementById('CitationNumberDiv'), document.getElementById('benefitMandatePrintForm:txtCitationNumber')); 

function formatTildaToCommaForStateCode(objName)
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
			formattedString += values[i+1] ;
			if(i < n-3)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}

hideIfNoValueForMandateInfo('stateCodeDiv','txtStateForMandateInfo');

function hideIfNoValueForMandateInfo(divId, txtName){
		hiddenIdObj = document.getElementById('benefitDetailedPrintForm:'+txtName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj.value == 'null' || hiddenIdObj.value == 'undefined'){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}	

// To hide the mandateInfo/notes tab based on the type of the benefit component(std/mandate)	
	
	hideRespectiveTab();
	function hideRespectiveTab(){
	obj = document.getElementById("benefitDetailedPrintForm:benTypeHidden");			
			if(obj.value== "STANDARD" ){
				document.getElementById("mandateInformation").style.display = 'none';
				showNote();
			}else{
				document.getElementById("benefitNotesTab").style.display = 'none';
				document.getElementById("notetable").style.display = 'none';
				document.getElementById('noteDiv').style.visibility = 'hidden';	
				document.getElementById('emptymsgForNotes').style.visibility = 'hidden';	
			}	
	}
	function showNote(){
		if(null != document.getElementById('benefitDetailedPrintForm:NotesTable')){
			// setColumnWidth('productStructureBenefitPrintNotesForm:NotesTable','25%');
			document.getElementById('noteDiv').style.visibility = 'visible';			
			document.getElementById('emptymsgForNotes').style.visibility = 'hidden';	
		}else{
			document.getElementById('noteDiv').style.visibility = 'hidden';		
			document.getElementById('emptymsgForNotes').style.visibility = 'visible';	
		}
}

var panelTableObject = document.getElementById('benefitDetailedPrintForm:panelTable');
		if(panelTableObject.rows.length > 0){
if(document.getElementById('benefitDetailedPrintForm:panelTable').offsetHeight <= 189) {
			var panelDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
			panelDivObj.style.visibility = "hidden";
			panelDivObj.style.height = "0px";
			setColumnWidth('resultHeaderTable','15%:14%:17%:9%:10%:9%:26%');	
			setColumnWidth('benefitDetailedPrintForm:panelTable','15%:14%:17%:9%:10%:9%:26%');
}else{
			document.getElementById('resultHeaderTable').width = "100%";
			document.getElementById('benefitDetailedPrintForm:panelTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			var panelDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
			panelDivObj.style.visibility = "hidden";
			panelDivObj.style.height = "0px";
			document.getElementById('benefitDetailedPrintForm:panelTable').width = relTblWidth-17+"px";
			document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
			setColumnWidth('resultHeaderTable','15%:14%:17%:9%:10%:9%:26%');	
			setColumnWidth('benefitDetailedPrintForm:panelTable','15%:14%:17%:9%:10%:9%:26%');
}
		}else{
			var msgDivObj = document.getElementById('benefitDetailedPrintForm:panelTable');
			var object = document.getElementById('associatedBenefitspanelHeader');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
			object.style.visibility = "hidden";
		}

window.print();		

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
</script>

</HTML>




