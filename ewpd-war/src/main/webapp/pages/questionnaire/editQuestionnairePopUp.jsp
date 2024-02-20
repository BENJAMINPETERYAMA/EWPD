<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">
<base target=_self>
<TITLE>Edit Questionnaire PopUp</TITLE>
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
		<style type = "text/css">
			.w8  { width:8%; text-align: left;vertical-align: top;}
			.w20  { width:20%; text-align: left;vertical-align: top;}
			.w11 { width:11%; text-align: left;vertical-align: top;}
			.w9 { width:9%; text-align: left;vertical-align: top; text-align: center;}
		</style>
</HEAD>
<f:view>
<!-- WAS 7.0 Changes - Hidden id loadEditQuestionnairePopUp value loaded using binding instead of value -->

<BODY>
<!--  hx:scriptCollector id="scriptCollector" -->
	<h:form styleClass="form" id="editQuestionnaireForm">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<w:message value="#{editQuestionnaireBackingBean.validationMessages}"></w:message>
			</TD>
		</TR>
		<h:inputHidden id="loadEditQuestionnairePopUp" binding="#{editQuestionnaireBackingBean.loadEditQuestionnairePopUp}" ></h:inputHidden>
		<h:inputHidden id="rootQuestionnaireHierarchySystemId" value="#{editQuestionnaireBackingBean.rootQuestionnaireHierarchySystemId}" ></h:inputHidden>
		<TR>
			<TD>
				&nbsp;
			</TD>
		</TR>
		<TR>
			<TD>
				&nbsp;
			</TD>
		</TR>
		<TR height="4%">
			<TD align="center" bgcolor="#cccccc">
				<b><h:outputText value="Edit Questionnaire"></h:outputText></b>
			</TD>
		</TR>
		<TR>
			<TD>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
	   					<TD colspan="2" valign="top" class="ContentArea">
							<%--<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">--%>
<!--	Start of Table for actual Data	-->		
								<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="100%">							
									<TR>	
										<TD align="left" width="25%">
											<h:outputText value="Selected Question"></h:outputText>
										</TD>
										<TD align="left" width="75%" colspan="2">
											<h:outputText 
												value="#{editQuestionnaireBackingBean.rootQuestionDescAndReference}" >
											</h:outputText>
										</TD>
									</TR>
									<TR>	
										<TD align="left" width="25%">
											<h:outputText value="Answer *" 
												styleClass="#{editQuestionnaireBackingBean.requiredAnswer?'mandatoryError': 'mandatoryNormal'}" >
											</h:outputText>
										</TD>
										<TD align="left" width="20%">
											<h:selectOneMenu id="answerId" value="#{editQuestionnaireBackingBean.selectedAnswerId}">
												<f:selectItems id="possibleAnswerList" value="#{editQuestionnaireBackingBean.possibleAnswerList}" />
											</h:selectOneMenu>
										</TD>
										<TD align="left" width="55%">&nbsp;</TD>
									</TR>
									<TR>	
										<TD align="left" width="25%">
											<h:outputText value="Question *" 
												styleClass="#{editQuestionnaireBackingBean.requiredQuestion?'mandatoryError': 'mandatoryNormal'}" >
											</h:outputText>
										</TD>
										<TD align="left" width="20%">
											<h:inputHidden id="selectedQuestionId"
												value="#{editQuestionnaireBackingBean.selectedQuestionId}"></h:inputHidden>
											<div id="questionSelectDiv" class="selectDataDisplayDiv"></div>										</TD>
										<TD align="left" width="55%">
											&nbsp;
											<h:commandButton id="selectQuestionButton" image="../../images/select.gif"
												onclick="ewpdModalWindow_ewpd('../adminoptionspopups/selectMultipleQuestionPopup.jsp?'+ 'temp=' + Math.random()+'&&'+'adminOptionId='+document.getElementById('editQuestionnaireForm:adminOptionSystemId').value,'questionSelectDiv','editQuestionnaireForm:selectedQuestionId',4,1);
																				return false;"												
												>
										</h:commandButton>
										</TD>
									</TR>
									<TR>
										<TD>&nbsp;
										</TD>
									</TR>
									<TR>
										<TD colspan="3">
											<h:commandButton value="Add Dependent Questions" styleClass="wpdButton" id="addButton" action="#{editQuestionnaireBackingBean.addDependentQuestions}"> 
											</h:commandButton>
										</TD>
									</TR>
            					</TABLE>
								<br/><br/><br/>
								<TABLE width="100%">
									<TR>
										<TD>
											<div id="questionsPanelDiv" style="width:100%;">
											<div id="headerPanelDiv" style="width:100%;">
												<h:panelGrid id="headerTable" 
													binding="#{editQuestionnaireBackingBean.headerPanel}">
												</h:panelGrid> 
											</div>
											<div id="associatedQuestionsPanelDiv" style="height:180px;width:100%;overflow-y:auto;">
												<h:panelGroup id="associatedPanel"	style="width:100%;"
													styleClass="dataTableColumnHeader">
													<h:panelGrid id="childQuesitonsTable"
			 											binding="#{editQuestionnaireBackingBean.childQuestionsPanel}"
														rowClasses="dataTableOddRow,dataTableEvenRow">
													</h:panelGrid>
												</h:panelGroup>
											</div>
											</div>
										</TD>
									</TR>
								</TABLE>
								<BR/>
								<div id="saveCloseButtonDiv">
								<TABLE width="100%">
									<TR>
										<TD width="5%">
											<h:commandButton value="Save" id ="save"  styleClass="wpdButton" 
												onclick="javascript:saveAction();return false;"> 
											</h:commandButton>
										</TD>
										<TD width="95%">&nbsp;
											<%--<h:commandButton value="Close" styleClass="wpdButton" 
												action="#{editQuestionnaireBackingBean.close}"> 
											</h:commandButton>--%>
										</TD>
									</TR>
								</TABLE>
								</div>
							<%--</FIELDSET>--%>									
						</TD>
					</TR>
				</table>
