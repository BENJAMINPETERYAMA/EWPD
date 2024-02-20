
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
<TITLE>Search Reference Mapping</TITLE>
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


								<tr>
									<td width="24%">&nbsp;<h:outputText id="RuleStar"
										value="Reference Id" /></td>
									<td width="28%">
									<DIV id="refDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="refhid"
										value="#{referenceMappingBackingBean.referenceCriteria}"></h:inputHidden>
									</TD>
									<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="RuleButton" image="../../images/select.gif"
										style="cursor: hand" tabindex="10"
										onclick="loadMultiSearchPopup('searchSPS','Reference',
                                                         'SPS Parameter Popup','refDiv','searchForm:refhid'); return false;"></h:commandButton>
									</TD>
									<td width="42%"></td>

								</tr>

								<TR>
									<TD width="27%">&nbsp;<h:outputText
										styleClass="#{referenceMappingBackingBean.requiredType? 'mandatoryError': 'mandatoryNormal'}"
										id="BenefitTypeStar" value="Type" /></TD>
									<TD width="29%">
									<TABLE cellspacing="0" cellpadding="0" border="0">
										<TR>
											<TD><h:inputHidden id="benefitType_copyhidden"
												value="#{referenceMappingBackingBean.typeCriteria}"></h:inputHidden>
											<h:selectOneMenu id="CorpPlanCd_list1"
												styleClass="formInputField" tabindex="6"
												value="#{referenceMappingBackingBean.typeCriteria}">
												<f:selectItems id="benefitTypeList"
													value="#{referenceMappingBackingBean.mappingTypes}" />
												<%-- This code will direct the control to Standardbenefit BB from where it calls ReferencedataBB.
													 This modifiction was done for maintaining the value in benefittype dropdown to prevent the 
													 validation error. Please dont modify the code  --%>

											</h:selectOneMenu></TD>
											<TD width="63%" align="right"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>


								<TR>
									<TD width="24%"><h:outputText
										styleClass="#{referenceMappingBackingBean.requiredTerm ? 'mandatoryError': 'mandatoryNormal'}"
										id="TermStar" value="Term" /></TD>

									<TD width="28%">

									<DIV id="TermDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtTerm"
										value="#{referenceMappingBackingBean.termCriteria}"></h:inputHidden>
									</TD>
									<TD width="375">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="Term" image="../../images/select.gif" style="cursor: hand"
										onclick="loadSearchPopupWithName('../popups/SearchMultiSelect.jsp','searchTerm','Term',
                                                         'Term Search Popup','TermDiv','searchForm:txtTerm'); return false;">
									</h:commandButton></TD>


									<TD align="left" valign="top" height="17" width="42%"><h:selectBooleanCheckbox
										value="#{referenceMappingBackingBean.aggregateTerm}">Combine</h:selectBooleanCheckbox></TD>
								</TR>


								<TR>
									<TD width="24%"><h:outputText
										styleClass="#{referenceMappingBackingBean.requiredQualifier ? 'mandatoryError': 'mandatoryNormal'}"
										id="QualifierStar" value="Qualifier" /></TD>
									<TD width="28%">
									<DIV id="QualifierDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtQualifier"
										value="#{referenceMappingBackingBean.qualifierCriteria}"></h:inputHidden>
									</TD>
									<TD width="9%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="QualifierButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="loadSearchPopupWithName('../popups/SearchQualifierMultiselect.jsp','searchQualifier','Qualifier',
                                                         'Qualifier Search Popup','QualifierDiv','searchForm:txtQualifier'); return false;"
										tabindex="13"></h:commandButton></TD>


									<TD align="left" valign="top" height="17" width="45%"><h:selectBooleanCheckbox
										value="#{referenceMappingBackingBean.aggregateQualifier}">Combine</h:selectBooleanCheckbox></TD>
								</TR>

								<TR>
									<TD width="144"><h:outputText id="pva"
										value="Provider Arrangement" /></TD>
									<TD width="177">
									<DIV id="ProviderArrangementDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="providerArrangement"
										value="#{referenceMappingBackingBean.pvaCriteria}"></h:inputHidden>
									</TD>
									<TD width="375">&nbsp; <h:commandButton alt="Select"
										id="ProviderArrangementButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="pvaClick('../popups/providerArrangementPopup.jsp','ProviderArrangementDiv','searchForm:providerArrangement',1,1); return false;"
										tabindex="14"></h:commandButton></TD>
								</TR>

								<TR>
									<TD width="24%"><h:outputText
										styleClass="#{referenceMappingBackingBean.requiredDataType ? 'mandatoryError': 'mandatoryNormal'}"
										id="DataTypeStar" value="Data Type" /></TD>
									<TD width="28%">
									<DIV id="DataTypeDiv" class="selectDataDisplayDiv"></DIV>
									<h:inputHidden id="txtDataType"
										value="#{referenceMappingBackingBean.dataTypeCriteria}"></h:inputHidden>
									</TD>
									<TD width="6%">&nbsp;&nbsp;<h:commandButton alt="Select"
										id="DataTypeButton" image="../../images/select.gif"
										style="cursor: hand"
										onclick="ewpdModalWindow_ewpd('../standardbenefitpopups/dataTypeSelectPopup.jsp','DataTypeDiv','searchForm:txtDataType',2,2); return false;"
										tabindex="15"></h:commandButton></TD>
									<TD width="42%"></TD>
								</TR>



								<TR>
									<TD colspan="3">&nbsp;</TD>
								</TR>
								<TR>
									<TD align="left" valign="top" colspan="3"><h:commandButton
										styleClass="wpdbutton" id="basicSearch" value="Locate"
										style="cursor: hand"
										action="#{referenceMappingBackingBean.searchReferenceMapping}"
										tabindex="11"></h:commandButton></TD>
								</TR>

							</TBODY>
						</TABLE>


						</DIV>
						</DIV>
						<h:commandLink id="deleteIndMap"
							style="display:none; visibility: hidden;"
							action="#{referenceMappingBackingBean.deleteIndicativeMapping}">
						</h:commandLink> <h:commandLink id="editIndMap"
							style="display:none; visibility: hidden;"
							action="#{referenceMappingBackingBean.locateReferenceMapping}">
							<f:verbatim />
						</h:commandLink>


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
									id="resultHeaderTable" border="0" style="width:100%;">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD align="left"><h:outputText value="SPS ID"></h:outputText></TD>
											<TD align="left"><h:outputText value="Description"></h:outputText></TD>
											<TD align="left"><h:outputText value="Type"></h:outputText></TD>
											<TD align="left"><h:outputText value="Term"></h:outputText></TD>
											<TD align="left"><h:outputText value="Qualifier"></h:outputText></TD>
											<TD align="left"><h:outputText value="PVA"></h:outputText></TD>
											<TD align="left"><h:outputText value="Data type"></h:outputText></TD>
											<TD align="left"></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
								</TD>
							</TR>
							<TR>
								<TD><!-- Search Result Data Table -->
								<DIV id="searchResultdataTableDiv"
									style="height:100%;width:100%;position:relative;z-index:1;font-size:10px;overflow:auto;"><h:dataTable
									headerClass="dataTableHeader" id="searchResultTable"
									var="singleValue" cellpadding="3" cellspacing="1" width="100%"
									bgcolor="#cccccc"
									rendered="#{referenceMappingBackingBean.searchResults != null}"
									value="#{referenceMappingBackingBean.searchResults}"
									rowClasses="dataTableEvenRow,dataTableOddRow" >
									<h:column>
										<h:inputHidden id="referenceIdHidden"
											value="#{singleValue.referenceId}"></h:inputHidden>
										<h:inputHidden id="keyValueHidden"
											value="#{singleValue.keyValue}"></h:inputHidden>
										<h:inputHidden id="typeHidden" value="#{singleValue.type}"></h:inputHidden>
										<h:inputHidden id="termHidden" value="#{singleValue.term}"></h:inputHidden>
										<h:inputHidden id="qualifierHidden"
											value="#{singleValue.qualifier}"></h:inputHidden>
										<h:inputHidden id="pvaHidden" value="#{singleValue.pva}"></h:inputHidden>
										<h:inputHidden id="datatypeHidden"
											value="#{singleValue.datatype}"></h:inputHidden>

										<h:inputHidden id="createdUser"
											value="#{singleValue.createdUser}"></h:inputHidden>

										<h:inputHidden id="chngdUser"
											value="#{singleValue.lastUpdatedUser}"></h:inputHidden>

										<h:inputHidden id="createdTime"
											value="#{singleValue.createdTime}"></h:inputHidden>

										<h:inputHidden id="chngdTime"
											value="#{singleValue.changeTime}"></h:inputHidden>



										<h:inputHidden id="typeDescHidden"
											value="#{singleValue.typeDesc}"></h:inputHidden>
										<h:inputHidden id="termDescHidden"
											value="#{singleValue.termDesc}"></h:inputHidden>
										<h:inputHidden id="qualDescHidden"
											value="#{singleValue.qualDesc}"></h:inputHidden>
										<h:inputHidden id="pvaDescHidden"
											value="#{singleValue.pvaDesc}"></h:inputHidden>
										<h:inputHidden id="dataTypeDescHidden"
											value="#{singleValue.datatypeDesc}"></h:inputHidden>
										<h:inputHidden id="dataTypeEditHidden"
											value="#{singleValue.datatype}"></h:inputHidden>
										<h:inputHidden id="cobolValueHidden"
											value="#{singleValue.cobolValue}"></h:inputHidden>
										<h:inputHidden id="refDescHidden"
											value="#{singleValue.description}"></h:inputHidden>


										<h:outputText id="indicative" value="#{singleValue.keyValue}"></h:outputText>

									</h:column>

									<h:column>
										<h:outputText id="indicativeDesc"
											value="#{singleValue.description}"></h:outputText>

									</h:column>

									<h:column>
										<h:outputText id="indicativeDesctype"
											value="#{singleValue.typeDesc}"></h:outputText>

									</h:column>


									<h:column>
										<h:outputText id="indicativeDescterm"
											value="#{singleValue.termDesc}"></h:outputText>

									</h:column>

									<h:column>
										<h:outputText id="indicativeDescQualifier"
											value="#{singleValue.qualDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="indicativeDescpva"
											value="#{singleValue.pvaDesc}"></h:outputText>
									</h:column>
									<h:column>
										<h:outputText id="indicativeDescdatatype"
											value="#{singleValue.datatypeDesc}"></h:outputText>
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



						</TD>
					</TR>
				</TABLE>



				<h:inputHidden id="referenceCriteria"
					value="#{referenceMappingBackingBean.referenceCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="typeCriteria"
					value="#{referenceMappingBackingBean.typeCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="qualifierCriteria"
					value="#{referenceMappingBackingBean.qualifierCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="pvaCriteria"
					value="#{referenceMappingBackingBean.pvaCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="dataTypeCriteria"
					value="#{referenceMappingBackingBean.dataTypeCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="termCriteria"
					value="#{referenceMappingBackingBean.termCriteriaDelete}"></h:inputHidden>
				<h:inputHidden id="termCriteriaDesc"
					value="#{referenceMappingBackingBean.termDesc}"></h:inputHidden>

				<h:inputHidden id="referenceKey"
					value="#{referenceMappingBackingBean.referenceCriteriaEdit}"></h:inputHidden>

				


				<h:inputHidden id="createdUserCriteria"
					value="#{referenceMappingBackingBean.createdUser}"></h:inputHidden>
				<h:inputHidden id="chngdUserCriteria"
					value="#{referenceMappingBackingBean.lastUpdatedUser}"></h:inputHidden>
				<h:inputHidden id="createdTimeCriteria"
					value="#{referenceMappingBackingBean.createdDate}"></h:inputHidden>
				<h:inputHidden id="chngdTimeCriteria"
					value="#{referenceMappingBackingBean.changeDate}"></h:inputHidden>
				<h:inputHidden id="cobolValueCriteria"
					value="#{referenceMappingBackingBean.cobolValue}"></h:inputHidden>
				<h:inputHidden id="indicativeDescCriteria"
					value="#{referenceMappingBackingBean.description}"></h:inputHidden>

				<h:inputHidden id="dataTypeValue"
					value="#{referenceMappingBackingBean.dataTypeEdit}"></h:inputHidden>
				<h:inputHidden id="dataTypeDescValue"
					value="#{referenceMappingBackingBean.dataTypeEditDesc}"></h:inputHidden>
				<h:inputHidden id="qualifierDesc"
					value="#{referenceMappingBackingBean.qualifierDesc}"></h:inputHidden>
			
					<h:inputHidden id="timeZoneKey"
					value="#{referenceMappingBackingBean.timeZone}"></h:inputHidden>



			</h:form></TD>
		</TR>
		<TR>
			<TD><%@include file="../navigation/bottom.jsp"%></TD>
		</TR>
	</TABLE>









	</body>
