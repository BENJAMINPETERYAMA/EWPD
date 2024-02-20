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

<TITLE>Associated Questions for Admin Options</TITLE>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>

</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="adminOptionQuestionForm">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>


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
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink id="adminOptionPage"
											onmousedown="javascript:navigatePageAction(this.id);"
											action="#{createAdminOptionBackingBean.displayAdminOptionTab}">
											<h:outputText value="Admin Options" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="Questionnaire" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<BR />
						<!--	Start of Table for actual Data	-->

						<DIV id="associatedQuestionsDiv">
						<TABLE id="associatedQuestionsTable" width="100%" cellpadding="0"
							cellspacing="0">
							<TR>
								<TD>
								<DIV id="hyper">
								<table>
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
											id="addRootQstn" href="#"
											onclick="openAddRootQuestion();return false;" onmousedown="javascript:navigatePageAction(this.id);"><h:outputText
											value="[Add Root Question]" styleClass="variableLink" /></a>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
											id="editRootQstn" href="#"
											onclick="openEditRootQuestion();return false;" onmousedown="javascript:navigatePageAction(this.id);"><h:outputText
											value="[Edit Root Question]" id="editLink"
											styleClass="variableLink" /></a></td>
									</tr>
									<tr>
										<td>&nbsp;<h:inputHidden id="hiddenForretreive"
											value="#{adminOptionQuestionnaireBackingBean.questionnaire}"></h:inputHidden></td>
									</tr>
								</table>
								</DIV>
								<DIV id="resultHeaderDiv">
								<TABLE id="questionHeaderTable" cellspacing="1"
									bgcolor="#cccccc" cellpadding="3" border="0" width="100%">
									<TBODY>
										<TR>
											<TD style="align:left;">
												<strong>&nbsp;Question [Reference]</strong>
											</TD>
											<TD style="align:left;">
												<strong>&nbsp;Parent Required</strong>
											</TD>
											<TD style="align:left;">
												<strong>&nbsp;Functional Domain</strong>
											</TD>
											<TD style="align:left;">
												<strong>Line of Business</strong>
											</TD>
											<TD style="align:left;">
												<strong>Business Entity </strong>
											</TD>
											<TD style="align:left;">
												<strong>Business Group</strong>
											</TD>
											<TD style="align:left;%"><strong>Market Business Unit</strong></TD>
											<TD style="align:left;"><strong>&nbsp;</strong></TD>
										</TR>
									</TBODY>
								</TABLE>


								</DIV>
								</TD>
							</TR>

							<TR>
								<TD>
								<DIV id="searchResultdataTableDiv"
									style="height:160px; overflow-x:hidden;overflow-y:auto;"><h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="associatedQuestionTable" var="eachRow" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									value="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire}"
									rowClasses="dataTableEvenRow,dataTableOddRow"
									rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire != null}">
									<h:column wrap>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:inputHidden id="hiddenLevel" value="#{eachRow.level}"></h:inputHidden>
										<h:inputHidden id="hiddenQuestnrId"
											value="#{eachRow.questionnaireId}"></h:inputHidden>
										<h:inputHidden id="hiddenParentQuestnrId"
											value="#{eachRow.parentQuestionnaireId}"></h:inputHidden>
										<h:commandButton alt="Collapse" title="Collapse" id="treeButton"
											image="../../images/lm.gif" value="Collapse"
											onclick="javascript:hideChildren();return false;">
										</h:commandButton>
										<h:outputText id="levelInd" value="#{eachRow.levelIndicator}"></h:outputText>
										<h:outputText id="assAnswerDesc" value="#{eachRow.answerDesc}">
										</h:outputText>
										<h:outputText id="space"
											rendered="#{eachRow.answerDesc != null}">
											<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										</h:outputText>
										<h:outputText id="assQuestionNumberDesc"
											value="#{eachRow.questionDesc}"></h:outputText>
										<h:outputText id="assReferenceDesc"
											value="#{eachRow.referenceDesc}"></h:outputText>
										<!-- Add the required hidden fields after this  -->

									</h:column>

									<h:column wrap>
										<h:selectBooleanCheckbox title="parentcheckbox"
											value="#{eachRow.parentRequiredChecked}"
											rendered="#{!eachRow.root}">
										</h:selectBooleanCheckbox>

									</h:column>

									<h:column wrap>
										<h:outputText title="functionaldomain"
											value="#{eachRow.functionalDomainDesc}" />
									</h:column>

									<h:column wrap>
										<h:outputText id="lob" value="#{eachRow.lobString}"></h:outputText>
									</h:column>
									<h:column wrap>
										<h:outputText id="be" value="#{eachRow.beString}"></h:outputText>
									</h:column>
									<h:column wrap>
										<h:outputText id="bg" value="#{eachRow.bgString}"></h:outputText>
									</h:column>
									<h:column wrap>
										<h:outputText id="bu" value="#{eachRow.buString}"></h:outputText>
									</h:column>
									<h:column>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Edit" title="Edit" id="basicEdit"
											image="../../images/edit.gif" value="Edit"
											onclick="javascript:editQuestionnaire();return false;"
											onmousedown="javascript:navigatePageAction(this.id);">
										</h:commandButton>
										<f:verbatim>&nbsp;</f:verbatim>
										<h:commandButton alt="Delete" title="Delete" id="basicDelete"
											onclick="deleteQuestion();return false;"
											image="../../images/delete.gif" value="Delete"
											onmousedown="javascript:navigatePageAction(this.id);">
										</h:commandButton>
									</h:column>

								</h:dataTable></DIV>
								</TD>
							</TR>
							<h:inputHidden id="hiddenParentRequiredStatus"
								value="#{adminOptionQuestionnaireBackingBean.parentRequiredString}"></h:inputHidden>
						</TABLE>
						<br>
						<br>
						<TABLE>
							<TR>
								<TD>&nbsp;</TD>
								<TD><h:commandButton id="saveButton" styleClass="wpdButton"
									value="Save" tabindex="1"
									onmousedown="javascript:savePageAction(this.id);"
									onclick="saveParentReqdString();"
									action="#{adminOptionQuestionnaireBackingBean.saveParentReqdStatus}">
								</h:commandButton></TD>
							</TR>
						</TABLE>
						</DIV>
						<BR>
						<BR>
						</fieldset>
						<BR>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
							<TR>
								<TD width="4%"><h:selectBooleanCheckbox
									value="#{adminOptionQuestionnaireBackingBean.checkInOpted}"
									id="checkIn" tabindex="4" /></TD>
								<TD align="left" width="69%"><h:outputText id="outTxtCheckIn"
									value="Check In" /></TD>
								<TD align="left" width="27%">
								<TABLE width="100%">
									<TR>
										<TD width="4%"><h:outputText id="outTxtState" value="State" /></TD>
										<TD>:&nbsp;<h:outputText id="state"
											value="#{adminOptionQuestionnaireBackingBean.state}" /></TD>
									</TR>
									<TR>
										<TD><h:outputText id="outTxtStatus" value="Status" /></TD>
										<TD>:&nbsp;<h:outputText id="status"
											value="#{adminOptionQuestionnaireBackingBean.status}" /></TD>
									</TR>
									<TR>
										<TD><h:outputText id="outTxtVersion" value="Version" /></TD>
										<TD>:&nbsp;<h:outputText id="versionNo"
											value="#{adminOptionQuestionnaireBackingBean.version}" /></TD>
									</TR>
								</TABLE>
								</TD>
							</TR>
						</TABLE>
						</fieldset>
						<br />
						<BR>
						<TABLE>
							<TR>
								<TD>&nbsp;<h:commandButton value="Done" styleClass="wpdButton"
									tabindex="5"
									action="#{adminOptionQuestionnaireBackingBean.checkInAdminOption}">
								</h:commandButton></TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->



				<h:inputHidden id="hiddenAdminOptionId"
					value="#{adminOptionQuestionnaireBackingBean.adminId}"></h:inputHidden>
				<h:inputHidden id="adminName"
					value="#{adminOptionQuestionnaireBackingBean.adminName}"></h:inputHidden>

				<h:inputHidden id="version"
					value="#{adminOptionQuestionnaireBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="hiddenStatus"
					value="#{adminOptionQuestionnaireBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="hiddenState"
					value="#{adminOptionQuestionnaireBackingBean.state}"></h:inputHidden>

				<h:inputHidden id="adminIdToNextPage"
					value="#{createAdminOptionBackingBean.adminOptionId}"></h:inputHidden>
				<h:inputHidden id="adminNameToNextPage"
					value="#{createAdminOptionBackingBean.adminName}"></h:inputHidden>
				<h:inputHidden id="adminVersionToNextPage"
					value="#{createAdminOptionBackingBean.version}"></h:inputHidden>

				<h:inputHidden id="selectedQuestionnaireId" ></h:inputHidden>

				<h:inputHidden id="selectedQuestnrId"
					value="#{adminOptionQuestionnaireBackingBean.selectedQuestnrId}"></h:inputHidden>
				<h:inputHidden id="selectedParentQuestnrId"
					value="#{adminOptionQuestionnaireBackingBean.selectedParentQuestnrId}"></h:inputHidden>
				<h:inputHidden id="quesReferenceHidden"
					value="#{rootQuestionsBackingBean.reference}" />
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{adminOptionQuestionnaireBackingBean.deleteQuestion}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="refreshLink"
					style="display:none; visibility: hidden;"
					action="#{adminOptionQuestionnaireBackingBean.refresh}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="addRootQuestion" style="hidden"
					action="#{rootQuestionsBackingBean.addRootQuestions}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="closeEditQuestionnaireLink"
					style="display:none; visibility: hidden;"
					action="#{editQuestionnaireBackingBean.close}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
	
