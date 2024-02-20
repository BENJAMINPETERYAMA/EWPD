
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
<TITLE>Admin Methods</TITLE>
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
				<h:inputHidden id="pageLoad" binding="#{adminMethodBackingBean.valuesFromSessionForProd}"></h:inputHidden>
				<TD><jsp:include page="../navigation/top_Print.jsp"></jsp:include></TD>
			</TR>
               
			<TR>
				<TD><h:form styleClass="form" id="adminMethodForm">
                   
					<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
						<TR>

							<TD width="273" valign="top" class="leftPanel"><DIV class="treeDiv">
								<jsp:include page="../product/productTree.jsp" /></DIV>
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
											<TD width="3" align="left"><IMG
												src="../../images/tabNormalLeft.gif" alt="Tab Left Normal"
												width="3" height="21"/></TD>
											 <TD class="tabNormal"><h:commandLink id="sbAdminOption" onmousedown="javascript:navigatePageAction(this.id);"											
											action="#{adminOptionBackingBean.loadForProduct}">
											<h:outputText value="Administration Option" />
										</h:commandLink></TD> 
											<!--<TD class="tabNormal"><h:outputText value="Administration Option" /></TD>-->
											<TD width="2" align="right"><IMG
												src="../../images/tabNormalRight.gif" alt="Tab Right Normal"
												width="4" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
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
                          

							<!-- End of tab Tables -->
	<!--CARS START-->
	<FIELDSET  style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
		   <table width="100%" border="0" bordercolor="green" cellspacing="0" cellpadding="0">
				<TR><td align="right"> 
						<a id="viewAll" href="#" onclick="viewAllAdminmethod();return false;"><h:outputText	value="[View all Admin methods]" styleClass="variableLink" /></a>
					</td>
				</tr>
			<tr>
				<td valign="top" class="ContentArea" >		
						<DIV id="messageDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;font-weight:bold;text-align:center;color:#000000;background-color:#FFFFFF;"><STRONG>No Admin Methods Associated.</STRONG></DIV>
						<DIV id="benefitDefinitionTable">
						<TABLE class="smallfont" id="resultsTable" width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="red">							
 						 <TR align="left">
						  <TD class="ContentArea" align="left" valign="top" width="100%">
						   <TABLE width="100%" cellpadding="0" align="left" border="0" id="tabheader" >
							<!--<TR bgcolor="#cccccc">
							 <TD colspan="1" bgcolor="#CCCCCC"  >	
							  <DIV id="panelMainHeader" style="background-color:#FFFFFF;width:100%;height: 20 ">
							   <B>&nbsp;</B>
								</div>
								</TD>
							</TR>								
							--><TR>
 							 <TD align="left" valign="top" width="100%">
							  <DIV id="resultHeaderDiv" align="left" style="position:relative;background-color:#FFFFFF;z-index:1;">
							   <h:panelGrid id="displayTable" binding="#{adminMethodBackingBean.displayPanel}" rendered ="#{adminMethodBackingBean.renderPanel}">								
								</h:panelGrid>
 							  </DIV>
						      <DIV id="panelContent" style="position:relative;background-color:#FFFFFF;">
                               <h:panelGroup id="associatedpanel" style="height:190px;width:100%;" styleClass="dataTableColumnHeader">
 							    <h:panelGrid id="panelTable" binding="#{adminMethodBackingBean.panelForOverride}"  rendered ="#{adminMethodBackingBean.renderPanel}" rowClasses="dataTableOddRow,dataTableEvenRow">
							    </h:panelGrid>
							   </h:panelGroup> 
							  </DIV>
								<br>											
								</TD>										
							</TR>
							<TR>
								<TD>&nbsp;</TD>
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
									<DIV id="panelContent1"	style="position:relative;background-color:#FFFFFF; solid #cccccc;">
 									 <h:panelGroup id="tierPanel" style="height:190px;width:100%;" styleClass="dataTableColumnHeader">
									  <h:panelGrid id="tierPanelTable11" binding="#{adminMethodBackingBean.tierColumnHeaderPanel}" rendered ="#{adminMethodBackingBean.renderTierPanel}" ></h:panelGrid>
									  <h:panelGrid id="tierPanelTable" binding="#{adminMethodBackingBean.tierPanel}" rendered ="#{adminMethodBackingBean.renderTierPanel}" rowClasses="dataTableOddRow,dataTableEvenRow">
									  </h:panelGrid>
									 </h:panelGroup>
									</DIV>
								   </TD>
							      </TR>
						 		 </TABLE>
								</TD>
							   </TR>	
					  		  </TABLE>
					 		 </TD>
							</TR>																
							<TR>
										<TD><SPAN style="margin-left:6px;margin-right:6px;"><h:commandButton rendered = "#{adminMethodBackingBean.typeFlag}" value="Save"
										styleClass="wpdButton" id="saveButton" onmousedown="javascript:savePageAction(this.id);"
										action="#{adminMethodBackingBean.saveAdminMethodsForProduct}"
										tabindex="13">
										</h:commandButton> </SPAN></TD>
							</TR>	
							<h:inputHidden id="adminState" value="#{adminMethodBackingBean.adminMethodState}"></h:inputHidden>
							<h:inputHidden id="duplicateData"
											value="#{adminMethodBackingBean.duplicateData}"></h:inputHidden>						
						</TABLE>					
			           </TD>
			          </TR>
					 </TABLE>
  				    </fieldset>
	<!--CARS END-->
				   </td>
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
	IGNORED_FIELD1='adminMethodForm:duplicateData';
		if(document.getElementById('adminMethodForm:displayTable')!= null){	
		setColumnWidth('adminMethodForm:displayTable', '25%:35%:40%');
		if(document.getElementById('adminMethodForm:displayTierTable')!= null)
        {	
		   setColumnWidth('adminMethodForm:displayTierTable', '25%:35%:40%');
        }
		//syncTables('resultHeaderTable', 'adminMethodForm:searchResultTable'); 
		document.getElementById('messageDiv').style.visibility = "hidden";
	}else{
		document.getElementById('resultHeaderDiv').style.visibility = "hidden";
	}

