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

<TITLE>Edit Sub-Catalog</TITLE>
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
<BODY >
	<table width="100%" cellpadding="0" cellspacing="0">
		
		<TR>
			<TD>
				<jsp:include page="../navigation/top_Print.jsp"></jsp:include>
			</TD>
		</TR>
		<tr>
			<td>
				<h:form styleClass="form" id="subCatalogAssociatedForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

	                		<TD width="273" valign="top" class="leftPanel">

								
								<DIV class="treeDiv">	
<!-- Space for Tree  Data	-->					

						 		</DIV>

							</TD>


	                		<TD colspan="2" valign="top" class="ContentArea">
								<TABLE>
									<TBODY>
										<tr>
											<TD>
												<w:message value="#{subCatalogBackingBean.validationMessages}"></w:message> 
											</TD>
										</tr>		
									</TBODY>
								</TABLE>

<!-- Table containing Tabs -->
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="TabTable">
									<tr>
					          			<td width="37%">
		          							<table width="100%" border="0" cellspacing="0" cellpadding="0">
		              							<tr>
				                					<td align="left" width="2"><img src="../../images/tabNormalLeft.gif" width="3" height="21" /></td>
													<td class="tabNormal" width="50%"> <h:commandLink action="#{subCatalogBackingBean.loadEditPage}" 
													id="genId" onmousedown="javascript:navigatePageAction(this.id);">
														<h:outputText value=" General Information"/></h:commandLink> </td> 
				                					<td width="2" align="right"><img src="../../images/tabNormalRight.gif" width="3" height="21" /></td>
		              							
				                					<td width="2" align="left"><img src="../../images/activeTabLeft.gif" width="3" height="21" /></td>
													<TD class="tabActive" width="50%"><h:commandLink action="#{subCatalogBackingBean.loadItem}">
																<h:outputText id="dataDomainTable" value="Item" /> </h:commandLink></TD> 
										
				                					<TD align="right" width="2"><IMG
											src="../../images/activeTabRight.gif" width="3" height="21"></TD>
		              							</TR>
		          							</table>
		          						</td>
										<td width="63%">
										</td>
									</tr>
								</table>
<!-- End of Tab table -->
								
								<fieldset style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
									
<!--	Start of Table for actual Data	-->		
									<TABLE border="0" cellspacing="0" cellpadding="3" class="outputText" width="80%">
									<TBODY>
										<TR>
											<TD colspan=3>
												<BR>
									<TABLE width="100%" border="0">
										<TR>
											<TD width="25%"><h:outputText
												id="LobStar" value="Choose Item*" /></TD>
											<TD width="24%">
											<DIV id="itemDiv" class="selectDataDisplayDiv"></DIV>
											<TD width="57%"><h:commandButton alt="Select" id="lobButton"
												image="../../images/select.gif" style="cursor: hand"
											onclick="ewpdModalWindow_ewpd('../subCatalog/itemPopupForSubCatalog.jsp'+getUrl()+'?'+ 'date='+currentTime ,'itemDiv','subCatalogAssociatedForm:Prm',2,1); return false;"
												tabindex="1"></h:commandButton></TD>
										</TR>
										<h:inputHidden id="Prm" value="#{subCatalogBackingBean.primaryCode}"> </h:inputHidden>
									</TABLE>
									</TD></TR>
                                                                                                                   
                                                                          

										

										<TR>
											<TD width="30%">
												<h:commandButton value="Save" onclick="unsavedDataFinder(this.id);return false;" onmousedown="javascript:savePageAction(this.id);" id="associated" styleClass="wpdButton" tabindex="8" action="#{subCatalogBackingBean.createItemAssociation}"> </h:commandButton>
											</TD>
											
										</TR>
									</TBODY>
									
