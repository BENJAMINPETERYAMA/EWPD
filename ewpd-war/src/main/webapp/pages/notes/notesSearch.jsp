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

<TITLE>Notes Search</TITLE>
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
		onkeydown="return submitOnEnterKey('NoteSearchForm:locateButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy" value="#{notesSearchBackingBean.noteId}"></h:inputHidden>
			<td><jsp:include page="../navigation/top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="NoteSearchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>

						<TR>
							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD width="100%" colspan="2"><w:message
										value="#{notesSearchBackingBean.validationMessages}"></w:message></TD>
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
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>

									<TR>
										<TD width="15%"><h:outputText id="noteIdOutputTxt" value="Id"></h:outputText></TD>
										<TD colspan="15%"><h:inputText
											styleClass="selectDataDisplayDivForNotes" id="txtNoteId"
											maxlength="40" tabindex="1"
											value="#{notesSearchBackingBean.noteIdForLocate}"
											onkeypress="return isAlphaNumeric(event);" />&nbsp;</TD>
										<td width="5%"></td>

									</TR>

									<TR>
										<TD width="15%"><h:outputText id="noteName" value="Name"></h:outputText></TD>
										<TD colspan="15%"><h:inputText
											styleClass="selectDataDisplayDivForNotes" id="txtNoteName"
											maxlength="30" value="#{notesSearchBackingBean.noteName}"
											tabindex="1"></h:inputText>&nbsp;</TD>
										<td width="5%"></td>

									</TR>

									<TR>
										<TD width="15%"><h:outputText value="Type" /></TD>
										<h:inputHidden id="noteTypeHidden"
											value="#{notesSearchBackingBean.noteType}"></h:inputHidden>
										<TD width="15%">
										<div id="txtNoteType" class="selectDataDisplayDivForNotes"></div>
										</TD>
										<TD width="5%">&nbsp;&nbsp;&nbsp;<h:commandButton alt="Select" title="Select"
											id="noteTypeButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/noteTypePopup.jsp?lookUpAction='+'1'+'&parentCatalog='+'Note Type','txtNoteType','NoteSearchForm:noteTypeHidden',2,1);
																				return false;"
											tabindex="2">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="15%"><h:outputText id="systemDomain"
											value="Target Systems"></h:outputText></TD>
										<h:inputHidden id="txtsystemDomainHidden"
											value="#{notesSearchBackingBean.systemDomain}"></h:inputHidden>
										<TD width="15%">
										<div id="txtsystemDomain" class="selectDataDisplayDivForNotes"></div>
										</TD>
										<TD width="5%">&nbsp;&nbsp;&nbsp;<h:commandButton alt="Select" title="Select"
											id="systemDomainButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/systemDomainPopup.jsp','txtsystemDomain','NoteSearchForm:txtsystemDomainHidden',2,1);
																				return false;"
											tabindex="3">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="locateButton" value="Locate"
											style="cursor: hand"
											action="#{notesSearchBackingBean.locateNotes}" tabindex="9"></h:commandButton>
										</TD>
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
												<td align="left"><h:outputText value="Name"></h:outputText></td>
												<td align="left"><h:outputText value="Id"></h:outputText></td>
												<td align="left"><h:outputText value="Type"></h:outputText></td>
												<td align="left"><h:outputText value="Text"></h:outputText></td>
												<td align="left"><h:outputText value="Version"></h:outputText></td>
												<td align="left"><h:outputText value="Status"></h:outputText></td>
												<td align="left"><h:outputText value=" "></h:outputText></td>
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
										id="searchResultTable" var="Note" cellpadding="3"
										cellspacing="1" bgcolor="#cccccc"
										rendered="#{notesSearchBackingBean.notesSearchResultList != null}"
										value="#{notesSearchBackingBean.notesSearchResultList}"
										width="100%">
										<h:column>
											<h:outputText id="noteNameOP" value="#{Note.noteName}"></h:outputText>
											<h:inputHidden id="hiddenNoteName" value="#{Note.noteName}"></h:inputHidden>
											<h:inputHidden id="oldNoteName" value="#{Note.oldNoteName}"></h:inputHidden>
											<h:inputHidden id="hiddenNoteId" value="#{Note.noteId}"></h:inputHidden>
											<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{Note.state.lockedByUser}"></h:graphicImage>
										</h:column>
										<h:column>
											<h:outputText id="noteId" value="#{Note.noteId}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="noteTypeOP" value="#{Note.noteTypeDesc}"></h:outputText>
											<h:inputHidden id="hiddenNoteTypeDesc"
												value="#{Note.noteTypeDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="noteDesc" value="#{Note.noteText}"></h:outputText>
										</h:column>

										<h:column>
											<h:outputText id="versionOP" value="#{Note.version}"></h:outputText>
											<h:inputHidden id="hiddenVersionNo" value="#{Note.version}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="notesStatus" value="#{Note.status}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{Note.state.validForView}"
												onclick="viewAction(); return false;"></h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="View All Versions" title="View All Versions" id="basicViewAll"
												image="../../images/notes.gif"
												rendered="#{Note.state.validForView}"
												onclick="viewAllAction('../notes/notesViewAllVersion.jsp'); return false;"></h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Copy" title="Copy" id="basicCopy"
												rendered="#{Note.state.validForCopy}"
												image="../../images/copy.gif" value="Copy"
												onclick="getValuesForCopy();return false;"></h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="compare" title="compare" id="compare"
												image="../../images/compare.gif"
												rendered="#{Note.state.validForView}" value="testFail"
												onclick="compareNotes();return false;">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit"
												rendered="#{Note.state.editableByUser}"
												onclick="getValuesForEdit();return false;">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>

											<h:commandButton alt="Send For Test" title="Send For Test" id="sendForTest"
												rendered="#{Note.state.validForTest}"
												image="../../images/schedule_test.gif" value="Send For Test"
												onclick="sendForTest();return false;">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Publish" title="Publish" id="publish"
												rendered="#{Note.state.validForPublish}"
												image="../../images/publish.gif" value="Publish"
												onclick="publish();return false;">
											</h:commandButton>
											<h:outputText value="" rendered="true"></h:outputText>
											<h:commandButton alt="testPass" title="testPass" id="testPass"
												image="../../images/test_successful.gif" value="testPass"
												onclick="testPass();return false;"
												rendered="#{Note.state.validForTestCompletion}">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="testFail" title="testFail" id="testFail"
												image="../../images/test_failed.gif" value="testFail"
												onclick="testFail();return false;"
												rendered="#{Note.state.validForTestCompletion}"></h:commandButton>
											<h:commandButton alt="Approve" title="Approve" id="approvalButton"
												image="../../images/approved.gif"
												onclick="Approve();return false;"
												rendered="#{Note.state.validForApprovalCompletion}">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Reject" id="rejectButton"
												image="../../images/rejected.gif"
												onclick="Reject();return false;"
												rendered="#{Note.state.validForApprovalCompletion}">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="CheckOut" title="CheckOut" id="checkOut"
												image="../../images/checkOut.gif" value="CheckOut"
												onclick="checkOutAction();return false;"
												rendered="#{Note.state.validForCheckOut}">
											</h:commandButton>
											<h:outputText value=" " rendered="true"></h:outputText>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												rendered="#{Note.state.validForDelete}"
												onclick="getValuesForDelete();return false;">
											</h:commandButton>
											<h:outputText value=" " ></h:outputText>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="unlockNote();return false;"
												rendered="#{Note.state.validForUnlock}">
											</h:commandButton>
										</h:column>

									</h:dataTable></div>
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

