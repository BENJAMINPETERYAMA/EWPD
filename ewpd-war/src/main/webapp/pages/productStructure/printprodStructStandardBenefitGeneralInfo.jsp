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
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="benefitDefinitionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
                              &nbsp;&nbsp;
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;&nbsp;&nbsp;&nbsp;<STRONG>Benefit</STRONG>
                        </TD>
                  </TR>
			<TR>
				<TD colspan="2" valign="top" class="ContentArea">
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
				<TABLE border="0" cellspacing="0" cellpadding="2" class="outputText">
					<TBODY>

						<TR>

							<h:inputHidden id="getGenInfoValue"
								value="#{productStructureBenefitDefenitionBackingBean.printBenefitDefValues}"></h:inputHidden>
							<TD colspan="5" width="437">
							<TABLE border="0" cellspacing="0" cellpadding="3">
								<TBODY>
									<TR>
										<TD colspan="4">
										<div id="panel2Header" class="tabTitleBar" 
                                        style="position:relative;width:140%; "><STRONG>General
										Information</STRONG></div>
										<br />
										<!-- </TD>
									</TR> -->
                                    <TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
							         <TBODY>
								      <TR>
								       <TD colspan="5">
                                <FIELDSET style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
								<LEGEND><FONT color="black">Business Domain</FONT></LEGEND><br>
                                <TABLE border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="151">&nbsp;<h:outputText value="Line of Business" /></TD>
										<TD width="263"><h:outputText id="txtLob"
											value="#{productStructureBenefitDefenitionBackingBean.lob}"></h:outputText></TD>
									</TR>
                                    <TR><TD width="151"><br></TD></TR>
									<TR>
										<TD width="151">&nbsp;<h:outputText value="Business Entity" /></TD>
										<TD width="263"><h:outputText id="txtBusinessEntity"
											value="#{productStructureBenefitDefenitionBackingBean.entity}"></h:outputText></TD>
									</TR>
                                    <TR><TD width="151"><br></TD></TR>
									<TR>
										<TD width="151">&nbsp;<h:outputText value="Business Group" /></TD>
										<TD width="263"><h:outputText id="txtBusinessGroup"
											value="#{productStructureBenefitDefenitionBackingBean.group}"></h:outputText></TD>
									</TR>
<!-- CARS START -->                 <TR><TD width="151"><br></TD></TR>
									<TR>
										<TD width="151">&nbsp;<h:outputText value="Market Business Unit" /></TD>
										<TD width="263"><h:outputText id="txtMarketBusinessUnit"
											value="#{productStructureBenefitDefenitionBackingBean.marketBusinessUnit}"></h:outputText></TD>
									</TR>
