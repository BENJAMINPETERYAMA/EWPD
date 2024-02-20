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
<STYLE type="text/css">
  
.gridColumn40{
	width: 40%;		
}
.gridColumn20{
	width: 20%;	
}
.gridColumn30{
	width: 30%;	    
}
.gridColumn10{
	width: 10%;	    
}
 </STYLE>
<TITLE>productBenefitAdministration</TITLE>
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
	<!--<h:inputHidden id="hidden1"
		value="#{productBenefitAdministrationBackingBean.hiddenInit}"></h:inputHidden> -->
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv" ><!-- Space for Tree  Data	--> <jsp:include
							page="../product/productTree.jsp" /></DIV>

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
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
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
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<DIV id="adminDiv">
									<br/>
									<br/>
									<h:outputText value="No Question Available." styleClass="dataTableColumnHeader" />
							</DIV>

						<DIV id="benefitAdministrationDiv">

						<BR>
							<TABLE>
									<TR>
										<TD width="5%" height="25" align="right">
											<a href="#" onclick="viewQuestionnaire();return false;">
												<h:outputText value="[View Questionnaire]" styleClass="variableLink"/>
											</a>
										</TD>
									</TR>
								</TABLE>
							<TABLE border="0" cellspacing="0" cellpadding="3" width="98%"
								class="outputText">
								<TBODY>
									<TR>
										<td></td>
									</TR>
									<TR>
										<td valign="middle">
										<div id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Associated Questionnaire"></h:outputText> </B>
										</div>
										<div id="displayHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;width:100%"><h:panelGrid
											id="displayHeaderTable" width="100%"
											binding="#{productBenefitAdministrationBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										<div id="displayPanelContent12"
											style="position:relative;overflow:auto;height:250px">
										<h:panelGrid id="panelTable" width="100%"
											binding="#{productBenefitAdministrationBackingBean.questionarePanel}"
											>
										</h:panelGrid></div>
										</td>
									</TR>
									<TR>
										<td valign="middle">
										<div id="LabelTierHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Associated Tiered Questionnaire"></h:outputText> </B>
										</div>
										<div id="displayTierHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;width:100%"><h:panelGrid
											id="displayTierHeaderTable" width="100%"
											binding="#{productBenefitAdministrationBackingBean.tierHeaderPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										<div id="displayPanelContent12"
											style="position:relative;overflow:auto;height:250px">
										<h:panelGrid id="panelTierTable" width="100%"
											binding="#{productBenefitAdministrationBackingBean.tierQuestionarePanel}"
											>
										</h:panelGrid></div>
										</td>
									</TR>
	                                <TR></TR>
	                               	<TR>
										<TD width="110" height="1"><h:commandButton value="Save"
											styleClass="wpdButton" onmousedown="javascript:savePageAction(this.id);"
											action="#{productBenefitAdministrationBackingBean.saveBenefitAdministration}">
										</h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>
						</DIV>
						<!--	End of Page data	-->
						</fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="currentRootAnswer" ></h:inputHidden>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<!-- <h:inputHidden id="componentData"
					value="#{productNoteAssociationBackingBean.noteString}"></h:inputHidden> -->
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{productBenefitAdministrationBackingBean.deleteQuestion}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="selectedShowHiddenLink"
					style="display:none; visibility: hidden;"
					action="#{productBenefitAdministrationBackingBean.loadBenefitAdministrationValues}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="unselectedShowHiddenLink"
					style="display:none; visibility: hidden;"
					action="#{productBenefitAdministrationBackingBean.loadWithoutHiddenValues}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="newQuestionnreListLink"
							style="display:none; visibility: hidden;"
							action="#{productBenefitAdministrationBackingBean.selectNewQuestionnreList}">
							<f:verbatim />
					</h:commandLink>
				<h:commandLink id="newTierQuestionnreListLink"
							style="display:none; visibility: hidden;"
							action="#{productBenefitAdministrationBackingBean.selectNewTierQuestionnreList}">
							<f:verbatim />
					</h:commandLink>
				<h:inputHidden id="valForCheckBox" value="#{productBenefitAdministrationBackingBean.showHiddenSelected}" ></h:inputHidden>
				<h:inputHidden id="qstnStates" value="#{productBenefitAdministrationBackingBean.questionsStates}" ></h:inputHidden>
				<h:inputHidden id="tieredQstnStates" value="#{productBenefitAdministrationBackingBean.tieredQuestionsStates}" ></h:inputHidden>
				<h:inputHidden id="rowId"
								value="#{productBenefitAdministrationBackingBean.rowNum}"></h:inputHidden>
					<h:inputHidden id="answerId"
								value="#{productBenefitAdministrationBackingBean.answerId}"></h:inputHidden>
				<h:inputHidden id="answerDesc"
								value="#{productBenefitAdministrationBackingBean.answerDesc}"></h:inputHidden>
					<h:inputHidden id="tierSysId"
								value="#{productBenefitAdministrationBackingBean.panelTierSysId}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
											value="#{productBenefitAdministrationBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="tildaNoteStatusId"
						value="#{productBenefitAdministrationBackingBean.tildaNoteStatus}"></h:inputHidden>
				<h:inputHidden id="tildaTierNoteStatusId"
						value="#{productBenefitAdministrationBackingBean.tildaTierNoteStatus}"></h:inputHidden>


				<h:inputHidden id="adminLevelAssociationId"
						value="#{productBenefitAdministrationBackingBean.adminLevelAssociationId}"></h:inputHidden>

				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript"><!--
	IGNORED_FIELD1='benefitAdmnForm:duplicateData';