<!--	End of Page data	-->		
					
<!-- Space for hidden fields -->		
				<h:inputHidden id="adminOptionSystemId" value="#{editQuestionnaireBackingBean.adminOptionSystemId}"></h:inputHidden>
				<h:inputHidden id="adminOptionName" value="#{editQuestionnaireBackingBean.adminOptionName}"></h:inputHidden>
				<h:inputHidden id="adminOptionVersion" value="#{editQuestionnaireBackingBean.adminOptionVersion}"></h:inputHidden>
				<h:commandLink id="addLink" action="#{editQuestionnaireBackingBean.addDependentQuestions}"><f:verbatim/></h:commandLink>
				<h:inputHidden id="panelData" value="#{editQuestionnaireBackingBean.panelData}" />
				<h:inputHidden id="duplicateData"
					value="#{editQuestionnaireBackingBean.duplicateData}"></h:inputHidden>
				<h:commandLink id="saveLink"
						style="display:none; visibility: hidden;"
						action="#{editQuestionnaireBackingBean.save}">
						<f:verbatim />
				</h:commandLink>
				<h:commandLink id="deleteLink"
						style="display:none; visibility: hidden;"
						action="#{editQuestionnaireBackingBean.delete}">
						<f:verbatim />
				</h:commandLink>
<!-- End of hidden fields  -->
		</td>
	</tr>
	<TR>
		<TD>
		</TD>
	</TR>
	
	</table>
	</h:form>
</BODY>
</f:view>


