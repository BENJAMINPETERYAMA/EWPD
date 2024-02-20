<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Benefit Print Notes</TITLE>

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
			<TD><h:form styleClass="form" id="BenefitNotesPrintForm">
				<TABLE width="97%" border="0" cellspacing="0" cellpadding="0">
				<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{standardBenefitBackingBean.printBreadCrumbText}">
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

						
						<!--	Start of Table for actual Data	--> <br>
<DIV id="noNotesDiv"><TABLE width="100%" id="noNotesTable" cellspacing="1" bgcolor="#cccccc" cellpadding="0" border="0">

							<tbody>
								<tr>
									<td>
									<STRONG><h:outputText
										value="Associated Notes"></h:outputText></STRONG>
									</td></tr>
							<tr class="dataTableColumnHeader">	<td><strong><h:outputText value="No Associated Notes."></h:outputText>
</strong>
</td>
</tr>
</tbody></TABLE>
</DIV>
									<DIV id="searchResultdataTableDiv"
										style="overflow: auto;">
<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">

						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">

							<tbody>
								<tr>
									<td>
									<DIV id="panel2Header" class="tabTitleBar"
										style="position:relative;width:100% "><STRONG><h:outputText
										value="Associated Notes"></h:outputText></STRONG></DIV>
									</td>
								</tr>

								<TR>
									<TD>
									<div id="resultHeaderDiv">
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

<!-- Search Result Data Table -->
									<h:dataTable
										rendered="#{standardBenefitNotesBackingBean.associatedNotesList!= null}"
										styleClass="outputText"
										headerClass="dataTableHeader" id="NotesTable"
										var="singleValue" cellpadding="1" width="100%" cellspacing="1"
										value="#{standardBenefitNotesBackingBean.associatedNotesList}">

										<h:column>
											<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
										</h:column>

									</h:dataTable>
									</TD>
								</TR>
							</tbody>

						</TABLE>
</FIELDSET>
						<BR>
						<br>
<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td width="15"></td>
								<td align="left"></td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText value="State" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{standardBenefitNotesBackingBean.state}" />
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{standardBenefitNotesBackingBean.status}" />
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText
											value="#{standardBenefitNotesBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{standardBenefitNotesBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>

						</FIELDSET>
</DIV>
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
if(null == document.getElementById('BenefitNotesPrintForm:NotesTable')){
			document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			document.getElementById('searchResultdataTableDiv').innerHTML = '';
			document.getElementById('noNotesDiv').style.visibility = 'visible';	
			document.getElementById('noNotesTable').style.height = '50px';
	}else{
			document.getElementById('searchResultdataTableDiv').style.visibility = 'visible';
			document.getElementById('noNotesDiv').style.visibility = 'hidden';
			document.getElementById('noNotesDiv').innerHTML = '';	
			setColumnWidth('BenefitNotesPrintForm:NotesTable','25%:25%:25%');
	}
</script>

</HTML>
<script>window.print();</script>
