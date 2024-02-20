

<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>

<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/test/ComponentBenefitGeneralInformation.java" --%><%-- /jsf:pagecode --%>

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

<TITLE>Benefit Notes View</TITLE>
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
		<h:form styleClass="form" id="benefitNotesViewForm">
			<TABLE width="100%" cellpadding="0" cellspacing="0">
				<TR>
					<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
				</TR>

				<TR>
					<TD>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
							<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>



							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD></TD>
									</TR>
								</TBODY>
							</TABLE>




							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{benefitComponentCreateBackingBean.loadComponentBenefitforView}">
												<h:outputText value="General Information" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<h:inputHidden id="hiddenTabValue"
										value="#{benefitComponentBackingBean.componentTypeTab}"></h:inputHidden>
									<td width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{ComponentBenefitDefinitionsBackingBean.loadStandardDefinitionView}">
												<h:outputText
													value="#{benefitComponentBackingBean.componentTypeTab}" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="200" id="sbMandateInfoTab">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td class="tabNormal"><h:commandLink
												action="#{benefitMandatesBackingBean.loadMandateInformationForView}">
												<h:outputText value="Mandate Information" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="200" id="sbOverrideNotesTab">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
											<td width="186" class="tabActive"><h:outputText
												value=" Notes" /></td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" width="2" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="25%"></td>
								</tr>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%;height:480">

							<!--	Start of Table for actual Data	-->
								<div id="emptymsg"><h:outputText
									value="No Notes Available."
									styleClass="dataTableColumnHeader" /></div>
							<TABLE id="newTable" width="100%" cellspacing="0" cellpadding="0" border="0">
								<TR>
									<TD><BR />
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="headerTable" border="0" width="100%">
										<TR>
											<TD><b> <h:outputText value="Associated Notes"></h:outputText>
											</b></TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Note Name "></h:outputText>
												</TD>
												<TD>&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:290px;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList != null}"
										value="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

										<h:column>
											<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
											<h:inputHidden id="noteNameHidden"
												value="#{singleValue.noteName}"></h:inputHidden>
											<h:inputHidden id="noteVersionHidden"
												value="#{singleValue.version}"></h:inputHidden>
											<h:outputText id="notesName" value="#{singleValue.noteName}">
											</h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<!-- start: for viewing the selected details -->
											<h:commandButton alt="View" id="viewButton" 	rendered="#{BenefitComponentNotesBackingBean.securityAccess}"
												image="../../images/view.gif"
												onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','benefitNotesViewForm:dummyHidden',1,1);return false;"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<BR>
							</TD>
							</TR>
					</table>

					<DIV id="dummyDiv" style="visibility:hidden"></DIV>

					<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
						id="targetHiddenToStoreNoteId"
						value="#{BenefitComponentNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
					<h:commandLink id="deleteStdBenefitOverriddenNotes"
						style="display:none; visibility: hidden;"
						action="#{BenefitComponentNotesBackingBean.deleteStandardBenefitOverrideNotes}">
						<f:verbatim />
					</h:commandLink> <h:inputHidden id="viewNoteId"
						value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden> <h:inputHidden
						id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
					<h:inputHidden id="viewNoteVersion"
						value="#{notesAttachmentBackingBean.version}"></h:inputHidden> <h:inputHidden
						id="noteVersionForHide"
						value="#{BenefitComponentNotesBackingBean.noteVersion}"></h:inputHidden>
					<h:inputHidden id="dummyHidden"></h:inputHidden> <!-- End of hidden file-->
					</TD>
				</TR>
				<TR>
					<TD><%@ include file="../navigation/bottom_view.jsp"%></TD>
				</TR>
			</TABLE>
			</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>


<script language="JavaScript">	
  	var noteIdForView; 
  	var noteNameForView; 
	var primaryEntityType = 'benftComp';
	var entityType = 'ATTACHBENEFIT' ;
	var lookUpAction = 3;
	
	
	//Hide table if no value is present
function viewAction(){
 		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteId','benefitNotesViewForm:viewNoteId');
		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteNameHidden','benefitNotesViewForm:viewNoteName');
		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteVersionHidden','benefitNotesViewForm:viewNoteVersion');
		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteVersionHidden','benefitNotesViewForm:noteVersionForHide');
		noteIdForView = document.getElementById('benefitNotesViewForm:viewNoteId').value;
		noteNameForView = document.getElementById('benefitNotesViewForm:viewNoteName').value;
		noteVersionForView = document.getElementById('benefitNotesViewForm:viewNoteVersion').value;		
  }
 hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitNotesViewForm:hiddenTabValue").value ;
	
	if(tab=="Standard Definition"){		
		sbOverrideNotesTab.style.display = '';		
		sbMandateInfoTab.style.display = 'none';
	}
	else{		
		sbOverrideNotesTab.style.display = 'none';	
		sbMandateInfoTab.style.display = '';	
	}
}
	
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitNotesViewForm:searchResultTable:0:noteId');
		var relTblWidth = document.getElementById('newTable').offsetWidth;	
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
			setColumnWidth('benefitNotesViewForm:searchResultTable', '40%:60%');
	 		setColumnWidth('resultHeaderTable', '40%:60%');
		if(document.getElementById('benefitNotesViewForm:searchResultTable').rows.length <= 12){
		document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
		document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
		setColumnWidth('benefitNotesViewForm:searchResultTable', '40%:60%');
	 	setColumnWidth('resultHeaderTable', '40%:60%');
		}else{
		document.getElementById('resultHeaderTable').width = relTblWidth-19+"px";
		setColumnWidth('benefitNotesViewForm:searchResultTable', '40%:60%');
	 	setColumnWidth('resultHeaderTable', '40%:60%');
		}
			
		}
	}		
	
	
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitOverriddenNotesPrint" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
