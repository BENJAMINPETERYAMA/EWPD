<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css" title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>View Questionnaire</TITLE>
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
<h:inputHidden id="viewQuestionnaire" value="#{adminOptionQuestionnaireBackingBean.viewProductQuestionnaire}"></h:inputHidden>
			<TR>
				<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
			</TR>			

			<TR>
				<TD><h:form styleClass="form" id="questionnaireView">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
							<TBODY>
								<TR>
									<TD><w:message></w:message>
									</TD>
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


							<strong><h:outputText
								rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire == null}"
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
												<TD bgcolor="#cccccc" align="left" width="70%"><strong>&nbsp;Question
												[Reference]</strong></TD>
												<TD bgcolor="#cccccc" align="left" width="10%"><strong>Line
												of Business</strong></TD>
												<TD bgcolor="#cccccc" align="left" width="10%"><strong>Business
												Entity </strong></TD>
												<TD bgcolor="#cccccc" align="left" width="10%""><strong>Business
												Group</strong></TD>
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
											<h:inputHidden id="hiddenLevel" value="#{eachRow.level}"></h:inputHidden>
											<h:inputHidden id="hiddenQuestnrId"
												value="#{eachRow.questionnaireId}"></h:inputHidden>
											<h:outputText id="levelInd" value="#{eachRow.levelIndicator}"></h:outputText>
											<h:outputText id="assAnswerDesc"
												value="#{eachRow.answerDesc}"></h:outputText>
											<h:outputText id="assQuestionDesc"
												value="#{eachRow.questionDesc}"></h:outputText>
											<h:outputText id="assReferenceDesc"
												value="#{eachRow.referenceDesc}"></h:outputText>											
										</h:column>
										<h:column>
											<h:outputText id="lob" value="#{eachRow.lobString}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="be" value="#{eachRow.beString}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="bg" value="#{eachRow.bgString}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<br>
							<br />

							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="hiddenAdminOptionId"
						value="#{adminOptionQuestionnaireBackingBean.adminId}"></h:inputHidden>
					<h:inputHidden id="adminName"
						value="#{adminOptionQuestionnaireBackingBean.adminName}"></h:inputHidden>
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
var tableObjectQuest = document.getElementById('questionnaireView:associatedQuestionTable');
if (tableObjectQuest != null) {
	tablesizeQuest = tableObjectQuest.rows.length;
	for(var i=0;i<tablesizeQuest;i++){
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
if(document.getElementById('questionnaireView:associatedQuestionTable') != null) {
	var relTblWidth = document.getElementById('questionHeaderTable').offsetWidth;
	if(document.getElementById('questionnaireView:associatedQuestionTable').offsetHeight <= 240)
	{
		document.getElementById('questionnaireView:associatedQuestionTable').width = relTblWidth+"px";
		document.getElementById('questionHeaderTable').width = relTblWidth+"px";
		setColumnWidth('questionHeaderTable','70%:10%:10%:10%');
		setColumnWidth('questionnaireView:associatedQuestionTable','70%:10%:10%:10%');
		syncTables('questionHeaderTable','questionnaireView:associatedQuestionTable');
	}
	else
	{
		setColumnWidth('questionHeaderTable','70%:10%:10%:10%');
		setColumnWidth('questionnaireView:associatedQuestionTable','70%:10%:10%:10%');
		syncTables('questionHeaderTable','questionnaireView:associatedQuestionTable');
	}
}
	if(document.getElementById('questionnaireView:associatedQuestionTable') != null) {	
	}
	else{
		var divObj = document.getElementById('associatedQuestionsDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	

	}



</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptionQuestionnaire" /></form>

</HTML>

