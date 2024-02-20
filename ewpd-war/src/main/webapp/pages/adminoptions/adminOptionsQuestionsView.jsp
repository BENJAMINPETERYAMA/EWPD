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

<TITLE>Admin Options Questions View</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionViewForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{adminOptionQuestionsBackingBean.validationMessages}"></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">

								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												onmousedown="copyAdminOptionInfo();"
												action="#{createAdminOptionBackingBean.getViewAdminOptions}">
												<h:outputText value="Admin Options" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
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
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:420"><!--	Start of Table for actual Data	-->
								<h:inputHidden id="hiddenForretreive"
									value="#{adminOptionQuestionnaireBackingBean.questionnaire}"></h:inputHidden>

									<strong><h:outputText rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire == null}" 
										value="No Questionnaire Associated."></h:outputText></strong>
							<TABLE id="associatedQuestionsTable" width="100%" cellpadding="0"
								cellspacing="0">
								
								<TR>
									<TD colspan="2">
									<DIV id="associatedQuestionsDiv">
									<TABLE id="questionHeaderTable" cellspacing="1"
									bgcolor="#cccccc" cellpadding="3" border="0" width="100%">
									<TBODY>
										<TR>
											<TD  bgcolor="#cccccc" align="left" ><strong>&nbsp;Question [Reference]</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Parent Required</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Functional Domain</strong></TD>
											<TD  bgcolor="#cccccc" align="left" ><strong>Line of Business</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Business Entity </strong></TD>
											<TD  bgcolor="#cccccc" align="left" ><strong>Business Group</strong></TD>
											<TD  bgcolor="#cccccc" align="left" ><strong>Market Business Unit</strong></TD>
										</TR>
										
									</TBODY>
								</TABLE>

                                
								</DIV>
								</TD>
							</TR>

							<TR>
								<TD>
								<DIV id="searchResultdataTableDiv"
									style="overflow-x:hidden;overflow-y:auto;"><h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="associatedQuestionTable" var="eachRow" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									value="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire}"
									rowClasses="dataTableEvenRow,dataTableOddRow"
									rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire != null}">
									<h:column>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:inputHidden id="hiddenLevel"
											value="#{eachRow.level}"></h:inputHidden>
										<h:inputHidden id="hiddenQuestnrId"
											value="#{eachRow.questionnaireId}"></h:inputHidden>
										<h:outputText id="levelInd" 
											value="#{eachRow.levelIndicator}"></h:outputText>
										<h:outputText id="assAnswerDesc" 
											value="#{eachRow.answerDesc}"></h:outputText>
										<h:outputText id="assQuestionNumberDesc"
											value="#{eachRow.questionDesc}"></h:outputText>
										<h:outputText id="assReferenceDesc"
											value="#{eachRow.referenceDesc}"></h:outputText>
										<!-- Add the required hidden fields after this  -->
									</h:column>

									<h:column wrap>
										<h:selectBooleanCheckbox title="parentcheckbox" disabled="true"
										value="#{eachRow.parentRequiredChecked}"
											rendered="#{!eachRow.root}">
										</h:selectBooleanCheckbox>
									</h:column>

									<h:column wrap>
										<h:outputText title="functionaldomain"
											value="#{eachRow.functionalDomainDesc}" />
									</h:column>

									<h:column>
										<h:outputText id="lob"
											value="#{eachRow.lobString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="be"
											value="#{eachRow.beString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="bg"
											value="#{eachRow.bgString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="bu"
											value="#{eachRow.buString}"></h:outputText>
									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
							</TABLE>
							</FIELDSET>
							<br>

							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
							<table border="0" cellspacing="0" cellpadding="3" width="100%">
								<tr>
									<td width="4%"></td>
										<td align="left"></td>
										<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td width="4%"><h:outputText id="outTxtState" value="State" /></td>
											<td>:&nbsp;<h:outputText id="state"
												value="#{createAdminOptionBackingBean.state}" /></td>
										</tr>
										<tr>
											<td width="4%"><h:outputText id="outTxtStatus" value="Status" /></td>
											<td>:&nbsp;<h:outputText id="status"
												value="#{createAdminOptionBackingBean.status}" /></td>
										</tr>
										<tr>
											<td width="4%"><h:outputText id="outTxtVersion"
												value="Version" /></td>
											<td>:&nbsp;<h:outputText id="versionNo"
												value="#{createAdminOptionBackingBean.version}" /></td>
										</tr>
									</table>
									</td>
								</tr>

							</table>

							</fieldset>
							<br />

							</TD>
						</TR>
					</TABLE>

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

					<!-- End of hidden fields  -->

				</h:form>

			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>
