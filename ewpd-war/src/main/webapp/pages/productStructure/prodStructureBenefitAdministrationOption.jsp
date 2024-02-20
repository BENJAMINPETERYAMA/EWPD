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
<base target=_self>
<TITLE>Admin Options</TITLE>
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
				<TD><!--  WAS 7.0 Changes - Hidden id rootQuestionLoad value loaded using binding instead of value -->
					<h:inputHidden binding="#{adminOptionBackingBean.loadProductStructureAdminOptions}"/>
					<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
				</TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionsForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD valign="top" class="leftPanel" width="300">
							<div class="treeDiv"><jsp:include
								page="../productStructure/productStructureTree.jsp"></jsp:include></div>

							</TD>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>
							
							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Administration Option" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												onmousedown="return false;"
												action="#{adminMethodBackingBean.loadForProductStructure}"
												id="thisid"
												onmousedown="javascript:navigatePageAction(this.id);" >
												<h:outputText value="Administration Process" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>

									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:470">

							<DIV id="adminOptionTable"><!--	Start of Table for actual Data	-->

							<BR>

							<TABLE border="0" cellspacing="0" cellpadding="3" width="98%"
								class="outputText">
								<TBODY>
									<TR>
										<td></td>
									</TR>
									<TR>
										<td valign="middle">
										<div id="noAdminOptionDiv"
											style="background-color:#FFFFFF;z-index:1;overflow:auto;height:20px;width:100%">
										<B><h:outputText value="No Associated Admin Options"></h:outputText> </B>
										</div>
										<div id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;width:100%">
										<B>&nbsp;<h:outputText value="Admin Options"></h:outputText> </B>
										</div>
								
							
										<div id="displayHeaderDiv"
											style="background-color:#FFFFFF;z-index:1;width:100%"><h:panelGrid
											id="displayHeaderTable" width="100%"
											binding="#{adminOptionBackingBean.headerPanel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid></div>
										<div id="displayPanelDiv"
											style="position:relative;overflow:auto;width:100%">
										<h:panelGrid id="panelTable" width="100%"
											binding="#{adminOptionBackingBean.panel}">
										</h:panelGrid></div>
										</td>
									</TR>
									<TR>
										<td><%-- div style="position:relative;  background-color:#ffffff;overflow:auto;">
											<div id="displayPanelContent12" style="position:relative;">
												<h:panelGrid id="panelTable"
													binding="#{productStructureBenefitAdministrationBackingBean.panel}"
													rowClasses="dataTableEvenRow,dataTableOddRow">
												</h:panelGrid> 
											</div>
										</div--%><BR>
										</td>
									</TR>
									

									<TR id="saveButton">
										<TD width="110" height="1"><div id="saveButtonDiv"
											style="position:relative;overflow:auto;width:100%"><h:commandButton value="Save"
											styleClass="wpdButton"
											onmousedown="javascript:savePageAction(this.id);"
											action="#{adminOptionBackingBean.saveAdminOptionsForProdStructure}">
										</h:commandButton></div></TD>
									</TR>
								</TBODY>
							</TABLE>
							</DIV>
							<!--	End of Page data	--></fieldset>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->						
					<!-- End of hidden fields  -->
					<h:inputHidden id="panelData"  />
					<h:inputHidden id="hiddenTabValue"
										value="#{adminOptionBackingBean.componentTypeTab}"></h:inputHidden>
					<h:inputHidden id="benTypeHidden"
							value="#{productStructureGeneralInfoBackingBean.benefitTypeTab}" />
					<h:inputHidden id="adminStates"
							value="#{adminOptionBackingBean.adminOptionStates}" />


					<h:commandLink id="hideQuestion"
						style="display:none; visibility: hidden;"
						action="#{adminOptionBackingBean.hideProductAdminOption}">
						<f:verbatim />
					</h:commandLink>
					<h:inputHidden id="hiddenAdminOptionForDelete"
						value="#{adminOptionBackingBean.adminLevelOptionAssnSystemId}"></h:inputHidden>
					<h:commandLink id="hiddenLnk1"
						style="display:none; visibility: hidden;">
						<f:verbatim />
					</h:commandLink>

					<h:inputHidden id="dummyHidden"></h:inputHidden>
					<!-- <h:inputHidden id="componentData"
					value="#{productNoteAssociationBackingBean.noteString}"></h:inputHidden> -->
					<h:commandLink id="deleteLink"
						style="display:none; visibility: hidden;"
						action="#{adminOptionBackingBean.deleteQuestion}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="selectedShowHiddenLink"
						style="display:none; visibility: hidden;"
						action="#{adminOptionBackingBean.loadWithHiddenValues}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="unselectedShowHiddenLink"
						style="display:none; visibility: hidden;"
						action="#{adminOptionBackingBean.loadWithoutHiddenValues}">
						<f:verbatim />
					</h:commandLink>
					<h:inputHidden id="duplicateData" value="#{adminOptionBackingBean.duplicateData}"/>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">


	IGNORED_FIELD1='adminOptionsForm:duplicateData';
