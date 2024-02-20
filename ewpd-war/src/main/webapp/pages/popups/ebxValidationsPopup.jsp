<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<!-- WAS 6.0 Migration Changes - the taglib contains an incorrect path,which results in JspTranslationException.In WAS 7 the path should be changed to   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">

<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">

<TITLE>Error Validations</TITLE>
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
<script language="javascript"> 
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

function openRulePopup(val){ 
	var tableObj = document.getElementById("ebxForm:searchResultTable"); 
	var tableRows = tableObj.rows;
	var ruleId;
	for(var j=0; j< tableRows.length; j++) {
		if(tableRows[val+1].cells[2].children[0].id == tableRows[j+1].cells[2].children[0].id){
			ruleId =""+document.getElementById("ebxForm:searchResultTable:" + (j) + ":headerRule").innerHTML;
			break;
		}
	}
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = 'EBXRuleID';
	var url = '../popups/headerRulePopup.jsp' + getUrl() + '?temp=' + Math.random() + '&ruleId=' + ruleId;
	newWinForView = window.showModalDialog(url,	param,'dialogHeight:150px;dialogWidth:400px;resizable=false;status=no;scroll=no;');
	return false;
}
function openSpsPopup(val){ 
	var tableObj = document.getElementById("ebxForm:searchResultTable"); 
	var tableRows = tableObj.rows;
	var spsId;
	for(var j=0; j< tableRows.length; j++) {
		if(tableRows[val+1].cells[2].children[0].id == tableRows[j+1].cells[2].children[0].id){
			spsId =""+document.getElementById("ebxForm:searchResultTable:" + (j) + ":SPSId").innerHTML;
			break;
		}
	}
	var param = new Object();
	param.parentWindow = window;
	param.hiddenId = 'EBXSPSID';
	var url = '../popups/spsPopup.jsp' + getUrl() + '?temp=' + Math.random() + '&spsId=' + spsId;
	newWinForView = window.showModalDialog(url,	param,'dialogHeight:100px;dialogWidth:800px;resizable=false;status=no;scroll=no;');
	return false;
}


