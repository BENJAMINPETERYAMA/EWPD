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
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<TITLE>Add Root Question Popup</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="" cellspacing="0">	
			<TR>
										<TD width="80%"><w:message value="#{rootQuestionsBackingBean.validationMessages}"></w:message>
										</TD>
									</TR>

			<h:inputHidden id="hiddenAdminId"
					value="#{rootQuestionsBackingBean.adminOptionLoad}" />	
			<TR>
				<TD><h:form styleClass="form" id="addRootQuestionForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>							
							<TD colspan="2" valign="top" class="ContentArea">
							<!--	Start of Table for actual Data	-->
							<TABLE width="98%" border="0">
								<TBODY>
									
									<TR>
										<TD colspan="3">
											<div id="messageDiv" class="errorDiv" style="display:none;width:98%;"></div>
										</TD>
									</TR>
									
									<TR>
										<TD colspan="3">&nbsp;
										</TD>
									</TR>
									<tr  height="4%"><td align="center"  bgcolor="#cccccc" colspan="3"><b>Add Root Question</b></td></tr>
									<TR>
										<TD colspan="3">
										<FIELDSET
											style="width:100%">
										<LEGEND><FONT color="black">Business Domain</FONT></LEGEND>
										<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
											<TR>
												<TD valign="top" width="40%"><h:outputText id="lineOfBusiness"
													value="Line of Business"></h:outputText></TD>
												<TD width="60%" align="left" nowrap>
												<h:inputText id="lobText" value="#{rootQuestionsBackingBean.lineOfBusiness}" styleClass="formInputField" readonly="true"></h:inputText>
												</TD>							
											</TR>
											<TR>
												<TD width="40%"><h:outputText id="businessEntity"
													value="Business Entity" styleClass="mandatoryNormal"/>
												</TD>
												<TD width="60%" height="17">
												<h:inputText id="businessEntityText" value="#{rootQuestionsBackingBean.businessEntity}" styleClass="formInputField" readonly="true"></h:inputText>
												</TD>								
											</TR>
											<TR>
												<TD width="40%"><h:outputText id="businessGroup"
													value="Business Group" styleClass="mandatoryNormal"/>
												</TD>
												<TD width="60%">
												<h:inputText id="businessGroupText" value="#{rootQuestionsBackingBean.businessGroup}" styleClass="formInputField" readonly="true"></h:inputText>

													</TD>								
											</TR>
<!-- ------------------------------------------------------------ -->
											<TR>
												<TD width="40%"><h:outputText id="businessUnit"
													value="Market Business Unit" styleClass="mandatoryNormal"/>
												</TD>
												<TD width="60%">
												<h:inputText id="txtMarketBusinessUnit" value="#{rootQuestionsBackingBean.marketBusinessUnit}" styleClass="formInputField" readonly="true"></h:inputText>

													</TD>								
											</TR>
<!-- ------------------------------------------------------------ -->
										</TABLE>
										</FIELDSET>
										</TD>
									</TR>

									
									<TR>
									<TD colspan="3" style="width:100%" height="0">
									<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
										<TR>
											<TD width="40%">&nbsp;<h:outputText id="quesSelect"
												value="Select Question *" /></TD>
											<h:inputHidden id="selectQuesHidden"
												value="#{rootQuestionsBackingBean.selectedQuestions}"></h:inputHidden>
											<TD width="30%">
											<div id="questionSelectDiv" class="selectDataDisplayDiv"></div>

											</TD>
											<TD width="10%" align="center"><h:commandButton alt="SelectQuestion"
												id="selectQues" image="../../images/select.gif"
												onclick="selectQuestion(); return false;" tabindex="1">
											</h:commandButton></TD>
											<TD width="20%">&nbsp;</TD>

										</TR>
									</TABLE>
									</TD>
								</TR>	
								
									<tr>
										<td width="100%">
										<div id="resultHeaderDiv" style="visibility:hidden;">
											<h:panelGrid id="headerPanel" binding="#{rootQuestionsBackingBean.headerPanelForAdd}"></h:panelGrid>
										</DIV>
										</TD>
									</TR>								

									<TR>
										<TD width="100%">
										<DIV id="searchResultdataTableDiv"
											style="height:250px;width:100%; overflow-y:auto;visibility:hidden;" >
												<h:panelGrid id="dispPanel" rowClasses="dataTableOddRow,dataTableEvenRow" binding = "#{rootQuestionsBackingBean.displayPanelForAdd}"></h:panelGrid>
										</DIV>
										</TD>
									</TR>
								</TBODY>
								</TABLE>
								<TABLE>
								<TBODY>
									<TR>
										<TD width="12%">&nbsp;
										</TD>

									</TR>
									<TR>
										<!-- <TD width="10"></TD> -->
										<TD width="12%"><div id="buttonDiv" style="visibility:hidden;">
										<h:commandButton value="Add" styleClass="wpdButton"
											id="createButton" tabindex="2" onclick="addRootQuestion(); return false;">
										</h:commandButton></div>
										</TD>

									</TR>

								</TBODY>
							</TABLE>
							<!--	End of Page data	-->							
					</TABLE>

					<!-- Space for hidden fields -->

				<h:inputHidden id="adminOptionHiddenId"
						value="#{rootQuestionsBackingBean.adminId}" />
				<h:inputHidden id="quesReferenceHidden"
						value="#{rootQuestionsBackingBean.reference}" />
				<h:inputHidden id="closeFlag"
						value="#{rootQuestionsBackingBean.closeFlag}" />
				<h:inputHidden id="flag"
						value="#{rootQuestionsBackingBean.validateFlag}" />
				<h:inputHidden id="questionFlag"
						value="#{rootQuestionsBackingBean.quesValidateFlag}" />
					<%--<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>--%>
					<!-- End of hidden fields  -->
					<h:commandLink id="addRootQuestion"
						style="hidden" action="#{rootQuestionsBackingBean.addRootQuestions}"><f:verbatim />
					</h:commandLink>
					<h:commandLink id="setDataTable"
						style="hidden" action="#{rootQuestionsBackingBean.setDataTable}"><f:verbatim />
					</h:commandLink>
					<h:commandLink id="navigateLink"
						style="hidden" action="#{rootQuestionsBackingBean.navigate}"><f:verbatim />
					</h:commandLink>
				</h:form></TD>
			</TR>			
		</TABLE>
	</BODY>
