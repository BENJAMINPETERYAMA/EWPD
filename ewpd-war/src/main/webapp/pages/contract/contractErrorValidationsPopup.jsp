<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<!-- WAS 6.0 Migration Changes - html_extended taglib was not included  -->
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<TITLE>Error Validations</TITLE>
<base target=_self>
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
<script>
function processConfirm(){
	
	if(window.opener != null && !window.opener.closed){
        var contractBasicInfoButton = window.opener.document.getElementById('contractForm:button2');
        var MembershipInfoButton = window.opener.document.getElementById('MembershipInfoForm:button2');
        var SpecificInfoButton = window.opener.document.getElementById('SpecificInfoForm:button2');
        var AdaptedInfoButton = window.opener.document.getElementById('AdaptedInfoForm:button2');
        var pricingInfoButton = window.opener.document.getElementById('pricingInfoForm:button2');
        var contractNotesButton = window.opener.document.getElementById('contractNotesForm:button2');
       	var ContractCommentButton = window.opener.document.getElementById('ContractCommentForm:button2');
       	var ContractProAdminCommentButton = window.opener.document.getElementById('formName:doneButton');
        var rulesInfoButton = window.opener.document.getElementById('rulesInfoForm:button2');
        
      if(contractBasicInfoButton){
        	window.opener.document.getElementById('contractForm:checkall').checked = true;
        	window.opener.document.getElementById('contractForm:invokeWebService').value = 'confirm';
        	contractBasicInfoButton.click();
        	window.close();
        } else if(MembershipInfoButton){
        	window.opener.document.getElementById('MembershipInfoForm:checkall').checked = true;
        	window.opener.document.getElementById('MembershipInfoForm:invokeWebService').value = 'confirm';
        	MembershipInfoButton.click();
        	window.close();
        }else if(SpecificInfoButton){
        	window.opener.document.getElementById('SpecificInfoForm:checkall').checked = true;
        	window.opener.document.getElementById('SpecificInfoForm:invokeWebService').value = 'confirm';
        	SpecificInfoButton.click();
        	window.close();
        }else if(AdaptedInfoButton){
        	window.opener.document.getElementById('AdaptedInfoForm:checkall').checked = true;
        	window.opener.document.getElementById('AdaptedInfoForm:invokeWebService').value = 'confirm';
        	AdaptedInfoButton.click();
        	window.close();
        } else if(pricingInfoButton){
        	window.opener.document.getElementById('pricingInfoForm:checkall').checked = true;
        	window.opener.document.getElementById('pricingInfoForm:invokeWebService').value = 'confirm';
        	pricingInfoButton.click();
        	window.close();
        }else if(contractNotesButton){
        	window.opener.document.getElementById('contractNotesForm:checkall').checked = true;
        	window.opener.document.getElementById('contractNotesForm:invokeWebService').value = 'confirm';
        	contractNotesButton.click();
        	window.close();        
        }else if(ContractCommentButton){
            window.opener.document.getElementById('ContractCommentForm:checkall').checked = true;
        	window.opener.document.getElementById('ContractCommentForm:invokeWebService').value = 'confirm';
        	ContractCommentButton.click();
        	window.close();
        }else if(ContractProAdminCommentButton){
            window.opener.document.getElementById('formName:checkall').checked = true;
        	window.opener.document.getElementById('formName:invokeWebService').value = 'confirm';
        	ContractProAdminCommentButton.click();
        	window.close();
        }else if(rulesInfoButton){
        	window.opener.document.getElementById('rulesInfoForm:checkall').checked = true;
        	window.opener.document.getElementById('rulesInfoForm:invokeWebService').value = 'confirm';
        	rulesInfoButton.click();
        	window.close();
        } 
        return;
     }
}

function invokeParentForCancelAction(){
	if(window.opener != null && !window.opener.closed){
        var contractBasicInfoHiddenLink = window.opener.document.getElementById('contractForm:cmdLnkIdForCancelButton');
        var membershipInfoHiddenLink = window.opener.document.getElementById('MembershipInfoForm:cmdLnkIdForCancelButton');
        var SpecificInfoHiddenLink = window.opener.document.getElementById('SpecificInfoForm:cmdLnkIdForCancelButton');
        var AdaptedInfoFormHiddenLink = window.opener.document.getElementById('AdaptedInfoForm:cmdLnkIdForCancelButton');
        var pricingInfoHiddenLink = window.opener.document.getElementById('pricingInfoForm:cmdLnkIdForCancelButton');
        var contractNotesHiddenLink = window.opener.document.getElementById('contractNotesForm:cmdLnkIdForCancelButton');
       	var ContractCommentHiddenLink = window.opener.document.getElementById('ContractCommentForm:cmdLnkIdForCancelButton');
      	var ContractProAdminHiddenLink = window.opener.document.getElementById('formName:cmdLnkIdForCancelButton');
       	var rulesInfoHiddenLink = window.opener.document.getElementById('rulesInfoForm:cmdLnkIdForCancelButton');
       
        if(contractBasicInfoHiddenLink){
        	contractBasicInfoHiddenLink.click();       
			window.close();
        	return false;
        }else if(membershipInfoHiddenLink){
        	membershipInfoHiddenLink.click();       
        	window.close();  
        	return false;      
        }else if(SpecificInfoHiddenLink){
        	SpecificInfoHiddenLink.click();       
           	window.close();
        	return false;
        }else if(AdaptedInfoFormHiddenLink){
        	AdaptedInfoFormHiddenLink.click();       
          	window.close();
        	return false;
        }else if(pricingInfoHiddenLink){
        	pricingInfoHiddenLink.click();       
        	window.close();
        	return false;
        }else if(contractNotesHiddenLink){
        	contractNotesHiddenLink.click();       
     	   	window.close();
        	return false;
        }else if(ContractCommentHiddenLink){
        	ContractCommentHiddenLink.click();       
        	window.close();
        	return false;
        }else if(ContractProAdminHiddenLink){
        	ContractProAdminHiddenLink.click();       
        	window.close();
        	return false;
        }else if(rulesInfoHiddenLink){
        	rulesInfoHiddenLink.click();       
        	window.close();
        	return false;
        } 
        return false;
     }
}    
</script>
</HEAD>


