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
<TITLE>Search Service Type Mapping</TITLE>
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
                "Administration >> Blue Exchange >> Service Type Mapping >> Locate");
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
								style="position:relative; cursor:hand;"><B>Locate Criteria</B></DIV>
							<DIV id="searchPanelContent" class="tabContentBox"
								style="position:relative;"><br />
							<TABLE width="900" cellpadding="2" border="0" class="outputText">
								<TBODY>

<!-- Header Rule-->
										<TR>
											<TD width="165"> <h:outputText value="Header Rule"/> </TD>
											<TD width="192"> 
												<h:inputHidden id="headerHidden" value="#{serviceTypeMappingSearchBackingBean.headerRule}"/>
												<div id="headerDiv" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="535">
												<h:commandButton alt="" image="../../images/select.gif" 
												onclick="ewpdModalWindow_ewpd('../blueexchange/benefitRuleMultiSelectPopUp.jsp','headerDiv','searchForm:headerHidden',2,2); return false;" />
											</TD>
										</TR>
<!-- Service Class High -->
										<TR>
											<TD width="165"><h:outputText value="Applicable to Blue Exchange"/> </TD>
											<TD width="192"><h:selectOneMenu id="applicable_txt" styleClass="formInputField" tabindex="7"
											value="#{serviceTypeMappingSearchBackingBean.isApplicable}"  onchange="hideEB03();" tabindex="5">
											<f:selectItems id="applicableList" value="#{serviceTypeMappingSearchBackingBean.applicableList}" />
									</h:selectOneMenu>
											</TD>
										</TR>
<!-- EB03 Identifiers  -->
										<TR id="EB03">
											<TD width="165"> <h:outputText value="EB03-Service Type Code"/> </TD>
											<TD width="192"> 
												<h:inputHidden id="eb03Hidden" value="#{serviceTypeMappingSearchBackingBean.eb03Identifier}"/>
												<div id="eb03Div" class="selectDataDisplayDiv"></div>
											</TD>
											<TD width="535">
												<h:commandButton alt="" image="../../images/select.gif" 
												onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'31'+'&parentCatalog='+'EB03'+'&title='+'EB03-Service Type Code','eb03Div','searchForm:eb03Hidden',2,2);return false;" />
											</TD>
										</TR>


									<TR>
										<TD colspan="3">&nbsp;</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Locate"
											style="cursor: hand"
											action="#{serviceTypeMappingSearchBackingBean.locate}"
											tabindex="11"></h:commandButton></TD>
									</TR>

								</TBODY>
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
									<DIV id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="100%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="Header rule Id"></h:outputText></TD>
												<TD align="left"><h:outputText value="Rule Description"></h:outputText></TD>
												<TD align="left"><h:outputText value="Applicable to Blue Exchange"></h:outputText></TD>
												<TD align="left"><h:outputText value="EB03-Service Type Code"></h:outputText></TD>
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
										style="height:260px;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{serviceTypeMappingSearchBackingBean.searchResultList != null}"
										value="#{serviceTypeMappingSearchBackingBean.searchResultList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<h:outputText id="HeaderRuleId"
												value="#{singleValue.ruleId}"></h:outputText>
											<h:inputHidden id="RuleDataHidden"
														value="#{singleValue.ruleId}"></h:inputHidden>
										</h:column>
										<h:column>
											<h:outputText id="ruleDesc"
												value="#{singleValue.ruleShortDesc}"></h:outputText>
										</h:column>
										<h:column>
											<h:outputText id="applicableBX"
												value="#{singleValue.applicableToBX}"></h:outputText>
											
										</h:column>
										<h:column>
											<h:outputText id="serviceCodes"
												value="#{singleValue.serviceCodesString}"></h:outputText>
										</h:column>
                                       
										<h:column>
											<f:verbatim> &nbsp; </f:verbatim>
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="showPopupForServiceTypeMapping('../blueexchange/serviceTypeMappingView.jsp');return false;"></h:commandButton>
											<f:verbatim>   &nbsp; </f:verbatim>
											
											<h:commandButton alt="Edit" id="editButton"
												image="../../images/edit.gif"
												onclick="submitDataTableButton('searchForm:searchResultTable','RuleDataHidden','searchForm:ruleID','searchForm:editLink');return false;"
												></h:commandButton>
											<f:verbatim>   &nbsp; </f:verbatim>
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
	
	copyHiddenToDiv_ewpd('searchForm:eb03Hidden','eb03Div',2,2);
	copyHiddenToDiv_ewpd('searchForm:headerHidden','headerDiv',2,2);
		
	
		var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'360',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('searchForm:searchResultTable') != null) {
		setColumnWidth('resultHeaderTable','15%:20%:15%:40%:10%');
		setColumnWidth('searchForm:searchResultTable','15%:20%:15%:40%:10%');	
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
hideEB03();
function hideEB03(){
		var selectObj = getObj('searchForm:applicable_txt');    // Get the ComboBox for contract Type
			var selIndex = selectObj.selectedIndex; 
             // Get the Selected index in contract type
   		var selectItem = selectObj[selIndex];             // Get the item object which is selected in Combo.
    	var eb03Row = getObj('EB03');                    // Get the Table row object which contain base contract.
  			if(selectItem.value == 'N') {
				eb03Row.style.display='none';
			}else{
				eb03Row.style.display='';
			}           
}
       

	//Open a popup window to show ServiceType.
	function showPopupForServiceTypeMapping(page){
		getFromDataTableToHidden('searchForm:searchResultTable','RuleDataHidden','searchForm:ruleID');
		var url=page+getUrl()+"?ruleID="+escape(document.getElementById('searchForm:ruleID').value)
					+'&temp='+Math.random();			
		var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
		if(returnvalue == undefined)
		{
	//		getObj('ContractBasicSearchForm:searchButton').click();
		}
	}

</script>
<script language="JavaScript">
	fillSpace();
</script>
</HTML>
