
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

<TITLE>Print Benefit Notes</TITLE>

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
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:16px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:95%">
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
			<TD><h:form styleClass="form" id="componentBenefitNotesPrintForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="1" valign="top" class="ContentArea" width="963"><!-- Table containing Tabs -->

						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	--> <br>
						<div id="emptymsgForNotes"><h:outputText
									value="No Notes Available."
									styleClass="dataTableColumnHeader" /></div>
						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
											<tr>
												<TD><b> <h:outputText value="Associated Notes"></h:outputText>
												</b></TD>
											</tr>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" height="30%"><h:outputText value="Note Name "></h:outputText>
												</TD>
												<TD height="30%">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="position:relative;z-index:1;font-size:10px;overflow:auto;"><!-- Search Result Data Table -->
									<h:dataTable
										rendered="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList!= null}"
										rowClasses="dataTableOddRow"
										bgcolor="#cccccc" styleClass="outputText"
										headerClass="dataTableHeader" id="NotesTable"
										var="singleValue" cellpadding="1" width="100%" cellspacing="1"
										value="#{BenefitComponentNotesBackingBean.standardBenefitOverrideNotesList}">

										<h:column>
											<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
										</h:column>

									</h:dataTable></DIV>
									</TD>
								</TR>
						</TABLE>

						</FIELDSET>
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
		hiddenIdObj = document.getElementById('componentBenefitNotesPrintForm:NotesTable');		
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsgForNotes').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsgForNotes').style.visibility = 'hidden';
			setColumnWidth('componentBenefitNotesPrintForm:NotesTable', '25%:25%:25%');
	 		setColumnWidth('resultHeaderTable', '25%:25%:25%');
		}
	}	
</script>

</HTML>
<script>window.print();</script>
