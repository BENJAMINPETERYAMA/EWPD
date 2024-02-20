<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
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
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script>window.print();</script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
	 function noenter(){
  	 return !(window.event && window.event.keyCode == 13); 
	 }
</script>
<TITLE>Admin Method Mapping Print</TITLE>
<script language="JavaScript" src="../../js/wpd.js"></script>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="AdminMethodMappingEditForm">

		<TABLE width="100%" cellpadding="1" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:1px;width:97%">
				<h:outputText id="breadcrumb"
					value="#{adminMethodMappingBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>

		<TABLE>
			<TBODY>
				<TR>
					<TD><w:message></w:message></TD>
				</TR>
			</TBODY>
		</TABLE>

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><h:inputHidden
					value="#{adminMethodMappingBackingBean.loadAdminMethodMappingForEditPrint}" />
				</TD>
			</TR>

			<TR>
				<td width="80%">
				<div id="genInfo" >
				<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:97%">
                <DIV id="header" class="tabTitleBar"
									style="position:relative;width:100% ">&nbsp;Admin Method Mapping</DIV>
				<TABLE width="80%" border="0" cellspacing="0" cellpadding="0">
					<TR>
                    <td>
					<TABLE border="0" cellspacing="5" cellpadding="5" class="outputText">
							<TBODY>
								<TR>
									<TD valign="top" width="25%"><h:outputText value="Admin Method Number" /></TD>
                                    
									<TD width="75%"><h:outputText id="adminMethodNo"
										value="#{adminMethodMappingBackingBean.adminMethodNo}" /></TD>

								</TR>

								<TR>
									<TD valign="top" width="25%"><h:outputText value="Admin Method Description" /></TD>
									<TD width="75%"><h:outputText id="description"
										value="#{adminMethodMappingBackingBean.description}" /></TD>

								</TR>

								<TR>
									<TD valign="top" width="25%"><h:outputText value="Processing Method" /></TD>
									<TD width="75%"><h:outputText id="processMethodDesc"
										value="#{adminMethodMappingBackingBean.processMethodDesc}" />

									</TD>

								</TR>
								<TR>
									<TD valign="top" width="25%"><h:outputText value="Term" /></TD>
									<TD  width="75%"><h:outputText id="term"
										value="#{adminMethodMappingBackingBean.term}" /></TD>

								</TR>

								<TR>
									<TD valign="top" width="25%"><h:outputText value="Qualifier" /></TD>
									<TD  width="75%"><h:outputText id="qualifier"
										value="#{adminMethodMappingBackingBean.qualifier}" /></TD>

								</TR>

								<TR>
									<TD valign="top" width="25%"><h:outputText value="Provider Arrangement" /></TD>
									<TD width="75%"><h:outputText id="pva"
										value="#{adminMethodMappingBackingBean.pva}" /></TD>

								</TR>
								<TR>
									<TD valign="top" width="25%"><h:outputText value="Data Type" /></TD>
									<TD width="75%"><h:outputText id="dataType"
										value="#{adminMethodMappingBackingBean.datatype}" /></TD>

								</TR>


								<TR>
									<TD valign="top" width="25%"><h:outputText value="Comments" /></TD>
									<TD  width="75%"><h:outputText id="comments"
										value="#{adminMethodMappingBackingBean.comments}" /></TD>

								</TR>
								<TR>
									<TD valign="top" width="25%"><h:outputText value="Question and Answers" /></TD>
									<TD  width="75%"><h:dataTable headerClass="dataTableHeader"
										id="searchResultTable" var="singleValueQuestionAnswer"
										cellpadding="3" cellspacing="1" width="100%" border="0"
										rendered="#{adminMethodMappingBackingBean.questions != null}"
										value="#{adminMethodMappingBackingBean.questions }"
										rowClasses="">
										<h:column>
											<h:outputText id="adminMethodQuestionsDesc"
												value="#{singleValueQuestionAnswer.questionDesc}">
											</h:outputText>

											<f:verbatim>&nbsp;?&nbsp;&nbsp;</f:verbatim>

											<h:outputText id="adminMethodQuestionsAnswer"
												value="#{singleValueQuestionAnswer.possibleAnswer}">
											</h:outputText>
										</h:column>
									</h:dataTable></TD>


								</TR>
								<tr>
									<td valign="top" width="25%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></td>
									<td width="31%"><h:outputText
										value="#{adminMethodMappingBackingBean.createdUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>
                                 
                                 
								<tr>
									<td valign="top" width="25%"><span class="mandatoryNormal"
										id="createdBy"></span><h:outputText value="Created Date" /></td>
									<td width="31%"><h:outputText
										value="#{adminMethodMappingBackingBean.createdDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>
                                   

								<tr>
									<td valign="top" width="25%"><span class="mandatoryNormal"
										id="updationDate"></span><h:outputText
										value="Last Updated By" /></td>
									<td width="31%"><h:outputText
										value="#{adminMethodMappingBackingBean.lastUpdatedUser}" /></td>
									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>

                                  
								<tr>
									<td valign="top" width="25%"><span class="mandatoryNormal"
										id="updateBy"></span><h:outputText
										value="Last Updated Date" /></td>
									<td width="31%"><h:outputText
										value="#{adminMethodMappingBackingBean.lastUpdatedDate}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</td>

									<td width="44%"><f:verbatim></f:verbatim></td>
								</tr>


							</TBODY>
						</TABLE>
                     </td>
					</TR>
				</TABLE>
				</fieldset>
				</div>

				</TD>
			</TR>

		</TABLE>
	</h:form>
	</BODY>
</f:view>
<script>
</script>


</HTML>
