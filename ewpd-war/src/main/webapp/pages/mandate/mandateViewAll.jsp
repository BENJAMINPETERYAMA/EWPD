<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/mandate/MandateViewAll.java" --%><%-- /jsf:pagecode --%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
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

<TITLE>Page Title</TITLE>
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
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<base target="_self">
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<h:inputHidden id="viewAllMandateKey"
								value="#{mandateBackingBean.viewAllMandateKey}" />
				<TD><jsp:include page="../navigation/top_viewAllVersions.jsp"></jsp:include></TD>
			</TR>
			
			<TR>
				<TD><h:form styleClass="form" id="viewAllVersionsForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>
							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
							<!-- Table containing Tabs -->
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">View All Versions</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>
							<TABLE cellpadding="0" cellspacing="0" border="0" width="100%">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText
													value="Mandate Name"></h:outputText></TD>
												<TD align="left"><h:outputText
													value="Mandate Type"></h:outputText></TD>
												<TD align="left"><h:outputText
													value="Jurisdiction"></h:outputText></TD>
												<td align="left"><h:outputText value="Group Size"></h:outputText></td>
												<TD align="left"><h:outputText
													value="Funding Arrangement"></h:outputText></TD>
												<TD align="left"><h:outputText value="Version"></h:outputText></TD>
												<TD align="left"><h:outputText
													value="Effective Date"></h:outputText></TD>
												<TD align="left"><h:outputText
													value="Expiry Date"></h:outputText></TD>
												<TD align="left"></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD>
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;">
									<!-- Search Result Data Table --> <h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableHeader"
										id="searchResultTable" var="singleValue" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										rendered="#{mandateBackingBean.viewAllMandateList!= null}"
										value="#{mandateBackingBean.viewAllMandateList}">
										<h:column>
											<h:outputText id="mandatename"
												value="#{singleValue.mandateName}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="mandateType"
												value="#{singleValue.mandateTypeDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="jurisdiction"
												value="#{singleValue.jurisdictionDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="groupSize"
												value="#{singleValue.groupSizeDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="FundArrangements"
												value="#{singleValue.fundingArrangementDesc}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="NumberOfVersions"
												value="#{singleValue.version}"></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="EffectiveDate"
												value="#{singleValue.effectiveDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>

										</h:column>
										<h:column>
											<h:outputText id="expiryDate"
												value="#{singleValue.expiryDate}"><f:convertDateTime pattern="MM/dd/yyyy" /></h:outputText>
											<h:inputHidden id="mandateId"
												value="#{singleValue.mandateId}"></h:inputHidden>
											<h:inputHidden id="name"
												value="#{singleValue.mandateName}"></h:inputHidden>
											<h:inputHidden id="versionNo"
												value="#{singleValue.version}"></h:inputHidden>
										</h:column>




										<h:column>
											<h:commandButton alt="View" title="View" id="viewButton"
												image="../../images/view.gif"
												rendered="#{singleValue.state.validForView}"
												onclick="setKeyForView(); return false;"></h:commandButton>
											<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
											<h:commandButton alt="Copy" title="Copy" id="basicCopy"
												image="../../images/copy.gif" value="Copy"
												rendered="#{singleValue.state.validForCopy}"
												onclick="setActionToSearchPage('Copy'); return false;"></h:commandButton>
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="Edit" title="Edit" id="basicEdit"
												image="../../images/edit.gif" value="Edit"
												rendered="#{singleValue.state.editableByUser}"
												onclick="setActionToSearchPage('Edit'); return false;"></h:commandButton>
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="CheckOut" title="CheckOut" id="checkOut"
												image="../../images/checkOut.gif" value="CheckOut"
												onclick="setActionToSearchPage('CheckOut');return false;"
												rendered="#{singleValue.state.validForCheckOut}">
											</h:commandButton>
											<h:commandButton alt="Send For Test" title="Send For Test" id="sendForTest"
												rendered="#{singleValue.state.validForTest && singleValue.status != 'SCHEDULED_FOR_TEST'}"
												image="../../images/schedule_test.gif" value="Send For Test"
												onclick="doAction('SendForTest');return false;">
											</h:commandButton>
											<h:commandButton alt="Publish" title="Publish" id="publish"
												rendered="#{singleValue.state.validForPublish}"
												image="../../images/publish.gif" value="Publish"
												onclick="doAction('Publish');return false;">
											</h:commandButton>
											<h:commandButton alt="testPass" title="testPass" id="testPass"
												image="../../images/test_successful.gif" value="testPass"
												onclick="doAction('TestPass');return false;"
												rendered="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}">
											</h:commandButton>
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
										<h:commandButton alt="testFail" title="testFail"
											id="testFail" image="../../images/test_failed.gif" 
											value="testFail" onclick="doAction('TestFail');return false;"
											rendered ="#{singleValue.status == 'SCHEDULED_FOR_TEST' ? true : false}"></h:commandButton>
											<f:verbatim> &nbsp;&nbsp;</f:verbatim>
											<h:commandButton alt="Delete" title="Delete" id="basicDelete"
												image="../../images/delete.gif" value="Delete"
												rendered="#{singleValue.state.validForDelete}"
												onclick="deleteConfirm();return false;"></h:commandButton>
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
							</TD>
						</TR>
					</TABLE>
	<h:inputHidden id="hiddenMandateIdForEdit"
					value="#{mandateBackingBean.mandateId}"></h:inputHidden>
				<h:inputHidden id="hiddenMandateNameForEdit"
					value="#{mandateBackingBean.mandateName}"></h:inputHidden>
				<h:inputHidden id="hiddenVersionNoForEdit"
					value="#{mandateBackingBean.version}"></h:inputHidden>
				<h:inputHidden id="hiddenMandateId"
					value="#{searchMandateBackingBean.mandateId}"></h:inputHidden>
				<h:inputHidden id="hiddenMandateName"
					value="#{searchMandateBackingBean.mandateName}"></h:inputHidden>
				<h:inputHidden id="hiddenVersionNo"
					value="#{searchMandateBackingBean.versionNo}"></h:inputHidden>
				<h:inputHidden id="hiddenAction"
					value="#{searchMandateBackingBean.action}"></h:inputHidden>
				<h:commandLink id="publishMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.publish}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="sendForTestMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.sendForTest}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testPassMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.testPass}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="testFailMandate"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.testFail}">
					<f:verbatim />
				</h:commandLink>
				<h:commandLink id="deleteLink"
					style="display:none; visibility: hidden;"
					action="#{searchMandateBackingBean.deleteMandate}">
					<f:verbatim />
				</h:commandLink>

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom_view.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script>
	// Space for page realated scripts
	if(document.getElementById('viewAllVersionsForm:searchResultTable')!= null){
		setColumnWidth('resultHeaderTable','10%:10%:10%:10%:10%:10%:10%:10%:20%');
		setColumnWidth('viewAllVersionsForm:searchResultTable','10%:10%:10%:10%:10%:10%:10%:10%:20%');
	}else {
          var headerDiv = document.getElementById('resultHeaderDiv');
          headerDiv.style.visibility = 'hidden';
          var headerDiv2 = document.getElementById('searchResultdataTableDiv');
          headerDiv2.visibility ='hidden';
    }  
		function setActionToSearchPage(action) {
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','mandateId','viewAllVersionsForm:hiddenMandateId');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateName');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','versionNo','viewAllVersionsForm:hiddenVersionNo');
			idToSearchPage = document.getElementById('viewAllVersionsForm:hiddenMandateId').value;
			nameToSearchPage = document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			versionToSearchPage = document.getElementById('viewAllVersionsForm:hiddenVersionNo').value;
			window.returnValue = action+"~"+idToSearchPage+"~"+nameToSearchPage+"~"+versionToSearchPage;
			window.close();
		}

		function setKeyForView() {
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','mandateId','viewAllVersionsForm:hiddenMandateId');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateName');
			var url = "../mandate/mandateView.jsp"+getUrl()+"?mandatekey="+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"&mandatename="+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			newWinForView =window.showModalDialog(url,"view","dialogHeight:800px;dialogWidth:1000px;resizable=false;status=no;");	
		}

		function deleteConfirm(){
			var message = 'Are you sure you want to delete the selected mandate version?';
			if (confirm(message) ){
				document.getElementById('viewAllVersionsForm:hiddenAction').value = 'viewAll';
				getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','mandateId','viewAllVersionsForm:hiddenMandateIdForEdit');
				getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateNameForEdit');
				getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateName');
				getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','versionNo','viewAllVersionsForm:hiddenVersionNo');
				setValueToHiddenFieldFromDataTable('viewAllVersionsForm:searchResultTable', 'mandateId', 'viewAllVersionsForm:hiddenMandateId', 'viewAllVersionsForm:deleteLink');
				window.returnValue = 'search'+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
				return true;
			} else
				return false;
		}

	function doAction(value) {
			document.getElementById('viewAllVersionsForm:hiddenAction').value = 'viewAll';
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','mandateId','viewAllVersionsForm:hiddenMandateIdForEdit');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateNameForEdit');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','name','viewAllVersionsForm:hiddenMandateName');
			getFromDataTableToHidden('viewAllVersionsForm:searchResultTable','versionNo','viewAllVersionsForm:hiddenVersionNo');
		if(value=='Publish'){
			setValueToHiddenFieldFromDataTable('viewAllVersionsForm:searchResultTable', 'mandateId', 'viewAllVersionsForm:hiddenMandateId', 'viewAllVersionsForm:publishMandate');
			window.returnValue = 'search'+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			return true;
		} else if(value == 'SendForTest'){
			setValueToHiddenFieldFromDataTable('viewAllVersionsForm:searchResultTable', 'mandateId', 'viewAllVersionsForm:hiddenMandateId', 'viewAllVersionsForm:sendForTestMandate');
			window.returnValue = 'search'+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			return true;
		} else if(value == 'TestPass'){
			setValueToHiddenFieldFromDataTable('viewAllVersionsForm:searchResultTable', 'mandateId', 'viewAllVersionsForm:hiddenMandateId', 'viewAllVersionsForm:testPassMandate');
			window.returnValue = 'search'+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			return true;
		} else if(value == 'TestFail'){
			setValueToHiddenFieldFromDataTable('viewAllVersionsForm:searchResultTable', 'mandateId', 'viewAllVersionsForm:hiddenMandateId', 'viewAllVersionsForm:testFailMandate');
			window.returnValue = 'search'+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateId').value+"~"+document.getElementById('viewAllVersionsForm:hiddenMandateName').value;
			return true;
		}
		return false;
	}



		
		</script>
</HTML>
