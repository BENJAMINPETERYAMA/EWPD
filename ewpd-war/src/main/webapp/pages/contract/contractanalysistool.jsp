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
<TITLE>Contract Analysis Tool</TITLE>
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

		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>

				<TD><%
        javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        httpReq.setAttribute("breadCrumbText",
                "Contract Development >> Validate >> Validation criteria");
    %> <jsp:include page="../navigation/top.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form id="searchForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE >
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
								style="position:relative; cursor:hand;"><B>Validation Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><br />
							<TABLE width="900" cellpadding="2" border="0" class="outputText">
								<TBODY>

<!-- Header Rule-->
										<TR>
											<TD width="165"> <h:outputText 
											styleClass="#{contractRuleValidationBackingBean.contractIdreq? 'mandatoryError': 'mandatoryNormal'}"
											value="Contract Id*"/> </TD>
											<TD width="192"> 
												<h:inputHidden id="cntHidden" value="#{contractRuleValidationBackingBean.contractIdCriteria}"/>
												<div id="cntDiv" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="535">
												<h:commandButton alt="" image="../../images/select.gif" 
												onclick="loadSearchPopup('searchContractId','Contract Id',
                                                         'Contract Id Popup','cntDiv','searchForm:cntHidden','effectiveDateDiv','searchForm:effectiveDateHidden'); return false;" />
											</TD>
										</TR>
<!-- Service Class High -->
										<TR>

											<TD width="165"> <h:outputText 
											styleClass="#{contractRuleValidationBackingBean.effectiveDatereq? 'mandatoryError': 'mandatoryNormal'}"
											value="Effective Date*"/> </TD>
											<h:inputHidden id="effectiveDateHidden"
											value="#{contractRuleValidationBackingBean.effectiveDate}"></h:inputHidden>
											<TD width="178">
											<div id="effectiveDateDiv" class="selectDataDisplayDiv"></div>
	
											</TD>
											<TD width="301"><h:commandButton alt="Effective Date"
												id="baseContractDtButton" image="../../images/select.gif" tabindex="7" 
												onclick="loadDatePopup('searchContractEffectiveDates','Contract Effective Date',
                                                         'Contract Effective Date Popup','effectiveDateDiv', 'searchForm:effectiveDateHidden');return false;">
											</h:commandButton></TD>

										</TR>
<!-- EB03 Identifiers  -->
										<TR id="EB03">
											<TD width="165"> <h:outputText 
											styleClass="#{contractRuleValidationBackingBean.contractRulereq? 'mandatoryError': 'mandatoryNormal'}"
											value="Contract Validation Rules*"/> </TD>
											<TD width="192"> 
												<h:inputHidden id="cntValHid" value="#{contractRuleValidationBackingBean.contractvalidationCriteria}"/>
												<div id="cntValHidDiv" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="535">
												<h:commandButton alt="" image="../../images/select.gif" 
												onclick="loadContractRulesPopup('searchContractRules','Contract Rules',
                                                         'Contarct Rules Popup','cntValHidDiv','searchForm:cntValHid'); return false;" />
											</TD>
										</TR>


									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Validate"
											style="cursor: hand"
											action="#{contractRuleValidationBackingBean.validateContract}"
											tabindex="11"></h:commandButton></TD>
									</TR>

								</TBODY>
							</TABLE>

						
							</DIV>
							</DIV>
							<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Validation Results</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;width:100%;"><BR>

							<TABLE cellpadding="0" cellspacing="0" width="100%" border="0">
								<TR>
									<TD>
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Rule"></h:outputText></TD>
												<TD align="left"><h:outputText value="Results"></h:outputText></TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{contractRuleValidationBackingBean.searchList != null}"
										value="#{contractRuleValidationBackingBean.searchList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<h:outputText id="HeaderRuleId"
												value="#{singleValue.ruleCategoryName}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="ruleDesc"
												value="#{singleValue.validationMessage}"></h:outputText>
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
								<h:inputHidden   id = "ruleID"  value = "#{serviceTypeMappingBackingBean.ruleId}">     </h:inputHidden>
								<h:commandLink id="editLink" style="display:none; visibility: hidden;" action="#{serviceTypeMappingBackingBean.editAction}"> <f:verbatim/></h:commandLink>

							</TD>
						</TR>
					</TABLE>
				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</body>
</f:view>
<script language="JavaScript">
	
	copyHiddenToDiv_ewpd('searchForm:cntHidden','cntDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:cntValHid','cntValHidDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:effectiveDateHidden','effectiveDateDiv',2,1); 	
	
		var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'360',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('searchForm:searchResultTable') != null) {
		setColumnWidth('resultHeaderTable','30%:70%');
		setColumnWidth('searchForm:searchResultTable','30%:70%');	
		showResultsTab();
	
		}else{
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
			
		}
		if(document.getElementById('searchForm:searchResultTable') != null){
			document.getElementById('searchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}
		
function syncTables(){
			var relTblWidth = document.getElementById('searchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}	

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp,peerDiv,peerHiddenComp){
	ewpdModalWindow_ewpd( '../popups/searchFilterPopupSingle.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2,peerDiv,peerHiddenComp);
}


function loadContractRulesPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/SearchContractRulesPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadDatePopup(queryName,headerName,titleName,displayDiv,outComp)
{	
	var contractCompositeId = document.getElementById('searchForm:cntHidden').value;
	var contractSysId = getContractSysIdFromTildaSeperatedValue(contractCompositeId);
	if(contractSysId == false){
		alert('Please select a Contract');
		return;
	}	
	ewpdModalWindow_ewpd('../contractpopups/selectContractEffectiveStartDatePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random()+ '&entityId=' +contractSysId, displayDiv,outComp,1,1);		
}

/**
	Function returns  contractSysId ,accepts a tilda seperated string of contractSysId and contractId	
**/
function getContractSysIdFromTildaSeperatedValue(contractCompositeId) {
	var values;
	if(null != contractCompositeId && contractCompositeId != undefined && contractCompositeId.length > 0){		
		values = contractCompositeId.split("~");	
		if(null != values[0] && values[0] != undefined && values[0].length > 0){
			return values[0];		
		}		 
	}
	return false;
}

</script>
<script language="JavaScript">
	fillSpace();
</script>
</HTML>
