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
.selectnoteIdColumn {
	width: 50%;
}
.shortDescriptionColumn {
	width: 90%;
}
</style>
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
<TITLE>Print Product Component</TITLE>
</HEAD>

<f:view>
	<BODY>
	<h:inputHidden
		value="#{productComponentGeneralInfoBackingBean.valueForPrint}"></h:inputHidden>
	<h:form styleClass="form" id="productGenInfoDetailedForm">
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
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
				<h:outputText id="breadcrumb"
					value="#{productComponentGeneralInfoBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<!-- General information block starts -->
			<TR id="productComponentGeneralInfo">
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
							<!--Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="4">
										<FIELDSET
											style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:350">
										<LEGEND> <FONT color="black">Business Domain</FONT></LEGEND><br>
										<TABLE border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="lineOfBusiness"
													value="Line Of Business" styleClass="outputText" /></TD>
												<h:inputHidden id="lineOfBusinessHidden"
													value="#{productComponentGeneralInfoBackingBean.lineOfBusiness}"></h:inputHidden>
												<TD width="184"><h:outputText id="lineOfBusinessDiv"
													value="#{productComponentGeneralInfoBackingBean.lineOfBusiness}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
                                            <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="outputText" /></TD>
												<h:inputHidden id="businessEntityHidden"
													value="#{productComponentGeneralInfoBackingBean.businessEntity}"></h:inputHidden>
												<TD width="184"><h:outputText id="businessEntityDiv"
													value="#{productComponentGeneralInfoBackingBean.businessEntity}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>
                                            <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="businessGroup"
													value="Business Group" styleClass="outputText" /></TD>
												<h:inputHidden id="businessGroupHidden"
													value="#{productComponentGeneralInfoBackingBean.businessGroup}"></h:inputHidden>
												<TD width="184"><h:outputText id="businessGroupDiv"
													value="#{productComponentGeneralInfoBackingBean.businessGroup}"
													styleClass="outputText" /></TD>
												<TD width="24"></TD>
											</TR>    
