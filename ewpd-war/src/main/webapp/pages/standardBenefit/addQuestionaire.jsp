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

<TITLE>Add Questionnaire</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="addQuestioniareForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><jsp:include
								page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>

							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message> <!-- Insert WPD Message Tag --></TD>
									</TR>
								</TBODY>
							</TABLE>
							<h:inputHidden id="benefitMode"
								binding="#{addQuestionaireBackingBean.benefitMode}"></h:inputHidden>
							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Questionnaire" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>

									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->


							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	--> <BR />
							<div id="messageTextForNoQuestionnaireDiv"><br />
							<br />
							<STRONG>&nbsp;<h:outputText value="No Questionnaire available." /></STRONG>
							</div>
							<div id="quesitonTableDiv">
							<TABLE width="100%" cellpadding="1" border="0" id="tabheader"
								class="smallfont">
								<td>
								<TABLE>
									<TR>
										<TD width="5%" height="25" align="right">
											<a href="#" onclick="viewQuestionnaire();return false;">
												<h:outputText value="[View Questionnaire]" styleClass="variableLink"/>
											</a>
										</TD>
									</TR>
								</TABLE>

								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" border="0">
									<TR bgcolor="#cccccc" height="18">
										<TD><strong>Associated Questionnaire</strong></TD>
									</TR>
								</table>
								<TR>
									<TD>
									<DIV id="displayPanelContent12"
										style="position:relative;overflow:auto;width:100%"><h:panelGrid
										id="quesHeadTable"
										binding="#{addQuestionaireBackingBean.headerPanel}"
										rowClasses="dataTableOddRow">
									</h:panelGrid></DIV>
									<DIV id="displayPanelContent12"
										style="position:relative;overflow:auto;height:290;width:100%">
									<h:panelGrid id="quesTable"
										binding="#{addQuestionaireBackingBean.panel}">
									</h:panelGrid>
									</TD>

								</TR>

							</TABLE>
							</div>
							<br />
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="tabTable">
								<TR>
									<TD width="30" align="left"><h:commandButton value="Save"
										styleClass="wpdButton" id="saveButton"
										onmousedown="javascript:savePageAction(this.id);"
										action="#{addQuestionaireBackingBean.saveQuestionnaire}">
									</h:commandButton></TD>
									<TD width="229">&nbsp;</TD>
								</TR>
							</TABLE>
							<br />

							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>


					</TABLE>
					<h:inputHidden id="duplicateData"
						value="#{addQuestionaireBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="rowId"
						value="#{addQuestionaireBackingBean.rowNum}"></h:inputHidden>
					<h:inputHidden id="answerId"
						value="#{addQuestionaireBackingBean.answerId}"></h:inputHidden>
					<h:inputHidden id="answerDesc"
						value="#{addQuestionaireBackingBean.answerDesc}"></h:inputHidden>
					<h:inputHidden id="oldAnswerValue"></h:inputHidden>
						
					<h:commandLink id="newQuestionnaireListLink"
						style="display:none; visibility: hidden;"
						action="#{addQuestionaireBackingBean.selectChildQuestionnaireList}">
					<h:inputHidden id="noteId"
						value="#{addQuestionaireBackingBean.noteId}"></h:inputHidden>
					<h:inputHidden id="tildaNoteStatusId"
						value="#{addQuestionaireBackingBean.tildaNoteStatus}"></h:inputHidden>
						<f:verbatim />
					</h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="javascript">
	IGNORED_FIELD1='addQuestioniareForm:duplicateData';
		test();
		var tableObject = document.getElementById('addQuestioniareForm:quesTable');
		if(tableObject.rows.length > 0){
			var msgDivObj = document.getElementById('messageTextForNoQuestionnaireDiv');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
		}else{
			var divObj = document.getElementById('quesitonTableDiv');
			divObj.style.visibility = "hidden";
			divObj.style.height = "2px";
			document.getElementById('addQuestioniareForm:saveButton').style.visibility = 'hidden';
		}
		if(tableObject != null) {
			var tblWidth = document.getElementById('addQuestioniareForm:quesTable').offsetWidth;
			var rowlength = document.getElementById('addQuestioniareForm:quesTable').rows.length;
			if(rowlength>1){
				document.getElementById('addQuestioniareForm:quesHeadTable').width = tblWidth+"px";
				syncTables('addQuestioniareForm:quesHeadTable','addQuestionForm:quesTable');	
				setColumnWidth('addQuestioniareForm:quesTable','40%:20%:30%:10%');
				setColumnWidth('addQuestioniareForm:quesHeadTable','40%:20%:30%:10%');
			}else{
				setColumnWidth('addQuestioniareForm:quesHeadTable','40%:20%:30%:10%');	
				setColumnWidth('addQuestioniareForm:quesTable','40%:20%:30%:10%');	
			}	
		}

		function test() {
			if(document.getElementById('addQuestioniareForm:benefitMode').value == 'View') {
				document.getElementById('addQuestioniareForm:saveButton').style.visibility = 'hidden';
			}
		}
 		function loadNewChild(answerComponent){
			var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';

			var answerComponentId = answerComponent.id;
			var answerComponentIdValue = answerComponent.value;	
			var answerComponentText = answerComponent.options[answerComponent.selectedIndex].text;

			var idArray = answerComponentId.split("selectitem");
			var rowNo = idArray[1];			
			document.getElementById('addQuestioniareForm:rowId').value = rowNo;
			document.getElementById('addQuestioniareForm:answerId').value = answerComponentIdValue;
 			document.getElementById('addQuestioniareForm:answerDesc').value = answerComponentText;

			var childCount = document.getElementById('addQuestioniareForm:childCount'+rowNo).value ;
			if(childCount>0){
				if (confirm(message) ){
					submitLink('addQuestioniareForm:newQuestionnaireListLink');
					return true;
				}else{
					answerComponent.value = document.getElementById('addQuestioniareForm:oldAnswerValue').value;
					return false;
				}
			}else{
				submitLink('addQuestioniareForm:newQuestionnaireListLink');
			}

		}
		function viewQuestionnaire(){
			var retValue = window.showModalDialog('../standardBenefit/viewQuestionnaire.jsp'+getUrl()+'?'+ 'temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');	
		}
		function storeOldValue(selectObj){
			var oldValue = selectObj.value;
			document.getElementById('addQuestioniareForm:oldAnswerValue').value = oldValue;
		}
IGNORED_FIELD2='addQuestioniareForm:tildaNoteStatusId';
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i){
var secondaryEntityType="ATTACHQUESTION";
		var retValue = window.showModalDialog(popupName + "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType , 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
oldStatusValue = document.getElementById('addQuestioniareForm:tildaNoteStatusId').value;
		var imageObj = document.getElementById('addQuestioniareForm:'+imageId);
if(retValue == "notes_exists"){
		imageObj.src = "../../images/notes_exist.gif";
	
	document.getElementById('addQuestioniareForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'Y';
	}else if(retValue == "no_notes"){
		imageObj.src = "../../images/page.gif";
	document.getElementById('addQuestioniareForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'N';
	}
	//document.getElementById('addQuestioniareForm:hiddenNotesStatus' + imageId).value = retValue;
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="addQuestion" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('addQuestioniareForm:duplicateData').value == '')
		document.getElementById('addQuestioniareForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('addQuestioniareForm:duplicateData').value;
</script>
</HTML>
