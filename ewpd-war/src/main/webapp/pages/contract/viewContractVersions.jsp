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
<TITLE>View Contract Versions</TITLE>

</HEAD>
<f:view>
	<BODY>
	<h:form id="viewAllVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
						<h:inputHidden id="breadCrumb"
							value="#{contractSearchBackingBean.breadCrumb}" />
		<jsp:include page="../navigation/top_view.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%"><w:message></w:message>
				<div id="searchPanelHeader" class="tabTitleBar" style="width:100%;"><b>Available
				Versions</b></div>
				<br />
				
				<div style="text-align:center;">
				<h:commandButton alt="First" title="First" id="firstPageId"
									image="../../images/24-arrow-first.png"
									action="#{contractSearchBackingBean.getFirstPage}"></h:commandButton>
									
				<h:commandButton alt="Previous" title="Previous" id="previousPageId"
									image="../../images/24-arrow-previous.png"
									action="#{contractSearchBackingBean.getPreviousPage}"></h:commandButton>
									
				<h:inputText id="navigateToPage" style="width:20px; font-size:10px;" value ="#{contractSearchBackingBean.navigateTo}"  ></h:inputText>	
				<h:outputLabel>of #{contractSearchBackingBean.lastPageLabel}&nbsp;</h:outputLabel>	
					<h:commandButton styleClass ="wpdbutton" id="goToPageButton" value="Go" 
					onclick="#{contractSearchBackingBean.navigateTo}">
					
					
					</h:commandButton>		
				<h:commandButton alt="Next" title="Next" id="nextPageId"
									image="../../images/24-arrow-next.png"
									action="#{contractSearchBackingBean.getNextPage}">
				</h:commandButton>
										
				<h:commandButton alt="Last" title="Last" id="lastPageId"
									image="../../images/24-arrow-last.png"
									action="#{contractSearchBackingBean.getLastPage}"></h:commandButton>	
							
				<h:inputHidden id="currentPage" value="#{contractSearchBackingBean.currentPage}"></h:inputHidden>	

				</div>
								
				<table align="left" cellpadding="0" cellspacing="0" width="100%"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="100%">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td align="left" width="15%"><strong><h:outputText
										value="ContractId"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Version"></h:outputText></strong></td>									
									<td align="left" width="15%"><strong><h:outputText
										value="Contract Type"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="Contract Status"></h:outputText></strong></td>
									<td align="left" width="10%"><strong><h:outputText
										value="DateSegment Type"></h:outputText></strong></td>
                                    <td align="left" width="15%"><strong><h:outputText
										value="Effective Date"></h:outputText></strong></td>
									<td align="left" width="15%"><strong><h:outputText
										value="Expiry Date"></h:outputText></strong></td>
									<td align="left" width="20%">&nbsp;</td>
                                    
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:470; overflow:auto; width:98%;"><h:dataTable
							headerClass="dataTableHeader" id="previousVersionsTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							bgcolor="#cccccc" rendered="true"
							value="#{contractSearchBackingBean.paginatedVersionList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="100%">
							<h:column>
								<h:outputText id="ContractId" value="#{singleValue.contractId}"></h:outputText><h:outputText id="ContractSpace"
												value="  "></h:outputText>
											<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
							</h:column>
							<h:column>
								<h:outputText id="Version" value="#{singleValue.version}"></h:outputText>
								<h:inputHidden id="VersionIn" value="#{singleValue.version}"></h:inputHidden>
							</h:column>
							
							<h:column>
								<h:outputText id="ContractType"
									value="#{singleValue.contractType}"></h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="Status" value="#{singleValue.status}"></h:outputText>
								<h:inputHidden id="StatusIn" value="#{singleValue.status}"></h:inputHidden>

								<h:inputHidden id="contractProductId"
									value="#{singleValue.productSysId}" />
								<h:inputHidden id="dateSegmentType"
									value="#{singleValue.testIndicator}" />
							</h:column>
                            
							<h:column>
								<h:outputText value="#{singleValue.description}"></h:outputText>
							</h:column>
                            <h:column>
								<h:outputText id="StartDate" value="#{singleValue.startDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>

							</h:column>
							<h:column>
								<h:outputText id="EndDate" value="#{singleValue.endDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
								<h:inputHidden id="contractKey"
									value="#{singleValue.contractKey}"></h:inputHidden>
								<h:inputHidden id="contractIdHid"
									value="#{singleValue.contractId}"></h:inputHidden>
								<h:inputHidden id="contractDateSegmentSysHId"
									value="#{singleValue.dateSegmentId}" />
							</h:column>
							<h:column>
								<h:outputText value="" id="a1spaceSpan02" rendered="true"></h:outputText>
								<h:commandButton alt="View" title="View" id="viewButton"
									image="../../images/view.gif"
									rendered="#{singleValue.state.validForView}"
									onclick="getContractView();return false;"></h:commandButton>
								<h:outputText value="" id="a2spaceSpan1" rendered="true"></h:outputText>
								<h:commandButton alt="Copy" title="Copy" id="copyButton"
									image="../../images/copy.gif"
									rendered="#{singleValue.validForCopyHere}"
									onclick="copyAction('../contract/contractCopySubstitute.jsp');return false;">
								</h:commandButton>
								<h:outputText value="" id="a1spaceSpan"
									rendered="#{singleValue.state.validForDelete}"></h:outputText>
								<h:commandButton alt="Delete" title="Delete" id="deleteButton1"
									image="../../images/delete.gif"
									rendered="#{singleValue.state.validForDelete}"
									onclick="deleteContract();return false;"></h:commandButton>
								<h:outputText value="" id="a2spaceSpan"
									rendered="#{singleValue.editableByUser}"></h:outputText>
								<h:commandButton alt="Edit" title="Edit" id="editButton"
									image="../../images/edit.gif"
									rendered="#{singleValue.editableByUser}"
									onclick="getContractEdit();return false;"></h:commandButton>
								<h:outputText value="" id="a3spaceSpan"
									rendered="#{singleValue.state.validForTest}"></h:outputText>
								<h:commandButton alt="SendToTest" title="SendToTest" id="sendToTestButton"
									image="../../images/schedule_test.gif"
									onclick="sendToTestAction();return false;"
									rendered="#{singleValue.state.validForTest}"></h:commandButton>
								<h:outputText value="" id="a4spaceSpan"
									rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
								<h:commandButton alt="TestPass" title="TestPass" id="testPassButton"
									image="../../images/test_successful.gif"
									onclick="testPassAction();return false;"
									rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
								<h:outputText value="" id="a5spaceSpan"
									rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
								<h:commandButton alt="TestFail" title="TestFail" id="testFailButton"
									image="../../images/test_failed.gif"
									onclick="testFailAction();return false;"
									rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
								<h:outputText value="" id="a6spaceSpan"
									rendered="#{singleValue.state.validForApproval}"></h:outputText>
								<h:commandButton alt="Schedule To Approve" title="Schedule To Approve" id="approveButton"
									image="../../images/scheduled_approval.gif"
									onclick="scheduleToApproveAction();return false;"
									rendered="#{singleValue.validForApproval}"></h:commandButton>
								<h:outputText value="" id="a7spaceSpan"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
								<h:commandButton alt="Approve" title="Approve" id="approvalButton"
									image="../../images/approved.gif"
									onclick="approveAction();return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
								<h:outputText value="" id="a8spaceSpan"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
								<h:commandButton alt="Reject" title="Reject" id="rejectButton"
									image="../../images/rejected.gif"
									onclick="rejectAction();return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
								<h:outputText value="" id="a9spaceSpan"
									rendered="#{singleValue.state.validForPublish}"></h:outputText>
								<h:commandButton alt="Publish" title="Publish" id="publishButton"
									image="../../images/publish.gif"
									onclick="publishAction();return false;"
									rendered="#{singleValue.state.validForPublish}"></h:commandButton>
								<h:outputText value="" id="a10spaceSpan"
									rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
								<h:commandButton alt="CheckOut" title="CheckOut" id="checkoutButton"
									image="../../images/checkOut.gif"
									onclick="getContractCheckOut();return false;"
									rendered="#{singleValue.state.validForCheckOut}">
								</h:commandButton>
								<h:outputText value="" id="a11spaceSpan" rendered="false"></h:outputText>
								<h:commandButton alt="Schedule To Production" title="Schedule To Production"
									id="scheduleToProductionButton"
									image="../../images/scheduled_production1.gif"
									onclick="scheduleToProduction();return false;" rendered="false"></h:commandButton>
								<h:outputText value="" id="a11spaceSpan1"
									rendered="#{singleValue.validForDS}"></h:outputText>
								<h:commandButton alt="Create Date Segment" title="Create Date Segment" id="ds01"
									rendered="#{singleValue.validForDS && singleValue.state.validForCreate && !(singleValue.state.lockedByUser)}"
									image="../../images/ds.gif"
									onclick="callDSCreate('../contractpopups/dateSegmentPopup.jsp');return false;"></h:commandButton>
								<h:outputText value="" id="a1spaceSpan09"
												rendered="#{singleValue.state.validForUnlock}"></h:outputText>
								<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="unlockContract();return false;"
												rendered="#{singleValue.state.validForUnlock}">
								</h:commandButton>
							</h:column>
						</h:dataTable></div>

						</TD>
					</tr>
					<TR>
						<TD><h:inputHidden id="sysIdHidden" value="#{requestScope.sysId}" />
						<h:inputHidden id="contIdHidden" value="#{requestScope.contId}" />
						<h:inputHidden id="versionHidden" value="#{requestScope.version}" />
						<h:inputHidden id="statusHidden" value="#{requestScope.status}" />
						<h:inputHidden id="selectedVersion"
							value="#{dateSegmentBackingBean.selectedVersion}" /> <h:inputHidden
							id="selectedStatus"
							value="#{dateSegmentBackingBean.selectedStatus}" /> <h:inputHidden
							id="selectedContractSysId"
							value="#{dateSegmentBackingBean.selectedContractSysId}" /> <h:inputHidden
							id="pageIdHidden" value="#{contractSearchBackingBean.pageId}" />

						<h:inputHidden id="selectedDateSegDelOption"
							value="#{contractSearchBackingBean.selectedDateSegDelOptionFromSearch}" />
						<h:inputHidden id="selectedContractId"
							value="#{contractSearchBackingBean.selectedContractIDFromSearch}" />
						<h:inputHidden id="selectedContractKey"
							value="#{contractSearchBackingBean.selectedContractKeyFromSearch}" />
						<h:inputHidden id="selectedContractDateSegSysId"
							value="#{contractSearchBackingBean.selectedDateSegKeyFromSearch}" />
						<h:inputHidden id="selectedStatusforLifeCycle"
							value="#{contractSearchBackingBean.selectedStatusFromSearch}" />
						<h:inputHidden id="selectedVersionForLifeCycle"
							value="#{contractSearchBackingBean.selectedVerionFromSearch}" />
						<h:inputHidden id="selectedProductId"
							value="#{contractSearchBackingBean.selectedProductFromSearch}" />
						<h:inputHidden id="selectedProductSysIdForCheckOut"
							value="#{contractBasicInfoBackingBean.selectedProductSysIdFromSearch}" />
						<h:inputHidden id="hiddenDateSegmentType"
							binding="#{contractBasicInfoBackingBean.hiddenDateSegmentType}" />
						<h:inputHidden id="testDateSegments"
							value="#{contractSearchBackingBean.testDateSegment}" /> <h:commandLink
							id="deleteLink" style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.deleteAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="viewLink"
							style="display:none; visibility: hidden;"
							action="#{productGeneralInformationBackingBean.viewAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="sendToTestLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.sendToTest}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="testPassLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.testPass}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="testFailLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.testFail}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="scheduleToApproveLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.scheduleToApprove}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="approveLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.approve}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="rejectLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.reject}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="publishLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.publish}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="scheduleToProductionLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.scheduleToProduction}">
							<f:verbatim />
						</h:commandLink>
						<h:commandLink id="unlockLink"
							style="display:none; visibility: hidden;"
							action="#{contractSearchBackingBean.unlockContract}">
							<f:verbatim />
						</h:commandLink></TD>
					</TR>
				</table>

				</td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td></tr>
		</table>

	</h:form>
	</BODY>
