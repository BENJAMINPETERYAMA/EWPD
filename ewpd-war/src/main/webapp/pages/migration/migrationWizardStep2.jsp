<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
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

<TITLE>Migration Wizard-Step 1</TITLE>
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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<!-- <script language="JavaScript" src="../../js/wpd.js"></script> -->
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>

<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>

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
							<DIV class="treeDiv"><!-- Space for Tree  Data	--></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message /></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">

								<tr>
									<TD>
							<div id='errorMessage1' style="display:none; " class='errorDiv' /><li id=errorItem>
							Invalid date </li></div><br />
									</TD>
								</tr>
								<tr>
									<TD>
									<div id='validationMessage' style="display:none; " class='errorDiv' /><li id=errorItem>Please select
									atleast one Date Segment</li></div>
									</TD>
								</tr>
									
								<tr>
									<TD>
									<div id='errorMessage2' style="display:none; " class='errorDiv' /><li id=errorItem>Please enter a valid 4 digit year between 1900 and 9999</li></div>
									</TD>
								</tr>
								<tr>
									<TD>
									<div id='errorMessage3' style="display:none; " class='errorDiv' /><li id=errorItem>The date format should be : mm/dd/yyyy</li></div>
									</TD>
								</tr>

								<tr>
									<td width="100%"><b> Step 2 : Date Segment </b></td>
								</tr>
								
								<tr></tr>
								<tr>
									<td>Select Date Segments for Contract ID <br />
									Display all the Date Segments present in the legacy system for
									this particular contract.</td>
								</tr>
							</TABLE>

							<TABLE border="0" cellspacing="0" cellpadding="3">
								<tr>
									<TD valign="top" width="227">&nbsp;&nbsp; <t:selectOneRadio id="migrRadio"
										layout="pageDirection" onclick="enableFromDate();">
										<f:selectItem itemValue="Y" itemLabel="Manual" />
										<f:selectItem itemValue="N" itemLabel="From Date" />
									</t:selectOneRadio></td>
									
									<TD>
									<br/>
									<br/>
									<br/>
								
									<h:inputText styleClass="formInputField"
										id="contractFromDate_txt" maxlength="10" tabindex="21"
										value="#{legacyContractBackingBean.fromDate}"
										style="width:70px" size="5" /> 
										
									</TD>
									<TD>
								<br/>
									<br/>
									<br/>
								
									<A href="#"	onclick="cal1.select('migrationform:contractFromDate_txt','anchor2','MM/dd/yyyy'); return false;"
										name="anchor2" id="anchor2"
										title="cal1.select('migrationform:contractFromDate_txt','anchor2','MM/dd/yyyy'); return false;"
										tabindex="22"> <h:commandButton image="../../images/cal.gif" 
										style="cursor: hand" id="calender" alt="Cal" />
										</A>
										
									</td>
								
									

								</tr>
								<tr><td></td>
										<td valign="top">	
									<h:outputText id="dateF" value="(mm/dd/yyyy)" styleClass="dateFormat"></h:outputText>
									
									</TD></tr>
								<tr></tr>
								<TR>
									<TD width="175"><SPAN style="margin-left:0px;margin-right:0px;">&nbsp;&nbsp;<h:commandButton
										value="Update" styleClass="wpdButton" id="updateButton"
										onclick="update(); return false;"></h:commandButton></SPAN></TD>
								</TR>

								<tr></tr>
								<tr></tr>
							</TABLE>
							<BR>
							<!-- <FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">  -->


							<TABLE cellpadding="0" cellspacing="0" width="98.5%" border="0"
								align="right">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" cellpadding="3" id="heading" border="0"
										width="100%" bgcolor="#cccccc">
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
												<TD align="center" valign="middle"><h:selectBooleanCheckbox
													value="" id="MainCheckBoxID"
													onclick="checkAll_ewpd(this,'migrationform:dataTable');">
												</h:selectBooleanCheckbox></TD>
												<TD align="left"><h:outputText value="Contract ID"></h:outputText></TD>
												<TD align="left"><h:outputText value="Start Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="End Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>

											</TR>
										</TBODY>
									</TABLE>
									</div>
									</td>
								</tr>

								<TR>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:%;"><h:dataTable
										headerClass="dataTableHeader" id="dataTable" var="singleValue"
										cellpadding="5" cellspacing="1" bgcolor="#cccccc"
										rendered="#{legacyContractBackingBean.cp2000ContractList != null}"
										value="#{legacyContractBackingBean.cp2000ContractList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<h:selectBooleanCheckbox id="CheckBoxID"
												rendered="#{singleValue.contractStat == 'Transferred to Production'}"
												onclick="getCheckBoxID(this);">
											</h:selectBooleanCheckbox>
										</h:column>
										<h:column>
											<h:outputText id="ContractID"
												value="#{singleValue.contractId}"></h:outputText>
											<h:inputHidden id="ContractID1"
												value="#{singleValue.contractId}"></h:inputHidden>
											<h:inputHidden id="startDateHidden"
												value="#{singleValue.startDateString}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="StartDate"
												value="#{singleValue.startDateString}">
												<f:convertDateTime pattern="MM-dd-yyyy" />
											</h:outputText>
											<h:inputHidden id="StartDate1"
												value="#{singleValue.startDateString}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="EndDate"
												value="#{singleValue.endDateString}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="EndDate1"
												value="#{singleValue.endDateString}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="Status1" value="#{singleValue.contractStat}" ></h:outputText>
											<h:outputText id="Status2" rendered="#{singleValue.rowStatus == 3}" value="(Renewed in ETAB)" ></h:outputText>
											<h:inputHidden id="Status3"
												value="#{singleValue.contractStat}"></h:inputHidden>

										</h:column>
									</h:dataTable></DIV>
									<BR>
									</td>
								</TR>
								<TR>
									<TD width="189"><SPAN style="margin-left:0px;margin-right:0px;">
									<h:commandButton value="Back" styleClass="wpdButton"
										id="backButton"
										action="#{legacyContractBackingBean.goToStep1}"></h:commandButton></SPAN>
									<LABEL> <h:commandButton value="Next" styleClass="wpdButton"
										id="nextButton"
										action="#{legacyContractBackingBean.goToStep3}"
										onclick="return navigatePage();"></h:commandButton></LABEL></TD>
								</TR>
							</TABLE>
								<h:inputHidden id="selectedDS"
												value="#{legacyContractBackingBean.selectedDS}"></h:inputHidden>
								<h:inputHidden id="multiplePage"
												value="#{legacyContractBackingBean.page}"></h:inputHidden>
							<!-- </FIELDSET>--> <BR>
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
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<SCRIPT>
	setColumnWidth('heading','25%:25%:25%:25%');
	setColumnWidth('subheading','5%:18%:21%:21%;34%');
	setColumnWidth('migrationform:dataTable','5%:18%:21%:21%;34%');
	var options = document.getElementsByName('migrationform:migrRadio');
	options[1].checked ='true'; 
	document.getElementById('validationMessage').style.visibility ='hidden';
	document.getElementById('errorMessage1').style.visibility ='hidden';
	document.getElementById('errorMessage1').style.display ='none';
	document.getElementById('errorMessage2').style.visibility ='hidden';
	document.getElementById('errorMessage3').style.visibility ='hidden';
	document.getElementById('errorMessage2').style.display ='none';	
	document.getElementById('errorMessage3').style.display ='none';
	document.getElementById('validationMessage').style.display ='none';


	disableFromDate();
	function disableFromDate(){
   		if(options[1].checked){
			document.getElementById('migrationform:contractFromDate_txt').style.visibility ='hidden';
			document.getElementById('migrationform:calender').style.visibility ='hidden';
			document.getElementById('migrationform:dateF').style.visibility ='hidden';
		}
	}


	
	function getFirstStartDate(){
		var tableSam = document.getElementById('migrationform:dataTable');
		var latestStartDate ;
		var cnt ;
		var l;
		cnt = tableSam.rows.length;
		for(var j=cnt-1; j>=0; j--) {
				l = tableSam.rows[j].cells[4].children[1].value;
				if(!('Migrated'== l )){
					latestStartDate = tableSam.rows[j].cells[2].children[1].value;
				}
		document.getElementById('migrationform:contractFromDate_txt').value = latestStartDate;
		}
	}
		
	function checkAll_ewpd(controller, table) 
	{	document.getElementById('validationMessage').style.visibility ='hidden';
		var tableObject = document.getElementById(table);
		var chkbox;
			
		for(var i=0; i<tableObject.rows.length; i++) {
			if(!('Migrated' == tableObject.rows[i].cells[4].children[1].value)){
				chkbox = tableObject.rows[i].cells[0].children[0];
				if (!(chkbox.disabled == true)) {
					chkbox.checked = controller.checked;
				}
			}	
		}
		
	}


	function getCheckBoxID(val)
	{
		var chkbox;
		var newCheckLimit;
		var tableObject = document.getElementById('migrationform:dataTable');
		var count = tableObject.rows.length;
		
		if(val.checked){
			for(var j=count-1; j>=0; j--) {
				if(!('Migrated' == tableObject.rows[j].cells[4].children[1].value)){
						chkbox = tableObject.rows[j].cells[0].children[0];
						if( val.id == chkbox.id){
							newCheckLimit = chkbox.id.match(j);
						}
					}
			}
			for(var i=newCheckLimit; i>=0; i--) {
				if(!('Migrated' == tableObject.rows[i].cells[4].children[1].value)){
					chkbox = tableObject.rows[i].cells[0].children[0];
					if (!(chkbox.disabled == true)) {
						chkbox.checked = 'true';
					}
				}	
			}
		}
		if(!val.checked){
			for(var j=count-1; j>=0; j--) {
				if(!('Migrated' == tableObject.rows[j].cells[4].children[1].value)){
					chkbox = tableObject.rows[j].cells[0].children[0];
					if( val.id == chkbox.id){
						newCheckLimit = chkbox.id.match(j);
					}
				}
			}
			for(var z=count-1; z>=newCheckLimit; z--) {
				if(!('Migrated' == tableObject.rows[z].cells[4].children[1].value)){
					chkbox = tableObject.rows[z].cells[0].children[0];
					if (!chkbox.disabled) {
						chkbox.checked = val.checked;
					}
				}
			}
		}
	}

	function update(){
		document.getElementById('errorMessage1').style.visibility ='hidden';
		document.getElementById('errorMessage2').style.visibility ='hidden';
		document.getElementById('errorMessage3').style.visibility ='hidden';
		document.getElementById('validationMessage').style.visibility ='hidden';
		document.getElementById('errorMessage1').style.display ='none';
		document.getElementById('errorMessage2').style.display ='none';	
		document.getElementById('errorMessage3').style.display ='none';
		document.getElementById('validationMessage').style.display ='none';

		if(options[2].checked){
			var no = checkForValidDate('migrationform:contractFromDate_txt');
			if(no == 1){
				document.getElementById('errorMessage1').style.visibility ='visible';
				document.getElementById('errorMessage1').style.display ='inline';
				return;
			}else if(no == 2){
				document.getElementById('errorMessage2').style.visibility ='visible';
				document.getElementById('errorMessage2').style.display ='inline';
				return;
			}else if(no == 4){
				document.getElementById('errorMessage3').style.visibility ='visible';
				document.getElementById('errorMessage3').style.display ='inline';
				return;
			}
		}
		var chkbox;
		var newCheckLimit;
		var tableObject = document.getElementById('migrationform:dataTable');
		var count = tableObject.rows.length;
		var compnent;
		var val= document.getElementById('migrationform:contractFromDate_txt').value;
		document.getElementById('validationMessage').style.visibility ='hidden';
			
		if(options[1].checked){
			document.getElementById('migrationform:MainCheckBoxID').disabled = false;
			for(var j=count-1; j>=0; j--) {
				if(!('Migrated' == tableObject.rows[j].cells[4].children[1].value)){
						chkbox = tableObject.rows[j].cells[0].children[0];
						document.getElementById(chkbox.id).disabled = false;
					}
			}
		}
		if(options[2].checked){
			for(var i=0;i<count;i++){
				if(!('Migrated' == tableObject.rows[i].cells[4].children[1].value)){
							chkbox = tableObject.rows[i].cells[0].children[0];
							document.getElementById(chkbox.id).checked = false;
						}
				}



			document.getElementById('migrationform:MainCheckBoxID').disabled = true;
			for(var j=count-1; j>=0; j--) {
				if(!('Migrated' == tableObject.rows[j].cells[4].children[1].value)){
						chkbox = tableObject.rows[j].cells[0].children[0];
						document.getElementById(chkbox.id).disabled = true;
				}
			}
			
			var lastStartDate;
			lastStartDate = tableObject.rows[count-1].cells[2].children[1].value;
			lastStartDateObj = new Date(lastStartDate);
			var applicationStartDate = '01/01/1900';
			applicationStartDateobj = new Date(applicationStartDate);
			valObj = new Date(val);
			if((valObj<=lastStartDateObj)){
					if((valObj>=applicationStartDateobj)){
							for(var i=count-1; i>=0; i--) {
								if(!('Migrated' == tableObject.rows[i].cells[4].children[1].value)){
								chkbox = tableObject.rows[i].cells[0].children[0];
								chkbox.checked = 'true';
							}	
					}
				}
			}
			
			for(var j=count-1; j>=0; j--) {
				var comparingStartDate = tableObject.rows[j].cells[2].children[1].value;
				var comparingEndDate = tableObject.rows[j].cells[3].children[1].value;
				userStartdate = new Date(comparingStartDate);
				userEnddate = new Date(comparingEndDate);
				userValdate = new Date(val);
				if((val == comparingStartDate)||((userValdate > userStartdate)&&(userValdate < userEnddate ))){
						if(!('Migrated' == tableObject.rows[j].cells[4].children[1].value)){
						chkbox = tableObject.rows[j].cells[0].children[0];
						newCheckLimit = chkbox.id.match(j);

						break;
					}
				}
			}
		for(var i=newCheckLimit; i>=0; i--) {
				if(!('Migrated' == tableObject.rows[i].cells[4].children[1].value)){
					chkbox = tableObject.rows[i].cells[0].children[0];
					chkbox.checked = 'true';
				}	
			}
			
		}
	}
	function enableFromDate(){
			disableFromDate();
			if(options[2].checked){
				document.getElementById('migrationform:contractFromDate_txt').style.visibility ='visible';
				document.getElementById('migrationform:calender').style.visibility ='visible';
				document.getElementById('migrationform:dateF').style.visibility ='visible';
				getFirstStartDate();
			}
	}

