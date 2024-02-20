<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/test/BenefitPopup.java" --%><%-- /jsf:pagecode --%>

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added inplace of f:attribute -->	
<style type="text/css">

.selectOrOptionColumn {
	width: 10%;
	text-align:center;
	vertical-align: middle;
}.shortDescriptionColumn {
	width: 90%;
	
}
</style>

<TITLE>Benefits Popup</TITLE>
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
</HEAD>
<f:view>
	<BODY>
	<h:form id="benefitForm">
		<h:inputHidden value = "#{BenefitComponentHierarchiesBackingBean.retrieveAllBenefitRecords}"></h:inputHidden>
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
          
			<tr>
				<td align="left">&nbsp;<h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('benefitForm:benefitDataTable',2);return false;"></h:commandButton>
				</td>
			</tr>
		</table>
		<table width="98%" align="right" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="10%" align="center" valign="middle"><h:selectBooleanCheckbox
								onclick="checkAllid(this,'benefitForm:benefitDataTable','benefitChkBox'); "></h:selectBooleanCheckbox></TD>
							<TD width="90%" align="center"><strong> <h:outputText
								value="Benefits">
							</h:outputText> </strong></td>
						</tr>
					</table>
					</TD>
				</TR>
				<tr>
					<td>
					<DIV id="popupDataTableDiv"  class="popupDataTableDiv"
						style="width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
					<h:dataTable cellspacing="1" width="100%" id="benefitDataTable"
					columnClasses="selectOrOptionColumn,shortDescriptionColumn"
						value="#{BenefitComponentHierarchiesBackingBean.benefitsList}"
						var="benefit" cellpadding="0" bgcolor="#cccccc">
						<h:column>
							
							<f:verbatim>
								<h:selectBooleanCheckbox id="benefitChkBox">
								</h:selectBooleanCheckbox>
							</f:verbatim>
						</h:column>
						<h:column>
							
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenTermValue"
								value="#{benefit.benefitName}" />
                            <f:verbatim>&nbsp;</f:verbatim>
							<h:inputHidden id="hiddenTermValueDesc"
								value="#{benefit.benefitId}" />
							<h:outputText id="benefitFirst" value="#{benefit.benefitName}"></h:outputText>
						</h:column>


					</h:dataTable></DIV>
					</td>
				</tr>
			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">

	tigra_tables('benefitForm:benefitDataTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	setColumnWidth('benefitForm:benefitDataTable','10%');


</script>
</HTML>



