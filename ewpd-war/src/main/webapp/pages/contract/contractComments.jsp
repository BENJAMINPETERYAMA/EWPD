<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
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

<TITLE>Contract Comment</TITLE>
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
<script>
function onPageLoadPopup(){
 	var ebxFlag = document.getElementById('ContractCommentForm:hidd_webServiceFlag').value;
 	var vendorFlag = document.getElementById('ContractCommentForm:hidd_VendorFlag').value;
   	if(vendorFlag == "true" && ebxFlag == "true"){ 
   		var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();		
		document.getElementById('ContractCommentForm:hidd_webServiceFlag').value = "false";
		document.getElementById('ContractCommentForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "true" && ebxFlag == "false"){
     	var url = '../contract/contractErrorValidationsPopup.jsp' + getUrl() + '?temp=' + Math.random();
     	document.getElementById('ContractCommentForm:hidd_webServiceFlag').value = "false";
		document.getElementById('ContractCommentForm:hidd_VendorFlag').value = "false";
		newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }else if(vendorFlag == "false" && ebxFlag == "true"){
     	var url = '../popups/ebxValidationsPopup.jsp'+ getUrl() + '?temp=' + Math.random();
     	document.getElementById('ContractCommentForm:hidd_webServiceFlag').value = "false";
		document.getElementById('ContractCommentForm:hidd_VendorFlag').value = "false";
     	newWinForView = window.open(url,'Validations','height=600,width=1000,left=100,top=100,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no, status=no');
     }

}  

  
</script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>


			<TR>
					
				<TD><h:form styleClass="form" id="ContractCommentForm">
				<h:inputHidden id="hidd_webServiceFlag" value="#{contractCommentBackingBean.webServiceFlag}"/>
				<h:inputHidden id="hidd_VendorFlag" value="#{contractCommentBackingBean.vendorFlag}"/>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="273"><!-- Space for Tree  Data	-->
							<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
								page="contractTree.jsp"></jsp:include></DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea" width="911">
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
							<h:inputHidden id="hidden1" value="#{contractCommentBackingBean.loadComments}"></h:inputHidden>
							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<td width="18%">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractBasicInfoBackingBean.getBasicInfo}"
												id="link"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="GeneralInformation"
													value=" General Information"></h:outputText>
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
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"
												id="priceId"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Pricing Information" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="14%" id="tabForStandard1">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabNormal"><h:commandLink
												action="#{ContractNotesBackingBean.load}" id="noteId"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Notes" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="16%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD width="100%" class="tabActive"><h:commandLink
												action="#{contractCommentBackingBean.loadComment}"
												id="linkToComment"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="Comments" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<td width="16%"  id="tabForStandard">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}"
												id="linkToAdminOption"
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
											<td class="tabNormal"><h:commandLink
												action="#{contractRuleBackingBean.displayRules}"
												id="linkToRules"
												onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText id="Rule" value="Rules"></h:outputText>
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">

								<tr></tr>
								<script type="text/javascript">
									function RSCustomInterface(tbElementName){
										this.tbName = tbElementName;
										this.getText = getText;
										this.setText = setText;
								
									function getText(){
										if(!document.getElementById(this.tbName)) {
											alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
											return '';
										} else return document.getElementById(this.tbName).value;
									}
									function setText(text){
										if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
									}
								}
								</script>
								<tr>
									<TD valign="top"><h:outputText id="newComment"
										value="Enter Comments*"
										styleClass="#{contractCommentBackingBean.requiredMinorHeading ? 'mandatoryError': 'mandatoryNormal'}">
									</h:outputText></TD>
									<td></td>
									<td><h:inputTextarea styleClass="formTxtAreaField3"
										id="txtNewComment"
										value="#{contractCommentBackingBean.newComments}" onblur="return validateNoteText();"></h:inputTextarea><BR>
									</td>
								</tr>
								<RapidSpellWeb:rapidSpellWebLauncher
										id="rapidSpellWebLauncher1"
										textComponentName="ContractCommentForm:txtNewComment"
										rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Contract Comments"
										modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
										showNoErrorsMessage="False" showFinishedMessage="False"
										includeUserDictionaryInSuggestions="True"
										allowAnyCase="True" allowMixedCase="True"
										finishedListener="spellFin" 
										windowWidth="570" windowHeight="300"
										modal="False" showButton="False"
										windowX="-1" warnDuplicates="False"
										textComponentInterface="Custom">
									</RapidSpellWeb:rapidSpellWebLauncher>
								<TR>
									
									<TD><h:commandButton styleClass="wpdbutton" value="Save"
										id="save"
										onclick="return runSpellCheck();">
									</h:commandButton></TD>
								</TR>
                                <tr><td>&nbsp;</td></tr>
							</TABLE>

							<div id="resultHeaderDiv">
							<TABLE id="resultHeaderTable1" width="100%" cellpadding="0"
								cellspacing="0" bgcolor="#cccccc">

								<TR>
									<TD height="20"><b>&nbsp;<h:outputText value="Comments History"></h:outputText></b></TD>
								</TR>

							</TABLE>
							<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
								id="resultHeaderTable" border="0" width="100%">
								<TBODY>

									<TR class="dataTableColumnHeader">
										<td align="left">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="Date"></h:outputText>
										</td>
										<td align="left">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="User"></h:outputText>
										</td>
										<td align="left">&nbsp;<h:outputText
											styleClass="formOutputColumnField" value="Comments"></h:outputText>
										</td>
										<td>&nbsp;</td>
									</TR>
								</TBODY>
							</TABLE>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="left"><h:dataTable
										headerClass="tableHeader_comments" id="resultsTable"
										border="0"
										value="#{contractCommentBackingBean.commentHistroyList}"
										rendered="#{contractCommentBackingBean.commentHistroyList != null}"
										var="eachRow" width="100%" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rowClasses="dataTableEvenRow,dataTableOddRow">

										<h:column >
											
											<h:inputHidden id="commentId" value="#{eachRow.commentSysId}"></h:inputHidden>
																						
											<h:outputText id="date" styleClass="formOutputColumnField"
												value="#{eachRow.createdTimeStamp}">
												<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" ></f:convertDateTime>
												</h:outputText><h:outputText id ="space" value =" "> </h:outputText><h:outputText id ="timezone" styleClass="formOutputColumnField" value="#{requestScope.timezone}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="hiddenUser"
												styleClass="formOutputColumnField"
												value="#{eachRow.createdUser}"></h:outputText>
											<h:inputHidden id="time"
											value="#{requestScope.timezone}">	</h:inputHidden>
											<h:inputHidden id="dateSegmentId"
												value="#{eachRow.dateSegmentSysId}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="hiddencomments"
												styleClass="formOutputColumnField"
												value="#{eachRow.commentText}"></h:outputText>

										</h:column>
										<h:column>
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="viewAction();window.showModalDialog('../contract/viewComment.jsp'+getUrl()+'?commentId='+commentId+'&dateSegmentId='+dateSegmentId+'&timezone='+ timezone+'&temp=' + Math.random(),'dummyDiv','dialogHeight:450px;dialogWidth:515px;resizable=false;status=no');return false;">
											</h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:column>
									</h:dataTable></td>
								</tr>

							</table>

							</div>
							<div id="viewAllDiv">
							<table width="100%" border="0" cellpadding="5" cellspacing="0"
								class="outputText">
								<tr>
									<td align="right"><h:commandLink
										style="text-decoration:underline;color:#0033FF"
										action="#{contractCommentBackingBean.actionViewAll}"
										id="linkToViewAll">
										<h:outputText value="View All" />
									</h:commandLink></td>
								</tr>
							</table>
							</div>
							</FIELDSET>

							<br>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td align="left"><table Width=100%><tr><td></td><td></td></tr><tr><td><h:selectBooleanCheckbox id="checkall" value="#{contractCommentBackingBean.checkin}">
									</h:selectBooleanCheckbox>&nbsp;
									<h:outputText value="Check In" />
									</td><td></td></tr><tr><td><a href="#" onclick=" showPopupForContractTransferLog();return false;">
										&nbsp;<h:outputText value="Transfer Log" styleClass="variableLink" /></a></td><td></td></table></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText value="State" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractCommentBackingBean.state}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Status" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractCommentBackingBean.status}" /></td>
										</tr>
										<tr>
											<td><h:outputText value="Version" /></td>
											<td>:<f:verbatim>&nbsp;</f:verbatim><h:outputText
												value="#{contractCommentBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>
								<!-- Transfer Log -->
								
							</table>
							</FIELDSET>
							<BR>
							<TABLE>
								<TBODY>
									<TR>
										<TD>&nbsp;&nbsp;
											<!-- <input type="button" value="Done" class="wpdButton" id="doneButton" onclick="processDone();"/> -->
											<h:commandButton styleClass="wpdbutton" id="button2" value="Done" onclick="return done();"></h:commandButton>
										</TD>

									</TR>
								</TBODY>
							</TABLE>

							<!--	End of Page data	-->
							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!-- Space for hidden fields --> <h:inputHidden
								id="commentIdHidden"
								value="#{contractCommentBackingBean.commentId}"></h:inputHidden>
							<h:inputHidden id="dateSegmentIdHidden"
										value="#{contractCommentBackingBean.dateSegmentId}"></h:inputHidden>
							<h:inputHidden id="timezoneComment"
											value="#{requestScope.timezone}">	</h:inputHidden>
							<h:inputHidden id="hiddenProductType"
											value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
							<h:inputHidden id="hasValErrors"
											value="#{contractCommentBackingBean.hasValidationErrors}"></h:inputHidden>

							<h:inputHidden id="dummyHidden"></h:inputHidden>
							<h:inputHidden id="duplicateData" value="#{contractCommentBackingBean.duplicateData}"></h:inputHidden>
							<h:inputHidden id="uncodedLines" value="#{contractCommentBackingBean.ifUncodedLineNotesExist}"></h:inputHidden>	
							
							<h:commandLink id="saveLink"
								style="hidden" action="#{contractCommentBackingBean.saveComments}">
							</h:commandLink>
							<h:commandLink id="doneLink"
								style="hidden" action="#{contractCommentBackingBean.done}">
							</h:commandLink>
							<h:commandLink id="checkinLink" 
								action="#{contractBasicInfoBackingBean.directCheckin}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink> 
 <!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
				<h:inputHidden id="invokeWebService" value="#{contractCommentBackingBean.invokeWebService}"></h:inputHidden>
				<h:commandLink id="cmdLnkIdForCancelButton" style="display:none; visibility: hidden;" action="#{contractCommentBackingBean.doContractCancelAction}"></h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	
	</BODY>
