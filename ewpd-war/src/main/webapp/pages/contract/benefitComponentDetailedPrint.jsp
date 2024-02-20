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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {

}
</style>

<TITLE>Contract Benefit Component Print</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
			<tr><td>&nbsp; </td></tr>
		<TR>
					<TD>  <FIELDSET style="margin-left:6px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:90%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
		<tr>
			<td><h:form styleClass="form" id="contractBenefitComponentFormPrint">
				<br />

				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:90%">
				<div id="panel2Header" class="tabTitleBar"
					style="position:relative;width:100% ">General Information</div>
				<h:inputHidden id="loadForPrint"
					value="#{contractComponentGeneralInfoBackingBean.valueForPrint}"></h:inputHidden>
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
							<TABLE border="0" cellspacing="0" cellpadding="3">
								<tr>


									<TD width="130"><h:outputText value="Line of Business" /></TD>
									<TD width="194"><h:outputText id="lobHidden"
										value="#{contractComponentGeneralInfoBackingBean.lineOfBusiness}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Entity" /></TD>
									<TD width="194"><h:outputText id="entityHidden"
										value="#{contractComponentGeneralInfoBackingBean.businessEntity}">
									</h:outputText></TD>
								</TR>
								<TR>
									<TD width="130"><h:outputText value="Business Group" /></TD>
									<TD width="194"><h:outputText id="groupHidden"
										value="#{contractComponentGeneralInfoBackingBean.businessGroup}">
									</h:outputText></TD>

								</TR>
								<TR>
									<TD width="130"><h:outputText value="Market Business Unit" /></TD>
									<TD width="194"><h:outputText id="marketBusinessUnitHidden"
										value="#{contractComponentGeneralInfoBackingBean.marketBusinessUnit}">
									</h:outputText></TD>

								</TR>
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Name" /></TD>
							<TD width="239"><h:outputText id="name"
								value="#{contractComponentGeneralInfoBackingBean.name}" /></TD>
						</TR>
						

						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Component Type" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.componentType}"
								id="compType" /></TD>
						</TR>
						
                        <TR>
							<TD width="3"></TD>
							<TD width="135" valign="top"><h:outputText value="Description" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.description}"></h:outputText>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Benefit Rule ID" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.ruleId}"
								id="ruleId" /></TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Effective Date" /></TD>
							<TD width="239"><h:outputText
								value="#{contractComponentGeneralInfoBackingBean.effectiveDate}"
								id="effDate" /></TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Expiry Date" /></TD>
							<TD width="239"><h:outputText id="expiryDate"
								value="#{contractComponentGeneralInfoBackingBean.expiryDate}" />
							</TD>
						</TR>
						<TR>
									<TD width="3"></TD>
									<TD width="135"><h:outputText value="Version" /></TD>
									<TD width="239"><h:outputText id="versionId"
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}" />
									<h:inputHidden
										value="#{contractComponentGeneralInfoBackingBean.benefitComponentVersion}"></h:inputHidden>
									</TD>
						</TR>
						
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Created By" /></TD>
							<TD width="239"><h:outputText id="createdUser"
								value="#{contractComponentGeneralInfoBackingBean.createdBy}" />
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Created Date" /></TD>
							<TD width="239"><h:outputText id="creationDate"
								value="#{contractComponentGeneralInfoBackingBean.createdDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Last Updated By" /></TD>
							<TD width="239"><h:outputText id="lastUpdatedUser"
								value="#{contractComponentGeneralInfoBackingBean.updatedBy}" />

							</TD>
						</TR>
						<TR>
							<TD width="3"></TD>
							<TD width="135"><h:outputText value="Last Updated Date" /></TD>
							<TD width="239"><h:outputText id="lastUpdatedDate"
								value="#{contractComponentGeneralInfoBackingBean.lastUpdatedDate}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
							</TD>
						</TR>
					</TBODY>
				</TABLE>







				<!--	Start of Table for actual Data	--> <!--	End of Page data	--></fieldset></TD>
		</TR>
	</table>

	
	<BR>
