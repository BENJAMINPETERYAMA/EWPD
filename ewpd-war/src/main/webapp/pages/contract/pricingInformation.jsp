<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>WellPoint Product Database: Pricing Information</TITLE>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function onPageLoadPopup(){
	 	var ebxFlag = document.getElementById('pricingInfoForm:hidd_webServiceFlag').value;
	 	var vendorFlag = document.getElementById('pricingInfoForm:hidd_VendorFlag').value;
	   	if(vendorFlag == "true" && ebxFlag == "true"){ 
	   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
			document.getElementById('pricingInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('pricingInfoForm:hidd_VendorFlag').value = "false";
			newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }else if(vendorFlag == "true" && ebxFlag == "false"){
	     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
	     	document.getElementById('pricingInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('pricingInfoForm:hidd_VendorFlag').value = "false";
			newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }else if(vendorFlag == "false" && ebxFlag == "true"){
	     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
	     	document.getElementById('pricingInfoForm:hidd_webServiceFlag').value = "false";
			document.getElementById('pricingInfoForm:hidd_VendorFlag').value = "false";
	     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
	     }
	} 
</script>
</HEAD>
<f:view>
<BODY onkeypress="return submitOnEnterKey('pricingInfoForm:addPricingInfoButton');">
<hx:scriptCollector id="scriptCollector1">
	<h:inputHidden id="hidden1" ></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
						

			<TD>
				<h:form styleClass="form" id="pricingInfoForm">
					<h:inputHidden id="hidd_webServiceFlag" value="#{contractPricingInfoBackingBean.webServiceFlag}"/>
					<h:inputHidden id="hidd_VendorFlag" value="#{contractPricingInfoBackingBean.vendorFlag}"/>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel">
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						
									<jsp:include page="contractTree.jsp"></jsp:include>	
						 		</DIV>
							</TD>

					<TD colspan="2" valign="top" class="ContentArea">
					<TABLE>
						<TR id ="ErrorRow">
							<TD><w:message ></w:message>
							</TD>
						</TR>

						<TR  id="NotesErrorRow" style="display:none;">
										<TD><div class='errorDiv' /><li id=errorItem>There are notes attached to un-coded benefitlines/unanswered questions.&nbsp;&nbsp;<img id='link_Notes' src='../../images/select.gif' alt='Select' 
											onclick= "callUncodedNotesNotesPopUp();return false;" style='cursor: hand'  /></li></div></TD>
									</TR>		
					</TABLE>
					<h:inputHidden id="hiddenPricingLoad" value="#{contractPricingInfoBackingBean.hiddenLoadPricing}"></h:inputHidden>
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
									<td class="tabNormal"><h:commandLink action="#{contractBasicInfoBackingBean.getBasicInfo}" id="linkToComment"
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
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive">Pricing Information</td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%" id="tabForStandard1">
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
									<td class="tabNormal"><h:commandLink action="#{contractCommentBackingBean.loadComment}" id="link"
									onmousedown="javascript:navigatePageAction(this.id);">
										<h:outputText id="LabelComments" value="Comments"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="16%" id="tabForStandard">
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
						</tr>
					</table>
<!-- End of Tab table -->
<!--	Start of Table for actual Data	-->		
					<fieldset
						style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="2">
						<tr>
							<TD width="150" align="left"><h:outputText
								styleClass="#{contractPricingInfoBackingBean.coverageInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="coverageOutText" value="Coverage* "></h:outputText></TD>

							<td align="left" width="180"><DIV id="CoverageDiv" class="selectDataDisplayDiv"></DIV>
								<h:inputHidden id="coverageHidden" 	value="#{contractPricingInfoBackingBean.coverage}"></h:inputHidden>
<!--							<SCRIPT>displayWithStyleForContract('pricingInfoForm:coverageHidden','pricingInfoForm:coverage'); </SCRIPT>	--></td>
							<td align="left"><img
								src="../../../images/select.gif"								
								onclick="coverageInfo(); return false;"
								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>

						<tr>
							<td width="150" align="left"><h:outputText
								styleClass="#{contractPricingInfoBackingBean.pricingInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="pricingOutText" value="Pricing* "></h:outputText></td>
							<td align="left" width="180"><DIV id="PricingDiv" class="selectDataDisplayDiv"></DIV>
							<h:inputHidden id="pricingHidden" 	value="#{contractPricingInfoBackingBean.pricing}"></h:inputHidden>
							</td>
							<td align="left"><img
								src="../../../images/select.gif"
								onclick="pricingInfo();return false;"
								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>
						<tr>
							<td width="150" align="left"><h:outputText
								styleClass="#{contractPricingInfoBackingBean.networkInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="networkOutText" value="Network* "></h:outputText></td>
							<td align="left" width="180"><DIV id="NetworkDiv" class="selectDataDisplayDiv"></DIV>
							<h:inputHidden id="networkHidden" 	 value="#{contractPricingInfoBackingBean.network}"></h:inputHidden>
							</td>
							<td align="left"><img
								src="../../../images/select.gif"
								onclick="networkInfo();return false;"
								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>
						<tr></tr>
						<tr></tr>
						<tr></tr>
						<tr>
							<td colspan="3" valign="top"><h:commandButton value="Save"
								styleClass="wpdbutton" id="addPricingInfoButton" 
								action="#{contractPricingInfoBackingBean.addAndStorePricingInfo}"
								disabled=""></h:commandButton></td>
						</tr>
						<tr>
						</tr>
					</table>
					</fieldset>
					<br/>
					<fieldset style="margin-left:6px;margin-right:-6px;width:100%;height:200px">
					<div id="panel2">
						<div id="resultInfo" class="dataTableColumnHeader">
						<br/><CENTER>No Pricing Information is Available.</CENTER>
					</div>

					<div id="resultHeaderDiv">
						<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width:100%;height: 15">
							&nbsp;Associated Pricing Records
						</div>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0" id="resultHeaderTable" border="0" style="width:99.3%;">
							<TBODY>

								<TR class="dataTableColumnHeader">
									<td align="left" >
												&nbsp;<h:outputText styleClass="formOutputColumnField" value="Coverage" ></h:outputText> 
									</td>
									<td  align="left" >
	              								&nbsp;<h:outputText styleClass="formOutputColumnField" value="Pricing" ></h:outputText>
									</td >
									<td align="left" >
	              								&nbsp;<h:outputText styleClass="formOutputColumnField" value="Network"></h:outputText>
									</td>
									<td >
										&nbsp;<h:outputText styleClass="formOutputColumnField" value="Delete"></h:outputText>
									</td>
								</TR>
							</TBODY>
						</TABLE>
					</div>
<%--
<h:inputHidden id="loadPricingInfoList" value= "#{contractPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>		
					<div id="panel2Content" style="height:106px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
					<table cellpadding="0" cellspacing="0" border="0">
					<tr>
					<td align="left">
							
							<h:dataTable headerClass="tableHeader" id="resultsTable" border="0"
								value="#{contractPricingInfoBackingBean.pricingInformationList}"
								rendered="#{contractPricingInfoBackingBean.renderFlag}" var="eachRow" 
								cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
								rowClasses="dataTableEvenRow,dataTableOddRow">
								
								<h:column>
									<h:inputHidden id="contractID" value="#{eachRow.contractDateSegmentSysId}"></h:inputHidden>	
									<h:outputText id="coverageInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.coverage}"></h:outputText>
									<h:inputHidden id="coverageInfoID" value="#{eachRow.coverage}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="pricingInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.pricing}"></h:outputText>
									<h:inputHidden id="pricingInfoID" value="#{eachRow.pricing}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="networkInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.network}"></h:outputText>
									<h:inputHidden id="networkInfoID" value="#{eachRow.network}"></h:inputHidden>
								</h:column>
								<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
													id="checkBox" onclick = 'javascript:validateDelete()'> 
                                        	</h:selectBooleanCheckbox>
								</h:column>
							</h:dataTable>
						</td>
					</tr>
						

					</table>

					</div>
						<BR/>
						<table>
							<TR>
									<TD>
										<DIV id='deletePricingInfo'>
											<h:commandButton id="delete" value="Delete"  
												styleClass="wpdButton" onclick = "confirmDeletion(); 
												return false;"> 
											</h:commandButton>
										</DIV>
									</TD>
								</TR>
						</table>
					</div>

					</fieldset>
						
				<br/>
					<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr><td align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td>
												<h:selectBooleanCheckbox id="checkall" value="#{contractPricingInfoBackingBean.checkIn}" > 
													</h:selectBooleanCheckbox>&nbsp;<h:outputText value="Check In"  />
													</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
											<td  align="left" width="127">
												<table Width = 100%>
													<tr>
														<td><h:outputText value="State"/></td>
                                                        
														<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{contractPricingInfoBackingBean.state}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Status" /></td>
														<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{contractPricingInfoBackingBean.status}" /></td>
													</tr>
													<tr>
														<td><h:outputText value="Version" /></td>
														<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText value="#{contractPricingInfoBackingBean.version}" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- Transfer Log -->
								
									</table>
								</FIELDSET>	
