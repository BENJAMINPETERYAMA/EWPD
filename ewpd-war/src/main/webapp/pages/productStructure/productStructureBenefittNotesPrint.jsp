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

<TITLE>Print product Structure Benefit Notes</TITLE>

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
			<TD><h:form styleClass="form" id="productStructureBenefitPrintNotesForm">
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
                                          value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
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

						<FIELDSET
							style="margin-left:6px;margin-right:-65px;padding-bottom:1px;padding-top:6px;width:100%">
                        <DIV id="panel2Header" class="tabTitleBar"
					        style="position:relative;width:100% "><STRONG><h:outputText
							value="Associated Notes"></h:outputText></STRONG></DIV>
						<!--	Start of Table for actual Data	--> <br>
						<div id="emptymsg"><h:outputText
									value="No Notes Available."	/></div>
						<div id="noteDiv">
						<TABLE width="100%" cellpadding="0" cellspacing="0" border="0">
							<tbody>								
								<TR>
									<TD>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" cellpadding="0"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR>
												<td align="left" width="25%"><strong><h:outputText value="Note Name"></h:outputText></strong>
												</td>
											</TR>
										</TBODY>
									</TABLE>
									<br></div>
									</TD>
								</TR>
								
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="overflow: auto;"><!-- Search Result Data Table -->
									<h:dataTable id="NotesTable"
										rendered="#{productStructureBenefitDefenitionBackingBean.associatedNotesList!= null}"
										headerClass="dataTableHeader" 
										var="singleValue" cellpadding="1" width="100%" cellspacing="1"
										value="#{productStructureBenefitDefenitionBackingBean.associatedNotesList}">

										<h:column>
											<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
										</h:column>

									</h:dataTable></DIV>
									</TD>
								</TR>
							</tbody>

						</TABLE>
						</div>

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
		if(null != document.getElementById('productStructureBenefitPrintNotesForm:NotesTable')){
			setColumnWidth('productStructureBenefitPrintNotesForm:NotesTable','25%');
			document.getElementById('noteDiv').style.visibility = 'visible';			
			document.getElementById('emptymsg').style.visibility = 'hidden';	
		}else{
			document.getElementById('noteDiv').style.visibility = 'hidden';			
			document.getElementById('emptymsg').style.visibility = 'visible';	
		}
</script>
<script>window.print();</script>
</HTML>