</f:view>
<script>
changeToReadOnly();
if(document.getElementById('addRootQuestionForm:closeFlag').value == "true"){
	window.returnValue = "Validation Success";
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
}
document.getElementById('addRootQuestionForm:flag').value = false;
document.getElementById('addRootQuestionForm:questionFlag').value = false;
var refFlag;
var quesFlag;
var tableObj = document.getElementById('addRootQuestionForm:dispPanel');
if((null != tableObj) && tableObj.rows.length > 0){
	setColumnWidth('addRootQuestionForm:headerPanel','60%:40%');
	setColumnWidth('addRootQuestionForm:dispPanel','60%:40%');
	document.getElementById('resultHeaderDiv').style.visibility = 'visible';
	document.getElementById('buttonDiv').style.visibility = 'visible';
	document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
}
/* function selectQuestion(){	
	var adminId = document.getElementById('addRootQuestionForm:adminOptionHiddenId').value;
	var retvalue = ewpdModalWindow_ewpd('../adminoptionspopups/selectMultipleQuestionPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'adminOptionId='+adminId,'questionSelectDiv','addRootQuestionForm:selectQuesHidden',3,1);
		if(document.getElementById('questionSelectDiv').innerText != ''){
			submitLink('addRootQuestionForm:setDataTable');
			document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
			document.getElementById('resultHeaderDiv').style.visibility = 'visible';
			document.getElementById('buttonDiv').style.visibility = 'visible';
		}
	 
} */
function addRootQuestion(){
		submitLink('addRootQuestionForm:addRootQuestion');
	
}


function closePopup(){	
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
return true;
}
function openReferencePopup(){
var e = window.event;
		if(!e || e==undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var sourceFieldId = 'addRootQuestionForm:quesTable' + ':' + rowcount + ':' + 'referenceId';
		var targetFieldId = 'addRootQuestionForm:quesTable' + ':' + rowcount + ':' + 'referenceOutput';
	ewpdModalWindow_ewpd('../adminoptionspopups/selectOneReferencePopupFilterSearch.jsp'+getUrl()+'?'+ 'temp=' + Math.random()+'&&'+'lookUpAction='+6+'&parentCatalog='+'reference',targetFieldId,sourceFieldId,1,1)
}

function getReference(){
var isValid = true;
var refQues;
var ref;
var ques;
var refArray;
	for (i=0; i<tableObj.rows.length; i++ ){
		ques = document.getElementById('addRootQuestionForm:quesTable' + ':' + i + ':' + 'quesNo').value;
		ref = document.getElementById('addRootQuestionForm:quesTable' + ':' + i + ':' + 'referenceId').value;
	
		refArray = ref.split('~');
		if(refArray[1] == undefined){
			refArray[1]='';
			document.getElementById('messageDiv').style.display = "block";
			document.getElementById('messageDiv').innerHTML = "<b><font color='red'>Please select reference for all the questions.</font></b>";
			isValid = false;
		}
		if(i==0)
			refQues = ques +'~'+ refArray[1];
		else
			refQues = refQues +'~'+ (ques +'~'+ refArray[1]);
	}
	document.getElementById('addRootQuestionForm:quesReferenceHidden').value = refQues;
	return isValid;
}
window.onbeforeunload = function(event)
      {   
		if((null != tableObj)){
	          if( event.clientX < document.body.clientWidth && event.clientY < 0 || event.altKey )   
	          {   
	              window.event.returnValue = "Unsaved data if any will be lost.";   
	          }
		}
      }

function changeToReadOnly(){
      var tags = document.getElementsByTagName('input');
      var tagType;
      var tagMatch; 
      for (i=0; i<tags.length; i++){
            tagType = tags[i].name;
            tagMatch = tagType.match('addRootQuestionForm:inputReference');
            if(tagMatch == 'addRootQuestionForm:inputReference'){
                  tags[i].readOnly = true;
            }
      }
}



</script>
</HTML>
