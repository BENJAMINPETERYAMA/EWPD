<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<title>Migration Wizard</title>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>

</HEAD>
<f:view>
	<BODY onkeypress="" onunload="copyFromInputTextToHidden();">


	<TABLE width="110%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_migration.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="migrateNotesForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD width="150px" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../migration/migrationWizardTree.jsp">
						</jsp:include></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TR>
								<TD><w:message
									value="#{migrateNotesBackingBean.validationMessages}"></w:message>
								</TD>
							</TR>
						</TABLE>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">

							<tr>
								<td width="100%"><b>Step8 : Migrate Notes </b></td>
							</tr>
							<tr>
								<td>This screen will display the contract level notes,major
								heading notes and minor heading notes.</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>

						</table>
						<!-- End of Tab table -->

						<table align="left" cellpadding="0" cellspacing="0" width="100%"
							border="0">
							<tr>
								<td></td>
							</tr>
							<tr>
								<TD>


								<TABLE width="80%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="0" id="resultHeaderTable" border="0">
									<TBODY>

										<TR class="dataTableOddRow">
											<td align="left" width="30%">&nbsp;<b><h:outputText
												styleClass="formOutputColumnField" value="Contract"></h:outputText>
											</b></td>
											<td align="center" width="30%"><h:outputText
												styleClass="formOutputColumnField"></h:outputText></td>
											<td align="center" width="10%"><h:outputText
												styleClass="formOutputColumnField"></h:outputText></td>
											<td align="left" width="20%">&nbsp;<b><h:outputText
												styleClass="formOutputColumnField" value="Notes"></h:outputText></b>
											</td>

										</TR>
										<TR class="dataTableEvenRow">
											<td align="left" width="30%">&nbsp;<h:outputText
												styleClass="formOutputColumnField"
												value="#{migrateNotesBackingBean.contractName}"></h:outputText>
											</td>
											<td align="center" width="30%"><h:outputText
												styleClass="formOutputColumnField"></h:outputText></td>
											<td align="center" width="10%"><h:outputText
												styleClass="formOutputColumnField"></h:outputText></td>
											<td align="left" width="20%">&nbsp;<h:commandButton
												id="notesViewButton" alt="View"
												image="../../images/view.gif" style="cursor: hand"
												onclick="viewContractNotes();return false;" /> &nbsp; <h:selectBooleanCheckbox
												id="notesSelectButton" value=""
												onclick="checkContractNotes();" /> <h:inputHidden
												id="notesSelectButtonHidden"
												value="#{migrateNotesBackingBean.contractNotesFlag}">
											</h:inputHidden></td>

										</TR>
									</TBODY>
								</TABLE>

								<div id="benefitComponentPanel" style="height:30px;"><br>

								<TABLE width="80%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="0" id="resultHeaderTable" border="0">
									<TBODY>

										<TR class="dataTableOddRow">
											<td align="left" width="30%">&nbsp;<b><h:outputText
												styleClass="formOutputColumnField" value="Benefit Component"></h:outputText>
											</b></td>
											<td align="left" width="30%">&nbsp;<b><h:outputText
												styleClass="formOutputColumnField" value="Major Heading Id"></h:outputText></b>
											</td>
											<td align="left" width="10%"><h:outputText
												styleClass="formOutputColumnField"></h:outputText></td>
											<td align="left" width="20%">&nbsp;<b><h:outputText
												styleClass="formOutputColumnField" value="Notes"></h:outputText></b>
											</td>

										</TR>
										<TR class="dataTableEvenRow">
											<td align="left" width="30%">&nbsp;<h:outputText
												styleClass="formOutputColumnField"
												value="#{migrateNotesBackingBean.benefitCompName}"></h:outputText>
											</td>
											<td align="left" width="30%">&nbsp;<h:inputText
												id="majorHeadingId" readonly="true"
												styleClass="formInputFieldForReference"
												value="#{migrateNotesBackingBean.majorheadingId}"></h:inputText>
											<h:inputHidden id="hiddenMajorHeadingId"
												value="#{migrateNotesBackingBean.majorheadingId}"></h:inputHidden>

											</td>
											<td align="left" width="10%">&nbsp;<h:commandButton
												id="majorheadingSelect" alt="Select"
												image="../../images/select.gif" style="cursor: hand"
												onclick="getMajorHeadingSelected();return false;" /> &nbsp;</td>
											<td align="left" width="20%">&nbsp;<h:commandButton
												id="componentNotesViewButton" alt="View"
												image="../../images/view.gif" style="cursor: hand"
												onclick="viewMajorHeadingNotes();return false;" /> &nbsp; <h:selectBooleanCheckbox
												id="componentNotesSelectButton" value=""
												onclick="checkNotes();" /></td>
											<h:inputHidden id="componentNotesSelectButtonHidden"
												value="#{migrateNotesBackingBean.benefitComponentNotesFlag}">
											</h:inputHidden>
										</TR>
									</TBODY>
								</TABLE>

								</div>



								<div id="benefitPanel" style="height:200px;"><br>
								<h:panelGrid id="benefitHeadingTable"
									binding="#{migrateNotesBackingBean.benefitHeadingPanel}"
									rowClasses="dataTableOddRow">
								</h:panelGrid> <h:panelGroup id="benefitPanel"
									style="height:150px;width:80%;overflow:auto; "
									styleClass="dataTableColumnHeader">
									<h:panelGrid id="panelTable2"
										binding="#{migrateNotesBackingBean.benefitPanel}"
										rowClasses="dataTableEvenRow,dataTableOddRow">
									</h:panelGrid>
								</h:panelGroup></div>


								</TD>
							</tr>
							<tr>
								<td>
								<table cellpadding="0" cellspacing="0" width="30%" border="0">
									<tr>
										<td width="15%"><h:commandButton value="Back"
											styleClass="wpdButton" onclick="goToPrevious();return false;">
										</h:commandButton></td>
										<h:commandLink id="previousMigrationLink"
											style="display:none; visibility: hidden;"
											action="#{migrateNotesBackingBean.getPreviousPage}">
											<f:verbatim />
										</h:commandLink>
										<td width="15%"><h:commandButton value="Next"
											styleClass="wpdButton" onclick="goToNext();return false;">
										</h:commandButton></td>
										<h:commandLink id="nextMigrationLink"
											style="display:none; visibility: hidden;"
											action="#{migrateNotesBackingBean.getNextPage}">
											<f:verbatim />
										</h:commandLink>

										<td width="15%"><h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
											styleClass="wpdButton" onclick="saveMapping();return false;">
										</h:commandButton></td>
										<h:commandLink id="saveMigrationLink"
											style="display:none; visibility: hidden;"
											action="#{migrateNotesBackingBean.saveNotesMigrationInfo}">
											<f:verbatim />
										</h:commandLink>
										<td width="15%"><h:commandButton value="Cancel"
											styleClass="wpdButton"
											onclick="cancelMapping();return false;">
										</h:commandButton></td>
										<h:commandLink id="cancelMigrationLink"
											style="display:none; visibility: hidden;"
											action="#{migrationGeneralInfoBackingBean.cancelMigration}">
											<f:verbatim />
										</h:commandLink>
									</tr>
									<tr>
										<td width="30%" colspan="4"><br>
										<h:commandButton value="Done" styleClass="wpdButton"
											onclick="doneMapping();return false;">
										</h:commandButton></td>
										<h:commandLink id="doneButtonLink"
											style="display:none; visibility: hidden;"
											action="#{migrateNotesBackingBean.done}">
											<f:verbatim />
										</h:commandLink>
										<h:inputHidden id="hiddenMajorHdng"
											value="#{legacyContractBackingBean.majorHeadingHidden}"></h:inputHidden>
										<h:inputHidden id="hiddenMinorHdng"
											value="#{legacyContractBackingBean.minorHeadingHidden}"></h:inputHidden>
										<h:inputHidden id="duplicateData" value="#{migrateNotesBackingBean.duplicateData}"></h:inputHidden>

									</tr>
								</table>
								</td>
							</tr>


						</TABLE>


						</td>
					</tr>

				</TABLE>
			</h:form></TD>
		</TR>
		<TR>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</TR>
	</TABLE>
	</body>
