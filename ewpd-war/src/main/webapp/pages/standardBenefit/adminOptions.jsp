
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
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionsForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv"><jsp:include
								page="../standardBenefit/standardBenefitTree.jsp"></jsp:include></DIV>

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
												value="Admin Options" /></TD>
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
												id="loadAdminMethod" 
												onmousedown="javascript:navigatePageAction(this.id);"
												action="#{adminMethodBackingBean.loadForBenefit}">
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

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

							<!--	Start of Table for actual Data	-->
							<TABLE border="0" cellspacing="0" cellpadding="3"
								class="outputText">
								<TBODY>
									<TR>
										<TD colspan="2"></TD>
									</TR>
									<TR>
										<TD colspan="2">&nbsp;</TD>
									</TR>
									<TR>
										<TD width="200" valign="top">&nbsp;<h:outputText
											styleClass="mandatoryNormal" id="lblBenefitAdmin"
											value="Administration Period" /></TD>
										<TD width="201" align="left"><h:outputText
											styleClass="mandatoryNormal" id="benefitAdmin"
											value="#{adminOptionsBackingBean.benefitAdminName}" /></TD>
									</TR>
									<TR>
										<TD width="200" valign="top">&nbsp;<h:outputText
											styleClass="#{adminOptionsBackingBean.requiredAdminLevel ? 'mandatoryError' : 'mandatoryNormal'}"
											id="lblAdminLevel" value="Administration Level*" /></TD>
										<TD width="201" align="left"><h:selectOneMenu
											styleClass="formInputField" id="selectAdminLevel"
											value="#{adminOptionsBackingBean.administrationLevel}"
											onchange="changeAdminLevel();" tabindex="1">
											<f:selectItems id="administrationLevel"
												value="#{ReferenceDataBackingBeanCommon.administrationList}" />
										</h:selectOneMenu></TD>
									</TR>

									<TR id="rowBenefitLevel">
										<TD width="200" valign="top">&nbsp;<h:outputText
											styleClass="#{adminOptionsBackingBean.requiredBenefitLevel ? 'mandatoryError' : 'mandatoryNormal'}"
											id="lblBenefitLevel" value="Benefit Level*" /></TD>
										<TD width="201" align="left">
										<DIV id="BenefitLevelPopupDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="hidden_BenefitLevel"
											value="#{adminOptionsBackingBean.benefitLevel}" /></TD>

										<TD align="left" valign="top" width="38"><h:commandButton
											id="butBenefitLevel" image="../../images/select.gif"
											style="cursor:hand;" alt="Select"
											onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/benefitLevelPopup.jsp'+getUrl()+'?'+ 'temp=' + Math.random(),
											'BenefitLevelPopupDiv', 'adminOptionsForm:hidden_BenefitLevel',2,2,'AdminOptionPopupDiv', 'adminOptionsForm:hidden_adminOptions');return false;"
											tabindex="2">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD width="200" valign="top">&nbsp;<h:outputText
											styleClass="#{adminOptionsBackingBean.requiredAdminOption ? 'mandatoryError' : 'mandatoryNormal'}"
											id="lblAdminOption" value="Admin Option*" /></TD>
										<TD>
										<DIV id="AdminOptionPopupDiv" class="selectDataDisplayDiv"></DIV>
										<h:inputHidden id="hidden_adminOptions"
											value="#{adminOptionsBackingBean.adminOption}" /></TD>

										<TD width="201" align="left"><h:commandButton
											id="butCreateAdminOption" alt="Select"
											image="../../images/select.gif"
											onclick="checkIfBenefitLevelAvailable('adminOptionsForm:hidden_BenefitLevel');return false;"
											tabindex="3">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD height="37" width="140">&nbsp;<h:commandButton
											value="Save" styleClass="wpdButton"
											action="#{adminOptionsBackingBean.saveAndAdd}" tabindex="4">
										</h:commandButton></TD>
										<!-- <TD><h:commandButton id="saveAdminOptions"
											styleClass="wpdbutton"
											action="#{adminOptionsBackingBean.saveAdminOptions}"
											value="Save" tabindex="5">
										</h:commandButton></TD> -->
									</TR>
								</TBODY>
							</TABLE>


							
							<TABLE id="displayAdmin" width="100%" cellspacing="0" cellpadding="0">
								<%-- --%>
								<tr>
									<td>
									<div id="associatedBenefitspanel"
										style="background-color:#FFFFFF;z-index:1;overflow:hidden;height:20;">
									<h:panelGrid id="displayTable"
										binding="#{adminOptionsBackingBean.displayPanel}">
									</h:panelGrid>									
									</div>
									</td>
								</TR>	

								<tr>
									<td>									
									<DIV id="benefitHeaderViewPanelDiv">
									<h:panelGrid id="BenefitHeaderViewPanel" 
										binding="#{adminOptionsBackingBean.adminOptionHeaderViewPanel}"
									rowClasses="dataTableOddRow">
									</h:panelGrid>
									</DIV>
									<%--</div>--%>
									</td>
								</TR>	
								<tr>
									<td bordercolor="#cccccc">
									<div id="searchResultdataTableDiv"
										style="height:320px; overflow: auto;">	
											<h:panelGrid id="panelTable" 
												binding="#{adminOptionsBackingBean.adminOptionPanel}"
												rowClasses="dataTableEvenRow,dataTableOddRow">
											</h:panelGrid></div>
	
									</td>
								</tr>
								<tr>
									<td valign="top">
										<h:commandButton id="deleteButton" action="#{adminOptionsBackingBean.deleteAdminOptions}" styleClass="wpdButton" value="Delete" onclick="return deleteAdminOptionAttachedToBenefit();" tabindex="5"></h:commandButton>
									</td>
								</tr>
								<tr>
									<td valign="top">
										&nbsp;
									</td>
								</tr>
								<%-- <TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable1" border="0" width="100%">
										<TR>
											<TD><h:outputText value="Associated Admin Options"></h:outputText>
											</TD>
										</TR>
									</TABLE>
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left" width="25%"><h:outputText value="Name "></h:outputText>
												</TD>
												<TD align="left" width="25%"><h:outputText
													value="Admin Level "></h:outputText></TD>
												<TD align="left" width="25%"><h:outputText
													value="Benefit Level "></h:outputText></TD>
												<TD width="25%">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR> 
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:200px;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc" rendered="true"
										value="#{adminOptionsBackingBean.associatedAdministrationList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0">

										<h:column>
											<h:inputHidden id="adminOptionIdHidden"
												value="#{singleValue.adminLevelOptionAssnSystemId}"></h:inputHidden>
											<h:outputText id="adminOptionName"
												value="#{singleValue.adminOptionDesc}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="adminLevelName"
												value="#{singleValue.adminLevelDesc}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:inputHidden id="benefitLevelIdOnPageLoad"
												value="#{singleValue.benefitLevelSysIdFromMaster}" />
											<h:outputText id="benefitLevelName"
												value="#{singleValue.benefitLevelDesc}">
											</h:outputText>
										</h:column>
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<!-- start: for editing the selected details -->
											<h:commandButton alt="Edit" id="editButton"
												image="../../images/edit.gif"
												onclick="submitDataTableButton('adminOptionsForm:searchResultTable', 'adminOptionIdHidden', 'adminOptionsForm:targetHiddenToStoreMasterKey', 'adminOptionsForm:editBenefitDefinition'); return false;"></h:commandButton>
											<!-- end: for editing the selected details -->
											<h:commandButton alt="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick="confirmDelete();return false;"></h:commandButton>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>  --%>
								
								
							</TABLE>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>

					<!-- Space for hidden fields -->
					<h:inputHidden id="adminOptionIdForUpdate"
						value="#{adminOptionsBackingBean.adminOptionIdForUpdate}"></h:inputHidden>
					<h:inputHidden id="administrationLevelId"
						value="#{adminOptionsBackingBean.administrationLevelId}"></h:inputHidden>
					<h:inputHidden id="benefitLevelId"
						value="#{adminOptionsBackingBean.benefitLevelIdForUpdate}"></h:inputHidden>
					<h:inputHidden id="adminLevelOptionAssnSystemId"
						value="#{adminOptionsBackingBean.adminLevelOptionAssnSystemId}"></h:inputHidden>
					<h:inputHidden id="targetHiddenToStoreMasterKey"
						value="#{adminOptionsBackingBean.adminLevelOptionAssnSystemId}"></h:inputHidden>
					<h:inputHidden id="masterKeyUsedForUpdate"
						value="#{adminOptionsBackingBean.masterKeyUsedForUpdate}"></h:inputHidden>
					<h:commandLink id="editBenefitDefinition"
						style="display:none; visibility: hidden;"
						action="#{adminOptionsBackingBean.retrieveAdminOptions}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="deleteBenefitDefinition"
						style="display:none; visibility: hidden;"
						action="#{adminOptionsBackingBean.deleteAdminOptions}">
						<f:verbatim />
					</h:commandLink>
					<h:inputHidden id="hiddenFieldToIdentifyIfEditIsClicked"
						value="#{adminOptionsBackingBean.editButtonIsClicked}"></h:inputHidden>
					<h:inputHidden id="duplicateData"
					value="#{adminOptionsBackingBean.duplicateData}"></h:inputHidden>
					<h:inputHidden id="benefitAdminSystemIdHidden"
					value="#{adminOptionsBackingBean.benefitAdminSystemIdHidden}"></h:inputHidden>  
					<!-- End of hidden fields  -->

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>



