<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<script language="JavaScript" src="../../js/adminMethod/adminmethodMapping.js"></script>
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 90%;
}
</style>
<!-- This tag gets the title name from the request -->
<title>Question Answer Pop Up</title>
</HEAD>
<BASE target="_self" />
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<h:form id="benefitTermSelectPopupForm">
			<h:inputHidden id="search"
				value="#{adminMethodMappingBackingBean.searchQuestionAnswer}"></h:inputHidden>		
					
			<TABLE id="message" width=98%>
			<TBODY>
				<tr>
					<TD>
						<div id="infoDivMessage"><w:messageForPopup></w:messageForPopup></div>
					</TD>
				</tr>
			</TBODY>
			</TABLE>
			<TABLE border="0" width="98%">
				<TR>
					<TD align="left" height="19">&nbsp;<INPUT type="button"
						class="wpdbutton" name="action" value="Select"
					onclick="selectQues();"></TD>
				</TR>
			</TABLE>
			<TABLE width="98%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<TR>
					<TD>

					<div id="headerTable">
					<TABLE id="businessEntityTable1" width="100%" cellpadding="0"
						cellspacing="0" bgcolor="#cccccc">
							<TR id="tr3">
							<TD width="10%" align="center" valign="middle" height="23"></TD>						
							<TD width="25%" align="left" height="22"><STRONG><h:outputText
								value="Description " styleClass="outputText"></h:outputText></STRONG></TD>
							<TD width="65%"></TD>
						</TR>
						<TR>
							<TD width="15%" align="center" height="27"></TD>
							<TD width="25%" align="left" valign="middle" height="23">
							<h:inputText
								id="searchText" size="20" styleClass="formInputField" 
								maxlength="34" tabindex="4" onkeypress="return expandcontract(event)" readonly="true" /></TD>
							<TD width="65%"></TD>
							
						</TR>
					</TABLE>
					</DIV>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" width="100%">
					<DIV id="popupTableDiv1" style="overflow:auto;"
						class="popupDataTableDiv">
						
						<h:dataTable headerClass="dataTableHeader" id="searchResultTable1test"
						var="question" cellpadding="2" width="100%"
						cellspacing="1" bgcolor="#cccccc"
						rendered="#{adminMethodMappingBackingBean.quesAnswerDataLookUpList!=null}"
						value="#{adminMethodMappingBackingBean.quesAnswerDataLookUpList}"
						border="0" rowClasses="dataTableEvenRow,dataTableEvenRow,dataTableOddRow,dataTableOddRow"
						columnClasses="selectOrOptionColumn,shortDescriptionColumn">
						
						<h:column>
						
							<h:inputHidden id="qstnId" value="#{question.questionId}"></h:inputHidden>						
							<h:selectBooleanCheckbox id="componentChkBox" onclick="return showAnswer(this);" 
								value ="#{question.questionChecked}">
							</h:selectBooleanCheckbox>
						</h:column>
						
						<h:column>
						
								<h:outputText id="queDesc" value="#{question.questionDesc}" 
										style="padding-left: 5px"></h:outputText>
							<h:dataTable headerClass="dataTableHeader" id="answerResultTable"
								var="answer" cellpadding="0" width="100%" style="display:none;"
								cellspacing="0" value="#{question.possibleAnswerIdList}" border="0" >
									<h:column>
										<h:inputHidden value="#{question.questionId}"></h:inputHidden>	
										
										<h:selectBooleanCheckbox id="ansChkBox1" onclick="return selectAnswer(this);" styleClass="mandatoryError"
										rendered="#{answer.possibleAnswerDesc!=null && answer.possibleAnswerDesc !=''}" value ="#{answer.answerChecked}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc" value="#{answer.possibleAnswerDesc}"></h:outputText>
										<h:inputHidden id="psvlAnsId" value="#{answer.possibleAnswerId}"></h:inputHidden>					
										<h:inputHidden id="psvlAnsDesc00" value="#{answer.possibleAnswerDesc}"></h:inputHidden>
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:selectBooleanCheckbox id="ansChkBox2" onclick="return selectAnswer(this);" 
										rendered="#{answer.possibleAnswerDesc2!=null && answer.possibleAnswerDesc2 !=''}" value ="#{answer.answerChecked1}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc2" value="#{answer.possibleAnswerDesc2}"></h:outputText>
										<h:inputHidden id="psvlAnsId2" value="#{answer.possibleAnswerId2}"></h:inputHidden>	
										<h:inputHidden id="psvlAnsDesc02" value="#{answer.possibleAnswerDesc2}"></h:inputHidden>				
										<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
										
										<h:selectBooleanCheckbox id="ansChkBox3" onclick="return selectAnswer(this);" 
										rendered="#{answer.possibleAnswerDesc3!=null && answer.possibleAnswerDesc3 !=''}" value ="#{answer.answerChecked2}"></h:selectBooleanCheckbox>
										<h:outputText id="psvlAnsDesc3" value="#{answer.possibleAnswerDesc3}"></h:outputText>
										<h:inputHidden id="psvlAnsId3" value="#{answer.possibleAnswerId3}"></h:inputHidden>	
										<h:inputHidden id="psvlAnsDesc03" value="#{answer.possibleAnswerDesc3}"></h:inputHidden>				
										
										
									</h:column>
							</h:dataTable>
							
						</h:column>
						
					</h:dataTable></DIV>
					<div id="errorMessageDiv" class="popupDataTableDiv"
						style="height:300px;display:none;">
					<table cellpadding="7" cellspacing="1" bgcolor="#cccccc" border="0"
						width="100%">
						<tr>
							<td bgcolor="#e1ecf7" colspan="2" width="100%"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:bold;
                                                font-size:9px;"><font
								color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;An unknown error
							occurred during processing.</font></td>
						</tr>
					</table>
					</div>
					</TD>
				</TR>
			</TABLE>
		
			<h:inputHidden id="hiddensortorder"
				value="#{ReferenceDataBackingBeanCommon.sortOrder}"></h:inputHidden>
			<h:inputHidden id="parentCatalog"
				value="#{ReferenceDataBackingBeanCommon.catalogNameForTermQualifier}"></h:inputHidden>		
			<h:inputHidden id="lookUpAction"
				value="#{ReferenceDataBackingBeanCommon.lookUpAction}"></h:inputHidden>
			<h:inputHidden id="entityId"
				value="#{ReferenceDataBackingBeanCommon.entityId}"></h:inputHidden>
			<h:inputHidden id="entityType"
				value="#{ReferenceDataBackingBeanCommon.entityType}"></h:inputHidden>
			<h:inputHidden id="qaString"
				value="#{adminMethodMappingBackingBean.questionAnswerString}"></h:inputHidden>	
			<h:inputHidden id="qaDelString"
				value="#{adminMethodMappingBackingBean.deleteQuestionAnswerString}"></h:inputHidden>
		</h:form>
	</hx:scriptCollector>  
	</BODY>
