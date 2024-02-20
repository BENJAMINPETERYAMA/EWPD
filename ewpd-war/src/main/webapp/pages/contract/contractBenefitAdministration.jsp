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

<STYLE type="text/css">  
.gridColumn30{
	width: 30%;				
}
.gridColumn20{
	width: 20%;		
}
.gridColumn25{
	width: 25%;		
}
.gridColumn15{
	width: 15%;		
}
.gridColumn10{
	width: 10%;		
}
 </STYLE>

<TITLE>Contract Benefit Administration</TITLE>
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
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>

				<TD><h:form styleClass="form" id="benefitAdmnForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">


							<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
							</DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="600" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>

									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabActive"><h:outputText
												value="Benefit Administration" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	--> 
							<DIV id="adminDiv"><h:outputText
								value="No Benefit Administration Available."
								styleClass="dataTableColumnHeader" /></DIV>
							<DIV id="benefitAdministrationDiv">
							<TABLE border="0" cellspacing="0" cellpadding="3" width="100%"
								class="outputText">
								<TBODY><tr><td>
							<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">
                              <tr>
								<td>
								<TABLE border="0">
									<TR>
										<TD width="5%" height="20" align="right">
											<a id="viewQuestionnaireLink" href="#" onclick="viewQuestionnaire();return false;" onmousedown="javascript:navigatePageAction(this.id);">
												<h:outputText value="[View Questionnaire]" styleClass="variableLink"/>
											</a>
										</TD>
									</TR>
								</TABLE>
							   </td>
							  </TABLE>
							 </td>
							</tr>
									<TR><TD valign="middle">

										<DIV id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:15;width:100%;">
										<B>&nbsp;<h:outputText value="Associated Questionnaire"></h:outputText>
										</B></DIV>
										<DIV id="displayHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
											id="displayHeaderTable"
											binding="#{contractBenefitAdministrationBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></DIV>
										<DIV id="displayPanelContent12"
											style="position:relative;overflow:auto;height:250px"><h:panelGrid
											id="panelTable"
											binding="#{contractBenefitAdministrationBackingBean.questionnarePanel}">
										</h:panelGrid> <BR>

										</div></TD></TR>
									<tr>
									  <td valign="middle">
										<div id="LabelTierHeaderDiv" style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Associated Tiered Questionnaire"></h:outputText> </B>
										</div>
										<div id="displayTierHeaderDiv" style="background-color:#FFFFFF;z-index:1;overflow:auto;">
										 <h:panelGrid id="displayTierHeaderTable" binding="#{contractBenefitAdministrationBackingBean.tierHeaderPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										 </h:panelGrid>
										</div>
										<div id="displayTierPanelContent12"	style="position:relative;overflow:auto;height:250px;width:100%;">
											<h:panelGrid id="panelTierTable" binding="#{contractBenefitAdministrationBackingBean.tierQuestionarePanel}">
											</h:panelGrid>
										</div>
									  </td>
									</TR>									


									<TR>
										<TD width="110">&nbsp;
										<div id="saveButtonDisplay"><h:commandButton value="Save"
											styleClass="wpdButton"
											rendered="#{contractBenefitAdministrationBackingBean.contractType != 'MANDATE'}"
											onmousedown="javascript:savePageAction(this.id);"
											action="#{contractBenefitAdministrationBackingBean.saveBenefitAdministration}">
										</h:commandButton></div>
										</TD>
									</TR>

								</TBODY>
							</TABLE>



							</DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>
				<h:inputHidden id="currentRootAnswer" ></h:inputHidden>
					<h:inputHidden id="adminStates"
						value="#{contractBenefitAdministrationBackingBean.asnwerStates}" />
					<h:inputHidden id="duplicateData"
						value="#{contractBenefitAdministrationBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="rowId"
						value="#{contractBenefitAdministrationBackingBean.rowNum}"></h:inputHidden>
					<h:inputHidden id="answerId"
						value="#{contractBenefitAdministrationBackingBean.answerId}"></h:inputHidden>
					<!-- added on 12 jan 2011 -->
					<h:inputHidden id="answerDesc"
											value="#{contractBenefitAdministrationBackingBean.answerDesc}"></h:inputHidden>
					<h:inputHidden id="tildaNoteStatusId"
						value="#{contractBenefitAdministrationBackingBean.tildaNoteStatus}"></h:inputHidden>
					<h:inputHidden id="tierSysId"
						value="#{contractBenefitAdministrationBackingBean.panelTierSysId}"></h:inputHidden>
					<h:inputHidden id="tieredQstnStates" 
						value="#{contractBenefitAdministrationBackingBean.tieredQuestionsStates}" ></h:inputHidden>
					<h:inputHidden id="tildaTierNoteStatusId"
						value="#{contractBenefitAdministrationBackingBean.tildaTierNoteStatus}"></h:inputHidden>
					<h:commandLink id="newQuestionnreListLink"
						style="display:none; visibility: hidden;"
						action="#{contractBenefitAdministrationBackingBean.selectNewQuestionnreList}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="newTierQuestionnreListLink"
							style="display:none; visibility: hidden;"
							action="#{contractBenefitAdministrationBackingBean.selectNewTierQuestionnreList}">
							<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

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
IGNORED_FIELD1='benefitAdmnForm:duplicateData';
initialize();