</f:view>
<script language="JavaScript">
	



		var newWinForView;


		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'360',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});

if(document.getElementById('searchForm:searchResultTable') != null) {
	
		setColumnWidth('resultHeaderTable','5%:18%:10%:16%:19%:15%:9%:8%');

		setColumnWidth('searchForm:searchResultTable','5%:18%:10%:16%:19%:15%:9%:8%');	
		showResultsTab();

		}else{
		
			var headerDiv = document.getElementById('resultHeaderDiv');
			headerDiv.style.visibility = 'hidden';
			
		}
		if(document.getElementById('searchForm:searchResultTable') != null){

			document.getElementById('searchForm:searchResultTable').onresize = syncTables;
			syncTables();
		}
		
	copyHiddenToDiv_ewpd('searchForm:refhid','refDiv',2,2);	
	copyHiddenToDiv_ewpd('searchForm:txtDataType','DataTypeDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:providerArrangement','ProviderArrangementDiv',1,1);
	copyHiddenToDiv_ewpd('searchForm:txtQualifier','QualifierDiv',2,2);
	copyHiddenToDiv_ewpd('searchForm:txtTerm','TermDiv',2,2);


	





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
			getFromDataTableToHidden('searchForm:searchResultTable', 'keyValueHidden', 'searchForm:referenceKey');

			getFromDataTableToHidden('searchForm:searchResultTable', 'referenceIdHidden', 'searchForm:referenceCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'typeHidden', 'searchForm:typeCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'termHidden', 'searchForm:termCriteria');
		    getFromDataTableToHidden('searchForm:searchResultTable', 'qualifierHidden', 'searchForm:qualifierCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'pvaHidden', 'searchForm:pvaCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'datatypeHidden', 'searchForm:dataTypeCriteria');
		   	getFromDataTableToHidden('searchForm:searchResultTable', 'dataTypeEditHidden', 'searchForm:dataTypeValue');
			getFromDataTableToHidden('searchForm:searchResultTable', 'termDescHidden', 'searchForm:termCriteriaDesc');
			getFromDataTableToHidden('searchForm:searchResultTable', 'dataTypeDescHidden', 'searchForm:dataTypeDescValue');			
			getFromDataTableToHidden('searchForm:searchResultTable', 'cobolValueHidden', 'searchForm:cobolValueCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'refDescHidden', 'searchForm:indicativeDescCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'qualDescHidden', 'searchForm:qualifierDesc');

				getFromDataTableToHidden('searchForm:searchResultTable', 'createdUser', 'searchForm:createdUserCriteria');
				getFromDataTableToHidden('searchForm:searchResultTable', 'chngdUser', 'searchForm:chngdUserCriteria');
				getFromDataTableToHidden('searchForm:searchResultTable', 'createdTime', 'searchForm:createdTimeCriteria');
				getFromDataTableToHidden('searchForm:searchResultTable', 'chngdTime', 'searchForm:chngdTimeCriteria');




 submitLink('searchForm:editIndMap');



	}
	function deleteIndMap(){	
		
		var message = "Are you sure you want to delete ?"		
		if(window.confirm(message)){
			
			getFromDataTableToHidden('searchForm:searchResultTable', 'keyValueHidden', 'searchForm:referenceCriteria');

			getFromDataTableToHidden('searchForm:searchResultTable', 'typeHidden', 'searchForm:typeCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'termHidden', 'searchForm:termCriteria');
		
		    getFromDataTableToHidden('searchForm:searchResultTable', 'qualifierHidden', 'searchForm:qualifierCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'pvaHidden', 'searchForm:pvaCriteria');
		
			getFromDataTableToHidden('searchForm:searchResultTable', 'datatypeHidden', 'searchForm:dataTypeCriteria');


		 submitLink('searchForm:deleteIndMap');
		}
		else{			 
				return false;
		}
	}



function loadIndicativeSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchIndicativeSPSpopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}

function loadSPSSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/SearchReferencePopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,3,2);
}