<!-- CARS START -->                         <TR><TD><br></TD></TR>
											<TR>
												<TD width="5"></TD>
												<TD width="122"><h:outputText id="marketBusinessUnit"
													value="Market Business Unit" styleClass="outputText" /></TD>
												<h:inputHidden id="marketBusinessUnitHidden"
													value="#{productComponentGeneralInfoBackingBean.marketBusinessUnit}"></h:inputHidden>
												<TD width="184"><h:outputText id="marketBusinessUnitDiv"
													value="#{productComponentGeneralInfoBackingBean.marketBusinessUnit}"
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
										<TD width="184"><h:outputText id="productCompName_txt"
											value="#{productComponentGeneralInfoBackingBean.name}"
											styleClass="outputText" /> <h:inputHidden
											id="productNameHidden"
											value="#{productComponentGeneralInfoBackingBean.name}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim></f:verbatim></TD>
									</TR>
									<TR>
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Component Type" /></TD>
										<TD width="184"><h:outputText id="cmpType"
											value="#{productComponentGeneralInfoBackingBean.componentType}" /><h:inputHidden
											id="cmpTypeHidden"
											value="#{productComponentGeneralInfoBackingBean.componentType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub1" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Mandate Type" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="mandateType"
											value="#{productComponentGeneralInfoBackingBean.mandateType}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="mandateType1Hidden"
											value="#{productComponentGeneralInfoBackingBean.mandateType}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub2" style="display:none;">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="State" /></TD>
										<TD width="184"><h:inputTextarea
											styleClass="selectDivReadOnly" id="stateCde"
											value="#{productComponentGeneralInfoBackingBean.stateDesc}"
											readonly="true" style="border:0"></h:inputTextarea> <h:inputHidden
											id="stateCdeHidden"
											value="#{productComponentGeneralInfoBackingBean.stateDesc}">
										</h:inputHidden> <SCRIPT>copyHiddenToInputText('productGenInfoDetailedForm:stateCdeHidden','productGenInfoDetailedForm:stateCde',1); </SCRIPT>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR id="sub3" style="display:none;">
										<TD width="3"></TD>
										<TD width="135"><h:outputText value="State" /></TD>
										<TD width="239"><h:outputText id="stateFederal" value="ALL"
											styleClass="" /></TD>
									</TR>
									<TR>
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Benefit Rule ID " /></TD>
										<TD width="184"><h:outputText id="ruleId"
											value="#{productComponentGeneralInfoBackingBean.ruleIdForView}" /><h:inputHidden
											id="ruleIdHidden"
											value="#{productComponentGeneralInfoBackingBean.ruleIdForView}"></h:inputHidden>
										</TD>
										<TD width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Description" /></TD>
										<TD width="184"><h:outputText id="productDescription_txt"
											value="#{productComponentGeneralInfoBackingBean.description}"
											styleClass="outputText"></h:outputText> <h:inputHidden
											id="productCompDescription_txtHidden"
											value="#{productComponentGeneralInfoBackingBean.description}"></h:inputHidden>
										</TD>
										<TD width="40"><f:verbatim></f:verbatim></TD>
									</TR>
                                    <TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Effective Date" /></TD>
										<TD width="184"><h:outputText id="effectiveDate_txt"
											value="#{productComponentGeneralInfoBackingBean.effectiveDate}"
											styleClass="outputText" /> <h:inputHidden
											id="effectiveDate_txtHidden"
											value="#{productComponentGeneralInfoBackingBean.effectiveDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Expiry Date"
											styleClass="" /></TD>
										<TD width="184"><h:outputText id="expiryDate_txt"
											value="#{productComponentGeneralInfoBackingBean.expiryDate}"
											styleClass="outputText" /> <h:inputHidden
											id="expiryDate_txtHidden"
											value="#{productComponentGeneralInfoBackingBean.expiryDate}"></h:inputHidden>
										</TD>
										<TD valign="top" width="24"></TD>
									</TR>
									<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="bnftCmpntversion"
										value="#{productComponentGeneralInfoBackingBean.bnftCmpntversion}"
										styleClass="" /> <h:inputHidden id="bnftCmpntversionHidden"
										value="#{productComponentGeneralInfoBackingBean.bnftCmpntversion}">
									</h:inputHidden>
									</TD>
								</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created By" /></TD>
										<TD width="184"><h:outputText id="createdBy_optxt"
											value="#{productComponentGeneralInfoBackingBean.createdBy}"
											styleClass="outputText" /> <h:inputHidden
											id="createdBy_optxtHidden"
											value="#{productComponentGeneralInfoBackingBean.createdBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Created Date" /></TD>
										<TD width="184"><h:outputText id="creationDate_optxt"
											value="#{productComponentGeneralInfoBackingBean.createdDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="creationDate_optxtHidden"
											value="#{productComponentGeneralInfoBackingBean.createdDate}">
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
											value="#{productComponentGeneralInfoBackingBean.updatedBy}" />
										<h:inputHidden id="updatedBy_optxtHidden"
											value="#{productComponentGeneralInfoBackingBean.updatedBy}"></h:inputHidden>
										</TD>
										<TD width="24"><f:verbatim /></TD>
									</TR>
									<TR valign="top">
										<TD width="5"></TD>
										<TD width="122"><h:outputText value="Last Updated Date" /></TD>
										<TD width="184"><h:outputText id="updationDate_optxt"
											value="#{productComponentGeneralInfoBackingBean.lastUpdatedDate}"
											styleClass="outputText">
											<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
										<h:inputHidden id="updationDate_optxtHidden"
											value="#{productComponentGeneralInfoBackingBean.lastUpdatedDate}">
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
			<h:inputHidden id="getValue"
							value="#{productComponentGeneralInfoBackingBean.printProductAssociatedBenefits}"></h:inputHidden>
			<tr id="prodAssociatedBnfts">
				<td>
                <table width="95%" border="0" cellpadding="0" cellspacing="0"
					id="TabTable">
					<tr><td>
						
					<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:98%">
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
									cellspacing="1" id="associatedBenefitsDataTable"
									rendered="#{productComponentGeneralInfoBackingBean.benefitDetailsList != null}"
										value="#{productComponentGeneralInfoBackingBean.benefitDetailsList}"
									var="singleValue" cellpadding="2" width="100%" columnClasses="selectnoteIdColumn">
										
										<h:column>
											<h:outputText id="Name" value="#{singleValue.standardBenefitDesc}"></h:outputText>
										
									</h:column>
								</h:dataTable></div>
								</td>
							</tr>
							
						</table>
				</fieldset></td></tr></table>
				</td>
			</tr>
			<!-- Notes block starts-->
			<TR id="productComponentNotes">
				<td>
				<TABLE width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="1" valign="top" class="ContentArea">
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<div id="panel2Header" class="tabTitleBar"
							style="position:relative;width:100% ">Associated Notes</div>
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE id="headerTable" width="100%" border="0"
									bgcolor="#cccccc" cellpadding="2" cellspacing="1">
									<tr class="dataTableOddRow">

										<td width="90%"><strong><h:outputText value="Notes"
											styleClass="outputText" /></strong></td>
									</tr>
								</TABLE>
								</div>
								<DIV id="InformationDiv"></DIV>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDiv"
									style="height:165px; overflow: auto;"><h:dataTable
									cellspacing="1" id="noteDataTable"
									rendered="#{productBenefitComponentNoteBackingBean.noteList != null}"
									value="#{productBenefitComponentNoteBackingBean.noteList}"
									var="singleValue" cellpadding="3" width="100%" columnClasses="shortDescriptionColumn">
									
									<h:column>
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
		</TABLE>

		<h:inputHidden id="printproductGeneralInformation"
			value="#{productComponentGeneralInfoBackingBean.printValue}"></h:inputHidden>
		<h:inputHidden id="printproductNotes"
			value="#{productBenefitComponentNoteBackingBean.printValue}"></h:inputHidden>
	</h:form>
	</BODY>
