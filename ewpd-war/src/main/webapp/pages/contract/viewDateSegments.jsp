<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
<script language="JavaScript" src="../../js/prototype.js"></script>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<BASE target="_self" />
<TITLE>View DateSegments</TITLE>
</HEAD>
<f:view>
	<BODY>
	<h:form id="viewAllVersionForm">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
						<h:inputHidden id="breadCrumb"
							value="#{dateSegmentBackingBean.breadCrumb}" />
			<jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%"><w:message></w:message>
				<DIV id="panel2">
				<div id="panel2Header" class="tabTitleBar" style="width: 942;margin-left: 8"><b>Available Date
				segments</b></div>
				<DIV id="panel2Content" class="tabContentBox"
					style="position:relative;font-size:10px;width:100%;"><BR>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="930px">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="140px"><strong><h:outputText
										value="ContractId"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="Version"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Effective Date"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Expiry Date"></h:outputText></strong></td>
									<td align="left" width="140px"><strong><h:outputText
										value="Contract Type"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="Contract Status"></h:outputText></strong></td>
									<td align="left" width="95px"><strong><h:outputText
										value="DateSegment Type"></h:outputText></strong></td>
									<td align="left" width="105px">&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>

						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:460; overflow:auto;position:relative;z-index:1;font-size:10px;width:950px;">
						<h:dataTable headerClass="dataTableHeader"
							id="previousVersionsTable" var="singleValue" cellpadding="3"
							cellspacing="1" bgcolor="#cccccc" rendered="true"
							value="#{dateSegmentBackingBean.resultList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="930px">
							<h:column>
								<h:outputText id="ContractId" value="#{singleValue.contractId}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText id="Version" value="#{singleValue.version}"></h:outputText>
								<h:inputHidden id="versionHid" value="#{singleValue.version}" />
							</h:column>
							<h:column>
								<h:inputHidden id="effectiveDateHid" value="#{singleValue.startDate}" >
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:inputHidden>
								<h:outputText id="StartDate" value="#{singleValue.startDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
							</h:column>
							<h:column>
								<h:inputHidden id="expiryDateHid" value="#{singleValue.endDate}" >
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:inputHidden>
								<h:outputText id="EndDate" value="#{singleValue.endDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
								<h:inputHidden id="contractKey" value="#{singleValue.contractKey}" />
								<h:inputHidden id="contractDateSegmentSysHId" value="#{singleValue.dateSegmentId}" />
								<h:inputHidden id="productSysHId" value="#{singleValue.productSysId}" />
								<h:inputHidden id="contractIdHid" value="#{singleValue.contractId}" />	
																
								
							</h:column>
							<h:column>
								<h:outputText id="ContractType"
									value="#{singleValue.contractType}"></h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
								<h:inputHidden id="statusHid" value="#{singleValue.status}" />
								<h:inputHidden id="contractProductSysId" value="#{singleValue.productSysId}"></h:inputHidden>
								<h:inputHidden id="dateSegmentType"
									value="#{singleValue.testIndicator}" />
							</h:column>
							<h:column>
								<h:outputText value="#{singleValue.description}"></h:outputText>
							</h:column>
							<h:column>
								<h:outputText value=" " id="a1spaceSpan02" rendered="true"></h:outputText>
								<h:commandButton alt="View" title="View" id="viewButton"
									image="../../images/view.gif"
									rendered="#{singleValue.state.validForView}"
									onclick="getContractView();return false;"></h:commandButton>
								<h:outputText value=" " id="a1spaceSpan01" rendered="true"></h:outputText>
								<h:commandButton alt="Copy" title="Copy" id="copyButton"
									image="../../images/copy.gif"
									rendered="#{singleValue.validForCopyHere}"
									onclick="copyActionChild('../contract/contractCopySubstitute.jsp');return false;"></h:commandButton>
								<h:outputText value=" " id="a1spaceSpan"
									rendered="#{singleValue.state.validForDelete}"></h:outputText>
								<h:commandButton alt="Delete" title="Delete" id="deleteButton1"
									image="../../images/delete.gif"
									onclick="deleteContract();return false;"
									rendered="#{singleValue.state.validForDelete}"></h:commandButton>
								<h:outputText value=" " id="a2spaceSpan"
									rendered="#{singleValue.state.editableByUser}"></h:outputText>
								<h:commandButton alt="Edit" title="Edit" id="editButton"
									image="../../images/edit.gif"
									onclick="getContractEdit();return false;"
									rendered="#{singleValue.editableByUser}"></h:commandButton>
								<h:outputText value=" " id="a10spaceSpan"
									rendered="#{singleValue.validForDS}"></h:outputText>
								<h:commandButton alt="Create Date Segment" title="Create Date Segment" id="ds01"
									rendered="#{singleValue.validForDS && singleValue.state.validForCreate && !(singleValue.state.lockedByUser)}"
									image="../../images/ds.gif"
									onclick="callDSCreate('../contractpopups/dateSegmentPopup.jsp');return false;"></h:commandButton>
								
								<h:outputText value=" " id="a11spaceSpan" rendered="#{singleValue.state.validForCheckOut}" />
								<h:commandButton alt="CheckOut" title="CheckOut" id="checkoutButton" 
									onclick="getContractCheckOut();return false;"
									rendered="#{singleValue.state.validForCheckOut}"
									image="../../images/checkOut.gif" />
									
								<h:outputText value="" id="a4spaceSpan"></h:outputText>	
								<h:commandButton alt="View Older Versions" title="View Older Versions" id="viewOlderVersionButton"
									image="../../images/oldVersion.png"
									onclick="viewOlderVersion();return false;">
								</h:commandButton>
							</h:column>
						</h:dataTable></div>

						</TD>
					</tr>
				</table>
				</DIV>
				</DIV>
				</td>
				</tr>
			<TR>
				<TD><h:inputHidden id="sysIdHidden" value="#{requestScope.sysId}" />
				<h:inputHidden id="contIdHidden" value="#{requestScope.contId}" />
				<h:inputHidden id="versionHidden" value="#{requestScope.version}" />
				<h:inputHidden id="statusHidden" value="#{requestScope.status}" />
				<h:inputHidden
							id="pageIdHidden" value="#{contractSearchBackingBean.pageId}" />
				
				
				
				<h:inputHidden id="selectedContractKeyForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedContractKeyFromSearch}" />
				<h:inputHidden id="selectedDateSegmentIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedDateSegmentIdFromSearch}" />
				<h:inputHidden id="selectedProductSysIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedProductSysIdFromSearch}" />
				<h:inputHidden id="selectedContractIdForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedContractIdFromSearch}" />
				<h:inputHidden id="selectedVersionForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedVersionFromSearch}" />
				<h:inputHidden id="selectedStatusForCheckOut"
				value="#{contractBasicInfoBackingBean.selectedStatusFromSearch}" />	
				<h:inputHidden id="productStatus" 
				value="#{contractBasicInfoBackingBean.productStatus}" />											
				<h:inputHidden id="noteStatus" 
				value="#{contractBasicInfoBackingBean.noteStatus}" />
				<h:inputHidden id="selectedVersionCheckOut"
				value="#{dateSegmentBackingBean.selectedVersion}" />	
				<h:inputHidden id="selectedStatusCheckOut"
				value="#{dateSegmentBackingBean.selectedStatus}" />	
				<h:inputHidden id="selectedProductId"
				value="#{productSearchBackingBean.selectedKeyFromSearch}" /> 
				<h:inputHidden id="selectedProductIdForView"
				value="#{productGeneralInformationBackingBean.selectedIdFromSearch}" />
				<h:commandLink id="viewLink"
					style="display:none; visibility: hidden;"
					action="#{productGeneralInformationBackingBean.viewAction}">
					<f:verbatim />
				</h:commandLink></TD>
			</TR>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>

		<h:inputHidden id="selectedDateSegDelOption"
			value="#{contractSearchBackingBean.selectedDateSegDelOptionFromSearch}" />
		<h:inputHidden id="selectedContractId"
			value="#{contractSearchBackingBean.selectedContractIDFromSearch}" />
		<h:inputHidden id="selectedContractKey"
			value="#{contractSearchBackingBean.selectedContractKeyFromSearch}" />
		<h:inputHidden id="selectedContractDateSegSysId"
			value="#{contractSearchBackingBean.selectedDateSegKeyFromSearch}" />
		<h:inputHidden id="selectedStatus"
			value="#{contractSearchBackingBean.selectedStatusFromSearch}" />
		<h:inputHidden id="selectedVersion"
			value="#{contractSearchBackingBean.selectedVerionFromSearch}" />
		<h:inputHidden id="contractProductId"
			value="#{contractSearchBackingBean.selectedProductFromSearch}" />
		<h:inputHidden id="selectedContractSysIdForPrint"
			value="#{dateSegmentBackingBean.selectedContractSysId}" />
		<h:inputHidden id="hiddenDateSegmentType"
			binding="#{contractBasicInfoBackingBean.hiddenDateSegmentType}" />
		<h:inputHidden id="selectedContractEffectDate"
			 />
		<h:inputHidden id="selectedContractExpDate"
			 />
					
		<h:commandLink id="deleteLink"
			style="display:none; visibility: hidden;"
			action="#{contractSearchBackingBean.deleteAction}">
			<f:verbatim />
		</h:commandLink>

	</h:form>
	</BODY>
