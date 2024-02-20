<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<base target=_self>
<TITLE>Benefit Level Notes Popup</TITLE>
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
<style type="text/css">
.treeNodeImage_WithNoBorder {
	border: none;
}
</style>

</HEAD>
<f:view>
<BODY onunload="changeParentImage()">

				<h:form styleClass="form" id="benefitNotesForm">

		<h:inputHidden id="loadAssociatedNotes" value="#{benefitLevelNotesAttachmentBackingBean.loadAssociatedNotes}" ></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel">
							<jsp:include page="benefitLevelNotesTree.jsp"></jsp:include>
							</TD>

	   <TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<TR>
											<TD>
												<w:message value="#{benefitLevelNotesAttachmentBackingBean.validationMessages}"></w:message>
												
											</TD>
										</TR>		
									</TBODY>
	
							</TABLE>
<!-- Table containing Tabs -->

		<h:inputHidden id="bnftLineId" value="#{benefitLevelNotesAttachmentBackingBean.bnftLineId}" ></h:inputHidden>
		<h:inputHidden id="bnftTermCode" value="#{benefitLevelNotesAttachmentBackingBean.benefitTermCode}" ></h:inputHidden>
		<h:inputHidden id="bnftQualifierCode" value="#{benefitLevelNotesAttachmentBackingBean.bnftQualifierCode}" ></h:inputHidden>

	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
		<TR>								
										<TD width="25%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
												<TD width="186" class="tabActive"> <h:outputText value="Notes Attachment" /> </TD> 
												<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
											</TR>
											</TABLE>
										</TD>
	
	
									<TD width="100%">
									</TD>
									</TR>
									</TABLE>	
<!-- End of Tab table -->

								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">							

										

								
					
								<TR >	
																							
									<TD valign="top" width="125" ><h:outputText value="Note Name*" 
									styleClass="#{benefitLevelNotesAttachmentBackingBean.requiredNoteName?'mandatoryError': 'mandatoryNormal'}" ></h:outputText></TD>
									<TD  align="left" width="200"><h:inputText id="noteSearchCriteria" tabindex="1"
													styleClass="formInputField" onkeypress="return callNoteSelect();"></h:inputText>
									<TD align="left" valign="top" width="633">	
										<h:graphicImage url="" value="../../../images/autoComplete.gif"
										alt="Select" style="cursor:hand;" width="15" height="15" onclick="getNotesPopUp()"	>
										</h:graphicImage>
									</TD>																										
								</TR>
								<TR id="divRow" style="display:none;">
									<TD valign="top" width="125" ></TD>
									<TD  align="left" width="200"><DIV id="selectedNotesDiv" class="selectDataDisplayDiv"></DIV></TD>
								</TR>
								<tr>
									<TD width="110">
									</TD>
								</tr>
															
											<TR>
												<TD width="110">
													<h:commandButton value="Attach" styleClass="wpdButton" tabindex="7" action="#{benefitLevelNotesAttachmentBackingBean.attachNotesForBenefitLine}"> </h:commandButton>
												</TD>
												
											</TR>
								
            					</TABLE>
									<br/>
									<TABLE width="100%" cellspacing="0" cellpadding="0">
									<TR>
										<TD>
											<DIV id="resultHeaderDiv">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0" width="100%">
													<TR>
														<TD>
															<b><h:outputText value="Associated Notes"></h:outputText></b>
														</TD>
													</TR>
												</TABLE>
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left" ><h:outputText value="Note Name "></h:outputText> 
															</TD>																										
															<TD><h:outputText value="Delete "></h:outputText>
																&nbsp;
															</TD>
														</TbR>
													</TBODY>
												</TABLE>
											</DIV>
										</TD>
									</TR>
									<TR>
									<TD>
											<DIV id="searchResultdataTableDiv" style="height:290px;position:relative;z-index:1;font-size:10px;overflow:auto;">
		          								<!-- Search Result Data Table -->
												<h:dataTable styleClass="outputText" headerClass="dataTableHeader" id="searchResultTable"
												 var="singleValue" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc"
												 rendered="#{benefitLevelNotesAttachmentBackingBean.associatedNotesList != null}"
												 value="#{benefitLevelNotesAttachmentBackingBean.associatedNotesList}" rowClasses="dataTableEvenRow,dataTableOddRow" border="0">
													<h:column>	
														<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>													
														<h:inputHidden id="noteNameHidden" value="#{singleValue.noteName}"></h:inputHidden>	
														<h:inputHidden id="noteVersionHidden" value="#{singleValue.version}"></h:inputHidden>													
														<h:outputText id="notesName" value="#{singleValue.noteName}">																											
														</h:outputText>
													</h:column>		
											
													<h:column>
														<h:selectBooleanCheckbox onclick="enableDisableDelete('benefitNotesForm:searchResultTable', 1, 0, 'benefitNotesForm:deleteButton');"></h:selectBooleanCheckbox>
			              							</h:column>	
								
												</h:dataTable>
	          								</DIV>
										</TD>
									</TR>
									<tr>
										<td>
											<h:commandButton value="Delete" id="deleteButton" styleClass="wpdButton" action="#{benefitLevelNotesAttachmentBackingBean.deleteBenefitLineNotes}" onclick="return confirmDeletion();"></h:commandButton>
										</td>
									</tr>
								</TABLE>

								</FIELDSET>									
					</TD>
						</TR>
						
					</table>
					
