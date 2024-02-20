<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<TITLE>Product Search</TITLE>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
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
<script language="JavaScript" src="../../js/prototype.js"></script>
<script language="JavaScript" src="../../js/rico.js"></script>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();
</script>

</HEAD>


<f:view>
	<body onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
	<h:inputHidden id="hidden1"
		value="#{productSearchBackingBean.hiddenInit}"></h:inputHidden>
	<hx:scriptCollector id="scriptCollector1">

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>

				<TD><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Product Configuration >> Product >> Locate");
    %> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form id="searchForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<TR>
									<TD width="200"></TD>
									<TD width="200"></TD>
								</TR>
							</TABLE>

							<!--Tab Ends-->
							<DIV id="accordionTest" style="margin:5px;">
							<DIV id="searchPanel">
							<DIV id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><br />
							<TABLE cellpadding="2" border="0" class="outputText">
								<TBODY>
									<TR>
										<TD valign="top"><h:outputText id="lineOfBusiness"
											value="Line of Business"></h:outputText></TD>
										<h:inputHidden id="txtlineOfBusinessHidden"
											value="#{productSearchBackingBean.lineOfBusiness}"></h:inputHidden>
										<TD align="left" nowrap>
										<div id="txtlineOfBusiness" class="selectDataDisplayDiv"></div>
										</TD>
										<TD><h:commandButton alt="Select" title="Select" id="lineOfBusinessButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','txtlineOfBusiness','searchForm:txtlineOfBusinessHidden',2,2);return false;"
											tabindex="1">
										</h:commandButton></TD>

									</TR>
									<TR>
										<TD valign="top"><h:outputText id="businessEntity"
											value="Business Entity"></h:outputText></TD>
										<h:inputHidden id="txtbusinessEntityHidden"
											value="#{productSearchBackingBean.businessEntity}"></h:inputHidden>
										<TD align="left">
										<div id="txtbusinessEntity" class="selectDataDisplayDiv"></div>

										</TD>
										<TD><h:commandButton alt="businessEntity" title="businessEntity"
											id="businessEntitButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','txtbusinessEntity','searchForm:txtbusinessEntityHidden',2,2);
																				return false;"
											tabindex="2">
										</h:commandButton></TD>

									</TR>
									<TR>
										<TD valign="top"><h:outputText id="businessGroup"
											value="Business Group"></h:outputText></TD>
										<h:inputHidden id="txtbusinessGroupHidden"
											value="#{productSearchBackingBean.businessGroup}"></h:inputHidden>
										<TD align="left">
										<div id="txtbusinessGroup" class="selectDataDisplayDiv"></div>
										</TD>
										<TD><h:commandButton alt="businessGroup" title="businessGroup"
											id="businessGroupButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','txtbusinessGroup','searchForm:txtbusinessGroupHidden',2,2);;
																				return false;"
											tabindex="3">
										</h:commandButton></TD>

									</TR>
<!-- CARS START -->					
									<TR>
										<TD valign="top"><h:outputText id="marketBusinessUnit"
											value="Market Business Unit"></h:outputText></TD>
										<h:inputHidden id="txtmarketBusinessUnitHidden"
											value="#{productSearchBackingBean.marketBusinessUnit}"></h:inputHidden>
										<TD align="left">
										<div id="txtmarketBusinessUnit" class="selectDataDisplayDiv"></div>
										</TD>
										<TD><h:commandButton alt="marketBusinessUnit" title="marketBusinessUnit"
											id="marketBusinessUnitButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','txtmarketBusinessUnit','searchForm:txtmarketBusinessUnitHidden',2,2);;
																				return false;"
											tabindex="3">
										</h:commandButton></TD>

									</TR>	
