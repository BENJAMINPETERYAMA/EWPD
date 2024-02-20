<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
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
	width: 10%;
	
}.shortDescriptionColumn {
	
}
.shortDescriptionColumn1 {
	
}
.longDescriptionColumn {
	
}
.longDescriptionColumn1 {
	
}
</style>

<TITLE>Migration Wizard-Step 2</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden id="dummy"></h:inputHidden> <jsp:include
					page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="migrationform">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv">
<!-- Space for Tree  Data	-->			<jsp:include page ="../migration/migrationWizardTree.jsp">  </jsp:include>			
</DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{legacyContractBackingBean.validationMessages}"></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
<%--
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Migration Wizard" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="100%"></TD>
								</TR>
--%>		
									<tr>
										<TD>
										<div id='validationMessage' style="display:none; " class='errorDiv' /><li id=errorItem>Please select
										atleast one Date Segment </li></div>
										</TD>
									</tr>
										<tr>
											<td width="100%"><b>Step 2 : Date Segment </b> </td>
										</tr>
										<tr>
											 <td>Display all the Date Segments present in the legacy system for this particular contract.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

							</TABLE>
							<!-- End of Tab table -->

<!--							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
-->
							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" " cellpadding="3" id="heading"
										border="0" width="100%" bgcolor="#cccccc">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" bgcolor="#cccccc"><h:outputText
													value="Contract Date Segments"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</td>
								</tr>

								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" cellpadding="3" id="subHeading"
										bgcolor="#cccccc" border="0" width="100%">
										<TBODY>

											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value=""></h:outputText></TD>
												<TD align="left"><h:outputText value="Contract ID"></h:outputText></TD>
												<TD align="left"><h:outputText value="Start Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="End Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Step Completed"></h:outputText></TD>

											</TR>
										</TBODY>
									</TABLE>
									</div>
									</td>
								</tr>

								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:%;">
									<h:dataTable
										headerClass="dataTableHeader" id="dataTable" var="singleValue"
										cellpadding="5" cellspacing="1" bgcolor="#cccccc"
										rendered="#{legacyContractBackingBean.cp2000ContractList != null}"
										value="#{legacyContractBackingBean.cp2000ContractList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,shortDescriptionColumn1,longDescriptionColumn,longDescriptionColumn1" border="0"
										width="100%">

										<h:column>
											
											<f:verbatim>
												<wpd:singleRowSelect 
												groupName="qualifier" 
												id="minor1"
												rendered="true" 
												/>
											</f:verbatim>
										</h:column>

										<h:column>
											<h:inputHidden id="datesegmentID" 	value="#{singleValue.systemId}"/>											
											<h:inputHidden id="ContractID1" 	value="#{singleValue.contractSysId}"/>
											<h:inputHidden id="startDateHidden"
												value="#{singleValue.startDateString}"></h:inputHidden>
											<h:outputText id="ContractID"		value="#{legacyContractBackingBean.contractId}"/>
										</h:column>

										<h:column>
											<h:outputText id="StartDate" value="#{singleValue.effectiveDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
										</h:column>

										<h:column>
											<h:outputText id="EndDate" value="#{singleValue.expiryDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
										</h:column>

										<h:column>
											<h:outputText id="Status" value="step#{singleValue.stepCompleted}"></h:outputText>
										</h:column>

									</h:dataTable>
								</DIV>
								<BR>
								</td>

								</TR>
								<TR>
									<TD width="189"><SPAN style="margin-left:0px;margin-right:0px;">
									<h:commandButton value="Back" styleClass="wpdButton"
										id="nextButton" action="#{legacyContractBackingBean.goToStep1}"
										disabled="#{legacyContractBackingBean.anyDSCompletedStep8}"></h:commandButton></SPAN> 
									<LABEL>
									<h:commandButton value="Next" styleClass="wpdButton"
										 id="backButton"
										onclick="onSelectCall();return false;"
										action="#{legacyContractBackingBean.goToStep3}"/>
									</LABEL>
									</TD>
								</TR>
							</TABLE>
<!--							</FIELDSET>-->
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							<BR>
							</TD>
						</TR>
					</TABLE>
					<!-- Space for hidden fields -->

					<!-- End of hidden fields  -->
		<h:inputHidden id="selectedDS" value="#{legacyContractBackingBean.selectedDS}"></h:inputHidden>
		<h:inputHidden id="selectedStartDate" value="#{legacyContractBackingBean.selectedDateSegmentFromStep2}"/>
		<h:inputHidden id="pageInfo" value="#{legacyContractBackingBean.page}"></h:inputHidden>
		<h:inputHidden id="pageBreadcrumb" value="#{legacyContractBackingBean.pageBreadcrumb}"></h:inputHidden>
		<h:commandLink id="nextMigrationLink"
				style="display:none; visibility: hidden;"
				action="#{legacyContractBackingBean.goToStep3}">
				<f:verbatim />
			</h:commandLink>

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>

	<SCRIPT>
//default first option is selected 
document.getElementById('migrationform:dataTable').rows[0].cells[0].children[0].checked = true ;
document.getElementById('migrationform:pageInfo').value = 'singlePage';
document.getElementById('validationMessage').style.visibility ='hidden';
document.getElementById('validationMessage').style.display ='none';


function onSelectCall(){
	var chkbox;
	var tableObject = document.getElementById('migrationform:dataTable');
	var count = tableObject.rows.length;
	var chkcount = 0;
	for(var z=count-1; z>=0; z--) {
		chkbox = tableObject.rows[z].cells[0].children[0];
		if (!chkbox.checked) {
			chkcount++;
		}
	}
	if(chkcount == count){
		document.getElementById('validationMessage').style.visibility ='visible';
		document.getElementById('validationMessage').style.display ='inline';
		return false;
	}
	var selectVal = getCheckedItems_ewpd_local('migrationform:dataTable',2);
	document.getElementById('migrationform:selectedStartDate').value = selectVal;
	freezeScreen();
	var tableObject = document.getElementById('migrationform:dataTable');
	var dynamicinfocode = '~';
	for(var i=0; i<tableObject.rows.length; i++) {
		var cur_row = tableObject.rows[i];
		if(cur_row.cells[0].children[0].checked){
			dynamicinfocode += cur_row.cells[1].children[2].value;
			dynamicinfocode += '~';
		}		
	}
	document.getElementById('migrationform:selectedDS').value = dynamicinfocode;
	document.getElementById('migrationform:nextMigrationLink').click();
}

function getCheckedItems_ewpd_local(id, attrCount)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';

				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}
//	window.returnValue = selectedValues;
//	window.close();	
	if(cnt > 0)
		return selectedValues;
	return false;
}
	setColumnWidth('heading','25%:25%:25%:25%');
	setColumnWidth('subheading','6%:18%:21%:21%;34%');
	setColumnWidth('migrationform:dataTable','6%:18%:21%:21%;34%');
		</SCRIPT>
		<%@include file="../template/freeze.html" %>
	</BODY>
</f:view>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>


