<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:pagecode language="java" location="/JavaSource/pagecode/pages/contract/ContractAdvancedSearch.java" --%><%-- /jsf:pagecode --%>
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

<TITLE>Contract Search</TITLE>
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
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
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>

<f:view>
	<BODY onkeypress="return submitOnEnterKey('ContractAdvancedSearchForm:searchButton');" onUnload="closeAction();">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
				
			<td><jsp:include page="../navigation/top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td><h:form id="ContractAdvancedSearchForm">
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
							<TD width="273" valign="top" class="leftPanel">
						
							<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->		<jsp:include page="../contract/contractSearchTree.jsp"></jsp:include>				

						 		</DIV>
						
							</TD>
							<td valign="top" class="ContentArea">
							<TABLE width="100%">
								<TR>
									<TD height="10">
										<w:message></w:message> 
									</TD>
											
								</TR>
							</TABLE>

							<DIV>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">
								<tr>
							<td width="200">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21"/></td>
									<td class="tabNormal"><h:commandLink action="#{contractSearchBackingBean.loadBasicSearch}" id="linkToBasicSearch"><h:outputText value="Basic Criteria" /></h:commandLink></td>
									<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21"/></td>
								</tr>
							</table>
							</td>
							<td width="200">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2" align="left"><img	src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
									<td width="186" class="tabActive"><h:outputText	value=" Advanced Criteria" /></td>
									<td width="2" align="right"><img src="../../images/activeTabRight.gif" width="2" height="21" /></td>
								</tr>
							</table>
							</td>
								<td width="100%"></td>
						</tr>
							</TABLE>
<!-- End of Tab table -->
							<DIV id="accordionTest" style="margin:5px;">
								<DIV id="searchPanel">
									<DIV id="searchPanelHeader" class="tabTitleBar"
										style="position:relative; cursor:hand;"><B>Search Criteria</B></DIV>
									<DIV id="searchPanelContent" class="tabContentBox"
										style="position:relative;"><BR>

