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

<TITLE>Add Question</TITLE>
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
				<TD> <h:form
					styleClass="form" id="addQuestionForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><jsp:include
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
												value="Questions" /></TD>
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
							<div id="messageTextForNoQuestionsDiv"><br />
							<br />
							<STRONG>&nbsp;<h:outputText value="No Question available." /></STRONG>
							</div>
							<div id="quesitonTableDiv">
							<TABLE width="100%" cellpadding="1" border="0" id="tabheader"
								class="smallfont">
							<td>
							<TABLE width="97.2%" cellspacing="1" bgcolor="#cccccc"
								cellpadding="3" border="0">
								<TR bgcolor="#cccccc" height="18">
									<TD><strong>Associated
									Questions</strong></TD>
								</TR>
								</table>

								<TR>
									<TD>
										<DIV id="displayPanelContent12"
										style="position:relative;overflow:auto;width:100%">
											<h:panelGrid id="quesHeadTable"
												binding="#{AddQuestionBackingBean.headerPanel}"
												rowClasses="dataTableOddRow">
											</h:panelGrid>
										</DIV>
										<DIV id="displayPanelContent12"
										style="position:relative;overflow:auto;height:290;width:100%">
											<h:panelGrid id="quesTable" 
												binding="#{AddQuestionBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid>
										
									</TD>

								</TR>

							</TABLE>
							</div>
							<br />
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="tabTable">
								<TR>
									<TD width="30" align="left"><h:commandButton
										value="Add Question" styleClass="wpdButton" id="addQues"
										onclick="addQuestionPopup('../standardBenefit/addQuestionPopUp.jsp');return false;"
										tabindex="#{AddQuestionBackingBean.tabindexForAddQuestionButton}">
									</h:commandButton></TD>
									<td>&nbsp;</td>
									<td> <h:commandButton value="Delete" styleClass="wpdButton" id="deleteButton" 
										 action="#{AddQuestionBackingBean.deleteQuestions}" onclick="return confirmDelete();"/>
									</td>

									<TD width="229">&nbsp;</TD>
								</TR>
							</TABLE>
							<br />

							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>


					</TABLE>

					<h:inputHidden id="selectedQuestionHidden"
						value="#{AddQuestionBackingBean.selectedQuestions}" />
					<h:inputHidden id="selectedAnswerHidden"
						value="#{AddQuestionBackingBean.selectedAnswers}" />
						<h:inputHidden id="duplicateData"
					value="#{AddQuestionBackingBean.duplicateData}"></h:inputHidden>
					<h:commandLink id="saveButton"
						action="#{AddQuestionBackingBean.saveSelectedQuestions}"
						style="hidden">
						<f:verbatim> &nbsp;&nbsp;</f:verbatim>
					</h:commandLink>
					<h:inputHidden id="selectedRowHidden"
						value="#{AddQuestionBackingBean.selectedRow}" />
					<h:commandLink id="hideCommandLink"
						action="#{AddQuestionBackingBean.hideSelectedQuesion}">
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
 IGNORED_FIELD1='addQuestionForm:duplicateData';
		test();
		var tableObject = document.getElementById('addQuestionForm:quesTable');
		if(tableObject.rows.length > 0){
			var msgDivObj = document.getElementById('messageTextForNoQuestionsDiv');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
		}else{
			var divObj = document.getElementById('quesitonTableDiv');
			divObj.style.visibility = "hidden";
			divObj.style.height = "2px";
			document.getElementById('addQuestionForm:deleteButton').style.visibility = 'hidden';
		}
		if(tableObject != null) {
			var tblWidth = document.getElementById('addQuestionForm:quesTable').offsetWidth;
			var rowlength = document.getElementById('addQuestionForm:quesTable').rows.length;
			if(rowlength>1){
				document.getElementById('addQuestionForm:quesHeadTable').width = tblWidth+"px";
				syncTables('addQuestionForm:quesHeadTable','addQuestionForm:quesTable');	
				setColumnWidth('addQuestionForm:quesTable','10%:38%:15%:27%:10%');
				setColumnWidth('addQuestionForm:quesHeadTable','10%:38%:15%:27%:10%');
			}else{
				setColumnWidth('addQuestionForm:quesHeadTable','10%:38%:15%:27%:10%');	
				setColumnWidth('addQuestionForm:quesTable','10%:38%:15%:27%:10%');	
			}	
		}
		function addQuestionPopup(url){
			var retValue = window.showModalDialog(url +getUrl()+ "?" + "&temp=" + Math.random(), self, "dialogHeight:370px;dialogWidth:480px;resizable=false;status=no;");	
			document.getElementById('addQuestionForm:selectedQuestionHidden').value=retValue;
			submitLink('addQuestionForm:saveButton');
			return false;
		}

		function confirmDelete() {
			return confirm("Do you want to delete the selected Question(s)?");
		}
		function getSelectedRow(selectedRow){
			var message = "Do you want to delete?"
			if(window.confirm(message)){
				document.getElementById('addQuestionForm:selectedRowHidden').value = selectedRow;
				document.getElementById('addQuestionForm:hideCommandLink').click();
				return false;			
			}
			else{
				return false;
			}
		}
		var len =  document.getElementById("addQuestionForm:quesTable").rows.length;
		for (var j=0;j<=len;j++) {			
			if(null != document.getElementById("addQuestionForm:Reference"+j))
			document.getElementById("addQuestionForm:Reference"+j).readOnly = true;
		}
	function test() {
		var chk = false;
//		if(obj.checked) {
//			chk = true;
//		}
		if(document.getElementById('addQuestionForm:quesTable') == null ||
			document.getElementById('addQuestionForm:quesTable').rows.length <= 0) {
			document.getElementById('addQuestionForm:deleteButton').disabled = true;			
		}
		var rows = document.getElementById('addQuestionForm:quesTable').rows;
		if(!chk) {
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[4].children[0].checked) {
					chk = true;
					break;
				}
			}
		}

		if(chk) {
			document.getElementById('addQuestionForm:deleteButton').disabled = false;
		} else {
			document.getElementById('addQuestionForm:deleteButton').disabled = true;
		}
	}
/*function syncTables(){
			var relTblWidth = document.getElementById('addQuestionForm:quesTable').offsetWidth;
			document.getElementById('displayPanelContent12').width = relTblWidth ;
			document.getElementById('addQuestionForm:quesHeadTable').width =  relTblWidth ;
		}

if(document.getElementById('addQuestionForm:quesTable')!= null){
		document.getElementById('addQuestionForm:quesTable').onresize = syncTables;
		var size = document.getElementById('addQuestionForm:quesTable').rows.length;
alert(size);
		var relTblWidth = document.getElementById('displayPanelContent12').offsetWidth;
		if(size<=12){
				document.getElementById('addQuestionForm:quesTable').width = relTblWidth+"px";
				document.getElementById('addQuestionForm:quesHeadTable').width = relTblWidth+"px";
		}else{
			syncTables();
		}
	}*/
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="addQuestion" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('addQuestionForm:duplicateData').value == '')
		document.getElementById('addQuestionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('addQuestionForm:duplicateData').value;
</script>
</HTML>