</f:view>

<script language="JavaScript">
IGNORED_FIELD1= 'migrateNotesForm:duplicateData' ;
for(var i = 0;i < document.getElementById('migrateNotesForm:size').value;i++){
	document.getElementById('migrateNotesForm:minorHeading'+i).value = document.getElementById('migrateNotesForm:inputTextHidden'+i).value;
	//document.getElementById('migrateNotesForm:noteCheckBox'+i).value = document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value;
}
function copyFromInputTextToHidden(){
	for(var i = 0;i < document.getElementById('migrateNotesForm:size').value;i++){
		document.getElementById('migrateNotesForm:inputTextHidden'+i).value = document.getElementById('migrateNotesForm:minorHeading'+i).value;
		//document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = document.getElementById('migrateNotesForm:noteCheckBox'+i).value;
	}
}

	if(document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value == 'Y'){
		document.getElementById('migrateNotesForm:componentNotesSelectButton').checked = true;
	}else{

		document.getElementById('migrateNotesForm:componentNotesSelectButton').checked= false;
	}
	if(document.getElementById('migrateNotesForm:notesSelectButtonHidden').value =='Y'){
		document.getElementById('migrateNotesForm:notesSelectButton').checked = true;
	}else{
		document.getElementById('migrateNotesForm:notesSelectButton').checked= false;
	}

	function checkNotes(){
		if(document.getElementById('migrateNotesForm:componentNotesSelectButton').checked){		
			document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value = 'Y';
		}else{
			document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value = 'N';
		}
	}
	function checkContractNotes(){
		if(document.getElementById('migrateNotesForm:notesSelectButton').checked){		
			document.getElementById('migrateNotesForm:notesSelectButtonHidden').value = 'Y';
		}else{
			document.getElementById('migrateNotesForm:notesSelectButtonHidden').value = 'N';
		}
	}
	var paneltablelength = document.getElementById('migrateNotesForm:panelTable2').rows.length;
	for (var i=0; i<paneltablelength ; i++){
		if(document.getElementById('migrateNotesForm:noteCheckBox'+i).checked){		
			document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "true";

		}else{
			document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "false";
		}
	}
	function checkMinorHeadingNotes(i){
		if(document.getElementById('migrateNotesForm:noteCheckBox'+i).checked){		
			document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "true";

		}else{
			document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "false";
		}
	}
	
	function goToNext(){
		submitLink('migrateNotesForm:nextMigrationLink');
	}

	function goToPrevious(){

	 navigatePageActionSubmit('migrateNotesForm:previousMigrationLink');
	
	}

	function doneMapping(){
		submitLink('migrateNotesForm:doneButtonLink');
	
	}
	function saveMapping(){
		submitLink('migrateNotesForm:saveMigrationLink');
	}	
	function cancelMapping(){
			var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
			if(window.confirm(message)){
				submitLink('migrateNotesForm:cancelMigrationLink');
			}else{
				return false;
			}
	}
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');
		var divNoResult = document.getElementById('panel2');
		var tableObj = document.getElementById('migrateNotesForm:resultsTable');
		if (tableObj != null) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
				
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
	}

		if(document.getElementById('migrateNotesForm:panelTable2') != null) {
			document.getElementById('migrateNotesForm:benefitHeadingTable').width = "80%";
			var relTblWidth = document.getElementById('migrateNotesForm:benefitHeadingTable').offsetWidth;		
		
			if(document.getElementById('migrateNotesForm:panelTable2').offsetHeight <= 190) {
				if(document.getElementById('migrateNotesForm:panelTable2').rows.length < 3){				
					document.getElementById('migrateNotesForm:panelTable2').width = relTblWidth+"px";				
					setColumnWidth('migrateNotesForm:panelTable2','24%:24%:9%:15%');
					setColumnWidth('migrateNotesForm:benefitHeadingTable','24%:24%:9%:15%');
				}
				else{					
					document.getElementById('migrateNotesForm:panelTable2').width = (relTblWidth-17)+"px";
					document.getElementById('migrateNotesForm:benefitHeadingTable').width = (relTblWidth-17)+"px";
					setColumnWidth('migrateNotesForm:panelTable2','24%:24%:9%:15%');
					setColumnWidth('migrateNotesForm:benefitHeadingTable','24%:24%:9%:15%');
				}
			}else{
				document.getElementById('migrateNotesForm:panelTable2').width = (relTblWidth-17)+"px";
				document.getElementById('migrateNotesForm:benefitHeadingTable').width = (relTblWidth-17)+"px";
				setColumnWidth('migrateNotesForm:panelTable2','24%:24%:9%:15%');
				setColumnWidth('migrateNotesForm:benefitHeadingTable','24%:24%:9%:15%');
			}
		}

		

