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
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
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
<TITLE>Benefit Level View</TITLE>
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form id="benefitLevelForm">
				<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<TD width="273" valign="top" class="leftPanel">

						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="../standardBenefit/standardBenefitTree.jsp"></jsp:include>

						</DIV>

						</TD>

						<td colspan="2" valign="top" class="ContentArea">
						
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td width="186" class="tabActive"  align="center">
										<h:outputText value="Benefit Level"></h:outputText>										
										</td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="100%"></td>
							</tr>
						</table>
						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:500">

<h:inputHidden id="loadHidden"   value="#{benefitLevelBackingBean.loadBenefitLevelView}"/>

						<table border="0" cellpadding="5" cellspacing="0" width="100%">

							<tr>
								<td style="outputText" width="40%" align="left"><h:outputText
									value="Benefit Period: " styleClass="outputText" />
								&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td width="60%" align="left"><h:outputText
									id="benefitDefinition"
									value="#{benefitLevelBackingBean.benefitDefinition}"
									styleClass="outputText" /></td>


							</tr>
					</table>

					<TABLE border="0" cellpadding="5" cellspacing="0" width="100%">
						<tr>
					<TD>
					<div  id="messageTextForNoBenefitLevelsDiv" align="center"><br>
						<br>
						<STRONG>&nbsp;<h:outputText  value="No Benefit Level available." /></STRONG>
						<CENTER>
					</div>	
						<div id="headerDiv">
						<table id="headerTable" cellpadding="3" border="0" width="100%">
							<TR bgcolor="#cccccc">
								<TD bgcolor="#CCCCCC" height="23px"><b><h:outputText
									value="Associated Benefit Levels" /></b></TD>
							</TR>
						</table>
						</div>
					</TD>
						</tr>
						<TR>
								<TD colspan="2">

		<div id="associatedBenefitspanelHeader">
				<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
					id="resultHeaderTable" border="0" width="100%">
					<TBODY>
						<TR class="dataTableColumnHeader">
							<TD align="left"><h:outputText value="Seq"/></TD>
							<TD align="left"><h:outputText value="Description"/></TD>
							<TD align="left"><h:outputText value="Term"/></TD>
							<TD align="left"><h:outputText value="Frequency - Qualifier"/></TD>
							<TD align="left"><h:outputText value="PVA"/></TD>
							<TD align="left"><h:outputText value="Format"/></TD>
							<TD align="left"><h:outputText value="Benefit Value"/></TD>
							<TD align="left"><h:outputText value="Reference"/></TD>
							<td id="noteColumn" ><h:outputText value=" "/></td>
						</TR>
					</TBODY>
				</TABLE>
</div>

								<div id="associatedBenefitspanel" style="height:334px;overflow:auto;">

						<h:dataTable headerClass="tableHeader" id="panelTable" border="0" 
									value="#{benefitLevelBackingBean.benefitLevelsListForView}"
									 var="eachRow" width="100%"
									 cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
									rowClasses="dataTableOddRow,dataTableEvenRow">
									
							<h:column>
								<h:inputHidden id="benefitLineIdHidden"   value="#{eachRow.benefitLineId}"/> 
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.seq}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.description}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.term}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.frequency}"/>
								<h:outputText  value=" "/>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.qualifier}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.pva}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.format}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.benefitValue}"/>
							</h:column>
							<h:column>
								<h:outputText  styleClass="formOutputColumnField" value="#{eachRow.reference}"/>
							</h:column>
							<h:column id="noteColumn1">
								<h:outputText value=" " id="a11spaceSpan" rendered="false"/>
										<h:commandButton alt="notes"
											id="scheduleToProductionButton" 
											image="#{eachRow.notesExist?'../../images/notes_exist.gif':'../../images/page.gif'}"
											onclick="onNoteIconClick();return false;"
											rendered="#{eachRow.renderNotesAttachmentImage}" />
							</h:column>
							
					</h:dataTable>
</div>
	<h:inputHidden id="selectedBenefitLineId"	 />
	<h:inputHidden id="isMandate"   value="#{benefitLevelBackingBean.mandate}"/>
								</td>
							</tr>
						</table>
						</fieldset>
						</td>
					</tr>
				</TABLE>
				<TR>
					<TD><%@ include file="../navigation/bottom_view.jsp"%></TD>
				</TR>
			</h:form></td>
		</tr>
	</table>
	</BODY>
</f:view>
<script language="JavaScript" type="text/javascript">