</f:view>
<script language="JavaScript">	
IGNORED_FIELD1='ContractCommentForm:duplicateData';
IGNORED_FIELD2='ContractCommentForm:commentIdHidden';
IGNORED_FIELD3='ContractCommentForm:dateSegmentIdHidden';

// for uncoded notes validation
	var ifUncodedLineNotesExist = document.getElementById('ContractCommentForm:uncodedLines').value;
	if(ifUncodedLineNotesExist == 'Yes'){
		var message = "There are notes attached to un-coded benefitlines/ unanswered questions and these notes will be removed while check in. Do you want to continue?"
		if(window.confirm(message)){
			document.getElementById('ContractCommentForm:uncodedLines').value ='';
			submitLink('ContractCommentForm:checkinLink');
		}else{
			  NotesErrorRow.style.display='';
			  ErrorRow.style.display ='none';	
		}
		document.getElementById('ContractCommentForm:uncodedLines').value ='';
	}


var doneFlag = false;

function done(){
	doneFlag = true;
	return runSpellCheck();   
}
var commentId;
var dateSegmentId;
var timezone;
var validationMessage = "Comment text has extended special characters, which are copied directly from Microsoft Word document.\nExtended special characters are not supported by WPD application. Please correct and try again.";

	if(document.getElementById('ContractCommentForm:resultsTable') != null) {	
		setColumnWidth('resultHeaderTable','25%:20%:50%:5%');
		setColumnWidth('ContractCommentForm:resultsTable','25%:20%:50%:5%');
	}
	else{
		var divObj = document.getElementById('resultHeaderDiv');
		var divObjViewAll = document.getElementById('viewAllDiv');
					
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
		divObjViewAll.style.visibility = 'hidden';
		divObjViewAll.style.height = "0px";
		divObjViewAll.style.position = 'absolute';
	}
	function viewAction(){

 		getFromDataTableToHidden('ContractCommentForm:resultsTable','commentId','ContractCommentForm:commentIdHidden');
		getFromDataTableToHidden('ContractCommentForm:resultsTable','dateSegmentId','ContractCommentForm:dateSegmentIdHidden');
		commentId = document.getElementById('ContractCommentForm:commentIdHidden').value;
		dateSegmentId = document.getElementById('ContractCommentForm:dateSegmentIdHidden').value;
		timezone = document.getElementById('ContractCommentForm:timezoneComment').value;

	  }
function viewAllComments(){
document.getElementById('resultHeaderDiv').overflow.auto;
}
i = document.getElementById("ContractCommentForm:hiddenProductType").value;

	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}
var initial='yes'; 
if(document.getElementById('ContractCommentForm:hasValErrors').value == 'true'){
	var url='../adminMethods/adminMethodContractValidation.jsp?temp='+Math.random()+'&initial='+initial;
	var returnvalue=window.showModalDialog(url,"","dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	document.getElementById('ContractCommentForm:hasValErrors').value = 'false';
}
function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){

		if(doneFlag)
			document.getElementById('ContractCommentForm:doneLink').click();	
		else{ 
			document.getElementById('ContractCommentForm:saveLink').click();			
		}

	doneFlag = false;	
}

function validateNoteText(){
	if(validateChars('ContractCommentForm:txtNewComment',validationMessage))
			return true;
		else{
			document.getElementById('ContractCommentForm:txtNewComment').focus();
			return false;
		}
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
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractComment" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('ContractCommentForm:duplicateData').value == '')
		document.getElementById('ContractCommentForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('ContractCommentForm:duplicateData').value;
</script>
<script> onPageLoadPopup(); </script>
</HTML>