</f:view>
<script language="JavaScript">
var checkValueArray = new Array();
//var sort = document.getElementById('benefitTermSelectPopupForm:hiddensortorder');
document.getElementById('benefitTermSelectPopupForm:searchText').readOnly = false;   

initialize();
	function initialize(){
	
		if(document.getElementById('benefitTermSelectPopupForm:searchResultTable1test') != null)	{	
			var tbSize = document.getElementById('benefitTermSelectPopupForm:searchResultTable1test').rows.length;
			
			if(tbSize==0){	
				document.getElementById("message").style.display = 'none';		
						
			}	
			else{	
				document.getElementById("message").style.display = 'block';	
				for(var i=1;i<tbSize;i=i+2){
					var ansTD = document.getElementById('benefitTermSelectPopupForm:searchResultTable1test').rows[i].childNodes[0];
						ansTD.getElementsByTagName('input')[1].style.display = 'none';	
				}
			}		
		}
	}
var isAnswered = true;
var qId = '';
var qObj = null;
var qFlag = false;
function showAnswer(obj){
	var parentObj = obj.parentNode.parentNode.nextSibling;
	if(qObj == obj)qFlag = true;
	qObj = obj;	
	if(obj.checked){
		if(isAnswered){
		isAnswered = false;
		parentObj.getElementsByTagName('TABLE')[0].style.display = 'block';
		qId = parentObj.getElementsByTagName('TABLE')[0].childNodes[0].childNodes[0].childNodes[0].childNodes[0].value;
		}else {
			alert('Please Select one or more answer(s) for previous selected Question');
			obj.checked = "false";
			return false;
		}
	}else {
		if(!isAnswered & !qFlag){
			alert('Please Select one or more answer(s) for previous selected Question');
			obj.checked = "true";
			return false;
		}
	
	var inputs = parentObj.getElementsByTagName('input');
		for(var j=0;j<inputs.length;j++){
			if(inputs[j].styleClass="mandatoryError"){
				inputs[j].checked = false;
				}
			}
		qId = obj.parentNode.childNodes[0].value;
		var delString= '';
		delString=document.getElementById('benefitTermSelectPopupForm:qaDelString').value;
			if(delString !=''){
				 delString	=delString+'~'+qId;
			 }else{ 
			 delString=qId;
		 }
		document.getElementById('benefitTermSelectPopupForm:qaDelString').value= delString;
		parentObj.childNodes[1].getElementsByTagName('TABLE')[0].style.display = 'none';
		qId = '';
		qObj = null;
		isAnswered = true;
	}
}
function selectAnswer(ansObj){
	var showAnsweredLength;
	var count = 0;
	if(!(isAnswered) && (ansObj.parentNode.childNodes[0].value != qId)){
		alert('Please Select one or more answer(s) for previous selected Question');
		ansObj.checked = false;
		return false;
	}
	if(ansObj.checked){
		isAnswered = true;
	}else {
		showAnsweredLength=ansObj.parentNode.parentNode.parentNode.getElementsByTagName('Input').length;	
		isAnswered = false;	
		for(i=0;i<showAnsweredLength;i++) {
			if(ansObj.parentNode.parentNode.parentNode.getElementsByTagName('Input')[i].styleClass="mandatoryError" 
					&& ansObj.parentNode.parentNode.parentNode.getElementsByTagName('Input')[i].checked){
				isAnswered = true;
				count++;
				return true;											
				}
		}
		qId = ansObj.parentNode.childNodes[0].value;
		if(count==0){
			isAnswered = false;
			// If unccheck all answers, to Identify the correspoding question id.
			var cont = 0;
			var parent = ansObj.parentNode;
			while(parent.tagName.toLowerCase()!='tr'|| cont<1){
				if(parent.tagName.toLowerCase()=='tr')cont++; 
				parent=parent.parentNode;
			}
			qObj = parent.previousSibling.getElementsByTagName('Input')[1];
		}
	}	
}