</f:view>

<script>
	var printForGenInfo = document.getElementById("productGenInfoDetailedForm:printproductGeneralInformation").value;
	var printForNotes = document.getElementById("productGenInfoDetailedForm:printproductNotes").value;

	if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('productComponentGeneralInfo');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;

		var proBenDivObj = document.getElementById('prodAssociatedBnfts');		
		proBenDivObj.style.visibility = "hidden";
        proBenDivObj.style.height = "0px";
		proBenDivObj.innerText = null;
	} 
	if( null == printForNotes || "" == printForNotes ){
		var proNoteDivObj = document.getElementById('productComponentNotes');
		proNoteDivObj.style.visibility = "hidden";
		proNoteDivObj.style.height = "0px";
		proNoteDivObj.innerText = null;

		var proBenDivObj = document.getElementById('prodAssociatedBnfts');		
		proBenDivObj.style.visibility = "hidden";
        proBenDivObj.style.height = "0px";
		proBenDivObj.innerText = null;
	} 

	initialize();
	function initialize(){
		if(null != document.getElementById('productGenInfoDetailedForm:noteDataTable')){
			if(document.getElementById('productGenInfoDetailedForm:noteDataTable').rows.length == 0){
				document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('searchResultdataTableDiv').style.height = '1px';
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationDiv').innerHTML = "No Notes Associated";
	        }else{
				document.getElementById('InformationDiv').style.visibility = 'hidden';
			}
		}if(null != document.getElementById('productGenInfoDetailedForm:associatedBenefitsDataTable')){
			if(document.getElementById('productGenInfoDetailedForm:associatedBenefitsDataTable').rows.length == 0){
				document.getElementById('benefitResultHeaderDiv').style.visibility = 'hidden';
				document.getElementById('benefitSearchResultdataTableDiv').style.height = '1px';
				document.getElementById('benefitSearchResultdataTableDiv').style.visibility = 'hidden';
				document.getElementById('InformationBenefitsDiv').innerHTML = "No Benefits Available";
			}
		}
	}

	if( "" != printForGenInfo){

		formatTildaToComma('productGenInfoDetailedForm:lineOfBusinessDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessEntityDiv');
		formatTildaToComma('productGenInfoDetailedForm:businessGroupDiv');
		formatTildaToComma('productGenInfoDetailedForm:marketBusinessUnitDiv');
		copyHiddenToInputText('productGenInfoDetailedForm:cmpTypeHidden','productGenInfoDetailedForm:cmpType',1);
		copyHiddenToInputText('productGenInfoDetailedForm:mandateType1Hidden','productGenInfoDetailedForm:mandateType',1);

		var i;
		var obj;
		obj = document.getElementById("productGenInfoDetailedForm:cmpTypeHidden");
		i= obj.value;
		var j;
		if(i=="MANDATE")
		{
		sub1.style.display='';
		j=document.getElementById("productGenInfoDetailedForm:mandateType").innerHTML;
				if(j == 'ST'){
					document.getElementById("productGenInfoDetailedForm:mandateType").value="State";
					}else if( j == 'ET'){
					document.getElementById("productGenInfoDetailedForm:mandateType").value="ET";
					}else if(j=='FED'){
					document.getElementById("productGenInfoDetailedForm:mandateType").value="Federal";
					}
		if(j== 'ET' || j=='ST')
		{
		sub2.style.display='';
		sub3.style.display='none';
		}else if(j== 'FED'){
		sub3.style.display='';
		sub2.style.display='none';
		}else{
		sub2.style.display='none';
		sub3.style.display='none';
		}
		}
		else 
		{
		sub1.style.display='none';
		}

		// Script for Notes tab hide for mandate
		var j;
		var obj1;
		obj1 = document.getElementById("productGenInfoDetailedForm:cmpTypeHidden");
		j= obj1.value;
		if(j== "MANDATE")
		{
			productComponentNotes.style.display='none';	
			prodAssociatedBnfts.style.display='none';	
		}else{
			productComponentNotes.style.display='';
			prodAssociatedBnfts.style.display='';	
		}
	}
		
</script>
</HTML>