</script>
</HEAD>
<f:view>
	<BODY>
	<h:form styleClass="form" id="ebxForm">
	<h:inputHidden id="hidd_init" value="#{contractBasicInfoBackingBean.initEbxWs}"/>
	<table width="100%" cellpadding="0" cellspacing="0">
	<TR>
	<td>
	<TABLE width="100%" id="breadCumbTable" bgcolor="#7670B3">
		<tr>
			<td height="16" valign="middle" bgcolor="#7670B3"
				class="breadcrumb"><b>Contract:</b><h:outputText id="ebxcontract" value="#{contractBasicInfoBackingBean.ebxWebSerPopupDisplayVO.ebxContract}"/> 	
				<b>Version:</b><h:outputText id="ebxcontractversion" value="#{contractBasicInfoBackingBean.ebxWebSerPopupDisplayVO.ebxVersion}"/> 
				<b>Effective Date:</b><h:outputText id="ebxcontracteffds" value="#{contractBasicInfoBackingBean.ebxWebSerPopupDisplayVO.ebxEffectiveDate}"/> 
				<b>Expiry Date:</b> <h:outputText id="ebxcontractendds" value="#{contractBasicInfoBackingBean.ebxWebSerPopupDisplayVO.ebxExpiryDate}"/>
			 </td>
		</tr>
	</TABLE>
	</td>
	</TR>
	<tr>
		<td>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD colspan="2" valign="top" class="ContentArea">
				<TABLE>
					<TBODY>
						<tr>
							<TD><!-- Insert WPD Message Tag --></TD>
						</tr>
					</TBODY>
				</TABLE>
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<td>
					<font color='red'>
					 <b>Please check the below validation errors.<br>
								   Click on Confirm to ignore the errors and Check In the contract.</b>
					</font>
					</td>
					</tr>
				</table>
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
				<br/>
					<!-- Table containing Tabs -->
				<TABLE width="250" border="0" cellpadding="0" cellspacing="0" id="TabTable">
				<TBODY>
				<TR>
					<TD width ="200">
						<t:div style='#{contractBasicInfoBackingBean.vendorFlag == true ? "display:block" : "display:none"}'>
						<TABLE  width="200" id=TabTable border="0" cellpadding="0" cellspacing="0"border=0>
							<TR>
								<TD align=left width=2><IMG height=21 alt="Tab Left Active"
									src="../../images/tabNormalLeft.gif" width=3>
								</TD>
								<TD width="196" class="tabNormal"><h:commandLink
										action="#{contractBasicInfoBackingBean.getVendorInfo}"
										id="linkToPricingInfo">Vendor Information
										</h:commandLink>
								</TD>
								<TD align=right width=2><IMG height=21 alt="Tab Right Active" src="../../images/tabNormalRight.gif" width=2></TD>
							</TR>
						</TABLE>
						</t:div>
						<t:div style='#{contractBasicInfoBackingBean.vendorFlag == false ? "display:block" : "display:none"}'>
						<!--  if ebx error exist and vendor info tab hide condition  -->
						<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
								<TR>
									<TD width="3" align="left"><img
										src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
										width="3" height="21" /></TD>
									<TD width="196" class="tabActive">eBX Validations</TD>
									<TD width="2" align="right"><img
										src="../../images/activeTabRight.gif" alt="Tab Right Normal"
										width="4" height="21" /></TD>
								</TR>
						</TABLE> 
					 </t:div>								
					</TD>
					
					<TD width="200">
						<t:div style='#{contractBasicInfoBackingBean.vendorFlag == false ? "display:none" : "display:block"}'>
						<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
							<TR>
								<TD width="3" align="left"><img
									src="../../images/activeTabLeft.gif" alt="Tab LeftNormal"
									width="3" height="21" /></TD>
								<TD width="196" class="tabActive">eBX Validations</TD>
								<TD width="2" align="right"><img
									src="../../images/activeTabRight.gif" alt="Tab Right Normal"
									width="4" height="21" /></TD>
							</TR>
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
	<FIELDSET style="margin-left:4px;margin-right:6px;padding-bottom:6px;padding-top:6px;width:95%">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td class="ContentArea">
							<div id="searchPanelContent" class="tabContentBox"
                                                style="position:relative;"><br />
                                          <t:div rendered="#{contractBasicInfoBackingBean.ebxWebSerDisplayList == null && contractBasicInfoBackingBean.ebxProcessInterruptMsg == null}">      
                                          <TABLE class="smallfont" id="resultsTable">
												<TR>
													<TD>
														<div style="position: relative; color: red; width: 99%" align="center">											
															<b>No available data for EBX Errors.</b>
														</div>
													</TD>
												</TR>
										  </TABLE>      
                                         </t:div>
                                         <t:div rendered="#{contractBasicInfoBackingBean.ebxProcessInterruptMsg != null}">      
                                          <TABLE class="smallfont" id="resultsTable" width="100%">
												<TR>
													<TD>
														<div style="position: relative; color: red; width: 99%" align="center">											
															<b>The MTM validations has encountered an issue and cannot display the validation errors.</b>
														</div>
													</TD>
												</TR>
										  </TABLE>      
                                         </t:div>
	<TABLE width="100%" cellpadding="2" border="0" class="outputText">
    <TBODY>
          <TR>
          <td>
              <div id="dataDiv" style="height:300px;width:960px;position:relative;z-index:1;font-size:10px;overflow:auto;">
               <t:dataTable headerClass="fixedDataTableHeader" id="searchResultTable" var="singleValue" cellpadding="3" 
                     cellspacing="2" bgcolor="#cccccc" 
                     	  rowIndexVar="row" 
                          rendered="#{contractBasicInfoBackingBean.ebxWebSerDisplayList != null}" 
                           value="#{contractBasicInfoBackingBean.ebxWebSerDisplayList}" rowClasses="dataTableEvenRow,dataTableOddRow" 
                           border="0" width="100%">
               <h:column >
                     <f:facet name="header">
						<f:verbatim>Benefit Component</f:verbatim>
                     </f:facet>
                     <h:outputText id="benefitComponent" value="#{singleValue.benefitComponent}"></h:outputText>
               </h:column>
               <h:column >
                     <f:facet name="header">
                         <f:verbatim>Benefit</f:verbatim>
					</f:facet>
                    <h:outputText id="benefit" value="#{singleValue.benefit}"></h:outputText>
               </h:column>
               <h:column >
                     <f:facet name="header">
						<f:verbatim>Header Rule</f:verbatim>
					</f:facet> 
					<h:commandLink title="Click to get Header Rule Info popup" id="headerRulePopup" onclick="return openRulePopup(#{row})"><h:outputText id="headerRule" value="#{singleValue.headerRule}"></h:outputText></h:commandLink>    
               </h:column>
               <h:column >
                     <f:facet name="header">
						<f:verbatim>SPS Id</f:verbatim>
					</f:facet>
                    <h:commandLink title="Click to get SPS ID Info popup" id="spsIdPopup" onclick="return openSpsPopup(#{row})"><h:outputText id="SPSId" value="#{singleValue.SPSId}"></h:outputText></h:commandLink>
               </h:column>
               <h:column >
                     <f:facet name="header">
                         <f:verbatim>PVA</f:verbatim>
                     </f:facet>
                     <h:outputText id="PVA" value="#{singleValue.PVA}"></h:outputText>
               </h:column>
               <h:column >
                     <f:facet name="header">
                         <f:verbatim>Format</f:verbatim>
                     </f:facet>
                     <h:outputText id="format" value="#{singleValue.format}"></h:outputText>
               </h:column>
               <h:column>
                     <f:facet name="header">
                        <f:verbatim>Value Coded</f:verbatim>
                     </f:facet>
                     <h:outputText id="valuecoded" value="#{singleValue.valueCoded}"></h:outputText>
               </h:column>
              <h:column>
                     <f:facet name="header">
                         <f:verbatim>Message Text</f:verbatim>
                     </f:facet>
                     <h:outputText id="messageText" value="#{singleValue.messageText}"></h:outputText>
               </h:column>     
               <h:column >
                     <f:facet name="header">
                        	<f:verbatim>Note Type Code</f:verbatim>
                     </f:facet>
                     <h:outputText id="noteTypeCode" value="#{singleValue.noteTypeCode}"></h:outputText>
               </h:column> 
               <h:column >
                     <f:facet name="header">
							<f:verbatim>Error</f:verbatim>
					</f:facet>
					<h:outputText id="error" value="#{singleValue.error}"></h:outputText>
               </h:column> 
               <h:column >
                     <f:facet name="header">
						<f:verbatim>Error Description</f:verbatim>
					 </f:facet>
					 <h:outputText id="errorDescription" value="#{singleValue.errorDescription}"></h:outputText>
               </h:column>   
         	</t:dataTable>
        	</div>
            </td>
            </TR>
            </TBODY>
      </TABLE> 
      </div>
							
		</td>
		</tr>
		<tr>
		<td>
	<t:div style="#{contractBasicInfoBackingBean.ebxProcessInterruptMsg != null ? 'display:none' : 'display:block'}">
	<table width="100%"  bgColor="#cccccc">
		<tr>
			<td><SPAN style="margin-left: 6px; margin-right: 2px;">
			<h:commandButton id="ebxDownloadExcelCommandButton" 
				styleClass="wpdButton" value="Export"
				action="#{contractBasicInfoBackingBean.downloadExcel}">
			</h:commandButton> </SPAN></td>
		</tr>
	</table>
	</t:div>
	</td>
			</tr>
		</table>	
	<!--</td>
	
</tr>
</table> --></FIELDSET></TD></TR>	
	</table>
	</h:form>
	</BODY>
</f:view>
</HTML>
