<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">

<TITLE>Add Question Popup</TITLE>
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
	<h:form id="addQuestionForm">
		<h:inputHidden value="#{AddQuestionBackingBean.openQuestionRecords}"></h:inputHidden>
			<Div id="saveButtonDiv">
			<table border="0" cellpadding="5" cellspacing="0" width="100%">
			
			<tr>
				<td align="left"><h:commandButton id="saveButton" value="Save"
					styleClass="wpdbutton"
					onclick="getCheckedItemsForQuestions('addQuestionForm:openQuestionDataTable','addQuestionForm:hideQuestionDataTable');return false;"></h:commandButton>
				</td>
			</tr>
		</table></Div>
		<table width="99%" align="right" cellpadding="0" cellspacing="0">
		<tr><td>
		<DIV id="noOpenQuestionDiv" style="height:0px; overflow:auto;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						>
						<TR>
							<TD width="70%" ><strong><h:outputText
								value="No Open Questions Available"></h:outputText></strong></TD>
						</TR>
					</table>
					</DIV>
		</td></tr>
		<tr id="openQuestionTab" style="display:none;">
		<td>
		<table width="100%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<DIV id="openQuestionHeader" style="height:0px; overflow:auto;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<TR>
							<TD width="6%" align="left"><h:selectBooleanCheckbox
								onclick="checkAll_ewpd(this,'addQuestionForm:openQuestionDataTable'); ">
							</h:selectBooleanCheckbox></TD>
							<TD width="70%" align="center"><strong><h:outputText
								value="Open Questions "></h:outputText></strong></TD>
						</TR>
					</table>
					</DIV>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="openDataTableDiv" style="height:124px; overflow:auto;" class="popupDataTableDiv"
						><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						width="100%" id="openQuestionDataTable"
						value="#{AddQuestionBackingBean.openQuestionList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<h:selectBooleanCheckbox id="openQuestionChkBox">
								</h:selectBooleanCheckbox>
							</f:verbatim>
						</h:column>
						<h:column>
							<h:inputHidden value="#{eachRow.questionDesc}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.questionNumber}"></h:inputHidden>
                             	<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.questionDesc}"></h:outputText>
							<h:inputHidden value="#{eachRow.sequenceNumber}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.answerId}"></h:inputHidden>
						</h:column>
					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
		</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>
		<DIV id="noDomainQuestionDiv" style="height:0px; overflow:auto;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						>
						<TR>
							<TD width="70%" ><strong><h:outputText
								value="No Domained Questions Available"></h:outputText></strong></TD>
						</TR>
					</table>
					</DIV>
		</td></tr>
<tr id="domainedQuestionTab" style="display:none;">
<td>
		<table width="100%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
						<DIV id="domainQuestionHeader"  style="height:0px; overflow:auto;" class=popupDataTableDiv>
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							bgcolor="#cccccc">
							<TR>
								<TD width="6%" align="left"><h:selectBooleanCheckbox
									onclick="checkAll_ewpd(this,'addQuestionForm:hideQuestionDataTable'); ">
								</h:selectBooleanCheckbox></TD>
								<TD width="70%" align="center"><strong><h:outputText
									value="Domained Questions "></h:outputText></strong></TD>
							</TR>
						</table>
					</DIV></TD>
				</TR>
				<tr>
					<td>
					<DIV id="domainedQuestionsDiv" style="height:124px;overflow:auto;" class=popupDataTableDiv
						><h:dataTable
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						width="100%" id="hideQuestionDataTable"
						value="#{AddQuestionBackingBean.hiddenQuestionList}" var="eachRow"
						cellpadding="0" bgcolor="#cccccc">
						<h:column>
							<f:verbatim>
								<h:selectBooleanCheckbox id="hideQuestionChkBox">
								</h:selectBooleanCheckbox>
							</f:verbatim>
						</h:column>
						<h:column>
							<h:inputHidden value="#{eachRow.questionDesc}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.questionNumber}"></h:inputHidden>
                            	<f:verbatim>&nbsp;</f:verbatim>
							<h:outputText value="#{eachRow.questionDesc}"></h:outputText>
							<h:inputHidden value="#{eachRow.sequenceNumber}"></h:inputHidden>
							<h:inputHidden value="#{eachRow.answerId}"></h:inputHidden>
						</h:column>
					</h:dataTable></DIV>
					<h:inputHidden id="hiddenForSize"
						value="#{AddQuestionBackingBean.hiddenQuestionListSize}"></h:inputHidden>
					<h:inputHidden id="hiddenOpenQuestionSize"
						value="#{AddQuestionBackingBean.hiddenOpenQuestionListSize}"></h:inputHidden>
					</td>
				</tr>
			</TBODY>
		</table>