function replaceAmberSignForEscaping(searchValueForPopUp){
 		var index = searchValueForPopUp.indexOf('&');
        while(index != -1){
            searchValueForPopUp = searchValueForPopUp.replace('&','%26');
            index = searchValueForPopUp.indexOf('&');
        }
        return searchValueForPopUp;
}

function replaceBackSlashForEscaping(searchValueForPopUp){
		var index = searchValueForPopUp.indexOf('\\');
        while(index != -1){
            searchValueForPopUp = searchValueForPopUp.replace('\\','%5C');
            index = searchValueForPopUp.indexOf('\\');
        }
        return searchValueForPopUp;
}

function expandcontract(dis) {	

      if(dis.keyCode=='13'){
      	
      		// check for unanswered question, and uncheck before search
      		if(!isAnswered && qObj!=null){
      			qObj.checked = false;
				showAnswer(qObj);
      		}
      			
            var searchField = document.getElementById('benefitTermSelectPopupForm:searchText');
			var searchValueForPopUp = searchField.value;
			if(searchValueForPopUp.indexOf('&')!=-1){
				searchValueForPopUp=replaceAmberSignForEscaping(searchValueForPopUp);
				}
			else if(searchValueForPopUp.indexOf('\\')!=-1){
				searchValueForPopUp=replaceBackSlashForEscaping(searchValueForPopUp);
				}
            var divObj = document.getElementById('popupTableDiv1');
            var errorMsgDiv = document.getElementById('errorMessageDiv');
			var msgDiv = document.getElementById('infoDivMessage');
			var attrObj =new Array(3);
			
			attrObj[0]=2;
			attrObj[1]=2;
			attrObj[2]=2;		
			
			// call ajax with the entityid, queryname, header name, search field, div object, div to be hidden, table id and error div.
			ajaxCallAdminMethodQestionAnsPopup('../popups/ajaxAdminMethodQuestionAnswerHelper.jsp?&searchValueForPopUp='+searchValueForPopUp+'&temp='+Math.random(),searchField,divObj,'benefitTermSelectPopupForm:searchResultTable1test',errorMsgDiv,attrObj,'','popupForm:checkId',msgDiv);
			loadPopUp();
			initialize();
			qId = '';
			qObj = null;
			isAnswered = true;	
    	  return false;
      }
}