if(getObj('benefitAdmnForm:panelTable') != null && getObj('benefitAdmnForm:panelTable').rows.length > 0) {
	syncTables('headert','formName:panelTable');
	getObj('adminDiv').style.display = 'none';
} else {
	getObj('benefitAdministrationDiv').style.visibility = 'hidden';
}
	
if(getObj('benefitAdmnForm:panelTierTable') != null && getObj('benefitAdmnForm:panelTierTable').rows.length > 0) {
	syncTables('headert','formName:panelTierTable');
	getObj('adminDiv').style.display = 'none';
} else {
	getObj('LabelTierHeaderDiv').style.visibility = 'hidden';	
	getObj('displayTierHeaderDiv').style.visibility = 'hidden';
}
			
function getRow(row){
document.getElementById('benefitAdmnForm:rowId').value = row;
submitLink('benefitAdmnForm:deleteLink');
return true;
}

function deleteConfirm(){
			var message = 'Are you sure you want to detach the Question?';
				if (confirm(message) ){
					return true;
				} else
				return false;
			}

if(null!= document.getElementById('benefitAdmnForm:panelTable')){
var relTblWidth = document.getElementById('benefitAdmnForm:displayHeaderTable').offsetWidth;
if(document.getElementById('benefitAdmnForm:panelTable').offsetHeight <= 250)
{
	document.getElementById('benefitAdmnForm:panelTable').width = relTblWidth+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:20%:30%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','40%:20%:30%:10%');
}
else
{
	document.getElementById('benefitAdmnForm:panelTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:20%:30%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','40%:20%:30%:10%');
}}

if(null!= document.getElementById('benefitAdmnForm:panelTierTable')){
var relTblWidth = document.getElementById('benefitAdmnForm:displayTierHeaderTable').offsetWidth;
if(document.getElementById('benefitAdmnForm:panelTierTable').offsetHeight <= 250)
{
	document.getElementById('benefitAdmnForm:panelTierTable').width = relTblWidth+"px";
	document.getElementById('benefitAdmnForm:displayTierHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitAdmnForm:displayTierHeaderTable','40%:20%:30%:10%');
	setColumnWidth('benefitAdmnForm:panelTierTable','40%:20%:30%:10%');
}
else
{
	document.getElementById('benefitAdmnForm:panelTierTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitAdmnForm:displayTierHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitAdmnForm:displayTierHeaderTable','40%:20%:30%:10%');
	setColumnWidth('benefitAdmnForm:panelTierTable','40%:20%:30%:10%');
}}




