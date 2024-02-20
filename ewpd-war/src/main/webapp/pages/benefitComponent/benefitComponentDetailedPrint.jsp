<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/StandardBenefitDetailPrint.java" --%><%-- /jsf:pagecode --%>
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
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 20%;
}
.shortDescriptionColumn {
	width: 80%;
}
</style>

<TITLE>Print Benefit Component</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="benefitComponentDetailedPrintForm">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="#{benefitComponentBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<h:inputHidden id="viewBenefitComponentKey"
					value="#{benefitComponentBackingBean.viewBenefitComponentKey}" />
				<TD>
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
					<TBODY>
						<br />
						<TR>
							<TD>
							<div id="panel3Header" style="position:relative;width:100% "><STRONG>General
							Information</STRONG></div>
							<br />
							</TD>
						</TR>
						<TR>
							<TD colspan=3>
							<FIELDSET style=""><LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
							<TABLE>

								<TR>
									<TD width="140"><h:outputText id="lobLabel"
										value="Line of Business " /></TD>
									<TD width="214"><h:outputText id="txtLob"
										value="#{benefitComponentBackingBean.lob}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText id="businessEntityLabel"
										value="Business Entity " /></TD>
									<TD width="214"><h:outputText id="txtBusinessEntity"
										value="#{benefitComponentBackingBean.businessEntity}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="140"><h:outputText id="businessGroupLabel"
										value="Business Group " /></TD>
									<TD width="214"><h:outputText id="txtBusinessGroup"
										value="#{benefitComponentBackingBean.businessGroup}"></h:outputText>
									</TD>
								</TR>
<!-- ---------------------------------------------------------------------------------- -->
								<TR>
									<TD width="140"><h:outputText
										value="Market Business Unit " /></TD>
									<TD width="214"><h:outputText id="txtMarketBusinessUnit"
										value="#{benefitComponentBackingBean.marketBusinessUnit}"></h:outputText>
									</TD>
								</TR>