<f:view>
<body onload="onClosePopup();">
		<h:form id="contractErrorValidationsForm">
			<h:inputHidden id="hidd_init" value="#{contractBasicInfoBackingBean.initVendor}"/>
			<h:inputHidden id="hidd_StartDate" value="#{contractBasicInfoBackingBean.dateSegment}"/>
			<h:inputHidden id="hidd_VendorPopUp" value="#{contractBasicInfoBackingBean.closePopup}"/>
			<h:inputHidden id="hidd_DateSegmentClickStatus" value="#{contractBasicInfoBackingBean.checkVendorInfoPopup}"/>
			<h:commandLink id="cmdLnkIdFindStartdateFromDateSegment"
				style="display:none; visibility: hidden;"
				action="#{contractBasicInfoBackingBean.fetchVendorInfo}"></h:commandLink>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
		<TR>
			<td valign="top" class="ContentArea" colSpan="2">
				<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
				<tr>
				<td height="16" valign="middle" bgcolor="#7670B3" class="breadcrumb">
					<b>Contract:</b><h:outputText id="contractId" value="#{contractBasicInfoBackingBean.contractIdDiv}"/> 	
					<b>Version:</b><h:outputText id="contractVersion" value="#{contractBasicInfoBackingBean.version}"/> 
					<b>Effective Date:</b><h:outputText id="contracteffds" value="#{contractBasicInfoBackingBean.carvEffectiveDate}"/> 
					<b>Expiry Date:</b> <h:outputText id="contractendds" value="#{contractBasicInfoBackingBean.carvEndDate}"/>	
				 </td>
				</tr>
				</TABLE>
				<br/>
				<t:div id="messagePanelHeader1" style="position: relative; color: red; width: 99%">
                          <b>Please check the below validation errors.<br>
								   Click on Confirm to ignore the errors and Check In the contract.</b>
                </t:div>
                <br/>
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<h:commandButton styleClass="wpdbutton"	id="confirmButton" value="Confirm" onclick="processConfirm();return false;">
							</h:commandButton> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<h:commandButton styleClass="wpdbutton" id="cancelButton" value="Cancel" onclick="invokeParentForCancelAction();return false;">
							</h:commandButton>
						</td>
						<td align="right"></td>
					</tr>
				</table>
				<br/>
				<fieldset style="height:300;">				
					<div id="searchPanelHeader" style="position:relative;z-index:1">
						<TABLE id=TabTable cellSpacing=0 cellPadding=0 width=250 border=0>
							<TBODY>
							<TR>
								<TD width ="200">
									<TABLE   width="200" id=TabTable border="0" cellpadding="0" cellspacing="0" border=0>
										<TR>
											<TD align=left width=2><IMG height=21 alt="Vendor Information"
												src="../../images/activeTabLeft.gif" width=3></TD>
											<TD width="196" class=tabActive><h:outputText
												id="mandateCatLabel" value="Vendor Information"></h:outputText></TD>
											<TD align=right width=2><IMG height=21 alt="Tab Right Active"
												src="../../images/activeTabRight.gif" width=2></TD>
										</TR>
									</TABLE>
								</TD>
								<TD width="200">
								<t:div style='#{contractBasicInfoBackingBean.webServiceFlag == true ? "display:block" : "display:none"}'>  
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><img
												src="../../images/tabNormalLeft.gif" alt="eBX Validations"
												width="3" height="21" /></TD>
											<TD class="tabNormal">
												<h:commandLink action="#{contractBasicInfoBackingBean.doProcessSimulationWebServices}">
												<h:outputText id="LabelPricingInformation" value="eBX Validations"></h:outputText>
												</h:commandLink></td>
											<TD width="2" align="right"><img
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21" /></TD>
										</TD>
									</TABLE>
								</t:div>
								</TD>
							</TR>
							<TR>
								<TD width=200>
								<TABLE cellSpacing=0 cellPadding=0 width=200 border=0>
								</TABLE>
								</TD>
							</TR>
							</TBODY>
						</TABLE>
					</div>
                	<FIELDSET style="margin-left:6px;margin-right:2px;height:250;">
                	<table width="80%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td class="ContentArea">
							<div id="searchPanelContent" class="tabContentBox" style="position:relative;">
							<br/>
							<t:div rendered="#{contractBasicInfoBackingBean.searchDateSegmentList == null}">      
	                            <TABLE class="smallfont" id="resultsTable">
								<TR>
									<TD>
										<div style="position: relative; color: red; width: 99%" align="center">											
											<b>No available data for carved out variable.</b>
										</div>
									</TD>
								</TR>
						  		</TABLE>      
                            </t:div>
                            <t:div rendered="#{contractBasicInfoBackingBean.searchDateSegmentList != null}">       
	                           <table width="100%" cellpadding="2" border="0" class="outputText">
	                           <TBODY>
	                           <TR>
	                           	<TD width="230" valign="top" class="leftPanel">
                				<div><b><h:outputText id="dateSegmentHeaderId" value="Carvedout DateSegments"></h:outputText></b></div>
								<DIV class="treeDiv" style="height:100%;width:230">
									<t:dataTable headerClass="dataTableHeader" id="searchDateSegmentsResultTable" 
			          					var="singleValue" cellpadding="3" 
			       						 	cellspacing="2" bgcolor="#cccccc"
								 			rendered="#{contractBasicInfoBackingBean.searchDateSegmentList != null}" 
								 			value="#{contractBasicInfoBackingBean.searchDateSegmentList}" rowClasses="dataTableEvenRow,dataTableOddRow" 
								 			border="0" width="100%" rowIndexVar="row">
			           							<h:column >
			           								<h:commandLink id="linkToRetrieveDateSegments" onclick="fetchStartDateFromDateSegment(#{row}); return false;">
			           								<h:outputText id="dateSegmentsId" value="#{singleValue.daterange}"></h:outputText></h:commandLink>								
			           							</h:column>											
					              	</t:dataTable>
								</DIV>
							</TD>
							<TD>
							<div id="searchResultdataTableDiv" style="height:220px; overflow:auto; width:100%;">
								<h:dataTable headerClass="dataTableHeader" id="searchResultTable" var="singleValue" cellpadding="3" 
		          						 		cellspacing="2" bgcolor="#cccccc"
										 		rendered="#{contractBasicInfoBackingBean.searchVendorResultList != null}" 
										 		value="#{contractBasicInfoBackingBean.searchVendorResultList}" rowClasses="dataTableEvenRow,dataTableOddRow" 
										 		border="0" width="100%">
             							<h:column >
             								<f:facet name="header">
														<f:verbatim>Benefit Component</f:verbatim>
												</f:facet>
             								<h:outputText id="majorHeading" value="#{singleValue.benefitCompName}"></h:outputText>
										
             							</h:column>
             							<h:column >
             								<f:facet name="header">
														<f:verbatim>Benefit</f:verbatim>
												</f:facet>
										<h:outputText id="minorHeading" value="#{singleValue.benefitName}"></h:outputText>
             							</h:column>
             							<h:column >
             								<f:facet name="header">
														<f:verbatim>Question</f:verbatim>
												</f:facet>
										<h:outputText id="variable" value="#{singleValue.vendorRef}"></h:outputText>
             							</h:column>
             							<h:column >
             								<f:facet name="header">
														<f:verbatim>Description</f:verbatim>
												</f:facet>
										<h:outputText id="description" value="#{singleValue.vendorRefDesc}"></h:outputText>
             							</h:column>
		              			</h:dataTable> 
		              		</div>
		              		</td>
	                     </T>
	                     </TBODY>
	                     </TABLE>
	                    </t:div>
                        </div>
					</td>
				</tr>
			</table>
	</FIELDSET>
</fieldset>
			</TD>
			</TR>
			</TBODY>
</TABLE>
</h:form>
	<script language="JavaScript">	
		
		function fetchStartDateFromDateSegment(val){
			var tableObj = document.getElementById("contractErrorValidationsForm:searchDateSegmentsResultTable");
		  	var tableRows = tableObj.rows;	  	
		  	var dateSegment = tableRows[val].cells[0].children[0].innerText;
		  	document.getElementById("contractErrorValidationsForm:hidd_StartDate").value = dateSegment;
		  	document.getElementById("contractErrorValidationsForm:cmdLnkIdFindStartdateFromDateSegment").click();
		}	
		function onClosePopup(){
			if(document.getElementById("contractErrorValidationsForm:hidd_DateSegmentClickStatus").value == "false")
				document.getElementById("contractErrorValidationsForm:searchDateSegmentsResultTable:0:linkToRetrieveDateSegments").click();
		}
		
		
	</script>
	
	</BODY>
</f:view>
</HTML>
