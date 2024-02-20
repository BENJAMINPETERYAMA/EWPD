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

<TITLE>Questions Search</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>

<f:view>
	<BODY
		onkeypress="return submitOnEnterKey('QuestionSearchForm:locateButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{QuestionSearchBackingBean.questionDesc}">
			</h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form id="QuestionSearchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>

						<TR>
							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD width="100%" colspan="2"><w:message
										value="#{QuestionSearchBackingBean.validationMessages}"></w:message></TD>
								</TR>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<TR>
									<TD width="200"></TD>
									<TD width="200"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->


							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><BR>

							<!--	Start of Table for actual Data	-->
							<TABLE width="50%" border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>

									<TR>
										<TD width="27%"><h:outputText value="Question" /></TD>
										<TD colspan="2"><h:inputText styleClass="formInputField"
											id="txtQuestion"
											value="#{QuestionSearchBackingBean.criteriaQuestion}"
											maxlength="250" tabindex="1" /></TD>
									</TR>
									<tr>
										<TD width="27%"><h:outputText value="Functional Domain" /></td>
										<TD width="39%">
										<DIV id="FunctionalDomainDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="funDomain"
											value="#{QuestionSearchBackingBean.functionalDomain}"></h:inputHidden>
										</TD>
										<TD align="left" width="34%"><h:commandButton alt="Select" title="Select"
											id="Domain" image="../../images/select.gif"
											style="cursor: hand"
											onclick="domainClick('../popups/functionalDomainPopup.jsp','FunctionalDomainDiv','QuestionSearchForm:funDomain',3,1); return false;"
											tabindex="2"></h:commandButton></TD>
									</tr>
									
									<tr>
										<TD width="27%"><h:outputText value="Reference" /></td>
										<TD width="39%">
										<DIV id="referenceDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="refernce"
											value="#{QuestionSearchBackingBean.spsRefernceId}"></h:inputHidden>
										</TD>
										<TD align="left" width="34%"><h:commandButton alt="Select" title="Select"
											id="refe" image="../../images/select.gif"
											style="cursor: hand"
											onclick="loadMultiSearchPopup('searchSPS','Reference',
                                                         'SPS Parameter Popup','referenceDiv','QuestionSearchForm:refernce'); return false;"
											tabindex="2"></h:commandButton></TD>
									</tr>
									
									<TR>
										<TD width="27%"><h:outputText value="Data Type" /></TD>
										<TD width="39%">
										<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="txtDataType"
											value="#{QuestionSearchBackingBean.criteriaDataType}"></h:inputHidden>
										</TD>
										<TD align="left" width="34%"><h:commandButton alt="Select" title="Select"
											id="BusinessEntityButton" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopup.jsp', 'DataTypeDiv','QuestionSearchForm:txtDataType',2,2);return false;"
											tabindex="2"></h:commandButton></TD>
									</TR>

									<TR>
										<TD colspan="3"></TD>
									</TR>

									<TR>
										<TD colspan="3"><h:commandButton value="Locate"
											styleClass="wpdButton" id="locateButton" tabindex="3"
											action="#{QuestionSearchBackingBean.searchQuestion}">
										</h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							</DIV>

							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" style="width: 100%;">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" ><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Functional Domain"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Reference"></h:outputText></TD>
												<TD align="left" ><h:outputText value="Provider Arrangement"></h:outputText>
												<TD align="left" ><h:outputText value="Data Type"></h:outputText>
												</TD>
												<TD align="left" ><h:outputText value="Version"></h:outputText>
												</TD>
												<TD align="left" ><h:outputText value="Status"></h:outputText>
												</TD>
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
										style="height:100%;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="Question" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{QuestionSearchBackingBean.questionSearchResultList != null}"
										value="#{QuestionSearchBackingBean.questionSearchResultList}">
										<h:column>
											<h:inputHidden id="questionNumber"
												value="#{Question.questionNumber}"></h:inputHidden>
											<h:inputHidden id="questionVersion"
												value="#{Question.version}"></h:inputHidden>
											<h:inputHidden id="questionStatus" value="#{Question.status}"></h:inputHidden>
											<h:outputText id="questionDescription"
												value="#{Question.displayQuestion}"></h:outputText>
											<h:graphicImage id="lockImage"
												url="../../images/lock_icon.jpg" alt="Locked"
												rendered="#{Question.state.lockedByUser}"></h:graphicImage>
											<h:inputHidden id="questionDesc"
												value="#{Question.questionDesc}"></h:inputHidden>
											<h:inputHidden id="domainDesc" value="#{Question.domainDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="functionDomain"
												value="#{Question.domainDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="spsReference"
												value="#{Question.description}"></h:outputText>
											<h:inputHidden id="ref" value="#{Question.spsReference}"></h:inputHidden>	
											<h:inputHidden id="term" value="#{Question.term}"></h:inputHidden>	
											<h:inputHidden id="qualifer" value="#{Question.qualifier}"></h:inputHidden>	
										</h:column>
										<h:column>
											<h:outputText id="ProviderArrangement"
												value="#{Question.providerArrangement}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="DataType" value="#{Question.dataTypeName}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="verisonNumber" value="#{Question.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="statusType" value="#{Question.status}"></h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{Question.state.validForView}"
												onclick="performAction(1); return false;">
											</h:commandButton>

											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll"
												image="../../images/notes.gif"
												rendered="#{Question.state.validForView}"
												onclick="performAction(2); return false;">
											</h:commandButton>

											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="Edit" title="Edit" id="editButton"
												image="../../images/edit.gif"
												onclick="performAction(3);return false;"
												rendered="#{Question.state.editableByUser}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="scheduleForTest"
												image="../../images/schedule_test.gif"
												value="scheduleForTest"
												onclick="performAction(5);return false;"
												rendered="#{Question.state.validForTest && Question.status != 'SCHEDULED_FOR_TEST'}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Test Pass" title="Test Pass" id="testPass"
												image="../../images/test_successful.gif" value="testPass"
												onclick="performAction(6);return false;"
												rendered="#{Question.state.validForTestCompletion}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Test Fail" title="Test Fail" id="testFail"
												image="../../images/test_failed.gif" value="testPass"
												onclick="performAction(7);return false;"
												rendered="#{Question.state.validForTestCompletion}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Publish" title="Publish" id="publish"
												image="../../images/publish.gif" value="publish"
												onclick="performAction(8);return false;"
												rendered="#{Question.state.validForPublish}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>

											<h:commandButton alt="Checkout" title="Checkout" id="Checkout"
												image="../../images/checkOut.gif" value="Checkout"
												onclick="performAction(9);return false;"
												rendered="#{Question.adminMethodMapped}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<h:commandButton alt="delete" title="delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												onclick="performAction(4);return false;"
												rendered="#{Question.deleteFlag}">
												<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											</h:commandButton>
											<h:outputText value=" " id="a1spaceSpan09"
												rendered="#{Question.state.validForUnlock}"></h:outputText>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockedQuestion"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="performAction(10);return false;"
												rendered="#{Question.state.validForUnlock}">
											</h:commandButton>
											
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
				</TABLE></td>
		</tr>
		<!-- Space for hidden fields -->
		<h:inputHidden id="hQuestionNumber"
			value="#{QuestionSearchBackingBean.questionNumber}"></h:inputHidden>
		<h:inputHidden id="hQuestionDesc"
			value="#{QuestionSearchBackingBean.questionDesc}"></h:inputHidden>
		<h:inputHidden id="hQuestionVersion"
			value="#{QuestionSearchBackingBean.version}"></h:inputHidden>
		<h:inputHidden id="hQuestionStatus"
			value="#{QuestionSearchBackingBean.status}"></h:inputHidden>


		<!-- Space for hidden fields for view and view all-->
		<h:inputHidden id="hiddenNumberForView"
			value="#{questionViewBackingBean.questionNumber}"></h:inputHidden>
		<h:inputHidden id="hiddenDescForView"
			value="#{questionViewBackingBean.questionDesc}"></h:inputHidden>
		<h:inputHidden id="hiddenVersionForView"
			value="#{questionViewBackingBean.version}"></h:inputHidden>



		<h:inputHidden id="hiddenQuestionNumber"
			value="#{saveQuestionBackingBean.questionNumber}"></h:inputHidden>
		<h:inputHidden id="hiddenQuestionDesc"
			value="#{saveQuestionBackingBean.question}"></h:inputHidden>
		<h:inputHidden id="hiddenQuestionVersion"
			value="#{saveQuestionBackingBean.version}"></h:inputHidden>
		<h:commandLink id="editQuestion"
			style="display:none; visibility: hidden;"
			action="#{saveQuestionBackingBean.retriveQuestion}">
			<f:verbatim />
		</h:commandLink>

		<h:commandLink id="unlockQuestion"
			style="display:none; visibility: hidden;"
			action="#{QuestionSearchBackingBean.unlock}">
			<f:verbatim />
		</h:commandLink>

		<h:commandLink id="editViewAllQuestion"
			style="display:none; visibility: hidden;"
			action="#{saveQuestionBackingBean.retriveQuestion}">
			<f:verbatim />
		</h:commandLink>

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

		<h:inputHidden id="hiddenCheckOutFlag"
			value="#{saveQuestionBackingBean.checkOutFlag}"></h:inputHidden>
		<h:commandLink id="checkOutQuestion"
			style="display:none; visibility: hidden;"
			action="#{QuestionSearchBackingBean.checkOut}">
			<f:verbatim />
		</h:commandLink>

		<h:commandLink id="searchQuestion"
			style="display:none; visibility: hidden;"
			action="#{QuestionSearchBackingBean.searchQuestion}">
			<f:verbatim />
		</h:commandLink>
		<h:inputHidden id="spsreference"
			value="#{saveQuestionBackingBean.spsRefernceId}"></h:inputHidden>
		<h:inputHidden id="hiddenTerm"
			value="#{saveQuestionBackingBean.term}"></h:inputHidden>
		<h:inputHidden id="hiddenQualifier"
			value="#{saveQuestionBackingBean.qualifier}"></h:inputHidden>
		<!-- End of hidden fields -->


		<tr>
			<td> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</h:form>
	</BODY>