</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
hideOpenQuestion('openQuestionHeader');
hideDomainQuestion('domainQuestionHeader');
hideSaveButton();
function hideOpenQuestion(divId){
		hiddenIdObj = document.getElementById('addQuestionForm:hiddenOpenQuestionSize').value;
		if(hiddenIdObj == 0){
			openQuestionTab.style.display = 'none';
			document.getElementById('noOpenQuestionDiv').style.visibility = 'visible';
		}else{
			document.getElementById('noOpenQuestionDiv').style.visibility = 'hidden';
			openQuestionTab.style.display = '';
			document.getElementById('openDataTableDiv').style.height = "200px";
		}
	}
function hideDomainQuestion(divId){
		hiddenIdObj = document.getElementById('addQuestionForm:hiddenForSize').value;
		if(hiddenIdObj == 0){
			domainedQuestionTab.style.display = 'none';
			document.getElementById('noDomainQuestionDiv').style.visibility = 'visible';
		}else{
			document.getElementById('noDomainQuestionDiv').style.visibility = 'hidden';
			domainedQuestionTab.style.display = '';
			document.getElementById('domainedQuestionsDiv').style.height = "200px";
		}
	}
	
	
function hideSaveButton(){
	var domainQuesSize = document.getElementById('addQuestionForm:hiddenForSize').value;
	var openQuestionSize = document.getElementById('addQuestionForm:hiddenOpenQuestionSize').value;
	if((domainQuesSize == 0)&&(openQuestionSize == 0)){
		document.getElementById('noDomainQuestionDiv').style.visibility = 'visible';
		document.getElementById('noOpenQuestionDiv').style.visibility = 'visible';
		document.getElementById('saveButtonDiv').style.visibility = 'hidden';
	}
}
function getCheckedItemsForQuestions(id1,id2)
{	
	var selectedValues = '';
	var retvalue = getCheckedItems_ewpd1(id1,4);
	if(retvalue != '')
	{
		selectedValues += retvalue;
	}
	if(selectedValues != '')
	{
		selectedValues  += '~';
	}
	var retvalueNext = getCheckedItems_ewpd1(id2,4);
	if(retvalueNext != '')
	{
		selectedValues += retvalueNext;
	}
	window.returnValue = selectedValues;
	window.close();
	return false;

}
function getCheckedItems_ewpd1(id, attrCount)
{
	var tableObject;	
	if (document.getElementById(id))
	{
		tableObject = document.getElementById(id);
		var selectedValues = '';
		var cnt = 0;
		var currentCell;
		for (var i=0;i<tableObject.rows.length;i++)
		{
			var checkboxObject = tableObject.rows[i].cells[0].children[0];
			currentCell = tableObject.rows[i].cells[1];
			
			if (checkboxObject && checkboxObject.checked) {
				if(cnt > 0)
					selectedValues += '~';

				switch(attrCount){
					case 1: selectedValues += currentCell.children[0].value; 
							break;
					case 2: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value;	
							break;
					case 3: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value;
							break;
					case 4: selectedValues += currentCell.children[0].value + '~' + currentCell.children[1].value + '~' + currentCell.children[2].value + '~' + currentCell.children[3].value;
							break;
					default: alert('invalid attrCount parameter for function getCheckedItems');
				}
				cnt++;
			}
		}	
	}


	return selectedValues;
}
	/*hideIfNoValue('domainQuestionHeader');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('addQuestionForm:hiddenForSize').value;
		if(hiddenIdObj == 0){
			document.getElementById(divId).style.visibility = 'hidden';
			domainedQuestionTab.style.display = 'none';
			document.getElementById('openDataTableDiv').style.height = "264px";
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		    domainedQuestionTab.style.display = '';
		}
	}
	hideOpenQuestion('openQuestionHeader');
	function hideOpenQuestion(divId){
		hiddenIdObj = document.getElementById('addQuestionForm:hiddenOpenQuestionSize').value;
		if(hiddenIdObj == 0){
			document.getElementById(divId).style.visibility = 'hidden';
			openQuestionTab.style.display = 'none';
			document.getElementById('domainedQuestionsDiv').style.height = "260px";
			
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		    openQuestionTab.style.display = '';
		}
	}*/
			if(null!= document.getElementById('addQuestionForm:hideQuestionDataTable'))
			{	
				document.getElementById('domainQuestionHeader').style.height = "0px";			
				document.getElementById('domainQuestionHeader').position = 'absolute';
				document.getElementById('domainedQuestionsDiv').style.height = "0px";			
				document.getElementById('domainedQuestionsDiv').position = 'absolute';
			}
setColumnWidth('addQuestionForm:openQuestionDataTable','4%');
setColumnWidth('addQuestionForm:hideQuestionDataTable','4%');
</script>
</HTML>