<!-- CARS END -->
									</TABLE></FIELDSET></TD></TR>
									<TR>
										<TD width="135"><h:outputText value="Name" /></TD>
										<TD width="263"><h:outputText id="structureName"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
										<h:inputHidden id="structureNameHidden"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:structureNameHidden','benefitDefinitionForm:structureName',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="135"><h:outputText value="Benefit Meaning" /></TD>
										<TD width="263"><h:outputText id="meaning"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}" />
										<h:inputHidden id="meaningHidden"
											value="#{productStructureBenefitDefenitionBackingBean.minorHeading}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:structureNameHidden','benefitDefinitionForm:structureName',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="135"><h:outputText value="Benefit Type" /></TD>
										<TD width="263"><h:outputText id="strType"
											value="#{productStructureBenefitDefenitionBackingBean.benefitType}" />
										<h:inputHidden id="strTypeHidden"
											value="#{productStructureBenefitDefenitionBackingBean.benefitType}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:strTypeHidden','benefitDefinitionForm:strType',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="135"><h:outputText value="Benefit Category" /></TD>
										<TD width="263"><h:outputText id="benCategory"
											value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}" />
										<h:inputHidden id="benCategoryHidden"
											value="#{productStructureBenefitDefenitionBackingBean.benefitCategory}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:benCategoryHidden','benefitDefinitionForm:benCategory',1); </SCRIPT>
										</TD>

									</TR>

									<tr id="sub1" style="display:none;">
										<TD width="135"><h:outputText value="Mandate Type" /></TD>
										<TD width="263"><h:outputText id="mandateType"
											value="#{productStructureBenefitDefenitionBackingBean.mandateType}" />
										<h:inputHidden id="mandTypeHidden"
											value="#{productStructureBenefitDefenitionBackingBean.mandateType}">
										</h:inputHidden><SCRIPT>copyHiddenToInputText('benefitDefinitionForm:mandTypeHidden','benefitDefinitionForm:mandateType',1);</SCRIPT>
										</TD>
									</TR>
                                    <TR>
										<TD width="135" valign="top"><h:outputText value="Description" /></TD>
										<TD width="263"><h:outputText
											value="#{productStructureBenefitDefenitionBackingBean.description}">
										</h:outputText></TD>
									</TR>
									<TR>
										<TD width="135" valign="top"><h:outputText value="Benefit Rule ID" /></TD>
										<TD width="263"><h:outputText id="ruleId"
											value="#{productStructureBenefitDefenitionBackingBean.ruleId}"
											styleClass="" /> <h:inputHidden id="ruleIdHidden"
											value="#{productStructureBenefitDefenitionBackingBean.ruleId}">
										</h:inputHidden></TD>
									</TR>		
									<TR>
										<TD width="135" valign="top"><h:outputText value="Tier Definition" /></TD>												
										<TD width="263">
										<h:outputText id="txtTier" 
												value="#{productStructureBenefitDefenitionBackingBean.tierProductStructure}"></h:outputText>
										</TD>
									</TR>							
									<TR>
										<TD width="130"><h:outputText value="Term" /></TD>
										<TD width="263"><h:outputText id="txtTerm"
											value="#{productStructureBenefitDefenitionBackingBean.term}"></h:outputText></TD>
									</TR>
									<TR>
										<TD width="130"><h:outputText value="Qualifier" /></TD>
										<TD width="263"><h:outputText id="txtQualifier"
											value="#{productStructureBenefitDefenitionBackingBean.qualifier}"></h:outputText></TD>
									</TR>
									<TR>
										<TD width="130" valign="top"><h:outputText value="Provider Arrangement" /></TD>
										<TD width="263"><h:outputText id="txtProviderArrangement"
											value="#{productStructureBenefitDefenitionBackingBean.providerArrangement}"></h:outputText></TD>
									</TR>
									<TR>
										<TD width="130" valign="top"><h:outputText value="Data Type" /></TD>
										<TD width="263"><h:outputText id="txtDataType"
											value="#{productStructureBenefitDefenitionBackingBean.dataType}"></h:outputText></TD>
									</TR>
									<TR>
										<TD width="135"><h:outputText value="Version" /></TD>
										<TD width="263"><h:outputText id="versionId"
											value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}" />
										<h:inputHidden id="benefitVersionHidden"
														value="#{productStructureBenefitDefenitionBackingBean.benefitVersion}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitGeneralInfoForm:benefitVersionHidden','benefitGeneralInfoForm:versionId',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="135"><h:outputText value="Created By" /></TD>
										<TD width="263"><h:outputText id="createdBy"
											value="#{productStructureBenefitDefenitionBackingBean.createdBy}" />
										<h:inputHidden id="createdByHidden"
											value="#{productStructureBenefitDefenitionBackingBean.createdBy}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:createdByHidden','benefitDefinitionForm:createdBy',1); </SCRIPT>
										</TD>
									</TR>
									<TR>
										<TD width="135"><h:outputText value="Created Date" /></TD>
										<TD width="263"><h:outputText id="creationDate"
											value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDateHidden"
											value="#{productStructureBenefitDefenitionBackingBean.creationDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:creationDateHidden','benefitDefinitionForm:creationDate',1); </SCRIPT>
										<h:inputHidden id="time" value="#{requestScope.timezone}">
										</h:inputHidden></TD>
									</TR>
									<TR>
										<TD width="135"><h:outputText value="Last Updated By" /></TD>
										<TD width="263"><h:outputText id="updatedBy"
											value="#{productStructureBenefitDefenitionBackingBean.updatedBy}" />
										<h:inputHidden id="updatedByHidden"
											value="#{productStructureBenefitDefenitionBackingBean.updatedBy}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:updatedByHidden','benefitDefinitionForm:updatedBy',1); </SCRIPT>
										</TD>

									</TR>
									<TR>
										<TD width="135"><h:outputText value="Last Updated Date" /></TD>
										<TD width="263"><h:outputText id="updatedDate"
											value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updatedDateHidden"
											value="#{productStructureBenefitDefenitionBackingBean.updatedDate}">
										</h:inputHidden> <h:inputHidden id="printHidden"
											value="#{productStructureBenefitMandatesBackingBean.printMandateInformation}">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('benefitDefinitionForm:updatedDateHidden','benefitDefinitionForm:updatedDate',1); </SCRIPT>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
