<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css" 			title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"		title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" 	title="Style">

<TITLE>WellPoint Product Database: Rules Information</TITLE>
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
<BASE target="_self" />
</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
<!-- WAS 7.0 Changes - Hidden id pageInit value loaded using binding instead of value -->
	
	<h:inputHidden id="pageInit" 	binding="#{contractRuleBackingBean.loadContractRules}"></h:inputHidden>
	<h:inputHidden id="hiddenDateSegmentSysId" value="#{contractRuleBackingBean.hiddenDateSegmentSysId}"></h:inputHidden>
	<h:inputHidden id="contractID" value="#{contractRuleBackingBean.contractID}"></h:inputHidden>
	<h:inputHidden id="selectedDateSysId" value="#{contractRuleBackingBean.selectedDateSysId}"></h:inputHidden>
	<h:inputHidden id="pnrRuleSelected" value="#{contractRuleBackingBean.pnrRuleSelected}"></h:inputHidden>
	<h:inputHidden id="exclusionRuleSelected" value="#{contractRuleBackingBean.exclusionRuleSelected}"></h:inputHidden>
	<h:inputHidden id="denialRuleSelected" value="#{contractRuleBackingBean.denialRuleSelected}"></h:inputHidden>
	<h:inputHidden id="umRuleSelected" value="#{contractRuleBackingBean.umRuleSelected}"></h:inputHidden>
	
		<TR>
			<TD><jsp:include page="../navigation/top_view.jsp"></jsp:include></TD>
		</TR>
		<tr>
			<td>
			<h:form styleClass="form" id="rulesInfoForm">
				
						<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" id="rulesinformationtable">
						<TR>
							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><!-- Space for Tree  Data	--> 
							 <jsp:include page="contractTree.jsp"></jsp:include> 
								</DIV>
							</TD>

							<TD colspan="2" valign="top" class="ContentArea">
							
							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable111">
									<TR>
					          			<TD width="16%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable111">
												<TR>
													<TD width="3" align="left"><IMG src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" id="img8"></TD>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractBasicInfoBackingBean.getBasicInfo}"  id="rulesinformationcl1">
												<h:outputText id="GeneralInformation" value="General Information" />
											</h:commandLink> 
													</TD>
													<TD width="2" align="right"><IMG src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" id="img9"></TD>
												</TR>
											</TABLE>
		          						</TD>
									
										
										<TD width="18%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable11">
												<TR>
													<TD width="3" align="left"><img	src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" id="img10" /></td>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractPricingInfoBackingBean.dispalyContractPricingInfoTab}" id="rulesinformationcl2"><h:outputText id="pricingInfoTabTable"  value="Pricing Information" /> </h:commandLink> 
													
													</TD>
													<td width="2" align="right"><img src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" id="img11"/> </td>
												</TR>
											</TABLE>
										</TD>
										<TD width="10%"  id="tabForStandard1">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable12">
												<TR>
													<TD width="3" align="left"><img	id="img1" src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /> </td>
													<td class="tabNormal"><h:commandLink
														action="#{ContractNotesBackingBean.load}"
														id="contractNotesID">
														<h:outputText id="labelNotes" value="Notes"></h:outputText>
													</h:commandLink></td>
													<td width="2" align="right"><img id="img2" src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /> </td>
												</TR>
											</TABLE>
										</TD>
										<TD width="14%">
											<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable13">
												<TR>
													<TD width="3" align="left"><img	id="img3" src="../../images/tabNormalLeft.gif" alt="Tab Left Normal" width="3" height="21" /> </td>
													<TD width="100%" class="tabNormal"> 
														<h:commandLink action="#{contractCommentBackingBean.loadComment}" id="linkToComment"><h:outputText id="commentsTabTable" value="Comments" /> </h:commandLink> 
													</TD>
													<td width="2" align="right"><img id="img4" src="../../images/tabNormalRight.gif" alt="Tab Right Normal" width="4" height="21" /> </td>
												</TR>
											</TABLE>
										</TD>
							<td width="14%"  id="tabForStandard">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable14">
								<tr>
									<td width="3" align="left"><img
										src="../../images/tabNormalLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" id="img12"/> </td>
									<td class="tabNormal"><h:commandLink action="#{contractProductAdminOptionBackingBean.displayContractAdminOption}" id="linkToAdminOption">
										<h:outputText id="LabelAdminOption" value="Admin Option"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
										width="4" height="21" id="img5" /> </td>
								</tr>
							</table>
							</td>
							<td width="14%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="TabTable15">
								<tr>
									<td width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21"id="img6" /> </td>
									<td class="tabActive"><h:commandLink action="#{contractRuleBackingBean.displayRules}" id="linkToRules">
										<h:outputText id="rules" value="Rules"></h:outputText>
									</h:commandLink></td>
									<td width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21"  id="img7"/> </td>
								</tr>
							</table>
							</td>
									</TR>
								</TABLE>
						<!-- End of Tab table -->

						<fieldset
							style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">

					
						
									<TABLE width="100%" cellspacing="0" cellpadding="0" id="TabTable16">
										<TR>
											<TD>
