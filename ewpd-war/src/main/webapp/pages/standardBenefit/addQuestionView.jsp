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

<TITLE>Add Question View</TITLE>
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
				<TD><h:form styleClass="form" id="addQuestionViewForm">
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
												value="Add Questions" /></TD>
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
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:2px;width:100%;height:500">

							<!--	Start of Table for actual Data	--> 
							<div id="messageTextForNoQuestionsDiv">
							</div>
							<div id="quesitonTableDiv">
							<TABLE width="100%" cellpadding="0" border="0" id="tabheader"
								class="smallfont" cellspacing="0">

								<TR bgcolor="#cccccc" height="20">
									<TD colspan="4"><span id="tableTitle">&nbsp;<strong>Associated
									Questions</strong></span></TD>
								</TR>


								<TR><TD colspan="2">
									<TD align="left" valign="top">
									<DIV style="position:relative;top:0px;left:0px" align="left">
									<DIV id="displayPanelContent12" align="left"
										style="position:relative;z-index:1;overflow:auto;width:100%">
											<h:panelGrid id="quesHeadTable" 
												binding="#{AddQuestionBackingBean.headerPanelForView}"
												rowClasses="dataTableOddRow" style="height:22px;">
											</h:panelGrid>
										</DIV>
							<div id="quesitonTableDiv1" style="height:300px;position:relative;z-index:1;font-size:10px;overflow:auto;width:100%">
									<h:panelGrid id="quesTable"
										binding="#{AddQuestionBackingBean.panelForView}"
										rowClasses="dataTableEvenRow,dataTableOddRow">
									</h:panelGrid></div></DIV></TD>

								</TR>

							</TABLE>
							</div>
							<br />

							<br />

							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>


					</TABLE>

					<h:inputHidden id="selectedQuestionHidden"
						value="#{AddQuestionBackingBean.selectedQuestions}" />
					<h:inputHidden id="selectedAnswerHidden"
						value="#{AddQuestionBackingBean.selectedAnswers}" />
					<h:inputHidden id="selectedRowHidden"
						value="#{AddQuestionBackingBean.selectedRow}" />
					<h:commandLink id="hideCommandLink"
						action="#{AddQuestionBackingBean.hideSelectedQuesion}">
						<f:verbatim />
					</h:commandLink>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script language="javascript">
		var tableObject = document.getElementById('addQuestionViewForm:quesTable');
		if(tableObject.rows.length > 0){
			var msgDivObj = document.getElementById('messageTextForNoQuestionsDiv');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
		}else{
			var divObj = document.getElementById('quesitonTableDiv');
			divObj.style.visibility = "hidden";
			divObj.style.height = "2px";
			document.getElementById('messageTextForNoQuestionsDiv').innerHTML = "<br><br><STRONG><center>No Question available.</center></STRONG>";
		}

	//	setColumnWidth('addQuestionViewForm:quesHeadTable','10%:40%:15%:30%:15%');
	//	setColumnWidth('addQuestionViewForm:quesTable','10%:40%:15%:30%:15%');
initialize();
		function initialize(){
			if(tableObject != null) {
			var tblWidth = document.getElementById('addQuestionViewForm:quesTable').offsetWidth;
			var rowlength = document.getElementById('addQuestionViewForm:quesTable').rows.length;
			if(rowlength>11){
				syncTables('addQuestionViewForm:quesHeadTable','addQuestionViewForm:quesTable');
				document.getElementById('addQuestionViewForm:quesHeadTable').width = tblWidth-17+"px";
				document.getElementById('quesitonTableDiv').style.width = tblWidth+"px";
				setColumnWidth('addQuestionViewForm:quesHeadTable','10%:33.25%:24.75%:20%:12%');	
				setColumnWidth('addQuestionViewForm:quesTable','10%:33.25%:24.75%:20%:12%');
			}else{
				document.getElementById('quesitonTableDiv').style.width = tblWidth+"px";
				setColumnWidth('addQuestionViewForm:quesHeadTable','10%:33.25%:24.75%:20%:12%');	
				setColumnWidth('addQuestionViewForm:quesTable','10%:33.25%:24.75%:20%:12%');	
			}	
			}
		}


		function getSelectedRow(selectedRow){
			document.getElementById('addQuestionViewForm:selectedRowHidden').value = selectedRow;
			document.getElementById('addQuestionViewForm:hideCommandLink').click();
			return false;
		}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="addQuestion" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
