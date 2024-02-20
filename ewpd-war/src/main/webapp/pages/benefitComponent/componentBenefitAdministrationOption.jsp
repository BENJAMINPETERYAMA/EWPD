<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
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
<base target=_self>
<TITLE>Admin Options</TITLE>
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
	<hx:scriptCollector id="scriptCollector1">
		<TABLE width="100%" cellpadding="0" cellspacing="0">
			<TR>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminOptionForm">

					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
							<DIV class="treeDiv"><jsp:include
								page="../benefitComponent/benefitComponentTree.jsp"></jsp:include></DIV>

							</TD>


							<TD colspan="2" valign="top" class="ContentArea">
							<TABLE>
								<TBODY>
									<TR>
										<TD><w:message></w:message></TD>
									</TR>
								</TBODY>
							</TABLE>

							<!-- Table containing Tabs -->
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="200">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Administration Option" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabNormal"><h:commandLink
												id="adminProcess"
												onmousedown="javascript:navigatePageAction(this.id);"
												action="#{adminMethodBackingBean.loadForBenefitComponent}">
												<h:outputText value="Administration Process" />
											</h:commandLink></TD>
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>

									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
							<!-- End of Tab table -->

							<FIELDSET
								style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%;height:550">

							<div id="adminDivHidden" align="center"><!-- <br>-->
							<!-- <br>-->
							<b><h:outputText value="No Benefit Administration Option Available."></h:outputText></b>
							</div>
							<div id="LabelHeaderDiv"
								style="background-color:#cccccc;height: 20px">

							<B>&nbsp;<h:outputText id="adminOptions" value="Admin Options"></h:outputText>
							</B></div>
							<div id="adminDiv" style="width:100%"><h:panelGrid id="headerPanelTable" columns="4"
								width="100%" styleClass="outputText" cellpadding="1"
								cellspacing="1" bgcolor="#cccccc"
								binding="#{adminOptionBackingBean.headerPanelForBenefitComp}"
								rowClasses="dataTableColumnHeader">
							</h:panelGrid></div>
							<DIV id="searchResultdataTableDiv">
							<TABLE width="100%" cellspacing="0" cellpadding="0">

								<TR>
									<TD>
									<div id="paneltable" style="height:275px; overflow:auto;"><h:panelGrid id="panelTable" 
										width="100%" styleClass="outputText" cellpadding="1"
										cellspacing="1" bgcolor="#cccccc"
										binding="#{adminOptionBackingBean.panelForBenefitComp}">
									</h:panelGrid></div>
									</TD>
								</TR>
							</TABLE>
							<TABLE>
								<tr>
									<td>


									<div id="saveButtonDiv"
										style="position:relative;overflow:auto;width:100%"><h:commandButton
										value="Save" styleClass="wpdButton"
										onmousedown="javascript:savePageAction(this.id);"
										rendered="#{adminOptionBackingBean.saveButtonRender}"
										id="save" onclick="saveHidden();return false;"></h:commandButton></div>
									</td>
								</tr>
							</TABLE>
							<h:inputHidden id="panelData"  />
							<h:commandLink id="saveButton"
								action="#{adminOptionBackingBean.hideAdminOption}"
								style="hidden">
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink> <h:commandLink id="getHidden"
								action="#{adminOptionBackingBean.loadForBenefitComponent}"
								style="hidden">
								<f:verbatim> &nbsp;&nbsp;</f:verbatim>
							</h:commandLink> <h:inputHidden
								value="#{adminOptionBackingBean.checkBoxValue}"
								id="saveButtonHidden"></h:inputHidden> <h:inputHidden
								value="#{adminOptionBackingBean.checkBoxValues}"
								id="checkBoxValues"></h:inputHidden> <h:inputHidden
								value="#{adminOptionBackingBean.saveButtonRender}"
								id="saveButtonRender"></h:inputHidden></DIV>
							<!--	End of Page data	--></FIELDSET>
							</TD>
						</TR>
					</TABLE>
						
					<h:inputHidden value="#{adminOptionBackingBean.adminOptionExists}"
								id="adminOptionExists"></h:inputHidden>	
					<h:inputHidden id="duplicateData"
								value="#{adminOptionBackingBean.duplicateData}"></h:inputHidden>
					<!-- Space for hidden fields -->
					<!-- End of hidden fields  -->	

				</h:form></TD>
			</TR>
			<TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			</TR>
		</TABLE>
	</hx:scriptCollector>
	</BODY>
</f:view>
<script language="JavaScript">
 IGNORED_FIELD1='adminOptionForm:duplicateData';