function viewContractNotes(){
		
		var retValue = window.showModalDialog('legacyContractNotes.jsp'+getUrl()+'?'+'temp='+Math.random()+'&notesSelectButtonHidden='+escape(document.getElementById('migrateNotesForm:notesSelectButtonHidden').value),null, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	
		if(retValue=='Y'){
			document.getElementById('migrateNotesForm:notesSelectButton').checked= true;
			document.getElementById('migrateNotesForm:notesSelectButtonHidden').value = 'Y';

			
		}else{
			document.getElementById('migrateNotesForm:notesSelectButton').checked= false;
			document.getElementById('migrateNotesForm:notesSelectButtonHidden').value = 'N';
		}

	}

function getMinorHeading(i){
	var returnvalue=window.showModalDialog('legacyMinorHeadingPopup.jsp'+getUrl()+'?'+'temp='+Math.random()+'&minorHeadingColumn='+i+'&minorHeadingId='+escape(document.getElementById('migrateNotesForm:minorHeading'+i).value),window,"dialogHeight:465px;dialogWidth:500px;resizable=true;status=no;");
	if(returnvalue != undefined){
		if(returnvalue != ''){
			if(document.getElementById('migrateNotesForm:noteCheckBox'+i).checked){
				document.getElementById('migrateNotesForm:noteCheckBox'+i).checked = true;
				document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "Y";
			}
			document.getElementById('migrateNotesForm:minorHeading'+i).value= returnvalue;
			document.getElementById('migrateNotesForm:inputTextHidden'+i).value= returnvalue;
		}else{
			document.getElementById('migrateNotesForm:minorHeading'+i).value= "";
			document.getElementById('migrateNotesForm:inputTextHidden'+i).value= "";
			document.getElementById('migrateNotesForm:noteCheckBox'+i).checked = false;
			document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "N";
		}	
	}else{
		document.getElementById('migrateNotesForm:minorHeading'+i).value= "";
		document.getElementById('migrateNotesForm:inputTextHidden'+i).value= "";
		document.getElementById('migrateNotesForm:noteCheckBox'+i).checked = false;
		document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = "N";
	}
	return false;

}
function getMajorHeadingSelected(){
	
	
	var returnvalue = ewpdModalWindow_ewpd('legacyMajorHeadingPopup.jsp'+getUrl()+'?'+'temp='+Math.random(),'migrateNotesForm:majorHeadingId','migrateNotesForm:hiddenMajorHeadingId',2,1);
	if(returnvalue != false){		
		document.getElementById('migrateNotesForm:majorHeadingId').value= returnvalue;
		document.getElementById('migrateNotesForm:hiddenMajorHdng').value= returnvalue;
		document.getElementById('migrateNotesForm:hiddenMajorHeadingId').value= returnvalue;
	}else{
		document.getElementById('migrateNotesForm:majorHeadingId').value= "";
		document.getElementById('migrateNotesForm:hiddenMajorHdng').value= "";
		document.getElementById('migrateNotesForm:hiddenMajorHeadingId').value= "";
	}
	document.getElementById('migrateNotesForm:componentNotesSelectButton').checked = false;
	document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value = "";
	return false;

}

	
function viewMajorHeadingNotes(){

		var retValue = window.showModalDialog('legacyMajorNotes.jsp'+getUrl()+'?'+'temp='+Math.random()+'&majorHeadingId='+escape(document.getElementById('migrateNotesForm:hiddenMajorHdng').value)+'&componentNotesSelectButtonHidden='+escape(document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value),null, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	
		if(retValue=='Y'){
			document.getElementById('migrateNotesForm:componentNotesSelectButton').checked= true;
			document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value = 'Y';
		
		}else{
			document.getElementById('migrateNotesForm:componentNotesSelectButton').checked= false;
			document.getElementById('migrateNotesForm:componentNotesSelectButtonHidden').value = 'N';
		}
	

	}
function getMinorNotes(i){
		var checkboxValue ;
		if(document.getElementById('migrateNotesForm:noteCheckBox'+i).checked){
			checkboxValue = 'Y';
		}else{
			checkboxValue = 'N';
		}
		var retValue = window.showModalDialog('legacyMinorNotes.jsp'+getUrl()+'?'+'temp='+Math.random()+'&minorHeadingId='+escape(document.getElementById('migrateNotesForm:inputTextHidden'+i).value)+'&noteCheckBoxHidden='+escape(checkboxValue),null, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	
	if(retValue=='Y'){
		document.getElementById('migrateNotesForm:noteCheckBox'+i).checked= true;
		document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = true;
	}else{
		document.getElementById('migrateNotesForm:noteCheckBox'+i).checked= false;
		document.getElementById('migrateNotesForm:noteCheckBoxHidden'+i).value = false;
	}
}




</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractMigrationReportGeneration" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('migrateNotesForm:duplicateData').value == '')
        document.getElementById('migrateNotesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
        document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('migrateNotesForm:duplicateData').value;
</script>
</HTML>

