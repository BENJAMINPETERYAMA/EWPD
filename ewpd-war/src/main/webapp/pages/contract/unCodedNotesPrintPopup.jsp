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
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<STYLE>
.gridColumn17{
	width: 17%;
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
.gridColumn20{
	width: 20%;
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
.gridColumn5{
	width: 5%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn6{
	width: 6%;
	font-size: 11px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-align:left;
}
.gridColumn18{
	width: 18%;
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
.gridColumn7{
	width: 7%;
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
<TITLE>Contract Notes Validation Print</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="formName">
	<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
		<h:inputHidden id="printproductBenefitDefinition"
			binding="#{contractBasicInfoBackingBean.loadUncodedNotesForPrint}"></h:inputHidden>
		<br />
		<table id="questionHeaderTable" width="98%" cellpadding="1"
			cellspacing="1" border="0" align="center">
			<tr>
				<td width="15%"><h:outputText value="Contract Name " /></td>
				<td>:&nbsp;<SPAN id="contractName"></SPAN></td>
				<td width="25%"><SPAN id="timeID">time</SPAN></td>
			</tr>
			<tr>
				<td><h:outputText value="Version" /></td>
				<td>:&nbsp;<SPAN id="versionNumber"></SPAN></td>
				<td></td>
			</tr>
		</table>
		<br />
		<DIV id="resultHeaderDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText
					value="Notes in uncoded Benefit Lines "></h:outputText></strong></TD>
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
					<TD align="left"><h:outputText value="Note Id"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</DIV>

		<DIV id="searchResultdataTableDiv"
			style="position:relative;z-index:1;font-size:10px;"></DIV>

		<br>
		

		<DIV id="resultHeaderQuestDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Notes in unanswered Questions"></h:outputText></strong></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultQuestHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left"><h:outputText value="Admin Option "></h:outputText></TD>
					<TD align="left"><h:outputText value="Question"></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</div>

		<DIV id="searchResultdataTableQuestDiv"
			style="position:relative;z-index:1;font-size:10px;"></DIV>
		<br />

		<DIV id="resultHeaderProdQuestDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Notes in unanswered Questions attached to Product "></h:outputText></strong></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="resultProdQuestHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Admin Option "></h:outputText></TD>
					<TD align="left"><h:outputText value="Question"></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</div>

		<DIV id="searchResultdataTableProdQuestDiv"
			style="position:relative;z-index:1;font-size:10px;"></DIV>
		<br />

		<DIV id="tierNoteHeaderDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Notes in uncoded Tiered Benefit Lines "></h:outputText></strong></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="tierNoteHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left"><h:outputText value="Line "></h:outputText></TD>
					<TD align="left"><h:outputText value="PVA "></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</div>

		<DIV id="searchResultTierNoteDiv"
			style="position:relative;z-index:1;font-size:10px;"><h:panelGrid id="tierPanelTable"
						binding="#{contractBasicInfoBackingBean.tierNotePanel}">
			</h:panelGrid></DIV>
		<br/>
		<DIV id="tierQuesHeaderDiv">
		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="headerTable" border="0" width="100%">
			<TR>
				<TD><strong><h:outputText value="Notes in Tiered Unanswered  Questions "></h:outputText></strong></TD>
			</TR>
		</TABLE>

		<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
			id="tierQuesHeaderTable" border="0" width="100%">
			<TBODY>
				<TR class="dataTableColumnHeader">
					<TD align="left"><h:outputText value="Date Segment "></h:outputText></TD>
					<TD align="left"><h:outputText value="Product "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit Component "></h:outputText></TD>
					<TD align="left"><h:outputText value="Benefit "></h:outputText></TD>
					<TD align="left"><h:outputText value="Admin Option "></h:outputText></TD>
					<TD align="left"><h:outputText value="Question"></h:outputText></TD>
					<TD align="left"><h:outputText value="Note Id"></h:outputText></TD>
				</TR>
			</TBODY>
		</TABLE>
		</div>

		<DIV id="searchResultTierQuesDiv"
			style="position:relative;z-index:1;font-size:10px;"><h:panelGrid id="tierQuesPanelTable"
						binding="#{contractBasicInfoBackingBean.tierQuestNotePanel}">
			</h:panelGrid></DIV>
	</h:form>
	</BODY>
</f:view>

<script>
	

	var parentDoc = window.dialogArguments; 
	document.getElementById('timeID').innerHTML =  parentDoc.getElementById('timeID').innerHTML;
	document.getElementById('contractName').innerHTML = 	parentDoc.getElementById('formName:contractName').innerHTML;
	document.getElementById('versionNumber').innerHTML = 	parentDoc.getElementById('formName:versionNumber').innerHTML;
	//document.getElementById('productName').innerHTML = 	parentDoc.getElementById('formName:productName').innerHTML;
	
	
	document.getElementById('searchResultdataTableDiv').innerHTML = parentDoc.getElementById('searchResultdataTableDiv').innerHTML;
	document.getElementById('searchResultdataTableQuestDiv').innerHTML = parentDoc.getElementById('searchResultdataTableQuestDiv').innerHTML;
	document.getElementById('searchResultdataTableProdQuestDiv').innerHTML = parentDoc.getElementById('searchResultdataTableProdQuestDiv').innerHTML;
	//document.getElementById('searchResultTierNoteDiv').innerHTML = parentDoc.getElementById('panelContent').innerHTML;

	
	var lineObject = getObj('formName:searchResultTable');
	for (var i=0;i<lineObject.rows.length;i++)
		{
			lineObject.rows[i].deleteCell(7);
			lineObject.rows[i].className = 'dataTableOddRow';
//			lineObject.rows[i].cells[6].children[0].style.visibility = 'hidden';
		}
	
	var questObject = getObj('formName:searchResultQuestTable');
	for (var i=0;i<questObject.rows.length;i++)
		{
			questObject.rows[i].deleteCell(7);
			questObject.rows[i].className = 'dataTableOddRow';
//questObject.rows[i].cells[6].children[0].style.visibility = 'hidden';
		}

	var prdQuestObject = getObj('formName:searchResultProdQuestTable');
	for (var i=0;i<prdQuestObject.rows.length;i++)
		{
			prdQuestObject.rows[i].deleteCell(5);
			prdQuestObject.rows[i].className = 'dataTableOddRow';
//			prdQuestObject.rows[i].cells[4].children[0].style.visibility = 'hidden';
		}

	if(getObj('formName:searchResultTable') != null && getObj('formName:searchResultTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultTable','18%:15%:15%:20%:22%:4%:6%');
		setColumnWidth('resultHeaderTable','18%:15%:15%:20%:22%:4%:6%');
		syncTables('resultHeaderTable','formName:searchResultTable');
	} else {
		document.getElementById('resultHeaderDiv').style.display = 'none';
		document.getElementById('searchResultdataTableDiv').style.display = 'none';
	}

	if(getObj('formName:searchResultQuestTable') != null && getObj('formName:searchResultQuestTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultQuestTable','18%:15%:15%:15%:15%:15%:7%');
		setColumnWidth('resultQuestHeaderTable','18%:15%:15%:15%:15%:15%:7%');
		syncTables('resultQuestHeaderTable','formName:searchResultQuestTable');
	} else {
		document.getElementById('resultHeaderQuestDiv').style.display = 'none';
		document.getElementById('searchResultdataTableQuestDiv').style.display = 'none';
	}

	if(getObj('formName:searchResultProdQuestTable') != null && getObj('formName:searchResultProdQuestTable').rows.length > 0 ) {
		setColumnWidth('formName:searchResultProdQuestTable','18%:20%:20%:25%:7%:10%');
		setColumnWidth('resultProdQuestHeaderTable','18%:20%:20%:25%:7%:10%');
		syncTables('resultProdQuestHeaderTable','formName:searchResultProdQuestTable');
	} else {
		document.getElementById('resultHeaderProdQuestDiv').style.display = 'none';
		document.getElementById('searchResultdataTableProdQuestDiv').style.display = 'none';
	}
	
	var notePanelObject = document.getElementById('formName:tierPanelTable');
	if(notePanelObject.rows.length==0){
		document.getElementById('tierNoteHeaderDiv').style.display = 'none';
		document.getElementById('searchResultTierNoteDiv').style.display = 'none';
	}else{
		setColumnWidth('tierNoteHeaderTable','17%:16%:17%:20%:19%:5%:6%');
		setColumnWidth('formName:tierPanelTable','17%:16%:17%:20%:19%:5%:6%');
		syncTables('tierNoteHeaderTable','formName:tierPanelTable');
	}

	var quesPanelObject = document.getElementById('formName:tierQuesPanelTable');
	if(quesPanelObject.rows.length==0){
		document.getElementById('tierQuesHeaderDiv').style.display = 'none';
		document.getElementById('searchResultTierQuesDiv').style.display = 'none';
	}else{
		setColumnWidth('tierQuesHeaderTable','18%:15%:15%:15%:15%:15%:7%');
		setColumnWidth('formName:tierQuesPanelTable','18%:15%:15%:15%:15%:15%:7%');
		syncTables('tierQuesHeaderTable','formName:tierQuesPanelTable');
	}
	
	window.print();
</script>

</HTML>
