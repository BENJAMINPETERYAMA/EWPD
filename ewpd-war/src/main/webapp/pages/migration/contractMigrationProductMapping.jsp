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
<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
<script language="JavaScript" src="../../js/date.js" ></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
<BODY onkeypress="return submitOnEnterKey('wizardForm:nextMigrationLink');">
<table width="110%" border="0" cellspacing="0" cellpadding="0">
	<TR>
		<TD>
			<jsp:include page="../navigation/top_migration.jsp"></jsp:include>
		</TD>
	</TR>
	<TR>
		<TD>
			<h:form  styleClass="form"  id="wizardForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" >
					<TR>
                		<TD width="273" valign="top" class="leftPanel">							
							<DIV class="treeDiv"><!-- Space for Tree  Data	-->		
								<jsp:include page ="../migration/migrationWizardTree.jsp">  </jsp:include>					
						 	</DIV>
						</TD>
						<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<TR>										
											<TD>
											 <w:message value ="#{ContrMigratProductMappingBackingBean.validationMessages}"></w:message>
											</TD>
										</TR>		
									</TBODY>
								</TABLE>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
<%--
									<TR>
					          			<TD width="200">
		          							<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
		              							<TR>
				                					<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
													<TD width="186" class="tabActive"> <h:outputText value=" Step 7" /> </TD> 
				                					<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
		              							</TR>
		          							</TABLE>
		          						</TD>
									</TR>
--%>		
										<tr>
											<td width="100%"><b>Step7 : Benefit Mapping </b> </td>
										</tr>
										<tr>
											 <td>To create a mapping between the legacy structure and the ET-Auto Bagging Product.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

								</TABLE>
