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
			<td><jsp:include page="../navigation/top_Print.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><h:form styleClass="form" id="benefitComponentCreateForm">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<TR>

						<TD width="273" valign="top" class="leftPanel">


						<DIV class="treeDiv"><jsp:include page="../navigation/tree.jsp"></jsp:include></DIV>

						</TD>

						<TD colspan="2" valign="top" class="ContentArea">
						<TABLE>
							<TBODY>
								<tr>
									<TD><w:message/></TD>
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
									<div id="resultHeaderDiv" style="width:100%;">
										<h:panelGroup rendered="#{searchResultsBackingBean.attachedObjectValidate}">
											<h:outputText value="Attached to "></h:outputText>
											<h:selectOneMenu id="attachedObjectList" value="#{searchResultsBackingBean.currentAttachedObject}"
												rendered="#{searchResultsBackingBean.attachedObjectValidate}" onchange="associatedObjectSelectedIndex(this);">
												<f:selectItems value="#{searchResultsBackingBean.attachmentObjectTypeList}" /></h:selectOneMenu>
											<f:verbatim><p/></f:verbatim>
										</h:panelGroup>
											<div style="text-align:center;"><h:panelGrid id="panelTable"
								 	binding="#{searchResultsBackingBean.panel}"  >    
								</h:panelGrid></div>

								<!--  table starts -->
							
								<h:dataTable
										rowClasses="dataTableEvenRow,dataTableOddRow"
										styleClass="outputText" headerClass="dataTableColumnHeader"
										var="item" cellpadding="3"
										width="100%" cellspacing="1" bgcolor="#cccccc"
										value="#{searchResultsBackingBean.benefit}">
											<h:column>
				  							<f:facet name="header">
												<h:panelGroup><h:outputText value="Name"></h:outputText>
														<h:commandButton id="asc1" alt="Sort Ascending" title="Sort Ascending" 
											   			image="#{searchResultsBackingBean.columnOneImageAsc}"  
														onclick="setFieldsForSort('Benefit_Name','ASC','1',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc1"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnOneImageDesc}"  
													    onclick="setFieldsForSort('Benefit_Name','DESC','1',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
											</h:panelGroup>
											</f:facet>  
											<h:outputText id="itemName" value="#{item.name}"></h:outputText>
											<h:inputHidden id="hiddenName" value="#{item.name}"></h:inputHidden>
										</h:column>
										<h:column>
											<f:facet name="header"><h:panelGroup><h:outputText value="Description"></h:outputText>
									  		 <h:commandButton id="asc2" alt="Sort Ascending" title="Sort Ascending"  
											   			image="#{searchResultsBackingBean.columnTwoImageAsc}"  
														onclick="setFieldsForSort('Benefit_Description','ASC','2',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc2"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnTwoImageDesc}"  
													    onclick="setFieldsForSort('Benefit_Description','DESC','2',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													</h:panelGroup></f:facet>
									  		<h:outputText id="Description" value="#{item.description}"></h:outputText>
										</h:column>	
										<h:column>
												<f:facet name="header"><h:panelGroup><h:outputText value="Version"></h:outputText>
													<h:commandButton id="asc3" alt="Sort Ascending" title="Sort Ascending" 
											   			image="#{searchResultsBackingBean.columnThreeImageAsc}"  
														onclick="setFieldsForSort('Benefit_Version','ASC','3',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc3"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnThreeImageDesc}"  
													    onclick="setFieldsForSort('Benefit_Version','DESC','3',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>  
													</h:panelGroup>			
													</f:facet>
											<h:outputText id="Term" value="#{item.version}"></h:outputText>
											<h:inputHidden id="hidden_value"
												value="#{searchResultsBackingBean.index}" />
										</h:column>
										<h:column>
											<f:facet name="header"><h:panelGroup><h:outputText value="Status"></h:outputText>
									  		 <h:commandButton id="asc4" alt="Sort Ascending" title="Sort Ascending" 
											   			image="#{searchResultsBackingBean.columnFourImageAsc}"  
														onclick="setFieldsForSort('Benefit_Status','ASC','4',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													<h:commandButton id="desc4"
														alt="Sort Descending" title="Sort Descending"
											   			image="#{searchResultsBackingBean.columnFourImageDesc}"  
													    onclick="setFieldsForSort('Benefit_Status','DESC','4',this);" 
														action="#{searchResultsBackingBean.sort}"
														style="margin-left:3">
													</h:commandButton>
													</h:panelGroup></f:facet>
											<h:outputText id="Status" value="#{item.status}"></h:outputText>
										</h:column>
										
										<h:column>
											 <f:facet name="header">
												<h:panelGroup>
													<h:outputText value="Actions"></h:outputText>
											   </h:panelGroup>
											</f:facet>	
										<h:commandButton id="image"
										   alt="List entities to which this is associated to" title="List entities to which this is associated to" rendered="#{searchResultsBackingBean.authorizedForAssociation == true}"
										   image="../../images/Association.gif"  onclick="setKeyForView()" action="#{searchResultsBackingBean.viewAssociation}">
										</h:commandButton>  
										<f:verbatim> &nbsp; </f:verbatim>
										<h:commandButton id="image1"
										   alt="View Benefit" title="View Benefit"
										   image="../../images/view.gif"  onclick="setKeyForView()" rendered="#{searchResultsBackingBean.authorizedForView == true}" action="#{searchResultsBackingBean.viewBenefitObject}">
										</h:commandButton> 
										<f:verbatim> &nbsp; </f:verbatim>
										<h:commandButton id="image2"
										   alt="More Actions" title="More Actions"
										  image="../../images/more_actions.gif" action="#{searchResultsBackingBean.viewMoreBenefitObject}" onclick="setKeyForView();moreActions(this);">
										</h:commandButton>  
										</h:column>
	
								<!--  table ends -->
							</h:dataTable>
						</TD>
					</TR>
				</table>

				<!-- Space for hidden fields -->
				<h:inputHidden id="numberOfPages" value="#{searchResultsBackingBean.numberOfPages}"/>
				<h:inputHidden id="checkForView" value="#{searchResultsBackingBean.checkForView}"/>
				<h:inputHidden id="benefitName" value="#{searchResultsBackingBean.benefitName}"/>
				<h:inputHidden id="benefitKey" value="#{searchResultsBackingBean.benefitKey}"/>
				<h:inputHidden id="clickedIndex" value="#{searchResultsBackingBean.clickedIndex}"/>
				<h:inputHidden id="viewAssociation" value="#{searchResultsBackingBean.viewAssociation}"/>
				<h:inputHidden id="fieldToSort" value="#{searchResultsBackingBean.fieldToSort}"/>
				<h:inputHidden id="sortOrder" value="#{searchResultsBackingBean.benefitSortOrder}"/>
				<h:inputHidden id="sortColumn" value="#{searchResultsBackingBean.benefitSortColumn}"/>
				<h:inputHidden id="association" value="#{searchResultsBackingBean.association}"/>
				<h:inputHidden id="selectedIndex" value="#{searchResultsBackingBean.selectedIndex}"/>
				<h:commandLink id="attachmentObjectIndex"  style="display:none; visibility: hidden;"
									 action="#{searchResultsBackingBean.associatedObjectSelectedIndex}">
                              <f:verbatim /></h:commandLink>	
							
				
				<!-- End of hidden fields  -->

			</h:form>
			
					</td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/bottom.jsp"%></td>
		</tr>
	</table>
	</BODY>
</f:view>
	<script language="JavaScript">

	if(document.getElementById('benefitComponentCreateForm:checkForView').value ==1){
		benefitSerchResultsActions()
 		/* window.showModalDialog('../standardBenefit/standardBenefitView.jsp'+getUrl()+'?benefitkey='+document.getElementById('benefitComponentCreateForm:benefitKey').value + '&temp=' + Math.random(),'ViewStandardBenefit','dialogHeight:800px;dialogLeft:100px;dialogTop:100px;dialogWidth:1000px;resizable=true;status=no;'); */

	}
	</script>
<script language="JavaScript">

	
	
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

	function moreActions(object){
		var objectName = object.name;
		var urlParts = objectName.split(':');
		var namVarName = urlParts[0] + ":" + urlParts[1] + ":" + urlParts[2] + ":" + "hiddenName";
		var objName = document.getElementsByName(namVarName)[0];
		document.getElementById('benefitComponentCreateForm:benefitName').value = objName.value;

	}


</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitsearchresultsprint" /></form>
</HTML>
