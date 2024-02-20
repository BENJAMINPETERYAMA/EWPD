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
<TITLE>Search Indicative Mapping</TITLE>
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

			<TD><jsp:include page="../navigation/top.jsp"></jsp:include></TD>
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
						<TABLE width="900" cellpadding="2" border="0" class="outputText">
							<TBODY>

								<!-- Header Rule-->
								<TR>
									<TD width="165"><h:outputText value="Indicative" /></TD>
									<TD width="192"><h:inputHidden id="indHidden"
										value="#{indicativeMappingBackingBean.indicativeCriteria}" />
									<div id="indDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="535"><h:commandButton alt=""
										image="../../images/select.gif"
										onclick="loadIndicativeSearchPopup('searchIndicative','Indicative',
                                                         'Indicative Popup','indDiv','searchForm:indHidden'); return false;" />
									</TD>
								</TR>
								<!-- Service Class High -->
								<TR>
									<TD width="165"><h:outputText value="SPS Parameter" /></TD>
									<TD width="192"><h:inputHidden id="spsHidden"
										value="#{indicativeMappingBackingBean.spsParamCriteria}" />
									<div id="spsDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="535"><h:commandButton alt=""
										image="../../images/select.gif"
										onclick="loadMultiSearchPopup('searchSPS','SPS Parameters',
                                                         'SPS Parameter Popup','spsDiv','searchForm:spsHidden'); return false;" />
									</TD>
								</TR>
								<!-- EB03 Identifiers  -->
								<TR id="EB03">
									<TD width="165"><h:outputText value="Benefit Name" /></TD>
									<TD width="192"><h:inputHidden id="bnHidden"
										value="#{indicativeMappingBackingBean.benefitNameCriteria}" />
									<div id="bnDiv" class="selectDataDisplayDiv"></div>
									</TD>
									<TD width="535"><h:commandButton alt=""
										image="../../images/select.gif"
										onclick="loadSearchPopup('searchBenefitName','Benefit',
                                                         'Benefit Name Popup','bnDiv','searchForm:bnHidden'); return false;" />
									</TD>
								</TR>


								<TR>
									<TD colspan="3">&nbsp;</TD>
								</TR>
								<TR>
									<TD align="left" valign="top" colspan="3"><h:commandButton
										styleClass="wpdbutton" id="basicSearch" value="Locate"
										style="cursor: hand"
										action="#{indicativeMappingBackingBean.searchIndicativeMapping}"
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
											<TD align="left"><h:outputText value="Indicative"></h:outputText></TD>
											<TD align="left"><h:outputText value="Segment Number"></h:outputText></TD>
											<TD align="left"><h:outputText value="SPS Parameter"></h:outputText></TD>
											<TD align="left"><h:outputText value="Benefit Name"></h:outputText></TD>
											<TD align="left"><h:outputText value="Description"></h:outputText></TD>
											<TD align="left">&nbsp;&nbsp;&nbsp;</TD>
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
									rendered="#{indicativeMappingBackingBean.searchResults != null}"
									value="#{indicativeMappingBackingBean.searchResults}"
									rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
									width="100%">
									<h:column>
										<h:outputText id="indicative"
											value="#{singleValue.indicativeSegment}"></h:outputText>
										<h:inputHidden id="indicativeHidden"
											value="#{singleValue.indicativeSegment}"></h:inputHidden>
										<h:inputHidden id="indicativeIdHidden"
											value="#{singleValue.indicativeSegmentCode}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:outputText id="indNumber"
											value="#{singleValue.indicativeSegmentNumber}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="spsParameter"
											value="#{singleValue.spsParameter}"></h:outputText>
										<h:inputHidden id="spsparamHidden"
											value="#{singleValue.spsParameter}"></h:inputHidden>
										<h:inputHidden id="spsparamIdHidden"
											value="#{singleValue.spsParameterCode}"></h:inputHidden>
									</h:column>
									<h:column>
										<h:outputText id="BenefitName" value="#{singleValue.benefit}"></h:outputText>
										<h:inputHidden id="benefitNameHidden"
											value="#{singleValue.benefit}"></h:inputHidden>
									</h:column>

									<h:column>
										<h:outputText id="descriptionId"
											value="#{singleValue.indSegDesc}"></h:outputText>
									</h:column>

									<h:column>
										<f:verbatim> &nbsp; </f:verbatim>
										<h:commandButton alt="View" title="View" id="viewButton"
											image="../../images/view.gif"
											onclick="viewAction();return false;"></h:commandButton>
										<f:verbatim>   &nbsp;&nbsp; </f:verbatim>

										<h:commandButton alt="Edit" title="Edit" id="editButton"
											image="../../images/edit.gif"
											onclick="editIndMap(); return false;"></h:commandButton>
										<f:verbatim>   &nbsp; </f:verbatim>

										<h:commandButton alt="Delete" title="Delete" id="deleteButton"
											image="../../images/delete.gif" value="Delete"
											onclick="deleteIndMap();return false;">
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



						<h:inputHidden id="ruleID"
							value="#{serviceTypeMappingBackingBean.ruleId}">
						</h:inputHidden> <h:commandLink id="editLink"
							style="display:none; visibility: hidden;"
							action="#{serviceTypeMappingBackingBean.editAction}">
							<f:verbatim />
						</h:commandLink></TD>
					</TR>
				</TABLE>
				<h:inputHidden id="targetHiddenToStoreSegId"
					value="#{indicativeMappingBackingBean.indicativeSegment}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreSpsId"
					value="#{indicativeMappingBackingBean.spsParameter}"></h:inputHidden>
				<h:inputHidden id="targetHiddenToStoreBenefitName"
					value="#{indicativeMappingBackingBean.benefit}"></h:inputHidden>
				<h:commandLink id="deleteIndMap"
					style="display:none; visibility: hidden;"
					action="#{indicativeMappingBackingBean.deleteIndicativeMapping}">
				</h:commandLink>
				<h:commandLink id="editIndMap"
					style="display:none; visibility: hidden;"
					action="#{indicativeMappingBackingBean.locateEditIndMap}">
				</h:commandLink>
			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>
	</body>
