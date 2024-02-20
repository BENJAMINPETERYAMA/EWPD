<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/benefitComponent/BenefitComponentViewAllVersions.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Benefit Component View All Versions</TITLE>
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
<base target="_self" />
</HEAD>
<f:view>
	<BODY onkeypress="return false;">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td><h:inputHidden id="dummy"
				value=" #{BenefitComponentSearchBackingBean.breadCrumpViewAll}"></h:inputHidden>
			<jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include></td>
		</tr>

		<tr>
			<td><h:form styleClass="form" id="benefitComponentViewAllForm">
				<table width="1200" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<!-- Space for Tree  Data	-->

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>
						<h:inputHidden id="view"
							value="#{BenefitComponentBackingBean.viewAll}" /> <!-- Table containing Tabs -->
						<!-- End of Tab table --> <!--	Start of Table for actual Data	-->
						<DIV id="panel2">
						<DIV id="panel2Header" class="tabTitleBar"
							style="position:relative; cursor:hand;">Available Versions</DIV>

						<DIV id="panel2Content" class="tabContentBox"
							style="position:relative;font-size:10px;width:1200;overflow-x:hidden;overflow-y:hidden;"><BR>

						<TABLE id="newTable" cellpadding="0" cellspacing="0" border="0" width="100%">
							<h:outputText value="No More Versions"
								rendered="#{BenefitComponentBackingBean.searchResultList == null}"
								styleClass="dataTableColumnHeader">
							</h:outputText>
							<TR>
								<TD>
								<DIV id="resultHeaderDiv" style="width=100%;">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<td align="left" ><h:outputText value="Name"></h:outputText>
											</td>
											<td align="left" ><h:outputText value="Version"></h:outputText>
											</td>
											<td align="left" ><h:outputText value="Line Of Business"></h:outputText>
											</td>
											<td align="left" ><h:outputText value="Description"></h:outputText>
											</td>
											<td align="left"><h:outputText value="Status"></h:outputText>
											</td>
											<td align="left" ><h:outputText value="Effective Date"></h:outputText></td>
											<td align="left" ><h:outputText value="Expiry Date"></h:outputText>
											</td>
											<td align="left" >&nbsp;</td>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD>
								<div id="searchResultdataTableDiv"
									style="height:252px;width=100%;overflow-y:scroll;position:relative;z-index:1;font-size:10px;"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1"
									bgcolor="#cccccc"
									rendered="#{BenefitComponentBackingBean.searchResultList != null}"
									value="#{BenefitComponentBackingBean.searchResultList}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
									width="100%">
									<h:column>

										<h:outputText id="bcname" value="#{singleValue.name}"></h:outputText>
										<h:graphicImage id="lockImage"
											url="../../images/lock_icon.jpg" alt="Locked"
											rendered="#{singleValue.state.lockedByUser}"></h:graphicImage>
										<h:inputHidden id="hiddenBCKey"
											value="#{singleValue.benefitComponentId}"></h:inputHidden>
										<h:inputHidden id="hiddenBCName" value="#{singleValue.name}"></h:inputHidden>
									</h:column>
									<h:column>

										<h:outputText id="version" value="#{singleValue.version}"></h:outputText>
										<h:inputHidden id="hiddenBCVersion"
											value="#{singleValue.version}"></h:inputHidden>
										<h:inputHidden id="hiddenBCParentId"
											value="#{singleValue.benefitComponentParentId}"></h:inputHidden>
									</h:column>
									<h:column>
											<h:outputText id="lob" value="#{singleValue.lob}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="descriptionId"
											value="#{singleValue.description}">
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="status" value="#{singleValue.status}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="effectiveDate"
											value="#{singleValue.effectiveDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="expiryDate"
											value="#{singleValue.expiryDate}">
											<f:convertDateTime pattern="MM/dd/yyyy" />
										</h:outputText>
									</h:column>
									<h:column>

										<h:commandButton alt="View" title="View" id="viewButton"
											image="../../images/view.gif"
											rendered="#{singleValue.state.validForView}"
											onclick="setKeyForView(); return false;"></h:commandButton>

										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="Copy" title="Copy" id="copyButton"
											image="../../images/copy.gif"
											rendered="#{singleValue.state.validForCopy}"
											onclick="setActionToSearchPage('copy');return false;"></h:commandButton>

										<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										<h:commandButton alt="Delete" title="Delete" id="deleteButton"
											image="../../images/delete.gif"
											onclick="deleteAction();return false;"
											rendered="#{singleValue.state.validForDelete}">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>


										<h:commandButton alt="Edit" title="Edit" id="Edit"
											image="../../images/edit.gif" value="Edit"
											rendered="#{singleValue.state.editableByUser}"
											onclick="setActionToSearchPage('edit');return false;">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Schedule For Test" title="Schedule For Test" id="sendtotest"
											image="../../images/schedule_test.gif" value="Send To Test"
											onclick="scheduleTest();return false;"
											rendered="#{singleValue.state.validForTest && singleValue.status != 'SCHEDULED_FOR_TEST'}">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>
										<h:commandButton alt="Approve" title="Approve" id="approve"
											image="../../images/approved.gif" value="Approve"
											rendered="#{singleValue.state.validForApprovalCompletion}"
											onclick="approve(); return false;">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Reject" title="Reject" id="reject"
											image="../../images/rejected.gif" value="Approve"
											rendered="#{singleValue.state.validForApprovalCompletion}"
											onclick="reject(); return false;">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="testPass" title="testPass" id="testPass"
											image="../../images/test_successful.gif" value="testPass"
											onclick="testPass();return false;"
											rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="testFail" title="testFail" id="testFail"
											image="../../images/test_failed.gif" value="testFail"
											onclick="testFail();return false;"
											rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Publish" title="Publish" id="publish"
											image="../../images/publish.gif" value="Publish"
											onclick="publishBC();return false;"
											rendered="#{singleValue.state.validForPublish}">
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
										</h:commandButton>

										<h:commandButton alt="Checkout" title="Checkout" id="checkout"
											image="../../images/checkOut.gif" value="Checkout"
											onclick="checkoutFromViewPage('checkout');return false;"
											rendered="#{singleValue.state.validForCheckOut}">
										</h:commandButton>

										<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
											image="../../images/lockgreen.gif" value="Unlock"
											onclick="unlockBenefitComponent();return false;"
											rendered="#{singleValue.state.validForUnlock}">
										</h:commandButton>

									</h:column>
								</h:dataTable></div>
								</TD>
							</TR>

						</TABLE>

						</DIV>
						</DIV>

						<!--	End of Page data	--></TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="fromPage"
					value="#{BenefitComponentSearchBackingBean.fromPage}"></h:inputHidden>
				<h:inputHidden id="hiddenBenefitComponentId"
					value="#{BenefitComponentSearchBackingBean.benefitComponentId}" />
				<h:inputHidden id="hiddenBenefitComponentName"
					value="#{BenefitComponentSearchBackingBean.benefitComponentName}" />
				<h:inputHidden id="hiddenBenefitComponentVersion"
					value="#{BenefitComponentSearchBackingBean.version}" />
				<h:inputHidden id="hiddenBenefitComponentParentId"
					value="#{BenefitComponentSearchBackingBean.benefitComponentParentId}" />
				<h:inputHidden id="selectedBenefitComponentKey"
					value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentKey}" />
				<h:inputHidden id="selectedBenefitComponentName"
					value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentName}" />
				<h:inputHidden id="selectedBenefitComponentVersion"
					value="#{BenefitComponentSearchBackingBean.selectedBenefitComponentVersion}" />
				<h:inputHidden id="hiddenBCType"
					value="#{BenefitComponentSearchBackingBean.componentType}"></h:inputHidden>
				<h:commandLink id="checkOutButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.checkOutBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="publishButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.publishBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="scheduleTestButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.scheduleForTestBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="approveButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.approveBenefitComponentFromViewAll}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="rejectButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.rejectBenefitComponentViewAll}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testPassButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.testPassBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testFailButton"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.testFailBenefitComponent}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="deleteButtonAction"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.deleteBenefitComponentVersion}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="unlockLink"
					style="display:none; visibility: hidden;"
					action="#{BenefitComponentSearchBackingBean.unLockAction}">
					<f:verbatim />
				</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom_view.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>