function loadMultiSearchPopup(queryName,headerName,titleName,displayDiv,outComp){

	ewpdModalWindow_ewpd( '../popups/searchSPSMultiSelectPopup.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/searchPopupSingle.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}


function viewAction(){
												
			
	
			getFromDataTableToHidden('searchForm:searchResultTable', 'keyValueHidden', 'searchForm:referenceKey');
 			getFromDataTableToHidden('searchForm:searchResultTable', 'referenceIdHidden', 'searchForm:referenceCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'typeDescHidden', 'searchForm:typeCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'termDescHidden', 'searchForm:termCriteria');
		    getFromDataTableToHidden('searchForm:searchResultTable', 'qualDescHidden', 'searchForm:qualifierCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'pvaDescHidden', 'searchForm:pvaCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'dataTypeDescHidden', 'searchForm:dataTypeCriteria');

			getFromDataTableToHidden('searchForm:searchResultTable', 'createdUser', 'searchForm:createdUserCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'chngdUser', 'searchForm:chngdUserCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'createdTime', 'searchForm:createdTimeCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'chngdTime', 'searchForm:chngdTimeCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'cobolValueHidden', 'searchForm:cobolValueCriteria');
			getFromDataTableToHidden('searchForm:searchResultTable', 'refDescHidden', 'searchForm:indicativeDescCriteria');

	var action = 'view';


	var ref_id = document.getElementById('searchForm:referenceKey').value;
	var cob_val = document.getElementById('searchForm:cobolValueCriteria').value;
	var ref_desc = document.getElementById('searchForm:indicativeDescCriteria').value;


	var type = document.getElementById('searchForm:typeCriteria').value;
	var term = document.getElementById('searchForm:termCriteria').value;
	var qualifier = document.getElementById('searchForm:qualifierCriteria').value;
	var dataType  = document.getElementById('searchForm:dataTypeCriteria').value;
	var pva  = document.getElementById('searchForm:pvaCriteria').value;

	var user = document.getElementById('searchForm:createdUserCriteria').value;
	var chngduser = document.getElementById('searchForm:chngdUserCriteria').value;
	var createdTime = document.getElementById('searchForm:createdTimeCriteria').value;
	var chngdTime = document.getElementById('searchForm:chngdTimeCriteria').value;
	
	var timeZone = document.getElementById('searchForm:timeZoneKey').value;

 window.showModalDialog('viewreferencemapping.jsp'+getUrl()+'?action='+action+ '&timeZone='+ timeZone+ '&chngdTime='+ chngdTime+ '&createdTime='+ createdTime+ '&chngduser='+ chngduser+ '&user='+ user +'&refid='+ ref_id + '&type='+ type + '&term='+ term + '&qualifier='+qualifier+'&dataType='+dataType+'&pva='+pva+'&cobolVal='+cob_val+'&refDesc='+ref_desc,'View Indicative Mapping','dialogHeight:800px;dialogWidth:1000px;resizable=true;status=no;');
}



function showIndNr(val){
	
	if((null!=val) && !('' == val)){
		var indr = val.split('~');
	
		document.getElementById('refDiv').innerHTML=indr[0];
		
	}else{
	
		document.getElementById('refDiv').innerHTML='';
	}
}

function pvaClick(url,targetDiv,targetHidden,attrCount,attrPosn){
	var lob = 'ALL~ALL';
	var be = 'ALL~ALL';
	var bg = 'ALL~ALL';
	var parentCatalog = 'provider arrangement';
	url = url + '?lookUpAction=2'+'&parentCatalog='+parentCatalog +'&lob='+lob +'&be='+be+'&bg='+bg;
			return ewpdModalWindow_ewpd(url, targetDiv, targetHidden, attrCount, attrPosn);
	}

function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp){

      ewpdModalWindow_ewpd( popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,2);
}

</script>
<script language="JavaScript">
	fillSpace();
</script>
</HTML>



