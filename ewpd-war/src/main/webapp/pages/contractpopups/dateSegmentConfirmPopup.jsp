<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="/WEB-INF/singleRow.tld" prefix="wpd"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"
	title="Style">
<base target=_self>
<TITLE>Create Date Segment</TITLE>

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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/tree_items.js"></script>
<script language="JavaScript" src="../../js/tree_tpl.js"></script>
<script language="JavaScript" src="../../js/tree.js"></script>
<script language="JavaScript">
var cal1 = new CalendarPopup();
cal1.showYearNavigation();
</script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<jsp:include page="../navigation/popupHeader.jsp"></jsp:include>
		<h:form id="dateSegmentForm">
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
				<TBODY>
					<TR>
						<td height="16" valign="middle" bgcolor="#7670B3"
							class="breadcrumb">Search &gt;&gt; Contract &gt;&gt; Date Segment
						</td>
						<td class="breadcrumb" width="55">&nbsp;</td>
					</TR>
				</TBODY>
			</TABLE>
			<table width="96%">
				<tr>
					<td>&nbsp;</td>
					<td align="left"><w:message></w:message></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo"
				style="width: 68%;margin-left: 10"><STRONG> <h:outputText
				value="Create Date Segment for Contract : ">
			</h:outputText> </STRONG><h:outputText
				value="#{dateSegmentBackingBean.selectedContractId}"
				styleClass="dataTableHeader"></h:outputText></div>

			<BR>
			<div id="panel2Content" class="tabContentBox"
				style="position:relative;font-size:10px;width:100%;">
			<DIV id="popupDataTableDiv1" align="left">
			<TABLE cellpadding="0" cellspacing="0" border="0">

				<tr>
					<td width="658">
					<div id="resultHeaderDiv">
					<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
						id="resultHeaderTable" border="0" width="100%">
						<TBODY>
							<TR class="dataTableColumnHeader">
								<td align="left"><h:outputText value="Contract ID"></h:outputText>
								</td>
								<td align="left"><h:outputText value="Start Date"></h:outputText>
								</td>
								<!--  
								<td align="left"><h:outputText value="Revision Date"></h:outputText>
								</td>
								-->

								<td align="left"><h:outputText value="End Date"></h:outputText>
								</td>
								<td align="left"><h:outputText value="Contract Status"></h:outputText>
								</td>
								<td align="left" width="84"><h:outputText value="Type"></h:outputText>
								</td>
							</TR>
						</TBODY>
					</TABLE>
					</div>
					</td>
				</tr>

				<TR>
					<TD>
					<DIV id="popupDataTableDiv"
						style="height:120px;overflow:auto;width:100%;"><h:dataTable
						headerClass="tableHeader"
						rowClasses="dataTableEvenRow,dataTableOddRow" cellspacing="1"
						id="benefitStructureDataTable"
						value="#{dateSegmentBackingBean.newDSList}" var="eachRow"
						cellpadding="3" bgcolor="#cccccc"
						 width="100%">
						<h:column>
							<h:outputText value="#{eachRow.contractId}"
								style="#{eachRow.color}"></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.startDate}"
								style="#{eachRow.color}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
						</h:column>
						<%-- 
						<h:column>
							<h:outputText value="#{eachRow.revisionDateStr}"
								style="#{eachRow.color}"></h:outputText>
						</h:column>
						--%>
						<h:column>
							<h:outputText value="#{eachRow.endDate}"
								style="#{eachRow.color}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
						</h:column>

						<h:column>
							<h:outputText value="#{eachRow.status}" style="#{eachRow.color}"></h:outputText>
						</h:column>
						<h:column>
							<h:outputText value="#{eachRow.description}" style="#{eachRow.color}"></h:outputText>
						</h:column>
					</h:dataTable></DIV>
					</TD>
				</TR>
			</TABLE>
			</DIV>
			<TABLE align="center" cellspacing="0" width="96%">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<script type="text/javascript">
					function RSCustomInterface(tbElementName){
						this.tbName = tbElementName;
						this.getText = getText;
						this.setText = setText;
				
					function getText(){
						if(!document.getElementById(this.tbName)) {
							alert('Error: element '+this.tbName+' does not exist, check TextComponentName.');
							return '';
						} else return document.getElementById(this.tbName).value;
					}
					function setText(text){
						if(document.getElementById(this.tbName)) document.getElementById(this.tbName).value = text;
					}
				}
				</script>
				<tr>
					<TD width="9"></TD>
					<TD height="63" width="18%">
						<h:outputText value="Copy Legacy Notes :" rendered ="#{dateSegmentBackingBean.legacyNoteCopyOptionRequired}">
							</h:outputText></TD>
					<td height="63" width="639">
						<h:selectBooleanCheckbox id = "legacyNoteCopy" value ="#{dateSegmentBackingBean.copyLegacyNotes}" onclick="setCopyNoteStatus();"
							rendered ="#{dateSegmentBackingBean.legacyNoteCopyOptionRequired}"></h:selectBooleanCheckbox></td>
				</tr>
				<tr>
					<TD width="9"></TD>
					<TD height="63" width="18%"><h:outputText
						value="Reason for creation *:"
						styleClass="#{dateSegmentBackingBean.requiredComments ? 
						'mandatoryError': 'mandatoryNormal'}"></h:outputText></TD>
					<td height="63" width="639"><h:inputTextarea id="comments"
						styleClass="formTxtAreaField3"
						value="#{dateSegmentBackingBean.comments}" onkeypress="return validateText();"></h:inputTextarea></td>
				</tr>
				<RapidSpellWeb:rapidSpellWebLauncher
					id="rapidSpellWebLauncher1"
					textComponentName="dateSegmentForm:comments"
					rapidSpellWebPage="../spellcheck/CustomSpellCheckerPopUp.jsp?title=Spell Checking - Reason for Creation"
					modalHelperPage="../spellcheck/RapidSpellModalHelper.html"
					showNoErrorsMessage="False" showFinishedMessage="False"
					includeUserDictionaryInSuggestions="True"
					allowAnyCase="True" allowMixedCase="True"
					finishedListener="spellFin" 
					windowWidth="570" windowHeight="300"
					modal="False" showButton="False"
					windowX="-1" warnDuplicates="False"
					textComponentInterface="Custom">
				</RapidSpellWeb:rapidSpellWebLauncher>
			</TABLE>
			<h:inputHidden id="contractIdForDS"
								value="#{dateSegmentBackingBean.selectedContractSysId}" />
			<h:inputHidden id="dateEntered"
								value="#{dateSegmentBackingBean.dateEntered}" />
			<h:inputHidden id="dsType"
								value="#{dateSegmentBackingBean.dsType}" />
			<h:inputHidden id="copyLegacyNotesid"
								value="#{dateSegmentBackingBean.copyLegacyNotes}" />

			<TABLE width="100%">
				<TR>
					<TD colspan="4">&nbsp;</TD>
				</TR>
				<TR>
					<TD width="36%"></TD>
					<TD valign="top" width="91"><h:commandButton styleClass="wpdbutton"
						id="confirm" value="Confirm" onclick="return runSpellCheck();" ></h:commandButton>
					</TD>
					<TD width="490"><h:commandButton styleClass="wpdbutton" id="cancel"
						value="Cancel" onclick="" action="#{dateSegmentBackingBean.cancelAction}"></h:commandButton>
					<BR class="brClass">
					</TD>
				</TR>
			</TABLE>
					<h:commandLink id="confirmLink"
						style="hidden" action="#{dateSegmentBackingBean.createDateSegmentOne}">
					</h:commandLink>
	<!--   WAS 6.0 Migration Changes - added </div> tag -->					
			</div>
		</h:form>
	</hx:scriptCollector>
	</BODY>