function getLevelId(id)
{
	document.getElementById('benefitAdmnForm:selectedLevelId').value = id;
	return true;
}


function goToAction(){
	
	var select =  document.getElementById('benefitAdmnForm:showHidden')
	if(select.checked){
		document.getElementById('benefitAdmnForm:valForCheckBox').value = true ;
		//submitLink('benefitAdmnForm:selectedShowHiddenLink');
	}else{
		document.getElementById('benefitAdmnForm:valForCheckBox').value = false ;
		//submitLink('benefitAdmnForm:unselectedShowHiddenLink');
	}
	submitLink('benefitAdmnForm:selectedShowHiddenLink');	
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
			}else
            {
                  questionComponent.value=document.getElementById('benefitAdmnForm:currentRootAnswer').value;
				return false;
			}
		}else{
			submitLink('benefitAdmnForm:newQuestionnreListLink');
		} 
}

function setCurrentValue(select)
{
   currentRootAnswer = select.value;		
   var currentRootAnswerHidden = document.getElementById('benefitAdmnForm:currentRootAnswer');
   currentRootAnswerHidden.value=select.value;
}

function loadNewChildTier(questionComponent){

	var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';
		var questionComponentId = questionComponent.id;
		var questionComponentIdValue = questionComponent.value;	
		var questionComponentText = questionComponent.options[questionComponent.selectedIndex].text;
		var idArray = questionComponentId.split("selectitem");
		var tieridsplit =idArray[1].split("tier")
		var rowNo = tieridsplit[0];
		var tierid =tieridsplit[1];
		var childCount = document.getElementById('benefitAdmnForm:childCount'+rowNo+'tier'+tierid).value ;
		document.getElementById('benefitAdmnForm:rowId').value = rowNo;
		document.getElementById('benefitAdmnForm:answerId').value = questionComponentIdValue;
		document.getElementById('benefitAdmnForm:tierSysId').value =tierid;
		document.getElementById('benefitAdmnForm:answerDesc').value = questionComponentText;
		if(childCount>0){
			if (confirm(message) ){
				submitLink('benefitAdmnForm:newTierQuestionnreListLink');
				return true;
			}else{
                questionComponent.value=document.getElementById('benefitAdmnForm:currentRootAnswer').value;
				return false;
			}
		}else{
			submitLink('benefitAdmnForm:newTierQuestionnreListLink');
		} 


}
function viewQuestionnaire(){
			var retValue = window.showModalDialog('../product/viewProductQuestionnaire.jsp'+getUrl()+'?'+ 'temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');	
		}
IGNORED_FIELD2='benefitAdmnForm:tildaNoteStatusId';
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i){	

secondaryEntityType="ATTACHQUESTION";
		var retValue = window.showModalDialog(popupName +getUrl()+ "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
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

function loadTierNotes(popupName,tierSysId,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i)
{
secondaryEntityType="ATTACHQUESTION";
var retValue = window.showModalDialog(popupName +getUrl()+ "?"
												 	+ "&temp=" + Math.random()
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType+"&tierSysId="+tierSysId
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
oldStatusValue = document.getElementById('benefitAdmnForm:tildaTierNoteStatusId').value;
		var imageObj = document.getElementById('benefitAdmnForm:'+imageId+'tier'+tierSysId);
if(retValue == "notes_exists"){
		imageObj.src = "../../images/notes_exist.gif";
		document.getElementById('benefitAdmnForm:tildaTierNoteStatusId').value = oldStatusValue+'~'+tierSysId+i+'Y';
	}else if(retValue == "no_notes"){
		imageObj.src = "../../images/page.gif";
		document.getElementById('benefitAdmnForm:tildaTierNoteStatusId').value = oldStatusValue+'~'+tierSysId+i+'N';
	}
}
--></script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitAdmnForm:duplicateData').value == '')
		document.getElementById('benefitAdmnForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdmnForm:duplicateData').value;
</script>
</HTML>

