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

<TITLE>Product Benefit Administration </TITLE>
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


							<DIV class="treeDiv"><jsp:include page="productTree.jsp"></jsp:include>
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
											<TD class="tabActive"><h:commandLink>
												<h:outputText value="Administration" />
											</h:commandLink></TD>
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
							<DIV id="adminDiv">
									<h:outputText value="No Question Available." styleClass="dataTableColumnHeader" />
							</DIV>
							<DIV id="benefitAdministrationDiv">
							<BR>
							<TABLE>
									<TR>
										<TD width="5%" height="25" align="right">
											<a href="#" onclick="viewQuestionnaire();return false;">
												<h:outputText value="[View Questionnaire]" styleClass="variableLink"/>
											</a>
										</TD>
									</TR>
								</TABLE>
							<TABLE border="0" cellspacing="0" cellpadding="0" width="100%"
								class="outputText">
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD valign="middle">

										<DIV id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Selected Questions"></h:outputText> </B>
										</DIV>
										<DIV id="displayHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;overflow:auto;"><h:panelGrid
											id="displayHeaderTable"
											binding="#{productAdminOptionBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></DIV>
										<DIV id="displayPanelContent12"
											style="position:relative;overflow:auto;height:250px";"><h:panelGrid
											id="panelTable"
											binding="#{productAdminOptionBackingBean.panel}">
										</h:panelGrid> </div>
										<BR>
										<h:commandButton value="Save" styleClass="wpdButton"
											onmousedown="javascript:savePageAction(this.id);"
											action="#{productAdminOptionBackingBean.saveBenefitAdministration}">
										</h:commandButton>
										<h:inputHidden id="duplicateData"
											value="#{productAdminOptionBackingBean.duplicateData}"></h:inputHidden>
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
					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>

				<h:inputHidden id="hiddenAsnwerStates" value="#{productAdminOptionBackingBean.answerState}"/>
				<h:inputHidden id="rowId"
								value="#{productAdminOptionBackingBean.rowNum}"></h:inputHidden>
					<h:inputHidden id="answerId"
								value="#{productAdminOptionBackingBean.answerId}"></h:inputHidden>
					<h:inputHidden id="tildaNoteStatusId"
						value="#{productAdminOptionBackingBean.tildaNoteStatus}"></h:inputHidden>
					<h:commandLink id="newQuestionnreListLink"
							style="display:none; visibility: hidden;"
							action="#{productAdminOptionBackingBean.selectNewQuestionnreList}">
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
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productAdmin" /></form>
<script language="JavaScript">
	IGNORED_FIELD1='benefitAdmnForm:duplicateData';
if(null!= document.getElementById('benefitAdmnForm:panelTable')){
var relTblWidth = document.getElementById('benefitAdmnForm:displayHeaderTable').offsetWidth;
if(document.getElementById('benefitAdmnForm:panelTable').offsetHeight <= 250)
{
	document.getElementById('benefitAdmnForm:panelTable').width = relTblWidth+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = relTblWidth+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:30%:20%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','40%:30%:20%:10%');
}
else
{
	document.getElementById('benefitAdmnForm:panelTable').width = (relTblWidth-17)+"px";
	document.getElementById('benefitAdmnForm:displayHeaderTable').width = (relTblWidth-17)+"px";
	setColumnWidth('benefitAdmnForm:displayHeaderTable','40%:30%:20%:10%');
	setColumnWidth('benefitAdmnForm:panelTable','40%:30%:20%:10%');
}}


initialize();
		function initialize(){
		if(null!= document.getElementById('benefitAdmnForm:panelTable')){
			if(document.getElementById('benefitAdmnForm:panelTable').rows.length > 0) {
				document.getElementById('adminDiv').style.visibility = 'hidden';
			}else {
				document.getElementById('benefitAdministrationDiv').style.visibility = 'hidden';
			}
		}else {
				document.getElementById('benefitAdministrationDiv').style.visibility = 'hidden';
			}
		}

function loadNewChild(questionComponent){

		var message = 'All the questions down the hierarchy will be cleared. Are you sure you want to continue?';
		var questionComponentId = questionComponent.id;
		var questionComponentIdValue = questionComponent.value;	
		var idArray = questionComponentId.split("selectitem");
		var rowNo = idArray[1];
		var childCount = document.getElementById('benefitAdmnForm:childCount'+rowNo).value ;
			document.getElementById('benefitAdmnForm:rowId').value = rowNo;
			document.getElementById('benefitAdmnForm:answerId').value = questionComponentIdValue;
		if(childCount>0){
			if (confirm(message) ){
				submitLink('benefitAdmnForm:newQuestionnreListLink');
				return true;
			}else{
				return false;
			}
		}else{
			submitLink('benefitAdmnForm:newQuestionnreListLink');
		} 
}
IGNORED_FIELD2='benefitAdmnForm:tildaNoteStatusId';
function loadNotesPopup(popupName,questionId,primaryentityId,primaryEntytyType,bcId,benefitDefnId,adminOptionId,imageId,i){	

var secondaryEntityType="ATTACHADMNQUEST";

var retValue = window.showModalDialog(popupName +getUrl()+ "?"
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

function viewQuestionnaire(){
			var retValue = window.showModalDialog('../product/viewProductQuestionnaire.jsp'+getUrl()+'?'+ 'temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');	
		}
</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('benefitAdmnForm:duplicateData').value == '')
		document.getElementById('benefitAdmnForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('benefitAdmnForm:duplicateData').value;
</script>
</HTML>