<!--	Start of BC Benefits	-->


							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:90%">
							<h:inputHidden id="listGenerator"
								value="#{contractComponentGeneralInfoBackingBean.list}"></h:inputHidden>
								
									
							<table width="100%"  align="right" cellpadding="0" cellspacing="0" >
							<TBODY>
								<TR><td>
									<DIV id="noContractBenefits"
									     style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No Benefits Attached.
									</DIV>
								</td></TR>
								<TR>
									<TD>
									<DIV id="headerBenefits">
										<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc" id="resultHeaderTable">
											<TR>			
																				
												<TD width="100%" > <strong> <h:outputText value="Benefits"> </h:outputText> </strong> </td>
											</tr>
										</table>
									</DIV>	
									</TD>
								</TR>
								<tr>
									<td>
									<DIV class="popupDataTableDiv" id="popupDataTableDiv">
										<h:dataTable   cellspacing="1" rowClasses="dataTableEvenRow,dataTableOddRow"
										columnClasses="selectOrOptionColumn"
											width="100%" id="contractTypeDataTable" 
											value="#{contractComponentGeneralInfoBackingBean.benefitList}"
											rendered = "#{contractComponentGeneralInfoBackingBean.benefitList != null}"
											var="singleValue" cellpadding="0">
										 	
										 	<h:column>
				                                <f:verbatim>&nbsp;</f:verbatim>
										 		<h:inputHidden value="#{singleValue.standardBenefitDesc}"></h:inputHidden>
												<h:outputText value="#{singleValue.standardBenefitDesc}"></h:outputText>
											</h:column>
										</h:dataTable>
									</DIV>	
									</td>
								</tr>
							</TBODY>
							</table>						
							</FIELDSET>


<!--	End of BC Benefits	-->
<!--	Start of BC Notes	-->
	<h:inputHidden id="loadPrint"
		value="#{contractBenefitComponentNotesBackingBean.loadForPrint}"></h:inputHidden>
	<br />
	<br />
<div id="benefitComponentNote">
	<FIELDSET
		style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:90%">
	<DIV id="noContractNote"
		style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No
	Notes Attached.</DIV>
	<TABLE width="100%" cellspacing="0" cellpadding="0">

		<TR>
			<TD>
			<DIV id="resultHeaderDiv">
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="headerTable" border="0" width="100%">
				<TR>
					<TD><b> <h:outputText value="Associated Notes"></h:outputText> </b></TD>
				</TR>
			</TABLE>
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="notesResultHeaderTable" border="0" width="100%">
				<TBODY>
					<TR class="dataTableColumnHeader">
						<TD align="left"><h:outputText value=" Note ID "></h:outputText></TD>
						<TD><h:outputText value=" Note Name "></h:outputText></TD>
					</TR>
				</TBODY>
			</TABLE>
			</DIV>
			</TD>
		</TR>
		<TR>
			<TD>
			<DIV id="searchResultdataTableDiv"
				style="height:50px;position:relative;z-index:1;font-size:10px;overflow:auto;">
			<!-- Search Result Data Table --> <h:dataTable
				styleClass="outputText" headerClass="dataTableHeader"
				id="searchResultTable" var="singleValue" cellpadding="3"
				width="100%" cellspacing="1" 
				rendered="#{contractBenefitComponentNotesBackingBean.noteList != null}"
				value="#{contractBenefitComponentNotesBackingBean.noteList}"
				border="0">

				<h:column>
					<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
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
	</FIELDSET>
</div><br />
	<!-- Space for hidden fields -->
	<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
	<h:commandLink id="hiddenLnk1"
		style="display:none; visibility: hidden;">
		<f:verbatim />
	</h:commandLink>
	<!-- End of hidden fields  -->

	</h:form>


	</BODY>
</f:view>

<script>

		formatTildaToComma('contractBenefitComponentFormPrint:lobHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:entityHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:groupHidden');
		formatTildaToComma('contractBenefitComponentFormPrint:marketBusinessUnitHidden');

hideIfNoValue('resultHeaderDiv');
hideIfNoBenefits('popupDataTableDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('contractBenefitComponentFormPrint:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractNote').style.visibility = 'hidden';
			setColumnWidth('contractBenefitComponentFormPrint:searchResultTable', '20%:80%');
			setColumnWidth('notesResultHeaderTable', '20%:80%');

		}
	}
					 	
	i = document.getElementById("contractBenefitComponentFormPrint:hiddenProductType").value;
	if(i=='MANDATE')
	{
	noContractNote.style.visibility = 'hidden';
	resultHeaderDiv.style.visibility = 'hidden';
	benefitComponentNote.style.visibility = 'hidden';
	}

	
	function hideIfNoBenefits(divId){
		hiddenIdObj = document.getElementById('contractBenefitComponentFormPrint:contractTypeDataTable');
		if(hiddenIdObj == null){

			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractBenefits').style.visibility = 'visible';
			document.getElementById('headerBenefits').style.visibility = 'hidden';

		}else{

			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractBenefits').style.visibility = 'hidden';
			document.getElementById('headerBenefits').style.visibility = 'visible';
			setColumnWidth('contractBenefitComponentFormPrint:contractTypeDataTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}

</script>


<script>window.print();</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComponentGeneralInfo" /></form>
</HTML>
