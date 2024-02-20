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
<BASE target="_self" />
<TITLE>Administration Process</TITLE>
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
			<TR><td><h:inputHidden id="setBreadCrumb"
													value="#{adminMethodBackingBean.adminMethodValidationBreadCrumb}"></h:inputHidden></td></TR>
			<TR>
				<TD><jsp:include page="../navigation/top_breadcrumb.jsp"></jsp:include></TD>
			</TR>
			<TR>
				<TD><h:form styleClass="form" id="adminMethodForm">
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel">
								<div class="treeDiv">
									<jsp:include
										page="../adminMethods/adminMethodContractTree.jsp"></jsp:include>	
								</div>	
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
							<TABLE width="200" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<TD width="100%">
									<TABLE width="200" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" width="3" height="21"></TD>
											<TD width="186" class="tabActive"><h:outputText
												value="Administration Process" /></TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
									<TD width="100%"></TD>
								</TR>
							</TABLE>
<!--CARS START-->
							
   <FIELDSET	style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">	
   			<h:inputHidden id="loadTree" binding="#{adminmethodTreeBackingBean.loadTree}" />	
			<h:inputHidden id="hasValErrors" value="#{adminMethodBackingBean.hasValidationErrors}"></h:inputHidden>					
		   <table width="100%" border="0" bordercolor="green" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" class="ContentArea" >		
						<DIV id="benefitDefinitionTable">
						<TABLE class="smallfont" id="resultsTable" width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="red">							
 						 <TR align="left">
						  <TD class="ContentArea" align="left" valign="top" width="100%">
						   <TABLE width="100%" cellpadding="0" align="left" border="0" id="tabheader" >														
							<TR>
 							 <TD align="left" valign="top" width="100%">
							  <DIV style="position:relative;top:-2px;left:0px" align="left">							
						      <DIV id="panelContent" style="position:relative;background-color:#FFFFFF;">
                               <h:panelGroup id="associatedpanel" style="width:100%;" styleClass="dataTableColumnHeader">
								  <DIV id="resultHeaderDiv" align="left" style="position:relative;background-color:#FFFFFF;z-index:1;">
								   <h:panelGrid id="displayTable" binding="#{adminMethodBackingBean.displayPanel}" rendered ="#{adminMethodBackingBean.renderPanel}">								
									</h:panelGrid>
	 							  </DIV>								
 							    <h:panelGrid id="panelTable" binding="#{adminMethodBackingBean.panelForContractValidation}" rowClasses="dataTableEvenRow,dataTableOddRow">
							    </h:panelGrid>
							   </h:panelGroup> 
							  </DIV>
								<br>											
							  </DIV>
								</TD>										
							</TR>																
							<TR align="left">
							  <TD align="left" valign="top" width="100%">
								<TABLE cellpadding="0" align="left" border="0" id="tabheaderTier" class="smallfont" width="100%">							 	 
								 <TR>
								   <TD valign="top">
									<DIV id="resultHeaderDiv1" 	style="position:relative;background-color:#FFFFFF;">
									 <h:panelGrid id="displayTable4" binding="#{adminMethodBackingBean.tierDisplayPanel}" rendered ="#{adminMethodBackingBean.renderTierPanel}">								
									 </h:panelGrid>
									</DIV>
                 					<DIV id="resultHeaderDiv1" 	style="position:relative;background-color:#FFFFFF;">
									</DIV>
									<DIV id="panelContent1"	style="position:relative;background-color:#FFFFFF; solid #cccccc;">
 									 <h:panelGroup id="tierPanel" style="height:190px;width:100%;" styleClass="dataTableColumnHeader">
									  <h:panelGrid id="tierPanelTable11" binding="#{adminMethodBackingBean.tierColumnHeaderPanelForCheckin}" rendered ="#{adminMethodBackingBean.renderTierPanel}" ></h:panelGrid>
									  <h:panelGrid id="tierPanelTable" binding="#{adminMethodBackingBean.panelForTierValidation}" >
									  </h:panelGrid>
									 </h:panelGroup>
									</DIV>
								   </TD>
							      </TR>
						 		 </TABLE>
								</TD>
							   </TR>
							   <TR>
							    <TD>
							     <SPAN style="margin-left:6px;margin-right:6px;">
							      <h:commandButton value="Save" styleClass="wpdButton" id="saveButton"	onmousedown="javascript:savePageAction(this.id);" action="#{adminMethodBackingBean.saveAdminMethodsForValidation}"	tabindex="13">
								  </h:commandButton>								  							      
							     </SPAN>
							    </TD>
						       </TR>	
					  		  </TABLE>
					 		 </TD>
							</TR>																						   																		
						</TABLE>					
			           </TD>
			          </TR>
					 </TABLE>
  				    </fieldset>