<!--								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
-->
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width= "100%">
									  <TBODY>
										<TR>
											<TD colspan="4">																									
												<TABLE border="0" cellspacing="5" cellpadding="3" width= "100%" >
													<tr class="dataTableOddRow">
														<td colspan="3" align="left" width= "50%">&nbsp;<strong>ETAB Data</strong></td>
														<td colspan="2" align="left">&nbsp;&nbsp;&nbsp;<strong>Adapted Data</strong></td>
													</tr>
													<tr>
														<td  width= "18%">
															<h:outputText value="Product"/></td>
														<td  width= "30%">
															<h:outputText  value="#{ContrMigratProductMappingBackingBean.productName}"/></td>
														<td align="center"  width="5%"><b>&lt;-&gt;</b></td>
														<td  width= "15%">
															<h:outputText value="Structure"/></td>
														<td  width= "32%">
														<h:outputText  value="#{ContrMigratProductMappingBackingBean.structureName}"/></td>									
													</tr>
													<tr>
														<td  width= "18%">
															<h:outputText value="Benefit Component"/></td>
														<td  width= "30%">
																<h:outputFormat value="#{ContrMigratProductMappingBackingBean.benefitCompName}"/></td>
														<td align="center"  width= "5%"><b>&lt;-&gt;</b></td>
														<td  width= "15%">
															<h:outputText value="Major Heading*"/></td>
														<td  width= "32%" >															
													<h:inputText id="majorHeadingTxtArea"
															 value="#{ContrMigratProductMappingBackingBean.majorHeading}"
															styleClass="formInputFieldForReference" >
															</h:inputText> 															
														<h:inputHidden id="majorHeadingHidden"
														value="#{ContrMigratProductMappingBackingBean.majorHeadinghidden}"></h:inputHidden> 
														<h:commandButton id="majorHeadingButton"
															alt="Select" image="../../images/select.gif"
															style="cursor: hand" onclick="majorHeadingSelected();return false;"
															 />
														<h:inputHidden id="majorHeadingIdHidden"
															value="#{ContrMigratProductMappingBackingBean.majorHeadingId}"></h:inputHidden></td>																							
													</tr>
													<tr>
														<td  >
															<h:outputText value="Standard Benefit" /></td>
														<td >
																<h:outputText value="#{ContrMigratProductMappingBackingBean.stdBenefitName}"/></td>
														<td align="center" ><b>&lt;-&gt;</b></td>
														<td>
															<h:outputText value="Minor Heading*"/></td>
														<td><h:inputText id="minorHeadingTxtArea"
															 value="#{ContrMigratProductMappingBackingBean.minorHeading}"
															styleClass="formInputFieldForReference">
															</h:inputText> <h:inputHidden id="minorHeadingHidden"
															value="#{ContrMigratProductMappingBackingBean.minorHeadinghidden}"></h:inputHidden>
														<h:commandButton id="minorHeadingButton"
															alt="Select" image="../../images/select.gif"
															style="cursor: hand" onclick="minorHeadingSelected();return false;"
															 />
															<h:inputHidden id="minorHeadingIdHidden"
															value="#{ContrMigratProductMappingBackingBean.minorHeadingId}"></h:inputHidden></td>
																							
													</tr>
									</TABLE>
						<DIV id="panel2Content" class="tabContentBox"
									style="position:relative;font-size:10px;width:100%;"><BR>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						
						</td>
					</tr>
					<tr>
						<TD>
						

						<div id="migrationwizardpanel" style="height:250px;">
							<h:panelGrid id="headingTable"
									binding="#{ContrMigratProductMappingBackingBean.headingPanel}" rowClasses="dataTableOddRow">
							</h:panelGrid>							
							<h:panelGroup id="wizardpanel" style="height:190px;width:100%;overflow:auto; " styleClass="dataTableColumnHeader">
								<h:panelGrid id="panelTable" 
									binding="#{ContrMigratProductMappingBackingBean.panel}"
									rowClasses="dataTableEvenRow,dataTableOddRow">
								</h:panelGrid>
							</h:panelGroup>
						</div>

						</TD>
					</tr>
					<tr>
					<td>
					<table  cellpadding="0" cellspacing="0" width="30%"
					border="0">
				<tr>
				<td width="23%">
					<h:commandButton value="Back"
							styleClass="wpdButton"
							onclick="goToPrevious();return false;" >
						</h:commandButton>
					
				</td>
				<h:commandLink id="previousMigrationLink"
					style="display:none; visibility: hidden;"
					action="#{ContrMigratProductMappingBackingBean.getPreviousPage}">
					<f:verbatim />
				</h:commandLink>
				<td width="22%">
						<h:commandButton value="Next"
							styleClass="wpdButton"
							onclick="goToNext();return false;" >
						</h:commandButton>
				</td>
				<h:commandLink id="nextMigrationLink"
						style="display:none; visibility: hidden;"
							action="#{ContrMigratProductMappingBackingBean.getNextPage}">
							<f:verbatim />
				</h:commandLink>	
				<td width="32%">
						<h:commandButton value="Cancel" 
							styleClass="wpdButton"
							onclick="cancelMapping();return false;" >
						</h:commandButton></td>
						<h:commandLink id="cancelMigrationLink"
							style="display:none; visibility: hidden;"
							action="#{migrationGeneralInfoBackingBean.cancelMigration}">
							<f:verbatim />
						</h:commandLink>
				<td width="23%">
					<h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
							styleClass="wpdButton"
							onclick="saveMapping();return false;" >
						</h:commandButton></td>
					<h:commandLink id="saveMigrationLink"
						style="display:none; visibility: hidden;"
							action="#{ContrMigratProductMappingBackingBean.saveMappingDetails}">
							<f:verbatim />
					</h:commandLink>
			</tr>
			<tr>
				<td colspan ="4"><h:inputHidden id="deleteRow" value="#{ContrMigratProductMappingBackingBean.hiddenBftLineId}"></h:inputHidden>
				<h:commandLink id="deleteMigrationLink"
				style="display:none; visibility: hidden;"
				action="#{ContrMigratProductMappingBackingBean.deleteMappingDetails}">
				<f:verbatim />
			</h:commandLink>
			</td>
			</tr><tr>
				<td colspan ="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan ="4"><h:commandButton value="Done"
							styleClass="wpdButton"
							onclick="doneMapping();return false;" >
						</h:commandButton>
						<h:commandLink id="doneButtonLink"
							style="display:none; visibility: hidden;"
							action="#{ContrMigratProductMappingBackingBean.done}">
							<f:verbatim />
					</h:commandLink>
					
				</td>
			</tr>
		</table>
					</td>
					</tr>



				</table>
			
			</DIV>
