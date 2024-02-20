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
</head>



<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<TR>
			<TD><h:form styleClass="form" id="benefitNotesViewForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="25%"><DIV class="treeDiv"><jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>
						</TD>
						<TD colspan="1" valign="top" class="ContentArea" width="963">
						<TABLE>
							<TBODY>
								<TR>
									<TD><!-- Insert WPD Message Tag --></TD>
								</TR>
							</TBODY>
						</TABLE>




						<!-- Table containing Tabs -->



						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">


							<TR>
								<TD width="34%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{standardBenefitBackingBean.loadStandardBenefitView}">
											<h:outputText value="General Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{benefitDefinitionBackingBean.loadBenefitDefinitionView}">
											<h:outputText
												value="#{standardBenefitBackingBean.benefitTypeTab}" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<!-- 
									<TD width="25%">
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabNormal">
												<h:commandLink action="#{MandateDefinitionBackingBean.loadMandateDefinition}">
												<h:outputText value="Adj Benefit Mandates" /></h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
 -->

								<TD id="manInfo" width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="3" align="left"><IMG
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21"></TD>
										<TD class="tabNormal" align="center" width="99%"><h:commandLink
											action="#{benefitMandateBackingBean.loadBenefitMandateView}">
											<h:outputText value="Mandate Information" />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD id="noteTab" width="33%">
								<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
										<TD class="tabActive" align="center" width="99%"><h:outputText
											value="Notes" /></TD>
										<TD width="2" align="right"><IMG
											src="../../images/activeTabRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</TD>
								<TD width="100%"></TD>
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%;height:430">

						<!--	Start of Table for actual Data	--> <br>
					<div id="emptymsg"><center><h:outputText
									value="No Notes Attached."
									styleClass="dataTableColumnHeader" /></center></div>
					<DIV id="resultHeader">
						<TABLE width="100%" cellspacing="0" cellpadding="0">
							<TR>
								<TD>
								<DIV id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TR>
										<TD><STRONG><h:outputText value="Associated Notes"></h:outputText></STRONG></TD>
									</TR>
								</TABLE>
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left" width="20%"><h:outputText value="Note Name "></h:outputText>
											</TD>
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
									rendered="#{standardBenefitNotesBackingBean.associatedNotesList!= null}"
									value="#{standardBenefitNotesBackingBean.associatedNotesList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

									<h:column>
										<h:inputHidden id="benefitNoteId"
											value="#{singleValue.noteId}"></h:inputHidden>									
										<h:inputHidden id="noteNameHidden"
											value="#{singleValue.noteName}"></h:inputHidden>
										<h:inputHidden id="noteVersionHidden"
											value="#{singleValue.version}"></h:inputHidden>	
										<h:outputText id="noteName" value="#{singleValue.noteName}">
										</h:outputText>
									</h:column>
									<h:column>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="View" id="viewButton" rendered="#{standardBenefitNotesBackingBean.securityAccess}"
											image="../../images/view.gif"
											onclick="viewAction();ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdForView+'&noteName='+noteNameForView+'&version='+noteVersionForView+'&temp=' + Math.random(),'dummyDiv','benefitNotesViewForm:dummyHidden',1,1);return false;"></h:commandButton>
										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>										
									</h:column>

								</h:dataTable></DIV>
								</TD>
							</TR>
						</TABLE>
</DIV>
						</fieldset>
						<br />
						<fieldset
							style="margin-left:6px;margin-right:-6px;margin-top:6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
								<td align="right" width="127">
								<table width="100%" border="0">
									<TR>
										<TD>State</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="state"
											value="#{standardBenefitNotesBackingBean.state}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD>Status</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="status"
											value="#{standardBenefitNotesBackingBean.status}"></h:outputText>
										</TD>
									</TR>
									<TR>
										<TD>Version</TD>
										<td><h:outputText value=":" /></td>
										<TD><h:outputText id="version"
											value="#{standardBenefitNotesBackingBean.version}"></h:outputText>
										</TD>
									</TR>
								</table>
								</td>
							</tr>
						</table>
						</fieldset>

						<!--	End of Page data	--> <!-- Space for hidden fields --> <h:inputHidden
							id="tabHidden"
							value="#{standardBenefitBackingBean.benefitTypeTab}"></h:inputHidden>
						<!-- End of hidden fields  --></TD>
					</TR>
				</table>
<DIV id="dummyDiv" style="visibility:hidden"></DIV>
				<h:inputHidden id="dummyHidden"></h:inputHidden>
				<h:inputHidden id="viewNoteId"
					value="#{notesAttachmentBackingBean.noteId}"></h:inputHidden>
				<h:inputHidden id="viewNoteName"
					value="#{notesAttachmentBackingBean.noteName}"></h:inputHidden>
				<h:inputHidden id="viewNoteVersion"
					value="#{notesAttachmentBackingBean.version}"></h:inputHidden>
			</h:form></td>
		</tr>
		<tr>
			<td colspan="2" width="100%"><%@ include
				file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script language="JavaScript">
	// Space for page realated scripts
		function syncTables(){
			var relTblWidth = document.getElementById('benefitNotesViewForm:searchResultTable').offsetWidth;
			//document.getElementById('benefitMandateViewForm:resultHeaderTable').width = relTblWidth + 'px';
		}


	
		if(document.getElementById('benefitNotesViewForm:searchResultTable')!= null){

			if(document.getElementById('benefitNotesViewForm:searchResultTable:0:benefitNoteId') != null){
				document.getElementById('emptymsg').style.visibility = 'hidden';	
				document.getElementById('resultHeader').style.visibility = 'visible';	
			
				document.getElementById('benefitNotesViewForm:searchResultTable').onresize = syncTables;
				syncTables();
				setColumnWidth('benefitNotesViewForm:searchResultTable', '20%:20%:20%:20%');
			}	
		}else{
				document.getElementById('emptymsg').style.visibility = 'visible';	
				document.getElementById('resultHeader').style.visibility = 'hidden';
		}
	hideTab();
function hideTab(){
	var tab;
	tab = document.getElementById("benefitNotesViewForm:tabHidden").value ;
	if(tab=="Standard Definition"){
		manInfo.style.display='none';
		noteTab.style.display='';
	}
	else{
		manInfo.style.display='';
		noteTab.style.display='none';
	}
}

function viewAction(){
 		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','benefitNoteId','benefitNotesViewForm:viewNoteId');
		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteNameHidden','benefitNotesViewForm:viewNoteName');
		getFromDataTableToHidden('benefitNotesViewForm:searchResultTable','noteVersionHidden','benefitNotesViewForm:viewNoteVersion');
		
		noteIdForView = document.getElementById('benefitNotesViewForm:viewNoteId').value;
		//alert('noteIdForView' +noteIdForView );
		noteNameForView = document.getElementById('benefitNotesViewForm:viewNoteName').value;
		noteVersionForView = document.getElementById('benefitNotesViewForm:viewNoteVersion').value;
//reloadData();
  }
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="notes" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
