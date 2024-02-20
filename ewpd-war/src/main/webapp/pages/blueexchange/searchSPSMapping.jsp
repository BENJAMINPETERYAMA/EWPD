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
<TITLE>SPSMapping Search</TITLE>
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

	<h:inputHidden id="hidden1"
		value="#{productSearchBackingBean.hiddenInit}"></h:inputHidden>

	<TABLE width="100%" cellpadding="0" cellspacing="0">
		<TR>

			<TD><%
		javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		httpReq.setAttribute("breadCrumbText",
				"Administration >> Blue Exchange >> SPS Mapping >> Locate");
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
										<TD valign="top" width="34%"><h:outputText id="spsId"
											value="SPS Parameter ID "></h:outputText></TD>
										<h:inputHidden id="spsIdHidden"
											value="#{spsMappingSearchBackingBean.spsParameterForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtspsId" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%" align="left">&nbsp;&nbsp;<h:commandButton
											alt="Select" id="spsIdButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="1"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangePopUpMultiSelect.jsp?lookUpAction='+'8'+'&parentCatalog='+'reference'+'&title='+'SPS Parameter'+'&temp=' + Math.random(), 'txtspsId','searchForm:spsIdHidden', 2, 2);return false; ">
										</h:commandButton></TD>

									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="EB01Id"
											value="EB01      - Eligibility or Benefit Information "></h:outputText></TD>
										<h:inputHidden id="EB01IdHidden"
											value="#{spsMappingSearchBackingBean.eb01ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtEB01Id" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="EBO1_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="2"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'EB01'+'&title='+'Eligibility or Benefit Information','txtEB01Id','searchForm:EB01IdHidden',2,2);return false;" />
										</TD>

									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="EBO02_ValueId"
											value="EB02      - Coverage Level Code "></h:outputText></TD>
										<h:inputHidden id="EBO02_ValueHidden"
											value="#{spsMappingSearchBackingBean.eb02ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtEBO02_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="EBO02_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="3"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'EB02'+'&title='+'Coverage Level Code','txtEBO02_Value','searchForm:EBO02_ValueHidden',2,2);return false;" />
										</TD>

									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="EB06_ValueId"
											value="EB06      - Time Period Qualifier "></h:outputText></TD>
										<h:inputHidden id="EB06_ValueHidden"
											value="#{spsMappingSearchBackingBean.eb06ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtEB06_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="EB06_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="4"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'EB06'+'&title='+'Time Period Qualifier','txtEB06_Value','searchForm:EB06_ValueHidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="III02_ValueId"
											value="EB09      - Quantity Qualifier "></h:outputText></TD>
										<h:inputHidden id="III02_ValueHidden"
											value="#{spsMappingSearchBackingBean.eb09ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtIII02_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="III02_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="5"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'EB09'+'&title='+'Quantity Qualifier','txtIII02_Value','searchForm:III02_ValueHidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD1_ValueId"
											value="HSD1      - Quantity Qualifier "></h:outputText></TD>
										<h:inputHidden id="HSD1_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd1ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtHSD1_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="24%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="HSD1_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="6"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'HSD01'+'&title='+'Quantity Qualifier','txtHSD1_Value','searchForm:HSD1_ValueHidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD2_ValueId"
											value="HSD2      - Benefit Quantity "></h:outputText></TD>
										<h:inputHidden id="HSD2_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd2ValueForList}"></h:inputHidden>
										<TD align="left" width="24%"><h:inputText
											styleClass="formInputField" maxlength="2" tabindex="7"
											id="HSD2"
											value="#{spsMappingSearchBackingBean.hsd2ValueForList}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="42%">&nbsp;&nbsp;</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD3_ValueId"
											value="HSD3      - Unit or Basis for measurement Code "></h:outputText></TD>
										<h:inputHidden id="HSD3_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd3ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtHSD3_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="HSD3_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="8"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'HSD03'+'&title='+'Unit or Basis for Measurment Code','txtHSD3_Value','searchForm:HSD3_ValueHidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD4_ValueId"
											value="HSD4      - Sample Selection Modulus "></h:outputText></TD>
										<h:inputHidden id="HSD4_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd4ValueForList}"></h:inputHidden>
										<TD align="left" width="24%"><h:inputText
											styleClass="formInputField" maxlength="2" tabindex="9"
											id="HSD4"
											value="#{spsMappingSearchBackingBean.hsd4ValueForList}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="42%">&nbsp;&nbsp;</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD5_ValueId"
											value="HSD5      - Time Period Qualifier "></h:outputText></TD>
										<h:inputHidden id="HSD5_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd5ValueForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="txtHSD5_Value" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="HSD5_ValueButton" image="../../images/select.gif"
											style="cursor: hand" tabindex="10"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangeCodesPopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'HSD05'+'&title='+'Time Period Qualifier','txtHSD5_Value','searchForm:HSD5_ValueHidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText id="HSD6_ValueId"
											value="HSD6      - Number of Periods "></h:outputText></TD>
										<h:inputHidden id="HSD6_ValueHidden"
											value="#{spsMappingSearchBackingBean.hsd6ValueForList}"></h:inputHidden>
										<TD align="left" width="24%"><h:inputText
											styleClass="formInputField" maxlength="2" tabindex="11"
											id="HSD6"
											value="#{spsMappingSearchBackingBean.hsd6ValueForList}"
											onkeypress="isNum1(this.id);" /></TD>
										<TD width="42%">&nbsp;&nbsp;</TD>
									</TR>
									<TR>
										<TD valign="top" width="34%"><h:outputText
											id="accummulatorSPSID_text" value="Accummulator SPS ID "></h:outputText></TD>
										<h:inputHidden id="accummulatorSPSID_Hidden"
											value="#{spsMappingSearchBackingBean.accumulatorSPSIDForList}"></h:inputHidden>
										<TD align="left" width="24%">
										<div id="accummulatorSPSID_Div" class="selectDataDisplayDiv"></div>
										</TD>
										<TD width="42%">&nbsp;&nbsp;<h:commandButton alt="Select"
											id="accummulatorSPSID_Button" image="../../images/select.gif"
											style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../blueexchange/blueExchangePopUpMultiSelect.jsp?lookUpAction='+'1'+'&parentCatalog='+'ACCUMULATOR REFERENCE'+'&title='+'Accumulator SPS ID','accummulatorSPSID_Div','searchForm:accummulatorSPSID_Hidden',2,2);return false;" />
										</TD>
									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Locate"
											style="cursor: hand" tabindex="12"
											action="#{spsMappingSearchBackingBean.spsMappingSearch}"
											tabindex="11"></h:commandButton></TD>
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
									<DIV id="resultHeaderDiv" style="width:99%;">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="99%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="left"><h:outputText value="SPS Id"></h:outputText></TD>
												<TD align="left"><h:outputText value="Description"></h:outputText></TD>
												<TD align="left"><h:outputText value="EB01"></h:outputText></TD>
												<TD align="left"><h:outputText value="EB02"></h:outputText></TD>
												<TD align="left"><h:outputText value="EB06"></h:outputText></TD>
												<TD align="left"><h:outputText value="EB09"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD1"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD2"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD3"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD4"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD5"></h:outputText></TD>
												<TD align="left"><h:outputText value="HSD6"></h:outputText></TD>
												<TD align="left"><h:outputText value="Accumulator SPS Id"></h:outputText></TD>
												<TD align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>

								<TR>
									<TD><!-- Search Result Data Table -->
									<DIV id="searchSpsDiv"
										style="height:280px;overflow:auto;width:100%;">
									<DIV id="searchResultdataTableDiv"
										style="height:280px;width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
										var="singleValue" cellpadding="0" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{spsMappingSearchBackingBean.searchResults != null}"
										value="#{spsMappingSearchBackingBean.searchResults}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="spsParameterId"
												value="#{singleValue.spsParameter}"></h:outputText>
											<h:inputHidden id="hiddenParameterId"
												value="#{singleValue.spsParameter}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="spsDescId"
												value="#{singleValue.spsParameterDesc}"></h:outputText>
											<h:inputHidden id="hiddenSpsDesc"
												value="#{singleValue.spsParameterDesc}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="eb01ValueId"
												value="#{singleValue.eb01Value}"></h:outputText>
											<h:inputHidden id="hiddeneb01Value"
												value="#{singleValue.eb01Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="eb02ValueId"
												value="#{singleValue.eb02Value}"></h:outputText>
											<h:inputHidden id="hiddeneb02Value"
												value="#{singleValue.eb02Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="eb06ValueId"
												value="#{singleValue.eb06Value}"></h:outputText>
											<h:inputHidden id="hiddeneb06Value"
												value="#{singleValue.eb06Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="eb09ValueId"
												value="#{singleValue.eb09Value}"></h:outputText>
											<h:inputHidden id="hiddeneb09Value"
												value="#{singleValue.eb09Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd1ValueId"
												value="#{singleValue.hsd1Value}"></h:outputText>
											<h:inputHidden id="hiddenhsd1Value"
												value="#{singleValue.hsd1Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd2ValueID"
												value="#{singleValue.hsd2Value}"></h:outputText>
											<h:inputHidden id="hiddenhsd2Value"
												value="#{singleValue.hsd2Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd3ValueID"
												value="#{singleValue.hsd3Value}"></h:outputText>
											<h:inputHidden id="hiddenhsd3Value"
												value="#{singleValue.hsd3Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd4ValueID"
												value="#{singleValue.hsd4Value}"></h:outputText>
											<h:inputHidden id="hiddenhsd4Value"
												value="#{singleValue.hsd4Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd5ValueID"
												value="#{singleValue.hsd5Value}"></h:outputText>
											<h:inputHidden id="hsd5ValueValue"
												value="#{singleValue.hsd5Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="hsd6ValueID"
												value="#{singleValue.hsd6Value}"></h:outputText>
											<h:inputHidden id="hsd6ValueValue"
												value="#{singleValue.hsd6Value}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:outputText id="accummulatorSPSID"
												value="#{singleValue.accumulatorSPSID}"></h:outputText>
											<h:inputHidden id="accummulatorSPSIDHidden"
												value="#{singleValue.accumulatorSPSID}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:commandButton alt="View" id="viewButton"
												image="../../images/view.gif"
												onclick="setKeyForView(); return false;">
											</h:commandButton>
											<f:verbatim>&nbsp;</f:verbatim>
											<h:commandButton alt="Edit" id="Edit"
												image="../../images/edit.gif" value="Edit"
												onclick="editAction(); return false;">
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
						value="#{spsMappingSearchBackingBean.selectedSpsId}"></h:inputHidden>
					<h:commandLink id="deleteSpsId"
						style="display:none; visibility: hidden;"
						action="#{spsMappingSearchBackingBean.deleteSps}">
						<f:verbatim />
					</h:commandLink>
					<h:commandLink id="editLink"
						style="display:none; visibility: hidden;"
						action="#{spsMappingBackingBean.loadSPSMappingEdit}">
						<f:verbatim />
					</h:commandLink>
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
	submitLink('searchForm:editLink');
}
copyHiddenToDiv_ewpd('searchForm:spsIdHidden','txtspsId',2,2);
copyHiddenToDiv_ewpd('searchForm:EB01IdHidden','txtEB01Id',2,2);
copyHiddenToDiv_ewpd('searchForm:EBO02_ValueHidden','txtEBO02_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:EB06_ValueHidden','txtEB06_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:III02_ValueHidden','txtIII02_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:HSD1_ValueHidden','txtHSD1_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:HSD3_ValueHidden','txtHSD3_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:HSD5_ValueHidden','txtHSD5_Value',2,2);
copyHiddenToDiv_ewpd('searchForm:accummulatorSPSID_Hidden','accummulatorSPSID_Div',2,2);