<h:inputHidden id="oldNotes"
			value="#{notesSearchBackingBean.oldNoteName}"></h:inputHidden>
		<h:inputHidden id="hiddenNoteIdForEdit"
			value="#{notesBackingBean.noteId}"></h:inputHidden>
		<h:inputHidden id="hiddenNoteNameForEdit"
			value="#{notesBackingBean.name}"></h:inputHidden>
		<h:inputHidden id="hiddenNoteVersionForEdit"
			value="#{notesBackingBean.version}"></h:inputHidden>
		<h:inputHidden id="NoteTypeDesc"
			value="#{notesBackingBean.hiddenNoteTypeDesc}"></h:inputHidden>
		<h:commandLink id="retrieveNotes"
			style="display:none; visibility: hidden;"
			action="#{notesBackingBean.retrieveNotes}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="copyNote" style="display:none; visibility: hidden;"
			action="#{notesBackingBean.copyNotes}">
		</h:commandLink>

		<h:inputHidden id="hiddenNoteIdForLCEvents"
			value="#{notesSearchBackingBean.noteId}"></h:inputHidden>
		<h:inputHidden id="hiddenVersionNoForLCEvents"
			value="#{notesSearchBackingBean.versionNo}"></h:inputHidden>
		<h:inputHidden id="hiddenNoteNameForLCEvents"
			value="#{notesSearchBackingBean.noteNameForLifeCycles}"></h:inputHidden>
		<h:inputHidden id="actionLCEvents"
			value="#{notesSearchBackingBean.action}"></h:inputHidden>
		<h:commandLink id="noteLifeCycleEvents"
			style="display:none; visibility: hidden;"
			action="#{notesSearchBackingBean.noteLifeCycleEvents}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="noteCheckout"
			style="display:none; visibility: hidden;"
			action="#{notesBackingBean.notesCheckout}">
			<f:verbatim />
		</h:commandLink>

		<h:inputHidden id="hiddenNoteNameForDelete"
			value="#{notesSearchBackingBean.noteNameForDelete}"></h:inputHidden>
		<h:commandLink id="noteDelete"
			style="display:none; visibility: hidden;"
			action="#{notesSearchBackingBean.deleteNotes}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="pageRefresh"
			style="display:none; visibility: hidden;"
			action="#{notesSearchBackingBean.locateNotes}">
			<f:verbatim />
		</h:commandLink>
		<h:commandLink id="unlockNote"
			style="display:none; visibility: hidden;"
			action="#{notesSearchBackingBean.unlockNote}">
			<f:verbatim />
		</h:commandLink>
		<!-- End of hidden fields -->


		<tr>
			<td> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	<form name="printForm"><input id="currentPrintPage" type="hidden"
		name="currentPrintPage" value="notesSearchResultPrint">
		
		</h:form>
	</BODY>