<!-- ----------------------------------------------------------------------------------- -->
							</TABLE>
							</FIELDSET>
							</TD>
						</TR>
						<TR>
							<TD height="27">&nbsp;<h:outputText id="name_label"
								value="Name" /></TD>
							<TD height="27" width="280"><h:outputText id="name_txt"
								value="#{benefitComponentBackingBean.name}" /></TD>
						</TR>
						<TR>
							<TD>&nbsp;<h:outputText value="Component Type" /></TD>
							<TD width="280"><h:outputText id="componentId"
								value="#{benefitComponentBackingBean.componentType}"></h:outputText>
							<h:inputHidden id="CorpPlanCd_list1"
								value="#{benefitComponentBackingBean.componentType}"></h:inputHidden></TD>
						</TR>

						<TR id="sub1" style="display:none;">
							<TD>&nbsp;<h:outputText value="Mandate Type" /></TD>
							<TD width="280"><h:outputText id="mandateId"
								value="#{benefitComponentBackingBean.mandateType}"></h:outputText>
							<h:inputHidden id="Mandate_type_list1"
								value="#{benefitComponentBackingBean.mandateType}"></h:inputHidden></TD>

						</TR>

						<TR id="sub2" style="display:none;">
							<TD>&nbsp;<h:outputText value="State" /></TD>
							<TD width="280">
							<DIV id="StateDiv"></DIV>
							<h:inputHidden id="txtState"
								value="#{benefitComponentBackingBean.selectedStateId}"></h:inputHidden></TD>
						</TR>
						<TR id="sub3" style="display:none;">
							<TD>&nbsp;<h:outputText id="stateCde2" value="State" />
							</TD>
							<TD width="280"><h:outputText id="FederalLabel" value="ALL"></h:outputText>
							</TD>
						</TR>
						<TR>
							<TD height="27" valign="top">&nbsp;<h:outputText
								id="description_label" value="Description" /></TD>
							<TD height="27" width="280"><h:outputText
								id="description_txtarea" styleClass="formTxtAreaReadOnly" style="border:none;width:250px;"
								value="#{benefitComponentBackingBean.description}" /></TD>
						</TR>

						<TR>
							<TD>&nbsp;<h:outputText id="ruleEditStar"
								value="Benefit Rule ID " /></TD>
							<TD width="280"><h:outputText id="txtRule"
								value="#{benefitComponentBackingBean.ruleId}"></h:outputText></TD>
						</TR>
						
                        <TR>
							<TD height="27">&nbsp;<h:outputText
								id="effectiveDate_label" value="Effective Date" /></TD>
							<TD height="27" width="280"><h:outputText id="effectiveDate_txt"
								value="#{benefitComponentBackingBean.effectiveDate}" /></TD>
						</TR>
						<TR>
							<TD height="27">&nbsp;<h:outputText
								id="expiryDate_label" value="Expiry Date" /></TD>
							<TD height="27" width="280"><h:outputText id="expiryDate_txt"
								value="#{benefitComponentBackingBean.expiryDate}" /></TD>
						</TR>
						<TR>
							<TD height="27">&nbsp;<h:outputText
								id="createdBy_label" value="Created By" /></TD>
							<TD height="27" width="280"><h:outputText id="createdBy_txt"
								value="#{benefitComponentBackingBean.createdUser}" /></TD>
						</TR>
						<TR>
							<TD height="27" valign="top">&nbsp;<h:outputText
								id="createDate_label" value="Created Date" /></TD>
							<TD height="27" width="280"><h:outputText id="createDate_txt"
								value="#{benefitComponentBackingBean.createdTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}" /></TD>
						</TR>
						<TR>
							<TD height="27">&nbsp;<h:outputText
								id="updatedBy_label" value="Last Updated By" /></TD>
							<TD height="27" width="280"><h:outputText id="updatedBy_txt"
								value="#{benefitComponentBackingBean.lastUpdatedUser}" /></TD>
						</TR>
						<TR>
							<TD height="27">&nbsp;<h:outputText
								id="updationDate_label" value="Last Updated Date" /></TD>
							<TD height="27" width="280"><h:outputText id="updationDate_txt"
								value="#{benefitComponentBackingBean.lastUpdatedTimestamp}">
								<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
							</h:outputText> <h:outputText value="#{requestScope.timezone}" /></TD>
						</TR>
						

					</TBODY>
				</TABLE>
				<!--	End of Page data	--></fieldset>
			
							<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="4%"></td>
									<td align="left" width="71%"></td>
									<td align="left" width="25%">
								<table Width=100%>
									<tr>
										<td width="2%"><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentBackingBean.state}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentBackingBean.status}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						<BR>
						<BR>
				<!-- Hidden Fields --> <h:inputHidden id="txtname"
					value="#{benefitComponentBackingBean.name}"></h:inputHidden> <h:inputHidden
					id="txtdescription"
					value="#{benefitComponentBackingBean.description}"></h:inputHidden>
				<h:inputHidden id="txtcreateduser"
					value="#{benefitComponentBackingBean.createdUser}"></h:inputHidden>
				<h:inputHidden id="txtcreatedTimestamp"
					value="#{benefitComponentBackingBean.createdTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden> <h:inputHidden id="txtlastUpdatedUser"
					value="#{benefitComponentBackingBean.lastUpdatedUser}"></h:inputHidden>
				<h:inputHidden id="txtlastUpdatedTimestamp"
					value="#{benefitComponentBackingBean.lastUpdatedTimestamp}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden> <h:inputHidden id="hiddenTabValue"
					value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>

				</TD>
			</TR>
			<TR>
				<TD>
				<fieldset
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">

				<!--	Start of Table for actual Data	--> <BR />
				<div id="emptymsg"><h:outputText
									value="No benefits Available."
									styleClass="dataTableColumnHeader" /></div>

				<TABLE width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td>

						<div id="resultHeaderDiv" style="width:100%;">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<tr>
								<TD><h:outputText value="Associated Benefits"
									style="font-weight: bold"></h:outputText></TD>
							</tr>
						</TABLE>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<TR class="dataTableColumnHeader">
									<td align="left" width="25%"><b><h:outputText value="Sequence"></h:outputText></b>
									</td>
									<td align="left" width="75%"><b><h:outputText
										value="Benefit"></h:outputText></b></td>

								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>

					<TR valign="top">
						<TD width="100%">
						<div id="searchResultdataTableDiv"><h:dataTable
							headerClass="dataTableHeader" id="searchResultTable"
							var="singleValue" cellpadding="3" cellspacing="1" columnClasses="selectOrOptionColumn,shortDescriptionColumn"
							value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchies}"
							border="0"
							width="100%">
							<h:column>
								<h:inputHidden id="benefitIdHidden" value="#{singleValue.sequenceNumber}"></h:inputHidden>
								<h:outputText id="benefitId"
									value="#{singleValue.sequenceNumber}"></h:outputText>
								</h:column>
							<h:column>
								<h:outputText id="benefitName"
									value="#{singleValue.benefitName}"></h:outputText>
								<!-- closing Column is added  -->
							</h:column>
						</h:dataTable></div>

						</TD>
					</TR>

				</TABLE>
				</fieldset>
				</TD>
			</TR>
			<!--  benefitComponentNotes -->
			<TR>
				<TD id="bcNotesTab">
				<br/><br/>
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:99%">

				<!--	Start of Table for actual Data	--> <br/>
				<div id="nobenComponentNote"><h:outputText
								value="No Notes Available."
								styleClass="dataTableColumnHeader" /></div>
				<TABLE width="100%"  cellpadding="3" cellspacing="1" border="0">

					<tbody>
						
						<TR>
							<TD>
							<div id="resultHeaderDiv1">
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="headerTable" border="0" width="100%">
									<TR>
										<TD><b> <h:outputText value="Associated Notes"></h:outputText>
										</b></TD>
									</TR>
							</TABLE>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
								id="resultHeaderTable" border="0" width="100%">
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
							<DIV id="searchResultdataTableDiv"
								style="overflow: auto;">
							<h:dataTable
								rendered="#{BenefitComponentNotesBackingBean.associatedNotesList!= null}"
								styleClass="outputText" headerClass="dataTableHeader"
								id="NotesTable" var="singleValue" cellpadding="1" width="100%"
								cellspacing="1"
								value="#{BenefitComponentNotesBackingBean.associatedNotesList}">

								<h:column>
									<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
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
			<!-- End of benefitComponentNotes -->
			<!--	End of Page data	-->


		</TABLE>

	</h:form>
	</BODY>
