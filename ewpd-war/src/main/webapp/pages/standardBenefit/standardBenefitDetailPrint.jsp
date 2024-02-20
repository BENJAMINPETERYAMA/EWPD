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
<TITLE>Benefit Detail Print</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="benefitDetailPrintForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>

			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:12px;margin-right:0px;padding-bottom:1px;padding-top:1px;width:173%">
				<h:outputText id="breadcrumb"
					value="#{standardBenefitBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<h:inputHidden id="viewStandardBenefitKey"
					value="#{standardBenefitBackingBean.printStandardBenefitKey}" />
				<td style="outputText" width="40%" align="left"><br>
				<br>
				&nbsp;&nbsp;&nbsp; <h:outputText value="Benefit"
					styleClass="outputText" /> &nbsp;: <h:outputText
					id="benefitNameTitle"
					value="#{standardBenefitBackingBean.minorHeading}"
					styleClass="outputText" /></td>
				<TD width="5"></TD>
				<TD width="5"></TD>
				<TD width="1"></TD>
			</TR>
		</TABLE>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<h:inputHidden id="tabHidden"
					value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>


				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TD>
				<TABLE border="0" cellspacing="6" cellpadding="5" class="outputText"
					width="100%">
					<TBODY>
						<TR>

							<TD colspan=3>
							<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
								cellpadding="3" border="0">
								<TR>
									<TD width="100%"><strong><h:outputText
										value="General Information"></h:outputText></strong></TD>
								</TR>
							</TABLE>
							<FIELDSET style="width:100%">
							<TABLE width="98%">
								<TR>
									<TD width="30%"><h:outputText id="LobStar"
										value="Line of Business " /></TD>
									<TD width="70%"><h:outputText id="txtLob"
										value="#{standardBenefitBackingBean.lob}"></h:outputText></TD>

								</TR>
								<TR>
									<TD width="30%"><h:outputText id="BusinessEntityStar"
										value="Business Entity " /></TD>
									<TD width="70%"><h:outputText id="txtBusinessEntity"
										value="#{standardBenefitBackingBean.businessEntity}"></h:outputText>
									</TD>

								</TR>
								<TR>
									<TD width="30%"><h:outputText id="BusinessGroupStar"
										value="Business Group " /></TD>
									<TD width="70%"><h:outputText id="txtBusinessGroup"
										value="#{standardBenefitBackingBean.businessGroup}"></h:outputText>
									</TD>

								</TR>
<!-- ---------------------------------------------------------------------------- -->
								<TR>
									<TD width="30%"><h:outputText id="MarketBusinessUnitStar"
										value="Market Business Unit " /></TD>
									<TD width="70%"><h:outputText id="txtMarketBusinessUnit"
										value="#{standardBenefitBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>

								</TR>
