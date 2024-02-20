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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 50%;
	
}
</style>

<TITLE>Benefit Component General Information</TITLE>
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
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="getValue"
				value="#{productStructureGeneralInfoBackingBean.benefitComponentGenInfo}"></h:inputHidden>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="productStructureBenefitCompForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
					
					<!--<TR>
						<TD align="left" colspan="2"><STRONG>&nbsp;&nbsp; Product
						Configuration : Product Structure (<h:outputText id="txtLabel"
							value="#{productStructureGeneralInfoBackingBean.name}"></h:outputText>):
						Print</STRONG>
						<TD width="130"></TD>
						<TD width="100"></TD>
					</TR>
					-->
					<TR>
						<TD><STRONG>&nbsp;&nbsp; Benefit Component</STRONG></TD>
					</TR>
					<TR>
					<TD>&nbsp;</TD>
					</TR>
					<TR>

						<TD><!-- colspan="2" valign="top" class="ContentArea">!-->
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
									<div id="panel2Header" class="tabTitleBar"
										style="position:relative;width:100% "><STRONG>General
									Information</STRONG></div>

						<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
							<TBODY>
								<TR>
								  <TD colspan="5">
                                <FIELDSET style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
								<LEGEND><FONT color="black">Business Domain</FONT></LEGEND><br>
                                <TABLE border="0" cellspacing="0" cellpadding="0">
								<tr>
									<TD width="130">&nbsp;<h:outputText value="Line of Business" /></TD>
									<TD width="194"><h:outputText id="txtLob"
										value="#{productStructureGeneralInfoBackingBean.lob}"></h:outputText></TD>
								</TR>
                                <TR><TD width="10"><br></TD></TR>
								<TR>
									<TD width="130">&nbsp;<h:outputText value="Business Entity" /></TD>
									<TD width="194"><h:outputText id="txtBusinessEntity"
										value="#{productStructureGeneralInfoBackingBean.entity}"></h:outputText>
									</TD>
								</TR>
                                <TR><TD width="10"><br></TD></TR>
								<TR>
									<TD width="130">&nbsp;<h:outputText value="Business Group" /></TD>
									<TD width="194"><h:outputText id="txtBusinessGroup"
										value="#{productStructureGeneralInfoBackingBean.group}"></h:outputText>
									</TD>
								</TR>
<!-- CARS START -->
                                <TR><TD width="10"><br></TD></TR>
								<TR>
									<TD width="130">&nbsp;<h:outputText value="Market Business Unit" /></TD>
									<TD width="194"><h:outputText id="txtMarketBusinessUnit"
										value="#{productStructureGeneralInfoBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
