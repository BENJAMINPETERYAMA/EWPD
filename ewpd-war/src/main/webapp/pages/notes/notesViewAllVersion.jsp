<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<script language="JavaScript" src="../../js/prototype.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Notes View All Versions</TITLE>
<base target="_self">
</HEAD>
<BODY onkeypress="return false;">
<f:view>
	<h:inputHidden id="typeList"
		value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}"></h:inputHidden>
	<h:inputHidden id="noteVersionList"
		value="#{notesBackingBean.viewAllNotes}"></h:inputHidden>
	<h:form id="viewAllVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%">
				<TABLE>
					<TBODY>
						<tr>
							<TD><!-- Insert WPD Message Tag --> <w:message></w:message></TD>
						</tr>
					</TBODY>
				</TABLE>

				<div id="searchPanelHeader" class="tabTitleBar"><b>All Versions</b></div>
				<br />
				<table id="newTable" align="left" cellpadding="0" cellspacing="0" border="0"
					width="100%">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left"><strong><h:outputText value="Name"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText value="Text"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText value="Type"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText value="Version"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText value="Status"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText
										value="Last Updated User"></h:outputText></strong></td>
									<td align="left"><strong><h:outputText
										value="Last Updated Date"></h:outputText></strong></td>
									<td align="left">&nbsp;</td>

								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:470; overflow:auto;width:100%;"><h:dataTable
							headerClass="dataTableHeader" id="previousVersionsTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							bgcolor="#cccccc"
							rendered="#{notesBackingBean.allVersionList != null}"
							value="#{notesBackingBean.allVersionList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="100%">
							<h:column>
								<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
								<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
								<h:inputHidden id="noteName_hidden"
									value="#{singleValue.noteName}"></h:inputHidden>
								<h:inputHidden id="noteNameOld"
									value="#{singleValue.oldNoteName}"></h:inputHidden>	
								<h:inputHidden id="noteId_hidden" value="#{singleValue.noteId}"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="noteText" value="#{singleValue.noteText}"></h:outputText>
								<h:inputHidden id="hidden_noteText"
									value="#{singleValue.noteText}"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="noteType" value="#{singleValue.noteType}">
								</h:outputText>
								<h:inputHidden id="hidden_noteType"
									value="#{singleValue.noteType}"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="noteVersion" value="#{singleValue.version}">
								</h:outputText>
								<h:inputHidden id="hidden_noteversion"
									value="#{singleValue.version}"></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="noteStatus" value="#{singleValue.status}" wrap>
								</h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="lastUser"
									value="#{singleValue.lastUpdatedUser}">
								</h:outputText>
								<h:inputHidden ></h:inputHidden>
							</h:column>
							<h:column>
								<h:outputText id="lastDate"
									value="#{singleValue.lastUpdatedTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:outputText>
								<h:outputText value="#{requestScope.timezone}"></h:outputText>
								<h:inputHidden value="#{singleValue.lastUpdatedTimestamp}">
									<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
								</h:inputHidden>
							</h:column>
							<h:column>
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								<h:commandButton alt="View" title="View" id="viewButton"
									image="../../images/view.gif"
									rendered="#{singleValue.state.validForView}"
									onclick="viewAction(); return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>

								<h:commandButton alt="Copy" title="Copy" id="basicCopy"
									image="../../images/copy.gif" value="Copy"
									rendered="#{singleValue.state.validForCopy}"
									onclick="submitToParentPage('NoteSearchForm:hiddenNoteIdForEdit','NoteSearchForm:hiddenNoteNameForEdit','NoteSearchForm:hiddenNoteVersionForEdit','NoteSearchForm:NoteTypeDesc','NoteSearchForm:copyNote');return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>

								<h:commandButton alt="Edit" title="Edit" id="basicEdit"
									image="../../images/edit.gif" value="Edit"
									rendered="#{singleValue.state.editableByUser}"
									onclick="submitToParentPage('NoteSearchForm:hiddenNoteIdForEdit','NoteSearchForm:hiddenNoteNameForEdit','NoteSearchForm:hiddenNoteVersionForEdit','NoteSearchForm:NoteTypeDesc','NoteSearchForm:retrieveNotes');return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>																
								<h:commandButton alt="Send For Test" title="Send For Test" id="sendForTest"
									rendered="#{singleValue.state.validForTest}"
									image="../../images/schedule_test.gif" value="Send For Test"
									onclick="performLCEvents('sendForTest');return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="Publish" title="Publish" id="publish"
									rendered="#{singleValue.state.validForPublish}"
									image="../../images/publish.gif" value="Publish"
									onclick="performLCEvents('publish');return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="testPass" title="testPass" id="testPass"
									image="../../images/test_successful.gif" value="testPass"
									onclick="performLCEvents('testPass');return false;"
									rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="testFail" title="testFail" id="testFail"
									image="../../images/test_failed.gif" value="testFail"
									onclick="performLCEvents('testFail');return false;"
									rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="Approve" title="Approve" id="approvalButton"
									image="../../images/approved.gif"
									onclick="performLCEvents('Approve');return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="Reject" title="Reject" id="rejectButton"
									image="../../images/rejected.gif"
									onclick="performLCEvents('Reject');return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:commandButton alt="CheckOut" title="CheckOut" id="checkOut"
									image="../../images/checkOut.gif" value="CheckOut"
									onclick="submitToParentPage('NoteSearchForm:hiddenNoteIdForEdit','NoteSearchForm:hiddenNoteNameForEdit','NoteSearchForm:hiddenNoteVersionForEdit','NoteSearchForm:NoteTypeDesc','NoteSearchForm:noteCheckout');return false;"
									rendered="#{singleValue.state.validForCheckOut}">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
								<h:outputText value=" " ></h:outputText>
								<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
									image="../../images/lockgreen.gif" value="Unlock"
									onclick="unlockNote();return false;"
									rendered="#{singleValue.state.validForUnlock}">
								</h:commandButton>
								<h:commandButton alt="Delete" title="Delete" id="basicDelete"
									image="../../images/delete.gif" value="Delete"
									rendered="#{singleValue.state.validForDelete}"
									onclick="getValuesForDelete();return false;">
									<f:verbatim> &nbsp;&nbsp;</f:verbatim>
								</h:commandButton>
							</h:column>
						</h:dataTable></div>
						</TD>
					</tr>
					<TR>
						<TD></TD>
					</TR>
				</table>
				<h:inputHidden id="oldNoteName"
					value="#{notesSearchBackingBean.oldNoteName}"></h:inputHidden> 
				<h:inputHidden id="selectedNoteVersion"
					value="#{notesSearchBackingBean.versionNo}"></h:inputHidden> <h:inputHidden
					id="noteLCEventAction" value="#{notesSearchBackingBean.action}"></h:inputHidden>
				<h:inputHidden id="hiddenNoteNameForLCEvents"
					value="#{notesSearchBackingBean.noteNameForLifeCycles}"></h:inputHidden>
				<h:inputHidden id="selectedNoteId"
					value="#{notesSearchBackingBean.noteId}"></h:inputHidden> <h:inputHidden
					id="selectedNoteName" value="#{notesSearchBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="selectedNoteNameForDelete"
					value="#{notesSearchBackingBean.noteNameForDelete}"></h:inputHidden>
				<h:inputHidden id="hiddenActionForViewAll"
					value="#{notesSearchBackingBean.actionForViewAll}"></h:inputHidden>
				<h:inputHidden id="ViewAllNoteTypeDesc"
					value="#{notesBackingBean.hiddenNoteTypeDesc}"></h:inputHidden> <h:inputHidden
					id="hiddenTrue" value="true"></h:inputHidden> <h:commandLink
					id="noteLifeCycleEvents" style="display:none; visibility: hidden;"
					action="#{notesSearchBackingBean.noteLifeCycleEvents}">
					<f:verbatim />
				</h:commandLink> <h:commandLink id="noteCheckout"
					style="display:none; visibility: hidden;"
					action="#{notesBackingBean.notesCheckout}">
					<f:verbatim />
				</h:commandLink> <h:commandLink id="noteDelete"
					style="display:none; visibility: hidden;"
					action="#{notesSearchBackingBean.deleteNotes}">
					<f:verbatim />
				</h:commandLink></td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
	</h:form>
	<form name="printForm"><input id="currentPrintPage" type="hidden"
		name="currentPrintPage" value="notesViewAllVersionsPrint"></form>
