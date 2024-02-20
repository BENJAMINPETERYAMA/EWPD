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
<TITLE>View All Versions - Product</TITLE>

</HEAD>
<f:view>
	<BODY>
	<h:form id="viewAllVersionForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<h:inputHidden id="dummy"
				value="#{productSearchBackingBean.breadCrumbViewAllVersions}" />
			<tr>
				<td>	
				<jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td colspan="2" valign="top" align="left" class="ContentArea"
					width="100%"><w:message></w:message>
				<div id="searchPanelHeader" class="tabTitleBar"><b>All Versions</b></div>
				<br />
				<table align="left" cellpadding="0" cellspacing="0" width="1100"
					border="0">
					<tr>
						<td>
						<div id="resultHeaderDiv" style="width:1100;">
						<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
							id="resultHeaderTable" border="0" width="1100">
							<TBODY>
								<tr bgcolor="#ffffff">
									<td><strong><h:outputText value="Name"></h:outputText></strong></td>
									<td><strong><h:outputText value="Version"></h:outputText></strong></td>
									<td><strong><h:outputText value="Line Of Business"></h:outputText></strong></td>
									<td><strong><h:outputText value="Description"></h:outputText></strong></td>
									<td><strong><h:outputText value="Status"></h:outputText></strong></td>
									<td><strong><h:outputText value="Effective Date"></h:outputText></strong></td>
									<td><strong><h:outputText value="Expiry Date"></h:outputText></strong></td>
									<td>&nbsp;</td>
								</TR>
							</TBODY>
						</TABLE>
						</div>
						</td>
					</tr>
					<tr>
						<TD><!-- Search Result Data Table -->
						<div id="searchResultdataTableDiv"
							style="height:292px; overflow:auto; width:1100;height:480"><h:dataTable
							headerClass="dataTableHeader" id="previousVersionsTable"
							var="singleValue" cellpadding="3" cellspacing="1"
							bgcolor="#cccccc" rendered="true"
							value="#{productSearchBackingBean.previousVersionList}"
							rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
							width="1100">
							<h:column>
								<h:outputText id="productName"
									value="#{singleValue.productName}"></h:outputText>
								<h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
								<h:inputHidden id="hidden_productStructureName"
									value="#{singleValue.productName}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productVersion" value="#{singleValue.version}"></h:outputText>
								<h:inputHidden id="hidden_productStructureDescription"
									value="#{singleValue.version}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="Line_Of_Business" value="#{singleValue.lineOfBusiness}"></h:outputText>
								<h:inputHidden id="hidden_lob"
									value="#{singleValue.lineOfBusiness}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productDescription" value="#{singleValue.productDescription}"></h:outputText>
								<h:inputHidden id="hidden_productDescription"
									value="#{singleValue.productDescription}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productStatus" value="#{singleValue.status}"></h:outputText>
								<h:inputHidden id="hidden_productStructureStatus"
									value="#{singleValue.status}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productEffectiveDate"
									value="#{singleValue.effectiveDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
								<h:inputHidden id="hidden_benefitComponents"
									value="#{singleValue.effectiveDate}"></h:inputHidden>
							</h:column>
							<h:column>

								<h:outputText id="productExpiryDate"
									value="#{singleValue.expiryDate}">
									<f:convertDateTime pattern="MM/dd/yyyy" />
								</h:outputText>
								<h:inputHidden id="hidden_businessDomain"
									value="#{singleValue.expiryDate}"></h:inputHidden>
								<h:inputHidden id="selectedProductKey"
									value="#{singleValue.productKey}"></h:inputHidden>
								<h:inputHidden id="productKey" value="#{singleValue.productKey}"></h:inputHidden>
							</h:column>

							<h:column>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								<h:commandButton alt="View" title="View" id="viewButton"
									image="../../images/view.gif"
									rendered="#{singleValue.state.validForView}"
									onclick="getProductView('../product/productGeneralInformationView.jsp');return false;"></h:commandButton>
								<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
								<h:commandButton alt="Copy" title="Copy" id="copyButton"
									image="../../images/copy.gif"
									rendered="#{singleValue.state.validForCopy}"
									onclick="submitToParentPage('searchForm:selectedProductId','searchForm:copyLink');return false;"></h:commandButton>
								<h:outputText value="" id="a1spaceSpan"
									rendered="#{singleValue.state.validForDelete}"></h:outputText>
								<h:commandButton alt="Delete" title="Delete" id="deleteButton1"
									image="../../images/delete.gif"
									onclick=" if(confirmTask('#{singleValue.deleteMessage}')) submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:deleteLink');return false;"
									rendered="#{singleValue.state.validForDelete}"></h:commandButton>
								<h:outputText value="" id="a2spaceSpan"
									rendered="#{singleValue.state.editableByUser}"></h:outputText>
								<h:commandButton alt="Edit" title="Edit" id="editButton"
									image="../../images/edit.gif"
									onclick="submitToParentPage('searchForm:selectedProductId','searchForm:editLink');return false;"
									rendered="#{singleValue.editableByUser}"></h:commandButton>
								<h:outputText value="" id="a3spaceSpan"
									rendered="#{singleValue.state.validForTest}"></h:outputText>
								<h:commandButton alt="SendToTest" title="SendToTest" id="sendToTestButton"
									image="../../images/schedule_test.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:sendToTestLink');return false;"
									rendered="#{singleValue.validForTesting}"></h:commandButton>
								<h:outputText value="" id="a4spaceSpan"
									rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
								<h:commandButton alt="TestPass" title="TestPass" id="testPassButton"
									image="../../images/test_successful.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:testPassLink');return false;"
									rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
								<h:outputText value="" id="a5spaceSpan"
									rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
								<h:commandButton alt="TestFail" title="TestFail" id="testFailButton"
									image="../../images/test_failed.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:testFailLink');return false;"
									rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
								<h:outputText value="" id="a6spaceSpan"
									rendered="#{singleValue.state.validForApproval}"></h:outputText>
								<h:commandButton alt="Schedule To Approve" title="Schedule To Approve"
									id="approveButtonSchedule"
									image="../../images/scheduled_approval.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:scheduleForApprovalLink');return false;"
									rendered="#{singleValue.state.validForApproval}"></h:commandButton>
								<h:outputText value="" id="a8spaceSpan"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
								<h:commandButton alt="Approve" title="Approve" id="approveButton"
									image="../../images/approved.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:approveLink');return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
								<h:outputText value="" id="a7spaceSpan"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
								<h:commandButton alt="Reject" title="Reject" id="rejectButton"
									image="../../images/rejected.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:rejectLink');return false;"
									rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
								<h:outputText value="" id="a9spaceSpan"
									rendered="#{singleValue.state.validForPublish}"></h:outputText>
								<h:commandButton alt="Publish" title="Publish" id="publishButton"
									image="../../images/publish.gif"
									onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:publishLink');return false;"
									rendered="#{singleValue.validForPuplish}"></h:commandButton>
								<h:outputText value="" id="a10spaceSpan"
									rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
								<h:commandButton alt="CheckOut" title="CheckOut" id="checkoutButton"
									image="../../images/checkOut.gif"
									onclick="submitToParentPage('searchForm:selectedProductId','searchForm:checkOutLink');return false;"
									rendered="#{singleValue.validForCheckOut}" />
								<h:outputText value="" id="a1spaceSpan09"
												rendered="#{singleValue.state.validForUnlock}"></h:outputText>
									<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
										image="../../images/lockgreen.gif" value="Unlock"
										onclick="submitDataTableButton('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId','viewAllVersionForm:unlockLink');return false;"
										rendered="#{singleValue.state.validForUnlock}">
									</h:commandButton>
								<h:outputText value="" id="a11spaceSpan" rendered="false"></h:outputText>
							</h:column>
						</h:dataTable></div>

						</TD>
					</tr>
					<TR>
						<TD><h:inputHidden id="pageIdHidden"
							value="#{productSearchBackingBean.pageId}" /> <h:inputHidden
							id="selectedProductId"
							value="#{productSearchBackingBean.selectedKeyFromSearch}" /> <h:inputHidden
							id="selectedProductIdForView"
							value="#{productGeneralInformationBackingBean.selectedIdFromSearch}" />
						<h:commandLink id="deleteLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.deleteAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="viewLink"
							style="display:none; visibility: hidden;"
							action="#{productGeneralInformationBackingBean.viewAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="testPassLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.testPassAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="testFailLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.testFailAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="sendToTestLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.sendToTestAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="publishLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.publishAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="scheduleForApprovalLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.scheduleForApprovalAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="transferLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.transferAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="approveLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.approveAction}">
							<f:verbatim />
						</h:commandLink> <h:commandLink id="rejectLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.rejectAction}">
							<f:verbatim />
						</h:commandLink></TD>
						<h:commandLink id="unlockLink"
							style="display:none; visibility: hidden;"
							action="#{productSearchBackingBean.unLockAction}">
							<f:verbatim />
						</h:commandLink>
					</TR>
				</table>

				</td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom_view.jsp"%></td>
			</tr>
		</table>

	</h:form>
	</BODY>
