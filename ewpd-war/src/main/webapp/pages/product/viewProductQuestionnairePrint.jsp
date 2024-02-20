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

<TITLE>Print Questionnaire</TITLE>
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
	
		<h:inputHidden id="viewQuestionnaireHidden"
			value="#{adminOptionQuestionnaireBackingBean.viewProductQuestionnaire}"></h:inputHidden>
		
		<TABLE cellpadding="0" cellspacing="0">	
			<tr><td>&nbsp;</td></tr>
			<tr>
						<td width="1000"><FIELDSET style="margin-left:3px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:99%"><h:outputText id="breadcrumb" 
		                              value="#{adminOptionQuestionnaireBackingBean.printBreadCrumb}">
		                        </h:outputText> 
		                   </FIELDSET>
						</td>		
			</tr>
			<TR>
				<TD width="1000"><br/><h:form styleClass="form" id="questionnaireView">
							<FIELDSET
								style="margin-left:3px;margin-right:-3px;padding-bottom:1px;padding-top:3px;width:99%;height:420"><!--	Start of Table for actual Data	-->


							<strong><h:outputText
								rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire == null}"
								value="No Questionnaire Associated."></h:outputText></strong>
							<TABLE id="associatedQuestionsTable" width="100%" cellpadding="0"
								cellspacing="0">

								<TR>
									<TD colspan="2">
									<DIV id="associatedQuestionsDiv">
									<TABLE id="questionHeaderTable" cellspacing="1"
										 cellpadding="3" border="0" width="100%">
										<TBODY>
											<TR>
												<TD  align="left" width="70%"><strong>&nbsp;Question
												[Reference]</strong></TD>
												<TD  align="left" width="10%"><strong>Line
												of Business</strong></TD>
												<TD  align="left" width="10%"><strong>Business
												Entity </strong></TD>
												<TD  align="left" width="10%""><strong>Business
												Group</strong></TD>
											</TR>
											
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>

								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"><h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="associatedQuestionTable" var="eachRow" cellpadding="3"
										width="100%" cellspacing="1" 
										value="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire}"										
										rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire != null}">
										<h:column>
											<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
											<h:inputHidden id="hiddenLevel" value="#{eachRow.level}"></h:inputHidden>
											<h:inputHidden id="hiddenQuestnrId"
												value="#{eachRow.questionnaireId}"></h:inputHidden>
											<h:outputText id="levelInd" value="#{eachRow.levelIndicator}"></h:outputText>
											<h:outputText id="assAnswerDesc"
												value="#{eachRow.answerDesc}"></h:outputText>
											<h:outputText id="assQuestionDesc"
												value="#{eachRow.questionDesc}"></h:outputText>
											<h:outputText id="assReferenceDesc"
												value="#{eachRow.referenceDesc}"></h:outputText>											
										</h:column>
										<h:column>
											<h:outputText id="lob" value="#{eachRow.lobString}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="be" value="#{eachRow.beString}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="bg" value="#{eachRow.bgString}"></h:outputText>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
							<br>
							<br />

							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="hiddenAdminOptionId"
						value="#{adminOptionQuestionnaireBackingBean.adminId}"></h:inputHidden>
					<h:inputHidden id="adminName"
						value="#{adminOptionQuestionnaireBackingBean.adminName}"></h:inputHidden>
					<!-- End of hidden fields  -->

				</h:form>
			
	</BODY>
</f:view>
<script>
if(document.getElementById('questionnaireView:associatedQuestionTable') != null) {
	if(document.getElementById('questionnaireView:associatedQuestionTable').offsetHeight <= 240)
	{
		setColumnWidth('questionHeaderTable','70%:10%:10%:10%');
		setColumnWidth('questionnaireView:associatedQuestionTable','70%:10%:10%:10%');
		syncTables('questionHeaderTable','questionnaireView:associatedQuestionTable');
	}
	else
	{
		setColumnWidth('questionHeaderTable','70%:10%:10%:10%');
		setColumnWidth('questionnaireView:associatedQuestionTable','70%:10%:10%:10%');
		syncTables('questionHeaderTable','questionnaireView:associatedQuestionTable');
	}
}
window.print();
</script>
</HTML>

