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
<META http-equiv="PRAGMA" content="NO-CACHE">
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">

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

<TITLE>Benefit Administration</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:form styleClass="form" id="benefitAdmnForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
 <TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{productStructureGeneralInfoBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;
                        </TD>
                  </TR>
					<h:inputHidden value="#{productStructureBenefitAdministrationBackingBean.loadQuestionForView}" />
					<TR>
						<TD colspan="2" valign="top" class="ContentArea">
						<fieldset style="width:99%">
						<div id="panel2Header" style="position:relative;width:100%;background-color:#cccccc;z-index:1;overflow:auto;height: 15px "><strong>
                        &nbsp;<h:outputText value="Selected Questions"></h:outputText></strong>
                        </div>
						<BR />

						<h:outputText value="No Benefit Administration Available."
							rendered="#{productStructureBenefitAdministrationBackingBean.viewQuestionnaireList == null}"
							styleClass="dataTableColumnHeader" />
						<table class="smallfont" id="resultsTable" width="100%"
							cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td>
								<div id="resultHeaderDiv">
								<TABLE id="headerTable" width="100%" border="0" cellpadding="1"
									cellspacing="1">
									<tr class="dataTableOddRow">
										<td width="25"><strong><h:outputText value="Question" /></strong></td>
										<td width="25"><strong><h:outputText value="Answer" /></strong></td>
										<td width="25"><strong><h:outputText value="Reference" /></strong></td>
									</tr>
								</TABLE>
								</div>
								</td>
							</tr>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDiv" style="overflow: auto;"><h:dataTable
									cellspacing="1" id="bComponentDataTable"
									rendered="#{productStructureBenefitAdministrationBackingBean.viewQuestionnaireList != null}"
									value="#{productStructureBenefitAdministrationBackingBean.viewQuestionnaireList}"
									rowClasses="dataTableOddRow" var="singleValue" cellpadding="3"
									width="100%">
									<h:column>
											<h:outputText id="questionName"
												value="#{singleValue.questionName}"></h:outputText>
											<h:inputHidden id="hidden_questionName"
												value="#{singleValue.questionName}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:outputText>
											<h:inputHidden id="hidden_ansDesc"
												value="#{singleValue.selectedAnswerDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="refDesc"
												value="#{singleValue.referenceDesc}"></h:outputText>
											<h:inputHidden id="hidden_level" value="#{singleValue.level}"></h:inputHidden>
										</h:column>
								</h:dataTable></div>

								</td>
							</tr>
							<tr>
								<TD></TD>
							</tr>
							<tr>
								<TD></TD>
							</tr>

						</table>
						<!--	End of Page data	--></fieldset>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->
				<script language="JavaScript">
		if(document.getElementById('benefitAdmnForm:bComponentDataTable') != null) {

		setColumnWidth('headerTable','50%:20%:30%');		
		setColumnWidth('benefitAdmnForm:bComponentDataTable','50%:20%:30%');
	}else {
		document.getElementById('resultHeaderDiv').style.visibility = 'hidden';
		document.getElementById('searchResultdataTableDiv').style.height = '1px';
		document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
	}
	
</script>
				<script>window.print();</script>
			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>

</HTML>