<script language="javascript">
IGNORED_FIELD1 ='adminOptionsForm:duplicateData';
	copyHiddenToDiv_ewpd('adminOptionsForm:hidden_BenefitLevel', 'BenefitLevelPopupDiv', 2, 2);	
	copyHiddenToDiv_ewpd('adminOptionsForm:hidden_adminOptions', 'AdminOptionPopupDiv', 2, 2);
	test();
	var tableObject = document.getElementById('adminOptionsForm:panelTable');
	if(tableObject.rows.length > 0){
	 	var divObj = document.getElementById('associatedBenefitspanel');
	}
	else{
		var divObj = document.getElementById('associatedBenefitspanel');
		divObj.style.visibility = "hidden";
		divObj.style.height = "2px";
		var divObj1 = document.getElementById('benefitHeaderViewPanelDiv');
		divObj1.style.visibility = "hidden";
		divObj1.style.height = "2px";
		document.getElementById('adminOptionsForm:deleteButton').style.visibility = "hidden";
	}
	if(tableObject != null) {
		var tblWidth = document.getElementById('displayAdmin').offsetWidth;
		var rowlength = document.getElementById('adminOptionsForm:panelTable').rows.length;
		if(rowlength>11){
			document.getElementById('adminOptionsForm:BenefitHeaderViewPanel').width = tblWidth-17+"px";
			setColumnWidth('adminOptionsForm:BenefitHeaderViewPanel','15%:30%:17%:17%:10%:11%');	
		setColumnWidth('adminOptionsForm:panelTable','15%:30%:17%:17%:10%:11%');
		}else{
		//setColumnWidth('adminOptionsForm:BenefitHeaderViewPanel','20.40%:50%:29.60%');	
		//setColumnWidth('adminOptionsForm:panelTable','20.40%:50%:29.60%');
		setColumnWidth('adminOptionsForm:BenefitHeaderViewPanel','15%:30%:17%:17%:10%:11%');	
		setColumnWidth('adminOptionsForm:panelTable','15%:30%:17%:17%:10%:11%');
		}	
		}

	//if(document.getElementById('adminOptionsForm:searchResultTable')!= null){		
	//	setColumnWidth('adminOptionsForm:searchResultTable', '25%:25%:25%:25%');
		// syncTables('resultHeaderTable', 'adminOptionsForm:searchResultTable'); 
	//}
	checkBenefitLevel();
	function checkBenefitLevel() {
		if( document.getElementById("adminOptionsForm:selectAdminLevel").value == '2') {
			document.getElementById("rowBenefitLevel").style.display='none';
			var benefitlevel = document.getElementById("adminOptionsForm:hidden_BenefitLevel");
			benefitlevel.value =''; 
		//	document.getElementById('adminOptionsForm:hidden_adminOptions').value = '';
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_BenefitLevel', 'BenefitLevelPopupDiv', 2, 2);	
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_adminOptions', 'AdminOptionPopupDiv', 2, 2);	
		 } 
		 else {
			document.getElementById("rowBenefitLevel").style.display='';	
//			document.getElementById('AdminOptionPopupDiv').innerHTML = '';
//			document.getElementById('adminOptionsForm:hidden_adminOptions').value = '';
			var benefitlevel = document.getElementById("adminOptionsForm:hidden_BenefitLevel");
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_BenefitLevel', 'BenefitLevelPopupDiv', 2, 2);	
//			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_adminOptions', 'AdminOptionPopupDiv', 2, 2);
		 }
	}

