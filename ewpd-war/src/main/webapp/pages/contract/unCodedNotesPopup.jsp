<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/UnCodedNotesPopup.java" --%><%-- /jsf:pagecode --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<STYLE>
.gridColumn13{
	width: 13%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn16{
	width: 16%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn17{
	width: 17%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn19{
	width: 19%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn22{
	width: 22%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn15{
	width: 15%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn17{
	width: 17%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn5{
	width: 5%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
 .gridColumn3{
	width: 3%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
</STYLE>
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
<base target=_self>
<TITLE>Contract Notes Validation</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="formName">


		<table width='100%'>
			<tr>
				<td align="right"><a href="#"> <img src="../../images/print.gif"
					alt="Print" width="19" height="14" border="0"
					onclick="printSelection();return false;" /> </a></td>
			</tr>
		</table>
		<TABLE>
			<TBODY>
				<tr>
					<TD><w:message></w:message></TD>
				</tr>
			</TBODY>
		</TABLE>
		<table id="questionHeaderTable" width="98%" cellpadding="1"
			cellspacing="1" border="0" align="center">
			<tr>
				<td width="15%"><h:outputText value="Contract Name " /></td>
				<td width="27" align="right"><h:outputText value=":" /></td>
				<td align="left" width="616"><h:outputText
					value="#{contractBasicInfoBackingBean.contractId}"
					id="contractName" /></td>
				<td width="18%"><SPAN id="timeID">time</SPAN></td>
			</tr>
			<tr>
				<td width="15%"><h:outputText value="Version" /></td>
				<td width="27" align="right"><h:outputText value=":" /></td>
				<td width="616"><h:outputText
					value="#{contractBasicInfoBackingBean.version}" id="versionNumber" />
				</td>
				<td width="177"></td>
			</tr>
			
		</table>

		<br>
	
	


		<DIV id="resultHeaderDiv" >
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Notes in uncoded Benefit Lines"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left"><h:outputText value="Line "></h:outputText></TD>
					<TD align="left"><h:outputText value="PVA "></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id "></h:outputText></TD>
					<TD align="left"><h:outputText value=""></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>



		<DIV id="searchResultdataTableDiv"
			style="position:relative;z-index:1;font-size:10px;">
		<!-- Search Result Data Table --> <h:dataTable styleClass="outputText"
			headerClass="dataTableHeader" id="searchResultTable"
			var="singleValue" cellpadding="3" width="100%" cellspacing="1"
			bgcolor="#cccccc"
			rendered="#{contractBasicInfoBackingBean.lineNotesList != null}"
			value="#{contractBasicInfoBackingBean.lineNotesList}"
			rowClasses="dataTableEvenRow" border="0">

			<h:column>
				<h:outputText id="dateSegmentId" value="#{singleValue.dateRange}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="productName3" value="#{singleValue.productName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitComponentName"
					value="#{singleValue.benefitComponentName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitSysName"
					value="#{singleValue.benefitSysName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitLevelDesc"
					value="#{singleValue.benefitLevelDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="pva"
					value="#{singleValue.benefitLinePva}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="noteId" value="#{singleValue.noteID}"></h:outputText>
				<h:inputHidden id="noteIdHid"
					value="#{singleValue.noteID}"/>
				<h:inputHidden id="noteVersionHid"
					value="#{singleValue.noteVersion}"/>
				<h:inputHidden id="noteNameHid"
					value="#{singleValue.noteName}"/>
			</h:column>	
			<h:column>
				<h:commandButton alt="View" id="view1" image="../../images/view.gif"
							onclick="viewActionLines();return false;"></h:commandButton>
			</h:column>
		</h:dataTable></DIV>
		
		<br>
		<br>
	
		<DIV id="resultHeaderQuestDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerQuestTable" border="0">
			<TR>
				<TD width="1251"><strong><h:outputText
					value="Notes in unanswered Questions"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultQuestHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left" width="161"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left" width="293"><h:outputText
						value="Benefit Component "></h:outputText></TD>
					<TD align="left" width="221"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left" width="173"><h:outputText value="Admin Option "></h:outputText></TD>
					<TD align="left" width="194"><h:outputText value="Question"></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id "></h:outputText></TD>
					<TD align="left"><h:outputText value=""></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>


		<DIV id="searchResultdataTableQuestDiv"
			style="position:relative;z-index:1;font-size:10px;">
		<!-- Search Result Data Table --> <h:dataTable styleClass="outputText"
			headerClass="dataTableHeader" id="searchResultQuestTable"
			var="singleValue" cellpadding="3" width="100%" cellspacing="1"
			bgcolor="#cccccc"
			rendered="#{contractBasicInfoBackingBean.questionNotesList != null}"
			value="#{contractBasicInfoBackingBean.questionNotesList}"
			rowClasses="dataTableEvenRow" border="0">

			<h:column>
				<h:outputText id="dateSegmentIdQuest"
					value="#{singleValue.dateRange}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="productName1" value="#{singleValue.productName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitComponentNameQuest"
					value="#{singleValue.benefitComponentName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="benefitSysNameQuest"
					value="#{singleValue.benefitSysName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="adminOptionDescQuest"
					value="#{singleValue.adminDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="referenceDescQuest"
					value="#{singleValue.questionDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="questionDescQuest" value="#{singleValue.noteID}"></h:outputText>
				<h:inputHidden id="noteIdHid1"
					value="#{singleValue.noteID}"/>
				<h:inputHidden id="noteVersionHid1"
					value="#{singleValue.noteVersion}"/>
				<h:inputHidden id="noteNameHid1"
					value="#{singleValue.noteName}"/>
			</h:column>
			<h:column>
				<h:commandButton alt="View" id="view2" image="../../images/view.gif"
						onclick="viewActionAdmin();return false;"></h:commandButton>
			</h:column>
		</h:dataTable></DIV>

		<br>
		<br>
	
		<DIV id="resultHeaderProdQuestDiv">


		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerProdQuestTable" border="0">
			<TR>
				<TD width="1251"><strong><h:outputText
					value="Notes in unanswered Questions attached to Product"></h:outputText></strong></TD>
			</TR>
		</TABLE>
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultProdQuestHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left" width="161"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left" width="173"><h:outputText value="Admin Option "></h:outputText></TD>
					<TD align="left" width="194"><h:outputText value="Question"></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id "></h:outputText></TD>
					<TD align="left"><h:outputText value=""></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>

		</DIV>

		<DIV id="searchResultdataTableProdQuestDiv"
			style="position:relative;z-index:1;font-size:10px;">
		<!-- Search Result Data Table --> <h:dataTable styleClass="outputText"
			headerClass="dataTableHeader" id="searchResultProdQuestTable"
			var="singleValue" cellpadding="3" width="100%" cellspacing="1"
			bgcolor="#cccccc"
			rendered="#{contractBasicInfoBackingBean.prdQuestNotesList != null}"
			value="#{contractBasicInfoBackingBean.prdQuestNotesList}"
			rowClasses="dataTableEvenRow" border="0">

			<h:column>
				<h:outputText id="dateSegmentIdProdQuest"
					value="#{singleValue.dateRange}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="productName2" value="#{singleValue.productName}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="adminOptionDescProdQuest"
					value="#{singleValue.adminDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="referenceDescProdQuest"
					value="#{singleValue.questionDesc}"></h:outputText>
			</h:column>
			<h:column>
				<h:outputText id="questionDescProdQuest"
					value="#{singleValue.noteID}"></h:outputText>
				<h:inputHidden id="noteIdHid2"
					value="#{singleValue.noteID}"/>
				<h:inputHidden id="noteVersionHid2"
					value="#{singleValue.noteVersion}"/>
				<h:inputHidden id="noteNameHid2"
					value="#{singleValue.noteName}"/>
			</h:column>
			<h:column>
				<h:commandButton alt="View" id="view3" image="../../images/view.gif"
				onclick="viewActionPrdQuest();return false;"></h:commandButton>
			</h:column>
		</h:dataTable></DIV>

		<br/>
		<DIV id="resultLineNoteDiv" style="position:relative;z-index:1;font-size:10px;overflow:auto;">
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="headerQuestTable" border="0">
				<TR>
					<TD width="1251"><strong><h:outputText
						value="Notes in uncoded Tiered Benefit Lines"></h:outputText></strong></TD>
				</TR>
			</TABLE>
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="tierLineHeaderTable" border="0" width="100%">
				<TBODY>
					<TR class="dataTableColumnHeader">
						<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
						<TD align="left"><h:outputText value="Product "></h:outputText></TD>
						<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
						<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
						<TD align="left"><h:outputText value="Line "></h:outputText></TD>
						<TD align="left"><h:outputText value="PVA "></h:outputText></TD>
						<TD align="left"><h:outputText value="Note Id "></h:outputText></TD>
						<TD align="left"><h:outputText value=""></h:outputText></TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
		<DIV id="panelContent"
			style="position:relative;z-index:1;font-size:10px;">
			<h:panelGrid id="tierPanelTable"
						binding="#{contractBasicInfoBackingBean.tierNotePanel}">
			</h:panelGrid>
		</DIV>
		<br>
		<br>
		<DIV id="resultQuestNoteDiv" style="position:relative;z-index:1;font-size:10px;overflow:auto;">
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="headerQuestTable" border="0">
				<TR>
					<TD width="1251"><strong><h:outputText
						value="Notes in Tiered Unanswered Questions"></h:outputText></strong></TD>
				</TR>
			</TABLE>
			<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
				id="tierQuestHeaderTable" border="0" width="100%">
				<TBODY>
					<TR class="dataTableColumnHeader">
						<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
						<TD align="left"><h:outputText value="Product "></h:outputText></TD>
						<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
						<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
						<TD align="left"><h:outputText value="Admin Option "></h:outputText></TD>
						<TD align="left"><h:outputText value="Question "></h:outputText></TD>
						<TD align="left"><h:outputText value="Note Id "></h:outputText></TD>
						<TD align="left"></TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
		<DIV id="panelContent1"
			style="position:relative;z-index:1;font-size:10px;">
			<h:panelGrid id="tierQuestNotePanel"
						binding="#{contractBasicInfoBackingBean.tierQuestNotePanel}">
			</h:panelGrid>
		</DIV>

		<DIV id="dummyDiv" style="visibility:hidden"></DIV>
	<h:inputHidden id="dummyHidden" ></h:inputHidden>
	<h:inputHidden id ="timezone" value="#{requestScope.timezone}"></h:inputHidden>

		<!-- Space for hidden fields -->
	</h:form>
	</BODY>
</f:view>
<script>

var panelObject1 = document.getElementById('formName:tierPanelTable');
if(panelObject1.rows.length==0){
	document.getElementById('resultLineNoteDiv').style.display = 'none';
	document.getElementById('panelContent').style.display = 'none';
}else{
	setColumnWidth('tierLineHeaderTable','13%:16%:17%:19%:22%:5%:5%:3%');
	syncTables('tierLineHeaderTable','formName:tierPanelTable');
}	

var panelObject = document.getElementById('formName:tierQuestNotePanel');
if(panelObject.rows.length==0){
	document.getElementById('resultQuestNoteDiv').style.display = 'none';
	document.getElementById('panelContent1').style.display = 'none';
}else{
	setColumnWidth('tierQuestHeaderTable','13%:16%:17%:15%:17%:15%:5%:3%');
	syncTables('tierQuestHeaderTable','formName:tierQuestNotePanel');
}

var tableObject = document.getElementById('formName:searchResultTable');
var tablesize = 0;
if(null != tableObject){
tablesize = tableObject.rows.length;
}
for(var i=0;i<tablesize;i++)
{
	var rowobj = tableObject.rows[i];
	var cellobj = tableObject.rows[i].cells[0];
	var text = cellobj.children[0].innerHTML;

if(text ==''|| text == null){
	rowobj.className = 'dataTableOddRow';
}
}
if(tablesize == 0)
{
	document.getElementById('resultHeaderDiv').style.display = 'none';
	document.getElementById('searchResultdataTableDiv').style.display = 'none';
}
else{
	var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
	setColumnWidth('formName:searchResultTable','13%:16%:17%:19%:22%:5%:5%:3%');
	setColumnWidth('resultHeaderTable','13%:16%:17%:19%:22%:5%:5%:3%');
	syncTables('resultHeaderTable','formName:searchResultTable');
	syncTables('headerTable','formName:searchResultTable');
}

var tableObjectQuest = document.getElementById('formName:searchResultQuestTable');
var tablesizeQuest=0;
if(null != tableObjectQuest){
tablesizeQuest = tableObjectQuest.rows.length;
}
for(var i=0;i<tablesizeQuest;i++)
{
	var rowobjQuest = tableObjectQuest.rows[i];
	var cellobjQuest = tableObjectQuest.rows[i].cells[0];
	var textQuest = cellobjQuest.children[0].innerHTML;
if(textQuest ==''|| textQuest == null){
rowobjQuest.className = 'dataTableOddRow';
}
}
if(tablesizeQuest == 0)
{
document.getElementById('resultHeaderQuestDiv').style.display = 'none';
document.getElementById('searchResultdataTableQuestDiv').style.display = 'none';
}
else{
	var relTblWidth = document.getElementById('resultQuestHeaderTable').offsetWidth;
	setColumnWidth('formName:searchResultQuestTable','13%:15%:15%:15%:15%:19%:5%:3%');
	setColumnWidth('resultQuestHeaderTable','13%:15%:15%:15%:15%:19%:5%:3%');
	syncTables('resultQuestHeaderTable','formName:searchResultQuestTable');
	syncTables('headerQuestTable','formName:searchResultQuestTable');
}

var tableObjectQuest = document.getElementById('formName:searchResultProdQuestTable');
var tablesizeQuest=0;
if(null != tableObjectQuest){
tablesizeQuest = tableObjectQuest.rows.length;
}
for(var i=0;i<tablesizeQuest;i++)
{
	var rowobjQuest = tableObjectQuest.rows[i];
	var cellobjQuest = tableObjectQuest.rows[i].cells[0];
	var textQuest = cellobjQuest.children[0].innerHTML;
if(textQuest ==''|| textQuest == null){
rowobjQuest.className = 'dataTableOddRow';
}
}
if(tablesizeQuest == 0)
{
document.getElementById('resultHeaderProdQuestDiv').style.display = 'none';
document.getElementById('searchResultdataTableProdQuestDiv').style.display = 'none';
}
else{
	var relTblWidth = document.getElementById('resultProdQuestHeaderTable').offsetWidth;
	setColumnWidth('formName:searchResultProdQuestTable','13%:24%:23%:27%:10%:3%');
	setColumnWidth('resultProdQuestHeaderTable','13%:24%:23%:27%:10%:3%');
	syncTables('resultProdQuestHeaderTable','formName:searchResultProdQuestTable');
	syncTables('headerProdQuestTable','formName:searchResultProdQuestTable');
}
var Stamp = new Date();
var year = Stamp.getYear();
var month = (Stamp.getMonth() + 1);
if(month < 10){
month = "0"+ month;
}
var day = Stamp.getDate();
if(day < 10){
day = "0"+day;
}
var Hours;
var Mins;
var Time;
Hours = Stamp.getHours();
if(Hours <10){
Hours = "0"+ Hours;
}
if (Hours >= 12) {
Time = " P.M.";
}
else {
Time = " A.M.";
}

Mins = Stamp.getMinutes();
if (Mins < 10) {
Mins = "0" + Mins;
}	
seconds =Stamp.getSeconds()
if (seconds < 10) {
seconds = "0" + seconds;
}
//var totaltime= ""+day+'/'+month+'/'+year+"        "+Hours+':'+Mins+Time;
var totaltime=""+month+'/'+day+'/'+year+"        "+Hours+':'+Mins+':'+seconds;
document.getElementById('timeID').innerHTML=totaltime;
function viewActionLines(){			
	var e = window.event;
	var button_id = e.srcElement.id;			
	var var1 = button_id.split(':');			
	var rowcount = var1[2];			
	var noteId = "formName:searchResultTable:"+rowcount+":noteIdHid";
	var noteName = "formName:searchResultTable:"+rowcount+":noteNameHid";
	var versionValue = "formName:searchResultTable:"+rowcount+":noteVersionHid";		
	var noteIdValue = document.getElementById(noteId).value;
	var version = document.getElementById(versionValue).value;
	var name = document.getElementById(noteName).value;
 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&version='+version+'&noteName='+name;
	//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
    newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','formName:dummyHidden',1,1);		
}

function viewActionAdmin(){			
	var e = window.event;
	var button_id = e.srcElement.id;			
	var var1 = button_id.split(':');			
	var rowcount = var1[2];			
	var noteId = "formName:searchResultQuestTable:"+rowcount+":noteIdHid1";
	var noteName = "formName:searchResultQuestTable:"+rowcount+":noteNameHid1";
	var versionValue = "formName:searchResultQuestTable:"+rowcount+":noteVersionHid1";		
	var noteIdValue = document.getElementById(noteId).value;
	var version = document.getElementById(versionValue).value;
	var name = document.getElementById(noteName).value;
 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&version='+version+'&noteName='+name;
	//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
    newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','formName:dummyHidden',1,1);		
}
function viewActionPrdQuest(){			
	var e = window.event;
	var button_id = e.srcElement.id;			
	var var1 = button_id.split(':');			
	var rowcount = var1[2];			
	var noteId = "formName:searchResultProdQuestTable:"+rowcount+":noteIdHid2";
	var noteName = "formName:searchResultProdQuestTable:"+rowcount+":noteNameHid2";
	var versionValue = "formName:searchResultProdQuestTable:"+rowcount+":noteVersionHid2";		
	var noteIdValue = document.getElementById(noteId).value;
	var version = document.getElementById(versionValue).value;
	var name = document.getElementById(noteName).value;
 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&version='+version+'&noteName='+name;
	//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
    newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','formName:dummyHidden',1,1);	
}
var noteIdValue;
var version;
var name;
function viewActionTierLine(noteIdValue,version,name){		
 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&version='+version+'&noteName='+name;
	//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
    newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','formName:dummyHidden',1,1);	
}
function viewActionTierQuesiion(noteIdValue,version,name){		
 	var url = '../popups/viewNoteDetails.jsp'+getUrl()+'?noteId='+noteIdValue+'&version='+version+'&noteName='+name;
	//newWinForView = window.showModalDialog(url+ "&temp=" + Math.random(), "","dialogHeight:300px;dialogWidth:500px;resizable=true;status=no;");		
    newWinForView = ewpdModalWindow_NotesView(url+ "&temp=" + Math.random(),'dummyDiv','formName:dummyHidden',1,1);	
}

	function printPage(){
		window.print();
	}

</script>

<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="uncodedNotesValidation" /></form>
</HTML>

