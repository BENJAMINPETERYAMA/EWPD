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
<TITLE>Accumulator Search</TITLE>
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
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
</HEAD>

<body>
<f:view>
	<table width="100%" cellpadding="0" cellspacing="0">
		<jsp:include page="../navigation/topStyle.jsp"></jsp:include>
		<tr>
			<td><h:form id="SearchForm">
				<TABLE width="100%"  border="0" cellspacing="0" cellpadding="0">
					<TBODY>
						<TR>
							<td>
							<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
								<tr>
									<td height="16" valign="middle" bgcolor="#7670B3"
										class="breadcrumb">Administration&nbsp;&gt;&gt;&nbsp;Standard
									Accumulator&nbsp;&gt;&gt;&nbsp;Locate</td>
								</tr>
							</table>
							</td>

						</TR>

						<TR>
							<td valign="top" class="ContentArea">
							<TABLE >
								<TR>
									<TD><w:message></w:message></TD>
								</TR>
							</TABLE>
							<div>
							<table border="0" cellpadding="0" cellspacing="0"
								id="TabTable"
								style="position:relative; top:25px; left:5px; z-index:120;">								<tr>
																
							</table>

							<!--Tab Ends-->
							<div id="accordionTest" style="margin:5px;">
							<div id="searchPanel">
							<div id="searchPanelHeader" class="tabTitleBar"
								style="position:relative; cursor:hand;"><b>Search
							Criteria</b></div>
							<div id="searchPanelContent" class="tabContentBox"
								style="position:relative;">

							<TABLE  border="0" cellspacing="0" cellpadding="2">
								<TBODY>
									<TR>
										<TD width="45%">
										
										<h:outputText id="lineOfBusiness"
											value="Line Of Business" /></TD>
										
										<TD >
										
										<div id="lineOfBusinessDiv" class="selectDataDisplayDiv"> </div>
											<h:inputHidden id="lineOfBusinessHidden"
											value="#{searchStdAccumBackingBean.lineOfBusiness}"></h:inputHidden>
										</TD>
										<TD width="20%"><h:commandButton alt="lineOfBusiness"
											id="lineOfBusinessButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpdforlob('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Line of Business','lineOfBusinessDiv','SearchForm:lineOfBusinessHidden',2,1);return false;"
											tabindex="1">
										</h:commandButton></TD>
									</TR>

									<TR>
										<TD ><h:outputText id="businessEntity"
											value="Business Entity" /></TD>
										<h:inputHidden id="businessEntityHidden"
											value="#{searchStdAccumBackingBean.be}"></h:inputHidden>
										<TD >
										<div id="businessEntityDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD ><h:commandButton alt="businessEntity"
											id="businessEntityButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Business Entity','businessEntityDiv','SearchForm:businessEntityHidden',2,1);
																				return false;"
											tabindex="2">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD ><h:outputText id="businessGroup"
											value="Business Group" /></TD>
										<h:inputHidden id="businessGroupHidden"
											value="#{searchStdAccumBackingBean.group}"></h:inputHidden>
										<TD >
										<div id="businessGroupDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD ><h:commandButton alt="businessGroup"
											id="businessGroupButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'business group','businessGroupDiv','SearchForm:businessGroupHidden',2,1);
																				return false;"
											tabindex="3">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD ><h:outputText id="MarketBusinessUnit"
											value="Market Business Unit" /></TD>
										<h:inputHidden id="marketBusinessUnitHidden"
											value="#{searchStdAccumBackingBean.mbu}"></h:inputHidden>
										<TD >
										<DIV id="marketBusinessUnit" class="selectDataDisplayDiv"></DIV>

										</TD>
										<TD ><h:commandButton alt="select"
											id="MarketBusinessUnitButton" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Market Business Unit','marketBusinessUnit','SearchForm:marketBusinessUnitHidden',2,1);
																				return false;"
											tabindex="4">
										</h:commandButton></TD>
									</TR>
									<TR>
										<TD ><h:outputText id="bntext"
											value="Benefit Name" /></TD>
										<h:inputHidden id="bnHidden"
											value="#{searchStdAccumBackingBean.benefitString}"></h:inputHidden>
										<TD >
										<div id="bnDiv" class="selectDataDisplayDiv"></div>
										</TD>
										<TD ><h:commandButton alt="Benefit Name"
											id="bn_Button" image="../../images/select.gif"
											onclick="loadSearchPopup('searchBenefitName','Benefit',
                                                         'Benefit Name Popup','bnDiv','SearchForm:bnHidden');return false;"
										tabindex="5">
										</h:commandButton></TD>
									</TR>


									<TR>
										<TD ><h:outputText id="quesSelect"
											value="Question " /></TD>
										<h:inputHidden id="selectQuesHidden"
											value="#{searchStdAccumBackingBean.question}"></h:inputHidden>
										<TD >
										<div id="questionSelectDiv" class="selectDataDisplayDiv"></div>

										</TD>
										<TD ><h:commandButton alt="SelectQuestion"
											id="selectQues" image="../../images/select.gif"
											onclick="ewpdModalWindow_ewpd('../adminoptionspopups/MultiselectquestionPopupForStandardAccumulator.jsp'+getUrl()+'?lookUpAction='+'1'+'&parentCatalog='+'Question',
												'questionSelectDiv','SearchForm:selectQuesHidden',2,1);return false;">
										</h:commandButton></TD>


									</TR>
									<TR>
										<TD ><h:outputText id="contractType" value="BY/CY " />
										</TD>
										<TD height="25"><h:selectOneMenu
											id="contractType_txt" styleClass="formInputField"
											tabindex="7" value="#{searchStdAccumBackingBean.byOrCy}">
											<f:selectItems id="contractTypeList"
												value="#{ReferenceDataBackingBeanCommon.contractTypeListForAccumCombo}" />
										</h:selectOneMenu></TD>
									</TR>
									<TR>
										<TD ><h:outputText id="accumulator"
											value="Accumulator">
										</h:outputText></TD>
										<TD >
										<div id="accumDiv" class="selectDataDisplayDiv"></div>
										<h:inputHidden id="accumHidden"
											value="#{searchStdAccumBackingBean.accumulatorName}"></h:inputHidden>
										</TD>
										<TD ><h:commandButton alt="Select"
											id="accumulatorButton" image="../../images/select.gif"
											style="cursor:hand"
											onclick="modalWindowforAccumPopupSearch('../accumulator/accumulatorPopupForStandardAccumulator.jsp'+getUrl(),'accumDiv','SearchForm:accumHidden');return false;">
										</h:commandButton></TD>

									</TR>
									<TR>
										<TD align="left" valign="top" colspan="3"><h:commandButton
											styleClass="wpdbutton" id="basicSearch" value="Search"
											disabled="" onclick="setAccumValues();"
											action="#{searchStdAccumBackingBean.performStdAccumSearch}"></h:commandButton>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td></td>
									<td align="right"></td>
								</tr>
							</table>

							</div>
							</div>
							<div id="panel2">
              			<div id="panel2Header" class="tabTitleBar" style="position:relative; cursor:hand;"> Search Results </div>
              			<div id="panel2Content" class="tabContentBox" style="position:relative;font-size:10px;"> 
              				<br>
								<table cellpadding="0" cellspacing="0" width="100%" border="0">
									<tr><td>

							<div id="resultHeaderDiv" >
							          <TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
											id="resultHeaderTable" border="0" width="100%">
											<TBODY>
												<TR class="dataTableColumnHeader">
													<td align="left"><h:outputText value="LOB"></h:outputText>
													</td>
													<td align="left"><h:outputText value="BE"></h:outputText>
													</td>
													<td align="left"><h:outputText value="Group"></h:outputText>
													</td>
													<td align="left"><h:outputText value="MBU"></h:outputText>
													</td>
													<td align="left"><h:outputText value="Benefit"></h:outputText>
													</td>
													<td align="left"><h:outputText value="Question"></h:outputText>
													</td>
													
													<td align="left"><h:outputText value="BY/CY"></h:outputText>
													</td>
													<td align="left"><h:outputText value="Accumulator Mapped"></h:outputText>
													</td>
													 <td>&nbsp;</td>
												   
											</TR>
											</TBODY>
										</TABLE>  
							</div>
							</td></tr>
			                <tr><td>  
							<div id="searchResultdataTableDiv" style="height:260px; overflow:auto; width:100%;">
											
				<h:dataTable headerClass="dataTableHeader" id="varDataTable" 
		              						 			  var="singleValue" cellpadding="3" 
		              						 			 cellspacing="1" bgcolor="#cccccc" 
														 rendered="#{searchStdAccumBackingBean.searchResultList != null}" value="#{searchStdAccumBackingBean.searchResultList}" rowClasses="dataTableEvenRow,dataTableOddRow" border="0" width="100%">
				
											<h:column>
												
												<h:outputText id="lob" value="#{singleValue.lineOfBusiness}"/>
												<h:inputHidden id="hiddenLOB"
													value="#{singleValue.lineOfBusiness}"></h:inputHidden>
											</h:column>

											<h:column>
												
												<h:outputText id="be" value="#{singleValue.be}" />
												<h:inputHidden id="hiddenbe" value="#{singleValue.be}"></h:inputHidden>
											</h:column>

											<h:column>
												
												<h:outputText id="group" value="#{singleValue.group}" />
												<h:inputHidden id="hiddengroup" value="#{singleValue.group}"></h:inputHidden>

											</h:column>

											<h:column>
												
												<h:outputText id="mbu" value="#{singleValue.mbu}" />
												<h:inputHidden id="hiddenmbu" value="#{singleValue.mbu}"></h:inputHidden>

											</h:column>
											<h:column>
												
												<h:outputText id="benefit"
													value="#{singleValue.benefitDescription}" />
												<h:inputHidden id="hiddenbenefit"
													value="#{singleValue.benefit}"></h:inputHidden>
												<h:inputHidden id="hiddenbenefitdesc"
													value="#{singleValue.benefitDescription}"></h:inputHidden>

											</h:column>
											<h:column>
												
												<h:outputText id="question"
													value="#{singleValue.questionDescription}" />
												<h:inputHidden id="hiddenques"
													value="#{singleValue.question}"></h:inputHidden>
												<h:inputHidden id="hiddenquesdesc"
													value="#{singleValue.questionDescription}"></h:inputHidden>
											</h:column>

											<h:column>
												
												<h:outputText id="type" value="#{singleValue.byOrCy}" />
												<h:inputHidden id="hiddentype" value="#{singleValue.byOrCy}"></h:inputHidden>
											</h:column>

											<h:column>
												
												<h:outputText id="acc"
													value="#{singleValue.standardAccumulatorStr}" />
												<h:inputHidden id="hiddenACC"
													value="#{singleValue.standardAccumulatorStr}"></h:inputHidden>
											</h:column>

											<h:column>
											
												<h:commandButton alt="Edit" id="editButton"
													image="../../images/edit.gif"
													onclick="getStdAccumforEdit();"
													action="#{standardAccumBackingBean.editAction}"></h:commandButton>
											</h:column>
										</h:dataTable></div>
										</td>
									</tr>
								</table>
								<TR>
									<TD> <h:inputHidden
										id="selectedLOBforEdit"
										value="#{standardAccumBackingBean.lineOfBusiness}" /> <h:inputHidden
										id="selectedBEforEdit" value="#{standardAccumBackingBean.be}" />
									<h:inputHidden id="selectedBGforEdit"
										value="#{standardAccumBackingBean.group}" /> <h:inputHidden
										id="selectedMBUforEdit"
										value="#{standardAccumBackingBean.mbu}" /> <h:inputHidden
										id="selectedCTforEdit"
										value="#{standardAccumBackingBean.byOrCy}" /> <h:inputHidden
										id="selectedQUESforEdit"
										value="#{standardAccumBackingBean.questionString}" /> <h:inputHidden
										id="selectedBENforEdit"
										value="#{standardAccumBackingBean.benefitString}" /> <h:inputHidden
										id="selectedBENDESCforEdit"
										value="#{standardAccumBackingBean.benefitDesc}" /> <h:inputHidden
										id="selectedQUESDESCforEdit"
										value="#{standardAccumBackingBean.quesdesc}" /> <h:inputHidden
										id="lobInput" value="#{searchStdAccumBackingBean.lobInput}"></h:inputHidden>
									<h:inputHidden id="beInput"
										value="#{searchStdAccumBackingBean.beInput}"></h:inputHidden>
									<h:inputHidden id="bgInput"
										value="#{searchStdAccumBackingBean.bgInput}"></h:inputHidden>
									<h:inputHidden id="mbuInput"
										value="#{searchStdAccumBackingBean.mbuInput}"></h:inputHidden>
									<h:inputHidden id="questInput"
										value="#{searchStdAccumBackingBean.questInput}"></h:inputHidden>
									<h:inputHidden id="accumInput"
										value="#{searchStdAccumBackingBean.accumInput}"></h:inputHidden>
									<h:inputHidden id="typeInput"
										value="#{searchStdAccumBackingBean.typeInput}"></h:inputHidden>
									<h:inputHidden id="benefitInput"
										value="#{searchStdAccumBackingBean.benefitInput}"></h:inputHidden>
									</TD>
								</TR>

							</table>

							</div>
							</div>
							</div>
							</div>
							</TD>
						</TR>
				</TABLE>

			</h:form></td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/pageFooter.jsp"%>
			</td>
		</tr>
	</table>
