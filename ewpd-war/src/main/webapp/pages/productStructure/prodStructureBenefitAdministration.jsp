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

<TITLE>Product Structure Benefit Administration</TITLE>    
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
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>		
				
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv" style="height:620"><jsp:include
							page="../productStructure/productStructureTree.jsp" /></DIV>

						</TD>
						
						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
										
								</tr>
							</TBODY>
						</TABLE>
						
						<!-- Table containing Tabs -->
						<table width="600" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>

								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabActive">
											<h:outputText value="Benefit Administration" />
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->
					
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:470">

						<!--	Start of Table for actual Data	--> <BR>
						<div id="messageTextForNoQuestionsDiv">
						<STRONG>&nbsp;<h:outputText value="No Questionnaire available." /></STRONG>
						</div>
						<DIV id="benefitAdministrationDiv">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td></td>
								</TR>
								<TR>
									<td valign="middle">

									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:15;width:100%;">
									<B>&nbsp;<h:outputText value="Selected Questions"></h:outputText> </B>
									</div>
									<div id="displayHeaderDiv"
										style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
										id="displayHeaderTable"
										binding="#{productStructureBenefitAdministrationBackingBean.headerPanel}"
										rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid></div>
									<div id="displayPanelContent12"
										style="position:relative;overflow:auto;height:420;"><h:panelGrid
										id="panelTable"
										binding="#{productStructureBenefitAdministrationBackingBean.panel}"
										>
									</h:panelGrid></div>

									</td>
								</TR>
								<TR>
									<td><%-- div style="position:relative;  background-color:#ffffff;overflow:auto;">
											<div id="displayPanelContent12" style="position:relative;">
												<h:panelGrid id="panelTable"
													binding="#{productStructureBenefitAdministrationBackingBean.panel}"
													rowClasses="dataTableEvenRow,dataTableOddRow">
												</h:panelGrid> 
											</div>
										</div--%><BR>
									</td>
								</TR>
								<TR>
								</TR>

							

								<TR id="saveButton">
									<TD width="110" height="1">
									<div id="saveButtonDisplay">
									<h:commandButton value="Save"
										styleClass="wpdButton" onmousedown="javascript:savePageAction(this.id);"
										action="#{productStructureBenefitAdministrationBackingBean.saveBenefitAdministration}">
									</h:commandButton></div></TD>
								</TR>
							</TBODY>
						</TABLE>
						</DIV>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
			    <h:inputHidden id="oldAnswerValue"></h:inputHidden>
						
				<h:inputHidden id="benTypeHidden"
							value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
				<h:inputHidden id="answerStates"
							value="#{productStructureBenefitAdministrationBackingBean.answerStates}" />
		        <h:inputHidden id="questionsStates"
							value="#{productStructureBenefitAdministrationBackingBean.questionsStates}" />
				<h:inputHidden id="tildaNoteStatusId"
						value="#{productStructureBenefitAdministrationBackingBean.tildaNoteStatus}"></h:inputHidden>


			<!--  	<h:inputHidden id = "valForChkBox" value ="#{productStructureBenefitAdministrationBackingBean.showHiddenSelected}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> -->
				<h:commandLink id="selectedShowHiddenLink"
					style="display:none; visibility: hidden;"
					action="#{productStructureBenefitAdministrationBackingBean.loadBenefitAdmins}">
					<f:verbatim />
				</h:commandLink>


				
	<h:inputHidden id="rowId"
								value="#{productStructureBenefitAdministrationBackingBean.rowNum}"></h:inputHidden>
	<h:inputHidden id="answerId"
								value="#{productStructureBenefitAdministrationBackingBean.answerId}"></h:inputHidden>
	<h:inputHidden id="answerDesc"
								value="#{productStructureBenefitAdministrationBackingBean.answerDesc}"></h:inputHidden>
	<h:commandLink id="newQuestionnreListLink"
					style="display:none; visibility: hidden;"
					action="#{productStructureBenefitAdministrationBackingBean.selectNewQuestionnreList}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="hiddenLnk2"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="attachNotesToQuestionLink"
		style="display:none; visibility: hidden;"
		action="#{notesAttachmentBackingBean.attachNotesToQuestion}">
		<f:verbatim />
	</h:commandLink>
				<!-- End of hidden fields  -->


				<!-- End of hidden fields  -->
				<h:inputHidden id="duplicateData" value="#{productStructureBenefitAdministrationBackingBean.duplicateData}"/>
			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
IGNORED_FIELD1='benefitAdmnForm:duplicateData';
initialize();
		
