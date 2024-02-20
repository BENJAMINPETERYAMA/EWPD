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

<TITLE>Question Print</TITLE>
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
			<td><h:form id="QuestionPrintForm">
				<h:inputHidden id="view" value="#{questionViewBackingBean.view}" />
			  <table><tr><td></td></tr></table>
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
				</br>
					<tr>
						<td><FIELDSET style="margin-left:15px;margin-right:-30px;padding-bottom:1px;padding-top:1px;width:57.5%">
		                        <h:outputText id="breadcrumb" 
		                              value="#{questionViewBackingBean.breadCrumbPrint}">
		                        </h:outputText>
		                   </FIELDSET>
						</td>
					</tr>
					<TR><TD width="5%"></TD></TR>
					<TR><TD width="5%"></TD></TR>
					<TR><TD width="5%"></TD></TR>
						
					<TR>

						
						<TD colspan="1" valign="top" class="ContentArea" width="532">
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

						<!--	Start of Table for actual Data	-->

						<TABLE border="0" cellspacing="0" cellpadding="0" width="100%"
							class="outputText">
							<TBODY>
								<tr>
									<TD colspan=3>
									<FIELDSET >
									<TABLE>
										<TR>
											<TD width="127"><h:outputText value="Question" /></TD>
											<TD width="164"><h:outputText id="txtQuestion"
												value="#{questionViewBackingBean.questionDesc}"></h:outputText>


											</TD>
											<TD></TD>
										</TR>
										<TR>
											<TD width="127" valign = "top"><h:outputText value="Functional Domain" /></TD>
											<TD width="164" ><h:outputText id="funDomain" 
												value="#{questionViewBackingBean.functionalDomain}"></h:outputText> 


											</TD>
											<TD></TD>
										</TR>

										<TR>
											<TD width="127" valign = "top"><h:outputText value="Term" /></TD>
											<TD width="164" ><h:outputText id="term" 
												value="#{questionViewBackingBean.term}"></h:outputText> 


											</TD>
											<TD></TD>
										</TR>
										<TR>
											<TD width="127" valign = "top"><h:outputText value="Qualifier" /></TD>
											<TD width="164" ><h:outputText id="qualifier" 
												value="#{questionViewBackingBean.qualifier}"></h:outputText> 


											</TD>
											<TD></TD>
										</TR>
										<TR>
											<TD width="127"><h:outputText value="Provider Arrangement" /></TD>
											<TD width="164"><h:outputText id="prvdrArrangement"
												value="#{questionViewBackingBean.providerArrangement}"></h:outputText>


											</TD>
											<TD></TD>
										</TR>

										<TR>
											<TD width="127"><h:outputText value="Data Type" /></TD>
											<TD width="164"><h:outputText id="txtDataType"
												value="#{questionViewBackingBean.dataType}"></h:outputText>
											</TD>
											<TD></TD>
										</TR>
										<TR>
											<TD width="127" valign = "top"><h:outputText value="Reference" /></TD>
											<TD width="164" ><h:outputText id="spsRef" 
												value="#{questionViewBackingBean.spsReference}"></h:outputText> 


											</TD>
											<TD></TD>
										</TR>
									</TABLE>
									</FIELDSET>
									</TD>
								</TR>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<TR>


									<TD colspan="3">
									<FIELDSET >
										<LEGEND><FONT color="black">Associated
									Answers</FONT></LEGEND>

									<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">

										<TR>
											<TD>
											<DIV id="searchResultdataTableDiv"
												style="height:100%;width:100%;position:relative;z-index:1;font-size:10px;">
											<!-- Search Result Data Table --> <h:dataTable
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" 
												rendered="#{questionViewBackingBean.answerList != null}"
												value="#{questionViewBackingBean.answerList}">
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
									<td width="281"><h:outputText value="Created By" /></td>
									<td width="792"><h:outputText
										value="#{questionViewBackingBean.createdUser}" /></td>

								</tr>
								<tr>
									<td width="281"><h:outputText value="Created Date" /></td>
									<td width="74%"><h:outputText
										value="#{questionViewBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></td>
								</tr>
								<tr>
									<td width="281"><h:outputText value="Last Updated By" /></td>
									<td width="792"><h:outputText
										value="#{questionViewBackingBean.updatedUserId}" /></td>
								</tr>
								<tr>
									<td width="281"><h:outputText value="Last Updated Date" /></td>
									<td width="74%"><h:outputText
										value="#{questionViewBackingBean.updatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></td>
								</tr>


							</TBODY>
						</TABLE>
						<!--	End of Page data	--></fieldset>
						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>


								<td>
								<table>
									<tr>
										<td width="45%"><h:outputText value="State" /></td>
										<td width="55%">:&nbsp;<h:outputText value="#{questionViewBackingBean.state}" /></td>

									</tr>
									<tr>
										<td width="45%"><h:outputText value="Status" /></td>
										<td width="55%">:&nbsp;<h:outputText value="#{questionViewBackingBean.status}" /></td>

									</tr>
									<tr>
										<td width="45%"><h:outputText value="Version" /></td>
										<td width="55%">:&nbsp;<h:outputText value="#{questionViewBackingBean.version}" /></td>

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
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>

	</table>
	</BODY>
</f:view>


<script>
window.print();
</script>
</HTML>