</f:view>
<script language="JavaScript">
	    var headerDiv;
		var accordTab = new Rico.Accordion('accordionTest',{panelHeight:'327',expandedBg:'rgb(130,130,130)',collapsedBg:'rgb(204,204,204)',hoverBg:'rgb(130,130,130)',collapsedTextColor:'rgb(130,130,130)',borderColor:'rgb(204,204,204)'});
		if(document.getElementById('SearchForm:varDataTable') != null) {	
		setColumnWidth('resultHeaderTable','6%:6%:7%:6%:26%:28%:7%:11%:3%');	
		    setColumnWidth('SearchForm:varDataTable','6%:6%:7%:6%:26%:28%:7%:11%:3%');	
			showResultsTab();
			
		}else{
			headerDiv = document.getElementById('searchResultdataTableDiv');
			headerDiv.style.visibility = 'hidden';
		}	
		
        var varInTxt = document.getElementById('SearchForm:contractType_txt').value;
    	var values = varInTxt.split("~");
		copyToScreen(values[0]);

	copyHiddenToDiv('SearchForm:lineOfBusinessHidden','lineOfBusinessDiv');	
	copyHiddenToDiv('SearchForm:accumHidden','accumDiv');
	copyHiddenToDiv('SearchForm:businessEntityHidden','businessEntityDiv');

	copyHiddenToDiv('SearchForm:businessGroupHidden','businessGroupDiv');
	copyHiddenToDiv('SearchForm:marketBusinessUnitHidden','marketBusinessUnit');
	copyHiddenToDiv('SearchForm:selectQuesHidden','questionSelectDiv');
	copyHiddenToDiv('SearchForm:bnHidden','bnDiv');
	
	function copyToScreen(value1){
		document.getElementById('SearchForm:contractType_txt').value = value1;
	}
	
		