</f:view>
<script>


// Enhancement
	hideNotesTab();
	function hideNotesTab(){
	var tab = document.getElementById('benefitComponentDetailedPrintForm:hiddenTabValue').value;	
	// alert('tab:'+ tab);
	if(tab == "Standard Definition")
		bcNotesTab.style.display = '';
	else
		bcNotesTab.style.display = 'none';
	}		

var i;
var obj;
obj = document.getElementById("benefitComponentDetailedPrintForm:CorpPlanCd_list1");

i= obj.value;
	
	if(i=="MANDATE")
	{
	sub1.style.display='';
	}
	else 
	{
	sub1.style.display='none';
	sub2.style.display='none';
	}
var i;
var obj;
obj = document.getElementById("benefitComponentDetailedPrintForm:Mandate_type_list1");
i= obj.value;
		
		if(i== "ExtraTerritorial" || i=="State")
		{
		//	alert('et or state');
		sub2.style.display='';	
		sub3.style.display='none';			
		}
		else if(i == "Federal")
		{
		// Federal
         // alert('federal');
			sub2.style.display='none';
			sub3.style.display='';
		}else{
			sub2.style.display='none';
			sub3.style.display='none';
		}

	formatTildaToComma('benefitComponentDetailedPrintForm:txtLob');
	formatTildaToComma('benefitComponentDetailedPrintForm:txtBusinessEntity');
	formatTildaToComma('benefitComponentDetailedPrintForm:txtBusinessGroup');
	formatTildaToComma('benefitComponentDetailedPrintForm:txtMarketBusinessUnit');

	formatTildaToComma1("benefitComponentDetailedPrintForm:txtRule");

	formatTildaToComma("benefitComponentDetailedPrintForm:txtState");
	copyHiddenToDiv_ewpd('benefitComponentDetailedPrintForm:txtState','StateDiv',2,2);  
	copyHiddenToInputText('benefitComponentDetailedPrintForm:CorpPlanCd_list1','benefitComponentDetailedPrintForm:componentId',1);
	copyHiddenToInputText('benefitComponentDetailedPrintForm:Mandate_type_list1','benefitComponentDetailedPrintForm:mandateId',1);

	hideIfNoValue('resultHeaderDiv1');
	hideIfNoValue('resultHeaderDiv');

	function hideIfNoValue(divId){
		if(divId == 'resultHeaderDiv1'){
			hiddenIdObj = document.getElementById('benefitComponentDetailedPrintForm:NotesTable:0:noteId');
			if(hiddenIdObj == null){
				document.getElementById(divId).style.visibility = 'hidden';
				document.getElementById('nobenComponentNote').style.visibility = 'visible';
			}else{
				document.getElementById(divId).style.visibility = 'visible';
				document.getElementById('nobenComponentNote').style.visibility = 'hidden';
				setColumnWidth('benefitComponentDetailedPrintForm:NotesTable', '40%:60%');
				setColumnWidth('resultHeaderTable', '40%:60%');
	
			}
		}else if(divId == 'resultHeaderDiv'){
			hiddenIdObj = document.getElementById('benefitComponentDetailedPrintForm:searchResultTable:0:benefitIdHidden');
			if(hiddenIdObj == null){
				document.getElementById(divId).style.visibility = 'hidden';
				document.getElementById('emptymsg').style.visibility = 'visible';
			}else{
				document.getElementById(divId).style.visibility = 'visible';
				document.getElementById('emptymsg').style.visibility = 'hidden';
				setColumnWidth('benefitComponentDetailedPrintForm:searchResultTable', '25%:75%');
				setColumnWidth('resultHeaderTable', '40%:60%');
	
			}
		}
	}
		
</script>
<script>
	window.print();	
	
</script>

</HTML>