</f:view>
<script>
	

	if(null !=  document.getElementById('viewAllVersionForm:previousVersionsTable') ) {
			//tigra_tables('ContractBasicSearchForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
			setColumnWidth('viewAllVersionForm:previousVersionsTable','9%:6%:10%:9%:10%:20%:14%:14%');
			setColumnWidth('resultHeaderTable','9%:6%:10%:9%:10%:20%:14%:14%');
			getObj('viewAllVersionForm:pageIdHidden').value = 'searchVersionPage';
			showResultsTab();	
			tableObj = getObj('viewAllVersionForm:previousVersionsTable');
			if(tableObj.rows.length == 0)
				window.close();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		

	// setColumnWidth('viewAllVersionForm:previousVersionsTable',);'10%:10%:12%:12%:8%:10%:10%:23%'
	
	// getObj('viewAllVersionForm:pageIdHidden').value = 'searchVersionPage';
	
	// fillSpace();
	// tableObj = getObj('viewAllVersionForm:previousVersionsTable');
	
	// if(tableObj.rows.length == 0)
	// 	window.close();

	function getContractView(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersion');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','statusHid','viewAllVersionForm:selectedStatus');
	var url = '../contract/contractBasicInfoView.jsp'+getUrl();
	  url = url +"?contractID="+escape(document.getElementById('viewAllVersionForm:selectedContractId').value)
				+'&contractSysId='+document.getElementById('viewAllVersionForm:selectedContractKey').value
				+'&contractDateSegmentSysId='+document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value
				+'&status='	+document.getElementById('viewAllVersionForm:selectedStatus').value
				+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value
				+'&temp='+Math.random()
				+'&reloadTree=Y';

	var returnvalue=window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;");
	}
	