<h:inputHidden id="changedFlag" value="N"></h:inputHidden>
<h:inputHidden id="nextFlag" value="#{ContrMigratProductMappingBackingBean.changedFlag}"></h:inputHidden>
<h:inputHidden id="previousFlag" value="#{ContrMigratProductMappingBackingBean.preChangedFlag}"></h:inputHidden>
<h:inputHidden id="changedNotesFlag" value="N"></h:inputHidden>
<h:inputHidden id="listSize" value="#{ContrMigratProductMappingBackingBean.benefitLineListSize}"></h:inputHidden>
<h:inputHidden id="duplicateData" value="#{ContrMigratProductMappingBackingBean.duplicateData}"></h:inputHidden>

</TD>
</TR></TBODY>	
</TABLE>
<!--	End of Page data	-->
<!--</FIELDSET>-->
</td>
</tr>
</table>
</h:form>
</TD></TR>
<TR>
<td>	<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
</TR>
</TABLE>
<script>

// Method to make the input field as ReadOnly, when page loads
	document.getElementById('wizardForm:majorHeadingTxtArea').readOnly = true;
	document.getElementById('wizardForm:minorHeadingTxtArea').readOnly = true;
	var len =  document.getElementById('wizardForm:panelTable').rows.length;
	
	for (var i=0;i<len;i++) {
		document.getElementById('wizardForm:variable'+i).readOnly = true;
		document.getElementById('wizardForm:value'+i).readOnly = true;
		
		if(document.getElementById('wizardForm:hideFlag'+i).value =='Y'){
		
			document.getElementById('wizardForm:viewButton'+i).style.visibility='';
			document.getElementById('wizardForm:checkBox'+i).style.visibility='';
			document.getElementById('wizardForm:checkBox'+i).value = true;		

		}else{
			document.getElementById('wizardForm:viewButton'+i).style.visibility='hidden';
			document.getElementById('wizardForm:checkBox'+i).style.visibility='hidden';
			document.getElementById('wizardForm:checkBox'+i).value = false;			
		}
	}

IGNORED_FIELD1= 'wizardForm:duplicateData' ;
var tableObject = document.getElementById('wizardForm:panelTable');
	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('migrationwizardpanel');
	}
	else{
		var divObj = document.getElementById('migrationwizardpanel');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
	}

		if(document.getElementById('wizardForm:panelTable') != null) {
			document.getElementById('wizardForm:headingTable').width = "100%";
			var relTblWidth = document.getElementById('wizardForm:headingTable').offsetWidth;		
		
			if(document.getElementById('wizardForm:panelTable').offsetHeight <= 190) {
				if(document.getElementById('wizardForm:panelTable').rows.length < 3){				
					document.getElementById('wizardForm:panelTable').width = relTblWidth+"px";				
					setColumnWidth('wizardForm:panelTable','18%:5%:15%:9%:6%:25%:6%:8%');
					setColumnWidth('wizardForm:headingTable','18%:5%:15%:9%:6%:25%:6%:8%');
				}
				else{					
					document.getElementById('wizardForm:panelTable').width = (relTblWidth-17)+"px";
					document.getElementById('wizardForm:headingTable').width = (relTblWidth-17)+"px";
					setColumnWidth('wizardForm:panelTable','18%:5%:15%:9%:6%:25%:6%:8%');
					setColumnWidth('wizardForm:headingTable','18%:5%:15%:9%:6%:25%:6%:8%');
				}
			}else{
				document.getElementById('wizardForm:panelTable').width = (relTblWidth-17)+"px";
				document.getElementById('wizardForm:headingTable').width = (relTblWidth-17)+"px";
				setColumnWidth('wizardForm:panelTable','18%:5%:15%:9%:6%:25%:6%:8%');
				setColumnWidth('wizardForm:headingTable','18%:5%:15%:9%:6%:25%:6%:8%');
			}
		}