function initialize(){
	if(document.getElementById('benefitAdmnForm:panelTable') != null) {
			if(document.getElementById('benefitAdmnForm:panelTable').rows.length <= 12)
				{
					document.getElementById('benefitAdmnForm:displayHeaderTable').width = "100%";
					document.getElementById('benefitAdmnForm:panelTable').width = "100%";
					setColumnWidth('benefitAdmnForm:displayHeaderTable','23%:15%:19%:25%:18%');
					setColumnWidth('benefitAdmnForm:panelTable','23%:15%:19%:25%:18%');
	
				}
			else
				{
					var relTblWidth = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					document.getElementById('benefitAdmnForm:displayHeaderTable').width = "97.4%";
					document.getElementById('benefitAdmnForm:panelTable').width = "100%";
					setColumnWidth('benefitAdmnForm:displayHeaderTable','23%:20%:14%:25%:18%');
					setColumnWidth('benefitAdmnForm:panelTable','23%:20%:14%:25%:18%');
				}
		}
}

var tableObject = document.getElementById('benefitAdmnForm:panelTable');
	if(tableObject.rows.length > 0){
		var msgDivObj = document.getElementById('messageTextForNoQuestionsDiv');
		msgDivObj.style.visibility = "hidden";
		msgDivObj.style.height = "0px";
	}else{
		var divObjdisplayHeaderDiv = document.getElementById('displayHeaderDiv');
		var divObjLabelHeaderDiv = document.getElementById('LabelHeaderDiv');
		var divObj = document.getElementById('displayPanelContent12');
		divObj.style.visibility = "hidden";
		divObjdisplayHeaderDiv.style.visibility="hidden";
		divObjLabelHeaderDiv.style.visibility="hidden";
		divObj.style.height = "2px";
		var divSaveButton = document.getElementById('saveButtonDisplay');
		divSaveButton.style.visibility = "hidden";
		//divSaveButton.style.height = "2px";
	}



//Method to hide the "save" button for mandate Product Structure
var type = document.getElementById("benefitAdmnForm:benTypeHidden").value;
if(type== "Standard Definition")
	saveButton.style.display="";
else
	saveButton.style.display="none";


function goToAction(){
	var select = document.getElementById('benefitAdmnForm:showHidden');
	if(select.checked){
		document.getElementById('benefitAdmnForm:valForChkBox').value = true ;
	}else{
		document.getElementById('benefitAdmnForm:valForChkBox').value = false ;
	}
	//submitLink('benefitAdmnForm:selectedShowHiddenLink');	
}
function storeOldValue(selectObj){
			var oldValue = selectObj.value;
			document.getElementById('benefitAdmnForm:oldAnswerValue').value = oldValue;
		}	
function loadNewChild(questionComponent){
var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';
var questionComponentId = questionComponent.id;
var questionComponentIdValue = questionComponent.value;	
var questionComponentText = questionComponent.options[questionComponent.selectedIndex].text;
var idArray = questionComponentId.split("selectitem");
var rowNo = idArray[1];
var childCount = document.getElementById('benefitAdmnForm:childCount'+rowNo).value ;
document.getElementById('benefitAdmnForm:rowId').value = rowNo;
document.getElementById('benefitAdmnForm:answerId').value = questionComponentIdValue;
document.getElementById('benefitAdmnForm:answerDesc').value = questionComponentText;
if(childCount>0){
			if (confirm(message) ){
				submitLink('benefitAdmnForm:newQuestionnreListLink');
				return true;
			}else{
			questionComponent.value = document.getElementById('benefitAdmnForm:oldAnswerValue').value;
				return false;
			}
		}else{
			submitLink('benefitAdmnForm:newQuestionnreListLink');
		} 
}
IGNORED_FIELD2='benefitAdmnForm:tildaNoteStatusId';
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntityType,bcId,benefitDefnId,adminLvlOptionId,imageId,i){	
	var retValue = window.showModalDialog(popupName +getUrl()+ "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="+primaryentityId+"&primaryEntytyType="+primaryEntityType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId , 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
oldStatusValue = document.getElementById('benefitAdmnForm:tildaNoteStatusId').value;
var imageObj = document.getElementById('benefitAdmnForm:'+imageId);
if(retValue == "notes_exists"){
		imageObj.src = "../../images/notes_exist.gif";
		document.getElementById('benefitAdmnForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'Y';
	}else if(retValue == "no_notes"){
		imageObj.src = "../../images/page.gif";
		document.getElementById('benefitAdmnForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'N';
	}
	//document.getElementById('addQuestioniareForm:hiddenNotesStatus' + imageId).value = retValue;
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitAdminProductStructure" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitAdmnForm:duplicateData').value == '')
		document.getElementById('benefitAdmnForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdmnForm:duplicateData').value;
</script>
</HTML>
