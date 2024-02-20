<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>ContractNotes</TITLE>
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
<script>
function processDone(){
	document.getElementById('contractNotesForm:invokeWebService').value = '';
	document.getElementById('contractNotesForm:button2').click();
} 
function onPageLoadPopup(){
 	var ebxFlag = document.getElementById('contractNotesForm:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('contractNotesForm:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('contractNotesForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractNotesForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');		
     }else if(vendorFlag == "true" && ebxFlag == "false"){     	
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('contractNotesForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractNotesForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('contractNotesForm:hidd_webServiceFlag').value = "false";
		document.getElementById('contractNotesForm:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }

}  

</script>
</HEAD>
<f:view>
<BODY>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				
				<h:form styleClass="form" id="contractNotesForm">
				<h:inputHidden id="hidd_webServiceFlag" value="#{ContractNotesBackingBean.webServiceFlag}"/>
					<h:inputHidden id="hidd_VendorFlag" value="#{ContractNotesBackingBean.vendorFlag}"/>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->				<jsp:include page="../contract/contractTree.jsp"></jsp:include>		
								</DIV>
						 		

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr id ="ErrorRow">
											<TD>
<!-- Space for message tag -->		<w:message value="#{ContractNotesBackingBean.validationMessages}"></w:message>										
											</TD>
										</tr>	
									<TR  id="NotesErrorRow" style="display:none;">
										<TD><div class='errorDiv' /><li id=errorItem>There are notes attached to un-coded benefitlines/unanswered questions.&nbsp;&nbsp;<img id='link_Notes' src='../../images/select.gif' alt='Select' 
											onclick= "callUncodedNotesNotesPopUp();return false;" style='cursor: hand'  /></li></div></TD>
									</TR>		
									</TBODY>
								</TABLE>
								<h:inputHidden id="hidden1" value="#{ContractNotesBackingBean.loadNote}"></h:inputHidden>
<!-- Table containing Tabs -->
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<TR>
									   <td width="18%">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="3" align="left"><img
													src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
													width="3" height="21" /></td>
												<td class="tabNormal"><h:commandLink action="#{contractBasicInfoBackingBean.getBasicInfo}" id="link"
												onmousedown="javascript:navigatePageAction(this.id);">
													<h:outputText id="GeneralInformation" value=" General Information"></h:outputText>
												</h:commandLink></td>
												<td width="2" align="right"><img
													src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
													width="4" height="21" /></td>
											</tr>
										</table>
										</td>
										<TD width="18%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}" id="priceId" onmousedown="javascript:navigatePageAction(this.id);"><h:outputText id="pricingInfoTabTable"  value="Pricing Information"/> </h:commandLink> 
													
													</TD>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</TR>
											</TABLE>
										</TD>
										<TD width="14%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/activeTabLeft.gif" alt="Tab Left Active" width="3" height="21" /></td>
													<td class="tabActive"> 
														<h:commandLink action="#{ContractNotesBackingBean.load}" id="noteId" onmousedown="javascript:navigatePageAction(this.id);"> <h:outputText value="Notes"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/activeTabRight.gif" alt="Tab Right Active" width="4" height="21" /></td>
												</tr>
											</TABLE>
										</TD>
										<TD width="16%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
												<TR>
													<TD width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment"
														onmousedown="javascript:navigatePageAction(this.id);"><h:outputText id="commentsTabTable" value="Comments"/> </h:commandLink> 
													</TD>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</TR>
											</TABLE>
										</TD>
							<td width="16%"  id="tabForStandard">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}" id="linkToAdminOption"
									onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
						<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal">
									<h:commandLink action="#{contractRuleBackingBean.displayRules}" id="linkToRules"
									onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText id="Rule" value="Rules"></h:outputText>
									</h:commandLink>
									</td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
									</TR>
								</TABLE>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->	
									<table border="0" cellspacing="0" cellpadding="0" class="outputText" >
										<tbody>	
											<tr><td>
												<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" >
													<TBODY>

														<TR >															
															<TD valign="top" width="137" ><h:outputText value="Note*" 
															styleClass="#{ContractNotesBackingBean.noteNameValdn?'mandatoryNormal': 'mandatoryError'}" ></h:outputText></TD>
															<TD  align="left" width="176"><h:inputText id= "noteSearchCriteria" styleClass="formInputField" onkeypress="return callNoteSelect();"></h:inputText>
															<h:inputHidden id="hidden_notes"  value= "#{ContractNotesBackingBean.noteName}" /></TD>
															<TD align="left" valign="top" width="379"><h:graphicImage url="" value="../../../images/autoComplete.gif"
																alt="Select" style="cursor:hand;" width="15" height="15" onclick="noteSelect();return false;">
																</h:graphicImage>
															</TD>																										
														</TR>
														<TR id="divRow" style="display:none;">
															<td width="16%" align="left"></TD>
															<td width="24%" align="left">
															<DIV id="selectedNotesDiv" class="selectDataDisplayDiv"></DIV>
															</TD>
														</TR>
														<TR><TD colspan="2">&nbsp;</TD></TR>
														<TR><TD colspan="3"><h:commandButton value="Attach Notes" action="#{ContractNotesBackingBean.attachNotesForContract}"
																styleClass="wpdbutton" id="attachNotesButton"></h:commandButton>																			
														</TD></TR>
														<TR><TD colspan="3">&nbsp;</TD></TR>											
													</TBODY>	
												</TABLE>
											</td></tr>
												<tr>
													<TD><!-- Attach Notes Data Table -->
													<DIV id="noContractNote"
														style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
														background-color:#FFFFFF;">No
														Notes Attached.</DIV>
					
													<div id="attachNotesDataTableDiv"
														style="height:160px; overflow:auto; width:100%;">
														<DIV id="resultHeaderDiv">
															<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0" width="100%" height="21">
																<TR>
																	<TD><b>
																	Notes Attached
																	</b></TD>
																</TR>
															</TABLE>
															<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%" height="21">
																<TBODY>
																<TR>
																<TD align="center" width="70%" class="dataTableColumnHeader"> Note Name</TD>																										
																<TD align="left" width="30%" class="dataTableOddRow">&nbsp;</TD>	
																</TR>
																</TBODY>
															</TABLE>
													</DIV>
														<h:dataTable
														headerClass="dataTableHeader" id="attachNotesTable"
														var="singleValue" cellpadding="3" cellspacing="1"
														bgcolor="#cccccc"
														rendered="#{ContractNotesBackingBean.attachNotesList != null}"
														value="#{ContractNotesBackingBean.attachNotesList}"
														rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
														width="100%">	
															
														<h:column>
															<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteName}"></h:outputText>
															<h:inputHidden id="hiddenNotesName"	value="#{singleValue.noteId}"></h:inputHidden>
															<h:inputHidden id="hiddenNotesId"	value="#{singleValue.noteId}"></h:inputHidden>
															<h:inputHidden id="hiddenNotesVersion"	value="#{singleValue.version}"></h:inputHidden>
													
														</h:column>
														<h:column>
															<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
															<h:commandButton alt="View" id="viewButton" rendered="#{ContractNotesBackingBean.securityAccess}"
																image="../../images/view.gif"
																onclick="viewAction();return false;"></h:commandButton>
		
															<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
															<h:commandButton alt="Delete" id="deleteButton" 
																image="../../images/delete.gif"
																onclick="confirmDeletion();return false;">
															</h:commandButton>
		
														</h:column>
													</h:dataTable></div>
													</TD>
												</tr>														
										</tbody>
								</table>
<!--	End of Page data	-->
								</fieldset>	
								
								<BR>
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr><td align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td><h:selectBooleanCheckbox id="checkall" value="#{ContractNotesBackingBean.checkin}" ></h:selectBooleanCheckbox> 
											&nbsp;<h:outputText value="Check In" />
											</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
											<td  align="left" width="127">
												<table Width = 100%>
													<tr>
														<td><h:outputText value="State  "/></td><td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{ContractNotesBackingBean.state}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Status" /></td><td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{ContractNotesBackingBean.status}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Version   " /></td><td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{ContractNotesBackingBean.version}"  /></td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- Transfer Log -->
								
									</table>
								</FIELDSET>	
								<BR>
								&nbsp;&nbsp;
								<h:commandButton value="Done" id="button2" styleClass="wpdButton" action="#{ContractNotesBackingBean.done}" > </h:commandButton>
								<!--  <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
						
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->

	<DIV id="dummyDiv" style="visibility:hidden"></DIV>
	<h:inputHidden id="dummyHidden" ></h:inputHidden>
	<h:inputHidden id="selectedNotesIdForDelete" value="#{ContractNotesBackingBean.selectedNotesId}" />
	<h:inputHidden id="verifyNotes"	value="#{ContractNotesBackingBean.verifyString}" />
	<h:inputHidden id="hasValErrors"	value="#{ContractNotesBackingBean.hasValidationErrors}"></h:inputHidden>
	<h:inputHidden id="hiddenProductType"	value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
	<h:inputHidden id="duplicateData" value="#{ContractNotesBackingBean.duplicateData}"></h:inputHidden>
	<h:inputHidden id="uncodedLines" value="#{ContractNotesBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>	
	<h:commandLink
			id="deleteNotesButton"
			style="display:none; visibility: hidden;"
			action="#{ContractNotesBackingBean.deleteNotes}">
			<f:verbatim>&nbsp;&nbsp;</f:verbatim>
	</h:commandLink> 
	<h:commandLink id="checkinLink" 
		action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
	</h:commandLink> 
	
<!-- End of hidden fields  -->
				<h:inputHidden id="invokeWebService" value="#{ContractNotesBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{ContractNotesBackingBean.doContractCancelAction}"></h:commandLink>
				</h:form>
			</td>
		</tr>
		<tr>
			<td >
				<%@ include file ="../navigation/bottom.jsp" %>
			</td>
		</tr>
	</table>
	
</BODY>
</f:view>
<script>

IGNORED_FIELD1='contractNotesForm:duplicateData';
		//copyHiddenToInputText('contractNotesForm:hidden_notes','contractNotesForm:noteSearchCriteria',1);
		var entityType = 'ATTACHPRODUCT';
		var lookUpAction = 2;

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('contractNotesForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('contractNotesForm:uncodedLines').value ='';
			submitLink('contractNotesForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('contractNotesForm:uncodedLines').value ='';
	}

		

		function viewAction(){			
			var e = window.event;
			var button_id = e.srcElement.id;			
			var var1 = button_id.split(':');			
			var rowcount = var1[2];			
			var noteId = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesId";
			var noteNameValue = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesName";
			var versionValue = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesVersion";		
			var noteIdValue = document.getElementById(noteId).value;
			var noteName = document.getElementById(noteNameValue).value;
			var version = document.getElementById(versionValue).value;
		 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&noteName='+noteName+'&version='+version;
			//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
			  newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','contractNotesForm:dummyHidden',1,1);		
		}
		function confirmDeletion(){
			var message = "Are you sure you want to detach the note?"
			if(window.confirm(message)){
				getFromDataTableToHidden('contractNotesForm:attachNotesTable','hiddenNotesId','contractNotesForm:selectedNotesIdForDelete');
				submitLink('contractNotesForm:deleteNotesButton');					
			}else{
				return false;
			}
		}
	// Disable Attach button if note already exist
	disbaleAttatchButton('contractNotesForm:verifyNotes');
	function disbaleAttatchButton(hiddenId){	

		hiddenIdObj = document.getElementById(hiddenId).value;
		if(hiddenIdObj!=null && ""!=hiddenIdObj){
			document.getElementById('contractNotesForm:attachNotesButton').disabled = true;
		}
		else{
			document.getElementById('contractNotesForm:attachNotesButton').disabled = false;
		}
		
	}
//Hide table if no value is present
	
	hideIfNoValue('attachNotesDataTableDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('contractNotesForm:attachNotesTable:0:notesName');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('contractNotesForm:attachNotesTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');
			setColumnWidth('headerTable', '40%:60%');
			document.getElementById('noContractNote').style.visibility = 'hidden';

		}
	}
	function callNoteSelect(){
		if(window.event.keyCode == 13){
			noteSelect();
			return false;
		}else{
			return true;
		}
	}
	function noteSelect(){
		var noteName= document.getElementById('contractNotesForm:noteSearchCriteria').value;;
		ewpdModalWindow_ewpd( '../contractpopups/contractNotesPopUp.jsp'+getUrl()+'?entityType='+entityType+'&lookUpAction=2&'+ 'temp=' + Math.random()+'&noteSearchCriteria='+noteName, 'selectedNotesDiv', 'contractNotesForm:hidden_notes',3,1);
		showDiv();
	}

	showDiv();
	copyHiddenToDiv_ewpd("contractNotesForm:hidden_notes", "selectedNotesDiv", 3, 1);
	function showDiv(){
		var selNote = document.getElementById("contractNotesForm:hidden_notes").value ;
		if(null==selNote || ""==selNote)
			divRow.style.display='none';
		else
			divRow.style.display='';
	}

i = document.getElementById("contractNotesForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	}else{
	tabForStandard.style.display='';
	}
var initial='yes'; 
if(document.getElementById('contractNotesForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('contractNotesForm:hasValErrors').value = 'false';
}
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
</script>
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractNotes" />
</form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('contractNotesForm:duplicateData').value == '')
		document.getElementById('contractNotesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractNotesForm:duplicateData').value;
</script>
<script>onPageLoadPopup(); </script>
</HTML>
