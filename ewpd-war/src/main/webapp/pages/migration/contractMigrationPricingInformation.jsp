<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="PRAGMA" content="NO-CACHE">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>WellPoint Product Database: Migration Pricing Information</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js" ></script>
<script language="JavaScript" src="../../js/PopupWindow.js" ></script>
<script language="JavaScript" src="../../js/date.js" ></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
</HEAD>
<f:view>
<BODY onkeypress="return submitOnEnterKey('pricingInfoForm:addPricingInfoButton');">
<hx:scriptCollector id="scriptCollector1">
	<h:inputHidden id="hidden1" value="#{migrationPricingInfoBackingBean.pricingInformationList}"></h:inputHidden>
	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD>
				<jsp:include page="../navigation/top_migration.jsp"></jsp:include>
			</TD>
		</TR>
		<TR>
			<TD>
				<h:form styleClass="form" id="pricingInfoForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
	                		<TD width="273" valign="top" class="leftPanel">
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->						
								<!--	<jsp:include page="migrationTree.jsp"></jsp:include>	-->	
									<jsp:include page ="../migration/migrationWizardTree.jsp">  </jsp:include>	
						 		</DIV>
							</TD>

					<TD colspan="2" valign="top" class="ContentArea">
					<TABLE>
						<TR>
							<TD><w:message value="#{migrationPricingInfoBackingBean.validationMessages}"></w:message>
							</TD>
						</TR>
					</TABLE>
<!-- Table containing Tabs -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
<%--
						<tr>						
							<td width="18%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></td>
									<td class="tabActive">Step 4</td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></td>
								</tr>
							</table>
							</td>							
						</tr>
--%>		

										<tr>
											<td width="100%"><b>Step4 : Pricing Information </b> </td>
										</tr>
										<tr>
											 <td>This screen will display the migrated pricing records and user will have to add more records or delete the existing records.</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>

					</table>
<!-- End of Tab table -->
<!--	Start of Table for actual Data	-->		
<!--					<fieldset
						style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">
-->
					<table width="100%" border="0" cellspacing="0" cellpadding="2">
						<tr>
							<TD width="150" align="left"><h:outputText
								styleClass="#{migrationPricingInfoBackingBean.coverageInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="coverageOutText" value="Coverage * "></h:outputText></TD>

							<td align="left" width="180"><DIV id="CoverageDiv" class="selectDataDisplayDiv"></DIV>
								<h:inputHidden id="coverageHidden" 	value="#{migrationPricingInfoBackingBean.coverage}"></h:inputHidden>
<!--							<SCRIPT>displayWithStyleForContract('pricingInfoForm:coverageHidden','pricingInfoForm:coverage'); </SCRIPT>	--></td>
							<td align="left"><img
								src="../../../images/select.gif"
								onclick="coverageInfo(); return false;"

								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>

						<tr>
							<td width="150" align="left"><h:outputText
								styleClass="#{migrationPricingInfoBackingBean.pricingInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="pricingOutText" value="Pricing * "></h:outputText></td>
							<td align="left" width="180"><DIV id="PricingDiv" class="selectDataDisplayDiv"></DIV>
							<h:inputHidden id="pricingHidden" 	value="#{migrationPricingInfoBackingBean.pricing}"></h:inputHidden>
							</td>
							<td align="left"><img
								src="../../../images/select.gif"
								onclick="pricingInfo();return false;"
								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>
						<tr>
							<td width="150" align="left"><h:outputText
								styleClass="#{migrationPricingInfoBackingBean.networkInvalid ? 'mandatoryError': 'mandatoryNormal'}"
								id="networkOutText" value="Network * "></h:outputText></td>
							<td align="left" width="180"><DIV id="NetworkDiv" class="selectDataDisplayDiv"></DIV>
							<h:inputHidden id="networkHidden" 	 value="#{migrationPricingInfoBackingBean.network}"></h:inputHidden>
							</td>
							<td align="left"><img
								src="../../../images/select.gif"
								onclick="networkInfo();return false;"
								alt="Select" width="15" height="15" style="cursor:hand;" /></td>
						</tr>
						<tr></tr>
						<tr></tr>
						<tr></tr>
						<tr>
							<td colspan="3" valign="top"><h:commandButton value="Save"
								onmousedown="javascript:savePageAction(this.id);"
								styleClass="wpdbutton" id="addPricingInfoButton"
								action="#{migrationPricingInfoBackingBean.savePricingInfo}"
								disabled=""></h:commandButton></td>
						</tr>
						<tr>
						</tr>
					</table>
