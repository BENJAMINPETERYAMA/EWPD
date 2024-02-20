<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<LINK rel="stylesheet" type="text/css" href="../../css/wpd.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/global.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/menu.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../css/calendar.css"	title="Style">
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css" title="Style">

<TITLE>AsociatedItems View</TITLE>
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
	<h:form styleClass="form" id="associatedItemsViewForm">
	
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<jsp:include page="../navigation/top_view.jsp"></jsp:include>
			</td>
		</tr>
		
		<tr>
			<td>				
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">
							&nbsp;
								
								
<!-- Space for Tree  Data	-->			
						 		

							</TD>

	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<!-- Insert WPD Message Tag --> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="200">
		          							<table width="200" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td width="2" align="left"><img src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
													<td width="186" class="tabNormal"> 
														<h:commandLink action ="#{subCatalogBackingBean.loadGeneralInformationView}"><h:outputText value=" General Information"/></h:commandLink> 
														 </td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" width="2" height="21" /></td>
		              							</tr>
		          							</table>
		          						</td>
										<td width="200">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="2" align="left"><img	src="../../images/activeTabLeft.gif" alt="Tab Left Active" width="3" height="21" /></td>
													<td width="186" class="tabActive"> 
														<h:commandLink ><h:outputText value="Item"/> </h:commandLink> 
													</td>
													<td width="2" align="right"><img src="../../images/activeTabRight.gif" alt="Tab Right Active" width="2" height="21" /></td>
												</tr>
											</table>
										</td>
										<td width="100%">
										</td>
									</tr>
								</table>	
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<BR/>

							 
							<TABLE width="100%" cellpadding="0" cellspacing="0" >
									<tr>
										<td>
										<h:outputText value="No Items Available." 
										rendered="#{subCatalogBackingBean.searchResultList == null}" 
										styleClass="dataTableColumnHeader"/>
										<div id="itemAssociationformDiv">
											<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Associated Items</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" width="100%" border="0">
								<tr>
									<td>
									<div id="resultHeaderDiv">
									<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
										id="resultHeaderTable" border="0" width="95%">
										<TBODY>
											<TR class="dataTableColumnHeader">
												<TD align="center" ><h:outputText
													value="Text"></h:outputText></TD>
												
											</TR>
										</TBODY>
									</TABLE>
									</DIV>
									</TD>
								</TR>
								<TR>
									<td><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:430; overflow:auto; width:95%;"><h:dataTable
										headerClass="dataTableHeader" id="associatedItemsTable"
										var="singleValue" cellpadding="3" cellspacing="1"
										bgcolor="#cccccc"
										rendered="#{subCatalogBackingBean.searchResultList != null}"
										value="#{subCatalogBackingBean.searchResultList}"
										rowClasses="dataTableEvenRow,dataTableOddRow" border="0"
										width="100%">
										<h:column>
											<h:outputText id="itName" value="#{singleValue.description}"></h:outputText>
											<h:inputHidden id="primaryCd" value= "#{singleValue.primaryCode}"/>
											<h:inputHidden id="catalogId" value= "#{singleValue.subCatalogId}"/>
										</h:column>
									
									</h:dataTable></DIV>
									</td>
								</TR>
								<TR>
								<TD>
									 
									</TD>
								</TR>
							</TABLE>
</DIV></DIV>
											</TD>
											</TR>			
															
									</TABLE>
<!--	End of Page data	-->		
								</fieldset>	

								<BR>
								<BR>


	
							</TD>
						</TR>
					</table>
					
<!-- Space for hidden fields -->
					<h:inputHidden id="hidden1" value="value1"></h:inputHidden>
					<h:commandLink id="hiddenLnk1" style="display:none; visibility: hidden;" > <f:verbatim/></h:commandLink>
<!-- End of hidden fields  -->
</td></table>
				</h:form>
			
		
			<td>
				<%@ include file ="../navigation/bottom_view.jsp" %>
			</td>
		
	
</BODY>
</f:view>

<script>
	initialize();
		function initialize(){
			if(document.getElementById('associatedItemsViewForm:associatedItemsTable') == null) 
				
				document.getElementById("itemAssociationformDiv").style.visibility = 'hidden';
			}

</script>

<form name="printForm">
		<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="associatedItemPrint" />
</form>
</HTML>