<!-- CARS END -->
									<TR>
										<TD valign="top"><h:outputText id="productName" value="Name"></h:outputText></TD>
										<TD align="left"><h:inputText styleClass="formInputField"
											id="txtProdName" maxlength="40"
											value="#{productSearchBackingBean.productName}" tabindex="4"></h:inputText>&nbsp;
										</TD>
										<TD width="20"></TD>

									</TR>

									<TR valign="top">
										<TD width="150"><h:outputText value="Effective Date"
											id="effectiveDate" /></TD>
										<TD width="150"><h:inputText styleClass="formInputField"
											id="effectiveDate_txt" maxlength="10" tabindex="5"
											value="#{productSearchBackingBean.effectiveDate}" /> <span
											class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="20"><A href="#"
											onclick="cal1.select('searchForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;"
											name="anchor1" id="anchor1"
											title="cal1.select('searchForm:effectiveDate_txt','anchor1','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="6" /> </A></TD>
									</TR>
									<TR valign="top">

										<TD width="150"><h:outputText value="Expiry Date"
											id="expiryDate" /></TD>
										<TD width="150"><h:inputText styleClass="formInputField"
											id="expiryDate_txt" maxlength="10" tabindex="7"
											value="#{productSearchBackingBean.expiryDate}" /> <span
											class="dateFormat">(mm/dd/yyyy)</span></TD>
										<TD valign="top" width="20"><A href="#"
											onclick="cal1.select('searchForm:expiryDate_txt','anchor2','MM/dd/yyyy'); return false;"
											name="anchor2" id="anchor2"
											title="cal1.select(document.forms[0].searchForm:expiryDate_txt,'anchor2','MM/dd/yyyy'); return false;">
										<h:commandButton image="../../images/cal.gif"
											style="cursor: hand" alt="Cal" tabindex="8" /> </A></TD>
									</TR>

									<TR valign="top">

										<TD width="150"><h:outputText id="productStructure"
											value="Product Structure" /></TD>
										<h:inputHidden id="productStructHidden"
											value="#{productSearchBackingBean.productStructure}"></h:inputHidden>
										<TD width="150">
										<div id="productStructDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="20"><h:commandButton alt="productStructure" title="productStructure"
											id="productStructureButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../product/searchProductStructurePopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'PRODUCT STRUCTURE','productStructDiv','searchForm:productStructHidden',2,1);
																return false;"
											tabindex="9">
										</h:commandButton></TD>
									</TR>



									<TR valign="top">

										<TD width="150"><h:outputText id="productFamily"
											value="Product Family" /></TD>
										<h:inputHidden id="productFamilyHidden"
											value="#{productSearchBackingBean.productFamily}"></h:inputHidden>
										<TD width="150">
										<div id="productFamilyDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD width="20"><h:commandButton alt="productFamily" title="productFamily"
											id="productFamilyButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../product/productFamilyPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Product Family','productFamilyDiv','searchForm:productFamilyHidden',2,2);
																return false;"
											tabindex="10">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Locate"
											style="cursor: hand"
											action="#{productSearchBackingBean.productSearch}"
											tabindex="11"></h:commandButton></TD>
									</TR>

								</TBODY>
							</TABLE>

							<TABLE border="0" cellpadding="0" cellspacing="0" width="1200">
								<TR>
									<TD></TD>
									<TD align="right"></TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;overflow:auto;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:1200;overflow-x:hidden;overflow-y:scroll;"><BR>
							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Name"></h:outputText></TD>
												<TD align="left"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left"><h:outputText value="Line Of Business"></h:outputText></TD>
												<TD align="left"><h:outputText value="Description"></h:outputText></TD>
												<TD align="left"><h:outputText value="Status"></h:outputText></TD>
												<TD align="left"><h:outputText value="Effective Date"></h:outputText></TD>
												<TD align="left"><h:outputText value="Expiry Date"></h:outputText></TD>
												<TD align="left">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:280px; width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{productSearchBackingBean.searchResultList != null}"
										value="#{productSearchBackingBean.searchResultList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<h:outputText id="prodName"
												value="#{singleValue.productName}"></h:outputText>
											<h:inputHidden id="hiddenProductName"
														value="#{singleValue.productName}"></h:inputHidden>
										    <h:graphicImage id="lockImage"  url="../../images/lock_icon.jpg"   alt="Locked" rendered ="#{singleValue.state.lockedByUser}"></h:graphicImage>
										</h:column>
										<h:column>
											<h:outputText id="productVersion"
												value="#{singleValue.version}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="productLOB"
												value="#{singleValue.lineOfBusiness}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="productDescription"
												value="#{singleValue.productDescription}"></h:outputText>
										</h:column>
                                        <h:column>
											<h:outputText id="productStatus"
												value="#{singleValue.status}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="effectDate"
												value="#{singleValue.effectiveDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="expDate" value="#{singleValue.expiryDate}">
												<f:convertDateTime pattern="MM/dd/yyyy" />
											</h:outputText>
											<h:inputHidden id="productKey"
												value="#{singleValue.productKey}"></h:inputHidden>
											<h:inputHidden id="productType"
												value="#{singleValue.productType}"></h:inputHidden>
										</h:column>
										
										<h:column>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="showPopup('../product/productGeneralInformationView.jsp');return false;"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="View All versions" title="View All versions" id="viewAllButton"
												image="../../images/notes.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="showPopupForAllVersions('../product/viewProductVersions.jsp');return false;"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="Copy" title="Copy" id="copyButton"
												image="../../images/copy.gif"
												rendered="#{singleValue.state.validForCopy}"
												onclick="copyAction();return false;"></h:commandButton>
											
											<h:outputText value="" id="a2spaceSpan"
												rendered="#{singleValue.state.editableByUser}"></h:outputText>
											<h:commandButton alt="Edit" title="Edit" id="editButton"
												image="../../images/edit.gif"
												onclick="editAction();return false;"
												rendered="#{singleValue.state.editableByUser}"></h:commandButton>
											<h:outputText value="" id="a3spaceSpan"
												rendered="#{singleValue.state.validForTest}"></h:outputText>
											<h:commandButton alt="SendToTest" title="SendToTest" id="sendToTestButton"
												image="../../images/schedule_test.gif"
												onclick="sendFortest();return false;"
												rendered="#{singleValue.state.validForTest}"></h:commandButton>
											<h:outputText value="" id="a4spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="TestPass" title="TestPass" id="testPassButton"
												image="../../images/test_successful.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:testPassLink');return false;"
												rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
											<h:outputText value="" id="a5spaceSpan"
												rendered="#{singleValue.state.validForTestCompletion}"></h:outputText>
											<h:commandButton alt="TestFail" title="TestFail" id="testFailButton"
												image="../../images/test_failed.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:testFailLink');return false;"
												rendered="#{singleValue.state.validForTestCompletion}"></h:commandButton>
											<h:outputText value="" id="a6spaceSpan"
												rendered="#{singleValue.state.validForApproval}"></h:outputText>
											<h:commandButton alt="Schedule To Approve" title="Schedule To Approve" id="approveButton"
												image="../../images/scheduled_approval.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:scheduleForApprovalLink');return false;"
												rendered="#{singleValue.state.validForApproval}"></h:commandButton>
											<h:outputText value="" id="a7spaceSpan"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
											<h:commandButton alt="Approve" title="Approve" id="approvalButton"
												image="../../images/approved.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:approveLink');return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value="" id="a8spaceSpan"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:outputText>
											<h:commandButton alt="Reject" title="Reject" id="rejectButton"
												image="../../images/rejected.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:rejectLink');return false;"
												rendered="#{singleValue.state.validForApprovalCompletion}"></h:commandButton>
											<h:outputText value="" id="a9spaceSpan"
												rendered="#{singleValue.state.validForPublish}"></h:outputText>
											<h:commandButton alt="Publish" title="Publish" id="publishButton"
												image="../../images/publish.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:publishLink');return false;"
												rendered="#{singleValue.state.validForPublish}"></h:commandButton>
											<h:outputText value="" id="a10spaceSpan"
												rendered="#{singleValue.state.validForCheckOut}"></h:outputText>
											<h:commandButton alt="CheckOut" title="CheckOut" id="checkoutButton"
												image="../../images/checkOut.gif"
												onclick="checkOutAction();return false;"
												rendered="#{singleValue.state.validForCheckOut}">
											</h:commandButton>
											<h:outputText value="" id="a11spaceSpan" rendered="false"></h:outputText>
											<h:commandButton alt="Schedule To Production" title="Schedule To Production"
												id="scheduleToProductionButton"
												image="../../images/scheduled_production1.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:transferLink');return false;"
												rendered="false"></h:commandButton>
											<h:outputText value="" id="a1spaceSpan"
												rendered="#{singleValue.state.validForDelete}"></h:outputText>
											<h:commandButton alt="Delete" title="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick="if(confirmTask('#{singleValue.deleteMessage}')) submitDataTableButton('searchForm:searchResultTable','productKey','searchForm:selectedProductKey','searchForm:deleteLink');return false;"
												rendered="#{singleValue.state.validForDelete}"></h:commandButton>
											<h:outputText value="" id="a1spaceSpan09"
												rendered="#{singleValue.state.validForUnlock}"></h:outputText>
											<h:commandButton alt="Unlock" title="Unlock" id="unlockAdmin"
												image="../../images/lockgreen.gif" value="Unlock"
												onclick="unlockProduct();return false;"
												rendered="#{singleValue.state.validForUnlock}">
											</h:commandButton>
										</h:column>
									</h:dataTable></DIV>
									</TD>
								</TR>
								<TR>
									<TD></TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
					</TABLE>
					<h:inputHidden id="pageIdHidden"
						value="#{productSearchBackingBean.pageId}" />
					<h:inputHidden id="selectedProductId"
						value="#{productGeneralInformationBackingBean.selectedIdFromSearch}" />
					<h:inputHidden id="selectedProductType"
						value="#{productSearchBackingBean.selectedProductTypeFromSearch}" />
					<h:inputHidden id="selectedProductKey"
						value="#{productSearchBackingBean.selectedKeyFromSearch}" />
					<h:commandLink id="editLink"
						style="display:none; visibility: hidden;"
						action="#{productGeneralInformationBackingBean.editAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="deleteLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.deleteAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="copyLink"
						style="display:none; visibility: hidden;"
						action="#{productGeneralInformationBackingBean.copyAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="testPassLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.testPassAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="testFailLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.testFailAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="checkOutLink"
						style="display:none; visibility: hidden;"
						action="#{productGeneralInformationBackingBean.checkOutAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="sendToTestLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.sendToTestAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="publishLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.publishAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="scheduleForApprovalLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.scheduleForApprovalAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="transferLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.transferAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="approveLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.approveAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="rejectLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.rejectAction}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="unlockLink"
						style="display:none; visibility: hidden;"
						action="#{productSearchBackingBean.unLockAction}">
						<f:verbatim />
					</h:commandLink>

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</body>
</f:view>
<script language="JavaScript">

	document.getElementById('searchForm:txtProdName').focus(); //for on load default selection

		var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('searchForm:searchResultTable') != null) {
		setColumnWidth('resultHeaderTable','17%:6%:6%:18%:20%:8%:8%:17%');
		setColumnWidth('searchForm:searchResultTable','17%:6%:6%:18%:20%:8%:8%:17%');	
			 showResultsTab();
		syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');
		}
		syncTables('searchForm:resultHeaderTable','searchForm:searchResultTable');	
		copyHiddenToDiv_ewpd('searchForm:txtlineOfBusinessHidden','txtlineOfBusiness',2,2); 
	 	copyHiddenToDiv_ewpd('searchForm:txtbusinessGroupHidden','txtbusinessGroup',2,2); 
		copyHiddenToDiv_ewpd('searchForm:txtbusinessEntityHidden','txtbusinessEntity',2,2); 
		copyHiddenToDiv_ewpd('searchForm:txtmarketBusinessUnitHidden','txtmarketBusinessUnit',2,2); 
		copyHiddenToDiv_ewpd('searchForm:productStructHidden','productStructDiv',2,1); 	 
		copyHiddenToDiv_ewpd('searchForm:productFamilyHidden','productFamilyDiv',2,2); 		  