if(document.getElementById('adminOptionsForm:panelTable') != null){
		
		if(document.getElementById('adminOptionsForm:displayHeaderTable').rows.length != 0){
			document.getElementById('displayHeaderDiv').style.visibility = 'visible';
			document.getElementById('displayPanelDiv').style.visibility = 'visible';
			document.getElementById('saveButtonDiv').style.visibility = 'visible';
			document.getElementById('LabelHeaderDiv').style.visibility = 'visible';
			document.getElementById('noAdminOptionDiv').style.visibility = 'hidden';
		}
		else{
			
			document.getElementById('displayHeaderDiv').style.visibility = 'hidden';
			document.getElementById('displayPanelDiv').style.visibility = 'hidden';
			document.getElementById('saveButtonDiv').style.visibility = 'hidden';
			document.getElementById('LabelHeaderDiv').style.visibility = 'hidden';
		
			document.getElementById('noAdminOptionDiv').style.visibility = 'visible';
		}
}

if(document.getElementById('adminOptionsForm:panelTable').offsetHeight <= 100)
{
	document.getElementById('adminOptionsForm:displayHeaderTable').width = "100%";
	document.getElementById('adminOptionsForm:panelTable').width = "100%";
	setColumnWidth('adminOptionsForm:displayHeaderTable','23%:18%:19%:25%:15%');
	setColumnWidth('adminOptionsForm:panelTable','23%:18%:19%:25%:15%');
}
else
{
	var relTblWidth = document.getElementById('adminOptionsForm:panelTable').offsetWidth;
	document.getElementById('adminOptionsForm:displayHeaderTable').width = "100%";
	document.getElementById('adminOptionsForm:panelTable').width = "100%";
	setColumnWidth('adminOptionsForm:displayHeaderTable','23%:20%:14%:25%:18%');
	setColumnWidth('adminOptionsForm:panelTable','23%:20%:14%:25%:18%');
}

function getLevelId(id)
{
	document.getElementById('adminOptionsForm:selectedLevelId').value = id;
	return true;
}

function goToAction(){
	
	var select =  document.getElementById('adminOptionsForm:showHidden');
	

	if(select.checked){
		submitLink('adminOptionsForm:selectedShowHiddenLink');
	}else{
		submitLink('adminOptionsForm:unselectedShowHiddenLink');
	}
		
}
clearPanelTable();
function clearPanelTable(){
	var select =  document.getElementById('adminOptionsForm:showHidden');
	if(!select.checked){
		var tableObject = document.getElementById('adminOptionsForm:panelTable');
		var rows = tableObject.rows.length;
		if(rows >0){
			var columns = tableObject.rows[0].cells.length;
			for(var i=0;i<rows;i++){
				if(null != document.getElementById('adminOptionsForm:showHidden'+i))
					document.getElementById('adminOptionsForm:showHidden'+i).checked = false;
			}
		}			
	}
}checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('adminOptionsForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('adminOptionsForm:panelTable');
		document.getElementById('adminOptionsForm:panelData').value = onLoadPanelData;
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
	var panelData = getPanelData('adminOptionsForm:panelTable');
	if(document.getElementById('adminOptionsForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			goToAction();
		}
		else{
			var select =  document.getElementById('adminOptionsForm:showHidden');
			if(select.checked){
				document.getElementById('adminOptionsForm:showHidden').checked=false;
			}else{
				document.getElementById('adminOptionsForm:showHidden').checked=true;
			}
		}
	}
	else{
		goToAction();
	}
}
/* var tableObject = document.getElementById('adminOptionsForm:panelTable');

if(tableObject.rows.length > 0){
	
	var divBnftDefn = document.getElementById('noBenefitDefinitions');
	divBnftDefn.style.visibility = "hidden";
	divBnftDefn.style.height = "2px";
}else{
	
	var divObj = document.getElementById('benefitDefinitionTable');
	divObj.style.visibility = "hidden";
	divObj.style.height = "2px";
	document.getElementById('noBenefitDefinitions').style.visibility = "visible";
} */
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productStructureAdminOption" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('adminOptionsForm:duplicateData').value == ''){
		document.getElementById('adminOptionsForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('adminOptionsForm:duplicateData').value;
</script>
</HTML>