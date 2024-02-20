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

<TITLE>Contract Admin Option</TITLE>
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
											<TD class="tabActive"><h:outputText value="Administration" />
											</TD>
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

							<!--	Start of Table for actual Data	--> <BR>
							<DIV id="adminDiv"><h:outputText
								value="No Questionnaire Available."
								styleClass="dataTableColumnHeader" /></DIV>
							<DIV id="benefitAdministrationDiv">
							<TABLE border="0" cellspacing="0" cellpadding="0" width="100%"
								class="outputText">
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD valign="middle">

										<DIV id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:15;width:100%;">
										<B>&nbsp;<h:outputText value="Associated Questionnaire"></h:outputText>
										</B></DIV>
										<DIV id="displayHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;overflow:auto;width:100%"><h:panelGrid
											id="displayHeaderTable"
											binding="#{contractProductAdminOptionOverrideBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></DIV>
										<DIV id="displayPanelContent12"
											style="position:relative;overflow:auto;height:420;"><h:panelGrid
											id="panelTable"
											binding="#{contractProductAdminOptionOverrideBackingBean.panel}">
										</h:panelGrid></div>
										<BR>
										<h:commandButton value="Save" styleClass="wpdButton"
											onmousedown="javascript:savePageAction(this.id);"
											action="#{contractProductAdminOptionOverrideBackingBean.saveQuestionnaire}">
										</h:commandButton>
									<tr>
									</TR>

									<TR>
										<TD width="110" height="1"></TD>
									</TR>

								</TBODY>
							</TABLE>
							</DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="duplicateData"
						value="#{contractProductAdminOptionOverrideBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="rowId"
						value="#{contractProductAdminOptionOverrideBackingBean.rowNum}"></h:inputHidden>
					<h:inputHidden id="answerId"
						value="#{contractProductAdminOptionOverrideBackingBean.answerId}"></h:inputHidden>
					<h:inputHidden id="answerStates"
						value="#{contractProductAdminOptionOverrideBackingBean.answerStates}"></h:inputHidden>
					<h:inputHidden id="tildaNoteStatusId"
						value="#{contractProductAdminOptionOverrideBackingBean.tildaNoteStatus}"></h:inputHidden>
					<h:commandLink id="newQuestionnaireListLink"
						style="display:none; visibility: hidden;"
						action="#{contractProductAdminOptionOverrideBackingBean.selectChildQuestionnaireList}">
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
			if(document.getElementById('benefitAdmnForm:panelTable') != null) {
				if(document.getElementById('benefitAdmnForm:panelTable').offsetHeight <= 200) {	
					setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
					setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
				}else{
					document.getElementById('benefitAdmnForm:displayHeaderTable').width = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					document.getElementById('LabelHeaderDiv').style.width = document.getElementById('benefitAdmnForm:panelTable').offsetWidth;
					setColumnWidth('benefitAdmnForm:panelTable','30%:20%:25%:15%:10%');
					setColumnWidth('benefitAdmnForm:displayHeaderTable','30%:20%:25%:15%:10%');
				}
				document.getElementById('adminDiv').style.visibility = 'hidden';
			}else {
				document.getElementById('benefitAdministrationDiv').style.visibility = 'hidden';
			}
		}
 		function loadNewChild(answerComponent){
			var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';

			var answerComponentId = answerComponent.id;
			var answerComponentIdValue = answerComponent.value;	
			var idArray = answerComponentId.split("selectitem");
			var rowNo = idArray[1];			
			document.getElementById('benefitAdmnForm:rowId').value = rowNo;
			document.getElementById('benefitAdmnForm:answerId').value = answerComponentIdValue;

			var childCount = document.getElementById('benefitAdmnForm:childCount'+rowNo).value ;
			if(childCount>0){
				if (confirm(message) ){
					submitLink('benefitAdmnForm:newQuestionnaireListLink');
					return true;
				}else{
					return false;
				}
			}else{
				submitLink('benefitAdmnForm:newQuestionnaireListLink');
			}

		}
		IGNORED_FIELD2='benefitAdmnForm:tildaNoteStatusId';
		function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminOptionId,imageId,i){	

secondaryEntityType="ATTACHADMNQUEST"
var retValue = window.showModalDialog(popupName + "?"
												 	+ "&temp=" + Math.random() 
													+ "&questionId="+questionId+"&primaryentityId="
													+primaryentityId+"&primaryEntytyType="+primaryEntytyType
													+"&bcId="+ bcId+"&benefitDefnId="
													+ benefitDefnId +"&adminOptionId="+adminOptionId+'&secondaryEntityType='+secondaryEntityType, 
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
		
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractProductAdmin" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
    if(document.getElementById('benefitAdmnForm:duplicateData').value == '')
		document.getElementById('benefitAdmnForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
    else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdmnForm:duplicateData').value;
</script>
</HTML>

