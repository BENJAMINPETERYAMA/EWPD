<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;

}
.shortDescriptionColumn {
	width: 90%;
}
</style>

<TITLE>State Code Popup</TITLE>
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
	<h:form id="stateCodeForm">
		<table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tr>
				<td>
				<TABLE>
					<TBODY>
						<TR>
							<TD>&nbsp;</TD>
						</TR>
					</TBODY>
				</TABLE>
				</td>
			</tr>
			<tr>
				<td align="left"><h:commandButton id="selectButton" value="Select"
					styleClass="wpdbutton"
					onclick="getCheckedItems_ewpd('stateCodeForm:jurisdictionSelectPopupTable',2);return false;"></h:commandButton>
				</td>
			</tr>

		</table>
		<table width="96%" align="center" cellpadding="0" cellspacing="0">
			<TBODY>
				<TR>
					<TD>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#cccccc">
						<tr>
							<td width="6%" align="left"></TD>
							<TD width="94%" align="center"><strong> <h:outputText
								value="State Code">
							</h:outputText> </strong></td>
						</tr>
					</table>
					</TD>
				</TR>


				<tr>
					<td><h:dataTable cellspacing="1" width="100%"
						id="jurisdictionSelectPopupTable" var="eachRow"
						value="#{ReferenceDataBackingBeanCommon.stateResultList}"
						cellpadding="0" bgcolor="#cccccc" columnClasses="selectOrOptionColumn,shortDescriptionColumn" >
						<h:column>
							
							<f:verbatim>
								<wpd:singleRowSelect groupName="jurisdiction" id="minor"
									rendered="true"></wpd:singleRowSelect>

							</f:verbatim>
						</h:column>
						<h:column>
							
							<h:outputText value="#{eachRow.description}"></h:outputText>
							<h:inputHidden id="hiddenJurisdiction"
								value="#{eachRow.description}"></h:inputHidden>
							<h:inputHidden id="hiddenJurisdictionCode"
								value="#{eachRow.code}"></h:inputHidden>
						</h:column>
					</h:dataTable></td>
				</tr>

			</TBODY>
		</table>
	</h:form>
	</BODY>
</f:view>

<script language="javascript">
		
	tigra_tables('stateCodeForm:jurisdictionSelectPopupTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
	matchCheckboxItems_ewpd('stateCodeForm:jurisdictionSelectPopupTable', window.dialogArguments.selectedValues, 1, 1, 1);
	
	// setColumnWidth('termSelectPopupForm:termSelectPopupTable', '5:250');
	

</script>
</HTML>