function cancelMapping(){
		var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
		if(window.confirm(message)){
			submitLink('wizardForm:cancelMigrationLink');
		}else{
				return false;
		}
}

function checkVariableNotes(i){
	if(document.getElementById('wizardForm:checkBox'+i).checked){		
		document.getElementById('wizardForm:checkBox'+i).value = "true";

	}else{
		document.getElementById('wizardForm:checkBox'+i).value = "false";
	}
}
function majorHeadingSelected(){
	document.getElementById('wizardForm:majorHeadinghidden').value = '';
	 var retvalue = ewpdModalWindow_ewpd('majorHeadingPopup.jsp'+getUrl()+'?'+'temp='+Math.random()+'&action='+'1','wizardForm:majorHeadingTxtArea','wizardForm:majorHeadinghidden',2,1);
     
	if(retvalue==false){			
			document.getElementById('wizardForm:majorHeadingIdHidden').value = '';	
    		return false;    
	}
	 var var1 = retvalue.split('~');
	 var majorid = var1[1];	
	document.getElementById('wizardForm:majorHeadingIdHidden').value = majorid;
	document.getElementById('wizardForm:majorHeadingHidden').value = var1[0];
	copyHiddenToInputText('wizardForm:majorHeadinghidden','wizardForm:majorHeadingTxtArea',1); 


}

function minorHeadingSelected(){
	
	document.getElementById('wizardForm:minorHeadingHidden').value = '';
	var retvalue = ewpdModalWindow_ewpd('minorHeadingPopup.jsp?'+'temp='+Math.random()+'&action='+'2'+'&majorHeadingId='+escape(document.getElementById('wizardForm:majorHeadingIdHidden').value),'wizardForm:minorHeadingTxtArea','wizardForm:minorHeadingHidden',2,1);

	if(retvalue==false){
		document.getElementById('wizardForm:minorHeadingIdHidden').value = minorid;
		return false;    
	}
	var var1 = retvalue.split('~');
	 var minorid = var1[1];	
	document.getElementById('wizardForm:minorHeadingIdHidden').value = minorid;	
	document.getElementById('wizardForm:minorHeadingHidden').value = var1[0];
	copyHiddenToInputText('wizardForm:minorHeadingHidden','wizardForm:minorHeadingTxtArea',1); 
}

