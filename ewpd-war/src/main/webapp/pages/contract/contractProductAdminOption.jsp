<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" 	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"		title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" 	title="Style">

<TITLE>WellPoint Product Database: Admin Option</TITLE>
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
<script type="text/javascript">
function onPageLoadPopup(){
 	var ebxFlag = document.getElementById('formName:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('formName:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('formName:hidd_webServiceFlag').value = "false";
		document.getElementById('formName:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "true" && ebxFlag == "false"){
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('formName:hidd_webServiceFlag').value = "false";
		document.getElementById('formName:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('formName:hidd_webServiceFlag').value = "false";
		document.getElementById('formName:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }
}  

</script>
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('formName:saveButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>

			<td><h:form styleClass="form" id="formName">
			<h:inputHidden id="hidd_webServiceFlag" value="#{contractProductAdminOptionBackingBean.webServiceFlag}"/>
			<h:inputHidden id="hidd_VendorFlag" value="#{contractProductAdminOptionBackingBean.vendorFlag}"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> 
							<jsp:include page="../contract/contractTree.jsp"></jsp:include>
						</DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR id ="ErrorRow">
										<TD><w:message></w:message></TD>
									</TR>
									<TR  id="NotesErrorRow" style="display:none;">
										<TD><div class='errorDiv' /><li id=errorItem>There are notes attached to un-coded benefitlines/unanswered questions.&nbsp;&nbsp;<img id='link_Notes' src='../../images/select.gif' alt='Select' 
											onclick= "callUncodedNotesNotesPopUp();return false;" style='cursor: hand'  /></li></div></TD>
									</TR>	
							</TBODY>
						</TABLE>
<h:inputHidden id="hiddenData"
					value="#{contractProductAdminOptionBackingBean.adminOptionHidden}"></h:inputHidden>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
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
							<td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal">
										<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}" id="priceId" onmousedown="javascript:navigatePageAction(this.id);"> 
										<h:outputText value="Pricing Information" /> </h:commandLink> </td>
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
									<td class="tabNormal"><h:commandLink
										action="#{ContractNotesBackingBean.load}"
										id="linkToExclusions" onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText id="labelNotes" value="Notes"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="16%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment"
									onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText id="LabelComments" value="Comments"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="16%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive"><h:outputText value="Admin Option" /></td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
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
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
							class="smallfont" border="0">
							<TBODY>
								<TR>
									<td>&nbsp;</td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:outputText
										styleClass="#{contractProductAdminOptionBackingBean.adminOptionInvalid ? 'mandatoryError': 'mandatoryNormal'}"
										value="Admin Option*" /></td>
									<td width="24%" align="center">
									<div id="adminDataDiv" align="left"
										class="selectDataDisplayDiv"></div>
									</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;&nbsp;&nbsp;<h:commandButton
										image="../../images/select.gif" alt="Select"
										onclick="checkIfBenefitLevelAvailable();return false;"></h:commandButton></td>
									<td width="52%" align="left">
								</TR>
								<TR>
									<td height="2">&nbsp;&nbsp;</td>
								</TR>
								<TR>
									<td width="16%" align="left"><h:commandButton
										styleClass="wpdButton" type="submit" value="Save"
										id="saveButton"
										action="#{contractProductAdminOptionBackingBean.saveProductAdmin}"></h:commandButton></td>
									<td width="24%" align="center">&nbsp;</td>
									<td width="8%" align="left" style="cursor:hand">&nbsp;</td>
									<td width="52%" align="left">
								</TR>
								
								<TR>
									<td colspan="4">
									<TABLE width="100%" cellspacing="0" cellpadding="0">

<DIV id="noContractAdminOptions"
														style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
														background-color:#FFFFFF;">No
														Admin Options Attached.</DIV>
										<TR>
											<TD><BR />
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD><b> <h:outputText value="Associated Admin Options"></h:outputText>
													</b></TD>
												</TR>
											</TABLE>
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="left"><h:outputText value="Admin Name "></h:outputText>
														</TD>
														<TD>&nbsp;<h:outputText value="Delete "></h:outputText></TD>
														</TbR>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>
											<td>
											
					
											<DIV id="searchResultdataTableDiv"
												style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
											<!-- Search Result Data Table --> 
											<h:dataTable
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{contractProductAdminOptionBackingBean.adminList != null}"
												value="#{contractProductAdminOptionBackingBean.adminList}"
												rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
												<h:column>
													<h:inputHidden id="adminId" value="#{singleValue.adminKey}"></h:inputHidden>
													<h:outputText id="adminDesc" value="#{singleValue.adminDesc}"></h:outputText>
												</h:column>
												<h:column>
													<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
													<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
													id="checkBox" onclick = 'javascript:validateDelete()'> 
                                        			</h:selectBooleanCheckbox>
												</h:column>
											</h:dataTable></DIV>
											</td>
										</tr>
									</TABLE>


									</td>
								</tr>
								<tr>
									<td><DIV id='deleteAdminOption'>
											<h:commandButton id="delete" value="Delete"  
												styleClass="wpdButton" onclick = "confirmDeletion(); 
												return false;"> 
											</h:commandButton>
										</DIV></td>
								</tr>
							</TBODY>
						</TABLE>
						<BR/>
						<!--	End of Page data	--></fieldset>

						<table width="100%">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
								<table border="0" width="100%" class="tableBorder">
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="73%"align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td><h:selectBooleanCheckbox id="checkall" 
											value="#{contractProductAdminOptionBackingBean.checkin}">
										</h:selectBooleanCheckbox>&nbsp; <h:outputText value="Check In"></h:outputText>
										</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
										
										<td width="26%">
										<table Width=100%>
											<tr>
												<td>State</td>
												<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
													value="#{contractProductAdminOptionBackingBean.state}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td>Status</td>
												<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
													value="#{contractProductAdminOptionBackingBean.status}"
													styleClass="outputText" /></td>
											</tr>
											<tr>
												<td>Version</td>
												<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
													value="#{contractProductAdminOptionBackingBean.version}"
													styleClass="outputText" /></td>
											</tr>
										</table>
										</td>
									</tr>
									<!-- Transfer Log -->
								
								</table>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<!-- <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
									<h:commandButton value="Done" styleClass="wpdButton" id="doneButton" action="#{contractProductAdminOptionBackingBean.done}"></h:commandButton>
								</td>
							</tr>
						</table>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="adminData"	value="#{contractProductAdminOptionBackingBean.adminString}"></h:inputHidden>
				<h:inputHidden id="adminDataDelete"	value="#{contractProductAdminOptionBackingBean.deleteAdminString}"></h:inputHidden>
				<h:inputHidden id="hasValErrors" value="#{contractProductAdminOptionBackingBean.hasValidationErrors}"></h:inputHidden>
				<h:inputHidden id="duplicateData" value="#{contractProductAdminOptionBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="dateSegemntId" value="#{contractProductAdminOptionBackingBean.dateSegmentId}"></h:inputHidden>
				<h:inputHidden id="uncodedLines" value="#{contractProductAdminOptionBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>	
				<h:commandLink id="deleteLink"	style="display:none; visibility: hidden;"
					action="#{contractProductAdminOptionBackingBean.deleteAdmin}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="checkinLink" 
					action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink> 
				<!-- End of hidden fields  -->
				<h:inputHidden id="invokeWebService" value="#{contractProductAdminOptionBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractProductAdminOptionBackingBean.doContractCancelAction}"></h:commandLink>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	
	</BODY>
</f:view>

<script>
IGNORED_FIELD1='formName:duplicateData';
// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('formName:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('formName:uncodedLines').value ='';
			submitLink('formName:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('formName:uncodedLines').value ='';
	}

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('formName:searchResultTable:0:adminId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractAdminOptions').style.visibility = 'visible';
					document.getElementById('deleteAdminOption').style.visibility = 'hidden';
		}else{
			if(document.getElementById('formName:searchResultTable').offsetHeight <= 200) {	
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('formName:searchResultTable', '40%:60%');
	 		setColumnWidth('resultHeaderTable', '40%:60%');
			}else{
				var relTblWidth = document.getElementById('formName:searchResultTable').offsetWidth;
				document.getElementById('headerTable').width = document.getElementById('formName:searchResultTable').offsetWidth;
				document.getElementById('resultHeaderTable').width = document.getElementById('formName:searchResultTable').offsetWidth;
setColumnWidth('formName:searchResultTable', '40%:60%');
	 		setColumnWidth('resultHeaderTable', '40%:60%');
			}
			document.getElementById('noContractAdminOptions').style.visibility = 'hidden';
				document.getElementById('deleteAdminOption').style.visibility = 'visible';
			document.getElementById('formName:delete').disabled = true;
			
		}
	}	
	
copyHiddenToDiv_ewpd('formName:adminData','adminDataDiv',2,2); 

	// Enable or disable delete button
	function validateDelete(){
		// var declarations
		var tableObject = null;
		var isChecked = null;
		var checkBoxObject = null;

		// get the table object
		tableObject = document.getElementById('formName:searchResultTable');
		if(tableObject){
			isChecked = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[1].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					isChecked = true;
					break;
				}
			}
			document.getElementById('formName:delete').disabled = !isChecked;			
		}
	}