</f:view>
<script>

	setColumnWidth('viewAllVersionForm:previousVersionsTable','9%:6%:12%:21%:10%:12%:12%:18%');
	setColumnWidth('resultHeaderTable','9%:6%:12%:21%:10%:12%:12%:18%');
	getObj('viewAllVersionForm:pageIdHidden').value = 'searchVersionPage';
	fillSpace();
	tableObj = getObj('viewAllVersionForm:previousVersionsTable');
	if(tableObj.rows.length == 0)
		window.close();

	function getContractEdit(){
	
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
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
		
	function getContractView(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
	var url = '../contract/contractBasicInfoView.jsp'+getUrl();
	  url = url +"?contractID="+escape(document.getElementById('viewAllVersionForm:selectedContractId').value)
				+'&contractSysId='+document.getElementById('viewAllVersionForm:selectedContractKey').value
				+'&contractDateSegmentSysId='+document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value
				+'&status='	+document.getElementById('viewAllVersionForm:selectedStatus').value
				+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value
				+'&temp='+Math.random()
				+'&reloadTree=Y';
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
	}

//for delete contract


<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				function deleteContract()
				<%
				}
				else {
				%>
				
				async function deleteContract()
				<%
				}
			%>


	
	{
		var message = "Are you sure you want to delete?"
		if(window.confirm(message)){
		var myObject = new Object();
		
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		//getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');

		var url = '../contractpopups/selectOneContractDateSegmentsPopup.jsp'+getUrl();
			url = url +'?status='	+document.getElementById('viewAllVersionForm:selectedStatusforLifeCycle').value;
			
			
			<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				var returnvalue = window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");	

				<%
				}
				else {
				%>
				
				var returnvalue = await window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");	

				<%
				}
			%>
			
		
			if(!(typeof(returnvalue) == "undefined" ||returnvalue ==''))
			{ 
		
				document.getElementById('viewAllVersionForm:selectedDateSegDelOption').value = returnvalue;
				//submitDataTableButton('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId','viewAllVersionForm:deleteLink');
				submitLink('viewAllVersionForm:deleteLink');
			}

		}else{
				return false;
		}
	}	

		function getContractCheckOut(){
		
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractProductId','viewAllVersionForm:selectedProductSysIdForCheckOut');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','dateSegmentType','viewAllVersionForm:hiddenDateSegmentType');

			
		var contractKey = document.getElementById('viewAllVersionForm:selectedContractKey').value;
		var contractId = document.getElementById('viewAllVersionForm:selectedContractId').value;
		var dateSegementId = document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value;
		var version = document.getElementById('viewAllVersionForm:selectedVersion').value;
		var status = document.getElementById('viewAllVersionForm:selectedStatus').value;
		var productSysId = document.getElementById('viewAllVersionForm:selectedProductSysIdForCheckOut').value;
		var dateSegmentType = document.getElementById('viewAllVersionForm:hiddenDateSegmentType').value;
		
		window.returnValue = '3' +'|'+ contractKey +'|'+ contractId +'|'+dateSegementId +'|'+version +'|'+status +'|'+productSysId +'|'+dateSegmentType;
		
		
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		window.close();
		<%
			}
		else {
		%>
		parent.document.getElementsByTagName('dialog')[0].close();
		<%
			}
		%>
		 
		
	}
	
	<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				function sendToTestAction()
				<%
				}
				else {
				%>
				
				async function sendToTestAction()
				<%
				}
			%>
			{
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		var contractSysId = document.getElementById('viewAllVersionForm:selectedContractKey').value;
		var url = '../contractpopups/selectTestDateSegmentsPopup.jsp'+getUrl()+'?contractkey='+contractSysId;
		var myObject = new Object();
		<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				var returnvalue = window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");	

				<%
				}
				else {
				%>
				
				var returnvalue = await window.showModalDialog(url, myObject, "dialogHeight:126px;dialogWidth:330px;resizable=false;status=no;");		

				<%
				}
			%>
			
		document.getElementById('viewAllVersionForm:testDateSegments').value = returnvalue;
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:sendToTestLink');
	}	
		
	function testPassAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:testPassLink');
	}

	function unlockContract(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:unlockLink');
	}
		function testFailAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:testFailLink');
	}
		function scheduleToApproveAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:scheduleToApproveLink');
	}
	function approveAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:approveLink');
	}
	function rejectAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:rejectLink');
	}

	function publishAction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:publishLink');
	}

	function scheduleToProduction(){
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersionForLifeCycle');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatusforLifeCycle');
		submitLink('viewAllVersionForm:scheduleToProductionLink');
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
	
		
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		function callDSCreate(page)
		<%
			}
		else {
		%>
		async function callDSCreate(page)
		<%
			}
		%>
	{
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractSysId');

		//added for getting the values of the row which we have clicked
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractProductId','viewAllVersionForm:selectedProductSysIdForCheckOut');
		
		var url=page+getUrl()+"?contractKey="+document.getElementById('viewAllVersionForm:selectedContractSysId').value+'&contractId='+escape(document.getElementById('viewAllVersionForm:contIdHidden').value)+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value+'&status='+document.getElementById('viewAllVersionForm:selectedStatus').value+'&temp='+Math.random();
	    //var winOfSearch = window.dialogArguments;
	    
		var returnvalue= await window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
        
		//setting the row values to the window object
		var contractKey = document.getElementById('viewAllVersionForm:selectedContractKey').value;
		var contractId = document.getElementById('viewAllVersionForm:selectedContractId').value;
		var dateSegementId = document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value;
		var version = document.getElementById('viewAllVersionForm:selectedVersion').value;
		var status = document.getElementById('viewAllVersionForm:selectedStatus').value;
		var productSysId = document.getElementById('viewAllVersionForm:selectedProductSysIdForCheckOut').value;
		var comments = returnvalue.comments;
		var dateEntered = returnvalue.dateEntered;
		var type = returnvalue.type;
		var copyLegacyNote = returnvalue.copyLegacyNote;
		var value = returnvalue.vValue;
		
		if(value == '1'){
		window.returnValue = '1' +'|'+ comments +'|'+ dateEntered +'|'+type +'|'+copyLegacyNote +'|'+value+'|'+ contractKey +'|'+ contractId +'|'+dateSegementId +'|'+version +'|'+status+'|'+productSysId;

			
			//window.close();
			
		}
		if(returnvalue == undefined){
			
		}
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		window.close();
		<%
			}
		else {
		%>
		parent.document.getElementsByTagName('dialog')[0].close();
		<%
			}
		%>
}


