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

<TITLE>Page Title</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/header.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="../navigation/MenuComponent.jsp"></jsp:include>
			</td>
		</tr>
		<TR>
			<td>
				<TABLE width = "100%" id="breadCumbTable" bgcolor="#7670B3">
					<tr>
						<td height="16" valign="middle" bgcolor="#7670B3" class="breadcrumb">
							Main Module&nbsp;&gt;&gt;&nbsp;Sub Module&nbsp;&gt;&gt;&nbsp;Operation
						</td>
					</tr>
				</TABLE>
			</td>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="formName">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						

						 		</DIV>

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabActive"> <h:outputText value=" Tab1"/> </td> 
				                					<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /></td>
													<td class="tabNormal"> 
														<h:commandLink > <h:outputText value="Tab2"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /></td>
												</tr>
											</table>
										</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText">
									<TBODY>
										<TR>
											<TD width="110"><h:outputText value="Label"/> </TD>
											<TD width="229"><h:inputText styleClass="formInputField"/> </TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText value="Label"/> </TD>
											<TD width="229"><h:inputText styleClass="formInputField"/> </TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText value="Label"/> </TD>
											<TD width="229"><h:inputText styleClass="formInputField"/> </TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText value="Label"/> </TD>
											<TD width="229"><h:inputText styleClass="formInputField"/> </TD>
										</TR>
										<TR>
											<TD width="110"><h:outputText value="Label"/> </TD>
											<TD width="229"><h:inputText styleClass="formInputField"/> </TD>
										</TR>
										<TR>
											<TD width="110">
												<h:commandButton value="Button"  styleClass="wpdButton"> </h:commandButton>
											</TD>
											<TD width="229">&nbsp;</TD>
										</TR>
									</TBODY>
									</TABLE>
<!--	End of Page data	-->
								</fieldset>		
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->

				</h:form>
			</td>
		</tr>
		<tr>
			<td>
				<%@ include file ="../navigation/pageFooter.jsp" %>
			</td>
		</tr>
	</table>
</BODY>
</f:view>

<script>

	// Space for page realated scripts

</script>
</HTML>