//if(document.getElementById('adminOptionForm:panelTable') != null){   
 //       if(document.getElementById('adminOptionForm:panelTable').rows.length != 0){
 //          document.getElementById('adminDiv').style.visibility = 'visible';
 //          document.getElementById('paneltable').style.visibility = 'visible';
  //         document.getElementById('saveButtonDiv').style.visibility = 'visible';
 //          document.getElementById('LabelHeaderDiv').style.visibility = 'visible';
 //          document.getElementById('adminDivHidden').style.visibility = 'hidden';
//	    }
 // 		else{
 //          document.getElementById('adminDiv').style.visibility = 'hidden';
 //          document.getElementById('paneltable').style.visibility = 'hidden';
 //          document.getElementById('saveButtonDiv').style.visibility = 'hidden';
  //         document.getElementById('LabelHeaderDiv').style.visibility = 'hidden';
  //         document.getElementById('adminDivHidden').style.visibility = 'visible';
  //////  	}
//}


if(document.getElementById('adminOptionForm:adminOptionExists').value == "true"){   
		 // 	alert('adminOptions present');
			
           document.getElementById('adminDiv').style.visibility = 'visible';
           document.getElementById('paneltable').style.visibility = 'visible';
           document.getElementById('saveButtonDiv').style.visibility = 'visible';
           document.getElementById('LabelHeaderDiv').style.visibility = 'visible';
           document.getElementById('adminDivHidden').style.visibility = 'hidden';
	    }
  		else{
			//  alert('show message - no admin options');	      
           document.getElementById('adminDiv').style.visibility = 'hidden';
           document.getElementById('paneltable').style.visibility = 'hidden';
           document.getElementById('saveButtonDiv').style.visibility = 'hidden';
           document.getElementById('LabelHeaderDiv').style.visibility = 'hidden';
           document.getElementById('adminDivHidden').style.visibility = 'visible';
    	}



function saveHidden(){
		submitLink('adminOptionForm:saveButton');
	}

	function getHidden(){
		if(document.getElementById('adminOptionForm:checkboxHeaderId').checked == true)
		{
			document.getElementById('adminOptionForm:saveButtonHidden').value = "true"; 
			document.getElementById('adminOptionForm:getHidden').click();
		}else{
			document.getElementById('adminOptionForm:saveButtonHidden').value = "false";
			document.getElementById('adminOptionForm:getHidden').click();
			
		}
	}
/*
var select =  document.getElementById('benefitAdmnForm:showHidden')
	if(select.checked){
		submitLink('benefitAdmnForm:selectedShowHiddenLink');
	}else{
		submitLink('benefitAdmnForm:unselectedShowHiddenLink');
	}
}*/



//		var noOfRows = document.getElementById('adminOptionForm:searchResultTable').rows.length;
//		for(i=0; i<=noOfRows; i++){
//		document.getElementById('adminOptionForm:searchResultTable:'+ i + ':chekbox').checked = true;
//		}
//	}
			setColumnWidth('adminOptionForm:panelTable','40%:20%:20%:20%');
	 		setColumnWidth('adminOptionForm:headerPanelTable', '40%:20%:20%:20%')
if(document.getElementById('adminOptionForm:panelTable').rows.length >11){
 document.getElementById('adminOptionForm:headerPanelTable').width = document.getElementById('adminOptionForm:panelTable').offsetWidth; 
}

checkForPanel();
function checkForPanel(){
	var tableObject = document.getElementById('adminOptionForm:panelTable');
	if(tableObject.rows.length >0){
		var onLoadPanelData = getPanelData('adminOptionForm:panelTable');
		document.getElementById('adminOptionForm:panelData').value = onLoadPanelData;
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
function unsavedDataFinder(){
	
	var panelData = getPanelData('adminOptionForm:panelTable');
	if(document.getElementById('adminOptionForm:panelData').value != panelData){
		var retValue = confirm("All unsaved changes will be lost. Click OK to continue, or Cancel to stay on the current page.");
		if (retValue){
			getHidden();
		}
		else{
			var select =  document.getElementById('adminOptionForm:checkboxHeaderId');
			if(select.checked){
				document.getElementById('adminOptionForm:checkboxHeaderId').checked=false;
			}else{
				document.getElementById('adminOptionForm:checkboxHeaderId').checked=true;
			}
		}
	}
	else{
		getHidden();
	}
}
</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="benefitComponentAdminOption" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('adminOptionForm:duplicateData').value == '')
		document.getElementById('adminOptionForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('adminOptionForm:duplicateData').value;
</script>
</html>