<!--	End of Page data	-->
														
					</table>
					<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
								<TR>
									<TD></TD>
									<TD align="right"></TD>
								</TR>
							</TABLE>
							
						<DIV id="panel2">
							<DIV id="panel2Header" class="tabTitleBar"
								style="position:relative; cursor:hand;">Associated Items</DIV>
							<DIV id="panel2Content" class="tabContentBox"
								style="position:relative;font-size:10px;"><BR>
							<table cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width="684">
									<div id="resultHeaderDiv">
								<TABLE cellspacing="1" bgcolor="#cccccc" cellpadding="3"
									id="resultHeaderTable" border="0" width="100%">
									<TBODY>
										<TR class="dataTableColumnHeader">
											<TD width="50%"><h:outputText value="Text"></h:outputText></TD>
											<TD width="50%"><h:outputText value="Delete"></h:outputText></TD>
										</TR>
									</TBODY>
								</TABLE>
								</DIV>
									</TD>
								</TR>
								<TR>
									<td width="684"><!-- Search Result Data Table -->
									<DIV id="searchResultdataTableDiv"
										style="height:252px; overflow:auto; width:100%;"><h:dataTable
										headerClass="dataTableHeader" id="searchResultTable"
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
											<h:inputHidden id="CtlgSysId" value="#{singleValue.subCatalogSysId}"></h:inputHidden>
											</h:column>
										<h:column>
											<h:selectBooleanCheckbox id="deleteChkBox" onclick="enableDisableDelete('subCatalogAssociatedForm:searchResultTable',1,0,'subCatalogAssociatedForm:deleteButton');" > </h:selectBooleanCheckbox>
										</h:column>
									
									</h:dataTable></DIV>
									</td>
								</TR>
								<TR>
								<TD width="684">
									
									<h:commandLink id="deleteItem" style="display:none; visibility: hidden;" action="#{subCatalogBackingBean.deleteItem}"> 
											<f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink> 
									<h:commandLink id="saveLink" style="display:none; visibility: hidden;" action="#{subCatalogBackingBean.createItemAssociation}"> 
                                         <f:verbatim> &nbsp;&nbsp;</f:verbatim></h:commandLink>	
									</TD>
								</TR>
								<TR>
									<TD>
										<h:commandButton id="deleteButton" styleClass="wpdButton" value="Delete"  onclick="unsavedDataFinder(this.id);return false;"></h:commandButton> 
									</TD>
						  	   </TR>
							</TABLE>
</DIV></DIV>
					
<!-- Space for hidden fields -->
<h:inputHidden id="panelData"  />
<h:inputHidden id="inputTextData"  />
<h:inputHidden id="duplicateData" value="#{subCatalogBackingBean.duplicateData}"/>
<h:inputHidden id="subCatalogId" value="#{subCatalogBackingBean.subCatalogId}"></h:inputHidden>
<h:inputHidden id="subCatalogParentId" value="#{subCatalogBackingBean.subCatalogParentId}"></h:inputHidden>
<h:inputHidden id="catalogParentId" value="#{subCatalogBackingBean.parentCatalogId}"></h:inputHidden>
<!-- End of hidden fields  -->

				
			</fieldset></TD>
		</tr>
		</table>
		<!-- WAS 6.0 migration changes - Javascript error Invalid arguement thrown, when html tags are not placed properly -->
		</h:form>	
</td></tr>
		<TR>
			<TD>
				<%@include file="../navigation/bottom.jsp"%>
			</TD>
		</TR></table></BODY>
</f:view>
<form name="printForm">
		<input id="currentPrintPage" type="hidden" name="currentPrintPage" value="associatedItemPrint" />
</form>
<!-- space for script -->

<script>
	IGNORED_FIELD1='subCatalogAssociatedForm:duplicateData';
	IGNORED_FIELD2='subCatalogAssociatedForm:panelData';	
	IGNORED_FIELD3='subCatalogAssociatedForm:inputTextData';	
	copyHiddenToDiv_ewpd('subCatalogAssociatedForm:Prm','itemDiv',2,1 );
	initialize();
	var currentDate = new Date();
	var currentTime = currentDate.getTime();

	function initialize(){
		if(document.getElementById('subCatalogAssociatedForm:searchResultTable') != null) {

			//document.getElementById('panel2Header').style.visibility = 'visible';
			//document.getElementById('panel2Content').style.visibility = 'visible';
			setColumnWidth('resultHeaderTable','50%:50%');		
			setColumnWidth('subCatalogAssociatedForm:searchResultTable','50%:50%');		
		}else{

			document.getElementById('panel2Header').style.visibility = 'hidden';
			document.getElementById('panel2Content').style.height = '1px';
			document.getElementById('panel2Content').style.visibility = 'hidden';
		}
	}

	enableDisableDelete('subCatalogAssociatedForm:searchResultTable',1,0,'subCatalogAssociatedForm:deleteButton');

