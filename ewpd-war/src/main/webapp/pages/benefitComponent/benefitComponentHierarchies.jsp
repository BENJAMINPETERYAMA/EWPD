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
<!-- WAS 7.0 Changes - Hidden id loadBenefitHierarchy value loaded using binding instead of value -->

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
							page="../benefitComponent/benefitComponentTree.jsp"></jsp:include>
						</DIV>


						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message
										value="#{BenefitComponentHierarchiesBackingBean.validationMessages}"></w:message>
									</TD>
								</tr>
							</TBODY>
						</TABLE>
							<h:inputHidden id="dummy" 
							binding="#{BenefitComponentHierarchiesBackingBean.loadBenefitHierarchy}"></h:inputHidden>
						<!-- Table containing Tabs -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="TabTable">
							<tr>
								<td width="200" >
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
										<td width="186" class="tabNormal"><h:commandLink id="genId"
											action="#{benefitComponentCreateBackingBean.loadUpdateTab}"
											onmousedown="javascript:navigatePageAction(this.id);">
											<h:outputText value=" General Information" />
										</h:commandLink></td>
										<td width="2" align="right"><img
											src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
									</tr>
								</table>
								</td>
								<h:inputHidden id="hiddenTabValue"
									value="#{benefitComponentCreateBackingBean.componentTypeTab}"></h:inputHidden>
								<td width="200">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2" align="left"><img
											src="../../images/activeTabLeft.gif" alt="Tab Left Active"
											width="3" height="21" /></td>
										<td class="tabActive" width="186">
											<h:outputText value="Benefit Hierarchies" />
										</td>
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
											action="#{BenefitComponentNotesBackingBean.loadBenefitComponentNotes}"
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
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:20px;width:100%">

						<!--	Start of Table for actual Data	-->
						
						<table border="0" id="tableNew" cellspacing="0" cellpadding="0"
							class="outputText">
							<tbody>
								<tr>
									<td>
									<TABLE border="0" cellspacing="0" cellpadding="3"
										class="outputText">
										<TBODY>

											<TR>
												<TD valign="top" width="137"><h:outputText
													value="Benefits*"
													styleClass="#{BenefitComponentHierarchiesBackingBean.benefitValdn?'mandatoryNormal': 'mandatoryError'}"></h:outputText></TD>
												<TD align="left" width="200">
												<DIV id="benefitDiv" class="selectDataDisplayDiv"></DIV>
												<TD align="left" valign="top" width="633">

												<h:commandButton
													image="../../../images/select.gif" alt="Select"
													style="cursor:hand;" 
													onclick="loadBenefitPopUp();return false;" 
													tabindex="1"/>
												</TD>
											</TR>
											<TR>
												<TD colspan="2">&nbsp;</TD>
											</TR>
											<TR>
												<TD colspan="3"><h:commandButton value="Create" 
													onclick="unsavedDataFinder(this.id);return false;"
													styleClass="wpdbutton" id="createButton" tabindex="2"></h:commandButton>
												</TD>
											</TR>
											<TR>
												<TD colspan="3">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</td>
								</tr>
								<%--<TR valign="top">
									<TD>
									<div id="associatedBenefitspanel" style="height:250px;"><h:panelGrid
										id="displayTable"
										binding="#{BenefitComponentHierarchiesBackingBean.displayPanel}">
									</h:panelGrid> <h:panelGroup id="associatedpanel"
										style="height:190px;width:100%;overflow:auto; "
										styleClass="dataTableColumnHeader">
										<h:panelGrid id="panelTable"
											binding="#{BenefitComponentHierarchiesBackingBean.panel}"
											rowClasses="dataTableOddRow,dataTableEvenRow">
										</h:panelGrid>
									</h:panelGroup></div>
									</TD>
								</TR> --%>

								<tr>
									<td>
									<div id="associatedBenefitspanel"
										style="background-color:#FFFFFF;z-index:1;overflow:hidden;">
									<h:panelGrid id="displayTable" style="height:22px"
										binding="#{BenefitComponentHierarchiesBackingBean.displayPanel}">
									</h:panelGrid>
									<%--<DIV style="height: 23px">
									<h:panelGrid id="BenefitHeaderViewPanel" 
										binding="#{BenefitComponentHierarchiesBackingBean.benefitHeaderViewPanel}"
									rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid>
									</DIV> --%> 
									</div>
									</td>
								</TR>	
								<tr>
									<td>
								
									<%--<div id="associatedBenefitspanel"
										style="background-color:#FFFFFF;z-index:1;height:45;overflow:hidden;">
									<h:panelGrid id="displayTable"
										binding="#{BenefitComponentHierarchiesBackingBean.displayPanel}">
									</h:panelGrid>--%>
									<DIV style="height: 20px;" id="benefitHeaderViewPanelDiv">
									<h:panelGrid id="BenefitHeaderViewPanel" 
										binding="#{BenefitComponentHierarchiesBackingBean.benefitHeaderViewPanel}"
									rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid>
									</DIV>
									<%--</div>--%>
									</td>
								</TR>	
																
								<tr>
									<td bordercolor="#cccccc">
									<div id="searchResultdataTableDiv"
										style="height:315px;overflow:auto;">	
										<h:panelGroup id="associatedpanel" 
											
												styleClass="dataTableColumnHeader">
											<h:panelGrid id="panelTable" 
												binding="#{BenefitComponentHierarchiesBackingBean.panel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid>
										</h:panelGroup></div>

									</td>
								</tr>
								<tr>
									<td valign="top">
										<h:commandButton id="deleteButton" 
										onclick="unsavedDataFinder(this.id);return false;" 
										styleClass="wpdButton" value="Delete" tabindex="5"
										></h:commandButton>
									</td>
								</tr>

							</tbody>
						</table>
						<!--	End of Page data	--></fieldset>

						<BR>
						<FIELDSET
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<table border="0" cellspacing="0" cellpadding="3" width="100%">
							<tr>
								<td ><h:selectBooleanCheckbox id="checkall"
									
									value="#{BenefitComponentHierarchiesBackingBean.checkInOpted}" tabindex="6"></h:selectBooleanCheckbox>
									<h:outputText value="Check In" />		
								</td>								
								<td align="right" >
								<table >
									<tr>
										<td ><h:outputText value="State" /></td>
										<td>: <h:outputText
											value="#{benefitComponentCreateBackingBean.state}" /></td>
										<h:inputHidden id="stateHidden"
											value="#{benefitComponentCreateBackingBean.state}" />
									</tr>
									<tr>
										<td ><h:outputText value="Status" /></td>
										<td>: <h:outputText
											value="#{benefitComponentCreateBackingBean.status}" /></td>
										<h:inputHidden id="statusHidden"
											value="#{benefitComponentCreateBackingBean.status}" />
									</tr>
									<tr>
										<td ><h:outputText value="Version" /></td>
										<td>: <h:outputText
											value="#{benefitComponentCreateBackingBean.version}" /></td>
										<h:inputHidden id="versionHidden"
											value="#{benefitComponentCreateBackingBean.version}" />
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</FIELDSET>
						<BR>
						&nbsp;&nbsp;&nbsp;<h:commandButton value="Done"
							styleClass="wpdButton"
							action="#{BenefitComponentHierarchiesBackingBean.done}" tabindex="7"> 
						</h:commandButton></TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="hiddenBenefitComponentId" value="#{BenefitComponentHierarchiesBackingBean.benefitComponentSysId}"></h:inputHidden>
				<h:inputHidden id="createFlag"
					value="#{BenefitComponentHierarchiesBackingBean.createFlag}"></h:inputHidden>
				<h:inputHidden id="benefitHierarchyId"
					value="#{BenefitComponentHierarchiesBackingBean.benefitHierarchyId}"></h:inputHidden>
				<h:commandLink id="hiddenLnk1"
					style="display:none; visibility: hidden;">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="createLink"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentHierarchiesBackingBean.create}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentHierarchiesBackingBean.deleteBenefits}">
					<f:verbatim />
				</h:commandLink>
				<h:inputHidden id="txtBenefits"
					value="#{BenefitComponentHierarchiesBackingBean.benefit}"></h:inputHidden>
				<h:inputHidden id="duplicateData"
					value="#{BenefitComponentHierarchiesBackingBean.duplicateData}"></h:inputHidden>
				<h:inputHidden id="panelData" value="#{BenefitComponentHierarchiesBackingBean.panelData}" />
				<!-- End of hidden fields  -->

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
test();
		copyHiddenToDiv_ewpd('BenefitComponentHierarchiesForm:txtBenefits','benefitDiv',2,2);
	var tableObject = document.getElementById('BenefitComponentHierarchiesForm:panelTable');
	if(tableObject!=null){

	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('BenefitHeaderViewPanel');
	}
	else{
		var divObj = document.getElementById('BenefitHeaderViewPanel');
		if(divObj!=null){
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		}
		var divObj1 = document.getElementById('benefitHeaderViewPanelDiv');
		if(divObj1!=null){
		divObj1.style.visibility = "hidden";
		divObj1.style.height = "2px";
		}
		document.getElementById('BenefitComponentHierarchiesForm:deleteButton').style.visibility = "hidden";
	}
    }else{
		var divObj = document.getElementById('BenefitHeaderViewPanel');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		var divObj1 = document.getElementById('benefitHeaderViewPanelDiv');
		divObj1.style.visibility = "hidden";
		divObj1.style.height = "2px";
		document.getElementById('BenefitComponentHierarchiesForm:deleteButton').style.visibility = "hidden";
    }