<!-- CARS END -->							
								</TABLE></FIELDSET></TD></TR>

								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Name" /></TD>
									<TD width="239"><h:outputText id="name"
										value="#{productStructureGeneralInfoBackingBean.name}" /> <h:inputHidden
										id="nameHidden"
										value="#{productStructureGeneralInfoBackingBean.name}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:nameHidden','productStructureBenefitCompForm:name',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Component Type" /></TD>
									<TD width="239"><h:outputText id="strType"
										value="#{productStructureGeneralInfoBackingBean.componentType}" />
									<h:inputHidden id="strTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.componentType}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:strTypeHidden','productStructureBenefitCompForm:strType',1); </SCRIPT>
									</TD>

								</TR>

								<tr id="sub1" style="display:none;">
									<TD width="135"><h:outputText value="Mandate Type" /></TD>
									<TD width="239"><h:outputText id="mandateType"
										value="#{productStructureGeneralInfoBackingBean.mandateType}" />
									<h:inputHidden id="mandTypeHidden"
										value="#{productStructureGeneralInfoBackingBean.mandateType}">
									</h:inputHidden><SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:mandTypeHidden','productStructureBenefitCompForm:mandateType',1);</SCRIPT>
									</TD>
								</TR>
								<tr id="sub2" style="display:none;">
									<TD width="135"><h:outputText value="Jurisdiction" /></TD>
									<TD width="239"><h:inputTextarea styleClass="selectDivReadOnly"
										id="stateCde"
										value="#{productStructureGeneralInfoBackingBean.stateCode}"
										readonly="true" style="border:0"></h:inputTextarea><h:inputHidden
										id="stateCdeHidden"
										value="#{productStructureGeneralInfoBackingBean.stateCode}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:stateCdeHidden','productStructureBenefitCompForm:stateCde',1); </SCRIPT>
									</TD>
								</TR>
								<tr id="sub3" style="display:none;">
									<TD width="135"><h:outputText value="Jurisdiction" /></TD>
									<TD width="239"><h:outputText id="stateFed" value="ALL" /></TD>
								</TR>
                                <TR>
									<TD width="135" valign="top">&nbsp;&nbsp;<h:outputText value="Description" /></TD>
									<TD width="239"><h:outputText id="desc"
										value="#{productStructureGeneralInfoBackingBean.description}" />
									<h:inputHidden id="descHidden"
										value="#{productStructureGeneralInfoBackingBean.description}">
									</h:inputHidden></TD>
								</TR>
								<TR>

									<TD width="135">&nbsp;&nbsp;<h:outputText value="Benefit Rule ID" /></TD>
									<TD width="239"><h:outputText id="ruleId"
										value="#{productStructureGeneralInfoBackingBean.ruleId}"
										styleClass="" /> <h:inputHidden id="ruleIdHidden"
										value="#{productStructureGeneralInfoBackingBean.ruleId}">
									</h:inputHidden></TD>
								</TR>
								<TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Effective Date" /></TD>
									<TD width="239"><h:outputText
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}"
										id="effDate" /> <h:inputHidden id="effDateHidden"
										value="#{productStructureGeneralInfoBackingBean.effectiveDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:effDateHidden','productStructureBenefitCompForm:effDate',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Expiry Date" /></TD>
									<TD width="239"><h:outputText id="expiryDate"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}" />
									<h:inputHidden id="expiryDateHidden"
										value="#{productStructureGeneralInfoBackingBean.expiryDate}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:expiryDateHidden','productStructureBenefitCompForm:expiryDate',1); </SCRIPT>

									</TD>
								</TR>
								<TR>
									<TD width="122">&nbsp;&nbsp;<h:outputText value="Version" /></TD>
									<TD width="326"><h:outputText id="bnftCmpntVersion"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}" />
									<h:inputHidden id="bnftCmpntVersionHidden"
										value="#{productStructureGeneralInfoBackingBean.bnftCmpntVersion}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:bnftCmpntVersionHidden','productStructureBenefitCompForm:bnftCmpntVersion',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Created By" /></TD>
									<TD width="239"><h:outputText id="createdUser"
										value="#{productStructureGeneralInfoBackingBean.createdUser}" />
									<h:inputHidden id="createdUserHidden"
										value="#{productStructureGeneralInfoBackingBean.createdUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:createdUserHidden','productStructureBenefitCompForm:createdUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Created Date" /></TD>
									<TD width="239"><h:outputText id="creationDate"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="creationDateHidden"
										value="#{productStructureGeneralInfoBackingBean.creationDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:creationDateHidden','productStructureBenefitCompForm:creationDate',1); </SCRIPT>
									<h:inputHidden id="time" value="#{requestScope.timezone}">
									</h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Last Updated By" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedUser"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}" />
									<h:inputHidden id="updatedUserHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedUser}">
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:updatedUserHidden','productStructureBenefitCompForm:lastUpdatedUser',1); </SCRIPT>
									</TD>
								</TR>
								<TR>
									<TD width="135">&nbsp;&nbsp;<h:outputText value="Last Updated Date" /></TD>
									<TD width="239"><h:outputText id="lastUpdatedDate"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<h:inputHidden id="updatedDateHidden"
										value="#{productStructureGeneralInfoBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:inputHidden> <SCRIPT>copyHiddenToInputText('productStructureBenefitCompForm:updatedDateHidden','productStructureBenefitCompForm:lastUpdatedDate',1); </SCRIPT>
									</TD>
								</TR>
							</TBODY>
						</TABLE>

						</fieldset>
						</TD>
					</TR>