function viewAllAdminmethod(){
//	window.showModalDialog('../popups/adminMethodViewAllPopup.jsp','','dialogHeight:900px;dialogWidth:1250px;'resizable:yes;status:no;');
	window.open('../popups/adminMethodViewAllPopup.jsp'+getUrl(),'','top=100, left=100, height=540, width=880, status=no, menubar=no, resizable=no, scrollbars=no, toolbar=no, location=no, directories=no');	

}

function setTitle(i){
		var e = window.event;
		var button_id = e.srcElement.id;
		//document.getElementById(button_id).title = document.getElementById(button_id).value;
        //changed the code
		var toolTipValueWithID = document.getElementById("adminMethodForm:hiddenAdminMethodDetails"+i).value;
		var toolTipValueArray = toolTipValueWithID.split("~");
		var toolTipValue = toolTipValueArray[0];
		document.getElementById(button_id).title = toolTipValue;
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
		document.getElementById(button_id).title = toolTipValue;	
}
/*CARS END*/
// Method to make the input field as ReadOnly, when page loads
var len =  document.getElementById("adminMethodForm:panelTable").rows.length;
for (var j=0;j<len-1;j++) {			
			document.getElementById("adminMethodForm:adminMethod"+j).readOnly = true;
		}

</script>
<form name="printForm"><input id="currentPrintPage" type="hidden"
	name="currentPrintPage" value="productAdminMethod" /></form>
<%@ include file="../navigation/unsavedDataFinder.html"%>
<script>
	if(document.getElementById('adminMethodForm:duplicateData').value == '')
		document.getElementById('adminMethodForm:duplicateData').value=document.ewpdDataChangedForm.ewpdOnloadData.value;
	else
		document.ewpdDataChangedForm.ewpdOnloadData.value=document.getElementById('adminMethodForm:duplicateData').value;

function getViewDetails(spsId,adminId,entityId,entityType,adminMethodSysId,hiddenAdminMethodDetails){
	var value = document.getElementById(hiddenAdminMethodDetails).value;
	var array = value.split('~');
	
	if(value != '' &&  value != ' '){
		var array = value.split('~');
		var retValueFromVersion = window.showModalDialog('../popups/adminMethodViewPopup.jsp'+getUrl()+'?&spsId='+spsId + '&adminId=' + adminId + '&entityId=' + entityId + '&entityType=' + entityType + '&adminMethodSysId=' + array[1] + '&temp=' + Math.random(),'ViewAdminMethodConfiguration','dialogHeight:400px;dialogWidth:500px;resizable=true;status=no;');
	}else{
		alert("Please select the admin method details");
	}
}
</script>
</html>