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

<TITLE>Print Role Detail</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="20">
		<tr>
			<td><h:form styleClass="form" id="roleDetailPrintForm">
				<h:inputHidden id="viewRoleId" value="#{roleBackingBean.viewRoleId}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
						<TR>

							<TD colspan=3>Administration >> Role (<h:outputText
								id="headerRoleName" value="#{roleBackingBean.roleName}"></h:outputText>)
							>> Print
							<div id="roleGenInfo">
							<FIELDSET style="width:70%">
							<div id="panel2Header" class="tabTitleBar"
								style="position:relative;width:100% ">General Information</div>

							<TABLE width="60%">

								<TR>
									<TD width="45%"><h:outputText value="Name" /></TD>
									<TD width="50%"><h:outputText id="txtRoleName"
										value="#{roleBackingBean.roleName}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%" valign="top"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="50%"><h:outputText id="txtDescription"
										value="#{roleBackingBean.description}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText
										value="Created By" /></TD>
									<TD width="50%"><h:outputText id="createdUserView"
										value="#{roleBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="50%"><h:outputText id="createdDateView"
										value="#{roleBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="50%"><h:outputText id="updatedUserView"
										value="#{roleBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="45%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="50%"><h:outputText id="updatedTimeView"
										value="#{roleBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
							</TABLE>

							</FIELDSET>

							<FIELDSET style="width:70%"><h:outputText
								value="No Modules Available."
								rendered="#{roleBackingBean.searchResultList == null}" />
							<TABLE border="0" cellspacing="1" cellpadding="3" width="100%"
								class="outputText">
								<TBODY>


									<TR valign="top">
										<TD width="100%">
										<DIV id="panel2">
										<DIV id="panel2Header" class="tabTitleBar"
											style="position:relative;width:100% cursor:hand;">Modules</DIV>
										<DIV id="panel2Content" class="tabContentBox"
											style="position:relative;font-size:10px;"><BR>
										<table cellpadding="0" cellspacing="0" width="100%" border="0">
											<h:dataTable headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												cellspacing="1"
												rendered="#{roleBackingBean.searchResultList != null}"
												value="#{roleBackingBean.searchResultList}" border="0"
												width="100%">
												<h:column>
													<h:outputText id="modName"
														value="#{singleValue.moduleName}"></h:outputText>
													
												</h:column>
											</h:dataTable>

										</table>
										</div>
										</DIV>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							</td>
						</tr>
						<tr>
							<td></h:form></td>
						</tr>
				</table>
	</BODY>
</f:view>
<script>
initialize();
		function initialize(){
			if(document.getElementById('roleDetailPrintForm:searchResultTable') == null) 
				
				document.getElementById("panel2").style.visibility = 'hidden';
				
			}
	
var printForGenInfo = document.getElementById("roleDetailPrintForm:viewRoleId").value;
if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('roleDetailPrint');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
</script>
<script>window.print();</script>
</HTML>
