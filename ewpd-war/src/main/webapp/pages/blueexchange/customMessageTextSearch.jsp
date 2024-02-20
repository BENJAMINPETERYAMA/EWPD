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
<TITLE>Custom Message Text Search</TITLE>
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

<body onkeypress="return submitOnEnterKey('searchForm:basicSearch');">
<f:view>

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>

				<TD><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Administration >> Blue Exchange >> Custom Message Text >> Locate");
    %> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form id="searchForm">
					<TBODY>
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
										<TD valign="top" width="34%"><h:outputText id="headerRuleIdId"
											value="Header Rule ID "></h:outputText></TD>
										<h:inputHidden id="headerRuleIdHidden"
											value="#{customMessageSearchBackingBean.headerRuleForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtHeaderRuleId" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%" align="left"><h:commandButton alt="Select" id="headerRuleIdButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../blueexchange/benefitRuleMultiSelectPopUp.jsp'+getUrl(),'txtHeaderRuleId','searchForm:headerRuleIdHidden',2,2); return false; ">

										</h:commandButton></TD>

									</TR>

									<TR>
										<TD valign="top" width="34%"><h:outputText id="spsId"
											value="SPS Parameter ID "></h:outputText></TD>
										<h:inputHidden id="spsIdHidden"
											value="#{customMessageSearchBackingBean.spsParameterForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtspsId" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%" align="left"><h:commandButton alt="Select" id="spsIdButton"
											image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../blueexchange/spsParameterFilterMultiSearchPopup.jsp'+getUrl()+'?lookUpAction='+'9'+'&parentCatalog='+'reference'+'&title='+'SPS Parameter'+'&temp=' + Math.random(), 'txtspsId', 'searchForm:spsIdHidden', 2, 2);return false;">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="messageText" value="Message Text"></h:outputText></TD>
										<TD align="left" width="24%"><h:inputText styleClass="formInputField"
											id="txtPMessageText" maxlength="40"
											value="#{customMessageSearchBackingBean.messageText}" tabindex="4"></h:inputText>&nbsp;
										</TD>
										<TD width="42%"></TD>

									</TR>
            						<TR>
										<TD valign="top" width="34%"><h:outputText id="messageIndicatorTxtId" value="Message Required Indicator"></h:outputText></TD>
										<TD height="9" width="24%"><h:selectOneMenu id="messageIndicator"
											styleClass="formInputField"
											value="#{customMessageSearchBackingBean.messageIndicatorTxt}">
											<f:selectItems id="messageIndicatorList"
												value="#{ReferenceDataBackingBeanCommon.messageIndicatorList}" />
											</h:selectOneMenu></TD>
										<TD width="42%"></TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Locate"
											style="cursor: hand"
											action="#{customMessageSearchBackingBean.customMessageSearch}"
											></h:commandButton></TD>
									</TR>
								</TBODY>
							</TABLE>

							<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
								<TR>
									<TD></TD>
									<TD align="right"></TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Locate Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>
							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv" style="width:100%;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Header Rule ID"></h:outputText></TD>
												<TD align="left"><h:outputText value="SPS ID"></h:outputText></TD>
												<TD align="left"><h:outputText value="Message Text"></h:outputText></TD>
												<TD align="left"><h:outputText value="Message Required Indicator"></h:outputText></TD>
 											    <TD align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchSpsDiv" style="height:280px;overflow:auto;width:100%;">
									<DIV id="searchResultdataTableDiv"
										style="height:200px;width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="0" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{customMessageSearchBackingBean.searchResults != null}"
										value="#{customMessageSearchBackingBean.searchResults}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
												<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="headerId"
												value="#{singleValue.headerRuleId}" ></h:outputText>
											<h:inputHidden id="hiddenheaderRuleId"
														value="#{singleValue.headerRuleId}"></h:inputHidden>
										</h:column>
										<h:column>
												<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="spsParameterId"
												value="#{singleValue.spsParameterId}" ></h:outputText>
											<h:inputHidden id="hiddenParameterId"
														value="#{singleValue.spsParameterId}"></h:inputHidden>
										</h:column>
										<h:column>
												<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="messagetextId"
												value="#{singleValue.messagetext}"></h:outputText>
											<h:inputHidden id="hiddenMessagetext"
														value="#{singleValue.messagetext}"></h:inputHidden>
										</h:column>
										<h:column>
												<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="eb02ValueId"
												value="#{singleValue.messageReqIndicator}"></h:outputText>
											<h:inputHidden id="hiddeneb02Value"
														value="#{singleValue.messageReqIndicator}"></h:inputHidden>
										</h:column>
										<h:column>
												<f:verbatim>&nbsp;</f:verbatim>
												<h:commandButton alt="View" id="viewButton"
													image="../../images/view.gif"
													onclick="setKeyForView();return false;">
												</h:commandButton>	
												<f:verbatim>&nbsp;</f:verbatim>
												<h:commandButton alt="Edit" id="Edit"
													image="../../images/edit.gif" value="Edit"
													onclick="editAction();return false;">
												</h:commandButton>
												<f:verbatim>&nbsp;</f:verbatim>
												<h:commandButton alt="Delete" id="deleteButton"
												image="../../images/delete.gif"
												onclick="confirmDeletion(); return false;">
												</h:commandButton>
										</h:column>
									</h:dataTable></DIV>
									</DIV>
									</TD>
								</TR>
							</TABLE>
							</DIV>
							</DIV>
							</DIV>
							</DIV>
							</TD>
						</TR>
					</TABLE>
				<h:inputHidden id="targetHiddenToStoreSpsID"
					value="#{customMessageSearchBackingBean.spsId}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreheaderRuleID"
					value="#{customMessageSearchBackingBean.headerRuleId}"></h:inputHidden>
				<h:commandLink id="deleteCustomMessageId"
					style="display:none; visibility: hidden;"
					action="#{customMessageSearchBackingBean.deleteCustomMessage}">
				<f:verbatim /></h:commandLink>
				<h:commandLink id="editLink"
					style="display:none; visibility: hidden;"
					action="#{customMessageBackingBean.loadCustomMessage}">
				<f:verbatim /></h:commandLink>
