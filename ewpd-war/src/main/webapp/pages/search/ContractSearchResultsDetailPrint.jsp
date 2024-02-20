<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<TITLE>Search Engine</TITLE>
<TITLE>Search Engine</TITLE>
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


</HEAD>
<f:view>
	<BODY>
	<table width="100%" cellpadding="0" cellspacing="0">
		<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
			<TR>
				<TD>
					<FIELDSET style="margin-left:12px;margin-right:-10px;padding-bottom:1px;padding-top:1px;width:98%">
						<h:outputText id="breadcrumb" 
							value="Search >> Search Results >> Contracts >> Print">
						</h:outputText>
					</FIELDSET>
				</TD>
			</TR>
			<TR>
				<TD>
					&nbsp;
				</TD>
			</TR>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>
					<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message></w:message></TD>
								</tr>
							</TBODY>
						</TABLE>
						<div id="panel2Header" ><b>
							<h:outputText id="associationText" value="#{searchResultsBackingBean.associationBreadCrumb}">
                        </h:outputText></b>
						   <h:inputHidden id ="associationChain" value="#{searchResultsBackingBean.associationBreadCrumb}"/>
						</div>
							<BR>

						<!-- Table containing Tabs -->
									
								<!--  table starts -->
							
								<h:dataTable 
										styleClass="outputText" headerClass="dataTableColumnHeader"
										var="item" cellpadding="3"
										width="100%" cellspacing="1" 
										value="#{searchResultsBackingBean.allContracts}">
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Id"></h:outputText>
												</h:panelGroup>
											</f:facet> 
											<h:outputText id="sample" value="#{item.contractId}"></h:outputText>
											<h:inputHidden id="hiddenContractId" value="#{item.contractId}"/>
										</h:column>
										<h:column>
											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Type"></h:outputText>
												</h:panelGroup>
											</f:facet>		  
											<h:outputText id="Term" value="#{item.contractType}"></h:outputText>
										</h:column>
										<h:column>
  											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Effective Date"></h:outputText>
												</h:panelGroup>
											</f:facet>		
											<h:outputText id="ed" value="#{item.startDate}">
											<f:convertDateTime pattern="MM/dd/yyyy"/>
											</h:outputText>
											<h:inputHidden id="hiddenEffectiveDate" value="#{item.startDate}">
											<f:convertDateTime pattern="MM/dd/yyyy"/>
											</h:inputHidden>
										</h:column>
										<h:column>
				  							  <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Expiry Date"></h:outputText>
												</h:panelGroup>
											</f:facet> 
											<h:outputText id="st" value="#{item.endDate}">
											<f:convertDateTime pattern="MM/dd/yyyy"/>
											</h:outputText>
											<h:inputHidden id="hiddenExpiryDate" value="#{item.endDate}">
											<f:convertDateTime pattern="MM/dd/yyyy"/>
											</h:inputHidden>
										</h:column>
										<h:column>
											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Version No"></h:outputText>
												</h:panelGroup>
											</f:facet>				
												<h:outputText id="versionNo" value="#{item.versionNo}"></h:outputText>
										</h:column>
										<h:column>
											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Status"></h:outputText>
												</h:panelGroup>
											</f:facet>				
												<h:outputText id="Qualifier" value="#{item.status}"></h:outputText>
										</h:column>

										
									<!--  table ends -->
							</h:dataTable>
								</TD>
							</TR>

						</TABLE>
				
						<!-- Space for hidden fields -->

				<h:inputHidden id="numberOfPages" value="#{searchResultsBackingBean.numberOfPages}"/>
				<h:inputHidden id="checkForView" value="#{searchResultsBackingBean.checkForView}"/>
				<h:inputHidden id="query" value="#{searchResultsBackingBean.query}"/>
				<h:inputHidden id="contractid" value="#{searchResultsBackingBean.contractid}"/>
				<h:inputHidden id="effectiveDate" value="#{searchResultsBackingBean.effectiveDate}"/>
				<h:inputHidden id="expiryDate" value="#{searchResultsBackingBean.expiryDate}"/>
				<h:inputHidden id="clickedIndex" value="#{searchResultsBackingBean.clickedIndex}"/>
				<h:inputHidden id="fieldToSort" value="#{searchResultsBackingBean.fieldToSort}"/>
				<h:inputHidden id="sortOrder" value="#{searchResultsBackingBean.contractSortOrder}"/>
				<h:inputHidden id="sortColumn" value="#{searchResultsBackingBean.contractSortColumn}"/>
				<h:inputHidden id="association" value="#{searchResultsBackingBean.association}"/>
				<h:inputHidden id="selectedIndex" value="#{searchResultsBackingBean.selectedIndex}"/>

				<h:commandLink id="attachmentObjectIndex"  style="display:none; visibility: hidden;"
									 action="#{searchResultsBackingBean.associatedObjectSelectedIndex}">
                              <f:verbatim /></h:commandLink>	
				
			
				<!-- End of hidden fields  -->

			</h:form></td>
		</tr>
		<tr>
			
		</tr>
	</table>
	</BODY>
</f:view>
<script language="JavaScript">
	

	if(document.getElementById('benefitComponentCreateForm:checkForView').value ==1){
		window.showModalDialog('../contract/contractBasicInfoView.jsp'+getUrl()+'?'+document.getElementById('benefitComponentCreateForm:query').value+'&temp='+Math.random(),'','dialogHeight:800px;dialogWidth:1200px;resizable=true;status=no;');
	}

	function moreActions(object){
		var objectName = object.name;
		var urlParts = objectName.split(':');
		var namVarId = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenContractId";
		var namEFD = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenEffectiveDate";
		var namEXD = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenExpiryDate";
		var objId = document.getElementsByName(namVarId)[0];
		var objEFD = document.getElementsByName(namEFD)[0];
		var objEXD = document.getElementsByName(namEXD)[0];
		document.getElementById('benefitComponentCreateForm:contractid').value = objId.value;
		document.getElementById('benefitComponentCreateForm:effectiveDate').value = objEFD.value;
		document.getElementById('benefitComponentCreateForm:expiryDate').value = objEXD.value;


	}
	function setKeyForView() {
		var e = window.event;
		if(!e || e==undefined) {
			return false;
		}
		var button_id = e.srcElement.id;
		var var1 = button_id.split(':');
		var rowcount = var1[2];
		document.getElementById('benefitComponentCreateForm:clickedIndex').value = rowcount;
		return true;
     }
	
	function setFieldsForSort(field, order,column,object){
		var ascImageUrl = "pushup1.jpg";
		var descImageUrl = "pushdown1.jpg";
		var objectUrl = object.src;
		var urlParts = objectUrl.split('/');
		var length = urlParts.length;
		objectUrl = urlParts[length - 1];
		if(null == object){
		return false;
		}
		if(ascImageUrl == objectUrl || descImageUrl == objectUrl){
		object.disabled = true;
		return false;
		}
		document.getElementById('benefitComponentCreateForm:fieldToSort').value = field;
		document.getElementById('benefitComponentCreateForm:sortOrder').value = order;
		document.getElementById('benefitComponentCreateForm:sortColumn').value = column;
		return true;
	}
	function associatedObjectSelectedIndex(object){
		var n = object.selectedIndex;
		if(n == null){
			return false;
		}
		document.getElementById('benefitComponentCreateForm:selectedIndex').value = n;
		document.getElementById('benefitComponentCreateForm:attachmentObjectIndex').click();
		return true;
	}
	window.print();
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="contractsearchresultprint" /></form>
</HTML>
