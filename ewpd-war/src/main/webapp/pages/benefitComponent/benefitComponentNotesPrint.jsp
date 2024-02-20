<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/BenefitDefinitionPrint.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Print BenefitComponent Notes</TITLE>

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
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>


<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</head>



<f:view>
	<BODY>
	<TABLE width="100%" cellpadding="0" cellspacing="0">

		<TR>
			<TD><h:form styleClass="form" id="benefitComponentPrintNotesForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="#{benefitComponentBackingBean.printBreadCrumbText}">
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
						<TD colspan="1" valign="top" class="ContentArea" width="963"><!-- Table containing Tabs -->
						<DIV id="searchResultdataTableDiv" style="overflow: auto;">	
						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <br>
						<div id="nobenComponentNote"><h:outputText
									value="No Notes Available."
									styleClass="dataTableColumnHeader" /></div>
						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

							<tbody>								
								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="headerTable" border="0" width="100%">
										<TR>
											<TD><b> <h:outputText value="Associated Notes"></h:outputText>
											</b></TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<td align="left" width="25%"><h:outputText value="Note Name"></h:outputText>
												</td>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="overflow: auto;"><!-- Search Result Data Table -->
									<h:dataTable id="NotesTable"
										rendered="#{BenefitComponentNotesBackingBean.associatedNotesList!= null}"
										styleClass="outputText"
										headerClass="dataTableHeader" 
										var="singleValue" cellpadding="1" width="100%" cellspacing="1"
										value="#{BenefitComponentNotesBackingBean.associatedNotesList}">

										<h:column>
											<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
											<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
										</h:column>

									</h:dataTable></DIV>
									</TD>
								</TR>
							</tbody>

						</TABLE>

						</FIELDSET>
						
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="4%"></td>
								<td align="left" width="71%"></td>
								<td align="left" width="25%">
								<table Width=100%>
									<tr>
										<td width="2%"><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentNotesBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{BenefitComponentNotesBackingBean.state}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentNotesBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{BenefitComponentNotesBackingBean.status}" />
									</tr>
									<tr>
										<td width="2%"><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{BenefitComponentNotesBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{BenefitComponentNotesBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
					</DIV>	
						<BR>

						<BR>
						<br>
						</TD>
					</TR>
				</TABLE>






				<!--	End of Page data	-->


				<!-- Space for hidden fields -->

				<!-- End of hidden fields  -->

			</h:form></TD>
		</TR>
	</TABLE>
	</BODY>
</f:view>
<script language="javascript">
		hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitComponentPrintNotesForm:NotesTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('nobenComponentNote').style.visibility = 'visible';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('nobenComponentNote').style.visibility = 'hidden';
			setColumnWidth('benefitComponentPrintNotesForm:NotesTable', '40%:60%');
			setColumnWidth('resultHeaderTable', '40%:60%');

		}
	}
	
	
		if(null != document.getElementById('benefitComponentPrintNotesForm:NotesTable'))
			setColumnWidth('benefitComponentPrintNotesForm:NotesTable','25%');

</script>
<script>window.print();</script>
</HTML>