</f:view>
<script language="javascript">
	document.getElementById('QuestionSearchForm:txtQuestion').focus(); // for on load default selection
	copyHiddenToDiv_ewpd('QuestionSearchForm:txtDataType','DataTypeDiv',2,2); 
	copyHiddenToDiv_ewpd('QuestionSearchForm:funDomain','FunctionalDomainDiv',3,1); 
	copyHiddenToDiv_ewpd('QuestionSearchForm:refernce','referenceDiv',2,2); 

	function syncTables(){
		var relTblWidth = document.getElementById('QuestionSearchForm:searchResultTable').offsetWidth;
		document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
	}		
	var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
	if(document.getElementById('QuestionSearchForm:searchResultTable') != null) {
		setColumnWidth('QuestionSearchForm:searchResultTable','18%:20%:13%:10%:6%:5%:13%:15%');
		setColumnWidth('resultHeaderTable','18%:20%:13%:10%:6%:5%:13%:15%');
		showResultsTab();
	}else {
		var headerDiv = document.getElementById('resultHeaderDiv');
		headerDiv.style.visibility = 'hidden';
	}	
	

	if(document.getElementById('QuestionSearchForm:searchResultTable') != null){
		document.getElementById('QuestionSearchForm:searchResultTable').onresize = syncTables;
		syncTables();
	}

	
	/**
	Method used to call the particular action, when a button is clicked in the page.
	For each button, a particular actionType is passing.
	*/
	function performAction(actionType) {

		copyValuesToHidden();
		switch(actionType) {
			case 1:
				var url = '../question/questionView.jsp'+getUrl()+'?'+'temp='+Math.random()+'&questionNumber='+document.getElementById('QuestionSearchForm:hQuestionNumber').value+'&action=view&description='+escape(document.getElementById('QuestionSearchForm:hQuestionDesc').value)+'&version='+document.getElementById('QuestionSearchForm:hQuestionVersion').value;
				newWinForView=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
				break;			
			case 2:
				viewAllAction();
				break;			
			case 3:
				editSelectedQuestion();
				break;
			case 4:
				deleteSelectedQuestion();
				break;		
			case 5:
				document.getElementById('QuestionSearchForm:scheduleTestQuestion').click();
				break;			
			case 6:
				document.getElementById('QuestionSearchForm:testPassQuestion').click();
				break;			
			case 7:
				document.getElementById('QuestionSearchForm:testFailQuestion').click();
				break;			
			case 8:
				document.getElementById('QuestionSearchForm:publishQuestion').click();
				break;
			case 9:
				document.getElementById('QuestionSearchForm:checkOutQuestion').click();
				break;
			case 10:
				document.getElementById('QuestionSearchForm:unlockQuestion').click();
				break;
		}	
	}


	function copyValuesToHidden() {
	
		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionNumber', 'QuestionSearchForm:hQuestionNumber');
		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionDesc', 'QuestionSearchForm:hQuestionDesc');
		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionVersion', 'QuestionSearchForm:hQuestionVersion');
		//getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'ref', 'QuestionSearchForm:spsreference');
		//getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'term', 'QuestionSearchForm:hiddenTerm');
		//getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'qualifer', 'QuestionSearchForm:hiddenQualifier');
		
	}

	function editSelectedQuestion() {

		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionNumber', 'QuestionSearchForm:hiddenQuestionNumber');
		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionDesc', 'QuestionSearchForm:hiddenQuestionDesc');
		getFromDataTableToHidden('QuestionSearchForm:searchResultTable', 'questionVersion', 'QuestionSearchForm:hiddenQuestionVersion');
		document.getElementById('QuestionSearchForm:editQuestion').click();
		return false;
	}

	function deleteSelectedQuestion(){
		var message = 'Are you sure to delete the selected Question?';
		if (confirm(message) ){
			document.getElementById('QuestionSearchForm:deleteQuestion').click();
			return true;
		} else
			return false;
	}