<!--CARS END-->
				   </TD>
			      </tr>
				 </table>
				</h:form>
			   </TD>
			  </TR>
			  <TR>
				<TD><%@include file="../navigation/bottom.jsp"%></TD>
			  </TR>
		     </TABLE>
	        </hx:scriptCollector>
	       </BODY>
          </f:view>
<script language="javascript">

if(document.getElementById('adminMethodForm:tree2:hasValErrors').value == 'false'){
	window.close();
}
		if(document.getElementById('adminMethodForm:adminProcess')!= null){		
		setColumnWidth('adminMethodForm:adminProcess', '25%:35%:40%');
		// syncTables('resultHeaderTable', 'adminOptionsForm:searchResultTable'); 
	}
	function setTitle(i){
		var e = window.event;
		var button_id = e.srcElement.id;
		//document.getElementById(button_id).title = document.getElementById(button_id).value;
        //changed the code
		var toolTipValueWithID = document.getElementById("adminMethodForm:hiddenAdminMethodDetails"+i).value;
		var toolTipValueArray = toolTipValueWithID.split("~");
		var toolTipValue = toolTipValueArray[0];
		if(toolTipValue != ' '){
			document.getElementById(button_id).title = toolTipValue;
		}
	}
	/*CARS START*/
	function setTierTitle(i){	
	
			var e = window.event; 
			var button_id = e.srcElement.id;
			//document.getElementById(button_id).title = document.getElementById(button_id).value;
	        //changed the code
			var toolTipValueWithID = document.getElementById("adminMethodForm:hiddenTieredAdminMethodDetails_"+i).value;
			var toolTipValueArray = toolTipValueWithID.split("~");
			var toolTipValue = toolTipValueArray[0];
			if(toolTipValue != ' '){
				document.getElementById(button_id).title = toolTipValue;	
			}
	}
	/*CARS END*/
	function getViewDetails(spsId,adminId,entityId,entityType,adminMethodSysId,hiddenAdminMethodDetails){
		var value = document.getElementById(hiddenAdminMethodDetails).value;
		var array = value.split('~');
		if(value != '' &&  value != ' ' && array[1] != 0){
			
			window.showModalDialog('../popups/adminMethodViewPopup.jsp'+getUrl()+'?&spsId='+spsId + '&adminId=' + adminId + '&entityId=' + entityId + '&entityType=' + entityType + '&adminMethodSysId=' + array[1] + '&validation=validation'  + '&temp=' + Math.random(),'ViewAdminMethodConfiguration','dialogHeight:400px;dialogWidth:500px;resizable=true;status=no;');
		}else{
			alert("Please select the admin method details");
		}
	}
/*CARS START */
function getViewDetailsForTiers(spsId,adminId,entityId,entityType,adminMethodSysId,hiddenAdminMethodDetails){
	var value = document.getElementById(hiddenAdminMethodDetails).value;
	var array = value.split('~');
	
	if(value != '' &&  value != ' '){
		var array = value.split('~');
		var retValueFromVersion = window.showModalDialog('../popups/adminMethodViewPopup.jsp'+getUrl()+'?&spsId='+spsId + '&adminId=' + adminId + '&entityId=' + entityId + '&entityType=' + entityType + '&adminMethodSysId=' + array[0] + '&temp=' + Math.random(),'ViewAdminMethodConfiguration','dialogHeight:400px;dialogWidth:500px;resizable=true;status=no;');
	}else{
		alert("Please select the admin method details");
	}
}
/*CARS END */
</script>

<%@ include file="../navigation/unsavedDataFinder.html"%>
</html>