//for delete contract
	/* function deleteContract()
	{
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
		var myObject = new Object();
			
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		//getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','statusHid','viewAllVersionForm:selectedStatus');

		var url = '../contractpopups/selectOneContractDateSegmentsPopup.jsp'+getUrl();
			url = url +'?status='	+document.getElementById('viewAllVersionForm:selectedStatus').value;
		var returnvalue = window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");	

			if(!(typeof(returnvalue) == "undefined" ||returnvalue ==''))
			{ 
				document.getElementById('viewAllVersionForm:selectedDateSegDelOption').value = returnvalue;
				submitDataTableButton('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId','viewAllVersionForm:deleteLink');
			}

		}else{
				return false;
		}
	} */	

	function getContractEdit(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersion');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','statusHid','viewAllVersionForm:selectedStatus');
	var oMyObject = window.dialogArguments;
	if(oMyObject === undefined){
		oMyObject = new Object();
	}
	oMyObject.contractKey = document.getElementById('viewAllVersionForm:selectedContractKey').value;
	oMyObject.contractId = document.getElementById('viewAllVersionForm:selectedContractId').value;
	oMyObject.dateSegementId = document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value;
	oMyObject.version = document.getElementById('viewAllVersionForm:selectedVersion').value;
	oMyObject.status = document.getElementById('viewAllVersionForm:selectedStatus').value;
	window.returnValue = '2';
	
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:contractKeyForDS').value = oMyObject.contractKey;
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:contractIdForDS').value = oMyObject.contractId;
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:contractDateSegmentSysForDS').value = oMyObject.dateSegementId;
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:verionForDS').value = oMyObject.version;
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:statusForDS').value = oMyObject.status;
		window.dialogArguments.document.getElementById('ContractBasicSearchForm:linkToEdit').click();
	
	window.close();
	}
		

	function getProductCopy(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId');
	window.dialogArguments.document.getElementById('searchForm:selectedProductId').value = document.getElementById('viewAllVersionForm:selectedProductId').value;
	window.dialogArguments.document.getElementById('searchForm:copyLink').click();
	window.close();
	}

	function getProductDelete(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId');
	window.dialogArguments.document.getElementById('searchForm:selectedProductId').value = document.getElementById('viewAllVersionForm:selectedProductId').value;
	window.dialogArguments.document.getElementById('searchForm:deleteLink').click();
	window.close();
	}

	function submitToParentPage(parentid,parentLink){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId');
		window.dialogArguments.document.getElementById(parentid).value = document.getElementById('viewAllVersionForm:selectedProductId').value;
		window.dialogArguments.document.getElementById(parentLink).click();
		window.returnValue = '';
		window.close();
	}
	function callDSCreate(page)
		{	
		
		var url=page+getUrl()+"?contractKey="+document.getElementById('viewAllVersionForm:sysIdHidden').value+'&contractId='+escape(document.getElementById('viewAllVersionForm:contIdHidden').value)+'&version='+document.getElementById('viewAllVersionForm:versionHidden').value+'&status='+document.getElementById('viewAllVersionForm:statusHidden').value+'&temp='+Math.random();
	    var winOfSearch = window.dialogArguments;
		var returnvalue=window.showModalDialog(url,winOfSearch,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
		
		if(returnvalue == '1'){
			window.returnValue = '1' ;
			window.close();
		}
		if(returnvalue == undefined){
			
		}
	}
	
	/* function copyAction(page){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersion');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','statusHid','viewAllVersionForm:selectedStatus');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractProductSysId','viewAllVersionForm:contractProductId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','dateSegmentType','viewAllVersionForm:hiddenDateSegmentType');	

		//testing validations for copy
		var contractSysId = document.getElementById('viewAllVersionForm:selectedContractKey').value;
		var dateSegmentSysId = document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value;
		var prodid = document.getElementById('viewAllVersionForm:contractProductId').value;
//		alert("consysid"+contractSysId);
//		alert("datesgmnt"+dateSegmentSysId);
//		alert("prodid"+prodid);
//		var url=page+"?dateSegmentId="+document.getElementById('ContractBasicSearchForm:selectedDateSegmentIdForCheckOut').value+'&contractKey='+document.getElementById('ContractBasicSearchForm:selectedContractKeyForCheckOut').value+'&contractId='+document.getElementById('ContractBasicSearchForm:selectedContractIdForCheckOut').value+'&version='+document.getElementById('ContractBasicSearchForm:selectedVersionForCheckOut').value+'&status='+document.getElementById('ContractBasicSearchForm:selectedStatusForCheckOut').value+'&productId='+document.getElementById('ContractBasicSearchForm:selectedProductSysIdForCheckOut').value+'&temp='+Math.random();
//		alert(document.getElementById('viewAllVersionForm:hiddenDateSegmentType').value);
		
       if(document.getElementById('viewAllVersionForm:hiddenDateSegmentType').value=='Y'){
			productStatus="";
            notesStatus="";
			
		}
		else{
        var url1 = "../contractpopups/validateCopyPopup.jsp"+getUrl()+"?contractKey="+contractSysId+'&dateSegmentId='+dateSegmentSysId+'&productId='+prodid+'&temp='+Math.random();
		var myObject = new Object();

		var returnvalue1=window.showModalDialog(url1,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
//		alert(returnvalue1);

		if(returnvalue1 == undefined){
			returnvalue1=""+"~"+"";
		}
		var array = returnvalue1.split("~");
		productStatus = array[0];
		notesStatus = array[1];

       }

	var url=page+getUrl()+"?dateSegmentId="+document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value+'&contractKey='+document.getElementById('viewAllVersionForm:selectedContractKey').value+'&contractId='+escape(document.getElementById('viewAllVersionForm:selectedContractId').value)+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value+'&status='+document.getElementById('viewAllVersionForm:selectedStatus').value+'&productId='+document.getElementById('viewAllVersionForm:contractProductId').value+'&productStatus='+productStatus+'&noteStatus='+notesStatus+'&temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
	
	if(returnvalue=='copy'){
		window.returnValue = 'copy';
		window.close();
	}	
	else if(returnvalue=='copyHeadings'){	
		window.returnValue = 'copyHeadings';
		window.close();
	}	
	}
	 */
		function viewOlderVersion(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','effectiveDateHid','viewAllVersionForm:selectedContractEffectDate');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','expiryDateHid','viewAllVersionForm:selectedContractExpDate');

		var url = '../contract/viewOlderVersions.jsp'+getUrl();
		  url = url +"?contractID="+escape(document.getElementById('viewAllVersionForm:selectedContractId').value)
					+'&contractSysId='+document.getElementById('viewAllVersionForm:selectedContractKey').value
					+'&contractEffDate='+document.getElementById('viewAllVersionForm:selectedContractEffectDate').value
					+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value
					+'&contractExpDate='+document.getElementById('viewAllVersionForm:selectedContractExpDate').value;
		var returnvalue=window.showModalDialog(url,window,"dialogHeight:700px;dialogWidth:1000px;resizable=true;status=no;");

		}
	
	function getContractCheckOut(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKeyForCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','versionHid','viewAllVersionForm:selectedVersionCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','statusHid','viewAllVersionForm:selectedStatusCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','productSysHId','viewAllVersionForm:selectedProductSysIdForCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedDateSegmentIdForCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','dateSegmentType','viewAllVersionForm:hiddenDateSegmentType');
		var oMyObject = window.dialogArguments;
		oMyObject.contractKey = document.getElementById('viewAllVersionForm:selectedContractKeyForCheckOut').value;
		oMyObject.contractId = document.getElementById('viewAllVersionForm:selectedContractId').value;
		oMyObject.dateSegementId = document.getElementById('viewAllVersionForm:selectedDateSegmentIdForCheckOut').value;
		oMyObject.version = document.getElementById('viewAllVersionForm:selectedVersionCheckOut').value;
		oMyObject.status = document.getElementById('viewAllVersionForm:selectedStatusCheckOut').value;
		oMyObject.productSysId = document.getElementById('viewAllVersionForm:selectedProductSysIdForCheckOut').value;
		oMyObject.dateSegmentType = document.getElementById('viewAllVersionForm:hiddenDateSegmentType').value;
		window.returnValue = '3';
		<%
			if(browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
			window.close();
		<%
		}
		else{
		%>
			parent.document.getElementsByTagName('dialog')[0].close();
		<%
		}
		%>
	}
	
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractViewAllDS" /></form>
</HTML>