<!-- 
											<DIV id="resultHeaderDiv">
 -->	
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="headerTable" border="0" width="100%">
												<TR>
													<TD><b> <h:outputText id="AssociatedRuleId" value="Associated Rule Id" /></b></TD>
												</TR>
											</TABLE>

		<table style="margin-top: 6px;">
					<tr>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadExclusionRule}"
							styleClass="#{contractRuleBackingBean.exclusionRuleSelected ? 'sectionheading': ''}" id="rulesinformationcl3">
							<h:outputText id="ExclusionRule" value="Exclusion Rule" styleClass="#{contractRuleBackingBean.exclusionRuleSelected ? 'sectionheading': ''}" /> </h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadUMRule}"
							styleClass="#{contractRuleBackingBean.umRuleSelected ? 'sectionheading': ''}" id="rulesinformationcl4">
							<h:outputText id="UMRule" value="UM Rule" styleClass="#{contractRuleBackingBean.umRuleSelected ? 'sectionheading': ''}" /> </h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadDenialRule}"
							styleClass="#{contractRuleBackingBean.denialRuleSelected ? 'sectionheading': ''}" id="rulesinformationcl5">
							<h:outputText id="DenialRule" value="Denial Rule" styleClass="#{contractRuleBackingBean.denialRuleSelected ? 'sectionheading': ''}" /> </h:commandLink>  
						</td>
						<td>
							 &nbsp;|&nbsp;
						</td>
						<td align="left" >
							<h:commandLink action="#{contractRuleBackingBean.loadPNRRule}"
							styleClass="#{contractRuleBackingBean.pnrRuleSelected ? 'sectionheading': ''}" id="rulesinformationcl6">
							<h:outputText id="PNRRule" value="PNR Rule" styleClass="#{contractRuleBackingBean.pnrRuleSelected ? 'sectionheading': ''}" /> </h:commandLink>  
						</td>
						<td align="right" width="60%">
                      <a href="#" onclick="loadSearchPopupWithName('../popups/extractRulespopup.jsp','extractrule','EXTRACT ALL RULES',
                                                         'Extract Rules Popup','ExtractRuleDiv','ExtractRuleDiv','contract');return false;">
										&nbsp;<h:outputText id="ExtractRules" value="Extract Rules" styleClass="variableLink" /> </a>
                       </td>
				</tr>
		</table>								