function initialize(){
//sync header columns and data columns for questionaire panel		
if(null!= document.getElementById('benefitAdmnForm:panelTable')){

var relTblWidth = document.getElementById('benefitAdmnForm:displayHeaderTable').offsetWidth;
if(document.getElementById('benefitAdmnForm:panelTable').offsetHeight <= 250)
{
	document.getElementById('benefitAdmnForm:panelTable').width = relTblWidth+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
}
else
{
	document.getElementById('benefitAdmnForm:panelTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
}}
//sync header columns and data columns for tier panel
if(null!= document.getElementById('benefitAdmnForm:panelTierTable')){
var relTblWidth = document.getElementById('benefitAdmnForm:displayTierHeaderTable').offsetWidth;
if(document.getElementById('benefitAdmnForm:panelTierTable').offsetHeight <= 250)
{
	document.getElementById('benefitAdmnForm:panelTierTable').width = relTblWidth+"px";
	document.getElementById('benefitAdmnForm:displayTierHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
	setColumnWidth('benefitAdmnForm:panelTierTable','30%:20%:25%:15%:10%');
}
else
{
	document.getElementById('benefitAdmnForm:panelTierTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitAdmnForm:displayTierHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitAdmnForm:displayTierHeaderTable','30%:20%:25%:15%:10%');
	setColumnWidth('benefitAdmnForm:panelTierTable','30%:20%:25%:15%:10%');
}}
		}





		/*function initialize(){
			if(document.getElementById('benefitAdmnForm:panelTable') != null) {
		if(document.getElementById('benefitAdmnForm:panelTable').rows.length <= 12)
			{
				document.getElementById('benefitAdmnForm:displayHeaderTable').width = "100%";
				document.getElementById('benefitAdmnForm:panelTable').width = "100%";
				setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
				setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');

			}
		else
			{
				var relTblWidth = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
				document.getElementById('benefitAdmnForm:displayHeaderTable').width = "97.4%";
				document.getElementById('benefitAdmnForm:panelTable').width = "100%";
				setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
				setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
			}
	}

		}*/
	var tableObject = document.getElementById('benefitAdmnForm:panelTable');
		if(tableObject.rows.length > 0){
			var msgDivObj = document.getElementById('adminDiv');
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

		if(getObj('benefitAdmnForm:panelTierTable') != null && getObj('benefitAdmnForm:panelTierTable').rows.length > 0) {
			getObj('adminDiv').style.display = 'none';
		} else {
			getObj('LabelTierHeaderDiv').style.visibility = 'hidden';	
			getObj('displayTierHeaderDiv').style.visibility = 'hidden';
		}
 	function loadNewChild(questionComponent){
		var message = 'All the questions down the hierarchy will be cleared. Do you want to continue?';
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
			var retValue = window.showModalDialog('../contract/viewQuestionnaire.jsp'+ getUrl()+'?temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');	
		}
IGNORED_FIELD2='benefitAdmnForm:tildaNoteStatusId';		
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i){	

var secondaryEntityType="ATTACHQUESTION";

var retValue = window.showModalDialog(popupName + "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType, 
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
	
}
function loadTierNotes(popupName,tierSysId,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i)
{
secondaryEntityType="ATTACHQUESTION";
var retValue = window.showModalDialog(popupName + "?"
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
setHeightForDiv();
function setHeightForDiv(){
	var panelDiv = document.getElementById('displayPanelContent12');
	var tierPanelDiv = document.getElementById('displayTierPanelContent12');
	var tierPanel = document.getElementById('benefitAdmnForm:panelTierTable');
	var panelDivHeight;
	var tierPanelDivHeight;
	if(panelDiv != null){
		panelDivHeight = panelDiv.offsetHeight;
		if( panelDivHeight > 300){
			panelDiv.style.height = "300px";
		}
	}
	if(tierPanelDiv != null){
		if(null != tierPanel){
			var rowConut = tierPanel.rows.length; 
			if(rowConut == 0){
				document.getElementById('LabelTierHeaderDiv').style.height = "0px";
				document.getElementById('displayTierHeaderDiv').style.height = "0px";
				tierPanelDiv.style.height = "0px";
			}
		}
		tierPanelDivHeight = tierPanelDiv.offsetHeight;		
		if(tierPanelDivHeight > 300){
			tierPanelDiv.style.height = "300px";
		}
	}
	
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBenefitAdminProductStructure" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('benefitAdmnForm:duplicateData').value == '')
		document.getElementById('benefitAdmnForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdmnForm:duplicateData').value;
</script>
</HTML>