<script>
if(null != document.getElementById('benefitComponentViewAllForm:searchResultTable:0:lob')){
	formatTildaToCommaForBenefit('benefitComponentViewAllForm:searchResultTable');
}
function formatTildaToCommaForBenefit(value){
	var i;
	var n;
	n = document.getElementById(value).rows.length;
	for(i=0; i < n; i++)
		{
			formatTildaToComma('benefitComponentViewAllForm:searchResultTable:'+i+':lob');
		}
}
	document.getElementById('benefitComponentViewAllForm:fromPage').value = "viewAll";
	if(document.getElementById('benefitComponentViewAllForm:searchResultTable')!= null){
	var relTblWidth = document.getElementById('benefitComponentViewAllForm:searchResultTable').offsetWidth;
	if(document.getElementById('benefitComponentViewAllForm:searchResultTable').rows.length < 11){	
		//document.getElementById('searchResultdataTableDiv').style.width = relTblWidth+"px";
		document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
		setColumnWidth('benefitComponentViewAllForm:searchResultTable', '20%:6%:6%:19%:13%:7%:7%:15%');
		setColumnWidth('resultHeaderTable','20%:6%:6%:19%:13%:7%:7%:15%');
	}else{
//document.getElementById('searchResultdataTableDiv').width = relTblWidth-17+"px";
//document.getElementById('resultHeaderDiv').width = relTblWidth-17+"px";
		//document.getElementById('resultHeaderDiv').style.width = relTblWidth+"px";
		setColumnWidth('benefitComponentViewAllForm:searchResultTable','20%:6%:6%:19%:13%:7%:7%:15%');
		setColumnWidth('resultHeaderTable','20%:6%:6%:19%:13%:7%:7%:15%');
		syncTables('resultHeaderTable','benefitComponentViewAllForm:searchResultTable');
	}
}
	
		else {
              var headerDiv = document.getElementById('resultHeaderDiv');
              headerDiv.visibility ='hidden';
            }   


	function unlockBenefitComponent(){

			getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:selectedBenefitComponentKey');
			getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:selectedBenefitComponentName');
			getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:selectedBenefitComponentVersion');
			submitLink('benefitComponentViewAllForm:unlockLink');
			window.returnValue = "refresh";
		}

	/*function syncTables(){
		var a = document.getElementById('benefitComponentViewAllForm:viewAllVersionTable').offsetWidth;		
		var relTblWidth = document.getElementById('benefitComponentViewAllForm:viewAllVersionTable').offsetWidth;	
		var obj = document.getElementById('resultHeaderTable');	
		document.getElementById('resultHeaderTable').width = relTblWidth+ 'px';	
	}*/
	
	function deleteAction(){
			var message = 'Are you sure you want to delete the selected benefit component version?';
				if (confirm(message) ){
					 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:selectedBenefitComponentKey');
					 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:selectedBenefitComponentName');
					 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:selectedBenefitComponentVersion');
					submitLink('benefitComponentViewAllForm:deleteButtonAction');
					window.returnValue = "refresh";
					return true;
				} else
				return false;
		}

		function setActionToSearchPage(action) {
			
			getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:selectedBenefitComponentKey');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:selectedBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:selectedBenefitComponentVersion');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCParentId','benefitComponentViewAllForm:hiddenBenefitComponentParentId');
				
			idToSearchPage = document.getElementById('benefitComponentViewAllForm:selectedBenefitComponentKey').value;
			nameToSearchPage = document.getElementById('benefitComponentViewAllForm:selectedBenefitComponentName').value;
			versionToSearchPage = document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentVersion').value;
			parentIdToSearchPage = document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentParentId').value;		
			window.returnValue = action+"~"+idToSearchPage+"~"+nameToSearchPage+"~"+versionToSearchPage+"~"+parentIdToSearchPage;
			if(action == 'checkout'){
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentId').value = idToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentName').value = nameToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:hiddenBenefitComponentVersion').value = versionToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:checkOutVersionButton').click();	
			}
			if(action == 'copy'){
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentKey').value = idToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentName').value = nameToSearchPage
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentVersion').value = versionToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:copyButton').click();
			}
			if(action == 'edit'){
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentKey').value = idToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentName').value =nameToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentVersion').value = versionToSearchPage;
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:selectedBenefitComponentParentId').value = parentIdToSearchPage;						
						window.dialogArguments.document.getElementById('benefitComponentSearchForm:editButton').click();
			}
				
			
			window.close();

		}
		 function checkoutFromViewPage(action)
			{
			getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:selectedBenefitComponentKey');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:selectedBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:selectedBenefitComponentVersion');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCParentId','benefitComponentViewAllForm:hiddenBenefitComponentParentId');

			idToSearchPage = document.getElementById('benefitComponentViewAllForm:selectedBenefitComponentKey').value;
			nameToSearchPage = document.getElementById('benefitComponentViewAllForm:selectedBenefitComponentName').value;
			versionToSearchPage = document.getElementById('benefitComponentViewAllForm:selectedBenefitComponentVersion').value;
			parentIdToSearchPage = document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentParentId').value;			
			window.returnValue = action+"~"+idToSearchPage+"~"+nameToSearchPage+"~"+versionToSearchPage+"~"+parentIdToSearchPage;
			
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

		function scheduleTest(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:scheduleTestButton');
			 window.returnValue = "refresh";
		}

		function publishBC(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:publishButton');
			 window.returnValue = "refresh";

		}

		function testPass(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:testPassButton');
			 window.returnValue = "refresh";
		}

		function testFail(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:testFailButton');
			 window.returnValue = "refresh";
		}
		
		function setKeyForView() {
		getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 //Code change for benefit component tree rendering optimization
			 var url = '../benefitComponent/benefitComponentView.jsp'+getUrl()+'?benefitcomponentkey='+document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentId').value+'&benfitVersion='+document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentVersion').value+'&benfitName='+document.getElementById('benefitComponentViewAllForm:hiddenBenefitComponentName').value+'&bcType='+document.getElementById('benefitComponentViewAllForm:hiddenBCType').value+'&reloadTree=Y';
			 newWinForView =window.showModalDialog(url+ "&temp=" + Math.random(),"","dialogHeight:800px;dialogWidth:1000px;resizable=false;status=no;");	
		}	
		function approve(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:approveButton');
			 window.returnValue = "refresh";
		}
		function reject(){
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCKey','benefitComponentViewAllForm:hiddenBenefitComponentId');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCName','benefitComponentViewAllForm:hiddenBenefitComponentName');
			 getFromDataTableToHidden('benefitComponentViewAllForm:searchResultTable','hiddenBCVersion','benefitComponentViewAllForm:hiddenBenefitComponentVersion');
			 submitLink('benefitComponentViewAllForm:rejectButton');
			 window.returnValue = "refresh";
		}
</script>
</HTML>