<BR/>
						<div id="resultInfo">
						<div id="resultInfoExclusion" class="dataTableColumnHeader">
						<CENTER>No associated Exclusion Rule.	</CENTER></div>
						<div id="resultInfoDenial" 	class="dataTableColumnHeader">
						<CENTER>No associated Denial Rule.	</CENTER></div>
						<div id="resultInfoUM" 		class="dataTableColumnHeader">
						<CENTER>No associated UM Rule.	</CENTER></div>
						<div id="resultInfoPNR" 	class="dataTableColumnHeader">
						<CENTER>No associated PNR Rule.	</CENTER></div>
						</div>
											<DIV id="resultHeaderDiv">
											<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
												id="resultHeaderTable" border="0" width="100%" style="width:98%;">
												<TBODY>
													<TR class="dataTableColumnHeader">
														<TD align="center" style="width: 15.7%;"><h:outputText id="EWPDRuleId" value="EWPD Rule Id" />														</TD>
														<TD align="center"><h:outputText id="Rule_Id" value="Rule Id"/>														</TD>
														<TD align="center"><h:outputText id="Description" value="Description" />														</TD>
														<TD align="center"><h:outputText id="PVA" value="PVA" />														</TD>
														<TD align="center"><h:outputText id="Group_Indicator" value="Group Indicator" />														</TD>
														<TD align="center"><h:outputText id="val1" value="Value" />														</TD>
														<TD align="center"></TD>	
														</TR>
												</TBODY>
											</TABLE>
											</DIV>
											</TD>
										</TR>

										<tr>
											<td>
												<div id="resultsTableDiv" style="height:106px; overflow: auto;">
												<h:panelGrid id="panelTable" binding="#{contractRuleBackingBean.panel}" rowClasses="dataTableEvenRow,dataTableOddRow" />
												  
												</div>
											</td>
										</tr>
									</TABLE>
									</fieldset>
						

						<!--	End of Page data	-->
						<br>

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
						 <table  border="0" cellspacing="0" cellpadding="3" width="100%" id="TabTable17">
								<tr>
									<!-- Transfer Log -->
									<td align="left"><a href="#" onclick=" showPopupForContractTransferLog();return false;"><h:outputText id="Log" value="Transfer Log" styleClass="variableLink" /> </a></td>
									<td align="left" width="127">
									<table Width=100%>
										<tr>
											<td><h:outputText id="state" value="State" /> </td>
											<td>:<h:outputText id="txtStateView"
												value="#{contractRuleBackingBean.state}" /> </td>
										</tr>
										<tr>
											<td><h:outputText id="status" value="Status" /> </td>
											<td>:<h:outputText id="txtStatusView"
												value="#{contractRuleBackingBean.status}" /> </td>
										</tr>
										<tr>
											<td><h:outputText id="Version" value="Version" /> </td>
											<td>:<h:outputText id="txtVersionView"
												value="#{contractRuleBackingBean.version}" /> </td>
										</tr>
									</table>
									</td>
								</tr>
								
										
							</table>
						</FIELDSET>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->

				

				<h:commandLink id="deleteButton1"
					action="#{contractRuleBackingBean.deleteRuleInfo}"
					style="display:none; visibility: hidden;" >
					<f:verbatim> &nbsp;&nbsp;</f:verbatim>
				</h:commandLink>
									<h:inputHidden id="selectedDateSegmentSysID"
										value="#{contractRuleBackingBean.selectedDateSysId}"></h:inputHidden>
									<h:inputHidden id="selectedRuleSysID"
										value="#{contractRuleBackingBean.selectedRuleSysID}"></h:inputHidden>
									
									<h:inputHidden id="renderFlagExclusion"	value="#{contractRuleBackingBean.exclusionRuleSelected}" />
									<h:inputHidden id="renderFlagDenial"	value="#{contractRuleBackingBean.denialRuleSelected}" />
									<h:inputHidden id="renderFlagUM"		value="#{contractRuleBackingBean.umRuleSelected}" />
									<h:inputHidden id="renderFlagPNR"		value="#{contractRuleBackingBean.pnrRuleSelected}" />
<h:inputHidden id="hiddenProductType"
					value="#{contractBenefitGeneralInfoBackingBean.pageLoadBasedOnContractType}"></h:inputHidden>

									<h:inputHidden id="renderFlag" value= "#{contractRuleBackingBean.renderFlagForPanel}" /> 
						
									<h:commandLink id="deleteLink"
											style="display:none; visibility: hidden;"
											action="#{contractRuleBackingBean.deleteRuleInfo}">
											<f:verbatim/>
									</h:commandLink>
				<!-- End of hidden fields  -->

			</h:form>
			</td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/pageFooter_View.jsp"%></td>
		</tr>
	</table>
	<DIV id="ExtractRuleDiv" ></DIV>
	</BODY>