</f:view>
<script>
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
		if(relTblWidth != 0){
			if(document.getElementById('viewAllVersionForm:previousVersionsTable').offsetHeight <= 292) {
				document.getElementById('viewAllVersionForm:previousVersionsTable').width = relTblWidth+"px";
				setColumnWidth('resultHeaderTable','22%:6%:6%:20%:14%:8%:7%:18%');
				setColumnWidth('viewAllVersionForm:previousVersionsTable','22%:6%:6%:20%:14%:8%:7%:18%');
			}else{
				document.getElementById('viewAllVersionForm:previousVersionsTable').width = (relTblWidth-17)+"px";
				document.getElementById('resultHeaderTable').width = (relTblWidth-17)+"px";
				setColumnWidth('resultHeaderTable','22%:6%:6%:20%:14%:8%:7%:18%');
				setColumnWidth('viewAllVersionForm:previousVersionsTable','22%:6%:6%:20%:14%:8%:7%:18%');
			}
		}else{
			setColumnWidth('resultHeaderTable','22%:6%:6%:20%:14%:8%:7%:18%');
			setColumnWidth('viewAllVersionForm:previousVersionsTable','22%:6%:6%:20%:14%:8%:7%:18%');
		}

	getObj('viewAllVersionForm:pageIdHidden').value = 'searchVersionPage';
	fillSpace();
	tableObj = getObj('viewAllVersionForm:previousVersionsTable');
	if(tableObj.rows.length == 0)
		window.close();
	function getProductView(page){
	var viewtype='viewVersion'
		getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductIdForView');
	 //Code change for product tree rendering optimization
	var url=page+getUrl()+"?productKey="+document.getElementById('viewAllVersionForm:selectedProductIdForView').value+'&type='+viewtype+'&temp='+Math.random()+'&reloadTree=Y';
	var returnvalue=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
	//	document.getElementById('viewAllVersionForm:viewLink').click();	
}

	function getProductEdit(){
	getFromDataTableToHidden('viewAllVersionForm:previousVersionsTable','selectedProductKey','viewAllVersionForm:selectedProductId');
	window.dialogArguments.document.getElementById('searchForm:selectedProductId').value = document.getElementById('viewAllVersionForm:selectedProductId').value;
	window.dialogArguments.document.getElementById('searchForm:editLink').click();
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
	
</script>
</HTML>