<script language="JavaScript">
	changeButtonOnLoad();
	IGNORED_FIELD1='editQuestionnaireForm:duplicateData';
	IGNORED_FIELD2='editQuestionnaireForm:panelData';
	copyHiddenToDiv_ewpd('editQuestionnaireForm:selectedQuestionId','questionSelectDiv',4,1); 
	onLoadUIs();
	setReadOnly();
	function onLoadUIs(){
		copyHiddenToDiv_ewpd('editQuestionnaireForm:selectedQuestionId', 'questionSelectDiv', 4, 1);
		var dataTableObject = document.getElementById('editQuestionnaireForm:childQuesitonsTable');
		var headerTableObject = document.getElementById('editQuestionnaireForm:headerTable');
	 	var divObjForHeaderPanel = document.getElementById('headerPanelDiv');
 		var divObjForDataPanel = document.getElementById('associatedQuestionsPanelDiv');
 		var divObjForButtons = document.getElementById('saveCloseButtonDiv');
		if(null != dataTableObject){
			if(dataTableObject.rows.length < 1){
				// hide the header and data panel
				divObjForHeaderPanel.style.visibility = "hidden";
				divObjForHeaderPanel.style.height = "2px";
				divObjForDataPanel.style.visibility = "hidden";
				divObjForDataPanel.style.height = "2px";
				divObjForButtons.style.visibility = "hidden";
				divObjForButtons.style.height = "2px";
				setColumnWidth('editQuestionnaireForm:childQuesitonsTable','6%:8%:18%:18%:12%:11%:11%:6%');	
				setColumnWidth('editQuestionnaireForm:headerTable','6%:8%:18%:18%:12%:11%:11%:6%');
			}else{
				setColumnWidth('editQuestionnaireForm:childQuesitonsTable','6%:8%:18%:18%:12%:11%:11%:6%');	
				setColumnWidth('editQuestionnaireForm:headerTable','6%:8%:18%:18%:12%:11%:11%:6%');
				if(!(document.getElementById('editQuestionnaireForm:childQuesitonsTable').offsetHeight < 180))
				{
						var relTblWidth = document.getElementById('editQuestionnaireForm:childQuesitonsTable').offsetWidth;						
						document.getElementById('editQuestionnaireForm:childQuesitonsTable').width = relTblWidth+"px";
						document.getElementById('editQuestionnaireForm:headerTable').width = relTblWidth+"px";
						setColumnWidth('editQuestionnaireForm:headerTable','6%:8%:18%:18%:12%:11%:11%:6%');
				}
			}
		}
	}
	
	function showRef(ques,ref){
		
		if(document.getElementById('editQuestionnaireForm:'+ques) != null){
		 var value= document.getElementById('editQuestionnaireForm:'+ques).value;
		 var arr= value.split('~');
		 document.getElementById('editQuestionnaireForm:'+ref).value = arr[2];
		}
	}

	function setReadOnly(){
		var tags = document.getElementsByTagName('input');
		var tagName;
		var tagMatchForQuestions;
		var tagMatchForReference;
		var tagMatchForLOB;
		var tagMatchForBusinessEntity;
		var tagMatchForBusinessGroup;
		for(i = 0; i < tags.length; i++){
			tagName = tags[i].name;
			tagMatchForQuestions = tagName.match('editQuestionnaireForm:questionInputText');
			if(tagMatchForQuestions == 'editQuestionnaireForm:questionInputText'){
				tags[i].readOnly = true;
			}
			tagMatchForReference = tagName.match('editQuestionnaireForm:referenceInputText');
			if(tagMatchForReference == 'editQuestionnaireForm:referenceInputText'){
				tags[i].readOnly = true;
			}
			tagMatchForLOB = tagName.match('editQuestionnaireForm:lobInputText');
			if(tagMatchForLOB == 'editQuestionnaireForm:lobInputText'){
				tags[i].readOnly = true;
			}
			tagMatchForBusinessEntity = tagName.match('editQuestionnaireForm:businessEntityInputText');
			if(tagMatchForBusinessEntity == 'editQuestionnaireForm:businessEntityInputText'){
				tags[i].readOnly = true;
			}
			tagMatchForBusinessGroup = tagName.match('editQuestionnaireForm:businessGroupOutputText');
			if(tagMatchForBusinessGroup == 'editQuestionnaireForm:businessGroupOutputText'){
				tags[i].readOnly = true;
			}
		}		
	}

	function changeButtonOnLoad(){
			var tags = document.getElementsByTagName('input');
			var isDeleteFlag = false;
			for(i = 0; i < tags.length; i++){
				if(tags[i].type == 'checkbox'){
					if(tags[i].checked == true){
						isDeleteFlag = true;
						break;
					}
				}
			}
			if(isDeleteFlag == true){
				document.getElementById('editQuestionnaireForm:Delete').disabled = false;
			}else{
				document.getElementById('editQuestionnaireForm:Delete').disabled = true;
			}		
	}

	function enableDeleteIfSelected(object){
		if(object.checked){
			document.getElementById('editQuestionnaireForm:Delete').disabled = false;
		}else{
			var tags = document.getElementsByTagName('input');
			var isDeleteFlag = false;
			for(i = 0; i < tags.length; i++){
				if(tags[i].type == 'checkbox'){
					if(tags[i].checked == true){
						isDeleteFlag = true;
						break;
					}
				}
			}
			if(isDeleteFlag == true){
				document.getElementById('editQuestionnaireForm:Delete').disabled = false;
			}else{
				document.getElementById('editQuestionnaireForm:Delete').disabled = true;
			}
		}
	}

	function deleteAction(){
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
			submitLink('editQuestionnaireForm:deleteLink');
		}
		else{
				return false;
		}	
	}

	function saveAction(){
		if(!isEwpdbDataModifed())
		{
			alert('No modifications to be updated.');
		}
		else{
			submitLink('editQuestionnaireForm:saveLink');
		}	
	}


	function ewpdModalWindow_ewpd_Domain(url, targetId, hiddenId, attrCount, attrPos, peer_divId, peer_hiddenId, hiddenIdToDisable){
		// Creating new object and setting all the values that needs to be passed into the popup screen. These values
		// Object param can be accessed from the popup by "window.dialogArguments".
		var param = new Object();
		param.parentWindow = window;
		param.hiddenId = hiddenId;
		param.selectedValues = getObj(hiddenId).value;
		param.hiddenIdToDisable = hiddenIdToDisable;
	
		// Calling popup window.
		var retValue = window.showModalDialog(url, param, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");	
		// Cancel action from popup. No need to do anything.
		if( retValue == undefined ) {
			return false;
		}
		// Setting the value from popup to hidden field
		var hiddenValueObj = getObj(hiddenId);
	
		
		if(peer_divId != undefined && peer_hiddenId != undefined) {
			var peer_DivObj = document.getElementById(peer_divId);
			var peer_hiddenObj = document.getElementById(peer_hiddenId);
			if(hiddenValueObj.value != retValue) {
				if(peer_hiddenObj.value != ''){
					peer_hiddenObj.value = '';
				}
				if(peer_DivObj != null){
					peer_DivObj.innerHTML = '';
					peer_DivObj.style.height="17px";
				}
			}	
		}
		hiddenValueObj.value = retValue;
		if(hiddenId == targetId)
			return true;
	
		// Setting necessary values from hidden field to div/text	
		if(	getObj(targetId).type == 'text'){
			copyHiddenToInputText_Domain(hiddenId, targetId, attrPos);
			return retValue;		
		}
		else
			copyHiddenToDiv_ewpd(hiddenId, targetId, attrCount, attrPos);
		return true;
	}

	function copyHiddenToInputText_Domain(hiddenId, targetId, attrPos){
		var text = document.getElementById(hiddenId);
		if(text != null)
		{
			var textValue = text.value;
			if(textValue != null && textValue.length > 0)
			{
				var values = textValue.split("~");
				for(var i = 1; i < values.length; i = i + 2){
					if(i == 1)
						document.getElementById(targetId).value = "";
					if(i != 1)
						document.getElementById(targetId).value = document.getElementById(targetId).value + ", "
					document.getElementById(targetId).value = document.getElementById(targetId).value + values[i];
				}
			}
			else if(textValue == ""){
				document.getElementById(targetId).value = textValue;
			}
		}
	}
	function setTitle(object){
		var value = object.value;
		object.title = value;
	}

	function checkForPanel(){
		var tableObject = document.getElementById('editQuestionnaireForm:childQuesitonsTable');
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('editQuestionnaireForm:childQuesitonsTable');
			document.getElementById('editQuestionnaireForm:panelData').value = onLoadPanelData;
		}
	}
	


	function unsavedDataFinder(objectId){
		var buttonId = objectId.id;
		var tableObject = document.getElementById('editQuestionnaireForm:childQuesitonsTable');
		if(buttonId == 'editQuestionnaireForm:addButton'){
			if(tableObject.rows.length == 0){
				submitLink('editQuestionnaireForm:addLink');	
			}
			else{
				var panelData = getPanelData('editQuestionnaireForm:childQuesitonsTable');
				if(document.getElementById('editQuestionnaireForm:panelData').value != panelData){
					var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
					if (retValue){
						submitLink('editQuestionnaireForm:addLink');	
					}
				}else{
						submitLink('editQuestionnaireForm:addLink');	
				}
			}
		}
	}

	function getPanelData(list){
		var tagNames = list.split(',');
		var dataOnScreen = "";
		var tableObject = document.getElementById(tagNames[0]);
		
		var rows = tableObject.rows.length;
		if(rows >0){
			var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
				for(var j=0;j<columns-1;j++){
					if(null != tableObject.rows[i].cells[j].children[0]){
						dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
					}
				}
			}
			return dataOnScreen;
		}else
			return dataOnScreen;
	}

	 window.onbeforeunload = function(event)
      {  
			var tableObject = document.getElementById('editQuestionnaireForm:childQuesitonsTable');
			var flag = false;
			if(isEwpdbDataModifed()){
			    flag = true;
			} 
			//if(checkForEmptyReference(tableObject)){
			//	 flag = true;
			//}
			if(flag){
				 if( event.clientX < document.body.clientWidth && event.clientY < 0 || event.altKey )   
			      {   
			          window.event.returnValue = "Unsaved data if any will be lost.";   
			      }
			}
		
      }

	//Checking for empty reference.
	function checkForEmptyReference(tableObject){
		var flag = false;
		var referenceInputObj;
		var referenceValue;
		if(null != tableObject){
			for(var i=0; i<tableObject.rows.length; i++) {
				referenceInputObj = tableObject.rows[i].cells[3].children[0];
				referenceValue = referenceInputObj.children[0].value;
				if(null == referenceValue || '' == referenceValue)
					flag = true; 
			}
		}
		return flag;
	}
checkForPanel();
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('editQuestionnaireForm:duplicateData').value == ''){
		document.getElementById('editQuestionnaireForm:duplicateData').value = document.ewpdDataChangedForm.ewpdOnloadData.value;
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('editQuestionnaireForm:duplicateData').value;
</script>
</HTML>