function getVariableValue(i){
	
	var variableVal = document.getElementById('wizardForm:hiddenVariableDetails'+i).value;
	var provider = document.getElementById('wizardForm:hiddenProvider'+i).value;
	var format = document.getElementById('wizardForm:format'+i).value;
	var varValue = document.getElementById('wizardForm:value'+i).value;
	var variableId = document.getElementById('wizardForm:variableHidden'+i).value;
	var description = document.getElementById('wizardForm:hiddenDescription'+i).value;
	var reference = document.getElementById('wizardForm:hiddenReference'+i).value;
	var varNoteFlag ;	

	if(document.getElementById('wizardForm:checkBox'+i).style.visibility=='hidden'){
			varNoteFlag = 'N';
	}else{
			varNoteFlag = 'Y';
	}
	
	var retValue = window.showModalDialog('variablePopup.jsp'+getUrl()+'?'+'temp='+Math.random()+'&majorHeadingId='+escape(document.getElementById('wizardForm:majorHeadingIdHidden').value)+'&minorHeadingId='+ escape(document.getElementById('wizardForm:minorHeadingIdHidden').value)+'&provider='+ escape(provider)+'&format='+ escape(format)+'&description='+ escape(description)+'&reference='+ escape(reference)+'&variableId='+ escape(variableId) +'&variableDesc='+ escape(variableVal)  ,null, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	

	if(retValue == undefined || retValue == ""){
		if(document.getElementById('wizardForm:variableHidden'+i).value == undefined 
				|| document.getElementById('wizardForm:variableHidden'+i).value == ""){
			document.getElementById('wizardForm:changedFlag').value = 'N';
		}else{
			document.getElementById('wizardForm:changedFlag').value = 'Y';
		}

		document.getElementById('wizardForm:variable'+i).value = "";
		document.getElementById('wizardForm:variableHidden'+i).value = "";
		document.getElementById('wizardForm:value'+i).value = "";
		document.getElementById('wizardForm:hiddenValue'+i).value = "";	
		document.getElementById('wizardForm:hiddenVariableDetails'+i).value = "";

		document.getElementById('wizardForm:viewButton'+i).style.visibility='hidden';
		document.getElementById('wizardForm:checkBox'+i).style.visibility='hidden';
		document.getElementById('wizardForm:checkBox'+i).value = false;
		document.getElementById('wizardForm:hideFlag'+i).value = 'N';



		return;
	}
	
	var var2 = retValue.split('@');	
	var varid = var2[0];
	var value = var2[4];
	
	if(var2[0]== undefined){		
		var2[0] = "";	
	}
	if(var2[1]== undefined){		
		var2[1] = "";	
	}
	if(var2[2]== undefined){		
		var2[2] = "";	
	}
	if(var2[3]== undefined){		
		var2[3] = "";	
	}
	if(var2[5] != undefined){
		varNoteFlag = var2[5];

	}	
 	var retVal = var2[0]+'/'+var2[1]+'/'+var2[2]+'/'+var2[3];
	
	if(retVal=='///'){
			retVal ="";
	}
	
	if(retValue== undefined){		
		retVal= variableVal;	
	}	
	if(value== undefined){	
		
		if(retValue != ""){
			value = varValue;
		}else{
			value ="";
		}			
	}
	if(varid == undefined || varid == false){
		if(retValue != ""){
			varid = variableId;
		}else{
			varid = "";	
		}	
	}

	var flagstatus1 = 'Y';
	var flagstatus2 = 'Y';
	if((document.getElementById('wizardForm:variableHidden'+i).value=='')&&(varid=='')){
		flagstatus1 = 'N';
	}
	
	if((document.getElementById('wizardForm:variableHidden'+i).value== 'null')&&(varid=='')){
		flagstatus2 = 'N';
	}
	
		
	if((flagstatus1 == 'Y')&&(flagstatus2 == 'Y')){
		if(document.getElementById('wizardForm:variableHidden'+i).value!=varid){
		
			document.getElementById('wizardForm:changedFlag').value = 'Y';
		}
	}
	if(varNoteFlag =='Y'){
		
		document.getElementById('wizardForm:viewButton'+i).style.visibility='';
		document.getElementById('wizardForm:checkBox'+i).style.visibility='';
		document.getElementById('wizardForm:checkBox'+i).value = true;
		document.getElementById('wizardForm:hideFlag'+i).value = 'Y';

	}else{
		document.getElementById('wizardForm:viewButton'+i).style.visibility='hidden';
		document.getElementById('wizardForm:checkBox'+i).style.visibility='hidden';
		document.getElementById('wizardForm:checkBox'+i).value = false;
		document.getElementById('wizardForm:hideFlag'+i).value = 'N';
	}

	document.getElementById('wizardForm:variable'+i).value = retVal;	
	document.getElementById('wizardForm:hiddenVariableDetails'+i).value = retVal;
	document.getElementById('wizardForm:value'+i).value = value;
	document.getElementById('wizardForm:hiddenValue'+i).value = value;	
	document.getElementById('wizardForm:variableHidden'+i).value = varid;
	
	return false;
}

function getVariableNote(i){
		var checkboxValue;
		if(document.getElementById('wizardForm:checkBox'+i).checked){
			checkboxValue = 'Y';
		}else{
			checkboxValue = 'N';
		}
		var retValue = window.showModalDialog('variableNotePopup.jsp'+getUrl()+'?'+'temp='+Math.random()+'&variableIdForNotes='+escape(document.getElementById('wizardForm:variableHidden'+i).value)+'&variableDescForNotes='+escape(document.getElementById('wizardForm:variable'+i).value)+'&notesCheckboxFlag='+escape(checkboxValue),null, "dialogHeight:465px;dialogWidth:500px;resizable=false;status=no;");	

		if(retValue=='Y'){
			document.getElementById('wizardForm:checkBox'+i).checked= true;
			document.getElementById('wizardForm:checkBox'+i).value = true;
			document.getElementById('wizardForm:changedNotesFlag').value = 'Y';		
		}else{
			document.getElementById('wizardForm:checkBox'+i).checked= false;
			document.getElementById('wizardForm:checkBox'+i).value = false;
			document.getElementById('wizardForm:changedNotesFlag').value = 'N';		
		}

	}

	function checkVarNotes(i){
		if(document.getElementById('wizardForm:checkBox'+i).checked){		
			document.getElementById('wizardForm:checkBox'+i).value = true;

		}else{
			document.getElementById('wizardForm:checkBox'+i).value = false;
		}
	}

function saveMapping(){

		var message = "The mapping is going to affect the selected Date Segment(s) in this migration. Do you want to save?"	
		if((document.getElementById('wizardForm:changedFlag').value=='Y') || 
			(document.getElementById('wizardForm:changedNotesFlag').value = 'Y')){
			if(window.confirm(message)){
				submitLink('wizardForm:saveMigrationLink');
			}else{
					return false;
			}
		}
}
function deleteMapping(bftLineId){	
	var message = "Do you want to delete the mapped record from master table?"
		if(window.confirm(message)){
			document.getElementById('wizardForm:deleteRow').value = bftLineId;
			submitLink('wizardForm:deleteMigrationLink');
		}else{
				return false;
		}
}


//Initial array for the notes
var length =  document.getElementById('wizardForm:panelTable').rows.length;
var initArray  = new Array(length);
for (var i=0;i<length;i++) {
	initArray[i] = document.getElementById('wizardForm:checkBox'+i).checked;
}

function goToNext(){
	var finalArray  = new Array(length);
	for (var i=0;i<length;i++) {
		finalArray[i] = document.getElementById('wizardForm:checkBox'+i).checked;
		if(finalArray[i] != initArray[i]){
			document.getElementById('wizardForm:changedNotesFlag').value = 'Y';
			break;
		}
	}
	if(document.getElementById('wizardForm:changedFlag').value=='Y' ||  document.getElementById('wizardForm:changedNotesFlag').value == 'Y'){
		document.getElementById('wizardForm:nextFlag').value= 'Y';
		var message = "The unsaved mappings will be saved.The mapping is going to affect the selected Date Segment(s) in this migration.Do you want to proceed ?"
		if(window.confirm(message)){
			submitLink('wizardForm:nextMigrationLink');
		}else{
			return false;
		}
	}else{
		document.getElementById('wizardForm:nextFlag').value = 'N';
		submitLink('wizardForm:nextMigrationLink');
	}
}

function goToPrevious(){
	if(document.getElementById('wizardForm:changedFlag').value=='Y'){

		document.getElementById('wizardForm:previousFlag').value=document.getElementById('wizardForm:changedFlag').value;
	//	var message = "The unsaved mappings will be saved.The mapping is going to affect all Date Segments. Do you want to proceed ?"

		var message = "The unsaved mappings will be lost. Do you want to proceed ?"
		if(window.confirm(message)){
			submitLink('wizardForm:previousMigrationLink');
		}else{
			return false;
		}
	}else{
		document.getElementById('wizardForm:previousFlag').value = 'N';
		submitLink('wizardForm:previousMigrationLink');
	}
}
function doneMapping(){
	if(document.getElementById('wizardForm:changedFlag').value=='Y'){
	
	var message = "The unsaved mappings will be saved.The mapping is going to affect all date segments. Do you want to proceed ?"
		if(window.confirm(message)){
			submitLink('wizardForm:doneButtonLink');
		}else{
			return false;
		}
	
	}else{
		submitLink('wizardForm:doneButtonLink');
	}

}

</script>

</BODY>
</f:view>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('wizardForm:duplicateData').value == '')
		document.getElementById('wizardForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('wizardForm:duplicateData').value;
</script>
</html>
