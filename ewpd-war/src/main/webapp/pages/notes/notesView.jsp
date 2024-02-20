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
<TITLE>Notes View</TITLE>
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
</HEAD>
<f:view>
<BODY>
<h:inputHidden id="typeList" value="#{ReferenceDataBackingBeanCommon.noteTypeListForCombo}"></h:inputHidden>
<h:inputHidden id="viewNotesKey" value= "#{notesBackingBean.viewNotesKey}"/>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top_view_print_menu.jsp"></jsp:include>
			</td>
		</tr>
		
		<tr>
			<td>
				<h:form styleClass="form" id="notesViewForm">

					
<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel" height="665">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->			

						 		</DIV>

							</TD>

						<TD colspan="2" valign="top" class="ContentArea" height="665">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabActive"><h:outputText
											value="General Information" /></td>
										<h:inputHidden id="noteID" value="#{notesBackingBean.noteId}"></h:inputHidden>
										<h:inputHidden id="noteName" value="#{notesBackingBean.name}"></h:inputHidden>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="3" align="left"><img
											src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
											width="3" height="21" /></td>
										<td class="tabNormal"><h:commandLink
											action="#{notesBackingBean.loadDataDomain}">
											<h:outputText value="Data Domain" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
											width="4" height="21" /></td>
									</tr>
								</table>
								</td>

								<td width="100%"></td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
			
								<TR>
									<TD width="150"><h:outputText value="Id"/></TD>
									<TD ><h:outputText  value="#{notesBackingBean.noteId}"/>
									</TD>
								</TR>

								<TR>
									<TD width="150" ><h:outputText value="Name" /></TD>
									<TD ><h:outputText
										
										value="#{notesBackingBean.name}" /></TD>
								</TR>
								<TR>
									<TD width="150" ><h:outputText value="Type" /></TD>
									<TD ><h:outputText
										
										value="#{notesBackingBean.noteTypeDesc}" /></TD>
								</TR>
								<TR>
									<TD width="150" ><h:outputText value="Target Systems" /></TD>
									<TD >
									<DIV id="systemDomainDiv" class="formInputFieldForNotesView"></DIV>
									<h:inputHidden id="txtSystemDomain"
										value="#{notesBackingBean.systemDomain}"></h:inputHidden></TD>
								</TR>
								<TR>
									<TD width="150" valign="top" ><h:outputText value="Text" /></TD>
									<TD colspan="2" width="539" ><DIV id="viewnotesdiv"></DIV>
									<h:inputHidden id="hiddenNoteText" value="#{notesBackingBean.formattedNotes}"></h:inputHidden>
								</TD>
								</TR>
								<TR>
									<TD width="150"><h:outputText value="Created By" /></TD>
									<TD ><h:outputText
										value="#{notesBackingBean.createdUser}" /></TD>
								</TR>

								<TR>
									<TD width="150"><h:outputText value="Created Date" /></TD>
									<TD ><h:outputText
										value="#{notesBackingBean.createdTimestamp}" >
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>

								<TR>
									<TD width="150"><h:outputText value="Last Updated By" /></TD>
									<TD ><h:outputText
										value="#{notesBackingBean.lastUpdatedUser}" /></TD>
								</TR>

								<TR>
									<TD width="150"><h:outputText
										value="Last Updated Date" /></TD>
									<TD ><h:outputText
										value="#{notesBackingBean.lastUpdatedTimestamp}" >
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
										</h:outputText>
										<h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
							<br>
								<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
									<td width="15">&nbsp;</td>
								<td align="left">&nbsp;</td>
								<td align="left" width="127">
								<table Width=100%>
									<tr>
										<td><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{notesBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{notesBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{notesBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
						</TABLE>
						</fieldset>

						</TD>
					</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hiddenVersion" value="#{notesBackingBean.version}"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
		</tr>
	</table>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="notesGeneralInformation" />
</BODY>
</f:view>
<script>
	copyHiddenToDiv('notesViewForm:txtSystemDomain','systemDomainDiv',1,1); 

	formatDiv('notesViewForm:hiddenNoteText','viewnotesdiv');
	
	function formatDiv(hiddenNoteText,div){
		var noteText= document.getElementById(hiddenNoteText).value;
		var divObjNormal = document.getElementById(div);
		var textLines = noteText.split('\n');
		var divValue1 = '';
		for(i=0; i<textLines.length; i++) {
				divValue1 += (textLines[i]+"\n");
		}
		divObjNormal.innerText = divValue1;
	}
	
</script>
</HTML>

