<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<!--   WAS 6.0 Migration Changes - WAS 7.0 not supports f:attribute hence new style added in place of f:attribute -->
<style type="text/css">
.selectOrOptionColumn {
	width: 10%;
	text-align: center;
	vertical-align: middle;
}
.shortDescriptionColumn {
	width: 30%;
}
.longDescriptionColumn {
	width: 70%;
}
</style>
<TITLE>Benefit Term Popup</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<link rel="stylesheet" href="../../css/wpd.css">
<script language="JavaScript" src="../../js/tigra_tables.js"></script>
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
	<hx:scriptCollector id="scriptCollector1">
		<h:form id="termForm">
			<h:inputHidden value="#{benefitLevelBackingBean.retrieveTermRecords}"></h:inputHidden>
			<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
				<TR>
					<TD align="left">&nbsp;<h:commandButton id="selectButton" value="Select" disabled="true" 
						styleClass="wpdbutton"
						onclick="getCheckedItems_ewpd('termForm:searchResultTable',2);return false;"></h:commandButton></TD>
				</TR>
			</TABLE>


			<TABLE width="98%" align="right" cellpadding="0" border="0"
				cellspacing="0" >
				<TR>
					<TD>
					<div id="popHeading"></div>

					<TABLE id="businessEntityTable" width="100%" cellpadding="0"
						cellspacing="0" bgcolor="#cccccc">
						<TR height="20px">
							<TD width="10%" align="center" valign="middle"></TD>
							<TD align="left" width="20%"></TD>
							<TD width="70%" align="left"><STRONG><h:outputText
								value="Benefit Term"></h:outputText></strong></TD>
						</TR>
					</TABLE>
				</TD>
			</TR>			
			
						<tr>
							<td colspan="2" width="100%"><div style="overflow:auto;" id="popUpDiv"><h:dataTable styleClass="outputText"
								headerClass="dataTableHeader" bgcolor="#cccccc" id="searchResultTable"
								var="termValue" cellpadding="1" width="100%" cellspacing="1"
								rendered="#{benefitLevelBackingBean.termResultList != null}"
								value="#{benefitLevelBackingBean.termResultList}"
								rowClasses="dataTableEvenRow,dataTableOddRow" columnClasses="selectOrOptionColumn,shortDescriptionColumn,longDescriptionColumn">

								<h:column>
								
									<h:selectBooleanCheckbox styleClass="selectBooleanCheckbox"
										id="termCheckBox" value="#{termValue.termValueCheckBx}"></h:selectBooleanCheckbox>
								</h:column>
								<h:column>
							
                                  
									<h:inputHidden id="hiddenTermValue"
										value="#{termValue.description}" />
                                      
									<h:inputHidden id="hiddenTermValueDesc"
										value="#{termValue.code}" />
                                      <f:verbatim>&nbsp;</f:verbatim>
									<h:outputText id="termValueId" value="#{termValue.code}"></h:outputText>
								</h:column>
								<h:column>
								
                                       <f:verbatim>&nbsp;</f:verbatim>
									<h:outputText id="termValue" value="#{termValue.description}"></h:outputText>
								</h:column>
							</h:dataTable></div></td>
						</tr>
					</TABLE>

			<SCRIPT language="javascript">
			if(null != document.getElementById('termForm:searchResultTable'))
			tigra_tables('termForm:searchResultTable',0,0,'#ffffff','#e1ecf7','#ffccff','#cc99ff');
		</SCRIPT>
			<!--<input type="button" class="wpdbutton" name="something" value="somevalue" />-->
			<FORM></FORM>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
document.getElementById('termForm:selectButton').disabled  = false;
if(null != document.getElementById('termForm:searchResultTable')){
	matchCheckboxItems_ewpd('termForm:searchResultTable',window.dialogArguments.selectedValues,2,2,2);
}
else{
	// id added and changed to fix script error
	document.getElementById('popUpDiv').style.visibility = "hidden";
}

</script>
</HTML>