function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

	function viewAllAction() {
		var viewAll = "viewAll";
		var url = '../question/questionViewAll.jsp'+getUrl()+'?'+'temp='+Math.random()+'&action=viewAll&description='+escape(document.getElementById('QuestionSearchForm:hQuestionDesc').value);
		var retValueFromChild = window.showModalDialog(url, window, "dialogHeight:650px;dialogWidth:1050px;resizable=false;status=no");
		if(retValueFromChild == 'REFRESH'){
		document.getElementById('QuestionSearchForm:searchQuestion').click();
		} else if(retValueFromChild == undefined) {
			return true;
		} else {
			var values = retValueFromChild.split("~");
			var action = values[0];
			var arg1 = values[1];
			var arg2 = values[2];
			var arg3 = values[3];

			if (action == 'EDIT') {
				document.getElementById('QuestionSearchForm:hiddenQuestionNumber').value = arg1;
				document.getElementById('QuestionSearchForm:hiddenQuestionDesc').value = arg2;
				document.getElementById('QuestionSearchForm:hiddenQuestionVersion').value = arg3;
				document.getElementById('QuestionSearchForm:editQuestion').click();
			} else if (action == 'CHECKOUT') {
				document.getElementById('QuestionSearchForm:hQuestionNumber').value = arg1;
				document.getElementById('QuestionSearchForm:hQuestionDesc').value = arg2;
				document.getElementById('QuestionSearchForm:hQuestionVersion').value = arg3;
				document.getElementById('QuestionSearchForm:checkOutQuestion').click();
			}
		}
	}

	function domainClick(url,targetDiv,targetHidden,attrCount,attrPosn){
		var lob = 'ALL~ALL';
	    var be = 'ALL~ALL';
	    var bg = 'ALL~ALL';
		var parentCatalog = 'Functional Domain';
		url = url + '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
		return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
    }
</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="questionSearchResultPrint"></form>
</HTML>
