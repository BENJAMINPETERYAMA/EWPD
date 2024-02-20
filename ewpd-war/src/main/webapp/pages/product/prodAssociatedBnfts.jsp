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

<TITLE>Benefit Component Hierarchies</TITLE>
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
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td><h:form styleClass="form" id="BenefitComponentHierarchiesForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><!-- Space for Tree  Data	--> <jsp:include
							page="productTree.jsp"></jsp:include></DIV>


						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>

						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink id="genId"
											action="#{productComponentGeneralInfoBackingBean.retrieveBenefitComponent}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value=" General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td class="tabActive" width="186"><h:outputText
											value="Benefit" /></td>
										<td width="2" align="right"><img
											src="../../images/activeTabRight.gif" alt="Tab Right Active"
											width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<td width="200" id="bcNotesTab">
								<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
									<TR>
										<TD width="2" align="left"><IMG
											src="../../images/tabNormalLeft.gif" width="3" height="21"></TD>
										<TD width="100%" class="tabNormal"><h:commandLink id="noteId"
											action="#{productBenefitComponentNoteBackingBean.loadNotes}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value="Notes " />
										</h:commandLink></TD>
										<TD width="2" align="right"><IMG
											src="../../images/tabNormalRight.gif" width="2" height="21"></TD>
									</TR>
								</TABLE>
								</td>
								<TD width="100%"></TD>
							</tr>
						</table>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:530">

						<!--	Start of Table for actual Data	--><h:inputHidden
							id="PageLoad1"
							binding="#{productComponentGeneralInfoBackingBean.loadAssociatedBenefits}"></h:inputHidden>

						<table border="0" id="tableNew" cellspacing="0" cellpadding="0"
							width="100%">
							<tr>
								<td><br>
								<br>
								</td>
							</tr>
							<tr>
								<td>
								<div id="associatedBenefitspanel"
									style="background-color:#FFFFFF;z-index:1;overflow:hidden;"><h:panelGrid
									id="displayTable" style="height:22px"
									binding="#{productComponentGeneralInfoBackingBean.displayPanel}">
								</h:panelGrid></div>
								</td>
							</TR>
							<tr>
								<td>
								<div id="associatedBenefitspanel"
									style="background-color:#FFFFFF;z-index:1;overflow:hidden;"><h:panelGrid
									id="BenefitHeaderViewPanel"
									binding="#{productComponentGeneralInfoBackingBean.benefitHeaderViewPanel}"
									rowClasses="dataTableOddRow,dataTableEvenRow">
								</h:panelGrid></div>
								</td>
							</TR>
							<tr>
								<td bordercolor="#cccccc">
								<div id="searchResultdataTableDiv"
									style="height:271px;overflow:auto;"><h:panelGroup
									id="associatedpanel" >
									<h:panelGrid id="panelTable"
										binding="#{productComponentGeneralInfoBackingBean.panel}"
										>
									</h:panelGrid>
								</h:panelGroup></div>
								</td>
							</tr>

						</table>
						<br>
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
							id="tabTable">
							<TR>
								<TD width="30" align="left"><h:commandButton value="Save" onmousedown="javascript:savePageAction(this.id);"
									rendered="#{productComponentGeneralInfoBackingBean.saveButton}"
									styleClass="wpdButton" id="addQues"
									onclick="saveFunc();return false;">
								</h:commandButton></TD>
								<TD width="229">&nbsp;</TD>
							</TR>
						</TABLE>
						<!--	End of Page data	--></fieldset>

						</TD>
					</TR>

				</table>
				<h:commandLink id="saveButton"
					action="#{productComponentGeneralInfoBackingBean.saveBenefitDetails}"
					style="hidden">
					<f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink>
				
				<h:commandLink id="showHiddenLink"
					action="#{productComponentGeneralInfoBackingBean.loadAssociatedBenefits}"
					style="hidden">
					<f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink>
				<h:inputHidden id="showHiddenInput"
					value="#{productComponentGeneralInfoBackingBean.showHidden}"></h:inputHidden>
				<h:inputHidden id="panelData" value="#{productComponentGeneralInfoBackingBean.panelData}" />
				<h:inputHidden id="states"
					value="#{productComponentGeneralInfoBackingBean.flagStatus}"></h:inputHidden>
				<h:inputHidden id="hiddenProductType"
					value="#{productComponentGeneralInfoBackingBean.productType}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{productComponentGeneralInfoBackingBean.duplicateData}"></h:inputHidden>


			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
IGNORED_FIELD1='BenefitComponentHierarchiesForm:duplicateData';
IGNORED_FIELD2='BenefitComponentHierarchiesForm:panelData';
//alert('inside 1');
		var tableObject = document.getElementById('BenefitComponentHierarchiesForm:panelTable');
		var divObj = document.getElementById('associatedBenefitspanel');	
		document.getElementById('tableNew').width = "100%";
		var relTblWidth = document.getElementById('tableNew').offsetWidth;
		document.getElementById('BenefitComponentHierarchiesForm:panelTable').width = relTblWidth+"px";
		document.getElementById('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel').width = relTblWidth+"px";
		setColumnWidth('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel','70%:30%');	
		setColumnWidth('BenefitComponentHierarchiesForm:panelTable','70%:30%');

		if(!(tableObject.rows.length>0)){
		var saveVar = document.getElementById('BenefitComponentHierarchiesForm:save');
		saveVar.style.display = 'none';

		}
//alert('inside 2');
function saveFunc(){
//alert('inside 3');
var select = document.getElementById('BenefitComponentHierarchiesForm:showHiddenCheckbx');
	if(select.checked){
	//alert(select.checked);
   	 document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value = true;
	}
	else{
//	alert(select.checked);
		document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value = false;
	}
//alert('inside 4');	
	submitLink('BenefitComponentHierarchiesForm:saveButton');
		
}


var i = document.getElementById("BenefitComponentHierarchiesForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
		bcNotesTab.style.display='none';
		//tab3.style.display='none';
	}else{
		bcNotesTab.style.display='';
		//tab3.style.display='';
	}


function getActionMethod(){
//alert('inside 5');	
	var select = document.getElementById('BenefitComponentHierarchiesForm:showHiddenCheckbx');
	
	if(select.checked)
    document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value = true;
	else
	document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value = false;
	//alert('inside 6');	
	//alert(document.getElementById('BenefitComponentHierarchiesForm:showHiddenInput').value);
	submitLink('BenefitComponentHierarchiesForm:showHiddenLink');
}

//checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('BenefitComponentHierarchiesForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('BenefitComponentHierarchiesForm:panelTable');
		document.getElementById('BenefitComponentHierarchiesForm:panelData').value = onLoadPanelData;
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
	var panelData = getPanelData('BenefitComponentHierarchiesForm:panelTable');
	if(document.getElementById('BenefitComponentHierarchiesForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			getActionMethod();
		}
		else{
			var select =  document.getElementById('BenefitComponentHierarchiesForm:showHiddenCheckbx');
			if(select.checked){
				document.getElementById('BenefitComponentHierarchiesForm:showHiddenCheckbx').checked=false;
			}else{
				document.getElementById('BenefitComponentHierarchiesForm:showHiddenCheckbx').checked=true;
			}
		}
	}
	else{
		getActionMethod();
	}
}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productBenefitInfo" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value == ''){
		document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
	else{
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value;
		}
</script>
</HTML>
