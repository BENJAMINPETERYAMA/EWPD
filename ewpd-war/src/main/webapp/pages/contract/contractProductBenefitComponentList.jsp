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
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
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
<base target=_self>
<TITLE>Attached Benefit Components</TITLE>
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
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractProductComponentForm">
					
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><jsp:include
								page="../contract/contractTree.jsp"></jsp:include></TD>
							<TD width="727" colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>
							<!-- Table containing Tabs -->
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<tr>
									<td width="25%" id="stdDefTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"></TD>
													<TD class="tabNormal"> 
														<h:commandLink action="#{contractProductGeneralInfoBackingBean.displayProductGeneralInfo}"
														id="linkToGeneralInfo"
												onmousedown="javascript:navigatePageAction(this.id);">
														 <h:outputText value="General Information" /> </h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"></TD>
										</TR>
									</table>
									</td>
									<td width="25%" id="sbMandateInfoTab">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="3" align="left"><img
												src="../../images/activeTabLeft.gif" 
												width="3" height="21" /></td>
											<td class="tabActive">
												<h:outputText value="Benefit Component" />
											</td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>

									<td width="25%"></td>
								</tr>
							</table>
							<!-- End of Tab table -->

							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:350">
							<DIV id="noBenefitDefinitions"
								style="font-family:Verdana, Arial, Helvetica, sans-serif;
							font-size:11px;font-weight:bold;text-align:center;color:#000000;
							background-color:#FFFFFF;">No
							Benefit Components available.</DIV>
					<!--	Start of Table for actual Data	-->

							<BR>
							
							<TABLE border="0" cellspacing="0" width="100%">
								
									<TR>
										<TD  height="20" bgcolor="#cccccc"><b>&nbsp;<h:outputText value="Associated Benefit Components"></h:outputText></b></TD>
									</TR>
									<TR>
										<td>
										<div id="displayHeaderDiv"
											style="background-color:#ffffff;z-index:1;"><h:panelGrid
											id="displayHeaderTable" 
											binding="#{contractComponentGeneralInfoBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										<div id="displayPanelContent12"
											style="position:relative;overflow:auto;height:230px;width:100%">
										<h:panelGrid id="panelTable" width="100%"
											binding="#{contractComponentGeneralInfoBackingBean.panel}">
										</h:panelGrid>
										</div>
										</td>
									</TR>
									<TR>
										<td>
										</td>
									</TR>
									<TR>
										<td>
										</td>
									</TR>
									<TR>
										<td>&nbsp;
										</td>
									</TR>

									<TR>
										<TD width="110" height="1"><h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
											styleClass="wpdButton" rendered ="#{contractComponentGeneralInfoBackingBean.contractTypeMandate != true}"
											action="#{contractComponentGeneralInfoBackingBean.saveBenefitComponentList}">
										</h:commandButton></TD>
									</TR>
								
							</TABLE>
						
							
							<!--	End of Page data	-->
							</fieldset>
						</TR>
					</TABLE>
					<!-- Space for hidden fields -->
					<h:inputHidden id="panelData"  />
					<h:inputHidden id="states"
					value="#{contractComponentGeneralInfoBackingBean.flasgStatusForBC}"></h:inputHidden>
					<h:inputHidden id="duplicateData"
					value="#{contractComponentGeneralInfoBackingBean.duplicateData}"></h:inputHidden>
					<h:commandLink id="selectedShowHiddenLink"
						style="display:none; visibility: hidden;"
						action="#{contractComponentGeneralInfoBackingBean.loadWithHiddenValues}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="unselectedShowHiddenLink"
						style="display:none; visibility: hidden;"
						action="#{contractComponentGeneralInfoBackingBean.loadWithoutHiddenValues}">
						<f:verbatim />
					</h:commandLink>
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD width="100%"><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
/*
initialize();
		function initialize(){
			if(document.getElementById('contractProductComponentForm:searchResultTable') != null) {

				setColumnWidth('contractProductComponentForm:searchResultTable','30%:30%:20%');
					
			}else {
				document.getElementById('searchResultdataTableDiv').style.visibility = 'hidden';
			}
		}
function getValuesForDelete() {
		var message = 'Are you sure to delete the selected Admin Option?';
		if (confirm(message) ){
			var e = window.event;
			var button_id = e.srcElement.id;
			var var1 = button_id.split(':');
			var rowcount = var1[2];
			var adminLevelOptionAssnSystemId = "contractProductComponentForm:searchResultTable:"+rowcount+":adminOptionIdHidden";
			var adminLevelOptionAssnSystemIdValue = document.getElementById(adminLevelOptionAssnSystemId).value;
			document.getElementById('contractProductComponentForm:hiddenAdminOptionForDelete').value = adminLevelOptionAssnSystemIdValue;
			document.getElementById('contractProductComponentForm:hideQuestion').click();
			return false;
		} else
			return false;
	}*/

