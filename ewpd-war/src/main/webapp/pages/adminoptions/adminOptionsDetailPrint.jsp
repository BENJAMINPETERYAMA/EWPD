<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/adminoptions/AdminOptionsDetailPrint.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Print Admin Options</TITLE>
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
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<tr><td>&nbsp; </td></tr>
		<TR>
					<TD>  <FIELDSET style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98.5%">
									<h:inputHidden id="viewAdminOptions"
				value="#{createAdminOptionBackingBean.viewAdminOptions}"></h:inputHidden>
                                    <h:outputText id="breadcrumb" 

                                          value="#{createAdminOptionBackingBean.breadCrumpText}">

                                    </h:outputText>

                              </FIELDSET>


					</TD>
				</TR>
		<TR>
			
			<TD><h:form styleClass="form" id="adminOptionForm">

				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
						<TD colspan="2" valign="top" class="ContentArea"><!-- Table containing Tabs -->
						<TABLE cellspacing="1" bgcolor="#cccccc"
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%"
							cellpadding="3" border="0">

							<TR>
								<TD width="5%"><h:outputText value="Admin Options"></h:outputText></TD>
								
							</TR>
						</TABLE>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<BR />
						<!--	Start of Table for actual Data	-->
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText">
							<TBODY>
								<TR>
									<TD width="180"><h:outputText id="outTxtAdminName" value="Name"
										styleClass="#{createAdminOptionBackingBean.requiredAdminName ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<TD width="200"><h:outputText id="txtAdminName"
										value="#{createAdminOptionBackingBean.adminName}" /> <h:inputHidden
										id="hiddenAdminName"
										value="#{createAdminOptionBackingBean.adminName}"></h:inputHidden>
									</TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtTerm" value="Term"
										styleClass="#{createAdminOptionBackingBean.requiredTerm ?
										 'mandatoryError' : 'mandatoryNormal'}" /></TD>
									<TD><h:outputText id="txtTerm"
										value="#{createAdminOptionBackingBean.term}"></h:outputText> <h:inputHidden
										id="hiddenTerm" value="#{createAdminOptionBackingBean.term}"></h:inputHidden>
									</TD>
									<TD width="25" valign="top"></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtQualifier" value="Qualifier" /></TD>
									<TD><h:outputText id="txtQualifier"
										value="#{createAdminOptionBackingBean.qualifier}"></h:outputText>
									<h:inputHidden id="hiddenQualifier"
										value="#{createAdminOptionBackingBean.qualifier}"></h:inputHidden>
									</TD>
									<TD valign="top"></TD>
								</TR>

								<TR>
									<TD><h:outputText id="outTxtCreatedUserId"
										value="Created By" /></TD>
									<TD><h:outputText id="createdUserId"
										value="#{createAdminOptionBackingBean.createdUserId}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtCreatedDate" value="Created Date" />
									</TD>
									<TD><h:outputText id="createdDate"
										value="#{createAdminOptionBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedUserId"
										value="Last Updated By" /></TD>
									<TD><h:outputText id="updatedUserId"
										value="#{createAdminOptionBackingBean.updatedUserId}" /></TD>
								</TR>
								<TR>
									<TD><h:outputText id="outTxtUpdatedDate"
										value="Last Updated Date" /></TD>
									<TD><h:outputText id="updatedDate"
										value="#{createAdminOptionBackingBean.updatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText></TD>
								</TR>

								<TR>
									<TD>&nbsp;</TD>
								</TR>

							</TBODY>
						</TABLE>
						</fieldset>
						<br>

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>

								<td>
								<table Width=100%>
									<tr>
										<td width="180"><h:outputText id="outTxtState" value="State" /></td>
										<td>:&nbsp;<h:outputText id="state"
											value="#{createAdminOptionBackingBean.state}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtStatus" value="Status" /></td>
										<td>:&nbsp;<h:outputText id="status"
											value="#{createAdminOptionBackingBean.status}" /></td>
									</tr>
									<tr>
										<td><h:outputText id="outTxtVersion" value="Version" /></td>
										<td>:&nbsp;<h:outputText id="version"
											value="#{createAdminOptionBackingBean.version}" /></td>
									</tr>
								</table>
								</td>
							</tr>

						</table>

						</fieldset>
						<br />
						<h:inputHidden id="viewAdminOptionsQuestions"
							value="#{adminOptionQuestionnaireBackingBean.printQuestionnaireList}">
						</h:inputHidden>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%"><!--	Start of Table for actual Data	-->
						<strong><h:outputText rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire == null}" 
										value="No Questionnaire Associated."></h:outputText></strong>

						<DIV id="associatedQuestionsDiv">
						<TABLE id="associatedQuestionsTable" width="100%" cellpadding="0"
							cellspacing="0" >

							<TR bgcolor="#cccccc">
								<TD colspan="2">
								<TABLE id="questionHeaderTable" cellspacing="1"
									bgcolor="#cccccc" cellpadding="3" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD  bgcolor="#cccccc" align="left"><strong>Question [Reference]</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Parent Required</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Functional Domain</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Line of Business</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Business Entity </strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Business Group</strong></TD>
											<TD  bgcolor="#cccccc" align="left"><strong>Market Business Unit</strong></TD>											
										</TR>
									</TBODY>
								</TABLE>
								</TD>
							</TR>

							<TR>
								<TD colspan="2" width="100%"><h:dataTable
									styleClass="outputText" 
									id="associatedQuestionTable" var="eachRow" cellpadding="3"
									width="100%" cellspacing="1"
									value="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire}"
									rowClasses="dataTableOddRow"
									rendered="#{adminOptionQuestionnaireBackingBean.associatedquestionnaire != null}">
									<h:column>
										<f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
										<h:inputHidden id="hiddenLevel"
											value="#{eachRow.level}"></h:inputHidden>
										<h:inputHidden id="hiddenQuestnrId"
											value="#{eachRow.questionnaireId}"></h:inputHidden>
										<h:outputText id="levelInd" 
											value="#{eachRow.levelIndicator}"></h:outputText>
										<h:outputText id="assAnswerDesc" 
											value="#{eachRow.answerDesc}"></h:outputText>
										<h:outputText id="assQuestionNumberDesc"
											value="#{eachRow.questionDesc}"></h:outputText>
										<h:outputText id="assReferenceDesc"
											value="#{eachRow.referenceDesc}"></h:outputText>
										<!-- Add the required hidden fields after this  -->
									</h:column>
									<h:column wrap>
										<h:selectBooleanCheckbox title="parentcheckbox" disabled="true"
										value="#{eachRow.parentRequiredChecked}"
											rendered="#{!eachRow.root}">
										</h:selectBooleanCheckbox>
									</h:column>
									<h:column wrap>
										<h:outputText title="functionaldomain"
											value="#{eachRow.functionalDomainDesc}" />
									</h:column>
									<h:column>
										<h:outputText id="lob"
											value="#{eachRow.lobString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="be"
											value="#{eachRow.beString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="bg"
											value="#{eachRow.bgString}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="bu"
											value="#{eachRow.buString}"></h:outputText>
									</h:column>
								</h:dataTable></TD>
							</TR>
						</TABLE>
						</DIV>
						</FIELDSET>

						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				<h:inputHidden id="hiddenCreatedUser"
					value="#{createAdminOptionBackingBean.createdUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenCreatedDate"
					value="#{createAdminOptionBackingBean.createdDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="hiddenUpdatedUser"
					value="#{createAdminOptionBackingBean.updatedUserId}"></h:inputHidden>
				<h:inputHidden id="hiddenUpdatedDate"
					value="#{createAdminOptionBackingBean.updatedDate}">
					<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
				</h:inputHidden>
				<h:inputHidden id="time" value="#{requestScope.timezone}">
				</h:inputHidden>


				<h:inputHidden id="hiddenStatus"
					value="#{createAdminOptionBackingBean.status}"></h:inputHidden>
				<h:inputHidden id="hiddenVersion"
					value="#{createAdminOptionBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="adminId"
					value="#{createAdminOptionBackingBean.adminOptionId}"></h:inputHidden>
				<h:inputHidden id="adminIdQstn"
					value="#{adminOptionQuestionnaireBackingBean.adminId}"></h:inputHidden>


				<!-- End of hidden fields  -->

			</h:form></TD>
		</TR>

	</TABLE>

	</BODY>
</f:view>
<script>
		if(document.getElementById('adminOptionForm:associatedQuestionTable')!= null){			
			document.getElementById('adminOptionForm:associatedQuestionTable').onresize = syncTables;			
			setColumnWidth('questionHeaderTable', '30%:9%:21%:10%:10%:10%:10%');
			setColumnWidth('adminOptionForm:associatedQuestionTable', '30%:9%:21%:10%:10%:10%:10%');
			syncTables('questionHeaderTable','adminOptionForm:associatedQuestionTable');
		}
		if(document.getElementById('adminOptionForm:associatedQuestionTable') == null) {	
			
			var divObj = document.getElementById('associatedQuestionsDiv');
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		
		
		}

formatTildaToComma("adminOptionForm:txtTerm");
formatTildaToComma("adminOptionForm:txtQualifier");
</script>
<script>window.print();</script>
</HTML>