</TBODY>				
</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
</f:view>
<script language="JavaScript">
function editAction(){
	getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenParameterId', 'searchForm:targetHiddenToStoreSpsID');
	getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenheaderRuleId', 'searchForm:targetHiddenToStoreheaderRuleID');
	submitLink('searchForm:editLink');
}
function setKeyForView() {
		getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenParameterId', 'searchForm:targetHiddenToStoreSpsID');
		getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenheaderRuleId', 'searchForm:targetHiddenToStoreheaderRuleID');
		var currentDate = new Date();
		var currentTime = currentDate.getTime();
		var headerRuleId = document.getElementById('searchForm:targetHiddenToStoreheaderRuleID').value;
		var spsParameterId = document.getElementById('searchForm:targetHiddenToStoreSpsID').value;
		var url = "../blueexchange/customMessageTextView.jsp"+getUrl()+"?"+"spsId="+spsParameterId+"&headerRuleId="+headerRuleId+'&date='+currentTime;
		newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=false;status=no;");	
	
		}	


copyHiddenToDiv_ewpd('searchForm:headerRuleIdHidden','txtHeaderRuleId',2,2);
copyHiddenToDiv_ewpd('searchForm:spsIdHidden','txtspsId',2,2);
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});

		var newWinForView;
		if(document.getElementById('searchForm:searchResultTable') != null) {
		setColumnWidth('resultHeaderTable','20%:20%:20%:20%:20%');
		setColumnWidth('searchForm:searchResultTable','20%:20%:20%:20%:20%');	
		syncTables('resultHeaderTable','searchForm:searchResultTable');
		showResultsTab();

		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}

	function confirmDeletion(){				
		var message = "Are you sure you want to delete ?"		
		if(window.confirm(message)){
			getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenParameterId', 'searchForm:targetHiddenToStoreSpsID');
			getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenheaderRuleId', 'searchForm:targetHiddenToStoreheaderRuleID');
		submitLink('searchForm:deleteCustomMessageId');

		}
		else{			 
				return false;
		}
	}
</script>
</body>
</HTML>