var tableObjectQuest = document.getElementById('adminOptionViewForm:associatedQuestionTable');
if (tableObjectQuest != null) {

tablesizeQuest = tableObjectQuest.rows.length;
for(var i=0;i<tablesizeQuest;i++)
{
	var rowobjQuest = tableObjectQuest.rows[i];
	var cellobjQuest = tableObjectQuest.rows[i].cells[0];
	var textQuest = cellobjQuest.children[0].value;
	cellobjQuest.children[0]
if(textQuest == 1){
rowobjQuest.className = 'dataTableEvenRow';
}else{
rowobjQuest.className = 'dataTableOddRow';
}
cellobjQuest.children[3].className ='migrationWizardConflict';
}
}
if(document.getElementById('adminOptionViewForm:associatedQuestionTable') != null) {
var relTblWidth = document.getElementById('questionHeaderTable').offsetWidth;
if(document.getElementById('adminOptionViewForm:associatedQuestionTable').offsetHeight <= 240)
	{
		document.getElementById('adminOptionViewForm:associatedQuestionTable').width = relTblWidth+"px";
		document.getElementById('questionHeaderTable').width = relTblWidth+"px";
		setColumnWidth('questionHeaderTable','35%:6%:23%:9%:9%:9%:9%');
		setColumnWidth('adminOptionViewForm:associatedQuestionTable','35%:6%:23%:9%:9%:9%:9%');
		syncTables('questionHeaderTable','adminOptionViewForm:associatedQuestionTable');
	}
	else
	{
		//setColumnWidth('questionHeaderTable','45%:6%:20%:8%:8%:8%:5%');
		setColumnWidth('questionHeaderTable','35%:6%:23%:9%:9%:9%:9%');
		setColumnWidth('adminOptionViewForm:associatedQuestionTable','35%:6%:23%:9%:9%:9%:9%');
		syncTables('questionHeaderTable','adminOptionViewForm:associatedQuestionTable');
	}
}
	if(document.getElementById('adminOptionViewForm:associatedQuestionTable') != null) {	
	//	tigra_tables('adminOptionViewForm:associatedQuestionTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	//	setColumnWidth('adminOptionViewForm:associatedQuestionTable','35%:40%:25%');
	}
	else{
		var divObj = document.getElementById('associatedQuestionsDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}
	function getValuesForDelete() {
	   	var e = window.event;
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var questionNumber = "adminOptionViewForm:associatedQuestionTable:"+rowcount+":assQuestionNumber";
		var questionNumberValue = document.getElementById(questionNumber).value;
		document.getElementById('adminOptionViewForm:hiddenQuestionNumberForDelete').value = questionNumberValue;
		document.getElementById('adminOptionViewForm:deleteQuestion').click();
		return false;
	}

	function getAdminOptionQuestionSequence(){
		var tableObj = document.getElementById('adminOptionViewForm:associatedQuestionTable');
		var sequenceValues = '';
		var changedSeq, initialSeq;
		var length = tableObj.rows.length;
		for(var i = 0; i<length; i++) {
				if(sequenceValues != '') {
					sequenceValues += '~';
				}
				sequenceValues += tableObj.rows[i].cells[1].children[0].value;
				sequenceValues += '~';
				sequenceValues += tableObj.rows[i].cells[0].children[0].value;
	}
	document.getElementById('adminOptionViewForm:selectedsequences').value = sequenceValues;
	if(sequenceValues == '')
		return false;
	else
		return true;
}


	function copyAdminOptionInfo(){
		document.getElementById('adminOptionViewForm:adminIdToNextPage').value = document.getElementById('adminOptionViewForm:hiddenAdminOptionId').value;
		document.getElementById('adminOptionViewForm:adminNameToNextPage').value = document.getElementById('adminOptionViewForm:adminName').value;
		document.getElementById('adminOptionViewForm:adminVersionToNextPage').value = document.getElementById('adminOptionViewForm:version').value;
	
	}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptionQuestion" /></form>

</HTML>