<br>

								<TABLE>
									<TBODY>
										<TR>
											<TD>
												&nbsp;&nbsp;
												<h:commandButton styleClass="wpdbutton" value="Done" id="button2" action="#{contractPricingInfoBackingBean.done}" > </h:commandButton>
												<!--  <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
											</TD>

										</TR>
									</TBODY>
								</TABLE>

			<h:commandLink id="deleteHidden"
			style="display:none; visibility: hidden;" >
			
		</h:commandLink>
	<table>
		<tr>
			<h:inputHidden id="hasValErrors" value="#{contractPricingInfoBackingBean.hasValidationErrors}"></h:inputHidden>
			<h:inputHidden id="contractIDForRefData" value="#{contractPricingInfoBackingBean.contractIdForRefData}"></h:inputHidden>
			<h:inputHidden id="selectedContractDSSysID" value="#{contractPricingInfoBackingBean.selectedContractDSSysID}"></h:inputHidden>
			<h:inputHidden id="selectedCoverageID" value="#{contractPricingInfoBackingBean.selectedCoverageID}"></h:inputHidden>
			<h:inputHidden id="selectedPricingID" value="#{contractPricingInfoBackingBean.selectedPricingID}"></h:inputHidden>
			<h:inputHidden id="selectedNetworkID" value="#{contractPricingInfoBackingBean.selectedNetworkID}"></h:inputHidden>
			<h:commandLink id="deleteButton1" action="#{contractPricingInfoBackingBean.deleteRecordPricingInfo}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink> 
			<h:inputHidden id="hiddenProductType" value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
			<h:inputHidden id="duplicateData" value="#{contractPricingInfoBackingBean.duplicateData}"></h:inputHidden>
			<h:inputHidden id="txtLob" value="#{contractPricingInfoBackingBean.lob}"></h:inputHidden>
			<h:inputHidden id="txtBusinessEntity" value="#{contractPricingInfoBackingBean.be}"></h:inputHidden>
			<h:inputHidden id="txtBusinessGroup" value="#{contractPricingInfoBackingBean.bg}"></h:inputHidden>
			<h:inputHidden id="txtMarketBusinessUnit" value="#{contractPricingInfoBackingBean.mbu}"></h:inputHidden>
			<h:inputHidden id="uncodedLines" value="#{contractPricingInfoBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>	
			<h:commandLink id="checkinLink" 
					action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
			</h:commandLink> 
		</tr>
	</table>
