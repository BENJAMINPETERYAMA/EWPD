<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<base target=_self>
<TITLE>Benefit Level Notes Details Page</TITLE>
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
<BODY>
<!--  hx:scriptCollector id="scriptCollector" -->
				<h:form styleClass="form" id="benefitNotesForm">

		<h:inputHidden id="loadAssociatedNotes" value="#{benefitLevelNotesAttachmentBackingBean.loadAssociatedNotes}" ></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv">
							<jsp:include page="benefitLevelNotesTree.jsp"></jsp:include></DIV>
							</TD>

	   <TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<TR>
											<TD>
												<w:message value="#{benefitLevelNotesAttachmentBackingBean.validationMessages}"></w:message>
												
											</TD>
										</TR>		
									</TBODY>
	
							</TABLE>
<!-- Table containing Tabs -->

		<h:inputHidden id="bnftLineId" value="#{benefitLevelNotesAttachmentBackingBean.bnftLineId}" ></h:inputHidden>
		<h:inputHidden id="bnftTermCode" value="#{benefitLevelNotesAttachmentBackingBean.benefitTermCode}" ></h:inputHidden>
		<h:inputHidden id="bnftQualifierCode" value="#{benefitLevelNotesAttachmentBackingBean.bnftQualifierCode}" ></h:inputHidden>

	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
		<TR>								
										<TD width="25%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
											<TR>
												<TD width="2" align="left"><IMG src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
												<TD width="186" class="tabActive"> <h:outputText value="Notes Details" /> </TD> 
												<TD width="2" align="right"><IMG src="../../images/activeTabRight.gif" width="2" height="21"></TD>
											</TR>
											</TABLE>
										</TD>
	
	
									<TD width="100%">
									</TD>
									</TR>
									</TABLE>	
<!-- End of Tab table -->

								<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									
									<table border="0" cellpadding="5" cellspacing="0" width="100%">
			
										<tr>
											<td valign="top" width="10%"><h:outputText value="Note Name" styleClass="outputText"/></td>
											<TD width="2%"><h:outputText value=":" styleClass="outputText"/></TD>
											<td align="left" width="88%">					
												<h:outputText value="#{benefitLevelNotesAttachmentBackingBean.noteName}" styleClass="outputText"/>
												<h:inputHidden id="noteName" value="#{benefitLevelNotesAttachmentBackingBean.noteName}" ></h:inputHidden>
											</td>
				
										</tr>

										<tr>
											<td valign="top" width="10%"><h:outputText value="Note Type" styleClass="outputText"/></td>
											<TD width="2%"><h:outputText value=":" styleClass="outputText"/></TD>
											<td align="left" width="88%">					
												<h:outputText value="#{benefitLevelNotesAttachmentBackingBean.noteType}" styleClass="outputText"/>
												<h:inputHidden id="noteType" value="#{benefitLevelNotesAttachmentBackingBean.noteType}" ></h:inputHidden>
											</td>
				
										</tr>

										<tr>
											<td valign="top" width="10%"><h:outputText value="Note Text" styleClass="outputText"/></td>
											<TD width="2%"><h:outputText value=":" styleClass="outputText"/></TD>
											<td align="left" width="88%">					
												<h:outputText value="#{benefitLevelNotesAttachmentBackingBean.noteText}" styleClass="outputText"/>
												<h:inputHidden id="noteText" value="#{benefitLevelNotesAttachmentBackingBean.noteText}" ></h:inputHidden>
											</td>
				
										</tr>
				
									</table><br/>

								</FIELDSET>									
					</TD>
						</TR>
						
					</table>
					
<!--	End of Page data	-->							
<DIV id="dummyDiv" style="visibility:hidden"></DIV>
					
<!-- Space for hidden fields -->		
			
					
<!-- End of hidden fields  -->
						</h:form>
			</td>
		</tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR>
	</table>
</BODY>
</f:view>

<%@ include file="../navigation/unsavedDataFinder.html"%>



</HTML>