</f:view>
<script language="javascript">
document.getElementById('NoteSearchForm:txtNoteId').focus(); // for on load default selection

		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'329',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('NoteSearchForm:searchResultTable') != null) {
			setColumnWidth('resultHeaderTable','23%:6%:12%:20%:6%:16%:18%');
			setColumnWidth('NoteSearchForm:searchResultTable','23%:6%:12%:20%:6%:16%:18%');
			showResultsTab();
			
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		

		if(document.getElementById('NoteSearchForm:searchResultTable') != null){
			document.getElementById('NoteSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}

		copyHiddenToDiv('NoteSearchForm:txtsystemDomainHidden','txtsystemDomain');	
		copyHiddenToDiv('NoteSearchForm:noteTypeHidden','txtNoteType');	
	
		function syncTables(){
			var relTblWidth = document.getElementById('NoteSearchForm:searchResultTable').offsetWidth;
			if(relTblWidth != 0)
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}		
	
		function getValuesForCopy() {
			   	var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var noteId = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteId";
				var noteIdValue = document.getElementById(noteId).value;
				document.getElementById('NoteSearchForm:hiddenNoteIdForEdit').value = noteIdValue;
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenNoteVersionForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteTypeDesc','NoteSearchForm:NoteTypeDesc');
				document.getElementById('NoteSearchForm:copyNote').click();
				return false;
			}
		
		function getValuesForEdit(){
				var e = window.event;
				var button_id = e.srcElement.id;
				var var1 = button_id.split(':');
				var rowcount = var1[2];
				var noteId = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteId";
				var noteIdValue = document.getElementById(noteId).value;
				var noteName = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteName";
				var noteNameValue = document.getElementById(noteName).value;
				var versionNo = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenVersionNo";
				var versionNoValue = document.getElementById(versionNo).value;	
				document.getElementById('NoteSearchForm:hiddenNoteIdForEdit').value = noteIdValue;
				document.getElementById('NoteSearchForm:hiddenNoteNameForEdit').value = noteNameValue;
				document.getElementById('NoteSearchForm:hiddenNoteVersionForEdit').value = versionNoValue;
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteTypeDesc','NoteSearchForm:NoteTypeDesc');
				document.getElementById('NoteSearchForm:retrieveNotes').click();
				return false;
		}
		function sendForTest(){
			document.getElementById('NoteSearchForm:actionLCEvents').value = "SendForTest";
			getValuesforLCEvents();				
		}
		
		function publish(){
			document.getElementById('NoteSearchForm:actionLCEvents').value = "Publish";
			getValuesforLCEvents();				
		}
		function testPass() {
			document.getElementById('NoteSearchForm:actionLCEvents').value = "TestPass";
			getValuesforLCEvents();				
		}
		function testFail() {				
			document.getElementById('NoteSearchForm:actionLCEvents').value = "TestFail";
			getValuesforLCEvents();				
		}			
		function checkOutAction(){
			document.getElementById('NoteSearchForm:actionLCEvents').value = "CheckOut";
			getValuesforLCEvents();				
		}
		function Approve(){
			document.getElementById('NoteSearchForm:actionLCEvents').value = "Approve";
			getValuesforLCEvents();				
		}
		function Reject() {
			document.getElementById('NoteSearchForm:actionLCEvents').value = "Reject";
			getValuesforLCEvents();				
		}

		function getValuesforLCEvents(){
			var e = window.event;
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			var noteId = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteId";
			var noteIdValue = document.getElementById(noteId).value;
			var noteName = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteName";
			var noteNameValue = document.getElementById(noteName).value;
			var versionNo = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenVersionNo";
			var versionNoValue = document.getElementById(versionNo).value;
			document.getElementById('NoteSearchForm:hiddenNoteIdForLCEvents').value = noteIdValue;
			document.getElementById('NoteSearchForm:hiddenVersionNoForLCEvents').value = versionNoValue;
			if(document.getElementById('NoteSearchForm:actionLCEvents').value == "CheckOut"){
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenNoteVersionForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteTypeDesc','NoteSearchForm:NoteTypeDesc');
				document.getElementById('NoteSearchForm:noteCheckout').click();				
			}
			else{
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForLCEvents');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForLCEvents');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenVersionNoForLCEvents');
				document.getElementById('NoteSearchForm:noteLifeCycleEvents').click();				
			}
		}
		
		// function to view the details 
		function viewAction(){	
			//var e = window.event;
			//var button_id = e.srcElement.id;
			//var var1 = button_id.split(':');
			//var rowcount = var1[2];
			//var noteId = "NoteSearchForm:searchResultTable:"+rowcount+":hiddenNoteId";
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForEdit');
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForEdit');
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenNoteVersionForEdit');
			var noteIdValue = document.getElementById('NoteSearchForm:hiddenNoteIdForEdit').value;
			var noteName = document.getElementById('NoteSearchForm:hiddenNoteNameForEdit').value;
			var noteVersion = document.getElementById('NoteSearchForm:hiddenNoteVersionForEdit').value;
		 	var url = '../notes/notesView.jsp'+getUrl()+'?notesId='+noteIdValue+"&noteVersion="+noteVersion+"&noteName="+escape(noteName);
			newWinForView=window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");		
		}

	function viewAllAction(page){
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForLCEvents');
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForLCEvents');
			var url=page+getUrl()+"?noteID="+document.getElementById('NoteSearchForm:hiddenNoteIdForLCEvents').value+'&noteName='+escape(document.getElementById('NoteSearchForm:hiddenNoteNameForLCEvents').value)+'&temp='+Math.random();
			var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");				
            if(returnvalue == undefined){
                  return true;
             }  
		if(returnvalue == 'refresh'){
			document.getElementById('NoteSearchForm:pageRefresh').click();
			}  	
		}

		
		// function to delete the notes
		function getValuesForDelete() {
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForLCEvents');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForDelete');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenVersionNoForLCEvents');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','oldNoteName','NoteSearchForm:oldNotes');
				submitLink('NoteSearchForm:noteDelete');					
			}else{
				return false;
			}
		}
		function compareNotes(){
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForEdit');
				getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenNoteVersionForEdit');
 				var page = '../notes/notesCompare.jsp';
				var url=page+getUrl()+"?noteID="+document.getElementById('NoteSearchForm:hiddenNoteIdForEdit').value+'&noteName='+escape(document.getElementById('NoteSearchForm:hiddenNoteNameForEdit').value) +'&version='+ document.getElementById('NoteSearchForm:hiddenNoteVersionForEdit').value+'&temp='+Math.random();
 				window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:470px;resizable=true;status=no;");				
		}

	// For checking the key pressed is digit 
 	function isAlphaNumeric(evt)
  	{
  		
     //var charCode = (evt.which) ? evt.which : event.keyCode
     //if (charCode > 31 && (charCode < 48 || charCode > 57))
       // return false;


	//return true;
		var k = document.all ? evt.keyCode : evt.which;
		return ((k > 47 && k < 58) || k == 8 || k == 0 || (k > 64 && k < 91) || (k > 96 && k < 123));
  	}
	
	if(document.getElementById('NoteSearchForm:searchResultTable')== null || document.getElementById('NoteSearchForm:searchResultTable')== undefined){
//		document.getElementById('printIconDiv').style.visibility = 'hidden';
//		document.getElementById('printIconDiv').innerHTML = '';
	}
	function unlockNote(){
		if(null != document.getElementById('NoteSearchForm:searchResultTable')){
			document.getElementById('NoteSearchForm:actionLCEvents').value = "unlock";
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteName','NoteSearchForm:hiddenNoteNameForLCEvents');
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenNoteId','NoteSearchForm:hiddenNoteIdForLCEvents');
			getFromDataTableToHidden('NoteSearchForm:searchResultTable','hiddenVersionNo','NoteSearchForm:hiddenVersionNoForLCEvents');
			document.getElementById('NoteSearchForm:noteLifeCycleEvents').click();	
		}

	}


</script>
</HTML>