<!--	End of Page data	-->							
<DIV id="dummyDiv" style="visibility:hidden"></DIV>
					
<!-- Space for hidden fields -->		
			<h:inputHidden id="targetHiddenToStoreNoteId" value="#{benefitLevelNotesAttachmentBackingBean.benefitLineNoteId}" ></h:inputHidden>
			<h:inputHidden id="selectedNotes" value="#{benefitLevelNotesAttachmentBackingBean.noteName}"></h:inputHidden>		
			<h:inputHidden id="dummyHidden" ></h:inputHidden>
			<h:inputHidden id="selectedNoteIds" value="#{benefitLevelNotesAttachmentBackingBean.noteIdsForDeletion}"></h:inputHidden>
			<h:commandLink id="deleteBenefitLineNotes" style="display:none; visibility: hidden;" 
					action="#{benefitLevelNotesAttachmentBackingBean.deleteBenefitLineNotes}"> <f:verbatim /></h:commandLink>
					
<!-- End of hidden fields  -->
						
			</td>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	</table>
	</h:form>
</BODY>
</f:view>


<script language="JavaScript">	
	//Script for changing the notes icon in the parent page.
	function changeParentImage(){
		var tableObject = document.getElementById('benefitNotesForm:searchResultTable');
		if(null != tableObject && tableObject.rows.length > 0)
			window.returnValue = "notes_exists";
		else
			window.returnValue = "no_notes";
	}
	window.onload = clearInputField;
	function clearInputField(){
		document.getElementById('benefitNotesForm:noteSearchCriteria').value = "";
	}
	enableDisableDelete('benefitNotesForm:searchResultTable', 1, 0, 'benefitNotesForm:deleteButton');
	//Hide table if no value is present

	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitNotesForm:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			getObj('benefitNotesForm:deleteButton').style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('benefitNotesForm:searchResultTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}

	function confirmDeletion(){				
	// For Deletion
		var message = "Are you sure you want to detach the selected notes?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('benefitNotesForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;

		delNoteId = getObj('benefitNotesForm:selectedNoteIds');
		delNoteId.value = '';
		var noteCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			if(cur_row.cells[1].children[0].checked) {
				if(noteCount != 0) 
					delNoteId.value += '~';
				delNoteId.value += cur_row.cells[0].children[0].value;
				noteCount ++;
			}
		}
		if(noteCount > 0)
			return true;
		return false;
	}
	
	
function getNotesPopUp(){
		var bnftLineId = document.getElementById('benefitNotesForm:bnftLineId').value;
		var bnftTermCode = document.getElementById('benefitNotesForm:bnftTermCode').value;
		var bnftQualifierCode = document.getElementById('benefitNotesForm:bnftQualifierCode').value;
		var noteName= document.getElementById('benefitNotesForm:noteSearchCriteria').value;
		var url = '../standardBenefit/benefitLineNotesPopUp.jsp'+getUrl()+'?&temp = ' + Math.random() + '&bnftLineId='+ bnftLineId + '&bnftTermCode='+ bnftTermCode + '&bnftQualifierCode=' + bnftQualifierCode+'&noteSearchCriteria='+noteName;
		ewpdModalWindow_ewpd( url , 'selectedNotesDiv', 'benefitNotesForm:selectedNotes', 3, 1);
		showDiv();
	}

function callNoteSelect(){
	if(window.event.keyCode == 13){
		getNotesPopUp();
		return false;
	}else{
		return true;
	}
}
showDiv();
copyHiddenToDiv_ewpd("benefitNotesForm:selectedNotes", "selectedNotesDiv", 3, 1);
function showDiv(){
var selNote = document.getElementById("benefitNotesForm:selectedNotes").value ;
	if(null==selNote || ""==selNote)
		divRow.style.display='none';
	else
		divRow.style.display='';
}
function syncTables(){
			var relTblWidth = document.getElementById('benefitNotesForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth ;
		}
if(document.getElementById('benefitNotesForm:searchResultTable')!= null){
		document.getElementById('benefitNotesForm:searchResultTable').onresize = syncTables;
		var size = document.getElementById('benefitNotesForm:searchResultTable').rows.length;
		var relTblWidth = document.getElementById('benefitNotesForm:searchResultTable').offsetWidth;
		if(size<=12){
				document.getElementById('benefitNotesForm:searchResultTable').width = relTblWidth+"px";
				document.getElementById('resultHeaderTable').width = relTblWidth+"px";
		}else{
			syncTables();

		}
	}
	window.onload=function(){
		var images,i;
		 images=document.getElementsByTagName("img");
		 for(i in images){
			 images[i].className = "treeNodeImage_WithNoBorder";
		 }
  		document.getElementById('scrollButton').click();
		}
</script>

<%@ include file="../navigation/unsavedDataFinder.html"%>
</HTML>