var tableObject = document.getElementById('BenefitComponentHierarchiesForm:panelTable');
	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('associatedBenefitspanel');	
				document.getElementById('tableNew').width = "100%";
				var relTblWidth = document.getElementById('tableNew').offsetWidth;
				if(document.getElementById('BenefitComponentHierarchiesForm:panelTable').rows.length < 13){
					document.getElementById('BenefitComponentHierarchiesForm:panelTable').width = relTblWidth+"px";
					document.getElementById('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel').width = relTblWidth+"px";
					setColumnWidth('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel','30%:14%:18%:14%:14%:5%:5%');	
					setColumnWidth('BenefitComponentHierarchiesForm:panelTable','30%:14%:18%:14%:14%:5%:5%');
				}else{
					document.getElementById('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel').width = relTblWidth-17+"px";
					setColumnWidth('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel','30%:14%:18%:14%:14%:5%:5%');	
					setColumnWidth('BenefitComponentHierarchiesForm:panelTable','30%:14%:18%:14%:14%:5%:5%');
				}
//				document.getElementById('defnDiv').style.visibility = 'hidden';	
	}
	else{
		var divObj = document.getElementById('associatedBenefitspanel');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		var divObj1 = document.getElementById('benefitHeaderViewPanelDiv');
		divObj1.style.visibility = "hidden";
		divObj1.style.height = "2px";		
	}
	if(tableObject != null) {	
		setColumnWidth('BenefitComponentHierarchiesForm:BenefitHeaderViewPanel','20.40%:50%:29.60%');	
		setColumnWidth('BenefitComponentHierarchiesForm:panelTable','20.40%:50%:29.60%');	
		}