</TABLE>
				<h:inputHidden id="invokeWebService" value="#{contractPricingInfoBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractPricingInfoBackingBean.doContractCancelAction}"></h:commandLink>
		</h:form>
		</td>			
		</tr>
		<tr> 
			<td>				
				<%@ include file ="../navigation/pageFooter.jsp" %>
			</td>			
		</tr>	
 	</TABLE>
</hx:scriptCollector>
	</body>
</f:view>

<script language="JavaScript">

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('pricingInfoForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('pricingInfoForm:uncodedLines').value ='';
			submitLink('pricingInfoForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('pricingInfoForm:uncodedLines').value ='';
	}

function test(){
	//alert(document.getElementById('pricingInfoForm:hiddenCoverage').value);
}
IGNORED_FIELD1='pricingInfoForm:duplicateData';

	hideResultDiv();
 copyHiddenToDiv_ewpd('pricingInfoForm:coverageHidden','CoverageDiv',2,2); 
 copyHiddenToDiv_ewpd('pricingInfoForm:pricingHidden','PricingDiv',2,2); 
 copyHiddenToDiv_ewpd('pricingInfoForm:networkHidden','NetworkDiv',2,2); 
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');

		var tableObj = document.getElementById('pricingInfoForm:resultsTable');
		if (tableObj != null && tableObj.rows.length >0) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
			document.getElementById('deletePricingInfo').style.visibility = 'visible';
			document.getElementById('pricingInfoForm:delete').disabled = true;
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
			document.getElementById('deletePricingInfo').style.visibility = 'hidden';
		}
	}


		if(document.getElementById('pricingInfoForm:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('pricingInfoForm:resultsTable').offsetHeight <= 106) {
				document.getElementById('pricingInfoForm:resultsTable').width = relTblWidth+"px";
				setColumnWidth('pricingInfoForm:resultsTable','31%:31%:31%:7%');
				setColumnWidth('resultHeaderTable','31%:31%:31%:7%');
			}else{
				document.getElementById('pricingInfoForm:resultsTable').width = (relTblWidth-17)+"px";
				document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
				document.getElementById('resultHeaderTableInfo').style.width = (relTblWidth-17)+"px";
				setColumnWidth('pricingInfoForm:resultsTable','31%:31%:31%:7%');
				setColumnWidth('resultHeaderTable','31%:31%:31%:7%');
			}
		}

	// Enable or disable delete button
	function validateDelete(){
		// var declarations
		var tableObject = null;
		var isChecked = null;
		var checkBoxObject = null;

		// get the table object
		tableObject = document.getElementById('pricingInfoForm:resultsTable');
		if(tableObject){
			isChecked = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[3].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					isChecked = true;
					break;
				}
			}
			document.getElementById('pricingInfoForm:delete').disabled = !isChecked;			
		}
	}

