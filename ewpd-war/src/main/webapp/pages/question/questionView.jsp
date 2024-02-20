<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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

<TITLE>Question View</TITLE>
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
</HEAD>
<f:view>
	<BODY>

	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<h:inputHidden id="view"
				value="#{QuestionSearchBackingBean.viewQuestion}" />
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form id="QuestionViewForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD valign="top" class="leftPanel" width="273">
						<DIV class="treeDiv" style="height:445px; overflow:auto;"><!-- Space for Tree  Data	-->

						<SCRIPT language="JavaScript">
										
									</SCRIPT></DIV>


						</TD>
						<TD colspan="1" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<TR>
									<TD></TD>
								</TR>
							</TBODY>
						</TABLE>





						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="left" width="0%"><img
											src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
										<td class="tabActive" align="center" width="99%"><h:outputText
											value=" Question" /></td>
										<td width="2%" align="right"><img
											src="../../images/activeTabRight.gif" width="3" height="21" /></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						<!-- End of Tab table -->

						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:410">

						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<tr>
									<TD colspan=3>
									<FIELDSET style="">
									<TABLE width="100%">
										<TR>
											<TD width="21%"><h:outputText value="Question" /></TD>
											<TD width="76%"><h:outputText id="txtQuestion"
												value="#{QuestionSearchBackingBean.questionDesc}"></h:outputText>


											</TD>
											<TD width="4%"></TD>
										</TR>
										<TR>
											<TD width="21%" valign="top"><h:outputText value="Functional Domain" /></TD>
											<TD width="76%"><h:outputText id= "funDomain"
												value="#{QuestionSearchBackingBean.functionalDomain}"></h:outputText>


											</TD>
											<TD width="4%"></TD>
										</TR>	

										<TR>
											<TD width="21%"><h:outputText value="Term" /></TD>
											<TD width="76%"><h:outputText id="txtTerm"
												value="#{QuestionSearchBackingBean.term}"></h:outputText>


											</TD>
											<TD width="4%"></TD>
										</TR>
										<TR>
											<TD width="21%"><h:outputText value="Qualifier" /></TD>
											<TD width="76%"><h:outputText id="txtQualifier"
												value="#{QuestionSearchBackingBean.qualifier}"></h:outputText>


											</TD>
											<TD width="4%"></TD>
										</TR>										
										<TR>
											<TD width="21%"><h:outputText value="Provider Arrangement" /></TD>
											<TD width="76%"><h:outputText id="txtProviderArrangement"
												value="#{QuestionSearchBackingBean.providerArrangement}"></h:outputText>
											</TD>
											<TD width="4%"></TD>
										</TR>
										<TR>
											<TD width="21%"><h:outputText value="Data Type" /></TD>
											<TD width="76%"><h:outputText id="txtDataType"
												value="#{QuestionSearchBackingBean.dataType}"></h:outputText>
											</TD>
											<TD width="4%"></TD>
										</TR>
										
										<TR>
											<TD width="21%"><h:outputText value="Benefit Line Data Type" /></TD>
											<TD width="76%"><h:outputText id="benefittxtDataType"
												value="#{QuestionSearchBackingBean.benefitLineDataType}"></h:outputText>
											</TD>
											<TD width="4%"></TD>
										</TR>
										
										<TR>
											<TD width="21%"><h:outputText value="Reference" /></TD>
											<TD width="76%"><h:outputText id="txtReference"
												value="#{QuestionSearchBackingBean.spsRefernceId}"></h:outputText>


											</TD>
											<TD width="4%"></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<TR>
									<TD colspan="3">
									<FIELDSET style=""><LEGEND><FONT color="black">Associated
									Answers</FONT></LEGEND>

									<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">

										<TR>
											<TD>
											<DIV id="searchResultdataTableDiv"
												style="height:100%;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
											<!-- Search Result Data Table --> <h:dataTable
												rowClasses="dataTableEvenRow,dataTableOddRow"
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{QuestionSearchBackingBean.answerList != null}"
												value="#{QuestionSearchBackingBean.answerList}">
												<h:column>
													<h:outputText id="Minorheading"
														value="#{singleValue.possibleAnswerDesc}"></h:outputText>
												</h:column>
											</h:dataTable></DIV>
											</TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>

								<tr>
									<td valign="top" width="22%"><span class="mandatoryNormal"
										id="creationDateId">&nbsp;</span><h:outputText
										value="Created By" /></td>
									<td width="78%"><h:outputText
										value="#{QuestionSearchBackingBean.createdUser}" /></td>

								</tr>
								<tr>
									<td valign="top" width="22%"><span class="mandatoryNormal"
										id="createdBy">&nbsp;</span><h:outputText value="Created Date" /></td>
									<td width="78%"><h:outputText
										value="#{QuestionSearchBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></td>
								</tr>
								<tr>
									<td valign="top" width="22%"><span class="mandatoryNormal"
										id="updationDate">&nbsp;</span><h:outputText
										value="Last Updated By" /></td>
									<td width="78%"><h:outputText
										value="#{QuestionSearchBackingBean.updatedUserId}" /></td>
								</tr>
								<tr>
									<td valign="top" width="22%"><span class="mandatoryNormal"
										id="updateBy">&nbsp;</span><h:outputText
										value="Last Updated Date" /></td>
									<td width="78%"><h:outputText
										value="#{QuestionSearchBackingBean.updatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></td>
								</tr>
								<tr>
									<TD width="22%"></TD>
								</tr>
								<tr>
									<TD width="22%"></TD>
								</tr>
								<tr>
									<TD width="172">&nbsp;&nbsp;</TD>
								</tr>

							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<td width="2%">&nbsp;</td>
								<td width="65%">&nbsp;</td>
								<td width="33%">
								<table width=100%>
									<tr>
										<td width="20%"><h:outputText value="State" /></td>
										<td width="198">:&nbsp;<h:outputText
											value="#{QuestionSearchBackingBean.state}" /></td>
									</tr>
									<tr>
										<td width="20%"><h:outputText value="Status" /></td>
										<td width="198">:&nbsp;<h:outputText
											value="#{QuestionSearchBackingBean.status}" /></td>
									</tr>
									<tr>
										<td width="20%"><h:outputText value="Version" /></td>
										<td width="198">:&nbsp;<h:outputText
											value="#{QuestionSearchBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
							</tr>

						</table>
						</FIELDSET>

						<BR>
						&nbsp;&nbsp;&nbsp;</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  --></td>
		</tr>
		<TR>
			<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
		</TR>
	</table>
	</h:form>
	</BODY>
</f:view>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="QuestionPrint" /></form>
</HTML>