// Enhancement	
	// To display the notes tab only if the componentType is standard
	hideNotesTab();
	function hideNotesTab(){
	var tab = document.getElementById('BenefitComponentHierarchiesForm:hiddenTabValue').value;	
	// alert('tab:'+ tab);
	if(tab == "Standard Definition")
		bcNotesTab.style.display = '';
	else
		bcNotesTab.style.display = 'none';
	}
// End - Enhancement	
	function deleteBenefitHierarchyAttachedToBenefitComponent(){
		var message = "Are you sure you want to delete the benefit attached to the benefit component?";
		var confirmValue = confirm(message);
		return confirmValue;
	}
	function deleteBenefitsAttachedToBenefitComponent(){
		var message = "Are you sure you want to delete the selected Benefits attached to the BenefitComponent?";
		var confirmValue = confirm(message);
		return confirmValue;
	}
	function test() {
		var chk = false;
//		if(obj.checked) {
//			chk = true;
//		}
		if(document.getElementById('BenefitComponentHierarchiesForm:panelTable') == null ||
			document.getElementById('BenefitComponentHierarchiesForm:panelTable').rows.length <= 0) {
			document.getElementById('BenefitComponentHierarchiesForm:deleteButton').disabled = true;			
		}

		if(document.getElementById('BenefitComponentHierarchiesForm:panelTable') != null ) {
					var rows = document.getElementById('BenefitComponentHierarchiesForm:panelTable').rows;


				if(!chk) {
					for(var i=0; i<rows.length; i++) {
						//alert(rows[i].cells[2].children[0].children[0]);
						//if(rows[i].cells[2].children[0].children[0] != null){
							if(rows[i].cells[2].children[0].children[0].checked) {
								chk = true;
								break;
							}
						//}
					}
				}
				//alert(chk);
				if(chk) {
					document.getElementById('BenefitComponentHierarchiesForm:deleteButton').disabled = false;
				} else {
					document.getElementById('BenefitComponentHierarchiesForm:deleteButton').disabled = true;
				}
          }
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
			for(var j=0;j<columns-1;j++){
				if(null != tableObject.rows[i].cells[j].children[0]){
					dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML); 	
				}
			}
		}
		return dataOnScreen;
	}else
		return dataOnScreen;
}
function unsavedDataFinder(objectId){
	var buttonId = objectId;
	
	
	if(buttonId == 'BenefitComponentHierarchiesForm:createButton'){
		var tableObject = document.getElementById('BenefitComponentHierarchiesForm:panelTable');
		if(tableObject.rows.length == 0){
			submitLink('BenefitComponentHierarchiesForm:createLink');	
		}
		else{
			var panelData = getPanelData('BenefitComponentHierarchiesForm:panelTable');
			if(document.getElementById('BenefitComponentHierarchiesForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('BenefitComponentHierarchiesForm:createLink');	
				}
			}else{
					submitLink('BenefitComponentHierarchiesForm:createLink');	
			}
		}
	}else if(buttonId == 'BenefitComponentHierarchiesForm:deleteButton'){ 
		if(deleteBenefitsAttachedToBenefitComponent()){
			submitLink('BenefitComponentHierarchiesForm:deleteLink');	
		}
	}
}
function loadBenefitPopUp(){
	var entityId = document.getElementById('BenefitComponentHierarchiesForm:hiddenBenefitComponentId').value;
	var queryName = 'benefitSearch';
	var headerName ='Benefits';
	var titleName = 'Benefit Popup';
	ewpdModalWindow_ewpd( '../popups/searchPopup.jsp'+getUrl()+'?entityId='+entityId+'&queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), 'benefitDiv','BenefitComponentHierarchiesForm:txtBenefits',2,2);
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentHierarchyPrint" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value == ''){
		document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
		checkForPanel();
	}
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('BenefitComponentHierarchiesForm:duplicateData').value;
</script>
</HTML>
