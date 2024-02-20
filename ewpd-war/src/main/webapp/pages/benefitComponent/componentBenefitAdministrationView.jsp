<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/ComponentBenefitAdministration.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Component Benefit Administration View</TITLE>
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
	<h:inputHidden value="#{BenefitComponentAdministrationBackingBean.loadQuestionForView}" />
	<h:form styleClass="form" id="bcBenefitAdministrationViewForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">
						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>
						</TD>

						<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="150">
								<table width="150" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:commandLink>
											<h:outputText value="Questions" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:510">
						<div id="messageTextForNoQuestionsDiv"><br />
						<STRONG>&nbsp;<h:outputText value="No Question available." /></STRONG>
						</div>
						<div id="resultDiv">
						<TABLE border="0" cellspacing="1" cellpadding="0" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td valign="middle">
									<div id="LabelHeaderDiv"
										style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
									<B> <h:outputText value="Selected Questions"></h:outputText> </B>
									</div>
									</td>
								</TR>
								<tr>
									<td>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="40%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="30%"><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" width="10%"><h:outputText value="Notes"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
								</TR>
								<TR>
									<td>
									<DIV id="searchResultdataTableDiv"
										style="height:370px;width:100%;overflow-y:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{BenefitComponentAdministrationBackingBean.viewQuestionnaireList != null}"
										rowClasses="dataTableOddRow"
										value="#{BenefitComponentAdministrationBackingBean.viewQuestionnaireList}"
										border="0" width="100%">
										<h:column>
											<h:outputText id="questionName"
												value="#{singleValue.questionName}"></h:outputText>
											<h:inputHidden id="hidden_questionName"
												value="#{singleValue.questionName}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:outputText>
											<h:inputHidden id="hidden_ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="refDesc"
												value="#{singleValue.referenceDesc}"></h:outputText>
											<h:inputHidden id="hidden_level" value="#{singleValue.level}"></h:inputHidden>
										</h:column>
										<h:column>
										<h:inputHidden id="noteStat" value="#{singleValue.notesExists}"></h:inputHidden>
										<h:inputHidden id="questnId" value="#{singleValue.questionId}"></h:inputHidden>
										<h:inputHidden id="adminLvlOpnId" value="#{singleValue.adminLevelOptionSysId}"></h:inputHidden>
										<h:commandButton alt="View" id="viewButton" 
														 image="../../images/notes_exist.gif"
														onclick="viewAction(); return false;" rendered="#{singleValue.noteAttached}"></h:commandButton>
										<h:commandButton alt="View" id="viewButton1" 
														 image="../../images/page.gif"
														 onclick="viewAction(); return false;" rendered="#{singleValue.noNoteAttached}"></h:commandButton>				
										</h:column>
									</h:dataTable></DIV>
									</td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>
					<h:inputHidden id="qstnId"
								value="#{BenefitComponentAdministrationBackingBean.questionId}"></h:inputHidden>
					<h:inputHidden id="prmyId"
								value="#{BenefitComponentAdministrationBackingBean.primaryEntityID}"></h:inputHidden>
					<h:inputHidden id="bcId"
								value="#{BenefitComponentAdministrationBackingBean.benefitComponentId}"></h:inputHidden>
					<h:inputHidden id="admmLvlOptId"
								value="#{BenefitComponentAdministrationBackingBean.adminLvlOptionAssnSysId}"></h:inputHidden>			
				<!-- Space for hidden fields --> <h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					
				</h:commandLink> <!-- End of hidden fields  --></td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		<!-- WAS 6.0 migration changes - fix for SIT defect WLPRD00080801 -->
		</h:form>
	</BODY>
</f:view>

<script>

	var tableObject = document.getElementById('bcBenefitAdministrationViewForm:searchResultTable'); 

	if(tableObject!= null && tableObject.rows.length > 0) {

			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			var rowlength = document.getElementById('bcBenefitAdministrationViewForm:searchResultTable').rows.length;
			document.getElementById('bcBenefitAdministrationViewForm:searchResultTable').width = relTblWidth+"px";
			syncTables('resultHeaderTable','bcBenefitAdministrationViewForm:searchResultTable');
		    setColumnWidth('resultHeaderTable','40%:20%:30%:10%');
			setColumnWidth('bcBenefitAdministrationViewForm:searchResultTable','40%:20%:30%:10%');	
			var divObj = document.getElementById('messageTextForNoQuestionsDiv');
			divObj.style.visibility = 'hidden';	
			divObj.style.height = "2px";				
	} else{
		var divHeader = document.getElementById('resultDiv');
		divHeader.style.visibility = 'hidden';	
		divHeader.style.height = "2px";	
	}
	for(var i=0; i<tableObject.rows.length; i++){
		var rowobj = tableObject.rows[i];
		var cellobj = tableObject.rows[i].cells[2];
		var text = cellobj.children[1].value;
		if(text ==1){
			rowobj.className = 'dataTableEvenRow';
		}
	}
function viewAction(){


getFromDataTableToHidden('bcBenefitAdministrationViewForm:searchResultTable','questnId','bcBenefitAdministrationViewForm:qstnId');
getFromDataTableToHidden('bcBenefitAdministrationViewForm:searchResultTable','adminLvlOpnId','bcBenefitAdministrationViewForm:admmLvlOptId');

var param = new Object();

var questionId=document.getElementById('bcBenefitAdministrationViewForm:qstnId').value;
var primaryentityId=document.getElementById('bcBenefitAdministrationViewForm:prmyId').value;
var primaryEntytyType="ATTACHCOMP";
var secondaryEntityType="ATTACHQUESTION";
var bcId=document.getElementById('bcBenefitAdministrationViewForm:bcId').value;
var adminLvlOptionId=document.getElementById('bcBenefitAdministrationViewForm:admmLvlOptId').value;

var retValue = window.showModalDialog('../popups/bcQuestionNotesViewPopup.jsp'+getUrl()+'?&questionId='+questionId+'&primaryentityId='+primaryentityId+'&primaryEntytyType='+primaryEntytyType+'&bcId='+bcId+'&adminLvlOptionId='+adminLvlOptionId+'&secondaryEntityType='+secondaryEntityType,param,"dialogHeight:465px;dialogWidth:512px;resizable=false;status=no;");

					

}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="componentBenefitAdministration" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
