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

<TITLE>Attached Benefits</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="contractBenefitForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD width="348" valign="top" class="leftPanel">
							<DIV class="treeDiv"><jsp:include page="contractTree.jsp"></jsp:include>
							</DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message
											value="#{contractBenefitComponentNotesBackingBean.validationMessages}"></w:message>

										</TD>
									</TR>
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
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td width="186" class="tabNormal"><h:commandLink
												action="#{contractComponentGeneralInfoBackingBean.retrieveBenefitComponent}"
												onmousedown="javascript:navigatePageAction(this.id);"
												id="thisId">
												<h:outputText value="General Information" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="200">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
											<td width="186" class="tabActive"><h:commandLink>
												<h:outputText value="Benefits" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/activeTabRight.gif" width="2" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="200" id ="tabForStandard">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21" /></td>
											<td width="186" class="tabNormal"><h:commandLink
												action="#{contractBenefitComponentNotesBackingBean.loadNotes}"
												onmousedown="javascript:navigatePageAction(this.id);" id ="notes">
												<h:outputText value="Notes" />
											</h:commandLink></td>
											<td width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></td>
										</tr>
									</table>
									</td>
									<td width="100%"></td>
								</tr>
							</table>
							<!-- End of Tab table -->
								<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						<TABLE border="0" cellspacing="0" cellpadding="3"
							class="outputText" width ="100%">
							<TBODY>
								<TR>
								<TR>
									<TD colspan="5">
							<h:inputHidden id="listGenerator"
								binding="#{contractComponentGeneralInfoBackingBean.list}"></h:inputHidden>
							<h:inputHidden id="panelRenderer"
								binding="#{contractComponentGeneralInfoBackingBean.renderer}"></h:inputHidden>
							
							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="0" width="100%" >

								<TR>
							
									
								</TR>

								<TR>

										


									<TD>

										<div id="LabelHeaderDiv"
											style="background-color:#cccccc;z-index:1;overflow:auto;height:20px;">
										<B> <h:outputText value=" Associated Benefits"></h:outputText> </B>
										</div>

									<div id="displayHeaderDiv"
										style="background-color:#ffffff;z-index:1;"><h:panelGrid
										id="displayHeaderTable" 
										binding="#{contractComponentGeneralInfoBackingBean.headerPanelForBenefit}"
										rowClasses="dataTableOddRow,dataTableEvenRow">
									</h:panelGrid></div>
								
									<div id="displayPanelContent12"
										style="position:relative;overflow:auto;height:244px;width:100%">
									<h:panelGrid id="panelTable" width="100%"
										binding="#{contractComponentGeneralInfoBackingBean.panelForBenefit}"
										>
									</h:panelGrid></div>
									</TD>
								</TR>
								<TR><TD>&nbsp;
									<br><h:commandButton id="hideButton" value="Save" onmousedown="javascript:savePageAction(this.id);" rendered ="#{contractComponentGeneralInfoBackingBean.viewSave}"styleClass="wpdButton" action="#{contractComponentGeneralInfoBackingBean.updateBenefits}"> </h:commandButton>
									</TD>
								</TR>
								<TR><TD>&nbsp;</TD>
								</TR>

							</TABLE>
							
						
					
		</TD>
		</TR>
		</TBODY>
		</TABLE>
				</FIELDSET>
							<BR>


							<DIV id="dummyDiv" style="visibility:hidden"></DIV>

							<!--	End of Page data	--> <!-- Space for hidden fields --> 
						<h:inputHidden id="panelData"  />
							<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>
							<h:inputHidden id="states"
					value="#{contractComponentGeneralInfoBackingBean.flasgStatus}"></h:inputHidden>
					<h:inputHidden id="duplicateData"
					value="#{contractComponentGeneralInfoBackingBean.duplicateData}"></h:inputHidden>
							<h:commandLink
								id="selectedShowHiddenLink"
								style="display:none; visibility: hidden;"
								action="#{contractComponentGeneralInfoBackingBean.loadWithHiddenValuesForBenefits}">
								<f:verbatim />
							</h:commandLink> <h:commandLink id="unselectedShowHiddenLink"
								style="display:none; visibility: hidden;"
								action="#{contractComponentGeneralInfoBackingBean.loadWithoutHiddenValuesForBenefits}">
								<f:verbatim />
							</h:commandLink> <!-- End of hidden fields  --></TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractBenefitCustomizationPrint" /></form>
<script language="JavaScript">	
IGNORED_FIELD1='contractBenefitForm:duplicateData';


setColumnWidth('contractBenefitForm:displayHeaderTable','70%:30%');	
setColumnWidth('contractBenefitForm:panelTable','70%:30%');


		
			var relTblWidth = document.getElementById('contractBenefitForm:displayHeaderTable').offsetWidth;
			if(document.getElementById('contractBenefitForm:panelTable').offsetHeight <= 100) {
				document.getElementById('contractBenefitForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('contractBenefitForm:displayHeaderTable','70%:30%');	
				setColumnWidth('contractBenefitForm:panelTable','70%:30%');
				
			}else{
				var relTblWidth = document.getElementById('contractBenefitForm:panelTable').offsetWidth;
				document.getElementById('contractBenefitForm:displayHeaderTable').width = document.getElementById('contractBenefitForm:panelTable').offsetWidth;
				setColumnWidth('contractBenefitForm:displayHeaderTable','70%:30%');	
				setColumnWidth('contractBenefitForm:panelTable','70%:30%');
				
			}

	i = document.getElementById("contractBenefitForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';

	}else{
	tabForStandard.style.display='';

	}

checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('contractBenefitForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('contractBenefitForm:panelTable');
		document.getElementById('contractBenefitForm:panelData').value = onLoadPanelData;
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
	var panelData = getPanelData('contractBenefitForm:panelTable');
	if(document.getElementById('contractBenefitForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			goToAction();
		}
		else{
			var select =  document.getElementById('contractBenefitForm:showHidden');
			if(select.checked){
				document.getElementById('contractBenefitForm:showHidden').checked=false;
			}else{
				document.getElementById('contractBenefitForm:showHidden').checked=true;
			}
		}
	}
	else{
		goToAction();
	}
}
function goToAction(){
	var select =  document.getElementById('contractBenefitForm:showHidden')
	if(select.checked){
		submitLink('contractBenefitForm:selectedShowHiddenLink');
	}else{
		submitLink('contractBenefitForm:unselectedShowHiddenLink');
	}
		
}
</script>

<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('contractBenefitForm:duplicateData').value == '')
		document.getElementById('contractBenefitForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('contractBenefitForm:duplicateData').value;
</script>
</HTML>