var newWinForView;
var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});

if(document.getElementById('searchForm:searchResultTable') != null) {
	setColumnWidth('resultHeaderTable','4%:12%:5%:5%:5%:5%:5%:5%:5%:5%:5%:5%:7%:7%');
	setColumnWidth('searchForm:searchResultTable','4%:12%:5%:5%:5%:5%:5%:5%:5%:5%:5%:5%:7%:7%');		
	showResultsTab();
	syncTables('resultHeaderTable','searchForm:searchResultTable');
}else{
	var headerDiv = document.getElementById('resultHeaderDiv');
	headerDiv.style.visibility = 'hidden';
}

function setKeyForView() {
	var currentDate = new Date();
	var currentTime = currentDate.getTime();
	getFromDataTableToHidden('searchForm:searchResultTable','hiddenParameterId','searchForm:targetHiddenToStoreSpsID');
	var url = "../blueexchange/spsMappingView.jsp"+getUrl()+"?"+"spsParamterId="+document.getElementById('searchForm:targetHiddenToStoreSpsID').value+'&date='+currentTime;
	newWinForView =window.showModalDialog(url,"","dialogHeight:650px;dialogWidth:1200px;resizable=false;status=no;");	
}	

function confirmDeletion(){				
	var message = "Are you sure you want to delete ?"		
	if(window.confirm(message)){
		getFromDataTableToHidden('searchForm:searchResultTable', 'hiddenParameterId', 'searchForm:targetHiddenToStoreSpsID');
		submitLink('searchForm:deleteSpsId');
	}
	else{			 
		return false;
	}
}		
</script>
</body>
</HTML>