<!-- ---------------------------------------------------------------------------- -->
								<TR>
									<TD width="30%"><h:outputText value="Name" /></TD>
									<TD width="70%"><h:outputText
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="30%"><h:outputText value="Benefit Meaning" /></TD>
									<TD width="70%"><h:outputText
										value="#{standardBenefitBackingBean.minorHeading}"></h:outputText>
									</TD>
								</TR>

								<TR>
									<TD width="30%"><h:outputText value="Benefit Type" /></TD>
									<TD width="70%"><h:outputText id="benefitId"
										value="#{standardBenefitBackingBean.benefitType}"></h:outputText>
									<h:inputHidden id="CorpPlanCd_list1"
										value="#{standardBenefitBackingBean.benefitType}"></h:inputHidden></TD>
								</TR>

								<TR id="sub1" style="display:none;">
									<TD width="30%"><h:outputText value="Mandate Type" /></TD>
									<TD width="70%"><h:outputText id="mandateId"
										value="#{standardBenefitBackingBean.mandateType}"></h:outputText>
									<h:inputHidden id="Mandate_type_list1"
										value="#{standardBenefitBackingBean.mandateType}"></h:inputHidden></TD>

								</TR>
								<TR>
									<TD width="30%"><h:outputText value="Benefit Category" /></TD>
									<TD width="70%"><h:outputText id="benefitCategoryId"
										value="#{standardBenefitBackingBean.benefitCategoryHidden}"></h:outputText>
									<!--<h:inputHidden id="Benefit_Category_list1"
										value="#{standardBenefitBackingBean.benefitCategory}"></h:inputHidden>
									--></TD>

								</TR>
								<TR>
									<TD valign="top" width="30%"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="70%"><h:outputText id="txtDescription"
										styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
										value="#{standardBenefitBackingBean.description}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="30%"><h:outputText value="Benefit Rule ID " /></TD>
									<TD width="70%"><h:outputText id="txtRule"
										value="#{standardBenefitBackingBean.rule}"></h:outputText></TD>
									<TD width="8%"></TD>
								</TR>


								<TR>
									<TD width="30%" valign="top"><h:outputText id="termEditStar"
										value="Term " /></TD>
									<TD width="70%"><h:outputText id="txtTerm"
										value="#{standardBenefitBackingBean.term}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="30%" valign="top"><h:outputText
										id="QualifierEditStar" value="Qualifier" /></TD>
									<TD width="70%"><h:outputText id="txtQualifier"
										value="#{standardBenefitBackingBean.qualifier}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="30%" valign="top"><h:outputText
										id="ProviderArrangementEditStar" value="Provider Arrangement " /></TD>
									<TD width="70%"><h:outputText id="txtProviderArrangement"
										value="#{standardBenefitBackingBean.providerArrangement}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="30%" valign="top"><h:outputText
										id="DataTypeEditStar" value="Data Type " /></TD>
									<TD width="70%"><h:outputText id="txtDataType"
										value="#{standardBenefitBackingBean.dataType}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="30%"><SPAN class="mandatoryNormal"
										id="creationDateId"></SPAN><h:outputText value="Created By" /></TD>
									<TD width="70%"><h:outputText id="createdUserView"
										value="#{standardBenefitBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="30%"><SPAN class="mandatoryNormal" id="createdBy"></SPAN><h:outputText
										value="Created Date" /></TD>
									<TD width="70%"><h:outputText id="createdDateView"
										value="#{standardBenefitBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText><h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="30%"><SPAN class="mandatoryNormal" id="updationDate"></SPAN><h:outputText
										value="Last Updated By" /></TD>
									<TD width="70%"><h:outputText id="updatedUserView"
										value="#{standardBenefitBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="30%"><SPAN class="mandatoryNormal" id="updateBy"></SPAN><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="70%"><h:outputText id="updatedTimeView"
										value="#{standardBenefitBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText><h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<BR>
							<FIELDSET style="width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td width="15"></td>
									<td align="left"></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText value="#{standardBenefitBackingBean.state}" /></td>

										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText
												value="#{standardBenefitBackingBean.status}" /></td>

										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td><h:outputText value=":" /></td>
											<td><h:outputText
												value="#{standardBenefitBackingBean.version}" /></td>

										</tr>
									</table>
									</td>
								</tr>
							</table>
							</FIELDSET>
							</TD>
						</TR>
				</TABLE>
				</TD>
			</TR>

			<TR>
				<TD>

				<DIV id="nobenefitdef">
				<FIELDSET style="width:100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!-- start of actual data -->
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								<DIV id="resultHeaderDiv">
								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" id="resultHeaderTable1" border="0">
									<TR>
										<TD width="100%"><strong><h:outputText
											value="Associated Benefit Definitions"></h:outputText></strong>
										</TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" cellpadding="3"
									id="resultHeaderTable1" border="0" width="100%">
									<TBODY>

										<TR class="outputText">
											<TD align="left" width="47.5%"><h:outputText
												value="No Associated Benefit Definitions Available."></h:outputText></TD>


										</TR>
									</TBODY>
								</TABLE>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</DIV>
				<div id="benefitDefnDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!--	Start of Table for actual Data	-->
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD width="100%">
								<DIV id="resultHeaderDiv">
								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" id="resultHeaderTable1" border="0">
									<TR>
										<TD width="100%"><strong><h:outputText
											value="Associated Benefit Definitions"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable1" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<TD align="left" width="40%"><h:outputText
												value="Description"></h:outputText></TD>
											<TD align="left" width="20%"><h:outputText
												value="Effective Date"></h:outputText></TD>
											<TD align="left" width="20%"><h:outputText
												value="Expiry Date"></h:outputText></TD>
											<TD align="left" width="20%"><h:outputText
												value="Tier Definition"></h:outputText></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD width="100%">

								<DIV id="searchResultdataTableDiv"><!-- Search Result Data Table -->
								<FIELDSET style="width:100%"><h:dataTable
									rendered="#{benefitDefinitionBackingBean.associatedBenefitDefinitionsList != null}"
									styleClass="outputText" headerClass="dataTableHeader"
									id="benefitDefinitionsTable" var="singleValue" cellpadding="1"
									width="100%" cellspacing="3"
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
								</h:dataTable></FIELDSET>
								</DIV>
								</TD>
							</TR>
						</TABLE>

						</TD>
					</TR>

				</table>
				</div>
				</TD>
			</TR>
			<TR>
				<h:inputHidden id="viewBenefitMandate"
					value="#{benefitMandateBackingBean.viewBenefitMandate}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TD>
				<div id="mandateInfoDiv">
				<TABLE border="0" cellspacing="6" cellpadding="5" class="outputText"
					width="100%">
					<TBODY>
						<TR>

							<TD colspan=3>
							<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
								cellpadding="3" border="0">
								<TR>
									<TD width="100%"><h:outputText value="Mandate Information"></h:outputText></TD>
								</TR>
							</TABLE>
							<FIELDSET style="width:100%">
							<TABLE width="98%">

								<!--	Start of Table for actual Data	-->

								<tr>
									<td width="198">

									<div id="stateCodeDiv">
									<TABLE width="333%">
										<TBODY>
											<tr>
												<td width="30%">&nbsp;<h:outputText id="StateCodeStar"
													value="Jurisdiction " /></td>
												<td width="70%">
												<table cellspacing="0" cellpadding="0" border="0"
													width="100%">
													<tr>
														<td width="88%"><h:outputText id="txtState"
															value="#{benefitMandateBackingBean.stateCode}"></h:outputText>
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
									<td width="30%">&nbsp;<h:outputText id="MandateCategoryStar"
										value="Mandate Category " /></td>
									<td width="70%"><h:inputTextarea styleClass="selectDivReadOnly"
										tabindex="5" readonly="true" style="border:0"
										value="#{benefitMandateBackingBean.mandateCategory}"></h:inputTextarea>
									</td>

								</tr>
								<TR>
									<TD width="30%">&nbsp;<h:outputText id="CitationNumberStar"
										value="Citation Number" /></TD>
									<TD width="70%"><!-- <DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV> -->
									<h:outputText id="CitationNumber"
										value="#{benefitMandateBackingBean.citationNumber}"></h:outputText>
									<h:inputHidden id="txtCitationNumber"
										value="#{benefitMandateBackingBean.citationNumber}"></h:inputHidden>
									</TD>
								</TR>


								<TR>
									<TD width="30%">&nbsp;<h:outputText id="FundingArrangementStar"
										value="Funding Arrangement " /></TD>
									<TD width="70%"><h:outputText id="txtFundingArrangement"
										value="#{benefitMandateBackingBean.fundingArrangement}"></h:outputText>
									</TD>


								</TR>

								<TR>
									<TD width="30%">&nbsp;<h:outputText id="EffectivenessStar"
										value="Effectiveness " /></TD>
									<td width="70%"><h:inputTextarea styleClass="selectDivReadOnly"
										tabindex="5" readonly="true" style="border:0"
										value="#{benefitMandateBackingBean.effectiveness}"></h:inputTextarea>
									<td width="63%" align="right"></td>

								</TR>


								<TR>
									<TD valign="top" width="30%">&nbsp;<h:outputText
										id="descriptionText" value="Text " /></TD>
									<TD valign="top" width="70%"><h:outputText
										id="txtDescriptionText"
										value="#{benefitMandateBackingBean.text}"></h:outputText></TD>
									<td width="63%"></td>
								</TR>



								<TR>
									<TD width="30%">&nbsp;</TD>
								</TR>
							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				</div>
				</TD>
			</TR>


			<tr>
				<td>
				<DIV id="noNote">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD class="ContentArea"><!-- start of actual data -->
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								<DIV id="resultHeaderDivNotes">
								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" id="resultHeaderTable1" border="0">
									<TR>
										<TD><strong><h:outputText value="Associated Notes"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" cellpadding="3"
									id="resultHeaderTable1" border="0" width="100%">
									<TBODY>

										<TR class="outputText">
											<TD align="left" width="47.5%"><h:outputText
												value="No Associated Notes Available."></h:outputText></TD>
										</TR>
									</TBODY>
								</TABLE>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</DIV>
				<div id="NotesDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!-- start of actual data -->
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD width="100%">
								<DIV id="resultHeaderDiv">
								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" id="resultHeaderTable1" border="0">
									<TR>
										<TD width="100%"><strong><h:outputText
											value="Associated Notes"></h:outputText></strong></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable1" border="0" width="100%">
									<TBODY>

										<TR class="dataTableColumnHeader">
											<TD align="left" width="47.5%"><h:outputText
												value="Note Name"></h:outputText></TD>


										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD width="100%">
								<DIV id="searchResultdataTableDiv">
								<FIELDSET width ="100%"><!-- Search Result Data Table --> <h:dataTable
									rendered="#{standardBenefitNotesBackingBean.associatedNotesList!= null}"
									styleClass="outputText" headerClass="dataTableHeader"
									id="NotesTable" var="singleValue" cellpadding="1" width="100%"
									cellspacing="1"
									value="#{standardBenefitNotesBackingBean.associatedNotesList}">

									<h:column>
										<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
									</h:column>
								</h:dataTable></FIELDSET>
								</DIV>
								</TD>
							</TR>
						</TABLE>
						</TD>
					</TR>

				</table>
				</div>
				</TD>
			</TR>

			<TR>
				<TD>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"></TD>
					</TR>
					<!-- Space for hidden fields -->
					<h:inputHidden id="hidden2" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk2"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

				</table>
				</TD>
			</TR>
		</table>
	</h:form>
	</BODY>