<!--
					</fieldset>
-->
					<br/>
					<fieldset style="margin-left:6px;margin-right:-6px;width:100%">
					<div id="panel2">
						<div id="resultInfo" class="dataTableColumnHeader">
						<br/>No Pricing Information is Available.
					</div>

					<div id="resultHeaderDiv">
						<div id="resultHeaderTableInfo" class="dataTableColumnHeaderInfo" style="width:100%">
							Associated Pricing Records
						</div>
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="0" id="resultHeaderTable" border="0" >
							<TBODY>

								<TR class="dataTableColumnHeader">
									<td align="center" >
												<h:outputText styleClass="formOutputColumnField" value="Coverage" ></h:outputText> 
									</td>
									<td  align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Pricing" ></h:outputText>
									</td >
									<td align="center" >
	              								<h:outputText styleClass="formOutputColumnField" value="Network"></h:outputText>
									</td>
									<td >
										&nbsp;
									</td>
								</TR>
							</TBODY>
						</TABLE>
					</div>
<%--
<h:inputHidden id="loadPricingInfoList" value= "#{migrationPricingInfoBackingBean.loadPrisingInfoList}"/> 
		--%>		
					<div id="panel2Content" style="height:106px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
					<table cellpadding="0" cellspacing="0" border="0">
					<tr>
					<td align="left">
							
							<h:dataTable headerClass="tableHeader" id="resultsTable" border="0"
								value="#{migrationPricingInfoBackingBean.pricingInformationList}"
								rendered="#{migrationPricingInfoBackingBean.renderFlag}" var="eachRow" 
								 cellpadding="0" cellspacing="1" bgcolor="#cccccc" 
								rowClasses="dataTableEvenRow,dataTableOddRow">
								
								<h:column>
									<h:inputHidden id="contractID" value="#{eachRow.contractDateSegmentSysId}"></h:inputHidden>	
									<h:outputText id="coverageInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.coverage}"></h:outputText>
									<h:inputHidden id="coverageInfoID" value="#{eachRow.coverage}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="pricingInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.pricing}"></h:outputText>
									<h:inputHidden id="pricingInfoID" value="#{eachRow.pricing}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:outputText id="networkInfoDesc" styleClass="formOutputColumnField" value="#{eachRow.network}"></h:outputText>
									<h:inputHidden id="networkInfoID" value="#{eachRow.network}"></h:inputHidden>
								</h:column>
								<h:column>
									<h:commandButton alt="Delete" id="deleteButton"
										image="../../images/delete.gif"
										onclick="getPricingInfoForDelete();return false;">
									</h:commandButton>
									<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								</h:column>
							</h:dataTable>
						</td>
					</tr>
					</table>

					</div>
					</div>

					</fieldset>
						
				<br/>