<!--	Start of Table for actual Data	-->
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
										<TBODY>
								
											<TR>
												<TD width="146"><h:outputText value="Group Size" /></TD>
													<TD width="175">
													<DIV id="groupSizeDiv" class="selectDataDisplayDiv"></DIV>
													<h:inputHidden id="txtGroupSize"
													value="#{contractSearchBackingBean.screenValueObject.groupSize}"></h:inputHidden>
													<SCRIPT> parseForDiv(document.getElementById('groupSizeDiv'), document.getElementById('ContractAdvancedSearchForm:txtGroupSize')); </SCRIPT>
												</TD>
												<TD width="50"><h:commandButton alt="Select"
													id="GroupSizeButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/groupSize.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Group Size','groupSizeDiv','ContractAdvancedSearchForm:txtGroupSize',2,1); return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
								
											<TR>
												<TD width="146"><h:outputText value="Funding Arrangement" /></TD>
												<TD width="175">
													<DIV id="fundingDiv" class="selectDataDisplayDiv"></DIV>
													<h:inputHidden id="txtFunding"
													value="#{contractSearchBackingBean.screenValueObject.fundingArrangement}"></h:inputHidden>
													<SCRIPT> parseForDiv(document.getElementById('fundingDiv'), document.getElementById('ContractAdvancedSearchForm:txtFunding')); </SCRIPT>
												</TD>
												<TD width="50"><h:commandButton alt="Select"
													id="FundingArrangementButton" image="../../images/select.gif"
													style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/fundingargument.jsp'+getUrl(),'fundingDiv','ContractAdvancedSearchForm:txtFunding',2,1); return false;"
													tabindex="3"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Brand Name" /></TD>
												<TD width="175">
													<h:inputText styleClass="formInputField"
														id="txtBrandName"
														value="#{contractSearchBackingBean.screenValueObject.brandName}" tabindex="4" maxlength="30"/>
												</TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Base Contract Code"/></TD>
													<h:inputHidden id="txtbcc"
														value="#{contractSearchBackingBean.screenValueObject.baseContractCode}"></h:inputHidden>
												<TD width="175"><DIV id="bccDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT> parseForDiv(document.getElementById('bccDiv'), document.getElementById('ContractAdvancedSearchForm:txtbcc')); </SCRIPT>
												</TD>
												<TD width="50"><h:commandButton alt="Select" id="bccButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/searchBaseContractCode.jsp'+getUrl(),'bccDiv','ContractAdvancedSearchForm:txtbcc',2,1); return false;"
													tabindex="5"></h:commandButton></TD>
											</TR>

											<TR>
												<TD width="146"><h:outputText value="Corporate Plan Code"/></TD>
													<h:inputHidden id="txtcpc"
													   	value="#{contractSearchBackingBean.screenValueObject.corporatePlanCode}"></h:inputHidden>
												<TD width="175"><DIV id="cpcDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT> parseForDiv(document.getElementById('cpcDiv'), document.getElementById('ContractAdvancedSearchForm:txtcpc')); </SCRIPT>
												</TD>
												<TD width="50"><h:commandButton alt="Select" id="cpcButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/searchPlancodePopUp.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Standard Plan Code','cpcDiv','ContractAdvancedSearchForm:txtcpc',2,1); return false;"
													tabindex="5"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Standard Plan Code"/> </TD>
													<h:inputHidden id="txtspc"
													value="#{contractSearchBackingBean.screenValueObject.standardPlanCode}"></h:inputHidden>
												<TD width="175"><DIV id="spcDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT> parseForDiv(document.getElementById('spcDiv'), document.getElementById('ContractAdvancedSearchForm:txtspc')); </SCRIPT>
																
												</TD>
												<TD width="50"><h:commandButton alt="Select" id="spcButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/searchStandardPlanCode.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Standard Plan Code','spcDiv','ContractAdvancedSearchForm:txtspc',2,1); return false;"
													tabindex="6"></h:commandButton></TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Created By" /></TD>
												<TD width="175">
													<h:inputText styleClass="formInputField"
														id="txtCreatedBy"
														value="#{contractSearchBackingBean.screenValueObject.createdBy}" tabindex="4" maxlength="30"/>
												</TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Created Date" /></TD>
												<TD width="175"><h:inputText styleClass="formInputField" id="CreateDateStart_txt" value="#{contractSearchBackingBean.screenValueObject.createdDateStart}" tabindex="3" maxlength="10"/> 
													<span class="dateFormat">(mm/dd/yyyy)</span></TD>
													<td valign="top" width="50">
													<a href="#" onclick="cal1.select('ContractAdvancedSearchForm:CreateDateStart_txt','Strt1_date_icon','MM/dd/yyyy'); return false;"
													title="cal1.select(document.forms[0].ContractAdvancedSearchForm:CreateDateStart_txt,'Strt1_date_icon','MM/dd/yyyy'); return false;"
													name="Strt1_date_icon" id="Strt1_date_icon" tabindex="4"> 
													<h:graphicImage	style="border:0" value="../../images/cal.gif" alt="Cal">
													</h:graphicImage> </a></TD>
													<TD width="27"><h:outputText value="to" /></TD>
												<TD width="175"><h:inputText styleClass="formInputField" id="CreateDateEnd_txt" value="#{contractSearchBackingBean.screenValueObject.createdDateEnd}" tabindex="3" maxlength="10"/> 
													<span class="dateFormat">(mm/dd/yyyy)</span></TD>
													<td valign="top" width="231">
													<a href="#" onclick="cal1.select('ContractAdvancedSearchForm:CreateDateEnd_txt','Exp1_date_icon','MM/dd/yyyy'); return false;"
													title="cal1.select(document.forms[1].ContractAdvancedSearchForm:CreateDateEnd_txt,'Exp1_date_icon','MM/dd/yyyy'); return false;"
													name="Exp1_date_icon" id="Exp1_date_icon" tabindex="4"> 
													<h:graphicImage	style="border:0" value="../../images/cal.gif" alt="Cal">
													</h:graphicImage> </a></TD>
											</TR>

											<TR>
												<TD width="146"><h:outputText value="Last Updated By" /></TD>
												<TD width="175">
													<h:inputText styleClass="formInputField"
														id="txtUpdatedBy"
														value="#{contractSearchBackingBean.screenValueObject.updatedBy}" tabindex="4" maxlength="30"/>
												</TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Last Updated Date" /></TD>
												<TD width="175"><h:inputText styleClass="formInputField" id="UpdatedDateStart_txt" value="#{contractSearchBackingBean.screenValueObject.updatedDateStart}" tabindex="3" maxlength="10"/> 
													<span class="dateFormat">(mm/dd/yyyy)</span></TD>
													<td valign="top" width="50">
													<a href="#" onclick="cal1.select('ContractAdvancedSearchForm:UpdatedDateStart_txt','Strt_date_icon','MM/dd/yyyy'); return false;"
													title="cal1.select(document.forms[0].ContractAdvancedSearchForm:UpdatedDateStart_txt,'Strt_date_icon','MM/dd/yyyy'); return false;"
													name="Strt_date_icon" id="Strt_date_icon" tabindex="4"> 
													<h:graphicImage	style="border:0" value="../../images/cal.gif" alt="Cal">
													</h:graphicImage> </a></TD>
													<TD width="27"><h:outputText value="to" /></TD>
												<TD width="175"><h:inputText styleClass="formInputField" id="UpdatedDateEnd_txt" value="#{contractSearchBackingBean.screenValueObject.updatedDateEnd}" tabindex="3" maxlength="10"/> 
													<span class="dateFormat">(mm/dd/yyyy)</span></TD>
													<td valign="top" width="231">
													<a href="#" onclick="cal1.select('ContractAdvancedSearchForm:UpdatedDateEnd_txt','Exp_date_icon','MM/dd/yyyy'); return false;"
													name="Exp_date_icon" id="Exp_date_icon" tabindex="4"> 
													<h:graphicImage	style="border:0" value="../../images/cal.gif" alt="Cal">
													</h:graphicImage> </a></TD>
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Product"/> </TD>
													<h:inputHidden id="txtProduct"
													value="#{contractSearchBackingBean.screenValueObject.product}"></h:inputHidden>
												<TD width="175"><DIV id="productDiv" class="selectDataDisplayDiv"></DIV>
													<SCRIPT> parseForDiv(document.getElementById('productDiv'), document.getElementById('ContractAdvancedSearchForm:txtProduct')); </SCRIPT>
												</TD>
												<TD width="50"><h:commandButton alt="Select" id="ProductButton"
													image="../../images/select.gif" style="cursor: hand"
													onclick="ewpdModalWindow_ewpd('../contractpopups/productPopup.jsp'+getUrl(),'productDiv','ContractAdvancedSearchForm:txtProduct',2,1); return false;"
													tabindex="7"></h:commandButton></TD>
			
											</TR>
											<TR>
												<TD width="146"><h:outputText value="Group Name" /></TD>
												<TD width="175">
													<h:inputText styleClass="formInputField"
														id="txtGroupName"
														value="#{contractSearchBackingBean.screenValueObject.groupName}" tabindex="4" maxlength="15"/>
												</TD>
											</TR>
											<TR>
												<TD width="146"><h:commandButton id="searchButton" value="Search"
													styleClass="wpdButton"
													action="#{contractSearchBackingBean.performLocate}" tabindex="9">
													</h:commandButton></TD>
												<TD width="175">&nbsp;</TD>
												<TD width="50">&nbsp;</TD>
											</TR>
										</TBODY>
									</TABLE>
								</DIV>
							</DIV>

							<DIV id="panel2">
								<DIV id="panel2Header" class="tabTitleBar"
									style="position:relative; cursor:hand;">Search Results</DIV>
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
															<TD align="left" width="15%"><h:outputText
																value="ContractId"></h:outputText></TD>
															<TD align="left" width="10%"><h:outputText value="Start Date"></h:outputText>
															</TD>
															<TD align="left" width="10%"><h:outputText
																value="Revision Date"></h:outputText></TD>
															<TD align="left" width="20%"><h:outputText
																value="Contract Type"></h:outputText></TD>
															<TD align="left" width="10%"><h:outputText
																value="Legal Entity"></h:outputText></TD>
															<TD align="left" width="10%"><h:outputText
																value="Status"></h:outputText></TD>
															<TD align="left" width="10%"><h:outputText
																value="Match %"></h:outputText></TD>
															
															<TD align="left" width="15%"><h:outputText></h:outputText></TD>
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
<!-- Search Result Data Table --> 
											<h:dataTable
												rowClasses="dataTableEvenRow,dataTableOddRow"
												styleClass="outputText" headerClass="dataTableHeader"
												id="searchResultTable" var="singleValue" cellpadding="3"
												width="100%" cellspacing="1" bgcolor="#cccccc"
												rendered="#{contractSearchBackingBean.locateResultList != null}"
												value="#{contractSearchBackingBean.locateResultList}">
												<h:column>
													<h:outputText id="ContractId"
														value="#{singleValue.contractId}"></h:outputText>
												</h:column>
												<h:column>
													
													<h:outputText id="StartDate"
														value="#{singleValue.contractId}"></h:outputText>
												</h:column>
												<h:column>
													<h:outputText id="RevisionDate"
														value="#{singleValue.contractId}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="ContractType"
														value="#{singleValue.contractId}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="LegalEntity"
														value="#{singleValue.contractId}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="Status"
														value="#{singleValue.contractId}"></h:outputText>
													
												</h:column>
												<h:column>
													<h:outputText id="MatchPercent"
														value="#{singleValue.contractId}"></h:outputText>
													
												</h:column>
											
												<h:column>

													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
													<h:commandButton alt="View" id="viewButton" image="../../images/view.gif" 
															onclick=" return false;"></h:commandButton>

													<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
													<h:commandButton alt="Copy" id="copy"
														image="../../images/copy.gif"
														onclick=" return false;"></h:commandButton>

													<f:verbatim>  &nbsp; &nbsp; </f:verbatim>
													<h:commandButton alt="Edit" id="Edit"
														image="../../images/edit.gif" value="Edit"
														onclick=" return false;">
													</h:commandButton>

													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
													<h:commandButton alt="Delete" id="basicDelete" image="../../images/delete.gif" value="Delete" 
															onclick=" return false;">
													<f:verbatim> &nbsp; &nbsp;</f:verbatim>
													</h:commandButton>

												</h:column>
												</h:dataTable>
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
		</td>
		</tr>
		<tr>
			<td></h:form> <%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>

</f:view>
<script language="javascript">
		
		


//Open a popup window to show all the version of the selected standard benefit.




function syncTables(){
			var relTblWidth = document.getElementById('ContractAdvancedSearchForm:searchResultTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}		
var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('ContractAdvancedSearchForm:searchResultTable') != null) {
			//tigra_tables('ContractAdvancedSearchForm:searchResultTable',0,0,'#e1ecf7','#ffffff','rgb(118,168,218)','rgb(118,168,218)');
			setColumnWidth('ContractAdvancedSearchForm:searchResultTable','15%:10%:10%:20%:10%:10%:10%:15%');
			setColumnWidth('resultHeaderTable','15%:10%:10%:20%:10%:10%:10%:15%');	
			showResultsTab();
		}else {
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		

		if(document.getElementById('ContractAdvancedSearchForm:searchResultTable') != null){
			document.getElementById('ContractAdvancedSearchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}

	



</script>
</HTML>