</f:view>
<SCRIPT>
	if(null != document.getElementById('viewAllVersionForm:previousVersionsTable')){
		setColumnWidth('resultHeaderTable','14%:12%:14%:6%:13%:10%:15%:18%');
		setColumnWidth('viewAllVersionForm:previousVersionsTable','14%:12%:14%:6%:13%:10%:15%:18%');
var relTblWidth = document.getElementById('newTable').offsetWidth;
	if(document.getElementById('viewAllVersionForm:previousVersionsTable').rows.length < 8){	
		document.getElementById('viewAllVersionForm:previousVersionsTable').width = relTblWidth+"px";
		document.getElementById('resultHeaderTable').width = relTblWidth+"px";
		setColumnWidth('viewAllVersionForm:previousVersionsTable','14%:12%:14%:6%:13%:10%:15%:18%');
		setColumnWidth('resultHeaderTable','14%:12%:14%:6%:13%:10%:15%:18%');	
	}else{	
		document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
		document.getElementById('viewAllVersionForm:previousVersionsTable').width = relTblWidth-17+"px";
		setColumnWidth('viewAllVersionForm:previousVersionsTable','14%:12%:14%:6%:13%:10%:15%:18%');
		setColumnWidth('resultHeaderTable','14%:12%:14%:6%:13%:10%:15%:18%');
	}
	}
	function submitToParentPage(parentid,parentname,parentversion,parentnoteType,parentLink){
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteId_hidden','viewAllVersionForm:selectedNoteId');
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteName');
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteType','viewAllVersionForm:ViewAllNoteTypeDesc');
				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteNameOld','viewAllVersionForm:selectedNoteName');
	            window.dialogArguments.document.getElementById(parentid).value = document.getElementById('viewAllVersionForm:selectedNoteId').value;
	            window.dialogArguments.document.getElementById(parentname).value = document.getElementById('viewAllVersionForm:selectedNoteName').value;
	            window.dialogArguments.document.getElementById(parentversion).value = document.getElementById('viewAllVersionForm:selectedNoteVersion').value;
	            window.dialogArguments.document.getElementById(parentnoteType).value = document.getElementById('viewAllVersionForm:ViewAllNoteTypeDesc').value;
	          //   window.dialogArguments.document.getElementById(parentnoteType).value = document.getElementById('viewAllVersionForm:oldNoteName').value;
	            window.dialogArguments.document.getElementById(parentLink).click();
	            window.returnValue = '';
	            window.close();
	      }
	function performLCEvents(action){
				copyToHidden('viewAllVersionForm:hiddenTrue','viewAllVersionForm:hiddenActionForViewAll');	
	            document.getElementById('viewAllVersionForm:noteLCEventAction').value = action;
	            if(action=='publish'){
	 				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
	                getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteNameOld','viewAllVersionForm:hiddenNoteNameForLCEvents');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');                
	               
					window.returnValue = 'refresh';
					
	
	            } else if(action == 'sendForTest'){                 
	 				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');                                                         
	                
					window.returnValue = 'refresh';
					
	
	            } else if(action == 'testPass'){
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');                            
	               
					window.returnValue = 'refresh';
					
	            } else if(action == 'testFail'){
	 				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');
	               
					window.returnValue = 'refresh';
					
	            } else if(action == 'Approve'){
	 				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteName');
	               	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteNameOld','viewAllVersionForm:hiddenNoteNameForLCEvents');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');
	               
					window.returnValue = 'refresh';
					
	            }else if(action == 'Reject'){
	 				getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:hiddenNoteNameForLCEvents');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteName');
					getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteNameOld','viewAllVersionForm:hiddenNoteNameForLCEvents');
	                setValueToHiddenFieldFromDataTable('viewAllVersionForm:previousVersionsTable', 'noteId_hidden', 'viewAllVersionForm:selectedNoteId', 'viewAllVersionForm:noteLifeCycleEvents');
	               
					window.returnValue = 'refresh';
					
	            }         
	}
	function viewAction(){  
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteId_hidden','viewAllVersionForm:selectedNoteId');
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteName');
	            getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
	            var noteIdValue = document.getElementById('viewAllVersionForm:selectedNoteId').value;
				var noteName = document.getElementById('viewAllVersionForm:selectedNoteName').value;
				var noteVersion = document.getElementById('viewAllVersionForm:selectedNoteVersion').value;
		 		var url = '../notes/notesView.jsp'+getUrl()+'?notesId='+noteIdValue+"&noteName="+escape(noteName)+"&noteVersion="+noteVersion;
				newWinForView=window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
	            }
	function getValuesForDelete() {
	                  var message = "Are you sure you want to delete?"
	                  if(window.confirm(message)){
							copyToHidden('viewAllVersionForm:hiddenTrue','viewAllVersionForm:hiddenActionForViewAll');	
	                        getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteId_hidden','viewAllVersionForm:selectedNoteId');
	                        getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteNameForDelete');
	                        getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','hidden_noteversion','viewAllVersionForm:selectedNoteVersion');
							getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteName_hidden','viewAllVersionForm:selectedNoteName');
							getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','noteNameOld','viewAllVersionForm:oldNoteName');
	                        document.getElementById('viewAllVersionForm:noteDelete').click();   
							window.returnValue = 'refresh';                      
	                  }else{
	                        return false;
	                  }
	}

	hideIfNoValue('resultHeaderDiv', 'previousVersionsTable');

	function hideIfNoValue(divId, tableName){
		hiddenIdObj = document.getElementById('viewAllVersionForm:'+tableName);
		hideDiv = document.getElementById(divId);
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			hideDiv.innerHTML = '';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}



</SCRIPT>
</BODY>
</HTML>