<br>
				<TABLE>
					<TBODY>
							<TR>
								<TD>
									<h:commandButton styleClass="wpdbutton" id="pricingId" value="Back"  onclick="clickBack();return false;"
									 > </h:commandButton>
									<h:commandLink id="previousPageLink"
									style="display:none; visibility: hidden;"
									action="#{migrationPricingInfoBackingBean.back}">
									<f:verbatim />
									</h:commandLink>
								</TD>
								<TD> &nbsp;</TD>
								<TD>
									<h:commandButton styleClass="wpdbutton" value="Next"  id="nextLink" onclick="clickNext();return false;" > </h:commandButton>
									<h:commandLink id="nextPageLink"
									style="display:none; visibility: hidden;"
									action="#{migrationPricingInfoBackingBean.next}">
									<f:verbatim />
									</h:commandLink>
								</TD>
								<TD> &nbsp;</TD>
								<TD>
									<h:commandButton styleClass="wpdbutton" value="Cancel"  onclick="deleteContract();return false;" > </h:commandButton>
								</TD>
			<h:commandLink id="cancelMigrationLink"
				style="display:none; visibility: hidden;"
				action="#{migrationGeneralInfoBackingBean.cancelMigration}">
				<f:verbatim />
			</h:commandLink>
							</TR>
					</TBODY>
				</TABLE>
<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
				<TABLE>
					<TBODY>
							<TR>
								<TD>
									<h:commandButton styleClass="wpdbutton" value="Done"  onclick="clickDone();return false;"> </h:commandButton>
									<h:commandLink id="doneLink"
									style="display:none; visibility: hidden;"
									action="#{migrationPricingInfoBackingBean.done}">
									<f:verbatim />
									</h:commandLink>
								</TD>
							</TR>
					</TBODY>
				</TABLE>

			<h:commandLink id="deleteHidden"
			style="display:none; visibility: hidden;"
			>
		</h:commandLink>
	<table>
		<tr>
			<h:inputHidden id="selectedContractDSSysID" value="#{migrationPricingInfoBackingBean.selectedMigratedDSSysID}"></h:inputHidden>
			<h:inputHidden id="selectedCoverageID" value="#{migrationPricingInfoBackingBean.selectedCoverageID}"></h:inputHidden>
			<h:inputHidden id="selectedPricingID" value="#{migrationPricingInfoBackingBean.selectedPricingID}"></h:inputHidden>
			<h:inputHidden id="selectedNetworkID" value="#{migrationPricingInfoBackingBean.selectedNetworkID}"></h:inputHidden>
			<h:inputHidden id="dateSegmentID" value="#{migrationPricingInfoBackingBean.migrationSysId}"></h:inputHidden>
			<h:commandLink id="deleteButton1" action="#{migrationPricingInfoBackingBean.removePricingInfo}" style="display:none; visibility: hidden;"><f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink> 
			<h:inputHidden id="duplicateData" value="#{migrationPricingInfoBackingBean.duplicateData}"></h:inputHidden>

		</tr>
	</table>
</TABLE>

		</h:form>
		</td>			
		</tr>
		<tr> 
			<td>				
				<%@ include file ="../navigation/pageFooter.jsp" %>
			</td>			
		</tr>	
 	</TABLE>
</hx:scriptCollector></body>
</f:view>