IGNORED_FIELD1='contractProductComponentForm:duplicateData';
if(document.getElementById('contractProductComponentForm:panelTable').offsetHeight <= 100)
{
	document.getElementById('contractProductComponentForm:displayHeaderTable').width = "100%";
	document.getElementById('contractProductComponentForm:panelTable').width = "100%";
	setColumnWidth('contractProductComponentForm:displayHeaderTable','23%:18%:19%:25%:15%');
	setColumnWidth('contractProductComponentForm:panelTable','23%:18%:19%:25%:15%');
}
else
{
	var relTblWidth = document.getElementById('contractProductComponentForm:panelTable').offsetWidth;
	document.getElementById('contractProductComponentForm:displayHeaderTable').width = "97.4%";
	document.getElementById('contractProductComponentForm:panelTable').width = "100%";
	setColumnWidth('contractProductComponentForm:displayHeaderTable','23%:20%:14%:25%:18%');
	setColumnWidth('contractProductComponentForm:panelTable','23%:20%:14%:25%:18%');
}
function getLevelId(id)
{
	document.getElementById('contractProductComponentForm:selectedLevelId').value = id;
	return true;
}
//hides the header panel when no data is present
var tableObject = document.getElementById('contractProductComponentForm:panelTable');
if(tableObject.rows.length > 0){
	
	var divBnftDefn = document.getElementById('noBenefitDefinitions');
	divBnftDefn.style.visibility = "hidden";
	divBnftDefn.style.height = "2px";
}else{
	

	document.getElementById('noBenefitDefinitions').style.visibility = "visible";
}

	setColumnWidth('contractProductComponentForm:displayHeaderTable','70%:30%');	
	setColumnWidth('contractProductComponentForm:panelTable','70%:30%');

checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('contractProductComponentForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('contractProductComponentForm:panelTable');
		document.getElementById('contractProductComponentForm:panelData').value = onLoadPanelData;
	}
}
function getPanelData(list){
	var tagNames = list.split(',');
	var dataOnScreen = "";
	var tableObject = document.getElementById(tagNames[0]);
	var rows = tableObject.rows.length;
	if(rows >0){
		var columns = tableObject.rows[0].cells.length;
		for(var i=0;i<rows;i++){
			for(var j=0;j<columns;j++){
				if(null != tableObject.rows[i].cells[j].children[0]){
					if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
						dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
					}else{
						dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
					}
				}
			}
		}
		return dataOnScreen;
	}else{
		return dataOnScreen;
	}
}
function unsavedDataFinder(){
	var panelData = getPanelData('contractProductComponentForm:panelTable');
	if(document.getElementById('contractProductComponentForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			goToAction();
		}
		else{
			var select =  document.getElementById('contractProductComponentForm:showHidden');
			if(select.checked){
				document.getElementById('contractProductComponentForm:showHidden').checked=false;
			}else{
				document.getElementById('contractProductComponentForm:showHidden').checked=true;
			}
		}
	}
	else{
		goToAction();
	}
}
function goToAction(){

	var select =  document.getElementById('contractProductComponentForm:showHidden');

	if(select.checked){
		submitLink('contractProductComponentForm:selectedShowHiddenLink');
	}else{
		submitLink('contractProductComponentForm:unselectedShowHiddenLink');
	}
		
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractProductBenefitComponentList" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('contractProductComponentForm:duplicateData').value == '')
		document.getElementById('contractProductComponentForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractProductComponentForm:duplicateData').value;
</script>


</html>