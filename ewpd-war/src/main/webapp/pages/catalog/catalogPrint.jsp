
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/standardBenefit/StandardBenefitView.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Print Catalog</TITLE>
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
			<td><h:form styleClass="form" id="catalogPrintForm">
				<h:inputHidden id="viewCatalogId"
					value="#{catalogBackingBean.viewCatalogId}" />
				<!-- End of Tab table -->

				<!--	Start of Table for actual Data	-->
				<TABLE border="0" cellspacing="1" cellpadding="3" class="outputText"
					width="100%">
					<TBODY>
					<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:93.5%">
						<h:outputText id="breadcrumb" 
							value="#{catalogBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
						<TR>

							<TD colspan=3>
							<div id="catalogGenInfo">
							<FIELDSET style="width:70%">


							<TABLE width="100%">
								<TR>
									<TD width="37%"><h:outputText value="Name" /></TD>
									<TD width="63%"><h:outputText id="txtCatalogName"
										value="#{catalogBackingBean.catalogName}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="37%"><h:outputText value="Datatype" /></TD>
									<TD width="63%"><h:outputText id="txtCatalogDatatype"
										value="#{catalogBackingBean.catalogDatatype}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="37%"><h:outputText value="Size" /></TD>
									<TD width="63%"><h:outputText id="txtCatalogSize"
										value="#{catalogBackingBean.catalogSize}"></h:outputText></TD>
								</TR>
								<TR>
									<TD valign="top" width="37%"><h:outputText id="descriptionStar"
										value="Description " /></TD>
									<TD width="63%"><h:outputText id="txtDescription"
										value="#{catalogBackingBean.description}"></h:outputText></TD>
								</TR>
								<TR>
									<TD width="37%"><span class="mandatoryNormal"
										id="creationDateId"></span><h:outputText value="Created By" /></TD>
									<TD width="63%"><h:outputText id="createdUserView"
										value="#{catalogBackingBean.createdUser}" /></TD>
								</TR>
								<TR>
									<TD width="37%"><span class="mandatoryNormal" id="createdBy"></span><h:outputText
										value="Created Date" /></TD>
									<TD width="63%"><h:outputText id="createdDateView"
										value="#{catalogBackingBean.createdTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
								<TR>
									<TD width="37%"><span class="mandatoryNormal" id="updationDate"></span><h:outputText
										value="Last Updated By" /></TD>
									<TD width="63%"><h:outputText id="updatedUserView"
										value="#{catalogBackingBean.lastUpdatedUser}" /></TD>
								</TR>
								<TR>
									<TD width="37%"><span class="mandatoryNormal" id="updateBy"></span><h:outputText
										value="Last Updated Date" /></TD>
									<TD width="63%"><h:outputText id="updatedTimeView"
										value="#{catalogBackingBean.lastUpdatedTimestamp}">
										<f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
									</h:outputText> <h:outputText value="#{requestScope.timezone}"></h:outputText>
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>

							</div>


							</TD>
						</TR>



					</TBODY>
				</TABLE></td>
		</tr>
		<tr>
			<td></h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>


<script>
/*formatTildaToComma("catalogPrintForm:txtLob");
formatTildaToComma("catalogPrintForm:txtBusinessEntity");
formatTildaToComma("catalogPrintForm:txtBusinessGroup");*/
var printForGenInfo = document.getElementById("catalogPrintForm:viewCatalogId").value;
if( null == printForGenInfo || "" == printForGenInfo ){
		var genInfoDivObj = document.getElementById('catalogGenInfo');
		genInfoDivObj.style.visibility = "hidden";
		genInfoDivObj.style.height = "0px";
		genInfoDivObj.innerText = null;
	}
</script>
<script>window.print();</script>
</HTML>