function changeAdminLevel(){
if( document.getElementById("adminOptionsForm:selectAdminLevel").value == '2') {
			document.getElementById("rowBenefitLevel").style.display='none';
			var benefitlevel = document.getElementById("adminOptionsForm:hidden_BenefitLevel");
			benefitlevel.value =''; 
			document.getElementById('adminOptionsForm:hidden_adminOptions').value = '';
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_BenefitLevel', 'BenefitLevelPopupDiv', 2, 2);	
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_adminOptions', 'AdminOptionPopupDiv', 2, 2);	
		 } 
		 else {
			document.getElementById("rowBenefitLevel").style.display='';	
			document.getElementById('AdminOptionPopupDiv').innerHTML = '';
			document.getElementById('adminOptionsForm:hidden_adminOptions').value = '';
			var benefitlevel = document.getElementById("adminOptionsForm:hidden_BenefitLevel");
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_BenefitLevel', 'BenefitLevelPopupDiv', 2, 2);	
			copyHiddenToDiv_ewpd('adminOptionsForm:hidden_adminOptions', 'AdminOptionPopupDiv', 2, 2);
		 }

}
	function confirmDelete(){
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
			submitDataTableButton('adminOptionsForm:searchResultTable', 'adminOptionIdHidden', 'adminOptionsForm:targetHiddenToStoreMasterKey', 'adminOptionsForm:deleteBenefitDefinition')
		}
		else{
			return false;
		}
	}
	//hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		hiddenIdObj = document.getElementById('adminOptionsForm:searchResultTable:0:adminOptionIdHidden');
		if(hiddenIdObj == null){
			document.getElementById(divId).style.visibility = 'hidden';
		}else{
			document.getElementById(divId).style.visibility = 'visible';
		}
	}
	function checkIfBenefitLevelAvailable(benefitLevelId){
		var benefitLevelIdObj = document.getElementById(benefitLevelId).value;
		var adminLevel = document.getElementById("adminOptionsForm:selectAdminLevel").value;	

		if(adminLevel==1){
				if( benefitLevelIdObj == ''){
					alert("Please select a Benefit Level.");
				}
				else{
					var benefitAdminSysId = document.getElementById('adminOptionsForm:benefitAdminSystemIdHidden').value;
					var titleName = 'Admin Option Popup';
					ewpdModalWindow_ewpd('../popups/searchPopup.jsp'+getUrl()+'?entityId='+benefitAdminSysId+'&queryName=getAdminOptionListForBenefitLevel&headerName=Admin Option&benefitLvlId='+benefitLevelIdObj+'&titleName='+titleName+'&temp='+Math.random(),'AdminOptionPopupDiv','adminOptionsForm:hidden_adminOptions',2,2);
		            return false;
				}
		}else{
				var titleName = 'Admin Option Popup';
				var benefitAdminSysId = document.getElementById('adminOptionsForm:benefitAdminSystemIdHidden').value;
				ewpdModalWindow_ewpd('../popups/searchPopup.jsp'+getUrl()+'?entityId='+benefitAdminSysId+'&queryName=getAdminOption&headerName=Admin Option&titleName='+titleName+'&temp='+Math.random(),'AdminOptionPopupDiv','adminOptionsForm:hidden_adminOptions',2,2);
				return false;
}
}
	function deleteAdminOptionAttachedToBenefit(){
		var message = "Are you sure you want to delete the selected admin option attached to the benefit?";
		var confirmValue = confirm(message);
		return confirmValue;
	}

	function test() {
		var chk = false;
//		if(obj.checked) {
//			chk = true;
//		}
		if(document.getElementById('adminOptionsForm:panelTable') == null ||
			document.getElementById('adminOptionsForm:panelTable').rows.length <= 0) {
			document.getElementById('adminOptionsForm:deleteButton').disabled = true;			
		}
		
		var rows = document.getElementById('adminOptionsForm:panelTable').rows;
		if(!chk) {
			for(var i=0; i<rows.length; i++) {
				if(rows[i].cells[5].children[0].checked) {
					chk = true;
					break;
				}
			}
		}

		if(chk) {
			document.getElementById('adminOptionsForm:deleteButton').disabled = false;
		} else {
			document.getElementById('adminOptionsForm:deleteButton').disabled = true;
		}
	}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="adminOptions" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('adminOptionsForm:duplicateData').value == '')
		document.getElementById('adminOptionsForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('adminOptionsForm:duplicateData').value;

</script>
</HTML>