</f:view>
<script language="JavaScript">
	
	copyHiddenToDiv_ewpd('searchForm:indHidden','indDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:spsHidden','spsDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:bnHidden','bnDiv',2,2);
	
		var newWinForView;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'360',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('searchForm:searchResultTable') != null) {
		setColumnWidth('resultHeaderTable','20%:15%:20%:15%:20%:10%');
		setColumnWidth('searchForm:searchResultTable','20%:15%:20%:15%:20%:10%');	
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

	function editIndMap(){				
			getFromDataTableToHidden('searchForm:searchResultTable', 'indicativeIdHidden', 'searchForm:targetHiddenToStoreSegId');
			getFromDataTableToHidden('searchForm:searchResultTable', 'spsparamIdHidden', 'searchForm:targetHiddenToStoreSpsId');
			getFromDataTableToHidden('searchForm:searchResultTable', 'benefitNameHidden', 'searchForm:targetHiddenToStoreBenefitName');
		    submitLink('searchForm:editIndMap');
	}
	function deleteIndMap(){				
		var message = "Are you sure you want to delete ?"		
		if(window.confirm(message)){
			getFromDataTableToHidden('searchForm:searchResultTable', 'indicativeIdHidden', 'searchForm:targetHiddenToStoreSegId');
			getFromDataTableToHidden('searchForm:searchResultTable', 'spsparamIdHidden', 'searchForm:targetHiddenToStoreSpsId');
			getFromDataTableToHidden('searchForm:searchResultTable', 'benefitNameHidden', 'searchForm:targetHiddenToStoreBenefitName');
		    submitLink('searchForm:deleteIndMap');
		}
		else{			 
				return false;
		}
	}
 
function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/indicativebnfitmultislctpopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadIndicativeSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/SearchIndicativePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}



function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function viewAction(){

 	getFromDataTableToHidden('searchForm:searchResultTable', 'indicativeIdHidden', 'searchForm:targetHiddenToStoreSegId');
	getFromDataTableToHidden('searchForm:searchResultTable', 'spsparamIdHidden', 'searchForm:targetHiddenToStoreSpsId');
	getFromDataTableToHidden('searchForm:searchResultTable', 'benefitNameHidden', 'searchForm:targetHiddenToStoreBenefitName');
	var action = 'view';
	var indSeg = document.getElementById('searchForm:targetHiddenToStoreSegId').value;
	var spsParam = document.getElementById('searchForm:targetHiddenToStoreSpsId').value;
	var benNm = document.getElementById('searchForm:targetHiddenToStoreBenefitName').value;
	 window.showModalDialog('../indicativemapping/indicativemappingview.jsp'+getUrl()+'?action='+action+ '&indSeg='+ indSeg + '&spsParam='+ spsParam + '&benNm='+ escape(benNm) +'&temp=' + Math.random(),'View Indicative Mapping','dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
}

</script>
<script language="JavaScript">
	fillSpace();
</script>
</HTML>
