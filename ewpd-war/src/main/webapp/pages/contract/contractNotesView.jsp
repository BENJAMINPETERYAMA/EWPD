<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>WellPoint Product Database:Contract Notes</TITLE>
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
<h:inputHidden id="hidden1" value="#{ContractNotesBackingBean.loadNote}"></h:inputHidden>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="contractNotesForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->				<jsp:include page="contractTree.jsp"></jsp:include>		
								</DIV>
						 		

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea" width="937">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="TabTable">
						<tr>
							<td width="16%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab Left Active"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{contractBasicInfoBackingBean.getBasicInfo}"
										id="linkToProfileInfo">
										<h:outputText id="labelBasicInfo"
											value="General Information"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Active"
										width="2" height="21" /></td>
								</tr>
							</table>
							</td>
							<!-- >td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{contractSpecificInfoBackingBean.loadContractSpecificInfo}"
										id="linkToAdjudicationInfo">
										<h:outputText id="labelSpecificInfo"
											value="Specific Information"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td-->
							<td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink
										action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}"
										id="linkToPricinginfoInfo">
										<h:outputText id="labelpricinginfo"
											value="Pricing Information"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="10%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive"><h:commandLink
										action="#{ContractNotesBackingBean.load}"
										id="contractNotesID">
										<h:outputText id="labelNotes" value="Notes"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment">
										<h:outputText id="LabelComments" value="Comments"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}" id="linkToAdminOption">
										<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
						<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabNormal"><h:commandLink action="#{contractRuleBackingBean.displayRules}" id="linkToRules">
										<h:outputText id="rules" value="Rules"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->	
									<table border="0" cellspacing="0" cellpadding="0" class="outputText" width ="100%">
										<tbody>	
												<tr>
													<TD><!-- Attach Notes Data Table -->
													<DIV id="noContractNote"
														style="font-family:Verdana, Arial, Helvetica, sans-serif;
														font-size:11px;font-weight:bold;text-align:center;color:#000000; height:20px;
														background-color:#FFFFFF;">No
														Notes Attached.</DIV>
					
													<div id="attachNotesDataTableDiv"
														style="height:250px; overflow:auto; width:100%;">
														<!-- WAS 6.0 Migration Changes -- Moved DIV tag inside datatable tags   -->
														<DIV id="resultHeaderDiv">
															<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0" width="100%" height="21">
																<TR>
																	<TD><b>
																	Notes Attached
																	</b></TD>
																</TR>
															</TABLE>
															<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%" height="21">
																<TBODY>
																<TR>
																<TD align="center" width="70%" class="dataTableColumnHeader">Note ID</TD>																										
																<TD align="left" width="30%" class="dataTableOddRow">&nbsp;</TD>	
																</TR>
																</TBODY>
															</TABLE>
													</DIV>
														<h:dataTable
														headerClass="dataTableHeader" id="attachNotesTable"
														var="singleValue" cellpadding="3" cellspacing="1"
														bgcolor="#cccccc"
														rendered="#{ContractNotesBackingBean.attachNotesList != null}"
														value="#{ContractNotesBackingBean.attachNotesList}"
														rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
														width="100%">	
															
														<h:column>
															<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}"></h:outputText>
															<h:inputHidden id="hiddenNotesName"	value="#{singleValue.noteId}"></h:inputHidden>
															<h:inputHidden id="hiddenNotesId"	value="#{singleValue.noteId}"></h:inputHidden>
															<h:inputHidden id="hiddenNotesVersion"	value="#{singleValue.version}"></h:inputHidden>
													
														</h:column>
														<h:column>
															<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
															<h:commandButton alt="View" id="viewButton"  rendered="#{ContractNotesBackingBean.securityAccess}"
																image="../../images/view.gif"
																onclick="viewAction();return false;"></h:commandButton>
				
														</h:column>
													</h:dataTable></div>
													</TD>
												</tr>														
										</tbody>
								</table>
<!--	End of Page data	-->
								</fieldset>	
								
								<BR>
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									<table  border="0" cellspacing="0" cellpadding="3" width ="100%">
										<tr><td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText value="Transfer Log" styleClass="variableLink" /></a></td>
											<td  align="left" width="127">
												<table Width = 100%>
													<tr>
														<td><h:outputText value="State"/></td><TD>:<h:outputText value="#{ContractNotesBackingBean.state}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Status" /></td><TD>:<h:outputText value="#{ContractNotesBackingBean.status}"/></td>
													</tr>
													<tr>
														<td><h:outputText value="Version" /></td><TD>:<h:outputText value="#{ContractNotesBackingBean.version}"  /></td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- Transfer Log -->
										
									</table>
								</FIELDSET>	
		


							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->

				<DIV id="dummyDiv" style="visibility:hidden"></DIV>
				<h:inputHidden id="dummyHidden" ></h:inputHidden>
	<h:inputHidden id="selectedNotesIdForDelete"
			value="#{ContractNotesBackingBean.selectedNotesId}" />
	
	<h:commandLink
			id="deleteNotesButton"
			style="display:none; visibility: hidden;"
			action="#{ContractNotesBackingBean.deleteNotes}">
			<f:verbatim>&nbsp;&nbsp;</f:verbatim>
		</h:commandLink> 
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td >
				<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
		</tr>
	</table>
</BODY>
</f:view>
<script>
		var entityType = 'ATTACHPRODUCT';
		var lookUpAction = 2;

		function viewAction(){			
			var e = window.event;
			var button_id = e.srcElement.id;			
			var var1 = button_id.split(':');			
			var rowcount = var1[2];			
			var noteId = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesId";
			var noteNameValue = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesName";
			var versionValue = "contractNotesForm:attachNotesTable:"+rowcount+":hiddenNotesVersion";		
			var noteIdValue = document.getElementById(noteId).value;
			var noteName = document.getElementById(noteNameValue).value;
			var version = document.getElementById(versionValue).value;
		 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&noteName='+noteName+'&version='+version;
//			newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
			newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','contractNotesForm:dummyHidden',1,1);		

		}


//Hide table if no value is present
	
	hideIfNoValue('attachNotesDataTableDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('contractNotesForm:attachNotesTable:0:notesName');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			setColumnWidth('contractNotesForm:attachNotesTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');
			setColumnWidth('headerTable', '40%:60%');
			document.getElementById('noContractNote').style.visibility = 'hidden';

		}
	}
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
</script>
<form name="printForm">
<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="contractNotes" />
</form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