</TD></TR></TBODY></TABLE></fieldset>
</td>
</tr>

<tr>
<TD colspan="2" valign="top" class="ContentArea">
				<fieldset 
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<h:inputHidden id="getValue"
							value="#{productStructureBenefitDefenitionBackingBean.printBenefitDefinition}"></h:inputHidden>
						<TD colspan="2" valign="top" class="ContentArea"><h:outputText
							value="No Standard Definitions Available."
							rendered="#{productStructureBenefitDefenitionBackingBean.benefitDefinitonsList == null}"
							styleClass="dataTableColumnHeader" />
</TD>
					</TR>
				</table>
						<div id="benefitDefitionsDiv">
						<table width="100%" border="0" bordercolor="green" cellspacing="0"
							cellpadding="0">
							<tr>
								<td valign="top" class="ContentArea">
								<TABLE class="smallfont" id="resultsTable" width="100%"
									cellpadding="3" cellspacing="1" border="0" bordercolor="red">
									<TR>
										<TD colspan="4"><div id="panel2Header" class="tabTitleBar" 
                                        style="position:relative;width:100% "><STRONG><h:outputText
											value="Associated Benefit Levels" /></STRONG></div></TD>
									</TR>
									<TR align="left">
										<TD class="ContentArea" align="left" valign="top" width="100%">
										<table class="smallfont" id="resultsTable" width="100%"
											cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td>
												<div id="resultHeaderDiv">
												<TABLE id="levelHeaderTable" width="100%" border="0"
													cellpadding="1" cellspacing="1" bgcolor="#cccccc">
													<tr class="dataTableOddRow">
														<td width="18%"><strong><h:outputText value="Description" /></strong></td>
														<td width="16%"><strong><h:outputText value="Term" /></strong></td>
														<td width="20%"><strong><h:outputText value="Frequency - Qualifier" /></strong></td>
														<td width="7%"><strong><h:outputText value="PVA" /></strong></td>
														<td width="10%"><strong><h:outputText value="Format" /></strong></td>
														<td width="9%"><strong><h:outputText value="Benefit Value" /></strong></td>
														<td width="20%"><strong><h:outputText value="Reference" /></strong></td>
													</tr>
												</TABLE>
												</div>
												</td>
											</tr>
											<tr>
												<td bordercolor="#cccccc">
												<div id="searchResultdataTableDivStdDef"
													><h:dataTable cellspacing="1"
													id="bComponentDataTable"
													rendered="#{productStructureBenefitDefenitionBackingBean.printBenftDefnList != null}"
													value="#{productStructureBenefitDefenitionBackingBean.printBenftDefnList}"
													rowClasses="dataTableOddRow" var="singleValue"
													cellpadding="3" width="100%" bgcolor="#cccccc">
													<h:column>
														<h:outputText id="question"
															value="#{singleValue.levelDesc}" />
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
														<h:outputText id="format"
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
											
										</table>
										</TD>


									</TR>
								</TABLE>
								<!--	Start of Table for actual Data	--></td>
							</tr>
						</table>
						</div>
						<%--/DIV> --%> <!--	End of Page data	-->

				</fieldset>
