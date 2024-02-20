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
	
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {

}.shortDescriptionColumn {
	width: 8%;
}

</style>

<TITLE>All Versions of Questions</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<BASE target="_self" />
</HEAD>

<f:view>
	<BODY onkeypress="return false;">
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<h:inputHidden id="view"
				value="#{QuestionSearchBackingBean.viewQuestion}" />
			<TD><jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include></TD>
		</TR>

		<tr>
			<td><h:form styleClass="form" id="viewAllVersionsForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>
						<!-- Table containing Tabs -->
						<DIV id="panel2">
						<DIV id="panel2Header" class="tabTitleBar"
							style="position:relative; cursor:hand;">View All Versions</DIV>
						<DIV id="panel2Content" class="tabContentBox"
							style="position:relative;font-size:10px;width:100%;"><BR>

						<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
							<TR>
								<TD>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left" ><h:outputText value="Question"></h:outputText></TD>
											<TD align="left" ><h:outputText value="Functioanl Domain"></h:outputText></TD>
											<TD align="left" ><h:outputText value="Provider Arrangement"></h:outputText></TD>
											<TD align="left" ><h:outputText value="SPS Reference"></h:outputText></TD>
											<TD align="left" ><h:outputText value="Data Type"></h:outputText></TD>
											<TD align="left" ><h:outputText value="Version"></h:outputText></TD>
											<TD align="left" ><h:outputText value="Status"></h:outputText></TD>
											<TD align="left" ><h:outputText></h:outputText></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD>
								<DIV id="searchResultdataTableDiv"
									style="height:470;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,selectOrOptionColumn,selectOrOptionColumn,selectOrOptionColumn,selectOrOptionColumn,selectOrOptionColumn,shortDescriptionColumn,selectOrOptionColumn"
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{QuestionSearchBackingBean.questionsList != null}"
									value="#{QuestionSearchBackingBean.questionsList}">
									<h:column>
										<h:outputText id="question"
											value="#{singleValue.questionDesc}"></h:outputText>
										<h:inputHidden id="questionDesc"
											value="#{singleValue.questionDesc}"></h:inputHidden>
										<h:graphicImage id="lockImage"
											url="../../images/lock_icon.jpg" alt="Locked"
											rendered="#{singleValue.state.lockedByUser}"></h:graphicImage>
									</h:column>
									<h:column>
										<h:outputText id="functionalDomain"
											value="#{singleValue.domainDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="providerArrangement"
											value="#{singleValue.providerArrangement}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="spsReference"
											value="#{singleValue.description}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="dataTypeName"
											value="#{singleValue.dataTypeName}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="verisonNumber"
											value="#{singleValue.version}"></h:outputText>
										<h:inputHidden id="questionVersion"
											value="#{singleValue.version}"></h:inputHidden>

										<h:inputHidden id="questionNumber"
											value="#{singleValue.questionNumber}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:outputText id="status" value="#{singleValue.status}"></h:outputText>
								
									</h:column>
									<h:column>
										<h:commandButton alt="View" title="View" id="viewButton"
											image="../../images/view.gif"
											rendered="#{singleValue.state.validForView}"
											onclick="performAction(1); return false;"></h:commandButton>

										<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="Delete" title="Delete" id="basicDelete"
											image="../../images/delete.gif" value="Delete"
											rendered="#{singleValue.deleteFlag}"
											onclick="performAction(3); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Edit" title="Edit" id="basicEdit"
											image="../../images/edit.gif" value="Edit"
											rendered="#{singleValue.state.editableByUser}"
											onclick="performAction(2); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="scheduleForTest"
											image="../../images/schedule_test.gif"
											value="scheduleForTest"
											rendered="#{singleValue.state.validForTest 
											&& singleValue.status != 'SCHEDULED_FOR_TEST'}"
											onclick="performAction(4); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
											image="../../images/test_successful.gif" value="testPass"
											rendered="#{singleValue.state.validForTestCompletion}"
											onclick="performAction(5); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Test Fail" title="Test Fail" id="testFail"
											image="../../images/test_failed.gif" value="testPass"
											rendered="#{singleValue.state.validForTestCompletion}"
											onclick="performAction(6); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Publish" title="Publish" id="publish"
											image="../../images/publish.gif" value="publish"
											rendered="#{singleValue.state.validForPublish}"
											onclick="performAction(7); return false;">
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Checkout" title="Checkout" id="Checkout"
											image="../../images/checkOut.gif" value="Checkout"
											rendered="#{singleValue.adminMethodMapped}"
											onclick="performAction(8); return false;"></h:commandButton>
										<h:outputText value=" " id="a1spaceSpan09"
											rendered="#{singleValue.state.validForUnlock}"></h:outputText>
										<h:commandButton alt="Unlock" title="Unlock" id="unlockedQuestion"
											image="../../images/lockgreen.gif" value="Unlock"
											onclick="performAction(9);return false;"
											rendered="#{singleValue.state.validForUnlock}">
										</h:commandButton>
									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
							<tr>
								<td></TD>
							</tr>
						</TABLE>
						</TD>
					</TR>
				</table>
				<h:inputHidden id="hQuestionNumber"
					value="#{QuestionSearchBackingBean.questionNumber}"></h:inputHidden>
				<h:inputHidden id="hQuestionDesc"
					value="#{QuestionSearchBackingBean.questionDesc}"></h:inputHidden>
				<h:inputHidden id="hQuestionVersion"
					value="#{QuestionSearchBackingBean.version}"></h:inputHidden>

				<h:inputHidden id="pageFlag"
					value="#{QuestionSearchBackingBean.lastPage}"></h:inputHidden>

				<h:commandLink id="deleteQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.deleteQuestion}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="scheduleTestQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.scheduleTest}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="publishQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.publish}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testPassQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.testPass}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testFailQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.testFail}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="unlockQuestion"
					style="display:none; visibility: hidden;"
					action="#{QuestionSearchBackingBean.unlock}">
					<f:verbatim />
				</h:commandLink>

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script>
	// Space for page realated scripts
	if(document.getElementById('viewAllVersionsForm:searchResultTable')!= null) {
		setColumnWidth('viewAllVersionsForm:searchResultTable', '18%:20%:13%:10%:6%:5%:13%:15%');
		setColumnWidth('resultHeaderTable', '18%:20%:13%:10%:6%:5%:13%:15%');
	}else{
		var divObj = document.getElementById('resultHeaderDiv');
		divObj.style.visibility = 'hidden';
		divObj.style.height = "0px";
		divObj.style.position = 'absolute';
	}


	
	/**
	Method used to call the particular action, when a button is clicked in the page.
	For each button, a particular actionType is passing.
	*/
	function performAction(actionType) {

		copyValuesToHidden();
		switch(actionType) {
			case 1:
				var url = '../question/questionView.jsp'+getUrl()+'?'+'temp='+Math.random()+'&questionNumber='+document.getElementById('viewAllVersionsForm:hQuestionNumber').value+'&action=view&description='+escape(document.getElementById('viewAllVersionsForm:hQuestionDesc').value)+'&version='+document.getElementById('viewAllVersionsForm:hQuestionVersion').value;
				newWinForView=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
				break;			
			case 2:
				editAction();
				break;
			case 3:
				deleteAction();
				break;		
			case 4:
				document.getElementById('viewAllVersionsForm:scheduleTestQuestion').click();
				window.returnValue = "REFRESH";
				break;			
			case 5:
				document.getElementById('viewAllVersionsForm:testPassQuestion').click();
				window.returnValue = "REFRESH";
				break;			
			case 6:
				document.getElementById('viewAllVersionsForm:testFailQuestion').click();
				window.returnValue = "REFRESH";
				break;			
			case 7:
				document.getElementById('viewAllVersionsForm:publishQuestion').click();
				window.returnValue = "REFRESH";
				break;
			case 8:
				checkOutAction();
				break;
			case 9:
				
				document.getElementById('viewAllVersionsForm:unlockQuestion').click();
				window.returnValue = "REFRESH";
				break;	
		}	
	}

	//Method fot edit action
	function editAction(){
		var arg1 = document.getElementById('viewAllVersionsForm:hQuestionNumber').value;
		var arg2 = document.getElementById('viewAllVersionsForm:hQuestionDesc').value;
		var arg3 = document.getElementById('viewAllVersionsForm:hQuestionVersion').value;
		window.returnValue = "EDIT"+"~"+arg1+"~"+arg2+"~"+arg3;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hiddenQuestionNumber').value = arg1;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hiddenQuestionDesc').value = arg2;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hiddenQuestionVersion').value = arg3;
		window.dialogArguments.document.getElementById('QuestionSearchForm:editQuestion').click();
		window.close();
	}
	function unlockAction(){
		var arg1 = document.getElementById('viewAllVersionsForm:hQuestionNumber').value;
		var arg2 = document.getElementById('viewAllVersionsForm:hQuestionDesc').value;
		var arg3 = document.getElementById('viewAllVersionsForm:hQuestionVersion').value;
		window.returnValue = "UNLOCK"+"~"+arg1+"~"+arg2+"~"+arg3;
		//window.close();
	}

	//Method for check out action
	function checkOutAction(){
		var arg1 = document.getElementById('viewAllVersionsForm:hQuestionNumber').value;
		var arg2 = document.getElementById('viewAllVersionsForm:hQuestionDesc').value;
		var arg3 = document.getElementById('viewAllVersionsForm:hQuestionVersion').value;
		window.returnValue = "CHECKOUT"+"~"+arg1+"~"+arg2+"~"+arg3;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hQuestionNumber').value = arg1;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hQuestionDesc').value = arg2;
		window.dialogArguments.document.getElementById('QuestionSearchForm:hQuestionVersion').value = arg3;
		window.dialogArguments.document.getElementById('QuestionSearchForm:checkOutQuestion').click();
		window.close();
	}

	//Method for delete action
	function deleteAction(){
		var message = 'Are you sure to delete the selected Question version?';
		if (confirm(message) ){
			document.getElementById('viewAllVersionsForm:deleteQuestion').click();
			window.returnValue = "REFRESH";
			return true;
		} else {
			return false;
		}
	}

	//Method to copy values to hidden variables
	function copyValuesToHidden() {
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'questionNumber', 'viewAllVersionsForm:hQuestionNumber');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'questionDesc', 'viewAllVersionsForm:hQuestionDesc');
		getFromDataTableToHidden('viewAllVersionsForm:searchResultTable', 'questionVersion', 'viewAllVersionsForm:hQuestionVersion');
		document.getElementById('viewAllVersionsForm:pageFlag').value = "viewAllPage";
	}


</script>
</html>