<h:inputHidden id="getBenefitValue"
									value="#{productStructureGeneralInfoBackingBean.printProductStructureAssociatedBenefits}"></h:inputHidden>
					<tr id="prodStructAssoBnefits">
						<td>
						
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% "><strong>
							Associated Standard Benefits</strong></div>
						<table class="smallfont" id="benefitResultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="benefitResultHeaderDiv">
								<TABLE id="headerTable" width="80%" border="0" cellpadding="2"
										cellspacing="1">
										<tr class="dataTableOddRow">
											<td><strong><h:outputText value="Benefit Name" /></strong></td>
										</tr>
										<tr><td><br></td></tr>
									</TABLE>
								</div>
								<div id="InformationBenefitsDiv"></div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="benefitSearchResultdataTableDiv" style="width:100%;"><h:dataTable
									cellspacing="1" id="associatedBenefitsDataTable" columnClasses="selectOrOptionColumn"
									rendered="#{productStructureGeneralInfoBackingBean.benefitDetailsList != null}"
										value="#{productStructureGeneralInfoBackingBean.benefitDetailsList}"
									var="singleValue" cellpadding="2" width="100%">
										
										<h:column>
											<h:outputText id="Name" value="#{singleValue.standardBenefitDesc}"></h:outputText>
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							
						</table>
				</fieldset>
						</td>						
					</tr>
					<tr id="notesTab">

						<td>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:70%">
						<!--	Start of Table for actual Data	--> 
                        <DIV id="panel2Header" class="tabTitleBar"
									style="position:relative;width:100% "><STRONG><h:outputText
									value="Associated Notes"></h:outputText></STRONG></DIV>
                        <BR/>
						<div id="emptymsg"><h:outputText value="No Notes Available." /></div>
						<div id="noteDiv">
						<TABLE width="100%" cellpadding="3" cellspacing="0" border="0"
							id="notetable">
							<TR>
								<TD>
								<div id="resultHeaderDiv">
								<TABLE border="0" cellspacing="0" cellpadding="3"
									class="outputText">
									<TBODY>
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
								<DIV id="searchResultdataTableDiv" style="overflow: auto;"><!-- Search Result Data Table -->
								<h:dataTable
									rendered="#{productStructureGeneralInfoBackingBean.associatedNotesPrintList!= null}"
									styleClass="outputText" headerClass="dataTableHeader"
									id="NotesTable" var="singleValue" cellpadding="1" width="100%"
									cellspacing="1"
									value="#{productStructureGeneralInfoBackingBean.associatedNotesPrintList}">

									<h:column>
										<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
									</h:column>

								</h:dataTable></DIV>
								</TD>
							</TR>

						</TABLE>
						</div>
						</fieldset>
						</td>
					</tr>
				</table>
			</h:form></td>
		</tr>
	</table>
	<SCRIPT>

initialize();
	function initialize(){
		if(null != document.getElementById('productStructureBenefitCompForm:associatedBenefitsDataTable')){
			if(document.getElementById('productStructureBenefitCompForm:associatedBenefitsDataTable').rows.length == 0){
				document.getElementById('benefitResultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('benefitSearchResultdataTableDiv').style.height = '1px';
				document.getElementById('benefitSearchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationBenefitsDiv').innerHTML = "No Benefits Available";
			}
		}
	}

		if(null != document.getElementById('productStructureBenefitCompForm:NotesTable')){
			document.getElementById('noteDiv').style.visibility = 'visible';			
			document.getElementById('emptymsg').style.visibility = 'hidden';			
		}else{
		    document.getElementById('noteDiv').style.visibility = 'hidden'; 			
			document.getElementById('emptymsg').style.visibility = 'visible';
		}
		formatTildaToComma("productStructureBenefitCompForm:txtLob");
		formatTildaToComma("productStructureBenefitCompForm:txtBusinessEntity");
		formatTildaToComma("productStructureBenefitCompForm:txtBusinessGroup");
		formatTildaToComma("productStructureBenefitCompForm:txtMarketBusinessUnit");

getStructureType();
getMandateType();
function getStructureType()
	{
	var obj;
	obj = document.getElementById("productStructureBenefitCompForm:strType");
		if(obj.value== "Mandate" || obj.value == "MANDATE" ||obj.value== "Mandate")
		{
		sub1.style.display='';		
		document.getElementById("notetable").style.display = 'none';
		}
		else 
		{
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
		}
	}

function getMandateType()
	{
	var obj;
	obj = document.getElementById("productStructureBenefitCompForm:mandateType");
	if( obj.value == "ET" || obj.value == "ST" || obj.value== "Extra-Territorial" || obj.value== "State"){
				if(obj.value=="ST")
					document.getElementById("productStructureBenefitCompForm:mandateType").value = "State";
				else
					document.getElementById("productStructureBenefitCompForm:mandateType").value = "Extra-Territorial";

		sub2.style.display='';
		sub3.style.display='none';
	}else if(obj.value=="Federal"){
		document.getElementById("productStructureBenefitCompForm:mandateType").value = "Federal";
		sub3.style.display='';
		sub2.style.display='none';
	}else {
		sub1.style.display='none';
		sub2.style.display='none';
		sub3.style.display='none';
	}
	}
displayMandateTab();
	function displayMandateTab(){
	var benType = document.getElementById("productStructureBenefitCompForm:strType");
	if(benType.value== "Mandate" || benType.value == "MANDATE" ||benType.value== "Mandate"||benType.value =="Mandate Definition"){
		notesTab.style.display='none';
		prodStructAssoBnefits.style.display='none';
	}
	else{
		notesTab.style.display='';
	prodStructAssoBnefits.style.display='';
	}
}

</SCRIPT>
	<script>window.print();</script>
	</BODY>
</f:view>

</HTML>
