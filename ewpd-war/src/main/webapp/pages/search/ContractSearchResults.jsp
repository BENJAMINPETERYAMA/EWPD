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

<TITLE>Search Engine</TITLE>
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
		<table width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td><h:form styleClass="form" id="benefitComponentCreateForm">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<TR>

								<TD width="273" valign="top" class="leftPanel">


									<DIV class="treeDiv"><jsp:include
											page="../navigation/tree.jsp"></jsp:include></DIV>

								</TD>

								<TD colspan="2" valign="top" class="ContentArea">
									<TABLE>
										<TBODY>
											<tr>
												<TD><w:message></w:message></TD>
											</tr>
										</TBODY>
									</TABLE>
									<div id="panel2Header">
										<b> <h:outputText id="associationText"
												value="#{searchResultsBackingBean.associationBreadCrumb}">
											</h:outputText></b>
										<h:inputHidden id="associationChain"
											value="#{searchResultsBackingBean.associationBreadCrumb}" />
									</div> <BR> <!-- Table containing Tabs -->
									<div id="resultHeaderDiv" style="width: 100%;">

										<h:panelGroup
											rendered="#{searchResultsBackingBean.attachedObjectValidate}">
											<h:outputText value="Attached to "></h:outputText>
											<h:selectOneMenu id="attachedObjectList"
												value="#{searchResultsBackingBean.currentAttachedObject}"
												rendered="#{searchResultsBackingBean.attachedObjectValidate}"
												onchange="associatedObjectSelectedIndex(this);">
												<f:selectItems
													value="#{searchResultsBackingBean.attachmentObjectTypeList}" />
											</h:selectOneMenu>
											<f:verbatim>
												<p />
											</f:verbatim>
										</h:panelGroup>

										<div style="text-align: center;">
											<h:panelGrid id="panelTable"
												binding="#{searchResultsBackingBean.panel}">
											</h:panelGrid>
										</div>
										<!--  table starts -->

										<h:dataTable rowClasses="dataTableEvenRow,dataTableOddRow"
											styleClass="outputText" headerClass="dataTableColumnHeader"
											var="item" cellpadding="3" width="100%" cellspacing="1"
											bgcolor="#cccccc"
											value="#{searchResultsBackingBean.contracts}">
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Id"></h:outputText>
														<h:commandButton id="asc1" alt="Sort Ascending"
															title="Sort Ascending"
															image="#{searchResultsBackingBean.columnOneImageAsc}"
															onclick="setFieldsForSort('Contract_Id','ASC','1',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc1" alt="Sort Descending"
															title="Sort Descending"
															image="#{searchResultsBackingBean.columnOneImageDesc}"
															onclick="setFieldsForSort('Contract_Id','DESC','1',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="sample" value="#{item.contractId}"></h:outputText>
												<h:inputHidden id="hiddenContractId"
													value="#{item.contractId}" />
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Type"></h:outputText>
														<h:commandButton id="asc2" alt="Sort Ascending"
															title="Sort Ascending"
															image="#{searchResultsBackingBean.columnTwoImageAsc}"
															onclick="setFieldsForSort('Contract_Type','ASC','2',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc2" alt="Sort Descending"
															title="Sort Descending"
															image="#{searchResultsBackingBean.columnTwoImageDesc}"
															onclick="setFieldsForSort('Contract_Type','DESC','2',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="Term" value="#{item.contractType}"></h:outputText>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Effective Date"></h:outputText>
														<h:commandButton id="asc3" alt="Sort Ascending"
															title="Sort Ascending"
															image="#{searchResultsBackingBean.columnThreeImageAsc}"
															onclick="setFieldsForSort('Contract_Effective_date','ASC','3',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc3" alt="Sort Descending"
															title="Sort Descending"
															image="#{searchResultsBackingBean.columnThreeImageDesc}"
															onclick="setFieldsForSort('Contract_Effective_date','DESC','3',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="ed" value="#{item.startDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
												<h:inputHidden id="hiddenEffectiveDate"
													value="#{item.startDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:inputHidden>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Expiry Date"></h:outputText>
														<h:commandButton id="asc4" alt="Sort Ascending" title="Sort Ascending"
															image="#{searchResultsBackingBean.columnFourImageAsc}"
															onclick="setFieldsForSort('Contract_Expiry_Date','ASC','4',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc4" alt="Sort Descending" title="Sort Descending"
															image="#{searchResultsBackingBean.columnFourImageDesc}"
															onclick="setFieldsForSort('Contract_Expiry_Date','DESC','4',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="st" value="#{item.endDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:outputText>
												<h:inputHidden id="hiddenExpiryDate" value="#{item.endDate}">
													<f:convertDateTime pattern="MM/dd/yyyy" />
												</h:inputHidden>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Version No"></h:outputText>
														<h:commandButton id="asc6" alt="Sort Ascending" title="Sort Ascending"
															image="#{searchResultsBackingBean.columnSixImageAsc}"
															onclick="setFieldsForSort('Contract_version','ASC','6',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc6" alt="Sort Descending" title="Sort Descending"
															image="#{searchResultsBackingBean.columnSixImageDesc}"
															onclick="setFieldsForSort('Contract_version','DESC','6',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="versionNo" value="#{item.versionNo}"></h:outputText>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Status"></h:outputText>
														<h:commandButton id="asc5" alt="Sort Ascending" title="Sort Ascending"
															image="#{searchResultsBackingBean.columnFiveImageAsc}"
															onclick="setFieldsForSort('Contract_Status','ASC','5',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
														<h:commandButton id="desc5" alt="Sort Descending" title="Sort Descending"
															image="#{searchResultsBackingBean.columnFiveImageDesc}"
															onclick="setFieldsForSort('Contract_Status','DESC','5',this);"
															action="#{searchResultsBackingBean.sort}"
															style="margin-left:3">
														</h:commandButton>
													</h:panelGroup>
												</f:facet>
												<h:outputText id="Qualifier" value="#{item.status}"></h:outputText>
											</h:column>
											<h:column>
												<f:facet name="header">
													<h:panelGroup>
														<h:outputText value="Actions"></h:outputText>
													</h:panelGroup>
												</f:facet>
												<h:commandButton id="image" alt="View Contract" title="View Contract"
													image="../../images/view.gif"
													rendered="#{searchResultsBackingBean.authorizedForView == true}"
													onclick="setKeyForView();"
													action="#{searchResultsBackingBean.viewContractObject}">
												</h:commandButton>
												<f:verbatim> &nbsp; </f:verbatim>
												<h:commandButton id="image2" alt="More Actions" title="More Actions"
													image="../../images/more_actions.gif"
													action="#{searchResultsBackingBean.viewMoreContractObject}"
													onclick="setKeyForView();moreActions(this);">
												</h:commandButton>

											</h:column>

											<!--  table ends -->
										</h:dataTable>
								</TD>
							</TR>

						</TABLE>

						<!-- Space for hidden fields -->

						<h:inputHidden id="numberOfPages"
							value="#{searchResultsBackingBean.numberOfPages}" />
						<h:inputHidden id="checkForView"
							value="#{searchResultsBackingBean.checkForView}" />
						<h:inputHidden id="query"
							value="#{searchResultsBackingBean.query}" />
						<h:inputHidden id="contractid"
							value="#{searchResultsBackingBean.contractid}" />
						<h:inputHidden id="effectiveDate"
							value="#{searchResultsBackingBean.effectiveDate}" />
						<h:inputHidden id="expiryDate"
							value="#{searchResultsBackingBean.expiryDate}" />
						<h:inputHidden id="clickedIndex"
							value="#{searchResultsBackingBean.clickedIndex}" />
						<h:inputHidden id="fieldToSort"
							value="#{searchResultsBackingBean.fieldToSort}" />
						<h:inputHidden id="sortOrder"
							value="#{searchResultsBackingBean.contractSortOrder}" />
						<h:inputHidden id="sortColumn"
							value="#{searchResultsBackingBean.contractSortColumn}" />
						<h:inputHidden id="association"
							value="#{searchResultsBackingBean.association}" />
						<h:inputHidden id="selectedIndex"
							value="#{searchResultsBackingBean.selectedIndex}" />

						<h:commandLink id="attachmentObjectIndex"
							style="display:none; visibility: hidden;"
							action="#{searchResultsBackingBean.associatedObjectSelectedIndex}">
							<f:verbatim />
						</h:commandLink>


						<!-- End of hidden fields  -->

					</h:form></td>
			</tr>
			<tr>
				<td><%@ include file="../navigation/bottom.jsp"%></td>
			</tr>
		</table>
	</BODY>