function confirmDeletion(){
		var message = "Are you sure you want to detach the Admin Option?"
		var adminId = getSelectedAdminIds();
		if(adminId == ''){
			alert("Please select atleast one Admin Option to detach.");	
			return false;
		}
		else{
			if(window.confirm(message)){
				//submitDataTableButton('benefitDefinitionCreateForm:searchResultTable', 'benefitDefinitionMasterId', 'benefitDefinitionCreateForm:targetHiddenToStoreMasterKey', 'benefitDefinitionCreateForm:deleteBenefitDefinition');
				document.getElementById('formName:adminDataDelete').value = adminId;
				document.getElementById('formName:deleteLink').click();
			}
			else{
				return false;
			}
		}
	}

function getSelectedAdminIds(){
		// variable declarations
		var tableObject = null;
		var checkBoxObject = null;
		var adminIds = null;
		var status = null;

		// get the table object
		tableObject = document.getElementById('formName:searchResultTable');
		if(tableObject){
			adminIds = '';
			status = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[1].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					if(status){
						adminIds = adminIds + '~';
					}
					status = true;
					// get the defn id and append the ids
					adminIds = adminIds + tableObject.rows[i].cells[0].children[0].value;
				}
			}
		}
		// return the selected ids
		return adminIds;
	}
var initial='yes'; 
if(document.getElementById('formName:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('formName:hasValErrors').value = 'false';
}
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
function checkIfBenefitLevelAvailable(){
		var dateSegemntId = document.getElementById('formName:dateSegemntId').value;
		ewpdModalWindow_ewpd('../popups/searchPopup.jsp'+getUrl()+'?entityId='+dateSegemntId+'&queryName=getAdminOptionForContract&headerName=Admin Option&titleName=Admin Option Popup&temp='+Math.random(),'adminDataDiv','formName:adminData',2,2);return false;
}

</script>
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractAdminOption" />
</form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('formName:duplicateData').value == '')
		document.getElementById('formName:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('formName:duplicateData').value;
</script>
<script> onPageLoadPopup(); </script>
</HTML>