function confirmDeletion(){
		var message = "Are you sure you want to delete?"
		var pricingInfo = getSelectedPricingInfoForDelete();
		if(pricingInfo[0] == ''){
			alert("Please select atleast one pricing information to delete.");	
			return false;
		}
		else{
			if(window.confirm(message)){
				document.getElementById('pricingInfoForm:selectedCoverageID').value = pricingInfo[0];
				document.getElementById('pricingInfoForm:selectedPricingID').value = pricingInfo[1];
				document.getElementById('pricingInfoForm:selectedNetworkID').value = pricingInfo[2];
				submitLink('pricingInfoForm:deleteButton1');
			}
			else{
				return false;
			}
		}
	}

	function getSelectedPricingInfoForDelete(){
		// variable declarations
		var tableObject = null;
		var checkBoxObject = null;
		var coverage = null;
		var pricing = null;
		var network = null;
		var status = null;

		// get the table object
		tableObject = document.getElementById('pricingInfoForm:resultsTable');
		if(tableObject){
			pricing = '';
			coverage ='';
			network = '';
			status = false;
			// iterate the rows
			for(var i = 0; i < tableObject.rows.length; i++){
				// check whether the checkbox is checked
				checkBoxObject = tableObject.rows[i].cells[3].children[0];
				if(checkBoxObject && checkBoxObject.checked){
					if(status){
						coverage = coverage + '~';
						pricing = pricing + '~';
						network = network + '~';
					}
					status = true;
					// get the defn id and append the ids
					coverage = coverage + tableObject.rows[i].cells[0].children[2].value;
					pricing = pricing + tableObject.rows[i].cells[1].children[1].value;
					network = network + tableObject.rows[i].cells[2].children[1].value;
				}
			}
		}
		// return the selected ids
		return [coverage,pricing,network];
	}

function coverageInfo(){
	var contractID = document.getElementById('pricingInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Coverage Popup';	
	getSelectedContractPricingData('../popups/SearchPopupSingleSelect.jsp'+getUrl()+'?parentCatalog='+'Coverage'+'&entityId='+ contractID +'&titleName='+titleName+ '&entityType=' + entityType,'pricingInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','CoverageDiv','pricingInfoForm:coverageHidden',2,2,'Coverage');
	setRefDataUseFlag('pricingInfoForm', 'coverageOutText', 'CoverageDiv');
 	return false;
}

function pricingInfo(){
	var contractID = document.getElementById('pricingInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Pricing Popup';	
	getSelectedContractPricingData('../popups/searchPricingPopup.jsp'+getUrl()+'?parentCatalog='+'Pricing'+'&entityId='+ contractID +'&titleName='+titleName+ '&entityType=' + entityType,'pricingInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','PricingDiv','pricingInfoForm:pricingHidden',2,2,'Pricing');	
	setRefDataUseFlag('pricingInfoForm', 'pricingOutText', 'PricingDiv');
 	return false;
}
function networkInfo(){
	var contractID = document.getElementById('pricingInfoForm:contractIDForRefData').value;
	var entityType = 'contract';
	var titleName = 'Network Popup';	
	getSelectedContractPricingData('../popups/SearchPopupSingleSelect.jsp'+getUrl()+'?parentCatalog='+'Network'+'&entityId='+ contractID +'&titleName='+titleName+ '&entityType=' + entityType,'pricingInfoForm','txtLob','txtBusinessEntity','txtBusinessGroup','txtMarketBusinessUnit','NetworkDiv','pricingInfoForm:networkHidden',2,2,'Network');
	setRefDataUseFlag('pricingInfoForm', 'networkOutText', 'NetworkDiv');
 	return false;
}
i = document.getElementById("pricingInfoForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
var initial='yes'; 
if(document.getElementById('pricingInfoForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp'+getUrl()+'?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('pricingInfoForm:hasValErrors').value = 'false';
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
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractPricingInfo" />
</form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('pricingInfoForm:duplicateData').value == '')
		document.getElementById('pricingInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('pricingInfoForm:duplicateData').value;
</script>
<script>
onPageLoadPopup();
</script>
</HTML>