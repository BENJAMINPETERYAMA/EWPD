<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<TITLE>Print Benefit Definitions</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
	<h:form styleClass="form" id="componentBenefitDefinitionPrint">
<table>
	
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="padding-bottom:1px;padding-top:1px;width:900">
						<h:outputText id="breadcrumb" 
							value="#{benefitComponentBackingBean.printBreadCrumbText}">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<tr>
				<TD>
					
						<FIELDSET style="width:900;height:500">
							<DIV id="messageTextForNoBenefitLevelsDiv" align="center"><BR>
								<CENTER>
								<BR>
								<STRONG>&nbsp;<h:outputText value="No Benefit Level available." /></STRONG>
								</CENTER>
						</DIV>
							<DIV id="associatedBenefitspanelHeader" >
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR bgcolor="#cccccc">
												<TD bgcolor="#CCCCCC" height="23px" colspan="3"><B><h:outputText
													value="Associated Benefit Lines" /></B></TD>
											</TR>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Description" /></TD>
												<TD align="left"><h:outputText value="Term" /></TD>
												<TD align="left"><h:outputText value="Frequency - Qualifier" /></TD>
												<TD align="left"><h:outputText value="PVA" /></TD>
												<TD align="left"><h:outputText value="Format" /></TD>
												<TD align="left"><h:outputText value="Benefit Value" /></TD>
												<TD align="left"><h:outputText value="Reference" /></TD>
												
											</TR>
										</TBODY>
								</TABLE>
							
							<h:dataTable headerClass="tableHeader" id="panelTable" border="0" width="100%" value="#{ComponentBenefitDefinitionsBackingBean.benefitLevelsListForView}" 
								var="eachRow" cellpadding="0" cellspacing="1" >
								<h:column>
									<h:inputHidden id="benefitLineIdHidden" value="#{eachRow.benefitLineId}" /> 
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.description}" />
								</h:column>

								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.term}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.qualifier}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.pva}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.format}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.benefitValue}" />
								</h:column>
								<h:column>
									<h:outputText styleClass="formOutputColumnField" value="#{eachRow.reference}" />
								</h:column>
								
							</h:dataTable>
<!-- Space for hidden fields --> 
<h:inputHidden id="selectedBenefitLineId"  />
<h:inputHidden id="bencompHidden" value="#{ComponentBenefitDefinitionsBackingBean.benefitComponent}"></h:inputHidden>
<h:inputHidden id="hidden1" value="value1"></h:inputHidden> 
<h:inputHidden id="displayHeaderPanel" value="#{ComponentBenefitDefinitionsBackingBean.headerDisplay}" />
<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;">
<f:verbatim />
</h:commandLink> 
<!-- End of hidden fields  -->
							</DIV>
						</FIELDSET>
						<DIV id="dummyDiv"></DIV>
					
				</TD>

			</tr>
	

</table>
	</h:form>
			
			
		</hx:scriptCollector>
</BODY>
</f:view>
<script>

	var panelTableObject = document.getElementById('componentBenefitDefinitionPrint:panelTable');
		if(panelTableObject.rows.length > 0){
if(document.getElementById('componentBenefitDefinitionPrint:panelTable').offsetHeight <= 189) {
			var panelDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
			panelDivObj.style.visibility = "hidden";
			panelDivObj.style.height = "0px";
			setColumnWidth('resultHeaderTable','15%:14%:17%:9%:10%:9%:26%');	
			setColumnWidth('componentBenefitDefinitionPrint:panelTable','15%:14%:17%:9%:10%:9%:26%');
}else{
			document.getElementById('resultHeaderTable').width = "100%";
			document.getElementById('componentBenefitDefinitionPrint:panelTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			var panelDivObj = document.getElementById('messageTextForNoBenefitLevelsDiv');
			panelDivObj.style.visibility = "hidden";
			panelDivObj.style.height = "0px";
			document.getElementById('componentBenefitDefinitionPrint:panelTable').width = relTblWidth-17+"px";
			document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
			setColumnWidth('resultHeaderTable','15%:14%:17%:9%:10%:9%:26%');	
			setColumnWidth('componentBenefitDefinitionPrint:panelTable','15%:14%:17%:9%:10%:9%:26%');
}
		}else{
			var msgDivObj = document.getElementById('componentBenefitDefinitionPrint:panelTable');
			var object = document.getElementById('associatedBenefitspanelHeader');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
			object.style.visibility = "hidden";
		}

	


window.print();

</script>
</HTML>