function onNoteIconClick(){
	
    getFromDataTableToHidden('benefitLevelForm:panelTable','benefitLineIdHidden','benefitLevelForm:selectedBenefitLineId');						
	var benefitLineId =  document.getElementById('benefitLevelForm:selectedBenefitLineId').value;
	var url = '../standardBenefit/benefitLineAssociatedNotesPopUp.jsp';
	var retValue = window.showModalDialog(url +getUrl()+ "?" + "&temp=" + Math.random() + "&bnftLineId=" + benefitLineId, self, "dialogHeight:250px;dialogWidth:550px;resizable=false;status=no;");	
	return true;
}

	var tableObject = document.getElementById('benefitLevelForm:panelTable');
	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('associatedBenefitspanel');
		var noMessage = document.getElementById('messageTextForNoBenefitLevelsDiv');
		noMessage.style.visibility = "hidden";
	}else{
		var divObj = document.getElementById('associatedBenefitspanel');
		var divObjForHeader = document.getElementById('associatedBenefitspanelHeader');
		var divForHeader = document.getElementById('headerDiv');
		divObjForHeader.style.visibility = "hidden";
		divObj.style.visibility = "hidden";
		divForHeader.style.visibility = "hidden";
		divObj.style.height = "2px";
	}


		if(document.getElementById('benefitLevelForm:panelTable') != null) {
		var selectObj = document.getElementById('benefitLevelForm:isMandate');    // Get the ComboBox for contract Type
	
			document.getElementById('resultHeaderTable').width = "100%";
			document.getElementById('benefitLevelForm:panelTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('benefitLevelForm:panelTable').offsetHeight <= 189) {
				document.getElementById('benefitLevelForm:panelTable').width = relTblWidth+"px";
				if(selectObj.value == 'true'){
					noteColumn.style.display='none';
					setColumnWidth('resultHeaderTable','7%:18%:10%:13%:5%:8%:8%:12%');
					setColumnWidth('benefitLevelForm:panelTable','7%:18%:10%:13%:5%:8%:8%:12%');
					syncTables('resultHeaderTable','benefitLevelForm:panelTable');
				}else{
					if(document.getElementById('benefitLevelForm:panelTable').rows.length > 0){
						setColumnWidth('resultHeaderTable','4%:15%:11%:15%:5%:7%:7%:14%:3%');
						setColumnWidth('benefitLevelForm:panelTable','4%:15%:11%:15%:5%:7%:7%:14%:3%');
						syncTables('resultHeaderTable','benefitLevelForm:panelTable');
					}
					
				}
			}else{
				document.getElementById('benefitLevelForm:panelTable').width = relTblWidth-17+"px";
				document.getElementById('resultHeaderTable').width = relTblWidth-17+"px";
				if(selectObj.value == 'true'){
					noteColumn.style.display='none';
					setColumnWidth('resultHeaderTable','4%:20%:10%:13%:4%:8%:8%:12%');
					setColumnWidth('benefitLevelForm:panelTable','4%:20%:10%:13%:4%:8%:8%:12%');
				}else{
					setColumnWidth('resultHeaderTable','4%:15%:11%:15%:5%:7%:7%:14%:3%');
					setColumnWidth('benefitLevelForm:panelTable','4%:15%:11%:15%:5%:7%:7%:14%:3%');
				}
				
			}
			
		}







//		if(document.getElementById('benefitLevelForm:panelTable').rows.length > 0) {	
			//to change style of first row
//			document.getElementById('benefitLevelForm:panelTable').rows[0].style.font="bold 11px Verdana, Arial, Helvetica, sans-serif";
/*
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			
			if(document.getElementById('benefitLevelForm:panelTable').offsetHeight > 334) {
//				document.getElementById('benefitLevelForm:panelTable').width = relTblWidth+"px";
				document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";

				setColumnWidth('benefitLevelForm:panelTable','5%:20%:15%:15%:8%:8%:9%');	
				setColumnWidth('resultHeaderTable','5%:20%:15%:15%:8%:8%:9%:16%');	
				syncTables('resultHeaderTable','benefitLevelForm:panelTable');
			}
*/
//		}

	function viewAssociatedNotesPopup(url, benefitLineId){
		var retValue = window.showModalDialog(url +getUrl()+ "?" + "&temp=" + Math.random() + "&bnftLineId=" + benefitLineId, self, "dialogHeight:250px;dialogWidth:550px;resizable=false;status=no;");	
		return false;
	}

		function syncTables(){
			var relTblWidth = document.getElementById('benefitLevelForm:panelTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitLevel" /></form>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
