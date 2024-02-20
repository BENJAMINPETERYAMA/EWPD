<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<script language="JavaScript" src="../../js/prototype.js"></script>
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

<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
<TITLE>contractViewAllVersions.jsp</TITLE>
</HEAD>
<f:view>
	<BODY onkeypress="return false;">
	<h:form id="viewAllVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Contract Development >> Contract >> View All Versions");
    %> <jsp:include page="../navigation/top_view.jsp"></jsp:include></td>


			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%"><w:message></w:message>
				<div id="searchPanelHeader" class="tabTitleBar"><b>All Versions</b></div>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="10%"><strong><h:outputText
										value="Contract Id"></h:outputText></strong></td>
									<td align="left" width="15%"><strong><h:outputText
										value="No of Date Segments"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Start Date"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Contract type"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Business Entity"></h:outputText></strong></td>
									<td align="left" width="5%"><strong><h:outputText
										value="Contract Status"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Plan Type Year"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="End Date"></h:outputText></strong></td>
									<td align="left" width="20%">&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:292px; overflow:auto; width:100%;"><h:dataTable
							headerClass="dataTableHeader" id="allVersionsTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							bgcolor="#cccccc" rendered="true"
							value="#{contractSearchBackingBean.allVersionsList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="100%">
							<h:column>

								<h:outputText id="contractId" value="A001"></h:outputText>

							</h:column>
							<h:column>

								<h:outputText id="noOfDateSegments" value="2"></h:outputText>

							</h:column>
							<h:column>

								<h:outputText id="startDate" value="12/12/2000">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>

							</h:column>
							<h:column>

								<h:outputText id="contractType" value="Mandate information">

								</h:outputText>

							</h:column>



							<h:column>

								<h:outputText id="businessEntity" value="Unicare"></h:outputText>

							</h:column>
							<h:column>

								<h:outputText id="status" value="Published"></h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="PlanTypeYear" value="2000">

								</h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="EndDate" value="12/12/2010">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
							</h:column>



							<h:column>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								<h:commandButton alt="View" title="View"  id="viewButton"
									image="../../images/view.gif" onclick="return false;"></h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								<h:commandButton alt="Copy" title="Copy" id="copyButton"
									image="../../images/copy.gif" onclick="return false;"></h:commandButton>
								<h:outputText value="" id="a1spaceSpan" rendered=""></h:outputText>
								<h:commandButton alt="Delete" title="Delete" id="deleteButton1"
									image="../../images/delete.gif" rendered="true"></h:commandButton>
								<h:outputText value="" id="a2spaceSpan" rendered="true"></h:outputText>
								<h:commandButton alt="Edit" title="Edit" id="editButton"
									image="../../images/edit.gif" onclick="return false;"
									rendered="true"></h:commandButton>
								<h:outputText value="" id="a3spaceSpan" rendered="true"></h:outputText>
								<h:commandButton alt="SendToTest" title="SendToTest" id="sendToTestButton"
									image="../../images/ds.gif" onclick="return false;"
									rendered="true"></h:commandButton>
								<h:outputText value="" id="a4spaceSpan" rendered="true"></h:outputText>

							</h:column>
						</h:dataTable></div>

						</TD>
					</tr>


				</table>

				</td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>

	</h:form>
	</BODY>
</f:view>
<script>
setColumnWidth('viewAllVersionForm:allVersionsTable','10%:15%:10%:10%,10%:10%:5%:10%:10%');
</script>
</HTML>
