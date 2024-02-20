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
<STYLE type="text/css">
  
.gridColumn40{
	width: 40%;
		
}
.gridColumn20{
	width: 20%;	
		
}
</STYLE>
<TITLE>Product Benefit Administration Print</TITLE>
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
	
	<h:form styleClass="form" id="productBenefitAdministrationPrintForm">
		<h:inputHidden value="#{productBenefitAdministrationBackingBean.loadQuestionForPrint}" />
		<table width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD>&nbsp;</TD>

			</TR>
			<TR>
				<TD>
				<FIELDSET
					style="margin-left:15px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
				<h:outputText id="breadcrumb"
					value="#{productBenefitAdministrationBackingBean.printBreadCrumbText}">
				</h:outputText></FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>&nbsp;</TD>
			</TR>
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD colspan="2" valign="top" class="ContentArea">
						
						<div id="emptymsg">
								<fieldset
							style="margin-left:6px;margin-right:-4px;padding-bottom:1px;padding-top:6px;width:100%">
								<h:outputText value="No questions Available."
							styleClass="dataTableColumnHeader" />	</fieldset></div>
						<div id="resultHeaderDiv" style="width:100%;">	
						<fieldset
							style="margin-left:6px;margin-right:-4px;padding-bottom:1px;padding-top:6px;width:99%">						
											
						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>

								<div id="resultHeaderDiv1" style="width:100%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<tr>
										<TD>											
											<div id="resultHeaderTitle2"><b><h:outputText value="Associated Questionnaire"></h:outputText></b></div>
										</TD>
									</tr>
								</TABLE> 
								<TABLE cellspacing="1" cellpadding="1"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" width="40%"><b><h:outputText
												value="Question"></h:outputText></b></td>
											<td align="left" width="20%"><b><h:outputText 
												value="Answer"></h:outputText></b></td>
											<td align="left" width="40%"><b><h:outputText
												value="Reference"></h:outputText></b></td>
										</TR>
									</TBODY>
								</TABLE>
								</div>
								</td>								
							</tr>

							<TR>
								<TD width="100%">
								<h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									columnClasses="gridColumn40,gridColumn20,gridColumn40"
									value="#{productBenefitAdministrationBackingBean.questionareList}"
									border="0" width="100%">
									<h:column>
										<h:outputText id="questionDesc"
											value="#{singleValue.questionName}"></h:outputText>
									
									</h:column>
									<h:column>
										<h:outputText id="answerDesc"
											value="#{singleValue.selectedAnswerDesc}"></h:outputText>
										
									</h:column>
									<h:column>
										<h:outputText id="referenceDesc"
											value="#{singleValue.referenceDesc}"></h:outputText>
										
									</h:column>
								</h:dataTable>

								</TD>
							</TR>

						</TABLE>
						<TABLE border="0" cellspacing="1" cellpadding="0" width="98%"
							class="outputText">
							<TBODY>
								<TR>
									<td valign="middle">
									<div id="LabelTierHeaderDiv"
										style="background-color:#cccccc;z-index:1;height:20px;width:100%">									
									<div id="LabelTierHeaderTitle2"><B> <h:outputText value="Associated Tiered Questionnaire"></h:outputText> </B></div>
									</div>
									</td>
								</TR>
								<TR>
									<td>
									<div id="LabelTierColHeaderDiv">
									<TABLE cellspacing="1" cellpadding="3"  
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="40%"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left" width="20%"><h:outputText value="Answer"></h:outputText></TD>
												<TD align="left" width="40%"><h:outputText value="Reference"></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</div>
									</TD>
								</TR>								
								<TR>
									<td valign="middle">									
									<div id="displayPanelContent12"
										style="position:relative;width:100%">
									<h:panelGrid id="panelTierTable" width="100%"
										binding="#{productBenefitAdministrationBackingBean.tierQuestionarePanel}">
									</h:panelGrid></div>
									</td>
							  	</TR>
							</TBODY>
						</TABLE>
						</fieldset></div>
						<!--	End of Page data	-->
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields --> <h:inputHidden id="hidden1"
					value="value1"></h:inputHidden> 
				<h:inputHidden id="editModeId"
								value="#{productBenefitAdministrationBackingBean.editMode}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink> <!-- End of hidden fields  --> </h:form></td>
			</tr>

		</table>
	</BODY>
</f:view>
<script>
	// Space for page realated scripts
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('productBenefitAdministrationPrintForm:searchResultTable');		
		if(hiddenIdObj.rows.length == 0){
			document.getElementById(divId).style.visibility = 'hidden';
			document.getElementById('emptymsg').style.visibility = 'visible';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('emptymsg').style.visibility = 'hidden';
	 		setColumnWidth('productBenefitAdministrationPrintForm:searchResultTable','40%:20%:40%');
		}
	}	

if(getObj('productBenefitAdministrationPrintForm:panelTierTable') == null || getObj('productBenefitAdministrationPrintForm:panelTierTable').rows.length == 0) {
			getObj('LabelTierHeaderDiv').style.visibility = 'hidden';	
			getObj('LabelTierColHeaderDiv').style.visibility = 'hidden';
	}	
</script>
<script>
	window.print();	
</script>
</HTML>