</td>
</tr>
<tr>
<TD colspan="2" valign="top" class="ContentArea">
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
				<div id="mandateDiv">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="mandateInfoTable">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!--	Start of Table for actual Data	-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							id="mandateInfoTable1">
							<tr>

								<td colspan="2" valign="top" class="ContentArea">
								<div id="panel2Header" style="position:relative;width:100% "><STRONG>Mandate
								Information</STRONG></div>
								<br />

								<fieldset
									style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%"
									id="mandateInfoTable2"><!--	Start of Table for actual Data	-->
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR id="stateRow">
										<TD width="24%" valign="top">&nbsp;<h:outputText id="stateCd"
											value="Jurisdiction " /></TD>
										<TD width="42%" valign="top">
										<!-- <DIV id="StateDiv" class="selectDivReadOnly"></DIV> -->
										<h:outputText id="state"
											value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:outputText>
										<h:inputHidden id="txtState"
											value="#{productStructureBenefitMandatesBackingBean.stateCode}"></h:inputHidden></TD>
										<td width="63%"><f:verbatim></f:verbatim></td>
									</TR>
									<tr>
										<td width="24%">&nbsp;<h:outputText id="MandateTypeStar"
											value="Mandate Category " /></td>
										<td width="42%"><h:outputText
											value="#{productStructureBenefitMandatesBackingBean.mandateCategory}"></h:outputText></td>

									</tr>
									<TR>
										<TD width="24%">&nbsp;<h:outputText id="CitationNumberStar"
											value="Citation Number " /></TD>
										<TD width="42%">
										<!-- <DIV id="CitationNumberDiv" class="selectDivReadOnly"></DIV> -->
										<h:outputText id="citationNumber"
											value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:outputText>
										<h:inputHidden id="CitationNumberHidden"
											value="#{productStructureBenefitMandatesBackingBean.citationNumber}"></h:inputHidden>
										</TD>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText
											id="FundingArrangementStar" value="Funding Arrangement " /></TD>
										<TD width="42%">
										<DIV id="FundingArrangementDiv" class="selectDivReadOnly" style="border:0"></DIV>
										<h:inputHidden id="FundingArrangementHidden"
											value="#{productStructureBenefitMandatesBackingBean.fundingArrangement}"></h:inputHidden>

										</TD>
									</TR>

									<TR>
										<TD width="24%">&nbsp;<h:outputText id="EffectivenessStar"
											value="Effectiveness " /></TD>
										<td width="42%"><h:outputText
											value="#{productStructureBenefitMandatesBackingBean.effectiveness}"></h:outputText>

										</td>
									</TR>


									<TR>
										<TD width="24%" valign="top">&nbsp;<h:outputText
											id="descriptionStar" value="Text " /></TD>
										<TD width="42%" valign="top"><h:outputText
											value="#{productStructureBenefitMandatesBackingBean.text}"
											></h:outputText></TD>
										<td width="63%"><f:verbatim></f:verbatim></td>
									</TR>


								</table>
							
								</fieldset>
								<br />
								</td>
								
							</tr>
						</table>
						<!--	End of Page data	--></TD>
					</TR>
				</table>
				</div>
            			                
						<!--	Start of Table for actual Data	-->
                        <DIV id="panel2Header" class="tabTitleBar"
										style="position:relative;width:100% "><STRONG><h:outputText
										value="Associated Notes"></h:outputText></STRONG></DIV>
                        <BR />
					    <div id="emptymsg"><h:outputText
									value="No Notes Available."	/>
			            <div id="noteDiv">
						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" id="notetable">

							<tbody>
								                                
								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR>
												<td align="left" width="25%"><strong><h:outputText value="Note Name"></h:outputText></strong>
												</td>
											</TR>
										</TBODY>
									</TABLE>
									</div></TD>
								</TR>
								
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										><!-- Search Result Data Table -->
									<h:dataTable id="NotesTable"
										rendered="#{productStructureBenefitDefenitionBackingBean.associatedNotesList!= null}"
										headerClass="dataTableHeader" 
										var="singleValue" cellpadding="1" width="100%" cellspacing="1"
										value="#{productStructureBenefitDefenitionBackingBean.associatedNotesList}">

										<h:column>
											<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText><br>
										</h:column>

									</h:dataTable></DIV>
									</TD>
								</TR>
							</tbody>

						</TABLE>
                        </div>
						</div>
				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> <h:inputHidden id="statHidden"
					value="#{productStructureBenefitMandatesBackingBean.stateCode}" />

				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --></fieldset></TD>
			</TR>
		</table>
	</h:form>
	</BODY>