</f:view>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractRulesInformation" /> </form>
<script>
function showPopupForContractTransferLog(){		
	var url='../contract/contractViewTransferLog.jsp'+getUrl()+'?temp='+Math.random();
	var returnvalue=window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;");
	if(returnvalue == undefined)
	{
//		getObj('ContractBasicSearchForm:searchButton').click();
	}
}
i = document.getElementById("rulesInfoForm:hiddenProductType").value;
	if(i=='MANDATE')
	{
	tabForStandard.style.display='none';
	tabForStandard1.style.display='none';
	}else{
	tabForStandard.style.display='';
	tabForStandard1.style.display='';
	}


	
	hideIfNoValue('resultHeaderDiv');
	function hideIfNoValue(divId){
		if(document.getElementById('rulesInfoForm:renderFlag').value=='true'){
			document.getElementById(divId).style.visibility = 'visible';
			document.getElementById('resultHeaderTable').width = "100%";
			var relTblWidth = document.getElementById('resultHeaderTable').offsetWidth;
			if(document.getElementById('rulesInfoForm:panelTable')!=null && document.getElementById('rulesInfoForm:panelTable').offsetHeight <= 106) {
				document.getElementById('rulesInfoForm:panelTable').width = relTblWidth+"px";
				setColumnWidth('rulesInfoForm:panelTable','14%:11%:45%:6%:10%:8%:4%');
				setColumnWidth('resultHeaderTable','14%:11%:45%:6%:10%:8%:4%');
			}else{
				if(document.getElementById('rulesInfoForm:panelTable')!= null){
					var relTblWidth = document.getElementById('rulesInfoForm:panelTable').offsetWidth;
					
			document.getElementById('resultHeaderTable').width = document.getElementById('rulesInfoForm:panelTable').offsetWidth;
			}

//				document.getElementById('rulesInfoForm:panelTable').height = 106;
	//			document.getElementById('rulesInfoForm:panelTable').width = (relTblWidth-17)+"px";
				
				setColumnWidth('resultHeaderTable','15%:13%:43%:7%:10%:9%:3%');
				setColumnWidth('rulesInfoForm:panelTable','15.7%:13%:43.8%:7%:10%:9%:3.4%');	
		//		document.getElementById('rulesInfoForm:panelTable').height = 106;
		//		document.getElementById('rulesInfoForm:panelTable').width = (relTblWidth-17)+"px";
				
		//		setColumnWidth('rulesInfoForm:panelTable','14.5%:11%:38%:12%:11%:7%:4.2%');
		//		setColumnWidth('resultHeaderTable','13.6%:10.5%:36.5%:11%:10%:8%:6.8%');
			}
				document.getElementById('resultInfo').style.visibility = 'hidden';				
				document.getElementById('resultInfo').style.height = "0px";				
				document.getElementById('resultInfo').style.position = 'absolute';	
		}else{
			document.getElementById('resultsTableDiv').style.visibility = 'hidden';
			document.getElementById('resultsTableDiv').style.height = "0px";
			document.getElementById(divId).style.visibility = 'hidden';
			if(document.getElementById('rulesInfoForm:renderFlagExclusion').value=='false')
			{	
				document.getElementById('resultInfoExclusion').style.visibility = 'hidden';		
				document.getElementById('resultInfoExclusion').style.height = "0px";			
				document.getElementById('resultInfoExclusion').position = 'absolute';	
			}			
			if(document.getElementById('rulesInfoForm:renderFlagDenial').value=='false')
			{	
				document.getElementById('resultInfoDenial').style.visibility = 'hidden';		
				document.getElementById('resultInfoDenial').style.height = "0px";			
				document.getElementById('resultInfoDenial').position = 'absolute';	
			}			
			if(document.getElementById('rulesInfoForm:renderFlagUM').value=='false')
			{	document.getElementById('resultInfoUM').style.visibility = 'hidden';		
				document.getElementById('resultInfoUM').style.height = "0px";			
				document.getElementById('resultInfoUM').position = 'absolute';
			}			
			if(document.getElementById('rulesInfoForm:renderFlagPNR').value=='false')
			{	
				document.getElementById('resultInfoPNR').style.visibility = 'hidden';		
				document.getElementById('resultInfoPNR').style.height = "0px";			
				document.getElementById('resultInfoPNR').position = 'absolute';		
			}			
		}
	}	

function confirmDeletion(key){	
		
		var message = "Are you sure you want to delete?"		
		if(window.confirm(message)){
			document.getElementById('rulesInfoForm:selectedRuleSysID').value = document.getElementById('rulesInfoForm:'+key).value;
			document.getElementById('rulesInfoForm:deleteLink').click();
		}
		else{			
				return false;
		}
	}

function viewAction(ruleIdObj){
	
	var ruleId = document.getElementById('rulesInfoForm:'+ruleIdObj).value;
	var pnrRuleSelected = document.getElementById('pnrRuleSelected').value;
	var exclusionRuleSelected = document.getElementById('exclusionRuleSelected').value;
	var denialRuleSelected = document.getElementById('denialRuleSelected').value;
	var umRuleSelected = document.getElementById('umRuleSelected').value;
	var checkMode = 'view';
	var url = '../contractpopups/ruleViewSubstitute.jsp'+getUrl()+'?ruleId='+escape(ruleId)+'&temp=' + Math.random()
				+'&pnrRuleSelected='+pnrRuleSelected+'&exclusionRuleSelected='+exclusionRuleSelected
				+'&denialRuleSelected='+denialRuleSelected+'&umRuleSelected='+umRuleSelected+'&checkMode='+checkMode;
	window.showModalDialog(url,'dummyDiv','dialogHeight:650px;dialogWidth:1000px;resizable=true;status=no;');
	
}
function loadSearchPopupWithName(popupName,queryName,headerName,titleName,displayDiv,outComp,pageFrom){
		
		var dateSegmentSysID = document.getElementById('hiddenDateSegmentSysId').value;
		var contractID = document.getElementById('contractID').value;
		var url = popupName+getUrl()+'?queryName='+queryName+'&headerName='+headerName+'&titleName='+titleName+'&temp='+Math.random()
				+'&pageFrom='+pageFrom+'&entityId='+dateSegmentSysID+'&entityName='+contractID;
      ewpdModalWindow_ewpd( url, displayDiv,outComp,2,2);
}

</script>
<%@ include file="../navigation/unsavedDataFinderView.html"%>
</HTML>
