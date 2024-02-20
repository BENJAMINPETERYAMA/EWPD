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

<TITLE>Questions Search Print</TITLE>
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript" src="../../js/menu.js"></script>
<script language="JavaScript" src="../../js/menu_items.js"></script>
<script language="JavaScript" src="../../js/menu_tpl.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>

</HEAD>

<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<h:inputHidden id="dummy"
				value="#{QuestionSearchBackingBean.questionSearchPrint}">
			</h:inputHidden>
			
		</tr>
		<tr>
			<td><h:form id="QuestionSearchPrintForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<tr><td>&nbsp;</td></tr>
						<tr>
						<td><FIELDSET style="margin-left:15px;margin-right:-30px;padding-bottom:1px;padding-top:1px;width:95.5%">
		                        <h:outputText id="breadcrumb" 
		                              value="#{QuestionSearchBackingBean.breadCrumbPrint}">
		                        </h:outputText>
		                   </FIELDSET>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
						<TR>
							<td valign="top" class="ContentArea">
													

							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Question"></h:outputText></TD>
												<TD align="left"><h:outputText value="Data Type"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Version"></h:outputText>
												</TD>
												<TD align="left"><h:outputText value="Status"></h:outputText>
												</TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="Question" cellpadding="3"
										width="100%" cellspacing="1" 
										rendered="#{QuestionSearchBackingBean.questionSearchResultList != null}"
										value="#{QuestionSearchBackingBean.questionSearchResultList}">
										<h:column>
											
											<h:outputText id="questionDescription"
												value="#{Question.displayQuestion}"></h:outputText>
											
										</h:column>
										<h:column>
											<h:outputText id="DataType" value="#{Question.dataTypeName}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="verisonNumber" value="#{Question.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="statusType" value="#{Question.status}"></h:outputText>
										</h:column>

										
									</h:dataTable></DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							
							</TD>
						</TR>
				</TABLE></h:form></td>
		</tr>
		
	</table>
	</BODY>

</f:view>
<script language="javascript">
	
		if(document.getElementById('QuestionSearchPrintForm:searchResultTable') != null) {
			//tigra_tables('QuestionSearchPrintForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
			setColumnWidth('QuestionSearchPrintForm:searchResultTable','40%:15%:15%:20%');
			setColumnWidth('resultHeaderTable','40%:15%:15%:20%');	
			
		}
		window.print();
</script>
</HTML>