function loadSearchPopup(queryName,headerName,titleName,displayDiv,outComp){
	ewpdModalWindow_ewpd( '../popups/benefitpopupForStandardAccumulator.jsp'+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random(), displayDiv,outComp,2,1);
}
		if(document.getElementById('SearchForm:varDataTable')!= null){
		document.getElementById('SearchForm:varDataTable').onresize = syncTables;
		syncTables();
		}
		
		function syncTables(){
			var relTblWidth = document.getElementById('SearchForm:varDataTable').offsetWidth;
			document.getElementById('resultHeaderTable').width = relTblWidth + 'px';
		}
		
		function setAccumValues(){
    	
		var lob = document.getElementById('SearchForm:lineOfBusinessHidden').value
    	var be = document.getElementById('SearchForm:businessEntityHidden').value
    	var bg = document.getElementById('SearchForm:businessGroupHidden').value
    	var mbu = document.getElementById('SearchForm:marketBusinessUnitHidden').value
    	var ques = document.getElementById('SearchForm:selectQuesHidden').value
    	var accum = document.getElementById('SearchForm:accumHidden').value
    	var type = document.getElementById('SearchForm:contractType_txt').value
   	    var ben = document.getElementById('SearchForm:bnHidden').value

   	 	
        
     	document.getElementById('SearchForm:lobInput').value = lob;
     	document.getElementById('SearchForm:beInput').value = be;
    	 	document.getElementById('SearchForm:bgInput').value =bg;
    	 	document.getElementById('SearchForm:mbuInput').value =mbu;
     	document.getElementById('SearchForm:questInput').value = ques;
     	document.getElementById('SearchForm:accumInput').value = accum;
     	document.getElementById('SearchForm:typeInput').value = type;
     	document.getElementById('SearchForm:benefitInput').value = ben;
    	
	}
		
</script>

</BODY>
</HTML>