hideIfNoValue('resultHeaderDiv');

showSaveButton();

function showSaveButton(){
	var tableObject =document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
	if(tableObject == null){
	document.getElementById('adminOptionQuestionForm:saveButton').style.visibility = 'hidden';
	}	
	else if(tableObject != null && tableObject.rows.length>0){
		document.getElementById('adminOptionQuestionForm:saveButton').style.visibility = 'hidden';	
		for (var i=0;i<tableObject.rows.length;i++){
			var cur_row = tableObject.rows[i];
			if(cur_row.cells[0].children[2].value!= cur_row.cells[0].children[1].value){	
				document.getElementById('adminOptionQuestionForm:saveButton').style.visibility = 'visible';
			}
		}
	}
}
   
function hideIfNoValue(divId){
	var tableObj = document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
	if (tableObj == null) {
		document.getElementById(divId).style.visibility = 'hidden';
	}else{
		document.getElementById(divId).style.visibility = 'visible';
	}
}

function deleteQuestion(){
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
			getFromDataTableToHidden('adminOptionQuestionForm:associatedQuestionTable','hiddenQuestnrId','adminOptionQuestionForm:selectedQuestnrId');
			getFromDataTableToHidden('adminOptionQuestionForm:associatedQuestionTable','hiddenParentQuestnrId','adminOptionQuestionForm:selectedParentQuestnrId');
			submitLink('adminOptionQuestionForm:deleteLink');
		}
		else{
				return false;
		}
}
var relTblWidth = document.getElementById('questionHeaderTable').offsetWidth;
var tableObj = document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
if (tableObj != null) {
	if(document.getElementById('adminOptionQuestionForm:associatedQuestionTable').offsetHeight <= 160)
	{
		//document.getElementById('adminOptionQuestionForm:associatedQuestionTable').width = relTblWidth+"px";
		//document.getElementById('questionHeaderTable').width = relTblWidth+"px";
		setColumnWidth('questionHeaderTable','34%:6%:22%:8%:8%:8%:8%:6%');
		setColumnWidth('adminOptionQuestionForm:associatedQuestionTable','34%:6%:22%:8%:8%:8%:8%:6%');
		//setColumnWidth('adminOptionQuestionForm:associatedQuestionTable','36%:6%:30%:6%:6%:6%:6%:4%');
		syncTables('questionHeaderTable','adminOptionQuestionForm:associatedQuestionTable');
	}
	else
	{
		setColumnWidth('questionHeaderTable','34%:6%:22%:8%:8%:8%:8%:6%');
		setColumnWidth('adminOptionQuestionForm:associatedQuestionTable','34%:6%:22%:8%:8%:8%:8%:6%');
		syncTables('questionHeaderTable','adminOptionQuestionForm:associatedQuestionTable');
	}
}
	if(document.getElementById('adminOptionQuestionForm:associatedQuestionTable') != null) {	

	}
	else{
		var divObj = document.getElementById('searchResultdataTableDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}
		



	function copyAdminOptionInfo(){
		document.getElementById('adminOptionQuestionForm:adminIdToNextPage').value = document.getElementById('adminOptionQuestionForm:hiddenAdminOptionId').value;
		document.getElementById('adminOptionQuestionForm:adminNameToNextPage').value = document.getElementById('adminOptionQuestionForm:adminName').value;
		document.getElementById('adminOptionQuestionForm:adminVersionToNextPage').value = document.getElementById('adminOptionQuestionForm:version').value;
	
	}
var tableObjectQuest = document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
if (tableObjectQuest != null) {

tablesizeQuest = tableObjectQuest.rows.length;
for(var i=0;i<tablesizeQuest;i++)
{
	var rowobjQuest = tableObjectQuest.rows[i];
	var cellobjQuest = tableObjectQuest.rows[i].cells[0];
	var textQuest = cellobjQuest.children[0].value;
if(textQuest == 1){
rowobjQuest.className = 'dataTableEvenRow';
}else{
rowobjQuest.className = 'dataTableOddRow';
}
// for tree
if(null != tableObjectQuest.rows[i+1]){
	textQuestNext =tableObjectQuest.rows[i+1].cells[0].children[0].value;
	if(textQuest>= textQuestNext){
	var treeButton = tableObjectQuest.rows[i].cells[0].children[3];
	treeButton.style.visibility ='hidden';
	}
}else{
	var treeButton = tableObjectQuest.rows[i].cells[0].children[3];
	treeButton.style.visibility ='hidden';
}
// Answer Red color
 cellobjQuest.children[5].className ='migrationWizardConflict';
}
}


function hideChildren(){
getFromDataTableToHidden('adminOptionQuestionForm:associatedQuestionTable','hiddenQuestnrId','adminOptionQuestionForm:selectedQuestnrId');
var tableObjectQuest = document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
if (tableObjectQuest != null) {
	tablesizeQuest = tableObjectQuest.rows.length;
	var initLevel = 9999;
	var treeState ='';
	var preState  ='';
	
	for(var i=0;i<tablesizeQuest;i++)
	{
		
	if(tableObjectQuest.rows[i].cells[0].children[1].value == document.getElementById('adminOptionQuestionForm:selectedQuestnrId').value){
			initLevel = tableObjectQuest.rows[i].cells[0].children[0].value;
			var src = tableObjectQuest.rows[i].cells[0].children[3].src;
			var array = src.split('/');
			var length = array.length;
			var imageFile = array[(parseInt(length) - 1)];
			treeState = (imageFile == "lp.gif")? 'open': 'close';
			if(treeState == 'close')
				tableObjectQuest.rows[i].cells[0].children[3].src = "../../images/lp.gif";
			else if(treeState == 'open')
				tableObjectQuest.rows[i].cells[0].children[3].src = "../../images/lm.gif";
		}
		else if(initLevel < tableObjectQuest.rows[i].cells[0].children[0].value){
			 // tableObjectQuest.rows[i].cells[0].children[3].src = "../../images/lm.gif";
			if(treeState == 'close'){
				tableObjectQuest.rows[i].style.display ='none';
			}else if(treeState == 'open'){
				if(tableObjectQuest.rows[i].cells[0].children[0].value - initLevel == 1){
					tableObjectQuest.rows[i].style.display ='block';
					if(tableObjectQuest.rows[i].cells[0].children[3].style.visibility != 'hidden'){
						tableObjectQuest.rows[i].cells[0].children[3].src = "../../images/lp.gif";
					}
				}
			}
		}
		else{
			initLevel = 9999;
			treeState ='';
		}			
		
	}

syncTables('questionHeaderTable','adminOptionQuestionForm:associatedQuestionTable');
syncTables('questionHeaderTable','adminOptionQuestionForm:associatedQuestionTable');
}
}



	// Method to open the edit questionnaire PopUp.
	/* function editQuestionnaire(){
 		getFromDataTableToHidden('adminOptionQuestionForm:associatedQuestionTable','hiddenQuestnrId','adminOptionQuestionForm:selectedQuestionnaireId');
		window.showModalDialog('../questionnaire/helperPopUp.jsp'+getUrl()+'?adminOptionName='+escape(document.getElementById('adminOptionQuestionForm:adminNameToNextPage').value)+'&adminOptionVersion='+document.getElementById('adminOptionQuestionForm:adminVersionToNextPage').value+'&parentQuestionnaireHierarchyId='+document.getElementById('adminOptionQuestionForm:selectedQuestionnaireId').value + '&temp=' + Math.random(),'EditQuestionnaire','dialogHeight:600px;dialogLeft:20px;dialogTop:60px;dialogWidth:1000px;scrollbars=false;resizable=false;status=no;');
		//submitLink('adminOptionQuestionForm:refreshLink');
		submitLink('adminOptionQuestionForm:closeEditQuestionnaireLink');
	} */

/* function openAddRootQuestion(){
	var adminId = document.getElementById('adminOptionQuestionForm:hiddenAdminOptionId').value;
	var adminName = document.getElementById('adminOptionQuestionForm:adminName').value;
	var adminVersion = document.getElementById('adminOptionQuestionForm:version').value;
	var retValue = window.showModalDialog('../questionnaire/helperPopUpForRoot.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'adminOptionId='+adminId+'&&'+'adminOptionName='+escape(adminName)+'&&'+'adminOptionVersion='+adminVersion+'&&submitPage=addRoot','','dialogHeight:600px;dialogLeft:100px;dialogTop:100px;dialogWidth:800px;scrollbars=false;resizable=false;status=no;');	
	if(retValue != undefined){
		//document.getElementById('adminOptionQuestionForm:quesReferenceHidden').value = retValue;
		submitLink('adminOptionQuestionForm:addRootQuestion');
	}
}
function openEditRootQuestion(){
	var adminId = document.getElementById('adminOptionQuestionForm:hiddenAdminOptionId').value;
	var adminName = document.getElementById('adminOptionQuestionForm:adminName').value;
	var adminVersion = document.getElementById('adminOptionQuestionForm:version').value;
	var retValue = window.showModalDialog('../questionnaire/helperPopUpForRoot.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'adminOptionId='+adminId+'&&'+'adminOptionName='+escape(adminName)+'&&'+'adminOptionVersion='+adminVersion+'&&submitPage=editRoot','','dialogHeight:580px;dialogWidth:930px;resizable=false;status=no;');	
	if(retValue == undefined){
		submitLink('adminOptionQuestionForm:refreshLink');
	}
} */

processEditLink();
function processEditLink(){
	var tableObj = document.getElementById('adminOptionQuestionForm:associatedQuestionTable');
		if (tableObj == null) {
			document.getElementById('adminOptionQuestionForm:editLink').style.display = 'none';
		}
}

function saveParentReqdString(){
var message='';
var parentString='';
var value ='';
var tableObject =document.getElementById('adminOptionQuestionForm:associatedQuestionTable');

for (var i=0;i<tableObject.rows.length;i++){
	var cur_row = tableObject.rows[i];
	
		if(cur_row.cells[0].children[2].value!= cur_row.cells[0].children[1].value){			
			if(cur_row.cells[1].children[0].checked) {
				value = cur_row.cells[0].children[1].value;
				message='Y~'+value;				
			}else{
				value = cur_row.cells[0].children[1].value;
				message='N~'+value;				
			}
			
			parentString=parentString+message+',';			
	    }
}
document.getElementById("adminOptionQuestionForm:hiddenParentRequiredStatus").value = parentString;
}	

</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptionQuestion" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>