</f:view>

<script>

	var textValue = document.getElementById('dateSegmentForm:comments').value;

	if(textValue != null && textValue.length > 0){
		var vValue = '1';
		//window.returnValue = vValue ;
		var oMyObject = window.dialogArguments;
		var date;
		var comment;
		if(oMyObject !== undefined){
		     date = oMyObject.dateEntered;
		     comment = oMyObject.comments;
		}else{
		  oMyObject=new Object();
		}

		oMyObject.comments = textValue;
		oMyObject.dateEntered =document.getElementById('dateSegmentForm:dateEntered').value;
		oMyObject.type = document.getElementById('dateSegmentForm:dsType').value;
		oMyObject.copylegacyNote = document.getElementById('dateSegmentForm:copyLegacyNotesid').value;
		oMyObject.vValue = 1;
		window.returnValue = oMyObject ;
<%
if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
%>
window.close();
<%
}
else {
%>
parent.document.getElementsByTagName('dialog')[0].close();
<%
}
%>
				
	}

if(document.getElementById('dateSegmentForm:benefitStructureDataTable') != null) {
			setColumnWidth('dateSegmentForm:benefitStructureDataTable','15%:15%:15%:30%:15%');
			setColumnWidth('resultHeaderTable','15%:15%:15%:30%:15%');
			//showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	


function validateText(){

	if(document.getElementById('dateSegmentForm:comments').value.length >= 4000){
		return false;
	}else{
		return true;
	}
}

function runSpellCheck(){
	var rswlCntrls = ["rapidSpellWebLauncher1"];
	var i=0;
	for(var i=0; i<rswlCntrls.length; i++){
		eval("popUpCheckSpelling"+rswlCntrls[i]+"('rsTCInt"+rswlCntrls[i]+"')");
	}
	return false;
}
function spellFin(cancelled){
		
		document.getElementById('dateSegmentForm:confirmLink').click();	
}
function setCopyNoteStatus(){
	if(document.getElementById('dateSegmentForm:legacyNoteCopy').checked){
		document.getElementById('dateSegmentForm:copyLegacyNotesid').value = 'true';
	}else{
		document.getElementById('dateSegmentForm:copyLegacyNotesid').value = 'false';
	}
}
</script>
</HTML>