</f:view>



</HTML>
<script language="javascript">
if(document.getElementById('benefitDetailPrintForm:benefitDefinitionsTable') != null)
	setColumnWidth('benefitDetailPrintForm:benefitDefinitionsTable', '40%:20%:20%:20%');

formatTildaToComma("benefitDetailPrintForm:txtLob");
formatTildaToComma("benefitDetailPrintForm:txtBusinessEntity");
formatTildaToComma("benefitDetailPrintForm:txtBusinessGroup");
formatTildaToComma("benefitDetailPrintForm:txtMarketBusinessUnit");
formatTildaToComma("benefitDetailPrintForm:txtTerm");
formatTildaToComma1("benefitDetailPrintForm:txtRule");
formatTildaToComma("benefitDetailPrintForm:txtQualifier");
formatTildaToComma("benefitDetailPrintForm:txtProviderArrangement");
formatTildaToCommaForDatatype("benefitDetailPrintForm:txtDataType");

copyHiddenToInputText('benefitDetailPrintForm:CorpPlanCd_list1','benefitDetailPrintForm:benefitId',1);
//copyHiddenToInputText('benefitDetailPrintForm:Benefit_Category_list1','benefitDetailPrintForm:benefitCategoryId',1);
//copyHiddenToInputText1('benefitDetailPrintForm:txtRule','benefitDetailPrintForm:ruleId',1);

