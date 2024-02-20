<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>Benefit Component Notes View</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
</HEAD>
<f:view>
<BODY><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="benefitComponentNotesForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel">
								<DIV class="treeDiv">	
								<jsp:include page="contractTree.jsp"></jsp:include>	
						 		</DIV>
							</TD>

	   <TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<TR>
											<TD>
												<w:message value="#{contractBenefitComponentNotesBackingBean.validationMessages}"></w:message>
												
											</TD>
										</TR>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->

							<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">


								
		              							<tr>
				                					<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
													<td width="186" class="tabNormal"><h:commandLink action= "#{contractComponentGeneralInfoBackingBean.retrieveBenefitComponent}">
													<h:outputText value="General Information" /></h:commandLink> </td> 
				                					<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
						
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink
											action="#{contractComponentGeneralInfoBackingBean.loadBenefits}">
											<h:outputText value="Benefits" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>
										<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:commandLink>
											<h:outputText value="Notes" /></h:commandLink></td>
										<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
										<td width="200">
											
										</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									
								<TABLE width="100%" cellspacing="0" cellpadding="0">
									
									<TR>
										<TD><BR/>
											<DIV id="noContractNote"
														style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:20px;
														background-color:#FFFFFF;">No
														Notes Attached.</DIV>							

											<DIV id="resultHeaderDiv">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0" width="100%">
													<TR>
														<TD><b>
															<h:outputText value="Associated Notes"></h:outputText>
														</b></TD>
													</TR>
												</TABLE>
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left" ><h:outputText value="Note ID "></h:outputText> 
															</TD>																										
															<TD >
																&nbsp;
															</TD>
														</TR>
													</TBODY>
												</TABLE>
											</DIV>
										</TD>
									</TR>
									<TR>
					<h:inputHidden id="resulthidden" value="#{contractBenefitComponentNotesBackingBean.hiddenNoteList}"></h:inputHidden>
										<TD>
											<DIV id="searchResultdataTableDiv" style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
		          								<!-- Search Result Data Table -->
			              						<h:dataTable styleClass="outputText" headerClass="dataTableHeader" id="searchResultTable" var="singleValue" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc" rendered="#{contractBenefitComponentNotesBackingBean.noteList != null}" value="#{contractBenefitComponentNotesBackingBean.noteList}" rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

													<h:column>	
														<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
														<h:inputHidden id="version" value="#{singleValue.version}"></h:inputHidden>													
														<h:inputHidden id="noteNameHidden" value="#{singleValue.noteName}"></h:inputHidden>													
														<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}">																											
														</h:outputText>
													</h:column>													
													<h:column>
										<h:commandButton alt="View Note" id="viewButton"
											image="../../images/view.gif"  rendered="#{contractBenefitComponentNotesBackingBean.securityAccess}" 
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+viewNoteVersion+'&temp=' + Math.random(),'dummyDiv','benefitComponentNotesForm:dummyHidden',1,1);return false;"></h:commandButton>
						
			              							</h:column>									
												</h:dataTable>
	          								</DIV>
										</TD>
									</TR>
								</TABLE>
								</FIELDSET>		
								<BR>
								
<DIV id="dummyDiv" style="visibility:hidden"></DIV>

<!--	End of Page data	-->							

					
<!-- Space for hidden fields -->			
					
					<h:inputHidden id="targetHiddenToStoreNoteId" value="#{contractBenefitComponentNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
<h:inputHidden id="targetHiddenToStoreVersion"
					value="#{contractBenefitComponentNotesBackingBean.versionToDelete}"></h:inputHidden>
					<h:inputHidden id="viewNoteId" value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
					<h:inputHidden id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
					<h:inputHidden id="viewNoteVersion" value="#{notesAttachmentBackingBean.version}"></h:inputHidden>
					<h:inputHidden id="dummyHidden" ></h:inputHidden>
					<h:commandLink id="deleteBenefitComponentNotes" style="display:none; visibility: hidden;" action="#{contractBenefitComponentNotesBackingBean.deleteBenefitComponentNotes}"> <f:verbatim /></h:commandLink>
<!-- End of hidden fields  -->
							</TD>
						</TR>
					</TABLE>
			</h:form>
			</TD>
		</TR>
		<TR>
			<TD>
				<%@include file="../navigation/bottom_view.jsp"%>
			</TD>
		</TR>
	</TABLE>
</hx:scriptCollector></BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBenefitComponentNotePrint" /></form>
<script language="JavaScript">	
var entityType = 'ATTACHCOMP';
var lookUpAction = 3;
var primaryEntityType = 'contract';

var noteIdForView; 
var noteNameForView; 
var viewNoteVersion;

	
	
	
	//Hide table if no value is present
	
	
	
	
	// For Deletion
	function confirmDeletion(){				
		var message = "Are you sure you want to detach the note?"		
		if(window.confirm(message)){			
			getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable', 'version', 'benefitComponentNotesForm:targetHiddenToStoreVersion');
			submitDataTableButton('benefitComponentNotesForm:searchResultTable', 'noteId', 'benefitComponentNotesForm:targetHiddenToStoreNoteId', 'benefitComponentNotesForm:deleteBenefitComponentNotes');
			
		}
		else{			
			return false;
		}
	}
	
	function viewAction(){
 		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','noteId','benefitComponentNotesForm:viewNoteId');
		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','noteNameHidden','benefitComponentNotesForm:viewNoteName');
		getFromDataTableToHidden('benefitComponentNotesForm:searchResultTable','version','benefitComponentNotesForm:viewNoteVersion');
		noteIdForView = document.getElementById('benefitComponentNotesForm:viewNoteId').value;
		noteNameForView = document.getElementById('benefitComponentNotesForm:viewNoteName').value;
		viewNoteVersion = document.getElementById('benefitComponentNotesForm:viewNoteVersion').value;

  }
 

hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitComponentNotesForm:searchResultTable:0:noteNameHidden');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('benefitComponentNotesForm:searchResultTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');
			document.getElementById('noContractNote').style.visibility = 'hidden';

		}
	}

</script>

<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