<script language="JavaScript">
IGNORED_FIELD1 = 'pricingInfoForm:duplicateData' ;
function deleteContract(){

			var message = "Are you sure you want to cancel? All data saved during this migration will be lost"
		if(window.confirm(message)){
			
				submitLink('pricingInfoForm:cancelMigrationLink');
		
		}else{
				return false;
		}
}
	hideResultDiv();
 copyHiddenToDiv_ewpd('pricingInfoForm:coverageHidden','CoverageDiv',2,2); 
 copyHiddenToDiv_ewpd('pricingInfoForm:pricingHidden','PricingDiv',2,2); 
 copyHiddenToDiv_ewpd('pricingInfoForm:networkHidden','NetworkDiv',2,2); 
	function hideResultDiv() {
		var divObj = document.getElementById('resultInfo');
		var divHeaderObj = document.getElementById('panel2Content');
		var divResultHeaderObj = document.getElementById('resultHeaderDiv');
		var divNoResult = document.getElementById('panel2');
		var tableObj = document.getElementById('pricingInfoForm:resultsTable');
		if (tableObj != null) {
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else{
			divHeaderObj.style.visibility = 'hidden';
			divResultHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divResultHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';
			divResultHeaderObj.style.position = 'absolute';
		}
	}
		function getPricingInfoForDelete(){
			var message = "Are you sure you want to delete?"
			if(window.confirm(message)){
				getFromDataTableToHidden('pricingInfoForm:resultsTable','coverageInfoID','pricingInfoForm:selectedCoverageID');
				getFromDataTableToHidden('pricingInfoForm:resultsTable','pricingInfoID','pricingInfoForm:selectedPricingID');
				getFromDataTableToHidden('pricingInfoForm:resultsTable','networkInfoID','pricingInfoForm:selectedNetworkID');
				submitDataTableButton('pricingInfoForm:resultsTable','contractID','pricingInfoForm:selectedContractDSSysID', 'pricingInfoForm:deleteButton1');
			}
			else{
				return false;
			}
		}

		if(document.getElementById('pricingInfoForm:resultsTable') != null) {
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('pricingInfoForm:resultsTable').offsetHeight <= 106) {
				document.getElementById('pricingInfoForm:resultsTable').width = relTblWidth+"px";
				setColumnWidth('pricingInfoForm:resultsTable','32%:32%:32%:4%');
				setColumnWidth('resultHeaderTable','32%:32%:32%:4%');
			}else{
				document.getElementById('pricingInfoForm:resultsTable').width = (relTblWidth-17)+"px";
				setColumnWidth('pricingInfoForm:resultsTable','32%:32%:32%:4%');
				setColumnWidth('resultHeaderTable','31.3%:31.3%:31.3%:6.1%');
			}
		}
function coverageInfo(){
	var contractID = document.getElementById('pricingInfoForm:dateSegmentID').value;
	ewpdModalWindow_ewpd('../contractpopups/singleSelectCoveragePopup.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'Coverage'+'&entityId='+ contractID + '&entityType=' + 'migration','CoverageDiv','pricingInfoForm:coverageHidden',2,2); 

}
function pricingInfo(){
	var contractID = document.getElementById('pricingInfoForm:dateSegmentID').value;
	ewpdModalWindow_ewpd('../contractpopups/singleSelectPricingPopup.jsp'+getUrl()+'?lookUpAction='+3+'&parentCatalog='+'Pricing'+'&entityId='+ contractID + '&entityType=' + 'migration','PricingDiv','pricingInfoForm:pricingHidden',2,2);
}
function networkInfo(){
	var contractID = document.getElementById('pricingInfoForm:dateSegmentID').value;
	ewpdModalWindow_ewpd('../contractpopups/singleSelectNetworkPopup.jsp'+getUrl()+'?lookUpAction='+'3'+'&parentCatalog='+'Network'+'&entityId='+contractID+'&entityType='+'migration','NetworkDiv','pricingInfoForm:networkHidden',2,2);
}
function clickBack(){
navigatePageActionSubmit('pricingInfoForm:previousPageLink');
}
function clickNext(){
navigatePageActionSubmit('pricingInfoForm:nextPageLink');
}
function clickDone(){
navigatePageActionSubmit('pricingInfoForm:doneLink');
}

/*
		if((navigatePageAction('pricingInfoForm:previousPageLink'))==true){
			document.getElementById('pricingInfoForm:previousPageLink').onclick();
		}	
*/		

</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
//	alert(document.getElementById('pricingInfoForm:duplicateData').value == document.ewpdDataChangedForm.ewpdOnloadData.value);
//	alert('DUP ->'+document.getElementById('pricingInfoForm:duplicateData').value + 'CURR ->' + document.ewpdDataChangedForm.ewpdOnloadData.value);
//	if(document.getElementById('pricingInfoForm:duplicateData').value == ''){
//		document.getElementById('pricingInfoForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
//	}
//	else{
//		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('pricingInfoForm:duplicateData').value;
//	}
</script>
</HTML>