var i;
var obj;
obj = document.getElementById("benefitDetailPrintForm:CorpPlanCd_list1");

i= obj.value;
	if(i=="MANDATE")
	{
	sub1.style.display='';
	}
	else 
	{
	sub1.style.display='none';
	//sub2.style.display='none';
	}
var i;
var obj;
obj = document.getElementById("benefitDetailPrintForm:Mandate_type_list1");
i= obj.value;
		if(i== "ET" || i=="State")
		{
		//sub2.style.display='';		
		}
		else 
		{
		//sub2.style.display='none';
		}

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



formatTildaToCommaForStateCode("benefitDetailPrintForm:txtState");
	formatTildaToCommaForFundingarrangement("benefitDetailPrintForm:txtFundingArrangement");
		formatTildaToComma("benefitDetailPrintForm:CitationNumber");
	// parseForDiv(document.getElementById('CitationNumberDiv'), document.getElementById('benefitDetailPrintForm:txtCitationNumber')); 

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

		for(i=0, n = values.length; i < n; i+=3)
		{
			formattedString += values[i+1] ;
			if(i < n-3)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}
function formatTildaToCommaForFundingarrangement(objName)
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
			if(i < n-2)
			formattedString += ", " ;
		}
		obj.innerHTML = formattedString;
	}
	return ;
}
hideIfNoValue('stateCodeDiv','txtState');

function hideIfNoValue(divId, txtName){
		hiddenIdObj = document.getElementById('benefitDetailPrintForm:'+txtName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj.value == 'null' || hiddenIdObj.value == 'undefined'){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}	


hideValues();

function hideValues(){
	var tab;
	tab = document.getElementById("benefitDetailPrintForm:tabHidden").value ;
	
	if(tab=="Standard Definition"){
		document.getElementById('mandateInfoDiv').style.visibility = 'hidden';
		document.getElementById('mandateInfoDiv').innerHTML = '';
		document.getElementById('NotesDiv').style.visibility = 'visible';

	}
	else{
		document.getElementById('mandateInfoDiv').style.visibility = 'visible';
		document.getElementById('NotesDiv').style.visibility = 'hidden';
		document.getElementById('NotesDiv').innerHTML = '';
		document.getElementById('resultHeaderDivNotes').style.visibility = 'hidden';
	}
}

	if(null == document.getElementById('benefitDetailPrintForm:benefitDefinitionsTable')){
			document.getElementById('benefitDefnDiv').style.visibility = 'hidden';
			document.getElementById('benefitDefnDiv').innerHTML = '';
			document.getElementById('nobenefitdef').style.visibility = 'visible';	
	}else{
			document.getElementById('benefitDefnDiv').style.visibility = 'visible';
			document.getElementById('nobenefitdef').style.visibility = 'hidden';
			document.getElementById('nobenefitdef').innerHTML = '';	
	}
	if(null == document.getElementById('benefitDetailPrintForm:NotesTable')){
			document.getElementById('NotesDiv').style.visibility = 'hidden';
			document.getElementById('NotesDiv').innerHTML = '';
			document.getElementById('noNote').style.visibility = 'visible';	
	}else{
			document.getElementById('NotesDiv').style.visibility = 'visible';
			document.getElementById('noNote').style.visibility = 'hidden';
			document.getElementById('noNote').innerHTML = '';	
	}
window.print();
</script>
