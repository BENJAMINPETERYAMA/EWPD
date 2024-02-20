<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Benefit Component Notes</TITLE>
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
</HEAD>
<f:view>
<BODY><hx:scriptCollector id="scriptCollector1">
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<h:form styleClass="form" id="benefitComponentNotesFormPrint">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr><td>&nbsp; </td></tr>
						<TR>
					<TD>  <FIELDSET style="margin-left:10px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">

                                    <h:outputText id="breadcrumb" 

                                          value="#{contractBasicInfoBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
						<TR>
	   <TD colspan="2" valign="top" class="ContentArea">
							<h:inputHidden id="loadPrint" value="#{contractBenefitComponentNotesBackingBean.loadForPrint}"></h:inputHidden>
								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									<DIV id="noContractNote"
									     style="font-family:Verdana, Arial, Helvetica, sans-serif;
										 font-size:11px;font-weight:bold;text-align:center;color:#000000; height:4px;
										 background-color:#FFFFFF;">No Notes Attached.
									</DIV>
								<TABLE width="100%" cellspacing="0" cellpadding="0">
									
									<TR>
										<TD>
											<DIV id="resultHeaderDiv">
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="headerTable" border="0" width="100%">
													<TR>
														<TD><b>
															<h:outputText value="Associated Notes"></h:outputText>
														</b></TD>
													</TR>
												</TABLE>
												<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%">
													<TBODY>
														<TR class="dataTableColumnHeader">
															<TD align="left" ><h:outputText value=" Note ID "></h:outputText> 
															</TD>																										
															<TD >
																<h:outputText value=" Note Name "></h:outputText>
															</TD>
														</TR>
													</TBODY>
												</TABLE>
											</DIV>
										</TD>
									</TR>
									<TR>
										<TD>
											<DIV id="searchResultdataTableDiv" style="height:50px;position:relative;z-index:1;font-size:10px;overflow:auto;">
		          								<!-- Search Result Data Table -->
			              						<h:dataTable styleClass="outputText" headerClass="dataTableHeader" id="searchResultTable" var="singleValue" cellpadding="3" width="100%" cellspacing="1" bgcolor="#cccccc" rendered="#{contractBenefitComponentNotesBackingBean.noteList != null}" value="#{contractBenefitComponentNotesBackingBean.noteList}" rowClasses="dataTableOddRow" border="0">

													<h:column>	
														<h:inputHidden id="noteId" value="#{singleValue.noteId}"></h:inputHidden>
														<h:outputText id="notesName" value="#{'Y' eq singleValue.legacyIndicator?'LEGACY':singleValue.noteId}">																											
														</h:outputText>
													</h:column>	
													<h:column>
														<h:outputText id="noteName" value="#{singleValue.noteName}"></h:outputText>
												
													</h:column>												
												</h:dataTable>
	          								</DIV>
										</TD>
									</TR>
								</TABLE>
								</FIELDSET>		
								<BR>
							</TD>
						</TR>
					</TABLE>
			</h:form>
			</TD>
		</TR>
	</TABLE>
</hx:scriptCollector></BODY>
</f:view>

<script language="JavaScript">	

	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('benefitComponentNotesFormPrint:searchResultTable:0:noteId');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('noContractNote').style.visibility = 'visible';

		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('noContractNote').style.visibility = 'hidden';
			setColumnWidth('benefitComponentNotesFormPrint:searchResultTable', '20%:80%');
			setColumnWidth('resultHeaderTable', '20%:80%');

		}
	}
	
	
	
	


</script>

<script>window.print();</script>
</HTML>
