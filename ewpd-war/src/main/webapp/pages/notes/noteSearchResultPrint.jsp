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

<TITLE>Notes Search Result Print</TITLE>
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
	<BODY>
		
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>	
			<h:inputHidden id="dummy" value="#{notesSearchBackingBean.noteSearchPrint}"></h:inputHidden>
			<h:inputHidden id="noteid" value="#{notesSearchBackingBean.noteId}"></h:inputHidden>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="NoteSearchPrintForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-1px;padding-bottom:1px;padding-top:1px;width:96%">
						<h:outputText id="breadcrumb" 
							value="#{notesSearchBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
						<TR>
							<td valign="top" class="ContentArea">


							<!-- End of Tab table -->	
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="font-size:10px;width:100%;"><BR>
							<TABLE cellpadding="0" border="0" cellspacing="0" width="98%">	
							<tr><td>
								<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" 
									id="resultHeaderTable" border="0" width="100%">																							
										<TR class="dataTableColumnHeader">
											<td align="left"><h:outputText value="Note Name"></h:outputText></td>
											<td align="left"><h:outputText value="Note Id"></h:outputText></td>
											<td align="left"><h:outputText value="Note Type"></h:outputText></td>
											<td align="left" ><h:outputText value="Note Text"></h:outputText></td>
											<td align="left"><h:outputText value="Version"></h:outputText></td>
											<td align="left"><h:outputText value="Status"></h:outputText></td>										
										</TR>	
										</table>
									</DIV>
									</td>
							    </tr>																							
								<TR>
									<TD >
									<div id="searchResultdataTableDiv" 
									style="font-size:10px;width:100%;">
								<!-- Search Result Data Table --> 
									<h:dataTable
									styleClass="outputText" width="100%" headerClass="dataTableHeader"
									id="searchResultTable" var="Note" cellpadding="3"
									 cellspacing="1" 
									rendered="#{notesSearchBackingBean.notesSearchResultList != null}"
									value="#{notesSearchBackingBean.notesSearchResultList}">
									<h:column>
										<h:outputText id="noteNameOP"
											value="#{Note.noteName}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="noteId"
											value="#{Note.noteId}"></h:outputText>
									</h:column>									
									<h:column>
										<h:outputText id="noteTypeOP"
											value="#{Note.noteTypeDesc}"></h:outputText>
									</h:column>									
									<h:column>
										<h:outputText id="noteDesc"
											value="#{Note.noteText}"></h:outputText>
									</h:column>									
									<h:column>
										<h:outputText id="versionOP" value="#{Note.version}"></h:outputText>
									</h:column>		
									<h:column>
										<h:outputText id="notesStatus" value="#{Note.status}"></h:outputText>
									</h:column>								

								</h:dataTable></div>
									</TD>
								</TR>
							</TABLE>
						</DIV>
				</TABLE></td>
		</tr>
		<!-- Space for hidden fields -->
		
		
		<!-- End of hidden fields -->

</h:form> 
	</table>
	</BODY>

</f:view>
<script language="javascript">

		if(document.getElementById('NoteSearchPrintForm:searchResultTable') != null) {
			setColumnWidth('NoteSearchPrintForm:searchResultTable','25%:7%:22%:20%:6%:20%');
			setColumnWidth('resultHeaderTable','25%:7%:22%:20%:6%:20%');
			//showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
				

	window.print();
</script>
</HTML>