function unsavedDataFinder(objectId){
	var buttonId = objectId;
	if(buttonId == 'subCatalogAssociatedForm:associated'){
		var tableObject = document.getElementById('subCatalogAssociatedForm:searchResultTable');
		if(null != tableObject){
			var panelData = getPanelData('subCatalogAssociatedForm:searchResultTable');
			if(document.getElementById('subCatalogAssociatedForm:panelData').value != panelData){
				var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
				if (retValue){
					submitLink('subCatalogAssociatedForm:saveLink');	
				}
			}else{
				submitLink('subCatalogAssociatedForm:saveLink');
			}
		}else{
			submitLink('subCatalogAssociatedForm:saveLink');
		}
	}else if (buttonId == 'subCatalogAssociatedForm:deleteButton'){
				if(confirmDelete()){
							submitLink('subCatalogAssociatedForm:deleteItem');	
				}
		}
	}

  function getPanelData(list){
      var tagNames = list.split(',');
      var dataOnScreen = "";
      var tableObject = document.getElementById(tagNames[0]);
      var rows = tableObject.rows.length;
      if(rows >0){
            var columns = tableObject.rows[0].cells.length;
            for(var i=0;i<rows;i++){
                  for(var j=0;j<columns;j++){
                        if(null != tableObject.rows[i].cells[j].children[0]){
                              if(tableObject.rows[i].cells[j].children[0].type == 'checkbox'){
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].checked);
                              }else{
                                    dataOnScreen += (tableObject.rows[i].cells[j].children[0].innerHTML);       
                              }
                        }
                  }
            }
			
            return dataOnScreen;
      }else{
            return dataOnScreen;
      }
	}

	function confirmDelete() {
	// For Deletion
		var message = "Are you sure you want to detach the selected Item?"		
		if(!window.confirm(message))
			return false;
		tableObj = getObj('subCatalogAssociatedForm:searchResultTable');
		if(tableObj == null || tableObj.rows == 0) 
			return false;
		selectedCatalogParentId = getObj('subCatalogAssociatedForm:catalogParentId');
		selectedCatalogParentId.value = '';
		subCatalogId = getObj('subCatalogAssociatedForm:subCatalogId');
		subCatalogId.value = tableObj.rows[0].cells[0].children[2].value;
		var ItemCount = 0;
		for(var i=0; i<tableObj.rows.length; i++) {
			var cur_row = tableObj.rows[i];
			
			if(cur_row.cells[1].children[0].checked) {
				if(ItemCount != 0) {
					selectedCatalogParentId.value += '~';
				 }
				selectedCatalogParentId.value += cur_row.cells[0].children[3].value;
				ItemCount ++;
			}
		}
		if(ItemCount > 0)
			return true;
		return false;
	}

	checkForOnLoadData();
   function checkForOnLoadData(){

	var tableObject = document.getElementById('subCatalogAssociatedForm:searchResultTable');
	if(null != tableObject){
		if(tableObject.rows.length >0){
			var onLoadPanelData = getPanelData('subCatalogAssociatedForm:searchResultTable');
			document.getElementById('subCatalogAssociatedForm:panelData').value = onLoadPanelData;
		}
	}
	if(null != document.getElementById('subCatalogAssociatedForm:Prm')){
		var inputTexts = checkForTextData();
		document.getElementById('subCatalogAssociatedForm:inputTextData').value = inputTexts;
	}

   }

  function checkForTextData(){
	var inputTexts = document.getElementById('subCatalogAssociatedForm:Prm').value; 
	return inputTexts;

	}

	


</script>
<%@ include file="../navigation/unsavedDataFinder.html"%>

<script>
	if(document.getElementById('subCatalogAssociatedForm:duplicateData').value == '')
		document.getElementById('subCatalogAssociatedForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('subCatalogAssociatedForm:duplicateData').value;
</script>
</HTML>