<%
		
				if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
				%>
				function copyAction(page)
				<%
				}
				else {
				%>
				async function copyAction(page)
				<%
				}
			%>
			
		
		{	
		
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractKey','viewAllVersionForm:selectedContractKey');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractIdHid','viewAllVersionForm:selectedContractId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractDateSegmentSysHId','viewAllVersionForm:selectedContractDateSegSysId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','VersionIn','viewAllVersionForm:selectedVersion');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','StatusIn','viewAllVersionForm:selectedStatus');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','contractProductId','viewAllVersionForm:selectedProductId');
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','dateSegmentType','viewAllVersionForm:hiddenDateSegmentType');		


		//testing validations for copy
		var contractSysId = document.getElementById('viewAllVersionForm:selectedContractKey').value;
		var dateSegmentSysId = document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value;
		var prodid = document.getElementById('viewAllVersionForm:selectedProductId').value;
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

<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		var returnvalue1= window.showModalDialog(url1,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
		<%
			}
		else {
		%>
		var returnvalue1=await window.showModalDialog(url1,myObject,"dialogHeight:300px;dialogWidth:800px;resizable=true;status=no;");
		<%
			}
		%>
		
//		alert(returnvalue1);

		if(returnvalue1 == undefined){
			returnvalue1=""+"~"+"";
		}
		var array = returnvalue1.split("~");
		productStatus = array[0];
		notesStatus = array[1];

       }

		var url=page+getUrl()+"?dateSegmentId="+document.getElementById('viewAllVersionForm:selectedContractDateSegSysId').value+'&contractKey='+document.getElementById('viewAllVersionForm:selectedContractKey').value+'&contractId='+escape(document.getElementById('viewAllVersionForm:selectedContractId').value)+'&version='+document.getElementById('viewAllVersionForm:selectedVersion').value+'&status='+document.getElementById('viewAllVersionForm:selectedStatus').value+'&productId='+document.getElementById('viewAllVersionForm:selectedProductId').value+'&productStatus='+productStatus+'&noteStatus='+notesStatus+'&temp='+Math.random();
		
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		var returnvalue=window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
		<%
			}
		else {
		%>
		var returnvalue=await window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;");
		<%
			}
		%>
		
		
		

		
		if(returnvalue=='copy'){
		window.returnValue = 'copy';
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		window.close();
		<%
			}
		else {
		%>
		parent.document.getElementsByTagName('dialog')[0].close();
		<%
			}
		%>
		}
		else if(returnvalue=='copyHeadings'){	
		window.returnValue = 'copyHeadings';
		<%
		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Trident") != -1) {
		%>
		window.close();
		<%
			}
		else {
		%>
		parent.document.getElementsByTagName('dialog')[0].close();
		<%
			}
		%>
		}
		
	}

	if(document.getElementById('viewAllVersionForm:previousVersionsTable') != null){
			document.getElementById('viewAllVersionForm:previousVersionsTable').onresize = syncTables;
			syncTables();
		}
	function syncTables(){
			var relTblWidth = document.getElementById('viewAllVersionForm:previousVersionsTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = document.getElementById('viewAllVersionForm:previousVersionsTable').offsetWidth;
		}	
			
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractViewAllVersions" /></form>
</HTML>
