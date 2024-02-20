
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

<TITLE>Component Benefit Administration</TITLE>
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
		</TR>
		<tr>
			<td><h:form styleClass="form" id="compBnftAdminForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>



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
							<h:inputHidden id="componentType"
								value="#{BenefitComponentAdministrationBackingBean.componentType}"></h:inputHidden>
							<tr>
								<TD width="100%">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText
											value="Questions" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>

								<TD width="100%"></TD>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:300">
						<div id="messageTextForNoQuestionsDiv"><STRONG>&nbsp;<h:outputText
							value="No Questionnaire available." /></STRONG></div>
						<div id="panelDiv">
						<TABLE border="0" cellspacing="0" cellpadding="0" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td valign="middle">
									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%;">
									<B>&nbsp;<h:outputText value="Selected Questions"></h:outputText>
									</B></div>
									</td>
								</TR>
								<TR>
									<td>
									<div id="displayHeaderDiv"
										style="background-color:#FFFFFF;z-index:1;overflow:auto;width:100%">
									<h:panelGrid id="displayHeaderTable"
										binding="#{BenefitComponentAdministrationBackingBean.headerPanel}"
										rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid></div>
									</td>
								</TR>
								<TR>
									<td>
									<div id="displayPanelContent12"
										style="position:relative;overflow:auto;height:320;width:100%">
									<h:panelGrid id="panelTable"
										binding="#{BenefitComponentAdministrationBackingBean.questionnarePanel}">
									</h:panelGrid></div>
									</td>
								</TR>
								<TR id="saveButton">
									<TD width="110" height="1">
									<div id="saveButtonDisplay"><h:commandButton value="Save"
										styleClass="wpdButton"
										onmousedown="javascript:savePageAction(this.id);"
										action="#{BenefitComponentAdministrationBackingBean.saveBenefitAdministration}">
									</h:commandButton></div>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="duplicateData"
					value="#{BenefitComponentAdministrationBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="rowId"
					value="#{BenefitComponentAdministrationBackingBean.rowNum}"></h:inputHidden>
				<h:inputHidden id="answerId"
					value="#{BenefitComponentAdministrationBackingBean.answerId}"></h:inputHidden>
				<h:inputHidden id="answerDesc"
					value="#{BenefitComponentAdministrationBackingBean.answerDesc}"></h:inputHidden>
				<h:inputHidden id="tildaNoteStatusId"
						value="#{BenefitComponentAdministrationBackingBean.tildaNoteStatus}"></h:inputHidden>
			    <h:inputHidden id="oldAnswerValue"></h:inputHidden>
						
				<h:commandLink id="newQuestionnreListLink"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentAdministrationBackingBean.selectNewQuestionnreList}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>

				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/pageFooter.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
 IGNORED_FIELD1='compBnftAdminForm:duplicateData';
	// Space for page realated scripts
	if(document.getElementById('compBnftAdminForm:panelTable') != null) {
		if(document.getElementById('compBnftAdminForm:panelTable').rows.length <= 12)
			{
				document.getElementById('compBnftAdminForm:displayHeaderTable').width = "100%";
				document.getElementById('compBnftAdminForm:panelTable').width = "100%";
				setColumnWidth('compBnftAdminForm:displayHeaderTable','40%:20%:30%:10%');
				setColumnWidth('compBnftAdminForm:panelTable','40%:20%:30%:10%');

			}
		else
			{
				var relTblWidth = document.getElementById('compBnftAdminForm:panelTable').offsetWidth;
				document.getElementById('compBnftAdminForm:displayHeaderTable').width = "97.4%";
				document.getElementById('compBnftAdminForm:panelTable').width = "100%";
				setColumnWidth('compBnftAdminForm:displayHeaderTable','40%:20%:30%:10%');
				setColumnWidth('compBnftAdminForm:panelTable','40%:20%:30%:10%');
			}
	}

	var tableObject = document.getElementById('compBnftAdminForm:panelTable');
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

hideSaveButton();
function hideSaveButton(){
	var type = document.getElementById('compBnftAdminForm:componentType').value;
	if(type == "STANDARD"){
		saveButton.style.display = "";
	}else{
		saveButton.style.display = "none";
	}
}
		function storeOldValue(selectObj){
			var oldValue = selectObj.value;
			document.getElementById('compBnftAdminForm:oldAnswerValue').value = oldValue;
		}
function loadNewChild(questionComponent){
		var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';
		var questionComponentId = questionComponent.id;
		var questionComponentIdValue = questionComponent.value;	
		var questionComponentText = questionComponent.options[questionComponent.selectedIndex].text;
		var idArray = questionComponentId.split("selectitem");
		var rowNo = idArray[1];
		var childCount = document.getElementById('compBnftAdminForm:childCount'+rowNo).value ;
			document.getElementById('compBnftAdminForm:rowId').value = rowNo;
			document.getElementById('compBnftAdminForm:answerId').value = questionComponentIdValue;
			document.getElementById('compBnftAdminForm:answerDesc').value = questionComponentText;
		if(childCount>0){
			if (confirm(message) ){
				submitLink('compBnftAdminForm:newQuestionnreListLink');
				return true;
			}else{
				questionComponent.value = document.getElementById('compBnftAdminForm:oldAnswerValue').value;
				return false;
			}
		}else{
			submitLink('compBnftAdminForm:newQuestionnreListLink');
		} 
}
IGNORED_FIELD2='compBnftAdminForm:tildaNoteStatusId';
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminLvlOptionId,imageId,i){	

var retValue = window.showModalDialog(popupName +getUrl()+ "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminLvlOptionId="+adminLvlOptionId, 
													self, "dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");
		oldStatusValue = document.getElementById('compBnftAdminForm:tildaNoteStatusId').value;
		var imageObj = document.getElementById('compBnftAdminForm:'+imageId);
if(retValue == "notes_exists"){
		imageObj.src = "../../images/notes_exist.gif";
		document.getElementById('compBnftAdminForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'Y';
	}else if(retValue == "no_notes"){
		imageObj.src = "../../images/page.gif";
		document.getElementById('compBnftAdminForm:tildaNoteStatusId').value = oldStatusValue+'~'+i+'N';
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('compBnftAdminForm:duplicateData').value == '')
		document.getElementById('compBnftAdminForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('compBnftAdminForm:duplicateData').value;
</script>
</HTML>
