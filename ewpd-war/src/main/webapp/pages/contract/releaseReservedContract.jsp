<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="/wpd.tld" prefix="w"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
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
<TITLE>Reserved Contract Create</TITLE>
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
<script language="JavaScript" src="../../js/CalendarPopup.js"></script>
<script language="JavaScript" src="../../js/PopupWindow.js"></script>
<script language="JavaScript" src="../../js/date.js"></script>
<script language="JavaScript" src="../../js/AnchorPosition.js"></script>
<script language="JavaScript">
	 var cal1 = new CalendarPopup();
	 cal1.showYearNavigation();

</script>
<LINK rel="stylesheet" type="text/css" href="../../theme/stylesheet.css"
	title="Style">
</HEAD>
<f:view>
	<BODY onkeypress="return submitOnEnterKey('reservedContractForm:releaseButton');">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<% javax.servlet.http.HttpServletRequest httpReq = (javax.servlet.http.HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
					httpReq.setAttribute("breadCrumbText","Administration >> Contract Id >> Release ");
				%>	
				<jsp:include page="../navigation/top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
				<h:form styleClass="form" id="reservedContractForm">
					<TBODY>
						<TR>
							<TD width="230" valign="top" class="leftPanel">
							<DIV class="treeDiv" style="height:100%;width:230"></DIV>

							</TD>
							<TD colspan="2" valign="top" class="ContentArea"><!--<p class="pageTitle">General Information </p>-->
							<TABLE>
								<TR>
									<TD><w:message
										value="#{releaseReservedContractsBackingBean.validationMessages}"></w:message>
									</TD>
								</TR>
							</TABLE>
							<TABLE width="73%" border="0" cellpadding="0" cellspacing="0"
								id="TabTable">
								<TR>
									<td width="148">
									<TABLE width="148" border="0" cellspacing="0" cellpadding="0">
										<TR>
											<TD width="2" align="left"><IMG
												src="../../images/activeTabLeft.gif" alt="Tab Left Normal"
												width="3" height="21"></TD>
											<TD class="tabActive">Basic Information</TD>
											<TD width="2" align="right"><IMG
												src="../../images/activeTabRight.gif" alt="Tab Right Normal"
												width="2" height="21"></TD>
										</TR>
									</TABLE>
									</TD>
								</TR>
							</TABLE>
							<fieldset
								style="margin-left:6px;margin-right:-6px;padding-bottom:6px;padding-top:6px;width:100%">
							<table width="100%" border="0" cellspacing="2" cellpadding="2">
								<tr>
									<TD colspan="3">
									<FIELDSET style="margin-left:6px;margin-right:-3px;padding-bottom:1px;padding-top:6px;width:95%">
										<TABLE  width="95%" border="0" cellspacing="0" cellpadding="3">
								  		 <tr>
										 <td>
											<h:panelGroup>
												<t:selectOneRadio id="buttons" layout="spread" onclick="disableInput();return true;"
														value="#{releaseReservedContractsBackingBean.option}" >
														<f:selectItem itemLabel="" itemValue="0" />
														<f:selectItem itemLabel="" itemValue="1" />
												</t:selectOneRadio>
												<h:panelGrid columns="1">
														<t:radio for="buttons" index="0" />
														<t:radio for="buttons" index="1" />
													</h:panelGrid>
											</h:panelGroup>
										</td>
										<td>
									<TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
												<tr>
													<td width="30%"><h:outputText id="fromContractId" value="Release Ids From Contract Id" styleClass="#{releaseReservedContractsBackingBean.startIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
													<td width="20%"><h:inputText id="startContractIdInput" styleClass="formInputFieldForContractId" tabindex="0" maxlength="4"
														value="#{releaseReservedContractsBackingBean.startContractId}" tabindex="1"/>
													</td>
													<td width="30%"><h:outputText id="endContractId" value="Through Contract Id" styleClass="#{releaseReservedContractsBackingBean.endIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
													<td width="20%"><h:inputText id="endContractIdInput" styleClass="formInputFieldForContractId" tabindex="1" maxlength="4"
														 value="#{releaseReservedContractsBackingBean.endContractId}" tabindex="2"/></td>
													
												
												</tr>
												<tr>
												
													<td width="30%"><h:outputText id="fromContractId1" value="Release Expired Contract Id(s)" styleClass="#{releaseReservedContractsBackingBean.expiredIdInvalid ? 'mandatoryError': 'mandatoryNormal'}"></h:outputText></td>
													<TD width="20%">
														<DIV id="expiredContractIdDiv" class="formInputFieldForContractIds"></DIV>
													</TD>
													<td width="30%">
														<h:commandButton
															alt="expired contract ids" id="expiredContractIdButton"
															image="../../images/select.gif" 
															onclick="ewpdModalWindow_ewpd('../popups/releaseExpiredContractsPopup.jsp'+getUrl()+'?+ temp=' + Math.random(),'expiredContractIdDiv','reservedContractForm:expiredContractIdTxtHidden',1,1); return false;"     
															tabindex="3">
														</h:commandButton> 
															<h:inputHidden id="expiredContractIdTxtHidden"
																value="#{releaseReservedContractsBackingBean.releaseExpiredDiv}"></h:inputHidden>
													</td>
													<td width="20%"></td>
												</tr>
											</TABLE>
									 	 </td>
							  			 </tr>
										</TABLE>
									</FIELDSET>
								</TD>
							</tr>
							<tr>
									<TD colspan=3>
										<TABLE border="0" cellspacing="5" cellpadding="1">
											<TR>
										<TD valign="top" align ="left" width="30%"><h:commandButton
											value="Release" styleClass="wpdbutton" id="releaseButton" tabindex="4"
											onclick = "releaseContractDetails();return false;"
											></h:commandButton>
										</TD>
									</TR>
									<tr>
									</tr>
								   </TABLE>
								</TD>
					 		</tr>
					</table>
			</FIELDSET>
			</TD>
		   </TR>
	     </TBODY>
			<h:commandLink id="releaseReservedLink" style="hidden" action="#{releaseReservedContractsBackingBean.releaseContractDetails}">
			</h:commandLink>
			<h:inputHidden id="idList" 	value="#{releaseReservedContractsBackingBean.idList}"></h:inputHidden>
			<h:inputHidden id="valid" 	value="#{releaseReservedContractsBackingBean.valid}"></h:inputHidden>
			<h:commandLink id="retrieveContractLink" style="hidden" action="#{releaseReservedContractsBackingBean.retrieveContractDetails}">
			</h:commandLink>
		</h:form>
		</TABLE>   
	   </td>
		</tr>
		<tr>
			<td><%@ include file="../navigation/pageFooter.jsp"%></td>
		</tr>
	</table>
	</hx:scriptCollector>
	</BODY>
	<script language="javascript">
	
	disableInput();	
	copyHiddenToDiv('reservedContractForm:expiredContractIdTxtHidden','expiredContractIdDiv');	
	
			var valid=document.getElementById('reservedContractForm:valid').value;
			if(valid=="valid"){
				releaseContractAvailable();
					/* var ret= window.showModalDialog('../contractpopups/releaseReservedContractsPopUp.jsp'+getUrl()+ '?temp=' + Math.random(),'','dialogHeight:650px;dialogWidth:800px;resizable=false;status=no;');  
					if(ret=='confirm'){
						document.getElementById('reservedContractForm:releaseReservedLink').click();
					} */
			}
	
	function releaseContractDetails(){

		var options = document.getElementsByName('reservedContractForm:buttons');
		if(options[0].checked){
			document.getElementById('reservedContractForm:retrieveContractLink').click();
		} 
		else if(options[1].checked){
			document.getElementById('reservedContractForm:releaseReservedLink').click();
		}
   		
	}

	function disableInput(){
		var options = document.getElementsByName('reservedContractForm:buttons');
		var popUp=document.getElementById('reservedContractForm:expiredContractIdButton');	
		var startIdInput=document.getElementById('reservedContractForm:startContractIdInput');
		var endIdInput=document.getElementById('reservedContractForm:endContractIdInput');
		if(options[0].checked){
			popUp.disabled=true; 
			startIdInput.disabled=false; 
			endIdInput.disabled=false; 
		}
		else if(options[1].checked){
			startIdInput.disabled=true; 
			endIdInput.disabled=true; 
			popUp.disabled=false; 
		}
		
	}

</script>
</f:view>
</HTML>
