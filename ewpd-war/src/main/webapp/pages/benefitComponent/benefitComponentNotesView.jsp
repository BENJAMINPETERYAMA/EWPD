

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitMandateCreate.java" --%><%-- /jsf:pagecode --%>
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
	<BODY>
	<h:form styleClass="form" id="benefitComponentNotesViewForm">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td><jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>

			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel"><!-- Space for Tree  Data	-->
						<jsp:include page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>



						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><!-- Insert WPD Message Tag --></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<TR>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink
											action="#{benefitComponentBackingBean.loadGeneralInformationView}">
											<h:outputText value="General Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>

								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal"><h:commandLink
											action="#{BenefitComponentHierarchiesBackingBean.loadBenefitHierarchyTabView}">
											<h:outputText value="Benefit Hierarchy" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="25%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD width="186" class="tabActive"><h:outputText value="Notes " />
										</TD>
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
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

						<!--	Start of Table for actual Data	-->
						<div id ="emptymsg" ><center><h:outputText
									value="No Notes Available."
									styleClass="dataTableColumnHeader" /></center></div>
						<TABLE id = "newTable" width="100%" cellspacing="0" cellpadding="0">

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
									style="height:335;position:relative;z-index:1;font-size:10px;overflow:auto;">
								<!-- Search Result Data Table --> <h:dataTable
									styleClass="outputText" headerClass="dataTableHeader"
									id="searchResultTable" var="singleValue" cellpadding="3"
									width="100%" cellspacing="1" bgcolor="#cccccc"
									rendered="#{BenefitComponentNotesBackingBean.associatedNotesList != null}"
									value="#{BenefitComponentNotesBackingBean.associatedNotesList}"
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
										<h:commandButton alt="View" id="viewButton" rendered="#{BenefitComponentNotesBackingBean.securityAccess}"
											image="../../images/view.gif"
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','benefitComponentNotesViewForm:dummyHidden',1,1);return false;"></h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
									</h:column>
								</h:dataTable></DIV>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:20px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td align="right">
								<table>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentBackingBean.state}" />
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentBackingBean.status}" />
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						
				</table>
				<DIV id="dummyDiv" style="visibility:hidden"></DIV>

				<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
					id="targetHiddenToStoreNoteId"
					value="#{BenefitComponentNotesBackingBean.benefitComponentNoteId}"></h:inputHidden>
				<h:inputHidden id="viewNoteId"
					value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden> <h:inputHidden
					id="viewNoteName" value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteVersion"
					value="#{notesAttachmentBackingBean.version}"></h:inputHidden> <h:inputHidden
					id="dummyHidden"></h:inputHidden> <h:commandLink
					id="deleteBenefitComponentNotes"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentNotesBackingBean.deleteBenefitComponentNotes}">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --> </td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>
		</h:form>
	</BODY>
</f:view>

<script language="JavaScript">	
  var noteIdForView; 
  var noteNameForView; 
  var entityType = 'ATTACHCOMP';
  var lookUpAction = 2;
	
	
	
	//Hide table if no value is present
	
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitComponentNotesViewForm:searchResultTable:0:noteId');
		var relTblWidth = document.getElementById('newTable').offsetWidth;	
		if(hiddenIdObj == null){			
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}else{
		if(document.getElementById('benefitComponentNotesViewForm:searchResultTable').rows.length <= 12){
			document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
			document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
			setColumnWidth('benefitComponentNotesViewForm:searchResultTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');
		}else{	
			document.getElementById('resultHeaderTable').style.width = relTblWidth-17+"px";
			setColumnWidth('benefitComponentNotesViewForm:searchResultTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');
		}
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
			setColumnWidth('benefitComponentNotesViewForm:searchResultTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}	
	
	
	function viewAction(){
 		getFromDataTableToHidden('benefitComponentNotesViewForm:searchResultTable','noteId','benefitComponentNotesViewForm:viewNoteId');
		getFromDataTableToHidden('benefitComponentNotesViewForm:searchResultTable','noteNameHidden','benefitComponentNotesViewForm:viewNoteName');
		getFromDataTableToHidden('benefitComponentNotesViewForm:searchResultTable','noteVersionHidden','benefitComponentNotesViewForm:viewNoteVersion');		
		noteIdForView = document.getElementById('benefitComponentNotesViewForm:viewNoteId').value;
		noteNameForView = document.getElementById('benefitComponentNotesViewForm:viewNoteName').value;
		noteVersionForView = document.getElementById('benefitComponentNotesViewForm:viewNoteVersion').value;
  }
 

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentNotes" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