</script>
<script language="JavaScript">
	getObj('searchForm:pageIdHidden').value = 'searchPage';
	fillSpace();
	function showPopup(page)
	{	
	getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductId');
     //Code change for product tree rendering optimization
	var url=page+getUrl()+"?productKey="+document.getElementById('searchForm:selectedProductId').value+'&temp='+Math.random()+'&reloadTree=Y';
	var returnvalue=window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=true;status=no;");
	}	
	function editAction(){
		getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductId');
		submitLink('searchForm:editLink')
	}
	function copyAction(){
		getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductId');
		submitLink('searchForm:copyLink')
	}
	function checkOutAction(){
		getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductId');
		submitLink('searchForm:checkOutLink')
	}
	function showPopupForAllVersions(page)
		{	

		var e =  window.event;
		var button_id = e.srcElement.id;	
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		var str = "searchForm:searchResultTable:"+rowcount+":hiddenProductName";
		var PName = document.getElementById(str).value;

		getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductId');
		 //Code change for product tree rendering optimization		
		var url=page+getUrl()+"?productKey="+document.getElementById('searchForm:selectedProductId').value+'&PName='+PName+'&temp='+Math.random()+'&reloadTree=Y';
		var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
		/* if(returnvalue == undefined)
			getObj('searchForm:basicSearch').click();*/
		} 	
	function sendFortest()
		{	
		getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductKey');
		getFromDataTableToHidden('searchForm:searchResultTable','productType','searchForm:selectedProductType');
		submitLink('searchForm:sendToTestLink')
		}	
	function unlockProduct()
	{
	getFromDataTableToHidden('searchForm:searchResultTable','productKey','searchForm:selectedProductKey');
	submitLink('searchForm:unlockLink')
	}
</script>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="productSearchResultPrint" >
	</form>
</HTML>
