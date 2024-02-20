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
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
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

<TITLE>Print Admin Questions</TITLE>
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
	<BODY><hx:scriptCollector id="scriptCollector1">
	<h:inputHidden id="loadPurpose" value="#{AddQuestionBackingBean.loadPurposeForprint}" />
	<h:form styleClass="form" id="addQuestionForm">
		<table width=100%>
			<TR>
                        <TD>
                              &nbsp;
                        </TD>

                  </TR>
                  <TR>
                        <TD>
                              <FIELDSET style="margin-left:2px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:99.5%">
                                    <h:outputText id="breadcrumb" 
                                          value="#{standardBenefitBackingBean.printBreadCrumbText}">
                                    </h:outputText>
                              </FIELDSET>
                        </TD>
                  </TR>
                  <TR>
                        <TD>
                              &nbsp;
                        </TD>
                  </TR>	
			</table>
<div id="noassociatedQuestions">
<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">
<tr><TD>
<FIELDSET style="width:70%">
		<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">

			<TR bgcolor="#cccccc" height="20">
				<TD colspan="4"><SPAN id="tableTitle"><STRONG>Associated Questions</STRONG></SPAN></TD>
			</TR>
			<TR  class="dataTableColumnHeader" height="20">
				<TD colspan="4"><SPAN id="tableTitle"><STRONG> No Associated Questions</STRONG></SPAN></TD>
			</TR>
		</TABLE>
</FIELDSET>
</TD></tr>
</TABLE>
</div>
<div id="associatedQuestions">
		
<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">
<tr>
<TD>
<FIELDSET style="width:70%">
		<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">

			<TR bgcolor="#cccccc" height="20">
				<TD colspan="4"><SPAN id="tableTitle"><STRONG>Associated Questions</STRONG></SPAN></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3" id="resultHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD><h:outputText value="Sequence"></h:outputText></TD>

					<TD><h:outputText value="Question"></h:outputText>
					</TD>
					<TD><h:outputText value="Answer"></h:outputText></TD>
					<TD><h:outputText value="Reference"></h:outputText></TD>

				</TR>
			</TBODY>
		</TABLE>



		<h:dataTable styleClass="outputText" headerClass="dataTableHeader" id="searchResultTable" var="singleValue" cellpadding="3" width="100%"
 cellspacing="1" rendered="#{AddQuestionBackingBean.locateResultList != null}" value="#{AddQuestionBackingBean.locateResultList}">
			<h:column>
				<h:outputText id="desc" value="#{singleValue.seqNumber}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="effDate" value="#{singleValue.question}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="expDate" value="#{singleValue.answer}"></h:outputText>
			</h:column>

				<h:column>
					<h:outputText id="reference" value="#{singleValue.referenceDesc}"></h:outputText>
				</h:column>
			</h:dataTable></FIELDSET></TD></tr></TABLE></div>
	</h:form>
	</hx:scriptCollector></BODY>
	<HEAD>
	<META HTTP-EQUIV="cache-control" CONTENT="no-cache">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	</HEAD>
</f:view>
<script language="javascript">
	if(document.getElementById('addQuestionForm:searchResultTable')!= null){
			document.getElementById('associatedQuestions').style.visibility = 'visible';
			document.getElementById('noassociatedQuestions').style.visibility = 'hidden';
			document.getElementById('noassociatedQuestions').innerHTML = '';
			setColumnWidth('resultHeaderTable','20%:20%:20%:60%');
			setColumnWidth('addQuestionForm:searchResultTable','20%:20%:20%:60%');
	}else{
			document.getElementById('noassociatedQuestions').style.visibility = 'visible';
			document.getElementById('associatedQuestions').style.visibility = 'hidden';
			document.getElementById('associatedQuestions').innerHTML = '';
	}

</script>
</HTML>
<script>window.print();</script>