</f:view>

<script>
		formatTildaToComma("benefitDefinitionForm:txtLob");
		formatTildaToComma("benefitDefinitionForm:txtBusinessEntity");
		formatTildaToComma("benefitDefinitionForm:txtBusinessGroup");
		formatTildaToComma("benefitDefinitionForm:txtMarketBusinessUnit");
		formatTildaToComma("benefitDefinitionForm:txtTerm");
		formatTildaToComma("benefitDefinitionForm:txtQualifier");
		formatTildaToComma("benefitDefinitionForm:txtProviderArrangement");
		formatTildaToComma("benefitDefinitionForm:txtDataType");
getStructureType();
		//for tier definitions
		formatTilda("benefitDefinitionForm:txtTier"); 

function getStructureType()
	{
	var obj;
	obj = document.getElementById("benefitDefinitionForm:strType");
		if(obj.value== "Mandate" || obj.value == "MANDATE")
		{
		sub1.style.display='';		
		}
		else 
		{
		sub1.style.display='none';
		}
	}


		
		if(document.getElementById('benefitDefinitionForm:bComponentDataTable') != null){
			//setColumnWidth('levelHeaderTable','18%:22%:15%:7%:10%:9%:20%');	
			setColumnWidth('benefitDefinitionForm:bComponentDataTable','18%:16%:20%:7%:10%:9%:20%');	
			}else{
				document.getElementById('benefitDefitionsDiv').style.visibility = 'hidden';
		}


	// copyHiddenToDiv_ewpd('benefitDefinitionForm:txtState','StateDiv',2,2);
	formatTildaToCommaForStateCode('benefitDefinitionForm:state');
	copyHiddenToDiv_ewpd('benefitDefinitionForm:FundingArrangementHidden','FundingArrangementDiv',2,2); 
	// copyHiddenToDiv_ewpd('benefitDefinitionForm:CitationNumberHidden','CitationNumberDiv',2,2); 
	formatTildaToCommaForStateCode('benefitDefinitionForm:citationNumber');

	// displayState();
	function displayState(){
	var state = document.getElementById('benefitDefinitionForm:statHidden').value;
	if(state=="" || state==null)
		stateRow.style.display='none';

	else
		stateRow.style.display='';

	}
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

	initialize();
	function initialize(){
		obj = document.getElementById("benefitDefinitionForm:strType");
			if(obj.value== "STANDARD" ){

				document.getElementById("mandateInfoTable").style.display = 'none';
				showNote();
			}else{
				document.getElementById("notetable").style.display = 'none';
			// 	document.getElementById('noteDiv').style.visibility = 'hidden';	
				document.getElementById('emptymsg').style.visibility = 'hidden';	
			}		
	}
	function showNote(){
		if(null != document.getElementById('benefitDefinitionForm:NotesTable')){

			// setColumnWidth('productStructureBenefitPrintNotesForm:NotesTable','25%');
			document.getElementById('noteDiv').style.visibility = 'visible';			
			document.getElementById('emptymsg').style.visibility = 'hidden';	
		}else{
			document.getElementById('noteDiv').style.visibility = 'hidden';		
			document.getElementById('emptymsg').style.visibility = 'visible';	
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
</script>
</HTML>