</f:view>
<script language="JavaScript">
	if (document.getElementById('benefitComponentCreateForm:checkForView').value == 1) {
	    contractSerchResultsActions();
		/* window
				.showModalDialog(
						'../contract/contractBasicInfoView.jsp'
								+ getUrl()
								+ '?'
								+ document
										.getElementById('benefitComponentCreateForm:query').value
								+ '&temp=' + Math.random(), '',
						'dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;'); */
	}

	function moreActions(object) {
		var objectName = object.name;
		var urlParts = objectName.split(':');
		var namVarId = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2]
				+ ":" + "hiddenContractId";
		var namEFD = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":"
				+ "hiddenEffectiveDate";
		var namEXD = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":"
				+ "hiddenExpiryDate";
		var objId = document.getElementsByName(namVarId)[0];
		var objEFD = document.getElementsByName(namEFD)[0];
		var objEXD = document.getElementsByName(namEXD)[0];
		document.getElementById('benefitComponentCreateForm:contractid').value = objId.value;
		document.getElementById('benefitComponentCreateForm:effectiveDate').value = objEFD.value;
		document.getElementById('benefitComponentCreateForm:expiryDate').value = objEXD.value;

	}
	function setKeyForView() {
		var e = window.event;
		if (!e || e == undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		document.getElementById('benefitComponentCreateForm:clickedIndex').value = rowcount;
		return true;
	}

	function setFieldsForSort(field, order, column, object) {
		var ascImageUrl = "pushup1.jpg";
		var descImageUrl = "pushdown1.jpg";
		var objectUrl = object.src;
		var urlParts = objectUrl.split('/');
		var length = urlParts.length;
		objectUrl = urlParts[length - 1];
		if (null == object) {
			return false;
		}
		if (ascImageUrl == objectUrl || descImageUrl == objectUrl) {
			object.disabled = true;
			return false;
		}
		document.getElementById('benefitComponentCreateForm:fieldToSort').value = field;
		document.getElementById('benefitComponentCreateForm:sortOrder').value = order;
		document.getElementById('benefitComponentCreateForm:sortColumn').value = column;
		return true;
	}
	function associatedObjectSelectedIndex(object) {
		var n = object.selectedIndex;
		if (n == null) {
			return false;
		}
		document.getElementById('benefitComponentCreateForm:selectedIndex').value = n;
		document.getElementById(
				'benefitComponentCreateForm:attachmentObjectIndex').click();
		return true;
	}
</script>
<form name="printForm">
	<input id="currentPrintPage" type="hidden" name="currentPrintPage"
		value="contractsearchresultprint" />
</form>
</HTML>