function navigatePage(){
	var chkbox;
	var tableObject = document.getElementById('migrationform:dataTable');
	var count = tableObject.rows.length;
	var chkcount = 0;
	var dynamicinfocode= '~';
			for(var z=count-1; z>=0; z--) {
				if(!('Migrated' == tableObject.rows[z].cells[4].children[1].value)){
					chkbox = tableObject.rows[z].cells[0].children[0];
					if (!chkbox.checked) {
						chkcount++;
					}
				}else{
					chkcount++;
				}
			}
	if(chkcount == count){
		document.getElementById('validationMessage').style.visibility ='visible';
		document.getElementById('validationMessage').style.display ='inline';
		return false;
	}
	var message = 'Are you sure you want to migrate all the selected Date Segments?';
	if(window.confirm(message)){
		var tableObject = document.getElementById('migrationform:dataTable');
		for(var i=0; i<tableObject.rows.length; i++) {
			var cur_row = tableObject.rows[i];
			if(null != cur_row.cells[0].children[0]){
					if(cur_row.cells[0].children[0].checked){
						dynamicinfocode += cur_row.cells[1].children[2].value;
						dynamicinfocode += '~';
					}
			}		
		}
		document.getElementById('migrationform:selectedDS').value = dynamicinfocode;
		document.getElementById('migrationform:multiplePage').value = 'multiplePage';
		return true;
	}else
		return false;
	}
	</SCRIPT>
<%@include file="../template/freeze.html"%>


</HTML>

