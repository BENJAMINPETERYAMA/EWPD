<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<HTML>
<BASE target="_self" />
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
<TITLE>Print Admin Methods</TITLE>
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
<h:form styleClass="form" id="adminMethodForm"> 
   <TABLE width="100%">
	<TR>
	 <TD>&nbsp;</TD>
	</TR>
	<TR>
 	 <TD>
	  <FIELDSET style="margin-left:2px;margin-right:-0px;padding-bottom:1px;padding-top:1px;width:110%">
	   <h:outputText id="breadcrumb" value="#{productAdminOptionBackingBean.printBreadCrumbText}">
	   </h:outputText>
	  </FIELDSET>
     </TD>
	</TR>
	<TR>
	 <TD>&nbsp;</TD>
	</TR>
   </TABLE>	
     <h:inputHidden id="loadPageforPrint" value="#{adminMethodBackingBean.loadProductPageForPrint}"></h:inputHidden>						
   <h:inputHidden value="#{adminMethodBackingBean.submitFlag}" id="submitFlag"/>
   <h:commandLink id="reloadForPrint" style="hidden" action="#{adminMethodBackingBean.reloadForPrint}"> </h:commandLink>
					

	<br>
<!--CARS START-->
	<FIELDSET style="margin-left:6px;margin-right:-6px;padding-bottom:1px;padding-top:6px;width:100%">
	     <table width="100%" id="mainTB" border="0" bordercolor="green" cellspacing="0" cellpadding="0" style="display:none;">
			<tr>
				<td valign="top" class="ContentArea" >		
						<DIV id="messageDiv" style="font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;font-weight:bold;text-align:center;color:#000000;background-color:#FFFFFF;"><STRONG>No Admin Methods Associated.</STRONG></DIV>
						<DIV id="benefitDefinitionTable">
						<TABLE class="smallfont" id="resultsTable" width="100%" cellpadding="0" cellspacing="0" border="0" bordercolor="red">							
 						 <TR align="left">
						  <TD class="ContentArea" align="left" valign="top" width="100%">
						   <TABLE width="100%" cellpadding="0" align="left" border="0" id="tabheader" >
							<TR>
 							 <TD align="left" valign="top" width="100%">
							  <DIV id="resultHeaderDiv" align="left" style="position:relative;background-color:#FFFFFF;z-index:1;">
							   <h:panelGroup id="associatedpanel" style="height:190px;width:100%;" styleClass="dataTableColumnHeader">
							   <h:panelGrid id="displayTable" binding="#{adminMethodBackingBean.displayPanel}" rendered ="#{adminMethodBackingBean.renderPanel}">								
								</h:panelGrid>
 							  </DIV>
						      <DIV id="panelContent" style="position:relative;background-color:#FFFFFF;">                               
 							    <h:panelGrid id="panelTable" binding="#{adminMethodBackingBean.panelForOverrideForPrint}"  rendered ="#{adminMethodBackingBean.renderPanel}">
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
 									 <h:panelGroup id="tierPanel" style="height:190px;width:100%;" styleClass="dataTableColumnHeader">
									 <h:panelGrid id="displayTable4" binding="#{adminMethodBackingBean.tierDisplayPanel}" rendered ="#{adminMethodBackingBean.renderTierPanel}">								
									 </h:panelGrid>
									</DIV>
									<DIV id="panelContent1"	style="position:relative;background-color:#FFFFFF; solid #cccccc;">
									  <h:panelGrid id="tierPanelTable11" binding="#{adminMethodBackingBean.tierColumnHeaderPanel}" rendered ="#{adminMethodBackingBean.renderTierPanel}" ></h:panelGrid>
									  <h:panelGrid id="tierPanelTable" binding="#{adminMethodBackingBean.tierPanelForPrint}" rendered ="#{adminMethodBackingBean.renderTierPanel}" >
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
						</TABLE>					
			           </TD>
			          </TR>
					 </TABLE>
  				    </fieldset>
<!--CARS END-->
		</h:form>
	</BODY>
</f:view>
<script language="javascript">

reloadPage();
	function reloadPage(){
       var submitFlag = document.getElementById('adminMethodForm:submitFlag').value;
		if(submitFlag == 'false'){
			document.getElementById('adminMethodForm:reloadForPrint').click();
		}else{ 
			document.getElementById('mainTB').style.display = "block";
			}
	}
	    if(null !=  document.getElementById('adminMethodForm:displayTable'))
        {	           
		   document.getElementById('messageDiv').style.visibility = "hidden";
	    }
        else
        {
		    document.getElementById('resultHeaderDiv').style.visibility = "visible";
	    }		
		var divObj = document.getElementById('noAdminProcess');
		var divHeaderObj = document.getElementById('resultHeaderDiv');
		var tableObj = document.getElementById('adminMethodForm:adminProcess:0:srsId');
		if (tableObj != null) 
		{
			divObj.style.visibility = 'hidden';
			divObj.style.height = "0px";
			divObj.style.position = 'absolute';
		}
		else
		{
			divHeaderObj.style.visibility = 'hidden';
			divHeaderObj.style.height = "0px";
			divHeaderObj.style.position = 'absolute';

		}
		window.print();
</script>
</html>


