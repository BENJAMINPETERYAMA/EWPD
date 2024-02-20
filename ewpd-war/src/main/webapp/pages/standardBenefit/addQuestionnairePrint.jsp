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

<TITLE>Print Admin Questionnaire</TITLE>
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
	<h:inputHidden id="loadPurpose" binding="#{addQuestionaireBackingBean.loadPrint}" />
	<h:form styleClass="form" id="addQuestioniareForm">
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

		
<TABLE width="100%" cellpadding="1" border="0" id="tabheader" class="smallfont">
<tr>
<TD>
<FIELDSET style="width:100%">
		<div id="messageTextForNoQuestionnaireDiv"><br />
							<br />
							<STRONG>&nbsp;<h:outputText value="No Questionnaire available." /></STRONG>
							</div>
							<div id="quesitonTableDiv">
							<TABLE width="100%" cellpadding="1" border="0" id="tabheader"
								class="smallfont">
								<tr>
								<td>
								<TABLE width="100%" cellspacing="1" bgcolor="#cccccc"
									cellpadding="3" border="0">
									<TR bgcolor="#cccccc" height="18">
										<TD><strong>Associated Questionnaire</strong></TD>
									</TR>
								</table></td></tr>
								<TR>
									<TD>
									<DIV id="displayPanelContent12"
										style="position:relative;overflow:auto;width:100%"><h:panelGrid
										id="quesHeadTable"
										binding="#{addQuestionaireBackingBean.headerPanelForPrint}"
										rowClasses="dataTableOddRow" cellpadding="1" cellspacing="1">
									</h:panelGrid></DIV>
									<DIV id="displayPanelContent12"
										style="position:relative;width:100%">
									<h:panelGrid id="quesTable"
										binding="#{addQuestionaireBackingBean.panelForPrint}"
										rowClasses="dataTableOddRow" cellpadding="1" cellspacing="1">
									</h:panelGrid>
									</TD>

								</TR>

							</TABLE>
							</div></TD></tr></TABLE>
	</h:form>
	</hx:scriptCollector></BODY>
	<HEAD>
	<META HTTP-EQUIV="cache-control" CONTENT="no-cache">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	</HEAD>
</f:view>
<script language="javascript">
		var tableObject = document.getElementById('addQuestioniareForm:quesTable');
		if(tableObject.rows.length > 0){
			var msgDivObj = document.getElementById('messageTextForNoQuestionnaireDiv');
			msgDivObj.style.visibility = "hidden";
			msgDivObj.style.height = "0px";
		}else{
			var divObj = document.getElementById('quesitonTableDiv');
			divObj.style.visibility = "hidden";
			divObj.style.height = "2px";
		}
		if(tableObject != null) {
			var tblWidth = document.getElementById('addQuestioniareForm:quesTable').offsetWidth;
			var rowlength = document.getElementById('addQuestioniareForm:quesTable').rows.length;
			if(rowlength>1){
				//document.getElementById('addQuestioniareForm:quesHeadTable').width = tblWidth + "px";
				syncTables('addQuestioniareForm:quesHeadTable','addQuestionForm:quesTable'); 	
				setColumnWidth('addQuestioniareForm:quesTable','50%:20%:30%');
				setColumnWidth('addQuestioniareForm:quesHeadTable','50%:20%:30%');
			}else{
				setColumnWidth('addQuestioniareForm:quesHeadTable','50%:20%:30%');	
				setColumnWidth('addQuestioniareForm:quesTable','50%:20%:30%');	
			}	
		}

		// To reset the style class of all the rows as "dataTableOddRow"
		for (var i=0; i<tableObject.rows.length; i++ ){
			var rowObject = tableObject.rows[i];
			rowObject.className = "dataTableOddRow";
		} 

		window.print();
</script>
</HTML>