loadPopUp();
function loadPopUp(){

	tableObject = document.getElementById('benefitTermSelectPopupForm:searchResultTable1test');	
	if(tableObject != null && tableObject.rows.length !=0){
		var selectedValues = '';
		var cnt = 0;
		var currentCell;		
		for (var i=0;i<tableObject.rows.length;i=i+2)
		{
	var checkboxObject = tableObject.rows[i].childNodes[0].childNodes[1];			
			currentCell = tableObject.rows[i].cells[1];			
			if (checkboxObject && checkboxObject.checked) {			
				var TBObj = tableObject.rows[i+1].childNodes[1].childNodes[1];
				TBObj.style.display = 'block';
			  }
			}
	}
}
function selectQues(){

var tableObject;
	if (document.getElementById('benefitTermSelectPopupForm:searchResultTable1test'))
	{
		// check for unanswered question, and uncheck before Select
    	if(!isAnswered && qObj!=null){
      		//qObj.checked = false;
			//showAnswer(qObj);	
			alert('Please select the answer(s) or uncheck the question');
			return false;
      	}

		tableObject = document.getElementById('benefitTermSelectPopupForm:searchResultTable1test');	
		var selectedValues = '';
		var cnt = 0;
		var currentCell;		
		for (var i=0;i<tableObject.rows.length;i=i+2)
		{
			var checkboxObject = tableObject.rows[i].childNodes[0].childNodes[1];			
			
			currentCell = tableObject.rows[i].cells[1];			
			
			if (checkboxObject && checkboxObject.checked) {			
	
				var firstCell = currentCell.children[0].innerText+'#'+tableObject.rows[i].childNodes[0].childNodes[0].value;
				var TBObj = tableObject.rows[i+1].childNodes[1].childNodes[1];
				var inputs = TBObj.getElementsByTagName('input');
				for(var j=0;j<inputs.length;j++){
					if(inputs[j].styleClass="mandatoryError"){
						if(inputs[j].checked){
						firstCell=firstCell+"@"+inputs[j+1].value+"`"+inputs[j+2].value;
						}
					}
				}
				
			if(selectedValues !='')
				selectedValues = selectedValues+"~"+firstCell; 
			else 
				selectedValues = firstCell; 
		}	
	}
    document.getElementById('benefitTermSelectPopupForm:qaString').value=selectedValues;
    
    var arr= new Array();
    arr[0]=selectedValues;
    arr[1]=document.getElementById('benefitTermSelectPopupForm:qaDelString').value;
    
	window.returnValue = arr;
	window.close();	
	if(cnt > 0)
		return true;
	return false;
}
return false;
}
</script>
</HTML>

